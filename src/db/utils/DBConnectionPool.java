/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2009 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

package db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

import utils.ErrorLogger;


class ConnectionReaper extends Thread
{

	private DBConnectionPool pool_;
	private final long       delay_ = 30000; // 300000

	ConnectionReaper(DBConnectionPool pool)
	{
		this.pool_ = pool;
	}

	@Override
	public void run( )
	{
		while( true )
		{
			try
			{
				 //System.out.println("Run");
				 
				sleep( delay_ );
			}
			catch( InterruptedException e )
			{
			}
			pool_.reapConnections( );
		}
	}
}

public class DBConnectionPool
{

	private Vector<DBConnection> connections_;
	private String               url_, user_, password_;
	final private long           timeout_                = 60000;
	final private long           idealConnectiontimeOut_ = 60000;
	private ConnectionReaper     reaper_;
	final private int            poolsize_               = 10;

	// private ErrorLogger errLogger_ = null;

	public DBConnectionPool(String url, String user, String password)
	{
		this.url_ = url;
		this.user_ = user;
		this.password_ = password;
		connections_ = new Vector<DBConnection>( poolsize_ );
		reaper_ = new ConnectionReaper( this );
		reaper_.start( );
	}

	public synchronized void reapConnections( )
	{

		//System.out.println( "Reap connection method called" );
		
		long stale = System.currentTimeMillis( ) - timeout_;

		long ideal = System.currentTimeMillis( ) - idealConnectiontimeOut_;

		Enumeration connlist = connections_.elements( );
		

		while( ( connlist != null ) && ( connlist.hasMoreElements( ) ) )
		{
			DBConnection conn = (DBConnection)connlist.nextElement( );
			
			//System.out.println( "conn in use="+conn.inUse( ) );
			
			//System.out.println( "conn get last use="+conn.getLastUse( ) );
			
			//System.out.println( "conn validate="+conn.validate( ) );

			if( ( conn.inUse( ) ) && ( stale > conn.getLastUse( ) ) && ( !conn.validate( ) ) )
			{
				removeConnection( conn );
				
				//System.out.println( "Connection removed from reapconnection: Connection Count="+connections_.size( ) );
			}
			else if( !conn.inUse( ) && ( ideal > conn.getLastUse( ) ) )
			{
				removeConnection( conn );
				
				//System.out.println( "Connection removed from reapconnection: Connection Count="+connections_.size( ) );
			}
		}
	}

	public synchronized void closeConnections( )
	{

		Enumeration connlist = connections_.elements( );

		while( ( connlist != null ) && ( connlist.hasMoreElements( ) ) )
		{
			DBConnection conn = (DBConnection)connlist.nextElement( );
			
			removeConnection( conn );
			
			//System.out.println( "Connction closed: Connection Count="+connections_.size( ) );
		}
	}

	private synchronized void removeConnection( DBConnection conn )
	{
		try
		{
			conn.closeConnection( );
		}
		catch( SQLException ex )
		{
		}
		connections_.remove( conn );
		
		//System.out.println( "Connction Removed: Connection Count="+connections_.size( ) );
	}

	public synchronized Connection getConnection( ) throws SQLException
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );

		DBConnection c;

		for( int i = 0; i < connections_.size( ); i++ )
		{
			c = (DBConnection)connections_.elementAt( i );

			if( c.lease( ) )
			{
				return c;
			}
		}
		try
		{
			Class.forName( "com.mysql.jdbc.Driver" );
		}
		catch( Exception e )
		{

		}

		Connection conn = DriverManager.getConnection( url_, user_, password_ );
		c = new DBConnection( conn, this );
		c.lease( );
		connections_.add( c );
		
		errorLogger.logMsg( " DBConnection  Count : "+connections_.size() );
		
		//System.out.println( "DBConnection count="+connections_.size( ) );
		
		//System.out.println( "Thread id="+Thread.currentThread( ).getId( ) );
		
		//System.out.println( "Thread name="+Thread.currentThread( ).getName( ) );

		return c;
	}

	public synchronized void returnConnection( DBConnection conn )
	{
		conn.expireLease( );
	}
}

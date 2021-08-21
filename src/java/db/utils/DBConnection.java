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

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class DBConnection implements Connection
{

	private DBConnectionPool pool_;
	private Connection       conn_;
	private boolean          inuse_;
	private long             timestamp_;

	public DBConnection(Connection conn, DBConnectionPool pool)
	{
		this.conn_ = conn;
		this.pool_ = pool;
		this.inuse_ = false;
		this.timestamp_ = 0;
	}

	public synchronized boolean lease( )
	{
		if( inuse_ )
		{
			return false;
		}
		else
		{
			
			inuse_ = true;
			timestamp_ = System.currentTimeMillis( );
			return true;
		}
	}

	public boolean validate( )
	{
		try
		{
			conn_.getMetaData( );
		}
		catch( Exception e )
		{
			return false;
		}
		return true;
	}

	public boolean inUse( )
	{
		return inuse_;
	}

	public long getLastUse( )
	{
		return timestamp_;
	}

	public void close( ) throws SQLException
	{
		// conn_.close();

		pool_.returnConnection( this );
	}

	protected void expireLease( )
	{
		inuse_ = false;
	}

	protected Connection getConnection( )
	{
		return conn_;
	}

	public void closeConnection( ) throws SQLException
	{
		conn_.close( );

		// pool_.returnConnection(this);
	}

	public PreparedStatement prepareStatement( String sql ) throws SQLException
	{
		return conn_.prepareStatement( sql );
	}

	public CallableStatement prepareCall( String sql ) throws SQLException
	{
		return conn_.prepareCall( sql );
	}

	public Statement createStatement( ) throws SQLException
	{
		return conn_.createStatement( );
	}

	public String nativeSQL( String sql ) throws SQLException
	{
		return conn_.nativeSQL( sql );
	}

	public void setAutoCommit( boolean autoCommit ) throws SQLException
	{
		conn_.setAutoCommit( autoCommit );
	}

	public boolean getAutoCommit( ) throws SQLException
	{
		return conn_.getAutoCommit( );
	}

	public void commit( ) throws SQLException
	{
		conn_.commit( );
	}

	public void rollback( ) throws SQLException
	{
		conn_.rollback( );
	}

	public boolean isClosed( ) throws SQLException
	{
		return conn_.isClosed( );
	}

	public DatabaseMetaData getMetaData( ) throws SQLException
	{
		return conn_.getMetaData( );
	}

	public void setReadOnly( boolean readOnly ) throws SQLException
	{
		conn_.setReadOnly( readOnly );
	}

	public boolean isReadOnly( ) throws SQLException
	{
		return conn_.isReadOnly( );
	}

	public void setCatalog( String catalog ) throws SQLException
	{
		conn_.setCatalog( catalog );
	}

	public String getCatalog( ) throws SQLException
	{
		return conn_.getCatalog( );
	}

	public void setTransactionIsolation( int level ) throws SQLException
	{
		conn_.setTransactionIsolation( level );
	}

	public int getTransactionIsolation( ) throws SQLException
	{
		return conn_.getTransactionIsolation( );
	}

	public SQLWarning getWarnings( ) throws SQLException
	{
		return conn_.getWarnings( );
	}

	public void clearWarnings( ) throws SQLException
	{
		conn_.clearWarnings( );
	}

	public Struct createStruct( String typeName, Object [ ] attributes )
	        throws SQLException
	{
		return conn_.createStruct( typeName, attributes );
	}

	public Array createArrayOf( String typeName, Object [ ] elements ) throws SQLException
	{
		return conn_.createArrayOf( typeName, elements );
	}

	public Properties getClientInfo( ) throws SQLException
	{
		return conn_.getClientInfo( );
	}

	public String getClientInfo( String name ) throws SQLException
	{
		return conn_.getClientInfo( name );
	}

	public void setClientInfo( String name, String value ) throws SQLClientInfoException
	{
		conn_.setClientInfo( name, value );
	}

	public void setClientInfo( Properties properties ) throws SQLClientInfoException
	{
		conn_.setClientInfo( properties );
	}

	public boolean isValid( int timeout ) throws SQLException
	{
		return conn_.isValid( timeout );
	}

	public SQLXML createSQLXML( ) throws SQLException
	{
		return conn_.createSQLXML( );
	}

	public NClob createNClob( ) throws SQLException
	{
		return conn_.createNClob( );
	}

	public Blob createBlob( ) throws SQLException
	{
		return conn_.createBlob( );
	}

	public Clob createClob( ) throws SQLException
	{
		return conn_.createClob( );
	}

	public PreparedStatement prepareStatement( String sql, String [ ] columnNames )
	        throws SQLException
	{
		return conn_.prepareStatement( sql, columnNames );
	}

	public PreparedStatement prepareStatement( String sql, int [ ] columnIndexes )
	        throws SQLException
	{
		return conn_.prepareStatement( sql, columnIndexes );
	}

	public PreparedStatement prepareStatement( String sql, int autoGeneratedKeys )
	        throws SQLException
	{
		return conn_.prepareStatement( sql, autoGeneratedKeys );
	}

	public CallableStatement prepareCall( String sql, int resultSetType,
	        int resultSetConcurrency, int resultSetHoldability ) throws SQLException
	{
		return conn_.prepareCall( sql, resultSetType, resultSetConcurrency,
		        resultSetHoldability );
	}

	public PreparedStatement prepareStatement( String sql, int resultSetType,
	        int resultSetConcurrency, int resultSetHoldability ) throws SQLException
	{
		return conn_.prepareStatement( sql, resultSetType, resultSetConcurrency,
		        resultSetHoldability );
	}

	public Statement createStatement( int resultSetType, int resultSetConcurrency,
	        int resultSetHoldability ) throws SQLException
	{
		return conn_.createStatement( resultSetType, resultSetConcurrency,
		        resultSetHoldability );
	}

	public void releaseSavepoint( Savepoint savepoint ) throws SQLException
	{
		conn_.releaseSavepoint( savepoint );
	}

	public void rollback( Savepoint savepoint ) throws SQLException
	{
		conn_.rollback( );
	}

	public Savepoint setSavepoint( String name ) throws SQLException
	{
		return conn_.setSavepoint( name );
	}

	public Savepoint setSavepoint( ) throws SQLException
	{
		return conn_.setSavepoint( );
	}

	public int getHoldability( ) throws SQLException
	{
		return conn_.getHoldability( );
	}

	public void setHoldability( int holdability ) throws SQLException
	{
		conn_.setHoldability( holdability );
	}

	public void setTypeMap( Map<String, Class< ? >> map ) throws SQLException
	{
		conn_.setTypeMap( map );
	}

	public Map<String, Class< ? >> getTypeMap( ) throws SQLException
	{
		return conn_.getTypeMap( );
	}

	public CallableStatement prepareCall( String sql, int resultSetType,
	        int resultSetConcurrency ) throws SQLException
	{
		return conn_.prepareCall( sql, resultSetType, resultSetConcurrency );
	}

	public PreparedStatement prepareStatement( String sql, int resultSetType,
	        int resultSetConcurrency ) throws SQLException
	{
		return conn_.prepareCall( sql, resultSetType, resultSetConcurrency );
	}

	public Statement createStatement( int resultSetType, int resultSetConcurrency )
	        throws SQLException
	{
		return conn_.createStatement( resultSetType, resultSetConcurrency );
	}

	public boolean isWrapperFor( Class< ? > iface ) throws SQLException
	{
		return conn_.isWrapperFor( iface );
	}

	public <T> T unwrap( Class<T> iface ) throws SQLException
	{
		return conn_.unwrap( iface );
	}

	


	@Override
    public void abort( Executor arg0 ) throws SQLException
    {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public int getNetworkTimeout( ) throws SQLException
    {
	    // TODO Auto-generated method stub
	    return 0;
    }

	@Override
    public String getSchema( ) throws SQLException
    {
	    // TODO Auto-generated method stub
	    return null;
    }

	@Override
    public void setNetworkTimeout( Executor arg0, int arg1 )
            throws SQLException
    {
	    // TODO Auto-generated method stub
	    
    }

	@Override
    public void setSchema( String arg0 ) throws SQLException
    {
	    // TODO Auto-generated method stub
	    
    }

}

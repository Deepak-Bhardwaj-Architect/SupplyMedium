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
import java.sql.SQLException;
//import zesto.utils.ConfigReader;

import utils.DBConfigReader;

/**
 * File: DBConnect.java
 * 
 * Created on Aug 26, 2009 12:33:08 PM
 * 
 * @author Ilanchezhian
 * 
 */
public class DBConnect
{

	private static DBConnect dbConnect_ = null;

	private DBConnectionPool pool;

	// private static ConfigReader cr_ = null;

	// private static ErrorLogger errLogger_ = null;

	/* Constructor */
	private DBConnect()
	{

		String host = DBConfigReader.instance( ).get( "HOST" );
		String db = DBConfigReader.instance( ).get( "DBNAME" );

		String user = DBConfigReader.instance( ).get( "DBUSER" );

		String pass = "";

		if( DBConfigReader.instance( ).get( "DBPASSWORD" ).equalsIgnoreCase( "none" ) )
		{
			pass = "";
		}
		else
		{
			pass = DBConfigReader.instance( ).get( "DBPASSWORD" );
		}

		String url = "jdbc:mysql://" + host + ":3306/" + db + "?autoReconnect=true";

		pool = new DBConnectionPool( url, user, pass );

	}

	public static DBConnect instance( )
	{
		if( dbConnect_ == null )
		{
			dbConnect_ = new DBConnect( );
		}

		return dbConnect_;
	}

	public Connection getConnection( ) throws SQLException
	{
		if( pool != null )
		{
			return pool.getConnection( );
		}
		return null;
	}

}

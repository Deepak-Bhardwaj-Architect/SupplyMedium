/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

package db.transconfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import core.transconfig.QuantityTypeData;
import db.utils.DBConnect;

/**
 * File:  QuantityTypeTable.java 
 *
 * Created on Jul 11, 2013 5:08:36 PM
 */

public class QuantityTypeTable
{
	private String tableName_;
	
	/*
	 * Method : QuantityTypeTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public QuantityTypeTable( )
	{
		this.tableName_ = "sm_config_quantity_type";
	}
	
	/*
	 * Method : find( )
	 * 
	 * Input : Lit<QuantityTypeData> object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the QuantityTypeData list
	 */

	public int find( List<QuantityTypeData> quantityTypeList )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errMsg 	=  "";

		String str = "SELECT * FROM " + tableName_ ;

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try
		{

			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			rs = stmt.executeQuery( str );

			while( rs.next( ) )
			{
				QuantityTypeData data = new QuantityTypeData( );
				
				data.quantityType_		= rs.getString( "quan_type" );
				data.quantityTypeKey_	= rs.getString( "quan_type_key" );
				
				quantityTypeList.add( data );
				
				data = null;
			}
			
			return 0;
		}
		catch( SQLException ex )
		{
			errMsg = "Exception::QuantityTypeTable.find( ) - "+ex;
			
			errLogger.logMsg( errMsg );
			
			return -1;  // failed
		}
		catch (Exception ex) 
		{
			errMsg = "Exception::QuantityTypeTable.find( ) - "+ex;
			
			errLogger.logMsg( errMsg );
			
			return -2;  // failed
		}
		finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (stmt != null)
                {
                    stmt.close();
                }
                if( con != null )
                {
                    con.close();
                }
           }
            catch (SQLException sQLException)
            {
            }
        }
	}
}

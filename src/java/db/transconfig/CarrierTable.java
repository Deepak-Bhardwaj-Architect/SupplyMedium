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
import core.transconfig.CarrierData;
import db.utils.DBConnect;

/**
 * File:  CarrierTable.java 
 *
 * Created on Jul 11, 2013 5:08:51 PM
 */
public class CarrierTable
{
	private String tableName_;
	
	/*
	 * Method : CarrierTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public CarrierTable( )
	{
		this.tableName_ = "sm_config_carrier";
	}
	
	/*
	 * Method : find( )
	 * 
	 * Input : List<CarrierData> object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the carrier  name list from config table
	 */

	public int find( List<CarrierData> carrierDataList )
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
				CarrierData data = new CarrierData( );
				
				data.carrierName_	= rs.getString( "carrier_name" );
				
				carrierDataList.add( data );
				
				data = null;
			}
			
			return 0;
		}
		catch( SQLException ex )
		{
			errMsg = "Exception::CarrierTable.find( ) - "+ex;
			
			errLogger.logMsg( errMsg );
			
			return  -1;  // failed
		}
		catch (Exception ex) 
		{
			errMsg = "Exception::CarrierTable.find( ) - "+ex;
			
			errLogger.logMsg( errMsg );
			
			return  -2;  // failed
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

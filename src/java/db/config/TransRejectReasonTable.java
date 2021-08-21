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
package db.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import core.common.TransRejectReasonData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  TransRejectReasonTable.java 
 *
 * Created on Jul 10, 2013 12:56:00 PM
 */
public class TransRejectReasonTable
{
	private String tableName_;
	
	/*
	 * Method : TransRejectReasonTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public TransRejectReasonTable()
	{
		this.tableName_ = " sm_config_trans_reject_reasons";
	}
	
	/*
	 * Method : find( )
	 * 
	 * Input : List<TransRejectReasonData> obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get transaction reject reasons found in the 
	 * sm_config_trans_reject_reasons table of supply medium data base
	 */

	public int find( List<TransRejectReasonData> transRejectReasonList )
	{
		String query = "SELECT * FROM " + tableName_;
                ErrorMaster errorMaster_ = null;

                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "State Query=" + query );

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = null;
		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				TransRejectReasonData data = new TransRejectReasonData( );
				
				data.rejectReason_	= rs.getString( "reject_reason" );
				
				transRejectReasonList.add( data );
				
				data = null;
			}
			
			return 0;
		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			
			String msg = "Exception::TransRejectReasonTable.find( ) - "+ex;
			
			errLogger.logMsg(msg);
			
			return -1;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			
			String msg = "Exception::TransRejectReasonTable.find( ) - "+ex;
			
			errLogger.logMsg(msg);
			
			return -2;
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

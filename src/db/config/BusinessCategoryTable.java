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

import core.common.BusinessCategoryData;

import db.utils.DBConnect;
import utils.ErrorLogger;

/**
 * File:  BusinessCategoryTable.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

/*
 * Purpose: It is used to query the business_categories table.
 * 
 */

public class BusinessCategoryTable
{
	private String tableName_;

	/*
	 * Method : BusinessCategoryTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public BusinessCategoryTable()
	{
		this.tableName_ = "business_categories";
	}

	/*
	 * Method : getAllBusinessCategories( )
	 * 
	 * Input : List<BusinessCategoryData> businessCatDataList
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the business categories from supplymedium and
	 * assign to businessCatDataList parameter so it is copied to caller method. 
	 */

	public int getAllBusinessCategories( List<BusinessCategoryData> businessCatDataList )
	{
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String errMsg = "";

		String str = "SELECT * FROM " + tableName_;

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
				BusinessCategoryData businessCatDataObj = new BusinessCategoryData( );

				businessCatDataObj.businessCategory_ = rs.getString( "business_category_name" );

				businessCatDataList.add( businessCatDataObj );
				
				businessCatDataObj = null;
			}
		}
		catch( SQLException ex )
		{
			errMsg = "Exception::BusinessCategoryTable: getAllBusinessCategories-"+ex;
			
			errLogger.logMsg( errMsg );
			
			return -1;  // failed
		}
		catch (Exception ex) 
		{
			errMsg = "Exception::BusinessCategoryTable:getAllBusinessCategories-"+ex;
			
			errLogger.logMsg( errMsg );
			
			return -1;  // failed
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
		
		return 0;  // success
	}
}

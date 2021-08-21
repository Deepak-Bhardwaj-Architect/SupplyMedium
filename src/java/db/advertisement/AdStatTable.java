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
package db.advertisement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ErrorLogger;
import core.advertisement.AdStatisticData;

import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  AdStatTable.java 
 *
 * Created on Oct 7, 2013 5:48:06 PM
 */
public class AdStatTable
{
	
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/*
	 * Method : AdStatTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public AdStatTable()
	{
		
		this.tableName_ = "ad_statistic";
			
		errLogger_ = ErrorLogger.instance( );
		
	}
	

	/*
	 * Method : insert( )
	 * 
	 * Input : AdStatisticData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the new Advertisement to Adstat table.
	 */
	
	
	public int insert( AdStatisticData statisticData)
	{
		AdStatTableUtils utils = new AdStatTableUtils( );

		AdStatRecord  record = new AdStatRecord( );
		
		int result = utils.dataToRecord( statisticData, record );
		
		if( result != 0 )
		{
			utils = null; 
			
			record = null;
			
			return result;
		}
		
		String insertQuery = utils.formInsertQuery( record );
		
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        result	= stmt.executeUpdate( insertQuery);
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::AdStatTable.insert( AdStatisticData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::AdStatTable.insert( AdStatisticData ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				utils = null; record = null;
				
	            if( con != null )
	            {
	            	con.close();
	            }
	            if( stmt != null )
	            {
	            	stmt.close( );
	            }
            }
            catch( Exception e2 )
            {
	            
            }
		}
	}
	
	
	
	/*
	 * Method : isRecordExist( )
	 * 
	 * Input : AdStatisticData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is check the given record is available or not
	 */
	
    public int isRecordExist( AdStatisticData statisticData )
    {
    	    		
    		Statement stmt = null;
    		Connection con = null;
    		String query = null;

    		AdStatTableUtils utils = new AdStatTableUtils( );
    		
    		AdStatRecord  record = new AdStatRecord( );
    		
    		//Convert the AdStatisticData to adRecord
    		//( using AdStatTableUtils->dataToRecord method )
    		
    		int result = utils.dataToRecord( statisticData, record );
    		
    		if( result != 0)
    		{
    			utils = null; record = null;
    			
    			return result;
    		}
    		

    		query = "SELECT * FROM " + tableName_ + " WHERE date= '" + record.date_+"' AND " +
    				"ad_rel_id='"+record.adId_ +"'";
    		 ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
    		errorMaster_.insert( "query="+query );
    	
    		
    		try
    		{
    			con = DBConnect.instance( ).getConnection( );

    			stmt = con.createStatement( );

    			ResultSet rs = stmt.executeQuery( query );

    			while( rs.next( ) )
    			{
    				int count=rs.getInt( "count" );
    				
    				statisticData.count_=count;
    				return 1;  // Record already exist
    			}

    			return 0;  // Record not exist
    			
    		}
    		catch( SQLException ex )
    		{
    			ErrorLogger errLogger = ErrorLogger.instance( );

    			String errorMessage = "Exception :: AdStatTable : isExist - " + ex;

    			errLogger.logMsg( errorMessage );

    			return -1; // SqlException
    		}
    		finally
            {
                try
                {
                    
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


	
    
	/*
	 * Method : update( )
	 * 
	 * Input : AdStatisticData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is update the  count value
	 */
	
    public int update( AdStatisticData statisticData )
    {
    	
    	AdStatTableUtils utils = new AdStatTableUtils( );

		AdStatRecord  record = new AdStatRecord( );
		
		int result = utils.dataToRecord( statisticData, record );
		
		if( result != 0 )
		{
			utils = null; 
			
			record = null;
			
			return result;
		}
		
		String updateQuery = "UPDATE" +" "+ this.tableName_+" "+"set count='"+statisticData.count_+"'"+ "WHERE  ad_rel_id='"+statisticData.adId_+"'";
		
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        result	= stmt.executeUpdate( updateQuery);
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::AdStatTable.update( AdStatisticData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::AdStatTable.update( AdStatisticData ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				utils = null; record = null;
				
	            if( con != null )
	            {
	            	con.close();
	            }
	            if( stmt != null )
	            {
	            	stmt.close( );
	            }
            }
            catch( Exception e2 )
            {
	            
            }
		}
    	   
    }

}

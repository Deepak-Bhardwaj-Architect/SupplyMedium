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
package db.history;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.history.TransRatingsData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  TransactionRatingTable.java 
 *
 * Created on Sep 26, 2013 3:24:10 PM
 */
public class TransactionRatingTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;
	
	private ErrorLogger errLogger_;
	

	/*
	 * Method : TransactionRatingTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransactionRatingTable()
    
	{
		

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "transaction_rating";
			
		errLogger_ = ErrorLogger.instance( );
		
	}

    	/*
    	 * Method : insert( )
    	 * 
    	 * Input : TransRatingsData obj
    	 * 
    	 * Return : int
    	 * 
    	 * Purpose:It is used to insert the new rating to  TransactionRating table.
    	 */

    	public int insert( TransRatingsData ratingsData )
        {
    		errorMaster_.insert( "db insert");
        	TransactionRatingTableUtils utils=new TransactionRatingTableUtils( );
        	
        	TransactionRatingRecord record=new TransactionRatingRecord( );
        	
    	    int result = utils.dataToRecord( ratingsData, record );
    		
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
    	        result	= stmt.executeUpdate( insertQuery );
    	        
    	        return 0;
            }
    		catch( SQLException e )
            {
            	errLogger_.logMsg( "Exception::TransactionRatingTable.insert( TransRatingsData ) - " + e );
    			
    			return -2;
            }
            catch( Exception e )
            {
            	errLogger_.logMsg( "Exception::TransactionRatingTable.insert( TransRatingsData ) - " + e );
    			
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
		 * Method : find( )
		 * 
		 * Input : TransRatingsData obj, 
		 * 
		 * Return : int
		 * 
		 * Purpose: It is used to fetch the all the TransRatingsData according to given TransReminderData object.
		 * After fetch the TransReminderData it add into ratings. So it is copied to caller classes.
		 */

		
        public int find( TransRatingsData transRatingsData, List<TransRatingsData> ratings )
        {
           
        	TransactionRatingTableUtils utils = new TransactionRatingTableUtils( );
    		
    		TransactionRatingRecord record = new TransactionRatingRecord( );
    		
    		int result = utils.dataToRecord( transRatingsData, record );
    	
    		StringHolder query=new StringHolder( );
    		
    		query.value="";
    		
    		result=utils.getFilterString( record, query );
    		
    		String selectQuery = "SELECT * FROM " + this.tableName_ ;
    		
    		selectQuery=selectQuery+query.value;
    		
    		errorMaster_.insert( "select Query="+selectQuery );
    		
    		if( result != 0)
    		{
    			utils = null; record = null;
    			
    			return result;
    		}
    		
    		
    		if( result != 0 )
    		{
    			utils = null; record = null;
    			
    			return result;
    		}
    		
    		Statement statement = null;
    		Connection con		= null;
    		ResultSet rs		= null;
    		
    		
    		
    		errorMaster_.insert( "Query="+selectQuery );
    		
    		
    		try
            {
    			con 		= DBConnect.instance( ).getConnection( );
    			statement 	= con.createStatement( );
    			rs			= statement.executeQuery( selectQuery );
    			
    			
    			
    			//convert result to connections
    			result = utils.rsToDataList( rs, ratings );
    			
    			utils = null; record = null; 
    			
    			return result;
            }
    		catch( SQLException e )
    		{
    			errLogger_.logMsg( "Exception::TransactionRatingTable.find() - " + e );
    			
    			return -2;
    		}
            catch( Exception e )
            {
            	errLogger_.logMsg( "Exception::TransactionRatingTable.find() - " + e );
    			
    			return -3;
            }
    		finally
            {
                try
                {
                	utils = null; record = null; 
                	
                    if (statement != null)
                    {
                    	statement.close();
                    }
                    if( con != null )
                    {
                        con.close();
                    }
                    if( rs != null )
                    {
                    	rs.close( );
                    }
               }
               catch (SQLException sQLException)
               {
               }
            }
    	}

        /*
    	 * Method : isRatingExist( )
    	 * 
    	 * Input : TransRatingsData object
    	 * 
    	 * Return : int
    	 * 
    	 * Purpose: It is check the given record is available or not
    	 */
        public int isRatingExist( TransRatingsData ratingsData )
        {
        	Statement stmt = null;
    		Connection con = null;
    		String query = null;

    		TransactionRatingTableUtils utils = new TransactionRatingTableUtils( );
    		
    		TransactionRatingRecord  record = new TransactionRatingRecord( );
    		
    		//Convert the TransRatingsData to transactionRatingRecord
    		//( using TransactionRatingTableUtils->dataToRecord method )
    		
    		int result = utils.dataToRecord( ratingsData, record );
    		
    		if( result != 0)
    		{
    			utils = null; record = null;
    			
    			return result;
    		}
    		

    		query = "SELECT * FROM " + tableName_ + " WHERE trans_rel_id= '" + record.transId_+"' AND " +
    				"from_regn_key='"+record.regnKey_ +"' AND "+"to_regn_key='"+record.customerKey_+"'";
    		
    		errorMaster_.insert( "query="+query );
    	
    		
    		try
    		{
    			con = DBConnect.instance( ).getConnection( );

    			stmt = con.createStatement( );

    			ResultSet rs = stmt.executeQuery( query );

    			while( rs.next( ) )
    			{
    				return 1;  // Record already exist
    			}

    			return 0;  // Record not exist
    			
    		}
    		catch( SQLException ex )
    		{
    			ErrorLogger errLogger = ErrorLogger.instance( );

    			String errorMessage = "Exception :: TransactionRatingTable : isExist - " + ex;

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
    	 * Input : TransRatingsData object
    	 * 
    	 * Return : int
    	 * 
    	 * Purpose: It is update the  count value
    	 */
        public int update( TransRatingsData ratingsData )
        {
        	
        	TransactionRatingTableUtils utils = new TransactionRatingTableUtils( );

        	TransactionRatingRecord  record = new TransactionRatingRecord( );
    		
    		int result = utils.dataToRecord( ratingsData, record );
    		
    		if( result != 0 )
    		{
    			utils = null; 
    			
    			record = null;
    			
    			return result;
    		}
    		
    		String updateQuery = "UPDATE" +" "+ this.tableName_+" "+"set star='"+ratingsData.starCount_+"'"+ "WHERE  trans_rel_id='"+ratingsData.transId_+"'";
    		
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
            	errLogger_.logMsg( "Exception::TransactionRatingTable.update( AdStatisticData ) - " + e );
    			
    			return -2;
            }
            catch( Exception e )
            {
            	errLogger_.logMsg( "Exception::TransactionRatingTable.update( AdStatisticData ) - " + e );
    			
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
     


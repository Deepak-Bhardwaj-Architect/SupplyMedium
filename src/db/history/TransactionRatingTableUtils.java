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

import java.sql.ResultSet;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.history.TransRatingsData;

/**
 * File:  TransactionRatingTableUtils.java 
 *
 * Created on Sep 26, 2013 5:36:41 PM
 */
public class TransactionRatingTableUtils
{
private String tableName_;
	
	private ErrorLogger errLogger_;
	
	

	/*TransactionRating table fields
	 * trans_rel_id
	 * from_regn_key
	 * to_regn_key
	 * star
	 * created_timestamp
	 */
	
	

	/*
	 * Method : TransactionRatingTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TransactionRatingTableUtils(  )
	{
		this.tableName_ = "transaction_rating";
		
		errLogger_ = ErrorLogger.instance( );
	}

	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : TransRatingsData object to TransactionRatingRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the TransRatingData object to
	 * TransactionRatingRecord object. And add to transactionRatingRecord parameter so it is copied
	 * to caller method.
	 */
    public int dataToRecord( TransRatingsData ratingsData,
            TransactionRatingRecord transactionRatingRecord )
    {
    	try
    	{
    		transactionRatingRecord.transRatingId_ 		= ratingsData.transRatingId_;
    		
    		transactionRatingRecord.transId_ 			= ratingsData.transId_;
		
    		transactionRatingRecord.regnKey_			= ratingsData.regnKey_.toString( );
		
    		transactionRatingRecord.customerKey_		= ratingsData.customerKey_.toString( );
		
    		transactionRatingRecord.starCount_			= ratingsData.starCount_;
		
    	
		
    		
    		return 0;
		
    	}
    	catch( Exception e )
    	{
    		errLogger_.logMsg( "Exception::TransactionRatingTableUtils.dataToRecord( ) - " + e );
    	
    		return -1;
    	}
    }
    
    
    /*
	 * Method : formInsertQuery( )
	 * 
	 * Input :TransactionRatingRecord object
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using TransactionRatingRecord and returns as
	 * string
	 */

	
    	public String formInsertQuery( TransactionRatingRecord transactionRatingRecord )
    	{
    		String query = "INSERT INTO " + this.tableName_ + "(";
    		query		 = query + "trans_rel_id,from_regn_key,to_regn_key,star) ";
    		query		 = query + " VALUES";
    		query		 = query + "(";
    		
    		
    	
    		query		 = query + "'" + transactionRatingRecord.transId_ +"',";
    		query		 = query + "'" + transactionRatingRecord.regnKey_ +"',";
    		query		 = query + "'" + transactionRatingRecord.customerKey_  + "', ";
    		query		 = query + "'" + transactionRatingRecord.starCount_ +"'";
    		
    		    		
    		
    		
    		query 	  	 = query + ")";
     		
    		System.out.println( "Query = " + query );
    		
    		return query;
    	}
    	
    	/*
    	 * Method : getFilterString( )
    	 * 
    	 * Input :	TransactionRatingRecord filter, StringHolder query
    	 * 
    	 * Return : int
    	 * 
    	 * Purpose: Using the TransactionRatingRecord it form the filter query and add to string holder
    	 * parameter so it is copied to caller class.
    	 */

    	public int getFilterString( TransactionRatingRecord filter, StringHolder query )
    	{
    			try
    	        {
    			query.value += filter.transRatingId_ != -1 ? 
    						" trans_rating_id='" + filter.transRatingId_ + "' AND" : "";
    				
    			query.value += filter.transId_ != -1 ? 
    						" trans_rel_id='" + filter.transId_ + "' AND" : "";
    		
    		
    			query.value += filter.regnKey_ != null ? 
    							" from_regn_key='" + filter.regnKey_ + "' AND" : "";
    			

    			query.value += filter.customerKey_ != null ? 
    							" to_regn_key='" + filter.customerKey_+ "' AND" : "";
    			

    			query.value += filter.starCount_ != 0? 
    							" star='" + filter.starCount_ + "' AND" : "";
    			
    			
    			
    			

    			if( !query.value.equalsIgnoreCase( "" ) )
    			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );
    			
    		
    			return 0;
    	        }
    			catch( Exception ex )
    			{
    				ErrorLogger errLogger_ = ErrorLogger.instance( );
    				errLogger_.logMsg( "Exception::TransactionRatingTableUtils.getFilterString( ) - " + ex );
    				return -1;
    			}
    		}

    	/*
    	 * Method : rsToDataList( )
    	 * 
    	 * Input :  ResultSet,ratings
    	 * 
    	 * Return : int
    	 * 
    	 * Purpose: It is used to convert the ResultSet object to List<TransRatingsData> object.
    	 * And add to remainders parameter so it is copied to caller classes.
    	 */
        public int rsToDataList( ResultSet rs, List<TransRatingsData> ratings )
        {
        	try
            {
    			while( rs.next( ) )
    			{
    				TransRatingsData transRatingsData= new TransRatingsData( );

    				transRatingsData.transRatingId_					= rs.getInt( "trans_rating_id" );
    				transRatingsData.transId_ 						= rs.getInt( "trans_rel_id" );
    				transRatingsData.customerKey_.companyPhoneNo_	= rs.getString( "to_regn_key" );
    				transRatingsData.regnKey_.companyPhoneNo_		= rs.getString( "from_regn_key" );
    				transRatingsData.starCount_						= rs.getInt( "star" );
    				
    				ratings.add( transRatingsData );

    				transRatingsData = null;
    			}
    			return 0;
            }
            catch( Exception e )
            {
            	ErrorLogger errLogger_ = ErrorLogger.instance( );
    			errLogger_.logMsg( "Exception::TransactionsRatingTableUtils.rsToDataList() - " + e );
    			return -1;
            }
    	}
}

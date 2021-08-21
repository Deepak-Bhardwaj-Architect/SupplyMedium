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
import core.history.TransReminderData;

/**
 * File:  TransactionRemainderTableUtils.java 
 *
 * Created on Sep 26, 2013 4:04:12 PM
 */
public class TransactionRemainderTableUtils
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	

	/*TransactionRemainder table fields
	 * trans_rel_id
	 * from_regn_key
	 * to_regn_key
	 * remainder
	 * due_date
	 * created_timestamp
	 */
	
	

	/*
	 * Method : TransactionRemainderTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TransactionRemainderTableUtils(  )
	{
		this.tableName_ = "transaction_remainder";
		
		errLogger_ = ErrorLogger.instance( );
	}

	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : TransReminderData object to TransactionRemainderRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the TransReminderData object to
	 * TransactionRemainderRecord object. And add to transactionRemainderRecord parameter so it is copied
	 * to caller method.
	 */
    public int dataToRecord( TransReminderData reminderData,
            TransactionRemainderRecord transactionRemainderRecord )
    {
    	try
    	{
    		transactionRemainderRecord.transRemainderId_  	= reminderData.transRemainderId_;
    		
    		transactionRemainderRecord.transId_ 			= reminderData.transId_;
    		
    		if( reminderData.regnKey_ != null )
    			transactionRemainderRecord.regnKey_			= reminderData.regnKey_.toString( );
    		
    		if( reminderData.customerKey_ != null )
    			transactionRemainderRecord.customerKey_		= reminderData.customerKey_.toString( );
		
    		transactionRemainderRecord.remainder_			= reminderData.remainder_;
		
    		transactionRemainderRecord.dueDate_				= reminderData.dueDate_;
		
    		
    		return 0;
		
    	}
    	catch( Exception e )
    	{
    		errLogger_.logMsg( "Exception::TransactionRemainderTableUtils.dataToRecord( ) - " + e );
    	
    		return -1;
    	}
    }
    
    
    /*
	 * Method : formInsertQuery( )
	 * 
	 * Input :TransactionRemainderRecord object
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using TransactionRemainderRecord and returns as
	 * string
	 */

	
    	public String formInsertQuery( TransactionRemainderRecord transactionRemainderRecord )
    	{
    		String query = "INSERT INTO " + this.tableName_ + "(";
    		query		 = query + "trans_rel_id,from_regn_key,to_regn_key,remainder,due_date) ";
    		query		 = query + " VALUES";
    		query		 = query + "(";
    		
    		query		 = query + "'" + transactionRemainderRecord.transId_ +"',";
    		query		 = query + "'" + transactionRemainderRecord.regnKey_ +"',";
    		query		 = query + "'" + transactionRemainderRecord.customerKey_  + "', ";
    		query		 = query + "'" + transactionRemainderRecord.remainder_ +"',";
    		query		 = query + "'" + transactionRemainderRecord.dueDate_ +"'";
    		    		
    		
    		
    		query 	  	 = query + ")";
     		
    		System.out.println( "Query = " + query );
    		
    		return query;
    	}
    	
    	/*
    	 * Method : getFilterString( )
    	 * 
    	 * Input :	TransactionRemainderRecord filter, StringHolder query
    	 * 
    	 * Return : int
    	 * 
    	 * Purpose: Using the TransactionRemainderRecord it form the filter query and add to string holder
    	 * parameter so it is copied to caller class.
    	 */

    	public int getFilterString( TransactionRemainderRecord filter, StringHolder query )
    	{
    			try
    	        {
    		    
    			query.value += filter.transRemainderId_ != -1 ? 
    						" trans_remainder_id='" + filter.transRemainderId_ + "' AND" : "";
    				
    			query.value += filter.transId_ != -1 ? 
    						" trans_rel_id='" + filter.transId_ + "' AND" : "";
    		
    		
    			query.value += filter.regnKey_ != null ? 
    							" from_regn_key='" + filter.regnKey_ + "' AND" : "";
    			

    			query.value += filter.customerKey_ != null ? 
    							" to_regn_key='" + filter.customerKey_+ "' AND" : "";
    			

    			query.value += filter.remainder_ != null ? 
    							" remainder='" + filter.remainder_ + "' AND" : "";
    			
    			query.value += filter.dueDate_ != null ? 
    					"due_date='" + filter.dueDate_ + "' AND" : "";
    			
    			

    			if( !query.value.equalsIgnoreCase( "" ) )
    			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );
    			
    		
    			return 0;
    	        }
    			catch( Exception ex )
    			{
    				ErrorLogger errLogger_ = ErrorLogger.instance( );
    				errLogger_.logMsg( "Exception::TransactionRemainderTableUtils.getFilterString( ) - " + ex );
    				return -1;
    			}
    		}

    	/*
    	 * Method : rsToDataList( )
    	 * 
    	 * Input :  ResultSet,remainders
    	 * 
    	 * Return : int
    	 * 
    	 * Purpose: It is used to convert the ResultSet object to List<TransReminderData> object.
    	 * And add to remainders parameter so it is copied to caller classes.
    	 */
        public int rsToDataList( ResultSet rs, List<TransReminderData> remainders )
        {
        	try
            {
    			while( rs.next( ) )
    			{
    				TransReminderData transReminderData= new TransReminderData( );
    				
    				transReminderData.transRemainderId_	 			= rs.getInt( "trans_remainder_id" );
    				transReminderData.transId_ 						= rs.getInt( "trans_rel_id" );
    				transReminderData.customerKey_.companyPhoneNo_	= rs.getString( "to_regn_key" );
    				transReminderData.regnKey_.companyPhoneNo_		= rs.getString( "from_regn_key" );
    				transReminderData.remainder_					= rs.getString( "remainder" );
    				transReminderData.dueDate_  					= rs.getDate( "due_date" );
    				
    				remainders.add( transReminderData );

    				transReminderData = null;
    			}
    			return 0;
            }
            catch( Exception e )
            {
            	ErrorLogger errLogger_ = ErrorLogger.instance( );
    			errLogger_.logMsg( "Exception::TransactionsRemainderTableUtils.rsToDataList() - " + e );
    			return -1;
            }
    	}
    	
    	
}



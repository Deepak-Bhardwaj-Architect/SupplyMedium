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

import core.dashboard.CustomerBasedData;
import core.dashboard.TimeBasedData;
import core.history.TransactionHistoryData;


/**
 * File:  TransactionHistoryTableUtils.java 
 *
 * Created on Sep 27, 2013 9:58:35 AM
 */
public class TransactionHistoryTableUtils
{

	private String tableName_;
	
	private ErrorLogger errLogger_;

	
	
	/*
	 * transaction_history table fields
	 *trans_rel_id
	 *from_regn_key
	 *to_regn_key
	 *trans_date
	 *amount
	 *status
	 *created_timestamp
	 * 
	 */
	

	/*
	 * Method : TransactionHistoryTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransactionHistoryTableUtils(  )
	{
		this.tableName_ = "transaction_history";
		
		errLogger_ = ErrorLogger.instance( );
	}

	
	
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : TransactionHistoryData obj, TransactionHistoryRecord obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the TransactionHistoryData object  to
	 * TransactionHistoryRecord object. And add to TransactionHistoryRecord parameter so it is copied
	 * to caller method.
	 */

	
    public int dataToRecord(TransactionHistoryData transactionHistoryData ,
            TransactionHistoryRecord transactionHistoryRecord )
    {
    	
    	try
        {
    		transactionHistoryRecord.fromRegnKey_	= transactionHistoryData.fromRegnKey_.toString( );
    		
    		transactionHistoryRecord.toRegnKey_		= transactionHistoryData.toRegnKey_.toString( );
    		
    		transactionHistoryRecord .transId_		= transactionHistoryData.transId_;
    		
    		transactionHistoryRecord.amount_		= transactionHistoryData.amount_;
    		
    		transactionHistoryRecord.status_		= transactionHistoryData.status_;
    		
    		transactionHistoryRecord.currency_		= transactionHistoryData.currency_;
    		
    		return 0;
  		
        }
        catch(  Exception e )
        {
        	
        		errLogger_.logMsg( "Exception::TransactionHistoryTableUtils.dataToRecord( ) - " + e );
        	
        		return -1;
        }
		
    }
    
    
    public int dataToTimeRecord(TimeBasedData timeBasedData,TransactionHistoryRecord transactionHistoryRecord)
    {
    	try
        {
    		transactionHistoryRecord.fromRegnKey_    =timeBasedData.regnKey_.toString( );
    		
    		transactionHistoryRecord.toRegnKey_		=timeBasedData.regnKey_.toString( );
    		
    		transactionHistoryRecord.amount_	=timeBasedData.transactionAmount_;
     		
    		//transactionHistoryRecord.currencyType	=timeBasedData.currencyType_;
    			
    		return 0;
	        
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransactionHistoryTableUtils.dataToTimeRecord( ) - " + e );
        	
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

	
    	public String formInsertQuery( TransactionHistoryRecord record )
    	{
    		String query = "INSERT INTO " + this.tableName_ + "(";
    		query		 = query + "trans_rel_id,from_regn_key,to_regn_key,amount,status,currency) ";
    		query		 = query + " VALUES";
    		query		 = query + "(";
    		
    		query		 = query + "'" + record.transId_ +"',";
    		query		 = query + "'" + record.fromRegnKey_ +"',";
    		query		 = query + "'" + record.toRegnKey_  + "', ";
    		query		 = query + "'" + record.amount_ +"',";
    		query		 = query + "'" + record.status_ +"',";
    		query		 = query + "'" + record.currency_ +"'";
    		    		
    		
    		
    		query 	  	 = query + ")";
     		
    		System.out.println( "Query = " + query );
    		
    		return query;
    	}
    	

	
    public int getFilterString( TransactionHistoryRecord filter, StringHolder query )
    {
    	try
        {
	    
		query.value += filter.transId_ != -1 ? 
					" trans_rel_id='" + filter.transId_ + "' AND" : "";
		

		query.value += filter.amount_ != 0.00 ? 
						" amount='" + filter.amount_ + "' AND" : "";
		
		query.value += filter.status_ != null ? 
				"status='" + filter.status_ + "' AND" : "";
		
				

		if( !query.value.equalsIgnoreCase( "" ) )
		    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );
		
	
		return 0;
        }
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransactionHistoryTableUtils.getFilterString( ) - " + ex );
			return -1;
		}
    }



    public int rsToDataList( ResultSet rs,
            List<TransactionHistoryData> transHistoryDataList )
    {
    	try
        {
			while( rs.next( ) )
			{
				TransactionHistoryData transactionHistoryData=new TransactionHistoryData( );
				

				transactionHistoryData.transId_ 				= rs.getInt( "trans_rel_id" );
				transactionHistoryData.amount_					= rs.getDouble( "amount" );
				transactionHistoryData.status_					= rs.getString( "status" );
				transactionHistoryData.createdTimestamp_		= rs.getTimestamp( "created_time_stamp" );
				
				transHistoryDataList.add( transactionHistoryData );

				transactionHistoryData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransactionsHistoryTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
    
    
    
    
    public int rsToTimeBasedDataList(ResultSet rs,List<TimeBasedData>timebasedList)
    {
    	try
        {
	        while(rs.next( ) )
	        {
	        	TimeBasedData timeBasedData			=new TimeBasedData( );
	        	
	        	timeBasedData.timeLabel_			=rs.getString( "lbl" );
	        	
	        	timeBasedData.transactionAmount_	=rs.getDouble( "amount" );
	        	
	        	timeBasedData.currencyType_			=rs.getString( "currency" );

	        	
	        	timebasedList.add(timeBasedData);
	        	
	        	timeBasedData=null;
	        	
	        }
	        return 0;
        }
        catch(  Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransactionsHistoryTableUtils.rsToTimeData() - " + e );
			return -1;
        }
    	
    }
    
    
    
    public int rsToCustomerBasedDataList(ResultSet rs,List<CustomerBasedData>timebasedList)
    {
    	try
        {
	        while(rs.next( ) )
	        {
	        	CustomerBasedData customerBasedData				= new CustomerBasedData( );
	        	
	        	customerBasedData.transactionAmount_			= rs.getDouble( "amount" );
	        	
	        	customerBasedData.regnKey_.companyPhoneNo_		= rs.getString( "from_regn_key" );
	        	
	        	customerBasedData.toRegnKey_.companyPhoneNo_	= rs.getString( "to_regn_key" );
	        	
	        	customerBasedData.currencyType_					= rs.getString( "currency" );
	        	
	        	timebasedList.add( customerBasedData );
	        	
	        	customerBasedData = null;
	        	
	        }
	        return 0;
        }
        catch(  Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
        	
			errLogger_.logMsg( "Exception::TransactionsHistoryTableUtils.rsToTimeData() - " + e );
			
			return -1;
        }
    	
    }
	    
}
	

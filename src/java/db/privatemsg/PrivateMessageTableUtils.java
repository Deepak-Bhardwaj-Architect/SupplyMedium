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
package db.privatemsg;

import java.sql.ResultSet;
import java.util.List;

import core.privatemsg.PrivateMessageData;


import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.StringHolder;



/**
 * File:  PrivateMessageTableUtils.java 
 *
 * Created on Sep 20, 2013 12:02:15 PM
 */
public class PrivateMessageTableUtils
{

	private String tableName_;
	
	private ErrorLogger errLogger_;

	
	
	/*
	 * private_message table fields
	 * 
	 * message_id
	 * message
	 * from_user_key
	 * to_user_key
	 * created_timestamp
	 */
	

	/*
	 * Method : PrivateMessageTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public PrivateMessageTableUtils(  )
	{
		this.tableName_ = "private_message";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : PrivateMessageData object to PrivateMessageRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the PrivateMessageData object to
	 * PrivateMessageRecord object. And add to privateMessageRecord parameter so it is copied
	 * to caller method.
	 */
	
	
    public int dataToRecord( PrivateMessageData privateMessageData, PrivateMessageRecord  privateMessageRecord )
    {
    	
    	try
        {
			privateMessageRecord.messageId_ 	= privateMessageData.messageId_;
			
			privateMessageRecord.message				= privateMessageData.message;
			
			privateMessageRecord.fromUserKey_ 		= privateMessageData.fromUserKey_.toString( );
			
			privateMessageRecord.toUserKey_				=privateMessageData.toUserKey_.toString();		
			
			privateMessageRecord.createdTimestamp_ = privateMessageData.createdTimestamp_;
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::PrivateMessageTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
    /*
	 * Method : formInsertQuery( )
	 * 
	 * Input :PrivateMessageRecord object
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using PrivateMessageRecord and returns as
	 * string
	 */
	 
    

	
    public String formInsertQuery( PrivateMessageRecord privateMessageRecord )
    {
    ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
    		String query = "INSERT INTO " + this.tableName_ + "(";
    		query		 = query + " message,from_user_key,to_user_key) ";
    		query		 = query + " VALUES";
    		query		 = query + "(";
    		
    		
			query		 = query + "'" + privateMessageRecord.message  + "', ";
    		query		 = query + "'" + privateMessageRecord.fromUserKey_ +"',";
    		query		 = query + "'" + privateMessageRecord.toUserKey_ +"'";
    		
    		
    		query 	  	 = query + ")";
     		
    		errorMaster_.insert( "Query = " + query );
    		
    		return query;
    	}
	
    /*
	 * Method : getFilterString( )
	 * 
	 * Input :	PrivateMessageRecord filter, StringHolder query
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the PrivateMessageRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
    
    public int getFilterString( PrivateMessageRecord filter, StringHolder query )
    {
    	
		try
		{
			query.value += filter.messageId_ != -1 ? " messageId_= '" + filter.messageId_
			        + "' AND" : "";

			query.value += filter.message != null ? " messages='" + filter.message
			        + "' AND" : "";

			query.value += filter.fromUserKey_ != null ? " fromUserKey='"
			        + filter.fromUserKey_ + "' AND" : "";

			query.value += filter.toUserKey_ != null ? "toUserKey='" + filter.toUserKey_
			        + "' AND" : "";

			query.value += filter.createdTimestamp_ != null ? " createdTimestamp_='"
			        + filter.createdTimestamp_ + "' AND" : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where "
			            + query.value.substring( 0, query.value.length( ) - 3 );

		}
		catch( Exception e )
		{
			errLogger_.logMsg( "Exception::PrivateMessageTableUtils.getFilterString() - " + e );
			
			return -1;
		}
		
			return 0;
	}
		
    
    /*
	 * Method : rsToDataList( )
	 * 
	 * Input :  ResultSet,messages
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the ResultSet object to List<PrivateMessageData> object.
	 * And add to messages parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<PrivateMessageData> messages )
	{
		try
        {
			while( rs.next( ) )
			{
				PrivateMessageData privateMessageData = new PrivateMessageData( );

				privateMessageData.messageId_ 			= rs.getInt( "message_id" );
				privateMessageData.message 				= rs.getString( "message" );
				privateMessageData.fromUserKey_.email_ 	= rs.getString( "from_user_key" );
				privateMessageData.toUserKey_.email_ 	= rs.getString( "to_user_key" );
				privateMessageData.createdTimestamp_ 	= rs.getTimestamp( "created_timestamp" );

				messages.add( privateMessageData );

				privateMessageData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::PrivateMessageTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}

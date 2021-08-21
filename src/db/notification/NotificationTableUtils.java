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
package db.notification;

import java.sql.ResultSet;
import java.util.List;


import core.notification.NotificationCenterData;
import utils.ErrorLogger;
import utils.StringHolder;

/**
 * File:  NotificationTableUtils.java 
 *
 * Created on Sep 24, 2013 2:04:28 PM
 */
public class NotificationTableUtils
{


	private String tableName_;
	
	private ErrorLogger errLogger_;

	
	
	/*
	 * notification table fields
	 *
	 * notification_id
	 *receiver_key
	 * receiver_regn_key
	 * sender_key
	 * sender_ regn_key
	 * notification _type
	 * notification_ type_id
	 * notification _message
	 * created_timestamp
	 */
	

	/*
	 * Method : NotificationTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public NotificationTableUtils(  )
	{
		this.tableName_ = "notification";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : NotificationCenterData object to NotificationRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the NotificationCenterData object to
	 * NotificationRecord object. And add to notificationRecord parameter so it is copied
	 * to caller method.
	 */
	
	
    public int dataToRecord( NotificationCenterData notificationCenterData, NotificationRecord  notificationRecord )
    {
    	
    	try
        {
    		notificationRecord.notificationId_ 			= notificationCenterData.notificationId_;
			
    		notificationRecord.receiverKey_				= notificationCenterData.receiverKey_.toString( );
			
    		notificationRecord.receiverRegnKey_		= notificationCenterData.receiverRegnKey_.toString( );
    		
    		notificationRecord.senderKey_				= notificationCenterData.senderKey_.toString( );
			
    		notificationRecord.senderRegnKey_		= notificationCenterData.senderRegnKey_.toString( );
    		
    		notificationRecord.notificationType_		= notificationCenterData.notificationType_;
    		
    		notificationRecord.notificationTypeId_		= notificationCenterData.notificationTypeId_;
    		
    		notificationRecord.notificationMessage_	= notificationCenterData.notificationMessage_;
			
    		notificationRecord.createdTimestamp_ 	= notificationCenterData.createdTimestamp_;
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::NotificationTableUtils.dataToRecord( ) - " + e );
        	
        	return -1;
        }
	}
    
    /*
	 * Method : formInsertQuery( )
	 * 
	 * Input :NotificationRecord object
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using NotificationRecord and returns as
	 * string
	 */
	 
    

    public String formInsertQuery( NotificationRecord notificationRecord )
    {

		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "receiver_key,receiver_regn_key,sender_key,sender_regn_key,notification_type,notification_type_id,notification_message) ";
		query		 = query + " VALUES";
		query		 = query + "(";
		
		
		query		 = query + "'" + notificationRecord.receiverKey_  + "', ";
		query		 = query + "'" + notificationRecord.receiverRegnKey_ +"',";
		query		 = query + "'" + notificationRecord.senderKey_  + "', ";
		query		 = query + "'" + notificationRecord.senderRegnKey_ +"',";
		query		 = query + "'" + notificationRecord.notificationType_ +"',";
		query		 = query + "'" + notificationRecord.notificationTypeId_ +"',";
		query		 = query + "'" + notificationRecord.notificationMessage_ +"'";
		
		
		
		query 	  	 = query + ")";
 		
		//System.out.println( "Query = " + query );
		
		return query;
	}

/*
 * Method : getFilterString( )
 * 
 * Input :	NotificationRecord filter, StringHolder query
 * 
 * Return : int
 * 
 * Purpose: Using the NotificationRecord it form the filter query and add to string holder
 * parameter so it is copied to caller class.
 */

public int getFilterString( NotificationRecord filter, StringHolder query )
{
		try
        {
	    
		query.value += filter.notificationId_ != -1 ? 
					" notification_id='" + filter.notificationId_ + "' AND" : "";
	
	
		query.value += filter.receiverKey_ != null ? 
						" receiver_key='" + filter.receiverKey_ + "' AND" : "";
		

		query.value += filter.receiverRegnKey_ != null ? 
						" receiver_regn_key='" + filter.receiverRegnKey_+ "' AND" : "";
		

		query.value += filter.senderKey_ != null ? 
						" sender_key='" + filter.senderKey_ + "' AND" : "";
		
		query.value += filter.senderRegnKey_ != null ? 
				"sender_regn_key='" + filter.senderRegnKey_ + "' AND" : "";
		
		
		query.value += filter.notificationType_ != null ? 
				"notification _type='" + filter.notificationType_ + "' AND" : "";
		
		query.value += filter.notificationTypeId_ != null ? 
				"notification_type_id='" + filter.notificationTypeId_ + "' AND" : "";
		
		query.value += filter.notificationMessage_ != null ? 
				"notification_message='" + filter.notificationMessage_ + "' AND" : "";
		
		query.value += filter.createdTimestamp_ != null ? 
						" created_timestamp>'" + filter.createdTimestamp_ + "' AND" : "";

		if( !query.value.equalsIgnoreCase( "" ) )
		    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 ) +" ORDER BY created_timestamp DESC";
		
	
		return 0;
        }
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::NotificationTableUtils.getFilterString( ) - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : rsToDataList( )
	 * 
	 * Input :  ResultSet,notifications
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the ResultSet object to List<NotificationCenterData> object.
	 * And add to watchLists parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<NotificationCenterData> notifications )
	{
		try
        {
			while( rs.next( ) )
			{
				NotificationCenterData notificationCenterData = new NotificationCenterData( );

				notificationCenterData.notificationId_ 				= rs.getInt( "notification_id" );
				notificationCenterData.receiverKey_.email_			= rs.getString( "receiver_key" );
				notificationCenterData.receiverRegnKey_.companyPhoneNo_	= rs.getString( "receiver_regn_key" );
				notificationCenterData.senderKey_.email_			= rs.getString( "sender_key" );
				notificationCenterData.senderRegnKey_.companyPhoneNo_  = rs.getString( "sender_regn_key" );
				notificationCenterData.notificationType_ 			= rs.getString( "notification_type" );
				notificationCenterData.notificationTypeId_ 			= rs.getString( "notification_type_id" );
				notificationCenterData.notificationMessage_ 		= rs.getString( "notification_message" );
				notificationCenterData.createdTimestamp_ 			= rs.getTimestamp( "created_timestamp" );

				notifications.add( notificationCenterData );

				notificationCenterData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::NotificationTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}


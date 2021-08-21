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
package db.myconn;

import java.sql.ResultSet;
import java.util.List;

import core.myconn.MyConnectionData;
import core.regn.UserProfileKey;
import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.StringHolder;

/**
 * File:  UserConnectionTableUtils.java 
 *
 * Created on 13-Aug-2013 5:09:59 PM
 */
public class MyConnectionTableUtils
{
	
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/* Table fields:
	 * 
		from_user_name
		from_comp_name
		from_user_key
		from_regn_key
		
		to_user_name
		to_comp_name
		to_user_key
		to-regn_key
		
		status
	 	created_timestamp 
	 */
		
	

	/*
	 * Method : UserConnectionTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public MyConnectionTableUtils()
	{
		this.tableName_ = "my_connection";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : MyConnectionData obj, MyConnectionRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the UserConnectionData object to
	 * UserConnectioRecord object. And add to UserConnectioRecord parameter so it is copied
	 * to caller method.
	 */
	
	public int dataToRecord( MyConnectionData myConnectionData, MyConnectionRecord myConnectioRecord )
	{
		try
        {
			myConnectioRecord.fromUserName_		= myConnectionData.fromUserName_;
			myConnectioRecord.fromCompName_		= myConnectionData.fromCompName_;
			myConnectioRecord.fromUserKey_	 	= myConnectionData.fromUserKey_.toString( );
			myConnectioRecord.fromRegnKey_		= myConnectionData.fromRegnKey_.toString( );
			
			myConnectioRecord.toUserName_		= myConnectionData.toUserName_;
			myConnectioRecord.toCompName_		= myConnectionData.toCompName_;
			myConnectioRecord.toUserKey_		= myConnectionData.toUserKey_.toString( );
			myConnectioRecord.toRegnKey_		= myConnectionData.toRegnKey_.toString( );
			
			myConnectioRecord.status_			= myConnectionData.status_;
			myConnectioRecord.createdTimestamp_ = myConnectionData.createdTimestamp_;
			
			
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::MyConnectionTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserConnectionRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserConnectionRecord and returns as
	 * string
	 */

	public String formInsertQuery( MyConnectionRecord userConnectionRecord )
	{
		String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + " from_user_name,from_comp_name, from_user_key, from_regn_key, ";
		query		 = query  + "to_user_name,to_comp_name, to_user_key, to_regn_key, ";
		query		 = query  + "status)";
		
		query		 = query + " VALUES";
		query		 = query + "(";
		
		query		 = query + "'" + userConnectionRecord.fromUserName_  + "', ";
		query		 = query + "'" + userConnectionRecord.fromCompName_ +"',";
		query		 = query + "'" + userConnectionRecord.fromUserKey_ + "', ";
		query		 = query + "'" + userConnectionRecord.fromRegnKey_ + "', ";
		
		query   	 = query + "'" + userConnectionRecord.toUserName_ + "', ";
		query		 = query + "'" + userConnectionRecord.toCompName_ + "', ";
		query 		 = query + "'" + userConnectionRecord.toUserKey_ + "', ";
		query 		 = query + "'" + userConnectionRecord.toRegnKey_ + "', ";
		
		query 		 = query + "'" + userConnectionRecord.status_ + "' ";
		
		
		query 	  	 = query + ")";
 		 ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "Query = " + query );
		
		return query;
	}

	
	/*
	 * Method : getFilterString( )
	 * 
	 * Input :	UserConnectionRecord filter, StringHolder query
	 * 
	 * Return : int
	 * 
	 * Purpose: Using the UserConnectionRecord it form the filter query and add to string holder
	 * parameter so it is copied to caller class.
	 */
	
	public int getFilterString( MyConnectionRecord filter, StringHolder query )
	{
		try
		{
			
			
			query.value += filter.fromUserName_ != null ? 
							" from_user_name= '" + filter.fromUserName_ + "' OR" : "";

			query.value += filter.fromCompName_ != null ? 
							" from_comp_name='" + filter.fromCompName_ + "' OR" : "";
			

			query.value += filter.fromUserKey_ != null ? 
							" from_user_key='" + filter.fromUserKey_ + "' OR" : "";

			query.value += filter.fromRegnKey_ != null ? 
							" from_regn_key='" + filter.fromRegnKey_ + "' OR" : "";

			query.value += filter.toUserName_ != null ? 
							" to_user_name='" + filter.toUserName_ + "' OR" : "";

			query.value += filter.toCompName_ != null ?
							" to_comp_name='" + filter.toCompName_ + "' OR" : "";

			query.value += filter.toUserKey_ != null ?
							" to_user_key='" + filter.toUserKey_ + "' OR" : "";
			
			query.value += filter.toRegnKey_ != null ? 
							" to_regn_key='" + filter.toRegnKey_ + "' OR" : "";

			query.value += filter.status_ != null ?
							" status='" + filter.status_ + "' OR" : "";
			
			query.value += filter.createdTimestamp_ != null ?  
					" created_timestamp='" + filter.createdTimestamp_ + "' OR" : "";

			
			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 2 );
			
		
			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::MyConnectionTableUtils.getFilterString() - " + ex );
			return -1;
		}
	}
	
	/*
	 * Method : rsToDataList( )
	 * 
	 * Input :  ResultSet,connections,userkey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the ResultSet object to List<UserConnectionData> object.
	 * And add to connections parameter so it is copied to caller classes.
	 */
	
	public int rsToDataList( ResultSet rs, List<MyConnectionData> connections,UserProfileKey fromUserKey )
	{
		try
        {
			while( rs.next( ) )
			{
    			MyConnectionData myConnData = new MyConnectionData( );
    			
    			// If user add the connection 
    			
    			if( fromUserKey.email_.equals(rs.getString( "from_user_key" )) )
    			{
    				myConnData.fromUserName_ 				= rs.getString( "from_user_name" );
        			myConnData.fromCompName_				= rs.getString( "from_comp_name" );
        			myConnData.fromUserKey_.email_			= rs.getString( "from_user_key" );
        			myConnData.fromRegnKey_.companyPhoneNo_	= rs.getString( "from_regn_key" );
        			
        			myConnData.toUserName_					= rs.getString( "to_user_name" );
        			myConnData.toCompName_					= rs.getString( "to_comp_name" );
        			myConnData.toUserKey_.email_			= rs.getString( "to_user_key" );
        			myConnData.toRegnKey_.companyPhoneNo_	= rs.getString( "to_regn_key" );
    			        myConnData.typ_="Sent";
                        }
    			else  // If other members add the user as a connection
    			{
    				myConnData.fromUserName_ 				= rs.getString( "to_user_name" );
        			myConnData.fromCompName_				= rs.getString( "to_comp_name" );
        			myConnData.fromUserKey_.email_			= rs.getString( "to_user_key" );
        			myConnData.fromRegnKey_.companyPhoneNo_	= rs.getString( "to_regn_key" );
        			
        			myConnData.toUserName_					= rs.getString( "from_user_name" );
        			myConnData.toCompName_					= rs.getString( "from_comp_name" );
        			myConnData.toUserKey_.email_			= rs.getString( "from_user_key" );
        			myConnData.toRegnKey_.companyPhoneNo_	= rs.getString( "from_regn_key" );
			        myConnData.typ_="Recieved";
                        }
    			
    			myConnData.status_						= rs.getString( "status" );
    			myConnData.createdTimestamp_ 			= rs.getTimestamp( "created_timestamp" );
    			
    			connections.add( myConnData );
    			
    			myConnData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::MyConnectionTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
}

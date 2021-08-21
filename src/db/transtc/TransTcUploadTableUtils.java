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
package db.transtc;

import java.sql.ResultSet;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.transtc.TransTcUploadData;
import db.utils.DBDecode;
import db.utils.DBEncode;

/**
 * File:  TransTcUploadTableUtils.java 
 *
 * Created on Oct 19, 2013 3:09:17 PM
 */
public class TransTcUploadTableUtils
{
	

	private String tableName_;
	
	private ErrorLogger errLogger_;
	

	/*
	 * trans_tc table fields
	 *
	 * trans_tc_id
	 * regn_rel_key
	 * trans _type
	 * tc
	 * created_timestamp
	 */
	
	

	/*
	 * Method : TransTcUploadTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransTcUploadTableUtils(  )
	{
		this.tableName_ = "trans_tc";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : TransTcUploadData object to TransTcUploadRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the TransTcUploadData object to
	 * TransTcUploadRecord object. And add to TransTcUploadRecord parameter so it is copied
	 * to caller method.
	 */
	
    public int dataToRecord( TransTcUploadData transTcUploadData, TransTcUploadRecord transTcUploadRecord )
    {
	   try
       {
		   DBEncode dbEncode = new DBEncode( );
		   
		   
		   transTcUploadRecord.regnKey_			=transTcUploadData.regnKey_.companyPhoneNo_;
		   
		   transTcUploadRecord.transTcId_		=transTcUploadData.transTcId_;
		   
		   transTcUploadRecord.tc_				= dbEncode.encode( transTcUploadData.tc_ );
		   
		   transTcUploadRecord.transType_		=transTcUploadData.transType_;
		   
		   transTcUploadRecord.createdTimestamp_=transTcUploadData.createdTimestamp_;
		   
		   dbEncode = null;
		   
		   return 0;
	        
       }
      catch( Exception e )
      {
    	  errLogger_.logMsg( "Exception::TransTcUploadTableUtils.dataToRecord( ) - " + e );
      	
      	  return -1;
      }
    }
    
    
    /*
     * Method : getFilterString( )
     * 
     * Input :	TransTcUploadRecord filter, StringHolder query
     * 
     * Return : int
     * 
     * Purpose: Using the TransTcUploadRecord it form the filter query and add to string holder
     * parameter so it is copied to caller class.
     */
	
    public int getFilterString( TransTcUploadRecord filter, StringHolder query )
    {
    	try
    	{
    	query.value += filter.regnKey_ != null ? 
				" regn_rel_key='" + filter.regnKey_+ "' AND" : "";
    	
    	query.value += filter.transType_ != null ? 
				" trans_type='" + filter.transType_ + "' AND" : "";
    	
    	query.value += filter.transTcId_ !=-1 ? 
				" trans_tc_id='" + filter.transTcId_ + "' AND" : "";
    	
    	query.value += filter.tc_ !=null ?  
				" tc='" + filter.tc_ + "' AND" : "";
    	
    	query.value += filter.createdTimestamp_ != null ? 
				" created_timestamp > '" + filter.createdTimestamp_+ "' AND" : "";

    	if( !query.value.equalsIgnoreCase( "" ) )
    		query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 ) +" ORDER BY created_timestamp DESC";


    	return 0;
    	}
    	catch( Exception ex )
    	{
    		ErrorLogger errLogger_ = ErrorLogger.instance( );
    		errLogger_.logMsg( "Exception::TransTcUploadTableUtils.getFilterString( ) - " + ex );
    		return -1;
    	}
    }

	
    
    
    /*
	 * Method : rsToDataList( )
	 * 
	 * Input :  ResultSet,translists
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the ResultSet object to List<TransTcUploadData> object.
	 * And add to translists parameter so it is copied to caller classes.
	 */
    public int rsToDataList( ResultSet rs, List<TransTcUploadData> translists )
    {
    	DBDecode dbDecode = new DBDecode( );
    	
    	try
        {
			while( rs.next( ) )
			{
				TransTcUploadData transTcUploadData = new TransTcUploadData( );

				transTcUploadData.transTcId_				= rs.getInt( "trans_tc_id" );
				transTcUploadData.regnKey_.companyPhoneNo_	= rs.getString( "regn_rel_key" );
				transTcUploadData.transType_ 				= rs.getString( "trans_type" );
				transTcUploadData.tc_ 						= dbDecode.decode( rs.getString( "tc") );
				transTcUploadData.createdTimestamp_ 		= rs.getTimestamp( "created_timestamp" );

				translists.add( transTcUploadData );

				transTcUploadData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::TransTcUploadTableUtils.rsToDataList() - " + e );
			return -1;
        }
	}
    
    
    
    
    /*
	 * Method : formInsertQuery( )
	 * 
	 * Input :TransTcUploadRecord object
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using transTcUploadRecord and returns as
	 * string
	 */
    public String formInsertQuery( TransTcUploadRecord transTcUploadRecord )
    {
    	String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "regn_rel_key,trans_type,tc) ";
		query		 = query + " VALUES";
		query		 = query + "(";
		
		
		query		 = query + "'" + transTcUploadRecord.regnKey_  + "', ";
		query		 = query + "'" + transTcUploadRecord.transType_+ "', ";
		query		 = query + "'" + transTcUploadRecord.tc_ +"'";
		
		
		
		query 	  	 = query + ")";
 		
		System.out.println( "Query = " + query );
		
		return query;
    }

}

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
package db.vendorregn;

import java.sql.ResultSet;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.notification.NotificationCenterData;
import core.regn.MailingAddressData;

/**
 * File:  VRMailingTableUtils.java 
 *
 * Created on Oct 30, 2013 3:27:22 PM
 */
public class VRMailingTableUtils
{
	
	private String tableName_;
	
	private ErrorLogger errLogger_;

	
	/*
	 * Method : NotificationTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public VRMailingTableUtils(  )
	{
		this.tableName_ = "vr_mailing_address";
		
		errLogger_ = ErrorLogger.instance( );
	}
	

	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : MailingAddressData object to VRMailingRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the MailingAddressData object to
	 * VRMailingRecord object. And add to VRMailingRecord parameter so it is copied
	 * to caller method.
	 */
    public int dataToRecord( MailingAddressData mailingAddressData, VRMailingRecord vrMailingRecord )
    {
    	try
        {
    		vrMailingRecord.address_ 			= mailingAddressData.address_;
			
    		vrMailingRecord.companyRegnKey_				= mailingAddressData.companyRegnKey_.toString( );
		   		
    		vrMailingRecord.city_				= mailingAddressData.city_;
			
    		vrMailingRecord.state_		= mailingAddressData.state_;
    		
    		vrMailingRecord.zipcode_	= mailingAddressData.zipcode_;
    		
    		vrMailingRecord.countryRegion_		= mailingAddressData.countryRegion_;
    		
    		vrMailingRecord.addressType_	= mailingAddressData.addressType_;
			
    		vrMailingRecord.emailid_ 	= mailingAddressData.emailid_;
    		
    		vrMailingRecord.addrid_=mailingAddressData.addrid_;
    		
    		
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::VRMailingTableUtils.dataToRecord( ) - " + e );
        	
        	return -1;
        }
    }

    /*
	 * Method : formInsertQuery( )
	 * 
	 * Input :VRMailingRecord object
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using VRMailingRecord and returns as
	 * string
	 */
    public String formInsertQuery( VRMailingRecord vrMailingRecord )
    {
    	String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "address,regn_key,city,state,zipcode,country_region,address_type,email) ";
		query		 = query + " VALUES";
		query		 = query + "(";
		
		
		query		 = query + "'" + vrMailingRecord.address_ + "', ";
		query		 = query + "'" + vrMailingRecord.companyRegnKey_ +"',";
		query		 = query + "'" + vrMailingRecord.city_+ "', ";
		query		 = query + "'" + vrMailingRecord.state_ +"',";
		query		 = query + "'" + vrMailingRecord.zipcode_ +"',";
		query		 = query + "'" + vrMailingRecord.countryRegion_ +"',";
		query		 = query + "'" + vrMailingRecord.addressType_ +"',";
		query		 = query + "'" + vrMailingRecord.emailid_ +"'";
		
		
		
		
		query 	  	 = query + ")";
 		
		System.out.println( "Query = " + query );
		
		return query;
    }


    /*
     * Method : getFilterString( )
     * 
     * Input :	VRMailingRecord filter, StringHolder query
     * 
     * Return : int
     * 
     * Purpose: Using the VRMailingRecord it form the filter query and add to string holder
     * parameter so it is copied to caller class.
     */
    public int getFilterString( VRMailingRecord filter, StringHolder query )
    {
	    try
        {
	        
	    	query.value += filter.addrid_ != -1 ? 
					" addr_id='" + filter.addrid_ + "' AND" : "";
	
	
		query.value += filter.address_ != null ? 
						" address='" + filter.address_ + "' AND" : "";
		

		query.value += filter.addressType_ != null ? 
						" address_type'" + filter.addressType_+ "' AND" : "";
		

		query.value += filter.city_ != null ? 
						" city='" + filter.city_ + "' AND" : "";
		
		query.value += filter.companyRegnKey_ != null ? 
				" regn_key='" + filter.companyRegnKey_ + "' AND" : "";
		
		
		query.value += filter.countryRegion_ != null ? 
				" country_region='" + filter.countryRegion_ + "' AND" : "";
		
		query.value += filter.emailid_ != null ? 
				" email='" + filter.emailid_ + "' AND" : "";
		
		query.value += filter.state_ != null ? 
				" state='" + filter.state_ + "' AND" : "";
		
		query.value += filter.zipcode_ != null ? 
						" zipcode'" + filter.zipcode_ + "' AND" : "";

		if( !query.value.equalsIgnoreCase( "" ) )
		    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 ) +" ORDER BY address_type DESC";
		
	
		return 0;
        }
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::VRMailingTableUtils.getFilterString( ) - " + ex );
			return -1;
		}
    }


    /*
	 * Method : rsToDataList( )
	 * 
	 * Input :  ResultSet,mailingAddressDataList
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the ResultSet object to List<MailingAddressData> object.
	 * And add to watchLists parameter so it is copied to caller classes.
	 */
    public int rsToDataList( ResultSet rs, List<MailingAddressData> mailingAddressDataList )
    {
    	try
        {
			while( rs.next( ) )
			{
				MailingAddressData mailingAddressData = new MailingAddressData( );

				mailingAddressData.addrid_ 				= rs.getInt( "addr_id" );
				mailingAddressData.emailid_				= rs.getString( "email" );
				mailingAddressData.companyRegnKey_.companyPhoneNo_	= rs.getString( "regn_key" );
				mailingAddressData.address_				= rs.getString( "address" );
				mailingAddressData.addressType_  	= rs.getString( "address_type" );
				mailingAddressData.city_ 					= rs.getString( "city" );
				mailingAddressData.countryRegion_ 	= rs.getString( "country_region" );
				mailingAddressData.state_ 				= rs.getString( "state" );
				mailingAddressData.zipcode_			=rs.getString("zipcode");

				mailingAddressDataList.add( mailingAddressData );

				mailingAddressData = null;
			}
			return 0;
        }
        catch( Exception e )
        {
        	ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::VRMailingTableUtils.rsToDataList() - " + e );
			return -1;
        }
    }

}

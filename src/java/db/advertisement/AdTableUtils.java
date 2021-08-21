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

import java.sql.ResultSet;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.advertisement.AdData;
import utils.ErrorMaster;

/**
 * File:  AdTableUtils.java 
 *
 * Created on Oct 3, 2013 2:03:09 PM
 */
public class AdTableUtils
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	
    

	/*
	 * advertisement table fields
	 *
	 * ad_id
	 * customer_key
	 * company_key
	 * alternate_text
	 * link
	 * image_dimension
	 * ad_image_path
	 * created_timestamp
	 */
	
	/*
	 * Method : AdTableUtils -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public AdTableUtils(  )
	{
		this.tableName_ = "advertisement";
		
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : dataToRecord( )
	 * 
	 * Input : AdvertisementData object to AdRecord object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the AdvertisementData object to
	 * AdRecord object. And add to adRecord parameter so it is copied
	 * to caller method.
	 */
		
	public int dataToRecord( AdData adData, AdRecord adrecord )
    {
		try
        {
			adrecord.adId_ 				= adData.adId_;
			
			adrecord.regnKey_		= adData.regnKey_.companyPhoneNo_;
			
			adrecord.userProfileKey_		= adData.userProfileKey_.toString( );
    		
			adrecord.alternateText_		= adData.alternateText_;
			
			adrecord.link_  			= adData.link_;
			
			adrecord.adImagePath_		= adData.adImagePath_;
    			
			adrecord.createdTimestamp_  = adData.createdTimestamp_;
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::AdTableUtils.dataToRecord( ) - " + e );
        	
        	return -1;
        }
	}
    
	
	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input: AdRecord object
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using AdRecord and returns as
	 * string
	 */
	
	public String formInsertQuery( AdRecord adRecord )
    {
	    
    	String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "user_rel_key, regn_rel_key, alternate_text, link, ad_image_path) ";
		query		 = query + " VALUES";
		query		 = query + "(";
		
		
		query		 = query + "'" + adRecord.userProfileKey_  + "', ";
		query		 = query + "'" + adRecord.regnKey_ +"',";
		query		 = query + "'" + adRecord.alternateText_  + "', ";
		query		 = query + "'" + adRecord.link_ +"',";
		query		 = query + "'" + adRecord.adImagePath_ +"'";
				
		
		query 	  	 = query + ")";
 		 ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "Query = " + query );
		
		return query;
    }
      
    
    /*
     * Method : getFilterString( )
     * 
     * Input :	AdRecord filter, StringHolder query
     * 
     * Return : int
     * 
     * Purpose: Using the AdRecord it form the filter query and add to string holder
     * parameter so it is copied to caller class.
     */

	
    public int getFilterString( AdRecord filter, StringHolder query )
    {
	    
    	try
        {
	    
		query.value += filter.adId_ != -1 ? 
					" ad_id='" + filter.adId_ + "' AND" : "";
	
	
		query.value += filter.userProfileKey_ != null ? 
						" user_rel_key='" + filter.userProfileKey_ + "' AND" : "";		
               
		query.value += filter.regnKey_ != null ? 
						" regn_rel_key='" + filter.regnKey_+ "' AND" : "";
		

		query.value += filter.alternateText_ != null ? 
						" alternate_text='" + filter.alternateText_ + "' AND" : "";
		
		query.value += filter.link_ != null ? 
				"link ='" + filter.link_ + "' AND" : "";
		
		query.value += filter.adImagePath_ != null ? 
				"ad_Image_path='" + filter.adImagePath_ + "' AND" : "";
		
		query.value += filter.createdTimestamp_ != null ? 
						" created_timestamp '" + filter.createdTimestamp_ + "' AND" : "";

		if( !query.value.equalsIgnoreCase( "" ) )
		    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 ) +" ORDER BY created_timestamp DESC";
		
	
		return 0;
        }
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::AdTableUtils.getFilterString( ) - " + ex );
			return -1;
		}
    }
        
    /*
	 * Method : rsToDataList( )
	 * 
	 * Input :  ResultSet,adlists
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the ResultSet object to List<AdvertisementData> object.
	 * And add to adlists parameter so it is copied to caller classes.
	 */
	
    public int rsToDataList( ResultSet rs, List<AdData> adlists )
    {
 			try
            {
    			while( rs.next( ) )
    			{
    				AdData adData = new AdData( );

    				adData.adId_ 						= rs.getInt( "ad_id" );
    				adData.userProfileKey_.email_		= rs.getString( "user_rel_key" );
    				adData.regnKey_.companyPhoneNo_		= rs.getString( "regn_rel_key" );
    				adData.alternateText_				= rs.getString( "alternate_text" );
    				adData.link_ 						= rs.getString( "link" );
    				adData.adImagePath_ 				= rs.getString( "ad_image_path" );
    				adData.createdTimestamp_ 			= rs.getTimestamp( "created_timestamp" );

    				adlists.add( adData );

    				adData = null;
    			}
    			return 0;
            }
            catch( Exception e )
            {
            	ErrorLogger errLogger_ = ErrorLogger.instance( );
    			errLogger_.logMsg( "Exception::AdTableUtils.rsToDataList() - " + e );
    			return -1;
            }
    	}
}

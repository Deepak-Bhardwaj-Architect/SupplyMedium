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
package core.advertisement;

import java.sql.Timestamp;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File:  AdData.java 
 *
 * Created on Oct 3, 2013 11:31:59 AM
 */
public class AdData
{
	//id of the advertisement.This is auto incremented id
	public long adId_;
	
	//companykey of the advertisement 
	public CompanyRegnKey regnKey_;
	
	//customerkey of the advertisement
	public UserProfileKey userProfileKey_;
	
	//alternateText of the advertisement
	public String alternateText_;
	
	//link of the advertisement
	public String link_;
		
	//adImagePath of the advertisement
	public String adImagePath_;
	
	
	// No of ads needed
	public int noOfAds_;
	
	//advertisement created time
	public Timestamp createdTimestamp_;
	
	private static ErrorMaster errorMaster_ = null;
	/*
	 * Method : AdvertisementData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public AdData()
	{
		adId_			 		= -1;
		
		noOfAds_				= -1;
		
		userProfileKey_			= new UserProfileKey( );
		
		regnKey_				= new CompanyRegnKey( );
		
		
	}	
	
	/*
	 * Method : show( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
                
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );

                errorMaster_.insert( "adId 			=" + adId_ );
		
		errorMaster_.insert( "noOfAds_ 		=" + noOfAds_ );
		
		errorMaster_.insert( "companyKey_  	=" + regnKey_ );
		
		errorMaster_.insert( "customerKey_  	=" + userProfileKey_ );
		
		errorMaster_.insert( "alternateText	=" + alternateText_ );
		
		errorMaster_.insert( "link			=" + link_ );
				
		errorMaster_.insert( "adImagePath 	=" + adImagePath_ );
		
		errorMaster_.insert( "createdTimestamp	=" + createdTimestamp_ );
		
		
	}
	
	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */
	
	public void clear( )
	{
		adId_			= -1;
		
		noOfAds_		= -1;
		
		regnKey_ 		= null;
		
		userProfileKey_ = null;
		
		alternateText_ 	= null;
		
		link_			= null;
			
		adImagePath_ 	= null;
	
		createdTimestamp_ = null;
		
	}

}

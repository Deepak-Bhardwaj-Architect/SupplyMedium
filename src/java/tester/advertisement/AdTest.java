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
package tester.advertisement;


import java.util.ArrayList;
import java.util.List;

import core.advertisement.AdData;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import ctrl.advertisement.AdController;
import utils.ErrorMaster;


/**
 * File:  AdTest.java 
 *
 * Created on Oct 3, 2013 3:40:09 PM
 */
public class AdTest
{
    private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : AdTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public AdTest()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	public void execute()
	{
		//addTest( );
		
		//deleteTest( );
		
		fetchTest( );
	}
	
	/*
	 * Method : addTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to add the new Advertisement. 
	 */

	public void addTest()
	{
		AdController controller = new AdController( );
		
		AdData adData = new AdData( );
		
		adData.userProfileKey_.email_ 	= "balakannan65@gmail.com";
		
		adData.regnKey_.companyPhoneNo_ = "9003361885";
		
		adData.alternateText_ 			= "Enter alternate Text here";
		
		adData.link_ 					= "http://image.";
			
		adData.adImagePath_ 			= "views/file/images";
		
		int result = controller.add( adData );
		
		errorMaster_.insert( "Advertisement result id ="+result );
		
		if( result == 10300  )
		{
			errorMaster_.insert( "Advertisement add test successfully completed" );
			
			errorMaster_.insert( "Advertisement added successfully" );
		}
		
		else
		{
			errorMaster_.insert( "Advertisement add test failed" );
		}
		
	}
	
	
	/*
	 * Method : deleteTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to delete the Advertisement. 
	 */
	
	public void deleteTest()
	{
		AdController controller = new AdController( );
		
		long adId = 4;
		
		int result = controller.remove( adId );
		
		if( result == 10310  )
		{
			errorMaster_.insert( "Advertisement delete test successfully completed" );
			
		}
		
		else
		{
			errorMaster_.insert( "Advertisement delete test failed" );
		}
	}
	
	
	/*
	 * Method : fetchTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the adlists for given user key. 
	 */
	
	public void fetchTest()
	{
		AdController controller = new AdController( );
		
		List<AdData> adlists 	= new ArrayList<AdData>( );
	
		
		//UserProfileKey customerKey = new UserProfileKey( );
		
		//customerKey.email_ 			= "balakannan65@yahoo.com";
		
		//nData.userProfileKey_		=customerKey;
		
		CompanyRegnKey companyKey 	= new CompanyRegnKey( );
		
		companyKey.companyPhoneNo_ 	= "9003361885";
		
		AdData nData 				= new AdData( );
		
		nData.regnKey_ 				= companyKey;	
		
		nData.noOfAds_  			= 5;
		
		
		int result = controller.get( nData, adlists );
		
		if( result == 0  )
		{
			errorMaster_.insert( "Advertisement fetch test successfully completed" );
			
			for( AdData adData : adlists )
            {
				adData.show( );
            }
			
		}
		
		else
		{
			errorMaster_.insert( "Advertisement fetch test failed" );
		}
			
	}
}

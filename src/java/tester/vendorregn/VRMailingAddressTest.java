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
package tester.vendorregn;

import java.util.ArrayList;
import java.util.List;

import core.notification.NotificationCenterData;
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import ctrl.notification.NotificationController;
import ctrl.vendorregn.VRMailingController;
import utils.ErrorMaster;

/**
 * File:  VRMailingAddressTest.java 
 *
 * Created on Oct 30, 2013 4:39:54 PM
 */
public class VRMailingAddressTest
{
	private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : VRMailingAddressTest -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public VRMailingAddressTest()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	public void execute()
	{
		//addTest( );
		//deleteTest( );
		fetchTest();
		
	}
	
	/*
	 * Method : addTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to add the new Address. 
	 */
	
	public void addTest()
	{
		VRMailingController controller = new VRMailingController( );
		
		MailingAddressData mailingAddressData = new MailingAddressData( );
		
		mailingAddressData.address_="nagar";		
		
		mailingAddressData.city_ = "madurai";
		
		mailingAddressData.state_ = "TamilNadu";
		
		mailingAddressData.zipcode_ = "600022";
		
		mailingAddressData.countryRegion_ = "india";
		
		mailingAddressData.addressType_ = "special";
		
		mailingAddressData.companyRegnKey_.companyPhoneNo_ = "91254618825";
		
		mailingAddressData.emailid_= "balakannan65@gmail.com";
		
	
		
		int result = controller.add( mailingAddressData );
		
		errorMaster_.insert( "VRMailing result id="+result );
		
		if( result == 10300  )
		{
			errorMaster_.insert( "Address add test successfully completed" );
			
			errorMaster_.insert( "Address added successfully" );
		}
		
		else
		{
			errorMaster_.insert( "Address add test failed" );
		}
			
	}
	
	/*
	 * Method : deleteTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to delete the Address. 
	 */
	
	public void deleteTest()
	{
		VRMailingController controller = new VRMailingController( );
		
		long addrid = 4;
		
		int result = controller.remove(addrid  );
		
		if( result == 10310  )
		{
			errorMaster_.insert( "Address delete test successfully completed" );
			
		}
		
		else
		{
			errorMaster_.insert( "Address delete test failed" );
		}
			
	}
	
	
	/*
	 * Method : fetchTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to fetch the Address. 
	 */
	
	public void fetchTest()
	{
		
		VRMailingController controller =new VRMailingController( );
		
		List<MailingAddressData>mailingAddressDataList=new ArrayList<MailingAddressData>( );
		
		CompanyRegnKey regnKey=new CompanyRegnKey( );
		
		regnKey.companyPhoneNo_=" 4044081111";
		
		MailingAddressData mailingAddressData=new MailingAddressData( );
		
		mailingAddressData.companyRegnKey_=regnKey;
		
		int result = controller.get( mailingAddressData, mailingAddressDataList );
		
		if( result == 10320  )
		{
			errorMaster_.insert( "Address fetch test successfully completed" );
			
			for( MailingAddressData mailingAddressData2 : mailingAddressDataList )
            {
				mailingAddressData2.show( );
            }
			
		}
		
		else
		{
			errorMaster_.insert( "Address fetch test failed" );
		}
			
		
	}

}

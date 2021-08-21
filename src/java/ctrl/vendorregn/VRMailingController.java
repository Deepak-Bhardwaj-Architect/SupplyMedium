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
package ctrl.vendorregn;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import core.notification.NotificationCenter;
import core.regn.MailingAddressData;
import core.vendorregn.VRMailer;



/**
 * File:  VRMailingController.java 
 *
 * Created on Oct 30, 2013 11:07:32 AM
 */


public class VRMailingController
{

	/*
	 * Method : VRMailingController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public VRMailingController()
	{
		
		
	}

	/*
	 * Method : processVR
	 * 
	 * Input  : HttpServletRequest object, list of MailingAddressData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the MailingAddress. It do the following operation in MailingAddressData
	 * 
	 * 1. Create the new Address
	 * 2. Delete the Address
	 * 3.get the Address for given regnKey
	 */

	
    public int processVR( HttpServletRequest request,
            List<MailingAddressData> mailingAddressDataList )
    {
    	
    	
	    
    	int result = 0;
   	 // Converting request object to MailingAddressData object
   	
   	VRMailingDataConverter converter = new VRMailingDataConverter( );
   	
   	MailingAddressData mailingAddressData = new MailingAddressData( );
   	
   	result = converter.convert( request, mailingAddressData );
      
   	converter = null;
   	
   	if( result != 0 )
   		return 17001;  // Parser error.
   	
   	String requestType = request.getParameter( "RequestType" );
   	
   	if( requestType.equals( "AddAddress" ))
   	{
   		result = add( mailingAddressData );
   		
   		if( result == 17000 )
   		{
   			mailingAddressDataList.add( mailingAddressData );
   		}
   	}
   	else if( requestType.equals( "DeleteAddress" ))
   	{
   		result = remove( mailingAddressData.addrid_ );
   	}
   	else if (requestType.equals( "FetchAddress" )) 
   	{
   		result=get(mailingAddressData,mailingAddressDataList);
		
	}
   	
   	else
   	{
   		result = 17002;  // can't find appropriate request type
   	}
   
   	return result;
    }

    /*
     * Method : get
     * 
     * Input  : MailingAddressData object, list of MailingAddressData object
     * 
     * Return : int success/fail
     * 
     * Purpose: It get all the Address for given userprofilekey. And copied to mailingAddressDataList parameter.
     * So it is available for caller classes
     */
    public int get( MailingAddressData mailingData,
            List<MailingAddressData> mailingAddressDataList )
    {
    	int result = 0;
    	
    	VRMailer vrMailer = new VRMailer( );
    	
    	result = vrMailer.get( mailingData, mailingAddressDataList );
    	
    	vrMailer = null;
    	
    	return result;
    }

	/*
     * Method : delete
     * 
     * Input  : mailingAddressData object
     * 
     * Return : int success/fail
     * 
     * Purpose: Used to delete the new Address .Also it remove the 
     * Address member relationship
     */
    public int remove( long addrid_ )
    {
	    
    	int result = 0;
    	
    	VRMailer vrMailer = new VRMailer( );
    	
    	result = vrMailer.remove( addrid_ );
    	
    	vrMailer = null;
    	
    	return result;
    }

	
    
    /*
     * Method : add
     * 
     * Input  : MailingAddressData object
     * 
     * Return : int success/fail
     * 
     * Purpose: Used to add the new Address for given user key. 
     */
    public int add( MailingAddressData mailingAddressData )
    {
    	
    	
    	int result = 0;
    	
    	VRMailer vrMailer = new VRMailer( );
    	
    	result = vrMailer.add( mailingAddressData );
    	
    	vrMailer = null;
    	
    	return result;
    }

}

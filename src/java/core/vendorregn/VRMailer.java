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
package core.vendorregn;

import java.util.List;

import core.regn.MailingAddressData;
import db.notification.NotificationTable;
import db.vendorregn.VRMailingTable;

/**
 * File:  VRMailer.java 
 *
 * Created on Oct 30, 2013 2:53:08 PM
 */
public class VRMailer
{
	
	
	/*
	 * Method : VRMailer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public VRMailer( )
	{
		
	}
	
	
	/*
	 * Method : delete
	 * 
	 * Input  : addrid_
	 * 	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It delete the address using given addrid_. Also it remove the 
	 * Address member relationship
	 */
    public int remove( long addrid_ )
    {
    	VRMailingTable vrMailingTable = new VRMailingTable( );
		
		int result = vrMailingTable.delete( addrid_ );
		
		vrMailingTable = null;
		
		if( result == 0 )
		{
			// delete the Address members from VRMailing table
			
			return 17010;
		}
		else 
		{
			return 17011;
		}
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
    	
    	
    	
    	VRMailingTable vrMailingTable = new VRMailingTable( );
		
		
		int result = vrMailingTable.insert( mailingAddressData );
		
		vrMailingTable = null;
		
		if( result == 0 )
			
			return 17000;  // Address successfully created
		else
			
			return 17004;  // Address creation failed 
		
    }

    
    
    /*
	 * Method : get
	 * 
	 * Input  : MailingAddressData, list of MailingAddressData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get all the Address for given userprofilekey. And copied to mailingAddressDataList parameter.
	 * So it is available for caller classes
	 */

    public int get( MailingAddressData mailingData,
            List<MailingAddressData> mailingAddressDataList )
    {
    	VRMailingTable vrMailingTable = new VRMailingTable( );

		int result = vrMailingTable.find( mailingData, mailingAddressDataList );
	
		vrMailingTable = null;
	
		if( result == 0 )
			return 17020;
		else
			return 17021;
    }

}

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

package core.regn;

import java.util.HashMap;
import java.util.Map;

/**
 * File:  RegnFactory.java 
 *
 * Created on Jan 9, 2013 11:27:28 AM
 */

/*
 * This factory method create and return the RegnProcess object for given request type.
 * It is related to company registration. 
 */

public class RegnFactory
{
	// This map contain all the RegnProcess implements classes object.
	private Map<String, RegnProcess> regnObjHashMap_ = null;

	/*
	 * Method : RegnFactory( ) 
	 * Input : None 
	 * Return : None
	 * 
	 * 
	 * Purpose: To initialize the map and set the vales to map. Each key contain
	 * one class object.
	 */
	public RegnFactory()
	{
		regnObjHashMap_ = new HashMap<String, RegnProcess>( );

		// This is for new company registration
		regnObjHashMap_.put( "NewRegistration", new NewRegn( ) );

		/*
		 * This is for activate the company by clicking the link which is sent
		 * at the time of registration
		 */

		regnObjHashMap_.put( "RegistrationActivate", new RegnLinkActivate( ) );

		// Deleting company from supply medium
		regnObjHashMap_.put( "DeleteRegistration", new RemoveRegn( ) );
	}

	/*
	 * Method : createRegnObj( ) 
	 * Input : request type 
	 * Return : RegnProcess object
	 * 
	 * Purpose: To return the RegnProcess implements class objects depending to
	 * requestType parameter. It get the values from HashMap which is already in
	 * class constructor
	 */

	public RegnProcess createRegnObj( String requestType )
	{
		return this.regnObjHashMap_.get( requestType );
	}

}

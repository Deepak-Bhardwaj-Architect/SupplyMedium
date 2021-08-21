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
package core.edi;

import java.util.HashMap;
import java.util.Map;

/**
 * File:  EDISpecFactory.java 
 *
 * Created on 21-Oct-2013 4:26:04 PM
 */
public class EDISpecFactory
{

	// private map contain all objects of the edi
	private Map< String, Map< String, EDISpec > > ediSpecObjMap_;
		
	private static EDISpecFactory EDISpecFactoryObj  = null;
	
	/*
	 * Method : instance( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: This is the singleton class. This class initialize at 
	 * application startup
	 */
	public static EDISpecFactory instance( )
	{
		if( EDISpecFactoryObj == null )
		{
			EDISpecFactoryObj = new EDISpecFactory( );
		}
		
		return EDISpecFactoryObj;
	}

	
	/*
	 * Method : EDISpecFactory( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: constructor call the private init method to load edi objects in map
	 */
	private EDISpecFactory( )
	{
		init( );
	}
	
	/*
	 * Method : init()
	 * 
	 * Input : None
	 * 
	 * Return : void
	 * 
	 * Purpose: Used to fill the map 
	 * 
	 */
	
	private void init()
    {
		
		ediSpecObjMap_ = new HashMap<String, Map<String,EDISpec>>( );
		
	    Map<String, EDISpec> ediSpecV1_0Map = new HashMap<String, EDISpec>();
	    
	    ediSpecV1_0Map.put( "RFQ", new RFQEDISpec_v1_0( ) );
	    
	    ediSpecV1_0Map.put( "Quote", new QuoteEDISpec_v1_0( ) );
	    
	    ediSpecV1_0Map.put( "PO", new POEDISpec_v1_0( ) );
	    
	    ediSpecV1_0Map.put( "Invoice", new InvoiceEDISpec_v1_0( ) );
	    
	    
	    ediSpecObjMap_.put( "V1_0", ediSpecV1_0Map );
    }
	
	/*
	 * Method : get()
	 * 
	 * Input : transType and version number
	 * 
	 * Return : EDISpec object
	 * 
	 * Purpose: Used to get the EDISpec object from map for given transType and version number
	 * of the spec
	 * 
	 */
	
	public EDISpec get( String transType, String version )
    {
		Map<String, EDISpec> ediSpecVersionMap = ediSpecObjMap_.get( version );
		
		return ediSpecVersionMap.get( transType );
    }

}

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

import utils.ErrorLogger;

/**
 * File:  InvoiceEDISpec_v1_0.java 
 *
 * Created on Oct 22, 2013 5:58:07 PM
 */
public class InvoiceEDISpec_v1_0 implements EDISpec
{
	ErrorLogger errorLogger_;

	// This map contain the all the PO EDI element code.
	private Map<String, String> elementCodeMap_;
	
	// This map contain the all the  PO segment code
	private Map<String, String> segCodeMap_;
	
	/*
	 * Method : RFQEDISpec_v1_0 -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public InvoiceEDISpec_v1_0()
	{
		elementCodeMap_ = new HashMap<String, String>();
		
		segCodeMap_ 	= new HashMap<String, String>( );
		
		init();
		
		errorLogger_ = ErrorLogger.instance( );
		
	}
	
	
	/*
	 * Method : init( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: This method is used to load the elements and segments code to corresponding map.
	 */
	private void init( )
	{
		loadElementCodeMap( );
		
		loadSegmentCodeMap( );
	}
	
	/*
	 * Method : init( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: This method is used to load the element codes to map
	 */
	private void loadElementCodeMap( )
	{
		elementCodeMap_.put( "Transaction Set Identifier Code", "143" );
		
		elementCodeMap_.put( "Transaction Set Control Number", "329" );
		
		elementCodeMap_.put( "Invoice Number", "76" );
		
		elementCodeMap_.put( "Purchase Order Number", "324" );
		
		elementCodeMap_.put( "Date", "373" );
		
		elementCodeMap_.put( "Entity Identifier Code", "98" );
		
		elementCodeMap_.put( "Currency Code", "100" );
		
		elementCodeMap_.put( "Reference Identification Qualifier", "128" );
		
		elementCodeMap_.put( "Reference Identification", "127" );
		
		elementCodeMap_.put( "Contact Function Code", "366" );
		
		elementCodeMap_.put( "Name", "93" );
		
		elementCodeMap_.put( "City Name", "19" );
		
		elementCodeMap_.put( "State or Provine Code", "156" );
		
		elementCodeMap_.put( "Postal Code", "116" );
		
		elementCodeMap_.put( "Address Information", "166" );
		
		elementCodeMap_.put( "Assigned Identification", "350" );
		
		elementCodeMap_.put( "Transaction Type Code", "640" );
		
		elementCodeMap_.put( "Unit or Basis for Measurement Code", "355" );
		
		elementCodeMap_.put( "Product/Service ID Qualifier", "235" );
		
		elementCodeMap_.put( "Product/Service ID", "234" );
		
		elementCodeMap_.put( "Item Description Type", "349" );
		
		elementCodeMap_.put( "Description", "352" );
		
		elementCodeMap_.put( "Number of Line Items", "354" );
		
		elementCodeMap_.put( "Number of Included Segments", "96" );
		
		elementCodeMap_.put( "Unit Price", "212" );
		
		elementCodeMap_.put( "Basis of Unit Price Code", "639" );
		
		elementCodeMap_.put( "Pricing Multiplier Qualifier", "648" );
		
		elementCodeMap_.put( "Multiplier", "649" );
				
		elementCodeMap_.put( "Date/Time Qualifier", "374" );
		
		elementCodeMap_.put( "Quantity Invoiced", "358" );
		
		elementCodeMap_.put( "Amount Qualifier Code", "522" );
		
		elementCodeMap_.put( "Monetary Amount", "782" );
		
		elementCodeMap_.put( "Standard Carrier Alpha Code", "140" );
		
		elementCodeMap_.put( "Routing", "387" );
		
	
		
	}
	
	/*
	 * Method : init( )
	 * 
	 * Input : None
	 * 
	 * Return : None
	 * 
	 * Purpose: This method is used to load the segment codes to map
	 */
	private void loadSegmentCodeMap( )
	{
		segCodeMap_.put( "Transaction Set Header", "ST" );
		
		segCodeMap_.put( "Beginning Segment Invoice", "BIG" );
		
		segCodeMap_.put( "Currency", "CUR" );
		
		segCodeMap_.put( "Reference Identification", "REF" );
		
		segCodeMap_.put( "Administrative Communications Contact", "PER" );
		
		segCodeMap_.put( "Additional Name Information", "N2" );
		
		segCodeMap_.put( "Address Information", "N3" );
		
		segCodeMap_.put( "Geographic Location", "N4" );
		
		segCodeMap_.put( "Name", "N1" );
		
		segCodeMap_.put( "Baseline Item Data", "IT1" );
		
		segCodeMap_.put( "Product/Item Description", "PID" );
		
		segCodeMap_.put( "Transaction Set Trailer", "SE" );
		
		segCodeMap_.put( "Pricing Information", "CTP" );
		
		segCodeMap_.put( "Contract Effective Date Time Reference", "DTM" );
		
		segCodeMap_.put( "Transaction Totals", "CTT" );
		
		segCodeMap_.put( "Monetary Amount", "AMT" );
		
		segCodeMap_.put( "Carrier Details", "CAD" );
		
		
		
	}


	/* (non-Javadoc)
	 * @see core.edi.EDISpec#getElementId(java.lang.String)
	 */
	
	/*
	 * Method : getElementId( )
	 * 
	 * Input  : string
	 * 
	 * Return : string
	 * 
	 * Purpose: This method is used to get the element id from map for
	 * given element name and return the element id
	 */
	
   // @Override
    public String getElementCode( String elementName )
    {
	    try
        {
	       return elementCodeMap_.get( elementName );
        }
        catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::InvoiceEDISpec.getElementId- " + e );
	        return null;
        }
    }


	/* (non-Javadoc)
	 * @see core.edi.EDISpec#getSegmentId(java.lang.String)
	 */
    
    /*
	 * Method : getSegmentId( )
	 * 
	 * Input  : string
	 * 
	 * Return : string
	 * 
	 * Purpose: This method is used to get the segment id from map for
	 * given segment name and return the segment id
	 */
    
    //@Override
    public String getSegmentCode( String segmentName )
    {
    	try
        {
    		 return segCodeMap_.get( segmentName );
        }
        catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::InvoiceEDISpec.getSegmentId- " + e );
	        return null;
        }
    }


}

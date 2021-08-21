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
package core.trans;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * File:  RFQItemData.java 
 *
 * Created on 20-Jun-2013 3:38:07 PM
 */
public class RFQItemData
{
	// This is the transaction id 
	public long transId_;
		
	// This is the unique id of this RFQ
	public long RFQId_;
	
	// Item description
	public String itemDesc_;
	
	// Item part number
	public String partNo_;
	
	// Quantity of the item
	public double quantity_;
	
	// ShipDate of the item
	public Date shipDate_;
	
	// Unit of the quantity ex(KG,Units,numbers)
	public String quantityUnit_;
	
	// CreatedTimestamp
	public Timestamp createdTimestamp_;

	/*
	 * Method : RFQItemData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public RFQItemData()
	{
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
		System.out.println( "transId_				= " + transId_ );
		System.out.println( "RFQId_					= " + RFQId_ );
		
		System.out.println( "itemDesc_				= " + itemDesc_ );
		System.out.println( "partNo_				= " + partNo_ );
		
		System.out.println( "quantity_				= " + quantity_ );
		System.out.println( "shipDate_				= " + shipDate_ );
		
		System.out.println( "quantityUnit_			= " + quantityUnit_ );
		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
		
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
		transId_ 			= -1;
		RFQId_ 				= -1;
		
		itemDesc_ 			= null;
		partNo_ 			= null;
		
		quantity_ 			= 0.0f;
		shipDate_ 			= null;
		
		quantityUnit_ 		= null;
		createdTimestamp_	= null;
	}

}

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

package db.trans;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * File:  POItemRecord.java 
 *
 * Created on Jul 4, 2013 2:22:19 PM
 */

public class POItemRecord
{
	// This is the transaction id
	public long		transId_;

	// This is the unique id of this Purchase Order
	public long		poId_;
	
	// Item description
	public String 	itemDesc_;
		
	// Item part number
	public String 	partNo_;
		
	// Quantity of the item
	public double 	quantity_;
		
	// ShipDate of the item
	public Date 	shipDate_;
		
	// Unit of the quantity ex(KG,Units,numbers)
	public String 	quantityUnit_;
	
	// Price of the item
	public double 	price_;

	// Currency of the item
	public String 	currency_;
	
	//Multiplier of the item
	public int 		multiplier_;
	
	// CreatedTimestamp
	public Timestamp createdTimestamp_;


	/*
	 * Method : QuoteItemRecord -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public POItemRecord()
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
		System.out.println( "POId_					= " + poId_ );
		
		System.out.println( "itemDesc_				= " + itemDesc_ );
		System.out.println( "partNo_				= " + partNo_ );
		
		System.out.println( "quantity_				= " + quantity_ );
		System.out.println( "shipDate_				= " + shipDate_ );
		
		System.out.println( "quantityUnit_			= " + quantityUnit_ );
		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
		
		System.out.println( "price_					= " + price_ );
		System.out.println( "currency_				= " + currency_ );
		System.out.println( "multiplier_			= " + multiplier_ );
		
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
		poId_	 			= -1;
		
		itemDesc_ 			= null;
		partNo_ 			= null;
		
		quantity_ 			= 0.0f;
		shipDate_ 			= null;
		
		quantityUnit_ 		= null;
		createdTimestamp_	= null;
		
		price_				= 0.0f;
		currency_			= null;
		multiplier_			= -1;
	}
}

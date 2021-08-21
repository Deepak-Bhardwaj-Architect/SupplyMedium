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
 * File:  InvoiceItemData.java 
 *
 * Created on Jul 6, 2013 4:32:57 PM
 */
public class InvoiceItemData
{
	// This is the transaction id
	public long	transId_;

	// This is the unique id of this invoice
	public long invoiceId_;
	
	// Item description
	public String itemDesc_;
		
	// Item part number
	public String partNo_;
		
	// Quantity of the item ordered
	public double quantityOrdered_;
		
	// ShipDate of the item
	public Date shipDate_;
		
	// Unit of the quantity ordered ex(KG,Units,numbers)
	public String quantityOrderedUnit_;
	
	// Price of the item
	public double price_;

	// Currency of the item
	public String currency_;
	
	// Multiplier of the item
	public int multiplier_;
	
	// Quantity of the item shipped
	public double quantityShipped_;
	
	//unit of the quantity shipped
	public String quantityShippedUnit_;
	
	// CreatedTimestamp
	public Timestamp createdTimestamp_;


	/*
	 * Method : InvoiceItemData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public InvoiceItemData()
	{
		transId_ = -1;
		
		invoiceId_ = -1;
		
		multiplier_ = -1;
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
		System.out.println( "invoiceId_				= " + invoiceId_ );
		
		System.out.println( "itemDesc_				= " + itemDesc_ );
		System.out.println( "partNo_				= " + partNo_ );
		
		System.out.println( "quantityOrdered_		= " + quantityOrdered_ );
		System.out.println( "shipDate_				= " + shipDate_ );
		
		System.out.println( "quantityOrderedUnit_	= " + quantityOrderedUnit_ );
		System.out.println( "createdTimestamp_		= " + createdTimestamp_ );
		
		System.out.println( "price_					= " + price_ );
		System.out.println( "currency_				= " + currency_ );
		
		System.out.println( "quantityShipped_		= " + quantityShipped_ );
		System.out.println( "quantityShippedUnit_	= " + quantityShippedUnit_ );
		
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
		invoiceId_ 			= -1;
		
		itemDesc_ 			= null;
		partNo_ 			= null;
		
		quantityOrdered_	= 0.0f;
		shipDate_ 			= null;
		
		quantityOrderedUnit_= null;
		createdTimestamp_	= null;
		
		price_				= 0.0f;
		currency_			= null;
		
		quantityShipped_	= 0.0f;
		quantityShippedUnit_ = null;
		
		multiplier_			= -1;
	}
}

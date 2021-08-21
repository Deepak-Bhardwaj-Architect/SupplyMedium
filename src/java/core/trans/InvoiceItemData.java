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
import utils.ErrorMaster;

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
        
        public String brcd_;
		
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
        
        private static ErrorMaster errorMaster_ = null;




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
                
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "transId_				= " + transId_ );
		errorMaster_.insert( "invoiceId_				= " + invoiceId_ );
		
		errorMaster_.insert( "itemDesc_				= " + itemDesc_ );
		errorMaster_.insert( "partNo_				= " + partNo_ );
		
		errorMaster_.insert( "quantityOrdered_		= " + quantityOrdered_ );
		errorMaster_.insert( "shipDate_				= " + shipDate_ );
		
		errorMaster_.insert( "quantityOrderedUnit_	= " + quantityOrderedUnit_ );
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
		
		errorMaster_.insert( "price_					= " + price_ );
		errorMaster_.insert( "currency_				= " + currency_ );
		
		errorMaster_.insert( "quantityShipped_		= " + quantityShipped_ );
		errorMaster_.insert( "quantityShippedUnit_	= " + quantityShippedUnit_ );
		
		errorMaster_.insert( "multiplier_			= " + multiplier_ );
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
                brcd_=null;
		
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

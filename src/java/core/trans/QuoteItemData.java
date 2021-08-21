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
 * File:  QuoteItemData.java 
 *
 * Created on 21-Jun-2013 4:53:14 PM
 */

public class QuoteItemData
{
	// This is the transaction id
	public long              transId_;

	// This is the unique id of this Quote
	public long              quoteId_;
	
	// Item description
	public String itemDesc_;
		
	// Item part number
	public String partNo_;
        
        public String brcd_;
		
	// Quantity of the item
	public double quantity_;
		
	// ShipDate of the item
	public Date shipDate_;
		
	// Unit of the quantity ex(KG,Units,numbers)
	public String quantityUnit_;
	
	// Price of the item
	public double price_;

	public String currency_;
	
	public int multiplier_;
	
	// CreatedTimestamp
	public Timestamp createdTimestamp_;
        
        private static ErrorMaster errorMaster_ = null;




	/*
	 * Method : QuoteItemData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public QuoteItemData()
	{
		transId_ = -1;
		
		quoteId_ = -1;
		
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
		errorMaster_.insert( "RFQId_					= " + quoteId_ );
		
		errorMaster_.insert( "itemDesc_				= " + itemDesc_ );
		errorMaster_.insert( "partNo_				= " + partNo_ );
		
		errorMaster_.insert( "quantity_				= " + quantity_ );
		errorMaster_.insert( "shipDate_				= " + shipDate_ );
		
		errorMaster_.insert( "quantityUnit_			= " + quantityUnit_ );
		errorMaster_.insert( "createdTimestamp_		= " + createdTimestamp_ );
		
		errorMaster_.insert( "price_					= " + price_ );
		errorMaster_.insert( "currency_				= " + currency_ );
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
		quoteId_ 			= -1;
		
		itemDesc_ 			= null;
		partNo_ 			= null;
                brcd_ 			= null;
		
		quantity_ 			= 0.0f;
		shipDate_ 			= null;
		
		quantityUnit_ 		= null;
		createdTimestamp_	= null;
		
		price_				= 0.0f;
		currency_			= null;
		multiplier_			= -1;
	}
}

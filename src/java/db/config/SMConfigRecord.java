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

package db.config;

import utils.ErrorMaster;

/**
 * File: SMConfigRecord.java
 * 
 * Created on Jan 5, 2013 3:29:51 PM
 */

//This DB record class is related to supply_medium_config table in db

public class SMConfigRecord
{
	public int regLinkExpireDays_;
	public int recycleExpireDays_;
	public double taxPercentage_;
	
	public SMConfigRecord( )
	{
		this.regLinkExpireDays_ = 0;
		this.recycleExpireDays_ = 0;
		this.taxPercentage_		= 0.0f;
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
		this.regLinkExpireDays_ = 0;
		this.recycleExpireDays_ = 0;
		this.taxPercentage_		= 0.0f;
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
           ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
            
		errorMaster_.insert( "regLinkExpireDays_=" + regLinkExpireDays_ );
		errorMaster_.insert( "recycleExpireDays_=" + recycleExpireDays_ );
		errorMaster_.insert( "taxPercentage_		=" + taxPercentage_ );
	}

}

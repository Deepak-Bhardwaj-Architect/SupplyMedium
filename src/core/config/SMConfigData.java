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

package core.config;

/**
 * File: SMConfigData.java
 * 
 * Created on Jan 10, 2013 11:27:28 AM
 */

// This core data class is related to supply_medium_config table in db

public class SMConfigData
{
	public int regLinkExpireDays_;
	public int recycleExpireDays_;
	public double taxPercentage_;
	
	public SMConfigData( )
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
		System.out.println( "regLinkExpireDays_=" + regLinkExpireDays_ );
		System.out.println( "recycleExpireDays_=" + recycleExpireDays_ );
		System.out.println( "taxPercentage_		=" + taxPercentage_ );
	}

}

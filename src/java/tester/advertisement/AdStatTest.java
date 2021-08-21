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
package tester.advertisement;



import core.advertisement.AdStatistic;
import core.advertisement.AdStatisticData;
import utils.ErrorMaster;

/**
 * File:  AdStat.java 
 *
 * Created on Oct 7, 2013 7:49:51 PM
 */
public class AdStatTest
{
	private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : AdStat -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public AdStatTest()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	public void execute()
	{
		addTest( );
		
	}
		

	/*
	 * Method : addTest()
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: it is used to add the new Advertisement. 
	 */
	public void addTest()
	{
		AdStatistic adStatistic=new AdStatistic( );
		
		AdStatisticData statisticData=new AdStatisticData( );
		
		statisticData.adId_ = 10;
		
		int result=adStatistic.add( statisticData );
		
		errorMaster_.insert( "AdStatistic result id"+result );
		
		if( result == 10100  )
		{
			errorMaster_.insert( "Advertisement add test successfully completed" );
			
			errorMaster_.insert( "Advertisement added successfully" );
		}
		else if (result == 10103) 
		{
			errorMaster_.insert( "Advertisement add test successful" );
			
			errorMaster_.insert( "Advertisement already exist" );
		}
		else
		{
			errorMaster_.insert( "Advertisement add test failed" );
		}
			
		
		
		
	}

}

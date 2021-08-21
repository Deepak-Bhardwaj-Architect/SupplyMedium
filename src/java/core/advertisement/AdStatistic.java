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
package core.advertisement;

import java.util.Date;

import db.advertisement.AdStatTable;

/**
 * File:  AdStatistic.java 
 *
 * Created on Oct 7, 2013 5:31:07 PM
 */
public class AdStatistic
{
	
	/*
	 * Method : AdStatistic -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public AdStatistic()
	{
		
	}
	
	
	/*
	 * Method : add
	 * 
	 * Input  : StatisticData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to add the new Advertisement  
	 */
	
	public int add(AdStatisticData statisticData)
	{
		
		AdStatTable adStatTable=new AdStatTable( );
		
		Date date = new Date(  );
		
		statisticData.date_ = new java.sql.Date( date.getTime( ) );
		
		int result=adStatTable.isRecordExist(statisticData);
		
		if( result == 1 )//adstatistic already exist
		{
			statisticData.count_=statisticData.count_+1;
			
			result=adStatTable.update( statisticData );
		}
		else
		{
			statisticData.count_ = 1;
			
			result = adStatTable.insert( statisticData );
			
		}
		

		
		adStatTable = null;
		
		if( result == 0 )
			return 10100;  // Advertisement successfully created
		else
			return 10104;  // Advertisement creation failed 
		
	}
}

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
package core.useracctmgmt;

import db.useracctmgmt.UserWorkingHoursDefaultsTable;
import db.useracctmgmt.UserWorkingHoursTable;

/**
 * File:  UserWorkingHoursSettings.java 
 *
 * Created on Apr 22, 2013 10:37:17 AM
 */

/*
 * Class: UserWorkingHoursSettings
 * 
 * Purpose: This class is used create new working hour settings
 * for new user and update the existing working hour settings
 * for the existing user
 */

public class UserWorkingHoursSettings
{
	
	/*Constructor*/
	
	public UserWorkingHoursSettings( )
	{
		
	}
	
	/*
	 * Method: create
	 * 
	 * Input: WorkingHoursData
	 * 
	 * Return: int
	 * 
	 * Purpose: This is used to create new working hour settings
	 * data for new user
	 * 
	 */
	
	public int create( WorkingHoursData workingHrsData )
	{
		UserWorkingHoursDefaultsTable workingHrsDefTbl = new UserWorkingHoursDefaultsTable( );
		
		int result = workingHrsDefTbl.getWorkingHoursDefaults( workingHrsData );
		
		if( result != 0 )
		{
			workingHrsDefTbl = null;
			
			return 2651; //Error occurred while fetching working hours defaults
		}
		
		workingHrsDefTbl = null;
		
		UserWorkingHoursTable workingHrsTbl = new UserWorkingHoursTable( );
		
		result = workingHrsTbl.insert( workingHrsData );
		
		if( result != 0 )
		{
			workingHrsTbl = null;
			
			return 2652; //Error occurred while inserting working hours defaults
		}
		
		workingHrsTbl = null;
		
		return 2650; // Inserting new working hour settings - Success
 	}
	
	/*
	 * Method: update
	 * 
	 * Input: WorkingHoursData object
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to udpate the existing working hours
	 * settings
	 */
	
	public int update( WorkingHoursData workingHrsData )
	{
		UserWorkingHoursTable workingHrsTbl = new UserWorkingHoursTable( );
		
		UserWorkingDaysConverter converter = new UserWorkingDaysConverter( );
		
		workingHrsData.workingDays_ = converter.encode( workingHrsData.workingDaysData_ );
		
		converter = null;
		
		int result = workingHrsTbl.update( workingHrsData );
		
		if( result != 0 )
		{
			workingHrsTbl = null;
			
			return 2661; //Error occurred while updating working hours settings
		}
		
		workingHrsTbl = null;
		
		return 2660; //Updating working hours settings - Success
	}
	
	/*
	 * Method: get
	 * 
	 * Input: WorkingHoursData object
	 *  
	 * Return: int
	 * 
	 *  Purpose: To fetch the user working hours data
	 */
	
	public int get( WorkingHoursData workingHrsData )
	{
		UserWorkingHoursTable workingHrsTbl = new UserWorkingHoursTable( );
		
		UserWorkingDaysConverter converter = new UserWorkingDaysConverter( );
		
		int result = converter.decode( workingHrsData.workingDays_, workingHrsData.workingDaysData_ );
		
		if( result != 0 )
		{	
			converter = null;
			
			return 2672;  //Error occurred while decoding 
		}
		
		result = workingHrsTbl.find( workingHrsData );
		
		if( result != 0)
		{
			workingHrsTbl = null;
			
			return 2671; //Error occurred while fetching working hours settings
		}
		
		workingHrsTbl = null;
		
		return 2670; //Successfully fetches the working hours settings
	}
	
}

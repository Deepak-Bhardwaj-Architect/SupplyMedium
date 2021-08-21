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

import core.regn.UserProfileKey;

/**
 * File: WorkingDaysData.java
 * 
 * Created on Apr 22, 2013 3:17:53 PM
 */

/*
 * Class: WorkingDaysData
 * 
 * Purpose: This class contains the domain variables for working days. All
 * the variables are initialized here.
 * 
 */

public class WorkingDaysData
{

	public UserProfileKey userProfileKey_;

	public Boolean        sunWorkingFlag_;
	public Boolean        monWorkingFlag_;
	
	public Boolean        tueWorkingFlag_;
	public Boolean        wedWorkingFlag_;
	
	public Boolean        thuWorkingFlag_;
	public Boolean        friWorkingFlag_;

	public Boolean        satWorkingFlag_;

	/*
	 * Method: WorkingDaysData() -- Constructor
	 * 
	 * Input: none
	 * 
	 * Return: none
	 * 
	 * Purpose: This method intializes the UserProfileKey object
	 * 
	 */

	public WorkingDaysData()
	{
		userProfileKey_ = new UserProfileKey( );
	}
	
	/*
	 * Method: show()
	 * 
	 * Input: none
	 * 
	 * Return: void
	 * 
	 * Purpose: This method is used to print all the domain variables in console
	 */
	
	public void show( )
	{
		System.out.println( "userProfileKey_ = " + userProfileKey_.email_ );
		
		System.out.println( "userProfileKey_ = " + sunWorkingFlag_ );
		System.out.println( "userProfileKey_ = " + monWorkingFlag_ );
		
		System.out.println( "userProfileKey_ = " + tueWorkingFlag_ );
		System.out.println( "userProfileKey_ = " + wedWorkingFlag_ );
		
		System.out.println( "userProfileKey_ = " + thuWorkingFlag_ );
		System.out.println( "userProfileKey_ = " + friWorkingFlag_ );
		
		System.out.println( "userProfileKey_ = " + satWorkingFlag_ );
		
	}
	
	/*
	 * Method: clear()
	 * 
	 * Input: none
	 * 
	 * Return: void
	 * 
	 * Purpose: This method is used to clear/null all domain variables
	 * 
	 */
	
	public void clear( )
	{
		userProfileKey_ = null;
		
		sunWorkingFlag_ = null;
		monWorkingFlag_ = null;
		
		tueWorkingFlag_ = null;
		wedWorkingFlag_ = null;
		
		thuWorkingFlag_ = null;
		friWorkingFlag_ = null;
		
		satWorkingFlag_ = null;
	}
}

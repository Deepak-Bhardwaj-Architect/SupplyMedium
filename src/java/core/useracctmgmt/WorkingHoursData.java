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

import java.sql.Time;

import core.regn.UserProfileKey;
import utils.ErrorMaster;

/**
 * File: WorkingHoursData.java
 * 
 * Created on Apr 22, 2013 12:47:58 PM
 */

/*
 * Class: WorkingHoursData
 * 
 * Purpose: This class contains the domain variables of user working hours. All
 * the variables are initialized here.
 */

public class WorkingHoursData
{
	public UserProfileKey  userProfileKey_;
	public int             workingDays_;
	public WorkingDaysData workingDaysData_;

	public Time       monFromTime_;
	public Time       monToTime_;

	public Time       tueFromTime_;
	public Time       tueToTime_;

	public Time       wedFromTime_;
	public Time       wedToTime_;

	public Time       thuFromTime_;
	public Time       thuToTime_;

	public Time       friFromTime_;
	public Time       friToTime_;

	public Time       satFromTime_;
	public Time       satToTime_;

	public Time       sunFromTime_;
	public Time       sunToTime_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method: WorkingHoursData() -- Constructor
	 * 
	 * Input: none
	 * 
	 * Return: none
	 * 
	 * Purpose: This method intializes the UserProfileKey object and
	 * WorkingDaysData object
	 */

	public WorkingHoursData()
	{
		userProfileKey_ = new UserProfileKey( );
		workingDaysData_ = new WorkingDaysData( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "userProfileKey_= " + userProfileKey_.email_ );
		errorMaster_.insert( "workingDays_ 	= " + workingDays_ );

		errorMaster_.insert( "monFromTime_	= " + monFromTime_ );
		errorMaster_.insert( "monToTime_ 	= " + monToTime_ );

		errorMaster_.insert( "tueFromTime_ 	= " + tueFromTime_ );
		errorMaster_.insert( "tueToTime_ 	= " + tueToTime_ );

		errorMaster_.insert( "wedFromTime_ 	= " + wedFromTime_ );
		errorMaster_.insert( "wedToTime_ 	= " + wedToTime_ );

		errorMaster_.insert( "thuFromTime_ 	= " + thuFromTime_ );
		errorMaster_.insert( "thuToTime_ 	= " + thuToTime_ );

		errorMaster_.insert( "friFromTime_ 	= " + friFromTime_ );
		errorMaster_.insert( "friToTime_ 	= " + friToTime_ );

		errorMaster_.insert( "satFromTime_ 	= " + satFromTime_ );
		errorMaster_.insert( "satToTime_ 	= " + satToTime_ );

		errorMaster_.insert( "sunFromTime_ 	= " + sunFromTime_ );
		errorMaster_.insert( "sunToTime_ 	= " + sunToTime_ );
	}

	/*
	 * Method: clear()
	 * 
	 * Input: none
	 * 
	 * Return: void
	 * 
	 * Purpose: This method is used to clear/null all domain variables
	 */

	public void clear( )
	{
		userProfileKey_ 	= null;
		workingDays_ 		= 0;
		workingDaysData_ 	= null;

		monFromTime_ 	= null;
		monToTime_ 		= null;

		tueFromTime_ 	= null;
		tueToTime_ 		= null;

		wedFromTime_ 	= null;
		wedToTime_ 		= null;

		thuFromTime_ 	= null;
		thuToTime_ 		= null;

		friFromTime_ 	= null;
		friToTime_ 		= null;

		satFromTime_ 	= null;
		satToTime_ 		= null;

		sunFromTime_ 	= null;
		sunToTime_ 		= null;
	}
	
}

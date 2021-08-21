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
package db.useracctmgmt;


import java.sql.Time;
import utils.ErrorMaster;

/**
 * File:  UserWorkingHoursRecord.java 
 *
 * Created on Apr 22, 2013 2:18:58 PM
 */

//This DB record class is related to user_workinghours table in db

public class UserWorkingHoursRecord
{
	public String userRelKey_;
	public int  workingDays_;

	public Time sunFromTime_;
	public Time sunToTime_;
	
	public Time monFromTime_;
	public Time monToTime_;

	public Time tueFromTime_;
	public Time tueToTime_;

	public Time wedFromTime_;
	public Time wedToTime_;

	public Time thuFromTime_;
	public Time thuToTime_;

	public Time friFromTime_;
	public Time friToTime_;

	public Time satFromTime_;
	public Time satToTime_;

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
		errorMaster_.insert( "userRelKey_	= " + userRelKey_ );
		errorMaster_.insert( "workingDays_	= " + workingDays_ );

		errorMaster_.insert( "sunFromTime_	= " + sunFromTime_ );
		errorMaster_.insert( "sunToTime_		= " + sunToTime_ );

		errorMaster_.insert( "monFromTime_	= " + monFromTime_ );
		errorMaster_.insert( "monToTime_		= " + monToTime_ );

		errorMaster_.insert( "tueFromTime_	= " + tueFromTime_ );
		errorMaster_.insert( "tueToTime_		= " + tueToTime_ );

		errorMaster_.insert( "wedFromTime_	= " + wedFromTime_ );
		errorMaster_.insert( "wedToTime_		= " + wedToTime_ );

		errorMaster_.insert( "thuFromTime_	= " + thuFromTime_ );
		errorMaster_.insert( "thuToTime_		= " + thuToTime_ );

		errorMaster_.insert( "friFromTime_	= " + friFromTime_ );
		errorMaster_.insert( "friToTime_		= " + friToTime_ );

		errorMaster_.insert( "satFromTime_	= " + satFromTime_ );
		errorMaster_.insert( "satToTime_		= " + satToTime_ );
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
		userRelKey_ 	= null;
		workingDays_ 	= 0;

		sunFromTime_ 	= null;
		sunToTime_ 		= null;

		monFromTime_ 	= null;
		monToTime_ 		= null;

		tueFromTime_ 	= null;
		tueToTime_ 		= null;

		wedFromTime_	= null;
		wedToTime_ 		= null;

		thuFromTime_ 	= null;
		thuToTime_ 		= null;

		friFromTime_ 	= null;
		friToTime_ 		= null;

		satFromTime_ 	= null;
		satToTime_ 		= null;

	}
}

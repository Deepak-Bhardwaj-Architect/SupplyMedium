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

/**
 * File: UserWorkingHoursDefaultsRecord.java
 * 
 * Created on Apr 22, 2013 2:20:17 PM
 */

// This DB record class is related to user_workinghours_defaults table in db

public class UserWorkingHoursDefaultsRecord
{
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
		System.out.println( "workingDays_	= " + workingDays_ );

		System.out.println( "sunFromTime_	= " + sunFromTime_ );
		System.out.println( "sunToTime_		= " + sunToTime_ );

		System.out.println( "monFromTime_	= " + monFromTime_ );
		System.out.println( "monToTime_		= " + monToTime_ );

		System.out.println( "tueFromTime_	= " + tueFromTime_ );
		System.out.println( "tueToTime_		= " + tueToTime_ );

		System.out.println( "wedFromTime_	= " + wedFromTime_ );
		System.out.println( "wedToTime_		= " + wedToTime_ );

		System.out.println( "thuFromTime_	= " + thuFromTime_ );
		System.out.println( "thuToTime_		= " + thuToTime_ );

		System.out.println( "friFromTime_	= " + friFromTime_ );
		System.out.println( "friToTime_		= " + friToTime_ );

		System.out.println( "satFromTime_	= " + satFromTime_ );
		System.out.println( "satToTime_		= " + satToTime_ );
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
		workingDays_ = 0;

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

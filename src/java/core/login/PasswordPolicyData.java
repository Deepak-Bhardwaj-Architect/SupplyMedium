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
package core.login;

import utils.ErrorMaster;

/**
 * File:  PasswordPolicyData.java 
 *
 * Created on Mar 9, 2013 11:04:05 AM
 */

/*
 * Class  : PasswordPolicyData
 *
 * Purpose: 
 * 
 */

public class PasswordPolicyData
{
	public String regnRelKey_;
	
	public int    passwordHistoryDays_;
	public int    passwordAgeMinDays_; 

	public int 	  passwordAgeMaxDays_;
	public int	  passwordLength_;
	
	public int    notificationRemainderNday_; 
	public int    dailyRemainderFlag_;
        
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "regnRelKey_			= " + regnRelKey_ );
		
		errorMaster_.insert( "passwordHistoryDays_	= " + passwordHistoryDays_ );
		errorMaster_.insert( "passwordAgeMinDays_	= " + passwordAgeMinDays_ );
		
		errorMaster_.insert( "passwordAgeMaxDays_	= " + passwordAgeMaxDays_ );
		errorMaster_.insert( "passwordLength_		= " + passwordLength_ );

		errorMaster_.insert( "notificationRemainderNday_	= " + notificationRemainderNday_ );
		errorMaster_.insert( "dailyRemainderFlag_	= " + dailyRemainderFlag_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.regnRelKey_ = null;
		
		this.passwordHistoryDays_ = 0;
		this.passwordAgeMinDays_ = 0;

		this.notificationRemainderNday_ = 0;
		this.dailyRemainderFlag_ = 0;
		
		this.passwordAgeMaxDays_	= 0;
		this.passwordLength_	= 0;
	}

}

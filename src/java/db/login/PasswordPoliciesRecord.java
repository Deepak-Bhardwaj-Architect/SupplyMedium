package db.login;

import utils.ErrorMaster;

/*
 * Class  : AccountPoliciesRecord
 *
 * Purpose: This is the mapper class for AccountPoliciesTable
 * 
 */

public class PasswordPoliciesRecord
{
	public String regnRelKey_;
	public int    passwordHistoryDays_;
	public int    passwordAgeMaxDays_;

	public int    passwordAgeMinDays_;
	public int    passwordLength_;
	public int    notificationRemainderNday_;

	public int    dailyRemainderFlag_;

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
                ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "regnRelKey_		= " + regnRelKey_ );
		errorMaster_.insert( "passwordHistoryDays_	= " + passwordHistoryDays_ );
		errorMaster_.insert( "passwordAgeMaxDays_	= " + passwordAgeMaxDays_ );

		errorMaster_.insert( "passwordAgeMinDays_	= " + passwordAgeMinDays_ );
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
		this.passwordAgeMaxDays_ = 0;

		this.passwordAgeMinDays_ = 0;
		this.passwordLength_ = 0;
		this.notificationRemainderNday_ = 0;

		this.dailyRemainderFlag_ = 0;
	}

}

package db.login;

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
		System.out.println( "regnRelKey_		= " + regnRelKey_ );
		System.out.println( "passwordHistoryDays_	= " + passwordHistoryDays_ );
		System.out.println( "passwordAgeMaxDays_	= " + passwordAgeMaxDays_ );

		System.out.println( "passwordAgeMinDays_	= " + passwordAgeMinDays_ );
		System.out.println( "passwordLength_		= " + passwordLength_ );
		System.out.println( "notificationRemainderNday_	= " + notificationRemainderNday_ );

		System.out.println( "dailyRemainderFlag_	= " + dailyRemainderFlag_ );
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

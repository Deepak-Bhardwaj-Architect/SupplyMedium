package db.usermgmt;

import java.sql.Timestamp;
import utils.ErrorMaster;

/*
 * Class  : UserAccountPoliciesRecord
 *
 * Purpose: This is the mapper class for UserAccuntPoliciesTable
 * 
 */

public class UserAccountPoliciesRecord
{
	public String    userRelKey_;
	public int       changePasswordFlag_;

	public int       disableAccountFlag_;
	public int		 accountExpireDays_;

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "userRelKey_		= " + userRelKey_ );
		errorMaster_.insert( "changePasswordFlag_	= " + changePasswordFlag_ );

		errorMaster_.insert( "disableAccountFlag_	= " + disableAccountFlag_ );
		errorMaster_.insert( "accountExpireDays_	= " + accountExpireDays_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.userRelKey_ = null;
		this.changePasswordFlag_ = 0;

		this.disableAccountFlag_ = 0;
		this.accountExpireDays_ = 0;
	}

}

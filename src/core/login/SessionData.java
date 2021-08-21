package core.login;

import java.sql.Timestamp;

import core.privilege.AllPrivilegesData;

/*
 * Class  : SessionData
 *
 * Purpose: This class contain the session data (which is stored in browser session). 
 */

public class SessionData
{
	public String            username_;
	public String            password_;
	public String            firstName_;

	public String            lastName_;
	public String            userType_;
	public String 			 companyRegnKeyStr_;

	public Timestamp         loginTimeStamp_;
	public int               sessionExpireMin_;

	public AllPrivilegesData allPrivilegesData_;
	public String			 companyName_;
	
	public String 			userImageURL_;
	public String 			companyType_;
	
	public String 			companyLogoUrl_;

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "username_		= " + username_ );
		System.out.println( "password_		= " + password_ );
		System.out.println( "firstName_		= " + firstName_ );

		System.out.println( "lastName_		= " + lastName_ );
		System.out.println( "userType_		= " + userType_ );
		System.out.println( "companyRegnKeyStr_		= " + companyRegnKeyStr_ );

		System.out.println( "loginTimeStamp_	= " + loginTimeStamp_ );
		System.out.println( "sessionExpireMin_	= " + sessionExpireMin_ );

		System.out.println( "allPrivilegesData_	= " + allPrivilegesData_ );
		System.out.println( "companyName_		= " + companyName_ );
		
		System.out.println( "userImageURL_      = " + userImageURL_);
		System.out.println( "companyType_      	= " + companyType_);
		
		System.out.println("companyLogoUrl_="+companyLogoUrl_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: It is used to reset all class variable values
	 */

	public void clear( )
	{
		this.username_ = null;
		this.password_ = null;
		this.firstName_ = null;

		this.lastName_ = null;
		this.userType_ = null;
		companyRegnKeyStr_ = null;

		this.loginTimeStamp_ = null;
		this.sessionExpireMin_ = 0;

		this.allPrivilegesData_ = null;
		this.companyName_	= null;
		
		userImageURL_ = null;
		companyName_  = null;
		
		companyLogoUrl_=null;
	}
}

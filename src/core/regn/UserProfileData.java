package core.regn;

import java.sql.Timestamp;

// This class contain user signup information details.

public class UserProfileData
{

	public CompanyRegnKey     companyRegnKey_;
	public UserProfileKey     userProfileKey_;
	public String             firstName_;
	public MailingAddressData mailingAddr_;

	public String             lastName_;
	public String             title_;
	public String             department_;

	public String             managerSupervisor_;
	public String             phoneNo_;
	
	public String             cellNo_;

	public String             faxNo_;
	public String             emailId_;
	public String             password_;

	public String             userType_;
	public String             userStatus_;

	public int                changePasswordFlag_;
	public int                disableAccountFlag_;
	//public Timestamp          accountExpireTs_;
	public int				  accountExpireDays_;

	public LoginData		  loginData_;
	public Timestamp		  createdDate_;
	public String			  profileUuid_;
	
	public String			  profilePicture_;
	public Object			  profileImage_;
	
	public UserProfileData( )
	{
		//userProfileKey_ = new UserProfileKey( );
	}
	
	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		this.companyRegnKey_ = null;
		this.firstName_ = null;
		this.mailingAddr_ = null;

		this.lastName_ = null;
		this.title_ = null;
		this.department_ = null;

		this.managerSupervisor_ = null;
		this.phoneNo_ = null;
		this.cellNo_ = null;

		this.faxNo_ = null;
		this.emailId_ = null;
		this.password_ = null;

		this.userType_ = null;
		this.userProfileKey_ = null;
		this.userStatus_ = null;

		this.changePasswordFlag_ = 0;
		this.disableAccountFlag_ = 0;
		this.accountExpireDays_ = 0;
		
		this.loginData_	= null;
		this.createdDate_ = null;
		this.profileUuid_ = null;
		
		this.profilePicture_ = null;
		this.profileImage_	= null;
	}

	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "-----------------------------------------" );
		System.out.println( "primaryPhoneNo_	= " + companyRegnKey_ );
		System.out.println( "firstName_			= " + firstName_ );

		System.out.println( "lastName_			= " + lastName_ );
		System.out.println( "title_				= " + title_ );
		System.out.println( "department_		= " + department_ );

		System.out.println( "managerSupervisor_	= " + managerSupervisor_ );
		System.out.println( "phoneNo_			= " + phoneNo_ );
		System.out.println( "cellNo_			= " + cellNo_ );

		System.out.println( "faxNo_				= " + faxNo_ );
		System.out.println( "emailId_			= " + emailId_ );
		System.out.println( "password_			= " + password_ );

		System.out.println( "mailingAddressObj_	= " + mailingAddr_ );

		System.out.println( "address_			= " + mailingAddr_.address_ );
		System.out.println( "city_				= " + mailingAddr_.city_ );
		System.out.println( "state_				= " + mailingAddr_.state_ );

		System.out.println( "zipcode_			= " + mailingAddr_.zipcode_ );
		System.out.println( "country_			= " + mailingAddr_.countryRegion_ );
		System.out.println( "userType_			= " + userType_ );

		System.out.println( "userProfileKey_	= " + userProfileKey_ );
		System.out.println( "userStatus_		= " + userStatus_ );

		System.out.println( "changePasswordFlag_	= " + changePasswordFlag_ );
		System.out.println( "disableAccountFlag_	= " + disableAccountFlag_ );
		System.out.println( "accountExpireDays_		= " + accountExpireDays_ );

		System.out.println( "loginData_		= " + loginData_ );
		System.out.println( "createdDate_	= " + createdDate_ );
		System.out.println( "profileUuid_	= " + profileUuid_ );
		
		System.out.println( "profilePicture_ = " + profilePicture_ );
		
		System.out.println( "-----------------------------------------" );
	}

}

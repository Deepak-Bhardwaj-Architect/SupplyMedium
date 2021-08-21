package core.regn;

import java.sql.Timestamp;
import utils.ErrorMaster;

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
        
        public String                    user_Store_profile_;
        public String user_url_path;
	public Object			  profileImage_;
        
        private static ErrorMaster errorMaster_ = null;


	
	public UserProfileData( )
	{
		//userProfileKey_ = new UserProfileKey( );
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
		errorMaster_.insert( "-----------------------------------------" );
		errorMaster_.insert( "primaryPhoneNo_	= " + companyRegnKey_ );
		errorMaster_.insert( "firstName_			= " + firstName_ );

		errorMaster_.insert( "lastName_			= " + lastName_ );
		errorMaster_.insert( "title_				= " + title_ );
		errorMaster_.insert( "department_		= " + department_ );

		errorMaster_.insert( "managerSupervisor_	= " + managerSupervisor_ );
		errorMaster_.insert( "phoneNo_			= " + phoneNo_ );
		errorMaster_.insert( "cellNo_			= " + cellNo_ );

		errorMaster_.insert( "faxNo_				= " + faxNo_ );
		errorMaster_.insert( "emailId_			= " + emailId_ );
		errorMaster_.insert( "password_			= " + password_ );

		errorMaster_.insert( "mailingAddressObj_	= " + mailingAddr_ );

		errorMaster_.insert( "address_			= " + mailingAddr_.address_ );
		errorMaster_.insert( "city_				= " + mailingAddr_.city_ );
		errorMaster_.insert( "state_				= " + mailingAddr_.state_ );

		errorMaster_.insert( "zipcode_			= " + mailingAddr_.zipcode_ );
		errorMaster_.insert( "country_			= " + mailingAddr_.countryRegion_ );
		errorMaster_.insert( "userType_			= " + userType_ );

		errorMaster_.insert( "userProfileKey_	= " + userProfileKey_ );
		errorMaster_.insert( "userStatus_		= " + userStatus_ );

		errorMaster_.insert( "changePasswordFlag_	= " + changePasswordFlag_ );
		errorMaster_.insert( "disableAccountFlag_	= " + disableAccountFlag_ );
		errorMaster_.insert( "accountExpireDays_		= " + accountExpireDays_ );

		errorMaster_.insert( "loginData_		= " + loginData_ );
		errorMaster_.insert( "createdDate_	= " + createdDate_ );
		errorMaster_.insert( "profileUuid_	= " + profileUuid_ );
		
		errorMaster_.insert( "profilePicture_ = " + profilePicture_ );
		
		errorMaster_.insert( "-----------------------------------------" );
	}

}

package core.login;

import java.sql.Timestamp;

import core.privilege.AllPrivilegesData;
import utils.ErrorMaster;

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
        
        private static ErrorMaster errorMaster_ = null;
        
        public String pypl_scs=null;
        public String pypl_fl=null;
        public String pypl_rslt=null;


        
        public SessionData()
        {
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
        }

	/*
	 * Method : show( ) Input : None Return : void
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		errorMaster_.insert( "username_		= " + username_ );
		errorMaster_.insert( "password_		= " + password_ );
		errorMaster_.insert( "firstName_		= " + firstName_ );

		errorMaster_.insert( "lastName_		= " + lastName_ );
		errorMaster_.insert( "userType_		= " + userType_ );
		errorMaster_.insert( "companyRegnKeyStr_		= " + companyRegnKeyStr_ );

		errorMaster_.insert( "loginTimeStamp_	= " + loginTimeStamp_ );
		errorMaster_.insert( "sessionExpireMin_	= " + sessionExpireMin_ );

		errorMaster_.insert( "allPrivilegesData_	= " + allPrivilegesData_ );
		errorMaster_.insert( "companyName_		= " + companyName_ );
		
		errorMaster_.insert( "userImageURL_      = " + userImageURL_);
		errorMaster_.insert( "companyType_      	= " + companyType_);
		
		errorMaster_.insert("companyLogoUrl_="+companyLogoUrl_ );
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
                
                this.pypl_scs = null;
		this.pypl_fl	= null;
                this.pypl_rslt=null;
	}
}

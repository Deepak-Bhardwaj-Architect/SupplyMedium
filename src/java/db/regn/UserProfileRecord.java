package db.regn;

import java.sql.Timestamp;
import utils.ErrorMaster;


//This DB record class is related to user_profile table in db

public class UserProfileRecord
{

	public long   userId_;
	public long   companyId_;
	public String firstName_;

	public String lastName_;
	public String title_;
	public String department_;

	public String userRole_;
	public String phoneNo_;
	public String cellNo_;

	public String faxNo_;
	public String emailId_;
	public String userType_;

	public String regnKey_;
	public String userProfileKey_;
	public String userStatus_;
	
	public Timestamp createdDate_;
	public String profilePicture_;

	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
           ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "userId_		= " + userId_ );
		errorMaster_.insert( "companyId_		= " + companyId_ );
		errorMaster_.insert( "firstName_		= " + firstName_ );

		errorMaster_.insert( "lastName_		= " + lastName_ );
		errorMaster_.insert( "title_			= " + title_ );
		errorMaster_.insert( "department_	= " + department_ );

		errorMaster_.insert( "userRole_		= " + userRole_ );
		errorMaster_.insert( "phoneNo_		= " + phoneNo_ );
		errorMaster_.insert( "cellNo_		= " + cellNo_ );

		errorMaster_.insert( "faxNo_			= " + faxNo_ );
		errorMaster_.insert( "emailId_		= " + emailId_ );
		errorMaster_.insert( "userType_		= " + userType_ );

		errorMaster_.insert( "companyPhoneNo = " + regnKey_ );
		errorMaster_.insert( "userProfileKey = " + userProfileKey_ );
		errorMaster_.insert( "userStatus		= " + userStatus_ );
		
		errorMaster_.insert( "createdDate_	= " + createdDate_ );
		errorMaster_.insert( "profilePicture_= " + profilePicture_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		this.userId_ = 0;
		this.companyId_ = 0;
		this.firstName_ = null;

		this.lastName_ = null;
		this.title_ = null;
		this.department_ = null;

		this.userRole_ = null;
		this.phoneNo_ = null;
		this.cellNo_ = null;

		this.faxNo_ = null;
		this.emailId_ = null;
		this.userType_ = null;

		this.regnKey_ = null;
		this.userProfileKey_ = null;
		this.userStatus_ = null;
		
		this.createdDate_ = null;
		this.profilePicture_ = null;
	}

}

package db.regn;

import java.sql.Timestamp;


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
		System.out.println( "userId_		= " + userId_ );
		System.out.println( "companyId_		= " + companyId_ );
		System.out.println( "firstName_		= " + firstName_ );

		System.out.println( "lastName_		= " + lastName_ );
		System.out.println( "title_			= " + title_ );
		System.out.println( "department_	= " + department_ );

		System.out.println( "userRole_		= " + userRole_ );
		System.out.println( "phoneNo_		= " + phoneNo_ );
		System.out.println( "cellNo_		= " + cellNo_ );

		System.out.println( "faxNo_			= " + faxNo_ );
		System.out.println( "emailId_		= " + emailId_ );
		System.out.println( "userType_		= " + userType_ );

		System.out.println( "companyPhoneNo = " + regnKey_ );
		System.out.println( "userProfileKey = " + userProfileKey_ );
		System.out.println( "userStatus		= " + userStatus_ );
		
		System.out.println( "createdDate_	= " + createdDate_ );
		System.out.println( "profilePicture_= " + profilePicture_ );
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

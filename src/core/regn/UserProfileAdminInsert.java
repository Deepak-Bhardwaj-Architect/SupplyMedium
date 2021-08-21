package core.regn;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import org.omg.CORBA.LongHolder;

import core.login.UserkeyUuidMapperData;
import core.privilege.UserPrivilegesData;
import core.useracctmgmt.NotificationSettingsData;
import core.useracctmgmt.UserNotificationSettings;
import core.useracctmgmt.UserWorkingHoursSettings;
import core.useracctmgmt.WorkingHoursData;

import utils.ErrorLogger;
import utils.HTMLMailComposer;
import utils.Mailer;
import db.login.ActivationPendingTable;
import db.login.UserkeyUuidMapperTable;
import db.privileges.PrivilegesDefaultsTable;
import db.privileges.UserPrivilegesTable;

import db.regn.CompanyRegnTable;
import db.regn.MailingAddressTable;
import db.regn.UserProfileTable;
import db.usermgmt.UserAccountDefaultsTable;
import db.usermgmt.UserAccountPoliciesTable;

/*
 * This is one of the implementation class for the interface
 * UserSignupProcess. This class is used to create a User from given user
 * signup details.
 */

public class UserProfileAdminInsert implements UserSignupProcess
{

	/*
	 * Method : process( ) Input : User Profile details Return : int value
	 * specify the User profile insert result (Success/failed)
	 * 
	 * Purpose: This method is used to process the inserting operations of user address details, 
	 * user profile details and user login details.  These details are obtained from UserProfileData
	 */

	public int process( UserProfileData userProfileData )
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		int addrVal = createUserAddress( userProfileData );

		if( addrVal != 0 )
		{
			// Log error
			errorLogger.logMsg( "Error::UserProfileAdminInsert.process() - Failed to create user address " +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );


			return 701;
			// Roll back code to be added here
		}

		System.out.println( "Address Details successfully inserted" );

		int profileVal = createUserProfile( userProfileData );

		if( profileVal != 0 )
		{
			errorLogger.logMsg( "Error::UserProfileAdminInsert.process() - Failed to create user profile " +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );

			return 701;
			// Roll back code to be added here
		}

		System.out.println( "Profile Details successfully inserted" );

		int loginVal = createUserLogin( userProfileData );

		if( loginVal != 0 )
		{
			errorLogger.logMsg( "Error::UserProfileAdminInsert.process() - Failed to create " +
								"login activation_pending" +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );

			return 701;
			// Roll back code to be added here
		}
		
		int accountDefaultsVal = setAccountDefaults( userProfileData );

		if( accountDefaultsVal != 0 )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger
			        .logMsg( "Error::UserProfileAdminInsert.process() - Failed to set account defaults" +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );

			// return 304;
		}

		int privilegesVal = setDefaultPrivileges( userProfileData );

		if( privilegesVal != 0 )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger
			        .logMsg( "Error::UserProfileAdminInsert.process() - Failed to set default privileges" +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );

			// return 305;
		}

		setNotifyDefaults( userProfileData );
		
		setWhDefaults( userProfileData );


		int mailSentVal = sendActivationLink( userProfileData );
		
		if( mailSentVal != 0 )
		{
			errorLogger.logMsg( "Error::UserProfileAdminInsert.process() - Failed to send activation link " +
								"for user " +"<" + userProfileData.firstName_+ ">, " +
								"<" + userProfileData.userProfileKey_.email_ + ">" );
			return 701;
		}

		return 700;
	}

	/*
	 * Method : createUserAddress( ) Input : user profile details Return : int
	 * 
	 * Purpose: This is used to create user Addresses for the given user profile data
	 * in the supply medium db. 
	 */

	public int createUserAddress( UserProfileData userProfileData )
	{
		int addrVal = 0;

		MailingAddressTable mailingAddressTblObj = new MailingAddressTable( );

		addrVal = mailingAddressTblObj.insertAddress( userProfileData.mailingAddr_ );

		mailingAddressTblObj = null;

		return addrVal;
	}

	/*
	 * Method : createUserProfile( ) Input : user profile details Return : int
	 * 
	 * Purpose: This is used to create user profile from given userProfileData in 
	 * the supply medium db.
	 */

	public int createUserProfile( UserProfileData userProfileData )
	{
		
		int profileVal = 0;
		
		UserProfileTable userProfileTblObj = new UserProfileTable( );
		
		profileVal = userProfileTblObj.insertUserProfile( userProfileData );
		
		userProfileTblObj = null;

		return profileVal;
	}

	/*
	 * Method : createUserLogin( ) Input : user signup details Return : int
	 * 
	 * Purpose: Insert the new user login details into activation_pending table.
	 * And return the 0 if login details successfully inserted in supply medium
	 * other wise not inserted.
	 */

	public int createUserLogin( UserProfileData userProfileData )
	{
		int loginVal = 0;
		
		LoginData loginData = createLoginData( userProfileData );
		
		ActivationPendingTable pendingTable = new ActivationPendingTable( );
		
		loginVal = pendingTable.insert( loginData );
		
		pendingTable = null;

		return loginVal;
	}

	/*
	 * Method : createLoginData( ) Input : user profile details Return : user
	 * login details
	 * 
	 * Purpose: This method is used to generate loginData obj for given
	 * userProfileData.
	 */

	private LoginData createLoginData( UserProfileData userProfileData )
	{
		LoginData loginData = new LoginData( );

		loginData.emailid_ = userProfileData.emailId_;
		loginData.password_ = userProfileData.password_;

		return loginData;
	}

	/*
	 * Method : sendActivationink( )
	 * 
	 * Input : 
	 * 
	 * Return : int
	 * 
	 * Purpose: 
	 */

	private int sendActivationLink( UserProfileData profileData )
	{
		int emailValidResult = 0;
		
		ActivationPendingTable actTbl = new ActivationPendingTable( );

		UserProfileKey userKey = new UserProfileKey( );

		userKey.email_ = profileData.emailId_;

		emailValidResult = actTbl.isEmailExists( userKey );

		if( emailValidResult == 0 ) // email exists
		{
			int insertUuidResult = 0;

			UserkeyUuidMapperData mapper = new UserkeyUuidMapperData( );

			mapper.userRelKey_ = profileData.emailId_;

			/*
			 * This call creates the random UUID number which will be unique
			 * everytime when it is called. This number is used to store as uuid
			 * for mapper table
			 */

			mapper.uuid_ = UUID.randomUUID( ).toString( );

			UserkeyUuidMapperTable mapperTable = new UserkeyUuidMapperTable( );

			if( mapperTable.isMapperRecExists( userKey ) == -1 )
			{
				insertUuidResult = mapperTable.insert( mapper );
			}
			else
			{
				insertUuidResult = mapperTable.update( mapper );
			}

			if( insertUuidResult == 0 ) // insert/update success
			{
				RegnData regnData = new RegnData( );
				
				String companyName = "";
				
				CompanyRegnTable regnTbl = new CompanyRegnTable( );
				
				regnTbl.getCompany( profileData.companyRegnKey_, regnData );
				
				System.out.println( "phone no="+profileData.companyRegnKey_.companyPhoneNo_ );
				
				companyName = regnData.companyName_;
				
				
				emailActivationLink( mapper.uuid_, mapper.userRelKey_,profileData.firstName_,companyName );

				actTbl = null;
				userKey = null;
				mapper = null;
				mapperTable = null;

				return 0; // Success
			}
			else
			{
				actTbl = null;
				userKey = null;
				mapper = null;
				mapperTable = null;

				return -1; // password reset error
			}

		}
		else
		{
			actTbl = null;
			userKey = null;

			return -2; // invalid email
		}
	}

	/*
	 * Method : emailActivationLink( ) : private
	 * 
	 * Input : String uuid, String emailId
	 * 
	 * Return : void
	 * 
	 * Purpose: It is used to send activation link 
	 * to the user who requests new password.
	 */

	private void emailActivationLink( String uuid, String emailId, String firstName, String companyName )
	{

		Mailer mailerObj = new Mailer( );

		String [ ] to = { emailId };

		String sub = "SupplyMedium Account Activation Link";

		//String path = AppConfigReader.instance( ).get( "SERVLET_PATH" );

		//String message = path + "/Views/Registration/jsp/useractivate.jsp?key=" + uuid;
		
		HTMLMailComposer composer = new HTMLMailComposer( );
		
		String message = composer.composeUserActivationLinkMail( uuid, emailId, firstName,companyName );
		
		composer = null;

		mailerObj.composeAndSendMail( to, sub, message );

		mailerObj = null;

		System.out.println( "Mail send successfully" );
	}


	/*
	 * Method : setAccountDefaults( )
	 * 
	 * Input : userProfileDataq object
	 * 
	 * Return : none
	 * 
	 * Purpose: Set default account policies for new user
	 */

	private int setAccountDefaults( UserProfileData profileData )
	{
//		ErrorLogger errLogger = ErrorLogger.instance( );

		UserAccountDefaultsTable defaults = new UserAccountDefaultsTable( );

		int defaultResult = defaults.getDefaults( profileData );

		if( defaultResult == 0 )
		{
//			errLogger.logMsg( "info::UserProfileInsert:UserAccountDefaultsTable.getDefaults:Success" );
		}

		defaults = null;

		UserAccountPoliciesTable policiesDefaults = new UserAccountPoliciesTable( );

		int policiesResult = policiesDefaults.insertPolicies( profileData );

		if( policiesResult == 0 )
		{
//			errLogger.logMsg( "info::UserProfileInsert:UserAccountPoliciesTable.insertPolicies:Success" );
		}

		policiesDefaults = null;
		return policiesResult;
	}

	/*
	 * Method : setDefaultPrivileges( )
	 * 
	 * Input : userProfileDataq object
	 * 
	 * Return : none
	 * 
	 * Purpose: Set default user privileges for new user
	 */

	private int setDefaultPrivileges( UserProfileData profileData )
	{
//		ErrorLogger errLogger = ErrorLogger.instance( );

		PrivilegesDefaultsTable defaults = new PrivilegesDefaultsTable( );

		LongHolder privilegesValue = new LongHolder( );

		int defaultResult = defaults.getUserDefaults( privilegesValue );

		if( defaultResult == 0 )
		{
//			errLogger.logMsg( "info::UserProfileInsert:setDefaultPrivileges:PrivilegesDefaultsTable.getUserDefaults:Success" );
		}

		defaults = null;

		UserPrivilegesTable privilegesTable = new UserPrivilegesTable( );

		UserPrivilegesData privilegesData = new UserPrivilegesData( );

		UserProfileKey key = new UserProfileKey( );

		key.email_ = profileData.emailId_;

		privilegesData.userKey_ = key;

		privilegesData.privilegesValue_ = privilegesValue.value;

		int privVal = privilegesTable.insert( privilegesData );

		if( privVal != 0 )
		{
//			errLogger.logMsg( "info::UserProfileInsert:setDefaultPrivileges:UserPrivilegesTable.setDefaultPrivileges:Failed" );
		}

		key = null;
		privilegesTable = null;
		return privVal;
	}

	/*
	 * Method : setNotifyDefaults( ) 
	 * 
	 * Input : userProfileData object 
	 * 
	 * Return : none
	 * 
	 * Purpose: Set user notification defaults for new user
	 */
	
	private void setNotifyDefaults( UserProfileData userProfileData )
	{	
		UserNotificationSettings notifySettings = new UserNotificationSettings( );
		
		NotificationSettingsData notifySettingsData = new NotificationSettingsData( );
		
		notifySettingsData.userProfileKey_.email_ = userProfileData.emailId_;
		
		notifySettings.create( notifySettingsData );
		
		notifySettings = null;
		
		notifySettingsData = null;
	}
	
	/*
	 * Method : setWhDefaults( ) 
	 * 
	 * Input : userProfileData object 
	 * 
	 * Return : none
	 * 
	 * Purpose: Set user working hours defaults for new user
	 */
	
	private void setWhDefaults( UserProfileData userProfileData )
	{	
		UserWorkingHoursSettings whSettings = new UserWorkingHoursSettings( );
		
		WorkingHoursData whData = new WorkingHoursData( );
		
		whData.userProfileKey_.email_ = userProfileData.emailId_;
		
		whSettings.create( whData );
		
		whSettings = null;
		
		whData = null;

	}
	
}
package core.regn;

import java.util.List;

import utils.ErrorLogger;

import db.login.LoginStatusTable;
import db.regn.MailingAddressTable;
import db.regn.UserProfileTable;
import db.usermgmt.UserAccountPoliciesTable;
import utils.ErrorMaster;

/*
 *This class is used to get user profile data
 *
 */


public class UserProfileFind
{
    private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : getUsers( )
	 * 
	 * Input  : company regn key
	 * 
	 * Return : List<UserProfileData>
	 * 
	 * Purpose: It is used to get list of user profile data for given company regn key
	 */
	
	public int getUsers( CompanyRegnKey key, List<UserProfileData> userProfileList )
    {
        if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert("core get user start");
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
	    UserProfileTable profileTable = new UserProfileTable( );
	    
	    int profileResult = profileTable.getAllUsers( key, userProfileList );
	    
	    if( profileResult != 0 )
	    {
	    	errorLogger.logMsg( "Error::UserProfileFind.getUsers() - Unable to fetch profile data list" +
								" for company key " +"<" + key.toString( ) + ">");
	    	
	    	return 901;
	    }
	    
	    for( UserProfileData userProfileData : userProfileList )
        {
	        LoginData loginData = new LoginData( );
	        
	        LoginStatusTable statusTable = new LoginStatusTable( );
	        
	        int statusResult = 0;
	        
	        statusResult = statusTable.getStatus( userProfileData.userProfileKey_, loginData ); ///////
	        
	        if( statusResult == 0 )
	        {
	        	userProfileData.loginData_ = loginData;
	        }
	        else
	        {
	        	//errorLogger.logMsg( "Error::UserProfileFind.getUsers() - Unable to fetch login status" );
			}
	        
	        MailingAddressTable mailTbl = new MailingAddressTable( );
	        
	        int addVal = 0;
	        
	        MailingAddressData mailingAddressData = new MailingAddressData( );
	        
	        addVal = mailTbl.getCity( userProfileData.userProfileKey_, mailingAddressData );
	        
	        if( addVal == 0 )
	        {
	        	userProfileData.mailingAddr_ = mailingAddressData;
	        }
	        else
	        {
	        	errorLogger.logMsg( "Error::UserProfileFind.getUsers() - Unable to fetch city " +
									"for user " +"<" + userProfileData.firstName_+ ">, " +
									"<" + userProfileData.userProfileKey_.email_ + ">" );
			}
	        
	        UserAccountPoliciesTable policiesTbl = new UserAccountPoliciesTable( );
	        
	        int policyVal = policiesTbl.getUserPolicies( userProfileData.userProfileKey_, userProfileData );
	        
	        if( policyVal != 0 )
	        {
	        	errorLogger.logMsg( "Error::UserProfileFind.getUsers() - Unable to fetch user account policies" +
									"for user " +"<" + userProfileData.firstName_+ ">, " +
									"<" + userProfileData.userProfileKey_.email_ + ">" );
	        }
	        
	        policiesTbl = null;
	        loginData = null;
	        statusTable = null;
	        mailTbl = null;
	        mailingAddressData = null;
        }
		errorMaster_.insert("core get user End" );
		return 900; //Success
    }
}

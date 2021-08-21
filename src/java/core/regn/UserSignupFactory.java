package core.regn;

import java.util.Map;
import java.util.HashMap;

import core.usermgmt.UserProfileDelete;
import core.usermgmt.UserPoliciesUpdate;
import core.usermgmt.UserStatusUpdate;

/*
 * This factory method create and return the UserSignupProcess object for given request type.
 * It is related to user signup. 
 */

public class UserSignupFactory
{
	// This map contain all the UserSignupProcess implements classes object.

	private Map<String, UserSignupProcess> signupObjHashMap_ = null;

	/*
	 * Method : UserSignupFactory( ) Input : None Return : None
	 * 
	 * Purpose: To initialize the map and set the vales to map. Each key contain
	 * one class object.
	 */

	public UserSignupFactory()
	{
		signupObjHashMap_ = new HashMap<String, UserSignupProcess>( );

		// This is for new user signup
		signupObjHashMap_.put( "NewSignup", new UserProfileInsert( ) );
		signupObjHashMap_.put( "AddUser", new UserProfileAdminInsert( ) );
		signupObjHashMap_.put( "UpdatePolicies", new UserPoliciesUpdate( ) );
		signupObjHashMap_.put( "DeleteUser", new UserProfileDelete( ) );
		signupObjHashMap_.put( "UserActivate", new UserLinkActivate( ) );
		signupObjHashMap_.put( "UserStatusUpdate", new UserStatusUpdate( ) );
	}

	/*
	 * Method : createRegnObj( ) Input : request type Return : UserSignupProcess
	 * object
	 * 
	 * Purpose: To return the UserSignupProcess implementation object based on
	 * requestType parameter. Request types can be
	 */

	public UserSignupProcess createRegnObj( String requestType )
	{
		
		return this.signupObjHashMap_.get( requestType );
	
	}

}

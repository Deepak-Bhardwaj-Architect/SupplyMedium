package core.regn;

/*
 * This is the main class for User signup.
 * Three classes implements this interface. There are UserProfileInsert, UserProfileUpdate,
 * UserProfileFine and UserProfileDelete.
 * UserSignupFactory is the factory class for this Factory design.
 * This factory class only decide which class process method need to be called according
 * to input parameter.
 */

public interface UserSignupProcess
{

	public int process( UserProfileData signupDataObj );
}

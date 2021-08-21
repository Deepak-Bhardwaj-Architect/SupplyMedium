package core.regn;

/*
 * This class contain the user key.
 * Concatenation of the all class variable create the User key.
 * If class contain only one variable, that is the user key.
 */

public class UserProfileKey
{

	public String email_;
	
	 /**
    *
    */

   public UserProfileKey( String phoneIn )
   {      
       this.email_ = phoneIn;
   }
   
   
   
   /**
   * Copy constructor
   */
   public UserProfileKey( UserProfileKey keyIn )
   {      
	   email_ = keyIn.email_;
   }
   
   
    /**
    *
    */
   public UserProfileKey( )
   {      
       this.email_ = null;
   }
   

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void show( )
	{
		System.out.println( "email_	= " + email_ );
	}

	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void clear( )
	{
		email_ = null;
	}
	
	// Override methods - Start
	   
	   // This is needed for this class as it would be used in Generics like HashSet<CompanyRegnKey>
	   
	   // While using in Hash based data structures, the object needs to have hashCode, equals and toString be 
	   // over ridden always.
	   
	    @Override
	    public boolean equals( Object o )
	    {
	        if( !( o instanceof UserProfileKey ) )
	            return false;
	       
	        UserProfileKey keyIn = (UserProfileKey)o;
	       
	        if( keyIn.email_.equalsIgnoreCase( email_ ) )
	        {
	            return true;
	        }
	       
	        return false;
	    }
	    
	    
	    @Override
	    public int hashCode( )
	    {
	        return this.email_.hashCode( );
	    }
	   
	    @Override
	    public String toString( )
	    {
	        return email_;
	    }
	    


}

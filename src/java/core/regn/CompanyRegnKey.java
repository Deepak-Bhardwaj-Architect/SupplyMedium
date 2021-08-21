/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

package core.regn;

import utils.ErrorMaster;

/**
 * File:  CompanyRegnKey.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

/*
 * This class contain the company registration key.
 * Concatenation of the all class variable create the company registration key.
 *  If class contain only one key, that is the company registration key.
 */

public class CompanyRegnKey
{

	public String companyPhoneNo_;
        private static ErrorMaster errorMaster_ = null;




	/**
    *
    */
   public CompanyRegnKey( )
   {      
	   this.companyPhoneNo_ = null;
   }
   
   public CompanyRegnKey( String phoneIn )
   {
	   this.companyPhoneNo_ = phoneIn;
   }
   
   
   
   // Override methods - Start
   
   // This is needed for this class as it would be used in Generics like HashSet<CompanyRegnKey>
   
   // While using in Hash based data structures, the object needs to have hashCode, equals and toString be 
   // over ridden always.
   
	@Override
    public boolean equals( Object o )
    {
        if( !( o instanceof CompanyRegnKey ) )
            return false;
       
        CompanyRegnKey keyIn = (CompanyRegnKey)o;
       
        if( keyIn.companyPhoneNo_.equalsIgnoreCase( companyPhoneNo_ ) )
        {
            return true;
        }
       
        return false;
    }
    
    
    @Override
    public int hashCode( )
    {
        return this.companyPhoneNo_.hashCode( );
    }
   
    @Override
    public String toString( ) // still not final, but will work now.
    {
        return companyPhoneNo_;
    }
    
    
    // Override methods - End
    
	/*
	 * Method 	: clear( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: To reset the class variables.
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "companyPhoneNo_		= " + companyPhoneNo_ );
	}

	/*
	 * Method 	: show( ) 
	 * Input 	: None 
	 * Return 	: None
	 * 
	 * Purpose	: It is used to print the all class variable values in console
	 */

	public void clear( )
	{
		companyPhoneNo_ = null;
	}

}

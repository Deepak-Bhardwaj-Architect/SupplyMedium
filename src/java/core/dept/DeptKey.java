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

package core.dept;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import utils.ErrorMaster;


/**
 * File:  DeptKey.java 
 *
 * Created on Feb 27, 2013 11:27:28 AM
 */

/*
 * This class contain the department key.
 * Concatenation of the all class variable create the dept key.
 */

public class DeptKey
{
	public CompanyRegnKey companyRegnKey_;

	public String         deptName_;
        
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "deptName_		= " + deptName_ );

		errorMaster_.insert( "companyRegnKey_	= " + companyRegnKey_ );
	}

	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	public void clear( )
	{
		deptName_ = null;

		companyRegnKey_ = null;
	}
	
	
	// Override methods - Start
	   
	  
	   
	    @Override
	    public boolean equals( Object o )
	    {
	        if( !( o instanceof DeptKey ) )
	            return false;
	       
	        DeptKey keyIn = (DeptKey)o;
	       
	        if( keyIn.toString( ).equalsIgnoreCase( this.toString( ) ) )
	        {
	            return true;
	        }
	       
	        return false;
	    }
	    
	    
	    @Override
	    public int hashCode( )
	    {
	        return this.toString( ).hashCode( );
	    }
	   
	    
	    
	    
	@Override
    public String toString( ) // still not final, but will work now.
    {
        return companyRegnKey_.toString( )+":"+deptName_;
    }
}

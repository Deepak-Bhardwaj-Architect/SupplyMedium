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

import utils.ErrorMaster;


/**
 * File:  FileData.java 
 *
 * Created on May 8, 2013 2:24:22 PM
 */

/*
 * Class: FileData
 * 
 * Purpose: This domain object wrapps the file and its attributes
 */

public class FileData
{
	public Object file_;
	public FileAttributesData attrData_;
        
        private static ErrorMaster errorMaster_ = null;


	
	/*Constructor*/
	
	public FileData( )
	{
		file_ = new Object( );
		attrData_ = new FileAttributesData( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: show
	 * 
	 * Input: None
	 * 
	 * Return: None
	 * 
	 * Purpose: To display the class variables
	 */

	public void show( )
	{
		errorMaster_.insert( "-----Showing FileData Object-----" );
		errorMaster_.insert( "file_ = " + file_.toString( ) );
		attrData_.show( );
		
		errorMaster_.insert( "---------------------------------" );
	}
	
	/*
	 * Method: clear
	 * 
	 * Input: None
	 * 
	 * Return: None
	 * 
	 * Purpose: To clear the class variables
	 */

	public void clear( )
	{
		file_ = null;
		attrData_ = null;
	}
	
}

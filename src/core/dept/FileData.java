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
	
	/*Constructor*/
	
	public FileData( )
	{
		file_ = new Object( );
		attrData_ = new FileAttributesData( );
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
		System.out.println( "-----Showing FileData Object-----" );
		System.out.println( "file_ = " + file_.toString( ) );
		attrData_.show( );
		
		System.out.println( "---------------------------------" );
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

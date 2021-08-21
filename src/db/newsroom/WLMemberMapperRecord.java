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
package db.newsroom;

import java.sql.Timestamp;


/**
 * File:  WLMemberMapperRecord.java 
 *
 * Created on 30-Aug-2013 4:05:40 PM
 */
public class WLMemberMapperRecord
{
	public long watchListId_;
	
	public String userKey_;
	
	public Timestamp createdTimestamp_;

	/*
	 * Method : WLMemberMapperRecord -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public WLMemberMapperRecord()
	{
		watchListId_ = -1;
	}
	
	/*
	 * Method : show( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		System.out.println( "watchListId 		=" +watchListId_);
		
		System.out.println( "userKey     		=" +userKey_);
			
		System.out.println( "CreatedTimestamp 	=" +createdTimestamp_);
	}
	
	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */
	
	public void clear( )
	{
		watchListId_ = -1;
		
		userKey_ = null;
		
		createdTimestamp_ = null;
	}

}


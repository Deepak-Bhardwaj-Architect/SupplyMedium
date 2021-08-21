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
package db.advertisement;

import java.sql.Timestamp;

/**
 * File:  AdRecord.java 
 *
 * Created on Oct 3, 2013 2:03:34 PM
 */
public class AdRecord
{
	//id of the advertisement.This is auto incremented id
	public long adId_;
	
	//companykey of the advertisement 
	public String regnKey_;
	
	//customerkey of the advertisement
	public String userProfileKey_;
	
	//alternateText of the advertisement
	public String alternateText_;
	
	//link of the advertisement
	public String link_;
	
	//imageDimension of the advertisement
	public String imageDimension_;
	
	//adImagePath of the advertisement
	public String adImagePath_;
	
	//advertisement created time
	public Timestamp createdTimestamp_;
	
	
	/*
	 * Method : AdRecord -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public AdRecord()
	{
		adId_ = -1;
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
		System.out.println( "adId =" + adId_ );
		
		System.out.println( "companyKey  =" + regnKey_ );
		
		System.out.println( "customerKey  =" + userProfileKey_ );
		
		System.out.println( "alternateText  =" + alternateText_ );
		
		System.out.println( "link	=" + link_ );
		
		System.out.println( "imageDimension	=" + imageDimension_ );
		
		System.out.println( "adImagePath  =" + adImagePath_ );
	
		System.out.println( "createdTimestamp	=" + createdTimestamp_ );
		
		
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
		adId_  = -1;
		
		regnKey_ = null;
		
		userProfileKey_ 	= null;
		
		alternateText_ 	= null;
		
		link_	 = null;
		
		imageDimension_ = null;
		
		adImagePath_ = null;
	
		createdTimestamp_ = null;
	}

}

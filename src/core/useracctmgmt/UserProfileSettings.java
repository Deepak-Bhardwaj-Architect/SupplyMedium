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
package core.useracctmgmt;

import utils.FileStore;
import utils.PathBuilder;
import utils.StringHolder;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileData;
import db.regn.CompanyRegnTable;
import db.regn.UserProfileTable;

/**
 * File:  UserProfileUpdate.java 
 *
 * Created on May 1, 2013 2:34:56 PM
 */

/*
 * Class: UserProfileUpdate
 * 
 * Purpose: This class is used to udpate the user profile details
 */

public class UserProfileSettings
{
	/*Constructor*/
	
	public UserProfileSettings( )
	{
		
	}
	
	/*
	 * Method: update
	 * 
	 * Input: UserProfileData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to update the user profile
	 * details 
	 */
	
	public int update( UserProfileData profileData )
	{		
		Object fileObject = profileData.profileImage_;
		
		if( fileObject == null )
		{
			return -1;
		}

		StringHolder dirPath = new StringHolder( );
		
		PathBuilder pathBuilder = new PathBuilder( );
		
		CompanyRegnTable tbl = new CompanyRegnTable( );
		
		String companyName = tbl.find( profileData.companyRegnKey_ );
		
		int result = pathBuilder.getUserLogoDirPath( companyName, profileData.companyRegnKey_.companyPhoneNo_, dirPath );
		
		System.out.println( "PathBuilder path - "+dirPath.value );
		
		FileStore fileStore = new FileStore( );
		
		StringHolder storedPath = new StringHolder( );
		
		result = fileStore.storeFile( fileObject, dirPath.value, profileData.userProfileKey_.email_, storedPath );
		
		System.out.println( "Filestore path - "+storedPath.value );
		
		if( result != 0 )
		{
			fileStore = null;
			
			storedPath = null;
			
			return 2552; //Failed to add file into the path
		}
		

		//storedPath.value = storedPath.value.replace( "\\", "\\\\" );
		
		StringHolder relativePath = new StringHolder( );
		
		pathBuilder.getRelativePath( storedPath.value, relativePath );
		
		profileData.profilePicture_ = relativePath.value;
		
		UserProfileTable profileTable = new UserProfileTable( );
		
		result = profileTable.update( profileData );
		
		if( result != 0 )
		{
			profileTable = null;
			
			return 2551;  //Error occurred while updating the user profile pic
		}
		
		profileTable = null;
		
		return 2550; // Update user profile pic - success
	}
	
	/*
	 * Method: get
	 * 
	 * Input: UserProfileData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method is used to get the user profile picture
	 */
	
	public int get( UserProfileData profileData )
	{
		UserProfileTable profileTable = new UserProfileTable( );
		
		int result = profileTable.getUserProfile( profileData.userProfileKey_, profileData );
		
		
		
		profileTable = null;
		
		if( result != 0)
		{
			return 2561; //Error occurred while fetching user profile pic
		}
		
		PathBuilder pathBuilder = new PathBuilder( );
		
		if( profileData.profilePicture_ != null && !profileData.profilePicture_.equals( "" ) )
		{
			StringHolder userImageURL = new StringHolder( );
			
			pathBuilder.getWebURLFromRelativepath( profileData.profilePicture_, userImageURL );
			
			profileData.profilePicture_ = userImageURL.value;
		}
		
		return 2560; //Fetch user profile pic - Success
	}
}

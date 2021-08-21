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


package core.feed;

import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import core.dept.DeptKey;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.feed.CompanyFeedTable;
import db.regn.UserProfileTable;
import utils.ErrorMaster;


/**
 * File: CompanyFeedManager.java
 *
 * Created on May 9, 2013
 */

public class CompanyFeedManager
{

    private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : CompanyFeedManager -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public CompanyFeedManager()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	

	/*
	 * Method : add()
	 * 
	 * Input  : CompanyFeedData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add the new company feed to supply medium for
	 * Particular company.
	 */
	public int add( CompanyFeedData companyFeedData )
	{
		int responseCode = 0;
		
		CompanyFeedTable companyFeedTbl = new CompanyFeedTable( );
		
		responseCode = companyFeedTbl.insert( companyFeedData );
		
		companyFeedTbl = null;
		
		if( responseCode == 0 ) // COmpany feed added successfully in SupplyMedium
		{
			UserProfileTable userProfileTable = new UserProfileTable( );
			
			UserProfileData userProfileData = new UserProfileData( );
			
			userProfileTable.getUserProfile( companyFeedData.userKey_, userProfileData );
            
            companyFeedData.userName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
            
            companyFeedData.userPictureUrl_ = userProfileData.profilePicture_;
            
            
			CompanyFeedCache companyFeedCache = CompanyFeedCache.instance( );
			
			// Adding company feed in company feed cache
			companyFeedCache.put( companyFeedData.regnKey_, companyFeedData );
			
			companyFeedCache = null;
			
			return 1600;
			
		}
		else // failed
		{
			return 1602;
		}
		
	}
	
	
	/*
	 * Method : remove()
	 * 
	 * Input  : company feed id and company key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to remove the company feed from supply medium.
	 */
	public int remove( long companyFeedId, CompanyRegnKey regnKey )
	{
		int responseCode = 0;
		
		CompanyFeedTable companyFeedTbl = new CompanyFeedTable( );
		
		responseCode = companyFeedTbl.delete( companyFeedId );
		
		companyFeedTbl = null;
		
		if( responseCode == 0 )  // Company Feed successfully removed from SupplyMedium
		{
			CompanyFeedCache companyFeedCache = CompanyFeedCache.instance( );
			
			companyFeedCache.remove( regnKey, companyFeedId );
			
			companyFeedCache = null;
			
			return 1610;
		}
		else // failed
		{
			return 1611;
		}
		
	}
	
	
	/*
	 * Method : find()
	 * 
	 * Input  : company key, list of company feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the company feeds. Using from and count parameters we filter the 
	 * feeds. Feed list add to companyFeedList so it is copied to caller classes.
	 */
	public int find( CompanyRegnKey regnKey, List<CompanyFeedData> companyFeedList,int from,int count,int lastFeedId )
	{
		int responseCode = 0;
		
		CompanyFeedCache companyFeedCache = CompanyFeedCache.instance( );
		
		responseCode = companyFeedCache.find( regnKey, companyFeedList, lastFeedId, count );
		
		if( responseCode == 0 ) // Needed company feeds available in cache
		{
			return 1620;
		}
		else // Needed company feeds not available in cache.
		{
			CompanyFeedTable companyFeedTbl = new CompanyFeedTable( );
			
			// So fetch the 100 company feeds from database table
			responseCode = companyFeedTbl.find( regnKey, companyFeedList, from, 100 );
			
			companyFeedTbl = null;

			UserProfileTable profileTable = new UserProfileTable( );
			
			for( CompanyFeedData companyFeedData : companyFeedList )
            {
				UserProfileData userProfileData = new UserProfileData( );
				
	            profileTable.getUserProfile( companyFeedData.userKey_, userProfileData );
	            
	            companyFeedData.userName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
	            
	            companyFeedData.userPictureUrl_ = userProfileData.profilePicture_;
            }
			
			if( responseCode == 0 )  // Feeds fetched successfully from DB
			{
				errorMaster_.insert( "fetched feeds count from database ="+companyFeedList.size( ) );
				
				companyFeedCache.remove( regnKey );
				
				// Create the sorted map for storing the company feed values
				SortedMap<Long, CompanyFeedData> feedMap = new TreeMap<Long, CompanyFeedData>(Collections.reverseOrder());
				
				// Add the values to sorted map
				for( CompanyFeedData companyFeedData : companyFeedList )
                {
					errorMaster_.insert( "Fetched feed id = "+companyFeedData.companyFeedId_ );
					
	                feedMap.put( new Long( companyFeedData.companyFeedId_ )  , companyFeedData );
                }
				
				// Add the sorted map to cache
				companyFeedCache.put( regnKey, feedMap );
				
				return 1620;
			} 
			else  // Failed
			{
				return 1621;
			}
		}
		
	}
	
	/*
	 * Method : find()
	 * 
	 * Input  : company key, list of company feeds,latest feed id
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to latest the company feeds. Feed list add to 
	 * compFeedList so it is copied to caller classes.
	 */
	public int find( CompanyRegnKey regnKey, List<CompanyFeedData> compFeedList,int latestFeedId )
	{
		int responseCode = 0;
		
		CompanyFeedCache compFeedCache = CompanyFeedCache.instance( );
		
		responseCode = compFeedCache.find( regnKey, compFeedList, latestFeedId );
		
		if( responseCode == 0 ) // Needed company feeds available in cache
		{
			UserProfileTable profileTable = new UserProfileTable( );
			
			for( CompanyFeedData compFeedData : compFeedList )
            {
				UserProfileData userProfileData = new UserProfileData( );
				
	            profileTable.getUserProfile( compFeedData.userKey_, userProfileData );
	            
	            compFeedData.userName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
	            
	            compFeedData.userPictureUrl_ = userProfileData.profilePicture_;
            }
			
			return 1625;
		}
		else 
		{
			return 1626;
		}
		
	}
	
	
	
	
	/*
	 * Method : update()
	 * 
	 * Input  : CompanyFeedData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update company feed to supply medium for
	 * Particular feed id.
	 */
	
	public int update( CompanyFeedData companyFeedData )
	{
		int responseCode = 0;
		
		CompanyFeedTable companyFeedTbl = new CompanyFeedTable( );
		
		responseCode = companyFeedTbl.update( companyFeedData );
		
		companyFeedTbl = null;
		
		if( responseCode == 0 ) // company feed updated successfully in SupplyMedium
		{
			CompanyFeedCache companyFeedCache = CompanyFeedCache.instance( );
			
			// Adding company feed in company feed cache
			companyFeedCache.put( companyFeedData.regnKey_, companyFeedData );
			
			return 1630;
			
		}
		else // failed
		{
			return 1631;
		}
		
	}
}
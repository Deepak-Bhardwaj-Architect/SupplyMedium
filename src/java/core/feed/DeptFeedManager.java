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
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.feed.CompanyFeedTable;
import db.feed.DeptFeedTable;
import db.feed.UserFeedTable;
import db.regn.UserProfileTable;
import utils.ErrorMaster;

/**
 * File:  DeptFeedManager.java 
 *
 * Created on 22-Apr-2013 11:30:47 AM
 */
public class DeptFeedManager
{

    private static ErrorMaster errorMaster_ = null;
    
	/*
	 * Method : DeptFeedManager -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public DeptFeedManager()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	

	/*
	 * Method : add()
	 * 
	 * Input  : DeptFeedData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add the new departments feed to supply medium for
	 * Particular user.
	 */
	public int add( DeptFeedData deptFeedData )
	{
		int responseCode = 0;
		
		DeptFeedTable deptFeedTbl = new DeptFeedTable( );
                
		
		responseCode = deptFeedTbl.insert( deptFeedData );
		
		deptFeedTbl = null;
		
		if( responseCode == 0 ) // dept feed added successfully in SupplyMedium
		{
			
			UserProfileTable userProfileTable = new UserProfileTable( );
			
			UserProfileData userProfileData = new UserProfileData( );
			
			userProfileTable.getUserProfile( deptFeedData.userKey_, userProfileData );
            
			deptFeedData.userName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
            
			deptFeedData.userPictureUrl_ = userProfileData.profilePicture_;
            
            
			DeptFeedCache deptFeedCache = DeptFeedCache.instance( );
			
			// Adding dept feed in dept feed cache
			deptFeedCache.put( deptFeedData.deptKey_, deptFeedData );
			
			// Add the company internal page 
			
			if( deptFeedData.companyFeedFlag_ == 1 )
			{
				addCompanyFeed( deptFeedData );
			}
			
			
			return 1730;
			
		}
		else // failed
		{
			return 1731;
		}
		
	}
	
	
	/*
	 * Method : addCompanyFeed()
	 * 
	 * Input  : DeptFeedData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to add the new internal feed to supply medium for
	 * Particular user.
	 */
	public int addCompanyFeed( DeptFeedData deptFeedData )
	{
		CompanyFeedData companyFeedData = new CompanyFeedData( );
		
		companyFeedData.feed_ = deptFeedData.deptFeed_;
		
		companyFeedData.feedTitle_ = deptFeedData.feedTitle_;
		
		companyFeedData.regnKey_ = deptFeedData.regnKey_;
		
		companyFeedData.userKey_ = deptFeedData.userKey_;
		
		CompanyFeedManager companyFeedManager = new CompanyFeedManager( );
		
		companyFeedManager.add( companyFeedData );
		
		companyFeedManager = null;
		
		return 0;
		
	}
	
	
	/*
	 * Method : remove()
	 * 
	 * Input  : dept feed id and dept key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to remove the department feed from supply medium.
	 */
	public int remove( long deptFeedId, DeptKey deptKey )
	{
		int responseCode = 0;
		
		DeptFeedTable deptFeedTbl = new DeptFeedTable( );
		
		responseCode = deptFeedTbl.delete( deptFeedId );
		
		deptFeedTbl = null;
		
		if( responseCode == 0 )  // department Feed successfully removed from SupplyMedium
		{
			DeptFeedCache deptFeedCache = DeptFeedCache.instance( );
			
			deptFeedCache.remove( deptKey, deptFeedId );
			
			deptFeedCache = null;
			
			return 1735;
		}
		else // failed
		{
			return 1736;
		}
		
	}
	
	
	/*
	 * Method : find()
	 * 
	 * Input  : dept key, list of dept feeds, feed starts from and how many feeds
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the department feeds. Using from and count parameters we filter the 
	 * feeds. Feed list add to deptFeedList so it is copied to caller classes.
	 */
	
	public int find( DeptKey deptKey, List<DeptFeedData> deptFeedList,int from,int count,int lastFeedId )
	{
		int responseCode = 0;
		
		DeptFeedCache deptFeedCache = DeptFeedCache.instance( );
		
		responseCode = deptFeedCache.find( deptKey, deptFeedList, lastFeedId, count );
		
		
		errorMaster_.insert( "******************************************************************"+responseCode );
		
		if( responseCode == 0 ) // Needed dept feeds available in cache
		{
			return 1740;
		}
		else if( responseCode == 1 || responseCode == 2 )  /* Cache feeds not in given range then get the 10 feeds from 
		db and send this view.*/
			
		{
			DeptFeedTable deptFeedTbl = new DeptFeedTable( );
			
			responseCode = deptFeedTbl.find( deptKey, deptFeedList, from, 10 );
			
			deptFeedTbl = null;
			
			if( responseCode == 0 )  // Feeds fetched successfully from DB
			{
				
				UserProfileTable profileTable = new UserProfileTable( );
				
				for( DeptFeedData deptFeedData : deptFeedList )
	            {
					UserProfileData userProfileData = new UserProfileData( );
					
		            profileTable.getUserProfile( deptFeedData.userKey_, userProfileData );
		            
		            deptFeedData.userName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
		            
		            deptFeedData.userPictureUrl_ = userProfileData.profilePicture_;
	            }
				
				
				return 1740;
			}
			else
			{
				return 1741;
			}
		}
		else // Needed department feeds not available in cache.
		{
			DeptFeedTable deptFeedTbl = new DeptFeedTable( );
			
			
			// So fetch the <count> department feeds from database table
			responseCode = deptFeedTbl.find( deptKey, deptFeedList, from, count );
			
			deptFeedTbl = null;
			
			if( responseCode == 0 )  // Feeds fetched successfully from DB
			{
				UserProfileTable profileTable = new UserProfileTable( );
				
				for( DeptFeedData deptFeedData : deptFeedList )
	            {
					UserProfileData userProfileData = new UserProfileData( );
					
		            profileTable.getUserProfile( deptFeedData.userKey_, userProfileData );
		            
		            deptFeedData.userName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
		            
		            deptFeedData.userPictureUrl_ = userProfileData.profilePicture_;
	            }
				
				errorMaster_.insert( "fetched feeds count from database ="+deptFeedList.size( ) );
				
				deptFeedCache.remove( deptKey );
				
				// Create the sorted map for storing the dept feed values
				SortedMap<Long, DeptFeedData> feedMap = new TreeMap<Long, DeptFeedData>(Collections.reverseOrder());
				
				// Add the values to sorted map
				for( DeptFeedData deptFeedData : deptFeedList )
                {
					errorMaster_.insert( "Fetched feed id = "+deptFeedData.deptFeedId_ );
					
	                feedMap.put( new Long( deptFeedData.deptFeedId_ )  , deptFeedData );
                }
				
				// Add the sorted map to cache
				
				deptFeedCache.put( deptKey, feedMap );
				
				return 1740;
			} 
			else  // Failed
			{
				return 1741;
			}
		}
		
		
	}
	
	/*
	 * Method : find()
	 * 
	 * Input  : dept key, list of dept feeds,latest feed id
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to latest the department feeds. Feed list add to 
	 * deptFeedList so it is copied to caller classes.
	 */
	public int find( DeptKey deptKey, List<DeptFeedData> deptFeedList,int latestFeedId )
	{
		int responseCode = 0;
		
		DeptFeedCache deptFeedCache = DeptFeedCache.instance( );
		
		responseCode = deptFeedCache.find( deptKey, deptFeedList, latestFeedId );
		
		if( responseCode == 0 ) // Needed dept feeds available in cache
		{
			UserProfileTable profileTable = new UserProfileTable( );
			
			for( DeptFeedData deptFeedData : deptFeedList )
            {
				UserProfileData userProfileData = new UserProfileData( );
				
	            profileTable.getUserProfile( deptFeedData.userKey_, userProfileData );
	            
	            deptFeedData.userName_ = userProfileData.firstName_+" "+userProfileData.lastName_;
	            
	            deptFeedData.userPictureUrl_ = userProfileData.profilePicture_;
            }
			
			return 1745;
		}
		else 
		{
			return 1746;
		}
		
	}
	
	

}


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
package core.history;

import core.feed.UserFeedData;
import core.newsroom.WLMemberData;
import core.newsroom.WatchListData;
import core.notification.NotificationCenterData;
import core.regn.UserProfileData;
import core.userratings.RatingSummaryData;
import db.history.TransactionRatingTable;
import db.newsroom.WLMemberMapperTable;
import db.newsroom.WatchListTable;
import db.notification.NotificationTable;
import db.regn.UserProfileTable;
import java.util.ArrayList;
import java.util.List;
import utils.NotificationType;


/**
 * File:  TransactionRating.java 
 *
 * Created on Sep 26, 2013 3:20:25 PM
 */
public class TransactionRating
{
	
	/*
	 * Method : TransactionRating -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransactionRating( )
	{
		
	}
	
	/*
	 * Method : add
	 * 
	 * Input  : TransRatingsData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to add the new Rating 
	 */
	
	public int add(TransRatingsData ratingsData)
	{
		
		TransactionRatingTable transactionRatingTable=new TransactionRatingTable( );
		
		
		int result=transactionRatingTable.isRatingExist(ratingsData);
		if(result==1)//already Exist
		{
			result=transactionRatingTable.update(ratingsData);
                        
		}
		else
		{
			
			result = transactionRatingTable.insert( ratingsData );
			
		}
		transactionRatingTable = null;
		
		if( result == 0 )
			return 16020;  // Rating successfully created
		else
			return 16022;  // Rating creation failed 
               
		
    }


}

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
package core.userratings;

import core.history.TransRatingsData;
import core.notification.NotificationCenterData;
import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import db.notification.NotificationRecord;
import db.notification.NotificationTable;
import db.notification.NotificationTableUtils;
import db.regn.CompanyRegnTable;
import db.userratings.RatingsSummaryTable;
import db.userratings.UserRatingsTable;
import db.utils.DBConnect;
import db.vendorregn.RegnVendorMapTable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import utils.IntHolder;
import utils.NotificationType;
import utils.PathBuilder;
import utils.StringHolder;

/**
 * @FileName : UserRattingsManager.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 28, 2013
 * 
 * @Purpose : It will handle all the Work related to the Rating Module
 *          functionality.
 * 
 */
public class UserRatingsManager
{
	private UserRatingsTable	_ratingsTable;
	private RatingsSummaryTable	_ratingsSummaryTable;
	private int	                _result;

	/**
	 * @Date : Aug 28, 2013 6:34:03 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Add or Update the Ratings Based on the Company RegnKey
	 * 
	 * @param data
	 * @return
	 * 
	 */

	public int save( UserRatingsData data, RatingSummaryData ratingSummaryData )
	{
		//IntHolder ratingsKey = new IntHolder( );
		
		_ratingsTable = new UserRatingsTable( );
		
		
		
		/*if( _ratingsTable.isRattingsExists( data, ratingsKey ) )
		{
			if( ratingsKey.value > 0 )
			{
				data._rattings_key = ratingsKey.value;
				_result = _ratingsTable.update( data );
			}
			else
				_result = -2;
		}
		else
		{
			_result = _ratingsTable.add( data );
		}*/
		
		
		_result = _ratingsTable.add( data );
                addNotification(data);

		_ratingsTable = null;

		_result = addUpdateRatingsSummary( data );

		this._ratingsSummaryTable = new RatingsSummaryTable( );

		Map<CompanyRegnKey, RatingSummaryData> ratingTotaldata = new HashMap<CompanyRegnKey, RatingSummaryData>( );
		this._ratingsSummaryTable.find( data._regn_rel_key, ratingTotaldata );

		if( ratingTotaldata.containsKey( data._regn_rel_key ) )
		{
			RatingSummaryData datasumary = ratingTotaldata.get( data._regn_rel_key );
			ratingSummaryData._avg_ratings = datasumary._avg_ratings;
			ratingSummaryData._no_of_ratings = datasumary._no_of_ratings;
		}

		if( _result == 0 )
			_result = 10505;
		else
			_result = 10506;

		return _result;

	}
        
         void addNotification( UserRatingsData userFeedData )
	{
            
                String query = "INSERT INTO notification(";
		query		 = query + "receiver_key,receiver_regn_key,sender_key,sender_regn_key,notification_type,notification_type_id,notification_message) ";
		query		 = query + " VALUES";
		query		 = query + "(";
		
		
		query		 = query + "'" + userFeedData._reviewer_id+ "', ";
		query		 = query + "'" + userFeedData._regn_rel_key +"',";
		query		 = query + "'" + userFeedData._user_profile_key + "', ";
		query		 = query + "'" + userFeedData._company_regn_key +"',";
		query		 = query + "'UserRating',";
		query		 = query + "'0',";
		query		 = query + "'" + userFeedData._reviewer_name+" has rated you "+userFeedData._ratings+" starts.'";
		query 	  	 = query + ")";
               Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        stmt.executeUpdate( query );
	        
	       // return 0;
        }
		catch( SQLException e )
        {
        	//errLogger_.logMsg( "Exception::NotificationTable.insert( NotificationCenterData ) - " + e );
			
			//return -2;
        }
        catch( Exception e )
        {
        	//errLogger_.logMsg( "Exception::NotificationTable.insert( NotificationCenterData ) - " + e );
			
			//return -3;
        }
		finally
		{
			try
            {
		
				
	            if( con != null )
	            {
	            	con.close();
	            }
	            if( stmt != null )
	            {
	            	stmt.close( );
	            }
            }
            catch( Exception e2 )
            {
	            
            }
		}
                
               // return response;
                
        }

	/**
	 * @Date : Aug 28, 2013 6:23:17 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Getting the List of Ratings for the Particular company
	 * 
	 * @param key
	 * @param dataList
	 * @return
	 * 
	 */
	public int getRatingsList( CompanyRegnKey key, List<UserRatingsData> dataList )
	{
		_ratingsTable = new UserRatingsTable( );
		_result = _ratingsTable.getRattingsList( key, dataList );
		_ratingsTable = null;
		_result = _result == 0 ? 10500 : 10501;
		return _result;
	}

	/**
	 * @Date : Aug 28, 2013 7:04:55 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the CompanyRatingList
	 * 
	 * @param key
	 * @param totalratingsData
	 * @return
	 * 
	 */
	public int getCompanyRatingList( CompanyRegnKey key, List<CompanyTotalRatingsData> CompanyRatingsList )
	{
		// 1. Geting the List of Vendor

		Set<CompanyRegnKey> vendorkeys = new HashSet<CompanyRegnKey>( );
		RegnVendorMapTable vendorMap = new RegnVendorMapTable( );
		_result = vendorMap.find( key, vendorkeys );
		if( _result != 0 ) return 10521;
		

		// 2. Getting the Ratings Summary Data

		Map<CompanyRegnKey, RatingSummaryData> ratingSummaryMap = new HashMap<CompanyRegnKey, RatingSummaryData>( );
		_ratingsSummaryTable = new RatingsSummaryTable( );
		_result = _ratingsSummaryTable.getAllRatingsSummaryList( ratingSummaryMap );
		if( _result != 0 ) return 10521;

		// 3. Getting All the Company Information

		Map<CompanyRegnKey, RegnData> companyDetailsMap = new HashMap<CompanyRegnKey, RegnData>( );
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		_result = regnTable.getAllCompanyData( companyDetailsMap );
		if( _result != 0 ) return 10521;

		// 4. Framing the CompanyTotalRatingsData
		RatingSummaryData ratingdata = null;
		RegnData regndata = null;
		CompanyTotalRatingsData companyRatingData = null;
		for( CompanyRegnKey item : vendorkeys )
		{
			ratingdata = null;
			regndata = null;
			companyRatingData = null;

			if( ratingSummaryMap.containsKey( item ) )
			{
				ratingdata = ratingSummaryMap.get( item );
			}
			else
			{
				ratingdata = new RatingSummaryData( );
				ratingdata._avg_ratings = 0;
				ratingdata._no_of_ratings = 0;
				ratingdata._regn_rel_key = item;
			}

			if( companyDetailsMap.containsKey( item ) )
			{
				regndata = companyDetailsMap.get( item );
			}
			else
				continue;

			companyRatingData = new CompanyTotalRatingsData( );
			companyRatingData._company_name = regndata.companyName_;
			companyRatingData._company_id=regndata.emailId_;
			StringHolder webURL = new StringHolder( );
			PathBuilder pathBuilder = new PathBuilder( );
			pathBuilder.getWebURLFromRelativepath( regndata.logo_, webURL );
			companyRatingData._logoPath = webURL.value;
			pathBuilder = null;
			
			companyRatingData._regn_rel_key = item;
			companyRatingData._avgRating = ratingdata._avg_ratings;
			companyRatingData._rating = ratingdata._no_of_ratings;
			
			
			companyRatingData.Show( );

			CompanyRatingsList.add( companyRatingData );

		}

		if( _result == 0 )
			_result = 10520;
		else
			_result = 10521;

		return _result;
	}

	/**
	 * @Date : Aug 28, 2013 7:01:21 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Add / Update the RatingsSummary Table Based on the
	 *          Availability
	 * 
	 * @param rattingdata
	 * @return
	 * 
	 */
	private int addUpdateRatingsSummary( UserRatingsData rattingdata )
	{
		RatingSummaryData data = new RatingSummaryData( );
		_result = this.convertToRatingsSumary( rattingdata, data );

		if( _result == 0 )
		{
			_ratingsSummaryTable = new RatingsSummaryTable( );

			if( _ratingsSummaryTable.isItemExists( data._regn_rel_key ) )
			{
				_result = _ratingsSummaryTable.update( data );
			}
			else
			{
				_result = _ratingsSummaryTable.add( data );
			}
		}

		return _result;
	}

	/**
	 * @Date : Aug 28, 2013 6:43:53 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Convert the UserRatingsData to Rating SummaryData
	 * 
	 * @param rattingdata
	 * @param data
	 * 
	 */
	private int convertToRatingsSumary( UserRatingsData rattingdata, RatingSummaryData data )
	{
		IntHolder ratingvalue = new IntHolder( );
		Date currentDate = new Date( );
		data._regn_rel_key = rattingdata._regn_rel_key;
		data._first_rating_timestamp = new Timestamp( currentDate.getTime( ) );
		data._last_rating_timestamp = new Timestamp( currentDate.getTime( ) );

		_ratingsTable = new UserRatingsTable( );

		_result = _ratingsTable.getAverageRattings( rattingdata._regn_rel_key, ratingvalue );
		if( _result == 0 )
		{
			data._avg_ratings = ratingvalue.value;
			_result = _ratingsTable.getNoOfRattings( rattingdata._regn_rel_key, ratingvalue );
			data._no_of_ratings = ratingvalue.value;
		}

		_ratingsTable = null;

		return _result;

	}

}

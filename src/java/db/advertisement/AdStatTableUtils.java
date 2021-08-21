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

import utils.ErrorLogger;
import utils.StringHolder;
import core.advertisement.AdStatisticData;
import utils.ErrorMaster;

/**
 * File:  AdStatTableUtils.java 
 *
 * Created on Oct 7, 2013 6:24:29 PM
 */
public class AdStatTableUtils
{
	
		private String tableName_;
		
		private ErrorLogger errLogger_;
		
		/*ad_stat_id
		 * ad_rel_id
		 * date
		 * count 
		 */
		
		
		/*
		 * Method : AdStatTableUtils -- constructor
		 * 
		 * Input  : None
		 * 
		 * Return : None
		 * 
		 * Purpose:
		 */
		public AdStatTableUtils()
		{
			this.tableName_ = "ad_statistic";
			
			errLogger_ = ErrorLogger.instance( );
		}
	
	
		
		/*
		 * Method : dataToRecord( )
		 * 
		 * Input : AdStatisticData object to AdStatRecord object
		 * 
		 * Return : int
		 * 
		 * Purpose: It is used to convert the AdStatisticData object to
		 * AdStatRecord object. And add to adStatRecord parameter so it is copied
		 * to caller method.
		 */
	
	

    public int dataToRecord( AdStatisticData statisticData, AdStatRecord  adStatRecord )
    {
    	try
        {
    		adStatRecord.adId_ 	= statisticData.adId_;
			
    		adStatRecord.adStatId_ 	= statisticData.adStatId_;
			
    		adStatRecord.date_ 		= statisticData.date_;
			
    		adStatRecord.count_	= statisticData.count_;
			
			return 0;
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::AdStatTableUtils.dataToRecord() - " + e );
        	
        	return -1;
        }
	}
	
    
    
    /*
	 * Method : formInsertQuery( )
	 * 
	 * Input :AdStatRecord object
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using AdStatRecord and returns as
	 * string
	 */
    public String formInsertQuery( AdStatRecord adStatRecord )
    {
    	
    	String query = "INSERT INTO " + this.tableName_ + "(";
		query		 = query + "ad_rel_id,date,count) ";
		query		 = query + " VALUES";
		query		 = query + "(";
	
		query		 = query + "'" + adStatRecord.adId_ + "', ";
		query		 = query + "'" + adStatRecord.date_ +"',";
		query 		 = query + "'" + adStatRecord.count_ + "' ";
		
		query 	  	 = query + ")";
 		 ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "Query = " + query );
		
		return query;
    }
	   
    	/*
    	 * Method : getFilterString( )
    	 * 
    	 * Input :	AdStatRecord filter, StringHolder query
    	 * 
    	 * Return : int
    	 * 
    	 * Purpose: Using the AdStatRecord it form the filter query and add to string holder
    	 * parameter so it is copied to caller class.
    	 */
    	
    	public int getFilterString( AdStatRecord filter, StringHolder query )
    	{
    		try
    		{
    			
    			
    			query.value += filter.adId_ != -1 ? 
    							" ad_rel_id= '" + filter.adId_ + "' AND" : "";

    			query.value += filter.adStatId_!= -1? 
    							" ad_stat='" + filter.adStatId_ + "' AND" : "";
    			

    			query.value += filter.date_ != null ? 
    							" date='" + filter.date_ + "' AND" : "";

    			query.value += filter.count_ != 0? 
    							" count='" + filter.count_ + "' AND" : "";

    				if( !query.value.equalsIgnoreCase( "" ) )
    			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );
    			
    		
    			return 0;
    		}
    		catch( Exception ex )
    		{
    			ErrorLogger errLogger_ = ErrorLogger.instance( );
    			errLogger_.logMsg( "Exception::AdStatTableUtils.getFilterString() - " + ex );
    			return -1;
    		}
    	}

    	
    	
    	
    
    }

	

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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utils.ErrorLogger;
import utils.StringHolder;

import core.advertisement.AdData;
import core.feed.UserFeedData;
import core.regn.CompanyRegnKey;
import db.feed.UserFeedRecord;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  AdTable.java 
 *
 * Created on Oct 3, 2013 11:29:52 AM
 */
public class AdTable
{
	
	private String tableName_;
	
	private ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : AdTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public AdTable()
	{
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "advertisement";
			
		errLogger_ = ErrorLogger.instance( );
	}
		
	/*
	 * Method : insert( )
	 * 
	 * Input : AdvertisementData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the new Advertisement to  ad table.
	 */
	
    public int insert( AdData adData )
    {
	    

		AdTableUtils utils = new AdTableUtils( );

		AdRecord record = new AdRecord( );
		
		int result = utils.dataToRecord( adData, record );
		
		if( result != 0 )
		{
			utils = null; 
			
			record = null;
			
			return result;
		}
		
		String insertQuery = utils.formInsertQuery( record );
		
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        result	= stmt.executeUpdate( insertQuery,Statement.RETURN_GENERATED_KEYS );
	        
	        if( result > 0 ) 
			{
				ResultSet keys = stmt.getGeneratedKeys( );

				while( keys.next( ) )
				{
					adData.adId_ = keys.getInt( 1 );
				
					errorMaster_.insert( "" );
				}

				Date date = new Date( );
				
				adData.createdTimestamp_ =new Timestamp(date.getTime());
				
				return 0; // Successfully inserted
			}
	        
	        return -1;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::AdTable.insert( adData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::AdTable.insert( adData ) - " + e );
			
			return -3;
        }
		finally
		{
			try
            {
				utils = null; record = null;
				
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
	}
   
    

	/*
	 * Method : update( )
	 * 
	 * Input : AdData object
	 * 
	 * Return : int - result - if 0 success otherwise failed.
	 * 
	 * Purpose: It is used to update the user feed data values into
	 * user_feed table using user feed id
	 */

	public int update( AdData adData )
	{
		AdTableUtils utils = new AdTableUtils( );

		AdRecord record = new AdRecord( );
		
		int result = utils.dataToRecord( adData, record );
		
		if( result != 0 )
		{
			utils = null; 
			
			record = null;
			
			return result;
		}
		// Form the update query
		String query = "UPDATE " + tableName_;
		query = query + " SET regn_rel_key = '" + record.regnKey_+"',";
		
		query = query + " user_rel_key = '" +  record.userProfileKey_+"',";
		query = query + " alternate_text = '" +  record.alternateText_+"',";
		query = query + " link = '" +  record.link_+"',";
		
		query = query + " ad_image_path = '" + record.adImagePath_+"'";
		query = query + " WHERE ad_id ='" + record.adId_ + "'";
		
		errorMaster_.insert( "Query="+query );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			if( insertResult > 0 ) 
				return 0; // Successfully inserted

			return -1; // Insert failed

		}

		catch( SQLException ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger = ErrorLogger.instance( );
			errLogger.logMsg( "Exception::UserFeedTable.update() - " + ex );

			return -2;
		}
		finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
                if( con != null )
                {
                    con.close();
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}

	
    /*
	 * Method : delete( )
	 * 
	 * Input : adId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the existing Advertisement from Ad table
	 */
    
    
    
    public int delete( long adId )
    {
    	Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE ad_id = '" + adId+"'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return -1;

		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::AdTable.delete( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::AdTable.delete( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
                if( con != null )
                {
                    con.close();
                }
           }
           catch (SQLException sQLException)
           {
           }
        }

	    
    }
    
    
    
    /*
	 * Method : find( )
	 * 
	 * Input : AdvertisementData obj, list of AdvertisementData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the AdvertisementData according to given AdvertisementData object.
	 * After fetch the AdvertisementData it add into adlists list. So it is copied to caller classes.
	 */
	
    public int find( AdData adData, List<AdData> adlists )
    {
	    
    	AdTableUtils utils = new AdTableUtils( );
		
		AdRecord record = new AdRecord( );

		
		//Convert the AdvertisementData to adRecord
		//( using AdTableUtils->dataToRecord method )
		
		int result = utils.dataToRecord( adData, record );
		

		
		StringHolder query=new StringHolder( );
		//selectQuery.value = "SELECT * FROM " + this.tableName_ ;
		
		query.value="";
		
		result=utils.getFilterString( record, query );
		
		String selectQuery = "SELECT * FROM " + this.tableName_ ;
		
		selectQuery=selectQuery+query.value;
	
		
		if( result != 0)
		{
			utils = null; record = null;
			
			return result;
		}
		
		
		if( result != 0 )
		{
			utils = null; record = null;
			
			return result;
		}
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		
		
		errorMaster_.insert( "Query="+selectQuery );
		
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			
			
			//convert result to connections
			result = utils.rsToDataList( rs, adlists );
			
			utils = null; record = null; 
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::AdTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::AdTable.find() - " + e );
			
			return -3;
        }
		finally
        {
            try
            {
            	utils = null; record = null; 
            	
                if (statement != null)
                {
                	statement.close();
                }
                if( con != null )
                {
                    con.close();
                }
                if( rs != null )
                {
                	rs.close( );
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}
    
    /*
	 * Method : getAllAdRegnKeys( )
	 * 
	 * Input : Company registration keys set
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the regn keys from ad table
	 */
	
    public int getAllAdRegnKeys( Set<CompanyRegnKey> regnKeys )
    {
	 
		int result = 0;
	
		String selectQuery = "SELECT DISTINCT regn_rel_key FROM " + this.tableName_ ;
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		errorMaster_.insert( "Query="+selectQuery );
		
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
	
			while( rs.next( ) )
			{
				CompanyRegnKey regnKey = new CompanyRegnKey( );
				
				regnKey.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				
				regnKeys.add( regnKey );
				
				regnKey = null;
			}
			
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::AdTable.getAllAdRegnKeys - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::AdTable.getAllAdRegnKeys - " + e );
			
			return -3;
        }
		finally
        {
            try
            {
            	
                if (statement != null)
                {
                	statement.close();
                }
                if( con != null )
                {
                    con.close();
                }
                if( rs != null )
                {
                	rs.close( );
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}
    
    
    /*
	 * Method : getAds( )
	 * 
	 * Input : Company registration keys set, list of ads
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the ads for given egn keys from ad table
	 */
	
    public int getAds( Set<CompanyRegnKey> regnKeys, List<AdData> adlists ,String key )
    {
	 
		int result = 0;
		
		AdTableUtils utils = new AdTableUtils( );
	
		String selectQuery = "SELECT * FROM " + this.tableName_ +" Where " ;
		
		int i = 0;
		
		for( CompanyRegnKey companyRegnKey : regnKeys )
        {
                if(companyRegnKey.toString( ).equals(key))
                {    System.out.println("regn_rel_key::"+companyRegnKey.toString( ));
	        if( i!= 0)
	        	selectQuery += " OR ";
	        	
	        selectQuery += " regn_rel_key ="+companyRegnKey.toString( );
	        
	        i++;
                }
        }   
                if( i== 0)
                {
                 selectQuery +=" regn_rel_key ='none'";    
                }    
                    
		//System.out.print("selectQuery ====================================>"+selectQuery);
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		errorMaster_.insert( "get all ads Query="+selectQuery );
		
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
	

			//convert result to connections
			result = utils.rsToDataList( rs, adlists );
			
			utils = null; 
			
			return result;
			
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::AdTable.getAds() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::AdTable.getAds() - " + e );
			
			return -3;
        }
		finally
        {
            try
            {
            	
                if (statement != null)
                {
                	statement.close();
                }
                if( con != null )
                {
                    con.close();
                }
                if( rs != null )
                {
                	rs.close( );
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}
}


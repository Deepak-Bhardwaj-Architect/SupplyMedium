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
package db.vendorregn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.notification.NotificationCenterData;
import core.regn.MailingAddressData;
import db.notification.NotificationRecord;
import db.notification.NotificationTableUtils;
import db.regn.MailingAddressRecord;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  VRMailingTable.java 
 *
 * Created on Oct 30, 2013 3:17:53 PM
 * @param <VRMailingRecord>
 */
public class VRMailingTable
{
	
	
	private String tableName_;
	
	private ErrorLogger errLogger_;
	private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : VRMailingTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public VRMailingTable()
    
	{
		
		this.tableName_ = "vr_mailing_address";
			if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
		
	}

	
	
	
	/*
	 * Method : delete( )
	 * 
	 * Input : addrid_
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the Address from VRMailing table
	 */
    public int delete( long addrid_ )
    {
    	
    	Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE addr_id = '" + addrid_+"'";

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
			String errorMessage = "Exception::VRMailingTable.delete( ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::VRMailingTable.delete( ) - "
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
	 * Method : insert( )
	 * 
	 * Input : MailingAddressData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the new Address to  VRMailing table.
	 */
    
    public int insert( MailingAddressData mailingAddressData )
	{

		VRMailingTableUtils utils = new VRMailingTableUtils( );

		VRMailingRecord record = new VRMailingRecord( );
		
		int result = utils.dataToRecord( mailingAddressData, record );
		
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

			int insertResult =stmt.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);

		   
			if( insertResult > 0 ) 
			{
				ResultSet keys = stmt.getGeneratedKeys( );

				while( keys.next( ) )
				{
					mailingAddressData.addrid_ = keys.getInt( 1 );
				
				}
				
				return 0; // Successfully inserted
			}
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::VRMailingTable.insert( mailingAddressData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::VRMailingTable.insert( mailingAddressData ) - " + e );
			
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
	 * Method : find( )
	 * 
	 * Input : MailingAddressData obj, list of MailingAddressData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the MailingAddressData according to given MailingAddressData object.
	 * After fetch the MailingAddressData it add into mailingAddressDataList list. So it is copied to caller classes.
	 */

    public int find( MailingAddressData mailingAddressData,
            List<MailingAddressData> mailingAddressDataList )
    {
	    VRMailingTableUtils utils=new VRMailingTableUtils( );
	    
	    VRMailingRecord record= new VRMailingRecord();
	    
	    int result = utils.dataToRecord( mailingAddressData, record );
		

		
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
			result = utils.rsToDataList( rs, mailingAddressDataList );
			
			utils = null; record = null; 
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::VRMailingTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::VRMailingTable.find() - " + e );
			
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

}

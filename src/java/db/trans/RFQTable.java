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

package db.trans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import utils.ErrorLogger;
import utils.StringHolder;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.trans.RFQData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * File:  RFQTable.java 
 *
 * Created on 21-Jun-2013 9:43:52 AM
 */

public class RFQTable
{
	private String tableName_;
	private ErrorLogger errLogger_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : RFQTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to in initialize the table name
	 */
	
	public RFQTable()
	{
		this.tableName_ = "rfq";
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errLogger_ = ErrorLogger.instance( );
	}
	
	
	/*
	 * Method : insert( )
	 * 
	 * Input : RFQData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the RFQData values into
	 * RFQ table.
	 * 1.Convert the rfqData to rfqRecord ( using REQTableUtils->dataToRecord method )
	 * 2.Form the insert query ( using REQTableUtils->formInsertQuery )
	 * 3.Execute the query
	 */

	public int insert( RFQData rfqData )
	{
		RFQTableUtils utils = new RFQTableUtils( );

		RFQRecord record = new RFQRecord( );
		
		int result = utils.dataToRecord( rfqData, record );
		
		if( result != 0 )
		{
			utils = null; record = null;
			
			return result;
		}
		
		String insertQuery = utils.formInsertQuery( record );
		
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        result	= stmt.executeUpdate( insertQuery, Statement.RETURN_GENERATED_KEYS);
	        
			if( result > 0 ) 
			{
				ResultSet keys = stmt.getGeneratedKeys( );

				if( keys.next( ) )
				{
					rfqData.RFQId_ = keys.getInt( 1 );
				}				
			}
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.insert( RFQItemData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.insert( RFQItemData ) - " + e );
			
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
	 * Input : RFQData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the RFQData values into
	 * RFQ table using RFQId.
	 * 1.Convert the rfqData to rfqRecord ( using REQTableUtils->dataToRecord method )
	 * 2.From the update string ( Using REQTableUtils->getUpdateString )
	 * 3.Form the query and execute
	 */

	public int update( RFQData rfqData )
	{
		RFQTableUtils utils = new RFQTableUtils( );

		RFQRecord record = new RFQRecord( );
		
		int result = utils.dataToRecord( rfqData, record );
		
		if( result != 0 )
		{
			utils = null; record = null;
			
			return result;
		}
		
		StringHolder query = new StringHolder( );
		
		result = utils.getUpdateString( record, query );
		
		if( result != 0 )
		{
			utils = null; record = null; query = null;
			
			return result;
		}
		
		String updateQuery = "UPDATE " + this.tableName_ + " SET " + query.value;
		
		errorMaster_.insert( "update Query="+updateQuery );
		 
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        stmt.executeUpdate( updateQuery );
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.update( RFQData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.update( RFQData ) - " + e );
			
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
	 * Method : updateStatus( )
	 * 
	 * Input : RFQId,toUserKey,status
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the status value into
	 * RFQ table using RFQId.
	 * 1.Form the update string 
	 * 2.Form the query and execute
	 */

	public int updateStatus( long rfgId,UserProfileKey toUserKey, String status )
	{
		
		String updateQuery = "UPDATE " + this.tableName_ + " SET user_to='"+toUserKey.toString( )+"', " +
				"status='"+status+"' WHERE rfq_id='"+rfgId+"'";
		
		errorMaster_.insert( "update Query="+updateQuery );
		 
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        stmt.executeUpdate( updateQuery );
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.updateStatus( long rfgId,UserProfileKey toUserKey, String status ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.updateStatus( long rfgId,UserProfileKey toUserKey, String status ) - " + e );
			
			return -3;
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
            	errLogger_.logMsg( "Exception::RFQTable.updateStatus( long rfgId,UserProfileKey toUserKey, String status ) - " + e2 );
    			
    			return -4;
            }
		}
	}
	
	
	
	
	/*
	 * Method : find( )
	 * 
	 * Input : RFQData obj, list of RFQlist obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the RFQ's and add in RFQList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the rfqData to rfqRecord ( using REQTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using REQTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( RFQData rfqData,List<RFQData> RFQList )
	{
		RFQTableUtils utils = new RFQTableUtils( );
		
		RFQRecord record = new RFQRecord( );

		String selectQuery = "SELECT * FROM " + this.tableName_;
		
		//Convert the RFQData to RFQRecord ( using RFQTableUtils->dataToRecord method )
		int result = utils.dataToRecord( rfqData, record );
		
		if( result != 0)
		{
			utils = null; record = null;
			
			return result;
		}
		
		StringHolder query = new StringHolder( );
		
		//Form Filter string
		result = utils.getFilterString( record, query );
		
		if( result != 0 )
		{
			utils = null; record = null; query = null;
			
			return result;
		}
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		selectQuery = selectQuery + query.value;
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( query.value );
			
			//convert result to RFQItemList
			result = utils.rsToDataList( rs, RFQList );
			
			utils = null; record = null; query = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::RFQTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.find() - " + e );
			
			return -3;
        }
		finally
        {
            try
            {
            	utils = null; record = null; query = null;
            	
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
	 * Method : find( )
	 * 
	 * Input : RFQData obj, list of RFQlist obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the RFQ's and add in RFQList
	 * parameter, so it is copied to caller method.
	 * 1.Convert the rfqData to rfqRecord ( using REQTableUtils->dataToRecord method )
	 * 2.From the filter string ( Using REQTableUtils->getFilterString )
	 * 3.Form the query and execute
	 */

	public int find( CompanyRegnKey regnKey, UserProfileKey userKey, List<RFQData> RFQList )
	{
		RFQTableUtils utils = new RFQTableUtils( );
		
		String selectQuery = "SELECT * FROM " + this.tableName_ + " WHERE ";
	
		selectQuery = selectQuery + "(from_regn_key = '" + regnKey.companyPhoneNo_ + "' AND ";
		selectQuery = selectQuery + "user_from = '" + userKey.email_ + "') OR ";
		selectQuery = selectQuery + "(to_regn_key = '" + regnKey.companyPhoneNo_ + "' AND ";
		selectQuery = selectQuery + "(user_to = '" + userKey.email_ + "' OR user_to='null')) ORDER BY created_timestamp DESC";
		
		errorMaster_.insert( "select query" +selectQuery);
		
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			//convert result to RFQItemList
			int result = utils.rsToDataList( rs, RFQList );
			
			utils = null;
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::RFQTable.find( CompanyRegnKey, UserProfileKey, List<RFQData> ) - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.find( CompanyRegnKey, UserProfileKey, List<RFQData> ) - " + e );
			
			return -3;
        }
		finally
        {
            try
            {
            	utils = null;
            	
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
	 * Method : setQuoteCreatedFlag( )
	 * 
	 * Input : transId
	 * 
	 * 
	 * Return : int
	 * 
	 * Purpose: This is method used to change the quote created flag to 1 when user
	 * create the quote for RFQ
	 */

	public int setQuoteCreatedFlag( long transId )
	{
		
		String updateQuery = "UPDATE " + this.tableName_ + " SET is_quote_created = '1' WHERE trans_id='"+ transId +"'";
		 
		 Statement stmt 	= null;
	     Connection con 	= null;
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        stmt.executeUpdate( updateQuery );
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.setQuoteCreatedFlag( RFQData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::RFQTable.setQuoteCreatedFlag( RFQData ) - " + e );
			
			return -3;
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
	}
	
	/*
	 * Method : delete( )
	 * 
	 * Input : RFQId
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the RFQ from supply Medium
	 */

	public int delete( long RFQId )
	{
		return 0;
	}
	
	//This is for search buyer
	
		/*
		 * 
		 */
		public int getCompanyKeys( List<RFQData> rfqDataList, Set<CompanyRegnKey> companyRegnKeys )
		{
			String query = null;

			Statement 		stmt_ = null;
			Connection 		con_ = null;
			ResultSet 		rs		= null;
			
			try
			{
				con_ = DBConnect.instance( ).getConnection( );
				stmt_ = con_.createStatement( );
				
				//this.formWhereClause( itemData, whereClause );
				
				query =  "SELECT DISTINCT from_regn_key FROM " + this.tableName_ ;
				
				if( rfqDataList.size( ) > 0 )
				{
					query = query + " WHERE rfq_id IN (";
					
	    			int i=0;
	    			
	    			for( RFQData rfqData : rfqDataList )
	                {
	    	            query = query + "'" + rfqData.RFQId_ + "'";
	    	            
	    	            i++;
	    	            
	    	            if( rfqDataList.size( ) > i )
	    	            {
	    	            	query = query + ",";
	    	            }
	                }
	    			query = query + ")";
				}
				
				errorMaster_.insert( "Query: " + query );
				
				rs = stmt_.executeQuery( query );

				while( rs.next( ) )
				{
					CompanyRegnKey regnKey = new CompanyRegnKey( );
					
					regnKey.companyPhoneNo_	= rs.getString( "from_regn_key" );
					
					companyRegnKeys.add( regnKey );
					
					regnKey = null;
				}

				return 0;
				// Success
			}
			catch( SQLException ex )
			{
				errLogger_.logMsg( "Exception::RFQTable.getCompanyKeys( ) - " 
						+ ex );
				return -1;
				// SQLException occurred while filtering a Buyer Company
			}
			catch( Exception ex )
			{
				errLogger_.logMsg( "Exception::RFQTable.getCompanyKeys( ) - " 
						+ ex );
				return -1;
				// Exception occurred while filtering a Buyer Company
			}
			finally
	        {
	            try
	            {
	                if (con_ != null)
	                {
	                	con_.close();
	                	con_ = null;
	                }
	                if( stmt_ != null )
	                {
	                	stmt_.close();
	                	stmt_ = null;
	                }
	                if( rs != null )
	                {
	                	rs.close();
	                	rs = null;
	                }
	           }
	           catch (SQLException sQLException)
	           {
	           }
	        }
		}
		
	
	/************************************* NO MORE METHODS ALLOWED HERE ******************************/

}

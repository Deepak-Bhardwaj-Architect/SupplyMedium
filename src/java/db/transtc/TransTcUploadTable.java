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
package db.transtc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;

import core.transtc.TransTcUploadData;
import db.utils.DBConnect;
import db.utils.DBEncode;
import utils.ErrorMaster;

/**
 * File:  TransTcUploadTable.java 
 *
 * Created on Oct 19, 2013 11:35:01 AM
 */
public class TransTcUploadTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;
	
	private ErrorLogger errLogger_;
	
	/*
	 * Method : TransTcUploadTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransTcUploadTable()
    
	{
		

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "trans_tc";
			
		errLogger_ = ErrorLogger.instance( );
		
	}
	
	
	/*
	 * Method : update( )
	 * 
	 * Input : TransTcUploadData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to update the Transaction term and condition to TransTcUpload table.
	 */
    public int update( TransTcUploadData transTcUploadData )
    {
	    
    	TransTcUploadTableUtils utils = new TransTcUploadTableUtils( );

		TransTcUploadRecord  record = new TransTcUploadRecord( );
		
		int result = utils.dataToRecord( transTcUploadData, record );
		
		if( result != 0 )
		{
			utils = null; 
			
			record = null;
			
			return result;
			
	
		}
		
		DBEncode dbEncode=new DBEncode( );
		
		
		String encodeTC=dbEncode.encode( transTcUploadData.tc_ );
		
		String updateQuery = "UPDATE" +" "+ this.tableName_+" "+"set tc='"+encodeTC+"'"
		
				+" WHERE  trans_type='"+transTcUploadData.transType_+"'"+" AND regn_rel_key='"+ 
				transTcUploadData.regnKey_+"'";
		
		
		errorMaster_.insert( "Query"+updateQuery );
		 Statement stmt 	= null;
	     Connection con 	= null;
	     
	     
		
		try
        { 
	        con 	= DBConnect.instance( ).getConnection( );
	        stmt 	= con.createStatement( );
	        result	= stmt.executeUpdate( updateQuery);
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::TransTcUploadTable.update( TransTcUploadData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTcUploadTable.update( TransTcUploadData ) - " + e );
			
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
	 * Input : TransTcUploadData obj, list of TransTcUploadData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the TransTcUploadData according to given TransTcUploadData object.
	 * After fetch the TransTcUploadData it add into translists list. So it is copied to caller classes.
	 */

    
    public int find( TransTcUploadData transTcUploadData, List<TransTcUploadData> translists )
    {
	    
    	TransTcUploadTableUtils utils = new TransTcUploadTableUtils( );
		
		TransTcUploadRecord record = new TransTcUploadRecord( );

		
		//Convert the transTcUploadData to transactionRecord
		//( using TransTcUploadTableUtils->dataToRecord method )
		
		int result = utils.dataToRecord( transTcUploadData, record );
		

		
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
			result = utils.rsToDataList( rs, translists );
			
			utils = null; record = null; 
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransTcUploadTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTcUploadTable.find() - " + e );
			
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
	 * Method : isRecordExist( )
	 * 
	 * Input : TransTcUploadData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is check the given record is available or not
	 */
    public int isExist( TransTcUploadData transTcUploadData )
    {
	  
    	Statement stmt = null;
		Connection con = null;
		String query = null;

		TransTcUploadTableUtils utils = new TransTcUploadTableUtils( );
		
		TransTcUploadRecord  record = new TransTcUploadRecord( );
		
		//Convert the TransTcUploadData to adRecord
		//( using TransTcUploadTableUtils->dataToRecord method )
		
		int result = utils.dataToRecord( transTcUploadData, record );
		
		if( result != 0)
		{
			utils = null; record = null;
			
			return result;
		}
		

		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key= '" + record.regnKey_+"' AND " +
				"trans_type='"+record.transType_ +"'";
		
		errorMaster_.insert( "query="+query );
	
		
		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				return 1;  // Record already exist
			}

			return 0;  // Record not exist
			
		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );

			String errorMessage = "Exception :: TransTcUploadTable : isExist - " + ex;

			errLogger.logMsg( errorMessage );

			return -1; // SqlException
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
	 * Input : TransTcUploadData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the Transaction term and condition to TranTcUpload table.
	 */
    public int insert( TransTcUploadData transTcUploadData )
    {
    	TransTcUploadTableUtils utils = new TransTcUploadTableUtils( );

		TransTcUploadRecord  record = new TransTcUploadRecord( );
		
		int result = utils.dataToRecord( transTcUploadData, record );
		
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
	        result	= stmt.executeUpdate( insertQuery);
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::TransTcUploadTable.insert( TransTcUploadData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransTcUploadTable.insert( TransTcUploadData ) - " + e );
			
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

}

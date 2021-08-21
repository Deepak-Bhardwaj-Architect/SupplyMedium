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
package db.history;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;

import core.dashboard.CustomerBasedData;
import core.dashboard.TimeBasedData;
import core.history.TransactionHistoryData;
import core.regn.CompanyRegnKey;
import db.utils.DBConnect;

/**
 * File:  TransactionHistoryTable.java 
 *
 * Created on Sep 26, 2013 3:23:36 PM
 */
public class TransactionHistoryTable
{
	private String tableName_;
	
	private ErrorLogger errLogger_;
	

	/*
	 * Method : TransactionHistoryTable -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransactionHistoryTable()
    
	{
		
		this.tableName_ = "transaction_history";
			
		errLogger_ = ErrorLogger.instance( );
		
	}
	
	/*
	 * Method : insert( )
	 * 
	 * Input : TransReminderData obj
	 * 
	 * Return : int
	 * 
	 * Purpose:It is used to insert the new transaction history to  TransactionHistory table.
	 */

    public int insert( TransactionHistoryData transHistoryData )
    {
    	TransactionHistoryTableUtils utils=new TransactionHistoryTableUtils( );
    	
    	TransactionHistoryRecord record=new TransactionHistoryRecord( );
    	
	    int result = utils.dataToRecord( transHistoryData, record );
		
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
	        result	= stmt.executeUpdate( insertQuery );
	        
	        return 0;
        }
		catch( SQLException e )
        {
        	errLogger_.logMsg( "Exception::TransactionHistoryTable.insert( TransactionHistoryData ) - " + e );
			
			return -2;
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransactionHistoryTable.insert( TransactionHistoryData ) - " + e );
			
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
	 * Input : CompanyRegn regnKey,CompanyRegn customerKey, list of TransactionHistoryData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch  all the regnkey,customerKey according to given TranscationHistoryData object.
	 * After fetch the TranscationHistoryData it add into transDataList. So it is copied to caller classes.
	 */

    public int find( CompanyRegnKey regnKey, CompanyRegnKey customerKey,
            List<TransactionHistoryData> transHistoryDataList )
    {
    	
    	TransactionHistoryTableUtils utils = new TransactionHistoryTableUtils( );
		
		int result = 0;
		
		StringHolder query=new StringHolder( );
		
		query.value="";
		
		String selectQuery = "SELECT * FROM " + this.tableName_  +" "+"WHERE  (from_regn_key = '"+regnKey +"'"
				+  "AND to_regn_key='"+customerKey+"') OR"+"(from_regn_key = '"+customerKey +"'"
				
				+  " AND to_regn_key='"+regnKey+"')"; 
		
		
		if( result != 0)
		{
			utils = null; 
			
			return result;
		}
		
		
		if( result != 0 )
		{
			utils = null; 
			
			return result;
		}
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		
		
		System.out.println( "Query="+selectQuery );
		
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			
			
			//convert result to connections
			result = utils.rsToDataList( rs, transHistoryDataList );
			
			utils = null; 
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransactionHistoryTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransactionHistoryTable.find() - " + e );
			
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
	 * Method : find( )
	 * 
	 * Input : TimeBasedData object, list of TimeBasedData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the time based report for given report type.
	 */
    
    public int find( TimeBasedData timeBasedData, List<TimeBasedData> timeBasedList )
    {
    	
        	TransactionHistoryTableUtils utils = new TransactionHistoryTableUtils( );
    		
    		int result = 0;
    		
    		String selectQuery = "";
    		
    		if( timeBasedData.reportType_.equals( "Daily" ))
    		{
    			 selectQuery = "SELECT SUM( amount ) as amount, DATE( created_time_stamp ) as lbl,currency FROM " + this.tableName_  +" "+"WHERE  (from_regn_key = '"
    	    				
    				+timeBasedData.regnKey_.toString( ) +"'"+  " OR to_regn_key='"+timeBasedData.regnKey_.toString( )+"') AND "
    				
    				+" (created_time_stamp >= '"+timeBasedData.fromDate_+"'"
    				
    				+  " AND  created_time_stamp <'"+timeBasedData.toDate_+"'"+" AND status = 'Accepted"+"') GROUP BY DATE( created_time_stamp ) ORDER BY SUM( amount ) DESC"; 
    		}
    		else if( timeBasedData.reportType_.equals( "Monthly" ))
    		{
    			 selectQuery = "SELECT  SUM( amount ) as amount, MONTH( created_time_stamp ) as lbl,currency FROM " + this.tableName_  +" "+"WHERE  (from_regn_key = '"
    	    				
    				+timeBasedData.regnKey_.toString( ) +"'"+  " OR to_regn_key='"+timeBasedData.regnKey_.toString( )+"') AND "
    				
    				+" (created_time_stamp >= '"+timeBasedData.fromDate_+"'"
    				
    				+  " AND  created_time_stamp <'"+timeBasedData.toDate_+"'"+" AND status = 'Accepted"+"') GROUP BY MONTH( created_time_stamp ) ORDER BY SUM( amount ) DESC"; 
    		}
    		else if( timeBasedData.reportType_.equals( "Quarterly" ))
    		{
    			 selectQuery = "SELECT  SUM( amount ) as amount, QUARTER( created_time_stamp ) as lbl,currency FROM " + this.tableName_  +" "+"WHERE  (from_regn_key = '"
    	    				
    				+timeBasedData.regnKey_.toString( ) +"'"+  " OR to_regn_key='"+timeBasedData.regnKey_.toString( )+"') AND "
    				
    				+" (created_time_stamp >= '"+timeBasedData.fromDate_+"'"
    				
    				+  " AND  created_time_stamp <'"+timeBasedData.toDate_+"'"+" AND status = 'Accepted"+"') GROUP BY QUARTER( created_time_stamp ) ORDER BY SUM( amount ) DESC"; 
    		}
    		else if( timeBasedData.reportType_.equals( "Yearly" ))
    		{
    			 selectQuery = "SELECT  SUM( amount ) as amount, YEAR( created_time_stamp ) as lbl,currency FROM " + this.tableName_  +" "+"WHERE  (from_regn_key = '"
    	    				
    				+timeBasedData.regnKey_.toString( ) +"'"+  " OR to_regn_key='"+timeBasedData.regnKey_.toString( )+"') "
    				+" AND status = 'Accepted"+"' GROUP BY YEAR( created_time_stamp ) ORDER BY SUM( amount ) DESC"; 
    			
    		}
    		
    		
    		System.out.println( "Query="+selectQuery );
    	
    		
    		if( result != 0)
    		{
    			utils = null; 
    			
    			return result;
    		}
    		
    		
    		if( result != 0 )
    		{
    			utils = null; 
    			
    			return result;
    		}
    		
    		Statement statement = null;
    		Connection con		= null;
    		ResultSet rs		= null;
    		
    		
    		try
            {
    			con 		= DBConnect.instance( ).getConnection( );
    			statement 	= con.createStatement( );
    			rs			= statement.executeQuery( selectQuery );
    			
    			//convert result to connections
    			result = utils.rsToTimeBasedDataList( rs, timeBasedList );
    			
    			utils = null; 
    			
    			return result;
            }
    		catch( SQLException e )
    		{
    			errLogger_.logMsg( "Exception::TransactionHistoryTable.find() - " + e );
    			
    			return -2;
    		}
            catch( Exception e )
            {
            	errLogger_.logMsg( "Exception::TransactionHistoryTable.find() - " + e );
    			
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
	 * Method : find( )
	 * 
	 * Input : CustomerBasedData object, list of CustomerBasedData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the customer based report for given report type.
	 */
    
    public int find( CustomerBasedData customerBasedData, List<CustomerBasedData> customerBasedList )
    {
    	
        	TransactionHistoryTableUtils utils = new TransactionHistoryTableUtils( );
    		
    		int result = 0;
    		
    		String selectQuery = "";
    		
    		
    		selectQuery = "SELECT SUM( amount ) as amount, from_regn_key, to_regn_key,currency FROM " + this.tableName_  +" "+"WHERE  (from_regn_key = '"
    	    				
    				+customerBasedData.regnKey_.toString( ) +"'"+  " OR to_regn_key='"+customerBasedData.regnKey_.toString( )+"') AND "
    				
    				+" (created_time_stamp >= '"+customerBasedData.fromDate_+"'"
    				
    				+  " AND  created_time_stamp <='"+customerBasedData.toDate_+"'"+" AND status = 'Accepted"+"') GROUP BY from_regn_key, to_regn_key ORDER BY SUM( amount ) DESC"; 
    		
    		
    		System.out.println( "query =" +selectQuery );
    		
    		
    		Statement statement = null;
    		Connection con		= null;
    		ResultSet rs		= null;
    		
    		
    		try
            {
    			con 		= DBConnect.instance( ).getConnection( );
    			statement 	= con.createStatement( );
    			rs			= statement.executeQuery( selectQuery );
    			
    			//convert result to connections
    			result = utils.rsToCustomerBasedDataList( rs, customerBasedList );
    			
    			utils = null; 
    			
    			return result;
            }
    		catch( SQLException e )
    		{
    			errLogger_.logMsg( "Exception::TransactionHistoryTable.find() - " + e );
    			
    			return -2;
    		}
            catch( Exception e )
            {
            	errLogger_.logMsg( "Exception::TransactionHistoryTable.find() - " + e );
    			
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
	 * Method : find( )
	 * 
	 * Input : CustomerBasedData object, list of CustomerBasedData objects
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the customer based report for given report type.
	 */
    
    public double find( CompanyRegnKey regnkey, Date from, Date to  )
    {
		
		String selectQuery = "";
		
		
		selectQuery = "SELECT SUM( amount ) as Amount FROM " + this.tableName_  +" "+"WHERE  (from_regn_key = '"
	    				
				+regnkey.toString( ) +"'"+  " OR to_regn_key='"+regnkey.toString( )+"') AND "
				
				+" (created_time_stamp >= '"+from+"'"
				
				+  " AND  created_time_stamp <='"+to+"'"+" AND status = 'Accepted"+"')"; 
		
		
		System.out.println( "************************ query =" +selectQuery );
		
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
			
			double amount = 0.0;
			
			if(rs.next())
			{
				amount = rs.getDouble( "Amount" );
			}
						
			return amount;
        }
		catch( SQLException e )
		{
			errLogger_.logMsg( "Exception::TransactionHistoryTable.find() - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger_.logMsg( "Exception::TransactionHistoryTable.find() - " + e );
			
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




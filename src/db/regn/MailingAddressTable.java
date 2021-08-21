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

package db.regn;

import core.regn.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;

import db.utils.DBConnect;

import utils.ErrorLogger;
import utils.StringHolder;

/**
 * File: MailingAddressTable.java
 * 
 * Created on Jan 5, 2013 3:29:51 PM
 */

/*
 * Class  : MailingAddressTable
 * 
 * Purpose: It is used to query the mailing_address table.
 * 
 */

public class MailingAddressTable
{
	private String tableName_;

	/*
	 * Method : MailingAddressTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public MailingAddressTable()
	{
		this.tableName_ = "mailing_address";
	}

	/*
	 * Method : insertAddress( )
	 * 
	 * Input : MailingAddressData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the MailingAddressData values into
	 * mailing_address table
	 */

	public int insertAddress( MailingAddressData mailingAddrData )
	{
		// Here the mailingAddrData will come in as the parameter,

		// Create UserAddress using mailingAddrData
		return createUserAddress( mailingAddrData );

	}

	/*
	 * Method : executeInsertAddress( )
	 * 
	 * Input : MailingAddressRecord obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the MailingAddressData values into
	 * mailing_address table
	 */

	public int executeInsertAddress( MailingAddressRecord mailingAddressRecObj )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		// Form the insertQuery using the UserAddressRecord object.
		String query = formInsertQuery( mailingAddressRecObj );

		System.out.println( "Insert Address Query=" + query );

		Statement stmt = null;
		Connection con = null;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			int val = stmt.executeUpdate( query );

			if( val == 0 ) return -1;

			return 0;
		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::UserAddressTable:insertAddress-" + ex );
			
			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserAddressTable:insertAddress-" + ex );
			
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
	 * Method : deleteUserAddress( )
	 * 
	 * Input : companyRegnKey 
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the MailingAddressData values from
	 * mailing_address table
	 */
	
	public int deleteUserAddress( String companyRegnKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "DELETE FROM " + tableName_ + " WHERE company_phoneno = '" + companyRegnKey
		        + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );
			if( deleteResult > 0 ) return 0;

			return -1;
		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::UserAddressTable:deleteUserAddress-"+ex );

			return -2;
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserAddressTable:deleteUserAddress-"+ex );

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
	 * Method : deleteAddress( )
	 * 
	 * Input : email 
	 * 
	 * Return : int
	 * 
	 * Purpose: To delete all address of a user through his email address
	 */
	
	public int deleteAddress( String email )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "DELETE FROM " + tableName_ + " WHERE email = '" + email
		        + "'";

		

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );
			if( deleteResult > 0 ) return 0;

			return 0;
		}

		catch( SQLException ex )  // Sql Exception
		{
			errLogger.logMsg( "Exception::UserAddressTable:deleteAddress-"+ex );

			return -2;
		}
		catch( Exception ex )  // General Exception
		{
			errLogger.logMsg( "Exception::UserAddressTable:deleteAddress-"+ex );

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
	 * Method : createUserAddress( )
	 * 
	 * Input : MailingAddressData object 
	 * 
	 * Return : int
	 * 
	 * Purpose: To insert the user address in mailing_address table.
	 */
	
	private int createUserAddress( MailingAddressData mailingAddrData )
	{
		// Form the record using the regnDataObj and return it
		MailingAddressRecord mailingAddressRecObj = new MailingAddressRecord( );
		int insertResult = 0;

		// set the values
		mailingAddressRecObj.address_ = mailingAddrData.address_;
		mailingAddressRecObj.addressType_ = mailingAddrData.addressType_;
		mailingAddressRecObj.cityName_ = mailingAddrData.city_;

		mailingAddressRecObj.zipcode_ = mailingAddrData.zipcode_;
		mailingAddressRecObj.regnKey_ = mailingAddrData.companyRegnKey_.companyPhoneNo_;

		mailingAddressRecObj.countryName_ = mailingAddrData.countryRegion_;
		mailingAddressRecObj.stateName_ = mailingAddrData.state_;
		mailingAddressRecObj.email_ = mailingAddrData.emailid_;

		System.out.println( "after user address db" );

		insertResult = executeInsertAddress( mailingAddressRecObj );

		if( insertResult != 0 )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			
			String errorMessage = "Error::UserAddressTable:insertAddress-Insert user " +
					"address failed.";

			errLogger.logMsg( errorMessage );

			return -1;
		}
		else
		{
			return 0;
		}

	}

	/*
	 * Method : createUserAddress( )
	 * 
	 * Input : MailingAddressRecord object 
	 * 
	 * Return : String insert query
	 * 
	 * Purpose: Form the insert query for mailing address
	 */
	
	
	private String formInsertQuery( MailingAddressRecord userAddressRecObj )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_id, address_type, address, city_name, ";
		insertValues = insertValues + "state_name, zipcode, country_name, regn_key, email)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + userAddressRecObj.userId_ + ", ";
		insertQuery = insertQuery + "'" + userAddressRecObj.addressType_ + "', ";
		insertQuery = insertQuery + "'" + userAddressRecObj.address_ + "', ";
		insertQuery = insertQuery + "'" + userAddressRecObj.cityName_ + "', ";
		insertQuery = insertQuery + "'" + userAddressRecObj.stateName_ + "', ";
		insertQuery = insertQuery + "'" + userAddressRecObj.zipcode_ + "', ";
		insertQuery = insertQuery + "'" + userAddressRecObj.countryName_ + "', ";
		insertQuery = insertQuery + "'" + userAddressRecObj.regnKey_ + "', ";
		insertQuery = insertQuery + "'" + userAddressRecObj.email_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
	
	/*
	 * Method : getMailingAddress( )
	 * 
	 * Input : CompanyRegn key, List<UserProfileData> obj(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the users from user_profile table
	 * for selected company
	 */
	
	public int getCity( UserProfileKey key, MailingAddressData mailingAddressData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT city_name FROM " + tableName_
		        + " WHERE email = '" + key.email_ + "'";

		//System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				mailingAddressData.city_ = rs.getString( "city_name" );

				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::MailingAddressTable:getCity"
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::MailingAddressTable:getCity"
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -3;
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
	
	/**
	 * @author Anbazhagan
	 * 
	 * @Date : Mar 23, 2013 5:08:32 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Geting MailingAddress Based on the MailingKey
	 * 
	 * @param key
	 * @param data
	 * @return
	 * 
	 */
	public int searchKey( MailingAddressKey key, MailingAddressData data )
	{
		PreparedStatement pretStmt = null;
		Connection con = null;
		String query = null;

		int result = 0;

		query = "SELECT * FROM " + tableName_ + " WHERE maill_key = ?";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			pretStmt = con.prepareStatement( query );
			pretStmt.setLong( 1, key.mailngKey_ );

			System.out.println( "Query=" + pretStmt );

			ResultSet rs = pretStmt.executeQuery( );

			MailingAddressUtils regnutils = new MailingAddressUtils( );

			result = regnutils.getData( rs, data );

			return result;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "MailingAddressTable :: searchKey :: SQL Error " + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		finally
        {
            try
            {
                if (pretStmt != null)
                {
                	pretStmt.close();
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

	/**
	 * @author Anbazhagan
	 * 
	 * @Date : Mar 23, 2013 5:09:11 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Retrun the List of Mailing Address based on the Filter
	 * 
	 * @param Filter
	 * @param regndataList
	 * @return
	 * 
	 */
	public int searchFilter( MailingAddressData Filter, List<MailingAddressData> dataList )
	{
		PreparedStatement pretStmt = null;
		Connection con = null;
		String query = null;

		
		int result = 0;

		query = "SELECT * FROM " + tableName_;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			
			if( con == null ) return -1;

			MailingAddressUtils regnutils = new MailingAddressUtils( );

			MailingAddressRecord regnrecord = new MailingAddressRecord( );
			
			regnutils.convert( Filter, regnrecord );

			StringHolder updaterQuery = new StringHolder( );
			
			updaterQuery.value = "";
			
			//System.out.println( "Email id="+ regnrecord.email_);
			
			regnutils.getFilterString( regnrecord, updaterQuery );
			
			query = query + updaterQuery.value;
			
			//System.out.println( "query="+query );

			pretStmt = con.prepareStatement( query );

			regnutils.prepareStatement( regnrecord, pretStmt );

			

			ResultSet rs = pretStmt.executeQuery( );

			result = regnutils.getDataList( rs, dataList );

			return result;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "MailingAddressTable : searchFilter :: SQL Error " + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		finally
        {
            try
            {
                if (pretStmt != null)
                {
                	pretStmt.close();
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

	/**
	 * 
	 * @author Anbazhagan
	 * 
	 * @Date : Mar 23, 2013 7:25:59 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Insert the Maill Address to the Table
	 * 
	 * @param data
	 * @param returnkey
	 * @return
	 * 
	 */
	public int insert( MailingAddressData data, MailingAddressKey returnkey )
	{
		PreparedStatement prestmt = null;
		Connection con = null;
		String query = null;
		ResultSet generateKey = null;

		query = "INSERT INTO " + tableName_ + " (";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			MailingAddressUtils regnutils = new MailingAddressUtils( );
			MailingAddressRecord regnrecord = new MailingAddressRecord( );
			regnutils.convert( data, regnrecord );

			StringHolder insertvalue = new StringHolder( );
			insertvalue.value = "";
			StringHolder insertcolumn = new StringHolder( );
			insertcolumn.value = "";
			regnutils.getInsertString( regnrecord, insertvalue, insertcolumn );
			query += insertcolumn.value + ") VALUES ( ";
			query += insertvalue.value + ")";
			System.out.println( "query =" + query );
			prestmt = con.prepareStatement( query );

			regnutils.prepareStatement( regnrecord, prestmt );

			System.out.println( "prestmt =" + prestmt );

			int val = prestmt.executeUpdate( );

			if( val > 0 )
			{
				generateKey = prestmt.getGeneratedKeys( );
				while( generateKey.next( ) )
					returnkey.mailngKey_ = generateKey.getInt( 1 );

				return 0;
			}
			else
				return -1;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MailingAddressTable :: insert :: SQL Error " + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
		}
		finally
        {
            try
            {
                if (prestmt != null)
                {
                	prestmt.close();
                }
                if( con != null )
                {
                    con.close();
                }
                if( generateKey != null)
                {
                	generateKey.close( );
                }
           }
           catch (SQLException sQLException)
           {
           }
        }
	}

	/**
	 * @author Anbazhagan
	 * 
	 * @Date : Mar 23, 2013 5:14:22 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Update the MailingAddressTable based on the Mailing Key
	 * 
	 * @param key
	 * @param data
	 * @return
	 * 
	 */
	public int update( MailingAddressKey key, MailingAddressData data )
	{
		PreparedStatement prestmt = null;
		Connection con = null;
		String query = null;
		int updateResult;

		query = "UPDATE " + tableName_ + " SET ";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;

			MailingAddressUtils regnutils = new MailingAddressUtils( );
			MailingAddressRecord regnrecord = new MailingAddressRecord( );
			regnutils.convert( data, regnrecord );
			regnrecord.show( );
			StringHolder updaterQuery = new StringHolder( );
			updaterQuery.value = "";
			regnutils.getUpdateString( regnrecord, updaterQuery );
			query += updaterQuery.value;
			query += " WHERE address_id =?";

			prestmt = con.prepareStatement( query );

			int index = regnutils.prepareStatement( regnrecord, prestmt );
			prestmt.setLong( index, key.mailngKey_ ); // for Adding the Company
													  // Regn Key Key

			System.out.println( "prestmt =" + prestmt );

			updateResult = prestmt.executeUpdate( );

			if( updateResult == 0 ) return -1;

			return 0;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "MailingAddressTable :: update :: SQL Error " + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
		}
		finally
        {
            try
            {
                if (prestmt != null)
                {
                	prestmt.close();
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

	/**
	 * @author Anbazhagan
	 * 
	 * @Date : Mar 23, 2013 7:26:28 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Deleting the Mail Address from the Table
	 * 
	 * @param mailKey
	 * @return
	 * 
	 */
	public int delete( MailingAddressKey mailKey )
	{
		PreparedStatement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE mail_key = ?";

		try
		{
			con = DBConnect.instance( ).getConnection( );
			if( con == null ) return -1;
			stmt = con.prepareStatement( query );
			stmt.setLong( 1, mailKey.mailngKey_ );
			System.out.println( "Query=" + stmt );

			deleteResult = stmt.executeUpdate( );
			if( deleteResult > 0 ) return 0;

			return -1;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::MailingAddressTable::delete::SQL Error " + ex;

			errLogger_.logMsg( errorMessage );

			return -2;
		}
		finally
        {
            try
            {
                if ( stmt != null)
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
	 * Input : CompanyRegn key, List<MailingAddressData> obj(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get mailing address data ( 1 ) for given user profile key
	 */
	
	public int find( UserProfileKey key, MailingAddressData mailingAddressData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_
		        + " WHERE email = '" + key.email_ + "' LIMIT 1";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				mailingAddressData.address_ = rs.getString( "address" );
				mailingAddressData.addressType_ = rs.getString( "address_type" );
				mailingAddressData.countryRegion_ = rs.getString( "country_name" );
				
				mailingAddressData.state_ = rs.getString( "state_name" );
				mailingAddressData.zipcode_ = rs.getString( "zipcode" );
				mailingAddressData.city_ = rs.getString( "city_name" );

				return 0; // Success
			}

			return 0; // No record for user profile key

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::MailingAddressTable:getCity"
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::MailingAddressTable:getCity"
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -3;
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
	 * Method : getAllRegnKeys( )
	 * 
	 * Input : Company registration keys set, country
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to fetch the all the regn keys from this table
	 */
	
    public int getAllRegnKeys( String country, Set<CompanyRegnKey> regnKeys )
    {
	 
		int result = 0;
		
		ErrorLogger errLogger = ErrorLogger.instance( );
	
		String selectQuery = "SELECT DISTINCT regn_key FROM " + this.tableName_+" WHERE country_name='"+country+"'";
		
		Statement statement = null;
		Connection con		= null;
		ResultSet rs		= null;
		
		System.out.println( "Query="+selectQuery );
		
		
		try
        {
			con 		= DBConnect.instance( ).getConnection( );
			statement 	= con.createStatement( );
			rs			= statement.executeQuery( selectQuery );
	
			while( rs.next( ) )
			{
				CompanyRegnKey regnKey = new CompanyRegnKey( );
				
				regnKey.companyPhoneNo_ = rs.getString( "regn_key" );
				
				regnKeys.add( regnKey );
				
				regnKey = null;
			}
			
			
			return result;
        }
		catch( SQLException e )
		{
			errLogger.logMsg( "Exception::MailingAddressTable.getAllRegnKeys - " + e );
			
			return -2;
		}
        catch( Exception e )
        {
        	errLogger.logMsg( "Exception::MailingAddressTable.getAllRegnKeys - " + e );
			
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

package db.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ErrorLogger;
import core.login.LoginAttemptsData;
import db.utils.DBConnect;

/*
 * Class  : LoginAttemptsTable
 * 
 * Purpose: It is used to query the login_attempts table.
 * 
 */

public class LoginAttemptsTable
{

	private String tableName_;

	/*
	 * Method : LoginAttemptsTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 * 
	 */

	public LoginAttemptsTable()
	{
		this.tableName_ = "login_attempts";
	}

	/*
	 * Method : insertLoginAttempts( )
	 * 
	 * Input : LoginAttemptsData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the LoginAttempts values into
	 * login_attempts table
	 */

	public int insertLoginAttempts( LoginAttemptsData loginAttemptsData )
	{
		// Here the regnDataObj will come in as the parameter,

		// Create AccountPolicyRecord using accPolicyData
		LoginAttemptsRecord loginAttemptsRec = createLoginAttemptsRecord( loginAttemptsData );

		// Form the insertQuery using the AccountPolicyRecord object.
		String query = formInsertQuery( loginAttemptsRec );

		System.out.println( "Query = " + query );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			if( insertResult > 0 ) return 0;

			return -1;

		}

		catch( SQLException ex )
		{
			System.out.println( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "LoginAttemtpsTable : insertLoginAttempts :: SQL Error "
			        + ex );

			return -2;
		}
		
		finally
        {
            try
            {
                if ( stmt != null )
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
	 * Method : updateLoginAttempts( )
	 * 
	 * Input : LoginAttemptsData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the LoginAttempts values into
	 * login_attempts table
	 */

	public int updateLoginAttempts( LoginAttemptsData loginAttemptsData )
	{
		LoginAttemptsRecord loginAttemptsRec = createLoginAttemptsRecord( loginAttemptsData );

		String query = "UPDATE " + tableName_;
		query = query + " SET invalid_attempts_count = "
		        + loginAttemptsRec.invalidAttemptsCount_ + ", ";
		query = query + "lock_out_timestamp = '" + loginAttemptsRec.lockoutTimestamp_
		        + "', ";
		query = query + "attempt_timestamp = '" + loginAttemptsRec.attemptedTimestamp_
		        + "'";
		query = query + " WHERE email ='" + loginAttemptsRec.email_ + "'";

		// query = query + " Commit ";

		System.out.println( "Query = " + query );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );

			stmt.executeQuery( "Commit" );

			if( insertResult > 0 ) return 0;

			return -1;

		}

		catch( SQLException ex )
		{
			System.out.println( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "LoginAttemtpsTable : updateLoginAttempts :: SQL Error "
			        + ex );

			return -2;
		}
		finally
        {
            try
            {
                if ( stmt != null )
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
	 * Method : getLoginAttempts( )
	 * 
	 * Input : String email, LoginAttemptsData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve LoginAttemptsData values from
	 * login_attempts table for given email
	 */

	public int getLoginAttempts( String email, LoginAttemptsData loginAttemptsData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		ResultSet rs = null;

		// List<CompanyRegnRecord> companyRegnRecObjList = new
		// ArrayList<CompanyRegnRecord>();

		query = "SELECT * FROM " + tableName_ + " WHERE email = '" + email + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				loginAttemptsData.email_ = rs.getString( "email" );
				loginAttemptsData.invalidAttemptsCount_ = rs
				        .getInt( "invalid_attempts_count" );
				loginAttemptsData.lockoutTimestamp_ = rs
				        .getTimestamp( "lock_out_timestamp" );
				loginAttemptsData.attemptedTimestamp_ = rs
				        .getTimestamp( "attempt_timestamp" );

				return 0; // Success
			}

			return -2; // No record exists
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "LoginAttemptsTable : getLoginAttempts :: SQL Error "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -1; // SqlException
		}
		
		finally
        {
            try
            {
                if ( rs != null )
                {
                    rs.close();
                }
                if ( stmt != null )
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
	 * Method : deleteLoginAttempts( )
	 * 
	 * Input : String email
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the LoginAttempts values from
	 * login_attempts table
	 */

	public int deleteLoginAttempts( String email )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE email = '" + email + "'";

		System.out.println( "Query=" + query );

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
			String errorMessage = "LoginAttemptsTable : deleteLoginAttempts :: SQL Error "
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
	 * Method : createLoginAttemptsRecord( )
	 * 
	 * Input : LoginAttemptsData obj
	 * 
	 * Return : LoginAttemptsRecord
	 * 
	 * Purpose: It converts LoginAttemptsData to LoginAttemptsRecord
	 */

	private LoginAttemptsRecord createLoginAttemptsRecord(
	        LoginAttemptsData loginAttemptsData )
	{
		// Form the record using the regnDataObj and return it
		LoginAttemptsRecord loginAttemptsRecord = new LoginAttemptsRecord( );

		// set the values
		loginAttemptsRecord.email_ = loginAttemptsData.email_;
		loginAttemptsRecord.invalidAttemptsCount_ = loginAttemptsData.invalidAttemptsCount_;
		loginAttemptsRecord.lockoutTimestamp_ = loginAttemptsData.lockoutTimestamp_;
		loginAttemptsRecord.attemptedTimestamp_ = loginAttemptsData.attemptedTimestamp_;

		return loginAttemptsRecord;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : LoginAttemptsRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using LoginAttemptsRecord and returns
	 * as string
	 */

	private String formInsertQuery( LoginAttemptsRecord loginAttemptsRecord )
	{
		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(email, invalid_attempts_count, lock_out_timestamp, attempt_timestamp)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + loginAttemptsRecord.email_ + "', ";
		insertQuery = insertQuery + loginAttemptsRecord.invalidAttemptsCount_ + ", ";
		insertQuery = insertQuery + "'" + loginAttemptsRecord.lockoutTimestamp_ + "', ";
		insertQuery = insertQuery + "'" + loginAttemptsRecord.attemptedTimestamp_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
}

package db.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import core.login.LockoutPolicyData;
import core.login.PasswordLoginPolicyData;

import utils.ErrorLogger;
import db.utils.DBConnect;

/*
 * Class  : LockoutPoliciesTable
 * 
 * Purpose: It is used to query the lockout_policies table.
 * 
 */

public class LockoutPoliciesTable
{

	private String tableName_;

	/*
	 * Method : LockoutPoliciesTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public LockoutPoliciesTable()
	{
		this.tableName_ = "account_lockout_policies";
	}

	/*
	 * Method : insertAccountLockoutPolicies( )
	 * 
	 * Input : AccountPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the AccountPoliciesData values into
	 * account_lockout_policies table
	 */

	public int insert( LockoutPolicyData lockoutPolicyData )
	{
		// Here the accPolicyData will come in as the parameter,

		// Create AccountPolicyRecord using accPolicyData
		LockoutPoliciesRecord lockOutPolicyRec = createLockOutPoliciesRecord( lockoutPolicyData );

		// Form the insertQuery using the AccountPolicyRecord object.
		String query = formInsertQuery( lockOutPolicyRec );

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
			errLogger_
			        .logMsg( "LockoutPoliciesTable : insertAccountLockoutPolicies :: SQL Error "
			                + ex );

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
	 * Method : getLockoutPolicies( )
	 * 
	 * Input : String regnKey, AccountPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve the AccountPoliciesData values from
	 * account_lockout_policies table for given regn key
	 */

	public int get( String regnKey, LockoutPolicyData data )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '" + regnKey + "'";

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				// AccountPolicyData data = new AccountPolicyData( );
				data.regnRelKey_ = rs.getString( "regn_rel_key" );
				data.invalidLoginAttempts_ = rs.getInt( "invalid_login_attempts" );
				data.lockoutDurationMin_ = rs.getInt( "lockout_duration_min" );
				data.resetLockoutDurationMin_ = rs.getInt( "reset_lockout_duration_min" );
				data.adminUnlockFlag_ = rs.getInt( "admin_unlock_flag" );
				data.expireSessionMin_ = rs.getInt( "expire_session_min" );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "LockoutPoliciesTable : getLockoutPolicies :: SQL Error "
			        + ex;

			errLogger_.logMsg( errorMessage );

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
	 * Method : getAllLockoutPolicies( )
	 * 
	 * Input : List<AccountPolicyData> obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the AccountLockoutPolicies values from
	 * account_policies table
	 */

	public int getList( List<LockoutPolicyData> accPolicyData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_;

		System.out.println( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				LockoutPolicyData data = new LockoutPolicyData( );
				data.regnRelKey_ = rs.getString( "regn_rel_key" );
				data.invalidLoginAttempts_ = rs.getInt( "invalid_login_attempts" );
				data.lockoutDurationMin_ = rs.getInt( "lockout_duration_min" );
				data.resetLockoutDurationMin_ = rs.getInt( "reset_lockout_duration_min" );
				data.adminUnlockFlag_ = rs.getInt( "admin_unlock_flag" );
				data.expireSessionMin_ = rs.getInt( "expire_session_min" );

				accPolicyData.add( data );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "LockoutPoliciesTable : getLockoutPolicies :: SQL Error "
			        + ex;

			errLogger_.logMsg( errorMessage );

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
	 * Method : update( )
	 * 
	 * Input : LockoutPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the lockout_policies values
	 */

	public int update( LockoutPolicyData lockoutPolicyData )
	{
		LockoutPoliciesRecord policiesRecord = createLockOutPoliciesRecord( lockoutPolicyData );
		
		
		String query = "UPDATE " + tableName_;
		query = query + " SET invalid_login_attempts = "
		        + policiesRecord.invalidLoginAttempts_ + ", ";
		query = query + "lockout_duration_min = " + policiesRecord.lockoutDurationMin_
		        + ", ";
		query = query + "reset_lockout_duration_min = " + policiesRecord.resetLockoutDurationMin_
		        + ", ";
		query = query + "admin_unlock_flag = " + policiesRecord.adminUnlockFlag_
		        + ", ";
		query = query + "expire_session_min = " + policiesRecord.expireSessionMin_;
		       
		query = query + " WHERE regn_rel_key ='" + policiesRecord.regnRelKey_ + "'";

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
			errLogger_.logMsg( "Exception::LockoutPoliciesTable:update - SQL Error "
			        + ex );

			return -2;
		}
		catch( Exception ex )
		{
			System.out.println( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::LockoutPoliciesTable:update - SQL Error "
			        + ex );

			return -3;
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
	 * Method : deleteLockoutPolicies( )
	 * 
	 * Input : String regnKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the AccountLockoutPolicies values from
	 * account_policies table for given regn key
	 */

	public int deleteLockoutPolicies( String regnKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE regn_rel_key = '" + regnKey + "'";

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
			String errorMessage = "LockoutPolicies : deleteLockoutPolicies :: SQL Error "
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
	 * Method : createLockOutPoliciesRecord( )
	 * 
	 * Input : AccountPolicyData obj
	 * 
	 * Return : LockoutPoliciesRecord
	 * 
	 * Purpose: It converts AccountPolicyData to AccountPoliciesRecord
	 */

	private LockoutPoliciesRecord createLockOutPoliciesRecord(
	        LockoutPolicyData lockoutPolicyData )
	{
		// Form the record using the regnDataObj and return it
		LockoutPoliciesRecord lockOutPolicyRecord = new LockoutPoliciesRecord( );

		// set the values
		lockOutPolicyRecord.adminUnlockFlag_ = lockoutPolicyData.adminUnlockFlag_;
		lockOutPolicyRecord.expireSessionMin_ = lockoutPolicyData.expireSessionMin_;
		lockOutPolicyRecord.invalidLoginAttempts_ = lockoutPolicyData.invalidLoginAttempts_;

		lockOutPolicyRecord.lockoutDurationMin_ = lockoutPolicyData.lockoutDurationMin_;
		lockOutPolicyRecord.regnRelKey_ = lockoutPolicyData.regnRelKey_;
		lockOutPolicyRecord.resetLockoutDurationMin_ = lockoutPolicyData.resetLockoutDurationMin_;

		return lockOutPolicyRecord;
	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : LockoutPoliciesRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using LockoutPoliciesRecord and returns
	 * as string
	 */

	private String formInsertQuery( LockoutPoliciesRecord lockOutPolicyRec )
	{
		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(regn_rel_key, invalid_login_attempts, lockout_duration_min, reset_lockout_duration_min, ";
		insertValues = insertValues + "admin_unlock_flag, expire_session_min)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + lockOutPolicyRec.regnRelKey_ + "', ";
		insertQuery = insertQuery + lockOutPolicyRec.invalidLoginAttempts_ + ", ";
		insertQuery = insertQuery + lockOutPolicyRec.lockoutDurationMin_ + ", ";

		insertQuery = insertQuery + lockOutPolicyRec.resetLockoutDurationMin_ + ", ";
		insertQuery = insertQuery + lockOutPolicyRec.adminUnlockFlag_ + ", ";
		insertQuery = insertQuery + lockOutPolicyRec.expireSessionMin_ + "";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}

}

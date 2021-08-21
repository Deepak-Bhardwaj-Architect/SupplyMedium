package db.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import core.login.AccountPolicyData;
import core.login.LoginAttemptsData;
//import core.login.AccountPolicy;
import core.login.PasswordPolicyData;

import utils.ErrorLogger;
import db.utils.DBConnect;
import utils.ErrorMaster;

/*
 * Class  : AccountPoliciesTable
 * 
 * Purpose: It is used to query the account_policies table.
 * 
 */

public class PasswordPoliciesTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : AccountPoliciesTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public PasswordPoliciesTable()
	{
           

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "password_policies";
	}

	/*
	 * Method : insertAccountPolicies( )
	 * 
	 * Input : AccountPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the AccountPoliciesData values into
	 * account_policies table
	 */

	public int insert( PasswordPolicyData passPolicyData )
	{
		// Here the regnDataObj will come in as the parameter,

		// Create AccountPolicyRecord using accPolicyData
		PasswordPoliciesRecord accPolicyRec = createAccPoliciesRecord( passPolicyData );

		// Form the insertQuery using the AccountPolicyRecord object.
		String query = formInsertQuery( accPolicyRec );
		
		errorMaster_.insert( "Inserting PasswordPolicyData....." );
		
		errorMaster_.insert( query );

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
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "AccountPoliciesTable : insertAccountPolicies :: SQL Error "
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
	 * Method : getAccountPolicies( )
	 * 
	 * Input : String regnKey, AccountPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve AccountPoliciesData values from
	 * account_policies table for given regn key
	 */

	public int get( String regnKey, PasswordPolicyData data )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// List<CompanyRegnRecord> companyRegnRecObjList = new
		// ArrayList<CompanyRegnRecord>();

		query = "SELECT * FROM " + tableName_ + " WHERE regn_rel_key = '" + regnKey + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				// AccountPolicyData data = new AccountPolicyData( );
				data.regnRelKey_ = rs.getString( "regn_rel_key" );
				data.passwordHistoryDays_ = rs.getInt( "password_history_days" );
				data.passwordAgeMinDays_ = rs.getInt( "password_agemin_days" );
				
				data.passwordAgeMaxDays_ = rs.getInt( "password_agemax_days" );
				data.passwordLength_ = rs.getInt( "password_length" );
				
				data.notificationRemainderNday_ = rs.getInt( "notification_remainder_nday" );
				data.dailyRemainderFlag_ = rs.getInt( "daily_remainder_flag" );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "CompanyRegnTable : getCompanyForKey :: SQL Error " + ex;

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
	 * Method : getAllAccountPolicies( )
	 * 
	 * Input : List<AccountPolicyData> obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the AccountPolicies values from
	 * account_policies table
	 */

	public int getList( List<PasswordPolicyData> passPolicyDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// List<CompanyRegnRecord> companyRegnRecObjList = new
		// ArrayList<CompanyRegnRecord>();

		query = "SELECT * FROM " + tableName_;

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				PasswordPolicyData data = new PasswordPolicyData( );
				data.regnRelKey_ = rs.getString( "regn_rel_key" );
				data.passwordHistoryDays_ = rs.getInt( "password_history_days" );
				data.passwordAgeMinDays_ = rs.getInt( "password_agemin_days" );
				
				data.passwordAgeMaxDays_ = rs.getInt( "password_agemax_days" );
				data.passwordLength_ = rs.getInt( "password_length" );
				
				data.notificationRemainderNday_ = rs.getInt( "notification_remainder_nday" );
				data.dailyRemainderFlag_ = rs.getInt( "daily_remainder_flag" );

				passPolicyDataList.add( data );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "CompanyRegnTable : getCompanyForKey :: SQL Error " + ex;

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
	 * Input : PasswordPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the password_policies values
	 */

	public int update( PasswordPolicyData passPolicyData )
	{
		//LoginAttemptsRecord loginAttemptsRec = createLoginAttemptsRecord( loginAttemptsData );
		PasswordPoliciesRecord policiesRecord = createAccPoliciesRecord( passPolicyData );
		
		String query = "UPDATE " + tableName_;
		query = query + " SET password_history_days = "
		        + policiesRecord.passwordHistoryDays_ + ", ";
		query = query + "password_agemax_days = " + policiesRecord.passwordAgeMaxDays_
		        + ", ";
		query = query + "password_agemin_days = " + policiesRecord.passwordAgeMinDays_
		        + ", ";
		query = query + "password_length = " + policiesRecord.passwordLength_
		        + ", ";
		query = query + "notification_remainder_nday = " + policiesRecord.notificationRemainderNday_
		        + ", ";
		query = query + "daily_remainder_flag = " + policiesRecord.dailyRemainderFlag_;
		       
		query = query + " WHERE regn_rel_key ='" + policiesRecord.regnRelKey_ + "'";

		// query = query + " Commit ";

		errorMaster_.insert( "Query = " + query );

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
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::PasswordPoliciesTable:update - SQL Error "
			        + ex );

			return -2;
		}
		catch( Exception ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::PasswordPoliciesTable:update - SQL Error "
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
	 * Method : deleteAccountPolicies( )
	 * 
	 * Input : String regnKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the AccountPolicies values from
	 * account_policies table
	 */

	public int delete( String regnKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE regn_rel_key = '" + regnKey + "'";

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
			String errorMessage = "AccountPoliciesTable : deleteAccountPolicies :: SQL Error "
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
	 * Method : createAccPoliciesRecord( )
	 * 
	 * Input : AccountPolicyData obj
	 * 
	 * Return : AccountPoliciesRecord
	 * 
	 * Purpose: It converts AccountPolicyData to AccountPoliciesRecord
	 */

	private PasswordPoliciesRecord createAccPoliciesRecord( PasswordPolicyData passPolicyData )
	{
		// Form the record using the regnDataObj and return it
		PasswordPoliciesRecord accPolicyRecord = new PasswordPoliciesRecord( );

		// set the values
		accPolicyRecord.dailyRemainderFlag_ = passPolicyData.dailyRemainderFlag_;
		accPolicyRecord.notificationRemainderNday_ = passPolicyData.notificationRemainderNday_;

		accPolicyRecord.passwordAgeMaxDays_	 = passPolicyData.passwordAgeMaxDays_;
		accPolicyRecord.passwordLength_	 = passPolicyData.passwordLength_;
		
		accPolicyRecord.passwordAgeMinDays_ = passPolicyData.passwordAgeMinDays_;
		accPolicyRecord.passwordHistoryDays_ = passPolicyData.passwordHistoryDays_;

		accPolicyRecord.regnRelKey_ = passPolicyData.regnRelKey_;

		return accPolicyRecord;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : AccountPoliciesRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using AccountPoliciesRecord and returns
	 * as string
	 */

	private String formInsertQuery( PasswordPoliciesRecord accPolicyRec )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(regn_rel_key, password_history_days, password_agemax_days, password_agemin_days, ";
		insertValues = insertValues
		        + "password_length, notification_remainder_nday, daily_remainder_flag)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + accPolicyRec.regnRelKey_ + "', ";
		insertQuery = insertQuery + accPolicyRec.passwordHistoryDays_ + ", ";
		insertQuery = insertQuery + accPolicyRec.passwordAgeMaxDays_ + ", ";

		insertQuery = insertQuery + accPolicyRec.passwordAgeMinDays_ + ", ";
		insertQuery = insertQuery + accPolicyRec.passwordLength_ + ", ";
		insertQuery = insertQuery + accPolicyRec.notificationRemainderNday_ + ", ";

		insertQuery = insertQuery + accPolicyRec.dailyRemainderFlag_;

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
}

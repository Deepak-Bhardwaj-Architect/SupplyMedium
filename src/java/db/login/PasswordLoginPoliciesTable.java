package db.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import utils.ErrorLogger;
import core.login.PasswordLoginPolicyData;
import core.login.PasswordPolicyData;
import db.utils.DBConnect;
import utils.ErrorMaster;

/*
 * Class  : PasswordPoliciesTable
 * 
 * Purpose: It is used to query the password_policies table.
 * 
 */

public class PasswordLoginPoliciesTable
{
private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : PasswordPoliciesTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public PasswordLoginPoliciesTable()
	{
         

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "password_login_policies";
	}

	/*
	 * Method : insertPasswordPolicies( )
	 * 
	 * Input : AccountPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the AccountPoliciesData values into
	 * password_policies table
	 */

	public int insert( PasswordLoginPolicyData passLoginPolicyData )
	{
		// Here the accPolicyData will come in as the parameter,

		// Create AccountPolicyRecord using accPolicyData
		PasswordLoginPoliciesRecord passwordPolicyRec = createPasswordPoliciesRecord( passLoginPolicyData );

		// Form the insertQuery using the AccountPolicyRecord object.
		String query = formInsertQuery( passwordPolicyRec );

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
			errLogger_
			        .logMsg( "PasswordPoliciesTable : insertPasswordPolicies :: SQL Error "
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
	 * Method : getPasswordPolicies( )
	 * 
	 * Input : String regnKey, AccountPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve AccountPoliciesData values from
	 * password_policies table for given regn key
	 */

	public int get( String regnKey, PasswordLoginPolicyData data )
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
				data.passwordComplexityFlag_ = rs.getInt( "password_complexity_flag" );
				data.upperLowerFlag_ = rs.getInt( "upper_lower_flag" );
				data.numericFlag_ = rs.getInt( "numeric_flag" );
				data.specialCharactersFlag_ = rs.getInt( "special_characters_flag" );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "PasswordPoliciesTable : getPasswordPolicies :: SQL Error "
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
	 * Method : getAllPasswordPolicies( )
	 * 
	 * Input : List<AccountPolicyData> obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the accPolicyData values from
	 * password_policies table
	 */

	public int getList( List<PasswordLoginPolicyData> passLoginPolicyDataList )
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
				PasswordLoginPolicyData data = new PasswordLoginPolicyData( );
				data.regnRelKey_ = rs.getString( "regn_rel_key" );
				data.passwordComplexityFlag_ = rs.getInt( "password_complexity_flag" );
				data.upperLowerFlag_ = rs.getInt( "upper_lower_flag" );
				data.numericFlag_ = rs.getInt( "numeric_flag" );
				data.specialCharactersFlag_ = rs.getInt( "special_characters_flag" );

				passLoginPolicyDataList.add( data );
			}

			return 0; // Success
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "PasswordPoliciesTable : getPasswordPolicies :: SQL Error "
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
	 * Method : deletePasswordPolicies( )
	 * 
	 * Input : String regnKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the AccountPolicyData values from
	 * password_policies table
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
			String errorMessage = "PasswordPoliciesTable : deletePasswordPolicies :: SQL Error "
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
	 * Method : update( )
	 * 
	 * Input : PasswordLoginPolicyData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the password_login_policies values
	 */

	public int update( PasswordLoginPolicyData passLoginPolicyData )
	{
		//LoginAttemptsRecord loginAttemptsRec = createLoginAttemptsRecord( loginAttemptsData );
		PasswordLoginPoliciesRecord policiesRecord = createPasswordPoliciesRecord( passLoginPolicyData );
		
		String query = "UPDATE " + tableName_;
		query = query + " SET password_complexity_flag = "
		        + policiesRecord.passwordComplexityFlag_ + ", ";
		query = query + "upper_lower_flag = " + policiesRecord.upperLowerFlag_
		        + ", ";
		query = query + "numeric_flag = " + policiesRecord.numericFlag_
		        + ", ";
		query = query + "special_characters_flag = " + policiesRecord.specialCharactersFlag_;
		        
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
			errLogger_.logMsg( "Exception::PasswordLoginPoliciesTable:update - SQL Error "
			        + ex );

			return -2;
		}
		catch( Exception ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::PasswordLoginPoliciesTable:update - SQL Error "
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
	 * Method : createPasswordPoliciesRecord( )
	 * 
	 * Input : AccountPolicyData obj
	 * 
	 * Return : PasswordPoliciesRecord
	 * 
	 * Purpose: It converts AccountPolicyData to PasswordPoliciesRecord
	 */

	private PasswordLoginPoliciesRecord createPasswordPoliciesRecord(
	        PasswordLoginPolicyData passLoginPolicyData )
	{
		// Form the record using the regnDataObj and return it
		PasswordLoginPoliciesRecord passwordPolicyRecord = new PasswordLoginPoliciesRecord( );

		// set the values
		passwordPolicyRecord.passwordComplexityFlag_ = passLoginPolicyData.passwordComplexityFlag_;
		passwordPolicyRecord.upperLowerFlag_ = passLoginPolicyData.upperLowerFlag_;
		passwordPolicyRecord.numericFlag_ = passLoginPolicyData.numericFlag_;

		passwordPolicyRecord.specialCharactersFlag_ = passLoginPolicyData.specialCharactersFlag_;
		passwordPolicyRecord.regnRelKey_ = passLoginPolicyData.regnRelKey_;

		return passwordPolicyRecord;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : AccountPoliciesRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using PasswordPoliciesRecord and
	 * returns as string
	 */

	private String formInsertQuery( PasswordLoginPoliciesRecord passwordPolicyRec )
	{
		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(regn_rel_key, password_complexity_flag, upper_lower_flag, numeric_flag, ";
		insertValues = insertValues + "special_characters_flag)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + passwordPolicyRec.regnRelKey_ + "', ";
		insertQuery = insertQuery + passwordPolicyRec.passwordComplexityFlag_ + ", ";
		insertQuery = insertQuery + passwordPolicyRec.upperLowerFlag_ + ", ";

		insertQuery = insertQuery + passwordPolicyRec.numericFlag_ + ", ";
		insertQuery = insertQuery + passwordPolicyRec.specialCharactersFlag_ + "";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}

}

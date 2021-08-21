package db.usermgmt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ErrorLogger;

import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import db.utils.DBConnect;
import utils.ErrorMaster;

/*
 * Class  : UserAccountPoliciesTable
 * 
 * Purpose: It is used to query the user_account_policies table.
 * 
 */

public class UserAccountPoliciesTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : UserAccountPoliciesTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public UserAccountPoliciesTable()
	{
            ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "user_account_policies";
	}

	/*
	 * Method : insertPolicies( )
	 * 
	 * Input : UserProfileData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the UserProfileData values into
	 * user_account_policies table
	 */

	public int insertPolicies( UserProfileData userProfileData )
	{
		UserAccountPoliciesRecord userPolicyRec = createUserPoliciesRecord( userProfileData );

		String query = formInsertQuery( userPolicyRec );

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
			errLogger_.logMsg( "UserAccountPoliciesTable : insertPolicies :: SQL Error "
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
	 * Method : updatePolicies( )
	 * 
	 * Input : UserProfileData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the userProfileData values into
	 * user_account_policies table
	 */

	public int updatePolicies( UserProfileData userProfileData )
	{
		UserAccountPoliciesRecord userPoliciesRec = createUserPoliciesRecord( userProfileData );

		userProfileData.show( );
		
		userPoliciesRec.show( );
		
		String query = "UPDATE " + tableName_;
		query = query + " SET change_password_flag = "
		        + userPoliciesRec.changePasswordFlag_ + ", ";
		query = query + "disable_account_flag = " + userPoliciesRec.disableAccountFlag_
		        + ", ";
		query = query + "account_expiration_days = "
		        + userPoliciesRec.accountExpireDays_ + "";
		query = query + " WHERE user_rel_key ='" + userPoliciesRec.userRelKey_ + "'";

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

			if( insertResult >= 0 ) return 0;

			return -1;

		}

		catch( SQLException ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "UserAccountPoliciesTable : updatePolicies :: SQL Error "
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
	 * Method : update( )
	 * 
	 * Input : UserProfileData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the change password flag value of
	 * user_account_policies table
	 */

	public int update( UserProfileData userProfileData )
	{
		UserAccountPoliciesRecord userPoliciesRec = createUserPoliciesRecord( userProfileData );

		String query = "UPDATE " + tableName_;
		query = query + " SET change_password_flag = "
		        + userPoliciesRec.changePasswordFlag_;
		query = query + " WHERE user_rel_key ='" + userPoliciesRec.userRelKey_ + "'";

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

			if( insertResult >= 0 ) return 0;

			return -1;

		}

		catch( SQLException ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "UserAccountPoliciesTable : updatePolicies :: SQL Error "
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
	 * Input : String UserProfileKey, UserProfileData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve UserProfileData values from
	 * user_account_policies table for given userKey key
	 */

	public int getUserPolicies( UserProfileKey userKey, UserProfileData data )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.email_
		        + "'";

		//errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				//data.userProfileKey_.email_ = rs.getString( "user_rel_key" );
				data.changePasswordFlag_ = rs.getInt( "change_password_flag" );
				data.disableAccountFlag_ = rs.getInt( "disable_account_flag" );
				data.accountExpireDays_ = rs.getInt( "account_expiration_days" );
				
				return 0; //Success
			}

			return -1; // No record exists
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::UserAccountPoliciesTable.getUserPolicies() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "Exception::UserAccountPoliciesTable.getUserPolicies() - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -3; // Exception
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
	 * Method : deletePolicies( )
	 * 
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the UserAccountPolicies values from
	 * user_account_policies table
	 */

	public int deletePolicies( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );

			deleteResult = stmt.executeUpdate( query );

			if( deleteResult > 0 ) return 0;

			return 0;

		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserAccountPoliciesTable : deletePolicies :: SQL Error "
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
	 * Method : createUserPoliciesRecord( )
	 * 
	 * Input : UserProfileData obj
	 * 
	 * Return : UserAccountPoliciesRecord
	 * 
	 * Purpose: It converts UserProfileData to UserAccountPoliciesRecord
	 */

	private UserAccountPoliciesRecord createUserPoliciesRecord(
	        UserProfileData userProfileData )
	{
		UserAccountPoliciesRecord userProfileRec = new UserAccountPoliciesRecord( );

		// set the values
		userProfileRec.userRelKey_ = userProfileData.userProfileKey_.email_;
		userProfileRec.changePasswordFlag_ = userProfileData.changePasswordFlag_;

		userProfileRec.disableAccountFlag_ = userProfileData.disableAccountFlag_;
		userProfileRec.accountExpireDays_ = userProfileData.accountExpireDays_;

		return userProfileRec;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserAccountPoliciesRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserAccountPoliciesRecord and
	 * returns as string
	 */

	private String formInsertQuery( UserAccountPoliciesRecord userPolicyRec )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_rel_key, change_password_flag, disable_account_flag, account_expiration_days)";
		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + userPolicyRec.userRelKey_ + "', ";
		insertQuery = insertQuery + userPolicyRec.changePasswordFlag_ + ", ";

		insertQuery = insertQuery + userPolicyRec.disableAccountFlag_ + ", ";
		insertQuery = insertQuery + "'" + userPolicyRec.accountExpireDays_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}
}
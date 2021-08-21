package db.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import utils.ErrorLogger;
import utils.PasswordStore;
import core.regn.LoginData;
import core.regn.UserProfileKey;
import db.utils.DBConnect;
import utils.ErrorMaster;

/*
 * Class  : ActivationPendingTable
 * 
 * Purpose: It is used to query the activation_pending table.
 * 
 */

public class ActivationPendingTable
{
    private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : ActivationPendingTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public ActivationPendingTable()
	{
          

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "activation_pending";
	}

	/*
	 * Method : insert( )
	 * 
	 * Input : LoginData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the login details values before activation
	 * into activation_pending table
	 */

	public int insert( LoginData loginData )
	{
		ActivationPendingRecord rec = createRecord( loginData );

		String query = formInsertQuery( rec );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		errorMaster_.insert( "Insert Login Query = "+query );
		
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
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "ActivationPendingTable : insert :: SQL Error " + ex );
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
	 * Method : createRecord( )
	 * 
	 * Input : LoginData obj
	 * 
	 * Return : ActivationPendingRecord
	 * 
	 * Purpose: It converts LoginData to ActivationPendingRecord
	 */

	private ActivationPendingRecord createRecord( LoginData loginData )
	{
		ActivationPendingRecord rec = new ActivationPendingRecord( );

		// set the values
		rec.emailAddress_ = loginData.emailid_;

		PasswordStore passwordStoreObj = new PasswordStore( );
		String encryptedPassword = passwordStoreObj.getEncryptedValue( loginData.password_ );
		rec.encryptedPassword_ = encryptedPassword;
		rec.createdTimestamp_ = new Timestamp( System.currentTimeMillis( ) );

		return rec;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : ActivationPendingRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using ActivationPendingRecord and
	 * returns as string
	 */

	private String formInsertQuery( ActivationPendingRecord rec )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_rel_key, password, created_timestamp)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + rec.emailAddress_ + "', ";
		insertQuery = insertQuery + "'" + rec.encryptedPassword_ + "', ";
		insertQuery = insertQuery + "'" + rec.createdTimestamp_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}

	/*
	 * Method : isValidLogin( )
	 * 
	 * Input : LoginData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check whether login credentials are valid
	 */

	public int isValidLogin( LoginData loginData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		ActivationPendingRecord rec = createRecord( loginData );

		query = "SELECT * FROM " + tableName_ + " WHERE user_rel_key = '"
		        + rec.emailAddress_ + "'";
		query = query + " AND password = '" + rec.encryptedPassword_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				return 0; // Success
			}

			return -2; // InvalidCredentials
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "ActivationPendingTable : isValidLogin :: SQL Error "
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
	 * Method : getPasswordTimestamp( )
	 * 
	 * Input : String email, ref LoginData
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get password timestamp from activation_pending
	 * table
	 */

	public int getPasswordTimestamp( String email, LoginData loginData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT created_timestamp FROM " + tableName_ + " WHERE user_rel_key = '"
		        + email + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				loginData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );

				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "ActivationPendingTable : getPasswordTimestamp :: SQL Error "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
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
	 * Method : getRecord( )
	 * 
	 * Input : String email, ref LoginData
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get password timestamp from activation_pending
	 * table
	 */

	public int getRecord( String email, LoginData loginData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT user_rel_key, password, created_timestamp FROM " + tableName_ + " WHERE user_rel_key = '"
		        + email + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				loginData.emailid_	=	rs.getString( "user_rel_key" );
				loginData.password_	= 	rs.getString( "password" );
				loginData.createdTimestamp_ = rs.getTimestamp( "created_timestamp" );

				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "ActivationPendingTable : getPasswordTimestamp :: SQL Error "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
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
	 * Method : isEmailExists( )
	 * 
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check whether given userkey (Email) exists in the
	 * activation_pending table or not
	 */

	public int isEmailExists( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT user_rel_key FROM " + tableName_ + " WHERE user_rel_key = '"
		        + userKey.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "ActivationPendingTable : isEmailExists :: SQL Error "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
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
	 * Method : delete( )
	 * 
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the activation pending details values from
	 * activation_pending table
	 */

	public int delete( UserProfileKey userKey )
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

			return -1;

		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "ActivationPendingTable : delete :: SQL Error " + ex;

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

}

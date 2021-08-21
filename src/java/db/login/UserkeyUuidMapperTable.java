package db.login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ErrorLogger;
import core.login.UserkeyUuidMapperData;
import core.regn.UserProfileKey;
import db.utils.DBConnect;
import utils.ErrorMaster;

/*
 * Class  : UserkeyUuidMapperTable
 * 
 * Purpose: It is used to query the userkey_uuid_mapper table.
 * 
 */

public class UserkeyUuidMapperTable
{
private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	/*
	 * Method : UserkeyUuidMapperTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used intialize the table name
	 */

	public UserkeyUuidMapperTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "userkey_uuid_mapper";
	}

	/*
	 * Method : insertUserRelKeyUuid( )
	 * 
	 * Input : UserKeyUuiMapperData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to insert the UserKeyUuiMapperData values into
	 * userkey_uuid_mapper table
	 */

	public int insert( UserkeyUuidMapperData userkeyUuidMapperData )
	{

		UserkeyUuidMapperRecord userkeyUuidMapperRec = createUserkeyUuidMapperRecord( userkeyUuidMapperData );

		String query = formInsertQuery( userkeyUuidMapperRec );

		errorMaster_.insert( "Query = " + query );

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
			        .logMsg( "UserKeyUuidMapperTable : insertUserRelKeyUuid :: SQL Error "
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
	 * Method : updateUserkeyUuidMapper( )
	 * 
	 * Input : UserKeyUuiMapperData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to update the UserKeyUuiMapperData values into
	 * userkey_uuid_mapper table
	 */

	public int update( UserkeyUuidMapperData userkeyUuidMapperData )
	{
		UserkeyUuidMapperRecord userkeyUuidMapperRec = createUserkeyUuidMapperRecord( userkeyUuidMapperData );

		String query = "UPDATE " + tableName_;

		query = query + " SET uuid = '" + userkeyUuidMapperRec.uuid_;
		query = query + "' WHERE user_rel_key ='" + userkeyUuidMapperRec.userRelKey_ + "'";

		errorMaster_.insert( "Query = " + query );

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
			        .logMsg( "UserKeyUuidMapperTable : updateUserkeyUuidMapper :: SQL Error "
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
	 * Method : getEmailForUuid( )
	 * 
	 * Input : String uuid, UserKeyUuidMapperData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to retrieve email from userkey_uuid_mapper table for
	 * given uuid
	 */

	public int getEmailId( String uuid, UserkeyUuidMapperData userkeyUuidMapperData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_ + " WHERE uuid = '" + uuid + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				userkeyUuidMapperData.userRelKey_ = rs.getString( "user_rel_key" );
				userkeyUuidMapperData.uuid_ = rs.getString( "uuid" );

				return 0; // Success
			}

			return -1; // No record exists
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );

			String errorMessage = "UserKeyUuidMapperTable : getUuid :: SQL Error " + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // SqlException
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
	 * Method : deleteUserKeyMapper( )
	 * 
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to delete the UserKeyUuidMapperData values from
	 * userkey_uuid_mapper table
	 */

	public int deleteUserKeyMapper( UserProfileKey userKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE user_rel_key = '" + userKey.email_
		        + "'";

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
			String errorMessage = "UserKeyUuidMapperTable : deleteUserKeyMapper :: SQL Error "
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
	 * Method : isMapperRecExists( )
	 * 
	 * Input : UserProfileKey userKey
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to check whether there exists mapper rec for given
	 * UserProfileKey
	 */

	public int isMapperRecExists( UserProfileKey userKey )
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
			String errorMessage = "UserKeyUuidMapperTable : isMapperRecExists :: SQL Error "
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
	 * Method: find
	 * 
	 * Input: UserProfileKey obj
	 * 
	 * Return: string
	 * 
	 * Purpose: This method fetches the user profile uuid for given company key
	 */
	
	public String getUuid( UserProfileKey profileKey ) 
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		String result = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		query = "SELECT uuid FROM " + tableName_;
		query = query + " WHERE user_rel_key = '" + profileKey.email_ + "'" ;
		
		errorMaster_.insert( "Query=" + query );
		
		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );
			
			if( rs.next( ) )
			{
				result = rs.getString( "uuid" );
			}
			
			return result; // email not exist for given company key
		}

		catch( SQLException ex ) // SqlException
		{
			errLogger.logMsg( "Exception::UserkeyUuidMapperTable.getUuid( UserProfileKey ) - "+ex );

			return ""; 
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserkeyUuidMapperTable.getUuid( UserProfileKey ) - "+ex );

			return ""; 
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
	 * Method : createUserkeyUuidMapperRecord( )
	 * 
	 * Input : UserkeyUuidMapperData obj
	 * 
	 * Return : UserkeyUuidMapperRecord
	 * 
	 * Purpose: It converts UserkeyUuidMapperData to UserkeyUuidMapperRecord
	 */

	private UserkeyUuidMapperRecord createUserkeyUuidMapperRecord(
	        UserkeyUuidMapperData userkeyUuidMapperData )
	{
		// Form the record using the regnDataObj and return it
		UserkeyUuidMapperRecord userkeyUuidMapperRec = new UserkeyUuidMapperRecord( );

		// set the values
		userkeyUuidMapperRec.userRelKey_ = userkeyUuidMapperData.userRelKey_;
		userkeyUuidMapperRec.uuid_ = userkeyUuidMapperData.uuid_;

		return userkeyUuidMapperRec;

	}

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : UserkeyUuidMapperRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using UserkeyUuidMapperRecord and
	 * returns as string
	 */

	private String formInsertQuery( UserkeyUuidMapperRecord userkeyUuidMapperRec )
	{
		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(user_rel_key, uuid)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + userkeyUuidMapperRec.userRelKey_ + "', ";
		insertQuery = insertQuery + "'" + userkeyUuidMapperRec.uuid_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}

}

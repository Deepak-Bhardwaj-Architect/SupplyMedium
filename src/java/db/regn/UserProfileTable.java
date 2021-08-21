package db.regn;

import core.regn.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import db.utils.DBConnect;

import utils.ErrorLogger;
import utils.ErrorMaster;
import utils.StringHolder;
import utils.UserStatus;
import utils.UserType;

public class UserProfileTable
{
private static ErrorMaster errorMaster_ = null;


	private String tableName_;

	public UserProfileTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.tableName_ = "user_profile";
	}

	public int insertUserProfile( UserProfileData userProfileData )
	{
		// Here the regnDataObj will come in as the parameter,

		// Create UserProfileRecord using regnDataObj
		UserProfileRecord userProfileRecObj = createUserProfileRecord( userProfileData );

		// Form the insertQuery using the UserProfileRecord object.
		String query = formInsertQuery( userProfileRecObj );

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
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "UserProfileTable : insertUserProfile :: SQL Error " + ex );

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

	public boolean isEmailExist( String email )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		boolean result = false;

		query = "SELECT 1 FROM " + tableName_ + " WHERE email = '" + email + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );
			ResultSet rs = stmt.executeQuery( query );

			if( !rs.next( ) )
			{
				// ResultSet is empty

				result = false; // Phone no not exists in DB. So it is valid
				                // phone number
			}
			else
			{
				result = true; // Phone already exists in DB
			}

			return result;
		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "UserProfileTable : isEmailExist :: SQL Error " + ex );

			return result;
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

	public int deleteUserProfile( String email )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;
		int deleteResult = 0;

		query = "DELETE FROM " + tableName_ + " WHERE email = '" + email
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
			String errorMessage = "UserProfileTable : deleteUserProfile :: SQL Error " + ex;

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

	public int getNoOfRegisteredUserForCompany( CompanyRegnKey companyRegnKey )
	{
		int userCount = 0;

		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT COUNT(*) as count FROM " + tableName_ + " WHERE regn_key = '"
		        + companyRegnKey.companyPhoneNo_
		        + "' AND user_status = 'Active' OR user_status = 'Blocked'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				userCount = rs.getInt( "count" );
			}

			return userCount;

		}
		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserProfileTable : getNoOfRegisteredUserForCompany :: SQL Error "
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
	 * Method: update
	 * 
	 * Input: UserProfileData
	 * 
	 * Return: int
	 * 
	 * Purpose: This method udpates the user profile details through user acct mgmt
	 */

	public int update( UserProfileData profileData )
	{		
		Statement stmt = null;
		Connection con = null;
		String query = null;
		
		UserProfileRecord record = createUserProfileRecord( profileData );
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		query = "UPDATE " + tableName_;
		
		query = query + " SET profile_picture_path = '" + record.profilePicture_ + "'";
		
		query = query + " WHERE user_profile_key ='" + record.userProfileKey_+ "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			int updateResult = 0;

			updateResult = stmt.executeUpdate( query );

			if( updateResult > 0 )
			{
				return 0; // Success
			}

			else
			{
				return -1; // No record for user profile key
			}

		}

		catch( SQLException ex ) // Sql Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable.update() - "+ex );

			return -2; 
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::UserProfileTable.update() - "+ex );

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
	
	private UserProfileRecord createUserProfileRecord( UserProfileData userProfileData )
	{
		// Form the record using the regnDataObj and return it
		
		UserProfileRecord userProfileRecObj = new UserProfileRecord( );

		// set the values
		userProfileRecObj.firstName_ = userProfileData.firstName_;
		userProfileRecObj.lastName_ = userProfileData.lastName_;
		userProfileRecObj.title_ = userProfileData.title_;

		userProfileRecObj.department_ = userProfileData.department_;
		userProfileRecObj.userRole_ = userProfileData.managerSupervisor_;
		userProfileRecObj.phoneNo_ = userProfileData.phoneNo_;

		userProfileRecObj.cellNo_ = userProfileData.cellNo_;
		userProfileRecObj.faxNo_ = userProfileData.faxNo_;
		userProfileRecObj.emailId_ = userProfileData.emailId_;
	
		if( userProfileData.companyRegnKey_ != null )
		{
			userProfileRecObj.regnKey_ = userProfileData.companyRegnKey_.companyPhoneNo_;
		}
		
		userProfileRecObj.userProfileKey_ = userProfileData.userProfileKey_.email_;
		userProfileRecObj.userType_ = userProfileData.userType_;

		userProfileRecObj.userStatus_ = userProfileData.userStatus_;
		userProfileRecObj.createdDate_ = userProfileData.createdDate_;
		userProfileRecObj.profilePicture_ = userProfileData.profilePicture_;

		return userProfileRecObj;

	}

	private String formInsertQuery( UserProfileRecord userProfileRecObj )
	{

		// form the query
		String insertQuery = null;
		String insertValues = null;

		insertValues = "(company_id, first_name, last_name, title, ";
		insertValues = insertValues + "department, user_role, phone, ";
		insertValues = insertValues
		        + "cell, fax, email, user_type,regn_key,user_profile_key,user_status)";

		insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + userProfileRecObj.companyId_ + ", ";
		insertQuery = insertQuery + "'" + userProfileRecObj.firstName_ + "', ";
		insertQuery = insertQuery + "'" + userProfileRecObj.lastName_ + "', ";

		insertQuery = insertQuery + "'" + userProfileRecObj.title_ + "', ";
		insertQuery = insertQuery + "'" + userProfileRecObj.department_ + "', ";
		insertQuery = insertQuery + "'" + userProfileRecObj.userRole_ + "', ";

		insertQuery = insertQuery + "'" + userProfileRecObj.phoneNo_ + "', ";
		insertQuery = insertQuery + "'" + userProfileRecObj.cellNo_ + "', ";
		insertQuery = insertQuery + "'" + userProfileRecObj.faxNo_ + "', ";

		insertQuery = insertQuery + "'" + userProfileRecObj.emailId_ + "', ";
		insertQuery = insertQuery + "'" + userProfileRecObj.userType_ + "', ";
		insertQuery = insertQuery + "'" + userProfileRecObj.regnKey_ + "', ";

		insertQuery = insertQuery + "'" + userProfileRecObj.userProfileKey_ + "',";
		insertQuery = insertQuery + "'" + userProfileRecObj.userStatus_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
	}

	public int getRegnKey( UserProfileKey userProfileKey, CompanyRegnKey regnKey )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		// query = "SELECT user_status FROM " + tableName_
		// + " WHERE user_profile_key = '" + userProfileKey +"'";

		query = "SELECT regn_key FROM " + tableName_ + " WHERE user_profile_key = '"
		        + userProfileKey.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				regnKey.companyPhoneNo_ = rs.getString( "regn_key" );

				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserProfileTable : getUserprofileForEmail :: SQL Error "
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

	public int getUserprofile( String userProfileKey, UserProfileData profileData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT user_status, regn_key FROM " + tableName_
		        + " WHERE user_profile_key = '" + userProfileKey + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				CompanyRegnKey regnKey = new CompanyRegnKey( );

				profileData.userStatus_ = rs.getString( "user_status" );

				regnKey.companyPhoneNo_ = rs.getString( "regn_key" );

				profileData.companyRegnKey_ = regnKey;

				return 0; // Success
			}

			return -1; // No record for user profile key

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserProfileTable : getUserprofileForEmail :: SQL Error "
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

	public int getUserProfile( UserProfileKey userProfileKey, UserProfileData profileData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT * FROM " + tableName_
		        + " WHERE user_profile_key = '" + userProfileKey.email_ + "'";

		errorMaster_.insert( "==>>Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				profileData.userProfileKey_ = userProfileKey;
				
				profileData.firstName_ = rs.getString( "first_name" );
				profileData.lastName_ = rs.getString( "last_name" );
				profileData.userType_ = rs.getString( "user_type" );
				
				profileData.emailId_ = rs.getString( "email" );
				profileData.profilePicture_ = rs.getString( "profile_picture_path" );
				
				profileData.department_ = rs.getString( "department" );
				profileData.managerSupervisor_ = rs.getString( "user_role" );
				profileData.phoneNo_ = rs.getString( "phone" );
				
				profileData.cellNo_ = rs.getString( "cell" );
				profileData.faxNo_ = rs.getString( "fax" );
				
				CompanyRegnKey regnKey = new CompanyRegnKey( );
				regnKey.companyPhoneNo_ = rs.getString( "regn_key" );
				profileData.companyRegnKey_ = regnKey;
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserProfileTable : getUserprofileForEmail :: SQL Error "
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
	 * Method : updateStatus( )
	 * 
	 * Input : UserProfileKey key
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the user profile status into
	 * user_profile table
	 */

	public int updateStatus( UserProfileKey key )
	{
		UserProfileRecord record = new UserProfileRecord( );
		
		record.emailId_	= key.email_;
		
		String query = "UPDATE " + tableName_;
		query = query + " SET user_status = '"
		        + record.userStatus_ + "'";
		query = query + " WHERE user_profile_key ='" + record.emailId_ + "'";

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
			errLogger_.logMsg( "UserProfileTable : updateStatus :: SQL Error "
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
	 * Method : getAllUsers( )
	 * 
	 * Input : CompanyRegn key, List<UserProfileData> obj(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the users from user_profile table
	 * for selected company
	 */
	
	public int getAllUsers( CompanyRegnKey key, List<UserProfileData> profileDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT first_name, last_name, user_status, email FROM " + tableName_
		        + "  where regn_key = '" + key.companyPhoneNo_ + "'";
		query = query + " AND user_type != 'Admin'";
                
                //System.out.print("query------------------------->"+query);
 
		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				UserProfileData data = new UserProfileData( );
				
				UserProfileKey userProfileKey = new UserProfileKey( );
				userProfileKey.email_ = rs.getString( "email" );
				
				data.userProfileKey_ = userProfileKey;
				data.firstName_ = rs.getString( "first_name" );
				data.lastName_ = rs.getString( "last_name" );
				data.userStatus_ = rs.getString( "user_status" );
				data.emailId_	= rs.getString( "email" );
				
				profileDataList.add( data );
				
				data	= null;
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserProfileTable : getAllUsers :: SQL Error "
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
	 * Method : get( )
	 * 
	 * Input : List<UserProfileData> obj(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the users from user_profile table
	 * 
	 */
	
	public int get( Map<UserProfileKey, UserProfileData> profileMap )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT first_name, last_name, user_status, email,profile_picture_path FROM " + tableName_;
		        
		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				UserProfileData data = new UserProfileData( );
				
				UserProfileKey userProfileKey = new UserProfileKey( );
				userProfileKey.email_ = rs.getString( "email" );
				
				data.userProfileKey_ 	= userProfileKey;
				data.firstName_ 		= rs.getString( "first_name" );
				data.lastName_ 			= rs.getString( "last_name" );
				data.userStatus_ 		= rs.getString( "user_status" );
				data.emailId_			= rs.getString( "email" );
				data.profilePicture_	= rs.getString( "profile_picture_path" );
				
				
				profileMap.put( userProfileKey, data );
				
				data	= null;
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserProfileTable : get :: SQL Error "
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
	 * Method : getActiveUsers( )
	 * 
	 * Input : CompanyRegn key, List<UserProfileData> obj(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the active users from user_profile table
	 * for selected company
	 */
	
	public int getActiveUsers( CompanyRegnKey key, List<UserProfileKey> profileKeyList )
	{
		Statement stmt 	= null;
		Connection con 	= null;
		String query 		= null;

		query = "SELECT  email FROM " + tableName_ + " WHERE regn_key = '" + key.companyPhoneNo_ + "'";
		
		query = query + " AND user_type != '" + UserType.type.ADMIN.getValue( ) + "'" +
								  " AND user_status = '" + UserStatus.status.ACTIVE.getValue( ) + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{	
				UserProfileKey userProfileKey = new UserProfileKey( );
				userProfileKey.email_ = rs.getString( "email" );
				
				profileKeyList.add( userProfileKey );
				
				userProfileKey	= null;
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserProfileTable.getActiveUsers( ) - "
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
	 * Method : getTransactionUsers( )
	 * 
	 * Input : CompanyRegn key, List<UserProfileData> obj(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the active users from user_profile table
	 * for selected company
	 */
	
	public int getTransactionUsers( CompanyRegnKey key, List<UserProfileData> userProfiles )
	{
		Statement stmt 	= null;
		Connection con 	= null;
		String query 		= null;
	
		query = "SELECT  first_name,last_name,email FROM " + tableName_ + " WHERE regn_key = '" + key.companyPhoneNo_ + "'";
		
		query = query + " AND ( user_type = '" + UserType.type.ADMIN.getValue( ) + "'" +
								  " OR user_type = '" + UserType.type.TRANSACTIONUSER.getValue( ) + "' )";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );
			

			while( rs.next( ) )
			{	
				UserProfileData profileData 	= new UserProfileData( );
				
				UserProfileKey userProfileKey	= new UserProfileKey( );
				userProfileKey.email_ 			= rs.getString( "email" );
				
				profileData.userProfileKey_ 	= userProfileKey;
				
				userProfileKey = null;
				
				profileData.firstName_ 			= rs.getString( "first_name" );
				profileData.lastName_ 			= rs.getString( "last_name" );
				
				userProfiles.add( profileData );
				
				profileData	= null;
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserProfileTable.getActiveUsers( ) - "
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
	 * Method : getCreatedDate( )
	 * 
	 * Input :  UserProfileData obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get user profile created date of user
	 */
	
	public int getCreatedDate( UserProfileData profileData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT created_date FROM " + tableName_
		        + " WHERE user_profile_key = '" + profileData.emailId_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				profileData.createdDate_ = rs.getTimestamp( "created_date" );
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserProfileTable : getCreatedDate :: SQL Error "
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
	 * Method : updateStatus( )
	 * 
	 * Input : UserProfileKey key, string status
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to udpate the user profile status into
	 * user_profile table
	 */

	public int updateStatus( UserProfileKey key, String status )
	{
		UserProfileRecord record = new UserProfileRecord( );
		
		record.emailId_	= key.email_;
		
		String query = "UPDATE " + tableName_;
		query = query + " SET user_status = '"
		        + status + "'";
		query = query + " WHERE user_profile_key ='" + record.emailId_ + "'";

		errorMaster_.insert( "Query = " + query );

		Statement stmt = null;
		Connection con = null;

		int insertResult = 0;

		try
		{
			con = DBConnect.instance( ).getConnection( );
			stmt = con.createStatement( );
			insertResult = stmt.executeUpdate( query );
			
			errorMaster_.insert( "update result="+insertResult );

			stmt.executeQuery( "Commit" );

			if( insertResult > 0 ) 
				return 0;

			return -1;

		}

		catch( SQLException ex )
		{
			errorMaster_.insert( "Exception=" + ex.getMessage( ) );
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "UserProfileTable : updateStatus :: SQL Error "
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
	 * Method : getCreatedDate( )
	 * 
	 * Input :  UserProfileData obj, String status (as ref)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get current status of user
	 */
	
	public int getStatus( UserProfileKey profileKey, StringBuilder status )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT user_status FROM " + tableName_
		        + " WHERE user_profile_key = '" + profileKey.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				String str = rs.getString( "user_status" );
				status = status.append( str );
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserProfileTable:getStatus - "
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
	 * Method : find( )
	 * 
	 * Input : CompanyRegn key, List<UserProfileData> obj(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the users from user_profile table
	 * for selected company
	 */
	
	public int find( CompanyRegnKey key, List<UserProfileData> profileDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT first_name, last_name, user_status, email FROM " + tableName_
		        + " WHERE regn_key = '" + key.companyPhoneNo_ + "' AND user_type != 'Admin' AND user_type != 'Intranet User'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				UserProfileData data = new UserProfileData( );
				
				UserProfileKey userProfileKey = new UserProfileKey( );
				userProfileKey.email_ = rs.getString( "email" );
				
				data.userProfileKey_ = userProfileKey;
				data.firstName_ = rs.getString( "first_name" );
				data.lastName_ = rs.getString( "last_name" );
				data.userStatus_ = rs.getString( "user_status" );
				data.emailId_	= rs.getString( "email" );
				
				profileDataList.add( data );
				
				data	= null;
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserProfileTable : getAllUsers :: SQL Error "
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
	 * Method : find( )
	 * 
	 * Input : UserProfileKey key, StringHolder contactName(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get first name and last name of the use
	 */
	
	public int find( UserProfileKey key, StringHolder contactName )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT first_name, last_name FROM " + tableName_
		        + " WHERE user_profile_key = '" + key.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				contactName.value = rs.getString( "first_name" ) + " " + 
									rs.getString( "last_name" );
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserProfileTable.find( UserProfileKey, StringHolder ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserProfileTable.find( UserProfileKey, StringHolder ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -3; // Sql error
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
	 * Method : find( )
	 * 
	 * Input : UserProfileKey key, StringHolder contactName(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get first name and last name of the use
	 */
	
	public int find( UserProfileKey key, UserProfileData profileData )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		query = "SELECT first_name, last_name, department, regn_key, cell, fax, email,profile_picture_path FROM " + tableName_
		        + " WHERE user_profile_key = '" + key.email_ + "'";

		errorMaster_.insert( "Query=" + query );

		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			if( rs.next( ) )
			{
				profileData.firstName_		= rs.getString( "first_name" );
				profileData.lastName_		= rs.getString( "last_name" );
				profileData.department_ 	= rs.getString( "department" );
				profileData.phoneNo_		= rs.getString( "regn_key" );
				profileData.cellNo_			= rs.getString( "cell" );
				profileData.faxNo_			= rs.getString( "fax" );
				profileData.emailId_		= rs.getString( "email" );
				profileData.profilePicture_ = rs.getString( "profile_picture_path" );
				
				CompanyRegnKey regnKey = new CompanyRegnKey();
				regnKey.companyPhoneNo_ = profileData.phoneNo_;
				profileData.companyRegnKey_ = regnKey;
			} 

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserProfileTable.find( UserProfileKey, UserProfileData ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -2; // Sql error
		}
		
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "Exception::UserProfileTable.find( UserProfileKey, UserProfileData ) - "
			        + ex;

			errLogger_.logMsg( errorMessage );

			return -3; // Sql error
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
	 * Method : find( )
	 * 
	 * Input : search text, List<UserProfileData> obj(as reference)
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the users from user_profile table
	 * for given search text. Search text may be firstname,lastname or email id
	 */
	
	public int find( String searchText, List<UserProfileData> profileDataList )
	{
		Statement stmt = null;
		Connection con = null;
		String query = null;

		
		query = "SELECT regn_key,first_name, last_name, user_type,email, profile_picture_path FROM " + tableName_
		        + " WHERE email LIKE '%" + searchText + "%' OR first_name LIKE '%"
				+searchText+"%' OR last_name LIKE '%"+searchText+"%'";

		errorMaster_.insert( "Query=" + query );

	
		
		try
		{
			con = DBConnect.instance( ).getConnection( );

			stmt = con.createStatement( );

			ResultSet rs = stmt.executeQuery( query );

			while( rs.next( ) )
			{
				UserProfileData profileData = new UserProfileData( );
				
				UserProfileKey userKey = new UserProfileKey( );
				userKey.email_ = rs.getString( "email" );
				profileData.userProfileKey_ = userKey;
				userKey = null;
				
				profileData.firstName_ = rs.getString( "first_name" );
				profileData.lastName_ = rs.getString( "last_name" );
				profileData.userType_ = rs.getString( "user_type" );
				
				profileData.emailId_ = rs.getString( "email" );
				profileData.profilePicture_ = rs.getString( "profile_picture_path" );
				
				CompanyRegnKey regnKey = new CompanyRegnKey( );
				regnKey.companyPhoneNo_ = rs.getString( "regn_key" );
				profileData.companyRegnKey_ = regnKey;
				
				profileDataList.add( profileData );
				
				profileData	= null;
			}

			return 0; // Success

		}

		catch( SQLException ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			String errorMessage = "UserProfileTable : find :: SQL Error "
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
	
}

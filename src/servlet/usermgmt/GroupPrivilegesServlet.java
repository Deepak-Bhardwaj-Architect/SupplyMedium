package servlet.usermgmt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import com.google.gson.Gson;


import core.login.SessionData;
import core.privilege.UsergroupPrivilegesData;
import core.usermgmt.GroupKey;
import ctrl.usermgmt.UserMgmtController;

/**
 * Servlet implementation class GroupPrivilegesServlet
 */
@WebServlet("/GroupPrivilegesServlet")
public class GroupPrivilegesServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String currentGroupName_;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupPrivilegesServlet()
	{
		super( );
		// TODO Auto-generated constructor stub
		
		currentGroupName_ = new String( );
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from browser. Then parse the
	 * HttpServletRequest and it send the group privilege management 
	 * (Fetch group privileges,update group privileges) result to jsp 
	 * using core classes. 
	 * 
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String msg = "Info::GroupPrivilegesServlet.doPost() - Request for :"
						+request.getParameter( "RequestType" ) + " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													
			errLogger.logMsg( msg );
			
			parseRequest( request );
			
			UserMgmtController userMgmtController = new UserMgmtController( );
			
			UsergroupPrivilegesData privilegesData = new UsergroupPrivilegesData( );
			
			int responseCode = userMgmtController.groupPrivileges( request, privilegesData );
			
			userMgmtController = null;

			String jsonStr = composeResponseJSON( responseCode, privilegesData );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::GroupPrivilegesServlet.doPost() - "+e;

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
		
	}
	
	/*
	 * Method : composeResponseJSON( ) 
	 * 
	 * Input : int responseCode, deptPrivilegesdata objetc
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON(
	        int responseCode, UsergroupPrivilegesData privilegesData )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		System.out.println( "Response Code="+responseCode );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 950 || responseCode == 960  )
		{
			jsonMap.put( "result", "success" );
			
			String msg = "Info::GroupPrivilegesServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			if( responseCode == 960 ) 
			{
				String[] valueArr = {currentGroupName_};
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
			}
			else 
			{
				if( privilegesData.addNewUser_ )
				{
					jsonMap.put( "AddUsers", "1" );
				}
				else
				{
					jsonMap.put( "AddUsers", "0" );
				}

				if( privilegesData.deleteUser_ )
				{
					jsonMap.put( "DeleteUsers", "1" );
				}
				else
				{
					jsonMap.put( "DeleteUsers", "0" );
				}

				if( privilegesData.uploadDoc_ )
				{
					jsonMap.put( "UploadDoc", "1" );
				}
				else
				{
					jsonMap.put( "UploadDoc", "0" );
				}

				if( privilegesData.deleteDoc_ )
				{
					jsonMap.put( "DeleteDoc", "1" );
				}
				else
				{
					jsonMap.put( "DeleteDoc", "0" );
				}

				if( privilegesData.addBuySupplier_ )
				{
					jsonMap.put( "AddBuyers", "1" );
				}
				else
				{
					jsonMap.put( "AddBuyers", "0" );
				}

				if( privilegesData.deleteBuySupplier_ )
				{
					jsonMap.put( "DeleteBuyers", "1" );
				}
				else
				{
					jsonMap.put( "DeleteBuyers", "0" );
				}

				if( privilegesData.accessUserMgmt_ )
				{
					jsonMap.put( "AccessUserMgmt", "1" );
				}
				else
				{
					jsonMap.put( "AccessUserMgmt", "0" );
				}

				if( privilegesData.postAnnouncement_ )
				{
					jsonMap.put( "PostAnnouncement", "1" );
				}
				else
				{
					jsonMap.put( "PostAnnouncement", "0" );
				}

				if( privilegesData.deleteAnnouncement_ )
				{
					jsonMap.put( "DeleteAnnouncement", "1" );
				}
				else
				{
					jsonMap.put( "DeleteAnnouncement", "0" );
				}

				if( privilegesData.rateBuySupplier_ )
				{
					jsonMap.put( "Rate", "1" );
				}
				else
				{
					jsonMap.put( "Rate", "0" );
				}

				if( privilegesData.createUserGroup_ )
				{
					jsonMap.put( "CreateGroup", "1" );
				}
				else
				{
					jsonMap.put( "CreateGroup", "0" );
				}

				if( privilegesData.deleteUserGroup_ )
				{
					jsonMap.put( "DeleteGroup", "1" );
				}
				else
				{
					jsonMap.put( "DeleteGroup", "0" );
				}
				if( privilegesData.applyThemes_ )
				{
					jsonMap.put( "ApplyTheme", "1" );
				}
				else
				{
					jsonMap.put( "ApplyTheme", "0" );
				}

				if( privilegesData.manageFolder_ )
				{
					jsonMap.put( "ManageFolders", "1" );
				}
				else
				{
					jsonMap.put( "ManageFolders", "0" );
				}

			}
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );
			
			String msg = "Info::GroupPrivilegesServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			if( responseCode == 951 )
			{
				String[] valueArr = {currentGroupName_};
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
			}
			
			else 
			{
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );	
			}
		}
		
		jsonStr = new Gson( ).  toJson( jsonMap );
		
		System.out.println( "json str="+jsonStr );

		return jsonStr;
	}
	
	
	
	/*To parse the request and find the group name*/
	
	private void parseRequest( HttpServletRequest request )
	{
		String groupKeyStr = request.getParameter( "GroupKey" );
		
		GroupKey groupKey = new GroupKey( );
		
		if( groupKeyStr != null )
		{
			 String [] strArr = groupKeyStr.split( ":" );
	            
	            if( strArr.length > 1)
	            {
	            	String groupName = strArr[1];
	            	
	            	groupKey.groupName_ = groupName;
	            	
	            	currentGroupName_ = groupKey.groupName_;
	            }
		}
	}
}

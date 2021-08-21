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
import core.usermgmt.GroupKey;

import ctrl.usermgmt.UserMgmtController;
import utils.ErrorMaster;

/**
 * Servlet implementation class GroupMgmtServlet
 */
@WebServlet("/GroupMgmtServlet")
public class GroupMgmtServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String currentGroupName_;
	private String newGroupName_;
        private static ErrorMaster errorMaster_ = null;



	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupMgmtServlet()
	{
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		currentGroupName_ = new String( );
		newGroupName_ = new String( );
		
		// TODO Auto-generated constructor stub
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	
	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from browser. Then parse the
	 * HttpServletRequest and it send the group management (New group creation,
	 * Rename group and group delete) result to jsp using core classes. 
	 * 
	 */
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String msg = "Info::GroupMgmtServlet.doPost() -  Request for "+
						request.getParameter( "RequestType" ) + " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			parseRequest( request );
			
			UserMgmtController userMgmtController = new UserMgmtController( );
			
			int responseCode = userMgmtController.manageGroup( request );
			
			userMgmtController = null;

			String jsonStr = composeResponseJSON( responseCode );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::GroupMgmtServlet.doPost() - "+e;

			errLogger.logMsg( msg );
			
			Map<String, String> jsonMap = new HashMap<String, String>( );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			jsonMap = null;
			
			response.getWriter( ).write( jsonStr );
        }
	}
	
	/*
	 * Method : composeResponseJSON( ) 
	 * 
	 * Input : int responseCode
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON( int responseCode )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 1000 || responseCode == 1050 || responseCode == 1100 )
		{
			jsonMap.put( "result", "success" );
			
			String msg = "Info::GroupFetchServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			if( responseCode == 1000 )
			{
				String[] valueArr = { newGroupName_ };
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
			}
			
			if( responseCode == 1050 )
			{
				String[] valueArr = {currentGroupName_, newGroupName_};
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
			}
			
			if( responseCode == 1100 )
			{
				String[] valueArr = { currentGroupName_ };
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
			}
		}
		else 
		{
			jsonMap.put( "result", "failed" );
			
			String msg = "Info::GroupFetchServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			if( responseCode == 1001 || responseCode == 1002 )
			{
				String[] valueArr = { newGroupName_ };
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
				
				jsonMap.put( "message", responseString );
			}
			
			else
			{
    			String[] valueArr = {currentGroupName_};
    
    			String responseString = ErrorCodeConfigReader.instance( ).get( responseCode,
    			        valueArr );
    
    			jsonMap.put( "message", responseString );
			}
				
		}
		
		jsonStr = new Gson( ).  toJson( jsonMap );
		
		jsonMap = null;
		
		errorMaster_.insert( "json str="+jsonStr );

		return jsonStr;
	}

	
	/*To parse the request and find the group name*/
	
	private void parseRequest( HttpServletRequest request )
	{
		String requestType = request.getParameter( "RequestType" );
		
		if( requestType.equals( "NewUserGroup" ) )
		{
			newGroupName_ = request.getParameter( "GroupName" );
		}
		
		if( requestType.equals( "UpdateUserGroup" ) || requestType.equals( "DeleteUserGroup" ))
		{
			if( requestType.equals( "UpdateUserGroup" ) )
			{
				newGroupName_ = request.getParameter( "GroupName" );
			}
			
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

}

package servlet.usermgmt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import core.regn.UserProfileData;
import core.usermgmt.GroupKey;

import ctrl.usermgmt.UserMgmtController;
import utils.ErrorMaster;

/**
 * Servlet implementation class UserGroupMapServlet
 */
@WebServlet("/UserGroupMapServlet")
public class UserGroupMapServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String currentGroupName_;
	private String currentUserName_;
	private String toasterDisAuxVerb_;
        private static ErrorMaster errorMaster_ = null;



	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserGroupMapServlet()
	{
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		currentGroupName_ = new String( );
		currentUserName_ = new String( );
		toasterDisAuxVerb_ = new String( );
		
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
	 * HttpServletRequest and it do the following operation
	 *	1.Assign users to Groups (AddUser)
	 *	2.Remove users from Groups (RemoveUser)
	 *	3.Fetch the Group users (FindUser)
	 *	4.Fetch the users which are not added in Groups (NonGroupUsers)
	 */
	
	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );		
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		int responseCode = 0;
		
		try
        {
			String msg = "Info::UserGroupMapServlet.doPost() - Request for :" 
						+ request.getParameter( "RequestType" ) 
						+ " for the company " +"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													
			errLogger.logMsg( msg );
			
			parseRequest( request );
			
			UserMgmtController userMgmtController = new UserMgmtController( );
			
			List<UserProfileData> userDataArr = new ArrayList<UserProfileData>( ); 
			
			responseCode = userMgmtController.mapUserGroup( request, userDataArr );
			
			errorMaster_.insert( "UserGroupMapServlet - ResponseCode: "+responseCode );
			
			userMgmtController = null;
			
			errorMaster_.insert( "userdataarr count="+userDataArr.size( ) );

			if( responseCode == 1150 || responseCode == 1170 || responseCode == 1250
					||responseCode == 1260  )
			{
				jsonMap.put( "result", "success" );
				
				msg = "Info::UserGroupMapServlet.doPost() - Request successful - Response code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
				if( responseCode == 1150 || responseCode == 1170) 
				{
					String valueArr[] = { currentUserName_, toasterDisAuxVerb_, currentGroupName_ };
					
					String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
					
					jsonMap.put( "message", responseString );
				}
				else 
				{
					for( UserProfileData userProfileData : userDataArr )
					{
						String userKey = userProfileData.userProfileKey_.email_;
								
						String userName = userProfileData.firstName_+" "+userProfileData.lastName_;
						
						jsonMap.put(userKey,userName );
						
					}
				}
				
			}
			else 
			{
				jsonMap.put( "result", "failed" );
				
				msg = "Info::UserGroupMapServlet.doPost() - Request failed - Error code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
				if( responseCode == 1151 || responseCode == 1171 )
				{
					String valueArr[] = { currentUserName_, currentGroupName_ };
					
					String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valueArr );
					
					jsonMap.put( "message", responseString );
		
				}
				else 
				{
					String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
					
					jsonMap.put( "message", responseString );	
				}
			}
			
			String jsonStr = new Gson( ).  toJson( jsonMap );
			
			errorMaster_.insert( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::UserGroupMapServlet.doPost() - "+e;

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }	
		
	}
	
	/*To get department current name and user name name*/
	private void parseRequest( HttpServletRequest request )
	{
		GroupKey groupKey = new GroupKey( );

		String deptKeyStr = request.getParameter( "GroupKey" );

		if( deptKeyStr != null )
		{
			String [ ] strArr = deptKeyStr.split( ":" );

			if( strArr.length > 1 )
			{
				String groupName = strArr[1];

				groupKey.groupName_ = groupName;
			}
		}

		currentGroupName_ = groupKey.groupName_;

		groupKey = null;
		
		if ( !request.getParameterMap().containsKey("UserNames[]") ) 
		{
			return;
		}
		
		String [] usersNameStrArr = request.getParameterValues( "UserNames[]" );
		
		if( usersNameStrArr == null )
		{
			return;
		}
		

		//This is configured like this inorder to show  that if one user is added,
		//user first name will be shown in toaster else, the below text will be shown
		
		if( usersNameStrArr.length > 1 )
		{
			currentUserName_ = "users";
			toasterDisAuxVerb_ = "have";
		}
		else 
		{
			currentUserName_ = "user '"+ usersNameStrArr[0]+ "'";
			toasterDisAuxVerb_ = "has";
		}
		
	}


}

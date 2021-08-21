package servlet.managefolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorLogger;


import core.dept.DeptData;
import core.login.SessionData;
import core.managefolder.GroupFolderData;
import core.managefolder.UserFolderData;
import core.regn.UserProfileData;
import core.usermgmt.GroupData;

import ctrl.managefolder.ManageFolderController;

/**
 * Servlet implementation class ManageFolderServlet
 */

@WebServlet("/ManageFolderServlet")
public class ManageFolderServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageFolderServlet()
	{
		super( ); 
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
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		String jsonStr =  "";
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String requestType = request.getParameter( "RequestType" );
			
			ManageFolderController mfCtrl = new ManageFolderController( );
			
			MFJSONComposer jsonComposer = new MFJSONComposer( );
			
			int responseCode = 0;
			
			if( requestType.equals( "ListAllGroups" ) )
			{
				List<GroupData> groupDataList = new ArrayList<GroupData>( );
				
				errLogger.logMsg( "Info::ManageFolderServlet.doPost() - Request for " + requestType + " for company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
				
				responseCode = mfCtrl.getGroups( request, groupDataList );
				
				jsonStr = jsonComposer.composeGroupListJSON( responseCode, groupDataList );
				
				groupDataList = null;
			}
			else if( requestType.equals( "ListNonGroupUsers" ) )
			{
				List<UserProfileData> profileDataList = new ArrayList<UserProfileData>( );
				
				errLogger.logMsg( "Info::ManageFolderServlet.doPost() - Request for " + requestType + " for company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
				
				responseCode = mfCtrl.getUsers( request, profileDataList );
				
				jsonStr = jsonComposer.composeUserListJSON( responseCode, profileDataList );
				
				profileDataList = null;
			}
			else if( requestType.equals( "ListNonGroupDepts" ) ||
					 requestType.equals( "ListNonUserDepts" ) )
			{
				List<DeptData> deptDataList = new ArrayList<DeptData>( );
				
				errLogger.logMsg( "Info::ManageFolderServlet.doPost() - Request for " + requestType + " for company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
				
				responseCode = mfCtrl.getDepts( request, deptDataList );
				
				System.out.println( "Response = "+ responseCode );
				
				jsonStr = jsonComposer.composeDeptListJSON( responseCode, deptDataList );
				
				deptDataList = null;
			}
			else if( requestType.equals( "GetGroupPrivileges" ) )
			{
				GroupFolderData groupFolderData = new GroupFolderData( );
				
				errLogger.logMsg( "Info::ManageFolderServlet.doPost() - Request for " + requestType + " for company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
				
				responseCode = mfCtrl.getGroupPri( request, groupFolderData );
				
				jsonStr = jsonComposer.composeGFDataJSON( responseCode, groupFolderData );
				
				groupFolderData = null;
			}
			else if( requestType.equals( "GetUserPrivileges" ) )
			{
				UserFolderData userFolderData = new UserFolderData( );
				
				errLogger.logMsg( "Info::ManageFolderServlet.doPost() - Request for " + requestType + " for company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
				
				responseCode = mfCtrl.getUserPri( request, userFolderData );
				
				jsonStr = jsonComposer.composeUFDataJSON( responseCode, userFolderData );
				
				userFolderData = null;
			}
			else if( requestType.equals( "UpdateGroupPrivileges" ) || 
					 requestType.equals( "UpdateUserPrivileges" ) )
			{
				errLogger.logMsg( "Info::ManageFolderServlet.doPost() - Request for " + requestType + " for company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
				
				responseCode = mfCtrl.updatePrivileges( request );
				
				jsonStr = jsonComposer.composeUpdateDataJSON( responseCode );
			}
			
			jsonComposer = null;
			
			mfCtrl = null;
			
			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::ManageFolderServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";		
			
			//jsonMap.put( "message", "Request failed. Try again." );
			
			jsonStr = jsonStr + "\"message\" : \" Request failed. Try again. \" }";
			
			//String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}

}

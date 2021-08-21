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
import core.usermgmt.GroupData;

import ctrl.usermgmt.UserMgmtController;

/**
 * Servlet implementation class GroupFetchServlet
 */
@WebServlet("/GroupFetchServlet")
public class GroupFetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupFetchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from browser. Fetch the all the group list
	 * associated with company. Then send the result to jsp.
	 * 
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
		
			String msg = "Info::GroupFetchServlet.doPost() -  Request for fetch all " +
						 "groups for the company <" + sessionData.companyRegnKeyStr_ + ">, " +
						 "<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													 
			errLogger.logMsg( msg );
			
			UserMgmtController userMgmtController = new UserMgmtController( );
			
			List< GroupData > groupDataArr = new ArrayList<GroupData>( );
						
			int responseCode = userMgmtController.getAllUserGroups( request, groupDataArr );
			
			userMgmtController = null;

			System.out.println( "Response code= "+groupDataArr.size( ) );
			
			if( responseCode == 1200  )
			{
				jsonMap.put( "result", "success" );
				
				msg = "Info::GroupFetchServlet.doPost() - Request successful - Response code - "
						+ responseCode + "\r\n\n\n";
						 							 
				errLogger.logMsg( msg );
				
				for( GroupData groupData : groupDataArr )
				{
					String groupKey = groupData.groupKey_.companyRegnKey_.companyPhoneNo_ + ":"
							+groupData.groupName_;
							
					String groupName = groupData.groupName_;
					
					System.out.println( "groupkey="+groupKey +",groupname="+groupName );
					
					jsonMap.put(groupKey,groupName );
					
					System.out.println( "iteration" +jsonMap.size( ));
				}
			}
			else 
			{
				msg = "Info::GroupFetchServlet.doPost() - Request failed - Error code - "
						+ responseCode + "\r\n\n\n";
						 							 
				errLogger.logMsg( msg );
				
				jsonMap.put( "result", "failed" );
				
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
			}
			
			String jsonStr = new Gson( ).  toJson( jsonMap );
			
			System.out.println( "json str="+jsonStr );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::GroupFetchServlet.doPost() - "+e;

			errLogger.logMsg( msg );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}

}

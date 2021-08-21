package servlet.useracctmgmt;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import core.login.SessionData;
import core.useracctmgmt.UserAcctMgmtData;

import ctrl.useracctmgmt.UserAcctMgmtController;
import utils.ErrorMaster;


/**
 * Servlet implementation class UserAcctMgmtServlet
 */
@WebServlet("/UserAcctMgmtServlet")
public class UserAcctMgmtServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String userName_;
        private static ErrorMaster errorMaster_ = null;



	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAcctMgmtServlet()
	{
		super( );
if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		userName_ = new String( );
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		
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
		
		userName_ = sessionData.firstName_ + " " + sessionData.lastName_;
		
		errorMaster_.insert( userName_ );
		
		response.setContentType( "application/json" );
		
		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String msg = "Info::UserAcctMgmtServlet.doPost() -  Request for "
						  + request.getParameter( "RequestType" ) + " for the company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			UserAcctMgmtController userAcctMgmtCtrl = new UserAcctMgmtController( );
			
			UserAcctMgmtData userAcctMgmtData = new UserAcctMgmtData( );
			
			int responseCode = userAcctMgmtCtrl.manageUserAccount( request, userAcctMgmtData );
			
			String jsonStr = composeResponseJSON( responseCode,userAcctMgmtData );

			response.getWriter( ).write( jsonStr );
			
			userAcctMgmtCtrl = null;
        }
        catch( Exception e )
        {
        	String msg = "Exception::UserAcctMgmt.doPost() - "+e+" \r\n\n\n";

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
	 * Input : int responseCode, UserAcctMgmtData object
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON(
	        int responseCode, UserAcctMgmtData userAcctMgmtData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String jsonStr = "";
		
		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 2500 || responseCode == 2550 ||
			responseCode == 2560 || responseCode == 2610 || 
			responseCode == 2620 || responseCode == 2660 || 
			responseCode == 2670  )
		{
			
			jsonMap.put( "result", "success" );
			
			String msg = "Info::UserAcctMgmtServlet.doPost() -  Request successful - Response code - "+responseCode+"\r\n\n\n";
			errLogger.logMsg( msg );
			
			if( responseCode == 2500 || responseCode == 2550 ||
				responseCode == 2610 || responseCode == 2660 ) 
			{
				String[] valArr = {userName_};
				
				String responseString = ErrorCodeConfigReader.instance( ).get( responseCode, valArr );
				errorMaster_.insert( responseString );
				jsonMap.put( "message", responseString );
			}
			else if( responseCode == 2560 )
			{	
				jsonMap.put( "ProfilePicPath", userAcctMgmtData.userProfileData_.profilePicture_ );
			}
			else if( responseCode == 2620 )
			{	
				jsonMap.put( "NonWorkingHoursFlag", String.valueOf( userAcctMgmtData.notifySettingsData_.nonWorkingHoursFlag_ ) );
				
				jsonMap.put( "NotifyEmail", String.valueOf( userAcctMgmtData.notifySettingsData_.notifyEmail_ ) );
				
				jsonMap.put( "NotifyMobile", String.valueOf( userAcctMgmtData.notifySettingsData_.notifyMobile_ ) );
				
				jsonMap.put( "NotifyNonWhMode", String.valueOf( userAcctMgmtData.notifySettingsData_.notifyNonWhMode_ ) );
				
				jsonMap.put( "NotifyStopFlag", String.valueOf( userAcctMgmtData.notifySettingsData_.notifyStopFlag_  ) );
				
				SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy");

	            String fromTime = dateFormat.format( userAcctMgmtData.notifySettingsData_.notifyStopFromTime_ );
	            
	            String toTime	= dateFormat.format( userAcctMgmtData.notifySettingsData_.notifyStopToTime_ );
				
				jsonMap.put( "NotifyStopFromTime", String.valueOf( fromTime  ) );
				
				jsonMap.put( "NotifyStopToTime", String.valueOf( toTime ) );
				
				jsonMap.put( "NotifyWhMode", String.valueOf( userAcctMgmtData.notifySettingsData_.notifyWhMode_ ) );
				
				jsonMap.put( "WorkingHoursFlag", String.valueOf( userAcctMgmtData.notifySettingsData_.workingHoursFlag_ ) );
	
			}
			else if( responseCode == 2670 ) 
			{
				jsonMap.put( "SunWorkingFlag", String.valueOf( userAcctMgmtData.workingHrsData_.workingDaysData_.sunWorkingFlag_ ) );
				
				jsonMap.put( "MonWorkingFlag", String.valueOf( userAcctMgmtData.workingHrsData_.workingDaysData_.monWorkingFlag_ ) );
				
				jsonMap.put( "TueWorkingFlag", String.valueOf( userAcctMgmtData.workingHrsData_.workingDaysData_.tueWorkingFlag_ ) );
				
				jsonMap.put( "WedWorkingFlag", String.valueOf( userAcctMgmtData.workingHrsData_.workingDaysData_.wedWorkingFlag_ ) );
				
				jsonMap.put( "ThuWorkingFlag", String.valueOf( userAcctMgmtData.workingHrsData_.workingDaysData_.thuWorkingFlag_ ) );
				
				jsonMap.put( "FriWorkingFlag", String.valueOf( userAcctMgmtData.workingHrsData_.workingDaysData_.friWorkingFlag_ ) );
				
				jsonMap.put( "SatWorkingFlag", String.valueOf( userAcctMgmtData.workingHrsData_.workingDaysData_.satWorkingFlag_ ) );
				
				
				jsonMap.put( "SunFromTime", String.valueOf( userAcctMgmtData.workingHrsData_.sunFromTime_ ) );
				
				jsonMap.put( "SunToTime", String.valueOf( userAcctMgmtData.workingHrsData_.sunToTime_ ) );
				
				jsonMap.put( "MonFromTime", String.valueOf( userAcctMgmtData.workingHrsData_.monFromTime_ ) ); 
				
				jsonMap.put( "MonToTime", String.valueOf( userAcctMgmtData.workingHrsData_.monToTime_ ) );
				
				jsonMap.put( "TueFromTime", String.valueOf( userAcctMgmtData.workingHrsData_.tueFromTime_ ) );
				
				jsonMap.put( "TueToTime", String.valueOf( userAcctMgmtData.workingHrsData_.tueToTime_ ) );
				
				jsonMap.put( "WedFromTime", String.valueOf( userAcctMgmtData.workingHrsData_.wedFromTime_ ) );
				
				jsonMap.put( "WedToTime", String.valueOf( userAcctMgmtData.workingHrsData_.wedToTime_ ) );
				
				jsonMap.put( "ThuFromTime", String.valueOf( userAcctMgmtData.workingHrsData_.thuFromTime_ ) );
				
				jsonMap.put( "ThuToTime", String.valueOf( userAcctMgmtData.workingHrsData_.thuToTime_ ) );
				
				jsonMap.put( "FriFromTime", String.valueOf( userAcctMgmtData.workingHrsData_.friFromTime_ ) );
				
				jsonMap.put( "FriToTime", String.valueOf( userAcctMgmtData.workingHrsData_.friToTime_ ) );
				
				jsonMap.put( "SatFromTime", String.valueOf( userAcctMgmtData.workingHrsData_.satFromTime_ ) );
				
				jsonMap.put( "SatToTime", String.valueOf( userAcctMgmtData.workingHrsData_.satToTime_ ) );
			}
			else 
			{
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );
			}
		}
		else 
		{
			jsonMap.put( "result", "failed" );
			
			String msg = "Info::UserAcctMgmtServlet.doPost() -  Request Failed - Error code - " + responseCode + "\r\n\n\n";
			errLogger.logMsg( msg );
			
			if( responseCode == 2501 || responseCode == 2503 || 
				responseCode == 2504 || responseCode == 2505 ||
				responseCode == 2506 || responseCode == 2508 ||
				responseCode == 2509 || responseCode == 2510 ||
				responseCode == 2511 || responseCode == 2512 )
			{
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+2501 );
				
				jsonMap.put( "message", responseString );
			}
			else if( responseCode == 2671 || responseCode == 2672 )
			{
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+2671 );
				
				jsonMap.put( "message", responseString );
			}
			else 
			{
				String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
				
				jsonMap.put( "message", responseString );	
			}
		}
		
//		/String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
		
		//jsonMap.put( "message", responseString );
		
		jsonStr = new Gson( ).  toJson( jsonMap );
		
		jsonMap = null;
		
		errorMaster_.insert( "json str="+jsonStr );

		return jsonStr;
	}

}
 
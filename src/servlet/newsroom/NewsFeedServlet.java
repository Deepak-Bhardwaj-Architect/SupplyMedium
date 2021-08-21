package servlet.newsroom;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
import utils.JSONEncode;
import utils.PathBuilder;
import utils.StringHolder;

import com.google.gson.Gson;

import core.feed.UserFeedData;
import core.login.SessionData;
import ctrl.newsroom.NewsFeedController;

/**
 * Servlet implementation class NewsFeedServlet
 */
@WebServlet("/NewsFeedServlet")
public class NewsFeedServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @see HttpServlet#HttpServlet()
	 */
	public NewsFeedServlet()
	{
		super( );

	}

	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{

	}

	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
		
			String msg = "Info::NewsFeedServlet.doPost() -  Request to  " +request.getParameter( "RequestType" )+
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													

			errLogger.logMsg( msg );
			
			System.out.println( "Message="+msg );
			
			List<UserFeedData> userFeedList = new ArrayList<UserFeedData>( );
			
			NewsFeedController newsFeedCtrl = new NewsFeedController( );
			
			int result = newsFeedCtrl.processRequest( request, userFeedList );
			
			//System.out.println( "userfeedlistcount="+userFeedList.size( ) );
		
			
			String jsonStr = composeResponseJSON( result, request, userFeedList );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	Map<String, String> jsonMap = new HashMap<String, String>( );
        	
        	String msg = "Exception::NewsFeedServlet.doPost() - "+e+"\r\n\n";

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
	 * Input : int responseCode,request object and user feed list
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON( int responseCode, HttpServletRequest request,List<UserFeedData> userFeedList  )
	{
		String jsonString = "";
		
		JSONEncode jsonEncode = new JSONEncode( );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		if( responseCode == 10160 || responseCode == 10170 )
		{
			String servletMsg = "Info::NewsFeedServlet.doPost() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			// System.out.println( "response code="+responseCode );

			int feedsCount = userFeedList.size( );

			if( feedsCount > 10 ) feedsCount = 10;

			jsonString = "{ \"result\" : \"success\",  ";

			jsonString = jsonString + "\"feeds\" : [";

			for( int i = 0; i < feedsCount; i++ )
			{
				UserFeedData userFeedData = userFeedList.get( i );
				
				StringHolder webURL = new StringHolder( );
				
				
				if( userFeedData.userProfilePicPath_ == null || userFeedData.userProfilePicPath_ .equals( "null" ) || 
						userFeedData.userProfilePicPath_ .equals( "" ) )
				{
					//System.out.println( "if block" );
					
					webURL.value = "";
				}
				else
				{
					//System.out.println( "else block" );
					
					PathBuilder pathBuilder = new PathBuilder( );
					
					pathBuilder.getWebURL( userFeedData.userProfilePicPath_, webURL );
					
					pathBuilder = null;
					
				}
				

				if( i != 0 ) jsonString = jsonString + ",";

				jsonString = jsonString + "{ \"userFeedId\" : \""+ userFeedData.userFeedId_ + "\", ";

				jsonString = jsonString + " \"feedTitle\" : \"" + jsonEncode.encode( userFeedData.feedTitle_ ) + "\", ";

				jsonString = jsonString + " \"feedDesc\" : \"" + jsonEncode.encode( userFeedData.feed_ ) + "\", ";
				
				jsonString = jsonString + "\"email\":\""+userFeedData.userKey_.toString( )+"\",";
	               
	            jsonString = jsonString + "\"userName\":\""+userFeedData.userName_+"\",";
	                
	            jsonString = jsonString + "\"userPictureURL\":\""+webURL.value+"\",";

	            
				// Creating web path from relative path
				StringHolder webPath = new StringHolder( );

				PathBuilder pathBuilder = new PathBuilder( );

				pathBuilder.getWebURLFromRelativepath( userFeedData.relativePath_, webPath );

				jsonString = jsonString + " \"imageURL\" : \"" + webPath.value + "\", ";

				pathBuilder = null;

				webPath = null;
	                

				SimpleDateFormat dateFormat = new SimpleDateFormat(
				        "dd MMM yyyy ',' hh:mm aaa" );

				String dateStr = dateFormat.format( userFeedData.createdTimestamp_ );

				jsonString = jsonString + " \"createdTime\" : \"" + dateStr + "\" ";

				jsonString = jsonString + "}";

			}

			jsonString = jsonString + "]}";

			//System.out.println( "feed =" + jsonString );

			jsonEncode = null;

			return jsonString;
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::NewsFeedServlet.doPost() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );
			
			jsonString = new Gson( ).  toJson( jsonMap );
			
			jsonMap = null;
			
			System.out.println( "json str="+jsonString );

			return jsonString;
			
		}
	
		
	}

}

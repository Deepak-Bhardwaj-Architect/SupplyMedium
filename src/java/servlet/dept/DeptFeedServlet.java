package servlet.dept;

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

import core.feed.DeptFeedData;
import core.login.SessionData;
import ctrl.dept.DeptFeedController;
import utils.ErrorMaster;


/**
 * Servlet implementation class DeptFeedServlet
 */
@WebServlet("/DeptFeedServlet")

public class DeptFeedServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	
	public DeptFeedServlet()
	{
		super( );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		errorMaster_.insert( "DeptFeedServlet" );
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
		
			String msg = "Info::DeptFeedServlet.doPost() -  Request to  " +request.getParameter( "RequestType" )+
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
													

			errLogger.logMsg( msg );
			
			errorMaster_.insert( "Message="+msg );
			
			List<DeptFeedData> deptFeedList = new ArrayList<DeptFeedData>( );
			
			DeptFeedController feedCtrl = new DeptFeedController( );
			
			int result = feedCtrl.processRequest( request, deptFeedList );
			
			//errorMaster_.insert( "userfeedlistcount="+userFeedList.size( ) );
		
			
			String jsonStr = composeResponseJSON( result, request, deptFeedList );

			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	Map<String, String> jsonMap = new HashMap<String, String>( );
        	
        	String msg = "Exception::DeptFeedServlet.doPost() - "+e+"\r\n\n";

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

	private String composeResponseJSON( int responseCode, HttpServletRequest request,List<DeptFeedData> deptFeedList  )
	{
		String jsonString = "";
		
		JSONEncode jsonEncode = new JSONEncode( );
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		if( responseCode == 1730 || responseCode == 1735 || responseCode == 1740  || responseCode == 1745 )
		{
			String servletMsg = "Info::DeptFeedServlet.doPost() - " +
					"Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );
			
			//errorMaster_.insert( "response code="+responseCode );
			
			if( responseCode == 1735 )
			{
				jsonMap.put( "result", "success" );
				
				String responseString = ErrorCodeConfigReader.instance( ).get( Integer.toString( responseCode ) );
				
				jsonMap.put( "message", responseString );
				
				jsonString = new Gson( ).  toJson( jsonMap );
				
				jsonMap = null;
				
				errorMaster_.insert( "json str="+jsonString );

				return jsonString;
				
			}
			
			else
			{
				//errorMaster_.insert( "response code="+responseCode );
				
				int feedsCount = deptFeedList.size( );
				
				if( feedsCount > 10)
					feedsCount = 10;
				
				
				jsonString = "{ \"result\" : \"success\",  ";
				
				jsonString = jsonString + "\"feeds\" : [";
				
				
				
				for( int i=0;i<feedsCount;i++ )
                {
					DeptFeedData deptFeedData = deptFeedList.get( i );
					
					StringHolder webURL = new StringHolder( );
					

					if( deptFeedData.userPictureUrl_ == null || deptFeedData.userPictureUrl_ .equals( "null" ) || 
							deptFeedData.userPictureUrl_ .equals( "" ) )
					{
						errorMaster_.insert( "if block" );
						
						webURL.value = "";
					}
					else
					{
						errorMaster_.insert( "else block" );
						
						PathBuilder pathBuilder = new PathBuilder( );
						
						pathBuilder.getWebURL( deptFeedData.userPictureUrl_, webURL );
						
						pathBuilder = null;
                                                webURL.value=deptFeedData.userPictureUrl_;
					} 
					
					if( i!= 0 )
						jsonString =jsonString+",";
					
	                jsonString = jsonString + "{ \"deptFeedId\" : \""+deptFeedData.deptFeedId_+"\", ";
	                
	                jsonString = jsonString + " \"feedTitle\" : \""+jsonEncode.encode( deptFeedData.feedTitle_)+"\", ";
	                
	                jsonString = jsonString + " \"feedDesc\" : \""+jsonEncode.encode(deptFeedData.deptFeed_)+"\", ";
 
	                jsonString = jsonString + " \"userKey\" : \""+jsonEncode.encode(deptFeedData.userKey_.toString( ))+"\", ";
	                
	                jsonString = jsonString + "\"userName\":\""+deptFeedData.userName_+"\",";
	                
	                jsonString = jsonString + "\"userPictureURL\":\""+webURL.value+"\",";
	               
	                
	                SimpleDateFormat dateFormat = new SimpleDateFormat( "dd MMM yyyy ',' hh:mm aaa");
	               
	                String dateStr =dateFormat.format( deptFeedData.createdTs_ );
	               
	                jsonString = jsonString + " \"createdTime\" : \""+dateStr+"\" ";  
	                
	                jsonString = jsonString + "}";
	                
                }
				
				jsonString = jsonString + "]}";
				
				errorMaster_.insert( "feed ="+jsonString );
				
				jsonEncode = null;

				return jsonString;
				
			}
			
		}
		else 
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::DeptFeedServlet.doPost() - "
			        + "Request failed - Error code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			String responseString = ErrorCodeConfigReader.instance( ).get(
			        "" + responseCode );

			jsonMap.put( "message", responseString );
			
			jsonString = new Gson( ).  toJson( jsonMap );
			
			jsonMap = null;
			
			errorMaster_.insert( "json str="+jsonString );

			return jsonString;
			
		}
	
		
	}

}


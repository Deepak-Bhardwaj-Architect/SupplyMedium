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

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import com.google.gson.Gson;

import core.dept.DeptFolderData;
import core.dept.FileData;
import core.privilege.DeptPrivilegesData;
import ctrl.dept.DPFetchDeptController;
import ctrl.dept.DeptPageController;

/**
 * Servlet implementation class DPFetchDeptServlet
 */
@WebServlet("/DPFetchDeptServlet")
public class DPFetchDeptServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DPFetchDeptServlet()
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
		ErrorLogger errLogger  = ErrorLogger.instance( );
		
		try
        {
			String servletMsg = "Info::DeptFolderServlet.doPost() "
			        + "Fetch folders request ";
			
			errLogger.logMsg( servletMsg );
			
			DPFetchDeptController dpFetchDeptCtrl = new DPFetchDeptController( );
			
	        List<DeptPrivilegesData> deptPrivilegesDataArr = new ArrayList<DeptPrivilegesData>( );
	        
	        int result = dpFetchDeptCtrl.get( request, deptPrivilegesDataArr );
	        
	        String jsonStr = composeResponseJSON( result, request, deptPrivilegesDataArr );
	        
	        response.setContentType( "application/json" );

			response.setCharacterEncoding( "UTF-8" );

			response.getWriter( ).write( jsonStr );
	        
        }
        catch( Exception e )
        {
        	String msg = "Exception::DeptFolderFetchServlet.doPost() - "+e+"\r\n\n";

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
	 * Input : int responseCode,request object and deptPrivilegesDataList
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the response in to json
	 * string
	 */

	private String composeResponseJSON( int responseCode, HttpServletRequest request,List<DeptPrivilegesData> deptPrivilegesDataList  )
	{
		String jsonString = "";
		
		Map<String, String> jsonMap = new HashMap<String, String>( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		
		if( responseCode == 1750  )
		{
			String servletMsg = "Info::DPFetchDeptServlet.composeResponseJSON() - "
			        + "Request successful - Response code - " + responseCode + "\r\n\n\n";

			errLogger.logMsg( servletMsg );

			jsonString = "{ \"result\" : \"success\",  ";

			jsonString = jsonString + "\"depts\" : [";

			int i=1;
			
			for( DeptPrivilegesData deptPrivilegesData : deptPrivilegesDataList )
			{
				jsonString = jsonString + "{ \"deptKey\" : \""
				        + deptPrivilegesData.deptData_.deptKey_.toString( ) + "\", ";

				jsonString = jsonString + " \"deptName\" : \""
				        + deptPrivilegesData.deptData_.deptKey_.deptName_ + "\", ";
				
				if( deptPrivilegesData.addFile_ )
				{
					jsonString = jsonString + " \"addFile\" : \"1\", ";
				}
				else 
				{
					jsonString = jsonString + " \"addFile\" : \"0\", ";
				}
				if( deptPrivilegesData.addFolder_ )
				{
					jsonString = jsonString + " \"addFolder\" : \"1\", ";
				}
				else 
				{
					jsonString = jsonString + " \"addFolder\" : \"0\", ";
				}
				if( deptPrivilegesData.deleteFile_ )
				{
					jsonString = jsonString + " \"deleteFile\" : \"1\", ";
				}
				else 
				{
					jsonString = jsonString + " \"deleteFile\" : \"0\", ";
				}
				if( deptPrivilegesData.deleteFolder_ )
				{
					jsonString = jsonString + " \"deleteFolder\" : \"1\", ";
				}
				else 
				{
					jsonString = jsonString + " \"deleteFolder\" : \"0\", ";
				}
				if( deptPrivilegesData.postAnnouncement_ )
				{
					jsonString = jsonString + " \"postAnnouncement\" : \"1\" ";
				}
				else 
				{
					jsonString = jsonString + " \"postAnnouncement\" : \"0\" ";
				}
				
				jsonString = jsonString + "}";
				
				if( i < deptPrivilegesDataList.size( ) )
				{
					jsonString = jsonString + ",";
				}
				
				i = i+1;
			}

			jsonString = jsonString + "]}";
			
			System.out.println( "json="+jsonString );
			
			return jsonString;
		}
		else 
		{
			jsonMap.put( "result", "failed" );

			String servletMsg = "Info::DPFetchDeptServlet.composeResponseJSON() - "
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

package servlet.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import utils.ErrorCodeConfigReader;
import core.config.SMConfigData;
import ctrl.config.SMConfigController;

/**
 * Servlet implementation class SMConfigServlet
 */
@WebServlet("/SMConfigServlet")
public class SMConfigServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SMConfigServlet()
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
		SMConfigController smConfigCtrl = new SMConfigController( );
		
		String jsonStr = "";

		int result = 0;

		SMConfigData smConfigData = new SMConfigData( );
		
		result = smConfigCtrl.getSMConfigs( smConfigData );

		if( result == 180 ) // Success
		{
			jsonStr = composeJSON( smConfigData );
		}
		else
		// Failed
		{
			jsonStr = composeErrorMessage( result );
		}

		smConfigData = null;
	
		System.out.println( "jsonStr=" + jsonStr );

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		response.getWriter( ).write( jsonStr );
	}
	
	/*This method composes the smConfigData JSON */
	public String composeJSON( SMConfigData smConfigData )
	{
		String jsonStr = "";

		Map<String, String> configMap = new HashMap<String, String>( );

		configMap.put( "result", "success" );

		configMap.put( "recycleExpireDays", String.valueOf(  smConfigData.recycleExpireDays_  ) );
		
		configMap.put( "regLinkExpireDays", String.valueOf( smConfigData.regLinkExpireDays_  ) );
		
        configMap.put( "taxPercentage", String.valueOf( smConfigData.taxPercentage_ ) );
		
		jsonStr = new Gson( ).toJson( configMap );

		return jsonStr;
	}

	/*This method composes the error message json*/
	public String composeErrorMessage( int errorCode )
	{
		String jsonStr = "";

		String message = null;

		Map<String, String> errorMap = new HashMap<String, String>( );

		errorMap.put( "result", "failed" );

		message = ErrorCodeConfigReader.instance( ).get( Integer.toString( errorCode ) );

		errorMap.put( "errmsg", message );

		jsonStr = new Gson( ).toJson( errorMap );

		return jsonStr;
	}

}

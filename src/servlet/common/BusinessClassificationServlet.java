package servlet.common;

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

import utils.BusinessClassification;
import com.google.gson.Gson;

import ctrl.common.BusinessClassficationController;

/**
 * Servlet implementation class BusinessClassificationServlet
 */
@WebServlet("/BusinessClassificationServlet")
public class BusinessClassificationServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BusinessClassificationServlet()
	{
		super( );

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException
	{
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException
	{
		System.out.println( "At Business classification servlet" );
		BusinessClassficationController busiClassCtrl = new BusinessClassficationController( );
		
		List<String> businessClassficationList = new ArrayList<String>( );
		
		int result = busiClassCtrl.getBusinessClassfications( businessClassficationList );
		
		busiClassCtrl = null;
		
		String jsonStr = "";

		//String message = null;

		Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( result == 0 )
		{
			jsonMap.put( "result", "success" );

			//message = ErrorCodeConfigReader.instance( ).get( Integer.toString( errorCode ) );
			
			int i=0;
			
			for( String string : businessClassficationList )
            {
				jsonMap.put( BusinessClassification.status.values( )[i].getValue( ), string );
				
				i++;
            }
			jsonStr = new Gson( ).toJson( jsonMap );
		}
		else 
		{
			jsonMap.put( "result", "failed" );

			//message = ErrorCodeConfigReader.instance( ).get( Integer.toString( errorCode ) );

			jsonMap.put( "errmsg", "Failed to load business classfications" );

			jsonStr = new Gson( ).toJson( jsonMap );

		}
		
		businessClassficationList = null;

		System.out.println( "jsonStr=" + jsonStr );

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		response.getWriter( ).write( jsonStr );
		
	}
}

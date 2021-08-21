package servlet.userrating;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ErrorLogger;

import com.google.gson.Gson;

import core.userratings.CompanyTotalRatingsData;
import core.userratings.UserRatingsData;
import ctrl.userratings.UserRatingsController;


/**
 * @FileName : FetchCompanyRatingServlet.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Aug 28, 2013
 * 
 * @Purpose : Getting the User Rating Servlet
 * 
 */

@WebServlet("/FetchCompanyRatingServlet")
public class FetchCompanyRatingServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;

	public FetchCompanyRatingServlet()
	{
		super( );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		UserRatingsController controller = new UserRatingsController( );
		int _result;
		String responseJson = "";

		//try
		//{
			List<CompanyTotalRatingsData> ratingsList = new ArrayList<CompanyTotalRatingsData>( );
			
			_result = controller.getCompanyRatingList( request, ratingsList );
			
			if( _result == 10520 )
			{
				responseJson = "{ \"result\" : \"success\",  ";

				Gson gson = new Gson( );
				String value = gson.toJson( ratingsList );
				responseJson += "\"UserRatingList\" :" + value;
				responseJson += "}";
			}
			else
			{
				responseJson = "{ \"result\" : \"failed\",  ";
			}

		/*}
		catch( Exception e )
		{
			String msg = "Exception::FetchCompanyRatingServlet.doPost() - " + e + "\r\n\n\n";
			errLogger.logMsg( msg );
			responseJson = "{ \"result\" : \"failed\",  ";
		}*/

		System.out.println( "json str=" + responseJson );
		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
		response.getWriter( ).write( responseJson.toString( ) );
	}

}

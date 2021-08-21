package servlet.prodcatalog;

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
import core.login.SessionData;
import core.prodcatalog.ProductCatalogData;
import ctrl.prodcatalog.ProdCatalogController;
import com.google.gson.Gson;
import utils.ErrorMaster;

/**
 * Servlet implementation class FetchProdCatalogServlet
 */
/**
 * @FileName : FetchProdCatalogServlet.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Jul 27, 2013
 * 
 * @Purpose : Get Product Catalog information
 * 
 */
@WebServlet("/FetchProdCatalogServlet")
public class FetchProdCatalogServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
        private static ErrorMaster errorMaster_ = null;




	public FetchProdCatalogServlet()
	{
		super( );if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		HttpSession session = request.getSession( );
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );

		String responseJson = "";

		try
		{

			String msg = "Info::FetchProdCatalogServlet.doPost() - Request to Fetch Product Catelgo" + " for the company " + "<"
			        + sessionData.companyRegnKeyStr_ + ">, " + "<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";

			errLogger.logMsg( msg );

			ProdCatalogController prodcatalog = new ProdCatalogController( );
			List<ProductCatalogData> productCatalogList = new ArrayList<ProductCatalogData>( );
			int responseCode = prodcatalog.getAllCatalog( request, productCatalogList );

			if( responseCode == 15000 )
			{
				// jsonMap.put( "result", "success" );
				msg = "Info::UserFetchServlet.doPost() - Request successful - Response code - " + responseCode + "\r\n\n\n";

				errLogger.logMsg( msg );

				responseJson = "{ \"result\" : \"success\",  ";

				Gson gson = new Gson( );
				String value = gson.toJson( productCatalogList );
				responseJson += "\"CatalogList\" :" + value;
				responseJson += "}";
			}
			else
			{
				responseJson = "{ \"result\" : \"failed\",  ";
				msg = "Info::FetchProdCatalogServlet.doPost() - Request Failed - Error code - " + responseCode + "\r\n\n\n";
				errLogger.logMsg( msg );

			}

		}
		catch( Exception e )
		{
			String msg = "Exception::FetchProdCatalogServlet.doPost() - " + e + "\r\n\n\n";
			errLogger.logMsg( msg );
			responseJson = "{ \"result\" : \"failed\",  ";
		}

		errorMaster_.insert( "json str=" + responseJson );
		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
		response.getWriter( ).write( responseJson );
	}

}

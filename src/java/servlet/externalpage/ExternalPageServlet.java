package servlet.externalpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import utils.IntHolder;
import utils.StringHolder;

import core.externalpage.ExternalPageData;
import core.externalpage.enumExternalPageAction;
import ctrl.externalpage.ExternalPageController;
import utils.ErrorMaster;

@WebServlet("/externalpageservlet")
public class ExternalPageServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
        private static ErrorMaster errorMaster_ = null;




	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		String Action = request.getParameter( "Action" );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		enumExternalPageAction action = enumExternalPageAction.valueOf( Action );
		int result = 0;
		JsonObject responseJson = new JsonObject( );
		ExternalPageController extpage = new ExternalPageController( );

		switch( action )
		{
		case LoadExternalPage:

			errorMaster_.insert( "Info::externalpageservlet :: LoadExternalPage" );

			ExternalPageData pagedata = new ExternalPageData( );
			result = extpage.procrssRequestLoad( request, pagedata );
			if( result == 4100 )
			{
				responseJson.addProperty( "companyregKey", pagedata.compnayRegnKey_.companyPhoneNo_ );
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "insertAddrId", pagedata.externalpageid_ );
				responseJson.addProperty( "compnayURLName", pagedata.companyURLName_ );
				responseJson.addProperty( "pageType", pagedata.pageType_ );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "External Page Info Not Found" );
			}

			break;
			
		case InsertExternalPage:
			IntHolder key = new IntHolder( );

			result = extpage.procrssRequestInsert( request, key );

			if( result == 4000 )
			{
				errorMaster_.insert( "Info::externalpageservlet :: InsertExternalPage ::External Page Inserted Successfully" );

				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "insertAddrId", key.value );
			}
			else if( result == 4003 )
			{
				errorMaster_.insert( "Info::externalpageservlet :: InsertExternalPage ::External Link URL Not Valid" );

				responseJson.addProperty( "result", "URLNotValid" );
				responseJson.addProperty( "message", "External Link is Invalid" );
			}
			else if( result == 4300 )
			{
				responseJson.addProperty( "result", "companyNameURLExists" );
				responseJson.addProperty( "message", "Company Name Already Exists" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Inserting the External Page failed" );
			}

			break;

		case UpdateExternalPage:

			errorMaster_.insert( "Info::externalpageservlet :: UpdateExternalPage" );

			result = extpage.procrssRequestUpdate( request );
			if( result == 4200 )
			{
				responseJson.addProperty( "result", "success" );
			}
			else if( result == 4003 )
			{
				errorMaster_.insert( "Info::externalpageservlet :: InsertExternalPage ::External Link URL Not Valid" );

				responseJson.addProperty( "result", "URLNotValid" );
				responseJson.addProperty( "message", "External Link is Invalid" );
			}
			else if( result == 4300 )
			{
				responseJson.addProperty( "result", "companyNameURLExists" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Unable to Update Pl Try again" );
			}

			break;
		case GetExternalPageLink:
			
			StringHolder  websiteURL = new StringHolder( );
			websiteURL.value = "";
			result = extpage.procrssRequestGetLink( request, websiteURL );
			
			if( result == 6200 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "websiteurl", websiteURL.value );
			}
			else 
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Error occurred while fetching website link" );
			}
		
			break;
		default:
			break;
		}

		errorMaster_.insert( "jsonStr=" + responseJson.toString( ) );

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		response.getWriter( ).write( responseJson.toString( ) );

	}

}

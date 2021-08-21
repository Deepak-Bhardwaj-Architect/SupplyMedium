package servlet.externalpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

import core.externalpage.TemplateCommonData;

import ctrl.externalpage.PageGeneratorController;
import utils.ErrorMaster;

/**
 * Servlet implementation class PageGeneratorServlet
 */
@WebServlet("/PageGeneratorServlet")
public class PageGeneratorServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
        private static ErrorMaster errorMaster_ = null;




	public PageGeneratorServlet()
	{
		super( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{

	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		String type = request.getParameter( "PageGenerateType" );
		PageGeneratorController controller = new PageGeneratorController( );

		int result = 0;
		JsonObject responseJson = new JsonObject( );

		if( type.equalsIgnoreCase( "Template" ) )
		{
			result = controller.processRequest( request );

			if( result == 4800 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Preview Successfully" );
				responseJson.addProperty( "CMPpath", TemplateCommonData.responseCMPPath_ );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Page Preview failled" );
			}

		}
		else if( type.equalsIgnoreCase( "WebSite" ) )
		{
			result = controller.processRequestExternalWebsite( request );

			if( result == 6100 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Preview Successfully" );
				responseJson.addProperty( "CMPpath", TemplateCommonData.exterlWebSite_ );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Page Preview failled" );
			}

			errorMaster_.insert( "jsonStr=" + responseJson.toString( ) );
		}

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		response.getWriter( ).write( responseJson.toString( ) );

	}

}

package servlet.externalpage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;

import core.externalpage.ExternalWebSiteData;
import core.externalpage.TemplateCommonData;
import core.externalpage.TemplateFillContentAboutUsData;
import core.externalpage.TemplateFillContentContactUsData;
import core.externalpage.TemplateFillContentHomeData;
import core.externalpage.TemplateFillContentProductsData;
import core.externalpage.TemplateFillContentServiceData;
import core.externalpage.TemplateFillContentSolutionsData;
import core.externalpage.TemplateWebStructureData;
import core.externalpage.enumTemplateAction;

import ctrl.externalpage.ExternalPageTemplateController;
import ctrl.externalpage.URLStatus;
import utils.ErrorMaster;

/**
 * Servlet implementation class TemplateDataServlet
 */
@WebServlet("/TemplateDataServlet")
public class TemplateDataServlet extends HttpServlet
{
	private static final long	serialVersionUID	= 1L;
        private static ErrorMaster errorMaster_ = null;




	public TemplateDataServlet()
	{
		super( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		
		enumTemplateAction action = enumTemplateAction.valueOf( request.getParameter( "Action" ) );
		ExternalPageTemplateController controller = new ExternalPageTemplateController( );
		int result = 0;
		JsonObject responseJson = new JsonObject( );

		switch( action )
		{
		case WebStructureData_Save:

			result = controller.processWebStructureSave( request );
			if( result == 4400 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Written Successfully" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Unable write the data" );
			}
			break;

		case WebStructureData_Read:

			TemplateWebStructureData webstructdata = new TemplateWebStructureData( );

			result = controller.processWebStructureRead( request, webstructdata );
			if( result == 4500 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Readed Successfully" );
				responseJson.addProperty( "Home", webstructdata.home_ );
				responseJson.addProperty( "Products", webstructdata.products_ );
				responseJson.addProperty( "Solutions", webstructdata.solutions_ );
				responseJson.addProperty( "Service", webstructdata.serviceSupport_ );
				responseJson.addProperty( "About_US", webstructdata.aboutUS_ );
				responseJson.addProperty( "ContactUS", webstructdata.contactUS_ );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Web Structure File Not Found" );
			}
			break;

		case ContentHome_Save:
			
			result = controller.processContentHomeSave( request );
			if( result == 4600 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Written Successfully" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Unable write the data" );
			}

			break;

		case ContentHome_Read:

			TemplateFillContentHomeData homeData = new TemplateFillContentHomeData( );

			result = controller.processContentHomeRead( request, homeData );
			if( result == 4700 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Readed Successfully" );
				responseJson.addProperty( "Video_URL", homeData.videoURL_ );
				responseJson.addProperty( "Video_Title", homeData.videoTitle_ );
				responseJson.addProperty( "Content", homeData.content_ );
				
				
				
				responseJson.addProperty( "image_1_content", homeData.image_1_content );
				responseJson.addProperty( "image_1_title_", homeData.image_1_Title_ );
				responseJson.addProperty( "image_1_imgName_", homeData.image_1_imgName_ );
				responseJson.addProperty( "image_1_hasimage_", homeData.image_1_hasimage_ );
				responseJson.addProperty( "image_1_imgName_", homeData.image_1_imgName_ );
				responseJson.addProperty( "image_directory", TemplateCommonData.externalPageDirImage_ );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Content Home File Not Found" );
			}

			break;

		case ContentProduct_Save:
			result = controller.processContentProductSave( request );
			if( result == 4800 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Written Successfully" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Unable write the data" );
			}
			break;

		case ContentProduct_Read:
			TemplateFillContentProductsData productdata = new TemplateFillContentProductsData( );

			result = controller.processContentProductRead( request, productdata );
			if( result == 4900 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Readed Successfully" );
				responseJson.addProperty( "videoURL_1_", productdata.videoURL_1_ );
				responseJson.addProperty( "videoURL_1_content_", productdata.videoURL_1_content_ );
				responseJson.addProperty( "videoURL_2_", productdata.videoURL_2_ );
				responseJson.addProperty( "videoURL_2_content_", productdata.videoURL_2_content_ );
				responseJson.addProperty( "image_1_content", productdata.image_1_content );
				responseJson.addProperty( "image_1_title_", productdata.image_1_Title_ );
				responseJson.addProperty( "image_1_imgName_", productdata.image_1_imgName_ );
				responseJson.addProperty( "image_1_hasimage_", productdata.image_1_hasimage_ );
				responseJson.addProperty( "image_1_imgName_", productdata.image_1_imgName_ );
				responseJson.addProperty( "image_2_content", productdata.image_2_content );
				responseJson.addProperty( "image_2_title_", productdata.image_2_Title_ );
				responseJson.addProperty( "image_2_imgName_", productdata.image_2_imgName_ );
				responseJson.addProperty( "image_2_hasimage_", productdata.image_2_hasimage_ );
				responseJson.addProperty( "image_2_imgName_", productdata.image_2_imgName_ );
				responseJson.addProperty( "image_3_content", productdata.image_3_content );
				responseJson.addProperty( "image_3_title_", productdata.image_3_Title_ );
				responseJson.addProperty( "image_3_imgName_", productdata.image_3_imgName_ );
				responseJson.addProperty( "image_3_hasimage_", productdata.image_3_hasimage_ );
				responseJson.addProperty( "image_3_imgName_", productdata.image_3_imgName_ );
				responseJson.addProperty( "image_4_content", productdata.image_4_content );
				responseJson.addProperty( "image_4_title_", productdata.image_4_Title_ );
				responseJson.addProperty( "image_4_imgName_", productdata.image_4_imgName_ );
				responseJson.addProperty( "image_4_hasimage_", productdata.image_4_hasimage_ );
				responseJson.addProperty( "image_4_imgName_", productdata.image_4_imgName_ );
				responseJson.addProperty( "image_directory", TemplateCommonData.externalPageDirImage_ );

				errorMaster_.insert( "Reading the Information" );
				productdata.Show( );

			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Content Product File Not Found" );
			}
			break;

		case ContentServices_Save:
			result = controller.processContentServicesSave( request );
			if( result == 5000 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Written Successfully" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Unable write the data" );
			}
			break;

		case ContentServices_Read:
			TemplateFillContentServiceData servicedata = new TemplateFillContentServiceData( );

			result = controller.processContentServiceRead( request, servicedata );
			if( result == 5100 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Readed Successfully" );
				responseJson.addProperty( "videoURL_1_", servicedata.videoURL_1_ );
				responseJson.addProperty( "videoURL_1_content_", servicedata.videoURL_1_content_ );
				responseJson.addProperty( "videoURL_2_", servicedata.videoURL_2_ );
				responseJson.addProperty( "videoURL_2_content_", servicedata.videoURL_2_content_ );
				responseJson.addProperty( "image_1_content", servicedata.image_1_content );
				responseJson.addProperty( "image_1_title_", servicedata.image_1_Title_ );
				responseJson.addProperty( "image_1_imgName_", servicedata.image_1_imgName_ );
				responseJson.addProperty( "image_1_hasimage_", servicedata.image_1_hasimage_ );
				responseJson.addProperty( "image_1_imgName_", servicedata.image_1_imgName_ );
				responseJson.addProperty( "image_2_content", servicedata.image_2_content );
				responseJson.addProperty( "image_2_title_", servicedata.image_2_Title_ );
				responseJson.addProperty( "image_2_imgName_", servicedata.image_2_imgName_ );
				responseJson.addProperty( "image_2_hasimage_", servicedata.image_2_hasimage_ );
				responseJson.addProperty( "image_2_imgName_", servicedata.image_2_imgName_ );
				responseJson.addProperty( "image_3_content", servicedata.image_3_content );
				responseJson.addProperty( "image_3_title_", servicedata.image_3_Title_ );
				responseJson.addProperty( "image_3_imgName_", servicedata.image_3_imgName_ );
				responseJson.addProperty( "image_3_hasimage_", servicedata.image_3_hasimage_ );
				responseJson.addProperty( "image_3_imgName_", servicedata.image_3_imgName_ );
				responseJson.addProperty( "image_4_content", servicedata.image_4_content );
				responseJson.addProperty( "image_4_title_", servicedata.image_4_Title_ );
				responseJson.addProperty( "image_4_imgName_", servicedata.image_4_imgName_ );
				responseJson.addProperty( "image_4_hasimage_", servicedata.image_4_hasimage_ );
				responseJson.addProperty( "image_4_imgName_", servicedata.image_4_imgName_ );
				responseJson.addProperty( "image_directory", TemplateCommonData.externalPageDirImage_  );

				errorMaster_.insert( "Reading the Service Information Information" );
				servicedata.Show( );

			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Content Service File Not Found" );
			}
			break;

		case ContentSolutions_Save:
			result = controller.processContentSolutionsSave( request );
			if( result == 5200 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Written Successfully" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Unable write the data" );
			}
			break;

		case ContentSolutions_Read:
			TemplateFillContentSolutionsData solutiondata = new TemplateFillContentSolutionsData( );

			result = controller.processContentSoultionsRead( request, solutiondata );
			if( result == 5300 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Readed Successfully" );
				responseJson.addProperty( "videoURL_1_", solutiondata.videoURL_1_ );
				responseJson.addProperty( "videoURL_1_content_", solutiondata.videoURL_1_content_ );
				responseJson.addProperty( "videoURL_2_", solutiondata.videoURL_2_ );
				responseJson.addProperty( "videoURL_2_content_", solutiondata.videoURL_2_content_ );
				responseJson.addProperty( "image_1_content", solutiondata.image_1_content );
				responseJson.addProperty( "image_1_title_", solutiondata.image_1_Title_ );
				responseJson.addProperty( "image_1_imgName_", solutiondata.image_1_imgName_ );
				responseJson.addProperty( "image_1_hasimage_", solutiondata.image_1_hasimage_ );
				responseJson.addProperty( "image_1_imgName_", solutiondata.image_1_imgName_ );
				responseJson.addProperty( "image_2_content", solutiondata.image_2_content );
				responseJson.addProperty( "image_2_title_", solutiondata.image_2_Title_ );
				responseJson.addProperty( "image_2_imgName_", solutiondata.image_2_imgName_ );
				responseJson.addProperty( "image_2_hasimage_", solutiondata.image_2_hasimage_ );
				responseJson.addProperty( "image_2_imgName_", solutiondata.image_2_imgName_ );
				responseJson.addProperty( "image_3_content", solutiondata.image_3_content );
				responseJson.addProperty( "image_3_title_", solutiondata.image_3_Title_ );
				responseJson.addProperty( "image_3_imgName_", solutiondata.image_3_imgName_ );
				responseJson.addProperty( "image_3_hasimage_", solutiondata.image_3_hasimage_ );
				responseJson.addProperty( "image_3_imgName_", solutiondata.image_3_imgName_ );
				responseJson.addProperty( "image_4_content", solutiondata.image_4_content );
				responseJson.addProperty( "image_4_title_", solutiondata.image_4_Title_ );
				responseJson.addProperty( "image_4_imgName_", solutiondata.image_4_imgName_ );
				responseJson.addProperty( "image_4_hasimage_", solutiondata.image_4_hasimage_ );
				responseJson.addProperty( "image_4_imgName_", solutiondata.image_4_imgName_ );
				responseJson.addProperty( "image_directory",TemplateCommonData.externalPageDirImage_  );

				errorMaster_.insert( "Reading the Service Information Information" );
				solutiondata.Show( );

			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Content Solutions File Not Found" );
			}
			break;

		case ContentAboutUs_Save:
			result = controller.processContentAboutUsSave( request );
			if( result == 5400 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Written Successfully" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Unable write the data" );
			}
			break;

		case ContentAboutUs_Read:
			TemplateFillContentAboutUsData aboutdata = new TemplateFillContentAboutUsData( );

			result = controller.processContentAboutUsRead( request, aboutdata );
			if( result == 5500 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Readed Successfully" );
				responseJson.addProperty( "image_1_content", aboutdata.image_1_content );
				responseJson.addProperty( "image_1_title_", aboutdata.image_1_Title_ );
				responseJson.addProperty( "image_1_imgName_", aboutdata.image_1_imgName_ );
				responseJson.addProperty( "image_1_hasimage_", aboutdata.image_1_hasimage_ );
				responseJson.addProperty( "image_1_imgName_", aboutdata.image_1_imgName_ );
				responseJson.addProperty( "image_directory", TemplateCommonData.externalPageDirImage_  );

				errorMaster_.insert( "Reading the about us Information Information" );
				aboutdata.Show( );

			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Content Solutions File Not Found" );
			}
			break;

		case ContentContatctUs_Save:
			result = controller.processContentContactUsSave( request );
			if( result == 5600 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Written Successfully" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Unable write the data" );
			}
			break;
			
		case ContentContatctUs_Read:

			TemplateFillContentContactUsData contactdata = new TemplateFillContentContactUsData( );

			result = controller.processContentContactUsRead( request, contactdata );
			if( result == 5700 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Readed Successfully" );
				responseJson.addProperty( "country_1", contactdata.country_ );
				responseJson.addProperty( "address_1", contactdata.adddress_);
				responseJson.addProperty( "city_1", contactdata.city_ );				
				responseJson.addProperty( "state_1", contactdata.state_ );
				responseJson.addProperty( "zipcode_1", contactdata.zipcode_ );
				responseJson.addProperty( "inquiry_1", contactdata.inquiry_ );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Content ContatctUs File Not Found" );
			}

			break;
			
		case ExternalWebSite_Save:
			result = controller.processContentExternalWebSiteSave( request );
			if( result == 5900 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Written Successfully" );
			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "Unable write the data" );
			}
			break;

		case ExternalWebSite_Read:
			ExternalWebSiteData websitedata = new ExternalWebSiteData( );

			result = controller.processContentExternalWebSiteRead( request, websitedata );
			if( result == 6000 )
			{
				responseJson.addProperty( "result", "success" );
				responseJson.addProperty( "message", "Data Readed Successfully" );
				responseJson.addProperty( "webURL", websitedata.webURL_ );

				errorMaster_.insert( "Reading the about us Information Information" );

			}
			else
			{
				responseJson.addProperty( "result", "failed" );
				responseJson.addProperty( "message", "ExternalWebSite  File Not Found" );
			}
			break;

		case isValid_URL:
			boolean results = false;
			URLStatus status = new URLStatus( );
			String URL = "";
			URL = request.getParameter( "URL" );
			if( !URL.equalsIgnoreCase( "" ) )
			{
				results = status.isURLValid( URL );
			}
			else
				results = false;

			responseJson.addProperty( "result", "success" );
			responseJson.addProperty( "isValid_URL", results );

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

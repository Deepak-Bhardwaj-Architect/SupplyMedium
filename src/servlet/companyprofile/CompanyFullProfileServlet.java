package servlet.companyprofile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;
import core.companyprofile.CompanyProfileData;
import core.login.SessionData;
import core.regn.MailingAddressData;
import ctrl.companyprofile.CompanyProfileLoaderController;

/**
 * Servlet implementation class CompanyFullProfileServlet
 */
@WebServlet("/CompanyFullProfileServlet")
public class CompanyFullProfileServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyFullProfileServlet()
	{
		super( );
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		// Map<String, String> jsonMap = new HashMap<String, String>( );

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		String jsonString = "";
		
		try
		{
			String msg = "Info::CompanyFullProfileServlet.doPost() - Request to fetch Company Full Profile"
						 + " for the company " +"<" + sessionData.companyRegnKeyStr_ + ">, " +
						 "<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";			        

			errLogger.logMsg( msg );
			
			CompanyProfileLoaderController cmpLoaderCtrl = new CompanyProfileLoaderController( );
			
			CompanyProfileData profileData = new CompanyProfileData( );

			int responseCode = cmpLoaderCtrl.getFullProfile( request, profileData );
			
			cmpLoaderCtrl = null;

			if( responseCode == 1410 )
			{
				// jsonMap.put( "result", "success" );
				msg = "Info::CompanyFullProfileServlet.doPost() - Request successful - Response code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );

				String responseString = ErrorCodeConfigReader.instance( ).get(
				        "" + responseCode );

				jsonString = "{ \"result\" : \"success\",  ";
				jsonString = jsonString + "\"msg\" : \""+responseString+"\", ";
				jsonString = jsonString + "\"profiledetails\" : ";
				  
				jsonString = jsonString + "{ \"companyid\" : \"" + profileData.companyID_ + "\", ";
				jsonString = jsonString + " \"companyname\" : \"" + profileData.companyName_ + "\", ";
	            jsonString = jsonString + " \"businesscategory\" : \"" + profileData.businessCategory_  + "\", ";
	            jsonString = jsonString + " \"businesstype\" : \"" + profileData.businessType_  + "\", ";
	            jsonString = jsonString + " \"businesscontactname\" : \"" + profileData.businessContactName_ + "\", ";
	           
	            jsonString = jsonString + " \"email\" : \"" + profileData.email_ + "\", ";
	            jsonString = jsonString + " \"department\" : \"" + profileData.department_ + "\", ";
	            jsonString = jsonString + " \"phone\" : \"" + profileData.phone_ + "\", ";
	            jsonString = jsonString + " \"fax\" : \"" + profileData.fax_ + "\", ";
	            jsonString = jsonString + " \"cell\" : \"" + profileData.cell_ + "\", ";
	                
	            jsonString = jsonString + " \"addressdata\" : [";
	                				 
				int iterator = 0;
				for( MailingAddressData data : profileData.mailingAddressArr_ )
                {
					 jsonString = jsonString + "{ \"addressId\" : \"" + data.addrid_ + "\", ";
					 
					 JSONEncode jsonEncode=new JSONEncode( );
					 
					 String address=jsonEncode.encode( data.address_ );
					 
	                jsonString = jsonString + " \"address\" : \"" +address.trim( ) + "\", ";
	                jsonString = jsonString + " \"city\" : \"" + data.city_ + "\", ";
	                jsonString = jsonString + " \"state\" : \"" + data.state_ + "\", ";
	                jsonString = jsonString + " \"zipcode\" : \"" + data.zipcode_ + "\", ";
	                jsonString = jsonString + " \"branch\" : \"" + data.addressType_ + "\", ";
	                jsonString = jsonString + " \"country\" : \""+ data.countryRegion_+"\"";
	                
	                jsonString = jsonString + "}";
	                iterator = iterator + 1;
	                
	                if( profileData.mailingAddressArr_.size( ) > iterator )
	                {
	                	jsonString = jsonString + ",";
	                }
                }
				
				jsonString = jsonString + "]}}";
			}
			else
			{
				String responseString = ErrorCodeConfigReader.instance( ).get(
				        "" + responseCode );

				jsonString = "{ \"result\" : \"failed\"  ";

				jsonString = jsonString + "\"msg\" : "+responseString+" }";
				
				msg = "Info::CompanyFullProfileServlet.doPost() - Request Failed - Error code - "+responseCode+"\r\n\n\n";
				
				errLogger.logMsg( msg );
				
			}

			System.out.println( "json str=" + jsonString );

			response.getWriter( ).write( jsonString );
		}
		catch( Exception e )
		{
			String msg = "Exception::CompanyFullProfileServlet.doPost() - " + e+"\r\n\n\n";

			errLogger.logMsg( msg );
			
			jsonString = "{ \"result\" : \"failed\"  ";

			jsonString = jsonString + "\"msg\" : \" Request failed.  Pls try again later \" }";

		}
	}

}

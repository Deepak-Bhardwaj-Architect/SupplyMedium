package servlet.vendorregn;

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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


import utils.ErrorLogger;
import core.login.SessionData;

import core.regn.MailingAddressData;



import ctrl.vendorregn.VRMailingController;


/**
 * Servlet implementation class VRMailingServlet
 */
@WebServlet("/VRMailingServlet")
public class VRMailingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public String companyName_;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VRMailingServlet() {
        super();
        
        companyName_ = "";
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {		
			String msg = "Info::VRMailingServlet.doPost() -  Request : "
						  + request.getParameter( "RequestType" ) + 
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			
			// Call to controller
			
			VRMailingController vrMailingController = new VRMailingController( );
			
			List<MailingAddressData> mailingAddressDataList= new ArrayList<MailingAddressData>( );
			
			int responseCode = vrMailingController.processVR( request, mailingAddressDataList );
			
			vrMailingController = null;
			
			
			// Compose the result 
			
			VRMailingComposer composer = new VRMailingComposer( );
			
			String jsonStr = composer.composeVRMailingJSON( responseCode, mailingAddressDataList );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::VRMailingServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			Map<String, String> jsonMap = new HashMap<String, String>( );
			
			jsonMap.put( "result", "failed" );
			
			jsonMap.put( "message", "Request failed. Try again." );
			
			String jsonStr = new Gson( ).toJson( jsonMap );
			
			jsonMap = null;
			
			response.getWriter( ).write( jsonStr );
			
        }

	}
}

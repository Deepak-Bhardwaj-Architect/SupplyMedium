package servlet.dashboard;

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

import utils.ErrorLogger;

import com.google.gson.Gson;

import core.dashboard.CustomerBasedData;
import core.dashboard.TimeBasedData;
import core.login.SessionData;
import ctrl.dashboard.CustomerBasedReportController;
import ctrl.dashboard.TimeBasedReportController;

/**
 * Servlet implementation class CustomerBasedReportServlet
 */
@WebServlet("/CustomerBasedReportServlet")
public class CustomerBasedReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	
    public CustomerBasedReportServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from Dashboard under the Network Menu from GUI. This
	 * servlet get the following request from view and send the response to
	 * View. It do the following 
	 * 
	 *  1.Get the Daily date trends for given companyKey
	 * 2.Get the Monthly date trends for given companyKey
	 * 3.Get the Yearly date trends for given companyKey
	 * 4.Get the quarterly date trends for given companyKey
	 */
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {		
			String msg = "Info::CustomerBasedReportServlet.doPost() -  Request : "
						  + request.getParameter( "RequestType" ) + 
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			
			// Call to controller
			
			CustomerBasedReportController customerBasedReportController = new CustomerBasedReportController( );
			
			List<CustomerBasedData> customerBasedList= new ArrayList<CustomerBasedData>( );
			
			int responseCode = customerBasedReportController.processCB( request, customerBasedList );
			
			customerBasedReportController = null;
			
			
			// Compose the result 
			
			CustomerBasedReportComposer composer = new CustomerBasedReportComposer( );
			
			String jsonStr = composer.composeCustomerBasedReportJSON( responseCode, customerBasedList );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::CustomerBasedReportServlet.doPost() - "+e+"\r\n\n";

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

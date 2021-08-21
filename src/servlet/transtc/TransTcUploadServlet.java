package servlet.transtc;

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

import core.login.SessionData;
import core.transtc.TransTcUploadData;

import ctrl.transtc.TransTcUploadController;

/**
 * Servlet implementation class TransTcUploadServlet
 */
@WebServlet("/TransTcUploadServlet")
public class TransTcUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	//constructor
    public TransTcUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: 
	 * 1.update the TransactionTerm and condition
	 * 2.Get the TransactionTerm and condition for given regn key 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {		
			String msg = "Info::TransTcUploadServlet.doPost() -  Request : "
						  + request.getParameter( "RequestType" ) + 
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">";
			
			errLogger.logMsg( msg );
			
			
			// Call to controller
			
			TransTcUploadController  transTcController = new TransTcUploadController( );
			
			List<TransTcUploadData> translists= new ArrayList<TransTcUploadData>( );
			
			int responseCode = transTcController.processTC( request, translists );
			
			transTcController = null;
			
			
			// Compose the result 
			
			TransTcUploadComposer composer = new TransTcUploadComposer( );
			
			String jsonStr = composer.composeTransTcJSON( responseCode, translists );

			response.getWriter( ).write( jsonStr );
        }
		
        catch( Exception e )
        {
        	String msg = "Exception::TransTcUploadServlet.doPost() - "+e+"\r\n\n";

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


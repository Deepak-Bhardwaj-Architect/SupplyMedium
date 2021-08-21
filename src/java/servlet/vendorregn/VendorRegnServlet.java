package servlet.vendorregn;

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
import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.vendorregn.VendorRegnData;
import ctrl.vendorregn.VendorRegnController;
import utils.ErrorMaster;

/**
 * Servlet implementation class VendorRegnServlet
 */
@WebServlet("/VendorRegnServlet")
public class VendorRegnServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public String companyName_;
        
        private static ErrorMaster errorMaster_ = null;



	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VendorRegnServlet()
	{
		super( );
		// TODO Auto-generated constructor stub
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		companyName_ = "";
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost( HttpServletRequest request,
	        HttpServletResponse response ) throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		HttpSession session = request.getSession( );
		
		SessionData sessionData = (SessionData)session.getAttribute( "UserSessionData" );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		String jsonStr =  "";
		
		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );
		
		try
        {
			String requestType = request.getParameter( "RequestType" );
			//response.getWriter( ).write( "RequestType"+request.getParameter( "RequestType" ));
			errorMaster_.insert( "request type"+requestType);
			
			VendorRegnController vrCtrl	= new VendorRegnController( );
			
			getCompanyName( request, vrCtrl );
			
			VRJSONComposer jsonComposer = new VRJSONComposer( this.companyName_ );
			
			int responseCode = 0;
			
			if( requestType.equals( "ListMyRequests" ) ||
				requestType.equals( "ListOtherVendorsRequest" ) ) // For DataTable filling
			{
                                //System.out.print("that exists =================>");
				errorMaster_.insert("line 1" );
				List<VendorRegnData> vendorRegnDataList = new ArrayList<VendorRegnData>( );
				
				errLogger.logMsg( "Info::VendorRegnServlet.doPost() - Request for " + requestType + " for company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
				
				responseCode = vrCtrl.getVendorRequest( request, vendorRegnDataList );
				//System.out.print("vendorRegnDataList2 =================>"+vendorRegnDataList);
				jsonStr = jsonComposer.composeVendorListJSON( responseCode, vendorRegnDataList );
				//System.out.print("jsonStrss2 =================>"+jsonStr);
				vendorRegnDataList = null;
			}
			else if( requestType.equals( "ListNRVendors" ) )  // For searching non-registered vendors
			{
                                //System.out.print("enter========================>");
				errorMaster_.insert("line 2");
				List<RegnData> regnDataList = new ArrayList<RegnData>( );
				
				errLogger.logMsg( "Info::VendorRegnServlet.doPost() - Request for " + requestType + " for company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
				
				responseCode = vrCtrl.getNRVendors( request, regnDataList );
				//System.out.print("responseCode========================>"+responseCode);
				jsonStr = jsonComposer.composeNRListJSON( responseCode, regnDataList );
				//System.out.print("jsonStr========================>"+jsonStr);
				regnDataList = null;
			}
			else
			{                            
				errLogger.logMsg( "Info::VendorRegnServlet.doPost() - Request for " + requestType + " for company " +
						"<" + sessionData.companyRegnKeyStr_ + ">, " +
						"<" + sessionData.companyName_ + "> by user <" + sessionData.username_ + ">" );
				
				responseCode = vrCtrl.processVendors( request );
				                        System.out.println("responseCode======================>"+responseCode);
				jsonStr = jsonComposer.composeProcessResult( responseCode );
			}
			
			jsonComposer = null;
			
			vrCtrl = null;
			
			response.getWriter( ).write( jsonStr );
        }
        catch( Exception e )
        {
        	String msg = "Exception::VendorRegnServlet.doPost() - "+e+"\r\n\n";

			errLogger.logMsg( msg );
			
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";		
			
			//jsonMap.put( "message", "Request failed. Try again." );
			
			jsonStr = jsonStr + "\"message\" : \" Request failed. Try again. \" }";
			
			//String jsonStr = new Gson( ).toJson( jsonMap );
			
			response.getWriter( ).write( jsonStr );
			
        }
	}
	
	/*To intialize company Name*/
	
	public void getCompanyName( HttpServletRequest request, VendorRegnController vrCtrl  )
	{
		CompanyRegnKey vendorKey = new CompanyRegnKey( );
		
		vendorKey.companyPhoneNo_	= request.getParameter( "VendorKey" );
		
		companyName_ = vrCtrl.get( vendorKey ); 
	}
}

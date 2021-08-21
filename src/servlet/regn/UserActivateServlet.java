package servlet.regn;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.AppConfigReader;
import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import ctrl.regn.UserSignupController;

/**
 * Servlet implementation class UserActivateServlet
 */
@WebServlet("/UserActivateServlet")
public class UserActivateServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserActivateServlet()
	{
		super( );
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		UserSignupController signupCtrl = new UserSignupController( );

		String servletMsg = "Info::CompanyRegnServlet.doPost() -" +
				" User activation request";
		
		errLogger.logMsg( servletMsg );
		
		int result = signupCtrl.processRequest( request );

		String resultStr = ErrorCodeConfigReader.instance( )
		        .get( Integer.toString( result ) );

		System.out.println( "ResultString: " + resultStr );

		String resultType = "";

		String page = "";

		if( result == 710 )
		{
			servletMsg = "Info::CompanyRegnServlet.doPost() -" +
					" Request successful - Response code - " + result + " \r\n\n\n";
			
			errLogger.logMsg( servletMsg );
			
			resultType = "Confirmation";
			
			String jspRelativePath = AppConfigReader.instance( ).get( "JSP_PATH" );
			
			String jspAbsolutePath = jspRelativePath + "/index.jsp";
			
			String link = "<a href='"+jspAbsolutePath+"'>here</a>";
			
			String [] valueArr = {link};

			resultStr = ErrorCodeConfigReader.instance( ).get( result, valueArr );
			

			HttpSession session = request.getSession( );

			session.setMaxInactiveInterval( 0 );
		}
		else
		{
			servletMsg = "Info::CompanyRegnServlet.doPost() -" +
					" Request failed - Error code - " + result + " \r\n\n\n";
			
			errLogger.logMsg( servletMsg );
			
			resultType = "Failed";
			
			resultStr = ErrorCodeConfigReader.instance( )
			        .get( Integer.toString( result ) );

		}
		
		page = "Views/Utils/jsp/successresponse.jsp";

		response.sendRedirect( "CompanyRegnResultServlet?page=" + page + "&resulttype="
		        + resultType + "&result=" + resultStr );

		
	}

}

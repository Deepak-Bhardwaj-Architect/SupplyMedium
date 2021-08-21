package servlet.login;

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

import ctrl.login.LoginController;

/**
 * Servlet implementation class PasswordResetServlet
 */
@WebServlet("/PasswordResetServlet")
public class PasswordResetServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordResetServlet()
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
		// TODO Auto-generated method stub

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		LoginController loginCtrl = new LoginController( );

		String msg = "Info::PasswordResetServlet.doPost() - Request for Reset Password"; 
		
		errLogger.logMsg( msg );
		
		int result = loginCtrl.resetPassword( request );

		String resultStr = ErrorCodeConfigReader.instance( )
		        .get( Integer.toString( result ) );

		System.out.println( "ResultString: " + resultStr );

		String resultType = "";

		String page = "";

		if( result == 600 )
		{
			msg = "Info::PasswordResetServlet.doPost() - Request successful - Response code - " + result + "\r\n\n\n"; 
			
			errLogger.logMsg( msg );
			
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
			msg = "Info::PasswordResetServlet.doPost() - Request failed - Error code - " + result + "\r\n\n\n"; 
			
			errLogger.logMsg( msg );
			
			resultType = "Failed";
			
			resultStr = ErrorCodeConfigReader.instance( )
			        .get( Integer.toString( result ) );

		}
		
		page = "Views/Utils/jsp/successresponse.jsp";

		response.sendRedirect( "CompanyRegnResultServlet?page=" + page + "&resulttype="
		        + resultType + "&result=" + resultStr );

	}

}

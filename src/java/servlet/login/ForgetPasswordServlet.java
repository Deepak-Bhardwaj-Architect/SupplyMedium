package servlet.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import ctrl.login.LoginController;

/**
 * Servlet implementation class ForgetPasswordServlet
 */
@WebServlet("/ForgetPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForgetPasswordServlet()
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

		LoginController loginCtrl = new LoginController( );

		ErrorLogger errLogger = ErrorLogger.instance( );
		
		String msg = "Info::ForgetPasswordServlet.doPost() - Request to Forget Password";			        

		errLogger.logMsg( msg );
		
		int result = loginCtrl.passwordResetRequest( request );

		String resultStr = null;

		String resultType = "";

		String page = "";

		if( result == 500 )
		{
			msg = "Info::ForgetPasswordServlet.doPost() - Request successful - Response code - " + result + "\r\n\n\n";

			errLogger.logMsg( msg );
			
			resultType = "Confirmation";
			
			String email = request.getParameter( "email" );
			
			String [] valueArr = {email};

			resultStr = ErrorCodeConfigReader.instance( ).get( result, valueArr );

		}
		else
		{
			msg = "Info::ForgetPasswordServlet.doPost() - Request failed - Error code - " + result + "\r\n\n\n";

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

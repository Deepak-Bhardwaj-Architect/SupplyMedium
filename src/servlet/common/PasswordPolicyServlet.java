package servlet.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ctrl.login.LoginController;

/**
 * Servlet implementation class PasswordPolicyServlet
 */
@WebServlet("/PasswordPolicyServlet")
public class PasswordPolicyServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasswordPolicyServlet()
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

		String jsonStr = loginCtrl.getPassswordPolicies( request );

		System.out.println( "jsonStr=" + jsonStr );

		response.setContentType( "application/json" );
		response.setCharacterEncoding( "UTF-8" );
		response.getWriter( ).write( jsonStr );
	}

}

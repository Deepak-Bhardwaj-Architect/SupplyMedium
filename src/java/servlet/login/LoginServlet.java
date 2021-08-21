package servlet.login;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;

import ctrl.login.LoginController;

import core.login.SessionData;
import utils.ErrorMaster;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
        private static ErrorMaster errorMaster_ = null;




	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet()
	{
		super( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		// TODO Auto-gif( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );enerated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub

		RequestDispatcher view = request.getRequestDispatcher( "index.jsp" );
		view.forward( request, response );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
		ErrorLogger errorLogger = ErrorLogger.instance( );

		LoginController loginCtrl = new LoginController( );

		SessionData sessionData = new SessionData( );

		String servletMsg = "Info::LoginServlet.doPost() "
		        + "Login request from email <" + request.getParameter( "email" ) + ">";
		
		errorLogger.logMsg( servletMsg );
		System.out.print("request.getParameter( \"email\" )================================>"+request.getParameter( "email" )+"request.getParameter( \"email\" )==============================================>"+request.getParameter( "password" ));
		int result = loginCtrl.isValidLogin( request, sessionData );
                System.out.print("result================================>"+result);
		//Map<String, String> userDetails = new HashMap<String, String>( );

		errorMaster_.insert( "Login Response - " + result );

		if( result == 400 )
		{

			String errMsg = "Info::LoginServlet.doPost() "
			        + "Login successful for user <" + request.getParameter( "email" ) 
			        + " with Response code " + result + "\r\n\n\n";
			
			errorLogger.logMsg( errMsg );

			if( request.getParameter( "remember" ) != null
			        && request.getParameter( "remember" ).equals( "on" ) )
			{

				Cookie emailCookie = new Cookie( "email", sessionData.username_ );

				Cookie passwordCookie = new Cookie( "password", sessionData.password_ );

				emailCookie.setMaxAge( 3600 * 24 * 7 );

				passwordCookie.setMaxAge( 3600 * 24 * 7 );

				response.addCookie( emailCookie ); // response is an instance of
				                                   // type HttpServletReponse

				
				
				response.addCookie( passwordCookie );
			}

			// Creating a Session object and storing
			// HashMap into it

			HttpSession session = request.getSession( );

			//session.setAttribute( "user", userDetails );
			session.setAttribute( "UserSessionData", sessionData );
			
			errorMaster_.insert( "********************************"+sessionData.sessionExpireMin_ );

			session.setMaxInactiveInterval( sessionData.sessionExpireMin_ * 60 );
			
			response.sendRedirect( "companyadminhome.jsp" );
		}
		else if( result == 403 || result == 414 )
		{
			HttpSession session = request.getSession( );

			//session.setAttribute( "user", userDetails );
			session.setAttribute( "UserSessionData", sessionData );

			session.setMaxInactiveInterval( sessionData.sessionExpireMin_ * 60 );
			
			response.sendRedirect( "Views/Registration/jsp/changepassword.jsp" );
		}
		else
		{
			String errorStr = ErrorCodeConfigReader.instance( ).get( "" + result );

			//ErrorLogger errLogger_ = ErrorLogger.instance( );
			
			String errMsg = "Info::LoginServlet.doPost() "
			        + "Login failed for user <" + request.getParameter( "email" ) + ">."
					+ errorStr+". Error code - " + result + "\r\n\n\n";
			
			errorLogger.logMsg( errMsg );
			
			//errLogger_.logMsg( errorStr );

			Cookie[] cookies = request.getCookies( );
			
			  if (cookies != null)
			        for (int i = 0; i < cookies.length; i++) {
			            cookies[i].setValue("");
			            cookies[i].setMaxAge(0);
			            response.addCookie(cookies[i]);
			        }
			
			request.setAttribute( "error", errorStr );

			RequestDispatcher dispatcher = request.getRequestDispatcher( "index.jsp" );
			dispatcher.forward( request, response );

		}
	}
}

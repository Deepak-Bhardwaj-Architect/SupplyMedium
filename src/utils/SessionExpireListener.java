package utils;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import core.login.LoginManager;
import core.login.SessionData;
import core.regn.LoginData;

/**
 * Application Lifecycle Listener implementation class SessionExpireListener
 * 
 */
@WebListener
public class SessionExpireListener implements HttpSessionListener
{

	/**
	 * Default constructor.
	 */
	public SessionExpireListener()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated( HttpSessionEvent arg0 )
	{
		// TODO Auto-generated method stub
		
		System.out.println( "Session created" );
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	
	//To change login_status when the session expires
	
	public void sessionDestroyed( HttpSessionEvent arg0 )
	{
		System.out.println( "Session expired - "+arg0 );
		
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		String message = "";
		
		try
        {
			message = "Info::LogoutServlet:doPost-logout request";
			
			errorLogger.logMsg( message );
			
			HttpSession session = arg0.getSession( );
			
			SessionData sessionObj = (SessionData)session.getAttribute( "UserSessionData" );

			if( sessionObj == null )
			{
				return;
			}
			
			sessionObj.show( );
			
			//System.out.println(  );
			
			LoginData loginData = new LoginData( );

			loginData.emailid_ = sessionObj.username_;

			loginData.loginStatus_ = LoginStatus.loginStatus.NOTCONNECTED.getValue( );
			
			LoginManager loginMgr = LoginManager.instance( );
			
			loginMgr.updateStatus( loginData );
			
			
        }
        catch(  Exception e )
        {
        	message = "Exception::SessionExpireListener: - sessionDestroyed "+e;
			
        	//response.sendRedirect( "index.jsp" );
        	
			errorLogger.logMsg( message );
	        
        }
		
		
	}

}

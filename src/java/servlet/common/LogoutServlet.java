/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

package servlet.common;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.login.SessionData;
import core.regn.LoginData;
import ctrl.login.LoginController;

import utils.ErrorLogger;
import utils.LoginStatus;

/**
 * File:  LogoutServlet.java 
 *
 * Created on Apr 4, 2013 11:27:28 AM
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public LogoutServlet()
    {
        super();
        
    }

    /*
	 * Method : doGet( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It is used to logout the session 
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ErrorLogger errorLogger = ErrorLogger.instance( );
		
		String message = "";
		
		try
        {
			message = "Info::LogoutServlet:doPost-logout request";
			
			errorLogger.logMsg( message );
			
			HttpSession session = request.getSession( );
			
			Cookie[] cookies = request.getCookies( );
			
			  if (cookies != null)
			        for (int i = 0; i < cookies.length; i++) {
			            cookies[i].setValue("");
			            cookies[i].setMaxAge(0);
			            response.addCookie(cookies[i]);
			        }
			
			SessionData sessionObj = (SessionData)session.getAttribute( "UserSessionData" );

			if( sessionObj == null )
			{
				response.sendRedirect( "index.jsp" );
				return;
			}
			
			//sessionObj.show( );
			
			LoginData loginData = new LoginData( );

			loginData.emailid_ = sessionObj.username_;

			loginData.loginStatus_ = LoginStatus.loginStatus.NOTCONNECTED.getValue( );
			
			LoginController loginCtrl = new LoginController( );
			
			loginCtrl.updateStatus( loginData );
			
			session.invalidate( );
			
			//RequestDispatcher dispatcher = request.getRequestDispatcher( "index.jsp" );
			
			//dispatcher.forward( request, response );
			
			response.sendRedirect( "index.jsp" );
        }
        catch(  Exception e )
        {
        	message = "Exception::LogoutServlet:doPost-"+e;
			
        	response.sendRedirect( "index.jsp" );
        	
			errorLogger.logMsg( message );
	        
        }
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

}

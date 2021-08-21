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

package servlet.regn;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * File:  CompanyRegnResultServlet.java 
 *
 * Created on Jan 21, 2013 2:39:16 PM
 */


/*
 *Servlet implementation class CompanyRegnServlet.It is used to get the 
 *request from servlet and forward to corresponding jsp files.
 * 
 */


@WebServlet("/CompanyRegnResultServlet")
public class CompanyRegnResultServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	public CompanyRegnResultServlet()
	{
		super( );
		
	}

	/*
	 * Method : doGet( ) 
	 * 
	 * Input  : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It is used to get the request from other servlets 
	 * and forward to corresponding jsp file that is mention in request.
	 */
	
	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		String pageName = request.getParameter( "page" );

		RequestDispatcher dispatcher = request.getRequestDispatcher( pageName );

		dispatcher.forward( request, response );
	}

	
	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{
		
	}

}

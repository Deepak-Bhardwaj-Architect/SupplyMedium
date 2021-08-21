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
package ctrl.common;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;
import core.common.EmailData;
import core.common.Emailer;

/**
 * File:  EmailController.java 
 *
 * Created on Oct 2, 2013 4:10:12 PM
 */
public class EmailController
{

	/*
	 * Method : EmailController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public EmailController( )
	{
		
	
	}
	
	
	
	/*
	 * Method: sendmail
	 * 
	 * Input: HttpServletRequest obj
	 * 
	 * Return: int
	 * 
	 * Purpose:  send a mail to customer 
	 */
	public int sendMail( HttpServletRequest request )
	{
		EmailData mailData=new EmailData();
		
		EmailDataConverter converter = new EmailDataConverter( );
		
		int result = converter.getEmailData( request, mailData );
		
		String requestType = request.getParameter( "RequestType" );
		
		if(requestType.equals( "SendHistoryMail" ))
		{
			mailData.show( );

			if( result != 0 )
			{
				ErrorLogger.instance( ).logMsg(
				        "Error::EmailController.sendMail() - Unable to parse data" );

				return 16051;
			}

			result = sendHistoryMail( mailData );
		}
		else if (requestType.equals( "SendInviteMail" ))
		{
			mailData.show( );

			if( result != 0 )
			{
				ErrorLogger.instance( ).logMsg( "Error::EmailController.sendMail() - Unable to parse data" );
				
				return 16051;
			}
			result =sendInviteMail(mailData);
		}
		else if (requestType.equals( "SendContactUsMail" )) // It is from external page contact us page
		{
			mailData.show( );
			
			if( result != 0 )
			{
				ErrorLogger.instance( ).logMsg( "Error::EmailController.sendMail() - Unable to parse data" );
				
				return 16051;
			}
			result =sendContactUsmail(mailData);
		}
				
		return result;
	}
	
	
	
	public int sendHistoryMail( EmailData mailData )
	{
				
		int result=0;
		
		Emailer mailer=new Emailer( );
		
		result =mailer.send( mailData );
		
		System.out.println( result);
	
		mailer=null;
		
		return result;
	}
	
	
	/*
	 * Method: sendInvitemail
	 * 
	 * Input: HttpServletRequest obj
	 * 
	 * Return: int
	 * 
	 * Purpose:  send  invitation mail to customer 
	 */
	public int sendInviteMail(EmailData mailData)
	{
		int result=0;
		
		Emailer mailer=new Emailer( );
		
		result= mailer.sendInvite( mailData );
		
		System.out.println( result);
		
		mailer=null;
		//System.out.println( result);
		return result;
		
	}
	
	/*
	 * Method: sendContactUsmail
	 * 
	 * Input: HttpServletRequest obj
	 * 
	 * Return: int
	 * 
	 * Purpose:  send contact us mail to particular compnay admin
	 * ( this request from external page contact us page)
	 */
	public int sendContactUsmail(EmailData mailData)
	{
		int result=0;
		
		Emailer mailer=new Emailer( );
		
		result= mailer.sendContactUsMail(mailData);
		
		System.out.println( result);
		
		mailer=null;
		//System.out.println( result);
		return result;
		
	}
	
}

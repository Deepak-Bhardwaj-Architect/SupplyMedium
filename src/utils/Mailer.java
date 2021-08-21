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
package utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * File:  CountryData.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */


//This core data class is related to countries table in db

public class Mailer
{

	private boolean    debug   = false;

	// private ErrorLogger errLogger_ = null;

	private String     huname_ = "";
	private String     hupass_ = "";

	private String     from_, message_, sub_, host_;

	private String [ ] toVect_;

	// private static Mailer mailerObj_ =null;

	/** Creates a new instance of Mailer */

	public Mailer()
	{
	}

	public void composeAndSendMail( String [ ] to, String subject, String message )
	{

		String from = AppConfigReader.instance( ).get( "EMAIL_FROM" );

		String host = AppConfigReader.instance( ).get( "EMAIL_HOST" );

		String userName = AppConfigReader.instance( ).get( "EMAIL_USERNAME" );

		String password = AppConfigReader.instance( ).get( "EMAIL_PASSWORD" );

		sendMail( from, to, host, userName, password, subject, message );
	}

	public void sendMail( String from, String [ ] toV, String host, String huname,
	        String hupass, String sub, String message )
	{

		this.huname_ = huname;
		this.hupass_ = hupass;
		this.from_ = from;
		this.toVect_ = toV;
		this.message_ = message;
		this.sub_ = sub;
		this.host_ = host;

		try
		{
			SendMail sendmail = new SendMail( );
			sendmail.start( );
		}
		catch( Exception ex )
		{
			System.err.println( ex );
			// ex.printStackTrace();
			// errLogger_ = ErrorLogger.instance();
			// errLogger_.logMsg("Mailer :: sendMail "+ex);
		}

	}

	private class SendMail extends Thread
	{
		public SendMail()
		{

		}

		@Override
		public void run( )
		{
			try
			{

				Properties prop = new Properties( );
				prop.put( "mail.smtp.starttls.enable", "true" );
				prop.put( "mail.smtp.host", host_ );
				prop.put( "mail.smtp.auth", "true" );

				Authenticator auth = new SMTPAuthenticator( );

				Session session = Session.getDefaultInstance( prop, auth );
				session.setDebug( debug );

				Message msg = new MimeMessage( session );
				
				try
				{

					InternetAddress fromAdd = new InternetAddress( from_ );
					
					msg.setFrom( fromAdd );
					

					InternetAddress [ ] toAdd = new InternetAddress[toVect_.length];
					for( int i = 0; i < toVect_.length; i++ )
					{
						toAdd[i] = new InternetAddress( toVect_[i] );
					}
					msg.setRecipients( Message.RecipientType.TO, toAdd );

					msg.setSubject( sub_ );

					msg.setContent( message_, "text/html" );
					
					msg.setSentDate( new Date( ) );
					
					Transport transport = session.getTransport( "smtp" );

					transport.connect( host_, from_, hupass_ );

					transport.sendMessage( msg, msg.getAllRecipients( ) );

					transport.close( );

				}
				catch( Exception ex )
				{
					ex.printStackTrace( );

				}

			}
			catch( Exception ex )
			{
				// errLogger_ = ErrorLogger.instance();
				// errLogger_.logMsg("Mailer :: sendMail "+ex);
				System.out.println( "Exception" + ex.getMessage( ) );
			}
		}

	}

	private class SMTPAuthenticator extends javax.mail.Authenticator
	{

		@Override
		public PasswordAuthentication getPasswordAuthentication( )
		{
			String username = huname_;
			String password = hupass_;
			return new PasswordAuthentication( username, password );
		}
	}

}

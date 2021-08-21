/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any. - - - - - - - - - - -
 * - - - - - - - - - - - - - - - - - - - - - - - - - -
 */
package utils;

import com.amazonaws.services.simpleemail.AWSJavaMailTransport;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * File: CountryData.java
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */
//This core data class is related to countries table in db
public class Mailer {

    private boolean debug = false;

//	 private ErrorLogger errLogger_ = null;
    private String huname_ = "";
    private String hupass_ = "";

    private String from_, message_, sub_, host_;

    private String[] toVect_;
    private ErrorMaster _errLogger = null;

	// private static Mailer mailerObj_ =null;
    /**
     * Creates a new instance of Mailer
     */
    public Mailer() {
        _errLogger = new ErrorMaster();
    }

    public void composeAndSendMail(String[] to, String subject, String message) {

        String from = AppConfigReader.instance().get("EMAIL_FROM");

        String host = AppConfigReader.instance().get("EMAIL_HOST");

        String userName = AppConfigReader.instance().get("EMAIL_USERNAME");

        String password = AppConfigReader.instance().get("EMAIL_PASSWORD");

                //_errLogger.insert(message);
//		sendMail( "supplymedium@gmail.com", to, "smtp.gmail.com", "supplymedium", "SupplyMedium!1", subject, message );
        sendMail(from, to, host, userName, password, subject, message);
    }

    public void sendMail(String from, String[] toV, String host, String huname,
            String hupass, String sub, String message) {

        this.huname_ = huname;
        this.hupass_ = hupass;
        this.from_ = from;
        this.toVect_ = toV;
        this.message_ = message;
        this.sub_ = sub;
        this.host_ = host;

        try {
            SendMail sendmail = new SendMail();
            sendmail.start();
        } catch (Exception ex) {
            _errLogger.insert("Exeption at Mailer :: sendMail " + ex);
        }

    }

    public void mail() {
        String FROM = "supplymedium2@gmail.com";   // Replace with your "From" address. This address must be verified.
        String TO = this.toVect_[0];  // Replace with a "To" address. If you have not yet requested
        // production access, this address must be verified.

        String BODY = this.message_;
        String SUBJECT = this.sub_;

        // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
        String SMTP_USERNAME = "AKIAJSFKZZTEEXQF57IQ";  // Replace with your SMTP username.
        String SMTP_PASSWORD = "Apw8Qe9CX1TIR3qNEtiTRq5ssSqNhMJ/mGMC5hdHKdGS";  // Replace with your SMTP password.

        // Amazon SES SMTP host name. This example uses the us-east-1 region.
        String HOST = "email-smtp.us-east-1.amazonaws.com";
        Transport transport = null;

            // Port we will connect to on the Amazon SES SMTP endpoint. We are choosing port 25 because we will use
        // STARTTLS to encrypt the connection.
        int PORT = 25;

        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);

                // Set properties indicating that we want to use STARTTLS to encrypt the connection.
        // The SMTP session will begin on an unencrypted connection, and then the client
        // will issue a STARTTLS command to upgrade to an encrypted connection.
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        try {

            // Create a Session object to represent a mail session with the specified properties. 
            Session session1 = Session.getDefaultInstance(props);

            // Create a message with the specified information. 
            MimeMessage msg = new MimeMessage(session1);
            msg.setFrom(new InternetAddress(FROM));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
            msg.setSubject(SUBJECT);
            msg.setContent(BODY, "text/html");

            // Create a transport.        
            transport = session1.getTransport();

            // Send the message.
            System.out.println("Attempting to send an email through the Amazon SES SMTP interface...");

            // Connect to Amazon SES using the SMTP username and password you specified above.
            transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

            // Send the email.
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }

    }

    private class SendMail extends Thread {

        public SendMail() {

        }

        @Override
        public void run() {
            try {
                //mail();

				Properties prop = new Properties( );
				prop.put( "mail.smtp.starttls.enable", "true" );
				prop.put( "mail.smtp.host", host_ );
				prop.put( "mail.smtp.auth", "true" );
                         _errLogger.insert("we are at 115");
//                                Properties props = new Properties();
//
//                                props.put("mail.transport.protocol", "smtps");
//                                props.put("mail.smtps.host", 465);
//                                props.put("mail.smtps.auth", "true");
//                                // props.put("mail.smtps.quitwait", "false");
//
//                                Session mailSession = Session.getDefaultInstance(props);
//                                mailSession.setDebug(true);
//                                Transport transport = mailSession.getTransport();
//
//                                MimeMessage message = new MimeMessage(mailSession);
//                                message.setSubject("Testing SMTP-SSL");
//                                message.setContent("This is a test", "text/plain");
//
//                                message.addRecipient(Message.RecipientType.TO,
//                                     new InternetAddress(toVect_[0]));
//
//                                transport.connect
//                                  (host_, 465, huname_, hupass_);
//
//                                transport.sendMessage(message,
//                                    message.getRecipients(Message.RecipientType.TO));
//                                transport.close();

				Authenticator auth = new SMTPAuthenticator( );

				Session session = Session.getDefaultInstance( prop, auth );
				session.setDebug( debug );
                         _errLogger.insert("we are at 145");

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
                         _errLogger.insert("we are at 175");

					transport.close( );

				}
				catch( Exception ex )
				{
					_errLogger.insertError("Mailer 1 :: sendMail "+ex);

				}
            } catch (Exception ex) {
                _errLogger.insertError("Mailer 2 :: sendMail " + ex);
            }
        }

    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            String username = huname_;
            String password = hupass_;
            _errLogger.insert("we are at 203");
            return new PasswordAuthentication(username, password);
        }
    }

}

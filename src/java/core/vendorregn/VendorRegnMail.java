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
package core.vendorregn;

import java.io.File;

import utils.AppConfigReader;
import utils.ErrorLogger;
import utils.HTMLMailComposer;
import utils.Mailer;
import utils.XMLMailConfigReader;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import db.regn.CompanyRegnTable;
import utils.ErrorMaster;

/**
 * File:  VendorRegnMail.java 
 *
 * Created on Aug 1, 2013 3:57:10 PM
 */

/*This is the helper class for VendorRegn to send 
 * mail for each process in vendor registrations*/

public class VendorRegnMail
{
	/*Constructor*/
	
	String mailType_;
	String inquiry_;
        //private static ErrorMaster errorMaster_ = null;


	
	public VendorRegnMail( )
	{
		mailType_ = "";
		inquiry_ = "";
                //if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	public VendorRegnMail( String mailType, String inquiry )
	{
		mailType_ = mailType;
		
		inquiry_ = inquiry;
	}
	
	/**/
		
	public void send( CompanyRegnKey fromKey, CompanyRegnKey toKey )
	{
		CompanyRegnTable regnTable = new CompanyRegnTable( );

		String comanyName = regnTable.find( fromKey );
		
		UserProfileKey toEmail = new UserProfileKey( );
		
		XMLMailConfigReader mailConfigReader = new XMLMailConfigReader( );
		
		regnTable.find( toKey, toEmail );
		
		regnTable = null;
		
		String mailContentPath = System.getProperty( "catalina.home" ) + "/"+ AppConfigReader.instance( ).get( "MAIL_CONTENT_PATH" );
		
		//errorMaster_.insert( mailContentPath + "/VRMail/" + mailType_ + ".xml" );
		
		//ErrorLogger.instance( ).logMsg( "\n\n\nInfo::VendorRegnMail.send( ) - " + mailContentPath + "/VRMail/" + mailType_ + ".xml\n\n\n" );

		
		String subject = mailConfigReader.getSubject( new File( mailContentPath + "/VRMail/" + mailType_ + ".xml" ) ) ;
		
		
		String content = mailConfigReader.getContent( new File( mailContentPath + "/VRMail/" + mailType_ + ".xml" ) );
		
		sendVRMail( toEmail.email_, comanyName, subject, content, inquiry_ );
		
		toEmail = null; mailConfigReader = null;
	}
	
	/*
	 * Method : sendVRMail( ) 
	 * 
	 * Input : Company name, content 
	 * 
	 * Return : none
	 * 
	 * Purpose: To send notify email for vendor registration process
	 */

	private void sendVRMail( String emailId, String companyName, String subject, String content, String inquiry )
	{
		//errorMaster_.insert( "Email id = " + emailId + "; Company Name = " + companyName + "; Content = " + content  );
		
		//ErrorLogger.instance( ).logMsg( "\n\n\nEmail id = " + emailId + "; Company Name = " + companyName + "; Content = " + content + "\n\n\n" );
		
		String [ ] to = { emailId };

		HTMLMailComposer composer = new HTMLMailComposer( );
		
		String message = composer.composeVRMail( companyName, content, inquiry );
		
		//ErrorLogger.instance( ).logMsg( "\n\n\nInfo::VendorRegnMail.sendVRMail( ) - " + message.toString( ) + "\n\n\n" );
		
		composer = null;
				  
		Mailer mailerObj = new Mailer( );
		
		//errorMaster_.insert( "-----Sending Mail-----" );
		//ErrorLogger.instance( ).logMsg( "\n\n\nInfo::VendorRegnMail.sendVRMail( ) - Sending Mail\n\n\n" );
		
		//ErrorLogger.instance( ).logMsg( "\n\n\nInfo::VendorRegnMail.sendVRMail( ) - Subject - "+ subject + "\n\n\n");
		//ErrorLogger.instance( ).logMsg( "\n\n\nInfo::VendorRegnMail.sendVRMail( ) - Content - "+ content + "\n\n\n" );
		//ErrorLogger.instance( ).logMsg( "\n\n\nInfo::VendorRegnMail.sendVRMail( ) - Inquiry - "+ inquiry + "\n\n\n");
		mailerObj.composeAndSendMail( to, subject, message.toString( ) );
		
		//ErrorLogger.instance( ).logMsg( "\n\n\nInfo::VendorRegnMail.sendVRMail( ) - Mail Sent\n\n\n" );
		
		//errorMaster_.insert( "-----Mail sent-----" );
		
		mailerObj = null;
	}
}

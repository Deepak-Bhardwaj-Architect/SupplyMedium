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
package core.common;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.regn.UserProfileKey;
import db.regn.CompanyRegnTable;
import utils.HTMLMailComposer;
import utils.InfoMailer;
import utils.Mailer;

/**
 * File:  Emailer.java 
 *
 * Created on Oct 2, 2013 4:16:54 PM
 */
public class Emailer
{
	
	
		
	/*
	 * Method : Emailer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public Emailer( )
	{
		
	}
	
	
	/*
	 * Method : send
	 * 
	 * Input  : EmailData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to send the mail 
	 */
    public int send( EmailData mailData )
    {
    	String subject="Info from SupplyMedium";
    	
    	InfoMailer mailer= new InfoMailer( );
    
    	int i = 0;
    	
    	String[ ]  customerKeysArray = new String[ mailData.customerKeys_.size( ) ];
    	
    	for( UserProfileKey profileKey : mailData.customerKeys_ )
        {
	        customerKeysArray[ i ]= profileKey.toString( );
	        
	        i++;
        }
    	
    	
    	mailer.composeAndSendMail( mailData.senderName_, mailData.senderKey_.toString( ), 
    								customerKeysArray, subject, mailData.message_ );
    	
	    return 16050;
    }
    
    
	/*
	 * Method : sendInvite
	 * 
	 * Input  : EmailData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to send the mail 
	 */
    
    public int sendInvite(EmailData mailData )
    {
    	String subject="Invitation from SupplyMedium";
    	
		Mailer mailer =new Mailer( );
		
		int i=0;
    	
		String[ ]customerKeysArray=new String [mailData.customerKeys_.size( ) ];
		
		System.out.println( "mailer:customerKeys count="+ mailData.customerKeys_.size( ));
		
		for( UserProfileKey profileKey : mailData.customerKeys_ )
        {
			customerKeysArray[ i ]= profileKey.toString( );
	        
	        i++;
        }
		
		HTMLMailComposer mailComposer=new HTMLMailComposer( );
		
		String companyName=mailData.companyName_;
		
		String senderName=mailData.senderName_;
		
		String message=mailComposer.composeInvitationMail( customerKeysArray,companyName,senderName );
		
		mailer.composeAndSendMail ( customerKeysArray, subject, message );
    	
	    return 16050;	
				
    }
    
    
    /*
	 * Method : sendContactUsMail
	 * 
	 * Input  : EmailData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to send the mail 
	 */
    
    public int sendContactUsMail(EmailData mailData )
    {
    
    	
    	String subject="Contact inquiry from "+mailData.senderName_;
    	
    	
    	String message="The following user had shown interest in you, with the information below\n\n";
    	
    	message=message+"Name : "+mailData.senderName_+"\n ";
    	message=message+"Email : "+mailData.senderKey_.toString( )+"\n\n";
    	message=message+"Address : "+mailData.message_;
    	
        	
    	InfoMailer mailer= new InfoMailer( );
    
    	int i = 0;
    	
    	String[ ]  customerKeysArray = new String[ mailData.customerKeys_.size( ) ];
    	
    	for( UserProfileKey profileKey : mailData.customerKeys_ )
        {
    		
    		RegnData regnData = new RegnData();
    		
    		CompanyRegnKey regnKey = new CompanyRegnKey();
    		
    		regnKey.companyPhoneNo_ = profileKey.toString( );
    		
    		CompanyRegnTable regnTbl = new CompanyRegnTable();	       
	        
	        regnTbl.find(regnKey, regnData);
    		
	        customerKeysArray[ i ]= regnData.emailId_;
	        
	        i++;
        }
    	
    			
		
		
    	
    	mailer.composeAndSendMail( mailData.senderName_, mailData.senderKey_.toString( ), 
    								customerKeysArray, subject, message );
    	
	    return 16050;
				
    }

}

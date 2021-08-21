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
package core.trans;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import utils.ErrorLogger;
import utils.HTMLMailComposer;
import utils.Mailer;
import utils.MailerSync;
import utils.MailerSynchronize;
import utils.MailerWithAttachment;
import utils.NotificationType;
import utils.PathBuilder;
import utils.StringHolder;
import utils.TransType;

import core.companyprofile.CompanyProfileDetails;
import core.edi.RFQEDICreator;
import core.history.CustomerHistory;
import core.history.TransactionHistoryData;
import core.notification.NotificationCenterData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.useracctmgmt.UserNotificationSettings;
import db.notification.NotificationTable;
import db.trans.RFQItemTable;
import db.trans.RFQTable;
import db.trans.TransInquireTable;
import db.trans.TransTable;
import utils.ErrorMaster;

/**
 * File:  RFQ.java 
 *
 * Created on 20-Jun-2013 3:03:48 PM
 */
public class RFQ
{

	ErrorLogger errLogger_;
        
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : RFQ -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public RFQ()
	{
		errLogger_ = ErrorLogger.instance( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method : add
	 * 
	 * Input  : RFQData object
	 * 
	 * Return : int value (This indicate whether RFQ created successful or not)
	 * 
	 * Purpose: This is method is used to created the new RFQ in supply medium.
	 * First it created the RFQ in supply medium then process the following tasks
	 * 1.convert the RFQData object into EDI format and store to company doc dir.
	 * 2.Send the notification to TO vendor about the RFQ request in one of the two ways.
	 * The way of communicate is define in user account management settings. From using that
	 * we send any notification
	 * 	a) Through mail (To vendor admin email id). This mail content the HTML format.
	 * 		RFQ form in html format and attachment of RFQ pdf document send to admin mailid.
	 * 	b)Through SMS
	 * 3.Send the Supply Medium invitation mail if supplier is outside supplier(ie not registered with 
	 * SupplyMedium)
	 */
	
	public int add( RFQData rfqData)
	{
		// add the RFQTable entry
		
		RFQTable rfqtable = new RFQTable( );
		
		TSM tsm = new TSM( );
		
		tsm.init( rfqData.transId_, TransType.transType.RFQ.getValue( ) );
		
		rfqData.status_ = tsm.state( ).toString( );
		
		tsm = null;
		
		int result = rfqtable.insert( rfqData );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.add() - Unable to insert into RFQ table" );
			
			rfqtable = null;
			
			return 8001;
		}
		
		// add the TransTable entry
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
	
		result = transTable.getMax( );
		
		if( result == -1 )
		{
			rfqData.transId_ = 1;
		}
		else if( result == -2 || result == -3 )
		{
			errLogger_.logMsg( "Error::RFQ.add() - Unable to get max trans id from trans table" );
			
			transTable = null;
			
			return 8005;
		}
		else 
		{
			rfqData.transId_ = result + 1;
		}
		
		errorMaster_.insert( "GetMax Value = " + result );
		
		generateTransData( rfqData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
				
		transNotification.add( transData );
				
		transNotification = null;
		
		transTable = null;transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.add() - Unable to insert into transaction table" );
			
			rfqtable = null;
			
			return 8002;
		}
		
		// update RFQTable with transaction id
		
		//rfqData.transId_ = transData.transId_;
		
		result = rfqtable.update( rfqData );
		
		rfqtable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.add() - Unable to update RFQ table" );
					
			return 8003;
		}

		// add the RFQItemTable entry
		
		for( RFQItemData rfqItemData :  rfqData.RFQItems_ )
        {
	        rfqItemData.RFQId_ = rfqData.RFQId_;
	        
	        rfqItemData.transId_ = rfqData.transId_;
        }
		
		RFQItemTable itemTable = new RFQItemTable( );
		          
		result = itemTable.insert( rfqData.RFQItems_ );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.add() - Unable to insert item list in RFQItem table" );
			
			return 8004;
		}
		
		// convert RFQData to EDI format
		
		createRFQEDI( rfqData );
		
		// Get the User account details for deciding the way of communication(Mail or SMS)
		
		// Send the Mail
		
		result = sendRFQMail( rfqData );
		
		/*UserNotificationSettings notificationSettings=new UserNotificationSettings( );
		
		boolean canSendMail=notificationSettings.canSendNotification(rfqData.userTo_);
		
		errorMaster_.insert( "can send mail"+canSendMail);
		
		if(canSendMail)
		{
			errorMaster_.insert( "----------------------RFQ mail---------------------------");
			sendRFQMail( rfqData );
		}*/
		
		if( result == -1 )
		{
			return 8006;
			
		}
		else if( result == -2 )
		{
			return 8007;
		}
		
		
		// Send the SMS
		
		// Send the SupplyMedium invitation mail
		
		
		return 8000; // Success
	}
	
	/*Helper method for add(rfqData)*/
	private void generateTransData( RFQData rfqData, TransData transData )
	{
		transData.action_ 		= "";
		transData.from_ 		= rfqData.from_;
		transData.to_			= rfqData.to_;
		transData.status_		= rfqData.status_;
		transData.transType_	= TransType.transType.RFQ.getValue( );
		transData.transTypeId_	= rfqData.RFQId_;
		transData.userFrom_		= rfqData.userFrom_;
		transData.userTo_		= rfqData.userTo_;
		transData.transId_		= rfqData.transId_;
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : regnKey, userKey and list of RFQData object
	 * 
	 * Return : int value (This indicate whether we get all RFQ successfully or not)
	 * 
	 * Purpose: This is used to fetch the all the pending RFQ transaction for the 
	 * particular user of the company. For fetching the transaction we do the following
	 * steps.
	 * 1. Using regnkey and userKey we get the all the RFQ request
	 * 2. Using the RFQId we fetch the each and every RFQ items
	 * 3. Using the RFQId we fetch the transaction details for each and every RFQ
	 */
	public int get( CompanyRegnKey regnKey, UserProfileKey userKey, List<RFQData> RFQList )
	{
		// Get the all the RFQ's using RegnKey and UserKey
		
		RFQTable rfqTable = new RFQTable( );
		
		RFQData rfqData = new RFQData( );
		
		rfqData.from_ = regnKey;
		
		rfqData.userFrom_ = userKey;
		
		int result = rfqTable.find(  regnKey,  userKey, RFQList );
		
		rfqTable = null; rfqData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.get() - Unable to fetch RFQ from RFQTable" );
			
			return 8021;
		}
		
		// Get the RFQ items for each RFQ
		
		RFQItemTable rfqItemTable = new RFQItemTable( );
		
		for( RFQData data : RFQList )
        {
			RFQItemData itemData = new RFQItemData( );
			
			List<RFQItemData> rfqItemList = new ArrayList<RFQItemData>( ); 
			
			itemData.RFQId_	= data.RFQId_;
			
			itemData.transId_ = data.transId_;
			
	        result = rfqItemTable.find( itemData, rfqItemList );
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::RFQ.get() - Unable to fetch RFQItemList from RFQItemTable" );
	        }
	        data.RFQItems_ = rfqItemList;
	        
	        rfqItemList = null;
	        
	        itemData = null;
        }
		
		rfqItemTable = null;
		
		// Get the Transaction details for each RFQ
		
		TransTable transTable = new TransTable( );
		
		for( RFQData data : RFQList )
        {
			TransData transData = new TransData( );
			
			List<TransData> transDataList = new ArrayList<TransData>( );
			
			transData.transTypeId_	= data.RFQId_;
			
			transData.transType_	= TransType.transType.RFQ.getValue( );
			
			//transData.transId_ = data.transId_;
			
			result = transTable.find( transData, transDataList );
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::RFQ.get() - Unable to fetch transDataList from TransTable" );
			}
			
			data.transList_ = transDataList;
			
			transData = null;
			
			transDataList = null;
        }
		
		transTable = null;
		
		// Get the Inquire details for each RFQ
		
		TransInquireTable inquireTable = new TransInquireTable( );
		
		for( RFQData data : RFQList )
        {
	        TransInquireData inquireData = new TransInquireData( );
	        
	        List<TransInquireData> inquireDataList = new ArrayList<TransInquireData>( );
	        
	        inquireData.transId_	= data.transId_;
	        
	        inquireData.transType_	= TransType.transType.RFQ.getValue( );
	        
	        inquireData.transTypeId_ = data.RFQId_;
	        
	        result = inquireTable.find( inquireData, inquireDataList );
	        
	        if( result != 0 )
			{
				errLogger_.logMsg( "Error::RFQ.get() - Unable to fetch transInquireList from TransInquireTable" );
			}
			
			data.transInquireList_ = inquireDataList;
			
			inquireData = null;
			
			inquireDataList = null;
        }
		
		inquireTable = null;
		
		CompanyProfileDetails profileDetails = new CompanyProfileDetails( );
		
		for( RFQData data : RFQList )
        {
	        profileDetails.getFullProfile( data.from_, data.fromCompanyProfileData_ );
	        
	        profileDetails.getFullProfile( data.to_, data.toCompanyProfileData_ );
        }
		
		profileDetails = null;
		
		return 8020; //Success
	}
	
	
	/*
	 * Method : update
	 * 
	 * Input  : RFQData object
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is used to update the already created RFQ's
	 */
	public int update( RFQData rfqData )
	{
		//Update rfq table update
		
		RFQTable rfqTable = new RFQTable( );
		
		TSM tsm = new TSM( );
		
		tsm.init( rfqData.transId_, TransType.transType.RFQ.getValue( ) );
		
		rfqData.status_ = tsm.next( rfqData.action_ ).toString( );
		
		int result = rfqTable.update( rfqData );
		
		rfqTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.update() - Unable to update RFQ in RFQTable" );
			
			return 8031;
		}
		
		// Transaction new entry
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( rfqData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
						
		transNotification.add( transData );
						
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.update() - Unable to insert into trans table" );
			
			return 8032;
		}
		
		// remove rfq items table
		
		RFQItemTable itemTable = new RFQItemTable( );
		
		result = itemTable.delete( rfqData.RFQId_ );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.update() - Unable to delete from rfq item table" );
			
			itemTable = null;
			
			return 8033;
		}
		
		// new item insert
		
		for( RFQItemData rfqItemData :  rfqData.RFQItems_ )
		 {
			     rfqItemData.RFQId_ = rfqData.RFQId_;
			        
			     rfqItemData.transId_ = rfqData.transId_;
		 }
		
		
		result = itemTable.insert( rfqData.RFQItems_ );
		
		itemTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.update() - Unable to insert into rfq items table" );
			
			return 8034;
		}
		
		
		return 8030; // Success
		
	}
	
	/*
	 * Method : changeStatus
	 * 
	 * Input  : RFQData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to change the status of the RFQ. Following is the 
	 * steps to change the status of the RFQ.
	 * 1.Using the action of the transaction get the next status of the RFQ using STATE MACHINE
	 * 2.Change the status for RFQ
	 * 3.Add the new entry for transaction for RFQ
	 * 
	 */
	public int changeStatus( RFQData rfqData )
	{
		errorMaster_.insert("rfq core start");
		// Get the next state using the action
		
		TSM tsm = new TSM( );
		
		tsm.init( rfqData.transId_, TransType.transType.RFQ.getValue( ) );
		
		rfqData.status_ = tsm.next( rfqData.action_).toString( );
		
		//rfqData.status_ = "RFQAccepted";
		
		tsm = null;
		
		// Update the status in RFQTable
		
		RFQTable rfqTable = new RFQTable( );
		
		int result = rfqTable.updateStatus( rfqData.RFQId_, rfqData.userFrom_, rfqData.status_ );
		
		rfqTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.changeStatus() - Unable to change the status" );
			
			return 8011;
		}
		
		// Add new entry in TransTable
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( rfqData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
						
		transNotification.add( transData );
						
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::RFQ.changeStatus() - Unable to insert into trans table" );
			
			return 8012;
		}
		
		if( rfqData.status_.equals( "Rejected" ))
			addTransHistory( rfqData );
		errorMaster_.insert( "rfq core End");
		return 8010;
	}
	
	
	// Used to add the history for this transaction
	
	private int addTransHistory( RFQData rfqData )
	{
		CustomerHistory customerHistory = new CustomerHistory( );

		TransactionHistoryData transHistoryData = new TransactionHistoryData( );

		transHistoryData.transId_ = rfqData.transId_;

		transHistoryData.fromRegnKey_ = rfqData.from_;

		transHistoryData.toRegnKey_ = rfqData.to_;

		transHistoryData.amount_ = 0.00;

		transHistoryData.status_ = rfqData.status_;

		int result = customerHistory.addTransHistory( transHistoryData );

		return result;
	}
	
	// This method is used to create and store the RFQ EDI document from given RFQData object
	private int createRFQEDI( RFQData rfqData )
	{
		CompanyProfileDetails profileDetails = new CompanyProfileDetails( );
		
	    profileDetails.getFullProfile( rfqData.from_, rfqData.fromCompanyProfileData_ );
	        
	    profileDetails.getFullProfile( rfqData.to_, rfqData.toCompanyProfileData_ );
		
		profileDetails = null;
		
		
		RFQEDICreator rfqediCreator = new RFQEDICreator( );
		
		int result = rfqediCreator.create( rfqData );
		
		rfqediCreator  = null;
		
		return result;
	}
	
	// Method is used to send the RFQ notification mail to receiver with attachement of EDI Document.
	private int sendRFQMail( RFQData rfqData )
	{
		try
        {
			
			
			String[] to = new String[1];
			
			if( rfqData.isOutsideSupplier_ == 1 && rfqData.outsideSupplierEmailFlag_ == 1)
			{
				errorMaster_.insert("Outside Supplier");
				
				
				to[0] = rfqData.outsideSupplierEmail_;
				
				
				String subject = "RFQ (RFQ"+rfqData.transId_+") "+rfqData.fromCompanyProfileData_.companyName_;
				
				String trasnMsg = "The company "+rfqData.fromCompanyProfileData_.companyName_+
						" who is currently using SupplyMedium, has sent you an RFQ via email. Please respond to this email or register for free at SupplyMedium to get the benefits of transacting with your vendors.";
				
				HTMLMailComposer composer = new HTMLMailComposer( );
				
				//String message = composer.composeTransactionMail( trasnMsg );
                                String message = composer.composeTransactionMail2( trasnMsg,rfqData.RFQItems_ );

				
				String fileName = "rfq.xml";
				
			
				StringHolder localPath = new StringHolder( );
				
				PathBuilder pathBuilder = new PathBuilder( );
				
				int result = pathBuilder.getEDIFilesDirPath( rfqData.transId_, localPath );
				
				
				MailerSync mailerSync  = new MailerSync( );
				
				result = mailerSync.composeAndSendMail( to, subject, message, localPath.value+"/"+fileName, fileName );
				
				
				return result;
			}
			else
			{
                                System.out.println("rfqData.userTo_"+rfqData.userTo_);
				to[0] = rfqData.userTo_.toString();
				
				
				String subject = "RFQ (RFQ"+rfqData.transId_+") from "+rfqData.fromCompanyProfileData_.companyName_;
				
				String trasnMsg = "The company "+rfqData.fromCompanyProfileData_.companyName_+
						" who is currently using SupplyMedium, has sent you an RFQ via email. Please respond to this email or register for free at SupplyMedium to get the benefits of transacting with your vendors.";
				
				HTMLMailComposer composer = new HTMLMailComposer( );
				
				//String message = composer.composeTransactionMail( trasnMsg );
                                String message = composer.composeTransactionMail2( trasnMsg,rfqData.RFQItems_ );

				
				String fileName = "rfq.xml";
				
			
				StringHolder localPath = new StringHolder( );
				
				PathBuilder pathBuilder = new PathBuilder( );
				
				int result = pathBuilder.getEDIFilesDirPath( rfqData.transId_, localPath );
				
				
				MailerSync mailerSync  = new MailerSync( );
				
				result = mailerSync.composeAndSendMail( to, subject, message, localPath.value+"/"+fileName, fileName );
				
				
				return result;
			}
			
			
			
			
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Error::RFQ.sendRFQMail() - Unable send email "+e.getMessage( ) );
        	
        	return -1;
        }
		
		
		
	}
}

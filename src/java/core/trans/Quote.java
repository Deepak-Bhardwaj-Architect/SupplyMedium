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

import java.util.ArrayList;
import java.util.List;

import utils.ErrorLogger;
import utils.HTMLMailComposer;
import utils.Mailer;
import utils.MailerSync;
import utils.MailerWithAttachment;
import utils.PathBuilder;
import utils.StringHolder;
import utils.TransType;
import core.companyprofile.CompanyProfileDetails;
import core.edi.QuoteEDICreator;
import core.edi.RFQEDICreator;
import core.history.CustomerHistory;
import core.history.TransactionHistoryData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.useracctmgmt.UserNotificationSettings;
import db.trans.QuoteItemTable;
import db.trans.QuoteTable;
import db.trans.RFQTable;
import db.trans.TransInquireTable;
import db.trans.TransTable;
import utils.ErrorMaster;

/**
 * File:  Quote.java 
 *
 * Created on 20-Jun-2013 3:04:14 PM
 */
public class Quote
{
    private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : Quote -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public Quote()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	ErrorLogger errLogger_;
	
	/*
	 * Method : add
	 * 
	 * Input  : QuoteData object
	 * 
	 * Return : int value (This indicate whether Quote created successful or not)
	 * 
	 * Purpose: This is method is used to created the new Quote in supply medium.
	 * First this create the Quote in supply medium then process the following tasks
	 * 1.convert the QuoteData object into EDI format and store to company doc dir.
	 * 2.Send the notification to TO vendor about the RFQ request in one of the two ways.
	 * The way of communicate is define in user account management settings. From using that
	 * we send any notification
	 * 	a) Through mail (To vendor admin email id). This mail content the HTML format.
	 * 		Quote form in html format and attachment of Quote pdf document send to admin mailid.
	 * 	b)Through SMS
	 * 3.Send the Supply Medium invitation mail if supplier is outside supplier(ie not registered with 
	 * SupplyMedium)
	 */
	
	public int add( QuoteData quoteData)
	{
		// add the QuoteTable entry
		
		QuoteTable quoteTable = new QuoteTable( );
		
		TSM tsm = new TSM( );
		
		tsm.init( quoteData.transId_, TransType.transType.QUOTE.getValue( ) );
		
		if( quoteData.transId_ == -1 )  // For new quotation
		{
			quoteData.status_ = tsm.state( ).toString( );
		}
		else // Create quote for RFQ
		{
			quoteData.status_ = tsm.next( quoteData.action_ ).toString( );
		}
		
		
		tsm = null;
		
		int result = quoteTable.insert( quoteData );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.add() - Unable to insert into Quote table" );
			
			quoteTable = null;
			
			return 8101;
		}
		
		// add the TransTable entry
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
	
		if( quoteData.transId_ == -1 )
		{
			result = transTable.getMax( );
		
    		if( result == -1 )
    		{
    			quoteData.transId_ = 1;
    		}
    		else if( result == -2 || result == -3 )
    		{
    			errLogger_.logMsg( "Error::Quote.add() - Unable to get max trans id from trans table" );
    			
    			transTable = null;
    			
    			return 8105;
    		}
    		else 
    		{
    			quoteData.transId_ = result + 1;
    		}
		}
		
		errorMaster_.insert( "GetMax Value = " + result );
		
		generateTransData( quoteData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
						
		transNotification.add( transData );
						
		transNotification = null;
		
		transTable = null;transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.add() - Unable to insert into transaction table" );
			
			quoteTable = null;
			
			return 8102;
		}
		
		// update QuoteTable with transaction id
		
		//quoteData.transId_ = transData.transId_;
		
		result = quoteTable.update( quoteData );
		
		quoteTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.add() - Unable to update Quote table" );
					
			return 8103;
		}

		// add the RFQItemTable entry
		
		for( QuoteItemData quoteItemData :  quoteData.quoteItems_ )
        {
			quoteItemData.quoteId_ = quoteData.quoteId_;
	        
			quoteItemData.transId_ = quoteData.transId_;
        }
		
		QuoteItemTable itemTable = new QuoteItemTable( );
		
		result = itemTable.insert( quoteData.quoteItems_ );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.add() - Unable to insert item list in QuoteItemTable" );
			
			return 8104;
		}
		
		// Change the status of quote creation in rfq table
		changeRFQStatus( quoteData.transId_ );
		
		// convert QuoteData to EDI format
		
		createQuoteEDI( quoteData );
		
		// Get the User account details for deciding the way of communication(Mail or SMS)
		
		// Send the Mail
		
		result = sendQuoteMail( quoteData );
		
	/*	UserNotificationSettings notificationSettings=new UserNotificationSettings( );
		
		boolean canSendMail=notificationSettings.canSendNotification(quoteData.userTo_);
		
		if(canSendMail)
		{
			sendQuoteMail( quoteData );
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
		
		
		return 8100; // Success
	}
	
	/*Helper method for add( quoteData )*/
	private void generateTransData( QuoteData quoteData, TransData transData )
	{
		transData.action_ 		= "";
		transData.from_ 		= quoteData.from_;
		transData.to_			= quoteData.to_;
		transData.status_		= quoteData.status_;
		transData.transType_	= TransType.transType.QUOTE.getValue( );
		transData.transTypeId_	= quoteData.quoteId_;
		transData.userFrom_		= quoteData.userFrom_;
		transData.userTo_		= quoteData.userTo_;
		transData.transId_		= quoteData.transId_;
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : regnKey, userKey and list of QuoteData object
	 * 
	 * Return : int value (This indicate whether we get all Quote successfully or not)
	 * 
	 * Purpose: This is used to fetch the all the pending Quote transaction for the 
	 * particular user of the company. For fetching the transaction we do the following
	 * steps.
	 * 1. Using regnkey and userKey we get the all the Quote request
	 * 2. Using the QuoteId we fetch the each and every Quote items
	 * 3. Using the QuoteId we fetch the transaction details for each and every Quote
	 */
	public int get( CompanyRegnKey regnKey, UserProfileKey userKey, List<QuoteData> quoteList )
	{
		// Get the all the Quote's using RegnKey and UserKey
		
		QuoteTable quoteTable = new QuoteTable( );
		
		QuoteData quoteData = new QuoteData( );
		
		quoteData.from_ = regnKey;
		
		quoteData.userFrom_ = userKey;
		
		int result = quoteTable.find(  regnKey,  userKey, quoteList );
		
		quoteTable = null; quoteData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.get() - Unable to fetch Quote from QuoteTable" );
			
			return 8121;
		}
		
		// Get the Quote items for each Quote
		
		QuoteItemTable quoteItemTable = new QuoteItemTable( );
		
		for( QuoteData data : quoteList )
        {
			QuoteItemData itemData = new QuoteItemData( );
			
			List<QuoteItemData> quoteItemList = new ArrayList<QuoteItemData>( );
			
			itemData.quoteId_	= data.quoteId_;
			
			itemData.transId_ = data.transId_;
			
	        result = quoteItemTable.find( itemData, quoteItemList );
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::Quote.get() - Unable to fetch QuoteItemList from QuoteItemTable" );
	        }
	        
	        data.quoteItems_ = quoteItemList;
	        
	        quoteItemList = null;
	        
	        itemData = null;
        }
		
		quoteItemTable = null;
		
		// Get the Transaction details for each Quote
		
		TransTable transTable = new TransTable( );
		
		for( QuoteData data : quoteList )
        {
			TransData transData = new TransData( );
			
			List<TransData> transDataList = new ArrayList<TransData>( );
			
			transData.transTypeId_	= data.quoteId_;
			
			transData.transType_	= TransType.transType.QUOTE.getValue( );
			
			//transData.transId_ = data.transId_;
			
			result = transTable.find( transData, transDataList );
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::Quote.get() - Unable to fetch transDataList from TransTable" );
			}
			
			data.transList_ = transDataList;
			
			transData = null;
			
			transDataList = null;
        }
		
		transTable = null;
		
		// Get the Inquire details for each RFQ
		
		TransInquireTable inquireTable = new TransInquireTable( );
		
		for( QuoteData data : quoteList )
        {
	        TransInquireData inquireData = new TransInquireData( );
	        
	        List<TransInquireData> inquireDataList = new ArrayList<TransInquireData>( );
	        
	        inquireData.transId_	= data.transId_;
	        
	        inquireData.transType_	= TransType.transType.QUOTE.getValue( );
	        
	        inquireData.transTypeId_ = data.quoteId_;
	        
	        result = inquireTable.find( inquireData, inquireDataList );
	        
	        if( result != 0 )
			{
				errLogger_.logMsg( "Error::Quote.get() - Unable to fetch transInquireList from TransInquireTable" );
			}
			
			data.transInquireList_ = inquireDataList;
			
			inquireData = null;
			
			inquireDataList = null;
        }
		
		inquireTable = null;
		
		CompanyProfileDetails profileDetails = new CompanyProfileDetails( );
		
		for( QuoteData data : quoteList )
        {
	        profileDetails.getFullProfile( data.from_, data.fromCompanyProfileData_ );
	        
	        profileDetails.getFullProfile( data.to_, data.toCompanyProfileData_ );
        }
		
		profileDetails = null;
		
		return 8120; //Success
	}
	
	
	/*
	 * Method : update
	 * 
	 * Input  : QuoteData object
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is used to update the already created Quote's
	 */
	public int update( QuoteData quoteData )
	{
		//Update quote table update
		
		QuoteTable quoteTable = new QuoteTable( );
		
		TSM tsm = new TSM( );
		
		tsm.init( quoteData.transId_, TransType.transType.QUOTE.getValue( ) );
		
		quoteData.status_ = tsm.next( quoteData.action_ ).toString( );
		
		int result = quoteTable.update( quoteData );
		
		quoteTable = null;
		
	
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.update() - Unable to update Quote in QuoteTable" );
			
			return 8131;
		}
		
		
		// Transaction new entry
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( quoteData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
						
		transNotification.add( transData );
						
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.update() - Unable to insert into trans table" );
			
			return 8132;
		}
		
		// remove rfq items table
		
		QuoteItemTable itemTable = new QuoteItemTable( );
		
		result = itemTable.delete( quoteData.quoteId_ );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.update() - Unable to delete from quote item table" );
			
			itemTable = null;
			
			return 8133;
		}
		
		// new item insert
		
		for( QuoteItemData quoteItemData :  quoteData.quoteItems_ )
		 {
			     quoteItemData.quoteId_ = quoteData.quoteId_;
			        
			     quoteItemData.transId_ = quoteData.transId_;
		 }
		
		
		result = itemTable.insert( quoteData.quoteItems_ );
		
		itemTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.update() - Unable to insert into quote items table" );
			
			return 8134;
		}
		
		
				
		return 8130; // Success
		
	}
	
	/*
	 * Method : changeStatus
	 * 
	 * Input  : QuoteData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to change the status of the Quote. Following is the 
	 * steps to change the status of the Quote.
	 * 1.Using the action of the transaction get the next status of the Quote using STATE MACHINE
	 * 2.Change the status for Quote
	 * 3.Add the new entry for transaction for Quote
	 * 
	 */
	public int changeStatus( QuoteData quoteData )
	{
		// Get the next state using the action
		
		TSM tsm = new TSM( );
		
		tsm.init( quoteData.transId_, TransType.transType.QUOTE.getValue( ) );
		
		quoteData.status_ = tsm.next( quoteData.action_).toString( );
		
		tsm = null;
		

		// Update the status in RFQTable
		
		QuoteTable quoteTable = new QuoteTable( );
		
		int result = quoteTable.updateStatus( quoteData.quoteId_, quoteData.userFrom_, quoteData.status_ );
		
		quoteTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.changeStatus() - Unable to change the status" );
			
			return 8111;
		}
		
		// Add new entry in TransTable
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( quoteData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
						
		transNotification.add( transData );
						
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Quote.changeStatus() - Unable to insert into trans table" );
			
			return 8112;
		}
		
		if( quoteData.status_.equals( "Rejected" ))
			addTransHistory( quoteData );
		
		return 8110;
	}
	
	
	
	// Used to change the RFQ table is_quote_created flag
	
	private int changeRFQStatus( long transId )
	{
		RFQTable rfqTable = new RFQTable( );
		
		int result = rfqTable.setQuoteCreatedFlag( transId );
		
		rfqTable = null;
		
		return result;
	}
	
	// Used to add the history for this transaction
	
	private int addTransHistory( QuoteData quoteData )
	{
		CustomerHistory customerHistory = new CustomerHistory( );

		TransactionHistoryData transHistoryData = new TransactionHistoryData( );

		transHistoryData.transId_ 		= quoteData.transId_;

		transHistoryData.fromRegnKey_ 	= quoteData.from_;

		transHistoryData.toRegnKey_ 	= quoteData.to_;

		transHistoryData.amount_ 		= quoteData.totalPrice_;

		transHistoryData.status_ 		= quoteData.status_;

		int result = customerHistory.addTransHistory( transHistoryData );

		return result;
	}
	
	// This method is used to create and store the Quote EDI document from given QuoteData object
	private int createQuoteEDI( QuoteData quoteData )
	{
		
		CompanyProfileDetails profileDetails = new CompanyProfileDetails( );
		
	    profileDetails.getFullProfile( quoteData.from_, quoteData.fromCompanyProfileData_ );
		        
	    profileDetails.getFullProfile( quoteData.to_, quoteData.toCompanyProfileData_ );
			
		profileDetails = null;
		
		QuoteEDICreator quoteediCreator = new QuoteEDICreator( );
		
		int result = quoteediCreator.create( quoteData );
			
		quoteediCreator  = null;
			
		return result;
	}
		
	// Method is used to send the Quote notification mail to receiver with attachment of EDI Document.
	private int sendQuoteMail( QuoteData quoteData )
	{

		try
        {
			
			String[] to = new String[1];
			
			if( quoteData.isOutsideSupplier_ == 1&& quoteData.outsideSupplierEmailFlag_ == 1 )
			{
				errorMaster_.insert("Outside Supplier");
				
				
				to[0] = quoteData.outsideSupplierEmail_;
				
				
				String subject = "Quote from "+quoteData.fromCompanyProfileData_.companyName_;
				
				String trasnMsg = "The company "+quoteData.fromCompanyProfileData_.companyName_+
						" who is currently using SupplyMedium, has sent you an Quote via email. Please respond to this email or register for free at SupplyMedium to get the benefits of transacting with your vendors.";
				
				HTMLMailComposer composer = new HTMLMailComposer( );
				
				String message = composer.composeTransactionMail( trasnMsg );

				
				String fileName = "quote.xml";
				
			
				StringHolder localPath = new StringHolder( );
				
				PathBuilder pathBuilder = new PathBuilder( );
				
				int result = pathBuilder.getEDIFilesDirPath( quoteData.transId_, localPath );
				
				
				MailerSync mailerSync  = new MailerSync( );
				
				result = mailerSync.composeAndSendMail( to, subject, message, localPath.value+"/"+fileName, fileName );
				
				
				return result;
			}
			else
			{
				/*if( rfqData.userTo_ == null )
				{
					to[0] = rfqData.toCompanyProfileData_.email_;
				}
				else 
				{
					to[0] = rfqData.userTo_.toString( );
				}*/
				
				return 0;
			}
			
			
			
			
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Error::Quote.sendRFQMail() - Unable send email "+e.getMessage( ) );
        	
        	return -1;
        }
	}

}

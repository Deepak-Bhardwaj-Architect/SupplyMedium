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
import java.util.Calendar;
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
import core.edi.InvoiceEDICreator;
import core.edi.POEDICreator;
import core.history.CustomerHistory;
import core.history.TransactionHistoryData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.useracctmgmt.UserNotificationSettings;
import db.trans.InvoiceItemTable;
import db.trans.InvoiceTable;
import db.trans.POTable;
import db.trans.QuoteTable;
import db.trans.TransInquireTable;
import db.trans.TransTable;
import utils.ErrorMaster;

/**
 * File:  Invoice.java 
 *
 * Created on 20-Jun-2013 3:04:41 PM
 */
public class Invoice
{

	ErrorLogger errLogger_;
        
        private static ErrorMaster errorMaster_ = null;


	
	/*
	 * Method : Invoice -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public Invoice( )
	{
		errLogger_ = ErrorLogger.instance( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	
	 /* Method : add
	 * 
	 * Input  : InvoiceData object
	 * 
	 * Return : int value (This indicate whether Invoice created successful or not)
	 * 
	 * Purpose: This is method is used to created the new Invoice in supply medium.
	 * First this create the Invoice in supply medium then process the following tasks
	 * 1.convert the InvoiceData object into EDI format and store to company doc dir.
	 * 2.Send the notification to TO vendor about the Invoice request in one of the two ways.
	 * The way of communicate is define in user account management settings. From using that
	 * we send any notification
	 * 	a) Through mail (To vendor admin email id). This mail content the HTML format.
	 * 		PO form in html format and attachment of Invoice pdf document send to admin mailid.
	 * 	b)Through SMS
	 * 3.Send the Supply Medium invitation mail if buyer is outside buyer(ie not registered with 
	 * SupplyMedium) */
	 
	
	public int add( InvoiceData invoiceData)
	{
		// add the POTable entry
		
		InvoiceTable invoiceTable = new InvoiceTable( );
		
		TSM tsm = new TSM( );
		
		tsm.init( invoiceData.transId_, TransType.transType.INVOICE.getValue( ) );
		
		if( invoiceData.transId_ == -1 )  // For new invoice
		{
			invoiceData.status_ = tsm.state( ).toString( );
		}
		else // Create invoice for PO
		{
			invoiceData.status_ = tsm.next( invoiceData.action_ ).toString( );
		}
		
		tsm = null;
		
		if( genInvoiceNum( invoiceData ) != 0 )
		{
			return 8306; //Unable to generate invoice number
		}
		
		int result = invoiceTable.insert( invoiceData );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.add() - Unable to insert into invoice table" );
			
			invoiceTable = null;
			
			return 8301;
		}
		
		// add the TransTable entry
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
	
		if( invoiceData.transId_ == -1 )
		{
			result = transTable.getMax( );
		
    		if( result == -1 )
    		{
    			invoiceData.transId_ = 1;
    		}
    		else if( result == -2 || result == -3 )
    		{
    			errLogger_.logMsg( "Error::PurchaseOrder.add() - Unable to get max trans id from trans table" );
    			
    			transTable = null;
    			
    			return 8305;
    		}
    		else 
    		{
    			invoiceData.transId_ = result + 1;
    		}
		}
		
		errorMaster_.insert( "GetMax Value = " + result );
		
		generateTransData( invoiceData, transData );
		
		result = transTable.insert( transData );
		
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
				
		transNotification.add( transData );
				
		transNotification = null;
		
		transTable = null;transData= null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.add() - Unable to insert into transaction table" );
			
			invoiceTable = null;
			
			return 8302;
		}
		
		// update InvocieTable with transaction id
		
		//invoiceData.transId_ = transData.transId_;
		
		result = invoiceTable.update( invoiceData );
		
		invoiceTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.add() - Unable to update invoice table" );
					
			return 8303;
		}

		// add the InvoiceItemTable entry
		
		for( InvoiceItemData invoiceItemData :  invoiceData.invoiceItems_ )
        {
			invoiceItemData.invoiceId_ = invoiceData.invoiceId_;
	        
			invoiceItemData.transId_ = invoiceData.transId_;
        }
		
		InvoiceItemTable itemTable = new InvoiceItemTable( );
		
		result = itemTable.insert( invoiceData.invoiceItems_ );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.add() - Unable to insert item list in InvoiceItemTable" );
			
			return 8304;
		}
		
		// Change the status of invocie creation in po table
		changePOStatus( invoiceData.transId_ );
		
		// convert InvoiceData to EDI format
		
		createInvoiceEDI( invoiceData );
		
		// Get the User account details for deciding the way of communication(Mail or SMS)
		
		// Send the Mail
		
		result = sendInvoiceMail( invoiceData );
		
		/*UserNotificationSettings notificationSettings=new UserNotificationSettings( );
		
		boolean canSendMail=notificationSettings.canSendNotification(invoiceData.userTo_);
		
		if(canSendMail)
		{
			sendInvoiceMail( invoiceData );
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
		
		
		
		return 8300; // Success
	}
	
	//Helper method for add( invoiceData )
	private void generateTransData( InvoiceData invoiceData, TransData transData )
	{
		transData.action_ 		= "";
		transData.from_ 		= invoiceData.from_;
		transData.to_			= invoiceData.to_;
		transData.status_		= invoiceData.status_;
		transData.transType_	= TransType.transType.INVOICE.getValue( );
		transData.transTypeId_	= invoiceData.invoiceId_;
		transData.userFrom_		= invoiceData.userFrom_;
		transData.userTo_		= invoiceData.userTo_;
		transData.transId_		= invoiceData.transId_;
	}
	
	
	 /* Method : get
	 * 
	 * Input  : regnKey, userKey and list of InvoiceData object
	 * 
	 * Return : int value (This indicate whether we get all Invoice successfully or not)
	 * 
	 * Purpose: This is used to fetch the all the pending Invoice transaction for the 
	 * 
	 * particular user of the company. For fetching the transaction we do the following
	 * steps.
	 * 1. Using regnkey and userKey we get the all the Invoice request
	 * 2. Using the InvoiceId we fetch the each and every Invoice items
	 * 3. Using the InvoiceId we fetch the transaction details for each and every Invoice */
	 
	public int get( CompanyRegnKey regnKey, UserProfileKey userKey, List<InvoiceData> invoiceList )
	{
		// Get the all the Invoice's using RegnKey and UserKey
		
		errorMaster_.insert( "fetch invoice core" );
		
		InvoiceTable invoiceTable = new InvoiceTable( );
		
		InvoiceData invoiceData = new InvoiceData( );
		
		invoiceData.from_ = regnKey;
		
		invoiceData.userFrom_ = userKey;
		
		int result = invoiceTable.find(  regnKey,  userKey, invoiceList );
		
		invoiceTable = null; invoiceData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.get() - Unable to fetch Invoice from InvoiceTable" );
			
			return 8321;
		}
		
		// Get the Invoice items for each Invoice
		
		InvoiceItemTable invoiceItemTable = new InvoiceItemTable( );
		
		for( InvoiceData data : invoiceList )
        {
			InvoiceItemData itemData = new InvoiceItemData( );
			
			List<InvoiceItemData> invoiceItemList	= new ArrayList<InvoiceItemData>( );
			
			itemData.invoiceId_		= data.invoiceId_;
			
			itemData.transId_ = data.transId_;
			
			result = invoiceItemTable.find( itemData, invoiceItemList );
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::Invoice.get() - Unable to fetch InvoiceItemList from InvoiceItemTable" );
	        }
	        
	        data.invoiceItems_ = invoiceItemList;
	        
	        invoiceItemList = null;
	        
	        itemData = null;
        }
		
		invoiceItemTable = null;
		
		// Get the Transaction details for each Invoice
		
		TransTable transTable = new TransTable( );
		
		for( InvoiceData data : invoiceList )
        {
			TransData transData = new TransData( );
			
			List<TransData> transDataList = new ArrayList<TransData>( );
			
			transData.transTypeId_	= data.invoiceId_;
			
			transData.transType_	= TransType.transType.INVOICE.getValue( );
			
			//transData.transId_ = data.transId_;
			
			result = transTable.find( transData, transDataList );
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::Invoice.get() - Unable to fetch transDataList from TransTable" );
			}
			
			data.transList_ = transDataList;
			
			transData = null;
			
			transDataList = null;
        }
		
		transTable = null;
		
		// Get the Inquire details for each Invoice
		
		TransInquireTable inquireTable = new TransInquireTable( );
		
		for( InvoiceData data : invoiceList )
        {
	        TransInquireData inquireData = new TransInquireData( );
	        
	        List<TransInquireData> inquireDataList = new ArrayList<TransInquireData>( );
	        
	        inquireData.transId_	= data.transId_;
	        
	        inquireData.transType_	= TransType.transType.INVOICE.getValue( );
	        
	        inquireData.transTypeId_ = data.invoiceId_;
	        
	        result = inquireTable.find( inquireData, inquireDataList );
	        
	        if( result != 0 )
			{
				errLogger_.logMsg( "Error::Invoice.get() - Unable to fetch transInquireList from TransInquireTable" );
			}
			
			data.transInquireList_ = inquireDataList;
			
			inquireData = null;
			
			inquireDataList = null;
        }
		
		inquireTable = null;
		
		CompanyProfileDetails profileDetails = new CompanyProfileDetails( );
		
		for( InvoiceData data : invoiceList )
        {
	        profileDetails.getFullProfile( data.from_, data.fromCompanyProfileData_ );
	        
	        profileDetails.getFullProfile( data.to_, data.toCompanyProfileData_ );
        }
		
		profileDetails = null;
		
		return 8320; //Success
	}
	
	
	
	 /* Method : update
	 * 
	 * Input  : InvoiceData object
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is used to update the already created Invoice's*/
	 
	public int update( InvoiceData invoiceData )
	{
		//Update invoice table update
		
		InvoiceTable invoiceTable = new InvoiceTable( );
		
		TSM tsm = new TSM( );
		
		tsm.init( invoiceData.transId_, TransType.transType.INVOICE.getValue( ) );
		
		invoiceData.status_ = tsm.next( invoiceData.action_ ).toString( );
		
		errorMaster_.insert( " status="+ invoiceData.status_);
		
		int result = invoiceTable.update( invoiceData );
		
		invoiceTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.update() - Unable to update invoice in InvoiceTable" );
			
			return 8331;
		}
		
		// Transaction new entry
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( invoiceData, transData );
		
		result = transTable.insert( transData );
		
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
				
		transNotification.add( transData );
				
		transNotification = null;
		
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.update() - Unable to insert into trans table" );
			
			return 8332;
		}
		
		// remove invoice items table
		
		InvoiceItemTable itemTable = new InvoiceItemTable( );
		
		result = itemTable.delete( invoiceData.invoiceId_ );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.update() - Unable to delete from invoice item table" );
			
			itemTable = null;
			
			return 8333;
		}
		
		// new item insert
		
		for( InvoiceItemData invoiceItemData :  invoiceData.invoiceItems_ )
		 {
			     invoiceItemData.invoiceId_ = invoiceData.invoiceId_;
			        
			     invoiceItemData.transId_ = invoiceData.transId_;
		 }
		
		
		result = itemTable.insert( invoiceData.invoiceItems_ );
		
		itemTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.update() - Unable to insert into invoice items table" );
			
			return 8334;
		}
		
	
				
		return 8330; // Success
		
	}
	
	
	 /* Method : changeStatus
	 * 
	 * Input  : InvoiceData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to change the status of the Invoice. Following is the 
	 * steps to change the status of the Invoice.
	 * 1.Using the action of the transaction get the next status of the Invoice using STATE MACHINE
	 * 2.Change the status for Invoice
	 * 3.Add the new entry for transaction for Invoice
	 **/ 
	 
	public int changeStatus( InvoiceData invoiceData )
	{
		// Get the next state using the action
		
		TSM tsm = new TSM( );
		
		tsm.init( invoiceData.transId_, TransType.transType.INVOICE.getValue( ) );
		
		invoiceData.status_ = tsm.next( invoiceData.action_).toString( );
		
		tsm = null;
		

		// Update the status in InvoiceTable
		
		InvoiceTable invoiceTable = new InvoiceTable( );
		
		int result = invoiceTable.updateStatus( invoiceData.invoiceId_, invoiceData.userFrom_, invoiceData.status_ );
		
		invoiceTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.changeStatus() - Unable to change the status" );
			
			return 8311;
		}
		
		// Add new entry in TransTable
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( invoiceData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
						
		transNotification.add( transData );
						
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::Invoice.changeStatus() - Unable to insert into trans table" );
			
			return 8312;
		}
		
		
		// User to archive the completed or rejected transaction
		addTransHistory( invoiceData );
		
		
		return 8310;
	}
	
	
	// Used to change the PO table is_invoice_created flag
	
	private int changePOStatus( long transId )
	{
		POTable poTable = new POTable( );
				
		int result = poTable.setInvoiceCreatedFlag( transId );
				
		poTable = null;
				
		return result;
	}
	
	/*This method is used to generate the invoice 
*/
	
	private int genInvoiceNum( InvoiceData invoiceData )
	{
		InvoiceTable invoiceTable = new InvoiceTable( );
		
		StringHolder invoiceNum = new StringHolder( );
		
		String invoiceNumStr = "";
		
		int result = invoiceTable.find( invoiceData.from_, invoiceNum );
		
		if( result == 0 )
		{
			String[] firstSplit = invoiceNum.value.split( "-" );
			
			if( firstSplit.length < 2 )
			{
				return -1;
			}
			
			String[] secondSplit = firstSplit[1].split( "/" ); 
			
			if( secondSplit.length < 1)
			{
				return -1;
			}
			
			Calendar c = Calendar.getInstance( );

			c.setTimeInMillis( System.currentTimeMillis( ) );
			
			//To be updated later
			invoiceNumStr = c.get( Calendar.YEAR ) + "-" + ( Integer.valueOf( secondSplit[0] )+1 );
			
			invoiceData.invoiceNo_ = invoiceNumStr;
		}
		else if( result == -1 )
		{
			Calendar c = Calendar.getInstance( );

			c.setTimeInMillis( System.currentTimeMillis( ) );

			invoiceNumStr = c.get( Calendar.YEAR ) + "-" + 1;
			
			invoiceData.invoiceNo_ = invoiceNumStr;
			
			result = 0;
		}
		else 
		{
			errLogger_.logMsg( "Error::Invoice.genInvoiceNum() - Unable to fetch last invoice num" );
			
			result = -1;
		}
		
		return result;
	}
	
	
	// Used to add the history for this transaction
	
	private int addTransHistory( InvoiceData invoiceData )
	{
		errorMaster_.insert( "Invoice data status="+invoiceData.status_ );
		
		if( invoiceData.status_.equals( "InvoiceAccepted" ))
		{
			CustomerHistory customerHistory = new CustomerHistory( );
			
			TransactionHistoryData transHistoryData = new TransactionHistoryData( );
			
			InvoiceTable invoiceTable=new InvoiceTable( );
			
			transHistoryData.transId_  		= invoiceData.transId_;
			
			transHistoryData.fromRegnKey_	= invoiceData.from_;
			
			transHistoryData.toRegnKey_		= invoiceData.to_;
			
			//transHistoryData.amount_		= invoiceData.totalPrice_;
			
			transHistoryData.status_		= "Accepted";
			
			int result= invoiceTable.findAmount( invoiceData.invoiceId_, invoiceData );
					
			transHistoryData.amount_		= invoiceData.totalPrice_;
			
			
			
			InvoiceItemTable invoiceItemTable=new InvoiceItemTable( );	
			
			InvoiceItemData invoiceItemData=new InvoiceItemData( );
			
			int result2=invoiceItemTable.getCurrency(invoiceData.invoiceId_,invoiceItemData);
			
			transHistoryData.currency_=invoiceItemData.currency_;
			

			
			
			result = customerHistory.addTransHistory( transHistoryData );
		}
		
		
		return 0;

	}
	
	// This method is used to create and store the Invoice EDI document from given InvoiceData object
	private int createInvoiceEDI( InvoiceData invoiceData )
	{
		
		CompanyProfileDetails profileDetails = new CompanyProfileDetails( );
		
		profileDetails.getFullProfile( invoiceData.from_, invoiceData.fromCompanyProfileData_ );
				        
		profileDetails.getFullProfile( invoiceData.to_, invoiceData.toCompanyProfileData_ );
					
		profileDetails = null;
					
		
		InvoiceEDICreator invoiceediCreator = new InvoiceEDICreator( );
				
		int result = invoiceediCreator.create( invoiceData );
					
		invoiceediCreator  = null;
					
		return result;
	}
				
	// Method is used to send the Invoice notification mail to receiver with attachment of EDI Document.
	private int sendInvoiceMail( InvoiceData invoiceData )
	{
		
		try
        {
			
			String[] to = new String[1];
			
			if( invoiceData.isOutsideBuyer_ == 1&& invoiceData.outsideSupplierEmailFlag_ == 1 )
			{
				errorMaster_.insert("Outside Supplier");
				
				
				to[0] = invoiceData.outsideBuyerEmail_;
				
				
				String subject = "Invoice (INV"+invoiceData.transId_+") from "+invoiceData.fromCompanyProfileData_.companyName_;
				
				String trasnMsg = "The company "+invoiceData.fromCompanyProfileData_.companyName_+
						" who is currently using SupplyMedium, has sent you an Invoice via email. Please respond to this email or register for free at SupplyMedium to get the benefits of transacting with your vendors.";
				
				HTMLMailComposer composer = new HTMLMailComposer( );
				
				String message = composer.composeTransactionMail5( trasnMsg,invoiceData);

				
				String fileName = "invoice.xml";
				
			
				StringHolder localPath = new StringHolder( );
				
				PathBuilder pathBuilder = new PathBuilder( );
				
				int result = pathBuilder.getEDIFilesDirPath( invoiceData.transId_, localPath );
				
				
				MailerSync mailerSync  = new MailerSync( );
				
				result = mailerSync.composeAndSendMail( to, subject, message, localPath.value+"/"+fileName, fileName );
				
				
				return result;
			}
			else
			{
				to[0] = invoiceData.userTo_.toString();
				
				
				String subject = "Invoice (INV"+invoiceData.transId_+") from "+invoiceData.fromCompanyProfileData_.companyName_;
				
				String trasnMsg = "The company "+invoiceData.fromCompanyProfileData_.companyName_+
						" who is currently using SupplyMedium, has sent you an Invoice via email. Please respond to this email or register for free at SupplyMedium to get the benefits of transacting with your vendors.";
				
				HTMLMailComposer composer = new HTMLMailComposer( );
				
				String message = composer.composeTransactionMail5( trasnMsg,invoiceData);

				
				String fileName = "invoice.xml";
				
			
				StringHolder localPath = new StringHolder( );
				
				PathBuilder pathBuilder = new PathBuilder( );
				
				int result = pathBuilder.getEDIFilesDirPath( invoiceData.transId_, localPath );
				
				
				MailerSync mailerSync  = new MailerSync( );
				
				result = mailerSync.composeAndSendMail( to, subject, message, localPath.value+"/"+fileName, fileName );
				
				
				return result;
			}
			
			
			
			
			
        }
        catch( Exception e )
        {
        	errLogger_.logMsg( "Error::Invoice.sendRFQMail() - Unable send email "+e.getMessage( ) );
        	
        	return -1;
        }
	
	}
}

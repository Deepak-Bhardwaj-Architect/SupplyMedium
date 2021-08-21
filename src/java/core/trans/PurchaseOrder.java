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
import core.edi.POEDICreator;
import core.edi.QuoteEDICreator;
import core.history.CustomerHistory;
import core.history.TransactionHistoryData;
import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.useracctmgmt.UserNotificationSettings;
import db.trans.POItemTable;
import db.trans.POTable;
import db.trans.QuoteTable;
import db.trans.RFQTable;
import db.trans.TransInquireTable;
import db.trans.TransTable;
import utils.ErrorMaster;

/**
 * File:  PurchaseOrder.java 
 *
 * Created on 20-Jun-2013 3:04:30 PM
 */
public class PurchaseOrder
{

    private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : PurchaseOrder -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public PurchaseOrder( )
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	ErrorLogger errLogger_;
	
	/*
	 * Method : add
	 * 
	 * Input  : POData object
	 * 
	 * Return : int value (This indicate whether PO created successful or not)
	 * 
	 * Purpose: This is method is used to created the new PO in supply medium.
	 * First this create the PO in supply medium then process the following tasks
	 * 1.convert the POData object into EDI format and store to company doc dir.
	 * 2.Send the notification to TO vendor about the PO request in one of the two ways.
	 * The way of communicate is define in user account management settings. From using that
	 * we send any notification
	 * 	a) Through mail (To vendor admin email id). This mail content the HTML format.
	 * 		PO form in html format and attachment of Quote pdf document send to admin mailid.
	 * 	b)Through SMS
	 * 3.Send the Supply Medium invitation mail if supplier is outside supplier(ie not registered with 
	 * SupplyMedium)
	 */
	
	public int add( POData poData)
	{
		// add the POTable entry
		
		POTable poTable = new POTable( );
		
		TSM tsm = new TSM( );
		
		tsm.init( poData.transId_, TransType.transType.PO.getValue( ) );
		
		errorMaster_.insert( "tsm init completed"+ poData.transId_);
		
		errorMaster_.insert( "tsm init completed"+tsm.state( ).toString( ) );
		
		if( poData.transId_ == -1 )  // For new PO
		{
			poData.status_ = tsm.state( ).toString( );
		}
		else // Create PO for Quote
		{
			poData.status_ = tsm.next( poData.action_ ).toString( );
		}
		
		
		tsm = null;
		
		if( genPONum( poData ) != 0 )
		{
			return 8206; //Unable to generate Po number
		}
		
		int result = poTable.insert( poData );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PurchaseOrder.add() - Unable to insert into po table" );
			
			poTable = null;
			
			return 8201;
		}
		
		// add the TransTable entry
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
	
		if( poData.transId_ == -1 )
		{
			result = transTable.getMax( );
		
    		if( result == -1 )
    		{
    			poData.transId_ = 1;
    		}
    		else if( result == -2 || result == -3 )
    		{
    			errLogger_.logMsg( "Error::PurchaseOrder.add() - Unable to get max trans id from trans table" );
    			
    			transTable = null;
    			
    			return 8205;
    		}
    		else 
    		{
    			poData.transId_ = result + 1;
    		}
		}
		
		errorMaster_.insert( "GetMax Value = " + result );
		
		generateTransData( poData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
						
		transNotification.add( transData );
						
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PurchaseOrder.add() - Unable to insert into transaction table" );
			
			poTable = null;
			
			return 8202;
		}
		
		// update POTable with transaction id
		
		//poData.transId_ = transData.transId_;
		
		result = poTable.update( poData );
		
		poTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PurchaseOrder.add() - Unable to update po table" );
					
			return 8203;
		}

		// add the POItemTable entry
		
		for( POItemData poItemData :  poData.poItems_ )
        {
			poItemData.poId_ = poData.poId_;
	        
			poItemData.transId_ = poData.transId_;
        }
		
		POItemTable itemTable = new POItemTable( );
		
		result = itemTable.insert( poData.poItems_ );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PurchaseOrder.add() - Unable to insert item list in POItemTable" );
			
			return 8204;
		}
		
		
		// Change the status of purchase order creation in quote table
		changeQuoteStatus( poData.transId_ );
				
		// convert POData to EDI format
		
		createPOEDI( poData );
		
		// Get the User account details for deciding the way of communication(Mail or SMS)
		
		// Send the Mail
		
		result = sendPOMail( poData );
		
		/*UserNotificationSettings notificationSettings=new UserNotificationSettings( );
		
		boolean canSendMail=notificationSettings.canSendNotification(poData.userTo_);
		
		if(canSendMail)
		{
			sendPOMail( poData );
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
		
		
		
		return 8200; // Success
	}
	
	/*Helper method for add( poData )*/
	private void generateTransData( POData poData, TransData transData )
	{
		transData.action_ 		= "";
		transData.from_ 		= poData.from_;
		transData.to_			= poData.to_;
		transData.status_		= poData.status_;
		transData.transType_	= TransType.transType.PO.getValue( );
		transData.transTypeId_	= poData.poId_;
		transData.userFrom_		= poData.userFrom_;
		transData.userTo_		= poData.userTo_;
		transData.transId_		= poData.transId_;
	}
	
	/*
	 * Method : get
	 * 
	 * Input  : regnKey, userKey and list of POData object
	 * 
	 * Return : int value (This indicate whether we get all PO successfully or not)
	 * 
	 * Purpose: This is used to fetch the all the pending PO transaction for the 
	 * particular user of the company. For fetching the transaction we do the following
	 * steps.
	 * 1. Using regnkey and userKey we get the all the PO request
	 * 2. Using the POId we fetch the each and every PO items
	 * 3. Using the POId we fetch the transaction details for each and every PO
	 */
	public int get( CompanyRegnKey regnKey, UserProfileKey userKey, List<POData> poList )
	{
		// Get the all the PO's using RegnKey and UserKey
		
		POTable poTable = new POTable( );
		
		POData poData = new POData( );
		
		poData.from_ = regnKey;
		
		poData.userFrom_ = userKey;
		
		int result = poTable.find(  regnKey,  userKey, poList );
		
		poTable = null; poData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PO.get() - Unable to fetch PO from POTable" );
			
			return 8221;
		}
		
		// Get the PO items for each PO
		
		POItemTable poItemTable = new POItemTable( );
		
		for( POData data : poList )
        {
			POItemData itemData = new POItemData( );
			
			List<POItemData> poItemList	= new ArrayList<POItemData>( );
			
			itemData.poId_		= data.poId_;
			
			itemData.transId_ = data.transId_;
			
			result = poItemTable.find( itemData, poItemList );
	        
	        if( result != 0 )
	        {
	        	errLogger_.logMsg( "Error::PurchaseOrder.get() - Unable to fetch POItemList from POItemTable" );
	        }
	        
	        data.poItems_ = poItemList;
	        
	        poItemList = null;
	        
	        itemData = null;
        }
		
		poItemTable = null;
		
		// Get the Transaction details for each PO
		
		TransTable transTable = new TransTable( );
		
		for( POData data : poList )
        {
			TransData transData = new TransData( );
			
			List<TransData> transDataList = new ArrayList<TransData>( );
			
			transData.transTypeId_	= data.poId_;
			
			transData.transType_	= TransType.transType.PO.getValue( );
			
			//transData.transId_ = data.transId_;
			
			result = transTable.find( transData, transDataList );
			
			if( result != 0 )
			{
				errLogger_.logMsg( "Error::PO.get() - Unable to fetch transDataList from TransTable" );
			}
			
			data.transList_ = transDataList;
			
			transData = null;
			
			transDataList = null;
        }
		
		transTable = null;
		
		// Get the Inquire details for each PO
		
		TransInquireTable inquireTable = new TransInquireTable( );
		
		for( POData data : poList )
        {
	        TransInquireData inquireData = new TransInquireData( );
	        
	        List<TransInquireData> inquireDataList = new ArrayList<TransInquireData>( );
	        
	        inquireData.transId_	= data.transId_;
	        
	        inquireData.transType_	= TransType.transType.PO.getValue( );
	        
	        inquireData.transTypeId_ = data.poId_;
	        
	        result = inquireTable.find( inquireData, inquireDataList );
	        
	        if( result != 0 )
			{
				errLogger_.logMsg( "Error::PurchaseOrder.get() - Unable to fetch transInquireList from TransInquireTable" );
			}
			
			data.transInquireList_ = inquireDataList;
			
			inquireData = null;
			
			inquireDataList = null;
        }
		
		inquireTable = null;
		
		CompanyProfileDetails profileDetails = new CompanyProfileDetails( );
		
		for( POData data : poList )
        {
	        profileDetails.getFullProfile( data.from_, data.fromCompanyProfileData_ );
	        
	        profileDetails.getFullProfile( data.to_, data.toCompanyProfileData_ );
        }
		
		profileDetails = null;
		
		return 8220; //Success
	}
	
	
	/*
	 * Method : update
	 * 
	 * Input  : POData object
	 * 
	 * Return : int 
	 * 
	 * Purpose: It is used to update the already created PO's
	 */
	public int update( POData poData )
	{
		//Update po table update
		
		errorMaster_.insert("Line 1");
		
		POTable poTable = new POTable( );
		
		TSM tsm = new TSM( );
		
		tsm.init( poData.transId_, TransType.transType.PO.getValue( ) );
		
		errorMaster_.insert("po action="+poData.action_);
		
		poData.status_ = tsm.next( poData.action_ ).toString( );
		
		errorMaster_.insert("po table update");
		
		int result = poTable.update( poData );
		
		poTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PurchaseOrder.update() - Unable to update po in POTable" );
			
			return 8231;
		}
		
		// Transaction new entry
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( poData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
						
		transNotification.add( transData );
						
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PO.update() - Unable to insert into trans table" );
			
			return 8232;
		}
		
		// remove po items table
		
		POItemTable itemTable = new POItemTable( );
		
		result = itemTable.delete( poData.poId_ );
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PurchaseOrder.update() - Unable to delete from po item table" );
			
			itemTable = null;
			
			return 8233;
		}
		
		// new item insert
		
		for( POItemData poItemData :  poData.poItems_ )
		 {
			     poItemData.poId_ = poData.poId_;
			        
			     poItemData.transId_ = poData.transId_;
		 }
		
		
		result = itemTable.insert( poData.poItems_ );
		
		itemTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PurchaseOrder.update() - Unable to insert into po items table" );
			
			return 8234;
		}
		
		
		
		return 8230; // Success
		
	}
	
	/*
	 * Method : changeStatus
	 * 
	 * Input  : POData
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to change the status of the PO. Following is the 
	 * steps to change the status of the PO.
	 * 1.Using the action of the transaction get the next status of the PO using STATE MACHINE
	 * 2.Change the status for PO
	 * 3.Add the new entry for transaction for PO
	 * 
	 */
	public int changeStatus( POData poData )
	{
		// Get the next state using the action
		
		TSM tsm = new TSM( );
		
		tsm.init( poData.transId_, TransType.transType.PO.getValue( ) );
		
		poData.status_ = tsm.next( poData.action_).toString( );
		
		tsm = null;
		

		// Update the status in POTable
		
		POTable poTable = new POTable( );
		
		int result = poTable.updateStatus( poData.poId_, poData.userFrom_, poData.status_ );
		
		poTable = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PurchaseOrder.changeStatus() - Unable to change the status" );
			
			return 8211;
		}
		
		// Add new entry in TransTable
		
		TransTable transTable = new TransTable( );
		
		TransData transData = new TransData( );
		
		generateTransData( poData, transData );
		
		result = transTable.insert( transData );
		
		// Insert new notification
		TransNotification transNotification = new TransNotification( );
						
		transNotification.add( transData );
						
		transNotification = null;
		
		transTable = null; transData = null;
		
		if( result != 0 )
		{
			errLogger_.logMsg( "Error::PO.changeStatus() - Unable to insert into trans table" );
			
			return 8212;
		}
		
		if( poData.status_.equals( "Rejected" ))
			addTransHistory( poData );
		
		return 8210;
	}
	
/* This method is used to generate the purchase order number */
	
	private int genPONum( POData poData )
	{
		POTable poTable = new POTable( );
		
		StringHolder poNum = new StringHolder( );
		
		String poNumStr = "";
		
		int result = poTable.find( poData.from_, poNum );
		
		if( result == 0 )
		{
			String[] firstSplit = poNum.value.split( "-" );
			
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
			poNumStr = c.get( Calendar.YEAR ) + "-" + ( Integer.valueOf( secondSplit[0] )+1 );
			
			poData.poNum_ = poNumStr;
		}
		else if( result == -1 )
		{
			Calendar c = Calendar.getInstance( );

			c.setTimeInMillis( System.currentTimeMillis( ) );

			poNumStr = c.get( Calendar.YEAR ) + "-" + 1;
			
			poData.poNum_ = poNumStr;
			
			result = 0;
		}
		else 
		{
			errLogger_.logMsg( "Error::PurchaseOrder.genPONum() - Unable to fetch last po num" );
			
			result = -1;
		}
		
		return result;
	}
	
	
	// Used to change the Quote table is_po_created flag
	
	private int changeQuoteStatus( long transId )
	{
		QuoteTable quoteTable = new QuoteTable( );
			
		int result = quoteTable.setPOCreatedFlag( transId );
			
		quoteTable = null;
			
		return result;
	}
		
		

	// Used to add the history for this transaction
	
	private int addTransHistory( POData podata )
	{
		CustomerHistory customerHistory = new CustomerHistory( );
		
		TransactionHistoryData transHistoryData = new TransactionHistoryData( );
		
		transHistoryData.transId_  = podata.transId_;
		
		transHistoryData.fromRegnKey_	= podata.from_;
		
		transHistoryData.toRegnKey_		= podata.to_;
		
		transHistoryData.amount_		= podata.totalPrice_;
		
		transHistoryData.status_		= podata.status_;
		
		int result = customerHistory.addTransHistory( transHistoryData );
		
		return result;
	}
	
	// This method is used to create and store the PO EDI document from given POData object
	private int createPOEDI( POData poData )
	{
		
		CompanyProfileDetails profileDetails = new CompanyProfileDetails( );
		
		profileDetails.getFullProfile( poData.from_, poData.fromCompanyProfileData_ );
			        
		profileDetails.getFullProfile( poData.to_, poData.toCompanyProfileData_ );
				
		profileDetails = null;
		
		POEDICreator poediCreator = new POEDICreator( );
			
		int result = poediCreator.create( poData );
				
		poediCreator  = null;
				
		return result;
	}
			
	// Method is used to send the PO notification mail to receiver with attachment of EDI Document.
	private int sendPOMail( POData poData )
	{
		
				
		try
        {
			
			
			String[] to = new String[1];
			
			if( poData.isOutsideSupplier_ == 1 && poData.outsideSupplierEmailFlag_ == 1)
			{
				errorMaster_.insert("Outside Supplier");
				
				
				to[0] = poData.outsideSupplierEmail_;
				
				
				String subject = "Purchase Order from "+poData.fromCompanyProfileData_.companyName_;
				
				String trasnMsg = "The company "+poData.fromCompanyProfileData_.companyName_+
						" who is currently using SupplyMedium, has sent you an Purchase Order via email. Please respond to this email or register for free at SupplyMedium to get the benefits of transacting with your vendors";
				
				HTMLMailComposer composer = new HTMLMailComposer( );
				
				String message = composer.composeTransactionMail( trasnMsg );

				
				String fileName = "po.xml";
				
			
				StringHolder localPath = new StringHolder( );
				
				PathBuilder pathBuilder = new PathBuilder( );
				
				int result = pathBuilder.getEDIFilesDirPath( poData.transId_, localPath );
				
				
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
        	errLogger_.logMsg( "Error::PurchaseOrder.sendRFQMail() - Unable send email "+e.getMessage( ) );
        	
        	return -1;
        }
		
		
		
	}
}

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
package servlet.trans;

import java.text.SimpleDateFormat;
import java.util.List;

import utils.ErrorCodeConfigReader;
import utils.ErrorLogger;
import utils.JSONEncode;
import core.trans.InvoiceData;
import core.trans.InvoiceItemData;
import core.trans.TransData;
import core.trans.TransInquireData;
import utils.ErrorMaster;

/**
 * File:  InvoiceJSONComposer.java 
 *
 * Created on Jul 6, 2013 2:30:54 PM
 */
public class InvoiceJSONComposer
{
    private static ErrorMaster errorMaster_ = null;



	/*Constructor*/
	
	public InvoiceJSONComposer()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: composeInvoiceListJSON
	 * 
	 * Input: int response, List<InvoiceData> invoiceDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the invoiceDataList and compose JSON string
	 */
	
	public String composeInvoiceListJSON( int responseCode, List<InvoiceData> invoiceDataList )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		errorMaster_.insert( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 8320 )
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy ");
			
			jsonStr = jsonStr+ "{ \"result\" : \"success\",  ";
			
			String msg = "Info::InvoiceServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			jsonStr = jsonStr + "\"invoicelist\" : [";
			
			int iterator = 0;
			
			for( InvoiceData invoiceData : invoiceDataList )
			{
				jsonStr = jsonStr + "{ \"invoiceId\" : \"" + invoiceData.invoiceId_ + "\", ";
				
				 String invoiceDateStr =	dateFormat.format( invoiceData.invoiceDate_  );
				 jsonStr = jsonStr + " \"invoiceDate\" : \"" + invoiceDateStr + "\", ";
				 

				jsonStr = jsonStr + " \"transId\" : \"" + invoiceData.transId_ + "\", ";
				jsonStr = jsonStr + " \"fromRegnKey\" : \"" + invoiceData.from_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"fromUserKey\" : \"" + invoiceData.userFrom_.toString( ) + "\", ";

				
				jsonStr = jsonStr + " \"status\" : \"" + invoiceData.status_ + "\", ";
				
				jsonStr = jsonStr + " \"invoiceNum\" : \"" + invoiceData.invoiceNo_ + "\", ";
				
	            String dueDateStr =	dateFormat.format( invoiceData.dueDate_  );
	            
				jsonStr = jsonStr + " \"invoiceDueDate\" : \"" + dueDateStr + "\",";
				
				errorMaster_.insert( " status=" + invoiceData.status_);
				
				jsonStr = jsonStr + " \"outsideBuyerEmail\" : \"" + invoiceData.outsideBuyerEmail_ + "\", ";
                                jsonStr = jsonStr + " \"outsideSupplieraddress\" : \"" + invoiceData.outsideSupplieraddress + "\", ";
				jsonStr = jsonStr + " \"invoiceRef\" : \"" + invoiceData.invoiceId_ + "\", ";
				

				if( invoiceData.fromCompanyProfileData_ != null )
				{
					jsonStr = jsonStr + " \"fromName\" : \"" + invoiceData.fromCompanyProfileData_.companyName_ + "\", ";
				}
				
				if( invoiceData.fromCompanyProfileData_.mailingAddressArr_.size( ) > 0 )
				{
					jsonStr = jsonStr + " \"fromCountry\" : \"" + invoiceData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).countryRegion_ + "\", ";
					jsonStr = jsonStr + " \"fromState\" : \"" + invoiceData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).state_ + "\", ";
					jsonStr = jsonStr + " \"fromCity\" : \"" + invoiceData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).city_ + "\", ";
					
					JSONEncode jsonEncode=new JSONEncode( );
					
					String fromAddress=jsonEncode.encode( invoiceData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).address_ );
									
					jsonStr = jsonStr + " \"fromAddress\" : \"" + fromAddress.trim( ) + "\", ";
					jsonStr = jsonStr + " \"fromZipcode\" : \"" + invoiceData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).zipcode_ + "\", ";
				}
				

				jsonStr = jsonStr + " \"toRegnKey\" : \"" + invoiceData.to_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"toUserKey\" : \"" + invoiceData.userTo_.toString( ) + "\", ";
				
				if( invoiceData.toCompanyProfileData_ != null )
				{
					jsonStr = jsonStr + " \"toName\" : \"" + invoiceData.toCompanyProfileData_.companyName_ + "\", ";
				}

				if( invoiceData.toCompanyProfileData_.mailingAddressArr_.size( ) > 0 )
				{
					jsonStr = jsonStr + " \"toCountry\" : \"" + invoiceData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).countryRegion_ + "\", ";
					jsonStr = jsonStr + " \"toState\" : \"" + invoiceData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).state_ + "\", ";
					jsonStr = jsonStr + " \"toCity\" : \"" + invoiceData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).city_ + "\", ";
					
					JSONEncode jsonEncode=new JSONEncode( );
					
					String toAddress=jsonEncode.encode( invoiceData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).address_ );
				
					jsonStr = jsonStr + " \"toAddress\" : \"" + toAddress.trim( ) + "\", ";
					jsonStr = jsonStr + " \"toZipcode\" : \"" + invoiceData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).zipcode_ + "\", ";
				}
				
				jsonStr = jsonStr + " \"isOutsideBuyer\" : \"" + invoiceData.isOutsideBuyer_ + "\", ";
				//jsonStr = jsonStr + " \"quotationRef\" : \"" + rfqData. + "\", ";
				jsonStr = jsonStr + " \"recurring\" : \"" + invoiceData.recurring_ + "\", ";
				
				jsonStr = jsonStr + " \"invoiceSubTotal\" : \"" + invoiceData.totalListPrice_ + "\", ";
				jsonStr = jsonStr + " \"taxPercentage\" : \"" + invoiceData.taxPercentage_ + "\", ";
				jsonStr = jsonStr + " \"freightHandling\" : \"" + invoiceData.freightHandling_ + "\", ";
				jsonStr = jsonStr + " \"invoiceTotal\" : \"" + invoiceData.totalPrice_ + "\", ";
				
				jsonStr = jsonStr + " \"freightCarrier\" : \"" + invoiceData.freightCarrier_ + "\", ";
				
				jsonStr = jsonStr + " \"billOfLanding\" : \"" + invoiceData.billOfLanding_ + "\", ";
				
				jsonStr = jsonStr + " \"freightWeight\" : \"" + invoiceData.freightWeight_ + "\", ";
				jsonStr = jsonStr + " \"freightWeightUnit\" : \"" + invoiceData.freightWeightUnit_ + "\", ";
				
				String shippedDateStr =	dateFormat.format( invoiceData.dateShipped_  );
				jsonStr = jsonStr + " \"shippedDate\" : \"" + shippedDateStr + "\", ";
				
				jsonStr = jsonStr + " \"isDiffAdd\" : \"" + invoiceData.isDiffAdd_ + "\", ";
				jsonStr = jsonStr + " \"diffEmail\" : \"" + invoiceData.diffEmail_ + "\", ";
				
				jsonStr = jsonStr + " \"isNonPoInvoice\" : \"" + invoiceData.isNonPOInvoice_ + "\", ";
				jsonStr = jsonStr + " \"poNo\" : \"" + invoiceData.poNo_ + "\", ";
				
				/*Items data*/
								
				jsonStr = jsonStr + " \"items\" : [";
                
				int iter = 0;
							
				for( InvoiceItemData itemData: invoiceData.invoiceItems_ )
                {
					jsonStr = jsonStr + "{ \"itemDesc\" : \"" + itemData.itemDesc_+ "\", ";
					jsonStr = jsonStr + " \"partNo\" : \"" + itemData.partNo_ + "\", ";
					jsonStr = jsonStr + " \"quantityOrdered\" : \"" + itemData.quantityOrdered_ + "\", ";
					jsonStr = jsonStr + " \"quantityShipped\" : \"" + itemData.quantityShipped_ + "\", ";
					jsonStr = jsonStr + " \"quantityOrderedUnit\" : \"" + itemData.quantityOrderedUnit_ + "\", ";
					jsonStr = jsonStr + " \"quantityShippedUnit\" : \"" + itemData.quantityShippedUnit_ + "\", ";
					
					
					jsonStr = jsonStr + " \"price\" : \"" + itemData.price_ + "\", ";
					jsonStr = jsonStr + " \"currency\" : \"" + itemData.currency_ + "\", ";
                                        jsonStr = jsonStr + " \"barcode_id\" : \"" + itemData.brcd_ + "\" ";
					
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( invoiceData.invoiceItems_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "],";
				
				/*Transaction data*/
				
				jsonStr = jsonStr + " \"trans\" : [";
                
				iter = 0;
				
				for( TransData transData: invoiceData.transList_ )
                {
					jsonStr = jsonStr + "{ \"status\" : \"" + transData.status_+ "\", ";
					
					String transDateStr =	dateFormat.format( transData.createdTimestamp_  );
					jsonStr = jsonStr + " \"date\" : \"" + transDateStr + "\", ";
					
					jsonStr = jsonStr + " \"from\" : \"" + transData.from_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"to\" : \"" + transData.to_.toString( ) + "\" ";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( invoiceData.transList_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "],";
				
				/*Inquire data*/
				
				jsonStr = jsonStr + " \"inquires\" : [";
                
				iter = 0;
				
				for( TransInquireData inauireData: invoiceData.transInquireList_ )
                {
					jsonStr = jsonStr + "{ \"details\" : \"" + inauireData.details_ + "\", ";
					jsonStr = jsonStr + " \"date\" : \"" + inauireData.createdTimestamp_ + "\", ";
					jsonStr = jsonStr + " \"from\" : \"" + inauireData.from_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"to\" : \"" + inauireData.to_.toString( ) + "\" ";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( invoiceData.transInquireList_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "]}";
				
				iterator = iterator + 1;

				if( invoiceDataList.size( ) > iterator )
				{
					jsonStr = jsonStr + ",";
				}
				//jsonStr = jsonStr + "}";
			}
				
			jsonStr = jsonStr + "]}";
		}
		else 
		{
			//jsonMap.put( "result", "failed" );
			
			jsonStr = "{ \"result\" : \"failed\",  ";
			
			String msg = "Info::InvoiceJSONComposer.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		//errorMaster_.insert( "json str="+jsonStr );
                //System.out.print("jsonStr/////////////////////"+jsonStr);
		return jsonStr;
	}
}

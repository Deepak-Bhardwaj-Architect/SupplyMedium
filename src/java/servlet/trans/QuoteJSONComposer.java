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
import core.trans.QuoteData;
import core.trans.QuoteItemData;
import core.trans.TransData;
import core.trans.TransInquireData;
import utils.ErrorMaster;

/**
 * File:  QuoteJSONComposer.java 
 *
 * Created on Jul 4, 2013 11:18:48 AM
 */

/*Class: QuoteJSONComposer
 * 
 *Purpose: This is used to parse the quoteList and compose the JSON string
 */

public class QuoteJSONComposer
{
    private static ErrorMaster errorMaster_ = null;



	/*Constructor*/
	
	public QuoteJSONComposer()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: composeQuoteListJSON
	 * 
	 * Input: int response, List<QuoteData> quoteDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the quoteDataList and compose JSON string
	 */
	
	public String composeQuoteListJSON( int responseCode, List<QuoteData> quoteDataList )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		errorMaster_.insert( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 8120 )
		{
			
			
			SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy ");
			
			jsonStr = jsonStr+ "{ \"result\" : \"success\",  ";
			
			String msg = "Info::QuoteServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			jsonStr = jsonStr + "\"quotelist\" : [";
			
			int iterator = 0;
			
			//for( VendorRegnData vendorRegnData : vendorRegnDataList )
			for( QuoteData quoteData : quoteDataList )
            {
				jsonStr = jsonStr + "{ \"quoteId\" : \"" + quoteData.quoteId_ + "\", ";
				
				 String quoteDateStr =	dateFormat.format( quoteData.quoteDate_  );
				 jsonStr = jsonStr + " \"quoteDate\" : \"" + quoteDateStr + "\", ";
					
				
				jsonStr = jsonStr + " \"transId\" : \"" + quoteData.transId_ + "\", ";
				jsonStr = jsonStr + " \"fromRegnKey\" : \"" + quoteData.from_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"fromUserKey\" : \"" + quoteData.userFrom_.toString( ) + "\", ";
				
				jsonStr = jsonStr + " \"isPOCreated\" : \"" + quoteData.isPOCreated_ + "\", ";
				
				jsonStr = jsonStr + " \"status\" : \"" + quoteData.status_ + "\", ";
				
				errorMaster_.insert( " status=" + quoteData.status_);
				
				jsonStr = jsonStr + " \"outsideSupplierEmail\" : \"" + quoteData.outsideSupplierEmail_ + "\", ";
				jsonStr = jsonStr + " \"quotationRef\" : \"" + quoteData.quoteRef_ + "\", ";
				

				if( quoteData.fromCompanyProfileData_ != null )
				{
					jsonStr = jsonStr + " \"fromName\" : \"" + quoteData.fromCompanyProfileData_.companyName_ + "\", ";
				}
				
				if( quoteData.fromCompanyProfileData_.mailingAddressArr_.size( ) > 0 )
				{
					jsonStr = jsonStr + " \"fromCountry\" : \"" + quoteData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).countryRegion_ + "\", ";
					jsonStr = jsonStr + " \"fromState\" : \"" + quoteData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).state_ + "\", ";
					jsonStr = jsonStr + " \"fromCity\" : \"" + quoteData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).city_ + "\", ";
					
					JSONEncode jsonEncode=new JSONEncode( );
					
					String fromAddress=jsonEncode.encode( quoteData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).address_ );
					
					
					jsonStr = jsonStr + " \"fromAddress\" : \"" + fromAddress.trim( ) + "\", ";
					jsonStr = jsonStr + " \"fromZipcode\" : \"" + quoteData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).zipcode_ + "\", ";
				}
				

				jsonStr = jsonStr + " \"toRegnKey\" : \"" + quoteData.to_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"toUserKey\" : \"" + quoteData.userTo_.toString( ) + "\", ";
				
				if( quoteData.toCompanyProfileData_ != null )
				{
					jsonStr = jsonStr + " \"toName\" : \"" + quoteData.toCompanyProfileData_.companyName_ + "\", ";
				}

				if( quoteData.toCompanyProfileData_.mailingAddressArr_.size( ) > 0 )
				{
					jsonStr = jsonStr + " \"toCountry\" : \"" + quoteData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).countryRegion_ + "\", ";
					jsonStr = jsonStr + " \"toState\" : \"" + quoteData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).state_ + "\", ";
					jsonStr = jsonStr + " \"toCity\" : \"" + quoteData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).city_ + "\", ";
					
					JSONEncode jsonEncode=new JSONEncode( );
					
					String toAddress=jsonEncode.encode( quoteData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).address_ );
					
					
					jsonStr = jsonStr + " \"toAddress\" : \"" + toAddress.trim( ) + "\", ";
					jsonStr = jsonStr + " \"toZipcode\" : \"" + quoteData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).zipcode_ + "\", ";
				}
				
				jsonStr = jsonStr + " \"isOutsideSupplier\" : \"" + quoteData.isOutsideSupplier_ + "\", ";
				//jsonStr = jsonStr + " \"quotationRef\" : \"" + rfqData. + "\", ";
				jsonStr = jsonStr + " \"recurring\" : \"" + quoteData.recurring_ + "\", ";
				
				jsonStr = jsonStr + " \"totalListPrice\" : \"" + quoteData.totalListPrice_ + "\", ";
				jsonStr = jsonStr + " \"taxPercentage\" : \"" + quoteData.taxPercentage_ + "\", ";
				jsonStr = jsonStr + " \"totalPrice\" : \"" + quoteData.totalPrice_ + "\", ";
				
				/*Items data*/
								
				jsonStr = jsonStr + " \"items\" : [";
                
				int iter = 0;
				
				for( QuoteItemData itemData: quoteData.quoteItems_ )
                {
					jsonStr = jsonStr + "{ \"itemDesc\" : \"" + itemData.itemDesc_+ "\", ";
					jsonStr = jsonStr + " \"partNo\" : \"" + itemData.partNo_ + "\", ";
					jsonStr = jsonStr + " \"quantity\" : \"" + itemData.quantity_ + "\", ";
					jsonStr = jsonStr + " \"quantityUnit\" : \"" + itemData.quantityUnit_ + "\", ";
                                        jsonStr = jsonStr + " \"barcode_id\" : \"" + itemData.brcd_ + "\", ";
		            String dateStr =	dateFormat.format( itemData.shipDate_  );
		            
					jsonStr = jsonStr + " \"shipDate\" : \"" + dateStr + "\",";
					
					jsonStr = jsonStr + " \"price\" : \"" + itemData.price_ + "\", ";
					jsonStr = jsonStr + " \"currency\" : \"" + itemData.currency_ + "\", ";
					jsonStr = jsonStr + " \"multiplier\" : \"" + itemData.multiplier_ + "\"";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( quoteData.quoteItems_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "],";
				
				/*Transaction data*/
				
				jsonStr = jsonStr + " \"trans\" : [";
                
				iter = 0;
				
				for( TransData transData: quoteData.transList_ )
                {
					jsonStr = jsonStr + "{ \"status\" : \"" + transData.status_+ "\", ";
					
					String transDateStr =	dateFormat.format( transData.createdTimestamp_  );
					jsonStr = jsonStr + " \"date\" : \"" + transDateStr + "\", ";
					
					jsonStr = jsonStr + " \"from\" : \"" + transData.from_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"to\" : \"" + transData.to_.toString( ) + "\" ";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( quoteData.transList_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "],";
				
				/*Inquire data*/
				
				jsonStr = jsonStr + " \"inquires\" : [";
                
				iter = 0;
				
				for( TransInquireData inauireData: quoteData.transInquireList_ )
                {
					jsonStr = jsonStr + "{ \"details\" : \"" + inauireData.details_ + "\", ";
					jsonStr = jsonStr + " \"date\" : \"" + inauireData.createdTimestamp_ + "\", ";
					jsonStr = jsonStr + " \"from\" : \"" + inauireData.from_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"to\" : \"" + inauireData.to_.toString( ) + "\" ";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( quoteData.transInquireList_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "]}";
				
				iterator = iterator + 1;

				if( quoteDataList.size( ) > iterator )
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
			
			String msg = "Info::QuoteServlet.doPost() - Request failed - Error code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			String responseString = ErrorCodeConfigReader.instance( ).get( ""+responseCode );
			
			jsonStr = jsonStr + "\"message\" : \""+responseString+"\" }";
			
			//jsonMap.put( "message", responseString );	
		
		}
		
		//jsonStr = new Gson( ).toJson( jsonMap );
		
		//errorMaster_.insert( "json str="+jsonStr );

		return jsonStr;
	}
}

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

import core.trans.RFQData;
import core.trans.RFQItemData;
import core.trans.TransData;
import core.trans.TransInquireData;
import utils.ErrorMaster;

/**
 * File:  RFQJSONComposer.java 
 *
 * Created on Jul 1, 2013 11:35:01 AM
 */

/*Class: RFQJSONComposer
 * 
 *Purpose: This is used to parse the RFQList and compose the JSON string
 */

public class RFQJSONComposer
{
    private static ErrorMaster errorMaster_ = null;



	/*Constructor*/
	
	public RFQJSONComposer()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: composeRFQListJSON
	 * 
	 * Input: int response, List<RFQData> rfqDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the rfqDataList and compose JSON string
	 */
	
	public String composeRFQListJSON( int responseCode, List<RFQData> rfqDataList )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		errorMaster_.insert( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 8020 )
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy ");
			
			jsonStr = jsonStr+ "{ \"result\" : \"success\",  ";
			
			String msg = "Info::RFQServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			jsonStr = jsonStr + "\"rfqlist\" : [";
			
			int iterator = 0;
			
			//for( VendorRegnData vendorRegnData : vendorRegnDataList )
			for( RFQData rfqData : rfqDataList )
            {
				jsonStr = jsonStr + "{ \"RFQId\" : \"" + rfqData.RFQId_ + "\", ";
				
	            String rfqDateStr =	dateFormat.format( rfqData.RFQDate_  );
				jsonStr = jsonStr + " \"RFQDate\" : \"" + rfqDateStr + "\", ";
				
				jsonStr = jsonStr + " \"transId\" : \"" + rfqData.transId_ + "\", ";
				jsonStr = jsonStr + " \"fromRegnKey\" : \"" + rfqData.from_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"fromUserKey\" : \"" + rfqData.userFrom_.toString( ) + "\", ";
				
				
				jsonStr = jsonStr + " \"status\" : \"" + rfqData.status_ + "\", ";
				
				errorMaster_.insert( " status="+rfqData.status_);
				
				jsonStr = jsonStr + " \"outsideSupplierEmail\" : \"" + rfqData.outsideSupplierEmail_ + "\", ";
                                jsonStr = jsonStr + " \"outsideSupplieraddress\" : \"" + rfqData.outsideSupplieraddress + "\", ";
				jsonStr = jsonStr + " \"quotationRef\" : \"" + rfqData.quoteRef_ + "\", ";
				jsonStr = jsonStr + " \"isQuoteCreated\" : \"" + rfqData.isQuoteCreated_ + "\", ";
				

				if( rfqData.fromCompanyProfileData_ != null )
				{
					jsonStr = jsonStr + " \"fromName\" : \"" + rfqData.fromCompanyProfileData_.companyName_ + "\", ";
				}
				
				if( rfqData.fromCompanyProfileData_.mailingAddressArr_.size( ) > 0 )
				{
					jsonStr = jsonStr + " \"fromCountry\" : \"" + rfqData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).countryRegion_ + "\", ";
					jsonStr = jsonStr + " \"fromState\" : \"" + rfqData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).state_ + "\", ";
					jsonStr = jsonStr + " \"fromCity\" : \"" + rfqData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).city_ + "\", ";
					
					JSONEncode jsonEncode=new JSONEncode( );
					
					String fromAddress=jsonEncode.encode( rfqData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).address_ );
					
					jsonStr = jsonStr + " \"fromAddress\" : \"" +fromAddress.trim() + "\", ";
					jsonStr = jsonStr + " \"fromZipcode\" : \"" + rfqData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).zipcode_ + "\", ";
				}
				

				jsonStr = jsonStr + " \"toRegnKey\" : \"" + rfqData.to_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"toUserKey\" : \"" + rfqData.userTo_.toString( ) + "\", ";
				
				if( rfqData.toCompanyProfileData_ != null )
				{
					jsonStr = jsonStr + " \"toName\" : \"" + rfqData.toCompanyProfileData_.companyName_ + "\", ";
				}

				if( rfqData.toCompanyProfileData_.mailingAddressArr_.size( ) > 0 )
				{
					jsonStr = jsonStr + " \"toCountry\" : \"" + rfqData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).countryRegion_ + "\", ";
					jsonStr = jsonStr + " \"toState\" : \"" + rfqData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).state_ + "\", ";
					jsonStr = jsonStr + " \"toCity\" : \"" + rfqData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).city_ + "\", ";
					
					JSONEncode jsonEncode=new JSONEncode( );
					
					String toAddress=jsonEncode.encode( rfqData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).address_ );
					
					jsonStr = jsonStr + " \"toAddress\" : \"" + toAddress.trim( ) + "\", ";
					jsonStr = jsonStr + " \"toZipcode\" : \"" + rfqData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).zipcode_ + "\", ";
				}
				
				jsonStr = jsonStr + " \"isOutsideSupplier\" : \"" + rfqData.isOutsideSupplier_ + "\", ";
				//jsonStr = jsonStr + " \"quotationRef\" : \"" + rfqData. + "\", ";
				jsonStr = jsonStr + " \"recurring\" : \"" + rfqData.recurring_ + "\", ";
				
				/*Items data*/
								
				jsonStr = jsonStr + " \"items\" : [";
                
				int iter = 0;
				
				for( RFQItemData itemData: rfqData.RFQItems_ )
                {
					jsonStr = jsonStr + "{ \"itemDesc\" : \"" + itemData.itemDesc_+ "\", ";
					jsonStr = jsonStr + " \"partNo\" : \"" + itemData.partNo_ + "\", ";
					jsonStr = jsonStr + " \"quantity\" : \"" + itemData.quantity_ + "\", ";
					jsonStr = jsonStr + " \"quantityUnit\" : \"" + itemData.quantityUnit_ + "\", ";
                                        jsonStr = jsonStr + " \"barcode_id\" : \"" + itemData.brcdNo_ + "\", ";
					
		            String dateStr =	dateFormat.format( itemData.shipDate_  );
		            
					jsonStr = jsonStr + " \"shipDate\" : \"" + dateStr + "\"";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( rfqData.RFQItems_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "],";
				
				/*Transaction data*/
				
				jsonStr = jsonStr + " \"trans\" : [";
                
				iter = 0;
				
				for( TransData transData: rfqData.transList_ )
                {
					jsonStr = jsonStr + "{ \"status\" : \"" + transData.status_+ "\", ";
					
					String transDateStr =	dateFormat.format( transData.createdTimestamp_  );
					jsonStr = jsonStr + " \"date\" : \"" + transDateStr + "\", ";
					
					jsonStr = jsonStr + " \"from\" : \"" + transData.from_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"to\" : \"" + transData.to_.toString( ) + "\" ";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( rfqData.transList_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "],";
				
				/*Inquire data*/
				
				jsonStr = jsonStr + " \"inquires\" : [";
                
				iter = 0;
				
				for( TransInquireData inauireData: rfqData.transInquireList_ )
                {
					jsonStr = jsonStr + "{ \"details\" : \"" + inauireData.details_ + "\", ";
					jsonStr = jsonStr + " \"date\" : \"" + inauireData.createdTimestamp_ + "\", ";
					jsonStr = jsonStr + " \"from\" : \"" + inauireData.from_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"to\" : \"" + inauireData.to_.toString( ) + "\" ";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( rfqData.transInquireList_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "]}";
				
				iterator = iterator + 1;

				if( rfqDataList.size( ) > iterator )
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
			
			String msg = "Info::VendorRegnServlet.doPost() - Request failed - Error code - "
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

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
import core.trans.POData;
import core.trans.POItemData;
import core.trans.TransData;
import core.trans.TransInquireData;
import utils.ErrorMaster;

/**
 * File:  POJSONComposer.java 
 *
 * Created on Jul 4, 2013 2:48:15 PM
 */
public class POJSONComposer
{
    private static ErrorMaster errorMaster_ = null;



	/*Constructor*/
	
	public POJSONComposer()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: composePOListJSON
	 * 
	 * Input: int response, List<POData> poDataList
	 * 
	 * Return: jsonString
	 * 
	 * Purpose: This method parse the poDataList and compose JSON string
	 */
	
	public String composePoListJSON( int responseCode, List<POData> poDataList )
	{
		String jsonStr = "";
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		errorMaster_.insert( "Response Code="+responseCode );
		
		//Map<String, String> jsonMap = new HashMap<String, String>( );
		
		if( responseCode == 8220 )
		{
			
			SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy ");
			
			jsonStr = jsonStr+ "{ \"result\" : \"success\",  ";
			
			String msg = "Info::POServlet.doPost() - Request successful - Response code - "
					+ responseCode + "\r\n\n\n";
					 							 
			errLogger.logMsg( msg );
			
			jsonStr = jsonStr + "\"polist\" : [";
			
			
			int iterator = 0;
			
			for( POData poData : poDataList )
            {
				jsonStr = jsonStr + "{ \"poId\" : \"" + poData.poId_ + "\", ";
				
				 String poDateStr =	dateFormat.format( poData.poDate_  );
				 jsonStr = jsonStr + " \"poDate\" : \"" + poDateStr + "\", ";
				 
				
				jsonStr = jsonStr + " \"transId\" : \"" + poData.transId_ + "\", ";
				jsonStr = jsonStr + " \"fromRegnKey\" : \"" + poData.from_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"fromUserKey\" : \"" + poData.userFrom_.toString( ) + "\", ";
				
				jsonStr = jsonStr + " \"status\" : \"" + poData.status_ + "\", ";
				
				jsonStr = jsonStr + " \"isInvoiceCreated\" : \"" + poData.isInvoiceCreated_ + "\", ";
				
				errorMaster_.insert( " status=" + poData.status_);
				
				jsonStr = jsonStr + " \"outsideSupplierEmail\" : \"" + poData.outsideSupplierEmail_ + "\", ";
				jsonStr = jsonStr + " \"poRef\" : \"" + poData.poNum_ + "\", ";
				

				if( poData.fromCompanyProfileData_ != null )
				{
					jsonStr = jsonStr + " \"fromName\" : \"" + poData.fromCompanyProfileData_.companyName_ + "\", ";
				}
				
				if( poData.fromCompanyProfileData_.mailingAddressArr_.size( ) > 0 )
				{
					jsonStr = jsonStr + " \"fromCountry\" : \"" + poData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).countryRegion_ + "\", ";
					jsonStr = jsonStr + " \"fromState\" : \"" + poData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).state_ + "\", ";
					jsonStr = jsonStr + " \"fromCity\" : \"" + poData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).city_ + "\", ";
					
					JSONEncode jsonEncode=new JSONEncode( );
								
					String fromAddress=jsonEncode.encode( poData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).address_ );
					
					
					jsonStr = jsonStr + " \"fromAddress\" : \"" + fromAddress.trim( ) + "\", ";
					jsonStr = jsonStr + " \"fromZipcode\" : \"" + poData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).zipcode_ + "\", ";
				}
				

				jsonStr = jsonStr + " \"toRegnKey\" : \"" + poData.to_.toString( ) + "\", ";
				jsonStr = jsonStr + " \"toUserKey\" : \"" + poData.userTo_.toString( ) + "\", ";
				
				if( poData.toCompanyProfileData_ != null )
				{
					jsonStr = jsonStr + " \"toName\" : \"" + poData.toCompanyProfileData_.companyName_ + "\", ";
				}

				if( poData.toCompanyProfileData_.mailingAddressArr_.size( ) > 0 )
				{
					jsonStr = jsonStr + " \"toCountry\" : \"" + poData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).countryRegion_ + "\", ";
					jsonStr = jsonStr + " \"toState\" : \"" + poData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).state_ + "\", ";
					jsonStr = jsonStr + " \"toCity\" : \"" + poData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).city_ + "\", ";
					
					JSONEncode jsonEncode=new JSONEncode( );
					
					String toAddress=jsonEncode.encode( poData.fromCompanyProfileData_.mailingAddressArr_.get( 0 ).address_ );
					
					
					
					jsonStr = jsonStr + " \"toAddress\" : \"" + toAddress.trim( ) + "\", ";
					jsonStr = jsonStr + " \"toZipcode\" : \"" + poData.toCompanyProfileData_.mailingAddressArr_.get( 0 ).zipcode_ + "\", ";
				}
				
				jsonStr = jsonStr + " \"isOutsideSupplier\" : \"" + poData.isOutsideSupplier_ + "\", ";
				//jsonStr = jsonStr + " \"quotationRef\" : \"" + rfqData. + "\", ";
				jsonStr = jsonStr + " \"recurring\" : \"" + poData.recurring_ + "\", ";
				
				jsonStr = jsonStr + " \"poSubTotal\" : \"" + poData.totalListPrice_ + "\", ";
				jsonStr = jsonStr + " \"taxPercentage\" : \"" + poData.taxPercentage_ + "\", ";
				jsonStr = jsonStr + " \"poTotal\" : \"" + poData.totalPrice_ + "\", ";
				
				/*Items data*/
								
				jsonStr = jsonStr + " \"items\" : [";
                
				int iter = 0;
							
				for( POItemData itemData: poData.poItems_ )
                {
					jsonStr = jsonStr + "{ \"itemDesc\" : \"" + itemData.itemDesc_+ "\", ";
					jsonStr = jsonStr + " \"partNo\" : \"" + itemData.partNo_ + "\", ";
					jsonStr = jsonStr + " \"quantity\" : \"" + itemData.quantity_ + "\", ";
					jsonStr = jsonStr + " \"quantityUnit\" : \"" + itemData.quantityUnit_ + "\", ";
					
		            String dateStr =	dateFormat.format( itemData.shipDate_  );
		            
					jsonStr = jsonStr + " \"shipDate\" : \"" + dateStr + "\",";
					
					jsonStr = jsonStr + " \"price\" : \"" + itemData.price_ + "\", ";
					jsonStr = jsonStr + " \"currency\" : \"" + itemData.currency_ + "\", ";
					jsonStr = jsonStr + " \"multiplier\" : \"" + itemData.multiplier_ + "\"";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( poData.poItems_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "],";
				
				/*Transaction data*/
				
				jsonStr = jsonStr + " \"trans\" : [";
                
				iter = 0;
				
				for( TransData transData: poData.transList_ )
                {
					jsonStr = jsonStr + "{ \"status\" : \"" + transData.status_+ "\", ";
					
					String transDateStr =	dateFormat.format( transData.createdTimestamp_  );
					jsonStr = jsonStr + " \"date\" : \"" + transDateStr + "\", ";
					
					jsonStr = jsonStr + " \"from\" : \"" + transData.from_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"to\" : \"" + transData.to_.toString( ) + "\" ";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( poData.transList_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "],";
				
				/*Inquire data*/
				
				jsonStr = jsonStr + " \"inquires\" : [";
                
				iter = 0;
				
				for( TransInquireData inauireData: poData.transInquireList_ )
                {
					jsonStr = jsonStr + "{ \"details\" : \"" + inauireData.details_ + "\", ";
					jsonStr = jsonStr + " \"date\" : \"" + inauireData.createdTimestamp_ + "\", ";
					jsonStr = jsonStr + " \"from\" : \"" + inauireData.from_.toString( ) + "\", ";
					jsonStr = jsonStr + " \"to\" : \"" + inauireData.to_.toString( ) + "\" ";
					
					jsonStr = jsonStr + "}";
					iter = iter + 1;
	                
	                if( poData.transInquireList_.size( ) > iter )
	                {
	                	jsonStr = jsonStr + ",";
	                }
                }
				
				jsonStr = jsonStr + "]}";
				
				iterator = iterator + 1;

				if( poDataList.size( ) > iterator )
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
			
			String msg = "Info::POServlet.doPost() - Request failed - Error code - "
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

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
package ctrl.trans;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.trans.POData;
import core.trans.POItemData;

/**
 * File:  PODataConverter.java 
 *
 * Created on Jul 4, 2013 2:47:54 PM
 */
public class PODataConverter
{
	ErrorLogger errorLogger_;

	/*
	 * Method : PODataConverter -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public PODataConverter()
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 * Method : convert 
	 * 
	 * Input  : HTTPServletRequest and POData object
	 * 
	 * Return : int
	 * 
	 * Purpose: This method is used to convert the HttpServletRequest object
	 * to POData object. And assign to poData parameter so it is copied in
	 * caller classes.
	 */
	
	public int convert( HttpServletRequest request, POData poData )
	{
		try
        {
			String requestType = request.getParameter( "RequestType" );
			
			if( requestType.equals( "POCreation" ) ||  requestType.equals( "UpdatePO" ) )  // This conversion process only for the POCreation request
			{
				
				String POJson = request.getParameter("PO").toString();
			
				JsonParser jsonParser = new JsonParser( );

				JsonObject poJSONData = (JsonObject)jsonParser.parse( POJson );
				
				// Setting from company regn key
				
				String fromRegnKeyStr = poJSONData.get( "fromRegnKey" ).getAsString( );
				
				CompanyRegnKey fromRegnKey = new CompanyRegnKey( );
				
				fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;
				
				poData.from_ = fromRegnKey;
				
				fromRegnKey = null;
				
				// Setting from user key
				
				String fromUserKeyStr = poJSONData.get( "fromUserKey" ).getAsString( );
				
				UserProfileKey fromUserKey = new UserProfileKey( );
				
				fromUserKey.email_ = fromUserKeyStr;
				
				poData.userFrom_ = fromUserKey;
				
				fromUserKey = null;
				
				
				
				poData.isOutsideSupplier_ = poJSONData.get( "isOutsideSupplier" ).getAsInt( );
				
				poData.outsideSupplierEmailFlag_ = poJSONData.get( "outsideSupplierMailFlag" ).getAsInt( );
				
				
				
				if( poData.isOutsideSupplier_ == 1 )
				{
					
					poData.outsideSupplierEmail_ = poJSONData.get( "outsideSupplierEmail" ).getAsString( );
				}
				else
				{
					
					// Setting to company regn key

					String toRegnKeyStr = poJSONData.get( "toRegnKey" ).getAsString( );
					
					CompanyRegnKey toRegnKey = new CompanyRegnKey( );
					
					toRegnKey.companyPhoneNo_ = toRegnKeyStr;
					
					poData.to_ = toRegnKey;
					
					toRegnKey = null;
					
				}
				
				
				//Setting the po sub total
				
				Double quoteTotalPrice = poJSONData.get( "totalListPrice" ).getAsDouble( );
				
				poData.totalListPrice_	= quoteTotalPrice; 
				
				
				//Setting the tax percentage
				
				Double taxPercentage = poJSONData.get( "taxPercentage" ).getAsDouble( );
				
				poData.taxPercentage_ = taxPercentage;
				
				
				//Setting the po total
				
				Double totalPrice = poJSONData.get( "totalPrice" ).getAsDouble( );
				
				poData.totalPrice_ = totalPrice;
				
				poData.poDate_ = new Date( System.currentTimeMillis( ) );
				
				poData.transId_ =  poJSONData.get( "transId" ).getAsLong( ); 
				
				poData.action_ =  poJSONData.get( "action" ).getAsString( ); 
				
				System.out.println( "po action="+poData.action_ );
				
				JsonArray itemsArr = poJSONData.get( "items" ).getAsJsonArray( );
				
				System.out.println("itemsArr"+itemsArr.size( ));
				
				for( JsonElement jsonele : itemsArr )
				{
					
					JsonObject quoteItemJSONData = (JsonObject)jsonele;
					
					System.out.println("line 1");
					// Converting JSON Data to strings
					String itemDesc = quoteItemJSONData.get( "itemDesc" ).getAsString( );
					System.out.println("line 2");
					String partNo = quoteItemJSONData.get( "partNo" ).getAsString( );
					System.out.println("line 3");
					String quantity = quoteItemJSONData.get( "quantity" ).getAsString( );
					System.out.println("line 4");
					String quantityUnit = quoteItemJSONData.get( "quantityUnit" ).getAsString( );
					System.out.println("line 5");
					String shipDateStr = quoteItemJSONData.get( "shipDate" ).getAsString( );
					System.out.println("line 6");
					Double price = quoteItemJSONData.get( "price" ).getAsDouble( );
					System.out.println("line 7");
					String currencyStr = quoteItemJSONData.get( "currency" ).getAsString( );
					System.out.println("line 8");
					int multiplier = quoteItemJSONData.get( "multiplier" ).getAsInt( );
					System.out.println("line 9");
					
				
					// Converting strings to POItemData object
					POItemData item = new POItemData( );
					
					item.itemDesc_ = itemDesc;
					
					item.partNo_ = partNo;
					
					item.quantity_ = Double.parseDouble( quantity );
					
					item.quantityUnit_ = quantityUnit;
					
					//Added for quote
					item.price_		= price;
					
					item.currency_	= currencyStr;
					
					item.multiplier_	= multiplier;
					
					System.out.println( "item date="+shipDateStr );
					
					DateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy" ); 
					
					java.util.Date shipDate = ( java.util.Date )formatter.parse( shipDateStr );
					
					item.shipDate_ = new java.sql.Date(shipDate.getTime());
					
					//item.shipDate_ = ( java.sql.Date ) shipDate;
					
					poData.poItems_.add( item );
					
					System.out.println( "Items count="+itemsArr.size( ) );
				
				
				}
				
				if( requestType.equals( "UpdatePO" ) )
				{
					// Setting from user key
					
					
					System.out.println( "if block" );
					
					String toUserKeyStr = poJSONData.get( "toUserKey" ).getAsString( );
					
					UserProfileKey toUserKey = new UserProfileKey( );
					
					toUserKey.email_ = toUserKeyStr;
					
					poData.userTo_ = toUserKey;
					
					toUserKey = null;
					
					System.out.println( "afetr user key convertion" );
					
					poData.poId_ =  poJSONData.get( "poId" ).getAsLong( ); 
					
					System.out.println( "POId="+poData.poId_+"transid="+poData.transId_ );

				}
				
				return 0;
			}
			else if( requestType.equals( "FetchPO" ) )
			{
				// Setting from company regn key
				
				String fromRegnKeyStr = request.getParameter( "RegnKey" );
				
				CompanyRegnKey fromRegnKey = new CompanyRegnKey( );
				
				fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;
				
				poData.from_ = fromRegnKey;
				
				fromRegnKey = null;
				
				
				
				// Setting from user key
				
				String fromUserKeyStr = request.getParameter( "UserKey" );
				
				UserProfileKey fromUserKey = new UserProfileKey( );
				
				fromUserKey.email_ = fromUserKeyStr;
				
				poData.userFrom_ = fromUserKey;
				
				fromUserKey = null;
			}
			
			else if( requestType.equals( "ChangeStatus" ) )
			{
				// Setting from company regn key
				
				String fromRegnKeyStr = request.getParameter( "FromRegnKey" );
				
				CompanyRegnKey fromRegnKey = new CompanyRegnKey( );
				
				fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;
				
				poData.from_ = fromRegnKey;
				
				fromRegnKey = null;
				
				
				
				// Setting from user key
				
				String fromUserKeyStr = request.getParameter( "FromUserKey" );
				
				UserProfileKey fromUserKey = new UserProfileKey( );
				
				fromUserKey.email_ = fromUserKeyStr;
				
				poData.userFrom_ = fromUserKey;
				
				fromUserKey = null;
				
				// Setting  to regn key
				
				String toRegnKeyStr = request.getParameter( "ToRegnKey" );
				
				CompanyRegnKey toRegnKey = new CompanyRegnKey( );
				
				toRegnKey.companyPhoneNo_ = toRegnKeyStr;
				
				poData.to_ = toRegnKey;
				
				fromRegnKey = null;
				
				
				
				// Setting to user key
				
				String toUserKeyStr = request.getParameter( "ToUserKey" );
				
				UserProfileKey toUserKey = new UserProfileKey( );
				
				toUserKey.email_ = toUserKeyStr;
				
				poData.userTo_ = toUserKey;
				
				
				poData.poId_ =  Integer.parseInt( request.getParameter( "POId" ));
				
				poData.transId_ =  Integer.parseInt( request.getParameter( "TransId" ));
				
				poData.status_ =   request.getParameter( "Status" );
				
				poData.action_ =   request.getParameter( "Action" );
				
				fromUserKey = null;
			}
        }
        catch( Exception e )
        {
        	errorLogger_.logMsg( "Exception::POdataConverter.convert() - " + e );
        	
        	return -1;
        }
		
		return 0;
	}
}

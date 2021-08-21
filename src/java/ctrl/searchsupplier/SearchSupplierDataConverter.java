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
package ctrl.searchsupplier;


import javax.servlet.http.HttpServletRequest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.ErrorLogger;

import core.regn.CompanyRegnKey;
import core.searchsupplier.AdvSearchOption;
import core.searchsupplier.SearchItem;
import core.searchsupplier.SearchOption;

/**
 * File:  SearchSupplierDataConverter.java 
 *
 * Created on 10-May-2013 11:25:34 AM
 */
public class SearchSupplierDataConverter
{

	/*
	 * Method :  constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public SearchSupplierDataConverter()
	{
	}

	/*
	 * Method : convert
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to 
	 * SearchOption object. If it is successfully convert, it return 0
	 * otherwise return -1.
	 */
	
	public int convert( HttpServletRequest request, SearchOption searchOption )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		try
        {
	        CompanyRegnKey regnKey = new CompanyRegnKey( );
	        
	        regnKey.companyPhoneNo_ = request.getParameter( "RegnKey" );
	        
	        searchOption.buyerKey_ = regnKey;
	        
	        searchOption.category_ = request.getParameter( "Category" );
	        
	        searchOption.keyword_  = request.getParameter( "Keyword" );
	        
	        if( request.getParameter( "SearchRegd" ).equals( "1" ) )
	        {
	        	 searchOption.searchRegd_ = true;
	        }
	        else 
	        {
	        	searchOption.searchRegd_ = false;
			}
        }
        catch( Exception e )
        {
        	String errMsg = "Exception::SearchSupplierDataConverter.convert()-"+e ;
			
			errLogger.logMsg( errMsg );
			
			return -1;
        }
		
		return 0;
		
	}
	
	
	/*
	 * Method : convert
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to convert the HttpServletRequest object to 
	 * AdvSearchOption object. If it is successfully convert, it return 0
	 * otherwise return -1.
	 */
	
	public int convert( HttpServletRequest request, AdvSearchOption advSearchOption )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		try
        {
			String advSearchJson = request.getParameter("AdvanceSearchData").toString();
			
			JsonParser jsonParser = new JsonParser( );

			JsonObject advSearchJSONData = (JsonObject)jsonParser.parse( advSearchJson );
			
			// Setting from buyer regn key
			
			String fromRegnKeyStr = advSearchJSONData.get( "buyerKey" ).getAsString( );
			
			CompanyRegnKey fromRegnKey = new CompanyRegnKey( );
			
			fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;
			
			advSearchOption.buyerKey_ = fromRegnKey;
			
			fromRegnKey = null;
				
			// Setting category
			
			advSearchOption.category_  = advSearchJSONData.get( "category" ).getAsString( );
			advSearchOption.country_=advSearchJSONData.get( "cntry" ).getAsString( );
			
			// Setting the check all flag
			String checkAll  = advSearchJSONData.get( "checkAll" ).getAsString( );
			
			if( checkAll.equals( "1" ) )
	        {
	        	advSearchOption.checkAll_= true;
	        }
	        else 
	        {
	        	advSearchOption.checkAll_ = false;
			}
			
			
			// Setting the registered supplier flag
			String searchRegd  = advSearchJSONData.get( "searchRegd" ).getAsString( );
			
			if( searchRegd.equals( "1" ) )
	        {
	        	advSearchOption.searchRegd_= true;
	        }
	        else 
	        {
	        	advSearchOption.searchRegd_ = false;
			}
			
			
			// Parsing search items
			
			JsonArray searchitemsArr = advSearchJSONData.get( "searchItemArr" ).getAsJsonArray( );
			
			//List<SearchItem> searchItems = new ArrayList<SearchItem>( );

			for( JsonElement jsonele : searchitemsArr )
			{
				
				JsonObject rfqItemJSONData = (JsonObject)jsonele;
				
				// Converting JSON Data to strings
				
				String partNo = rfqItemJSONData.get( "partNo" ).getAsString( );

				String name = rfqItemJSONData.get( "name" ).getAsString( );

				// Creating search item object
				
				SearchItem searchItem = new SearchItem( );
				
				searchItem.name_ = name;
				
				searchItem.partno_ = partNo;
				
				advSearchOption.searchItemList_.add( searchItem );
				
				searchItem = null;
			}
		
        }
        catch( Exception e )
        {
        	String errMsg = "Exception::SearchSupplierDataConverter.convert()-"+e ;
			
			errLogger.logMsg( errMsg );
			
			return -1;
        }
		
		return 0;
		
	}
}

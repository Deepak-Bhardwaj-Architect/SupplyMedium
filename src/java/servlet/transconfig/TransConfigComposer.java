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
package servlet.transconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;

import com.google.gson.Gson;

import core.transconfig.CarrierData;
import core.transconfig.CurrencyData;
import core.transconfig.NAICSCodeData;
import core.transconfig.QuantityTypeData;

/**
 * File:  TransConfigComposer.java 
 *
 * Created on Jul 11, 2013 5:12:38 PM
 */
public class TransConfigComposer
{
	/*
	 * Method : composeCarriersJSON( ) 
	 * 
	 * Input : List<CarriersData>  list
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the carriersDataList to json
	 * string
	 */

	public String composeCarriersJSON( List<CarrierData> carrierDataList )
	{
		String jsonStr = "";

		Map<String, String> carrierDataMap = new HashMap<String, String>( );

		carrierDataMap.put( "result", "success" );

		for( CarrierData carrierData : carrierDataList )
        {
			carrierDataMap.put( carrierData.carrierName_, carrierData.carrierName_ );
		}
		
		jsonStr = new Gson( ).toJson( carrierDataMap );

		return jsonStr;
	}

	/*
	 * Method : composeCurrencyJSON( ) 
	 * 
	 * Input : CurrencyData list 
	 * 
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the currency data list in to json string
	 */

	public String composeCurrencyJSON( List<CurrencyData> CurrencyDataList )
	{
		String jsonStr = "";

		Map<String, String> currencyMap = new HashMap<String, String>( );

		currencyMap.put( "result", "success" );

		for( CurrencyData currencyData : CurrencyDataList )
        {
	        currencyMap.put( currencyData.currencyKey_, currencyData.currencyName_ );
        }

		jsonStr = new Gson( ).toJson( currencyMap );

		return jsonStr;
	}

	/*
	 * Method : quantityTypeJSON( ) 
	 * 
	 * Input : quantityType list 
	 * 
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the quantity type list to json string
	 */

	public String quantityTypeJSON( List<QuantityTypeData> quantityTypeDataList )
	{
		String jsonStr = "";

		Map<String, String> quantityTypeMap = new HashMap<String, String>( );

		quantityTypeMap.put( "result", "success" );

		for( QuantityTypeData quantityTypeData : quantityTypeDataList )
        {
			quantityTypeMap.put( quantityTypeData.quantityTypeKey_, quantityTypeData.quantityType_ );
        }
		
		jsonStr = new Gson( ).toJson( quantityTypeMap );

		return jsonStr;
	}

	/*
	 * Method : composeNAICSCodeJSON( ) 
	 * 
	 * Input : NAICSCodeData list 
	 * 
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the NAICSCode list in to json string
	 */

	public String composeNAICSCodeJSON( List<NAICSCodeData> NAICSCodeDataList )
	{
		String jsonStr = "";

		Map<String, String> naicsCodeMap = new HashMap<String, String>( );

		naicsCodeMap.put( "result", "success" );

		for( NAICSCodeData naicsCodeData : NAICSCodeDataList )
        {
			naicsCodeMap.put( naicsCodeData.naicsCode_,naicsCodeData.naicsCodeDesc_ );
        }
		
		jsonStr = new Gson( ).toJson( naicsCodeMap );

		return jsonStr;
	}

	/*
	 * Method : composeErrorMessage( ) 
	 * 
	 * Input : error code 
	 * 
	 * Return : JSON String
	 * 
	 * Purpose: It is used to form the error json string from given error
	 * code.It read the corresponding error message from errorcodecofig file.
	 */

	public String composeErrorMessage( int errorCode )
	{
		String jsonStr = "";

		String message = null;

		Map<String, String> errorMap = new HashMap<String, String>( );

		errorMap.put( "result", "failed" );

		message = ErrorCodeConfigReader.instance( ).get( Integer.toString( errorCode ) );

		errorMap.put( "errmsg", message );

		jsonStr = new Gson( ).toJson( errorMap );

		return jsonStr;
	}
}

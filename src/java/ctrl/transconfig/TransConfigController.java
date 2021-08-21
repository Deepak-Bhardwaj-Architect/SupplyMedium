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
package ctrl.transconfig;

import java.util.List;

import utils.ErrorLogger;
import core.transconfig.CarrierData;
import core.transconfig.CurrencyData;
import core.transconfig.NAICSCodeData;
import core.transconfig.QuantityTypeData;
import core.transconfig.TransConfig;

/**
 * File:  TransConfigController.java 
 *
 * Created on Jul 11, 2013 5:11:26 PM
 */

/*
 * Class: TransConfigController
 * 
 *  Purpose: This class is used to fetch config values like carrier details, currency
 *  details, quantity types and naics code details
 */

public class TransConfigController
{
	ErrorLogger errorLogger_;
	
	/*Constructor*/
	
	public TransConfigController( )
	{
		errorLogger_ = ErrorLogger.instance( );
	}
	
	/*
	 *Method: getAllCarriers
	 *
	 * Input: List<CarrierData> carrierDataList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the carriers through carrierDataList
	 */

	public int getAllCarriers( List<CarrierData> carrierDataList )
	{
		TransConfig transConfig = new TransConfig( );
		
		int result = transConfig.getAllCarriers( carrierDataList );

		transConfig = null;
		
		return result;
	}
	
	/*
	 *Method: getAllCurrencies
	 *
	 * Input: List<CurrencyData> currencyDataList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the currencies through currencyDataList
	 */

	public int getAllCurrencies( List<CurrencyData> currencyDataList )
	{
		TransConfig transConfig = new TransConfig( );
		
		int result = transConfig.getAllCurrencies( currencyDataList );

		transConfig = null;
		
		return result;
	}
	
	/*
	 *Method: getAllQuantityType
	 *
	 * Input: List<QuantityTypeData> quantityTypeDataList
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the Quantity types through quantityTypeDataList
	 */

	public int getAllQuantityType( List<QuantityTypeData> quantityTypeDataList )
	{
		TransConfig transConfig = new TransConfig( );
		
		int result = transConfig.getAllQuantityType( quantityTypeDataList );
		
		transConfig = null;
		
		return result;
	}
	
	/*
	 *Method: getAllNAICSCode
	 *
	 * Input: List<NAICSCodeData> 
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches all the NAICSCode through NAICSCodeDataList
	 */

	public int getAllNAICSCode( List<NAICSCodeData> NAICSCodeDataList )
	{
		TransConfig transConfig = new TransConfig( );
		
		int result = transConfig.getAllNAICSCode( NAICSCodeDataList );
		
		transConfig = null;
		
		return result;
	}
	
}

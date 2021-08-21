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
package core.transconfig;

import java.util.List;

import db.transconfig.CarrierTable;
import db.transconfig.CurrencyTable;
import db.transconfig.NAICSCodeTable;
import db.transconfig.QuantityTypeTable;

import utils.ErrorLogger;

/**
 * File:  TransConfig.java 
 *
 * Created on Jul 11, 2013 5:10:01 PM
 */

/*
 * Class: TransConfig
 * 
 * Purpose: To fetch all the configs required for transaction
 * */

public class TransConfig
{
	
	ErrorLogger errorLogger_;
	
	/*Constructor*/
	
	public TransConfig( )
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
		CarrierTable carrierTable = new CarrierTable( );
		
		int result = carrierTable.find( carrierDataList );
		
		carrierTable = null;
		
		if( result != 0 )
		{
			errorLogger_.logMsg( "Error::TransConfig.getAllCarriers( ) - unable to fetch carriers list " );
			
			return 9001;
		}
		
		return 9000;
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
		CurrencyTable currencyTable = new CurrencyTable( );
		
		int result = currencyTable.find( currencyDataList );
		
		currencyTable = null;
		
		if( result != 0 )
		{
			errorLogger_.logMsg( "Error::TransConfig.getAllCurrencies( ) - unable to fetch all currencies" );
			
			return 9011;
		}
		
		return 9010;
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
		QuantityTypeTable quantityTypeTable = new QuantityTypeTable( );
		
		int result = quantityTypeTable.find( quantityTypeDataList );
		
		quantityTypeTable = null;
		
		if( result != 0 )
		{
			errorLogger_.logMsg( "Error::TransConfig.getAllQuantityType( ) - Unable to fetch all quantity type" );
			
			return 9021;
		}
		
		return 9020;
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
		NAICSCodeTable codeTable = new NAICSCodeTable( );
		
		int result = codeTable.find( NAICSCodeDataList );
		
		codeTable = null;
		
		if( result != 0 )
		{
			errorLogger_.logMsg( "Error::TransConfig.getAllNAICSCode( ) - Unable to fetch all NAICS Code" );
			
			return 9031;
		}
		
		return 9030;
	}
}

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

package servlet.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.ErrorCodeConfigReader;

import com.google.gson.Gson;

import core.common.BranchData;
import core.common.BusinessCategoryData;
import core.common.CountryData;
import core.common.StateData;
import core.common.ThemeData;
import core.common.TransRejectReasonData;
import core.regn.CCMapperData;

/**
 * File:  EntityLoaderDataComposer.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

public class EntityLoaderDataComposer 
{
	
	/*
	 * Method : composeBusinessCategoryJSON( ) 
	 * 
	 * Input : businesscategorydata list
	 *
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the business category list in to json
	 * string
	 */

	public String composeBusinessCategoryJSON(
	        List<BusinessCategoryData> businessCatDataList )
	{
		String jsonStr = "";

		Map<String, String> businessCatMap = new HashMap<String, String>( );

		businessCatMap.put( "result", "success" );

		for( BusinessCategoryData businessCatData : businessCatDataList )
		{
			businessCatMap.put( businessCatData.businessCategory_,
			        				businessCatData.businessCategory_ );

		}

		jsonStr = new Gson( ).toJson( businessCatMap );

		return jsonStr;
	}

	/*
	 * Method : composeCountryJSON( ) 
	 * 
	 * Input : countrydata list 
	 * 
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the country list in to json string
	 */

	public String composeCountryJSON( List<CountryData> countryDataList )
	{
		String jsonStr = "";

		Map<String, String> countryMap = new HashMap<String, String>( );

		countryMap.put( "result", "success" );

		for( CountryData countryData : countryDataList )
		{
			countryMap.put( countryData.country_, countryData.country_ );
		}

		jsonStr = new Gson( ).toJson( countryMap );

		return jsonStr;
	}

	/*
	 * Method : composeStateJSON( ) 
	 * 
	 * Input : statedata list 
	 * 
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the state list in to json string
	 */

	public String composeStateJSON( List<StateData> stateDataList )
	{
		String jsonStr = "";

		Map<String, String> stateMap = new HashMap<String, String>( );

		stateMap.put( "result", "success" );

		for( StateData stateData : stateDataList )
		{
			stateMap.put( stateData.state_, stateData.state_ );
		}

		jsonStr = new Gson( ).toJson( stateMap );

		return jsonStr;
	}

	/*
	 * Method : composeStateJSON( ) 
	 * 
	 * Input : companydata list 
	 * 
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the company list in to json string
	 */

	public String composeCompanyJSON( List<CCMapperData> ccMapperDataList )
	{
		String jsonStr = "";

		Map<String, String> companyMap = new HashMap<String, String>( );

		companyMap.put( "result", "success" );

		for( CCMapperData ccMapperData : ccMapperDataList )
		{
			companyMap.put( ccMapperData.companyRegnKey_.companyPhoneNo_,
			        			ccMapperData.companyname_ );
		}

		jsonStr = new Gson( ).toJson( companyMap );

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
	
	/**
	 * 
	 * @author Anbazhagan
	 * 
	 * @Date : Apr 7, 2013 8:38:41 PM
	 * 
	 * @Return : String
	 * 
	 * @Purpose : Compose the State List
	 * 
	 * @param stateBranchList
	 * @return
	 * 
	 */
	public String composeBranchJSON( List<BranchData> branchList )
	{
		String jsonStr = "";

		Map<String, String> branchMap = new HashMap<String, String>( );

		branchMap.put( "result", "success" );

		for( BranchData branchData : branchList )
		{
			branchMap.put( branchData.branch_, branchData.branch_ );
		}

		jsonStr = new Gson( ).toJson( branchMap );

		return jsonStr;
	}

	/**
	 * @author Anbazhagan
	 * 
	 * @Date : Apr 7, 2013 8:54:20 PM
	 * 
	 * @Return : String
	 * 
	 * @Purpose : Compose the Theme List
	 * 
	 * @param themeList
	 * @return
	 * 
	 */
	public String composeThemeJSON( List<ThemeData> themeList )
	{
		String jsonStr = "";

		Map<String, String> themeMap = new HashMap<String, String>( );

		themeMap.put( "result", "success" );

		for( ThemeData themeData : themeList )
		{
			themeMap.put( themeData.theme_, themeData.theme_ );
		}

		jsonStr = new Gson( ).toJson( themeMap );

		return jsonStr;
	}

	/*
	 * Method : composeRejectReasonJSON( ) 
	 * 
	 * Input : companydata list 
	 * 
	 * Return : JSON String
	 * 
	 * Purpose: It is used to convert the reject reason list in to json string
	 */

	public String composeRejectReasonJSON( List<TransRejectReasonData> rejectReasonList )
	{
		String jsonStr = "";

		Map<String, String> reasonMap = new HashMap<String, String>( );

		reasonMap.put( "result", "success" );

		for( TransRejectReasonData transRejectReasonData : rejectReasonList )
        {
	        reasonMap.put( transRejectReasonData.rejectReason_, transRejectReasonData.rejectReason_ );
        }
		
		jsonStr = new Gson( ).toJson( reasonMap );

		return jsonStr;
	}
	
}

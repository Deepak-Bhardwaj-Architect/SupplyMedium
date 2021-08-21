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

package ctrl.common;

import core.common.BranchData;
import core.common.BusinessCategoryData;
import core.common.CountryData;
import core.common.StateData;
import core.common.ThemeData;
import core.common.TransRejectReasonData;
import core.regn.CCMapperData;
import core.common.EntityLoader;

import java.util.List;

import utils.ErrorLogger;


/**
 * File:  EntityLoaderController.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

//It is used to fill the drop down datas in views.

public class EntityLoaderController
{
	
	private ErrorLogger errLogger_ ;
	
	/*
	 * Method : EntityLoaderController( ) - constructor
	 * 
	 * Input :none
	 * 
	 * Return :none
	 * 
	 * Purpose: It is used intialize the ErrorLogger object
	 */
	
	public EntityLoaderController()
	{
		
		errLogger_  = ErrorLogger.instance();
	}
	
	
	/*
	 * Method : getAllBusinessCategories( ) 
	 * 
	 * Input : businesscategory data list object 
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to get the all business categories of supplymedium
	 * and assign to businesscatDataList parameter so we can use this in caller
	 * class.
	 */

	public int getAllBusinessCategories( List<BusinessCategoryData> businessCatDataList )
	{
		int result = 0;

		try
		{
			EntityLoader entityLoaderObj = new EntityLoader( );

			result = entityLoaderObj.getAllBusinesscategories( businessCatDataList );

			entityLoaderObj = null;

			return result;

		}

		catch( Exception ex )
		{
			String msg = "Exception :: DropDownDataController : getBusinessCategory - "+ex;
			
			errLogger_.logMsg(msg);

			return 111;  // failed
		}

	}

	/*
	 * Method : getAllCountries( ) 
	 * 
	 * Input : country data list object 
	 * 
	 * Return : int  success/failed
	 * 
	 * Purpose: It is used to get the all countries of supplymeidum and assign
	 * to countryDataList parameter so we can use this in caller class.
	 */

	public int getAllCountries( List<CountryData> countryDataList )
	{

		int result = 0;

		try
		{
			EntityLoader entityLoaderObj = new EntityLoader( );

			result = entityLoaderObj.getAllCountries( countryDataList );

			entityLoaderObj = null;

			return result;
		}

		catch( Exception ex )
		{
			String msg = "Exception :: EntityLoaderController : getAllCountries - "+ex;
			
			errLogger_.logMsg( msg );

			return 121;
		}

	}

	/*
	 * Method : getAllStatesForCountry( ) 
	 * 
	 * Input : state data list object and country 
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to get the all states for given country and assign to
	 * businesscatDataList parameter so we can use this in caller class.
	 */

	public int getAllStatesForCountry( String country, List<StateData> stateDataList )
	{

		int result = 0;

		try
		{
			EntityLoader entityLoaderObj = new EntityLoader( );

			result = entityLoaderObj.getAllStates( country, stateDataList );

			entityLoaderObj = null;

			return result;
		}

		catch( Exception ex )
		{
			String msg = "Exception :: DropDownDataController : getStatesForCountry - "+ex;
			
			errLogger_.logMsg(msg);

			return 131;
		}

	}

	/*
	 * Method : getAllCompaniesForCountry( ) Input : CCMapper data list object
	 * and country name Return : int success/failed
	 * 
	 * Purpose: It is used to get the all companies for given country and assign
	 * to ccMapperDataList parameter so we can use this in caller class.
	 */

	public int getAllCompaniesForCountry( String country,
	        List<CCMapperData> ccMapperDataList )
	{

		int result = 0;

		try
		{
			EntityLoader entityLoaderObj = new EntityLoader( );

			result = entityLoaderObj.getAllCompanies( country, ccMapperDataList );

			entityLoaderObj = null;

			return result;

		}

		catch( Exception ex )
		{	
			String msg = "Exception :: DropDownDataController : getAllCompaniesForCountry - "+ ex ;

			errLogger_.logMsg( msg );

			return 141;
		}

	}

	/**
	 * @author Anbazhagan
	 * 
	 * @Date : Apr 7, 2013 8:50:41 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the List of Branch
	 * 
	 * @param branchDataList
	 * @return
	 * 
	 */
	public int getBranchList( List<BranchData> branchDataList )
	{

		int result = 0;

		try
		{
			EntityLoader entityLoaderObj = new EntityLoader( );

			result = entityLoaderObj.getAllBaranches( branchDataList );

			entityLoaderObj = null;

			return result;
		}

		catch( Exception ex )
		{
			String msg = "Exception :: DropDownDataController : getBranchList - " + ex;

			errLogger_.logMsg( msg );

			return 151;
		}

	}

	/**
	 * 
	 * @author Anbazhagan
	 * 
	 * @Date : Apr 7, 2013 8:52:06 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the Theme List
	 * 
	 * @param themeList
	 * @return
	 * 
	 */
	public int getThemeList( List<ThemeData> themeList )
	{

		int result = 0;

		try
		{
			EntityLoader entityLoaderObj = new EntityLoader( );

			result = entityLoaderObj.getAllTheme( themeList );

			entityLoaderObj = null;

			return result;
		}

		catch( Exception ex )
		{
			String msg = "Exception :: DropDownDataController : getThemeList - " + ex;

			errLogger_.logMsg( msg );

			return 161;
		}

	}
	
	/*
	 * Method : getTransRejectReasons 
	 * 
	 * Input : List<TransRejectReasonData> obj 
	 * 
	 * Return : int success/failed
	 * 
	 * Purpose: It is used to get all the trans reject reasons
	 */
	public int getTransRejectReasons( List<TransRejectReasonData> rejectReasonList )
	{
		EntityLoader loader = new EntityLoader( );
		
		int result = loader.get( rejectReasonList );
		
		return result;
	}
}

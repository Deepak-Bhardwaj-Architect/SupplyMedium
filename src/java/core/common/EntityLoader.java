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

package core.common;

import java.util.List;

import utils.ErrorLogger;
import core.regn.CCMapperData;
import db.config.BusinessCategoryTable;
import db.config.CountryTable;
import db.config.StateTable;
import db.config.TransRejectReasonTable;
import db.regn.CCMapperTable;


/**
 * File: EntityLoader.java
 * 
 * Created on Jan 18, 2013 11:27:28 AM
 */

// It is used to fill the drop down datas in views.

public class EntityLoader
{
	/*
	 * Method : getAllBusinesscategories( )
	 * 
	 * Input : empty List for getting the list
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the all business categories of supplymedium
	 * and convert into to List of BusinessCategoryData objects. Insert into
	 * businessCatDataList parameter. So that it can used in caller class.
	 * Return type integer specified the success or failed result.
	 */

	public int getAllBusinesscategories( List<BusinessCategoryData> businessCatDataList )
	{

		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{
			BusinessCategoryTable businessCatTblObj = new BusinessCategoryTable( );

			int responseCode = businessCatTblObj
			        .getAllBusinessCategories( businessCatDataList );

			businessCatTblObj = null;

			if( responseCode == 0 )
			{
				if( businessCatDataList.size( ) == 0 )
				{
//					String msg = "Info::DropDownFiller.getAllBusinessCategory() - " +
//							"No Categories available";
//
//					errLogger.logMsg( msg );

					return 112; // no categories available
				}

				return 110; // success
			}
			else
			{
				return 111; // failed
			}

		}

		catch( Exception ex )
		{
			String msg = "Exception::DropDownFiller.getBusinessCategory() - " + ex;

			errLogger.logMsg( msg );

			return 111;
		}

	}

	/*
	 * Method : getAllCountries( )
	 * 
	 * Input : empty List reference
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the all countries of supplymedium and convert
	 * into to List of countrydata objects. Insert into countryDataList
	 * parameter. So that it can used in caller class. Return type integer
	 * specified the success or failed result.
	 */

	public int getAllCountries( List<CountryData> countryDataList )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{
			CountryTable countryTblObj = new CountryTable( );

			int responseCode = countryTblObj.getAllCountries( countryDataList );

			countryTblObj = null;

			if( responseCode == 0 )
			{
				if( countryDataList.size( ) == 0 )
				{
//					String msg = "Info::DropDownFiller.getAllCountries() - " +
//							"No country available";
//
//					errLogger.logMsg( msg );

					return 122; // no countries available
				}

				return 120; // success
			}
			else
			{
				return 121; // failed
			}

		}
		catch( Exception ex )
		{
			String msg = "Exception::DropDownFiller.getAllCountries() - " + ex;

			errLogger.logMsg( msg );

			return 121;
		}

	}

	/*
	 * Method : getAllStates( )
	 * 
	 * Input : empty List reference, country name
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the all states for given country and convert
	 * into to List of statedata objects. Insert into stateDataList parameter.
	 * So that it can used in caller class. Return type integer specified the
	 * success or failed result.
	 */

	public int getAllStates( String country, List<StateData> stateDataList )
	{

		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{
			StateTable stateTblObj = new StateTable( );

			int responseCode = stateTblObj.getAllStates( country, stateDataList );

			stateTblObj = null;

			if( responseCode == 0 )
			{
				if( stateDataList.size( ) == 0 )
				{
//					String msg = "Info::DropDownFiller.getAllStates() - " +
//							"No states available";
//
//					errLogger.logMsg( msg );

					return 132; // no states available
				}

				return 130; // success
			}
			else
			{
				return 131; // failed
			}

		}
		catch( Exception ex )
		{
			String msg = "Exception::DropDownFiller.getAllStates() - " + ex;

			errLogger.logMsg( msg );

			return 131;
		}

	}

	/*
	 * Method : getAllCompanies( )
	 * 
	 * Input : empty List reference, country name
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get the all companies for given country and
	 * convert into to List of ccMapperData objects. Insert into
	 * ccMapperDataList parameter. So that it can used in caller class. Return
	 * type integer specified the success or failed result.
	 */

	public int getAllCompanies( String country,
	        								List<CCMapperData> ccMapperDataList )
	{

		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{
			CCMapperTable ccMapperTblObj = new CCMapperTable( );

			int responseCode = ccMapperTblObj.getAllCompanies( country,
			        												ccMapperDataList );

			ccMapperTblObj = null;

			if( responseCode == 0 )
			{
				if( ccMapperDataList.size( ) == 0 )
				{
//					String msg = "Info::DropDownFiller.getAllCompaniesForCountry() - " +
//							"" +
//							"No companies available";
//
//					errLogger.logMsg( msg );

					return 142; // no companies available
				}

				return 140; // success
			}
			else
			{
				return 141; // failed
			}

		}
		catch( Exception ex )
		{
			String msg = "Exception::DropDownFiller.getAllCompaniesForCountry() - " + ex;

			errLogger.logMsg( msg );

			return 141; // Failed
		}

	}
	
	/**
	 * @author Anbazhagan
	 * 
	 * @Date : Apr 7, 2013 8:34:53 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the Branched List from the DB
	 * 
	 * @param branchList
	 * @return
	 * 
	 */
	public int getAllBaranches( List<BranchData> branchList )
	{

		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{

			branchList.add( new BranchData( "Corporate Office" ) );
			branchList.add( new BranchData( "Site Office" ) );
			branchList.add( new BranchData( "Warehouse / Distribution Center" ) );
			branchList.add( new BranchData( "Store" ) );

			return 150;
		}
		catch( Exception ex )
		{
			String msg = "Exception :: DropDownFiller :getAllBaranches - " + ex;

			errLogger.logMsg( msg );

			return 151;
		}

	}

	/**
	 * 
	 * @author Anbazhagan
	 * 
	 * @Date : Apr 7, 2013 8:50:00 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the ThemeList
	 * 
	 * @param themeList
	 * @return
	 * 
	 */
	public int getAllTheme( List<ThemeData> themeList )
	{

		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{

			themeList.add( new ThemeData( "Theme 1" ) );
			themeList.add( new ThemeData( "Theme 2" ) );
			themeList.add( new ThemeData( "Theme 3" ) );
			themeList.add( new ThemeData( "Theme 4" ) );

			return 160;
		}
		catch( Exception ex )
		{
			String msg = "Exception :: DropDownFiller :getAllTheme - " + ex;

			errLogger.logMsg( msg );

			return 161;
		}

	}

	/*
	 * Method : get( )
	 * 
	 * Input : List<TransRejectReasonData> obj
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to get all the trans reject reasons
	 */

	public int get( List<TransRejectReasonData> transRejectReasonList )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		TransRejectReasonTable rejectReasonTable = new TransRejectReasonTable( );
		
		int result = rejectReasonTable.find( transRejectReasonList );

		if( result != 0 )
		{
			String msg = "Error::EntityLoader.get( ) - Unable to get Transaction reject reasons";

			errLogger.logMsg( msg );
			
			return 171;
		}
		
		if( transRejectReasonList.size( )  == 0 )
		{
			String msg = "Error::EntityLoader.get( ) - No transaction reject reasons available";

			errLogger.logMsg( msg );
			
			return 172;
		}
		
		return 170;
	}

}

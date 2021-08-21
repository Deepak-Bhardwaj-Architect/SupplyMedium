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

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.common.BranchData;
import core.common.BusinessCategoryData;
import core.common.CountryData;
import core.common.StateData;
import core.common.ThemeData;
import core.common.TransRejectReasonData;
import core.regn.CCMapperData;

import ctrl.common.EntityLoaderController;

/**
 * File:  EntityLoaderServlet.java 
 *
 * Created on Jan 18, 2013 11:27:28 AM
 */

/**
 * Servlet implementation class EntityLoaderServlet. It fetch the data for GUI
 * dropdown list.
 */

@WebServlet("/EntityLoaderServlet")
public class EntityLoaderServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public EntityLoaderServlet()
	{
		super( );

	}

	protected void doGet( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{

	}

	/*
	 * Method : doPost( )
	 * 
	 * Input : HttpServletRequest object , HttpServletResponse object
	 * 
	 * Return : none
	 * 
	 * Purpose: It get the request from browser. Then parse the
	 * HttpServletRequest and it send the drop down box data depending upon
	 * request using core classes.
	 */

	protected void doPost( HttpServletRequest request, HttpServletResponse response )
	        throws ServletException, IOException
	{

		String entity = request.getParameter( "entityname" );

		//ErrorLogger errLogger = ErrorLogger.instance( );

		//String msg = "Info :: EntityLoaderServlet : doPost -  Request for fetch " + entity;

		//errLogger.logMsg( msg );

		EntityLoaderController entityLoaderCtrl = new EntityLoaderController( );

		EntityLoaderDataComposer entityLoaderDataComp = new EntityLoaderDataComposer( );

		String jsonStr = "";

		int result = 0;

		if( entity.equals( "businesscategory" ) ) // fetch all business
		                                          // categories
		{
			List<BusinessCategoryData> businessCatDataList = new ArrayList<BusinessCategoryData>( );

			result = entityLoaderCtrl.getAllBusinessCategories( businessCatDataList );

			if( result == 110 ) // Success
			{
				jsonStr = entityLoaderDataComp
				        .composeBusinessCategoryJSON( businessCatDataList );
			}
			else
			// Failed
			{
				jsonStr = entityLoaderDataComp.composeErrorMessage( result );
			}

			businessCatDataList = null;
		}
		else if( entity.equals( "country" ) ) // fetch all countries
		{
			List<CountryData> countryDataList = new ArrayList<CountryData>( );

			result = entityLoaderCtrl.getAllCountries( countryDataList );

			if( result == 120 ) // Success
			{
				jsonStr = entityLoaderDataComp.composeCountryJSON( countryDataList );
			}
			else
			// Failed
			{
				jsonStr = entityLoaderDataComp.composeErrorMessage( result );
			}

			countryDataList = null;
		}
		else if( entity.equals( "state" ) ) // Fetch all states for given
		                                    // country
		{
			List<StateData> stateDataList = new ArrayList<StateData>( );

			String country = request.getParameter( "country" );

			result = entityLoaderCtrl.getAllStatesForCountry( country, stateDataList );

			if( result == 130 ) // Success
			{
				jsonStr = entityLoaderDataComp.composeStateJSON( stateDataList );
			}
			else
			// Failed
			{
				jsonStr = entityLoaderDataComp.composeErrorMessage( result );
			}

			stateDataList = null;

		}
		else if( entity.equals( "branch" ) ) // Fetch all states for given
		// country
		{
			List<BranchData> branchDataList = new ArrayList<BranchData>( );

			result = entityLoaderCtrl.getBranchList( branchDataList );

			if( result == 150 ) // Success
			{
				jsonStr = entityLoaderDataComp.composeBranchJSON( branchDataList );
			}
			else
			// Failed
			{
				jsonStr = entityLoaderDataComp.composeErrorMessage( result );
			}

			branchDataList = null;
		}
		else if( entity.equals( "theme" ) ) // Featch Theme List
		{
			List<ThemeData> themeList = new ArrayList<ThemeData>( );

			result = entityLoaderCtrl.getThemeList( themeList );

			if( result == 160 ) // Success
			{
				jsonStr = entityLoaderDataComp.composeThemeJSON( themeList );
			}
			else
			// Failed
			{
				jsonStr = entityLoaderDataComp.composeErrorMessage( result );
			}

			themeList = null;
		}

		else if( entity.equals( "company" ) ) // Fetch all companies for given
		                                      // country
		{
			List<CCMapperData> ccMapperDataList = new ArrayList<CCMapperData>( );

			String country = request.getParameter( "country" );

			result = entityLoaderCtrl.getAllCompaniesForCountry( country, ccMapperDataList );

			if( result == 140 ) // Success
			{
				jsonStr = entityLoaderDataComp.composeCompanyJSON( ccMapperDataList );
			}
			else
			// failed
			{
				jsonStr = entityLoaderDataComp.composeErrorMessage( result );
			}

			ccMapperDataList = null;
		}
		else if( entity.equals( "RejectReasons" ) )
		{
			List<TransRejectReasonData> transRejectReasonList = new ArrayList<TransRejectReasonData>( );
			
			result = entityLoaderCtrl.getTransRejectReasons( transRejectReasonList );
			
			if( result == 170 ) //Success
			{
				jsonStr = entityLoaderDataComp.composeRejectReasonJSON( transRejectReasonList );
			}
			else 
			{
				jsonStr = entityLoaderDataComp.composeErrorMessage( result );
			}
			
			transRejectReasonList = null;
		}

		entityLoaderCtrl = null;

		entityLoaderDataComp = null;

		System.out.println( "jsonStr=" + jsonStr );

		response.setContentType( "application/json" );

		response.setCharacterEncoding( "UTF-8" );

		response.getWriter( ).write( jsonStr );
	}
}

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

import java.util.ArrayList;
import java.util.List;

import utils.ErrorLogger;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.vendorregn.NRVendorSearchData;
import db.regn.CompanyRegnTable;
import db.vendorregn.RegnVendorMapTable;
import db.vendorregn.VendorRegnTable;
import utils.ErrorMaster;

/**
 * File:  SearchVendor.java 
 *
 * Created on 29-Jun-2013 12:19:03 PM
 * 
 * Purpose : This method is used to search the registered and non-registered
 * vendors
 */
public class SearchVendor
{
	ErrorLogger errorLogger_;
        private static ErrorMaster errorMaster_ = null;



	/*
	 * Method : SearchVendor -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public SearchVendor()
	{
		errorLogger_ = ErrorLogger.instance( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method: getNonRegList
	 * 
	 * Input: CompanyRegnKey obj, List<VendorRegnData> obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the list of company who are not 
	 * the registered vendors for the given CompanyRegnKey 
	 */
	
	public int getNonRegVendorList( NRVendorSearchData nrVendorSrchData, List<RegnData> NRVendorRegnDataList )
	{				
		RegnVendorMapTable mapTable = new RegnVendorMapTable( );
		
		List<CompanyRegnKey> vendorRegnKeyList = new ArrayList<CompanyRegnKey>( );
 		
		int result = mapTable.find( nrVendorSrchData.regnKey_, vendorRegnKeyList );
		
		mapTable = null;
		
		
		if( result != 0 )
		{
			errorLogger_.logMsg( "Error::SearchVendor.getNonRegList() - Failed to fetch regn keys" );
			
			vendorRegnKeyList = null;
			
			return  3641; //Failed to fetch regn keys
		}
		
		//This is added to filter the current user's company
		vendorRegnKeyList.add( nrVendorSrchData.regnKey_ );
		
		//To filter the companies who are all in the process of vendor registration 
		//to get non registered vendors
		VendorRegnTable vendorRegnTable = new VendorRegnTable( );
		
		List<CompanyRegnKey> filterList = new ArrayList<CompanyRegnKey>( );
		
		result = vendorRegnTable.filter( nrVendorSrchData.regnKey_, filterList );
		
		vendorRegnTable = null;
		
		if( result != 0)
		{
			errorLogger_.logMsg( "Error::SearchVendor.getNonRegList() - Failed to fetch regn keys" );
			
			vendorRegnKeyList = null;
			
			return  3641; //Failed to fetch regn keys			
		}
		
		for( CompanyRegnKey key : filterList )
        {
	        vendorRegnKeyList.add( key );
        }
		
		//This will fetch 10 companies in the list
		
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		result = regnTable.find( vendorRegnKeyList, NRVendorRegnDataList, nrVendorSrchData );
		
		vendorRegnKeyList = null;
		
		if( result != 0)
		{
			errorLogger_.logMsg( "Error::SearchVendor.getNonRegList() - Failed to fetch 10 NR Company list" );
			
			vendorRegnKeyList = null; regnTable = null;
			
			return  3642; //Failed to 10 NR Company list
		}
		
		 regnTable = null;
		
		return 3640;
	}
	
	/*
	 * Method: getRegList
	 * 
	 * Input: CompanyRegnKey obj, List<VendorRegnData> obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the list of company who are not 
	 * the registered vendors for the given CompanyRegnKey 
	 */
	
	public int getRegnVendorList( RVendorSearchData rVendorSrchData, List<RegnData> RVendorRegnDataList )
	{				
		RegnVendorMapTable mapTable = new RegnVendorMapTable( );
		
		List<CompanyRegnKey> vendorRegnKeyList = new ArrayList<CompanyRegnKey>( );
 		
		int result = mapTable.find( rVendorSrchData.regnKey_, vendorRegnKeyList );
		
		errorMaster_.insert( "regn vendor list count="+vendorRegnKeyList.size( ) );
		
		mapTable = null;
		
		if( vendorRegnKeyList.size( ) == 0 )  // There is no registered vendor for this company
			return 3645;
		
		if( result != 0 )
		{
			errorLogger_.logMsg( "Error::SearchVendor.getRegnVendorList() - Failed to fetch regn keys" );
			
			vendorRegnKeyList = null;
			
			return  3646; //Failed to fetch regn keys
		}
		
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		result = regnTable.find( vendorRegnKeyList, RVendorRegnDataList, rVendorSrchData );
		
		regnTable = null;
		
		
		return 3645;
	}

}

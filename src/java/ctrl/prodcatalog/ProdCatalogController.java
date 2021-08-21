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
package ctrl.prodcatalog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.IntHolder;
import utils.StringHolder;

import core.prodcatalog.ProductCatalog;
import core.prodcatalog.ProductCatalogData;
import core.prodcatalog.ProductKey;
import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * @FileName : ProdCatalogController.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Jul 27, 2013
 * 
 * @Purpose :
 * 
 */
public class ProdCatalogController
{

	/**
	 * @Date : Jul 27, 2013 11:43:14 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param productCatalogList
	 * @return
	 * 
	 */
	public int getAllCatalog( HttpServletRequest request, List<ProductCatalogData> productCatalogList )
	{
		int result = 0;
		CompanyRegnKey regnKey = new CompanyRegnKey( );
		regnKey.companyPhoneNo_ = request.getParameter( "CompanyRegnKey" );

		ProductCatalog prodCatalog = new ProductCatalog( );
		result = prodCatalog.find( regnKey, productCatalogList );

		return result;
	}

	/**
	 * @Date : Jul 27, 2013 7:08:16 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param removingItem
	 * @return
	 * 
	 */
	public int RemoveItem( HttpServletRequest request, StringHolder removingItem )
	{
		int result;
		String item_key = request.getParameter( "RemoveItemID" );
		removingItem.value = item_key;

		ProductKey key = new ProductKey( );
		key.productKey_ = item_key;

		if( item_key != "" )
		{
			ProductCatalog prodCatalog = new ProductCatalog( );
			result = prodCatalog.remove( key );

		}
		else
		{
			result = 15014;
		}

		return result;
	}

	/**
	 * @Date : Jul 28, 2013 11:03:14 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int UpdateItem( HttpServletRequest request )
	{
		int result;

		ProductCatalogDataConvertor dataconObj = new ProductCatalogDataConvertor( );
		ProductCatalogData prodCatalogdata = new ProductCatalogData( );
		result = dataconObj.Convert( request, prodCatalogdata );

		if( result == 0 )
		{
			ProductCatalog prodCatalog = new ProductCatalog( );
			result = prodCatalog.update( prodCatalogdata );

		}
		else
		{
			result = 15024;
		}

		return result;
	}

	/**
	 * @Date : Jul 28, 2013 1:59:31 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param addedItemKey
	 * @return
	 * 
	 */
	public int AddItem( HttpServletRequest request )
	{
		int result;

		ProductCatalogDataConvertor dataconObj = new ProductCatalogDataConvertor( );
		List<ProductCatalogData> listdata = new ArrayList<ProductCatalogData>( );
		result = dataconObj.Convert( request, listdata );
		if( result == 0 && listdata.size( ) > 0 )
		{
			IntHolder temp=new IntHolder( );
			ProductCatalog prodCatalog = new ProductCatalog( );
			result = prodCatalog.add( listdata ,temp);

		}
		else
		{
			result = 15034;
		}

		return result;
	}

	/**
	 * @Date : Jul 28, 2013 4:48:53 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int ProductKeyExist( HttpServletRequest request, IntHolder value )
	{
		int result;

		CompanyRegnKey regnkey = new CompanyRegnKey( );
		regnkey.companyPhoneNo_ = request.getParameter( "cmpregnKey" );

		String PartNumber = request.getParameter( "PartNumber" );

		if( !PartNumber.equalsIgnoreCase( "" ) )
		{
			ProductCatalog prodCatalog = new ProductCatalog( );
			result = prodCatalog.productkeyExist( regnkey, PartNumber, value );

		}
		else
		{
			result = 15041;
		}

		return result;
	}

	/**
	 * @Date : Jul 30, 2013 7:51:36 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @return
	 * 
	 */
	public int CSVimport( HttpServletRequest request, IntHolder TotalNumberofRecord, IntHolder TotalNumberofRecordAdded )
	{
		int result;

		ProductCatalogCSVConvertor csvConvertor = new ProductCatalogCSVConvertor( );
		List<ProductCatalogData> listdata = new ArrayList<ProductCatalogData>( );

		result = csvConvertor.CSVConvert( request, listdata, TotalNumberofRecord );
		 ErrorMaster errorMaster_ = null;
	if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		if( result == 15053 )
		{
			errorMaster_.insert( "listdata coun="+listdata.size( ) );
			
			if( listdata.size( ) > 0 )
			{
				ProductCatalog prodCatalog = new ProductCatalog( );
				result = prodCatalog.add( listdata, TotalNumberofRecordAdded );
				
				if(result == 15030)
					result = 15050;
				else if (result == 15031)
					result = 15055;
			}
			else
			{
				result = 15054; // No Record Found
			}

		}

		return result;
	}

}

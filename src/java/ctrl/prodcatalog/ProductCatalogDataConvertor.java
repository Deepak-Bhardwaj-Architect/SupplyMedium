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

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import core.common.CurrencyKey;
import core.common.QuanTypeKey;
import core.prodcatalog.ProductCatalogData;
import core.prodcatalog.ProductKey;
import core.regn.CompanyRegnKey;

/**
 * @FileName : ProductCatalogDataConvertor.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Jul 28, 2013
 * 
 * @Purpose :
 * 
 */
public class ProductCatalogDataConvertor
{

	/**
	 * @Date : Jul 28, 2013 11:06:55 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param prodCatalogdata
	 * @return
	 * 
	 */
	public int Convert( HttpServletRequest request, ProductCatalogData prodCatalogdata )
	{
		try
		{
			Map<String, String [ ]> reqMap = request.getParameterMap( );
			if( reqMap.containsKey( "item_key" ) )
			{
				prodCatalogdata.itemKey_ = request.getParameter( "item_key" );
			}

			if( reqMap.containsKey( "cmp_regn_key" ) )
			{
				CompanyRegnKey regnkey = new CompanyRegnKey( );
				regnkey.companyPhoneNo_ = request.getParameter( "cmp_regn_key" );
				prodCatalogdata.itemCompRegnRelKey_ = regnkey;
			}

			if( reqMap.containsKey( "item_name" ) )
			{
				prodCatalogdata.itemName_ = request.getParameter( "item_name" );
			}

			if( reqMap.containsKey( "item_description" ) )
			{
				prodCatalogdata.itemDesc_ = request.getParameter( "item_description" );
			}

			if( reqMap.containsKey( "item_part_no" ) )
			{
				prodCatalogdata.itemPartNo_ = request.getParameter( "item_part_no" );
			}

			if( reqMap.containsKey( "item_sku" ) )
			{
				prodCatalogdata.itemsku_ = request.getParameter( "item_sku" );
			}

			if( reqMap.containsKey( "item_quantity" ) )
			{
				prodCatalogdata.itemQuan_ = request.getParameter( "item_quantity" );
			}

			if( reqMap.containsKey( "quantity_unit" ) )
			{
				QuanTypeKey key = new QuanTypeKey( );
				key.quanTypeKey_ = request.getParameter( "quantity_unit" );
				prodCatalogdata.itemQuanTypeRelKey_ = key;
			}

			if( reqMap.containsKey( "item_price" ) )
			{
				prodCatalogdata.itemPrice_ = request.getParameter( "item_price" );
			}

			if( reqMap.containsKey( "item_currency" ) )
			{
				CurrencyKey key = new CurrencyKey( );
				key.currencyKey_ = request.getParameter( "item_currency" );
				prodCatalogdata.itemCrcyRelKey_ = key;
			}

			if( reqMap.containsKey( "item_tax" ) )
			{
				prodCatalogdata.itemTaxRate_ = request.getParameter( "item_tax" );
			}

			if( reqMap.containsKey( "HidePrice" ) )
			{
				prodCatalogdata.hidePrice_ = request.getParameter( "HidePrice" );
			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: ProductCatalogDataConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

	}

	public int Convert( HttpServletRequest request, List<ProductCatalogData> prodCatalogdata )
	{
		try
		{

			Map<String, String [ ]> reqMap = request.getParameterMap( );

			CompanyRegnKey regnkey = new CompanyRegnKey( );
			regnkey.companyPhoneNo_ = request.getParameter( "cmp_regn_key" );

			String insetValue = "";
			if( reqMap.containsKey( "values" ) )
			{
				insetValue = request.getParameter( "values" );
			}
			

			if( insetValue.equalsIgnoreCase( "" ) ) return -1;

			String[] ManList = insetValue.split( "\\|" );

			if( ManList.length < 0 ) return -1;
			

			for( int i = 0; i < ManList.length; i++ )
			{
				if( !ManList[i].equalsIgnoreCase( "" ) )
				{					
					String[] innerList=ManList[i].split( "\\^" );
					
					if(innerList.length>9)
					{
						ProductCatalogData data = new ProductCatalogData( );
						data.itemCompRegnRelKey_ = regnkey;
						
						data.itemName_=innerList[2];
						data.itemDesc_ = innerList[3];
						data.itemPartNo_ = innerList[4];
						data.itemsku_ = innerList[5];
						data.barcode_=innerList[6];
						String[] Temp = innerList[7].split( " " );	
						data.itemQuan_ = Temp[0];
						QuanTypeKey quankey=new QuanTypeKey( );
						quankey.quanTypeKey_ = Temp[1];
						data.itemQuanTypeRelKey_ = quankey;
						
						String[] Temp2 = innerList[8].split( " " );
						data.itemPrice_ = Temp2[1];
						CurrencyKey currkey = new CurrencyKey( );
						currkey.currencyKey_ = Temp2[0];
						data.itemCrcyRelKey_ = currkey;
						
						data.itemTaxRate_ = innerList[9];	
						data.hidePrice_ = innerList[10].equalsIgnoreCase( "false" )?"0":"1";
						
						data.itemKey_ = data.itemPartNo_+":"+data.itemCompRegnRelKey_.companyPhoneNo_;
						prodCatalogdata.add( data );
					}
				}

			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: ProductCatalogDataConvertor : convert - " + ex;
			errLogger.logMsg( errMsg );
			return -1;
		}

	}

}

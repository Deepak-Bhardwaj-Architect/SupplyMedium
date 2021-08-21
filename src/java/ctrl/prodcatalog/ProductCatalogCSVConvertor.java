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

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import utils.ErrorLogger;
import utils.IntHolder;

import core.common.CurrencyKey;
import core.common.QuanTypeKey;
import core.prodcatalog.ProductCatalogData;
import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * @FileName : ProductCatalogCSVConvertor.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Jul 30, 2013
 * 
 * @Purpose :
 * 
 */
public class ProductCatalogCSVConvertor
{

	/**
	 * @Date : Jul 30, 2013 7:53:32 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param request
	 * @param listdata
	 * @return
	 * 
	 */
	public int CSVConvert( HttpServletRequest request, List<ProductCatalogData> listdata, IntHolder NumberofRecords )
	{
            
		try
		{
                    ErrorMaster errorMaster_ = null;
                    if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			CompanyRegnKey regnkey = new CompanyRegnKey( );
			regnkey.companyPhoneNo_ = request.getParameter( "cmpRegnKey" );

			Object CSVFile = request.getAttribute( "csv_data" );
			errorMaster_.insert( regnkey.companyPhoneNo_ + "------" + CSVFile );
			if( CSVFile != null )
			{
				// FileItem item = (FileItem)iter.next( );
				FileItem item = (FileItem)CSVFile;
				errorMaster_.insert( "------" + item.getName( ) );
				// processes only fields that are not form fields
				if( !item.isFormField( ) )
				{

					BufferedReader br = null;
					String cvsSplitBy = "\\,";
					String line = "";
					int i = 0;
					boolean isInValidData = false;

					try
					{
						br = new BufferedReader( new InputStreamReader( item.getInputStream( ) ) );
						while( ( line = br.readLine( ) ) != null )
						{
							errorMaster_.insert( line );
							String [ ] innerList = line.split( cvsSplitBy );
							
							isInValidData = false;

							if( i == 0 )
							{
								// Validating the Column Header
								if( !innerList[0].trim( ).equalsIgnoreCase( "Part Name" ) || !innerList[1].trim( ).equalsIgnoreCase( "Item Description" )
								        || !innerList[2].trim( ).equalsIgnoreCase( "Part Number" ) || !innerList[3].trim( ).equalsIgnoreCase( "SKU" )
								        || !innerList[4].trim( ).equalsIgnoreCase( "Quantity" ) || !innerList[5].trim( ).equalsIgnoreCase( "Quantity Unit" )
								        || !innerList[6].trim( ).equalsIgnoreCase( "Price" ) || !innerList[7].trim( ).equalsIgnoreCase( "Currency" )
								        || !innerList[8].trim( ).equalsIgnoreCase( "Tax" )  )
								{
									return 15051; // CSV Not in Correct format
								}
							}
							else
							{

								if( innerList.length == 9 )
								{
									ProductCatalogData data = new ProductCatalogData( );
									data.itemCompRegnRelKey_ = regnkey;

									if( innerList[0].length( ) >= 1 )
									{
										if( innerList[0].length( ) > 80)
										{
											data.itemName_ = innerList[0].substring(0, 79);
										}
										else
										{
											data.itemName_ = innerList[0];
										}
										
									}
									else
									{
										isInValidData = true;
									}

									if( innerList[1].length( ) >= 1 )
									{
										if( innerList[1].length( ) > 80)
										{
											data.itemDesc_ = innerList[1].substring(0, 79);
										}
										else
										{
											data.itemDesc_ = innerList[1];
											
										}
										
									}
									else
									{
										isInValidData = true;
									}

									if( innerList[2].length( ) >= 1 )
									{
										if( innerList[2].length( ) > 80)
										{
											data.itemPartNo_ = innerList[2].substring(0, 79);
										}
										else
										{
											data.itemPartNo_ = innerList[2];
										}
										
									}
									else
									{
										isInValidData = true;
									}

									if( innerList[3].length( ) >= 1 )
									{
										if( innerList[3].length( ) > 80)
										{
											data.itemsku_ = innerList[3].substring(0, 79);
										}
										else
										{
											data.itemsku_ = innerList[3];
										}
										
									}
									else
									{
										isInValidData = true;
									}

									if( isInteger( innerList[4] ) )
									{
										data.itemQuan_ = innerList[4];
									}
									else
									{
										isInValidData = true;
									}

									if( innerList[5].length( ) > 0 )
									{
										QuanTypeKey quankey = new QuanTypeKey( );
										quankey.quanTypeKey_ = innerList[5];
										data.itemQuanTypeRelKey_ = quankey;
									}
									else
									{
										isInValidData = true;
									}

									if( isInteger( innerList[6] ) )
									{
										data.itemPrice_ = innerList[6];
									}
									else
									{
										isInValidData = true;
									}

									if( innerList[7].length( ) > 0 )
									{
										CurrencyKey currkey = new CurrencyKey( );
										currkey.currencyKey_ = innerList[7];
										data.itemCrcyRelKey_ = currkey;
									}
									else
									{
										isInValidData = true;
									}

									if( isInteger( innerList[8] ) )
									{
										data.itemTaxRate_ = innerList[8];
									}
									else
									{
										isInValidData = true;
									}

									
									data.hidePrice_ =  "0" ;
									
									data.itemKey_ = data.itemPartNo_ + ":" + data.itemCompRegnRelKey_.companyPhoneNo_;
									
									data.show( );
									
									errorMaster_.insert(""+isInValidData);
									errorMaster_.insert(""+innerList[3].length( ));

									if( !isInValidData )
									{
										listdata.add( data );
									}
								}
							}

							i++;

						}
						
						NumberofRecords.value = i-1;
					}
					catch( Exception ex )
					{
						ErrorLogger errLogger = ErrorLogger.instance( );
						String errMsg = "Exception :: ProductCatalogCSVConvertor : CSVConvert - " + ex;
						errLogger.logMsg( errMsg );
						return 15052;
					}
					finally
					{
						br.close( );
					}
				}
			}
			

			return 15053;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger = ErrorLogger.instance( );
			String errMsg = "Exception :: ProductCatalogCSVConvertor : CSVConvert - " + ex;
			errLogger.logMsg( errMsg );
			return 15052;
		}
	}

	public boolean isInteger( String str )
	{
		try{
		    double d= Double.valueOf(str);
		    d=(int)d;
		    return true;
		}catch(Exception e){
		    return false;
		}
	}
}

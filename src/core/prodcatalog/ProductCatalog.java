package core.prodcatalog;

import java.util.List;

import utils.ErrorLogger;
import utils.IntHolder;

import core.regn.CompanyRegnKey;
import db.prodcatalog.ProductCatalogTable;

/**
 * This is the core class for the product catalog which used for add, update,
 * remove and to find the product catalog under a supplier company
 */
public class ProductCatalog
{
	private ErrorLogger	errLogger_;

	/**
	 * Method : ProductCatalog( ) - constructor Input : Return :
	 * 
	 * Purpose: It is used initialize the table name
	 */
	public ProductCatalog()
	{
		this.errLogger_ = ErrorLogger.instance( );
	}

	/**
	 * Method : add( ) Input : ProductCatalogData data list Return : int
	 * success/failed
	 * 
	 * Purpose: It is used to add the list of product catalogs under a supplier
	 * company.
	 */
	public int add( List<ProductCatalogData> pdList,IntHolder NumberofRecordAdded )
	{
		try
		{
			ProductCatalogTable prodCatalogTblObj = new ProductCatalogTable( );
			int result = prodCatalogTblObj.add( pdList, NumberofRecordAdded);
			prodCatalogTblObj = null;
			if( result == -1 )
			{
				return 15031;
				// Exception occurred while creating a record object
			}
			else if( result == -2 )
			{
				return 15032;
				// SQLException occurred while adding a product catalog
			}
			else if( result == -3 )
			{
				return 15033;
				// Error occurred while adding a product catalog
			}
			else if( result == -4 )
			{
				return 15034;
				// Error occurred while adding list of product catalog
			}
			else
			{
				return 15030;
				// List of product catalog has been created successfully
			}

		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::ProductCatalog : add " + ex );

			return 2009;
			// Exception occurred while adding a product catalog
		}
	}

	/**
	 * Method : update( ) Input : ProductCatalogData data Return : int
	 * success/failed
	 * 
	 * Purpose: It is used to update a product catalog under a supplier company.
	 */
	public int update( ProductCatalogData pdata )
	{
		try
		{
			ProductCatalogTable prodCatalogTblObj = new ProductCatalogTable( );
			int result = prodCatalogTblObj.update( pdata );
			prodCatalogTblObj = null;
			if( result == -1 )
			{
				return 15021;
				// Exception occurred while creating a record object
			}
			else if( result == -2 )
			{
				return 15022;
				// Error occurred while updating a product catalog
			}
			else if( result == -3 )
			{
				return 15023;
				// SQLException occurred while updating a product catalog
			}
			else
			{
				return 15020;
				// Product catalog has been updated successfully
			}

		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::ProductCatalog : update " + ex );

			return 2024;
			// Exception occurred while updating a product catalog
		}
	}

	/**
	 * Method : remove( ) Input : ProductKey key object Return : int
	 * success/failed
	 * 
	 * Purpose: It is used to remove a product catalog under a supplier company.
	 */
	public int remove( ProductKey key )
	{
		try
		{
			ProductCatalogTable prodCatalogTblObj = new ProductCatalogTable( );
			int result = prodCatalogTblObj.remove( key );
			prodCatalogTblObj = null;
			if( result == -1 )
			{
				return 15011;
				// Error occurred while removing a product catalog
			}
			else if( result == -2 )
			{
				return 15012;
				// SQLException occurred while removing a product catalog
			}
			else
			{
				return 15010;
				// Product catalog has been removed successfully
			}

		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::ProductCatalog : remove " + ex );

			return 15013;
			// Exception occurred while removing a product catalog
		}
	}

	/**
	 * Method : find( ) Input : CompanyRegnKey key object and ProductCatalogData
	 * data list Return : int success/failed
	 * 
	 * Purpose: It is used to find a product catalog under a supplier company
	 * and in response we get the list of product catalogs under a supplier
	 * company.
	 */
	public int find( CompanyRegnKey key, List<ProductCatalogData> pdList )
	{
		try
		{
			ProductCatalogTable prodCatalogTblObj = new ProductCatalogTable( );
			int result = prodCatalogTblObj.find( key, pdList );
			prodCatalogTblObj = null;
			if( result == -1 )
			{
				return 15001;
				// SQLException occurred while finding a product catalog
			}
			else
			{
				return 15000;
				// Product catalogs has been retrieved successfully
			}

		}
		catch( Exception ex )
		{
			ex.printStackTrace( );
			errLogger_.logMsg( "Exception::ProductCatalog : find " + ex );

			return 15001;
			// Exception occurred while finding a product catalog
		}
	}

	/**
	 * @Date : Jul 28, 2013 4:51:26 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param regnkey
	 * @param partNumber
	 * @return
	 * 
	 */
	public int productkeyExist( CompanyRegnKey regnkey, String partNumber , IntHolder resultvalue)
	{
		try
		{
			ProductCatalogTable prodCatalogTblObj = new ProductCatalogTable( );
			ProductKey prodkey = new ProductKey( );
			prodkey.productKey_ = partNumber + ":" + regnkey.companyPhoneNo_;
			boolean result = prodCatalogTblObj.isItemExists( prodkey );
			prodCatalogTblObj = null;
			if(result)
			{
				resultvalue.value = 1;
				return 15040;
				
				// SQLException occurred while finding a product catalog
			}
			else
			{
				resultvalue.value=0;
				return 15040;
				// Product catalogs has been retrieved successfully
			}

		}
		catch( Exception ex )
		{
			ex.printStackTrace( );
			errLogger_.logMsg( "Exception::ProductCatalog : productkeyExist " + ex );

			return 15041;
			// Exception occurred while finding a product catalog
		}
	}
}

package db.prodcatalog;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import utils.ErrorLogger;
import utils.IntHolder;
import utils.StringHolder;

import core.prodcatalog.ProductCatalogData;
import core.prodcatalog.ProductKey;
import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import core.searchsupplier.SearchOption;
import db.utils.DBConnect;
import utils.ErrorMaster;

/**
 * This is the db class for the product_catalog table in the database.
 * 
 */

public class ProductCatalogTable
{
private static ErrorMaster errorMaster_ = null;


	private ErrorLogger	errLogger_;
	private String	    tableName_;
	private Statement	stmt_;
	private Connection	con_;

	/**
	 * Method : ProductCatalogTable( ) - constructor Input : Return :
	 * 
	 * Purpose: It is used initialize the table name
	 */
	public ProductCatalogTable()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.errLogger_ = ErrorLogger.instance( );
		this.tableName_ = "product_catalog";
		this.stmt_ = null;
		this.con_ = null;
	}

	/**
	 * Method : add( ) Input : ProductCatalogData data list Return : int 0 =>
	 * success / -ve nos => failed
	 * 
	 * Purpose: It is used to add the list of product catalog data under a
	 * supplier company.
	 * 
	 */
	public int add( List<ProductCatalogData> pdList, IntHolder NumberofAddedRecord )
	{

		List<ProductCatalogRecord> cmpList = new ArrayList<ProductCatalogRecord>( );

		for( ProductCatalogData pcData : pdList )
		{

			pcData.show( );

			ProductCatalogRecord pcRecord = null;
			pcRecord = createProdCatalogRecord( pcData );

			ProductKey prodKey = new ProductKey( );
			prodKey.productKey_ = pcData.itemKey_;

			if( isItemExists( prodKey ) )
			{
				cmpList.add( pcRecord );
				continue;
			}

			try
			{
				con_ = DBConnect.instance( ).getConnection( );
				stmt_ = con_.createStatement( );

				// form the query

				String insertQuery = null;
				String insertValues = null;

				insertValues = "( item_key, item_desc, part_number, item_name,";
				insertValues = insertValues + "quantity, quan_type_rel_key, price, currency_rel_key,";
				insertValues = insertValues + "tax_rate, regn_rel_key,hide_price,SKU,barcode_id ) ";

				insertQuery = "INSERT INTO " + this.tableName_ + " " + insertValues + " VALUES ";
				insertQuery = insertQuery + "(";

				insertQuery = insertQuery + "'" + pcRecord.itemKey_ + "', ";
				insertQuery = insertQuery + "'" + pcRecord.itemDesc_ + "', ";
				insertQuery = insertQuery + "'" + pcRecord.itemPartNo_ + "', ";

				insertQuery = insertQuery + "'" + pcRecord.itemName_ + "', ";
				insertQuery = insertQuery + "'" + pcRecord.quantity_ + "', ";
				insertQuery = insertQuery + "'" + pcRecord.quantityTypeRelKey_ + "', ";

				insertQuery = insertQuery + "'" + pcRecord.price_ + "', ";
				insertQuery = insertQuery + "'" + pcRecord.currencyRelKey_ + "', ";
				insertQuery = insertQuery + "'" + pcRecord.taxRate_ + "', ";

				insertQuery = insertQuery + "'" + pcRecord.regnRelKey_ + "',";
				insertQuery = insertQuery + "" + pcRecord.hidePrice_ + ",";
				insertQuery = insertQuery + "'" + pcRecord.sku_ + "',";
                                insertQuery = insertQuery + "'" + pcRecord.barcode_ + "'";

				insertQuery = insertQuery + ")";
				errorMaster_.insert( "----------------------------" );
				errorMaster_.insert( insertQuery );

				int val = stmt_.executeUpdate( insertQuery );

				// create a list of item keys that are not added.
				if( val == 0 ) cmpList.add( pcRecord );

			}
			catch( SQLException ex )
			{

				// print the item keys not added here.
				errLogger_.logMsg( "SQLException::ProductCatalogTable.add( ) " + ex );
				cmpList.add( pcRecord );

				// return -1;

			}

		}

		NumberofAddedRecord.value = pdList.size( ) - cmpList.size( );

		if( cmpList.size( ) == 0 )
			return 0;
		else
			return -1;

	}

	/**
	 * Method : update( ) Input : ProductCatalogData data Return : int 0 =>
	 * success/ -ve no => Failure
	 * 
	 * Purpose: It is used to update a product catalog under a supplier company.
	 * 
	 */

	public int update( ProductCatalogData pcData )
	{

		ProductCatalogRecord pcRecord = null;

		pcRecord = createProdCatalogRecord( pcData );

		int updateResult = 0;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );
			stmt_ = con_.createStatement( );
			// form the query

			String updateQuery = null;

			updateQuery = "UPDATE " + this.tableName_ + " SET ";

			updateQuery = updateQuery + " item_desc = '" + pcRecord.itemDesc_ + "', ";
			updateQuery = updateQuery + " part_number = '" + pcRecord.itemPartNo_ + "', ";

			updateQuery = updateQuery + " item_name = '" + pcRecord.itemName_ + "', ";
			updateQuery = updateQuery + " quantity = '" + pcRecord.quantity_ + "', ";
			updateQuery = updateQuery + " quan_type_rel_key = '" + pcRecord.quantityTypeRelKey_ + "', ";

			updateQuery = updateQuery + " price = '" + pcRecord.price_ + "', ";
			updateQuery = updateQuery + " currency_rel_key = '" + pcRecord.currencyRelKey_ + "', ";
			updateQuery = updateQuery + " tax_rate = '" + pcRecord.taxRate_ + "', ";

			updateQuery = updateQuery + " SKU = '" + pcRecord.sku_ + "', ";

			updateQuery = updateQuery + " hide_price = " + pcRecord.hidePrice_ + ", ";

			updateQuery = updateQuery + " regn_rel_key = '" + pcRecord.regnRelKey_ + "'";

			updateQuery = updateQuery + "WHERE item_key= '" + pcRecord.itemKey_ + "'";

			errorMaster_.insert( updateQuery );

			updateResult = stmt_.executeUpdate( updateQuery );

			if( updateResult > 0 )
			{
				return 0;
				// Success
			}
			else
			{
				errLogger_.logMsg( "Error::ProductCatalogTable.update( ) " + "Error updating product catalog for item_key::" + pcRecord.itemKey_ );
				return -1;
				// Error occurred while updating a product catalog
			}

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::ProductCatalogTable.update( ) " + ex );

			return -2;
			// SQLException occurred while updating a product catalog
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

		int deleteResult = 0;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			stmt_ = con_.createStatement( );

			// form the query
			String deleteQuery = null;
			deleteQuery = "DELETE FROM " + this.tableName_ + " WHERE item_key= '" + key.productKey_ + "'";

			deleteResult = stmt_.executeUpdate( deleteQuery );

			if( deleteResult > 0 )
			{
				return 0;
				// Success
			}
			else
			{
				errLogger_.logMsg( "Error::ProductCatalogTable.remove( ) " + "Error in removing the item::" + key.productKey_
				        + " from product_catalog" );
				return -1;
				// Error occurred while removing a product catalog
			}

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::ProductCatalogTable.remove( ) for item::" + key.productKey_ + " \n" + ex );

			return -2;
			// SQLException occurred while removing a product catalog
		}

	}

	/**
	 * Method : isItemExists( ) Input : partNumber String and CompanyRegnKey key
	 * object Return : boolean exist/not-exist
	 * 
	 * Purpose: It is used for checking item already exist or not.
	 * 
	 */

	public boolean isItemExists( ProductKey prodKey )
	{

		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );
			stmt_ = con_.createStatement( );

			query = "SELECT item_key FROM " + this.tableName_ + " WHERE item_key = '" + prodKey.productKey_ + "'";
			System.out.print( query );
			ResultSet rs = stmt_.executeQuery( query );

			if( rs.next( ) )
				return true;
			else
				return false;

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::ProductCatalogTable.isItemExists( ) " + ex );
			return false;
			// SQLException occurred while checking the existence of a product
			// catalog
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

	public int find( CompanyRegnKey key, List<ProductCatalogData> pcdList )
	{

		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );
			stmt_ = con_.createStatement( );

			query = "SELECT * FROM " + this.tableName_ + " WHERE regn_rel_key = '" + key.companyPhoneNo_ + "'";
			ResultSet rs = stmt_.executeQuery( query );

			int val = rsToPCDataList( rs, pcdList );
			System.out.print( query );
			return val;
			// Success
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::ProductCatalogTable.find( ) " + ex );

			return -1;
			// SQLException occurred while finding a product catalog
		}

	}

	/**
	 * Method : getCompanyRecordList( ) Input : ResultSet Return :
	 * ProductCatalogData data list
	 * 
	 * Purpose: It is used to find a retrieve the list of ProductCatalogData
	 * objects from the result set.
	 */
	private int rsToPCDataList( ResultSet rs, List<ProductCatalogData> pcdList )
	{

		ProductCatalogData pcData = null;

		try
		{
			while( rs.next( ) )
			{
				pcData = null;
				pcData = new ProductCatalogData( );

				pcData.itemKey_ = rs.getString( "item_key" );
				pcData.itemDesc_ = rs.getString( "item_desc" );
				pcData.itemPartNo_ = rs.getString( "part_number" );
				pcData.itemName_ = rs.getString( "item_name" );
				pcData.itemQuan_ = rs.getBigDecimal( "quantity" ).toString( );
				pcData.itemQuanTypeRelKey_.quanTypeKey_ = rs.getString( "quan_type_rel_key" );
				pcData.itemPrice_ = rs.getBigDecimal( "price" ).toString( );
				pcData.itemCrcyRelKey_.currencyKey_ = rs.getString( "currency_rel_key" );
				pcData.itemTaxRate_ = rs.getBigDecimal( "tax_rate" ).toString( );
				pcData.itemCompRegnRelKey_.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				pcData.itemsku_ = rs.getString( "sku" );
                                pcData.barcode_=rs.getString( "barcode_id" );
				String temp = rs.getString( "hide_price" );
				if( temp.equalsIgnoreCase( "0" ) )
					pcData.hidePrice_ = "False";
				else
					pcData.hidePrice_ = "True";

				pcdList.add( pcData );

			}

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::ProductCatalogTable.rsToPCDataList( ) " + ex );

			return -1;

			// SQLException occurred while populating a product catalog data
			// list
		}

		return 0;

	}

	/**
	 * Method : createProdCatalogRecord( ) Input : ProductCatalogData data
	 * object Return : ProductCatalogRecord record object
	 * 
	 * Purpose: It is used to create record object from the data object
	 */
	private ProductCatalogRecord createProdCatalogRecord( ProductCatalogData pcData )
	{
		// Form the record using the prodCatalogDataObj and return it
		ProductCatalogRecord prodCatalogRecord = new ProductCatalogRecord( );

		// set the values
		if( pcData.itemKey_ == "" )
		{
			pcData.itemKey_ = pcData.itemCompRegnRelKey_.companyPhoneNo_;
			pcData.itemKey_ += ":";
			pcData.itemKey_ += pcData.itemPartNo_;

		}

		// set the key if its already in pcData or form the key as above using
		// pcData values
		prodCatalogRecord.itemKey_ = pcData.itemKey_;

		prodCatalogRecord.itemDesc_ = pcData.itemDesc_;
		prodCatalogRecord.itemPartNo_ = pcData.itemPartNo_;
		prodCatalogRecord.itemName_ = pcData.itemName_;
		prodCatalogRecord.quantity_ = pcData.itemQuan_;
		prodCatalogRecord.quantityTypeRelKey_ = pcData.itemQuanTypeRelKey_.quanTypeKey_;
		prodCatalogRecord.price_ = pcData.itemPrice_;
		prodCatalogRecord.currencyRelKey_ = pcData.itemCrcyRelKey_.currencyKey_;
		prodCatalogRecord.taxRate_ = pcData.itemTaxRate_;
		prodCatalogRecord.regnRelKey_ = pcData.itemCompRegnRelKey_.companyPhoneNo_;
		prodCatalogRecord.sku_ = pcData.itemsku_;
		prodCatalogRecord.hidePrice_ = pcData.hidePrice_;
                prodCatalogRecord.barcode_ = pcData.barcode_;

		return prodCatalogRecord;

	}

	/**
	 * 
	 */
	public int getCompanyKeys( ProductCatalogData pcData, Set<CompanyRegnKey> companyKeys )
	{

		String query = null;
		StringHolder whereClause = new StringHolder( );

		whereClause.value = "";

		try
		{
			con_ = DBConnect.instance( ).getConnection( );
			stmt_ = con_.createStatement( );

			this.formWhereClause( pcData, whereClause );

			query = "SELECT DISTINCT regn_rel_key FROM " + this.tableName_ + whereClause.value;

			errorMaster_.insert( "Query: " + query );

			ResultSet rs = stmt_.executeQuery( query );

			this.getCompanyKeySet( rs, companyKeys );

			return 0;
			// Success
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::ProductCatalogTable : getCompanyKeys " + ex );
			return -1;
			// SQLException occurred while filtering a Supplier Company
		}

	}

	/**
	 * The most important method that queries the ProductCatalog Table based on
	 * the keyword. The keyword search is performed on the item_name and
	 * item_desc fields of the table.
	 * 
	 */
	public int getCompanyKeys( String keyword, Set<CompanyRegnKey> companyKeys )
	{

		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );
			stmt_ = con_.createStatement( );

			query = "SELECT DISTINCT regn_rel_key FROM " + this.tableName_;
			// query += " WHERE MATCH (item_name, item_desc) AGAINST ('" +
			// keyword + "' IN boolean MODE)";

			query += " WHERE item_desc LIKE '%" + keyword + "%'  OR item_name LIKE '%" + keyword + "%'";

			errorMaster_.insert( "ProductCatalogTable:getCompanyKeys:query: " + query );
			ResultSet rs = stmt_.executeQuery( query );

			this.getCompanyKeySet( rs, companyKeys );

			return 0;
			// Success
		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::ProductCatalogTable : getCompanyKeys " + ex );
			return -1;
			// SQLException occurred while filtering a Supplier Company
		}

	}

	/**
	 * 
	 */
	private void formWhereClause( ProductCatalogData pcData, StringHolder whereClause )
	{

		if( pcData.itemName_ != null && !( pcData.itemName_.equalsIgnoreCase( "" ) ) )
		{

			if( whereClause.value != "" )
			{
				whereClause.value += " OR";
			}
			else
			{
				whereClause.value += " WHERE";
			}

			whereClause.value += " item_name = '" + pcData.itemName_ + "'";

		}

		if( pcData.itemPartNo_ != null && !( pcData.itemPartNo_.equalsIgnoreCase( "" ) ) )
		{

			if( whereClause.value != "" )
			{
				whereClause.value += " OR";
			}
			else
			{
				whereClause.value += " WHERE";
			}

			whereClause.value += " part_number = '" + pcData.itemPartNo_ + "'";

		}

	}

	/**
	 * 
	 */
	private void getCompanyKeySet( ResultSet rs, Set<CompanyRegnKey> companyKeys )
	{

		CompanyRegnKey companyRegnKeyObj = null;

		try
		{
			while( rs.next( ) )
			{
				companyRegnKeyObj = null;
				companyRegnKeyObj = new CompanyRegnKey( );
				companyRegnKeyObj.companyPhoneNo_ = rs.getString( "regn_rel_key" );
				companyKeys.add( companyRegnKeyObj );
			}

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::ProductCatalogTable : getCompanyKeySet " + ex );
			// SQLException occurred while populating a Supplier Company Set
		}

	}

}
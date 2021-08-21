package db.prodcatalog;

import utils.ErrorMaster;

/**
 * This is the db class for a product catalog record
 */
public class ProductCatalogRecord
{

	public String	itemKey_;
	public String	itemDesc_;
	public String	itemPartNo_;
	public String	itemName_;
	public String	quantity_;
	public String	quantityTypeRelKey_;
	public String	price_;
	public String	currencyRelKey_;
	public String	taxRate_;
	public String	regnRelKey_;
	public String	sku_;
	public String	hidePrice_;
        public String	barcode_;

	public ProductCatalogRecord()
	{

		itemKey_ = null;
		itemDesc_ = null;
		itemPartNo_ = null;
		itemName_ = null;
		quantity_ = null;
		quantityTypeRelKey_ = null;
		price_ = null;
		currencyRelKey_ = null;
		taxRate_ = null;
		regnRelKey_ = null;
		sku_ = null;
		hidePrice_=null;
                barcode_=null;
	}

	/**
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose : To reset the class variables.
	 */
	public void clear( )
	{

		itemKey_ = null;
		itemDesc_ = null;
		itemPartNo_ = null;
		itemName_ = null;
		quantity_ = null;
		quantityTypeRelKey_ = null;
		price_ = null;
		currencyRelKey_ = null;
		taxRate_ = null;
		regnRelKey_ = null;
		sku_ = null;
		hidePrice_=null;
                barcode_=null;

	}

	/**
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */
	public void show( )
	{
ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "Item Key	= " + itemKey_ );
		errorMaster_.insert( "Item Description	= " + itemDesc_ );
		errorMaster_.insert( "Item Part No.	= " + itemPartNo_ );
		errorMaster_.insert( "Item Name	= " + itemName_ );
		errorMaster_.insert( "Quantity	= " + quantity_ );
		errorMaster_.insert( "Quantity Rel Key	= " + quantityTypeRelKey_ );
		errorMaster_.insert( "Price	= " + price_ );
		errorMaster_.insert( "Currency Rel Key	= " + currencyRelKey_ );
		errorMaster_.insert( "Tax Rate	= " + taxRate_ );
		errorMaster_.insert( "Regn Rel Key	= " + regnRelKey_ );
		errorMaster_.insert( "Item SKU = " + sku_ );
		errorMaster_.insert( "hidePrice_ = " + hidePrice_ );

	}

}

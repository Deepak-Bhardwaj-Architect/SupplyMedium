package db.prodcatalog;

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

	}

	/**
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */
	public void show( )
	{

		System.out.println( "Item Key	= " + itemKey_ );
		System.out.println( "Item Description	= " + itemDesc_ );
		System.out.println( "Item Part No.	= " + itemPartNo_ );
		System.out.println( "Item Name	= " + itemName_ );
		System.out.println( "Quantity	= " + quantity_ );
		System.out.println( "Quantity Rel Key	= " + quantityTypeRelKey_ );
		System.out.println( "Price	= " + price_ );
		System.out.println( "Currency Rel Key	= " + currencyRelKey_ );
		System.out.println( "Tax Rate	= " + taxRate_ );
		System.out.println( "Regn Rel Key	= " + regnRelKey_ );
		System.out.println( "Item SKU = " + sku_ );
		System.out.println( "hidePrice_ = " + hidePrice_ );

	}

}

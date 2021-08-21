package core.prodcatalog;

import core.regn.CompanyRegnKey;
import core.common.CurrencyKey;
import core.common.QuanTypeKey;

/**
 * This is the core class for the product catalog data which contains data
 * details about a product catalog under a supplier company
 */
public class ProductCatalogData
{
	public String	      itemKey_;
	public String	      itemDesc_;
	public String	      itemPartNo_;
	public String	      itemName_;
	public String	      itemQuan_;
	public QuanTypeKey	  itemQuanTypeRelKey_;
	public String	      itemPrice_;
	public CurrencyKey	  itemCrcyRelKey_;
	public String	      itemTaxRate_;
	public CompanyRegnKey	itemCompRegnRelKey_;
	public String	      itemsku_;
	public String	      hidePrice_;
	
	public ProductCatalogData()
	{
		this.itemQuanTypeRelKey_=new QuanTypeKey( );
		this.itemCrcyRelKey_ = new CurrencyKey( );
		this.itemCompRegnRelKey_ = new CompanyRegnKey( );
	}

	/**
	 * @Date : Jul 27, 2013 11:36:29 AM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Clear the Domain Data
	 * 
	 */
	public void clear( )
	{
		itemKey_ = null;
		itemDesc_ = null;
		itemPartNo_ = null;
		itemName_ = null;
		itemQuan_ = null;
		itemQuanTypeRelKey_ = null;
		itemPrice_ = null;
		itemCrcyRelKey_ = null;
		itemTaxRate_ = null;
		itemCompRegnRelKey_ = null;
		itemsku_ = null;
		hidePrice_ = null;

	}

	/**
	 * @Date : Jul 27, 2013 11:38:29 AM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : It is used to print the all class variable values in console
	 * 
	 */
	public void show( )
	{
		System.out.println( "itemKey_	= " + itemKey_ );
		System.out.println( "itemDesc_	= " + itemDesc_ );
		System.out.println( "itemPartNo_	= " + itemPartNo_ );
		System.out.println( "itemName_	= " + itemName_ );
		System.out.println( "itemQuan_	= " + itemQuan_ );
		itemQuanTypeRelKey_.show( );
		System.out.println( "itemPrice_	= " + itemPrice_ );
		itemCrcyRelKey_.show( );
		System.out.println( "itemTaxRate_	= " + itemTaxRate_ );
		itemCompRegnRelKey_.show( );
		System.out.println( "itemsku_ =" + itemsku_ );
		System.out.println( "hidePrice_ =" + hidePrice_ );
		
	}
}

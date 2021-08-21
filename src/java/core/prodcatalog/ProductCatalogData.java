package core.prodcatalog;

import core.regn.CompanyRegnKey;
import core.common.CurrencyKey;
import core.common.QuanTypeKey;
import utils.ErrorMaster;

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
        public String	      barcode_;
        private static ErrorMaster errorMaster_ = null;


	
	public ProductCatalogData()
	{
		this.itemQuanTypeRelKey_=new QuanTypeKey( );
		this.itemCrcyRelKey_ = new CurrencyKey( );
		this.itemCompRegnRelKey_ = new CompanyRegnKey( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
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
                barcode_=null;

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
		errorMaster_.insert( "itemKey_	= " + itemKey_ );
		errorMaster_.insert( "itemDesc_	= " + itemDesc_ );
		errorMaster_.insert( "itemPartNo_	= " + itemPartNo_ );
		errorMaster_.insert( "itemName_	= " + itemName_ );
		errorMaster_.insert( "itemQuan_	= " + itemQuan_ );
		itemQuanTypeRelKey_.show( );
		errorMaster_.insert( "itemPrice_	= " + itemPrice_ );
		itemCrcyRelKey_.show( );
		errorMaster_.insert( "itemTaxRate_	= " + itemTaxRate_ );
		itemCompRegnRelKey_.show( );
		errorMaster_.insert( "itemsku_ =" + itemsku_ );
		errorMaster_.insert( "hidePrice_ =" + hidePrice_ );
		
	}
}

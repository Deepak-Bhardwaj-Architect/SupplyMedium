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
package core.edi;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import utils.AppConfigReader;
import utils.PathBuilder;
import utils.StringHolder;


import core.companyprofile.CompanyProfileData;
import core.regn.MailingAddressData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import core.trans.RFQData;
import core.trans.RFQItemData;
import db.regn.UserProfileTable;
import utils.ErrorMaster;

/**
 * File:  RFQEDICreator.java 
 *
 * Created on 22-Oct-2013 9:47:04 AM
 */
public class RFQEDICreator
{
	
	Document rfqDoc_;
	
	EDISpec ediSpec_;
        private static ErrorMaster errorMaster_ = null;

	/*
	 * Method : RFQEDICreator -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public RFQEDICreator()
	{
		
		init( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	

	/*
	 * Method : init
	 * 
	 * Input  : none
	 * 
	 * Return : void
	 * 
	 * Purpose: This method is used to create the new rfqEDI document.
	 */
	
	private void init()
	{
		try
                {
                    String version = AppConfigReader.instance( ).get( "RFQ_EDI_VERSION" );

                    ediSpec_ = EDISpecFactory.instance( ).get( "RFQ", version );

                    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                    rfqDoc_ = docBuilder.newDocument();

                }
                catch( Exception e )
                {
                    errorMaster_.insert( "Exception" +e);
                }
	}
	
	
	/*
	 * Method : RFQEDICreator -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public int create( RFQData rfqData )
	{
		int value = 0;
		
		int segmentCount = 0;
		
		try
        {
			
		
			Element rootElement = rfqDoc_.createElement( "transactionSet" );
			
			rfqDoc_.appendChild( rootElement );

			rootElement.appendChild( createTransHeaderSeg( rfqData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createRFQSeg( rfqData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createReferenceIdSeg( rfqData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createAdminContactSeg( rfqData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createCompanyInfo( rfqData.fromCompanyProfileData_, rfqData.toCompanyProfileData_ ) );
			
			segmentCount ++;
			
			for( RFQItemData rfqItemData : rfqData.RFQItems_ )
            {
	            rootElement.appendChild( createBaseLineDataSeg( rfqItemData ) );
	            
	            segmentCount ++;
	            
	            rootElement.appendChild( createProductDescSeg( rfqItemData.itemDesc_ ) );
	            
	            segmentCount ++;
            }
			
			rootElement.appendChild( createTransTotalSeg( rfqData.RFQItems_.size( ) ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createTransTrailerSeg( segmentCount, rfqData.RFQId_ ) );

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance( );
			Transformer transformer = transformerFactory.newTransformer( );
			DOMSource source = new DOMSource( rfqDoc_ );
			
			StringHolder path = new StringHolder( );
			
			int response = getEDIPath( rfqData.transId_, path );
			
			if( response != 0 )
				return -1;
			
			// Output to file
			
			File file = new File( path.value );
			
			file.mkdirs( );
			
			StreamResult result = new StreamResult( file+"//rfq.xml" );

			// Output to console for testing
			//StreamResult result = new StreamResult( System.out );

			transformer.transform( source, result );
		
        }
        catch( Exception e )
        {
	       errorMaster_.insert( "Exception"+e );
        }
       
		
		return value;
	}
	

	/*
	 * Method : createTransHeaderSegment 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	private int getEDIPath( Long transId, StringHolder path )
	{
		int result = 0;
		
		PathBuilder pathBuilder = new PathBuilder( );
		
		result = pathBuilder.getEDIFilesDirPath( transId, path );
		
		pathBuilder = null;
		
		if( result != 0 )
			return -1;
		
		
		return result;
		
	}
	
	

	/*
	 * Method : createTransHeaderSegment 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	private Element createElement( String eleName, String eleCode, String value )
	{
		// Setting Transaction Set Identifier Code name
		Element element = rfqDoc_.createElement( eleName );
		
		if( eleCode != "" )
			element.setAttribute( "code", eleCode );
		
		if( value != "" )
			element.appendChild( rfqDoc_.createTextNode( value ) );
		
		return element;
			
		
	}
	
	
	/*
	 * Method : createTransHeaderSegment 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	private Element createTransHeaderSeg( RFQData rfqData )
	{
		
		// Segment creation
		String segmentCode = ediSpec_.getSegmentCode( "Transaction Set Header" );
		Element segment = createElement( "segment", segmentCode, "" );
		
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Transaction Set Header" );
		segment.appendChild(segmentName);
		
		// Transaction Set Identifier Code element
		String IdCode = ediSpec_.getElementCode( "Transaction Set Identifier Code" );
		Element Id = createElement( "element", IdCode, "" );
		
		
		// Setting Transaction Set Identifier Code name element
		Element idName = createElement( "name", "", "Transaction Set Identifier Code" );
		Id.appendChild(idName);
		
		// Setting Transaction Set Identifier Code value element
		Element idValue = createElement( "value", "", "Request for Quotation" );
		Id.appendChild(idValue);
		
		segment.appendChild( Id );
		
		
		// Transaction Set Control Number element
		String ctrlNoCode = ediSpec_.getElementCode( "Transaction Set Control Number" );
		Element ctrlNo = createElement( "element", ctrlNoCode, "" );
				
			
		// Transaction Set control Number name element
		Element ctrlNoName =createElement( "name", "", "Transaction Set Control Number" );
		ctrlNo.appendChild(ctrlNoName);
				
		// Transaction set control number value element
		Element ctrlNoValue = createElement( "value", "", ""+rfqData.RFQId_ );
		ctrlNo.appendChild(ctrlNoValue);
				
		segment.appendChild( ctrlNo );
		
		return segment;
		 
	}
	
	/*
	 * Method : createTransHeader 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	private Element createRFQSeg( RFQData rfqData )
	{
		// segment creation
		String segmentCode = ediSpec_
		        .getSegmentCode( "Beginning Segment for Request for Quotation" );
		Element segment = createElement( "segment", segmentCode, "" );

	
		// Setting segment name
		Element segmentName = createElement( "name", "", "Beginning Segment for Request for Quotation" );
		segment.appendChild( segmentName );

		// Transaction Set Purpose Code element
		String purposeCodeCode = ediSpec_.getElementCode( "Transaction Set Purpose Code" );
		Element purposeCode = createElement( "element", purposeCodeCode, "" );
		

		// Setting Transaction Set purpose Code name element
		Element purposeCodeName = createElement( "name", "", "Transaction Set Purpose Code" );
		purposeCode.appendChild( purposeCodeName );

		// Setting Transaction Set purpose code value element
		Element purposeCodeValue = createElement( "value", "", "Original" );
		purposeCode.appendChild( purposeCodeValue );

		segment.appendChild( purposeCode );

		// Request for Quote Reference Number element
		String rfqRefNoCode = ediSpec_.getElementCode( "Request for Quote Reference Number" );
		Element rfqRefNo = createElement( "element", rfqRefNoCode, "" );

		// Request for Quote Reference Number Code name
		Element rfqRefNoName = createElement( "name", "", "Request for Quote Reference Number" );
		rfqRefNo.appendChild( rfqRefNoName );

		// Request for Quote Reference Number Code value
		Element rfqRefNoValue = createElement( "value", "", "" + rfqData.RFQId_ );
		rfqRefNo.appendChild( rfqRefNoValue );

		segment.appendChild( rfqRefNo );

		// Date element
		String dateCode = ediSpec_.getElementCode( "Date" );
		Element date =createElement( "element", dateCode, "" );

		// Date name element
		Element dateName = createElement( "name", "", "Date" );
		date.appendChild( dateName );

		// Date value element
		Element dateValue = createElement( "value", "", "" + rfqData.RFQDate_ );
		date.appendChild( dateValue );

		segment.appendChild( date );

		return segment;
	}
	
	

	/*
	 * Method : createTransHeaderSegment 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	private Element createReferenceIdSeg( RFQData rfqData )
	{
		// segment creation
		String segmentCode = ediSpec_.getSegmentCode( "Reference Identification" );
		Element segment = createElement( "segment", segmentCode, "" );
		
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Reference Identification" );
		segment.appendChild( segmentName );
		
		// Reference Identification Qualifier - element
		String element1Code = ediSpec_.getElementCode( "Reference Identification Qualifier" );
		Element element1 = createElement( "element", element1Code, "" );
	
		
		// Setting Reference Identification Qualifier -  name
		Element element1Name = createElement( "name", "", "Reference Identification Qualifier" );
		element1.appendChild( element1Name );
		
		// Setting Reference Identification QualifierCode - value
		Element element1Value = createElement( "value", "", "Vendor ID Number" );
		element1.appendChild( element1Value );
		
		segment.appendChild( element1 );
		
		// Reference Identification element
		String ctrlNoCode = ediSpec_.getElementCode( "Reference Identification" );
		Element ctrlNo = createElement( "element", ctrlNoCode, "" );

		// Setting Reference Identification name element
		Element ctrlNoName = createElement( "name", "", "Reference Identification" );
		ctrlNo.appendChild(ctrlNoName);
				
		// Setting Reference Identification value element
		Element ctrlNoValue = createElement( "value", "", ""+rfqData.to_.toString( )  );
		ctrlNo.appendChild(ctrlNoValue);
				
		segment.appendChild( ctrlNo );
		
		return segment;
		 
	}
	
	/*
	 * Method : createAdminContact
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	private Element createAdminContactSeg( RFQData rfqData )
	{
		// segment creation
		String segmentCode = ediSpec_.getSegmentCode( "Administrative Communications Contact" );
		Element segment = createElement( "segment", segmentCode, "" );
		
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Administrative Communications Contact" );
		segment.appendChild( segmentName );
		
		//Contact Function Code - element
		String element1Code = ediSpec_.getElementCode( "Contact Function Code" );
		Element element1 = createElement( "element", element1Code, "" );
	
		
		// Contact Function Code - name
		Element element1Name = createElement( "name", "", "Contact Function Code" );
		element1.appendChild( element1Name );
		
		// Contact Function Code - value
		Element element1Value = createElement( "value", "EB", "Entered By" );
		element1.appendChild( element1Value );
		
		segment.appendChild( element1 );
		
		// Name - element
		String element2Code = ediSpec_.getElementCode( "Name" );
		Element element2 = createElement( "element", element2Code, "" );

		// Name - name
		Element element2Name = createElement( "name", "", "Name" );
		element2.appendChild( element2Name );
				
		// Name - value
		Element element2Value = createElement( "value", "", ""+rfqData.toCompanyProfileData_.companyName_ );
		element2.appendChild( element2Value );
				
		segment.appendChild( element2 );
		
		return segment;
		 
	}
	
	/*
	 * Method : createAdminContact
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	private Element createCompanyInfo( CompanyProfileData fromCompProfileData, CompanyProfileData toCompProfileData )
    {
		
		MailingAddressData fromAddress = fromCompProfileData.mailingAddressArr_.get( 0 );
		
		MailingAddressData toAddress = toCompProfileData.mailingAddressArr_.get( 0 );
		
		
		UserProfileData fromProfileData = new UserProfileData( );
		
		UserProfileData toProfileData = new UserProfileData( );
		
		
		UserProfileKey fromUserKey = new UserProfileKey( );
		
		fromUserKey.email_ = fromCompProfileData.email_;
		
		
		UserProfileKey touserKey = new UserProfileKey( );
		
		touserKey.email_ = toCompProfileData.email_;
		
		
		
		UserProfileTable userProfileTable = new UserProfileTable( );
		
		userProfileTable.getUserProfile( fromUserKey, fromProfileData );
		
		userProfileTable.getUserProfile( touserKey, toProfileData );
		
		userProfileTable = null;
		
		fromUserKey = null;
		
		touserKey = null;
	
		// segment creation
		
		Element segment = createElement( "loop", "N1", "" );
		
		// Buyer's contact information
		segment.appendChild( createNameSeg( fromProfileData.firstName_+" "+fromProfileData.lastName_,
												"Buying Party", "BY") );
		
		segment.appendChild( createAddrSeg( fromAddress.address_ ) );
		
		segment.appendChild( createGeographicLocSeg( fromAddress.city_, fromAddress.state_, fromAddress.zipcode_ ) );
		
		// Supplier's contact information
		segment.appendChild( createNameSeg( toProfileData.firstName_+" "+toProfileData.lastName_
												,"Vendor", "VN") );
		
		segment.appendChild( createAddrSeg( toAddress.address_ ) );
		
		segment.appendChild( createGeographicLocSeg( toAddress.city_, toAddress.state_, toAddress.zipcode_ ) );
		
		return segment;
	    
    }
	
	/*
	 * Method : createNameSeg
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	private Element createNameSeg( String Name, String companyType, String typeCode )
    {
		// segment creation
		String segmentCode = ediSpec_.getSegmentCode( "Name" );
		Element segment = createElement( "segment", segmentCode, "" );
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Name" );
		segment.appendChild( segmentName );

		// Entity Identifier Code - element
		String element1Code = ediSpec_.getElementCode( "Entity Identifier Code" );
		Element element1 = createElement( "element", element1Code, "" );

		// Entity Identifier Code - name
		Element element1Name = createElement( "name", "", "Entity Identifier Code" );
		element1.appendChild( element1Name );

		// Entity Identifier Code - value
		Element element1Value = createElement( "value", typeCode, companyType );
		element1.appendChild( element1Value );

		segment.appendChild( element1 );

		// Name - element
		String element2Code = ediSpec_.getElementCode( "Name" );
		Element element2 = createElement( "element", element2Code, "" );

		// Name - name
		Element element2Name = createElement( "name", "", "Name" );
		element2.appendChild( element2Name );

		// Name - value
		Element element2Value = createElement( "value", "", Name );
		element2.appendChild( element2Value );

		segment.appendChild( element2 );
		
		return segment;
	    
    }
	
	/*
	 * Method : createAddrSeg
	 * 
	 * Input  : None
	 * 
	 * Return : Element
	 * 
	 * Purpose:
	 */
	
	private Element createAddrSeg( String address )
	{
		// segment creation
		String segmentCode = ediSpec_.getSegmentCode( "Address Information" );
		Element segment = createElement( "segment", segmentCode, "" );

		// Setting segment name
		Element segmentName = createElement( "name", "", "Address Information" );
		segment.appendChild( segmentName );

		// Address Information - element
		String element1Code = ediSpec_.getElementCode( "Address Information" );
		Element element1 = createElement( "element", element1Code, "" );

		// Address Information - name
		Element element1Name = createElement( "name", "", "Address Information" );
		element1.appendChild( element1Name );

		// Address Information - value
		Element element1Value = createElement( "value", "", address );
		element1.appendChild( element1Value );

		segment.appendChild( element1 );


		return segment;
	}
	
	
	/*
	 * Method : createSeg
	 * 
	 * Input  : None
	 * 
	 * Return : Element
	 * 
	 * Purpose:
	 */
	
	private Element createGeographicLocSeg( String city, String state, String postalCode )
	{
		// segment creation
		String segmentCode = ediSpec_.getSegmentCode( "Geographic Location" );
		Element segment = createElement( "segment", segmentCode, "" );

		// Setting segment name
		Element segmentName = createElement( "name", "", "Geographic Location" );
		segment.appendChild( segmentName );

		// City Name - element
		String element1Code = ediSpec_.getElementCode( "City Name" );
		Element element1 = createElement( "element", element1Code, "" );

		// City Name - name
		Element element1Name = createElement( "name", "", "City Name" );
		element1.appendChild( element1Name );

		// City Name - value
		Element element1Value = createElement( "value", "", city );
		element1.appendChild( element1Value );

		segment.appendChild( element1 );
		
		
		// State Name - element
		String element2Code = ediSpec_.getElementCode( "State or Province Code" );
		Element element2 = createElement( "element", element2Code, "" );

		// State Name - name
		Element element2Name = createElement( "name", "", "State or Province Code" );
		element2.appendChild( element2Name );

		// State Name - value
		Element element2Value = createElement( "value", "", state );
		element2.appendChild( element2Value );

		segment.appendChild( element2 );
		
		// Postal Code - element
		String element3Code = ediSpec_.getElementCode( "Postal Code" );
		Element element3 = createElement( "element", element3Code, "" );

		// Postal Code - name
		Element element3Name = createElement( "name", "", "Postal Code" );
		element3.appendChild( element3Name );

		// Postal Code - value
		Element element3Value = createElement( "value", "", postalCode );
		element3.appendChild( element3Value );

		segment.appendChild( element3 );
		
		return segment;

	}
	
	/*
	 * Method : createBaseLineData
	 * 
	 * Input  : RFQItemData obj
	 * 
	 * Return : Element
	 * 
	 * Purpose:
	 */
	
	private Element createBaseLineDataSeg( RFQItemData rfqItemData )
	{
		// loop creation
		String segmentCode = ediSpec_.getSegmentCode( "Baseline Item Data" );
		Element loop = createElement( "loop", segmentCode, "" );
		
		// Segment creation
		Element segment = createElement( "segment", segmentCode, "" );
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Baseline Item Data" );
		segment.appendChild( segmentName );
		
		// Assigned Identification - element
		String element1Code = ediSpec_.getElementCode( "Assigned Identification" );
		Element element1 = createElement( "element", element1Code, "" );

		// Assigned Identification - name
		Element element1Name = createElement( "name", "", "Assigned Identification" );
		element1.appendChild( element1Name );

		// Assigned Identification - value
		Element element1Value = createElement( "value", "", "1" );
		element1.appendChild( element1Value );

		segment.appendChild( element1 );
		
		// Quantity Ordered - element
		String element2Code = ediSpec_.getElementCode( "Quantity Ordered" );
		Element element2 = createElement( "element", element2Code, "" );

		// Quantity Ordered - name
		Element element2Name = createElement( "name", "", "Quantity Ordered" );
		element2.appendChild( element2Name );

		// Quantity Ordered - value
		Element element2Value = createElement( "value", "", ""+rfqItemData.quantity_ );
		element2.appendChild( element2Value );

		segment.appendChild( element2 );
		
		// Unit or Basis for Measurement Code - element
		String element3Code = ediSpec_.getElementCode( "Unit or Basis for Measurement Code" );
		Element element3 = createElement( "element", element3Code, "" );

		// Unit or Basis for Measurement Code - name
		Element element3Name = createElement( "name", "", "Unit or Basis for Measurement Code" );
		element3.appendChild( element3Name );

		// Unit or Basis for Measurement Code - value
		Element element3Value = createElement( "value", "", rfqItemData.quantityUnit_ );
		element3.appendChild( element3Value );

		segment.appendChild( element3 );
		
		// Product/Service ID Qualifier - element
		String element4Code = ediSpec_.getElementCode( "Product/Service ID Qualifier" );
		Element element4 = createElement( "element", element4Code, "" );

		// Product/Service ID Qualifier - name
		Element element4Name = createElement( "name", "", "Product/Service ID Qualifier" );
		element4.appendChild( element4Name );

		// Product/Service ID Qualifier - value
		Element element4Value = createElement( "value", "MG", "Manufacturer's Part Number" );
		element4.appendChild( element4Value );

		segment.appendChild( element4 );
		
		// Product/Service ID - element
		String element5Code = ediSpec_.getElementCode( "Product/Service ID" );
		Element element5 = createElement( "element", element5Code, "" );

		// Product/Service ID - name
		Element element5Name = createElement( "name", "", "Product/Service ID" );
		element5.appendChild( element5Name );

		// Product/Service ID - value
		Element element5Value = createElement( "value", "", rfqItemData.partNo_ );
		element5.appendChild( element5Value );

		segment.appendChild( element5 );
		
		loop.appendChild( segment );
		
		return loop;
						
				

	}
	
	/*
	 * Method : createProductDescSeg
	 * 
	 * Input  : description
	 * 
	 * Return : Element
	 * 
	 * Purpose:
	 */
	
	private Element createProductDescSeg( String description )
	{

		// loop creation
		String segmentCode = ediSpec_.getSegmentCode( "Product/Item Description" );
		Element loop = createElement( "loop", segmentCode, "" );
		
		// Segment creation
		Element segment = createElement( "segment", segmentCode, "" );
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Product/Item Description" );
		segment.appendChild( segmentName );
		
		// Item Description Type - element
		String element1Code = ediSpec_.getElementCode( "Item Description Type" );
		Element element1 = createElement( "element", element1Code, "" );

		// Item Description Type - name
		Element element1Name = createElement( "name", "", "Item Description Type" );
		element1.appendChild( element1Name );

		// Item Description Type - value
		Element element1Value = createElement( "value", "F", "Free-form" );
		element1.appendChild( element1Value );

		segment.appendChild( element1 );
		
		// Description - element
		String element2Code = ediSpec_.getElementCode( "Description" );
		Element element2 = createElement( "element", element2Code, "" );

		// Description - name
		Element element2Name = createElement( "name", "", "Description" );
		element2.appendChild( element2Name );

		// Description - value
		Element element2Value = createElement( "value", "", description );
		element2.appendChild( element2Value );

		segment.appendChild( element2 );
		
		loop.appendChild( segment );
		
		return loop;
	}
	
	/*
	 * Method : createTransTotalSeg
	 * 
	 * Input  : description
	 * 
	 * Return : Element
	 * 
	 * Purpose:
	 */
	
	private Element createTransTotalSeg( int itemsCount )
	{
		String segmentCode = ediSpec_.getSegmentCode( "Transaction Totals" );
		// Segment creation
		Element segment = createElement( "segment", segmentCode, "" );
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Transaction Totals" );
		segment.appendChild( segmentName );
		
		
		// Number of Line Items - element
		String element1Code = ediSpec_.getElementCode( "Number of Line Items" );
		Element element1 = createElement( "element", element1Code, "" );

		// Number of Line Items - name
		Element element1Name = createElement( "name", "", "Number of Line Items" );
		element1.appendChild( element1Name );

		// Number of Line Items - value
		Element element1Value = createElement( "value", "", ""+itemsCount );
		element1.appendChild( element1Value );

		segment.appendChild( element1 );
		
		return segment;
				
	}
	
	/*
	 * Method : createTransTrailerSeg
	 * 
	 * Input  : description
	 * 
	 * Return : Element
	 * 
	 * Purpose:
	 */
	
	private Element createTransTrailerSeg( int segmentCount, long rfqId )
	{
		String segmentCode = ediSpec_.getSegmentCode( "Transaction Set Trailer" );
		// Segment creation
		Element segment = createElement( "segment", segmentCode, "" );
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Transaction Set Trailer" );
		segment.appendChild( segmentName );
		
		
		// Number of Included Segmentss - element
		String element1Code = ediSpec_.getElementCode( "Number of Included Segments" );
		Element element1 = createElement( "element", element1Code, "" );

		// Number of Included Segments - name
		Element element1Name = createElement( "name", "", "Number of Included Segments" );
		element1.appendChild( element1Name );

		// Number of Included Segments - value
		Element element1Value = createElement( "value", "", ""+segmentCount+1 );
		element1.appendChild( element1Value );

		segment.appendChild( element1 );
		
		// Transaction Set Control Number - element
		String element2Code = ediSpec_.getElementCode( "Transaction Set Control Number" );
		Element element2 = createElement( "element", element2Code, "" );

		// Transaction Set Control Number - name
		Element element2Name = createElement( "name", "", "Transaction Set Control Number" );
		element2.appendChild( element2Name );

		// Transaction Set Control Number - value
		Element element2Value = createElement( "value", "", ""+rfqId );
		element2.appendChild( element2Value );

		segment.appendChild( element2 );
		
		return segment;
	}
}

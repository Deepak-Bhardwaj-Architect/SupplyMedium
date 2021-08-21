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
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.RegnData;
import core.regn.UserProfileData;
import core.regn.UserProfileKey;
import core.trans.POData;
import core.trans.POItemData;
import db.regn.CompanyRegnTable;
import db.regn.UserProfileTable;

/**
 * File:  POEDICreator.java 
 *
 * Created on Oct 23, 2013 4:02:18 PM
 */
public class POEDICreator
{
	Document poDoc_;
	
	EDISpec ediSpec_;

	/*
	 * Method : POEDICreator -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public POEDICreator()
	{
		
		init( );
	}
	

	/*
	 * Method : init
	 * 
	 * Input  : none
	 * 
	 * Return : void
	 * 
	 * Purpose: This method is used to create the new POEDI document.
	 */
	
	
	private void init()
	{
		try
        {
			
			String version = AppConfigReader.instance( ).get( "PO_EDI_VERSION" );
			
			ediSpec_ = EDISpecFactory.instance( ).get( "PO", version );
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
			poDoc_ = docBuilder.newDocument();
			
        }
        catch( Exception e )
        {
        	System.out.println( "Exception" +e);
        }
	}
	
	
	/*
	 * Method : POEDICreator -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public int create( POData poData )
	{
		int value = 0;
		
		int segmentCount = 0;
		
		try
        {
		
			Element rootElement = poDoc_.createElement( "transactionSet" );
			
			poDoc_.appendChild( rootElement );

			rootElement.appendChild( createTransHeaderSeg( poData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createPOSeg( poData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createCurrencySeg( poData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createReferenceIdSeg( poData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createAdminContactSeg( poData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createCompanyInfo( poData.fromCompanyProfileData_, poData.toCompanyProfileData_ ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createDateTimeRef( poData ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createMonetaryAmount( poData) );
			
			segmentCount ++;
			
			
			for( POItemData poItemData : poData.poItems_ )
            {
	            rootElement.appendChild( createBaseLineDataSeg( poItemData ) );
	            
	            segmentCount ++;
	            
	            rootElement.appendChild( createProductDescSeg( poItemData.itemDesc_ ) );
	            
	            segmentCount ++;
            }
			
			rootElement.appendChild( createPricingInfo( poData ) );
			
			segmentCount ++;
			
			
			
			rootElement.appendChild( createTransTotalSeg( poData.poItems_.size( ) ) );
			
			segmentCount ++;
			
			rootElement.appendChild( createTransTrailerSeg( segmentCount, poData.poId_ ) );

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance( );
			Transformer transformer = transformerFactory.newTransformer( );
			DOMSource source = new DOMSource( poDoc_ );
			
			StringHolder path = new StringHolder( );
			
			int response = getEDIPath( poData.transId_, path );
			
			if( response != 0 )
				return -1;
			
			// Output to file
			File file = new File( path.value );
			
			file.mkdirs( );
			
			StreamResult result = new StreamResult( file+"//po.xml" );

			// Output to console for testing
			//StreamResult result = new StreamResult( System.out );

			transformer.transform( source, result );
		
        }
        catch( Exception e )
        {
	       System.out.println( "Exception"+e );
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
		Element element = poDoc_.createElement( eleName );
		
		if( eleCode != "" )
			element.setAttribute( "code", eleCode );
		
		if( value != "" )
			element.appendChild( poDoc_.createTextNode( value ) );
		
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
	
	private Element createTransHeaderSeg( POData poData )
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
		Element idValue = createElement( "value", "", "Purchase Order" );
		Id.appendChild(idValue);
		
		segment.appendChild( Id );
		
		
		// Transaction Set Control Number element
		String ctrlNoCode = ediSpec_.getElementCode( "Transaction Set Control Number" );
		Element ctrlNo = createElement( "element", ctrlNoCode, "" );
				
			
		// Transaction Set control Number name element
		Element ctrlNoName =createElement( "name", "", "Transaction Set Control Number" );
		ctrlNo.appendChild(ctrlNoName);
				
		// Transaction set control number value element
		Element ctrlNoValue = createElement( "value", "", ""+poData.poId_ );
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
	
	private Element createPOSeg( POData poData )
	{
		// segment creation
		String segmentCode = ediSpec_
		        .getSegmentCode( "Beginning Segment for Purchase Order" );
		Element segment = createElement( "segment", segmentCode, "" );

	
		// Setting segment name
		Element segmentName = createElement( "name", "", "Beginning Segment for Purchase Order" );
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

		// purchase Order Type code element
		String poRefNoCode = ediSpec_.getElementCode( "Purchase Order Type Code" );
		Element poRefNo = createElement( "element", poRefNoCode, "" );

		// purchase Order Type code name
		Element poRefNoName = createElement( "name", "", "Purchase Order Type Code" );
		poRefNo.appendChild( poRefNoName );

		// purchase Order Type code value
		Element poRefNoValue = createElement( "value", "NE"," New Order" );
		poRefNo.appendChild( poRefNoValue );

		segment.appendChild( poRefNo );
		
		
		//Purchase Order Number-element
		String purchaseOrder = ediSpec_.getElementCode( "Purchase Order Number" );
		Element po = createElement( "element", purchaseOrder, "" );
		
		//Purchase Order Number-name
		Element poname = createElement( "name", "", "Purchase Order Number" );
		po.appendChild( poname );
		
		//Purchase Order Number-value
		Element poValue = createElement( "value", "", ""+poData.poId_ );
		po.appendChild( poValue );
		
		segment.appendChild( po );

		// Date element
		String dateCode = ediSpec_.getElementCode( "Date" );
		Element date =createElement( "element", dateCode, "" );

		// Date name element
		Element dateName = createElement( "name", "", "Date" );
		date.appendChild( dateName );

		// Date value element
		Element dateValue = createElement( "value", "", "" + poData.poDate_ );
		date.appendChild( dateValue );

		segment.appendChild( date );

		return segment;
	}
	
	
	/*
	 * Method : createCurrencySegment 
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	
	private Element  createCurrencySeg( POData poData )
	{
		//segment creation
		String segmentCode = ediSpec_.getSegmentCode( "Currency" );
		Element segment = createElement( "segment", segmentCode, "" );
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Currency" );
		segment.appendChild(segmentName);
		
		// Transaction Set Currency Code element
		String currencyCode = ediSpec_.getElementCode( "Entity Identifier Code" );
		Element currCode = createElement( "element", currencyCode, "" );
		
		// Setting currency name
		Element currencyName = createElement( "name", "", "Entity Identifier Code" );
		currCode.appendChild(currencyName);
		
		// Setting Transaction Set Currency code value element
		Element CurrencyCodeValue = createElement( "value", "", "Buying Party" );
		currCode.appendChild( CurrencyCodeValue );
		
		segment.appendChild( currCode );
		
		// Transaction Set Currency Code element
		String cuCode = ediSpec_.getElementCode( "Currency Code" );
		Element curCode = createElement( "element", cuCode, "" );
		
		//Setting Currency Code Name Element
		Element currencyCodeName = createElement( "name", "", "Currency Code" );
		curCode.appendChild(currencyCodeName);
			
		//Setting value CurrencyCode
		Element CurrencyValue = createElement( "value", "", "US Dollar" );
		currCode.appendChild( CurrencyValue );
		
		segment.appendChild( curCode );
		
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
	
	private Element createReferenceIdSeg( POData poData )
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
		Element ctrlNoValue = createElement( "value", "", ""+poData.to_.toString( )  );
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
	
	private Element createAdminContactSeg( POData poData )
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
		Element element2Value = createElement( "value", "", ""+poData.toCompanyProfileData_.companyName_ );
		element2.appendChild( element2Value );
				
		segment.appendChild( element2 );
		
		return segment;
		 
	}
	
	/*
	 * Method : createDateTimeRef
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	private Element createDateTimeRef(POData poData)
	{
		//segment
		String segmentCode = ediSpec_.getSegmentCode( "Effective Date Time Reference" );
		Element segment = createElement( "segment", segmentCode, "" );
		
		//segment name
		Element segmentName = createElement( "name", "", "Effective Date Time Reference" );
		segment.appendChild( segmentName );
		
		//Date/Time Qualifier-element
		String dateTimeRef= ediSpec_.getElementCode( "Date/Time Qualifier" );
		Element element1 = createElement( "element", dateTimeRef, "" );
		
		//Date/Time Qualifier-name
		Element dateTimeRefName = createElement( "name", "", "Date/Time Qualifier" );
		element1.appendChild( dateTimeRefName );
		
		//Date/Time Qualifier-value
		Element dateTimeRefValue = createElement( "value", "", "Effective Date" );
		element1.appendChild( dateTimeRefValue );
		
		segment.appendChild( element1 );
		
		//Date-element
		String date= ediSpec_.getElementCode( "Date" );
		Element element2 = createElement( "element", date, "" );
		
		//Date-name
		Element dateName = createElement( "name", "", "Date" );
		element2.appendChild( dateName );
		
		//Date-value
		Element dateValue = createElement( "value", "", ""+poData.poDate_ );
		element2.appendChild( dateValue );
		
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
	 * Input  : POtemData obj
	 * 
	 * Return : Element
	 * 
	 * Purpose:
	 */
	
	private Element createBaseLineDataSeg( POItemData poItemData )
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
		Element element2Value = createElement( "value", "", ""+poItemData.quantity_ );
		element2.appendChild( element2Value );

		segment.appendChild( element2 );
		
		// Unit or Basis for Measurement Code - element
		String element3Code = ediSpec_.getElementCode( "Unit or Basis for Measurement Code" );
		Element element3 = createElement( "element", element3Code, "" );

		// Unit or Basis for Measurement Code - name
		Element element3Name = createElement( "name", "", "Unit or Basis for Measurement Code" );
		element3.appendChild( element3Name );

		// Unit or Basis for Measurement Code - value
		Element element3Value = createElement( "value", "", poItemData.quantityUnit_ );
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
		Element element5Value = createElement( "value", "", poItemData.partNo_ );
		element5.appendChild( element5Value );

		segment.appendChild( element5 );
		
		//Unit Price-element
		String element6Code = ediSpec_.getElementCode( "Unit Price" );
		Element element6 = createElement( "element", element6Code, "" );
		
		//Unit Price-name
		Element element6Name = createElement( "name", "", "Unit Price" );
		element6.appendChild( element6Name );
		
		//Unit Price-value
		Element element6Value = createElement( "value", "",""+ poItemData.price_ );
		element6.appendChild( element6Value );
		
		segment.appendChild( element6 );
		
		//Basis of Unit Price Code-element
		String element7Code = ediSpec_.getElementCode( "Basis of Unit Price Code" );
		Element element7 = createElement( "element", element7Code, "" );
		
		//Basis of Unit Price Code-name
		Element element7Name = createElement( "name", "", "Basis of Unit Price Code" );
		element7.appendChild( element7Name );
		
		//Basis of Unit Price Code-value
		Element element7Value = createElement( "value", "CP", "Current Price" );
		element7.appendChild( element7Value );
		
		segment.appendChild( element7 );
					
		loop.appendChild( segment );
		
		return loop;
						
				

	}
	
	/*
	 * Method : Monetary AmountSeg
	 * 
	 * Input  : description
	 * 
	 * Return : Element
	 * 
	 * Purpose:
	 */
	private  Element createMonetaryAmount(POData poData)
	{
		//segment
		String segmentCode = ediSpec_.getSegmentCode( "Monetary Amount" );
		Element segment = createElement( "segment", segmentCode, "" );
		
		//segment name
		Element segmentName = createElement( "name", "", "Monetary Amount" );
		segment.appendChild( segmentName );
		
		//Amount Qualifier code-element
		String amtQualifierCode = ediSpec_.getElementCode( "Amount  Qualifier Code" );
		Element element1 = createElement( "element", amtQualifierCode, "" );
		
		//Amount Qualifier Code-name
		Element amtQualifierName = createElement( "name", "", "Amount  Qualifier Code" );
		element1.appendChild( amtQualifierName );
		
		//Amount Qualifier Code-value
		Element amtQualifierValue = createElement( "value", "TT", "Total Transaction Amount" );
		element1.appendChild( amtQualifierValue );
		
		segment.appendChild( element1 );
		
		//Monetary Amount-element
		String moAmt = ediSpec_.getElementCode( "Monetary Amount" );
		Element element2 = createElement( "element", moAmt, "" );
		
		//Monetary Amount-name
		Element moAmtName = createElement( "name", "", "Monetary Amount" );
		element2.appendChild( moAmtName );
		
		//Monetary Amount"-value
		Element moAmtValue = createElement( "value", "",""+ poData.totalPrice_ );
		element1.appendChild( moAmtValue );
		
		segment.appendChild( element2 );
		
		return segment;
	}
	

	/*
	 * Method : Pricing InformationSeg
	 * 
	 * Input  : description
	 * 
	 * Return : Element
	 * 
	 * Purpose:
	 */
	
	private Element createPricingInfo(POData poData)
	{
		//segment
		String segmentCode = ediSpec_.getSegmentCode( "Pricing Information" );
		Element segment = createElement( "segment", segmentCode, "" );
		
		// Setting segment name
		Element segmentName = createElement( "name", "", "Pricing Information" );
		segment.appendChild( segmentName );
		
		//Price Multiplier Qualifier-element
		String element1MultiplierCode = ediSpec_.getElementCode( "Price Multiplier Qualifier" );
		Element element1 = createElement( "element", element1MultiplierCode, "" );
		
		//Price Multiplier Qualifier-name
		Element element1MultiplierName = createElement( "name", "", "Price Multiplier Qualifier" );
		element1.appendChild( element1MultiplierName );
		
		//Price Multiplier Qualifier-value
		Element element1MultiplierValue = createElement( "value", "DIS", "Discount Multiplier" );
		element1.appendChild( element1MultiplierValue );
		
		segment.appendChild( element1 );
		
		// Multiplier-element
		String MultiplierCode = ediSpec_.getElementCode( "Multiplier" );
		Element element2 = createElement( "element", MultiplierCode, "" );
		
		//Multiplier-name
		Element MultiplierName = createElement( "name", "", "Multiplier" );
		element2.appendChild( MultiplierName );
		
		// Multiplier -value
		Element MultiplierValue = createElement( "value", "",""+ poData.poItems_.get( 0 ).multiplier_ );
		element2.appendChild( MultiplierValue );
		
		segment.appendChild( element2 );
		
		
		return segment;
		
		
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
	
	private Element createTransTrailerSeg( int segmentCount, long poId )
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
		Element element2Value = createElement( "value", "", ""+poId );
		element2.appendChild( element2Value );

		segment.appendChild( element2 );
		
		return segment;
	}

}

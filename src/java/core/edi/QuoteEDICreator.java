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
import core.trans.QuoteData;
import core.trans.QuoteItemData;
import db.regn.CompanyRegnTable;
import db.regn.UserProfileTable;
import utils.ErrorMaster;

/**
 * File:  QuoteEDICreator.java 
 *
 * Created on Oct 23, 2013 10:30:29 AM
 */
public class QuoteEDICreator
{	
		Document quoteDoc_;
		
		EDISpec ediSpec_;
                
                private static ErrorMaster errorMaster_ = null;



		/*
		 * Method : QuoteEDICreator -- constructor
		 * 
		 * Input  : None
		 * 
		 * Return : None
		 * 
		 * Purpose:
		 */
		
		public QuoteEDICreator()
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
		 * Purpose: This method is used to create the new quoteEDI document.
		 */
		
		private void init()
		{
			try
                        {

                                String version = AppConfigReader.instance( ).get( "QUOTE_EDI_VERSION" );

                                ediSpec_ = EDISpecFactory.instance( ).get( "Quote", version );

                                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                                quoteDoc_ = docBuilder.newDocument();

                        }
                        catch( Exception e )
                        {
                                errorMaster_.insert( "Exception" +e);
                        }
		}
		
		
		/*
		 * Method : QuoteEDICreator -- constructor
		 * 
		 * Input  : None
		 * 
		 * Return : None
		 * 
		 * Purpose:
		 */
		
		public int create( QuoteData quoteData )
		{
			int value = 0;
			
			int segmentCount = 0;
			
			try
	        {
			
				Element rootElement = quoteDoc_.createElement( "transactionSet" );
				
				quoteDoc_.appendChild( rootElement );

				errorMaster_.insert( "line 1" );
				rootElement.appendChild( createTransHeaderSeg( quoteData ) );
				
				segmentCount ++;
				
				errorMaster_.insert( "line 2" );
				rootElement.appendChild( createQuoteSeg( quoteData ) );
				
				segmentCount ++;
				
				errorMaster_.insert( "line 3" );
				rootElement.appendChild( createCurrencySeg( quoteData ) );
				
				segmentCount ++;
				
				errorMaster_.insert( "line 4" );
				rootElement.appendChild( createReferenceIdSeg( quoteData ) );
				
				segmentCount ++;
				
				errorMaster_.insert( "line 5" );
				rootElement.appendChild( createAdminContactSeg( quoteData ) );
				
				segmentCount ++;
				
				errorMaster_.insert( "line 6" );
				rootElement.appendChild( createCompanyInfo( quoteData.fromCompanyProfileData_, quoteData.toCompanyProfileData_ ) );
				
				segmentCount ++;
				
				errorMaster_.insert( "line 7" );
				rootElement.appendChild( createMonetaryAmount( quoteData ) );
				
				segmentCount ++;
				
				for( QuoteItemData quoteItemData : quoteData.quoteItems_ )
	            {
		            rootElement.appendChild( createBaseLineDataSeg( quoteItemData ) );
		            
		            segmentCount ++;
		            
		            rootElement.appendChild( createProductDescSeg( quoteItemData.itemDesc_ ) );
		            
		            segmentCount ++;
	            }
				
				errorMaster_.insert( "line 8" );
				rootElement.appendChild( createPricingInfo( quoteData ) );
				
				segmentCount ++;
				
				errorMaster_.insert( "line 9" );
				rootElement.appendChild( createLineItemSCH( quoteData ) );
				
				segmentCount ++;
				
				errorMaster_.insert( "line 10" );
				rootElement.appendChild( createTransTotalSeg( quoteData.quoteItems_.size( ) ) );
				
				segmentCount ++;
				
				errorMaster_.insert( "line 11" );
				rootElement.appendChild( createTransTrailerSeg( segmentCount, quoteData.quoteId_ ) );

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance( );
				Transformer transformer = transformerFactory.newTransformer( );
				DOMSource source = new DOMSource( quoteDoc_ );
				
				StringHolder path = new StringHolder( );
				
				int response = getEDIPath( quoteData.transId_, path );
				
				if( response != 0 )
					return -1;
				
				// Output to file
				File file = new File( path.value );
				
				file.mkdirs( );
				
				StreamResult result = new StreamResult( file+"//quote.xml" );

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
			Element element = quoteDoc_.createElement( eleName );
			
			if( eleCode != "" )
				element.setAttribute( "code", eleCode );
			
			if( value != "" )
				element.appendChild( quoteDoc_.createTextNode( value ) );
			
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
		
		private Element createTransHeaderSeg( QuoteData quoteData )
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
			Element idValue = createElement( "value", "", "Response To RFQ" );
			Id.appendChild(idValue);
			
			segment.appendChild( Id );
			
			
			// Transaction Set Control Number element
			String ctrlNoCode = ediSpec_.getElementCode( "Transaction Set Control Number" );
			Element ctrlNo = createElement( "element", ctrlNoCode, "" );
					
				
			// Transaction Set control Number name element
			Element ctrlNoName =createElement( "name", "", "Transaction Set Control Number" );
			ctrlNo.appendChild(ctrlNoName);
					
			// Transaction set control number value element
			Element ctrlNoValue = createElement( "value", "", ""+quoteData.quoteId_ );
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
		
		private Element createQuoteSeg( QuoteData quoteData )
		{
			// segment creation
			String segmentCode = ediSpec_
			        .getSegmentCode( "Beginning Segment for Response To Request for Quotation" );
			Element segment = createElement( "segment", segmentCode, "" );

		
			// Setting segment name
			Element segmentName = createElement( "name", "", "Beginning Segment for Response To Request for Quotation" );
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
			String quoteRefNoCode = ediSpec_.getElementCode( "Request for Quote Reference Number" );
			Element quoteRefNo = createElement( "element", quoteRefNoCode, "" );

			// Request for Quote Reference Number Code name
			Element rfqRefNoName = createElement( "name", "", "Request for Quote Reference Number" );
			quoteRefNo.appendChild( rfqRefNoName );

			// Request for Quote Reference Number Code value
			Element quoteRefNoValue = createElement( "value", "", "" + quoteData.quoteId_ );
			quoteRefNo.appendChild( quoteRefNoValue );

			segment.appendChild( quoteRefNo );

			// Date element
			String dateCode = ediSpec_.getElementCode( "Date" );
			Element date =createElement( "element", dateCode, "" );

			// Date name element
			Element dateName = createElement( "name", "", "Date" );
			date.appendChild( dateName );

			// Date value element
			Element dateValue = createElement( "value", "", "" + quoteData.quoteDate_ );
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
		
		
		private Element  createCurrencySeg( QuoteData quoteData )
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
			Element Currencyvalue = createElement( "value", "", "US Dollar" );
			curCode.appendChild( Currencyvalue );
			
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
		
		private Element createReferenceIdSeg( QuoteData quoteData )
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
			Element ctrlNoValue = createElement( "value", "", ""+quoteData.to_.toString( )  );
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
		
		private Element createAdminContactSeg( QuoteData quoteData )
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
			Element element2Value = createElement( "value", "", ""+quoteData.toCompanyProfileData_.companyName_ );
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
		
		private Element createBaseLineDataSeg( QuoteItemData quoteItemData )
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
			Element element2Value = createElement( "value", "", ""+quoteItemData.quantity_ );
			element2.appendChild( element2Value );

			segment.appendChild( element2 );
			
			// Unit or Basis for Measurement Code - element
			String element3Code = ediSpec_.getElementCode( "Unit or Basis for Measurement Code" );
			Element element3 = createElement( "element", element3Code, "" );

			// Unit or Basis for Measurement Code - name
			Element element3Name = createElement( "name", "", "Unit or Basis for Measurement Code" );
			element3.appendChild( element3Name );

			// Unit or Basis for Measurement Code - value
			Element element3Value = createElement( "value", "", quoteItemData.quantityUnit_ );
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
			Element element5Value = createElement( "value", "", quoteItemData.partNo_ );
			element5.appendChild( element5Value );

			segment.appendChild( element5 );
			
			//Unit Price-element
			String element6Code = ediSpec_.getElementCode( "Unit Price" );
			Element element6 = createElement( "element", element6Code, "" );
			
			//Unit Price-name
			Element element6Name = createElement( "name", "", "Unit Price" );
			element6.appendChild( element6Name );
			
			//Unit Price-value
			Element element6Value = createElement( "value", "", ""+quoteItemData.price_ );
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
		private  Element createMonetaryAmount(QuoteData quoteData)
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
			Element moAmtValue = createElement( "value", "",""+ quoteData.totalPrice_ );
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
		
		private Element createPricingInfo(QuoteData quoteData)
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
			Element MultiplierValue = createElement( "value", "", ""+quoteData.quoteItems_.get( 0 ).multiplier_);
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
		 * Method : LineItemSCHSeg
		 * 
		 * Input  : description
		 * 
		 * Return : Element
		 * 
		 * Purpose:
		 */
		private Element createLineItemSCH(QuoteData quoteData)
		{
			// loop creation
			String segmentCode = ediSpec_.getSegmentCode( "Line Item Schedule" );
			Element loop = createElement( "loop", segmentCode, "" );
			
			// Segment creation
			Element segment = createElement( "segment", segmentCode, "" );
		
			// Setting segment name
			Element segmentName = createElement( "name", "", "Line Item Schedule" );
			segment.appendChild( segmentName );
		
		
			// Quantity - element
			String element1Code = ediSpec_.getElementCode( "Quantity" );
			Element element1 = createElement( "element", element1Code, "" );

			// Quantity - name
			Element quantityName = createElement( "name", "", "Quantity" );
			element1.appendChild( quantityName );

			// Quantity - value
			Element quantityValue = createElement( "value", "", ""+quoteData.quoteItems_.get( 0 ).quantity_);
			element1.appendChild( quantityValue );

			segment.appendChild( element1 );
			
			// Unit or Measurement Code - element
			String unitCode = ediSpec_.getElementCode( "Unit or Basis for Measurement Code" );
			Element element2 = createElement( "element", unitCode, "" );
			
			// Unit or Measurement Code- name
			Element unitName = createElement( "name", "", "Unit or Basis for Measurement Code" );
			element2.appendChild( unitName );	
			
			//Unit or Measurement Code-value
			Element unitValue = createElement( "value", "TN", "Ton" );
			element2.appendChild( unitValue );

			segment.appendChild( element2 );
			
			// Date/Time Qualifier - element
			String dateTimeCode = ediSpec_.getElementCode( "Date/Time Qualifier" );
			Element element3 = createElement( "element", dateTimeCode, "" );
			
			//Date/Time Qualifier-name
			Element dateTimeName = createElement( "name", "", "Date/Time Qualifier" );
			element3.appendChild( dateTimeName );	
			
			//Date/Time Qualifier-value
			Element dateTimeValue = createElement( "value", "", "Delivery Requested" );
			element3.appendChild( dateTimeValue );

			segment.appendChild( element3 );
			
			//Date-element
			String dateCode = ediSpec_.getElementCode( "Date" );
			Element element4 = createElement( "element", dateCode, "" );
			
			//Date-name
			Element dateName = createElement( "name", "", "Date" );
			element4.appendChild( dateName );	
			
			//Date-value
			Element dateValue = createElement( "value", "", ""+quoteData.quoteDate_ );
			element4.appendChild( dateValue );

			segment.appendChild( element4 );
			
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
		
		private Element createTransTrailerSeg( int segmentCount, long quoteId )
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
			Element element2Value = createElement( "value", "", ""+quoteId );
			element2.appendChild( element2Value );

			segment.appendChild( element2 );
			
			return segment;
		}

}

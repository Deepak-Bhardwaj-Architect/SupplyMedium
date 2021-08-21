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
package utils;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * File: XMLMailConfigReader.java
 * 
 * Created on Jul 26, 2013 6:09:19 PM
 */

public class XMLMailConfigReader
{
    private static ErrorMaster errorMaster_ = null;




	public XMLMailConfigReader( )
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	public String getSubject( File xmlFile )
	{
		String subject = "";
		
		try
		{
//			File stocks = new File( "Stocks.xml" );
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance( );
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder( );
			Document doc = dBuilder.parse( xmlFile );
			doc.getDocumentElement( ).normalize( );

			errorMaster_.insert( "root of xml file"
			        + doc.getDocumentElement( ).getNodeName( ) );
			NodeList nodes = doc.getElementsByTagName( "mail" );
			errorMaster_.insert( "==========================" );

			
			//Right now, There are only one node for a single file
			//So, the nodes.getLength will be always one.
			
			for( int i = 0; i < nodes.getLength( ); i++ )
			{
				Node node = nodes.item( i );

				if( node.getNodeType( ) == Node.ELEMENT_NODE )
				{
					Element element = (Element)node;
					//errorMaster_.insert( "Stock Symbol: " + getValue( "subject", element ) );
					subject = getValue( "subject", element );
					
					//errorMaster_.insert( "Stock Price: " + getValue( "content", element ) );
				}
			}
			return subject;
		}
		catch( Exception ex )
		{
			ErrorLogger.instance( ).logMsg( "Exception::XMLMailConfigReader.getSubject( ) - "+ ex );
			
			return subject;
		}
	}


	public String getContent( File xmlFile )
	{
		String content = "";
		
		try
		{
//			File stocks = new File( "Stocks.xml" );
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance( );
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder( );
			Document doc = dBuilder.parse( xmlFile );
			doc.getDocumentElement( ).normalize( );

			errorMaster_.insert( "root of xml file"
			        + doc.getDocumentElement( ).getNodeName( ) );
			NodeList nodes = doc.getElementsByTagName( "mail" );
			errorMaster_.insert( "==========================" );

			
			//Right now, There are only one node for a single file
			//So, the nodes.getLength will be always one.
			
			for( int i = 0; i < nodes.getLength( ); i++ )
			{
				Node node = nodes.item( i );

				if( node.getNodeType( ) == Node.ELEMENT_NODE )
				{
					Element element = (Element)node;
					//errorMaster_.insert( "Stock Symbol: " + getValue( "subject", element ) );
					content = getValue( "content", element );
					
					//errorMaster_.insert( "Stock Price: " + getValue( "content", element ) );
				}
			}
			return content;
		}
		catch( Exception ex )
		{
			ErrorLogger.instance( ).logMsg( "Exception::XMLMailConfigReader.getContent( ) - "+ ex );
			
			return content;
		}
	}
	
	private String getValue( String tag, Element element )
	{
		NodeList nodes = element.getElementsByTagName( tag ).item( 0 ).getChildNodes( );
		Node node = (Node)nodes.item( 0 );
		return node.getNodeValue( );
	}
	

}

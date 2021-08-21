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

/**
 * File:  BinaryDecimalConverter.java 
 *
 * Created on Jul 24, 2013 12:31:23 PM
 */

/*This class is used to convert a string, int, long from binary to 
 * decimal or decimal to binary*/

public class BinaryDecimalConverter
{
	private static ErrorMaster errorMaster_ = null;



	/*Binary to decimal*/
	
	public long decToBin( long number )
	{
		try
        {
			return Long.parseLong( Long.toBinaryString( number ) );
        }
        catch( Exception e )
        {
	        // TODO: handle exception
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
        	errorMaster_.insert( "Exception - "+e );
        	return -1;
        }
        
	}
	
	/*Decimal to Binary*/
	public long binToDec( long number )
	{
		return Long.parseLong( String.valueOf( number ), 2 );
	}
}

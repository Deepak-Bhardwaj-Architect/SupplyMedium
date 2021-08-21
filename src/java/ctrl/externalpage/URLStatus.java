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
package ctrl.externalpage;

import java.net.HttpURLConnection;
import java.net.URL;
import utils.ErrorMaster;

/**
 * @FileName	: URLStatus.java
 *
 * @author 		: Anbazhagan
 *
 * @Date 		: May 12, 2013
 *
 * @Purpose 		: 
 *
 */
public class URLStatus
{
	public boolean isURLValid( String URL )
	{
		try
		{		
                     ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
			errorMaster_.insert( URL );
			URL u = new URL( URL );
			HttpURLConnection huc = (HttpURLConnection)u.openConnection( );
			huc.setRequestMethod( "GET" ); // OR huc.setRequestMethod ("HEAD");
			huc.connect( );
			int code = huc.getResponseCode( );
			errorMaster_.insert( ""+code );

			return true;
		}
		catch( Exception e )
		{
			return false;
		}

	}

}

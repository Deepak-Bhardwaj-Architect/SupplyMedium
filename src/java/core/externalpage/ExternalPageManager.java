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
package core.externalpage;


import core.regn.CompanyRegnKey;
import db.externalpage.ExternalPageTable;
import utils.AppConfigReader;
import utils.IntHolder;
import utils.StringHolder;

/**
 * @FileName : ExternalPageManager.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 5, 2013
 * 
 * @Purpose : It will handle all the Basic Work of External Page
 * 
 */
public class ExternalPageManager
{
	public int insert( ExternalPageData data, IntHolder externalpagekey )
	{
		int result = 0;

		result = this.companyURLNameExists( data );

		if( result == 4301 )
		{
			ExternalPageTable table = new ExternalPageTable( );
			result = table.insert( data, externalpagekey );

			if( result == 0 )
				result = 4000;
			else
				result = 4001;
		}

		return result;
	}

	public int getInformation( CompanyRegnKey key, ExternalPageData data )
	{
		int resutl = 0;

		ExternalPageTable table = new ExternalPageTable( );
		resutl = table.serachKey( key, data );

		if( resutl == 0 )
			return 4100;
		else
			return 4101;

	}

	/**
	 * @Date : May 11, 2013 11:41:45 AM
	 * 
	 * @Return : int
	 * 
	 * @Purpose :
	 * 
	 * @param pagedata
	 * @return
	 * 
	 */
	public int update( ExternalPageData pagedata )
	{
		int result = 0;

		result = this.companyURLNameExists( pagedata );

		if( result == 4301 )
		{
			ExternalPageTable table = new ExternalPageTable( );
			result = table.update( pagedata.compnayRegnKey_, pagedata );
			result = result == 0 ? 4200 : 4201;
		}

		return result;
	}

	private int companyURLNameExists( ExternalPageData data )
	{
		int result;
		ExternalPageData filter = new ExternalPageData( );
		filter.companyURLName_ = data.companyURLName_;

		ExternalPageData resultdata = new ExternalPageData( );

		ExternalPageTable table = new ExternalPageTable( );

		result = table.serachFilter( filter, resultdata );

		if( result == 0 )
		{
			if( resultdata.companyURLName_ != null )
			{
				result = 4301;

				if( resultdata.companyURLName_.equalsIgnoreCase( data.companyURLName_ )
				        && !resultdata.compnayRegnKey_.companyPhoneNo_.equalsIgnoreCase( data.compnayRegnKey_.companyPhoneNo_ ) )
					result = 4300;
				else
					result = 4301;
			}
			else
			{
				result = 4301;
			}
		}

		return result;
	}
	
	public int getWebSiteLink( ExternalPageData data, StringHolder websiteURL)
	{
		int resuit = 0;

		resuit = getInformation( data.compnayRegnKey_, data );
		
		if( resuit != 4100 || data.companyURLName_ == null)
			return resuit;
		
		AppConfigReader appConfigReader = AppConfigReader.instance( );
		
		String basePath = appConfigReader.get( "BASE_SERVER_URL" );
		
		websiteURL.value = basePath+"/SupplyMedium/External/"+data.companyURLName_;
		
		return 6200;

	}

}

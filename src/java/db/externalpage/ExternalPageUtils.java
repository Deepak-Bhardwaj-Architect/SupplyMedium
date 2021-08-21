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
package db.externalpage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.ErrorLogger;
import utils.StringHolder;
import core.externalpage.ExternalPageData;
import core.regn.CompanyRegnKey;

/**
 * @FileName : ExternalPageUtils.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : May 5, 2013
 * 
 * @Purpose :
 * 
 */
public class ExternalPageUtils
{

	/**
	 * @Date : May 5, 2013 1:13:37 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the ExternalPageData from the Database
	 * 
	 * @param rs
	 * @param data
	 * @return
	 * 
	 */
	public int getData( ResultSet rs, ExternalPageData data )
	{
		int result_ = 0;
		ExternalPageRecord record = new ExternalPageRecord( );

		try
		{
			while( rs.next( ) )
			{
				record.compnayRegnKey_ = rs.getString( "compnayRegnKey" );
				record.companyURLName_ = rs.getString( "companyURLName" );
				record.externalpageid_ = rs.getInt( "externalpageid" );
				record.pageType_ = rs.getString( "pageType" );
			}
		}
		catch( SQLException e )
		{
			result_ = -1;
		}

		if( result_ == 0 ) convert( record, data );

		return result_;
	}

	/**
	 * @Date : May 5, 2013 12:34:26 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Converting the ExternalPageData to ExternalPageRecord Object
	 * 
	 * @param data
	 * @param record
	 * 
	 */
	public void convert( ExternalPageData data, ExternalPageRecord record )
	{
		record.companyURLName_ = data.companyURLName_;
		record.compnayRegnKey_ = data.compnayRegnKey_ != null ? data.compnayRegnKey_.companyPhoneNo_ : "";
		record.externalpageid_ = data.externalpageid_;
		record.pageType_ = data.pageType_;
	}

	/**
	 * @Date : May 5, 2013 12:40:04 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Converting the ExternalPageRecord to ExternalPageData Object
	 * 
	 * @param record
	 * @param data
	 * 
	 */
	public void convert( ExternalPageRecord record, ExternalPageData data )
	{
		data.companyURLName_ = record.companyURLName_;
		data.compnayRegnKey_ = record.compnayRegnKey_ != "" ? new CompanyRegnKey( record.compnayRegnKey_ ) : null;
		data.externalpageid_ = record.externalpageid_;
		data.pageType_ = record.pageType_;
	}

	/**
	 * @Date : May 5, 2013 12:54:59 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Preparing the InsertString Query based on the given value
	 * 
	 * @param filter
	 * @param insertvalue
	 * @param insertcolumn
	 * @return
	 * 
	 */

	public int getInsertString( ExternalPageRecord filter, utils.StringHolder insertvalue, utils.StringHolder insertcolumn )
	{
		try
		{
			if( filter.compnayRegnKey_ != null )
			{
				insertvalue.value += " ?,";
				insertcolumn.value += "compnayRegnKey,";
			}

			if( filter.companyURLName_ != null )
			{
				insertvalue.value += " ?,";
				insertcolumn.value += "companyURLName,";
			}
			
			if( filter.pageType_ != null )
			{
				insertvalue.value += " ?,";
				insertcolumn.value += "pageType,";
			}

			if( filter.externalpageid_ != 0 )
			{
				insertvalue.value += " ?,";
				insertcolumn.value += "externalpageid,";
			}

			if( !insertvalue.value.equalsIgnoreCase( "" ) )
			{
				insertvalue.value = insertvalue.value.substring( 0, insertvalue.value.length( ) - 1 );
				insertcolumn.value = insertcolumn.value.substring( 0, insertcolumn.value.length( ) - 1 );
			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::ExternalPageUtils :: getInsertString :: " + ex );
			return -1;
		}
	}

	/**
	 * @Date : May 5, 2013 12:58:26 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Preparing the External Page Query String
	 * 
	 * @param filter
	 * @param searchQuery
	 * @return
	 * 
	 */
	public int getFilterString( ExternalPageRecord filter, StringHolder searchQuery )
	{
		try
		{
			searchQuery.value += filter.compnayRegnKey_ != null ? " compnayRegnKey=? and" : "";

			searchQuery.value += filter.companyURLName_ != null ? " companyURLName=? and" : "";
			
			searchQuery.value += filter.pageType_ != null ? " pageType=? and" : "";

			searchQuery.value += filter.externalpageid_ != 0 ? " externalpageid=? and" : "";

			if( !searchQuery.value.equalsIgnoreCase( "" ) )
			    searchQuery.value = " Where " + searchQuery.value.substring( 0, searchQuery.value.length( ) - 3 );

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::ExternalPageUtils :: getFilterString :: " + ex );
			return -1;
		}
	}

	/**
	 * @Date : May 5, 2013 1:00:59 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Preparing the Update String Query
	 * 
	 * @param filter
	 * @param query
	 * @return
	 * 
	 */
	public int getUpdateString( ExternalPageRecord filter, StringHolder query )
	{
		try
		{
			query.value += filter.compnayRegnKey_ != null ? " compnayRegnKey=? ," : "";

			query.value += filter.companyURLName_ != null ? " companyURLName=? ," : "";
			
			query.value += filter.pageType_ != null ? " pageType=? ," : "";

			query.value += filter.externalpageid_ != 0 ? " externalpageid=? ," : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			{
				query.value = query.value.substring( 0, query.value.length( ) - 1 );
			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::ExternalPageUtils :: getUpdateString :: " + ex );
			return -1;
		}
	}

	/**
	 * @Date : May 5, 2013 1:04:38 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Preparing the Statement dynamically
	 * 
	 * @param filter
	 * @param prestmt
	 * @return
	 * 
	 */
	public int prepareStatement( ExternalPageRecord filter, PreparedStatement prestmt )
	{
		try
		{
			int i = 1;

			if( filter.compnayRegnKey_ != null )
			{
				prestmt.setString( i++, filter.compnayRegnKey_ );
			}

			if( filter.companyURLName_ != null )
			{
				prestmt.setString( i++, filter.companyURLName_ );
			}
			
			if( filter.pageType_ != null )
			{
				prestmt.setString( i++, filter.pageType_ );
			}

			if( filter.externalpageid_ != 0 )
			{
				prestmt.setInt( i++, filter.externalpageid_ );
			}

			return i;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::ExternalPageUtils :: prepareStatement :: " + ex );
			return -1;
		}
	}

}

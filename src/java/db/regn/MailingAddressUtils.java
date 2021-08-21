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
package db.regn;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import utils.ErrorLogger;
import utils.StringHolder;
import core.regn.CompanyRegnKey;
import core.regn.MailingAddressData;
import core.regn.MailingAddressKey;
import utils.ErrorMaster;

/**
 * @FileName : MailingAddressUtils.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Mar 23, 2013
 * 
 * @Purpose : convert MailingAddressdata to MailingAddressRecord
 * 
 */
public class MailingAddressUtils
{
private static ErrorMaster errorMaster_ = null;


	/**
	 * @Date : Mar 23, 2013 4:50:40 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Getting Mailing Address Data from the DB
	 * 
	 * @param rs
	 * @param maildata
	 * @return
	 * 
	 */
public MailingAddressUtils()
{

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
}
	public int getData( ResultSet rs, MailingAddressData maildata )
	{
		MailingAddressRecord record = new MailingAddressRecord( );
		int result = getRecord( rs, record );
		if( result == 0 ) convert( record, maildata );

		return result;
	}

	/**
	 * @Date : Mar 23, 2013 4:56:30 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Geting Mailing Address List
	 * 
	 * @param rs
	 * @param dataList
	 * @return
	 * 
	 */
	public int getDataList( ResultSet rs, List<MailingAddressData> dataList )
	{
		List<MailingAddressRecord> RecordList = new ArrayList<MailingAddressRecord>( );
		int result = getRecordList( rs, RecordList );
		if( result == 0 )
		{
			for( MailingAddressRecord record : RecordList )
			{
				MailingAddressData data = new MailingAddressData( );
				convert( record, data );
				dataList.add( data );
				//data.show( );
			}
		}

		return result;
	}

	/**
	 * @Date : Mar 23, 2013 4:28:46 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Convert MailingAddressData To MailingAddressRecord
	 * 
	 * @param data
	 * @param record
	 * 
	 */
	public void convert( MailingAddressData data, MailingAddressRecord record )
	{
		record.address_ = data.address_;
		record.userId_ = data.userID_;
		record.mailKey_ = data.mailkey_ != null ? data.mailkey_.mailngKey_ : 0;
		record.addressType_ = data.addressType_;
		record.cityName_ = data.city_;
		record.countryName_ = data.countryRegion_;
		record.email_ = data.emailid_;
		record.regnKey_ = data.companyRegnKey_ != null ? data.companyRegnKey_.companyPhoneNo_ : null;
		record.stateName_ = data.state_;
		record.zipcode_ = data.zipcode_;
	}

	/**
	 * @Date : Mar 23, 2013 4:28:59 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Convert MailingAddressRecord to MailingAddressData
	 * 
	 * @param record
	 * @param data
	 * 
	 */
	public void convert( MailingAddressRecord record, MailingAddressData data )
	{
		errorMaster_.insert( "______________________________________________________addressid="+record.addressId_);
		data.address_ = record.address_;
		data.userID_ = record.userId_;
		data.mailkey_ = new MailingAddressKey( record.mailKey_ );
		data.addrid_ = record.addressId_;
		data.addressType_ = record.addressType_;
		data.city_ = record.cityName_;
		data.countryRegion_ = record.countryName_;
		data.emailid_ = record.email_;
		data.companyRegnKey_ = new CompanyRegnKey( record.regnKey_ );
		data.state_ = record.stateName_;
		data.zipcode_ = record.zipcode_;
	}

	/**
	 * @Date : Mar 23, 2013 4:46:23 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get MailingAddress Record Based on the Key
	 * 
	 * @param rs
	 * @param record
	 * @return
	 * 
	 */
	private int getRecord( ResultSet rs, MailingAddressRecord record )
	{

		try
		{
			int result;
			while( rs.next( ) )
			{
				result = getRecordObj( rs, record );

				if( result == -1 ) return result;
			}

			return 0;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "MailingAddressUtils :: getRecord :: SQL Error " + ex );
			return -1;
		}

	}

	/**
	 * @Date : Mar 23, 2013 4:57:05 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Geting Mailing Address based on the Filter
	 * 
	 * @param rs
	 * @param recordList
	 * @return
	 * 
	 */
	private int getRecordList( ResultSet rs, List<MailingAddressRecord> recordList )
	{
		try
		{
			int result;
			while( rs.next( ) )
			{
				MailingAddressRecord record = new MailingAddressRecord( );
				result = getRecordObj( rs, record );
				if( result == -1 ) return result;
				recordList.add( record );
			}

			return 0;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "MailingAddressUtils :: getRecordList :: SQL Error " + ex );
			return -1;

		}

	}

	/**
	 * @Date : Mar 23, 2013 4:44:59 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Geting Mailing Address info
	 * 
	 * @param rs
	 * @param record
	 * @return
	 * 
	 */
	private int getRecordObj( ResultSet rs, MailingAddressRecord record )
	{
		try
		{

			record.address_ = rs.getString( "address" );
			record.mailKey_ = rs.getLong( "mail_key" );
			record.userId_ = rs.getLong( "user_id" );
			record.addressType_ = rs.getString( "address_type" );

			record.cityName_ = rs.getString( "city_name" );
			record.countryName_ = rs.getString( "country_name" );
			record.email_ = rs.getString( "email" );

			record.regnKey_ = rs.getString( "regn_key" );
			record.stateName_ = rs.getString( "state_name" );
			record.zipcode_ = rs.getString( "zipcode" );
			
			record.addressId_ = rs.getLong( "address_id" );

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "MailingAddressUtils :: getRecordObj :: SQL Error " + ex );
			return -1;
		}
	}

	/**
	 * @Date : Mar 23, 2013 5:24:57 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Generating Insert Query
	 * 
	 * @param filter
	 * @param query
	 * @return
	 * 
	 */
	public int getInsertString( MailingAddressRecord filter, StringHolder query, StringHolder columList )
	{
		try
		{
			if( filter.mailKey_ != 0 )
			{
				query.value += " ?,";
				columList.value += "mail_key,";
			}

			if( filter.userId_ != 0 )
			{
				query.value += " ?,";
				columList.value += "user_id,";
			}

			if( filter.addressType_ != null )
			{
				query.value += " ?,";
				columList.value += "address_type,";
			}

			if( filter.address_ != null )
			{
				query.value += " ?,";
				columList.value += "address,";
			}

			if( filter.cityName_ != null )
			{
				query.value += " ?,";
				columList.value += "city_name,";
			}

			if( filter.stateName_ != null )
			{
				query.value += " ?,";
				columList.value += "state_name,";
			}

			if( filter.zipcode_ != null )
			{
				query.value += " ?,";
				columList.value += "zipcode,";
			}

			if( filter.countryName_ != null )
			{
				query.value += " ?,";
				columList.value += "country_name,";
			}

			if( filter.email_ != null )
			{
				query.value += " ?,";
				columList.value += "email,";
			}

			if( filter.regnKey_ != null )
			{
				query.value += " ?,";
				columList.value += "regn_key,";
			}

			if( !query.value.equalsIgnoreCase( "" ) )
			{
				query.value = query.value.substring( 0, query.value.length( ) - 1 );
				columList.value = columList.value.substring( 0, columList.value.length( ) - 1 );
			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::MailingAddressUtils :: getInsertString :: " + ex );
			return -1;
		}
	}

	/**
	 * @Date : Mar 23, 2013 4:35:12 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Filter String Generation for Mailing Address Table
	 * 
	 * @param filter
	 * @param query
	 * @return
	 * 
	 */
	public int getFilterString( MailingAddressRecord filter, StringHolder query )
	{
		try
		{
			query.value += filter.address_ != null ? " address=? and" : "";

			query.value += filter.addressId_ != 0 ? " address_id=? and" : "";

			query.value += filter.addressType_ != null ? " address_type=? and" : "";

			query.value += filter.cityName_ != null ? " city_name=? and" : "";

			query.value += filter.countryName_ != null ? " country_name=? and" : "";

			query.value += filter.email_ != null ? " email=? and" : "";

			query.value += filter.regnKey_ != null ? " regn_key=? and" : "";
			
			query.value+=filter.zipcode_!=null? " zipcode=? and" :"";

			query.value += filter.stateName_ != null ? " state_name=? and" : "";
			
			errorMaster_.insert( "query.value="+query.value );

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "MailingAddressUtils :: getFilterString :: " + ex );
			return -1;
		}
	}

	/**
	 * @Date : Mar 23, 2013 4:36:35 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Update String Generation
	 * 
	 * @param filter
	 * @param query
	 * @return
	 * 
	 */
	public int getUpdateString( MailingAddressRecord filter, StringHolder query )
	{
		try
		{
			query.value += filter.mailKey_ != 0 ? " mail_key=? ," : "";

			query.value += filter.userId_ != 0 ? " user_id=? ," : "";

			query.value += filter.addressType_ != null ? " address_type=? ," : "";

			query.value += filter.address_ != null ? " address=? ," : "";

			query.value += filter.cityName_ != null ? " city_name=? ," : "";

			query.value += filter.stateName_ != null ? " state_name=? ," : "";

			query.value += filter.zipcode_ != null ? " zipcode=? ," : "";

			query.value += filter.countryName_ != null ? " country_name=? ," : "";

			query.value += filter.email_ != null ? " email=? ," : "";

			query.value += filter.regnKey_ != null ? " regn_key=? ," : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			{
				query.value = query.value.substring( 0, query.value.length( ) - 1 );
			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "MailingAddressUtils :: getUpdateString :: " + ex );
			return -1;
		}
	}

	/**
	 * @Date : Mar 23, 2013 4:28:20 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Prepare the Statement
	 * 
	 * @param filter
	 * @param prestmt
	 * @return
	 * 
	 */
	public int prepareStatement( MailingAddressRecord filter, PreparedStatement prestmt )
	{
		try
		{
			int i = 1;

			if( filter.mailKey_ != 0 )
			{
				prestmt.setLong( i++, filter.mailKey_ );
			}

			if( filter.userId_ != 0 )
			{
				prestmt.setLong( i++, filter.userId_ );
			}

			if( filter.addressType_ != null )
			{
				prestmt.setString( i++, filter.addressType_ );
			}

			if( filter.address_ != null )
			{
				prestmt.setString( i++, filter.address_ );
			}

			if( filter.cityName_ != null )
			{
				prestmt.setString( i++, filter.cityName_ );
			}

			if( filter.stateName_ != null )
			{
				prestmt.setString( i++, filter.stateName_ );
			}

			if( filter.zipcode_ != null )
			{
				prestmt.setString( i++, filter.zipcode_ );
			}

			if( filter.countryName_ != null )
			{
				prestmt.setString( i++, filter.countryName_ );
			}

			if( filter.email_ != null )
			{
				prestmt.setString( i++, filter.email_ );
			}

			if( filter.regnKey_ != null )
			{
				prestmt.setString( i++, filter.regnKey_ );
			}

			return i;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "Exception::MailingAddressUtils :: prepareStatement :: " + ex );
			return -1;
		}
	}
}

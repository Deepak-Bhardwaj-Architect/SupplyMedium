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

import core.regn.CompanyRegnKey;
import core.regn.RegnData;

import utils.ErrorLogger;
import utils.StringHolder;

/**
 * @FileName : CompanyRegnUtils.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Mar 23, 2013
 * 
 * @Purpose : CompanyRengn Table Utils
 * 
 */
public class CompanyRegnUtils
{

	/**
	 * @Date : Mar 23, 2013 12:29:47 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get the Result Set and Convert them to Regn Data
	 * 
	 * @param rs
	 * @param regndata
	 * @return
	 * 
	 */
	public int getData( ResultSet rs, RegnData regndata )
	{
		CompanyRegnRecord record = new CompanyRegnRecord( );
		int result = getRecord( rs, record );
		if( result == 0 ) convert( record, regndata );

		return result;
	}

	/**
	 * @Date : Mar 23, 2013 12:31:29 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : get the RegnData from the ResultSet
	 * 
	 * @param rs
	 * @param regnDataList
	 * @return
	 * 
	 */
	public int getDataList( ResultSet rs, List<RegnData> regnDataList )
	{
		List<CompanyRegnRecord> companyRegnRecordList = new ArrayList<CompanyRegnRecord>( );
		int result = getRecordList( rs, companyRegnRecordList );
		if( result == 0 )
		{
			for( CompanyRegnRecord record : companyRegnRecordList )
			{
				RegnData regndata = new RegnData( );
				convert( record, regndata );
				regnDataList.add( regndata );

			}
		}

		return result;
	}

	/**
	 * @Date : Mar 23, 2013 12:17:31 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Convert CompanyRegnRecord to RegnData
	 * 
	 * @param regnRecord
	 * @param regndata
	 * 
	 */
	public void convert( CompanyRegnRecord regnRecord, RegnData regndata )
	{
		// CompanyRegnRecord regnRecord=getRegnRecord(rs);

		// set the values
		regndata.companyId_ = regnRecord.companyId_;
		regndata.companyName_ = regnRecord.companyName_;
		regndata.logo_ = regnRecord.companyLogoPath_;
		regndata.businessCategory_ = regnRecord.businessCategoryName_;

		regndata.signUpAs_ = regnRecord.companyType_;
                //System.out.print("regndata.signUpAs==========================>"+regndata.signUpAs_);
		regndata.theme_ = regnRecord.companyTheme_;
		regndata.pricingOption_ = regnRecord.pricingOption_;

		regndata.phoneNo_ = regnRecord.companyPhoneNo_;
		regndata.uuid_ = regnRecord.uuid_;
		regndata.companyStatus_ = regnRecord.companyStatus_;

		regndata.segmentDivisionName_ = regnRecord.segmentDivisionName_;
		regndata.businessUnitName_ = regnRecord.businessUnitName_;
		regndata.emailId_ = regnRecord.email_;

		regndata.createdDate_ = regnRecord.createdDate_;

		CompanyRegnKey companyRegnKey = new CompanyRegnKey( );
		companyRegnKey.companyPhoneNo_ = regnRecord.companyPhoneNo_;
		regndata.companyRegnKey_ = companyRegnKey;

	}

	/**
	 * @Date : Mar 23, 2013 2:34:45 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Convert RegnData to CompanyRegnRecord
	 * 
	 * @param regndata
	 * @param regnRecord
	 * 
	 */
	public void convert( RegnData regndata, CompanyRegnRecord regnRecord )
	{
		// CompanyRegnRecord regnRecord=getRegnRecord(rs);

		// set the values
		regnRecord.companyName_ = regndata.companyName_;
		regnRecord.companyLogoPath_ = regndata.logoPath_;
		regnRecord.businessCategoryName_ = regndata.businessCategory_;
                //System.out.println("regnRecord.businessCategoryName_============="+regnRecord.businessCategoryName_);
		regnRecord.companyType_ = regndata.signUpAs_;
		regnRecord.companyTheme_ = regndata.theme_;
		regnRecord.pricingOption_ = regndata.pricingOption_;

		regnRecord.companyPhoneNo_ = regndata.phoneNo_;
		regnRecord.uuid_ = regndata.uuid_;
		regnRecord.companyStatus_ = regndata.companyStatus_;

		regnRecord.segmentDivisionName_ = regndata.segmentDivisionName_;
		regnRecord.businessUnitName_ = regndata.businessUnitName_;
		regnRecord.email_ = regndata.emailId_;

		regnRecord.createdDate_ = regndata.createdDate_;

		regnRecord.regnKey_ = regndata.companyRegnKey_.companyPhoneNo_;

	}

	/**
	 * @Date : Mar 23, 2013 12:33:32 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Get CompanyRegnRecord From ResultSet
	 * 
	 * @param rs
	 * @param companyRegnRecObj
	 * @return
	 * 
	 */
	private int getRecord( ResultSet rs, CompanyRegnRecord companyRegnRecObj )
	{

		try
		{
			int result;
			while( rs.next( ) )
			{
				result = getRecordObj( rs, companyRegnRecObj );

				if( result == -1 ) return result;
			}

			return 0;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "CompanyRegnUtils : getRecord :: SQL Error " + ex );
			return -1;
		}

	}

	/**
	 * @Date : Mar 23, 2013 11:39:44 AM
	 * 
	 * @Return : List<CompanyRegnRecord>
	 * 
	 * @Purpose : Conver the ResultSet to Rs
	 * 
	 * @param rs
	 * @return
	 * 
	 */
	private int getRecordList( ResultSet rs, List<CompanyRegnRecord> companyRegnRecObj )
	{
		try
		{
			int result;
			while( rs.next( ) )
			{
				CompanyRegnRecord record = new CompanyRegnRecord( );
				result = getRecordObj( rs, record );
				if( result == -1 ) return result;
				companyRegnRecObj.add( record );
			}

			return 0;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "CompanyRegnUtils : getRecordList :: SQL Error " + ex );
			return -1;

		}

	}

	/**
	 * @Date : Mar 23, 2013 12:35:33 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Input the Result Set and Return the CompanyRegnRecord
	 * 
	 * @param rs
	 * @param companyRegnRecObj
	 * @return
	 * 
	 */
	private int getRecordObj( ResultSet rs, CompanyRegnRecord companyRegnRecObj )
	{
		try
		{

			companyRegnRecObj.companyId_ = rs.getString( "company_id" );
			companyRegnRecObj.companyName_ = rs.getString( "company_name" );
			companyRegnRecObj.companyLogoPath_ = rs.getString( "company_logo_path" );

			companyRegnRecObj.businessCategoryName_ = rs.getString( "business_category_name" );
			companyRegnRecObj.companyType_ = rs.getString( "company_type" );
			companyRegnRecObj.companyTheme_ = rs.getString( "company_theme" );

			companyRegnRecObj.pricingOption_ = rs.getString( "pricing_option" );
			companyRegnRecObj.companyPhoneNo_ = rs.getString( "company_phoneno" );
			companyRegnRecObj.uuid_ = rs.getString( "uuid" );

			companyRegnRecObj.createdDate_ = rs.getTimestamp( "created_date" );
			companyRegnRecObj.companyStatus_ = rs.getString( "company_status" );
			companyRegnRecObj.segmentDivisionName_ = rs.getString( "segment_division_name" );

			companyRegnRecObj.businessUnitName_ = rs.getString( "business_unit_name" );
			companyRegnRecObj.regnKey_ = rs.getString( "regn_key" );
			companyRegnRecObj.email_ = rs.getString( "email" );

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "CompanyRegnUtils :: getRecordObj :: SQL Error " + ex );
			return -1;
		}
	}

	/**
	 * @Date : Mar 23, 2013 12:45:16 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Createt the Filter Query Based on the RegnData
	 * 
	 * @param filter
	 * @param query
	 * @param prestmt
	 * @return
	 * 
	 */
	public int getFilterString( CompanyRegnRecord filter, StringHolder query )
	{
		try
		{
			query.value += filter.companyId_ != null ? " company_id=? and" : "";

			query.value += filter.companyName_ != null ? " company_name=? and" : "";

			query.value += filter.businessCategoryName_ != null ? " business_category_name=? and" : "";

			query.value += filter.companyType_ != null ? " company_type=? and" : "";

			query.value += filter.companyTheme_ != null ? " company_theme=? and" : "";

			query.value += filter.pricingOption_ != null ? " pricing_option=? and" : "";

			query.value += filter.companyPhoneNo_ != null ? " company_phoneno=? and" : "";

			query.value += filter.uuid_ != null ? " uuid=? and" : "";

			query.value += filter.companyStatus_ != null ? " company_status=? and" : "";

			query.value += filter.segmentDivisionName_ != null ? " segment_division_name=? and" : "";

			query.value += filter.businessUnitName_ != null ? " business_unit_name=? and" : "";

			query.value += filter.regnKey_ != null ? " regn_key=? and" : "";

			query.value += filter.email_ != null ? " email=? and" : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			    query.value = " Where " + query.value.substring( 0, query.value.length( ) - 3 );

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "CompanyRegnUtils :: getFilterString :: " + ex );
			return -1;
		}
	}

	/**
	 * @Date : Mar 23, 2013 4:36:01 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Update String for CompanRegnTable
	 * 
	 * @param filter
	 * @param query
	 * @return
	 * 
	 */
	public int getUpdateString( CompanyRegnRecord filter, StringHolder query )
	{
		try
		{
			query.value += filter.companyId_ != null ? " company_id=? ," : "";
                        query.value += filter.companyLogoPath_ != null ? " company_logo_path=? ," : "";

			query.value += filter.companyName_ != null ? " company_name=? ," : "";

			query.value += filter.businessCategoryName_ != null ? " business_category_name=? ," : "";

//			query.value += filter.companyType_ != null ? " company_type=? ," : "";

			query.value += filter.companyTheme_ != null ? " company_theme=? ," : "";

			query.value += filter.pricingOption_ != null ? " pricing_option=? ," : "";

			query.value += filter.companyPhoneNo_ != null ? " company_phoneno=? ," : "";

			query.value += filter.uuid_ != null ? " uuid=? ," : "";

			query.value += filter.companyStatus_ != null ? " company_status=? ," : "";

			query.value += filter.segmentDivisionName_ != null ? " segment_division_name=? ," : "";

			query.value += filter.businessUnitName_ != null ? " business_unit_name=? ," : "";

			query.value += filter.regnKey_ != null ? " regn_key=? ," : "";

			query.value += filter.email_ != null ? " email=? ," : "";

			if( !query.value.equalsIgnoreCase( "" ) )
			{
				query.value = query.value.substring( 0, query.value.length( ) - 1 );
			}

			return 0;
		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "CompanyRegnUtils :: getUpdateString :: " + ex );
			return -1;
		}
	}

	/**
	 * @Date : Mar 23, 2013 12:45:16 PM
	 * 
	 * @Return : int
	 * 
	 * @Purpose : Createt the Filter Query Based on the RegnData
	 * 
	 * @param filter
	 * @param query
	 * @param prestmt
	 * @return
	 * 
	 */
	public int prepareStatement( CompanyRegnRecord filter, PreparedStatement prestmt )
	{
		try
		{
			int i = 1;

			if( filter.companyId_ != null )
			{                                 
				prestmt.setString( i++, filter.companyId_ );
                                //System.out.println("filter.companyId_:="+i);
			}

			if( filter.companyLogoPath_ != null )
			{
				prestmt.setString( i++, filter.companyLogoPath_ );
                                //System.out.println("filter.companyLogoPath_:="+i);
			}
                        
                        if( filter.companyName_ != null )
			{

				prestmt.setString( i++, filter.companyName_ );
                                //System.out.println("filter.companyName_:="+i);
			}

			if( filter.businessCategoryName_ != null )
			{
				prestmt.setString( i++, filter.businessCategoryName_ );
                                //System.out.println("filter.businessCategoryName_:="+i);
			}

			/*if( filter.companyType_ != null )
			{
				prestmt.setString( i++, filter.companyType_ );
                                System.out.println("filter.companyType_:="+i);
			}*/

			if( filter.companyTheme_ != null )
			{
				prestmt.setString( i++, filter.companyTheme_ );
                                //System.out.println("filter.companyTheme_:="+i);
			}

			if( filter.pricingOption_ != null )
			{
				prestmt.setString( i++, filter.pricingOption_ );
                                //System.out.println("filter.pricingOption_:="+i);
			}

			if( filter.companyPhoneNo_ != null )
			{
				prestmt.setString( i++, filter.companyPhoneNo_ );
                                //System.out.println("filter.companyPhoneNo_:="+i);
			}

			if( filter.uuid_ != null )
			{
				prestmt.setString( i++, filter.uuid_ );
                                //System.out.println("filter.uuid_:="+i);
			}

			if( filter.companyStatus_ != null )
			{
				prestmt.setString( i++, filter.companyStatus_ );
                                //System.out.println("filter.companyStatus_:="+i);
			}

			if( filter.segmentDivisionName_ != null )
			{
				prestmt.setString( i++, filter.segmentDivisionName_ );
                                //System.out.println("filter.segmentDivisionName_:="+i);
			}

			if( filter.businessUnitName_ != null )
			{
				prestmt.setString( i++, filter.businessUnitName_ );
                                //System.out.println("filter.businessUnitName_:="+i);
			}

			if( filter.regnKey_ != null )
			{
				prestmt.setString( i++, filter.regnKey_ );
                                //System.out.println("filter.regnKey_:="+i);
			}

			if( filter.email_ != null )
			{
				prestmt.setString( i++, filter.email_ );
                                //System.out.println("filter.email_:="+i);
			}

			return i;

		}
		catch( Exception ex )
		{
			ErrorLogger errLogger_ = ErrorLogger.instance( );
			errLogger_.logMsg( "CompanyRegnUtils :: prepareStatement :: " + ex );
			return -1;
		}
	}

}

/**
 * CompanyRegnTableUtils.java : Mar 27, 2013
 * 
 * 
 */
package db.regn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import utils.ErrorLogger;
import utils.StringHolder;
import core.regn.CompanyRegnKey;
import core.regn.RegnData;

/**
 * @author 
 * 
 * This is a utils class for the CompanyRegnTable, that provides various helper methods like, forming where clause
 * of a query, forming insert query string etc. These were originally as private methods of the CompanyRegnTable class,
 * but moved here now, as helper methods in utils class.
 *
 */
public class CompanyRegnTableUtils
{

	/**
	 * @param args
	 * 
	 */
	
	public String	tableName_;

	
	public  CompanyRegnTableUtils( )
	{
		
		
	}
	
	
	
	public int formWhereClause( RegnData queryFilter, StringHolder whereClause )
	{
		
		int result = -1;
		
		if( queryFilter.signUpAs_ != "" && queryFilter.signUpAs_ != null )
		{
			if( whereClause.value != "" )
			{
				whereClause.value += " AND";
			}
			else
			{
				whereClause.value += " WHERE";
			}
			
			whereClause.value += " ( company_type = '" + queryFilter.signUpAs_ + "'";
			whereClause.value += " OR company_type = 'Both' )";
			result = 0;
		}

		if( queryFilter.businessCategory_ != "" && queryFilter.businessCategory_ != null && !queryFilter.businessCategory_.equals( "All" ))
		{
			
			if( whereClause.value != "" )
			{
				whereClause.value += " AND";
			}
			else
			{
				whereClause.value += " WHERE";
			}
			
			whereClause.value += " business_category_name = '" + queryFilter.businessCategory_ + "'" ;
			
			result = 0;
			
		}
		
		System.out.println( "where condiction ="+ whereClause);
		
		return result;
		
	}
	
	
	/**
	 * This method converts the result set object, to a list of RegData objects.
	 * 
	 * Input: ResultSet rs. This can contain one or more records of the company registration table.
	 * 
	 * Output: The Set<> of CompanyRegnKey objects. This is useful for the main table class when it needs
	 * keys from the result set.
	 * 
	 */
	public int rsToDataList( ResultSet rs, List<RegnData> companyList )
	{

		RegnData regnDataObj = null;
		CompanyRegnKey companyRegnKeyObj = null;
		ErrorLogger errLogger = ErrorLogger.instance( );


		try
		{
			while( rs.next( ) )
			{
				regnDataObj = null;
				regnDataObj = new RegnData( );

				companyRegnKeyObj = null;
				companyRegnKeyObj = new CompanyRegnKey( );

				companyRegnKeyObj.companyPhoneNo_ 	= rs.getString( "regn_key" );
				regnDataObj.emailId_ 				= rs.getString( "email" );

				regnDataObj.companyRegnKey_ 		= companyRegnKeyObj;
				regnDataObj.companyName_ 			= rs.getString( "company_name" );
				
				regnDataObj.companyId_ 				= rs.getString( "company_id" );
				regnDataObj.companyName_ 			= rs.getString( "company_name" );

				regnDataObj.businessCategory_ 		= rs.getString( "business_category_name" );
				
				//regnDataObj.companyType_ = rs.getString( "company_type" );
				
				regnDataObj.theme_ 					= rs.getString( "company_theme" );

				regnDataObj.pricingOption_ 			= rs.getString( "pricing_option" );
				regnDataObj.phoneNo_ 				= rs.getString( "company_phoneno" );
				regnDataObj.uuid_ 					= rs.getString( "uuid" );

				regnDataObj.createdDate_ 			= rs.getTimestamp( "created_date" );
				regnDataObj.companyStatus_ 			= rs.getString( "company_status" );
				regnDataObj.segmentDivisionName_ 	= rs.getString( "segment_division_name" );

				regnDataObj.businessUnitName_ 		= rs.getString( "business_unit_name" );
				
				
				companyList.add( regnDataObj );

			}
			return 0;
			// success

		}
		catch( SQLException ex )
		{
			errLogger.logMsg( "SQLException::CompanyRegnTable:rsToDataList" + ex );
			return -1;
			// SQLException occurred while populating a Supplier Search Result
			// list
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:rsToDataList" + ex );

			return -2;
		}
		
		
	}
	
	
	
	
	/**
	 * This method converts the result set object, to a set of company Keys object.
	 * 
	 * Input: ResultSet rs. This can contain one or more records of the company registration table.
	 * 
	 * Output: The Set<> of CompanyRegnKey objects. This is useful for the main table class when it needs
	 * keys from the result set.
	 * 
	 */
	public void rsToKeySet( ResultSet rs, Set<CompanyRegnKey> companyKeys )
	{
		
		CompanyRegnKey companyRegnKeyObj = null;
		ErrorLogger errLogger = ErrorLogger.instance( );
	
		try
		{
			while( rs.next( ) )
			{
				companyRegnKeyObj = null;
				companyRegnKeyObj = new CompanyRegnKey( );
				companyRegnKeyObj.companyPhoneNo_ = rs.getString( "regn_key" );
				companyKeys.add( companyRegnKeyObj );
			}

		}
		catch( SQLException ex )
		{
			errLogger.logMsg( "SQLException::CompanyRegnTable:getCompanyKeySet " + ex );
			// SQLException occurred while populating a Company Set
		}
		catch( Exception ex ) // General Exception
		{
			errLogger.logMsg( "Exception::CompanyRegnTable:getCompanyKeySet" + ex );
		}
		
			
	}
	

	/*
	 * Method : formInsertQuery( )
	 * 
	 * Input : CompanyRegnRecord obj
	 * 
	 * Return : String
	 * 
	 * Purpose: It forms an insert query using CompanyRegnRecord and returns as
	 * string
	 */

	public String formInsertQuery( CompanyRegnRecord companyRegnRecObj )
	{

		// form the query

		String insertQuery = null;
		String insertValues = null;

		insertValues = "(company_id,company_name, company_logo_path, business_category_name, company_type, ";
		insertValues = insertValues
		        + "company_theme, pricing_option, company_phoneno, uuid, company_status,  ";
		insertValues = insertValues + "segment_division_name, business_unit_name,regn_key,email)";

		insertQuery = "INSERT INTO " + tableName_ + " " + insertValues + " VALUES ";
		insertQuery = insertQuery + "(";

		insertQuery = insertQuery + "'" + companyRegnRecObj.companyId_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.companyName_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.companyLogoPath_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.businessCategoryName_ + "', ";

		insertQuery = insertQuery + "'" + companyRegnRecObj.companyType_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.companyTheme_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.pricingOption_ + "', ";

		insertQuery = insertQuery + "'" + companyRegnRecObj.companyPhoneNo_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.uuid_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.companyStatus_ + "', ";

		insertQuery = insertQuery + "'" + companyRegnRecObj.segmentDivisionName_ + "', ";
		insertQuery = insertQuery + "'" + companyRegnRecObj.businessUnitName_ + "',";
		insertQuery = insertQuery + "'" + companyRegnRecObj.regnKey_ + "',";

		insertQuery = insertQuery + "'" + companyRegnRecObj.email_ + "'";

		insertQuery = insertQuery + ")";

		return insertQuery;
		
	}
	

}

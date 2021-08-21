/**
 * 
 */
package db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Set;

import core.regn.CompanyRegnKey;

import utils.ErrorLogger;

import db.utils.DBConnect;
import utils.ErrorMaster;


public class RegnCompanyMapperTable 
{
	private static ErrorMaster errorMaster_ = null;



	private ErrorLogger errLogger_;
	private String 		tableName_;
	private Statement 	stmt_;
	private Connection 	con_;
	
	/*
	 * Method : RegnCompanyMapperTable( ) - constructor
	 * 
	 * Input :
	 * 
	 * Return :
	 * 
	 * Purpose: It is used for initialization
	 */
	public RegnCompanyMapperTable()
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		this.errLogger_ = ErrorLogger.instance( );
		this.tableName_ = "regn_vendor_mapper";
		this.stmt_ 		= null;
		this.con_ 		= null;
		
	}
	
	
	/**
	 * 
	 */
	public int getRegnCompanyKeys( CompanyRegnKey regnKey, Set<CompanyRegnKey> companyKeys )
	{
		
		String query = null;

		try
		{
			con_ = DBConnect.instance( ).getConnection( );

			stmt_ = con_.createStatement( );

			
			query =  "SELECT DISTINCT regn_rel_key_map FROM " + this.tableName_;
			query += " WHERE regn_rel_key = '" + regnKey.companyPhoneNo_ + "'";
			
			errorMaster_.insert( "getRegnCompanyKeys query: " + query );
			
			
			ResultSet rs = stmt_.executeQuery( query );
			
			if( rs == null ) return -1;
			
			this.getRegnCompanyKeySet( rs, companyKeys );

			return 0;
			// Success
		}
		catch( SQLException ex )
		{
			
			errLogger_.logMsg( "SQLException::RegnCompanyMapperTable : getRegnCompanyKeys " + ex );
			
			errorMaster_.insert( "RegnCompanyMapperTable.getRegnCompanyKeys() ex->" + ex );
			
			return -2;
			// SQLException occurred while filtering a Registered Company
		}
		catch( Exception ex )
		{
			
			errLogger_.logMsg( "Exception::RegnCompanyMapperTable : getRegnCompanyKeys " + ex );
			
			errorMaster_.insert( "RegnCompanyMapperTable.getRegnCompanyKeys() ex->" + ex );
			
			return -3;
			
		}
		
		//errorMaster_.insert( "Regn Company Mapper" );
		
		
	}
	
	
	/**
	 * 
	 */
	private void getRegnCompanyKeySet( ResultSet rs, Set<CompanyRegnKey> companyKeys )
	{
		
		CompanyRegnKey regnKey = null;
		
		errorMaster_.insert(  "In here" );
		
		try
		{
			while( rs.next( ) )
			{
				regnKey = null;
				regnKey = new CompanyRegnKey();
				regnKey.companyPhoneNo_ =  rs.getString( "regn_rel_key_map" );
				errorMaster_.insert( "regnkey.phone = " + regnKey.companyPhoneNo_ );
				companyKeys.add( regnKey );
			}

		}
		catch( SQLException ex )
		{
			errLogger_.logMsg( "SQLException::RegnCompanyMapperTable.getRegnCompanyKeySet( ) " + ex );
			errorMaster_.insert(  "SQLException::RegnCompanyMapperTable.getRegnCompanyKeySet( ) " + ex );
			// SQLException occurred while populating a Registered Company Set
		}
		catch( Exception ex )
		{
			errLogger_.logMsg( "Exception::RegnCompanyMapperTable.getRegnCompanyKeySet( ) " + ex );
			errorMaster_.insert(  "Exception::RegnCompanyMapperTable.getRegnCompanyKeySet( ) " + ex );
		}
		
	}
	
	
}


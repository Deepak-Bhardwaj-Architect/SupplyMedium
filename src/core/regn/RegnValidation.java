package core.regn;

import core.common.CompanyEmailDomainData;
import core.common.CountryCodeData;
import core.policies.AccountLimiter;
import core.policies.MemberLimiter;
import db.config.CompanyEmailDomainTable;
import db.config.CountryCodeTable;
import db.regn.CompanyRegnTable;
import db.regn.UserProfileTable;
import utils.ErrorLogger;
import utils.CompanyType;
import utils.StringHolder;

/*
 * This class is used to validate the registration and user sign up form fields.
 * It check the given data is valid or not. 
 */

public class RegnValidation
{

	/*
	 * Method : validate( ) 
	 * 
	 * Input : email address, company name
	 * 
	 * Return : boolean
	 * 
	 * Purpose: To check given email address is valid or not
	 */

	public int validate( String email, String companyName )
	{
		
		ErrorLogger errLogger = ErrorLogger.instance( );

		try
		{
			UserProfileTable userProfileTblObj = new UserProfileTable( );
			
			boolean isEmailExist = userProfileTblObj.isEmailExist( email );
			
			userProfileTblObj = null;
			
			if( isEmailExist )
				return 1;  //  Email already exist in supply medium
			
			CompanyEmailDomainTable comEmailDomainTbl = new CompanyEmailDomainTable( );
			
			CompanyEmailDomainData comEmailDomainData = new CompanyEmailDomainData( );
			
			int response = comEmailDomainTbl.find( companyName, comEmailDomainData );
			
			comEmailDomainTbl = null;
			
			if( response !=0 )
				return -2;  // Error occur try again.
			
			
			System.out.println("companyemaildomain="+comEmailDomainData.emailDomain_);
			
			// Email domain name not exist in email domian table
			if( comEmailDomainData.emailDomain_ == null)
			{
				return 0;  // Vaild email id 
			}
			else  // Email domain name exist for given company
			{
				String[] emailSplitArr = email.split( "@" );
				
				if( emailSplitArr.length > 1 )
				{
					// Check whether email domain name and user mail domain names are equal
					if( comEmailDomainData.emailDomain_.equals( emailSplitArr[1] ))  
					{
						return 0; // valid email id
					}
					else
					{
						return 2; // Domain name mismatch
					}
				}
				else 
				{
					return -1; // Invalid email.
				}
				
			}
			
		}

		catch( Exception ex )
		{
			errLogger.logMsg( "Exception::RegnValidation.validate() - " + ex );

			return -2;  // Error occur try again.
		}
	}

	/*
	 * Method : isPhoneNoExist( ) 
	 * 
	 * Input : phone number 
	 * 
	 * Return : boolean
	 * 
	 * Purpose: To check given phone number is already exist in supply medium
	 * db. If phone number already exist return true. Otherwise return false.
	 */

	public boolean isPhoneNoExist( String phoneNo )
	{
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		boolean result = false;

		try
		{
			CompanyRegnTable companyRegnTblObj = new CompanyRegnTable( );
			
			phoneNo = phoneNo.replaceAll( "-", "" );
			
			phoneNo = phoneNo.replaceAll("\\s","");
			
			result = companyRegnTblObj.isPhoneNoExist( phoneNo );
			
			companyRegnTblObj = null;

			return result;
		}

		catch( Exception ex )
		{
			
			errLogger.logMsg( "Exception::RegnValidation.isPhoneNoExist() - " + ex );

			return result;
		}

	}

	/*
	 * Method : isMemberAllowed( ) 
	 * 
	 * Input : company registration key 
	 * 
	 * Return : boolean
	 * 
	 * Purpose: To check whether max user for this company exists. This max user
	 * limit depending to company pricing option. If not return true. (user can
	 * add new members for this company) Otherwise return false.
	 */

	public int isMemberAllowed( CompanyRegnKey companyRegnKey )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		int result = 0;

		try
		{
			AccountLimiter accountLimiter = new MemberLimiter( );
			
			result = accountLimiter.isAllowed( companyRegnKey );
			
			accountLimiter = null;

			return result;
		}

		catch( Exception ex )
		{
			
			errLogger.logMsg( "Exception::RegnValidation.isMemberAllowed() - " + ex );

			return result;
		}

	}

	/*
	 * Method : getCompanyType( ) 
	 * 
	 * Input : company registration key 
	 * 
	 * Return : int value specified company type
	 * 
	 * Purpose: It get the company type for given company registration key. If
	 * company is buyer return 1 If company is Supplier return 2 If company is
	 * both return 3 Other wise return -1 for error
	 */

	public int getCompanyType( CompanyRegnKey companyRegnKey )
	{
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		try
		{
			CompanyRegnTable companyRegnTblObj = new CompanyRegnTable( );
			
			String result = companyRegnTblObj.getCompanyType( companyRegnKey );
			
			companyRegnTblObj = null;

			if( result == null )
			{
				return -1;
			}

			if( result.equals( CompanyType.type.BUYER.getValue( ) ) )
			{
				return 1;
			}
			else if( result.equals( CompanyType.type.SUPPLIER.getValue( ) ) )
			{
				return 2;
			}
			else if( result.equals( CompanyType.type.BOTH.getValue( ) ) )
			{
				return 3;
			}
			else
			{
				return -1;
			}

		}

		catch( Exception ex )
		{

			errLogger.logMsg( "Exception::RegnValidation.getCompanyType() - " + ex );

			return -1;
		}

	}
	
	/*
	 * Method : getCountryCode( ) 
	 * 
	 * Input : country name , StringHolder object that contain countrycode
	 * 
	 * Return : int value result success or failed
	 * 
	 * Purpose:It get the country code for given country name. Then assign the 
	 * country code value to string holder object, so it is copied to caller class.
	 */

	public int getCountryCode( String countryName, StringHolder countryCode )
	{
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		try
		{
			CountryCodeTable countryCodeTbl = new CountryCodeTable( );
			
			CountryCodeData countryCodeData = new CountryCodeData( );
			
			int result = countryCodeTbl.find( countryName, countryCodeData );
			
			countryCodeTbl = null;

			if( result !=0 )
			{
				countryCodeData = null;
				
				return -1;
			}
			else 
			{
				countryCode.value = countryCodeData.countryCode_;
				
				countryCodeData = null;
				
				return 0;
			}

		}

		catch( Exception ex )
		{

			errLogger.logMsg( "Exception::RegnValidation.getCountryCode() - " + ex );

			return -1;
		}

	}

}

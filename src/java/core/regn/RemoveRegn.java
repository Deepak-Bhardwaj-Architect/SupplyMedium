package core.regn;

import db.regn.CompanyRegnTable;
import utils.ErrorLogger;

/*
 * This is one of the implementation class for RegnProcess Interface.
 * This class is used to remove the registered company from supply medium.
 */

public class RemoveRegn implements RegnProcess
{
	/*
	 * Method : clear( ) Input : Registration details Return : int
	 * 
	 * Purpose: From the registration key this method remove the already
	 * registered company from the supply medium DB. If successfully removed
	 * from DB it return 0 Other wise it return error code
	 */

	public int process( RegnData regnDataObj )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		CompanyRegnKey companyRegnKeyObj = new CompanyRegnKey( );

		companyRegnKeyObj.companyPhoneNo_ = regnDataObj.phoneNo_;

		String regnKey = generateKey( companyRegnKeyObj );

		int deleteResult = 0;

		if( regnKey.equals( "" ) )
		{
			String errorMessage = "Error::RegnDelete.process() - Cant create key from " +
					"delete company request ";

			errLogger.logMsg( errorMessage );

			return deleteResult;
			
		}
		
		try
		{
			CompanyRegnTable companyRegTblObj = new CompanyRegnTable( );
			
			deleteResult = companyRegTblObj.delete( regnKey );
			
			companyRegTblObj = null;

			return deleteResult;
			
		}
		catch( Exception ex )
		{
			
			String errorMessage = "Exception::RegnDelete.process() - "+ ex;

			errLogger.logMsg( errorMessage );

			return deleteResult;
		}

	}

	private String generateKey( CompanyRegnKey companyRegnKeyObj )
	{
		String regnKey = companyRegnKeyObj.companyPhoneNo_;

		// More code to be added here to generate key, if there are more than
		// one tuple

		if( regnKey != null )
		{
			return regnKey;
		}

		else
		{
			return "";
		}
	}
}
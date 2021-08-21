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



package core.privilege;


import utils.ErrorLogger;
import utils.ErrorMaster;

/**
 * File:  UserPrivilegesConverter.java 
 *
 * Created on Feb 27, 2013 11:27:28 AM
 */

public class UserPrivilegesConverter
{

	private final int noOfPrivileges = 14;
        private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : encode( ) 
	 * 
	 * Input : UserPrivilegesData object 
	 * 
	 * Return : long
	 * 
	 * Purpose: It is used to convert the userPrivilegesData object to long
	 * format. This long contain the all the privilege in 0 and 1 format. If 1
	 * user have that privilege otherwise don't have that.
	 */

	public long encode( UserPrivilegesData userPrivilegesData )
	{
		int [ ] intArr = new int[noOfPrivileges];
		
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		try
        {
			if( userPrivilegesData.addNewUser_ )
			{
				intArr[0] = 1;
			}
			else
			{
				intArr[0] = 0;
			}
			if( userPrivilegesData.deleteUser_ )
			{
				intArr[1] = 1;
			}
			else
			{
				intArr[1] = 0;
			}
			if( userPrivilegesData.uploadDoc_ )
			{
				intArr[2] = 1;
			}
			else
			{
				intArr[2] = 0;
			}
			if( userPrivilegesData.deleteDoc_ )
			{
				intArr[3] = 1;
			}
			else
			{
				intArr[3] = 0;
			}
			if( userPrivilegesData.addBuySupplier_ )
			{
				intArr[4] = 1;
			}
			else
			{
				intArr[4] = 0;
			}
			if( userPrivilegesData.deleteBuySupplier_ )
			{
				intArr[5] = 1;
			}
			else
			{
				intArr[5] = 0;
			}
			if( userPrivilegesData.accessUserMgmt_ )
			{
				intArr[6] = 1;
			}
			else
			{
				intArr[6] = 0;
			}
			if( userPrivilegesData.postAnnouncement_ )
			{
				intArr[7] = 1;
			}
			else
			{
				intArr[7] = 0;
			}
			if( userPrivilegesData.deleteAnnouncement_ )
			{
				intArr[8] = 1;
			}
			else
			{
				intArr[8] = 0;
			}
			if( userPrivilegesData.rateBuySupplier_ )
			{
				intArr[9] = 1;
			}
			else
			{
				intArr[9] = 0;
			}
			if( userPrivilegesData.createUserGroup_ )
			{
				intArr[10] = 1;
			}
			else
			{
				intArr[10] = 0;
			}
			if( userPrivilegesData.deleteUserGroup_ )
			{
				intArr[11] = 1;
			}
			else
			{
				intArr[11] = 0;
			}
			if( userPrivilegesData.applyThemes_ )
			{
				intArr[12] = 1;
			}
			else
			{
				intArr[12] = 0;
			}
			if( userPrivilegesData.manageFolder_ )
			{
				intArr[13] = 1;
			}
			else
			{
				intArr[13] = 0;
			}
			
        }
		catch(  ArrayIndexOutOfBoundsException ex )
        {
        	errLogger.logMsg( "Exception::UserPrivilegesConverter.encode() - " + ex );
        	
        	return -1;
        }
		catch (Exception ex) 
		{
			errLogger.logMsg( "Exception::UserPrivilegesConverter.encode() - " + ex );
        	
        	return -1;
		}
		
		long result = convertToLong( intArr );

		return result;
	}

	/*
	 * Method 	: decode( ) 
	 * 
	 * Input 	: long , UserPrivilegesData object
	 * 
	 * Return 	: int result- success/failed
	 * 
	 * Purpose: It is used to convert the long to UserPrivileges data and filled 
	 * 			in userPrivilegesData parameter. so it will be copied in caller class.
	 */

	public int decode( long privileges,UserPrivilegesData userPrivilegesData  )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		long [ ] intArr = convertToDigits( privileges );

		try
		{
//			if( privileges == 0 )
//			{
//				for( int j=0; j<noOfPrivileges; j++)
//				{
//					intArr[j] = 0; 
//				}
//			}
			
			if( intArr[0] == 1 )
			{
				userPrivilegesData.addNewUser_ = true;
			}
			else
			{
				userPrivilegesData.addNewUser_ = false;
			}
			if( intArr[1] == 1 )
			{
				userPrivilegesData.deleteUser_ = true;
			}
			else
			{
				userPrivilegesData.deleteUser_ = false;
			}
			if( intArr[2] == 1 )
			{
				userPrivilegesData.uploadDoc_ = true;
			}
			else
			{
				userPrivilegesData.uploadDoc_ = false;
			}
			if( intArr[3] == 1 )
			{
				userPrivilegesData.deleteDoc_ = true;
			}
			else
			{
				userPrivilegesData.deleteDoc_ = false;
			}
			if( intArr[4] == 1 )
			{
				userPrivilegesData.addBuySupplier_ = true;
			}
			else
			{
				userPrivilegesData.addBuySupplier_ = false;
			}
			if( intArr[5] == 1 )
			{
				userPrivilegesData.deleteBuySupplier_ = true;
			}
			else
			{
				userPrivilegesData.deleteBuySupplier_ = false;
			}
			if( intArr[6] == 1 )
			{
				userPrivilegesData.accessUserMgmt_ = true;
			}
			else
			{
				userPrivilegesData.accessUserMgmt_ = false;
			}
			if( intArr[7] == 1 )
			{
				userPrivilegesData.postAnnouncement_ = true;
			}
			else
			{
				userPrivilegesData.postAnnouncement_ = false;
			}
			if( intArr[8] == 1 )
			{
				userPrivilegesData.deleteAnnouncement_ = true;
			}
			else
			{
				userPrivilegesData.deleteAnnouncement_ = false;
			}
			if( intArr[9] == 1 )
			{
				userPrivilegesData.rateBuySupplier_ = true;
			}
			else
			{
				userPrivilegesData.rateBuySupplier_ = false;
			}
			if( intArr[10] == 1 )
			{
				userPrivilegesData.createUserGroup_ = true;
			}
			else
			{
				userPrivilegesData.createUserGroup_ = false;
			}
			if( intArr[11] == 1 )
			{
				userPrivilegesData.deleteUserGroup_ = true;
			}
			else
			{
				userPrivilegesData.deleteUserGroup_ = false;
			}
			if( intArr[12] == 1 )
			{
				userPrivilegesData.applyThemes_ = true;
			}
			else 
			{
				userPrivilegesData.applyThemes_ = false;
			}
			if( intArr[13] == 1 )
			{
				userPrivilegesData.manageFolder_ = true;
			}
			else
			{
				userPrivilegesData.manageFolder_ = false;
			}

		}
		catch(  ArrayIndexOutOfBoundsException ex )
        {
        	errLogger.logMsg( "Exception::UserPrivilegesConverter.decode() - " + ex );
        	
        	return -1;
        }
		catch (Exception ex) 
		{
			errLogger.logMsg( "Exception::UserPrivilegesConverter.decode() - " + ex );
        	
        	return -1;
		}
		return 0;
	}

	/*
	 * Method : convertToDigits( ) 
	 * 
	 * Input : long 
	 * 
	 * Return : array of int value
	 * 
	 * Purpose: It is used to convert the long value to array of int value
	 */

	private long [ ] convertToDigits( long value )
	{
		long [ ] intArr = new long[noOfPrivileges];

		String a = String.valueOf( value );
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		if( a.length( ) < 14 )
		{
			a = String.format( "%014d", value );
			

			errorMaster_.insert( "String Privileges after appending - " + a );
		}
		
		char[ ] priCharArr = a.toCharArray( );
		
		for( int i=0; i<14; i++ )
		{
			//intArr[i] = Integer.valueOf( priCharArr[i] ) ;
			
			intArr[i] = new Integer( ""+priCharArr[i] );
			
			//errorMaster_.insert( "Value at index "+ i + "is : " + intArr[i]  );
		}
		
		
		
//		
//		int i = 1;
//
//		int max = intArr.length;
//		
//		while( value > 0 )
//		{
//			intArr[max-i] = (int)( value % 10 );
//			value /= 10;
//			i++;
//		}

		return intArr;
	}

	/*
	 * Method : convertToLong( )
	 * 
	 * Input : array of int
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the array of int values into long value
	 */

	private long convertToLong( int [ ] intArr )
	{
		String digitsStr ="";
		
		for( int i : intArr )
        {
	        digitsStr = digitsStr+i;
        }

		long value = Long.parseLong( digitsStr );

		return value;

	}

}

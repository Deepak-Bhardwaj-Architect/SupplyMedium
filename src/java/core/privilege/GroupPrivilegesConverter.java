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
 * File: GroupPrivilegesConverter.java
 * 
 * Created on Mar 6, 2013 3:55:51 PM
 */
public class GroupPrivilegesConverter
{
	private final int noOfPrivileges = 14;
        
        private static ErrorMaster errorMaster_ = null;

        public GroupPrivilegesConverter()
        {
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
        }

	/*
	 * Method : encode( )
	 * 
	 * Input : UsergroupPrivilegesData object
	 * 
	 * Return : long
	 * 
	 * Purpose: It is used to convert the UsergroupPrivilegesData object to long
	 * format. This long contain the all the privilege in 0 and 1 format. If 1
	 * user have that privilege otherwise don't have that.
	 */

	public long encode( UsergroupPrivilegesData privilegesData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		int [ ] intArr = new int[noOfPrivileges];
		
		try
        {
			if( privilegesData.addNewUser_ )
			{
				intArr[0] = 1;
			}
			else
			{
				intArr[0] = 0;
			}
			
			if( privilegesData.deleteUser_ )
			{
				intArr[1] = 1;
			}
			else
			{
				intArr[1] = 0;
			}
			
			if( privilegesData.uploadDoc_ )
			{
				intArr[2] = 1;
			}
			else
			{
				intArr[2] = 0;
			}
			
			if( privilegesData.deleteDoc_ )
			{
				intArr[3] = 1;
			}
			else
			{
				intArr[3] = 0;
			}
			
			if( privilegesData.addBuySupplier_ )
			{
				intArr[4] = 1;
			}
			else
			{
				intArr[4] = 0;
			}
			
			if( privilegesData.deleteBuySupplier_ )
			{
				intArr[5] = 1;
			}
			else
			{
				intArr[5] = 0;
			}
			
			if( privilegesData.accessUserMgmt_ )
			{
				intArr[6] = 1;
			}
			else
			{
				intArr[6] = 0;
			}
			
			if( privilegesData.postAnnouncement_ )
			{
				intArr[7] = 1;
			}
			else
			{
				intArr[7] = 0;
			}
			
			if( privilegesData.deleteAnnouncement_ )
			{
				intArr[8] = 1;
			}
			else
			{
				intArr[8] = 0;
			}
			
			if( privilegesData.rateBuySupplier_ )
			{
				intArr[9] = 1;
			}
			else
			{
				intArr[9] = 0;
			}
			
			if( privilegesData.createUserGroup_ )
			{
				intArr[10] = 1;
			}
			else
			{
				intArr[10] = 0;
			}
			
			if( privilegesData.deleteUserGroup_ )
			{
				intArr[11] = 1;
			}
			else
			{
				intArr[11] = 0;
			}
			
			if( privilegesData.applyThemes_ )
			{
				intArr[12] = 1;
			}
			else
			{
				intArr[12] = 0;
			}
			
			if( privilegesData.manageFolder_ )
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
        	errLogger.logMsg( "Exception::GroupPrivilegesConverter.encode() - " + ex );
        	
        	return -1;
        }
		catch (Exception ex) 
		{
			errLogger.logMsg( "Exception::GroupPrivilegesConverter.encode() - " + ex );
        	
        	return -1;
		}

		long result = convertToLong( intArr );
		
		return result;
	}

	/*
	 * Method : decode( )
	 * 
	 * Input : long , UsergroupPrivilegesData object
	 * 
	 * Return : int result- success/failed
	 * 
	 * Purpose: It is used to convert the long to GroupPrivileges data and filled
	 * in UsergroupPrivilegesData parameter. so it will be copied in caller
	 * class.
	 */

	public int decode( long privileges, UsergroupPrivilegesData privilegesData )
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
				privilegesData.addNewUser_ = true;
			}
			else
			{
				privilegesData.addNewUser_ = false;
			}
			
			if( intArr[1] == 1 )
			{
				privilegesData.deleteUser_ = true;
			}
			else
			{
				privilegesData.deleteUser_ = false;
			}
			
			if( intArr[2] == 1 )
			{
				privilegesData.uploadDoc_ = true;
			}
			else
			{
				privilegesData.uploadDoc_ = false;
			}
			
			if( intArr[3] == 1 )
			{
				privilegesData.deleteDoc_ = true;
			}
			else
			{
				privilegesData.deleteDoc_ = false;
			}
			
			if( intArr[4] == 1 )
			{
				privilegesData.addBuySupplier_ = true;
			}
			else
			{
				privilegesData.addBuySupplier_ = false;
			}
			
			if( intArr[5] == 1 )
			{
				privilegesData.deleteBuySupplier_ = true;
			}
			else
			{
				privilegesData.deleteBuySupplier_ = false;
			}
			
			if( intArr[6] == 1 )
			{
				privilegesData.accessUserMgmt_ = true;
			}
			else
			{
				privilegesData.accessUserMgmt_ = false;
			}
			
			if( intArr[7] == 1 )
			{
				privilegesData.postAnnouncement_ = true;
			}
			else
			{
				privilegesData.postAnnouncement_ = false;
			}
			
			if( intArr[8] == 1 )
			{
				privilegesData.deleteAnnouncement_ = true;
			}
			else
			{
				privilegesData.deleteAnnouncement_ = false;
			}
			
			if( intArr[9] == 1 )
			{
				privilegesData.rateBuySupplier_ = true;
			}
			else
			{
				privilegesData.rateBuySupplier_ = false;
			}
			
			if( intArr[10] == 1 )
			{
				privilegesData.createUserGroup_ = true;
			}
			else
			{
				privilegesData.createUserGroup_ = false;
			}
			
			if( intArr[11] == 1 )
			{
				privilegesData.deleteUserGroup_ = true;
			}
			else
			{
				privilegesData.deleteUserGroup_ = false;
			}
			
			if( intArr[12] == 1 )
			{
				privilegesData.applyThemes_ = true;
			}
			else
			{
				privilegesData.applyThemes_ = false;
			}
			
			if( intArr[13] == 1 )
			{
				privilegesData.manageFolder_ = true;
			}
			else
			{
				privilegesData.manageFolder_ = false;
			}
		
		}
		catch(  ArrayIndexOutOfBoundsException ex )
        {
        	errLogger.logMsg( "Exception::GroupPrivilegesConverter.decode() - " + ex );
        	
        	return -1;
        }
		catch ( Exception ex ) 
		{
			errLogger.logMsg( "Exception::GroupPrivilegesConverter.decode() - " + ex );
        	
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
	 * Return : long
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

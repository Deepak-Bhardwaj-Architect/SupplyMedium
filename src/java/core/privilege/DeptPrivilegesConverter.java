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
 * File: DeptPrivilegesConverter.java
 * 
 * Created on Feb 26, 2013 11:27:28 AM
 */

/*
 * This class is used to convert the privileges from DeptPrivilegesData to int
 * and vice versa.
 */
public class DeptPrivilegesConverter
{
	private final int noOfPrivileges = 6;
        
        private static ErrorMaster errorMaster_ = null;

        public DeptPrivilegesConverter()
        {
            if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
        }

	/*
	 * Method : encode( )
	 * 
	 * Input : DeptPrivilegesData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the deptPrivilegesData object to int
	 * format. This int contain the all the privilege in 0 and 1 format.If 1
	 * user have that privilege otherwise don't have that privilege.
	 */

	public int encode( DeptPrivilegesData deptPrivilegesData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		int result = 0;
		
		int [ ] intArr = new int[noOfPrivileges];
		
		try
        {
			if( deptPrivilegesData.addFolder_ )
			{
				intArr[0] = 1;
			}
			else
			{
				intArr[0] = 0;
			}
			if( deptPrivilegesData.deleteFolder_ )
			{
				intArr[1] = 1;
			}
			else
			{
				intArr[1] = 0;
			}
			if( deptPrivilegesData.addFile_ )
			{
				intArr[2] = 1;
			}
			else
			{
				intArr[2] = 0;
			}
			if( deptPrivilegesData.deleteFile_ )
			{
				intArr[3] = 1;
			}
			else
			{
				intArr[3] = 0;
			}
			if( deptPrivilegesData.postAnnouncement_ )
			{
				intArr[4] = 1;
			}
			else
			{
				intArr[4] = 0;
			}
			if( deptPrivilegesData.manageFolder_ )
			{
				intArr[5] = 1;
			}
			else
			{
				intArr[5] = 0;
			}
			

        }
        catch(  ArrayIndexOutOfBoundsException ex )
        {
        	errLogger.logMsg( "Exception::DeptPrivilegesConverter.encode() - " + ex );
        	
        	return -1;
        }
		catch (Exception ex) 
		{
			errLogger.logMsg( "Exception::DeptPrivilegesConverter.encode() - " + ex );
        	
			return -1;
		}
		
	    result = convertToInt( intArr );

		return result;
	}

	/*
	 * Method : decode( )
	 * 
	 * Input : int , DeptPrivilegesData object
	 * 
	 * Return : int result- success/failed
	 * 
	 * Purpose: It is used to convert the int to DeptPrivilegesData object and
	 * filled in deptPrivilegesData parameter. so it will be copied in caller
	 * class.
	 */

	public int decode( int privileges, DeptPrivilegesData deptPrivilegesData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );

		int [ ] intArr = convertToDigits( privileges );
		
		for( int i : intArr )
        {
	        errorMaster_.insert("arr element="+i);
        }

		try
		{
			if( intArr[0] == 1 )
			{
				deptPrivilegesData.addFolder_ = true;
			}
			else
			{
				deptPrivilegesData.addFolder_ = false;
			}
			if( intArr[1] == 1 )
			{
				deptPrivilegesData.deleteFolder_ = true;
			}
			else
			{
				deptPrivilegesData.deleteFolder_ = false;
			}
			if( intArr[2] == 1 )
			{
				deptPrivilegesData.addFile_ = true;
			}
			else
			{
				deptPrivilegesData.addFile_ = false;
			}
			if( intArr[3] == 1 )
			{
				deptPrivilegesData.deleteFile_ = true;
			}
			else
			{
				deptPrivilegesData.deleteFile_ = false;
			}
			if( intArr[4] == 1 )
			{
				deptPrivilegesData.postAnnouncement_ = true;
			}
			else
			{
				deptPrivilegesData.postAnnouncement_ = false;
			}
			if( intArr[5] == 1 )
			{
				deptPrivilegesData.manageFolder_ = true;
			}
			else
			{
				deptPrivilegesData.manageFolder_ = false;
			}
			
		}
		
		catch(  ArrayIndexOutOfBoundsException ex )
        {
        	errLogger.logMsg( "Exception::DeptPrivilegesConverter.decode() - " + ex );
        	
        	return -1;
        }
		catch (Exception ex) 
		{
			errLogger.logMsg( "Exception::DeptPrivilegesConverter.decode() - " + ex );
        	
			return -1;
		}
		
		return 0;
	}

	/*
	 * Method : convertToDigits( )
	 * 
	 * Input : int
	 * 
	 * Return : array of int value
	 * 
	 * Purpose: It is used to convert the int value to array of int value
	 */

	private int [ ] convertToDigits( int value )
	{
		int [ ] intArr = new int[noOfPrivileges];

//		int i = noOfPrivileges-1;
//		
//		while( value > 0 )
//		{
//			intArr[i] = ( value % 10 );
//
//			value /= 10;
//			
//			i--;
//		}
//		
		//long [ ] intArr = new long[noOfPrivileges];

		String a = String.valueOf( value );
		
		if( a.length( ) < 6 )
		{
			a = String.format( "%06d", value );
			
			errorMaster_.insert( "String Privileges after appending - " + a );
		}
		
		char[ ] priCharArr = a.toCharArray( );
		
		for( int i=0; i<6; i++ )
		{
			//intArr[i] = Integer.valueOf( priCharArr[i] ) ;
			
			intArr[i] = new Integer( ""+priCharArr[i] );
			
			//errorMaster_.insert( "Value at index "+ i + "is : " + intArr[i]  );
		}

		return intArr;
	}

	/*
	 * Method : convertToInt( )
	 * 
	 * Input : array of int
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the array of int values into int value
	 */

	private int convertToInt( int [ ] intArr )
	{
		String digitsStr ="";
		
		for( int i : intArr )
        {
	        digitsStr = digitsStr+i;
        }

		int value = Integer.parseInt( digitsStr );

		return value;

	}
}

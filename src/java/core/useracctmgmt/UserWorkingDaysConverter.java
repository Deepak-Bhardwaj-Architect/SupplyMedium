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

package core.useracctmgmt;

import utils.ErrorLogger;

/**
 * File:  UserWorkingDaysConverter.java 
 *
 * Created on Apr 22, 2013 4:59:38 PM
 */

/*
 * This class is used to convert the working days from WorkingDaysData to int
 * and vice versa.
 */

public class UserWorkingDaysConverter
{
	private final int noOfWorkingDays = 7;

	/*
	 * Method : encode( )
	 * 
	 * Input : WorkingDaysData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It is used to convert the WorkingDaysData object to int
	 * format. This int contain the all the working days in 0 and 1 format.If '1',
	 * then the day is working day; else, it is non working day
	 */

	public int encode( WorkingDaysData workingDaysData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		int result = 0;
		
		int [ ] intArr = new int[noOfWorkingDays];
		
		try
        {
			if( workingDaysData.sunWorkingFlag_ )
			{
				intArr[0] = 1;
			}
			else
			{
				intArr[0] = 0;
			}
			
			if( workingDaysData.monWorkingFlag_ )
			{
				intArr[1] = 1;
			}
			else 
			{
				intArr[1] = 0;
			}
			
			if( workingDaysData.tueWorkingFlag_ )
			{
				intArr[2] = 1;
			}
			else
			{
				intArr[2] = 0;
			}
			
			if( workingDaysData.wedWorkingFlag_ )
			{
				intArr[3] = 1;
			}
			else
			{
				intArr[3] = 0;
			}
			
			if( workingDaysData.thuWorkingFlag_ )
			{
				intArr[4] = 1;
			}
			else
			{
				intArr[4] = 0;
			}
			
			if( workingDaysData.friWorkingFlag_ )
			{
				intArr[5] = 1;
			}
			else
			{
				intArr[5] = 0;
			}
			
			if( workingDaysData.satWorkingFlag_ )
			{
				intArr[6] = 1;
			}
			else
			{
				intArr[6] = 0;
			}
			
        }
		
        catch(  ArrayIndexOutOfBoundsException ex )
        {
        	errLogger.logMsg( "Exception::UserWorkingDaysConverter.encode() - " + ex );
        	
        	return -1;
        }
		catch (Exception ex) 
		{
			errLogger.logMsg( "Exception::UserWorkingDaysConverter.encode() - " + ex );
        	
			return -1;
		}
		
	    result = convertToInt( intArr );

		return result;
	}

	/*
	 * Method : decode( )
	 * 
	 * Input : int , WorkingDaysData object
	 * 
	 * Return : int result- success/failed
	 * 
	 * Purpose: It is used to convert the int to WorkingDaysData object 
	 * 
	 */

	public int decode( int workingDays, WorkingDaysData workingDaysData )
	{
		ErrorLogger errLogger = ErrorLogger.instance( );
		
		int [ ] intArr = convertToDigits( workingDays );

		try
		{
			int length = (int)(Math.log10(workingDays)+1);
			
			if( length < noOfWorkingDays )
			{
				int fillerCount = noOfWorkingDays - length;
				
				for( int i = 0; i < fillerCount; i++ )
				{
					intArr[i] = 0;
				}
			}
			
			if( intArr[0] == 1 )
			{
				workingDaysData.sunWorkingFlag_ = true;
			}
			else
			{
				workingDaysData.sunWorkingFlag_ = false;
			}
			
			if( intArr[1] == 1 )
			{
				workingDaysData.monWorkingFlag_ = true;
			}
			else
			{
				workingDaysData.monWorkingFlag_ = false;
			}
			
			if( intArr[2] == 1 )
			{
				workingDaysData.tueWorkingFlag_ = true;
			}
			else
			{
				workingDaysData.tueWorkingFlag_ = false;
			}
			
			if( intArr[3] == 1 )
			{
				workingDaysData.wedWorkingFlag_ = true;
			}
			else
			{
				workingDaysData.wedWorkingFlag_ = false;
			}
			
			if( intArr[4] == 1 )
			{
				workingDaysData.thuWorkingFlag_ = true;
			}
			else
			{
				workingDaysData.thuWorkingFlag_ = false;
			}
			
			if( intArr[5] == 1 )
			{
				workingDaysData.friWorkingFlag_ = true;
			}
			else
			{
				workingDaysData.friWorkingFlag_ = false;
			}
			
			if( intArr[6] == 1 )
			{
				workingDaysData.satWorkingFlag_ = true;
			}
			else 
			{
				workingDaysData.satWorkingFlag_ = false;
			}
			
		}
		
		catch(  ArrayIndexOutOfBoundsException ex )
        {
        	errLogger.logMsg( "Exception::UserWorkingDaysConverter.decode() - " + ex );
        	
        	return -1;
        }
		catch (Exception ex) 
		{
			errLogger.logMsg( "Exception::UserWorkingDaysConverter.decode() - " + ex );
        	
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
		int [ ] intArr = new int[noOfWorkingDays];

		int i = noOfWorkingDays-1;
		
		while( value > 0 )
		{
			intArr[i] = ( value % 10 );

			value /= 10;
			
			i--;
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
		String digitsStr = "";
		
		for( int i : intArr )
        {
	        digitsStr = digitsStr+i;
        }

		int value = Integer.parseInt( digitsStr );

		return value;
	}
	
}

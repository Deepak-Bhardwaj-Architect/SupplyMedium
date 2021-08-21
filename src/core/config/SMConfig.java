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
package core.config;

import utils.ErrorLogger;
import db.config.SMConfigTable;

/**
 * File:  SMConfig.java 
 *
 * Created on Jul 11, 2013 8:00:01 PM
 */

/*
 * Class: SMConfig
 * 
 * Purpose: This class is used to get the SM config
 */

public class SMConfig
{
	
	/*Constructor*/
	
	public SMConfig( )
	{
		
	}
	
	/*
	 * Method: getSMConfigs
	 * 
	 * Input: SMConfigData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the SMConfigs from the SMConfigTable
	 */
	
	public int getSMConfigs( SMConfigData smConfigData )
	{
		SMConfigTable smConfigTable = new SMConfigTable( );
		
		int result = smConfigTable.getSMConfig( smConfigData );
		
		smConfigTable = null;
		
		if( result != 0 )
		{
			ErrorLogger errorLogger = ErrorLogger.instance( );
			
			errorLogger.logMsg( "Error::SMConfig.getSMConfigs( ) - Unable to get SM configs from SM Config table" );
			
			return 181;
		}
		
		return 180;
	}
}

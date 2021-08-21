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
package ctrl.config;

import core.config.SMConfig;
import core.config.SMConfigData;

/**
 * File:  SMConfigController.java 
 *
 * Created on Jul 11, 2013 8:09:48 PM
 */

/*
 * Class: SMConfigController
 * 
 *  Purpose: This method controls the fetching operation to fetch all the SMConfigs
 *  
 */

public class SMConfigController
{
	
	/*Constructor*/
	
	public SMConfigController( )
	{
		
	}
	
	/*
	 * Method: getSMConfigs
	 * 
	 * Input: SMConfigData obj
	 * 
	 * Return: int
	 * 
	 * Purpose: This method fetches the data and fills the SMConfigData
	 */
	
	public int getSMConfigs( SMConfigData smConfigData )
	{
		SMConfig smConfig = new SMConfig( );
		
		int result = smConfig.getSMConfigs( smConfigData );
		
		return result;
	}
	
}

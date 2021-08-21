/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensor's, if any.
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

package utils;

import java.io.File;

import org.apache.commons.io.FileUtils;

import core.regn.CompanyRegnKey;
import core.regn.RegnData;
import db.config.PricingOptionTable;
import db.regn.CompanyRegnTable;

/**
 * File:  DiskSpaceChecker.java 
 *
 * Created on Jul 16, 2013 1:06:18 PM
 */

/*This class is used to calculate the available disk space for given regn key*/

public class DiskSpaceChecker
{
	public double totalSpace_;
	public double spaceOccupied_;
	public double spaceRemaining_;
	
	/*Constructor*/
	
	public DiskSpaceChecker( CompanyRegnKey regnKey )
	{
		totalSpace_ 	= 0.0f;
		spaceOccupied_ 	= 0.0f;
		spaceRemaining_ = 0.0f;
		
		init( regnKey );
	}
	
	private void init( CompanyRegnKey regnKey )
	{
		checkSpace( regnKey );
	}
	
	/*
	 * Method: 	checkSpaceÊ
	 * 
	 * Input:	 	CompanyRegnKey obj
	 * 
	 * Return: 	int
	 * 
	 * Purpose: 	To calculate totalSpace_, spaceOccupied_ and spaceRemaining_
	 */
	
	private void checkSpace( CompanyRegnKey regnKey )
	{
		CompanyRegnTable regnTable = new CompanyRegnTable( );
		
		RegnData regnData = new RegnData( );
		
		int result = regnTable.getCompany( regnKey, regnData );
		
		if( result != 0 )
		{
			ErrorLogger.instance( ).logMsg( "Error::DiskSpaceChecker.checkSpace( ) - Unable to get company details" );
			
			return ;
		}
		
		PricingOptionTable pricingOptionTable = new PricingOptionTable( );
		
		this.totalSpace_ = pricingOptionTable.getMaxSpace( regnData.pricingOption_ );;
	
		PathBuilder pathBuilder = new PathBuilder( );
			
		StringHolder path = new StringHolder( );
		
		path.value = "";
		
		result = pathBuilder.getCompanyDirPath( regnData.companyName_, regnKey.toString( ), path );
		
		if( result != 0 )
		{
			ErrorLogger.instance( ).logMsg( "Error::DiskSpaceChecker.checkSpace( ) - Unable to get company dir path" );
			
			return;
		}
				
		try
        {
			double spaceOccupied = 0.0f;

			File f = new File( path.value );

			if( f.exists( ) )
			{

				double spaceOccupiedBytes = FileUtils
				        .sizeOfDirectory( new File( path.value ) );

				System.out
				        .println( "****************************************** space occupied="
				                + spaceOccupiedBytes );

				if( spaceOccupiedBytes > 0 )
				{
					spaceOccupied = result;

					spaceOccupied = spaceOccupiedBytes / ( 1024.0 * 1024.0 );
				}
			}
			
			
			this.spaceOccupied_ = spaceOccupied;
			
			this.spaceRemaining_ = this.totalSpace_ - this.spaceOccupied_;
        }
        catch( Exception e )
        {
        	ErrorLogger.instance( ).logMsg( "Error::DiskSpaceChecker.checkSpace( ) - " + e );
        	
        	this.spaceOccupied_ = -1;
        	
        	this.spaceRemaining_ = -1;
			
			return;
        }
	}
}

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
package db.advertisement;

import java.sql.Date;
import utils.ErrorMaster;

/**
 * File:  AdStatTableRecord.java 
 *
 * Created on Oct 7, 2013 6:25:07 PM
 */
public class AdStatRecord
{

	public Date date_;
	
	public long adId_;

	public long adStatId_;

	public int count_;
	
	
	/*
	 * Method : AdStatRecord -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public AdStatRecord()
	{
		adId_			 = -1;
		adStatId_		 =-1;
		
	}	
	
	/*
	 * Method : show( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
           ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "adRelId =" + adId_ );
		
		errorMaster_.insert( "adStatId  =" + adStatId_ );
		
		errorMaster_.insert( "date  =" + date_ );
		
		errorMaster_.insert( "count	=" + count_ );
		
				
	}
	
	/*
	 * Method : clear( ) 
	 * 
	 * Input : None 
	 * 
	 * Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */
	
	public void clear( )
	{
		adId_ =-1;
		
		adStatId_=-1;
		
		date_	  =null;
		
		count_	  =0;
		
	}
	
	

}

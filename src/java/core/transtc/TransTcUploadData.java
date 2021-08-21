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
package core.transtc;

import java.sql.Timestamp;



import core.regn.CompanyRegnKey;
import utils.ErrorMaster;

/**
 * File:  TransTcUploadData.java 
 *
 * Created on Oct 19, 2013 11:37:11 AM
 */
public class TransTcUploadData
{

	public long transTcId_;
	
	public CompanyRegnKey regnKey_;
	
	public String transType_;
	
	public Timestamp createdTimestamp_;

	public String tc_;
        
        private static ErrorMaster errorMaster_ = null;
	
	
	
	/*
	 * Method : TransTcUploadData -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransTcUploadData()
	{
		transTcId_=-1;
		regnKey_=new CompanyRegnKey( );
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
	public void show()
	{
            

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		System.out.println( "transTcId 		=" +transTcId_ );
		
		System.out.println( " tc 		="+tc_);
		
		System.out.println( "regnKey 			="+regnKey_);
		
		System.out.println( "transType			="+transType_);
		
		System.out.println( "createdTimeStamp	="+createdTimestamp_);
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
	
	public void clear()
	{
		tc_	=null;
		transTcId_=-1;
		regnKey_=null;
		transType_=null;
		createdTimestamp_=null;
		
	}

}

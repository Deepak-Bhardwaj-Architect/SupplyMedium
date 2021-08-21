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

import java.util.List;


import core.regn.RegnData;


import db.regn.CompanyRegnTable;

import db.transtc.TransTcUploadTable;
import utils.ErrorMaster;

/**
 * File:  TransTcUploader.java 
 *
 * Created on Oct 19, 2013 11:36:06 AM
 */
public class TransTcUploader
{
	private static ErrorMaster errorMaster_ = null;


	/*
	 * Method : TransTcUploader -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TransTcUploader( )
	{
		if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}

	
	
	
	/*
	 * Method : update
	 * 
	 * Input  : transTcUploadData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: Used to update the Transaction term and condition for given regnkey key. 
	 */
	
    public int update( TransTcUploadData transTcUploadData )
    {
    	
    	TransTcUploadTable transTcUploadTable = new TransTcUploadTable( );
		
		int result = transTcUploadTable.isExist( transTcUploadData );
	
		
		if(result==1)  //already Exist
		{
			result=transTcUploadTable.update( transTcUploadData );
		}
		
		else 
		{
			result=transTcUploadTable.insert(transTcUploadData);
			errorMaster_.insert( "TransTc inserted successfully");
		}
		
		transTcUploadTable = null;
		
		if( result == 0 )
			return 17000;  // TransactionTc successfully updated
		else
			return 17003;  // TransactionTc updated failed 
		
	    
    }
    
    
    
    
    /*
	 * Method : get
	 * 
	 * Input  : TransTcUploadData, list of TransTcUploadData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: It get the Transaction term and condition  for given regnkey. And copied to translists parameter.
	 * So it is available for caller classes
	 */
	
    public int get( TransTcUploadData tcData, List<TransTcUploadData> translists )
    {
    	
    	TransTcUploadTable transTcUploadTable = new TransTcUploadTable( );

		int result = transTcUploadTable.find( tcData, translists );
	
		transTcUploadTable = null;
		
		
		
		if( result == 0 )
			return 17010;

		else
			
			return 17011;
		
	    
    }
    
     
}



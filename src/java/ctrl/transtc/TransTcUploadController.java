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
package ctrl.transtc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import core.transtc.TransTcUploadData;
import core.transtc.TransTcUploader;


/**
 * File:  TransTcUploadController.java 
 *
 * Created on Oct 19, 2013 11:35:38 AM
 */
public class TransTcUploadController
{
	/*
	 * Method : TransTcUploadController -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	public TransTcUploadController()
	{		
		
	}
	
	
	/*
	 * Method : processTC
	 * 
	 * Input  : HttpServletRequest object, list of TransTcUploadData object
	 * 
	 * Return : int success/fail
	 * 
	 * Purpose: This method is used to control the TransactionUpload. It do the following operation in TransactionUpload
	 * 
	 * 1. update the TransactionTerm and condition
	 * 3. Get the TransactionTerm and condition for given regn key
	 * 
	 */

    public int processTC( HttpServletRequest request, List<TransTcUploadData> translists )
    {
    	
    	int result = 0;
   	 // Converting request object to TransTcUploadData object
   	
   	TransTcUploadConverter converter = new TransTcUploadConverter( );
   	
   	TransTcUploadData transTcUploadData = new TransTcUploadData( );
   	
   	result = converter.convert( request, transTcUploadData );
      
   	converter = null;
   	
   	if( result != 0 )
   		return 17001;  // Parser error.
   	
   	String requestType = request.getParameter( "RequestType" );
   	
   	if( requestType.equals( "UpdateTransTc" ))
   	{
   		result = update( transTcUploadData );
   	}
   	else if( requestType.equals( "FetchTransTc" ))
   	{
   		result = get( transTcUploadData,translists );
   	}
   
   	
   	else
   	{
   		result = 17002;  // can't find appropriate request type
   	}
   	
   	return result;

    }

    /*
     * Method : get
     * 
     * Input  :TransTcUploadData object, list of TransTcUploadData object
     * 
     * Return : int success/fail
     * 
     * Purpose: It get the Transaction term and condition for given regnkey. And copied to notifications parameter.
     * So it is available for caller classes
     */
	
    public int get( TransTcUploadData tcData, List<TransTcUploadData> translists )
    {
    	
    	int result = 0;
    	
    	TransTcUploader transTcUploader = new TransTcUploader( );
    	
    	result = transTcUploader.get( tcData, translists );
    	
    	transTcUploader = null;
    	
    	return result;
    	
    }


    /*
     * Method : update
     * 
     * Input  : TransTcUploadData object
     * 
     * Return : int success/fail
     * 
     * Purpose: Used to update the TransTcterm and condition for given regnkey. 
     */
    public int update( TransTcUploadData transTcUploadData )
    {
	    
    	int result = 0;
    	
    	TransTcUploader transTcUploader = new TransTcUploader( );
    	
    	result = transTcUploader.update( transTcUploadData );
    	
    	transTcUploader = null;
    	
    	return result;
    }

}

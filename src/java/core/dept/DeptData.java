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

package core.dept;

import java.util.ArrayList;
import java.util.List;
import utils.ErrorMaster;



/**
 * File:  DeptData.java 
 *
 * Created on Feb 27, 2013 11:27:28 AM
 */

/* 
 * This data class contain department data is related 
 * departments table in database.
 */

public class DeptData
{
	public DeptKey deptKey_;
	//public CompanyRegnKey companyRegnKey_;
	public String  deptName_;
	public List<DeptFolderData> deptFolderDataList_;
        private static ErrorMaster errorMaster_ = null;



	/*Constructor*/
	
	public DeptData( )
	{
		deptFolderDataList_ = new ArrayList<DeptFolderData>( );
                if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
	}
	
	/*
	 * Method : show( ) Input : None Return : None
	 * 
	 * Purpose: It is used to print the all class variable values in console
	 */

	public void show( )
	{
		errorMaster_.insert( "deptKey_				= " + deptKey_.companyRegnKey_.companyPhoneNo_+":"+deptKey_.deptName_ );
		//errorMaster_.insert( "companyRegnKey_		= " + companyRegnKey_ );
		errorMaster_.insert( "deptName_				= " + deptName_ );
		errorMaster_.insert( "deptFolderDataList_ size = " + deptFolderDataList_.size( ) );
	}


	   
    @Override
    public String toString( )
    {
        return deptKey_.companyRegnKey_.companyPhoneNo_+":"+deptKey_.deptName_;
    } 
	
	
	/*
	 * Method : clear( ) Input : None Return : None
	 * 
	 * Purpose: To reset the class variables.
	 */

	
	public void clear( )
	{
		deptKey_ 		= null;
		deptName_ 		= null;
		//companyRegnKey_ = null;
		deptFolderDataList_ = null;
	}
}

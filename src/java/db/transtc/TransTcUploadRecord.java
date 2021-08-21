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
package db.transtc;

import java.sql.Timestamp;
import utils.ErrorMaster;


/**
 * File:  TransTcUploadRecord.java 
 *
 * Created on Oct 19, 2013 3:10:45 PM
 */
public class TransTcUploadRecord
{
	public String regnKey_;
	
	public String transType_;
	
	public long transTcId_;
	
	public Timestamp createdTimestamp_;
	
	public String tc_;
	
	
	public TransTcUploadRecord()
	{
		transTcId_=-1;
	}
	
	
	public void show()
	{
           ErrorMaster errorMaster_ = null;

if( errorMaster_ == null ) errorMaster_ = new ErrorMaster( );
		errorMaster_.insert( "transTcId 		=" +transTcId_ );
		
		errorMaster_.insert( " tc 		="+tc_);
		
		errorMaster_.insert( "regnKey 			="+regnKey_);
		
		errorMaster_.insert( "transType			="+transType_);
		
		errorMaster_.insert( "createdTimeStamp	="+createdTimestamp_);
		
		
	}
	
	public void clear()
	{
		tc_	=null;
		transTcId_=-1;
		regnKey_=null;
		transType_=null;
		createdTimestamp_=null;
	}

}

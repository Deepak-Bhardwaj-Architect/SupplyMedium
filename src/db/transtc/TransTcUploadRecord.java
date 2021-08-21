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
		System.out.println( "transTcId 		=" +transTcId_ );
		
		System.out.println( " tc 		="+tc_);
		
		System.out.println( "regnKey 			="+regnKey_);
		
		System.out.println( "transType			="+transType_);
		
		System.out.println( "createdTimeStamp	="+createdTimestamp_);
		
		
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

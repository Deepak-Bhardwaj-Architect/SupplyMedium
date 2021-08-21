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

package core.common;

/**
 * @FileName : BranchData.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Apr 7, 2013
 * 
 * @Purpose : Branch List Data
 * 
 */
public class BranchData
{
	public String	branch_;

	/**
	 * Asigning the Value to the Branch
	 */
	public BranchData(String branch)
	{
		this.branch_ = branch;
	}

	/**
	 * @Date : Apr 7, 2013 9:17:08 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Show the Branch
	 * 
	 * 
	 */
	public void Show( )
	{
		System.out.println( " branch_ =" + branch_ );
	}

	/**
	 * @Date : Apr 7, 2013 9:17:41 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : clear the Value
	 * 
	 * 
	 */
	public void Clear( )
	{
		branch_ = null;
	}
}

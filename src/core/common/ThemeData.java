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
 * @FileName : ThemeData.java
 * 
 * @author : Anbazhagan
 * 
 * @Date : Apr 7, 2013
 * 
 * @Purpose : Theme Data
 * 
 */
public class ThemeData
{
	public String	theme_;

	/**
	 * Constractor
	 */
	public ThemeData(String theme)
	{
		this.theme_ = theme;
	}

	/**
	 * @Date : Apr 7, 2013 9:18:46 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : Show the Theme List
	 * 
	 * 
	 */
	public void Show( )
	{
		System.out.println( "theme_ = " + theme_ );
	}

	/**
	 * @Date : Apr 7, 2013 9:19:35 PM
	 * 
	 * @Return : void
	 * 
	 * @Purpose : clear the value
	 * 
	 * 
	 */
	public void Clear( )
	{
		this.theme_ = null;
	}
}

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
package core.regn;

/**
 * @FileName	: MailingAddressKey.java
 *
 * @author 		: Anbazhagan
 *
 * @Date 		: Mar 23, 2013
 *
 * @Purpose 		: 
 *
 */
public class MailingAddressKey
{
	public long mailngKey_=0;
	
	public MailingAddressKey()
	{
		
	}
	
	public MailingAddressKey(long key)
	{
		this.mailngKey_=key;
	}
	
	public void show()
	{
		System.out.println(" Mailing Address Key :"+this.mailngKey_);
	}
	
	public void clear()
	{
		this.mailngKey_=0;
	}
}

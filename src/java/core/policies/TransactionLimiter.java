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

package core.policies;

import core.regn.CompanyRegnKey;

/**
 * File:  TransactionLimiter.java 
 *
 * Created on Feb 20, 2013 11:27:28 AM
 */

public class TransactionLimiter implements AccountLimiter
{

	public int isAllowed( CompanyRegnKey companyRegnKey )
	{
		return 0;
	}
}

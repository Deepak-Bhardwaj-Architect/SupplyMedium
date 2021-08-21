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
 * File: AccountLimiter.java
 * 
 * Created on Feb 20, 2013 11:27:28 AM
 */

/*
 * This interface is used to Limit the user signup and transactions of the
 * company according to company pricing option. Two classes MemberLimiter and
 * TransactionLimiter implements this interface. MemberLimiter class limit the
 * user signup and TransactionLimiter class limit the company transaction.
 */

public interface AccountLimiter
{
	public int isAllowed( CompanyRegnKey companyRegnKey );
}

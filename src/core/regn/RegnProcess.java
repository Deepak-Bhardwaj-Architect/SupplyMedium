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
 * File:  RegnProcess.java 
 *
 * Created on Jan 21, 2013 2:39:16 PM
 */

/*
 * This is the main class for company registration process.
 * Three classes implements this interface. There are NewRegn, RegnLinkActivate and
 * RemoveRegn. RegnFactory is the factory class for this Factory design.
 * This factory class only decide which class process method need to be called according
 * to input parameter.
 */
public interface RegnProcess
{
	public int process( RegnData regnDataObj );

}

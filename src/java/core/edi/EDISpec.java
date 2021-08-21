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
package core.edi;

/**
 * File:  EDISpec.java 
 *
 * Created on 21-Oct-2013 4:26:33 PM
 */

public interface EDISpec
{
	// Use to fetch the element id of the given element name
	public String getElementCode( String elementName );
	
	// Use to fetch the segment id of the given segment name
	public String getSegmentCode( String segmentName );

}

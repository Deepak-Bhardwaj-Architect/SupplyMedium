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
package core.trans;

/**
 * File:  TransMailer.java 
 *
 * Created on 22-Jun-2013 9:37:01 AM
 */


public class TransMailer
{

	/*
	 * Method : TransMailer -- constructor
	 * 
	 * Input  : None
	 * 
	 * Return : None
	 * 
	 * Purpose:
	 */
	
	public TransMailer()
	{
	}

	/*
	 * Method : compose
	 * 
	 * Input  : RFQData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It get the rfqdata from the core classes and generate the 
	 * html mail. Using the Mailer class it send the mail to given email id.
	 * It also attach the PDF format of the RFQ form with this mail.
	 */
	
	public int compose( RFQData rfqData )
	{
		return 0;
	}
	
	/*
	 * Method : compose
	 * 
	 * Input  : QuoteData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It get the quotedata from the core classes and generate the 
	 * html mail. Using the Mailer class it send the mail to given email id.
	 * It also attach the PDF format of the Quote form with this mail.
	 */
	
	public int compose( QuoteData quoteData )
	{
		return 0;
	}
	
	/*
	 * Method : compose
	 * 
	 * Input  : POData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It get the podata from the core classes and generate the 
	 * html mail. Using the Mailer class it send the mail to given email id.
	 * It also attach the PDF format of the PO form with this mail.
	 */
	
	public int compose( POData poData )
	{
		return 0;
	}
	
	/*
	 * Method : compose
	 * 
	 * Input  : InvoiceData object
	 * 
	 * Return : int
	 * 
	 * Purpose: It get the invoicedata from the core classes and generate the 
	 * html mail. Using the Mailer class it send the mail to given email id.
	 * It also attach the PDF format of the Invoice form with this mail.
	 */
	
	public int compose( InvoiceData invoiceData )
	{
		return 0;
	}
}

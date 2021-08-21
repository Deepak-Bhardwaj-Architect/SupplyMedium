/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any. - - - - - - - - - - -
 * - - - - - - - - - - - - - - - - - - - - - - - - - -
 */
package db.trans;

import java.sql.Date;
import java.sql.Timestamp;
import utils.ErrorMaster;

/**
 * File: InvoiceRecord.java
 *
 * Created on Jul 6, 2013 2:28:27 PM
 */
public class InvoiceRecord {

    // This is the transaction id
    public long transId_;

    // This is the unique id of this invoice
    public long invoiceId_;

    // This is the companyRegnKey who sent the invoice request
    public String from_;

    // This is the companyRegnKey who receive the invoice request
    public String to_;

    // This is the userProfileKey who sent the invoice request
    public String userFrom_;

    // This is the userProfileKey who receive the invoice request
    public String userTo_;

    // Current status of the invoice request
    public String status_;

    /*
     * This is the current action of the transaction.It should be Accept or
     * Reject or Inquire
     */
    public String action_;

    /*
     * This flag is set to true if invoice send to outside buyer who is not
     * registered with SupplyMedium. Flag set to false invoice send to registered
     * buyer
     */
    public int isOutsideBuyer_;

    /* Email id for outside buyer */
    public String outsideBuyerEmail_;

    public String outsideSuppliercountry;

    public String outsideSupplierstate;

    public String outsideSuppliercity;

    public String outsideSupplieraddress;

    public String outsideSupplierzipcode;

    // This string indicate the recurring type
    public String recurring_;

    // Invoice Date
    public Date invoiceDate_;

    // Total amount of the Invoice with out tax amount
    public double totalListPrice_;

    // Percentage of tax for Invoice
    public double taxPercentage_;

    // Freight handling charges
    public double freightHandling_;

    // Total amount of the invoice with tax amount
    public double totalPrice_;

    // Invoice discount
    public double discount_;

    // Invoice billing period
    public String invoiceBillingPeriod_;

    //Invoice due date
    public Date invoiceDueDate_;

    //Invoice no.
    public String invoiceNo_;

    // Freight carrier
    public String freightCarrier_;

    // Bill of landing
    public long billOfLanding_;

    //Freight weight
    public double freightWeight_;

    //Freight weight unit
    public String freightWeightUnit_;

    //Date shipped
    public Date dateShipped_;

    // is Non po invoice
    public int isNonPOInvoice_;

    // PO Number
    public String poNo_;

    // is diff address
    public int isDiffAdd_;

    // if address different, email
    public String diffEmail_;

    public String selected_address;

    // This is the Invoice form added in SM date
    public Timestamp createdTimestamp_;

    private static ErrorMaster errorMaster_ = null;

    /*
     * Method : PORecord -- constructor
     * 
     * Input  : None
     * 
     * Return : None
     * 
     * Purpose:
     */
    public InvoiceRecord() {
        if (errorMaster_ == null) {
            errorMaster_ = new ErrorMaster();
        }
    }

    /*
     * Method : show( ) 
     * 
     * Input : None 
     * 
     * Return : None
     * 
     * Purpose: It is used to print the all class variable values in console
     */
    public void show() {
        errorMaster_.insert("transId_				= " + transId_);
        errorMaster_.insert("invoiceId_				= " + invoiceId_);
        errorMaster_.insert("from					= " + from_.toString());

        errorMaster_.insert("to						= " + to_.toString());
        errorMaster_.insert("userFrom				= " + userFrom_.toString());
        errorMaster_.insert("userTo					= " + userTo_.toString());

        errorMaster_.insert("status_				= " + status_);
        errorMaster_.insert("isOutsideBuyer_		= " + isOutsideBuyer_);
        errorMaster_.insert("outsideBuyerEmail_		= " + outsideBuyerEmail_);

        errorMaster_.insert("recurring_				= " + recurring_);
        errorMaster_.insert("invoiceDate_			= " + invoiceDate_);
        errorMaster_.insert("createdTimestamp_		= " + createdTimestamp_);

        errorMaster_.insert("totalListPrice_		= " + totalListPrice_);
        errorMaster_.insert("taxPercentage_			= " + taxPercentage_);

        errorMaster_.insert("freightHandling_		= " + freightHandling_);
        errorMaster_.insert("totalPrice_			= " + totalPrice_);

        errorMaster_.insert("discount_				= " + discount_);
        errorMaster_.insert("invoiceBillingPeriod_	= " + invoiceBillingPeriod_);
        errorMaster_.insert("invoiceDueDate_		= " + invoiceDueDate_);

        errorMaster_.insert("invoiceNo_				= " + invoiceNo_);
        errorMaster_.insert("freightCarrier_		= " + freightCarrier_);

        errorMaster_.insert("billOfLanding_			= " + billOfLanding_);
        errorMaster_.insert("freightWeight_			= " + freightWeight_);

        errorMaster_.insert("freightWeightUnit_		= " + freightWeightUnit_);
        errorMaster_.insert("dateShipped_			= " + dateShipped_);

        errorMaster_.insert("isNonPOInvoice_		= " + isNonPOInvoice_);
        errorMaster_.insert("poNo_					= " + poNo_);
        errorMaster_.insert("isDiffAdd_				= " + isDiffAdd_);

        errorMaster_.insert("diffEmail				= " + diffEmail_);
    }

    /*
     * Method : clear( ) 
     * 
     * Input : None 
     * 
     * Return : None
     * 
     * Purpose: To reset the class variables.
     */
    public void clear() {
        transId_ = -1;
        invoiceId_ = -1;

        from_ = null;
        to_ = null;

        userFrom_ = null;
        userTo_ = null;

        status_ = null;

        isOutsideBuyer_ = -1;
        outsideBuyerEmail_ = null;

        outsideSuppliercountry = null;

        outsideSupplierstate = null;

        outsideSuppliercity = null;

        outsideSupplieraddress = null;

        outsideSupplierzipcode = null;

        recurring_ = null;
        invoiceDate_ = null;
        createdTimestamp_ = null;

        totalListPrice_ = 0.0f;
        taxPercentage_ = 0.0f;
        totalPrice_ = 0.0f;

        freightHandling_ = 0.0f;
        freightCarrier_ = null;
        billOfLanding_ = -1;

        freightWeight_ = 0.0f;
        freightWeightUnit_ = null;
        dateShipped_ = null;

        isNonPOInvoice_ = 0;
        poNo_ = null;
        isDiffAdd_ = 0;

        diffEmail_ = null;

        selected_address = null;
    }
}

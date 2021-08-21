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
package ctrl.trans;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.trans.InvoiceData;
import core.trans.InvoiceItemData;
import utils.ErrorMaster;

/**
 * File: InvoiceDataConverter.java
 *
 * Created on Jul 6, 2013 2:30:34 PM
 */
public class InvoiceDataConverter {

    ErrorLogger errorLogger_;

    /*
     * Method : InvoiceDataConverter -- constructor
     * 
     * Input  : None
     * 
     * Return : None
     * 
     * Purpose:
     */
    public InvoiceDataConverter() {
        errorLogger_ = ErrorLogger.instance();
    }

    /*
     * Method : convert 
     * 
     * Input  : HTTPServletRequest and InvoiceData object
     * 
     * Return : int
     * 
     * Purpose: This method is used to convert the HttpServletRequest object
     * to InvoiceData object. And assign to poData parameter so it is copied in
     * caller classes.
     */
    public int convert(HttpServletRequest request, InvoiceData invoiceData) {
        try {
            ErrorMaster errorMaster_ = null;
            if (errorMaster_ == null) {
                errorMaster_ = new ErrorMaster();
            }
            String requestType = request.getParameter("RequestType");

            // This conversion process only for the InvoiceCreation request
            if (requestType.equals("InvoiceCreation") || requestType.equals("UpdateInvoice")) {
                errorMaster_.insert("if condition");

                String InvoiceJson = request.getParameter("Invoice").toString();

                JsonParser jsonParser = new JsonParser();

                JsonObject invoiceJSONData = (JsonObject) jsonParser.parse(InvoiceJson);

				// Setting from company regn key
                String fromRegnKeyStr = invoiceJSONData.get("fromRegnKey").getAsString();

                CompanyRegnKey fromRegnKey = new CompanyRegnKey();

                fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;

                invoiceData.from_ = fromRegnKey;

                fromRegnKey = null;

				// Setting from user key
                String fromUserKeyStr = invoiceJSONData.get("fromUserKey").getAsString();

                UserProfileKey fromUserKey = new UserProfileKey();

                fromUserKey.email_ = fromUserKeyStr;

                invoiceData.userFrom_ = fromUserKey;

                fromUserKey = null;

				// fetching isOutsideSupplier 
                invoiceData.isOutsideBuyer_ = invoiceJSONData.get("isOutsideSupplier").getAsInt();

                invoiceData.outsideSupplierEmailFlag_ = invoiceJSONData.get("outsideSupplierMailFlag").getAsInt();

                if (invoiceData.isOutsideBuyer_ == 1) {
                    invoiceData.outsideBuyerEmail_ = invoiceJSONData.get("outsideSupplierEmail").getAsString();
                    invoiceData.outsideSuppliercountry = invoiceJSONData.get("outsideSuppliercountry").getAsString();
                    invoiceData.outsideSupplierstate = invoiceJSONData.get("outsideSupplierstate").getAsString();
                    invoiceData.outsideSuppliercity = invoiceJSONData.get("outsideSuppliercity").getAsString();
                    invoiceData.outsideSupplieraddress = invoiceJSONData.get("outsideSupplieraddress").getAsString();
                    invoiceData.outsideSupplierzipcode = invoiceJSONData.get("outsideSupplierzipcode").getAsString();
                } else {

					// Setting to company regn key
                    String toRegnKeyStr = invoiceJSONData.get("toRegnKey").getAsString();

                    CompanyRegnKey toRegnKey = new CompanyRegnKey();

                    toRegnKey.companyPhoneNo_ = toRegnKeyStr;

                    invoiceData.to_ = toRegnKey;

                    toRegnKey = null;

                }

                errorMaster_.insert("userFrom_=" + invoiceData.userFrom_);

				//Setting the invoice sub total
                Double quoteTotalPrice = invoiceJSONData.get("totalListPrice").getAsDouble();

                invoiceData.totalListPrice_ = quoteTotalPrice;

                errorMaster_.insert("totalListPrice_=" + invoiceData.totalListPrice_);

				//Setting the tax percentage
                Double taxPercentage = invoiceJSONData.get("taxPercentage").getAsDouble();

                invoiceData.taxPercentage_ = taxPercentage;

                errorMaster_.insert("taxPercentage_=" + invoiceData.taxPercentage_);

				//Setting the invoice total
                Double totalPrice = invoiceJSONData.get("totalPrice").getAsDouble();

                invoiceData.totalPrice_ = totalPrice;

                errorMaster_.insert("totalPrice_=" + invoiceData.totalPrice_);

				//Setting the invoice freight handling charges
                Double freightHandling = invoiceJSONData.get("freightHandling").getAsDouble();

                invoiceData.freightHandling_ = freightHandling;

                errorMaster_.insert("freightHandling_=" + invoiceData.freightHandling_);

                invoiceData.invoiceDate_ = new Date(System.currentTimeMillis());

				// Invoice Due Date
                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

                String invoiceDueDateStr = invoiceJSONData.get("invoiceDueDate").getAsString();

                java.util.Date invoiceDueDate = (java.util.Date) formatter.parse(invoiceDueDateStr);

                invoiceData.dueDate_ = new java.sql.Date(invoiceDueDate.getTime());

                errorMaster_.insert("dateShipped_=" + invoiceData.dueDate_);

				// Setting freight carrier
                String freightCarrier = invoiceJSONData.get("freightCarrier").getAsString();

                invoiceData.freightCarrier_ = freightCarrier;

                errorMaster_.insert("freightCarrier_=" + invoiceData.freightCarrier_);

				//Setting the invoice freight weight
                Double freightWeight = invoiceJSONData.get("freightWeight").getAsDouble();

                invoiceData.freightWeight_ = freightWeight;

                errorMaster_.insert("freightWeight_=" + invoiceData.freightWeight_);

				// Setting freight weight unit
                String freightWeightUnit = invoiceJSONData.get("freightWeightUnit").getAsString();

                invoiceData.freightWeightUnit_ = freightWeightUnit;

                errorMaster_.insert("freightWeightUnit_=" + invoiceData.freightWeightUnit_);

				//Setting shipped date ( All items shipped date )
                String shippedDateStr = invoiceJSONData.get("shippedDate").getAsString();
                                //System.out.println("shippedDateStr-----------------------"+shippedDateStr);

                java.util.Date shippedDate = (java.util.Date) formatter.parse(shippedDateStr);

                invoiceData.dateShipped_ = new java.sql.Date(shippedDate.getTime());
                //System.out.println("shippedDateStr converted-----------------------");
                errorMaster_.insert("dateShipped_=" + invoiceData.dateShipped_);

				//Setting Bill of landing number
				//String billOfLandingStr = invoiceJSONData.get( "billOfLanding" ).getAsLong( );
				//java.util.Date billOfLanding = ( java.util.Date )formatter.parse( billOfLandingStr );
                invoiceData.billOfLanding_ = invoiceJSONData.get("billOfLanding").getAsLong();

                errorMaster_.insert("billOfLanding_=" + invoiceData.billOfLanding_);

				//Setting PO NUM
                String poNum = invoiceJSONData.get("poNum").getAsString();

                invoiceData.poNo_ = poNum;
                errorMaster_.insert("poNo_=" + invoiceData.poNo_);

				//Setting isNonPOInvoice
                int isNonPOInvoice = invoiceJSONData.get("isNonPoInvoice").getAsInt();

                invoiceData.isNonPOInvoice_ = isNonPOInvoice;

                errorMaster_.insert("isNonPOInvoice_=" + invoiceData.isNonPOInvoice_);

				//Setting isDiffAddress
                int isDiffAdd = invoiceJSONData.get("isDiffAdd").getAsInt();

                invoiceData.isDiffAdd_ = isDiffAdd;

                invoiceData.diffEmail_ = invoiceJSONData.get("diffEmail").getAsString();

                invoiceData.selected_address = invoiceJSONData.get("selected_address").getAsString();

                invoiceData.transId_ = invoiceJSONData.get("transId").getAsLong();

                invoiceData.action_ = invoiceJSONData.get("action").getAsString();

                JsonArray itemsArr = invoiceJSONData.get("items").getAsJsonArray();

                for (JsonElement jsonele : itemsArr) {
                    errorMaster_.insert("Items count=" + itemsArr.size());

                    JsonObject invoiceItemJSONData = (JsonObject) jsonele;

                    // Converting JSON Data to strings
                    String itemDesc = invoiceItemJSONData.get("itemDesc").getAsString();

                    String partNo = invoiceItemJSONData.get("partNo").getAsString();

                    String brcd = invoiceItemJSONData.get("brcd").getAsString();

                    String quantityOrdered = invoiceItemJSONData.get("quantityOrdered").getAsString();

                    String quantityOrderedUnit = invoiceItemJSONData.get("quantityOrderedUnit").getAsString();

                    String quantityShipped = invoiceItemJSONData.get("quantityShipped").getAsString();

                    String quantityShippedUnit = invoiceItemJSONData.get("quantityShippedUnit").getAsString();

                    Double price = invoiceItemJSONData.get("price").getAsDouble();

                    String currencyStr = invoiceItemJSONData.get("currency").getAsString();

                    errorMaster_.insert("Items count=" + itemsArr.size());

                    // Converting strings to InvoiceItemData object
                    InvoiceItemData item = new InvoiceItemData();

                    item.itemDesc_ = itemDesc;

                    item.partNo_ = partNo;

                    item.brcd_ = brcd;

                    item.quantityOrdered_ = Double.parseDouble(quantityOrdered);

                    item.quantityOrderedUnit_ = quantityOrderedUnit;

                    item.quantityShipped_ = Double.parseDouble(quantityShipped);

                    item.quantityShippedUnit_ = quantityShippedUnit;

                    item.price_ = price;

                    item.currency_ = currencyStr;

                    invoiceData.invoiceItems_.add(item);

                    errorMaster_.insert("Items count=" + itemsArr.size());

                }

                if (requestType.equals("UpdateInvoice")) {
					// Setting from user key

                    errorMaster_.insert("if block");

                    String toUserKeyStr = invoiceJSONData.get("toUserKey").getAsString();

                    UserProfileKey toUserKey = new UserProfileKey();

                    toUserKey.email_ = toUserKeyStr;

                    invoiceData.userTo_ = toUserKey;

                    toUserKey = null;

                    errorMaster_.insert("afetr user key convertion");

                    invoiceData.invoiceId_ = invoiceJSONData.get("invoiceId").getAsLong();

                    errorMaster_.insert("InvoiceId=" + invoiceData.invoiceId_ + "transid=" + invoiceData.transId_);

                }

                return 0;
            } else if (requestType.equals("FetchInvoice")) {
				// Setting from company regn key

                String fromRegnKeyStr = request.getParameter("RegnKey");

                CompanyRegnKey fromRegnKey = new CompanyRegnKey();

                fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;

                invoiceData.from_ = fromRegnKey;

                fromRegnKey = null;

				// Setting from user key
                String fromUserKeyStr = request.getParameter("UserKey");

                UserProfileKey fromUserKey = new UserProfileKey();

                fromUserKey.email_ = fromUserKeyStr;

                invoiceData.userFrom_ = fromUserKey;

                fromUserKey = null;
            } else if (requestType.equals("ChangeStatus")) {
				// Setting from company regn key

                String fromRegnKeyStr = request.getParameter("FromRegnKey");

                CompanyRegnKey fromRegnKey = new CompanyRegnKey();

                fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;

                invoiceData.from_ = fromRegnKey;

                fromRegnKey = null;

				// Setting from user key
                String fromUserKeyStr = request.getParameter("FromUserKey");

                UserProfileKey fromUserKey = new UserProfileKey();

                fromUserKey.email_ = fromUserKeyStr;

                invoiceData.userFrom_ = fromUserKey;

                fromUserKey = null;

				// Setting  to regn key
                String toRegnKeyStr = request.getParameter("ToRegnKey");

                CompanyRegnKey toRegnKey = new CompanyRegnKey();

                toRegnKey.companyPhoneNo_ = toRegnKeyStr;

                invoiceData.to_ = toRegnKey;

                fromRegnKey = null;

				// Setting to user key
                String toUserKeyStr = request.getParameter("ToUserKey");

                UserProfileKey toUserKey = new UserProfileKey();

                toUserKey.email_ = toUserKeyStr;

                invoiceData.userTo_ = toUserKey;

                invoiceData.invoiceId_ = Integer.parseInt(request.getParameter("InvoiceId"));

                invoiceData.transId_ = Integer.parseInt(request.getParameter("TransId"));

                invoiceData.status_ = request.getParameter("Status");

                invoiceData.action_ = request.getParameter("Action");

                fromUserKey = null;
            }
        } catch (Exception e) {
            errorLogger_.logMsg("Exception::InvoicedataConverter.convert() - " + e);

            return -1;
        }

        return 0;
    }
}

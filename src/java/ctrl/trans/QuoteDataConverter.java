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
import core.trans.QuoteData;
import core.trans.QuoteItemData;
import utils.ErrorMaster;

/**
 * File: QuoteDataConverter.java
 *
 * Created on Jul 4, 2013 10:37:42 AM
 */
public class QuoteDataConverter {

    ErrorLogger errorLogger_;

    /*
     * Method : QuoteDataConverter -- constructor
     * 
     * Input  : None
     * 
     * Return : None
     * 
     * Purpose:
     */
    public QuoteDataConverter() {
        errorLogger_ = ErrorLogger.instance();
    }

    /*
     * Method : convert 
     * 
     * Input  : HTTPServletRequest and QuoteData object
     * 
     * Return : int
     * 
     * Purpose: This method is used to convert the HttpServletRequest object
     * to QuoteData object. And assign to quoteData parameter so it is copied in
     * caller classes.
     */
    public int convert(HttpServletRequest request, QuoteData quoteData) {
        try {
            ErrorMaster errorMaster_ = null;
            if (errorMaster_ == null) {
                errorMaster_ = new ErrorMaster();
            }
            String requestType = request.getParameter("RequestType");

            if (requestType.equals("QuoteCreation") || requestType.equals("UpdateQuote")) // This conversion process only for the QuoteCreation request
            {
                String QuoteJson = request.getParameter("Quote").toString();

                JsonParser jsonParser = new JsonParser();

                JsonObject quoteJSONData = (JsonObject) jsonParser.parse(QuoteJson);

				// Setting from company regn key
                String fromRegnKeyStr = quoteJSONData.get("fromRegnKey").getAsString();

                CompanyRegnKey fromRegnKey = new CompanyRegnKey();

                fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;

                quoteData.from_ = fromRegnKey;

                fromRegnKey = null;

				// Setting from user key
                String fromUserKeyStr = quoteJSONData.get("fromUserKey").getAsString();

                UserProfileKey fromUserKey = new UserProfileKey();

                fromUserKey.email_ = fromUserKeyStr;

                quoteData.userFrom_ = fromUserKey;
                quoteData.quoteDate_ = new Date(System.currentTimeMillis());

                fromUserKey = null;

				// fetching isOutsideSupplier 
                quoteData.isOutsideSupplier_ = quoteJSONData.get("isOutsideSupplier").getAsInt();

                quoteData.outsideSupplierEmailFlag_ = quoteJSONData.get("outsideSupplierMailFlag").getAsInt();

                if (quoteData.isOutsideSupplier_ == 1) {
                    quoteData.outsideSupplierName_ = quoteJSONData.get("outsideSuppliername").getAsString();
                    quoteData.outsideSupplierEmail_ = quoteJSONData.get("outsideSupplierEmail").getAsString();

                    quoteData.outsideSuppliercountry = quoteJSONData.get("outsideSuppliercountry").getAsString();

                    quoteData.outsideSupplierstate = quoteJSONData.get("outsideSupplierstate").getAsString();

                    quoteData.outsideSuppliercity = quoteJSONData.get("outsideSuppliercity").getAsString();

                    quoteData.outsideSupplieraddress = quoteJSONData.get("outsideSupplieraddress").getAsString();

                    quoteData.outsideSupplierzipcode = quoteJSONData.get("outsideSupplierzipcode").getAsString();
                } else {

					// Setting to company regn key
                    String toRegnKeyStr = quoteJSONData.get("toRegnKey").getAsString();

                    CompanyRegnKey toRegnKey = new CompanyRegnKey();

                    toRegnKey.companyPhoneNo_ = toRegnKeyStr;

                    quoteData.to_ = toRegnKey;

                    String toUserKeyStr = quoteJSONData.get("toUserKey").getAsString();

                    UserProfileKey toUserKey = new UserProfileKey();

                    toUserKey.email_ = toUserKeyStr;

                    quoteData.userTo_ = toUserKey;

                    fromUserKey = null;

                    toRegnKey = null;

                }

				//Setting the total list price
                Double quoteTotalPrice = quoteJSONData.get("totalListPrice").getAsDouble();

                quoteData.totalListPrice_ = quoteTotalPrice;

				//Setting the tax percentage
                Double taxPercentage = quoteJSONData.get("taxPercentage").getAsDouble();

                quoteData.taxPercentage_ = taxPercentage;

				//Setting the tax percentage
                Double totalPrice = quoteJSONData.get("totalPrice").getAsDouble();

                quoteData.totalPrice_ = totalPrice;
                

                quoteData.quoteRef_ = quoteJSONData.get("quoteRef").getAsString();

                JsonArray itemsArr = quoteJSONData.get("items").getAsJsonArray();

                for (JsonElement jsonele : itemsArr) {
                    errorMaster_.insert("Items count=" + itemsArr.size());

                    JsonObject quoteItemJSONData = (JsonObject) jsonele;

                    // Converting JSON Data to strings
                    String itemDesc = quoteItemJSONData.get("itemDesc").getAsString();

                    String partNo = quoteItemJSONData.get("partNo").getAsString();

                    String brcd = quoteItemJSONData.get("brcd").getAsString();

                    String quantity = quoteItemJSONData.get("quantity").getAsString();

                    String quantityUnit = quoteItemJSONData.get("quantityUnit").getAsString();

                    String shipDateStr = quoteItemJSONData.get("shipDate").getAsString();

                    Double price = quoteItemJSONData.get("price").getAsDouble();

                    String currencyStr = quoteItemJSONData.get("currency").getAsString();

                    errorMaster_.insert("multiplier=" + quoteItemJSONData.get("multiplier").getAsString());

                    int multiplier = 0;

                    if (!quoteItemJSONData.get("multiplier").getAsString().equals("")) {
                        multiplier = quoteItemJSONData.get("multiplier").getAsInt();
                    }

                    errorMaster_.insert("multiplier=" + multiplier);

                    errorMaster_.insert("Items count=" + itemsArr.size());

                    // Converting strings to QuoteItemData object
                    QuoteItemData item = new QuoteItemData();

                    item.itemDesc_ = itemDesc;

                    item.partNo_ = partNo;

                    item.brcd_ = brcd;

                    item.quantity_ = Double.parseDouble(quantity);

                    item.quantityUnit_ = quantityUnit;

                    //Added for quote
                    item.price_ = price;

                    item.currency_ = currencyStr;

                    item.multiplier_ = multiplier;

                    errorMaster_.insert("item multiplier=" + item.multiplier_);

                    DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

                    java.util.Date shipDate = (java.util.Date) formatter.parse(shipDateStr);

                    item.shipDate_ = new java.sql.Date(shipDate.getTime());

					//item.shipDate_ = ( java.sql.Date ) shipDate;
                    quoteData.quoteItems_.add(item);

                    errorMaster_.insert("Items count=" + itemsArr.size());

                    quoteData.transId_ = quoteJSONData.get("transId").getAsLong();

                    quoteData.action_ = quoteJSONData.get("action").getAsString();

                }

                if (requestType.equals("UpdateQuote")) {
					// Setting from user key

                    errorMaster_.insert("if block");

                    String toUserKeyStr = quoteJSONData.get("toUserKey").getAsString();

                    UserProfileKey toUserKey = new UserProfileKey();

                    toUserKey.email_ = toUserKeyStr;

                    quoteData.userTo_ = toUserKey;

                    toUserKey = null;

                    errorMaster_.insert("afetr user key convertion");

                    quoteData.quoteId_ = quoteJSONData.get("quoteId").getAsLong();

                    errorMaster_.insert("QuoteId=" + quoteData.quoteId_ + "transid=" + quoteData.transId_);

                }

                return 0;
            } else if (requestType.equals("FetchQuote")) {
				// Setting from company regn key

                String fromRegnKeyStr = request.getParameter("RegnKey");

                CompanyRegnKey fromRegnKey = new CompanyRegnKey();

                fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;

                quoteData.from_ = fromRegnKey;

                fromRegnKey = null;

				// Setting from user key
                String fromUserKeyStr = request.getParameter("UserKey");

                UserProfileKey fromUserKey = new UserProfileKey();

                fromUserKey.email_ = fromUserKeyStr;

                quoteData.userFrom_ = fromUserKey;

                fromUserKey = null;
            } else if (requestType.equals("ChangeStatus")) {
				// Setting from company regn key

                String fromRegnKeyStr = request.getParameter("FromRegnKey");

                CompanyRegnKey fromRegnKey = new CompanyRegnKey();

                fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;

                quoteData.from_ = fromRegnKey;

                fromRegnKey = null;

				// Setting from user key
                String fromUserKeyStr = request.getParameter("FromUserKey");

                UserProfileKey fromUserKey = new UserProfileKey();

                fromUserKey.email_ = fromUserKeyStr;

                quoteData.userFrom_ = fromUserKey;

                fromUserKey = null;

				// Setting  to regn key
                String toRegnKeyStr = request.getParameter("ToRegnKey");

                CompanyRegnKey toRegnKey = new CompanyRegnKey();

                toRegnKey.companyPhoneNo_ = toRegnKeyStr;

                quoteData.to_ = toRegnKey;

                fromRegnKey = null;

				// Setting to user key
                String toUserKeyStr = request.getParameter("ToUserKey");

                UserProfileKey toUserKey = new UserProfileKey();

                toUserKey.email_ = toUserKeyStr;

                quoteData.userTo_ = toUserKey;

                quoteData.quoteId_ = Integer.parseInt(request.getParameter("QuoteId"));

                quoteData.transId_ = Integer.parseInt(request.getParameter("TransId"));

                quoteData.status_ = request.getParameter("Status");

                quoteData.action_ = request.getParameter("Action");

                fromUserKey = null;
            }
        } catch (Exception e) {
            errorLogger_.logMsg("Exception::QuotedataConverter.convert() - " + e);

            return -1;
        }

        return 0;
    }
}

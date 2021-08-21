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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;

import utils.ErrorLogger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import core.regn.CompanyRegnKey;
import core.regn.UserProfileKey;
import core.trans.RFQData;
import core.trans.RFQItemData;
import utils.ErrorMaster;

/**
 * File: RFQDataConverter.java
 *
 * Created on 21-Jun-2013 9:22:25 AM
 */
public class RFQDataConverter {

    ErrorLogger errorLogger_;
    private static ErrorMaster errorMaster_ = null;


    /*
     * Method : RFQDataConverter -- constructor
     * 
     * Input  : None
     * 
     * Return : None
     * 
     * Purpose:
     */
    public RFQDataConverter() {
        errorLogger_ = ErrorLogger.instance();
        if (errorMaster_ == null) {
            errorMaster_ = new ErrorMaster();
        }
    }

    /*
     * Method : convert 
     * 
     * Input  : HTTPServletRequest and RFQData object
     * 
     * Return : int
     * 
     * Purpose: This method is used to convert the HttpServletRequest object
     * to RFQData object. And assign to rfqData parameter so it is copied in
     * caller classes.
     */
    public int convert(HttpServletRequest request, RFQData rfqData) {
        errorMaster_.insert("UpdateRFQ converter");

        try {

            String requestType = request.getParameter("RequestType");

			// This conversion process only for the RFQCreation request
            if (requestType.equals("RFQCreation") || requestType.equals("UpdateRFQ")) {
                String RFQJson = request.getParameter("RFQ").toString();

                JsonParser jsonParser = new JsonParser();

                JsonObject rfqJSONData = (JsonObject) jsonParser.parse(RFQJson);

				// Setting from company regn key
                String fromRegnKeyStr = rfqJSONData.get("fromRegnKey").getAsString();

                CompanyRegnKey fromRegnKey = new CompanyRegnKey();

                fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;

                rfqData.from_ = fromRegnKey;

                fromRegnKey = null;

				// Setting from user key
                String fromUserKeyStr = rfqJSONData.get("fromUserKey").getAsString();

                UserProfileKey fromUserKey = new UserProfileKey();

                fromUserKey.email_ = fromUserKeyStr;

                rfqData.userFrom_ = fromUserKey;

                fromUserKey = null;

				// fetching isOutsideSupplier 
                rfqData.isOutsideSupplier_ = rfqJSONData.get("isOutsideSupplier").getAsInt();

                rfqData.outsideSupplierEmailFlag_ = rfqJSONData.get("outsideSupplierMailFlag").getAsInt();

                if (rfqData.isOutsideSupplier_ == 1) {
                    rfqData.outsideSupplierName_ = rfqJSONData.get("outsideSuppliername").getAsString();
                    rfqData.outsideSupplierEmail_ = rfqJSONData.get("outsideSupplierEmail").getAsString();
                    rfqData.outsideSuppliercountry = rfqJSONData.get("outsideSuppliercountry").getAsString();
                    rfqData.outsideSupplierstate = rfqJSONData.get("outsideSupplierstate").getAsString();
                    rfqData.outsideSuppliercity = rfqJSONData.get("outsideSuppliercity").getAsString();
                    rfqData.outsideSupplieraddress = rfqJSONData.get("outsideSupplieraddress").getAsString();
                    rfqData.outsideSupplierzipcode = rfqJSONData.get("outsideSupplierzipcode").getAsString();
                } else {

					// Setting to company regn key
                    String toRegnKeyStr = rfqJSONData.get("toRegnKey").getAsString();

                    CompanyRegnKey toRegnKey = new CompanyRegnKey();

                    toRegnKey.companyPhoneNo_ = toRegnKeyStr;

                    rfqData.to_ = toRegnKey;

                    String toUserKeyStr = rfqJSONData.get("touserKey").getAsString();

                    UserProfileKey toUserKey = new UserProfileKey();

                    toUserKey.email_ = toUserKeyStr;

                    rfqData.userTo_ = toUserKey;

                    toRegnKey = null;

                }

				// fetching outsideSupplierEmail
                rfqData.RFQDate_ = new Date(System.currentTimeMillis());

                JsonArray itemsArr = rfqJSONData.get("items").getAsJsonArray();

                rfqData.quoteRef_ = rfqJSONData.get("quoteRef").getAsString();

                for (JsonElement jsonele : itemsArr) {
                    errorMaster_.insert("Items count=" + itemsArr.size());

                    JsonObject rfqItemJSONData = (JsonObject) jsonele;

                    // Converting JSON Data to strings
                    String itemDesc = rfqItemJSONData.get("itemDesc").getAsString();

                    String partNo = rfqItemJSONData.get("partNo").getAsString();
                    
                    String brcdNo = rfqItemJSONData.get("brcdNo").getAsString();

                    String quantity = rfqItemJSONData.get("quantity").getAsString();

                    String quantityUnit = rfqItemJSONData.get("quantityUnit").getAsString();

                    String shipDateStr = rfqItemJSONData.get("shipDate").getAsString();

                    errorMaster_.insert("Items count=" + itemsArr.size());

                    // Converting strings to RFQItemData object
                    RFQItemData item = new RFQItemData();

                    item.itemDesc_ = itemDesc;

                    item.partNo_ = partNo;
                    
                    item.brcdNo_=brcdNo;

                    item.quantity_ = Double.parseDouble(quantity);

                    item.quantityUnit_ = quantityUnit;

                    errorMaster_.insert("item date=" + shipDateStr);

                    DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

                    java.util.Date shipDate = (java.util.Date) formatter.parse(shipDateStr);

                    item.shipDate_ = new java.sql.Date(shipDate.getTime());

					//item.shipDate_ = ( java.sql.Date ) shipDate;
                    rfqData.RFQItems_.add(item);

                    errorMaster_.insert("Items count=" + itemsArr.size());

                }

                if (requestType.equals("UpdateRFQ")) {
					// Setting from user key

                    errorMaster_.insert("if block");

                    String toUserKeyStr = rfqJSONData.get("toUserKey").getAsString();

                    UserProfileKey toUserKey = new UserProfileKey();

                    toUserKey.email_ = toUserKeyStr;

                    rfqData.userTo_ = toUserKey;

                    toUserKey = null;

                    errorMaster_.insert("afetr user key convertion");

                    rfqData.RFQId_ = rfqJSONData.get("rfqId").getAsLong();

                    rfqData.transId_ = rfqJSONData.get("transId").getAsLong();

                    rfqData.action_ = rfqJSONData.get("action").getAsString();

                    errorMaster_.insert("RFQId=" + rfqData.RFQId_ + "transid=" + rfqData.transId_);

                }

                return 0;
            } else if (requestType.equals("FetchRFQ")) {
				// Setting from company regn key

                String fromRegnKeyStr = request.getParameter("RegnKey");

                CompanyRegnKey fromRegnKey = new CompanyRegnKey();

                fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;

                rfqData.from_ = fromRegnKey;

                fromRegnKey = null;

				// Setting from user key
                String fromUserKeyStr = request.getParameter("UserKey");

                UserProfileKey fromUserKey = new UserProfileKey();

                fromUserKey.email_ = fromUserKeyStr;

                rfqData.userFrom_ = fromUserKey;

                fromUserKey = null;
            } else if (requestType.equals("ChangeStatus")) {
				// Setting from company regn key

                String fromRegnKeyStr = request.getParameter("FromRegnKey");

                CompanyRegnKey fromRegnKey = new CompanyRegnKey();

                fromRegnKey.companyPhoneNo_ = fromRegnKeyStr;

                rfqData.from_ = fromRegnKey;

                fromRegnKey = null;

				// Setting from user key
                String fromUserKeyStr = request.getParameter("FromUserKey");

                UserProfileKey fromUserKey = new UserProfileKey();

                fromUserKey.email_ = fromUserKeyStr;

                rfqData.userFrom_ = fromUserKey;

                fromUserKey = null;

				// Setting  to regn key
                String toRegnKeyStr = request.getParameter("ToRegnKey");

                CompanyRegnKey toRegnKey = new CompanyRegnKey();

                toRegnKey.companyPhoneNo_ = toRegnKeyStr;

                rfqData.to_ = toRegnKey;

                fromRegnKey = null;

				// Setting to user key
                String toUserKeyStr = request.getParameter("ToUserKey");

                UserProfileKey toUserKey = new UserProfileKey();

                toUserKey.email_ = toUserKeyStr;

                rfqData.userTo_ = toUserKey;

                rfqData.RFQId_ = Integer.parseInt(request.getParameter("RFQId"));

                rfqData.transId_ = Integer.parseInt(request.getParameter("TransId"));

                rfqData.status_ = request.getParameter("Status");

                rfqData.action_ = request.getParameter("Action");

                fromUserKey = null;
            }
        } catch (Exception e) {
            errorLogger_.logMsg("Exception::RFQdataConverter.convert() - " + e);

            return -1;
        }

        return 0;
    }

}

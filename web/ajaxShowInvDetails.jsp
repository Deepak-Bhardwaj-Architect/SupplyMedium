<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%@page import="supply.medium.home.bean.TransactionInvBean"%>
<%@page import="supply.medium.home.bean.TransactionInvItemBean"%>
<%@page import="supply.medium.home.database.TransactionInvItemMaster"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.database.TransactionPoMaster"%>
<%@page import="supply.medium.home.bean.TransactionPoBean"%>
<%@page import="supply.medium.home.bean.TransactionInvItemBean"%>
<%@page import="supply.medium.home.database.TransactionInvItemMaster"%>
<%@page import="supply.medium.home.bean.TransactionQteBean"%>
<%@page import="supply.medium.home.database.TransactionQteMaster"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.database.CurrencyMaster"%>
<%@page import="supply.medium.home.database.QuantityTypeMaster"%>
<%@page import="supply.medium.home.database.GlobalProductItemMaster"%>
<%@page import="supply.medium.home.bean.GlobalProductItemBean"%>
<%@page import="supply.medium.home.bean.TransactionQteItemBean"%>
<%@page import="supply.medium.home.database.TransactionQteItemMaster"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.InquiryBean"%>
<%@page import="supply.medium.home.database.InquiryMaster"%>
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.home.database.CompanyDetailAddressMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailAddressBean"%>
<div class="Cus_Popup_Outline invoice_popup_outline">
    <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
        <div style="padding: 0px 0px 0px 15px; float: left" id="invoice_tl">INV<%=request.getParameter("invoiceKey")%></div>
<%
                            TransactionInvBean objTr=TransactionInvMaster.showByKey(request.getParameter("invoiceKey"));
String nme=null;
    String cnt=null;
    String stt=null;
    String cty=null;
    String add=null;
    String zip=null;
    String eml=null;
    if(objTr.getInv_is_outside().equalsIgnoreCase("yes"))
    {
        String outsider[]=objTr.getInv_is_outside_address().split("@#@#@");
        nme=outsider[0];
        cnt=CountryMaster.showCountryFromKey(outsider[1]);
        stt=StateMaster.showStateFromKey(outsider[2]);
        cty=outsider[3];
        add=outsider[4];
        zip=outsider[5];
        eml=outsider[6];
    }
    else
    {
        CompanyDetailAddressBean cdab2 = (CompanyDetailAddressBean) (CompanyDetailAddressMaster.showCompanyDetailAddress(request.getParameter("buyerKey"))).get(0);
        nme=cdab2.getCompanyName();
        cnt=CountryMaster.showCountryFromKey(cdab2.getCountry());
        stt=StateMaster.showStateFromKey(cdab2.getState());
        cty=cdab2.getCity();
        add=cdab2.getAddress();
        zip=cdab2.getZipcode();
        eml="";
    }%>
        <div style="float: right;width:100px;">
            <a onClick="this.href = '<%="cropData/company-"+session.getAttribute("companyKey")+"/transaction/INV"+request.getParameter("invoiceKey")+".pdf" %>'" target="_blank">
                <img src="inside/open.png" style="margin-top:10px;">
            </a>
            <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" onclick="$('#invoice_popup').fadeOut();">
            </div>
        </div>
    </div>
    <div id="invoice_form_popup_content" style="margin:0px;float:left;height:auto;" class="invoice_form_content">
        <form action="transInvAction" method="post">
            <div class="invoice_popup_form mCustomScrollbar _mCS_2" id="invoice_popup_form" style="margin-top:10px;overflow:auto;float:left;height:700px;width:99%;">
                <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_2" style="invoicesition:relative; height:100%; overflow:hidden; max-width:100%;">
                    <div class="mCSB_container mCS_no_scrollbar" style="invoicesition: relative; top: 0px;">
                        <input type="hidden" id="invoiceKey" name="invoiceKey" value="<%=request.getParameter("invoiceKey")%>">
                        <input type="hidden" id="invoiceAction" name="invoiceAction">
                        <input type="hidden" id="fromKey" name="fromKey" value="<%=request.getParameter("supplierKey")%>">
                        <input type="hidden" id="toKey" name="toKey" value="<%=request.getParameter("buyerKey")%>">
                        <div class="invoice_buyer_det">
                            <% CompanyDetailAddressBean cdab = (CompanyDetailAddressBean) (CompanyDetailAddressMaster.showCompanyDetailAddress(request.getParameter("supplierKey"))).get(0);%>
                            <div class="sub_heading">Supplier Details</div>
                            <div class="trans_row" style="float: left;">
                                <label class="trans_label"> Suppliers Name </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_buyer_name" disabled="" value="<%=cdab.getCompanyName()%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> Country </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_buyer_country" disabled="" value="<%=CountryMaster.showCountryFromKey(cdab.getCountry())%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> State/Province </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_buyer_state" disabled="" value="<%=StateMaster.showStateFromKey(cdab.getState())%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> City </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_buyer_city" disabled="" value="<%=cdab.getCity()%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> Address </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_buyer_addr" disabled="" value="<%=cdab.getAddress()%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> Zip Code/Postal Code </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_buyer_zipcode" disabled="" value="<%=cdab.getZipcode()%>">
                            </div>
                            <div class="sub_heading" style="margin-top: 20px;">Request
                                For Quote</div>
                            <div class="trans_row">
                                <label class="trans_label"> Quote Reference </label>
                                <input type="text" autocomplete="off" class="textbox" id="invoicepup_po_ref" disabled="">
                            </div>
                        </div>
                        <div class="addr_sep">
                        </div>
                        <div class="invoice_supplier_det"  style="width:440px;">
                            <div class="sub_heading">Buyer Details</div>
                            <div class="trans_row">
                                <div class="checkContainer">
                                    <input type="checkbox" <% if(objTr.getInv_is_outside().equalsIgnoreCase("yes")){out.println("checked");}%> disabled class="checkbox" id="invoicepup_outside_supplier">
                                    <div>
                                    </div>
                                </div>
                                <label for="outside_supplier" class="trans_label" style="line-height: 19px; margin-left: 5px;">Outside
                                    Buyer</label>
                            </div>
                            <div class="invoicepup_supplier_address" style="width: 100%; height: 300px;">
                                <div class="trans_row">
                                    <label class="trans_label"> Buyer Name </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="to_company_popup" value="<%=nme%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> Country </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_supplier_country" value="<%=cnt%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> State/Province </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_supplier_state" value="<%=stt%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> City </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_supplier_city" value="<%=cty%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> Address </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_supplier_addr" value="<%=add%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> Zip Code/Postal Code </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="invoicepup_supplier_zipcode" value="<%=zip%>" disabled="">
                                </div>
                            </div>
                            <div class="popup_outside_sup_email_content" style="width: 480px; <% if(objTr.getInv_is_outside().equalsIgnoreCase("no")){out.println("display: none;");}%>">
                                <div class="trans_row" style="margin-top: 0px;">
                                    <label class="trans_label"> Email </label>
                                    <input type="text" autocomplete="off" value="<%=eml%>" class="textbox" id="invoicepup_email">
                                </div>
                            </div>
                            <div class="trans_row" style="margin-top: 50px;">
                                <label class="trans_label"> Recurring </label>
                                <select id="invoicepup_recurring" class="selectbox hasCustomSelect" style="width: 188px; -webkit-appearance: menulist-button; position: absolute; opacity: 0; height: 28px; font-size: 12px;" disabled="">
                                    <option value="weekly">None</option>
                                    <option value="weekly">Weekly</option>
                                    <option value="monthly">Monthly</option>
                                    <option value="annually">Annually</option>
                                </select>
                                <span class="customSelect selectbox customSelectDisabled" style="width: 165px; display: inline-block;">
                                    <span class="customSelectInner" style="width: 142px; display: inline-block;">None</span>
                                </span>
                            </div>
                        </div>
                        <div class="items_head" style="margin-bottom:20px;">
                            <div class="items_head" style="padding-left:3px;">
                                <label class="trans_label" style="width:38px;margin-right:4px;">S.No</label>
                                <label class="trans_label" style="width: 72px;margin-right:4px;">Item Name</label>
                                <label class="trans_label" style="width: 110px;margin-right:4px;">Item Description</label>
                                <label class="trans_label" style="width: 55px;margin-right:4px;">Part No</label>
                                <label class="trans_label" style="width: 50px;margin-right:4px;">SKU No</label>
                                <label class="trans_label" style="width:66px;margin-right:4px;">Barcode</label>
                                <label class="trans_label" style="width:76px;margin-right:4px;">Quantity</label>
                                <label class="trans_label" style="width:76px;margin-right:4px;">Shipped</label>
                                <label class="trans_label" style="width:90px;margin-right:4px;">Receive Date</label>
                                <label class="trans_label" style="width:98px;margin-right:4px;">Price per unit</label>
                                <label class="trans_label" style="width:65px;margin-right:4px;">Multiplier</label>
                                <label class="trans_label" style="width:100px;margin-right:4px;">Total</label>
                            </div>
                        </div>
                        <div class="items" id="invoicepup_items">         
                                <%
                                    ArrayList itemList = TransactionInvItemMaster.showInvItemFromInvKey(request.getParameter("invoiceKey"));
                                    TransactionInvItemBean transactionInvItemBean = null;
                                    GlobalProductItemBean gpib = null;
                                    float qty=0,price = 0, multi = 0, total = 0, tax = 0, grandTotal = 0, billing = 0;
                                    for (int i = 0; i < itemList.size(); i++) {
                                        transactionInvItemBean = (TransactionInvItemBean) itemList.get(i);
                                        gpib = GlobalProductItemMaster.showByItemKey(transactionInvItemBean.getItemKey());
                                        qty = Float.parseFloat(transactionInvItemBean.getQuantityShipped());
                                        price = Float.parseFloat(transactionInvItemBean.getPrice());
                                        multi = Float.parseFloat(transactionInvItemBean.getMultiplier());
                                        total = price * multi*qty;
                                        grandTotal += total;
                                %> 
                            <div id="item1" class="item" style="width:970px;float:left;margin-top:5px;margin-left:15px;margin-right:0px;">
                                <input type="text" autocomplete="off" class="textbox" id="sno1" style="width:20px;margin-right:10px;" disabled="" value="<%=(i+1)%>">
                                <input type="text" autocomplete="off" class="textbox" maxlength="80" id="item_name1" style="width:60px;margin-right:10px;" disabled="" value="<%=gpib.getItemName()%>">
                                <input type="text" autocomplete="off" class="textbox" maxlength="80" id="item_desc1" style="width:90px;margin-right:10px;" disabled="" value="<%=gpib.getItemDescription()%>">
                                <input type="text" autocomplete="off" class="textbox" maxlength="80" id="part_no1" style="width:43px;margin-right:10px;" disabled="" value="<%=transactionInvItemBean.getPartNo()%>">
                                <input type="text" autocomplete="off" class="textbox" maxlength="80" id="sku_no1" style="width:35px;margin-right:10px;" disabled="" value="<%=gpib.getSKUno()%>">
                                <input type="text" autocomplete="off" class="textbox" maxlength="80" id="brcd_no1" style="width:48px;margin-right:10px;" disabled="" value="<%=transactionInvItemBean.getBarcode()%>">
                                <input type="text" autocomplete="off" class="textbox" maxlength="7" id="quantity1" style="width:28px;" disabled="" value="<%=transactionInvItemBean.getQuantityOrdered()%>">
                                <div class="quantity_unit" id="quantity_unit1" style="margin-right:10px;"><%=QuantityTypeMaster.showCodeByKey(transactionInvItemBean.getQuantityOrderedUnitKey())%></div>
                                <input type="text" autocomplete="off" class="textbox" maxlength="7" id="quantity1" style="width:28px;" disabled="" value="<%=transactionInvItemBean.getQuantityShipped()%>">
                                <div class="quantity_unit" id="quantity_unit1" style="margin-right:10px;"><%=QuantityTypeMaster.showCodeByKey(transactionInvItemBean.getQuantityShippedUnitKey())%></div>
                                <input type="text" autocomplete="off" class="textbox" id="ship_date1" style="width:74px;margin-right:10px;" disabled="" value="<%=transactionInvItemBean.getShipDate().split(" ")[0]%>">
                                <input type="text" autocomplete="off" class="textbox" id="ppunit1" style="width:50px;margin-right:0px;" disabled="" value="<%=price%>">
                                <div class="quantity_unit" id="currency1" style="margin-right:10px;"><%=CurrencyMaster.showCodeByKey(transactionInvItemBean.getCurrencyKey())%></div>
                                <input type="text" autocomplete="off" class="textbox" id="multipl1" style="width:48px;margin-right:10px;" disabled="" value="<%=multi%>">
                                <input type="text" autocomplete="off" class="textbox" id="totl1" style="width:65px;margin-right:10px;" disabled="" value="<%=total%>">
                            </div>
                                <% } %>
                        </div>                        
                        <div style="width: 900px; float: left; margin-left: 40px; height: 50px;">
                            <input id="invoicepup_add_item_btn" type="button" class="add_item general-button" value="Add Item" style="display:none;">
                        </div>
                        
                        <div class="price_det" id="price_det">
                            <div class="price_det_content">
                                <label class="price_det_lbl" id="tot_list_price_lbl"> Total List Price: </label>
                                <label class="price_det_lbl" id="invoicepup_tot_list_price_amt"> <%=grandTotal %> </label>
                            </div>
                            <% TransactionInvBean qtb=TransactionInvMaster.showByKey(request.getParameter("invoiceKey")); %>
                            <div class="price_det_content">
                                <label class="price_det_lbl" id="tax_lbl"> Tax in Percentage: </label>
                                <label class="price_det_lbl" id="invoicepup_tax_amt" style="min-width:35px;width:auto;">
                                </label>
                                <label><%=qtb.getInv_tax_percentage() %>%</label>
                            </div>
                            <div class="price_det_content">
                                <label class="price_det_lbl" id="frei_lbl"> Freight &amp; Handling Charges: </label>
                                <label class="price_det_lbl" id="invoicepup_tax_amt" style="min-width:35px;width:auto;">
                                </label>
                                <label><%=qtb.getFreight_handling() %>%</label>
                            </div>
                            <div class="price_det_content">
                                <label class="price_det_lbl" id="tot_price_lbl"> Total Price: </label>
                                <label class="price_det_lbl" id="invoicepup_tot_price_amt"> <%=qtb.getInv_total_billing_amount() %> </label>
                            </div>
                        </div>
                        <div class="sub_heading" style="padding-left:40px;">Bill of Landing Information</div>    
                        <div class="shipping_det_head">
<!--                                        <label class="trans_label" style="width: 200px;margin-right: 9px;">Carrier</label>-->
                                        <label class="trans_label" style="width: 132px;margin-right: 17px;">Bill of Landing No.</label>
                                        <label class="trans_label" style="width:98px;margin-right: 63px;">Freight Weight</label>
                                        <label class="trans_label" style="width:98px;margin-right:25px;">Date Shipped</label>
                                    </div>
                        <div class="shipping_det_content">
                                        <!--                                    <div style="float:left">
                                                                                <input type="text" autocomplete="off" class="textbox" id="carrier">
                                                                                <option value="" selected="selected">--Select Carrier--</option>
                                                                                <option value="First Flight">First Flight</option>
                                                                                <option value="Professional courier">Professional courier</option>
                                                                                </input>
                                                                            </div>-->
                                        <input type="text" autocomplete="off" class="textbox" name="bill_of_landing" id="bill_of_landing" value="<%=qtb.getBill_of_landing() %>" style="width:120px" readonly="">
                                        <input type="text" autocomplete="off" class="textbox" name="freight_weight" id="freight_weight" value="<%=qtb.getFreight_weight() %>" style="margin-left:20px;width:80px;" readonly="">
                                        <div class="quantity_unit" id="quantity_freight_unit" name="quantity_freight_unit" >KG</div>
                                        <div class="quan_units" id="units_quantity_freight_unit" style="display:none;left:470px;"></div>
                                        <input type="text" autocomplete="off" class="textbox" name="date_shipped" id="date_shipped" value="<%=qtb.getShipped_date() %>" style="margin-left:20px;width:100px;"  readonly="">
                                    </div>
                                    <%
                            String display = "none";
                            String name = "";
                            String result = "";
                            ArrayList inquiryMessageList = InquiryMaster.showAllInquiryByTypeAndKey("INV", request.getParameter("invoiceKey"));
                            InquiryBean ib = null;
                            if (inquiryMessageList.size() > 0) {
                                display = "block";
                            }
                            for (int i = 0; i < inquiryMessageList.size(); i++) {
                                ib = (InquiryBean) inquiryMessageList.get(i);

                                name = CompanyMaster.getCompanyNameFromKey(ib.getInquire_from());

                                result += "           <div class='addl_det_container' id='inquiry_details' style='display:" + display + ";'>";
                                result += "                   <label class='inquire_by'> " + name + " </label>";
                                result += "                   <textarea class='inquire_det' readonly>" + ib.getInquire_details() + "</textarea>";
                                result += "           </div>"
                                        + "<div style='clear:both;'></div>";
                            }
                           // TransactionInvBean qtb=TransactionInvMaster.showByKey(request.getParameter("invoiceKey"));
                            out.print(result);
                        %>       
                        <!--                        <div class="inquires" id="invoicepup_inquires">
                                                </div>-->
                        <div class="new_inquire" id="rfq_popup_new_inquire"  style="display:none">
                            <div class="inquire_row">
                                <label class="inquire_by"> Inquire: </label>
                                <textarea class="inquire_det" id="new_inquire_message" name="new_inquire_message"></textarea>
                                <input type="submit" class="gen-btn-blue" style="margin-left:30px;margin-top:7px;" id="invoice_inquire_send" value="Send" onclick="$('#invoiceAction').val('Inquired');
                                        return validateTransactionInquireMessage();">
                                <label class="invoice_error" style="width:300px;margin-left: 125px;margin-top: 10px;" id="inquire_error">
                                </label>
                            </div>
                        </div>
                        <div id="supplier_ctrls" class="supplier_controls">
                            <%
                                if (!session.getAttribute("userKey").toString().equals(request.getParameter("supplierKey")) && objTr.getInv_trans_status().equals("INV Sent")) {  %>
                            <input type="submit" class="gen-btn-blue" style="margin-left:30px;" onclick="$('#invoiceAction').val('Accepted');" id="invoice_accept_btn" value="Accept">
                            <input type="submit" class="gen-btn-red" style="margin-left:30px;" onclick="$('#invoiceAction').val('Rejected');" id="invoice_reject_btn" value="Reject">
                            <% }%>
                            <input type="button" class="gen-btn-blue" style="margin-left:30px;" id="supp_invoice_inquire_btn" value="OK" onclick="$('#invoice_popup').fadeOut();">                                                        
                            <input type="button" class="gen-btn-blue" style="margin-left:30px;width:130px;" id="buy_invoice_inquire_btn" value="Send Message" onclick="transactionEnquireBox();">
                        </div>
                        <div class="invoice_error" id="invoice_popup_form_error">
                        </div>
                    </div>
                    <div class="mCSB_scrollTools" style="invoicesition: absolute; display: none;">
                        <div class="mCSB_draggerContainer">
                            <div class="mCSB_dragger" style="invoicesition: absolute; top: 0px;" oncontextmenu="return false;">
                                <div class="mCSB_dragger_bar" style="invoicesition:relative;">
                                </div>
                            </div>
                            <div class="mCSB_draggerRail">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
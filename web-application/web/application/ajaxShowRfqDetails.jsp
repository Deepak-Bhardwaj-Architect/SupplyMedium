<%@page import="supply.medium.home.bean.TransactionRfqBean"%>
<%@page import="supply.medium.home.database.TransactionRfqMaster"%>
<%@page import="java.io.File"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.database.QuantityTypeMaster"%>
<%@page import="supply.medium.home.database.GlobalProductItemMaster"%>
<%@page import="supply.medium.home.bean.GlobalProductItemBean"%>
<%@page import="supply.medium.home.bean.TransactionRfqItemBean"%>
<%@page import="supply.medium.home.database.TransactionRfqItemMaster"%>
<%@page import="supply.medium.home.bean.TransactionQteItemBean"%>
<%@page import="supply.medium.home.database.TransactionQteItemMaster"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.InquiryBean"%>
<%@page import="supply.medium.home.database.InquiryMaster"%>
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.home.database.CompanyDetailAddressMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailAddressBean"%>
<div class="Cus_Popup_Outline rfq_popup_outline">
    <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
        <div style="padding: 0px 0px 0px 15px; float: left" id="rfq_tl">RFQ<%=request.getParameter("rfqKey")%></div>
<%
    TransactionRfqBean objTr=TransactionRfqMaster.showByKey(request.getParameter("rfqKey"));
    String nme=null;
    String cnt=null;
    String stt=null;
    String cty=null;
    String add=null;
    String zip=null;
    String eml=null;
    if(objTr.getIsOutersideSupplier().equalsIgnoreCase("yes"))
    {
        String outsider[]=objTr.getOutsiderAddress().split("@#@#@");
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
        CompanyDetailAddressBean cdab2 = (CompanyDetailAddressBean) (CompanyDetailAddressMaster.showCompanyDetailAddress(request.getParameter("supplierKey"))).get(0);
        nme=cdab2.getCompanyName();
        cnt=CountryMaster.showCountryFromKey(cdab2.getCountry());
        stt=StateMaster.showStateFromKey(cdab2.getState());
        cty=cdab2.getCity();
        add=cdab2.getAddress();
        zip=cdab2.getZipcode();
        eml="";
    }
%>
        <div style="float: right;width:100px;" onclick="window.location.href='';">
            <a onClick="this.href = '<%=request.getRealPath("") + File.separator + "cropData/company-"+session.getAttribute("companyKey")+"/transaction/RFQ"+request.getParameter("rfqKey")+".pdf" %>'" target="_blank">
                <img src="inside/open.png" style="margin-top:10px;">
            </a>
            <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" onclick="$('#rfq_popup').fadeOut();">
            </div>
        </div>
    </div>
    <div id="rfq_form_popup_content" style="margin:0px;float:left;height:auto;" class="rfq_form_content">
        <form method="post" action="TransRfqAction">
            <div class="rfq_popup_form mCustomScrollbar _mCS_2" id="rfq_popup_form" style="margin-top:10px;overflow:auto;float:left;height:700px;width:99%;">
                <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_2" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
                    <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
                        <input type="hidden" id="rfqKey" name="rfqKey" value="<%=request.getParameter("rfqKey")%>">
                        <input type="hidden" id="rfqAction" name="rfqAction">
                        <input type="hidden" id="fromKey" name="fromKey" value="<%=request.getParameter("buyerKey")%>">
                        <input type="hidden" id="toKey" name="toKey" value="<%=request.getParameter("supplierKey")%>">
                        <div class="rfq_buyer_det">
                            <% CompanyDetailAddressBean cdab = (CompanyDetailAddressBean) (CompanyDetailAddressMaster.showCompanyDetailAddress(request.getParameter("buyerKey"))).get(0);%>
                            <div class="sub_heading">Buyer Details</div>
                            <div class="trans_row" style="float: left;">
                                <label class="trans_label"> Buyers Name </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="popup_buyer_name" disabled="" value="<%=cdab.getCompanyName()%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> Country </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="popup_buyer_country" disabled="" value="<%=CountryMaster.showCountryFromKey(cdab.getCountry())%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> State/Province </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="popup_buyer_state" disabled="" value="<%=StateMaster.showStateFromKey(cdab.getState())%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> City </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="popup_buyer_city" disabled="" value="<%=cdab.getCity()%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> Address </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="popup_buyer_addr" disabled="" value="<%=cdab.getAddress()%>">
                            </div>
                            <div class="trans_row">
                                <label class="trans_label"> Zip Code/Postal Code </label>
                                <input type="text" autocomplete="off" class="textbox_disable" id="popup_buyer_zipcode" disabled="" value="<%=cdab.getZipcode()%>">
                            </div>
                            <div class="sub_heading" style="margin-top: 20px;">Request
                                For Quote</div>
                            <div class="trans_row">
                                <label class="trans_label"> Quote Reference </label>
                                <input type="text" autocomplete="off" class="textbox" id="popup_quote_ref" disabled="">
                            </div>
                        </div>
                        <div class="addr_sep">
                        </div>
                        <div class="rfq_supplier_det"  style="width:440px;">
                            <div class="sub_heading">Supplier Details</div>
                            <div class="trans_row">
                                <div class="checkContainer">
                                    <input type="checkbox" <% if(objTr.getIsOutersideSupplier().equalsIgnoreCase("yes")){out.println("checked");}%> disabled class="checkbox" id="popup_outside_supplier">
                                    <div>
                                    </div>
                                </div>
                                <label for="outside_supplier" class="trans_label" style="line-height: 19px; margin-left: 5px;">Outside
                                    Supplier</label>
                            </div>
                            <div class="popup_supplier_address" style="width: 100%; height: 300px;">
                                <div class="trans_row">
                                    <label class="trans_label"> Supplier Name </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="to_company_popup" value="<%=nme%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> Country </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="popup_supplier_country" value="<%=cnt%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> State/Province </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="popup_supplier_state" value="<%=stt%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> City </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="popup_supplier_city" value="<%=cty%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> Address </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="popup_supplier_addr" value="<%=add%>" disabled="">
                                </div>
                                <div class="trans_row">
                                    <label class="trans_label"> Zip Code/Postal Code </label>
                                    <input type="text" autocomplete="off" class="textbox_disable" id="popup_supplier_zipcode" value="<%=zip%>" disabled="">
                                </div>
                            </div>
                            <div class="popup_outside_sup_email_content" style="width: 480px; <% if(objTr.getIsOutersideSupplier().equalsIgnoreCase("no")){out.println("display: none;");}%>">
                                <div class="trans_row" style="margin-top: 0px;">
                                    <label class="trans_label"> Email </label>
                                    <input type="text" autocomplete="off" class="textbox" value="<%=eml%>" id="popup_email">
                                </div>
                            </div>
                            <div class="trans_row" style="margin-top: 50px;">
                                <label class="trans_label"> Recurring </label>
                                <select id="popup_recurring" class="selectbox hasCustomSelect" style="width: 188px; -webkit-appearance: menulist-button; position: absolute; opacity: 0; height: 28px; font-size: 12px;" disabled="">
                                    <option value="weekly">None</option>
                                    <option value="weekly">Weekly</option>
                                    <option value="monthly">Monthly</option>
                                    <option value="annually">Annually</option>
                                </select>
                                <span class="customSelect selectbox customSelectDisabled" style="width: 165px; display: inline-block;position:static;">
                                    <span class="customSelectInner" style="width: 142px; display: inline-block;">None</span>
                                </span>
                            </div>
                        </div>
                        <div class="items_head">
                            <label class="trans_label" style="width:28px;margin-right:20px;">S.No</label>
                            <label class="trans_label" style="width:98px;margin-right:20px;">Item Name</label>
                            <label class="trans_label" style="width:118px;margin-right:20px;">Item Description</label>
                            <label class="trans_label" style="width:58px;margin-right:20px;">Batch No</label>
                            <label class="trans_label" style="width:58px;margin-right:20px;">SKU No</label>
                            <label class="trans_label" style="width:58px;margin-right:20px;">Barcode</label>
                            <label class="trans_label" style="width:58px;margin-right:64px;">Quantity</label>
                            <label class="trans_label" style="width:118px;margin-right:20px;">Ship Date</label>
                        </div>
                        <div class="items" id="popup_items">
                            <%
                            ArrayList itemList = TransactionRfqItemMaster.showRfqItemFromRfqKey(request.getParameter("rfqKey")); 
                            TransactionRfqItemBean transactionRfqItemBean = null;
                            GlobalProductItemBean gpib=null;
                            for (int i = 0; i < itemList.size(); i++) {
                                transactionRfqItemBean = (TransactionRfqItemBean) itemList.get(i);
                                gpib=GlobalProductItemMaster.showByItemKey(transactionRfqItemBean.getItemKey());
                            %> 
                        <div id="item1" class="item" style="width:820px;float:left;margin:0 10px 10px 40px;">
                            <input type="text" autocomplete="off" class="textbox" id="sno1" style="width:28px;margin-right:10px;" disabled="" value="<%=i+1 %>">
                            <input type="text" autocomplete="off" class="textbox" maxlength="80" id="item_name1" style="width:98px;margin-right:10px;" disabled="" value="<%=gpib.getItemName() %>">
                            <input type="text" autocomplete="off" class="textbox" maxlength="80" id="item_desc1" style="width:118px;margin-right:10px;" disabled="" value="<%=gpib.getItemDescription()%>">
                            <input type="text" autocomplete="off" class="textbox" maxlength="80" id="part_no1" style="width:58px;margin-right:10px;" disabled="" value="<%=transactionRfqItemBean.getPartNo() %>">
                            <input type="text" autocomplete="off" class="textbox" maxlength="80" id="sku_no1" style="width:58px;margin-right:10px;" disabled="" value="<%=gpib.getSKUno()%>">
                            <input type="text" autocomplete="off" class="textbox" maxlength="80" id="brcd_no1" style="width:58px;margin-right:10px;" disabled="" value="<%=transactionRfqItemBean.getBarcode() %>">
                            <input type="text" autocomplete="off" class="textbox" maxlength="12" id="quantity1" style="width:58px;" disabled="" value="<%=transactionRfqItemBean.getQuantity() %>">
                            <div class="quantity_unit" id="quantity_unit1"><%=QuantityTypeMaster.showCodeByKey(transactionRfqItemBean.getQuantityUnitKey()) %></div>
                            <input type="text" autocomplete="off" class="textbox" id="ship_date1" style="width:118px;margin-right:10px;" disabled="" value="<%=transactionRfqItemBean.getShipDate().split(" ")[0] %>">                            
                        </div>    
                            <% } %> 
                        </div>
                        <div style="width: 900px; float: left; margin-left: 40px; height: 50px;">
                            <input id="popup_add_item_btn" type="button" class="add_item general-button" value="Add Item" style="display:none;">
                        </div>
                        <%
                            String display = "none";
                            String name = "";
                            String result = "";
                            ArrayList inquiryMessageList = InquiryMaster.showAllInquiryByTypeAndKey("RFQ", request.getParameter("rfqKey"));
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
                            out.print(result);
                        %>       
                        <!--                        <div class="inquires" id="popup_inquires">
                                                </div>-->
                        <div class="new_inquire" id="rfq_popup_new_inquire"  style="display:none">
                            <div class="inquire_row">
                                <label class="inquire_by"> Inquire: </label>
                                <textarea class="inquire_det" id="new_inquire_message" name="new_inquire_message"></textarea>
                                <input type="submit" class="gen-btn-blue" style="margin-left:30px;margin-top:7px;" id="rfq_inquire_send" value="Send" onclick="$('#rfqAction').val('Inquired');
                                        return validateTransactionInquireMessage();">
                                <label class="rfq_error" style="width:300px;margin-left: 125px;margin-top: 10px;" id="inquire_error">
                                </label>
                            </div>
                        </div>
                        <div id="supplier_ctrls" class="supplier_controls">
                            <%
                            if (session.getAttribute("userKey").toString().equals(request.getParameter("supplierKey")) && (objTr.getTransactionStatus().equals("RFQ Sent") || objTr.getTransactionStatus().equals("Inquired"))){  %>
                            <input type="submit" class="gen-btn-blue" style="margin-left:30px;" onclick="$('#rfqAction').val('Accepted');" id="rfq_accept_btn" value="Accept">
                            <input type="submit" class="gen-btn-red" style="margin-left:30px;" onclick="$('#rfqAction').val('Rejected');" id="rfq_reject_btn" value="Reject">
                            <% }%>
                            <input type="button" class="gen-btn-blue" style="margin-left:30px;" id="supp_rfq_inquire_btn" value="OK" onclick="$('#rfq_popup').fadeOut();">
                            <%
                            if(!objTr.getTransactionStatus().equalsIgnoreCase("Accepted"))
                            {
                            %>
                            <input type="button" class="gen-btn-blue" style="margin-left:30px;width:130px;" id="buy_rfq_inquire_btn" value="Send Message" onclick="transactionEnquireBox();">
                            <%
                            }
                            %>
                        </div>
                        <div class="rfq_error" id="rfq_popup_form_error">
                        </div>
                    </div>
                    <div class="mCSB_scrollTools" style="position: absolute; display: none;">
                        <div class="mCSB_draggerContainer">
                            <div class="mCSB_dragger" style="position: absolute; top: 0px;" oncontextmenu="return false;">
                                <div class="mCSB_dragger_bar" style="position:relative;">
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
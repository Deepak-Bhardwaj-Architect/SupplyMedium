<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailsForVrBean"%>
<%@page import="supply.medium.home.bean.TransactionInvBean"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0048)/SupplyMedium/user_home.jsp -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="inside/jquery-ui-1.9.2.custom_spinner.css">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/jquery.mCustomScrollbar.css">
        <link rel="stylesheet" href="inside/user_home.css">
        <link rel="stylesheet" href="inside/dilbag.css">
                        <link rel="stylesheet" href="inside/rfq.css">
        <!-- Chat JS style -->
        <link rel="stylesheet" type="text/css" href="inside/jquery.chatjs.css">
        <script type="text/JavaScript" src="inside/SMNamespace.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.9.2.custom_spinner.js">
        </script>
        <script type="text/JavaScript" src="inside/filterlist.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.customSelect.js">
        </script>
        <script type="text/javascript" src="inside/jquery.mCustomScrollbar.js">
        </script>
        <script src="inside/jquery.mousewheel.min.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.dataTables.js">
        </script>
        <script type="text/JavaScript" src="inside/common.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.validate.js">
        </script>
        <script type="text/JavaScript" src="inside/additional-methods.js">
        </script>
        <script type="text/JavaScript" src="inside/dropdownfiller.js">
        </script>
        <script type="text/JavaScript" src="inside/SMNamespace(1).js">
        </script>
        <script type="text/JavaScript" src="inside/footer.js">
        </script>
        <script type="text/JavaScript" src="inside/ajax_loader.js">
        </script>
        <!-- ChatJS and dependencies -->
        <script src="inside/jquery.chatjs.longpollingadapter.js" type="text/javascript">
        </script>
        <script src="inside/jquery.autosize.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.activity-indicator-1.0.0.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.chatjs.js" type="text/javascript">
        </script>
        <script type="text/JavaScript" src="inside/user_home.js">
        </script>
        <script type="text/JavaScript" src="inside/dilbag.js">
        </script>
        <title>Supply Medium</title>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
    </head>
    <body onLoad="lockUnlock('webkrit_content_loader');">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <link rel="stylesheet" href="inside/transaction.css">
        <link rel="stylesheet" href="inside/tablestyle.css">
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <div class="pagetitlecontainer">
            <!-- Page header -->
            <div class="pagetitle">Transaction</div>
        </div>
        <div class="page">
            <div class="contentcontainer" style="min-height:709px;">
                <div id="trans_super_container" style="">
                    <div class="main_tab_container">
                        <!-- This is the main tab bar container -->
                        <div id="rfq_tab" class="main_tab_unselect">
                            <!-- This is the RFQ tab -->
                            <a href="transactions.jsp" class="white">RFQ</a>
                        </div>
                        <div id="quote_tab" class="main_tab_unselect">
                            <!-- This is the Quotation tab -->
                            <a href="transactionsQuote.jsp" class="white">Quote</a>
                        </div>
                        <div id="po_tab" class="main_tab_unselect">
                            <!-- This is the Purchase order tab -->
                            <a href="transactionsPurchaseOrder.jsp" class="white">Purchase Order</a>
                        </div>
                        <div id="invoice_tab" class="main_tab_unselect">
                            <!-- This is the Invoice tab -->
                            <a href="transactionsInvoice.jsp" class="white">Invoice</a>
                        </div>
                        <div id="payment_tab" class="main_tab_select">
                            <!-- This is the Invoice tab -->
                            <a href="transactionsPayment.jsp" class="white">Payment</a>
                        </div>
                    </div>
                    <div class="main_tab_sep">
                        <!-- This is the seperator div  -->
                    </div>
                    <div id="trans_content" class="trans_content" style="display: block;">
                        <link rel="stylesheet" href="/SupplyMedium/Views/Transaction/Invoice/css/invoice.css">
                        <form name="paypalForm" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_blank">
                            <input type="hidden" name="cmd" value="_xclick">
                            <input type="hidden" name="business" value="dsdilbag345@gmail.com">
                            <input type="hidden" name="password" value="dilbagdssingh">
                            <input type="hidden" name="custom" value="1123">
                            <input type="hidden" name="item_name" value="Plan">
                            <input type="hidden" id="amnt" name="amount" value="0">
                            <input type="hidden" name="rm" value="1">
                            <input type="hidden" id="rtrn" name="return" value="/SupplyMedium/paypal?result=2824663706424">
                            <input type="hidden" id="cncl_rtrn" name="cancel_return" value="/SupplyMedium/paypal?result=1412331853212">
                            <input type="hidden" name="cert_id" value="API Singature">
                        </form>
                        <div class="sub_tab_container" id="invoice_submenu">
                            <!-- This is Invoice inner tab bar container -->
                            <div class="highlight" id="invoice_request_tab">Payments</div>
                            <!-- My Request tab -->
                        </div>
                        <div id="invoice_request_content" style="position:relative;">
                            <!-- This is the invoice queue. -->
                            <div class="DT_border">
                            </div>
                            <div id="invoice_list_wrapper" class="dataTables_wrapper" role="grid">
                                <div class="dataTables_filter" id="invoice_list_filter">
                                    <label>Search <input type="text" autocomplete="off" aria-controls="invoice_list">
                                    </label>
                                </div>
                                <div class="fixed_height">
                                    <table id="invoice_list" style="width: 997px;padding-left:1px;" class="dataTable" aria-describedby="invoice_list_info">
                                        <thead>
                                            <tr role="row">
                                                <th class="rowBorder sorting_asc" style="border-left-width: 1px; border-left-style: solid; border-left-color: rgb(200, 200, 200); width: 20px;" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-sort="ascending" aria-label=": activate to sort column descending">
                                                </th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-label="Invoice ID: activate to sort column ascending" style="width: 78px;">Invoice ID</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-label="Date: activate to sort column ascending" style="width: 104px;">Date</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-label="Company: activate to sort column ascending" style="width: 129px;">Company</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-label="Phone: activate to sort column ascending" style="width: 107px;">Phone</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-label="Email: activate to sort column ascending" style="width: 174px;">Email</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-label="Payment: activate to sort column ascending" style="width: 113px;">Payment</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending" style="width: 104px;">Status</th>
                                                <th class="rowBorder sorting" style="border-right-width: 1px; border-right-style: solid; border-right-color: rgb(200, 200, 200); width: 49px;" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-label="Open: activate to sort column ascending">Open</th>
                                            </tr>
                                        </thead>
                                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                                            <%
                                                ArrayList al = TransactionInvMaster.showByTypeCompanyKey("Invoice", companyKey);
                                                String stl = "";
                                                String img = "";
                                                String cname = "";
                                                String cphone = "";
                                                String cemail = "";
                                                String cstate = "";
                                                String cstatus = "";
                                                String outsider[] = null;
                                                TransactionInvBean trb = null;
                                                CompanyDetailsForVrBean cb = null;
                                                for (int i = 0; i < al.size(); i++) {
                                                    outsider = null;
                                                    cname = "";
                                                    cphone = "";
                                                    cemail = "";
                                                    cstate = "";
                                                    cstatus = "";
                                                    if (i % 2 == 0) {
                                                        stl = "class='even'";
                                                    } else {
                                                        stl = "class='odd'";
                                                    }
                                                    trb = (TransactionInvBean) al.get(i);
                                                    if(trb.getIs_inv_paid().equalsIgnoreCase("yes"))
                                                    {
                                                    if (companyKey.equals(trb.getCompany_key_from())) {
                                                        img = "trans_sent_icon.png";
                                                        if (trb.getInv_is_outside().equalsIgnoreCase("yes")) {
                                                            outsider = trb.getInv_is_outside_address().split("@#@#@");
                                                            cname = outsider[0];
                                                            cphone = "Outside Transaction";
                                                            cemail = outsider[6];
                                                            cstate = StateMaster.showStateFromKey(outsider[2]);
                                                        } else if (trb.getInv_is_outside().equalsIgnoreCase("no")) {
                                                            cb = CompanyMaster.showCompanyDetailsForVrProcess(trb.getCompany_key_to());
                                                            cname = cb.getCompanyName();
                                                            cphone = cb.getPhone();
                                                            cemail = cb.getEmail();
                                                            cstate = StateMaster.showStateFromKey(cb.getState());
                                                        }
                                                        cstatus = trb.getInv_trans_status();
                                                        if (cstatus.equals("Accepted")) {
                                                            cstatus = "Inoive Accepted";
                                                        } else if (cstatus.equals("Inquired")) {
                                                            cstatus = "Invoice Inquired";
                                                        } else {
                                                            cstatus = "Invoice Generated";
                                                        }
                                                        if (trb.getIs_inv_paid().equalsIgnoreCase("yes")) {
                                                            cstatus="Payment Done";
                                                        }
                                                    } else if (companyKey.equals(trb.getCompany_key_to())) {
                                                        img = "trans_received_icon.png";
                                                        cname = trb.getBuyerCompanyName();
                                                        cphone = trb.getBuyerPhone();
                                                        cemail = trb.getBuyerEmail();
                                                        cstate = trb.getBuyerState();
                                                        cstatus = trb.getInv_trans_status();
                                                        if (cstatus.equals("Accepted")) {
                                                            if (trb.getIs_inv_paid().equalsIgnoreCase("no")) {
                                                                cstatus = "<a style='text-decoration: underline;color:blue;' onclick=\"javascript:$('#rtrn').val($('#rtrn').val()+'?id="+trb.getTrans_key()+"');$('#item_name').val('SupplyMedium Invoice No: "+trb.getInv_trans_rqf_key()+"');$('#amnt').val('"+trb.getInv_total_billing_amount()+"');$('#rtrn').val($('#rtrn').val()+'?invoiceKey="+trb.getInv_trans_rqf_key()+"');$('#paypalForm').submit();\">Make a Payment</a>";
                                                            } else {
                                                                cstatus = "Payment Done";
                                                            }
                                                        } else if (cstatus.equals("Inquired")) {
                                                            cstatus = "Invoice Inquired";
                                                        } else {
                                                            cstatus = "Approve Invoice";
                                                        }
                                                    }
                                            %>
                                            <tr <%=stl%> onclick="$('#invoice_popup').fadeIn();
                                                    showInvDetailInPopup('<%=trb.getTrans_key()%>', '<%=trb.getCompany_key_to()%>', '<%=trb.getCompany_key_from()%>');
                                                    $('#toKey').val('<%=trb.getCompany_key_to()%>');
                                                    showQuotePopupData(<%=trb.getTrans_key()%>,<%=companyKey%>,<%=trb.getCompany_key_from()%>);">
                                                <td class="rowBorder"><img src="inside/<%=img%>"/></td>
                                                <td class="rowBorder"><%=trb.getInv_trans_rqf_key()%></td>
                                                <td class="rowBorder"><%=trb.getInv_created_on().split(" ")[0]%></td>
                                                <td class="rowBorder"><%=cname%></td>
                                                <td class="rowBorder"><%=cphone%></td>
                                                <td class="rowBorder"><%=cemail%></td>
                                                <td class="rowBorder"><%=StateMaster.showStateFromKey(cstate)%></td>
                                                <td class="rowBorder"><%=cstatus%></td>
                                                <td class="rowBorder"><a target="_blank" href="cropData/company-<%=companyKey%>/transaction/<%=trb.getInv_trans_rqf_key()%>.pdf"><img alt="Open" title="Open" src="inside/open.png"></a></td>
                                            </tr>
                                            <%
                                                }
                                            }
                                            if (al.size() == 0) {
                                            %>
                                            <tr class="odd">
                                                <td valign="top" colspan="9" class="dataTables_empty">No Request found</td>
                                            </tr>
                                            <%
                                            }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="dataTables_info" id="invoice_list_info">Showing 0 to 0 of 0 entries</div>
                                <div class="dataTables_paginate paging_full_numbers" id="invoice_list_paginate">
                                    <a tabindex="0" class="first paginate_button paginate_button_disabled" id="invoice_list_first">First</a>
                                    <a tabindex="0" class="previous paginate_button paginate_button_disabled" id="invoice_list_previous">Previous</a>
                                    <span>
                                    </span>
                                    <a tabindex="0" class="next paginate_button paginate_button_disabled" id="invoice_list_next">Next</a>
                                    <a tabindex="0" class="last paginate_button paginate_button_disabled" id="invoice_list_last">Last</a>
                                </div>
                            </div>
                        </div>
                        <div id="invoice_form_content" style="display:none;" class="invoice_form_content">
                            <!-- This is the invoice form details -->
                            <div class="to_comp">
                                <label class="label" style="padding-left:20px;width:65px;color:white;line-height:25px;"> Sold To:</label>
                                <input type="text" autocomplete="off" placeholder="Search Registered Vendor" id="to_company" style="width:900px;" class="textbox" onKeyUp="getRegVendor();">
                            </div>
                            <input type="hidden" id="selected_ven_key">
                            <div class="invoice_form">
                                <div id="ven_search_result" class="com_search_result" style="display:none;left:84px;top:-8px;">
                                </div>
                                <input type="hidden" id="form_trans_id" value="-1">
                                <div class="invoice_buyer_det">
                                    <div class="sub_heading">Invoice Details</div>
                                    <div class="trans_row" style="float:left;">
                                        <label class="trans_label"> Invoice Payment Due Date </label>
                                        <input type="text" autocomplete="off" class="textbox hasDatepicker" id="invoice_due_date" readonly="">
                                    </div>
                                    <div class="trans_row" style="float:left;">
                                        <label class="trans_label"> Purchase Order Number </label>
                                        <input type="text" autocomplete="off" class="textbox" id="po_no">
                                    </div>
                                    <div class="trans_row" style="float:left;">
                                        <label class="trans_label"> Please Remit To </label>
                                        <input type="text" autocomplete="off" class="textbox_disable" id="buyer_name" disabled="">
                                    </div>
                                    <div class="trans_row">
                                        <label class="trans_label"> Country </label>
                                        <input type="text" autocomplete="off" class="textbox_disable" id="buyer_country" disabled="">
                                    </div>
                                    <div class="trans_row">
                                        <label class="trans_label"> State/Province </label>
                                        <input type="text" autocomplete="off" class="textbox_disable" id="buyer_state" disabled="">
                                    </div>
                                    <div class="trans_row">
                                        <label class="trans_label"> City </label>
                                        <input type="text" autocomplete="off" class="textbox_disable" id="buyer_city" disabled="">
                                    </div>
                                    <div class="trans_row">
                                        <label class="trans_label"> Address </label>
                                        <input type="text" autocomplete="off" class="textbox_disable" id="buyer_addr" disabled="">
                                    </div>
                                    <div class="trans_row">
                                        <label class="trans_label"> Zip Code/Postal Code </label>
                                        <input type="text" autocomplete="off" class="textbox_disable" id="buyer_zipcode" disabled="">
                                    </div>
                                </div>
                                <div class="addr_sep">
                                </div>
                                <div class="invoice_supplier_det">
                                    <div class="sub_heading">Buyer Details</div>
                                    <div class="trans_row">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="non_po_invoice">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="non_po_invoice" class="trans_label" style="line-height:19px;margin-left:5px;">Non P.O Invoice</label>
                                    </div>
                                    <div class="trans_row">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="outside_supplier">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="outside_supplier" class="trans_label" style="line-height:19px;margin-left:5px;">Outside Buyer</label>
                                    </div>
                                    <div class="supplier_address" style="width:100%;height:300px;">
                                        <div class="trans_row">
                                            <label class="trans_label"> Country </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_country" disabled="">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> State/Province </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_state" disabled="">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> City </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_city" disabled="">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> Address </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_addr" disabled="">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> Zip Code/Postal Code </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_zipcode" disabled="">
                                        </div>
                                    </div>
                                    <div class="outside_sup_email_content" style="width:100%;height:300px;display:none">
                                        <div class="trans_row" style="margin-top:100px;">
                                            <label class="trans_label"> Email </label>
                                            <input type="text" autocomplete="off" class="textbox" id="email">
                                        </div>
                                    </div>
                                    <div class="trans_row">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="is_diff_addr">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="is_diff_addr" class="trans_label" style="line-height:19px;margin-left:5px;width:360px;line-height:15px;">In case the shipping address is different than one given above</label>
                                    </div>
                                    <div class="trans_row">
                                        <label class="trans_label"> Email </label>
                                        <input type="text" autocomplete="off" class="textbox" id="diff_addr_email" disabled="">
                                    </div>
                                </div>
                                <div class="items_head">
                                    <label class="trans_label" style="width:40px;margin-right:10px;">S.No</label>
                                    <label class="trans_label" style="width:110px;margin-right:10px;">Item Description</label>
                                    <label class="trans_label" style="width:100px;margin-right:10px;">Batch No/SKU No</label>
                                    <label class="trans_label" style="width:100px;margin-right:10px;">Barcode</label>
                                    <label class="trans_label" style="width:105px;margin-right:10px;">Quantity Ordered</label>
                                    <label class="trans_label" style="width:105px;margin-right:10px;">Quantity Shipped</label>
                                    <label class="trans_label" style="width:95px;margin-right:10px;">Price per unit</label>
                                    <label class="trans_label" style="width:60px;margin-right:10px;">Multiplier</label>
                                    <label class="trans_label" style="width:118px;margin-right:10px;">Total</label>
                                </div>
                                <div class="items" id="items">
                                </div>
                                <div style="width:100%;float:left;margin-left:40px;height:50px;">
                                    <input id="add_item_btn" type="button" class="add_item general-button" value="Add Item">
                                </div>
                                <div class="price_det" id="price_det">
                                    <div class="price_det_content">
                                        <label class="price_det_lbl" id="tot_list_price_lbl"> Total List Price: </label>
                                        <label class="price_det_lbl" id="tot_list_price_amt"> 0 </label>
                                    </div>
                                    <div class="price_det_content">
                                        <label class="price_det_lbl" id="tax_lbl"> Tax in Percentage: </label>
                                        <label class="price_det_lbl" id="tax_amt" style="min-width:35px;width:auto;">10.0</label>
                                        <label>%</label>
                                    </div>
                                    <div class="price_det_content">
                                        <label class="price_det_lbl" id="frei_lbl"> Freight &amp; Handling: </label>
                                        <label class="price_det_lbl" id="frei_hand_amt"> 10  </label>
                                    </div>
                                    <div class="price_det_content">
                                        <label class="price_det_lbl" id="tot_price_lbl"> Total Price: </label>
                                        <label class="price_det_lbl" id="tot_price_amt">
                                        </label>
                                    </div>
                                </div>
                                <div class="sub_heading" style="padding-left:40px;">Bill of Landing Information</div>
                                <div class="shipping_det_head">
                                    <label class="trans_label" style="width: 200px;margin-right: 9px;">Carrier</label>
                                    <label class="trans_label" style="width: 112px;margin-right: 17px;">Bill of Landing No.</label>
                                    <label class="trans_label" style="width:98px;margin-right: 63px;">Freight Weight</label>
                                    <label class="trans_label" style="width:98px;margin-right:25px;">Date Shipped</label>
                                </div>
                                <div class="shipping_det_content">
                                    <div style="float:left">
                                        <input type="text" autocomplete="off" class="textbox" id="carrier">
                                        <option value="" selected="selected">--Select Carrier--</option>
                                        <option value="First Flight">First Flight</option>
                                        <option value="Professional courier">Professional courier</option>
                                        </input>
                                    </div>
                                    <input type="text" autocomplete="off" class="textbox" id="bill_of_land" style="margin-left:20px;width:100px">
                                    <input type="text" autocomplete="off" class="textbox" id="freight_weight" style="margin-left:20px;width:80px;">
                                    <div class="quantity_unit" id="quantity_freight_unit">KG</div>
                                    <div class="quan_units" id="units_quantity_freight_unit" style="display:none;left:470px;">
                                    </div>
                                    <input type="text" autocomplete="off" class="textbox hasDatepicker" id="date_shipped" style="margin-left:20px;width:100px;" readonly="">
                                </div>
                                <div style="width:100%;float:left;margin-left:40px;height:50px;" id="invoice_terms_container">
                                    <div class="checkContainer">
                                        <input type="checkbox" class="checkbox" id="invoice_terms_cond">
                                        <div>
                                        </div>
                                    </div>
                                    <label for="invoice_terms_cond" class="trans_label" style="line-height:19px;margin-left:5px;cursor:pointer;text-decoration:underline;" id="invoice_tc_link">Accept terms &amp; conditions</label>
                                </div>
                                <div class="invoice_form_btns">
                                    <input type="button" class="gen-btn-Gray" style="margin-right:50px;" value="Reset" onClick="resetInvoiceForm()">
                                    <input type="button" class="gen-btn-blue" value="Send" id="add_invoice_btn" onClick="validateInvoice();">
                                </div>
                                <div class="invoice_error" id="invoice_form_error">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <title>Insert title here</title>
        <div style="display: none; z-index: 1000;" class="Custome_Popup_Window" id="tc_content_popup">
            <div class="Cus_Popup_Outline add_content_outline">
                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                    <div style="padding: 0px 0px 0px 15px; float: left">Content</div>
                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" id="tc_content_close">
                    </div>
                </div>
                <textarea class="tc_content" id="tc_content" disabled="">
                </textarea>
                <input type="button" class="gen-btn-blue" value="Accept" style="margin-left: 180px;" id="tc_accept_btn">
                <input type="button" class="gen-btn-red" value="Reject" style="margin-left: 90px;" id="tc_reject_btn">
                <input type="hidden" id="check_box_id">
            </div>
        </div>
        <title>Toast Window </title>
        <link rel="stylesheet" href="inside/Cus_Toast.css">
        <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
        <div id="Toast_Window" style="display:none;">
            <p class="Toast_Data">
            </p>
        </div>
        <%@include file="_footer.jsp"%>
        <div>
        </div>
        <title>Insert title here</title>
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/popup_warning.css">
        <div id="warning_container" style="display: none;">
            <div id="warning_popup">
                <div id="war_head">
                    <label id="war_head_title">Warning</label>
                </div>
                <div id="war_body">
                    <label id="war_message">
                    </label>
                    <div id="war_btns">
                        <input type="button" id="okbtn" style="float:left;margin-right:30px;" class="pop-button pop-button-White" value="Yes">
                        <input id="Popup_Cancel" style="float:left;" type="button" class="pop-button pop-button-White" value="No">
                    </div>
                </div>
            </div>
        </div>
    </div>
        <%@include file="_invite.jsp"%>
    <script type="text/JavaScript" src="inside/restrict_menu.js">
    </script>
    <script>


                                        $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt_fieldvalidator.js");
                                        $.getScript("/SupplyMedium/Views/Registration/js/regvalidator.js");
                                        $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt.js", function(data, textStatus, jqxhr) {
                                            userOnload();

                                        });

    </script>
</body>
</html>
<%@page import="supply.medium.home.bean.CurrencyBean"%>
<%@page import="supply.medium.home.database.CurrencyMaster"%>
<%@page import="supply.medium.home.database.QuantityTypeMaster"%>
<%@page import="supply.medium.home.bean.QuantityTypeBean"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailsForVrBean"%>
<%@page import="supply.medium.home.bean.TransactionInvBean"%>
<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%@page import="supply.medium.home.bean.CountryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.database.CompanyDetailAddressMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailAddressBean"%>
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
        <link rel="stylesheet" href="inside/invoice.css">
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
        <script>
            $(function() {
                $( "#invoice_due_date").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
                $( "#date_shipped").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
            });
        </script>
        <style>
            body 
            {
                font-size: 80%;
            }
        </style>
        <title>Supply Medium</title>  
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
    </head>
    <body onLoad="lockUnlock('webkrit_content_loader');<% if (request.getParameter("generateinvoice") != null) {%>swapWkTabs2('invoice_form_tab', 'invoice_request_tab');
        $('#invoice_form_content').show();
        $('#invoice_request_content').hide();<% }%>">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <form id="paypalForm" name="paypalForm" action="https://www.paypal.com/cgi-bin/webscr" method="post">
        <input type="hidden" name="cmd" value="_xclick">
        <input type="hidden" name="business" value="supplymedium@gmail.com">
        <input type="hidden" name="lc" value="US">
        <input type="hidden" name="button_subtype" value="services">
        <input type="hidden" name="currency_code" value="USD">
        <input type="hidden" id="item_name" name="item_name" value="" />
        <input type="hidden" id="amnt" name="amount" value="0"/>
        <input type="hidden" name="bn" value="PP-BuyNowBF:btn_paynowCC_LG.gif:NonHosted">
        <input type="hidden" id="rtrn" name="return" value="<%=SmProperties.emailUrl%>transactionsPayments.jsp"/>
        <input type="hidden" id="cncl_rtrn" name="cancel_return" value="<%=SmProperties.emailUrl%>transactionsInvoice.jsp" />
    </form>
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
                        <div id="invoice_tab" class="main_tab_select">
                            <!-- This is the Invoice tab -->
                            <a href="transactionsInvoice.jsp" class="white">Invoice</a>
                        </div>
                        <div id="payment_tab" class="main_tab_unselect">
                            <!-- This is the Invoice tab -->
                            <a href="transactionsPayment.jsp" class="white">Payment</a>
                        </div>
                    </div>
                    <div class="main_tab_sep">
                        <!-- This is the seperator div  -->
                    </div>
                    <div id="trans_content" class="trans_content" style="display: block;">
                        <link rel="stylesheet" href="/SupplyMedium/Views/Transaction/Invoice/css/invoice.css">
                        <!--                        <div id="chs_usr_adrs" class="rfq_post_confirmation" style="height:auto;margin-left:15%;width:700px;">
                                                    <center>
                                                        <p>Choose Shipping Address<br>
                                                            <br>
                                                        </p>
                                                        <table style="width: 698px;padding-left:1px;" id="cmpny_adrs_lst">
                                                        </table>
                                                        <br>
                                                        <br>
                                                        <input type="button" class="gen-btn-blue" value="Close" onClick="if (document.getElementById('diff_address').checked === true) {document.getElementById('selected_address').value = document.getElementById('diffaddress').value + ' , ' + document.getElementById('diffcity').value + ' , ' + document.getElementById('diffstate').value + ' , ' + document.getElementById('diffcountry').value + ' , ' + document.getElementById('diffpincode').value;}$('#chs_usr_adrs').fadeOut();">
                                                    </center>
                                                </div>-->
                        <div class="sub_tab_container" id="invoice_submenu">
                            <!-- This is Invoice inner tab bar container -->
                            <div class="highlight" id="invoice_request_tab" onclick="swapWkTabs2('invoice_request_tab', 'invoice_form_tab');
                                $('#invoice_request_content').show();
                                $('#invoice_form_content').hide();">My Request</div>
                            <!-- My Request tab -->
                            <%
                                        if (companyTypeMy.equals("Buyers/Suppliers") || companyTypeMy.equals("Supplier")) {
                            %>
                            <div class="normal" id="invoice_form_tab" onclick="swapWkTabs2('invoice_form_tab', 'invoice_request_tab');
                                     $('#invoice_form_content').show();
                                     $('#invoice_request_content').hide();">Generate Invoice</div>
                            <%                                        }
                            %>
                            <!-- invoice Form tab -->
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
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="invoice_list" rowspan="1" colspan="1" aria-label="State: activate to sort column ascending" style="width: 113px;">State</th>
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
                                                                        cstatus = "Invoice Accepted";
                                                                    } else if (cstatus.equals("Inquired")) {
                                                                        cstatus = "Invoice Inquired";
                                                                    } else {
                                                                        cstatus = "Invoice Generated";
                                                                    }
                                                                    if (trb.getIs_inv_paid().equalsIgnoreCase("yes")) {
                                                                        cstatus = "Payment Done";
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
                                                                            cstatus = "<a style='text-decoration: underline;color:blue;' onclick=\"javascript:$('#rtrn').val($('#rtrn').val()+'?id=" + trb.getTrans_key() + "');$('#item_name').val('SupplyMedium Invoice No: " + trb.getInv_trans_rqf_key() + "');$('#amnt').val('" + trb.getInv_total_billing_amount() + "');$('#rtrn').val($('#rtrn').val()+'?invoiceKey=" + trb.getInv_trans_rqf_key() + "');$('#paypalForm').submit();\">Make a Payment</a>";
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
                                                <tr <%=stl%><% if (trb.getTrans_key().equals(request.getParameter("nid"))) {
                                                                                                            out.println("style='background:#c9c9c9;'");
                                                                                                        }%>>
                                                <td class="rowBorder" onclick="$('#invoice_popup').fadeIn();
                                                    showInvDetailInPopup('<%=trb.getTrans_key()%>', '<%=trb.getCompany_key_to()%>', '<%=trb.getCompany_key_from()%>');
                                                    $('#toKey').val('<%=trb.getCompany_key_to()%>');
                                                    showQuotePopupData(<%=trb.getTrans_key()%>,<%=companyKey%>,<%=trb.getCompany_key_from()%>);"><img src="inside/<%=img%>"/></td>
                                                <td class="rowBorder" onclick="$('#invoice_popup').fadeIn();
                                                    showInvDetailInPopup('<%=trb.getTrans_key()%>', '<%=trb.getCompany_key_to()%>', '<%=trb.getCompany_key_from()%>');
                                                    $('#toKey').val('<%=trb.getCompany_key_to()%>');
                                                    showQuotePopupData(<%=trb.getTrans_key()%>,<%=companyKey%>,<%=trb.getCompany_key_from()%>);"><%=trb.getInv_trans_rqf_key()%></td>
                                                <td class="rowBorder" onclick="$('#invoice_popup').fadeIn();
                                                    showInvDetailInPopup('<%=trb.getTrans_key()%>', '<%=trb.getCompany_key_to()%>', '<%=trb.getCompany_key_from()%>');
                                                    $('#toKey').val('<%=trb.getCompany_key_to()%>');
                                                    showQuotePopupData(<%=trb.getTrans_key()%>,<%=companyKey%>,<%=trb.getCompany_key_from()%>);"><%=trb.getInv_created_on().split(" ")[0]%></td>
                                                <td class="rowBorder" onclick="$('#invoice_popup').fadeIn();
                                                    showInvDetailInPopup('<%=trb.getTrans_key()%>', '<%=trb.getCompany_key_to()%>', '<%=trb.getCompany_key_from()%>');
                                                    $('#toKey').val('<%=trb.getCompany_key_to()%>');
                                                    showQuotePopupData(<%=trb.getTrans_key()%>,<%=companyKey%>,<%=trb.getCompany_key_from()%>);"><%=cname%></td>
                                                <td class="rowBorder" onclick="$('#invoice_popup').fadeIn();
                                                    showInvDetailInPopup('<%=trb.getTrans_key()%>', '<%=trb.getCompany_key_to()%>', '<%=trb.getCompany_key_from()%>');
                                                    $('#toKey').val('<%=trb.getCompany_key_to()%>');
                                                    showQuotePopupData(<%=trb.getTrans_key()%>,<%=companyKey%>,<%=trb.getCompany_key_from()%>);"><%=cphone%></td>
                                                <td class="rowBorder" onclick="$('#invoice_popup').fadeIn();
                                                    showInvDetailInPopup('<%=trb.getTrans_key()%>', '<%=trb.getCompany_key_to()%>', '<%=trb.getCompany_key_from()%>');
                                                    $('#toKey').val('<%=trb.getCompany_key_to()%>');
                                                    showQuotePopupData(<%=trb.getTrans_key()%>,<%=companyKey%>,<%=trb.getCompany_key_from()%>);"><%=cemail%></td>
                                                <td class="rowBorder" onclick="$('#invoice_popup').fadeIn();
                                                    showInvDetailInPopup('<%=trb.getTrans_key()%>', '<%=trb.getCompany_key_to()%>', '<%=trb.getCompany_key_from()%>');
                                                    $('#toKey').val('<%=trb.getCompany_key_to()%>');
                                                    showQuotePopupData(<%=trb.getTrans_key()%>,<%=companyKey%>,<%=trb.getCompany_key_from()%>);"><%=StateMaster.showStateFromKey(cstate)%></td>
                                                <td class="rowBorder"><%=cstatus%></td>
                                                <td class="rowBorder" onclick="window.location.href='';"><a target="_blank" href="cropData/company-<%=companyKey%>/transaction/<%=trb.getInv_trans_rqf_key()%>.pdf"><img alt="Open" title="Open" src="inside/open.png"></a></td>
                                            </tr>
                                            <%
                                                        }
                                                        if (al.size() == 0) {
                                            %>
                                            <tr class="odd">
                                                <td valign="top" colspan="9" class="dataTables_empty">No Request found</td>
                                            </tr>
                                            <%                                                        }
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
                            <form method="post" action="TransInv" onsubmit="return invoiceFormValidation();">
                                <!-- This is the invoice form details -->
                                <%                                CompanyDetailAddressBean cdab = (CompanyDetailAddressBean) (CompanyDetailAddressMaster.showCompanyDetailAddress(companyKey)).get(0);
                                %>
                                <div class="to_comp">
                                    <label class="label" style="padding-left:20px;width:65px;color:white;line-height:25px;"> Sold To:</label>
                                    <input type="text" autocomplete="off" placeholder="Search Registered Vendor" id="to_company" style="width:900px;" class="textbox" onKeyUp="showCompaniesVendorSearch(this.value, 'Buyer');">
                                </div>
                                <input type="hidden" id="selectedVendorKey" name="companyKeyTo" value="0">
                                <div class="invoice_form">
                                    <div id="ven_search_result" class="com_search_result" style="display:none;left:84px;top:-8px;">
                                    </div>
                                    <input type="hidden" id="form_trans_id" value="-1">
                                    <div class="invoice_buyer_det">
                                        <div class="sub_heading">Invoice Details</div>
                                        <div class="trans_row" style="float:left;">
                                            <label class="trans_label"> Invoice Payment Due Date </label>
                                            <input type="text" autocomplete="off" class="textbox" readonly name="invoice_due_date" id="invoice_due_date">
                                        </div>
                                        <div class="trans_row" style="float:left;">
                                            <label class="trans_label"> Purchase Order Number </label>
                                            <input type="text" autocomplete="off" class="textbox" id="po_no" name="po_no">
                                        </div>
                                        <div class="trans_row" style="float:left;">
                                            <label class="trans_label"> Please Remit To </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_name" value="<%=cdab.getCompanyName()%>" disabled="">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> Country </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_country" value="<%=CountryMaster.showCountryFromKey(cdab.getCountry())%>" disabled="">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> State/Province </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_state" value="<%=StateMaster.showStateFromKey(cdab.getState())%>" disabled="">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> City </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_city" value="<%=cdab.getCity()%>" disabled="">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> Address </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_addr" value="<%=cdab.getAddress()%>" disabled="">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> Zip Code/Postal Code </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="supplier_zipcode" value="<%=cdab.getZipcode()%>" disabled="">
                                        </div>
                                    </div>
                                    <div class="addr_sep">
                                    </div>
                                    <div class="invoice_supplier_det">
                                        <div class="sub_heading">Buyer Details</div>
                                        <div class="trans_row">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="non_po_invoice" name="non_po_invoice">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="non_po_invoice" class="trans_label" style="line-height:19px;margin-left:5px;">Non P.O Invoice</label>
                                        </div>
                                        <div class="trans_row">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox"  name="outside_supplier" id="outside_supplier" onclick="if (this.checked) {
                                                    $('#insideBuyer').hide();
                                                    $('#outsideBuyer').show();
                                                    $('#to_company').val('');
                                                    $('#selectedVendorKey').val(0);
                                                } else {
                                                    $('#insideBuyer').show();
                                                    $('#outsideBuyer').hide();
                                                }
                                                       ">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="outside_supplier" class="trans_label" style="line-height:19px;margin-left:5px;">Outside Buyer</label>
                                        </div>
                                        <div class="supplier_address" id="insideBuyer" style="width:100%;height:300px;">
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
                                        <div class="outside_sup_email_content" id="outsideBuyer" style="width:100%;height:300px;display:none">
                                            <div class="trans_row">
                                                <label class="trans_label"> Name </label>
                                                <input type="text" autocomplete="off" class="textbox" name="otsd_splr_nm" id="otsd_splr_nm">
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> Country </label>
                                                <SELECT name="outsideSupplierCountry" onChange="fetchState(this.value);" style="width:187px;height:28px;" class ="customSelect required">
                                                    <OPTION value="">--Select 
                                                        Country--</OPTION>
                                                        <%                                    ArrayList countryList = CountryMaster.showAll();
                                                                    CountryBean scb = null;
                                                                    for (int i = 0; i < countryList.size(); i++) {
                                                                        scb = (CountryBean) countryList.get(i);
                                                        %>
                                                    <option value="<%=scb.getCountryKey()%>"><%=scb.getCountryName()%></option>
                                                    <%
                                                                }
                                                    %>
                                                </SELECT>
                                                <label for="countryregion_0" generated="true" class="error" id="countryregion_0err">
                                                </label>
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> State/Province </label>
                                                <div id="select_0_container">
                                                    <select id="state_0" name="state_0" class="selectbox hasCustomSelect" style="-webkit-appearance: menulist-button; width: 188px; position: absolute; opacity: 0; height: 28px; font-size: 12px;">
                                                        <option>--Select State--</option>
                                                    </select>
                                                    <span class="customSelect selectbox" style="display: inline-block;">
                                                        <span class="customSelectInner" style="width: 142px; display: inline-block;">--Select State--</span>
                                                    </span>
                                                    <label for="state_0" generated="true" class="error" id="state_0err">
                                                    </label>
                                                </div>
                                                <input style="display:none;margin-left:200px;margin-top:-27px;" type="text" autocomplete="off" name="state_text" class="textbox" id="state_text">
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> City </label>
                                                <input type="text" autocomplete="off" class="textbox" name="otsd_splr_cty" id="otsd_splr_cty">
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> Address </label>
                                                <input type="text" autocomplete="off" class="textbox" name="otsd_splr_adrs" id="otsd_splr_adrs">
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> Zip Code/Postal Code </label>
                                                <input type="text" autocomplete="off" class="textbox" name="otsd_splr_zpcd" id="otsd_splr_zpcd">
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> Email </label>
                                                <input type="text" autocomplete="off" class="textbox" name="email" id="email">
                                            </div>
                                        </div>
                                        <div class="trans_row">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" name="is_diff_addrss" id="is_diff_addr" onClick="if (this.checked) {
                                                //                                                            $('#chs_usr_adrs').fadeIn();
                                                //                                                            get_shiping_address();
                                                $('#diff_eml').fadeIn();
                                            } else {
                                                //                                                            $('#chs_usr_adrs').fadeOut();
                                                $('#diff_eml').fadeOut();
                                            }">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="is_diff_addr" class="trans_label" style="line-height:19px;margin-left:5px;width:360px;line-height:15px;">In case email <!--the shipping--> address is different than one given above</label>
                                        </div>
                                        <div class="trans_row" id="diff_eml" style="display:none;">
                                            <label class="trans_label"> Email </label>
                                            <input type="text" autocomplete="off" class="textbox" name="diff_addr_email" id="diff_addr_email" >
                                        </div>
                                    </div>
                                    <div class="items_head">
                                        <label class="trans_label" style="width:40px;margin-right:10px;">S.No</label>
                                        <label class="trans_label" style="width:100px;margin-right:10px;">Item Name</label>
                                        <label class="trans_label" style="width:130px;margin-right:10px;">Description</label>
                                        <label class="trans_label" style="width:58px;margin-right:10px;">Part Nn</label>
                                        <label class="trans_label" style="width:60px;margin-right:10px;">SKU No</label>
                                        <label class="trans_label" style="width:55px;margin-right:10px;">Barcode</label>
                                        <label class="trans_label" style="width:76px;margin-right:10px;">Qty Ordered</label>
                                        <label class="trans_label" style="width:80px;margin-right:10px;">Qty Shipped</label>
                                        <label class="trans_label" style="width:80px;margin-right:10px;">Price</label>
                                        <label class="trans_label" style="width:60px;margin-right:10px;">Multiplier</label>
                                        <label class="trans_label" style="width:90px;margin-right:10px;">Total</label>
                                    </div>
                                    <ol style="list-style-type: decimal;">
                                        <div class="items" id="items">
                                        </div>
                                    </ol>
                                    <div style="width:100%;float:left;margin-left:40px;height:50px;">
                                        <input id="add_item_btn" type="button" class="add_item general-button" value="Add Item" onclick="$('#invoice_add_item_popup').fadeIn();">
                                    </div>
                                    <div class="price_det" id="price_det">
                                        <div class="price_det_content">
                                            <label class="price_det_lbl" id="tot_list_price_lbl"> Total List Price: </label>
                                            <input type="text" autocomplete="off" readonly class="textbox" id="tot_list_price_amt" name="tot_list_price_amt" style="width:90px;margin-right:10px;" value="0">
                                        </div>
                                        <div class="price_det_content">
                                            <label class="price_det_lbl" id="tax_lbl"> Tax in Percentage: </label>
                                            <input type="text" autocomplete="off" name="qt_tx" class="textbox" id="qt_tx" style="width:90px;margin-right:10px;" value="10" onKeyUp="clclt_trans_amnt(this.value)">
                                            <label>%</label>
                                        </div>
                                        <div class="price_det_content">
                                            <label class="price_det_lbl" id="frei_lbl"> Freight &amp; Handling Charges: </label>
                                            <input type="text" autocomplete="off" class="textbox" id="frei_hand_amt" name="frei_hand_amt" style="width:90px;margin-right:10px;" value="0" onKeyUp="clclt_frt_amnt(this.value)">
                                        </div>
                                        <div class="price_det_content">
                                            <label class="price_det_lbl" id="tot_price_lbl"> Total Price: </label>
                                            <input type="text" autocomplete="off" readonly class="textbox" id="tot_price_amt" name="tot_price_amt" style="width:90px;margin-right:10px;" value="0">
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
                                        <input type="text" autocomplete="off" class="textbox" name="bill_of_landing"  id="bill_of_landing" style="width:120px">
                                        <input type="text" autocomplete="off" class="textbox" name="freight_weight" id="freight_weight" style="margin-left:20px;width:80px;">
                                        <div class="quantity_unit" id="quantity_freight_unit" name="quantity_freight_unit" onclick="$('#selectQuantityFrieghtUnit').toggle();">KGS</div>
                                        <input type="hidden" id="quantity_freight_units" name="quantity_freight_units" value="KGS" />
                                        <div id="selectQuantityFrieghtUnit" style="display:none; position: absolute;margin-left:242px;margin-top:27px;padding:0px 2px;  border:1px solid #000; z-index: 99999;width:50px;height:80px;overflow-y:scroll;">
                                            <%                                ArrayList categoryLists = QuantityTypeMaster.showAll();
                                                        QuantityTypeBean qtbs = null;
                                                        for (int i = 0; i < categoryLists.size(); i++) {
                                                            qtbs = (QuantityTypeBean) categoryLists.get(i);
                                                            out.print("<span style='cursor:pointer;' onclick=\"javascript:$('#quantity_freight_unit').html('" + qtbs.getQuantityCode() + "');$('#quantity_freight_units').val('" + qtbs.getQuantityCode() + "');$('#selectQuantityFrieghtUnit').fadeOut();\">" + qtbs.getQuantityCode() + "</span></br>");

                                                        }
                                            %>
                                        </div>
                                        <div class="quan_units" id="units_quantity_freight_unit" style="display:none;left:470px;"></div>
                                        <input type="text" autocomplete="off" readonly class="textbox" name="date_shipped" id="date_shipped" style="margin-left:20px;width:100px;">
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
                                        <input type="button" class="gen-btn-Gray" style="margin-right:50px;" value="Reset">
                                        <input type="submit" class="gen-btn-blue" value="Send" id="add_invoice_btn">
                                    </div>
                                    <div class="invoice_error" id="invoice_form_error">
                                    </div>
                                    <div class="invoice_error" id="tx_er">
                                    </div>
                                </div>
                                <input type="hidden" name="allInvItems" id="allInvItems">
                            </form>
                        </div>
                        <div style="display: none;z-index:2000;" class="Custome_Popup_Window" id="invoice_add_item_popup">
                            <div class="Cus_Popup_Outline invoice_add_item_popup_outline"style="height:400px">
                                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;">
                                    <div style="padding: 0px 0px 0px 15px; float: left">Add
                                        Item</div>
                                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" onclick="cancelInvItem();">
                                    </div>
                                </div>
                                <input type="hidden" id="from_form">
                                <div style="margin:20px 0 0 20px;width:350px;float: left;">
                                       <input type="radio" onclick="$('#existingItem').show();
                                   $('#addNewItem').hide();" checked type="radio" name="itemSelection" id="selectItem" value="selectItem">Select Item from Existing Product Catalog
                                </div>
                                <div style="margin:20px 0 0 20px;width:350px;float: left;">
                                       <input type="radio" onclick="$('#existingItem').hide();
                                   $('#addNewItem').show();" name="itemSelection" id="addItem" value="addItem">Add New Item
                                </div>
                                <div style="margin-top:0px;margin-left:10px;float:left;position:relative;" id="existingItem">
                                    <div class="items_head">
                                        <label class="trans_label" style="width:385px;margin-right:20px;">Item Name / Item Description / Batch No / SKU No / Barcode</label>
                                        <label class="trans_label" style="width:107px;margin-right:10px;">Quantity Ordered</label>
                                        <label class="trans_label" style="width:106px;margin-right:10px;">Quantity Shipped</label>
                                        <label class="trans_label" style="width:105px;margin-right:10px;">Price</label>
                                        <label class="trans_label" style="width:60px;margin-right:10px;">Multiplier</label>
                                        <label class="trans_label" style="width:98px;margin-right:10px;">Total</label>
                                    </div>
                                    <div class="item" style="width: 975px;float:left;margin-left:10px;margin-right:0px;position:relative;">
                                        <input onkeyup="showGlobalItemSearch(this.value);" type="text" autocomplete="off" class="textbox" id="popup_item_desc" maxlength="80" style="width:380px;margin-right:20px;">
                                        <input type="text" autocomplete="off" onKeyUp="mult()" class="textbox" id="popup_quantity_ordered" maxlength="12" style="width:63px;">
                                        <div class="quantity_unit" id="popup_quantity_type" style="margin-right:10px;" onclick="$('#selectQuantityUnit').toggle();">PCS</div>
                                        <input type="hidden" name="selectedQuantityUnit" id="selectedQuantityUnit" value="PCS" >
                                        <div class="quan_units" id="units_quantity_ordered_unit" style="display: none;left: 442px;">
                                        </div>
                                        <input type="text" autocomplete="off" class="textbox" id="popup_quantity" maxlength="12" style="width:63px;">
                                        <div class="quantity_unit" id="popup_quantity_type2" style="margin-right:10px;" onclick="$('#selectQuantityUnit2').toggle();">PCS</div>
                                        <input type="hidden" name="selectedQuantityUnit2" id="selectedQuantityUnit2" value="PCS" >
                                        <div class="quan_units" id="units_quantity_shipped_unit" style="display: none;left:579px;">
                                        </div>
                                        <input type="text" autocomplete="off" onKeyUp="mult()" class="textbox" id="popup_price" maxlength="12" style="width:63px;">
                                        <div class="currency" id="currency" style="margin-right:10px;" onclick="$('#selectCurrencyUnit').toggle();">USD</div>
                                        <input type="hidden" class="selectedCurrencyUnit" name="selectedCurrencyUnit" id="selectedCurrencyUnit" value="USD" >
                                        <div class="currency_list" id="currency_currency" style="display: none;left:726px;">
                                            <div class="currency_name" id="currency_USD">USD</div>
                                            <div class="currency_name" id="currency_INR">INR</div>
                                        </div>
                                        <input type="text" autocomplete="off" class="textbox" value="1" id="dscnt" maxlength="12" style="width:50px;margin-right:10px;" onKeyUp="mult()">
                                        <input type="text" autocomplete="off" class="textbox" value="0" readonly="" id="pop_multiplier" maxlength="12" style="width:110px;margin-right:10px;">
                                        <div id="item_search_result" style="display:none;width:480px;margin-right:20px;z-index: 999999;height:100px;border: 1px solid #000;overflow-y: scroll;"></div>
                                        <input type="hidden" id="selectedItemKey"> 
                                    </div>
                                    <div id="selectQuantityUnit" style="display:none; position: absolute;margin-left:494px;margin-top:97px;padding:0px 2px;  border:1px solid #000; z-index: 99999;width:50px;height:80px;overflow-y:scroll;">
                                        <%                                ArrayList categoryList = QuantityTypeMaster.showAll();
                                                    QuantityTypeBean qtb = null;
                                                    for (int i = 0; i < categoryList.size(); i++) {
                                                        qtb = (QuantityTypeBean) categoryList.get(i);
                                                        out.print("<span style='cursor:pointer;' onclick=\"javascript:$('#selectedQuantityUnit').val('" + qtb.getQuantityCode() + "');$('#popup_quantity_type').html('" + qtb.getQuantityCode() + "');$('#selectQuantityUnit').fadeOut();\">" + qtb.getQuantityCode() + "</span></br>");

                                                    }
                                        %>
                                    </div>
                                    <div id="selectQuantityUnit2" style="display:none; position: absolute;margin-left:610px;margin-top:97px;padding:0px 2px;  border:1px solid #000; z-index: 99999;width:50px;height:80px;overflow-y:scroll;">
                                        <%                                //ArrayList categoryList = QuantityTypeMaster.showAll();
                                                    qtb = null;
                                                    for (int i = 0; i < categoryList.size(); i++) {
                                                        qtb = (QuantityTypeBean) categoryList.get(i);
                                                        out.print("<span style='cursor:pointer;' onclick=\"javascript:$('#selectedQuantityUnit2').val('" + qtb.getQuantityCode() + "');$('#popup_quantity_type2').html('" + qtb.getQuantityCode() + "');$('#selectQuantityUnit2').fadeOut();\">" + qtb.getQuantityCode() + "</span></br>");

                                                    }
                                        %>
                                    </div>
                                    <div id="selectCurrencyUnit" style="display:none; position: absolute;margin-left:726px;margin-top:97px;padding:0px 2px; border:1px solid #000; z-index: 99999;width:50px;height:80px;overflow-y:scroll;">
                                        <%
                                                    ArrayList currencyList = CurrencyMaster.showAll();
                                                    CurrencyBean cb2 = null;
                                                    for (int i = 0; i < currencyList.size(); i++) {
                                                        cb2 = (CurrencyBean) currencyList.get(i);
                                                        out.print("<span style='cursor:pointer;' onclick=\"javascript:$('.selectedCurrencyUnit').val('" + cb2.getCurrencyCode() + "');$('#currency').html('" + cb2.getCurrencyCode() + "');$('#selectCurrencyUnit').fadeOut();\">" + cb2.getCurrencyCode() + "</span></br>");

                                                    }
                                        %>
                                    </div>
                                    <div class="items_head2" id="resulted-data-head" style="display:none;">                                        
                                        <label class="trans_label" style="width:120px;margin-right:20px;font-weight:bold;">Item Name</label>
                                        <label class="trans_label" style="width:120px;margin-right:20px;font-weight:bold;">Item Description</label>
                                        <label class="trans_label" style="width:98px;margin-right:20px;font-weight:bold;">Batch No</label>
                                        <label class="trans_label" style="width:98px;margin-right:20px;font-weight:bold;">SKU No</label>
                                        <label class="trans_label" style="width:398px;margin-right:20px;font-weight:bold;">Barcode</label>
                                    </div>
                                    <div class="items_head2" id="resulted-data" style="display:none;">                                        
                                        <label id="lblin" class="trans_label" style="width:120px;margin-right:20px;"></label>
                                        <label id="lblid" class="trans_label" style="width:120px;margin-right:20px;"></label>
                                        <label id="lblpn" class="trans_label" style="width:98px;margin-right:20px;"></label>
                                        <label id="lblsn" class="trans_label" style="width:98px;margin-right:20px;"></label>
                                        <label id="lblbc" class="trans_label" style="width:398px;margin-right:20px;"></label>
                                    </div>
                                    <div class="row" style="margin-top:40px;float:left;margin-left: 173px;">
                                        <input id="invoice_add_item_popup_cancel" style="margin-left:60px;margin-right: 50px" type="button" class="gen-btn-Gray" value="Cancel" onclick="cancelInvItem();">
                                        <input id="save_item_btn" type="button" class="gen-btn-blue" value="Add" tabindex="8" onclick="addInvItem();">
                                    </div>
                                    <div class="invoice_error" id="rfq_item_form_error" style="width:820px;">
                                    </div>
                                </div>
                                <div style="margin-top:0px;margin-left:10px;float:left;position:relative;display:none; " id="addNewItem">
                                    <div class="items_head">                                        
                                        <label class="trans_label" style="width:120px;margin-right:20px;">Item Name</label>
                                        <label class="trans_label" style="width:120px;margin-right:20px;">Item Description</label>
                                        <label class="trans_label" style="width:98px;margin-right:20px;">Batch No</label>
                                        <label class="trans_label" style="width:98px;margin-right:20px;">SKU No</label>
                                        <label class="trans_label" style="width:98px;margin-right:20px;">Barcode</label>
                                        <!--                                        <label class="trans_label" style="width:98px;margin-right:52px;">Quantity</label>
                                                                                <label class="trans_label" style="width:118px;margin-right:20px;">Receive Date</label>-->
                                    </div>
                                    <div class="item" style="width:940px;float:left;margin-left:15px;margin-right:20px;position:relative;">
                                        <input type="text" autocomplete="off" class="textbox" id="popup_item_name" maxlength="80" style="width:100px;margin-right:20px;">
                                        <input type="text" autocomplete="off" class="textbox" id="popup_item_descir" maxlength="80" style="width:100px;margin-right:20px;">
                                        <input type="text" autocomplete="off" class="textbox" id="popup_part_no" maxlength="80" style="width:90px;margin-right:20px;">
                                        <input type="text" autocomplete="off" class="textbox" id="popup_sku_no" maxlength="80" style="width:90px;margin-right:20px;">
                                        <input type="text" autocomplete="off" class="textbox" id="popup_brcd" maxlength="80" style="width:90px;margin-right:20px;">
                                        <!--                                        <input type="text" autocomplete="off" class="textbox" id="popup_quantity" maxlength="12" style="width:90px;">
                                                                                <div class="quantity_unit" id="popup_quantity_type">KG</div>
                                                                                <div class="quan_units" id="popup_quantity_type_key" style="display:none;left:540px;">
                                                                                </div>
                                                                                <input type="text" autocomplete="off" class="textbox hasDatepicker" id="popup_ship_date" style="width:110px;margin-right:20px;">-->
                                    </div>                                    
                                    <div class="row" style="margin-top:40px;float:left;margin-left:100px;">
                                        <input id="rfq_add_item_popup_cancel" style="margin-left:100px;margin-right: 50px" type="button" class="gen-btn-Gray" value="Cancel"  onclick="cancelInvItem();">
                                        <input id="save_item_btn" type="button" class="gen-btn-blue" value="Add" tabindex="8" onclick="addRfqItem();">
                                    </div>
                                    <div class="invoice_error" id="rfq_item_form_error2" style="width:620px;">
                                    </div>
                                </div>
                            </div>
                            <a id="addone_lastHiddenElement" href="/SupplyMedium/user_home.jsp#" style="opacity: 0; " tabindex="9">test</a>
                        </div>
                        <title>Insert title here</title>
                        <link rel="stylesheet" href="inside/invoice_popup.css">
                        <div style="display: none; z-index: 1000;" class="Custome_Popup_Window" id="invoice_popup"></div>
                        <title>Insert title here</title>
                        <div style="display: none; z-index: 1000;" class="Custome_Popup_Window" id="invoice_inquire_popup">
                            <div class="Cus_Popup_Outline invoice_inquire_outline">
                                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                                    <div style="padding: 0px 0px 0px 15px; float: left">Invoice Inquire Details</div>
                                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                    </div>
                                </div>
                                <input type="hidden" id="chat_invoice_id">
                                <input type="hidden" id="chat_trans_id">
                                <input type="hidden" id="chat_reply_to_comp">
                                <input type="hidden" id="chat_reply_to_user">
                                <input type="hidden" id="chat_status">
                                <div id="chat_invoice_inquires">
                                </div>
                                <div id="chat_new_inquire">
                                    <div class="inquire_row">
                                        <label class="inquire_by" id="chat_company_name">
                                        </label>
                                        <textarea class="inquire_det" id="chat_new_inquire_message">
                                        </textarea>
                                        <label class="invoice_error" style="width:300px;margin-left: 125px;margin-top: 10px;" id="chat_inquire_error">
                                        </label>
                                    </div>
                                </div>
                                <input type="button" class="gen-btn-blue" value="Send" style="margin-left: 250px;margin-top: 30px;margin-bottom: 20px;" id="chat_inquire_save_btn">
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
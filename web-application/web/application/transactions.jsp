<%@page import="supply.medium.home.database.QuantityTypeMaster"%>
<%@page import="supply.medium.home.bean.QuantityTypeBean"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailsForVrBean"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailAddressBean"%>
<%@page import="supply.medium.home.database.CompanyDetailAddressMaster"%>
<%@page import="supply.medium.home.bean.TransactionRfqBean"%>
<%@page import="supply.medium.home.database.TransactionRfqMaster"%>
<%@page import="supply.medium.home.bean.CountryBean"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0048)SupplyMedium/user_home.jsp -->
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
                $( "#popup_ship_date").datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
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
    <body onLoad="lockUnlock('webkrit_content_loader');
        lastSelectedList = 'rfq_request_tab';
        //$('#popup_ship_date').datetimepicker({dateFormat: 'dd-M-yy', minDate: 0});" onkeydown="displayunicode(event);">
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
                <div id="trans_super_container">
                    <div class="main_tab_container">
                        <!-- This is the main tab bar container -->
                        <div id="rfq_tab" class="main_tab_select">
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
                        <div id="payment_tab" class="main_tab_unselect">
                            <!-- This is the Invoice tab -->
                            <a href="transactionsPayment.jsp" class="white">Payment</a>
                        </div>
                    </div>
                    <div class="main_tab_sep">
                        <!-- This is the seperator div  -->
                    </div>
                    <div id="trans_content" class="trans_content">
                        <link rel="stylesheet" href="inside/rfq.css">
                        <div id="rfq_post_confirmation" class="rfq_post_confirmation">
                            <center>
                                <p>
                                    <br>
                                    <br>Do you want to post RFQ in news feed as open BID ? Open BID will display your RFQ to other vendors in your business category.<br>
                                    <br>
                                    <br>
                                    <br>
                                           <input type="button" class="gen-btn-blue" value="YES" onClick="send_feed();
                                        $('#rfq_post_confirmation').fadeOut();">
                                           <input type="button" class="gen-btn-blue" value="NO" onClick="sendRFQ();
                                        $('#rfq_post_confirmation').fadeOut();" style="margin-left:30px;">
                                </p>
                            </center>
                            <input type="hidden" id="cmpny_nm9" value="WebKrit Infocom">
                        </div>
                        <div class="sub_tab_container" id="rfq_submenu">
                            <!-- This is RFQ inner tab bar container -->
                                 <div class="highlight" id="rfq_request_tab" onclick="selectList('rfq_request_tab');
                                $('#rfq_request_content').show();
                                $('#rfq_form_content').hide();">My Request</div>
                            <!-- My Request tab -->
                            <%
                                        if (companyTypeMy.equals("Buyers/Suppliers") || companyTypeMy.equals("Buyer")) {
                            %>
                                 <div class="normal" id="rfq_form_tab" onclick="selectList('rfq_form_tab');
                                $('#rfq_form_content').show();
                                $('#rfq_request_content').hide();">Generate RFQ</div>
                            <%                                        }
                            %>
                            <!-- RFQ Form tab -->
                        </div>
                        <div id="rfq_request_content" style="position:relative;">
                            <!-- This is the RFQ queue. -->
                            <div class="DT_border">
                            </div>
                            <div id="rfq_list_wrapper" class="dataTables_wrapper" role="grid">
                                <div class="dataTables_filter" id="rfq_list_filter">
                                    <label>Search <input type="text" autocomplete="off" aria-controls="rfq_list">
                                    </label>
                                </div>
                                <div class="fixed_height">
                                    <table id="rfq_list" style="width: 997px;padding-left:1px;" class="dataTable" aria-describedby="rfq_list_info">
                                        <thead>
                                            <tr role="row">
                                                <th class="rowBorder sorting_asc" style="border-left-width: 1px; border-left-style: solid; border-left-color: rgb(200, 200, 200); width: 20px;" role="columnheader" tabindex="0" aria-controls="rfq_list" rowspan="1" colspan="1" aria-sort="ascending" aria-label=": activate to sort column descending">
                                                </th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="rfq_list" rowspan="1" colspan="1" aria-label="RFQ ID: activate to sort column ascending" style="width: 76px;">RFQ ID</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="rfq_list" rowspan="1" colspan="1" aria-label="Date: activate to sort column ascending" style="width: 110px;">Date</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="rfq_list" rowspan="1" colspan="1" aria-label="Company: activate to sort column ascending" style="width: 134px;">Company</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="rfq_list" rowspan="1" colspan="1" aria-label="Phone: activate to sort column ascending" style="width: 110px;">Phone</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="rfq_list" rowspan="1" colspan="1" aria-label="Primary Contact(Email): activate to sort column ascending" style="width: 160px;">Primary Contact(Email)</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="rfq_list" rowspan="1" colspan="1" aria-label="State: activate to sort column ascending" style="width: 112px;">State</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="rfq_list" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending" style="width: 105px;">Status</th>
                                                <th class="rowBorder sorting" style="border-right-width: 1px; border-right-style: solid; border-right-color: rgb(200, 200, 200); width: 46px;" role="columnheader" tabindex="0" aria-controls="rfq_list" rowspan="1" colspan="1" aria-label="Open: activate to sort column ascending">Open</th>
                                            </tr>
                                        </thead>
                                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                                            <%
                                                        ArrayList al = TransactionRfqMaster.showByTypeCompanyKey("RFQ", companyKey);
                                                        String stl = "";
                                                        String img = "";
                                                        String cname = "";
                                                        String cphone = "";
                                                        String cemail = "";
                                                        String cstate = "";
                                                        String cstatus = "";
                                                        String toKey = "";
                                                        String outsider[] = null;
                                                        TransactionRfqBean trb = null;
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
                                                            trb = (TransactionRfqBean) al.get(i);
                                                            if (companyKey.equals(trb.getBuyerCompanyKey())) {
                                                                toKey = trb.getSupplierCompanyKey();
                                                                img = "trans_sent_icon.png";
                                                                if (trb.getIsOutersideSupplier().equalsIgnoreCase("yes")) {
                                                                    outsider = trb.getOutsiderAddress().split("@#@#@");
                                                                    cname = outsider[0];
                                                                    cphone = "Outside Transaction";
                                                                    cemail = outsider[6];
                                                                    cstate = StateMaster.showStateFromKey(outsider[2]);
                                                                } else if (trb.getIsOutersideSupplier().equalsIgnoreCase("no")) {
                                                                    cb = CompanyMaster.showCompanyDetailsForVrProcess(trb.getSupplierCompanyKey());
                                                                    cname = cb.getCompanyName();
                                                                    cphone = cb.getPhone();
                                                                    cemail = cb.getEmail();
                                                                    cstate = StateMaster.showStateFromKey(cb.getState());
                                                                }
                                                                cstatus = trb.getTransactionStatus();
                                                                if (cstatus.equals("Accepted")) {
                                                                    cstatus = "RFQ Accepted";
                                                                } else if (cstatus.equals("Inquired")) {
                                                                    cstatus = "RFQ Inquired";
                                                                } else {
                                                                    cstatus = "RFQ Generated";
                                                                }
                                                            } else if (companyKey.equals(trb.getSupplierCompanyKey())) {
                                                                toKey = trb.getBuyerCompanyKey();
                                                                img = "trans_received_icon.png";
                                                                cname = trb.getBuyerCompanyName();
                                                                cphone = trb.getBuyerPhone();
                                                                cemail = trb.getBuyerEmail();
                                                                cstate = trb.getBuyerState();
                                                                cstatus = trb.getTransactionStatus();
                                                                if (cstatus.equals("Accepted")) {
                                                                    if (trb.getIsQuoteCreated().equalsIgnoreCase("no")) {
                                                                        cstatus = "<a href='transactionsQuotes.jsp?generatequote=" + trb.getTransactionKey() + "&key=" + toKey + "'' style='text-decoration:underline;'>Generate Quote</a>";
                                                                    } else {
                                                                        cstatus = "Quote Generated";
                                                                    }
                                                                } else if (cstatus.equals("Inquired")) {
                                                                    cstatus = "RFQ Inquired";
                                                                } else {
                                                                    cstatus = "Approve RFQ";
                                                                }
                                                            }
                                            %>
                                            <tr <%=stl%> <% if (trb.getTransactionKey().equals(request.getParameter("nid"))) {
                                                                                                    out.println("style='background:#b7b7b7;'");
                                                                                                }%>
                                                >
                                                <td class="rowBorder" onclick="$('#rfq_popup').fadeIn();showRfqDetailInPopup('<%=trb.getTransactionKey()%>','<%=trb.getBuyerCompanyKey()%>','<%=trb.getSupplierCompanyKey()%>');$('#toKey').val('<%=toKey%>');
                                                    showRfqPopupData(<%=trb.getTransactionKey()%>,<%=companyKey%>,<%=trb.getBuyerCompanyKey()%>);"><img src="inside/<%=img%>"/></td>
                                                <td class="rowBorder" onclick="$('#rfq_popup').fadeIn();showRfqDetailInPopup('<%=trb.getTransactionKey()%>','<%=trb.getBuyerCompanyKey()%>','<%=trb.getSupplierCompanyKey()%>');$('#toKey').val('<%=toKey%>');
                                                    showRfqPopupData(<%=trb.getTransactionKey()%>,<%=companyKey%>,<%=trb.getBuyerCompanyKey()%>);"><%=trb.getTransactionNo()%></td>
                                                <td class="rowBorder" onclick="$('#rfq_popup').fadeIn();showRfqDetailInPopup('<%=trb.getTransactionKey()%>','<%=trb.getBuyerCompanyKey()%>','<%=trb.getSupplierCompanyKey()%>');$('#toKey').val('<%=toKey%>');
                                                    showRfqPopupData(<%=trb.getTransactionKey()%>,<%=companyKey%>,<%=trb.getBuyerCompanyKey()%>);"><%=trb.getCreatedOn().split(" ")[0]%></td>
                                                <td class="rowBorder" onclick="$('#rfq_popup').fadeIn();showRfqDetailInPopup('<%=trb.getTransactionKey()%>','<%=trb.getBuyerCompanyKey()%>','<%=trb.getSupplierCompanyKey()%>');$('#toKey').val('<%=toKey%>');
                                                    showRfqPopupData(<%=trb.getTransactionKey()%>,<%=companyKey%>,<%=trb.getBuyerCompanyKey()%>);"><%=cname%></td>
                                                <td class="rowBorder" onclick="$('#rfq_popup').fadeIn();showRfqDetailInPopup('<%=trb.getTransactionKey()%>','<%=trb.getBuyerCompanyKey()%>','<%=trb.getSupplierCompanyKey()%>');$('#toKey').val('<%=toKey%>');
                                                    showRfqPopupData(<%=trb.getTransactionKey()%>,<%=companyKey%>,<%=trb.getBuyerCompanyKey()%>);"><%=cphone%></td>
                                                <td class="rowBorder" onclick="$('#rfq_popup').fadeIn();showRfqDetailInPopup('<%=trb.getTransactionKey()%>','<%=trb.getBuyerCompanyKey()%>','<%=trb.getSupplierCompanyKey()%>');$('#toKey').val('<%=toKey%>');
                                                    showRfqPopupData(<%=trb.getTransactionKey()%>,<%=companyKey%>,<%=trb.getBuyerCompanyKey()%>);"><%=cemail%></td>
                                                <td class="rowBorder" onclick="$('#rfq_popup').fadeIn();showRfqDetailInPopup('<%=trb.getTransactionKey()%>','<%=trb.getBuyerCompanyKey()%>','<%=trb.getSupplierCompanyKey()%>');$('#toKey').val('<%=toKey%>');
                                                    showRfqPopupData(<%=trb.getTransactionKey()%>,<%=companyKey%>,<%=trb.getBuyerCompanyKey()%>);"><%=cstate%></td>
                                                <td class="rowBorder"><%=cstatus%></td>
                                                <td class="rowBorder" onclick="window.location.href='';"><a target="_blank" href="<%=request.getRealPath("") + File.separator%>cropData/company-<%=companyKey%>/transaction/<%=trb.getTransactionNo()%>.pdf"><img alt="Open" title="Open" src="inside/open.png"></a></td>
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
                                <div class="dataTables_info" id="rfq_list_info">Showing 0 to 0 of 0 entries</div>
                                <div class="dataTables_paginate paging_full_numbers" id="rfq_list_paginate">
                                    <a tabindex="0" class="first paginate_button paginate_button_disabled" id="rfq_list_first">First</a>
                                    <a tabindex="0" class="previous paginate_button paginate_button_disabled" id="rfq_list_previous">Previous</a>
                                    <span>
                                    </span>
                                    <a tabindex="0" class="next paginate_button paginate_button_disabled" id="rfq_list_next">Next</a>
                                    <a tabindex="0" class="last paginate_button paginate_button_disabled" id="rfq_list_last">Last</a>
                                </div>
                            </div>
                        </div>
                        <div id="rfq_form_content" style="display:none;" class="rfq_form_content">
                            <form method="post" id="rfqFormData" action="TransRfq"><!-- onsubmit="return rfqFormValidation();"> -->
                                <!-- This is the RFQ form details -->
                                <%
                                            CompanyDetailAddressBean cdab = (CompanyDetailAddressBean) (CompanyDetailAddressMaster.showCompanyDetailAddress(companyKey)).get(0);
                                %>
                                <div class="to_comp">
                                    <label class="label" style="padding-left:20px;width:45px;color:white;line-height:25px;">To:</label>
                                    <input type="text" autocomplete="off" placeholder="Search Registered Vendor" id="to_company" style="width:920px;" class="textbox" onKeyUp="showCompaniesVendorSearch(this.value, 'Supplier');">
                                </div>
                                <input type="hidden" id="selectedVendorKey" name="companyKeyTo" value="0">
                                <input type="hidden" id="selected_ven_id">
                                <div class="rfq_form">
                                    <div id="ven_search_result" class="com_search_result" style="display:none;">
                                    </div>
                                    <div class="rfq_buyer_det">
                                        <div class="sub_heading">Buyer Details</div>
                                        <div class="trans_row" style="float:left;">
                                            <label class="trans_label"> Buyers Name </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="buyer_name" disabled="" value="<%=cdab.getCompanyName()%>">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> Country </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="buyer_country" disabled="" value="<%=CountryMaster.showCountryFromKey(cdab.getCountry())%>">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> State/Province </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="buyer_state" disabled="" value="<%=StateMaster.showStateFromKey(cdab.getState())%>">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> City </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="buyer_city" disabled="" value="<%=cdab.getCity()%>">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> Address </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="buyer_addr" disabled="" value="<%=cdab.getAddress()%>">
                                        </div>
                                        <div class="trans_row">
                                            <label class="trans_label"> Zip Code/Postal Code </label>
                                            <input type="text" autocomplete="off" class="textbox_disable" id="buyer_zipcode" disabled="" value="<%=cdab.getZipcode()%>">
                                        </div>
                                        <div class="sub_heading" style="margin-top:20px;">Request For Quote</div>
                                        <div class="trans_row">
                                            <label class="trans_label"> Quote Reference </label>
                                            <input type="text" autocomplete="off" class="textbox" id="quote_ref" name="quoteRef">
                                        </div>
                                    </div>
                                    <div class="addr_sep">
                                    </div>
                                    <div class="rfq_supplier_det">
                                        <div class="sub_heading">Supplier Details</div>
                                        <div class="trans_row">
                                            <div class="checkContainer">
                                                <input type="checkbox" name="isOutside" class="checkbox" id="outside_supplier" onclick="if (this.checked) {
                                                    $('#insideSupplier').hide();
                                                    $('#outsideSupplier').show();
                                                    $('#to_company').val('');
                                                    $('#selectedVendorKey').val(0);
                                                } else {
                                                    $('#insideSupplier').show();
                                                    $('#outsideSupplier').hide();
                                                }
                                                       ">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="outside_supplier" class="trans_label" style="line-height:19px;margin-left:5px;">Outside Supplier</label>
                                        </div>
                                        <div class="supplier_address" id="insideSupplier" style="width:100%;height:300px;">
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
                                        <div class="outside_sup_email_content" id="outsideSupplier" style="width:100%;height:300px;display:none">
                                            <div class="trans_row">
                                                <label class="trans_label"> Name </label>
                                                <input type="text" autocomplete="off" class="textbox" id="otsd_splr_nm" name="outsideSupplierName">
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> Country </label>
                                                <SELECT name="outsideSupplierCountry" onChange="fetchState(this.value);" style="width:187px;height:28px;" class ="customSelect required selectbox">
                                                    <OPTION value="">--Select 
                                                        Country--</OPTION>
                                                        <%                                    ArrayList countryList = CountryMaster.showAll();
                                                                    CountryBean scb = null;
                                                                    for (int i = 0; i < countryList.size(); i++) {
                                                                        scb = (CountryBean) countryList.get(i);
                                                        %>
                                                    <option value="<%=scb.getCountryKey()%>">
                                                        <%=scb.getCountryName()%>
                                                    </option>
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
                                                <input type="text" autocomplete="off" name="outsideSupplierCity" class="textbox" id="otsd_splr_cty">
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> Address </label>
                                                <input type="text" autocomplete="off" name="outsideSupplierAddress" class="textbox" id="otsd_splr_adrs">
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> Zip Code/Postal Code </label>
                                                <input type="text" autocomplete="off" name="outsideSupplierZipcode" class="textbox" id="otsd_splr_zpcd">
                                            </div>
                                            <div class="trans_row">
                                                <label class="trans_label"> Email </label>
                                                <input type="text" autocomplete="off" name="outsideSupplierEmail" class="textbox" id="email">
                                            </div>
                                        </div>
                                        <div class="trans_row" style="margin-top:50px;">
                                            <label class="trans_label"> Recurring </label>
                                            <select name="recurring" id="recurring" class="selectbox hasCustomSelect" style="width: 188px; -webkit-appearance: menulist-button; position: absolute; opacity: 0; height: 28px; font-size: 12px;">
                                                <option value="weekly"> None </option>
                                                <option value="weekly"> Weekly </option>
                                                <option value="monthly"> Monthly </option>
                                                <option value="annually"> Annually </option>
                                            </select>
                                            <span class="customSelect selectbox" style="width: 165px; display: inline-block;">
                                                <span class="customSelectInner" style="width: 142px; display: inline-block;"> None </span>
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
                                        <label class="trans_label" style="width:118px;margin-right:20px;">Receive Date</label>
                                    </div>
                                    <ol style="list-style-type: decimal;">
                                        <div class="items" id="items">

                                        </div>
                                    </ol>
                                    <div style="width:100%;float:left;margin-left:40px;height:50px;">
                                        <input id="add_item_btn" type="button" class="add_item general-button" value="Add Item" onclick="$('#rfq_add_item_popup').fadeIn();">
                                        <input type="hidden" name="allRfQItems" id="allRfQItems">
                                    </div>
                                    <div style="width:100%;float:left;margin-left:40px;height:50px;" id="rfq_terms_container">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="rfq_terms_cond">
                                            <div>
                                            </div>
                                        </div>
                                        <label class="trans_label" style="line-height:19px;margin-left:5px;cursor:pointer;text-decoration:underline;" id="rfq_tc_link">Accept terms &amp; conditions</label>
                                    </div>
                                    <div class="rfq_form_btns">
                                        <input type="hidden" name="rfqToFeed" id="rfqToFeed"/>
                                        <input type="button" class="gen-btn-Gray" style="margin-right:50px;" value="Reset" onClick="resetRFQForm()">
                                        <input type="button" onclick="rfqFormValidation()" class="gen-btn-blue" value="Send" id="add_rfq_btn">
                                    </div>
                                    <div class="rfq_error" id="rfq_form_error">
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div style="display: none;z-index:2000;" class="Custome_Popup_Window" id="rfq_add_item_popup">
                            <div class="Cus_Popup_Outline rfq_add_item_popup_outline">
                                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;">
                                    <div style="padding: 0px 0px 0px 15px; float: left">Add
                                        Item</div>
                                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" onclick="cancelRfqItem();">
                                    </div>
                                </div>
                                <input type="hidden" id="from_form">
                                <div style="margin:20px 0 0 20px;width:350px;float: left;">
                                    <input type="radio" onclick="$('#existingItem').show();$('#addNewItem').hide();" checked type="radio" name="itemSelection" id="selectItem" value="selectItem">Select Item from Existing Product Catalog
                                </div>
                                <div style="margin:20px 0 0 20px;width:350px;float: left;">
                                    <input type="radio" onclick="$('#existingItem').hide();$('#addNewItem').show();" name="itemSelection" id="addItem" value="addItem">Add New Item
                                </div>
                                <div style="margin-top:0px;margin-left:10px;float:left;position:relative;display:block;" id="existingItem">
                                    <div class="items_head">                                        
                                        <label class="trans_label" style="width:500px;margin-right:20px;">Item Name / Item Description / Batch No / SKU No / Barcode</label>
                                        <label class="trans_label" style="width:98px;margin-right:52px;">Quantity</label>
                                        <label class="trans_label" style="width:118px;margin-right:20px;">Receive Date</label>
                                    </div>
                                    <div id="selectQuantityUnit" style="display: none;position: absolute;margin-left:653px;margin-top:97px;padding:0px 2px; border:1px solid #000; z-index: 99999;width:50px;height:80px;overflow-y:scroll;">
                                        <%                                ArrayList categoryList = QuantityTypeMaster.showAll();
                                                    QuantityTypeBean qtb = null;
                                                    for (int i = 0; i < categoryList.size(); i++) {
                                                        qtb = (QuantityTypeBean) categoryList.get(i);
                                                        out.print("<span style='cursor:pointer;' onclick=\"javascript:$('#selectedQuantityTypeKey').val('" + qtb.getQuantityCode() + "');$('#popup_quantity_type').html('" + qtb.getQuantityCode() + "');$('#selectQuantityUnit').fadeOut();\">" + qtb.getQuantityCode() + "</span></br>");

                                                    }
                                        %>
                                    </div>
                                    <div class="item" style="width:940px;float:left;margin-left:40px;margin-right:20px;position:relative;">
                                        <input onkeyup="showGlobalItemSearch(this.value);" type="text" autocomplete="off" class="textbox" id="popup_item_desc" maxlength="80" style="width:480px;margin-right:20px;">
                                        <input type="text" autocomplete="off" class="textbox" id="popup_quantity" maxlength="12" style="width:90px;">
                                        <div class="quantity_unit" id="popup_quantity_type"  onclick="$('#selectQuantityUnit').toggle();">PCS</div>
                                        <div class="quan_units" id="popup_quantity_type_key" style="display:none;left:540px;">
                                        </div>
                                        <input type="text" readonly autocomplete="off" class="textbox" name="popup_ship_date" id="popup_ship_date" style="width:110px;margin-right:20px;">
                                        <div id="item_search_result" style="display:none;width:480px;margin-right:20px;z-index: 999999;height:100px;border: 1px solid #000;overflow-y: scroll;"></div>
                                        <input type="hidden" id="selectedItemKey"> 
                                        <input type="hidden" value="PCS" id="selectedQuantityTypeKey">
                                    </div>
                                    <div class="items_head2" id="resulted-data-head" style="display:none;">                                        
                                        <label class="trans_label" style="width:120px;margin-right:20px;font-weight:bold;">Item Name</label>
                                        <label class="trans_label" style="width:120px;margin-right:20px;font-weight:bold;">Item Description</label>
                                        <label class="trans_label" style="width:98px;margin-right:20px;font-weight:bold;">Batch No</label>
                                        <label class="trans_label" style="width:98px;margin-right:20px;font-weight:bold;">SKU No</label>
                                        <label class="trans_label" style="width:98px;margin-right:20px;font-weight:bold;">Barcode</label>
                                    </div>
                                    <div class="items_head2" id="resulted-data" style="display:none;">                                        
                                        <label id="lblin" class="trans_label" style="width:120px;margin-right:20px;"></label>
                                        <label id="lblid" class="trans_label" style="width:120px;margin-right:20px;"></label>
                                        <label id="lblpn" class="trans_label" style="width:98px;margin-right:20px;"></label>
                                        <label id="lblsn" class="trans_label" style="width:98px;margin-right:20px;"></label>
                                        <label id="lblbc" class="trans_label" style="width:98px;margin-right:20px;"></label>
                                    </div>
                                    <div class="row" style="margin-top:100px;float:left;margin-left:100px;">
                                        <input id="rfq_add_item_popup_cancel" style="margin-left:100px;margin-right: 50px" type="button" class="gen-btn-Gray" value="Cancel"  onclick="cancelRfqItem();">
                                        <input id="save_item_btn" type="button" class="gen-btn-blue" value="Add" tabindex="8" onclick="addRfqItem();">
                                    </div>
                                    <div class="rfq_error" id="rfq_item_form_error" style="width:620px;">
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
                                    <div class="item" style="width:940px;float:left;margin-left:40px;margin-right:20px;position:relative;">
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
                                        <input id="rfq_add_item_popup_cancel" style="margin-left:100px;margin-right: 50px" type="button" class="gen-btn-Gray" value="Cancel"  onclick="cancelRfqItem();">
                                        <input id="save_item_btn" type="button" class="gen-btn-blue" value="Add" tabindex="8" onclick="addRfqItem();">
                                    </div>
                                    <div class="rfq_error" id="rfq_item_form_error2" style="width:620px;font-weight:bold;color:green;font-size:14px;padding-top:10px;">
                                    </div>
                                </div>
                            </div>
                            <a id="addone_lastHiddenElement" href="home.jsp" style="opacity: 0; " tabindex="9">test</a>
                        </div>
                        <title>Insert title here</title>
                        <link rel="stylesheet" href="inside/rfq_popup.css">
                        <div style="display: none; z-index: 1000;" class="Custome_Popup_Window" id="rfq_popup"></div>
                        <title>Insert title here</title>
                        <div style="display: none; z-index: 1000;" class="Custome_Popup_Window" id="rfq_inquire_popup">
                            <div class="Cus_Popup_Outline rfq_inquire_outline">
                                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                                    <div style="padding: 0px 0px 0px 15px; float: left">RFQ Inquire Details</div>
                                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                    </div>
                                </div>
                                <input type="hidden" id="chat_rfq_id">
                                <input type="hidden" id="chat_trans_id">
                                <input type="hidden" id="chat_reply_to_comp">
                                <input type="hidden" id="chat_reply_to_user">
                                <input type="hidden" id="chat_status">
                                <div id="chat_rfq_inquires">
                                </div>
                                <div id="chat_new_inquire">
                                    <div class="inquire_row">
                                        <label class="inquire_by" id="chat_company_name">
                                        </label>
                                        <textarea class="inquire_det" id="chat_new_inquire_message">
                                        </textarea>
                                        <label class="rfq_error" style="width:300px;margin-left: 125px;margin-top: 10px;" id="chat_inquire_error">
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
        <LINK rel="stylesheet" href="inside/Custome_Popup.css">
        <LINK rel="stylesheet" href="inside/popup_warning.css">
        <SCRIPT type="text/javascript">
            $(function () {

                $(".Cus_Popup_Close").click(function () {
                    $("#warning_container").hide();
                });
                $("#Popup_Cancel").click(function () {
                    $("#warning_container").hide();
                });

                $('#okbtn').click(
                        function ()
                        {
                            $("#warning_container").hide();
                            document.getElementById("rfqToFeed").value="yes";
                            $("#rfqFormData").submit();
                            showLoading();
                        });

                $('#Popup_Cancel').click(
                        function ()
                        {
                            $("#warning_container").hide();
                            document.getElementById("rfqToFeed").value="no";
                            $("#rfqFormData").submit();
                            showLoading();
                        });
            });

            function showWarningBroadcast(message)
            {
                $('#war_message').text("Do you want to broadcast RFQ as open BID ? Open BID will display your RFQ to other vendors in your business category.");

                $("#warning_container").show();

                $("#Popup_Cancel").focus();
                //return $('#returnwarval').text();
            }

        </SCRIPT>
        <DIV style="display: none;" id="warning_container">
            <DIV id="warning_popup">
                <DIV id="war_head">
                    <LABEL id="war_head_title">Warning</LABEL>
                </DIV>
                <DIV id="war_body">
                    <LABEL id="returnwarval"></LABEL>
                    <LABEL id="war_message">
                    </LABEL>
                    <DIV id="war_btns">
                        <a id="confirmFeedRemove"><INPUT style="margin-right: 30px; float: left;" id="okbtn" class="pop-button pop-button-White" value="Yes" type="button"></a>
                        <INPUT style="float: left;" id="Popup_Cancel" class="pop-button pop-button-White" value="No" type="button">
                    </DIV>
                </DIV>
            </DIV>
        </DIV>
        <LINK rel="stylesheet" href="inside/Custome_Popup.css">
        <LINK rel="stylesheet" href="inside/ResetCSS.css">
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
</body>
</html>
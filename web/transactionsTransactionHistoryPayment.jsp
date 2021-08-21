<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailsForVrBean"%>
<%@page import="supply.medium.home.bean.TransactionInvBean"%>
<%@page import="supply.medium.home.database.CompanyDetailAddressMaster"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailAddressBean"%>
<%@page import="supply.medium.home.database.TransactionInvMaster"%>
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
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/productcatalog.css">
        <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/tablestyle.css">
        <link rel="stylesheet" href="inside/customerhistory.css">
        <div class="pagetitlecontainer">
            <div class="pagetitle">Payment History</div>
        </div>
        <div class="page">
            <div class="contentcontainer" style="min-height: 0px;">
                <form name="customerhistoryfrm" id="customerhistoryfrm" style="min-height:600px;">
                    <div id="customerhistory_container">
                        <div class="tabbar">
                            <div class="customerhistoryerr" id="customerhistoryblerr">
                            </div>
                            <div class="normal" id="customer_history" onclick="window.location.href='transactionsTransactionHistory.jsp'" style="display:block;width:150px;">Customer History</div>
                            <div class="normal" id="transaction_history" onclick="window.location.href='transactionsTransactionHistoryCustomer.jsp?cid=<%=request.getParameter("cid")%>'" style="display:block;width:150px;">Transaction History</div>
                            <div class="highlight" id="payment_history" onclick="window.location.href='transactionsTransactionHistoryPayment.jsp?cid=<%=request.getParameter("cid")%>'" style="display:block;width:150px;">Payment History</div>
                        </div>
                        <div class="transactionhistorycontent" id="transaction_history_content" style="display: block;">
                            <div id="customer_info" class="customer_info">
                                <%
                                CompanyDetailAddressBean cdab=(CompanyDetailAddressBean)CompanyDetailAddressMaster.showCompanyDetailAddress(request.getParameter("cid")).get(0);
                                %>
                                <div class="trans_history_row">
                                    <label class="trans_history_lbl">Customer Name: </label>
                                    <label class="trans_history_lbl" style="width:400px;" id="cusName"><%=cdab.getCompanyName()%>
                                    </label>
                                </div>
                                <div class="trans_history_row">
                                    <label class="trans_history_lbl">Type: </label>
                                    <label class="trans_history_lbl" style="width:400px;" id="addType"><%=cdab.getCompanyType()%>
                                    </label>
                                </div>
                                <div class="trans_history_row">
                                    <label class="trans_history_lbl">Address: </label>
                                    <label class="trans_history_lbl" style="width:400px;" id="cusAddress"><%=cdab.getAddress()%>
                                    </label>
                                </div>
                                <div class="trans_history_row">
                                    <label class="trans_history_lbl">City: </label>
                                    <label class="trans_history_lbl" style="width:400px;" id="city"><%=cdab.getCity()%>
                                    </label>
                                </div>
                                <div class="trans_history_row">
                                    <label class="trans_history_lbl">State: </label>
                                    <label class="trans_history_lbl" style="width:400px;" id="state"><%=StateMaster.showStateFromKey(cdab.getState())%>
                                    </label>
                                </div>
                                <div class="trans_history_row">
                                    <label class="trans_history_lbl">Country: </label>
                                    <label class="trans_history_lbl" style="width:400px;" id="cusId"><%=CountryMaster.showCountryFromKey(cdab.getCountry())%>
                                    </label>
                                </div>
                            </div>
                            <div class="tablecontent" id="table_content" style="position:relative;margin-top:190px;">
                                <div class="DT_border">
                                </div>
                                <div id="TransList_wrapper" class="dataTables_wrapper" role="grid">
<!--                                    <div class="dataTables_filter" id="TransList_filter">
                                        <label>Search <input type="text" autocomplete="off" aria-controls="TransList">
                                        </label>
                                    </div>-->
                                    <div class="fixed_height">
                                        <table id="TransList" style="width: 997px;" class="dataTable" aria-describedby="TransList_info">
                                            <thead>
                                                <tr role="row">
                                                    <th class="rowBorder sorting_asc" role="columnheader" tabindex="0" aria-controls="TransList" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Transaction No: activate to sort column descending" style="width: 30px;">Type</th>
                                                    <th class="rowBorder sorting_asc" role="columnheader" tabindex="0" aria-controls="TransList" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Transaction No: activate to sort column descending" style="width: 200px;">Transaction No</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="TransList" rowspan="1" colspan="1" aria-label="Date: activate to sort column ascending" style="width: 200px;">Date</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="TransList" rowspan="1" colspan="1" aria-label="Amount: activate to sort column ascending" style="width: 200px;">Amount</th>
                                                    <!--<th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="TransList" rowspan="1" colspan="1" aria-label="Reminder: activate to sort column ascending" style="width: 200px;">Reminder</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="TransList" rowspan="1" colspan="1" aria-label="XML File: activate to sort column ascending" style="width: 129px;">XML File</th>-->
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="TransList" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending" style="width: 70px;">Status</th>
                                                    <!--<th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="TransList" rowspan="1" colspan="1" aria-label="Feedback: activate to sort column ascending" style="width: 70px;">Feedback</th>-->
                                                </tr>
                                            </thead>
                                            <tbody role="alert" aria-live="polite" aria-relevant="all">
                                                <%
                                                ArrayList al = TransactionInvMaster.showByTypeCompanyKeyOther("Invoice", companyKey,request.getParameter("cid"),"yes");
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
                                                    } else if (companyKey.equals(trb.getCompany_key_to())) {
                                                        img = "trans_received_icon.png";
                                                        cname = trb.getBuyerCompanyName();
                                                        cphone = trb.getBuyerPhone();
                                                        cemail = trb.getBuyerEmail();
                                                        cstate = trb.getBuyerState();
                                                    }
                                                %>
                                                <tr <%=stl%>>
                                                    <td><img src="inside/<%=img%>"></td>
                                                    <td><%=trb.getTrans_key()%></td>
                                                    <td><%=trb.getInv_created_on().split(" ")[0]%></td>
                                                    <td>USD <%=trb.getInv_total_billing_amount()%></td>
                                                    <!--<td><a href="_downloadEDI.jsp?cid=<%=request.getParameter("cid")%>" target="_blank">XML File</a></td>-->
                                                    <td><% if(trb.getIs_inv_paid().equalsIgnoreCase("yes")){out.println("Paid");}else{out.println("Unpaid");} %></td>
                                                </tr>
                                                <%
                                                }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="dataTables_info" id="TransList_info">Showing 0 to 0 of 0 entries</div>
                                    <div class="dataTables_paginate paging_full_numbers" id="TransList_paginate">
                                        <a tabindex="0" class="first paginate_button paginate_button_disabled" id="TransList_first">First</a>
                                        <a tabindex="0" class="previous paginate_button paginate_button_disabled" id="TransList_previous">Previous</a>
                                        <span>
                                        </span>
                                        <a tabindex="0" class="next paginate_button paginate_button_disabled" id="TransList_next">Next</a>
                                        <a tabindex="0" class="last paginate_button paginate_button_disabled" id="TransList_last">Last</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
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
        <div class="Custome_Popup_Window" style="display:none;" id="add_ratings">
            <div class="Cus_Popup_Outline add_ratings_outline">
                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                    <div style="padding: 0px 0px 0px 15px; float: left">Ratings</div>
                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                    </div>
                </div>
                <div class="star_container">
                    <!--  <img src="images/great_star_full.png" class="star_prop">
                    <img src="images/great_star_full.png" class="star_prop">
                    <img src="images/great_star_full.png" class="star_prop">
                    <img src="images/great_star_full.png" class="star_prop">
                    <img src="images/great_star_empty.png" class="star_prop">-->
                    <div id="add_ratings_star" style="float: left; height: 85px; width: 425px; overflow: hidden; z-index: 1; position: relative;" data-average="0">
                        <div class="jRatingColor" style="width: 0px;">
                        </div>
                        <div class="jRatingAverage" style="width: 0px; top: -85px;">
                        </div>
                        <div class="jStar" style="width: 425px; height: 85px; top: -170px; background: url(/SupplyMedium//Views/UserRatings/images/great_star_empty.png) repeat-x;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="Custome_Popup_Window" style="display:none;" id="add_remainder">
            <div class="Cus_Popup_Outline add_remainder_outline">
                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                    <div style="padding: 0px 0px 0px 15px; float: left">Reminders</div>
                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" id="add_remainder_close">
                    </div>
                </div>
                <div class="remainder_container">
                    <div class="add_rem_left_label">Reminder
                    </div>
                    <textarea class="text_area" placeholder="What do you want to be reminded of ?" id="new_remainder">
                    </textarea>
                </div>
                <div class="remainder_container">
                    <div class="add_rem_left_label">Date and Time
                    </div>
                    <div class="dat">
                        <input type="text" autocomplete="off" class="calendar_style hasDatepicker" id="remainder_due_date" readonly="">
                        <img class="ui-datepicker-trigger" src="inside/calendar.png" alt="..." title="...">
                    </div>
                </div>
                <input type="button" class="gen-btn-Orange" id="save_remainder_btn" value="Save" style="margin-left:230px;margin-top:20px;">
            </div>
        </div>
        <div class="Custome_Popup_Window" style="display:none;" id="download_info">
            <div class="Cus_Popup_Outline add_remainder_outline">
                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                    <div style="padding: 0px 0px 0px 15px; float: left">Download EDI</div>
                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" id="download_close">
                    </div>
                </div>
                <div class="file_container">
                    <div class="cont_file" id="rfq_file" style="display:none;">
                        <input type="checkbox" class="chkbx" id="rfq_checkbox">
                        <label for="rfq_checkbox" class="lblstyl">rfq.xml</label>
                    </div>
                    <div class="cont_file" id="quote_file" style="display:none;">
                        <input type="checkbox" class="chkbx" id="quote_checkbox">
                        <label for="quote_checkbox" class="lblstyl">quote.xml</label>
                    </div>
                    <div class="cont_file" id="po_file" style="display:none;">
                        <input type="checkbox" class="chkbx" id="po_checkbox">
                        <label for="po_checkbox" class="lblstyl">purchase_order.xml</label>
                    </div>
                    <div class="cont_file" id="invoice_file" style="display:none;">
                        <input type="checkbox" class="chkbx" id="invoice_checkbox">
                        <label for="invoice_checkbox" class="lblstyl">invoice.xml</label>
                    </div>
                    <input type="hidden" id="trans_id">
                </div>
                <input type="button" class="gen-btn-Orange" id="download_btn" value="Download" style="margin-left:230px;margin-top:20px;">
            </div>
        </div>
        <div class="Custome_Popup_Window " style="display:none;" id="email_composer">
            <div class="Cus_Popup_Outline remainders_outline email_composer_outline">
                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                    <div style="padding: 0px 0px 0px 15px; float: left">Send Email</div>
                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                    </div>
                </div>
                <div class="left_label">Message:</div>
                <div class="right_text">
                    <textarea class="textarea" id="message_mail" placeholder="Send a message">
                    </textarea>
                </div>
                <input type="hidden" id="hidden_mail">
                <div class="button">
                    <input type="button" class="gen-btn-Gray" id="cancel_mail_btn" style="margin-top:10px;margin-right:20px;float:left" value="Cancel">
                    <input type="button" class="gen-btn-Orange" id="send_mail_btn" style="margin-top:10px;" value="Send">
                </div>
            </div>
        </div>
        <div class="Custome_Popup_Window" style="display:none;" id="remainders">
            <div class="Cus_Popup_Outline remainders_outline">
                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                    <div style="padding: 0px 0px 0px 15px; float: left">Reminders</div>
                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" id="remainders_close">
                    </div>
                </div>
                <div id="remainders_content" class="mCustomScrollbar _mCS_7">
                    <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_7" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
                        <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
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
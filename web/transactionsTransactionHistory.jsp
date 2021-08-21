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
        <link rel="stylesheet" href="/SupplyMedium/Views/UserRatings/css/jRating.jquery.css">
        <div class="pagetitlecontainer">
            <div class="pagetitle">Customer Transaction History</div>
        </div>
        <div class="page">
            <div class="contentcontainer" style="min-height: 0px;">
                <form name="customerhistoryfrm" id="customerhistoryfrm" style="min-height:600px;">
                    <div id="customerhistory_container">
                        <div class="tabbar" style="position:none;margin-top:5px;">
                            <div class="customerhistoryerr" id="customerhistoryblerr">
                            </div>
                            <div class="highlight" id="customer_history" onclick="window.location.href='transactionsTransactionHistory.jsp'" style="display:block;width:150px;">Customer History</div>
                            <div class="normal" id="transaction_history" onclick="window.location.href='transactionsTransactionHistoryCustomer.jsp'" style="display:none;width:150px;">Transaction History</div>
                            <div class="normal" id="payment_history" onclick="window.location.href='transactionsTransactionHistoryPayment.jsp'" style="display:none;width:150px;">Payment History</div>
                        </div>
                        <div class="customerhistorycontent" id="customer_history_content" style="display:block;">
                            <div class="tablecontent" id="table_content" style="position:relative;">
                                <div class="DT_border">
                                </div>
                                <div id="CustomerList_wrapper" class="dataTables_wrapper" role="grid">
<!--                                    <div class="dataTables_filter" id="CustomerList_filter">
                                        <label>Search <input type="text" autocomplete="off" aria-controls="CustomerList">
                                        </label>
                                    </div>-->
                                    <div class="fixed_height">
                                        <table id="CustomerList" style="width: 997px;" class="dataTable" aria-describedby="CustomerList_info">
                                            <thead>
                                                <tr role="row">
                                                    <th class="rowBorder sorting_asc" role="columnheader" tabindex="0" aria-controls="CustomerList" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Phone No: activate to sort column descending" style="width: 200px;">Phone No</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="CustomerList" rowspan="1" colspan="1" aria-label="Company Name: activate to sort column ascending" style="width: 220px;">Company Name</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="CustomerList" rowspan="1" colspan="1" aria-label="Address: activate to sort column ascending" style="width: 200px;">Address</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="CustomerList" rowspan="1" colspan="1" aria-label="Email: activate to sort column ascending" style="width: 200px;">Email</th>
                                                    <!--<th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="CustomerList" rowspan="1" colspan="1" aria-label="Updates: activate to sort column ascending" style="width: 129px;">Updates</th>-->
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="CustomerList" rowspan="1" colspan="1" aria-label="Documents: activate to sort column ascending" style="width: 120px;">Documents</th>
                                                    <!--<th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="CustomerList" rowspan="1" colspan="1" aria-label="Ratings: activate to sort column ascending" style="width: 120px;">Ratings</th>-->
                                                </tr>
                                            </thead>
                                            <tbody role="alert" aria-live="polite" aria-relevant="all">
                                                <%
                                                ArrayList altic=TransactionInvMaster.showInvoicedCompanies(companyKey);
                                                CompanyDetailAddressBean cb=null;
                                                //UserBean ub=null;
                                                String emlsd=null;
                                                for(int ticv=0;ticv<altic.size();ticv++)
                                                {
                                                    cb=(CompanyDetailAddressBean)altic.get(ticv);
                                                    emlsd=UserMaster.getAdminEmail(cb.getCompanyKey());
                                                %>
                                                <tr class="<% if(ticv%2==0){out.println("even");}else{out.println("odd");}%>">
                                                    <td class="sorting_1 rowBorder">
                                                        <a style="text-decoration:underline;" onclick="window.location.href='transactionsTransactionHistoryCustomer.jsp?cid=<%=cb.getCompanyKey()%>'"><%=UserMaster.getPhoneByAdminEmail(emlsd)%></a>
                                                    </td>
                                                    <td class="rowBorder">
                                                        <a style="text-decoration:underline;" onclick="window.location.href='transactionsTransactionHistoryCustomer.jsp?cid=<%=cb.getCompanyKey()%>'"><%=cb.getCompanyName()%></a>
                                                    </td>
                                                    <td class="rowBorder">
                                                        <a style="text-decoration:underline;" onclick="window.location.href='transactionsTransactionHistoryCustomer.jsp?cid=<%=cb.getCompanyKey()%>'"><%=cb.getCity()%>, <%=StateMaster.showStateFromKey(cb.getState())%>, <%=CountryMaster.showCountryFromKey(cb.getCountry())%></a>
                                                    </td>
                                                    <td class="rowBorder">
                                                        <a style="text-decoration:underline;" onclick="window.location.href='transactionsTransactionHistoryCustomer.jsp?cid=<%=cb.getCompanyKey()%>'"><%=emlsd%></a>
                                                    </td>
<!--                                                    <td class="rowBorder">
                                                        <a style="text-decoration:underline;" onclick="window.location.href='transactionsTransactionHistoryCustomer.jsp?cid=<%=cb.getCompanyKey()%>'">Reminders</a>
                                                    </td>-->
                                                    <td class="rowBorder"><a href="javascript:" onclick="window.open('_downloadEDI.jsp?cid=<%=companyKey%>');" target="_blank">Download EDI</a></td>
<!--                                                    <td class="rowBorder">
                                                        <div id="customerRating_9815990066" data-average="0" style="height: 13px; width: 65px; overflow: hidden; z-index: 1; position: relative;">
                                                            <div class="jRatingColor" style="width: 0px;">
                                                            </div>
                                                            <div class="jRatingAverage" style="width: 0px; top: -13px;">
                                                            </div>
                                                            <div class="jStar" style="width: 65px; height: 13px; top: -26px; background: url(/SupplyMedium/Views/UserRatings/images/small_star_empty.png) repeat-x;">
                                                            </div>
                                                        </div>
                                                    </td>-->
                                                </tr>
                                                <%
                                                }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="dataTables_info" id="CustomerList_info">Showing 1 to 1 of 1 entries</div>
                                    <div class="dataTables_paginate paging_full_numbers" id="CustomerList_paginate">
                                        <a tabindex="0" class="first paginate_button paginate_button_disabled" id="CustomerList_first">First</a>
                                        <a tabindex="0" class="previous paginate_button paginate_button_disabled" id="CustomerList_previous">Previous</a>
                                        <span>
                                            <a tabindex="0" class="paginate_active">1</a>
                                        </span>
                                        <a tabindex="0" class="next paginate_button paginate_button_disabled" id="CustomerList_next">Next</a>
                                        <a tabindex="0" class="last paginate_button paginate_button_disabled" id="CustomerList_last">Last</a>
                                    </div>
                                </div>
                            </div>
                        </div>                        
                    </div>
                </form>
                <form name="recent_trans_edi_download_form" id="recent_trans_edi_download_form" action="/SupplyMedium/EDIFileDownloadServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="recent_trans_id" name="TransId">
                    <input type="hidden" name="RequestType" value="LatestEDIFiles">
                </form>
                <form name="recent_trans_edi_download_form" id="specific_trans_edi_download_form" action="/SupplyMedium/EDIFileDownloadServlet" method="post" enctype="multipart/form-data">
                    <input type="hidden" id="sepcific_trans_id" name="TransId">
                    <input type="hidden" id="trans_types" name="TransTypes">
                    <input type="hidden" name="RequestType" value="SpecificEDIFiles">
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
</body>
</html>
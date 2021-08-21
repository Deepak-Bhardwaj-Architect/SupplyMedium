<%@page import="supply.medium.home.database.TermsConditionsMaster"%>
<%@page import="supply.medium.home.bean.TermsConditionsBean"%>
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
        <script type="text/JavaScript" src="inside/trans_tc.js">
        </script>
        <title>Supply Medium</title>
        <!--<script>
        Usr_anlys('Admin');
        </script>-->
    </head>
    <body onLoad="lockUnlock('webkrit_content_loader');" onkeydown="displayunicode(event);">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <title>Insert title here</title>
        <link rel="stylesheet" href="inside/trans_tc.css">
        <form id="tc_form" name="tc_form" action="TermsConditionsAction" method="post" enctype="multipart/form-data">
            <input type="hidden" name="transactionType" value="INV">
            <div class="mainpage" style="height:830px;">
            <div class="pagetitlecontainer">
                <div class="pagetitle"> Transaction Settings </div>
            </div>
            <div class="contentcontainer">
                <div class="tc_content">
                    <div class="menu_container">
                        <div class="tab_container">
                            <div class="tab_gray" id="rfq_tab"><a href="transactionsTaC.jsp" class="white">RFQ</a></div>
                            <div class="tab_gray" id="quote_tab"><a href="transactionsTaCq.jsp" class="white">Quote</a></div>
                            <div class="tab_gray" id="po_tab"><a href="transactionsTaCp.jsp" class="white">Purchase Order</a></div>
                            <div class="tab_orange" id="invoice_tab"><a href="transactionsTaCi.jsp" class="white">Invoice</a></div>
                        </div>
                        <div style="width:1002px; height:12px; float:left; background-color:#de7b13">
                        </div>
                    </div>
                    <div class="transet_container">
                        <div class="transet_title" id="transet_titile">RFQ Terms and Conditions</div>
                        <% TermsConditionsBean tcb= TermsConditionsMaster.show(companyKey, "INV");
                        try
                        {
                            String donot="style='display:none;'";
                            String does="style='display:block;'";
                            if(tcb==null)
                            {
                                donot="style='display:block;'";
                                does="style='display:none;'";
                        %>
                        <div class="transet_content" id="no_tc" <%=donot%>>
                            <div class="transet_lable">Upload Terms &amp; Conditions</div>
                            <div class="transet_upload_cont">

                                <input type="file" class="browse_btn" name="file_upload" id="file_upload" onchange="$('#tc_form').submit();">

                                    <a class="upload_txt_cont" style="width:230px;cursor:pointer; margin-left:320px;" onClick="$('#file_upload').click();">
                                    <img src="inside/add_file.png" class="upload_img">
                                    <label class="upload_txt_lable" style="width:195px;">Click to upload as Text file</label>
                                </a>
                                <div class="transet_or" style="margin:30px 0px 30px 0px;">(or)</div>
                                <div class="upload_txt_cont" id="upload_txt_cont" style="margin-left:270px;" onclick="$('#tc_content_popup').show();">
                                    <img src="inside/add_file.png" class="upload_img">
                                    <div class="upload_txt_lable" onclick="javascript:$('#tc_content_popup').fadeIn();">Click to copy/paste Terms &amp; Conditions</div>
                                </div>
                            </div>
                        </div>
                        <%
                        }
                        else
                        {
                        %>
                        <div class="transet_content" id="tc_content" <%=does%>>
                            <div class="edit_container" id="edit_container" onclick="javascript:$('#tc_content_popup').show();">
                                <div class="edit_lable">Edit</div>
                                <img src="inside/trans_edit.png" id="edit_btn">
                            </div>
                            <textarea class="transet_lable_content" id="transet_label_content" disabled=""><%=tcb.getTextMessage() %></textarea>
                        </div>
                        <%
                            }
                        }
                        catch(Exception ex)
                        {
                                ErrorMaster.insert("here : "+ex.getMessage());
                        }
                        %>
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
                <% if(tcb==null) { %>
                <textarea class="content_txtarea" id="content_txtarea" name="textMessage"></textarea>
                <% } else { %>
                <textarea class="content_txtarea" id="content_txtarea" name="textMessage"><%=tcb.getTextMessage()%></textarea>
                <% } %>
                <input type="submit" class="gen-btn-blue" onclick="showLoading();" value="Save" style="margin-left: 290px;" id="content_save_btn">
            </div>
        </div>
        </form>
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
        <title>Toast Window </title>
        <link rel="stylesheet" href="inside/Cus_Toast.css">
        <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
        <div id="Toast_Window" style="display:none;">
            <p class="Toast_Data">
            </p>
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
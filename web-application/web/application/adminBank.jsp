<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.home.database.BankInfoMaster"%>
<%@page import="supply.medium.home.bean.BankInfoBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <LINK rel="stylesheet" 
              href="inside/jquery-ui-1.9.2.custom_spinner.css">
        <LINK rel="stylesheet" 
              href="inside/commonlayout.css">
        <LINK rel="stylesheet" href="inside/elements.css">
        <LINK rel="stylesheet" href="inside/Custome_Buttons.css">
        <LINK rel="stylesheet" 
              href="inside/jquery.mCustomScrollbar.css">
        <LINK rel="stylesheet" href="inside/user_home.css">
        <LINK rel="stylesheet" href="inside/dilbag.css">
        <!-- Chat JS style -->
        <LINK 
            rel="stylesheet" type="text/css" href="inside/jquery.chatjs.css">
        <SCRIPT type="text/JavaScript" src="inside/SMNamespace.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.9.2.custom_spinner.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/filterlist.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.customSelect.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.mCustomScrollbar.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.dataTables.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/common.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.validate.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/additional-methods.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dropdownfiller.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/SMNamespace(1).js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/footer.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/ajax_loader.js">
        </SCRIPT>
        <!-- ChatJS and dependencies -->
        <SCRIPT type="text/javascript" src="inside/jquery.chatjs.longpollingadapter.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.autosize.min.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.activity-indicator-1.0.0.min.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.chatjs.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/user_home.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dilbag.js">
        </SCRIPT>
        <TITLE>Supply Medium</TITLE>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onload="lockUnlock('webkrit_content_loader');" onkeydown="displayunicode(event);">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <DIV style="min-height: 700px; background-color: rgb(241, 241, 241);" id="mainpage" 
         class="mainpage">
        <LINK rel="stylesheet" href="inside/newsroom.css">
        <LINK rel="stylesheet" href="inside/newsroom_feed.css">
        <LINK rel="stylesheet" 
              href="inside/view_members.css">
        <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/Custome_Popup.css">
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/Custome_Buttons.css">
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/custome_controls.css">
            <link rel="stylesheet" href="inside/company_website.css">
            <link rel="stylesheet" href="inside/T_form.css">
            <link rel="stylesheet" href="inside/web_page_structure.css">
            <link rel="stylesheet" href="inside/fill_content.css">
            <link rel="stylesheet" href="inside/T_preview.css">
            <link rel="stylesheet" href="inside/content_steps.css">
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/ResetCSS.css">
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/commonlayout.css">
            <div class="pagetitlecontainer">
                <div class="pagetitle">Bank Account Info</div>
            </div>
            <div class="page" style="padding-top: 0px;">
                <div class="contentcontainer pageContent" style="width: 1010px;min-height:650px;font-family: Verdana, Arial, sans-serif;background-color:white;">
                    <div id="webselection">
                        <%
                                    String acName = "",acNo = "",swiffCode="",ifscCode="",branchAddress="";
                                    String paypalAcName="",paypalEmail="";
                                    BankInfoBean cwb = BankInfoMaster.showBankInfoDetail(session.getAttribute("companyKey").toString());
                                    if(cwb!=null)
                                    {
                                        acName = cwb.getAcName();
                                        acNo = cwb.getAcNo();
                                        swiffCode = cwb.getSwiffCode();
                                        ifscCode = cwb.getIfscCode();
                                        branchAddress = cwb.getBranchAddress();
                                        paypalAcName = cwb.getPaypalAcName();
                                        paypalEmail = cwb.getPaypalEmail();
                                    }
                        %>
                        <form method="post" action="InsertUpdateBankInfo" >
                            <div class="websel_container" style="border-bottom:none;">
                                <div style="padding: 15px 0px 0px 35px">
                                    <table>
                                        <%
                                        if(request.getParameter("msg")!=null && request.getParameter("msg").equals("success"))
                                        {
                                        %>
                                        <tr height="50">
                                            <td align="center" colspan="2" style="color:green;font-size:16px;font-weight:bold;">Info Updated</td>
                                        </tr>
                                        <%
                                        }
                                        else if(request.getParameter("msg")!=null && request.getParameter("msg").equals("fail"))
                                        {
                                        %>
                                        <tr height="50">
                                            <td align="center" colspan="2" style="color:red;font-size:16px;font-weight:bold;">Info Updaion failed</td>
                                        </tr>
                                        <%
                                        }
                                        %>
                                        <tr height="50">
                                            <td width="200"><label for="text_company_url">A/C Name</label></td>
                                            <td><input name="acName" class="textbox" value="<%=acName%>" required  type="text" autocomplete="off"></td>
                                        </tr>
                                        <tr height="50">
                                            <td><label for="text_company_url">A/C No</label></td>
                                            <td><input name="acNo" class="textbox" value="<%=acNo%>" required  type="text" autocomplete="off"></td>
                                        </tr>
                                        <tr height="50">
                                            <td><label for="text_company_url">SWIFF Code</label></td>
                                            <td><input name="swiffCode" class="textbox" value="<%=swiffCode%>" required  type="text" autocomplete="off"></td>
                                        </tr>
                                        <tr height="50">
                                            <td><label for="text_company_url">RTGS / NEFT IFSC Code</label></td>
                                            <td><input name="ifscCode" class="textbox" value="<%=ifscCode%>" required  type="text" autocomplete="off"></td>
                                        </tr>
                                        <tr height="50">
                                            <td><label for="text_company_url">Branch Address</label></td>
                                            <td><input name="branchAddress" class="textbox" value="<%=branchAddress%>" required  type="text" autocomplete="off"></td>
                                        </tr>
                                        <tr height="50">
                                            <td align="center" colspan="2"><b><i style="font-size:16px;">OR</i></b></td>
                                        </tr>
                                        <tr height="50">
                                            <td><label for="text_company_url">Paypal Account Holder Name</label></td>
                                            <td><input name="paypalAcName" class="textbox" value="<%=paypalAcName%>" required  type="text" autocomplete="off"></td>
                                        </tr>
                                        <tr height="50">
                                            <td><label for="text_company_url">Paypal E-mail Address</label></td>
                                            <td><input name="paypalEmail" class="textbox" value="<%=paypalEmail%>" required  type="email" autocomplete="off"></td>
                                        </tr>
                                        <tr height="50">
                                            <td></td>
                                            <td><input type="submit" style="margin-top:15px;" class="gen-btn-Orange" value="Update Info" /></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <%@include file="_footer.jsp" %>
            <DIV>
            </DIV>
            <LINK rel="stylesheet" href="inside/Custome_Popup.css">
            <LINK rel="stylesheet" href="inside/popup_warning.css">
            <SCRIPT type="text/javascript">
                $(function() {

                    $(".Cus_Popup_Close").click(function() {
                        $("#warning_container").hide();
                    });
                    $("#Popup_Cancel").click(function() {
                        $("#warning_container").hide();
                    });

                    $('#okbtn').click(
                    function()
                    {
                        deleteConfirm();

                        $("#warning_container").hide();
                    }
                );

                    $('#Popup_Cancel').click(
                    function()
                    {
                        cancelInfo();

                        $("#warning_container").hide();
                    }
                );
                }
            );

                function showWarning(message)
                {
                    $('#war_message').text(message);

                    $("#warning_container").show();

                    $("#Popup_Cancel").focus();
                }

            </SCRIPT>
            <DIV style="display: none;" id="warning_container">
                <DIV id="warning_popup">
                    <DIV id="war_head">
                        <LABEL id="war_head_title">Warning</LABEL>
                    </DIV>
                    <DIV id="war_body">
                        <LABEL id="war_message">
                        </LABEL>
                        <DIV id="war_btns">
                            <INPUT style="margin-right: 30px; float: left;" id="okbtn" class="pop-button pop-button-White" value="Yes" type="button">
                            <INPUT style="float: left;" id="Popup_Cancel" class="pop-button pop-button-White" value="No" type="button">
                        </DIV>
                    </DIV>
                </DIV>
            </DIV>
            <LINK rel="stylesheet" href="inside/Custome_Popup.css">
            <LINK rel="stylesheet" href="inside/ResetCSS.css">
            <SCRIPT type="text/javascript">
                $(function() {

                    $(".Gen_Cus_Popup_Close").click(function() {
                        $("#add_watchlist_popup").hide();
                    });
                    $("#cancel_watchlist").click(function() {
                        $("#add_watchlist_popup").hide();
                    });

                    $('#new_watchlist_input').keyup(function()
                    {
                        $('#new_watchlist_input_err').text("");
                    });
                }
            );
            </SCRIPT>
            <DIV style="display: none;" id="add_watchlist_popup" class="Custome_Popup_Window">
                <TABLE>
                    <TBODY>
                        <TR>
                            <TD style="vertical-align: middle;">
                                <DIV class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout">
                                    <DIV class="Popup_Tilte_NewGroup Gen_Popup_Title ">					 	New Watchlist
                                        Creation
                                        <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                        </DIV>
                                    </DIV>
                                    <DIV class="popupcontent">
                                        <DIV class="row">
                                            <DIV style="width: 150px;" class="label">Watchlist Name</DIV>
                                            <INPUT style="border: 1px solid rgb(165, 183, 187); width: 300px; height: 20px;"
                                                   id="new_watchlist_input" class="textbox required" name="new_watchlist_input"
                                                   type="text" autocomplete="off">
                                            <LABEL style="margin-left: 150px;" id="new_watchlist_input_err"
                                                   class="error">
                                            </LABEL>
                                        </DIV>
                                        <DIV style="margin-top: 60px; margin-left: 120px;" class="row">
                                            <INPUT style="margin-right: 20px;" id="cancel_watchlist" class="gen-btn-Gray" value="Cancel" type="button">
                                            <INPUT id="save_watchlist" class="gen-btn-Orange" value="Create" type="button">
                                        </DIV>
                                    </DIV>
                                </DIV>
                            </TD>
                        </TR>
                    </TBODY>
                </TABLE>
            </DIV>
            <SCRIPT>
                $(".Gen_Cus_Popup_Close").click(function()
                {
                    $("#member_container").hide();
                });
            </SCRIPT>
            <DIV style="display: none;" id="member_container" class="container Cus_Popup_Outline">
                <DIV style="border-radius: 0px;" class="Popup_Tilte_NewGroup Gen_Popup_Title">
                    <DIV style="padding: 0px 0px 0px 15px; float: left;">Members</DIV>
                    <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                    </DIV>
                </DIV>
                <DIV class="member_container">
                    <DIV class="add_member_container">
                        <DIV id="members_count" class="add_member">
                        </DIV>
                        <DIV class="textbox_container">
                            <INPUT class="newsroom_textbox" type="text" autocomplete="off"
                                   placeholder="Find a Member">
                            <IMG style="float: left; cursor: pointer;" src="inside/search_lens.png">
                        </DIV>
                    </DIV>
                    <DIV class="button_container">
                        <DIV id="delete_members_btn" class="del_button">Delete</DIV>
                    </DIV>
                </DIV>
                <DIV id="memberlist_container" class="memberlist_container">
                </DIV>
            </DIV>
            <LINK rel="stylesheet" href="inside/Custome_Popup.css">
            <LINK rel="stylesheet" href="inside/original_image_popup.css">
            <SCRIPT type="text/javascript">
                $(function()
                {

                    $("#image_head").click(function()
                    {
                        $("#image_container").hide();
                    });
                }

            );

                function showFullSizeImage(url)
                {
                    $("#image_container").show();
                    $("#image_body").empty();
                    var imageTag = "<img src='" + url + "' class='original_img' id='orgi_image'/>";
                    $("#image_body").append(imageTag);

                    createThumbnail($("#orgi_image"), 900, 600);
                }
            </SCRIPT>
            <DIV style="display: none;" id="image_container">
                <CENTER>
                    <DIV id="image_popup">
                        <DIV id="image_head">
                            <INPUT style="border: currentColor; color: rgb(204, 204, 204); padding-right: 5px; font-size: 20px; float: right; cursor: pointer; background-color: transparent;" id="Popup_Cancel" value="X" type="button">
                        </DIV>
                        <DIV id="image_body">
                        </DIV>
                    </DIV>
                </CENTER>
            </DIV>
            <SCRIPT>

                $.getScript("/SupplyMedium/Views/NewsRoom/js/newsroom_feed.js");
                $.getScript("/SupplyMedium/Views/NewsRoom/js/watchlist_feed.js");

                $.getScript("/SupplyMedium/Views/NewsRoom/js/watchlist_members.js", function(data, textStatus, jqxhr)
                {
                    $("#delete_members_btn").click(removeMembers);

                });

                $.getScript("/SupplyMedium/Views/NewsRoom/js/newsroom.js", function(data, textStatus, jqxhr)
                {
                    newsroomOnload();

                });
                $.getScript("/SupplyMedium/Views/NewsRoom/js/watchlist.js", function(data, textStatus, jqxhr)
                {
                    fetchWatchLists();
                    $("#save_watchlist").click(saveWatchList);

                });

            </SCRIPT>
        </DIV>
        <%@include file="_invite.jsp" %>
        <SCRIPT type="text/JavaScript" src="inside/restrict_menu1.js">
        </SCRIPT>
        <SCRIPT>


            $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt_fieldvalidator.js");
            $.getScript("/SupplyMedium/Views/Registration/js/regvalidator.js");
            $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt.js", function(data, textStatus, jqxhr) {
                userOnload();

            });

        </SCRIPT>
    </BODY>
</HTML>

<%@page import="supply.medium.home.bean.CountryBean"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0048)http://localhost:8084/SupplyMedium/user_home.jsp -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK 
            rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
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
        <SCRIPT type="text/JavaScript" src="inside/usermgmt.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/usermgmt_fieldvalidator.js">
        </SCRIPT>
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
<!--        <SCRIPT type="text/JavaScript" src="inside/dropdownfiller.js">
        </SCRIPT>-->
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

    </SCRIPT>
    <TITLE>Supply Medium</TITLE>
    <!--<script>
    Usr_anlys('Admin');    
    </script>-->
    <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
</HEAD>
<BODY onLoad="lockUnlock('webkrit_content_loader');loadValidator();//Usr_anlys('Admin');
        //customizeMenu();
        //pypl_rslt('null');">
<LINK rel="stylesheet" 
      href="inside/userheader.css">
<LINK rel="stylesheet" href="inside/notifi_dropdown.css">
<%@include file="_header.jsp"%>
<!--    <DIV style="min-height: 700px; background-color: rgb(241, 241, 241);" id="mainpage" 
         class="mainpage">-->
<LINK rel="stylesheet" href="inside/newsroom.css">
<LINK rel="stylesheet" href="inside/newsroom_feed.css">
<LINK rel="stylesheet" 
      href="inside/view_members.css">
<!--<link rel="stylesheet" href="/SupplyMedium/dilbag.css">-->
<!---->
<div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!--<link rel="stylesheet" href="inside/commonlayoutusr.css">
    <link rel="stylesheet" href="inside/elementsusr.css">
    <link rel="stylesheet" href="inside/jquery-ui-1.10.0.customusr.css">
    <link rel="stylesheet" href="inside/Custome_Buttonsusr.css">-->
    <link rel="stylesheet" href="inside/usermgmt.css">
    <link rel="stylesheet" href="inside/tablestyle.css">
    <link rel="stylesheet" href="inside/usermgmt_popup.css">
    <div class="pagetitlecontainer">
        <div class="pagetitle">Users</div>
    </div>
    <div id="sv_dtl_cnfrmtn_msg_dv" class="update_user_detail2" onClick="$('#sv_dtl_cnfrmtn_msg_dv').fadeOut();">User detail has been updated</div>
    <div class="page">
        <div class="contentcontainer" style="min-height:0px;">
            <form name="usermgmtfrm" id="usermgmtfrm" method="post" action="AdminAddUser" style="min-height:600px;" novalidate="novalidate">
                <div id="usermgmt_content" style="display:block">
                    <div class="tabbar">
                        <div class="usermgmterr" id="usertblerr">
                        </div>
                        <div class="normal deactive" id="user_details" style="display:block;"><a href="adminListUsers.jsp">List Of Users</a></div>
                        <div class="highlight" id="new_users" style="display:block;"><a href="adminNewUser.jsp">Add User</a></div>
                    </div>
                    <div class="newusercontent" id="newuser_content">
                        <div class="contenthead" id="content_head">Invite a New User to Signup</div>
                        <div class="contentdetail" id="content_detail" style="padding-top:60px;">
                            <div style="float: left;">
                                <div class="row" style="margin-left: 60px;">
                                    <div class="label" style="font-size:13px;">
                                        First Name<span class="mandatory">*</span>
                                    </div>
                                    <input type="text" autocomplete="off" id="firstname" name="firstname" class="textbox">
                                </div>
                                <div class="row" style="margin-left: 60px;">
                                    <div class="label" style="font-size:13px;">
                                        Last Name<span class="mandatory">*</span>
                                    </div>
                                    <input type="text" autocomplete="off" id="lastname" name="lastname" class="textbox">
                                </div>
                                <div class="row" style="margin-left: 60px;">
                                    <div class="label" style="font-size:13px;">
                                        E-mail ID<span class="mandatory">*</span>
                                    </div>
                                    <input type="text" autocomplete="off" name="email" id="email" class="required textbox email" onblur="validateEmail(this.value)">
                                    <span id="emailcheck">
                                    </span>
                                    <label for="email" generated="true" class="error" id="emailerr">
                                    </label>
                                    <input type="hidden" name="emailexist" id="emailexist">
                                </div>
                                <div class="row" style="margin-left: 60px;">
                                    <div class="label" style="font-size:13px;">
                                        Phone<span class="mandatory">*</span>
                                    </div>
                                    <input type="text" autocomplete="off" name="phone" id="phone" class="required textbox" onblur="validatePhoneNo(this.value)">
                                    <div id="phonenocheck">
                                    </div>
                                    <label for="phone" generated="true" class="error" id="phoneerr">
                                    </label>
                                    <input type="hidden" name="phoneexist" id="phoneexist">
                                </div>
                                <div class="row" style="margin-left: 60px;">
                                    <div class="label" style="font-size:13px;">
                                        Mobile
                                    </div>
                                    <input type="text" autocomplete="off" id="cell" name="cell" class="textbox">
                                </div>
                                <div class="row" style="margin-left: 60px;">
                                    <div class="label" style="font-size:13px;">
                                        City
                                    </div>
                                    <input type="text" autocomplete="off" id="city" name="city" class="textbox">
                                </div>
                                <div class="row" style="margin-left:60px;">
                                    <div class="label" style="font-size:13px;">
                                        Country<span class="mandatory">*</span>
                                    </div>
                                    <SELECT 
                                        id="countryregion_0" name="countryregion" onChange="fetchState(this.value);" 
                                        class="required selectbox hasCustomSelect" style="margin-bottom: 5px;
-webkit-appearance: menulist-button;
width: 188px;
position: absolute;
height: 28px;
font-size: 12px;
background-color: rgb(243, 243, 243);
border: 1px solid rgb(165, 183, 187);" name="country">
                                        <OPTION value="" selected>--Select Country--</OPTION>
                                            <%
                                                ArrayList countryList = CountryMaster.showAll();
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
                                <div class="row" style="margin-left:60px;">
                                    <div class="label" style="font-size:13px;">State/Province</div>
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
                                <div class="row" style="margin-left: 60px;">
                                    <div class="label" style="font-size:13px;">
                                        Zip Code/Postal Code
                                    </div>
                                    <input type="text" autocomplete="off" id="zipcode" name="zipcode" class="textbox">
                                </div>
                            </div>
                            <div class="rightcontent" style="width:45%;float: right;height: auto;">
                                <div class="row">
                                    <div class="label">
                                        User Type<span class="mandatory">*</span>
                                    </div>
                                    <SELECT id="usertype" name="usertype" class="required selectbox hasCustomSelect" style="margin-bottom: 5px;
-webkit-appearance: menulist-button;
width: 188px;
position: absolute;
height: 28px;
font-size: 12px;
background-color: rgb(243, 243, 243);
border: 1px solid rgb(165, 183, 187);" name="country">
                                        <option>Intranet User</option>
                                        <option>Regular User</option>   
                                    </SELECT>
                                </div>
                                <div class="row">
                                    <div class="label">Department</div>
                                    <input type="text" autocomplete="off" name="department" id="department" class="textbox">
                                </div>
                                <div class="row">
                                    <div class="label">Manager/Supervisor</div>
                                    <input type="text" autocomplete="off" name="managersupervisor" id="managersupervisor" class="textbox">
                                </div>
                                <div class="row">
                                    <div class="label">Fax</div>
                                    <input type="text" autocomplete="off" name="fax" id="fax" class="textbox">
                                </div>
                            </div>
                            <div style="margin-left: 350px; margin-top: 14px; float: left;">
                                <input type="button" value="Reset" class="gen-btn-Gray" onClick="reset('usermgmtfrm');
                                        resetNewUserFrm();">
                                    <input type="submit" value="Add User" style="margin-left: 30px;" class="gen-btn-Orange" onClick="return addUser();">
                                <input type="hidden" id="RequestType" name="RequestType" value="NewSignup">
                            </div>
                            <div id="newusererr" class="usermgmterr">
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Toast Window </title>
    <link rel="stylesheet" href="inside/Cus_Toast.css">
    <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
    <div id="Toast_Window" style="display:none;">
        <p class="Toast_Data">
        </p>
    </div>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="inside/Custome_Popup.css">
    <div style="display: none;" id="policies_popup" class="Custome_Popup_Window">
        <table>
            <tbody>
                <tr>
                    <td style="vertical-align: middle;">
                        <div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout">
                            <div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
                                User Settings
                                <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                </div>
                            </div>
                            <div style="width:100%;height:auto;z-index:999999;" id="user_settings">
                            </div>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    <input type="hidden" id="emailId">
    <input type="hidden" id="rowno">
    <%@include file="_footer.jsp" %>
    <div>
    </div>
</div>
<%@include file="_invite.jsp" %>
</BODY>
</HTML>

<%@page import="supply.medium.home.bean.AccountPolicyBean"%>
<%@page import="supply.medium.home.database.AccountPolicyMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0048)http://localhost:8084/SupplyMedium/user_home.jsp -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK 
            rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <LINK rel="stylesheet" href="inside/jquery-ui-1.9.2.custom_spinner.css">
        <LINK rel="stylesheet" href="inside/commonlayout.css">
        <LINK 
            rel="stylesheet" href="inside/elements.css">
        <LINK rel="stylesheet" 
              href="inside/Custome_Buttons.css">
        <LINK rel="stylesheet" 
              href="inside/jquery.mCustomScrollbar.css">
        <LINK rel="stylesheet" 
              href="inside/user_home.css">
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
    <BODY onLoad="lockUnlock('webkrit_content_loader');
        Usr_anlys('Admin');
        customizeMenu();
        pypl_rslt('null');" onkeydown="displayunicode(event);">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <DIV style="min-height: 700px; background-color: rgb(241, 241, 241);" id="mainpage" 
         class="mainpage">
        <TITLE>News Room</TITLE>
        <LINK rel="stylesheet" href="inside/newsroom.css">
        <LINK rel="stylesheet" href="inside/newsroom_feed.css">
        <LINK 
            rel="stylesheet" href="inside/view_members.css">
        <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
            <link rel="stylesheet" href="inside/commonlayout.css">
            <link rel="stylesheet" href="inside/elements.css">
            <link rel="stylesheet" href="inside/account_policies.css">
            <link rel="stylesheet" href="inside/elements.css">
            <link rel="stylesheet" href="inside/Custome_Buttons.css">
            <div class="pagetitlecontainer">
                <div class="pagetitle">Account Policies</div>
            </div>
            <div class="page">
                <div class="contentcontainer">
                    <div class="content spinner" style="padding: 0px; width: 1002px; height: 720px;" id="accpolicies_content">
                        <form method="post" action="AccountPolicyUpdate">
                            <%  ArrayList policyList = AccountPolicyMaster.showAccountPolicy(session.getAttribute("companyKey").toString());
                                //out.print("policyList :"+policyList);
                                String accountPolicyKey = "";
                                String enforcePasswordHistory = "";
                                String maxPasswordAge = "";
                                String minPasswordAge = "";
                                String minPasswordLength = "";
                                String sendEmailBeforePasswordExpire = "";
                                String sendDailyReminderAfterDate = "";
                                String passwordComplexity = "";
                                String upperLowerCaseLetter = "";
                                String numericalCharacters = "";
                                String nonAlphanummericCharacters = "";
                                String accountLockAfterAttempts = "";
                                String lockoutDuration = "";
                                String resetLockoutCounterAfter = "";
                                String accountUnlockedByAdmin = "";
                                String sessionEndTime = "";
                                AccountPolicyBean apb = null;
                                for (int i = 0; i < policyList.size(); i++) {
                                    apb = (AccountPolicyBean) policyList.get(i);
                                    accountPolicyKey = apb.getAccountPolicyKey();
                                    enforcePasswordHistory = apb.getEnforcePasswordHistory();
                                    maxPasswordAge = apb.getMaxPasswordAge();
                                    minPasswordAge = apb.getMinPasswordAge();
                                    minPasswordLength = apb.getMinPasswordLength();
                                    sendEmailBeforePasswordExpire = apb.getSendEmailBeforePasswordExpire();
                                    sendDailyReminderAfterDate = apb.getSendDailyReminderAfterDate();
                                    passwordComplexity = apb.getPasswordComplexity();
                                    upperLowerCaseLetter = apb.getUpperLowerCaseLetter();
                                    numericalCharacters = apb.getNumericalCharacters();
                                    nonAlphanummericCharacters = apb.getNonAlphanummericCharacters();
                                    accountLockAfterAttempts = apb.getAccountLockAfterAttempts();
                                    lockoutDuration = apb.getLockoutDuration();
                                    resetLockoutCounterAfter = apb.getResetLockoutCounterAfter();
                                    accountUnlockedByAdmin = apb.getAccountUnlockedByAdmin();
                                    sessionEndTime = apb.getSessionEndTime();
                                }
                            %>
                            <input type="hidden" name="accountPolicyKey" value="<%=accountPolicyKey%>">
                            <div class="policiesleft">
                                <div class="pass_policies">
                                    <div class="policyhead" style="margin:0px;">
                                        Password Policies
                                    </div>
                                    <div class="policyrow">
                                        <label class="label" style="font-size: 12px;"> Enforce
                                            password history</label>
                                        <span class="ui-spinner ui-widget ui-widget-content ui-corner-all">
                                            <input id="pass_his" readonly style="width: 34px; height: 20px; float: left;" name="enforcePasswordHistory" value="<%=enforcePasswordHistory%>" class="ui-spinner-input" autocomplete="off" role="spinbutton" aria-valuemin="0" aria-valuemax="5">
                                            <a onclick="javascript:document.getElementById('pass_his').value=spinUp(document.getElementById('pass_his').value)" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-n">?</span>
                                                </span>
                                            </a>
                                            <a onclick="javascript:document.getElementById('pass_his').value=spinDown(document.getElementById('pass_his').value,3)" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-s">?</span>
                                                </span>
                                            </a>
                                        </span>
                                        <label class="label" style="font-size: 12px; float: right;">
                                            days</label>
                                    </div>
                                    <div class="policyrow">
                                        <label class="label" style="font-size: 12px;"> Maximum
                                            password age</label>
                                        <span class="ui-spinner ui-widget ui-widget-content ui-corner-all">
                                            <input id="max_pass" readonly style="width: 34px; height: 20px; float: left;" name="maxPasswordAge" value="<%=maxPasswordAge%>" class="ui-spinner-input" autocomplete="off" role="spinbutton" aria-valuemin="1" aria-valuemax="999">
                                            <a onclick="javascript:document.getElementById('max_pass').value=spinUp(document.getElementById('max_pass').value)" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-n">?</span>
                                                </span>
                                            </a>
                                            <a onclick="javascript:document.getElementById('max_pass').value=spinDown(document.getElementById('max_pass').value,5000)" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-s">?</span>
                                                </span>
                                            </a>
                                        </span>
                                        <label class="label" style="font-size: 12px; float: right;">
                                            days</label>
                                    </div>
                                    <div class="policyrow">
                                        <label class="label" style="font-size: 12px;"> Minimum
                                            password age</label>
                                        <span class="ui-spinner ui-widget ui-widget-content ui-corner-all">
                                            <input id="min_pass" readonly style="width:34px; height: 20px; float: left;" name="minPasswordAge"  value="<%=minPasswordAge%>" class="ui-spinner-input" autocomplete="off" role="spinbutton" aria-valuemin="0" aria-valuemax="998">
                                            <a onclick="javascript:document.getElementById('min_pass').value=spinUp(document.getElementById('min_pass').value)" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-n">?</span>
                                                </span>
                                            </a>
                                            <a onclick="javascript:document.getElementById('min_pass').value=spinDown(document.getElementById('min_pass').value,3)" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-s">?</span>
                                                </span>
                                            </a>
                                        </span>
                                        <label class="label" style="font-size: 12px; float: right;">
                                            days</label>
                                    </div>
                                    <div class="policyrow">
                                        <label class="label" style="font-size: 12px;"> Minimum
                                            password Length</label>
                                        <span class="ui-spinner ui-widget ui-widget-content ui-corner-all">
                                            <input id="pass_length" readonly style="width: 34px; height: 20px; float: left;" name="minPasswordLength" value="<%=minPasswordLength%>" class="ui-spinner-input" autocomplete="off" role="spinbutton" aria-valuemin="5" aria-valuemax="10">
                                            <a onclick="javascript:document.getElementById('pass_length').value=spinUp(document.getElementById('pass_length').value)" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-n">?</span>
                                                </span>
                                            </a>
                                            <a onclick="javascript:document.getElementById('pass_length').value=spinDown(document.getElementById('pass_length').value,9)" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-s">?</span>
                                                </span>
                                            </a>
                                        </span>
                                        <label class="label" style="font-size: 12px; float: right;">
                                            characters</label>
                                    </div>
                                    <div class="policyrow">
                                        <label class="label" style="font-size: 12px;"> Send
                                            E-mail notifications </label>
                                        <span class="ui-spinner ui-widget ui-widget-content ui-corner-all">
                                            <input id="email_noti" readonly style="width: 34px; height: 20px; float: left;" name="sendEmailBeforePasswordExpire" value="<%=sendEmailBeforePasswordExpire%>" class="ui-spinner-input" autocomplete="off" role="spinbutton" aria-valuemin="1" aria-valuemax="4999" aria-valuenow="1">
                                            <a onclick="javascript:document.getElementById('email_noti').value=spinUp(document.getElementById('email_noti').value)" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-n">?</span>
                                                </span>
                                            </a>
                                            <a onclick="javascript:document.getElementById('email_noti').value=spinDown(document.getElementById('email_noti').value,7)" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-s">?</span>
                                                </span>
                                            </a>
                                        </span>
                                        <label class="label" style="font-size: 12px; float: right;">
                                            days before password expiry</label>
                                    </div>
                                    <div class="policyrow">
                                        <div class="checkContainer">
                                            <input type="checkbox" id="daily_rem" class="ap_check_lbl" name="sendDailyReminderAfterDate" value="yes" <% if (sendDailyReminderAfterDate.trim().equals("yes")) {%> checked="checked" <% } %> >
                                            <div>
                                            </div>
                                        </div>
                                        <label for="daily_rem" class="ap_check_lbl" style="width:450px;">
                                            Send daily remainder after this date</label>
                                    </div>
                                </div>
                                <div class="pass_compl">
                                    <fieldset class="policy_fieldset">
                                        <legend class="ap_legend">Password Complexity
                                        </legend>
                                        <div class="policyrow" style="height:30px;width:350px">
                                            <input name="passwordComplexity"  id="pass_comp_ena" value="yes" type="radio"  onclick="enablePassComp();" <% if (passwordComplexity.trim().equals("yes")) { %> checked="checked" <% } %> class="ap_check_lbl">
                                            <label for="pass_comp_enb" class="ap_check_lbl">Enabled</label>
                                            <input name="passwordComplexity" id="pass_comp_dis" value="no" type="radio" onClick="disablePassComp();" <% if (passwordComplexity.trim().equals("no")) { %> checked="checked" <% } %> class="ap_check_lbl">
                                            <label for="pass_comp_dis" class="ap_check_lbl">Disabled</label>
                                        </div>
                                        <div class="policyrow" style="height:30px;width:350px">
                                            <div class="checkContainer">
                                                <input type="checkbox" id="upperlower" class="ap_check_lbl" name="upperLowerCaseLetter" value="yes" <% if (upperLowerCaseLetter.trim().equals("yes")) { %> checked="checked" <% } %>>
                                                <div>
                                                </div>
                                            </div>
                                            <label for="upperlower" style="width:350px;" class="ap_check_lbl">
                                                Upper and lower case letters</label>
                                        </div>
                                        <div class="policyrow" style="height:30px;width:300px">
                                            <div class="checkContainer">
                                                <input type="checkbox" id="numeric" class="ap_check_lbl" name="numericalCharacters" value="yes" <% if (numericalCharacters.trim().equals("yes")) { %> checked="checked" <% } %>>
                                                <div>
                                                </div>
                                            </div>
                                            <label for="numeric" class="ap_check_lbl" style="width:300px;">
                                                Numeric characters</label>
                                        </div>
                                        <div class="policyrow" style="height:30px;width:350px">
                                            <div class="checkContainer">
                                                <input type="checkbox" id="non_alpha" name="nonAlphanummericCharacters" value="yes" class="ap_check_lbl" <% if (nonAlphanummericCharacters.trim().equals("yes")) { %> checked="checked" <% }%>>
                                                <div>
                                                </div>
                                            </div>
                                            <label for="non_alpha" class="ap_check_lbl" style="width:300px;">
                                                Non-Alphanumeric charcters</label>
                                        </div>
                                    </fieldset>
                                    <div id="pass_comp_error" class="">
                                    </div>
                                </div>
                            </div>
                            <div class="policiesright">
                                <div class="lockout_policies">
                                    <div class="policyhead">
                                        Account Lockout Policies
                                    </div>
                                    <div class="policyrow">
                                        <label class="label" style="font-size: 12px;"> Account
                                            will lockout after</label>
                                        <span class="ui-spinner ui-widget ui-widget-content ui-corner-all">
                                            <input id="lockout" readonly style="width: 34px; height: 20px; float: left;" name="accountLockAfterAttempts" value="<%=accountLockAfterAttempts%>" class="ui-spinner-input" autocomplete="off" role="spinbutton" aria-valuemin="3" aria-valuemax="10">
                                            <a onclick="javascript:document.getElementById('lockout').value=spinUp(document.getElementById('lockout').value)" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-n">?</span>
                                                </span>
                                            </a>
                                            <a onclick="javascript:document.getElementById('lockout').value=spinDown(document.getElementById('lockout').value,3)" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-s">?</span>
                                                </span>
                                            </a>
                                        </span>
                                        <label class="label" style="font-size: 12px; float: right;">
                                            invalid login attempts</label>
                                    </div>
                                    <div class="policyrow">
                                        <label class="label" style="font-size: 12px;"> Lockout
                                            duration</label>
                                        <span class="ui-spinner ui-widget ui-widget-content ui-corner-all">
                                            <input id="lockout_dur" readonly value="<%=lockoutDuration%>" style="width: 34px; height: 20px; float: left;" name="lockoutDuration" class="ui-spinner-input" autocomplete="off" role="spinbutton" aria-valuemin="0" aria-valuemax="1440">
                                            <a onclick="javascript:document.getElementById('lockout_dur').value=spinUp(document.getElementById('lockout_dur').value)" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-n">?</span>
                                                </span>
                                            </a>
                                            <a onclick="javascript:document.getElementById('lockout_dur').value=spinDown(document.getElementById('lockout_dur').value,3)" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-s">?</span>
                                                </span>
                                            </a>
                                        </span>
                                        <label class="label" style="font-size: 12px; float: right;">
                                            minutes</label>
                                    </div>
                                    <div class="policyrow">
                                        <label class="label" style="font-size: 12px;"> Reset lockout counter after</label>
                                        <span class="ui-spinner ui-widget ui-widget-content ui-corner-all">
                                            <input id="reset_counter" readonly value="<%=resetLockoutCounterAfter%>" style="width: 34px; height: 20px; float: left;" name="resetLockoutCounterAfter" class="ui-spinner-input" autocomplete="off" role="spinbutton" aria-valuemin="0" aria-valuemax="10">
                                            <a onclick="javascript:document.getElementById('reset_counter').value=spinUp(document.getElementById('reset_counter').value)" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-n">?</span>
                                                </span>
                                            </a>
                                            <a onclick="javascript:document.getElementById('reset_counter').value=spinDown(document.getElementById('reset_counter').value,5)" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-s">?</span>
                                                </span>
                                            </a>
                                        </span>
                                        <label class="label" style="font-size: 12px; float: right;">
                                            minutes</label>
                                    </div>
                                    <div class="policyrow" style="margin-bottom:20px;">
                                        <div class="checkContainer">
                                            <input type="checkbox" id="unlock_by_admin" name="accountUnlockedByAdmin" value="yes" class="ap_check_lbl" <% if (accountUnlockedByAdmin.trim().equals("yes")) { %> checked="checked" <% }%>>
                                            <div>
                                            </div>
                                        </div>
                                        <label for="unlock_by_admin" class="ap_check_lbl" style="width:450px;">
                                            Account must be unlocked by an administrator</label>
                                    </div>
                                </div>
                                <div class="session_mgmt">
                                    <div class="policyhead">
                                        Session Management
                                    </div>
                                    <div class="policyrow">
                                        <label class="label" style="font-size: 12px;"> End
                                            session after</label>
                                        <span class="ui-spinner ui-widget ui-widget-content ui-corner-all">
                                            <input id="session" readonly style="width: 34px; height: 20px; float: left;" name="sessionEndTime" value="<%=sessionEndTime%>" class="ui-spinner-input" autocomplete="off" role="spinbutton" aria-valuemin="5" aria-valuemax="60">
                                            <a onclick="javascript:document.getElementById('session').value=spinUp(document.getElementById('session').value)" class="ui-spinner-button ui-spinner-up ui-corner-tr ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-n">?</span>
                                                </span>
                                            </a>
                                            <a onclick="javascript:document.getElementById('session').value=spinDown(document.getElementById('session').value,30)" class="ui-spinner-button ui-spinner-down ui-corner-br ui-button ui-widget ui-state-default ui-button-text-only" tabindex="-1" role="button" aria-disabled="false">
                                                <span class="ui-button-text">
                                                    <span class="ui-icon ui-icon-triangle-1-s">?</span>
                                                </span>
                                            </a>
                                        </span>
                                        <label class="label" style="font-size: 12px; float: right;">
                                            minutes of inactivity</label>
                                    </div>
                                </div>
                            </div>
                            <input type="submit" value="Save" class="gen-btn-Orange ap_upt_btn" onClick="showLoading();">
                            <div id="acc_policy_err">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div>
            </div>
            <%@include file="_footer.jsp" %>
            <DIV>
            </DIV>
        </DIV>
        <%@include file="_invite.jsp" %>
        <SCRIPT type="text/JavaScript" src="inside/restrict_menu.js">
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

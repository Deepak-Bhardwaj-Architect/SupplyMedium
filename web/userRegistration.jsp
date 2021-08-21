<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.home.bean.CountryBean"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK 
            rel="stylesheet" href="inside/companyregistration.css">
        <LINK 
            rel="stylesheet" href="inside/usersignup.css">
        <LINK rel="stylesheet" 
              href="inside/layout.css">
        <LINK rel="stylesheet" href="inside/elements.css">
        <LINK 
            rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <LINK 
            rel="stylesheet" href="inside/tooltipster.css">
        <LINK rel="stylesheet" 
              href="inside/Custome_Buttons.css">
        <LINK rel="stylesheet" href="inside/Custome_Popup.css">
        <SCRIPT type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.validate.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.customSelect.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/additional-methods.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dropdownfiller.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/common.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/usersignup.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/signupfieldsvalidator.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/regvalidator.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/captcha.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/regvalidator.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/webkrit.js">
        </SCRIPT>
        <SCRIPT>
            $(document).ready(function () {
                $('.tooltip').tooltipster({
                    interactive: true,
                    theme: '.my-custom-theme'
                });

                $('select.selectbox').customSelect();
            });



        </SCRIPT>
        <TITLE>User Registration - SupplyMedium Inc.</TITLE>
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onLoad="generate_captcha('lcaptcha');
            loadValidator();" onkeydown="displayunicode(event);">
    <LINK rel="stylesheet" href="inside/header.css">
    <DIV id="regnheader" class="header">
        <DIV class="headercontent">
            <DIV class="logo">
                <A href="index.jsp">
                    <IMG 
                        style="border: none; margin-top: 10px;" src="inside/logo.png" 
                        width="325" height="100">
                </A>
            </DIV>
        </DIV>
    </DIV>
    <DIV class="pagetitlecontainer">
        <DIV class="pagetitle">New User Sign Up</DIV>
    </DIV>
    <DIV class="contentcontainer">
        <DIV class="content">
            <FORM id="signupform" onSubmit="return submitSignupForm();" method="post" 
                  action="UserRegistration">
                <DIV class="leftcontent">
                    <DIV class="row">
                        <DIV class="label">							Country<SPAN class="mandatory">*</SPAN>
                        </DIV>
                        <SELECT onChange="fetchCompany(this.value);fetchState(this.value);"
                            class="required selectbox">
                            <OPTION value="">--Select 
                                Country--</OPTION>
                                <%
                                    ArrayList countryList = CountryMaster.showAll();
                                    CountryBean scb = null;
                                    for (int i = 0; i < countryList.size(); i++) {
                                        scb = (CountryBean) countryList.get(i);
                                %>
                            <option value="<%=scb.getCountryKey()+"*"+scb.getCountryCode() %>">
                                <%=scb.getCountryName()%>
                            </option>
                            <%
                                }
                            %>
                        </SELECT>
                        <input type="hidden" id="countryregion_0" name="country">
                        <LABEL id="countryregion_0err" class="error" for="countryregion_0" 
                               generated="true">
                        </LABEL>
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">							Select Company<SPAN class="mandatory">*</SPAN>
                        </DIV>
                        <DIV style="width: 220px; height: 30px; float: left;">
                            <SELECT id="companyname" onchange="$('#companyKey').val(this.value);"
                                    class="selectbox required" name="companyname">
                            </SELECT>
                            <DIV class="infopopup tooltip" title="Company not registered?<br/>
                                 <a href='companyRegistration.jsp' style='color:blue;'>Click here</a> to register">
                            </DIV>
                        </DIV>
                        <LABEL 
                            id="companyerr" class="error" for="companyname" generated="true">
                        </LABEL>
                        <INPUT 
                            id="companyexist" name="companyexist" type="hidden">
                        <INPUT id="canaddmember" 
                               name="canaddmember" type="hidden">
                        <INPUT name="companyKey" id="companyKey" type="hidden"/>
                    </DIV>
                    <DIV style="display: none;" id="comtype" class="row">
                        <DIV class="label">Company Registered As:</DIV>
                        <DIV style="width: auto;" id="comptypename" class="label">
                        </DIV>
                        <DIV id="comtypeimg" class="comtypeimg">
                        </DIV>
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">Title</DIV>
                        <SELECT class="selectbox" name="title">
                            <OPTION 
                                value="MR">MR</OPTION>
                            <OPTION value="MRS">MRS</OPTION>
                            <OPTION 
                                value="MS">MS</OPTION>
                            <OPTION value="M/S">M/S</OPTION>
                        </SELECT>
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">							First Name<SPAN class="mandatory">*</SPAN>
                        </DIV>
                        <INPUT 
                            id="firstname" class="required textbox" name="firstname" type="text" autocomplete="off">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">							Last Name<SPAN class="mandatory">*</SPAN>
                        </DIV>
                        <INPUT 
                            id="lastname" class="required textbox" name="lastname" type="text" autocomplete="off">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">Address</DIV>
                        <INPUT class="textbox" name="address" type="text" autocomplete="off">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">City</DIV>
                        <INPUT class="textbox" name="city" 
                               type="text" autocomplete="off">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">State/Province</DIV>
                        <DIV id="select_0_container">
                            <SELECT id="state_0" class="selectbox" 
                                    name="state">
                                <OPTION>--Select State--</OPTION>
                            </SELECT>
                            <LABEL id="state_0err" 
                                   class="error" for="state_0" generated="true">
                            </LABEL>
                        </DIV>
                        <INPUT style="margin-top: -27px; margin-left: 200px; display: none;" 
                               id="state_textbox" class="required textbox" name="state_text" type="text" autocomplete="off">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">Zip Code/Postal Code</DIV>
                        <INPUT class="textbox" name="zipcode" 
                               type="text" autocomplete="off">
                    </DIV>
                    <FIELDSET style="height: 80px;" class="fieldset">
                        <LEGEND>Are you human?</LEGEND>
                        <DIV style="width: 350px; padding-top: 10px;" class="row">
                            <DIV>
                                <LABEL id="lcaptcha" class="captchalabel captchabg" 
                                       for="captcha">
                                </LABEL>
                                <INPUT id="captcha" class="required capthatextbox" name="captcha" 
                                       type="captcha">
                                <INPUT class="captchareload" onClick="generate_captcha('lcaptcha');" type="button">
                                <BR>
                                <INPUT id="captchavalue" name="captchavalue" type="hidden">
                                <LABEL style="margin-left: 100px;" 
                                       class="error" for="captcha" 
                                       generated="true">
                                </LABEL>
                            </DIV>
                        </DIV>
                    </FIELDSET>
                </DIV>
                <DIV style="height: 480px; margin-top: 20px;" class="formseparator">
                </DIV>
                <DIV class="rightcontent">
                    <!--<div class="row" style="height: 100px;">-->
                    <!--<div class="label">
                                                                            User Type<span class="mandatory">*</span>
                    </div>
                    <fieldset class="fieldset" style="width: 160px;">
                    <legend> User Type </legend>-->
                    <INPUT 
                        id="internetuser" name="usertype" value="Intranet User" type="hidden">
                    <!--                                                        <input type="radio" style="vertical-align: middle; font-size: 12px;margin:0px;" disabled name="usertype" id="internetuser"
                                                                                    checked class="radiobtn required" value="IntranetUser"
                                                                                    style="vertical-align:middle;font-size: 12px;" />
                    <label for="internetuser"
                                                                                    style="vertical-align: middle; font-size: 12px; color: #282828">
                                                                                    Company User (Intranet) &nbsp;</label>
                    <BR /> -->
                    <!--							<input type="radio" id="transuser"
                                                                                   name="usertype"  readonly disabled class="radiobtn" style="vertical-align: middle; font-size: 12px;margin:0px;"
                                                                                    value="TransactionUser"
                                                                                    style="vertical-align:middle;font-size: 12px;" />
                    <label for="transuser"
                                                                                    style="vertical-align: middle; font-size: 12px; color: #282828">
                                                                                    Regular UserIndividual User (Regular) &nbsp;</label>
                    <label for="usertype" checked generated="true"
                                                                                    class="error" style="margin-left: 0px !important; width: 150px;">
                    </label>-->
                    <!--</fieldset>-->
                    <!--</div>-->
                    <DIV class="row">
                        <DIV class="label">							Email<SPAN class="mandatory">*</SPAN>
                        </DIV>
                        <INPUT 
                            onblur="validateEmail(this.value)" id="email" class="required textbox email" 
                            name="email" type="text" autocomplete="off">
                        <SPAN id="emailcheck">
                        </SPAN>
                        <LABEL id="emailerr" 
                               class="error" for="email" generated="true">
                        </LABEL>
                        <INPUT id="emailexist" name="emailexist" 
                               type="hidden">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">							Password<SPAN class="mandatory">*</SPAN>
                        </DIV>
                        <INPUT 
                            id="password" class="textbox required" name="password" type="password">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">							Confirm Password<SPAN 
                                class="mandatory">*</SPAN>
                        </DIV>
                        <INPUT id="retypepassword" class="textbox required" 
                               name="retypepassword" type="password">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">Department</DIV>
                        <INPUT class="textbox" name="department" 
                               type="text" autocomplete="off">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">Manager/Supervisor</DIV>
                        <INPUT class="textbox" name="managersupervisor" 
                               type="text" autocomplete="off">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">							Phone<SPAN class="mandatory">*</SPAN>
                        </DIV>
                        <INPUT 
                            style="width: 25px; text-align: center; margin-right: 5px; float: left;" id="countrycode" 
                            class="textbox" disabled="" type="text" autocomplete="off">
                        <INPUT onBlur="validatePhoneNo(this.value)" 
                               style="width: 137px;" id="phone" class="required textbox" name="phone" type="text" autocomplete="off">
                        <DIV id="phonenocheck">
                        </DIV>
                        <LABEL id="phoneerr" class="error" for="phone" 
                               generated="true">
                        </LABEL>
                        <INPUT id="phoneexist" name="phoneexist" 
                               type="hidden">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">Cell</DIV>
                        <INPUT class="textbox" name="cell" 
                               type="text" autocomplete="off">
                    </DIV>
                    <DIV class="row">
                        <DIV class="label">Fax</DIV>
                        <INPUT class="textbox" name="fax" type="text" autocomplete="off">
                    </DIV>
                    <FIELDSET style="height: 80px;" class="fieldset">
                        <LEGEND>Conditions</LEGEND>
                        <DIV style="width: 320px; height: 35px;" id="condition1" class="row">
                            <DIV class="checkContainer">
                                <INPUT style="float: left; cursor: pointer;" id="termsconditions" 
                                       class="required" name="termsconditions" type="checkbox">
                                <DIV>
                                </DIV>
                            </DIV>
                            <!-- <label class="label" for="termsconditions" id="termsconditionslbl"
                                                                                            style="width: 210px; height: 20px; line-height: 16px;padding-left:5px;text-decoration:underline;cursor:pointer;">Accept
                                                                                            terms &amp; condition</label> -->
                            <DIV style="width: 210px; height: 20px; line-height: 16px; padding-left: 5px; text-decoration: underline; cursor: pointer;" 
                                 id="termsconditionslbl" class="label" for="termsconditions">Accept								terms 
                                &amp; condition</DIV>
                            <LABEL style="width: 250px; margin-left: 0px;" class="error" 
                                   for="termsconditions" generated="true">
                            </LABEL>
                        </DIV>
                        <DIV style="width: 320px; height: 35px; cursor: pointer;" id="condition2" class="row">
                            <DIV class="checkContainer">
                                <INPUT style="float: left; cursor: pointer;" id="policies" 
                                       class="required" name="policies" type="checkbox">
                                <DIV>
                                </DIV>
                            </DIV>
                            <!-- <label class="label" for="policies" id="policieslbl"
                                                                                            style="width: 280px; height: 20px; line-height: 16px;padding-left:5px;text-decoration:underline;cursor:pointer;">Accept
                                                                                            privacy &amp; security policies</label> -->
                            <DIV style="width: 280px; height: 20px; line-height: 16px; padding-left: 5px; text-decoration: underline; cursor: pointer;" 
                                 id="policieslbl" class="label" for="policies">Accept								privacy &amp; 
                                security policies</DIV>
                            <LABEL style="width: 250px; margin-left: 0px;" class="error" 
                                   for="policies" generated="true">
                            </LABEL>
                        </DIV>
                    </FIELDSET>
                </DIV>
                <DIV style="margin-top: 45px; margin-left: 380px; float: left;">
                    <INPUT class="gen-btn-Gray" onClick="resetUserSignupFrm('signupform');" value="Reset" type="button">
                    <INPUT style="margin-left: 60px;" class="gen-btn-Orange" value="Submit" type="submit">
                    <INPUT name="RequestType" value="NewSignup" type="hidden">
                </DIV>
            </FORM>
        </DIV>
        <LINK rel="stylesheet" href="inside/term.css">
        <TITLE>
        </TITLE>
        <SCRIPT type="text/javascript">
            $(function () {

                $(".Gen_Cus_Popup_Close").click(function () {
                    $(".Custome_Popup_Window").hide();
                });
                $("#termsdeclinebtn").click(function () {
                    $(".Custome_Popup_Window").hide();
                });

            }
            );

        </SCRIPT>
        <DIV style="display: none;" id="terms_popup" class="Custome_Popup_Window">
            <DIV style="left: 50%; top: 50%; width: 600px; height: 500px; margin-top: -250px; margin-left: -300px; position: fixed;" 
                 class="Cus_Popup_Outline">
                <DIV class="Popup_Tilte_NewGroup Gen_Popup_Title">
                    <DIV style="padding: 0px 0px 0px 15px; float: left;">Accept Terms and Conditions
                    </DIV>
                    <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                    </DIV>
                </DIV>
                <DIV style="margin: 30px; padding: 10px; border: 1px solid rgb(200, 200, 200); height: 300px; overflow: auto;">
                    <DIV style="margin: 0px;" class="termhead">Introduction</DIV>
                    <BR>
                    <P class="term_paraalign"> These terms and conditions govern your use of this 
                        website; by using this website, you accept these terms and conditions in full.   
                        If you disagree with these terms and conditions or any part of these terms and 
                        conditions, you must not use this website. <BR>
                        <BR>[You must be at least [18] 
                        years of age to use this website.  By using this website [and by agreeing to 
                        these terms and conditions] you warrant and represent that you are at least [18] 
                        years of age.]<BR>
                        <BR>[This website uses cookies.  By using this website and 
                        agreeing to these terms and conditions, you consent to our SupplyMedium's  use 
                        of cookies in accordance with the terms of SupplyMedium's [privacy policy / 
                        cookies policy].]</P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">License to use website</DIV>
                    <BR>
                    <P class="term_paraalign"> Unless otherwise stated, SupplyMedium and/or its 
                        licensors own the intellectual property rights in the website and material on 
                        the website.  Subject to the license below, all these intellectual property 
                        rights are reserved.<BR>
                        <BR>You may view, download  for caching purposes only, 
                        and print pages [or [OTHER CONTENT]] from the website for your own personal use, 
                        subject to the  restrictions set out below and elsewhere in these terms and 
                        conditions.<BR>
                        <BR>You must not:<BR>
                    <UL class="term_paraalign">
                        <LI>republish material from this website (including republication on another 
                            website);</LI>
                        <LI>sell, rent or sub-license material from the website;</LI>
                        <LI>show any material from the website in public;</LI>
                        <LI>reproduce, duplicate, copy or otherwise exploit material on this website 
                            for a commercial purpose;]</LI>
                        <LI>[edit or otherwise modify any material on the website; or]</LI>
                        <LI>[redistribute material from this website [except for content specifically 
                            and expressly made available for redistribution].]</LI>
                    </UL> [Where content is 
                    specifically made available for redistribution, it may only be redistributed 
                    [within your organisation].] 
                    <P>
                    </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Acceptable use</DIV>
                    <BR>
                    <P class="term_paraalign">You must not use this website in any way that causes, 
                        or may cause, damage to the website or impairment of the availability or  
                        accessibility of the website; or in any way which is unlawful, illegal, 
                        fraudulent or harmful, or in connection with any unlawful, illegal,  fraudulent 
                        or harmful purpose or activity.<BR>
                        <BR>  You must not use this website to copy, 
                        store, host, transmit, send, use, publish or   distribute any material which 
                        consists of (or is linked to) any spyware, computer virus, Trojan horse, worm, 
                        keystroke logger, rootkit or   other malicious computer software.<BR>
                        <BR>  You 
                        must not conduct any systematic or automated data collection activities   
                        (including without limitation scraping, data mining, data extraction and data 
                        harvesting) on or in relation to this website without   SupplyMedium express 
                        written consent.<BR>
                        <BR>  [You must not use this website to transmit or send 
                        unsolicited commercial communications.]<BR>
                        <BR>  [You must not use this website 
                        for any purposes related to marketing without SupplyMedium express written 
                        consent.]  <BR>
                        <BR>
                    </P>
                    <P class="term_paraalign">
                        <B>[Restricted access</B>
                        <BR>
                        <BR>[Access to certain 
                        areas of this website is restricted.]  SupplyMedium reserves the right to 
                        restrict access to [other] areas of this website, or indeed this entire website, 
                        at SupplyMedium discretion.<BR>
                        <BR>If SupplyMedium provides you with a user ID 
                        and password to enable you to access restricted areas of this website or other 
                        content or services, you must ensure that the user ID and password are kept 
                        confidential. <BR>
                        <BR> [SupplyMedium may disable your user ID and password in 
                        SupplyMedium sole discretion without notice or explanation.]<BR>
                        <BR>
                    </P>
                    <P class="term_paraalign">
                        <B>[User content</B>
                        <BR>
                        <BR> In these terms and 
                        conditions, âyour user contentâ means material (including without limitation 
                        text, images, audio material, video material  and audio-visual material) that 
                        you submit to this website, for whatever purpose.<BR>
                        <BR> You grant to 
                        SupplyMedium a worldwide, irrevocable, non-exclusive, royalty-free license to 
                        use, reproduce, adapt, publish, translate and  distribute your user content in 
                        any existing or future media.  You also grant to SupplyMedium the right to 
                        sub-license these rights, and the  right to bring an action for infringement of 
                        these rights.<BR>
                        <BR> Your user content must not be illegal or unlawful, must 
                        not infringe any third party's legal rights, and must not be capable of giving 
                        rise to  legal action whether against you or SupplyMedium or a third party (in 
                        each case under any applicable law).  <BR>
                        <BR> You must not submit any user 
                        content to the website that is or has ever been the subject of any threatened or 
                        actual legal proceedings or other similar complaint.<BR>
                        <BR> SupplyMedium 
                        reserves the right to edit or remove any material submitted to this website, or 
                        stored on SupplyMedium servers, or hosted  or published upon this 
                        website.<BR>
                        <BR> [Notwithstanding SupplyMedium rights under these terms and 
                        conditions in relation to user content, SupplyMedium does not undertake to  
                        monitor the submission of such content to, or the publication of such content 
                        on, this website.]<BR>
                    </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">No warranties</DIV>
                    <BR>
                    <P class="term_paraalign">This website is provided âas isâ without any 
                        representations or warranties, express or implied.  SupplyMedium makes no 
                        representations  or warranties in relation to this website or the information 
                        and materials provided on this website. <BR>
                        <BR>Without prejudice to the 
                        generality of the foregoing paragraph, SupplyMedium does not warrant that:<BR>
                    <UL class="term_paraalign">
                        <LI>this website will be constantly available, or available at all; or</LI>
                        <LI>the information on this website is complete, true, accurate or 
                            non-misleading.</LI>
                    </UL>
                    <P class="term_paraalign">Nothing on this website constitutes, or is meant to 
                        constitute, advice of any kind.  [If you require advice in relation to any 
                        [legal, financial or medical]  matter you should consult an appropriate 
                        professional.]</P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Limitations of liability</DIV>
                    <BR>
                    <P class="term_paraalign">SupplyMedium will not be liable to you (whether under 
                        the law of contact, the law of torts or otherwise) in relation to the contents 
                        of, or use of, or otherwise in connection with, this website:
                    <UL class="term_paraalign">
                        <LI>[to the extent that the website is provided free-of-charge, for any direct 
                            loss;]</LI>
                        <LI>for any indirect, special or consequential loss; or</LI>
                        <LI>	for any business losses, loss of revenue, income, profits or anticipated 
                            savings, loss of contracts or business relationships, loss of reputation  or 
                            goodwill, or loss or corruption of information or data.</LI>
                    </UL>
                    <P class="term_paraalign">These limitations of liability apply even if 
                        SupplyMedium has been expressly advised of the potential loss. </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Exceptions</DIV>
                    <BR>
                    <P class="term_paraalign">Nothing in this website disclaimer will exclude or 
                        limit any warranty implied by law that it would be unlawful to exclude or limit; 
                        and nothing  in this website disclaimer will exclude or limit SupplyMedium 
                        liability in respect of any:<BR>
                    <UL class="term_paraalign">
                        <LI>death or personal injury caused by SupplyMedium negligence;</LI>
                        <LI>fraud or fraudulent misrepresentation on the part of SupplyMedium; or</LI>
                        <LI>matter which it would be illegal or unlawful for SupplyMedium to exclude 
                            or limit, or to attempt or purport to exclude or limit, its liability. </LI>
                    </UL>
                    <P>
                    </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Reasonableness</DIV>
                    <BR>
                    <P class="term_paraalign">By using this website, you agree that the exclusions 
                        and limitations of liability set out in this website disclaimer are reasonable. 
                        <BR>
                        <BR>If you do not think they are reasonable, you must not use this 
                        website.<BR>
                    </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Other parties</DIV>
                    <BR>
                    <P class="term_paraalign">  [You accept that, as a limited liability entity, 
                        SupplyMedium has an interest in limiting the personal liability of its officers 
                        and employees.  You agree that you will not bring any claim personally against 
                        SupplyMedium officers or employees in respect of any  losses you suffer in 
                        connection with the website.]<BR>
                        <BR>[Without prejudice to the foregoing 
                        paragraph,] you agree that the limitations of warranties and liability set out 
                        in this website disclaimer will protect SupplyMedium officers, employees, 
                        agents, subsidiaries, successors, assigns and sub-contractors as well as 
                        SupplyMedium. </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Unenforceable provisions</DIV>
                    <BR>
                    <P class="term_paraalign">If any provision of this website disclaimer is, or is 
                        found to be, unenforceable under applicable law, that will not affect the 
                        enforceability  of the other provisions of this website disclaimer.</P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Indemnity</DIV>
                    <BR>
                    <P class="term_paraalign">You hereby indemnify SupplyMedium and undertake to 
                        keep SupplyMedium indemnified against any losses, damages, costs,  liabilities 
                        and expenses (including without limitation legal expenses and any amounts paid 
                        by SupplyMedium to a third party in settlement of a  claim or dispute on the 
                        advice of SupplyMedium legal advisers) incurred or suffered by SupplyMedium 
                        arising out of any breach by you of any provision of these terms and 
                        conditions[, or arising out of any claim that you have breached any provision of 
                        these terms and conditions]. </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Breaches of these terms and 
                        conditions</DIV>
                    <BR>
                    <P class="term_paraalign">Without prejudice to SupplyMedium other rights under 
                        these terms and conditions, if you breach these terms and conditions in any way, 
                        SupplyMedium may take such action as SupplyMedium deems appropriate to deal 
                        with the breach, including suspending your access  to the website, prohibiting 
                        you from accessing the website, blocking computers using your IP address from 
                        accessing the website,  contacting your internet service provider to request 
                        that they block your access to the website and/or bringing court proceedings 
                        against you.</P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Variation</DIV>
                    <BR>
                    <P class="term_paraalign">SupplyMedium may revise these terms and conditions 
                        from time-to-time.  Revised terms and conditions will apply to the use of this 
                        website from the date of the publication of the revised terms and conditions on 
                        this website.  Please check this page regularly to ensure you are familiar  with 
                        the current version.  </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Assignment</DIV>
                    <BR>
                    <P class="term_paraalign">SupplyMedium may transfer, sub-contract or otherwise 
                        deal with SupplyMedium rights and/or obligations under these terms and 
                        conditions  without notifying you or obtaining your consent.<BR>
                        <BR> You may not 
                        transfer, sub-contract or otherwise deal with your rights and/or obligations 
                        under these terms and conditions.  <BR>
                    </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Severability</DIV>
                    <BR>
                    <P class="term_paraalign">If a provision of these terms and conditions is 
                        determined by any court or other competent authority to be unlawful and/or 
                        unenforceable,    the other provisions will continue in effect.  If any unlawful 
                        and/or unenforceable provision would be lawful or enforceable if part of it were 
                        deleted, that part will be deemed to be deleted, and the rest of the 
                        provision will continue in effect. </P>
                    <BR>
                    <DIV style="margin: 0px;" class="termhead">Entire agreement</DIV>
                    <BR>
                    <P class="term_paraalign">These terms and conditions [, together with Term of 
                        Use and Privacy Policy,] constitute the entire agreement between you and    
                        SupplyMedium in relation to your use of this website, and supersede all previous 
                        agreements in respect of your use of this website.</P>
                    <P class="term_paraalign">
                        <B>Miscellaneous.</B>  This Agreement shall be 
                        governed by the laws of Ohio, except for its conflicts of laws principles. Any 
                        dispute or claim arising out of or in   connection with this Agreement shall be 
                        adjudicated in Franklin County, Ohio. The parties specifically exclude from 
                        application to the  Agreement the United Nations Convention on Contracts for the 
                        International Sale of Goods and the Uniform Computer Information   Transactions 
                        Act. This Agreement constitutes the entire agreement between the parties with 
                        respect to the subject matter hereof.   Any modifications to this Agreement must 
                        be made in a writing executed by both parties, by Your online acceptance of 
                        updated terms,   or after Your continued participation in the Program after such 
                        terms have been updated by SupplyMedium. The failure to require performance  of 
                        any provision shall not affect a party's right to require performance at any 
                        time thereafter, nor shall a waiver of any breach or default of this  Agreement 
                        constitute a waiver of any subsequent breach or default or a waiver of the 
                        provision itself. If any provision herein is held   unenforceable, then such 
                        provision will be modified to reflect the parties' intention, and the remaining 
                        provisions of this Agreement will   remain in full force and effect. You may not 
                        resell, assign, or transfer any of Your rights hereunder. Any such attempt may 
                        result in   termination of this Agreement, without liability to SupplyMedium. 
                        Notwithstanding the foregoing, SupplyMedium may assign this Agreement   to any 
                        affiliate at any time without notice. The relationship between SupplyMedium and 
                        You is not one of a legal partnership relationship,   but is one of independent 
                        contractors.</P>
                </DIV>
                <DIV style="margin-top: 25px;" class="row">
                    <INPUT style="margin-right: 50px; margin-left: 150px;" id="termsdeclinebtn" class="gen-btn-Gray" tabIndex="2" value="Decline" type="button">
                    <INPUT id="termsacceptbtn" class="gen-btn-Orange" tabIndex="1" value="Accept" type="button">
                </DIV>
                <DIV style="margin: 0px; width: 0px; height: 0px; opacity: 0;" id="terms_lastHiddenElement" 
                     class="row" tabIndex="3">
                </DIV>
            </DIV>
        </DIV>
        <LINK rel="stylesheet" href="inside/privacy.css">
        <TITLE>
        </TITLE>
        <SCRIPT type="text/javascript">
            $(function () {

                $(".Gen_Cus_Popup_Close").click(function () {
                    $(".Custome_Popup_Window").hide();
                });
                $("#policiesdeclinebtn").click(function () {
                    $(".Custome_Popup_Window").hide();
                });

            }
            );

        </SCRIPT>
        <DIV style="display: none;" id="policies_popup" class="Custome_Popup_Window">
            <DIV style="left: 50%; top: 50%; width: 600px; height: 500px; margin-top: -250px; margin-left: -300px; position: fixed;" 
                 class="Cus_Popup_Outline">
                <DIV class="Popup_Tilte_NewGroup Gen_Popup_Title">
                    <DIV style="padding: 0px 0px 0px 15px; float: left;">Accept privacy &amp; 
                        security policies</DIV>
                    <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                    </DIV>
                </DIV>
                <DIV style="margin: 30px; padding: 10px; border: 1px solid rgb(200, 200, 200); height: 300px; overflow: auto;">
                    <DIV style="margin: 0px;" class="privacyhead">Privacy Policy 
                        SupplyMedium</DIV>
                    <BR>
                    <DIV style="margin: 0px;" class="privacyhead">What we collect	</DIV>
                    <BR>
                    <P class="paraalign">
                        <B>What personal information do we collect on the Site and 
                            why?</B>
                        <BR>
                        <BR>We collect several types of information on our Site including 
                        "Personal Information," which generally is information that can be used to 
                        identify you individually,  such as your name and email address. The information 
                        we collect depends on what services you use on our Site(s). The following is a 
                        more detailed explanation of these types of information, as well as when and how 
                        we use it.<BR>
                    </P>
                    <P class="paraalign">
                        <B>Contact Information -</B> We may collect information 
                        that identifies you personally,  such as your name and e-mail address,when You 
                        submit a form, such as, but not limited to, a request for information, a request 
                        for an SupplyMedium White Paper,  a purchase of a subscription to one of our 
                        solutions, or to register for the Connect.SupplyMedium.Com site.We collect 
                        additional information if You choose to submit it,  including Your title and 
                        place of employment, business telephone number, mailing and facsimile address, 
                        as well as the contents of Your questions and/or comments   submitted along with 
                        this information.In the case of purchasing a subscription or a service some of 
                        this additional information is required.  In general, we may use this 
                        information to respond to Your inquiry, request Your participation in a survey, 
                        provide you with the service that youâve purchased , and contact You about 
                        SupplyMedium product or partner offerings that may be of interest to 
                        You.<BR>
                        <BR>Here are some details about certain features of our Site(s).<BR>
                    </P>
                    <P class="paraalign">
                        <B>Newsletters-</B>If You register for an SupplyMedium 
                        newsletter, we will use Your email address to send the newsletter to You and you 
                        may be added to a list  to receive additional promotions or communications.<BR>
                    <P>
                    <P class="paraalign">
                        <B>Investor Inquiries-</B> SupplyMedium's investor 
                        relations department accepts requests for corporate and securities information 
                        about SupplyMedium through the Site. Personal information submitted with such 
                        requests is only used by SupplyMedium to respond to the investor's request as 
                        long as the request is for investor information.<BR>
                    <P>
                    <P class="paraalign">
                        <B>IP Addresses-</B>Each visitor to the Site is identified 
                        by an IP address during their visit and IP addresses are automatically captured 
                        by the Site management software.  SupplyMedium uses this information to help 
                        improve the operation of the Site and may at some time use this information to 
                        help adjust the content of the Site to the general geographic location of the 
                        visitor and for reporting purposes.<BR>
                    </P>
                    <BR>
                    <DIV style="margin: 0px;" class="privacyhead">Security</DIV>
                    <BR>
                    <P class="paraalign">We are committed to ensuring that your information is 
                        secure. In order to prevent unauthorized access or disclosure we have put in 
                        place suitable physical, electronic  and managerial procedures to safeguard and 
                        secure the information we collect online.<BR>
                    </P>
                    <P class="paraalign">
                        <B>How we use Cookies</B>
                        <BR>  When you access our website 
                        or use SupplyMedium, we (including companies we work with) may place small data 
                        files on your computer or other device. These data files  may be cookies, pixel 
                        tags, "Flash cookies," or other local storage provided by your browser or 
                        associated applications ("Cookies"). We use these technologies to: recognize 
                        you as a customer; customize SupplyMedium, content, and advertising; measure 
                        promotional effectiveness; help ensure that your account security is not 
                        compromised; mitigate risk and prevent fraud; and to promote trust and safety 
                        across our sites and SupplyMedium.<BR>We use both session and persistent 
                        Cookies. Session Cookies expire and no longer have any effect when you log out 
                        of your account or close your browser. Persistent Cookies  remain on your device 
                        until you erase them or they expire.<BR>We encode our Cookies so that we can 
                        interpret the information stored in them. You are free to decline our Cookies if 
                        your browser or browser add-on permits, but doing so may  interfere with your 
                        use of our website and SupplyMedium. Refer to the help section of your browser, 
                        browser extensions, or installed applications for instructions on blocking, 
                        deleting, or disabling Cookies. You may encounter PayPal Cookies on websites 
                        that we do not control. For example, if you view a web page created by a third 
                        party or use an application developed by a third party, there may be a Cookie 
                        placed by the web page or application. Likewise, these third parties may place 
                        their own Cookies that are not subject to our control and the PayPal  Privacy 
                        Policy does not cover their use.	</P>
                    <BR>
                    <DIV class="privacyhead">Links to other websites</DIV>
                    <BR>
                    <P class="paraalign">We are not responsible for the practices employed by any 
                        websites or services linked to or from our Service, including the information or 
                        content contained within them. 	Please remember that when you use a link to go 
                        from our Service to another website or service, our Privacy Policy does not 
                        apply to those third-party websites or services. 	Your browsing and interaction 
                        on any third-party website or service, including those that have a link on our 
                        website, are subject to that third party's own rules and policies. 	In addition, 
                        you agree that we are not responsible and do not have control over any 
                        third-parties that you authorize to access your User Content. If you are using a 
                        third-party 	website or service and you allow them to access your User Content 
                        you do so at your own risk.<BR>
                    </P>
                    <BR>
                    <P class="paraalign">
                        <B>How does SupplyMedium use the information it collects 
                            about me?</B>
                        <BR>
                        <BR>SupplyMedium uses the information we collect about you in a 
                        number of ways, such as:
                    <UL class="paraalign">
                        <LI>Providing you with the SupplyMedium websites and applications for which 
                            you have registered, as well as any services, support, or information you have 
                            requested<BR>
                            <BR>
                        </LI>
                        <LI>Better understanding how our websites and applications are being used so 
                            we can improve them<BR>
                            <BR>
                        </LI>
                        <LI>Diagnosing problems in our websites and applications<BR>
                            <BR>
                        </LI>
                        <LI>Tailoring a website, application, or SupplyMedium marketing to your likely 
                            interests<BR>
                            <BR>
                        </LI>
                        <LI>Sending you business messages such as those related to payments or 
                            expiration of your subscription<BR>
                            <BR>
                        </LI>
                        <LI>Sending you information about SupplyMedium, new application releases, 
                            special offers, and similar information<BR>
                            <BR>
                        </LI>
                        <LI>Conducting market research about our customers, their interests, and the 
                            effectiveness of our marketing campaigns<BR>
                            <BR>
                        </LI>
                        <LI>Reducing fraud, software piracy, and protecting our customers as well as 
                            SupplyMedium<BR>
                            <BR>
                        </LI>
                        <LI>As further described for a specific SupplyMedium website or 
                            application<BR>
                            <BR>
                        </LI>
                        <LI>
                            <B>To contact you.</B> We may contact you with service-related 
                            announcements from time to time. You may opt out of all communications except 
                            essential updates on your account notifications page. We may include content 
                            you see on SupplyMedium in the emails we send to you<BR>
                            <BR>
                        </LI>
                        <LI>
                            <B>To serve personalized advertising to you.</B> We donât share your 
                            information with advertisers without your consent. (An example of consent 
                            would be if you asked us to provide  your shipping address to an advertiser to 
                            receive a free sample.) We allow advertisers to choose the characteristics of 
                            users who will see their advertisements and we may use   any of the 
                            non-personally identifiable attributes we have collected (including 
                            information you may have decided not to show to other users, such as your 
                            birth year   or other sensitive personal information or preferences) to select 
                            the appropriate audience for those advertisements. Even though we do not share 
                            your information with advertisers   without your consent, when you click on or 
                            otherwise interact with an advertisement there is a possibility that the 
                            advertiser may place a cookie in your browser and note that   it meets the 
                            criteria they selected.<BR>
                            <BR>
                        </LI>
                        <LI>
                            <B>To make Suggestions.</B> We use your profile information and other 
                            relevant information, to help you connect with other companies, including 
                            making suggestions   to you and other users that you connect with on 
                            SupplyMedium. You want to limit your visibility in suggestions we make to 
                            other companies or block specific companies    from being suggested to you and 
                            you from being suggested to them.<BR>
                            <BR>
                        </LI>
                    </UL>
                    <P>
                    </P>
                    <DIV style="margin: 0px;" class="privacyhead">Conditions of Use, Notices, and 
                        Revisions</DIV>
                    <BR>
                    <P class="paraalign">If you choose to visit SupplyMedium, your visit and any 
                        dispute over privacy is subject to this Notice and our Terms Of Use, including 
                        limitations on damages,  resolution of disputes, and application of the law of 
                        the state of Ohio. If you have any concern about privacy at SupplyMedium, please 
                        contact us with a thorough description, and we will try to resolve it. Our 
                        business changes constantly, and our Privacy Policy and the Terms of Use will 
                        change also. We may e-mail periodic  reminders of our notices and conditions, 
                        but you should check our Web site frequently to see recent changes. Unless 
                        stated otherwise, our current Privacy Notice  applies to all information that we 
                        have about you and your account. We stand behind the promises we make, however, 
                        and will never materially change our policies  and practices to make them less 
                        protective of customer information collected in the past without the consent of 
                        affected customers. </P>
                </DIV>
                <DIV style="margin-top: 25px;" class="row">
                    <INPUT style="margin-right: 50px; margin-left: 150px;" id="policiesdeclinebtn" class="gen-btn-Gray" tabIndex="2" value="Decline" type="button">
                    <INPUT id="policiesacceptbtn" class="gen-btn-Orange" tabIndex="1" value="Accept" type="button">
                </DIV>
                <DIV style="margin: 0px; width: 0px; height: 0px; opacity: 0;" id="policy_lastHiddenElement" 
                     class="row" tabIndex="3">
                </DIV>
            </DIV>
        </DIV>
        <DIV>
        </DIV>
    </DIV>
    <LINK rel="stylesheet" href="inside/regnfooter.css">
    <LINK rel="stylesheet" href="inside/footer.css">
    <TITLE>Insert 
        title here</TITLE>
    <DIV class="footer">
        <DIV class="footercontent">
            <DIV class="copyrights">Copyright 2015. <A style="color: rgb(255, 255, 255); text-decoration: none;"
                                                       href="http://supplymedium.com/" target="_blank">Supply Medium,
                    Inc.</A>
            </DIV>
        </DIV>
    </DIV>
    <LINK rel="stylesheet" href="inside/privacy.css">
    <TITLE>
    </TITLE>
    <SCRIPT type="text/javascript">
        $(function () {

            $(".Gen_Cus_Popup_Close").click(function () {
                $(".Custome_Popup_Window").hide();
            });
            $("#policiesdeclinebtn").click(function () {
                $(".Custome_Popup_Window").hide();
            });

        }
        );

    </SCRIPT>
    <DIV style="display: none;" id="privacy_commonpopup" class="Custome_Popup_Window">
        <DIV style="left: 50%; top: 50%; width: 671px; height: 500px; margin-top: -250px; margin-left: -335px; position: fixed;" 
             class="Cus_Popup_Outline">
            <DIV class="Popup_Tilte_NewGroup Gen_Popup_Title">
                <DIV style="padding: 0px 0px 0px 15px; float: left;">Accept privacy &amp; 
                    security policies</DIV>
                <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                </DIV>
            </DIV>
            <DIV style="height: 445px; overflow: auto; margin-right: 10px; margin-left: 10px;" 
                 id="group_content">
                <DIV style="margin: 0px;" class="privacyhead">Privacy Policy 
                    SupplyMedium</DIV>
                <BR>
                <DIV style="margin: 0px;" class="privacyhead">What we collect	</DIV>
                <BR>
                <P class="paraalign">
                    <B>What personal information do we collect on the Site and 
                        why?</B>
                    <BR>
                    <BR> We collect several types of information on our Site including 
                    "Personal Information," which generally is information that can be used to 
                    identify you individually,  such as your name and email address. The information 
                    we collect depends on what services you use on our Site(s). The following is a 
                    more detailed explanation of these types of information, as well as when and how 
                    we use it.<BR>
                </P>
                <P class="paraalign">
                    <B>Contact Information -</B> We may collect information 
                    that identifies you personally,  such as your name and e-mail address,when You 
                    submit a form, such as, but not limited to, a request for information, a request 
                    for an SupplyMedium White Paper,  a purchase of a subscription to one of our 
                    solutions, or to register for the Connect.SupplyMedium.Com site.We collect 
                    additional information if You choose to submit it,  including Your title and 
                    place of employment, business telephone number, mailing and facsimile address, 
                    as well as the contents of Your questions and/or comments   submitted along with 
                    this information.In the case of purchasing a subscription or a service some of 
                    this additional information is required.  In general, we may use this 
                    information to respond to Your inquiry, request Your participation in a survey, 
                    provide you with the service that youâve purchased , and contact You about 
                    SupplyMedium product or partner offerings that may be of interest to 
                    You.<BR>
                    <BR>Here are some details about certain features of our Site(s).<BR>
                </P>
                <P class="paraalign">
                    <B>Newsletters-</B>If You register for an SupplyMedium 
                    newsletter, we will use Your email address to send the newsletter to You and you 
                    may be added to a list  to receive additional promotions or communications.<BR>
                <P>
                <P class="paraalign">
                    <B>Investor Inquiries-</B> SupplyMedium's investor 
                    relations department accepts requests for corporate and securities information 
                    about SupplyMedium through the Site. Personal information submitted with such 
                    requests is only used by SupplyMedium to respond to the investor's request as 
                    long as the request is for investor information.<BR>
                <P>
                <P class="paraalign">
                    <B>IP Addresses-</B>Each visitor to the Site is identified 
                    by an IP address during their visit and IP addresses are automatically captured 
                    by the Site management software.  SupplyMedium uses this information to help 
                    improve the operation of the Site and may at some time use this information to 
                    help adjust the content of the Site to the general geographic location of the 
                    visitor and for reporting purposes.<BR>
                </P>
                <BR>
                <DIV style="margin: 0px;" class="privacyhead">Security</DIV>
                <BR>
                <P class="paraalign">We are committed to ensuring that your information is 
                    secure. In order to prevent unauthorized access or disclosure we have put in 
                    place suitable physical, electronic  and managerial procedures to safeguard and 
                    secure the information we collect online.<BR>
                </P>
                <P class="paraalign">
                    <B>How we use Cookies</B>
                    <BR>  When you access our website 
                    or use SupplyMedium, we (including companies we work with) may place small data 
                    files on your computer or other device. These data files  may be cookies, pixel 
                    tags, "Flash cookies," or other local storage provided by your browser or 
                    associated applications ("Cookies"). We use these technologies to: recognize 
                    you as a customer; customize SupplyMedium, content, and advertising; measure 
                    promotional effectiveness; help ensure that your account security is not 
                    compromised; mitigate risk and prevent fraud; and to promote trust and safety 
                    across our sites and SupplyMedium.<BR>We use both session and persistent 
                    Cookies. Session Cookies expire and no longer have any effect when you log out 
                    of your account or close your browser. Persistent Cookies  remain on your device 
                    until you erase them or they expire.<BR>We encode our Cookies so that we can 
                    interpret the information stored in them. You are free to decline our Cookies if 
                    your browser or browser add-on permits, but doing so may  interfere with your 
                    use of our website and SupplyMedium. Refer to the help section of your browser, 
                    browser extensions, or installed applications for instructions on blocking, 
                    deleting, or disabling Cookies. You may encounter PayPal Cookies on websites 
                    that we do not control. For example, if you view a web page created by a third 
                    party or use an application developed by a third party, there may be a Cookie 
                    placed by the web page or application. Likewise, these third parties may place 
                    their own Cookies that are not subject to our control and the PayPal  Privacy 
                    Policy does not cover their use.	</P>
                <BR>
                <DIV class="privacyhead">Links to other websites</DIV>
                <BR>
                <P class="paraalign">We are not responsible for the practices employed by any 
                    websites or services linked to or from our Service, including the information or 
                    content contained within them. 	Please remember that when you use a link to go 
                    from our Service to another website or service, our Privacy Policy does not 
                    apply to those third-party websites or services. 	Your browsing and interaction 
                    on any third-party website or service, including those that have a link on our 
                    website, are subject to that third party's own rules and policies. 	In addition, 
                    you agree that we are not responsible and do not have control over any 
                    third-parties that you authorize to access your User Content. If you are using a 
                    third-party 	website or service and you allow them to access your User Content 
                    you do so at your own risk.<BR>
                </P>
                <BR>
                <P class="paraalign">
                    <B>How does SupplyMedium use the information it collects 
                        about me?</B>
                    <BR>
                    <BR>SupplyMedium uses the information we collect about you in a 
                    number of ways, such as:
                <UL class="paraalign">
                    <LI>Providing you with the SupplyMedium websites and applications for which 
                        you have registered, as well as any services, support, or information you have 
                        requested<BR>
                        <BR>
                    </LI>
                    <LI>Better understanding how our websites and applications are being used so 
                        we can improve them<BR>
                        <BR>
                    </LI>
                    <LI>Diagnosing problems in our websites and applications<BR>
                        <BR>
                    </LI>
                    <LI>Tailoring a website, application, or SupplyMedium marketing to your likely 
                        interests<BR>
                        <BR>
                    </LI>
                    <LI>Sending you business messages such as those related to payments or 
                        expiration of your subscription<BR>
                        <BR>
                    </LI>
                    <LI>Sending you information about SupplyMedium, new application releases, 
                        special offers, and similar information<BR>
                        <BR>
                    </LI>
                    <LI>Conducting market research about our customers, their interests, and the 
                        effectiveness of our marketing campaigns<BR>
                        <BR>
                    </LI>
                    <LI>Reducing fraud, software piracy, and protecting our customers as well as 
                        SupplyMedium<BR>
                        <BR>
                    </LI>
                    <LI>As further described for a specific SupplyMedium website or 
                        application<BR>
                        <BR>
                    </LI>
                    <LI>
                        <B>To contact you.</B> We may contact you with service-related 
                        announcements from time to time. You may opt out of all communications except 
                        essential updates on your account notifications page. We may include content 
                        you see on SupplyMedium in the emails we send to you<BR>
                        <BR>
                    </LI>
                    <LI>
                        <B>To serve personalized advertising to you.</B> We donât share your 
                        information with advertisers without your consent. (An example of consent 
                        would be if you asked us to provide  your shipping address to an advertiser to 
                        receive a free sample.) We allow advertisers to choose the characteristics of 
                        users who will see their advertisements and we may use   any of the 
                        non-personally identifiable attributes we have collected (including 
                        information you may have decided not to show to other users, such as your 
                        birth year   or other sensitive personal information or preferences) to select 
                        the appropriate audience for those advertisements. Even though we do not share 
                        your information with advertisers   without your consent, when you click on or 
                        otherwise interact with an advertisement there is a possibility that the 
                        advertiser may place a cookie in your browser and note that   it meets the 
                        criteria they selected.<BR>
                        <BR>
                    </LI>
                    <LI>
                        <B>To make Suggestions.</B> We use your profile information and other 
                        relevant information, to help you connect with other companies, including 
                        making suggestions   to you and other users that you connect with on 
                        SupplyMedium. You want to limit your visibility in suggestions we make to 
                        other companies or block specific companies    from being suggested to you and 
                        you from being suggested to them.<BR>
                        <BR>
                    </LI>
                </UL>
                <P>
                </P>
                <DIV style="margin: 0px;" class="privacyhead">Conditions of Use, Notices, and 
                    Revisions</DIV>
                <BR>
                <P class="paraalign">If you choose to visit SupplyMedium, your visit and any 
                    dispute over privacy is subject to this Notice and our Terms Of Use, including 
                    limitations on damages,  resolution of disputes, and application of the law of 
                    the state of Ohio. If you have any concern about privacy at SupplyMedium, please 
                    contact us with a thorough description, and we will try to resolve it. Our 
                    business changes constantly, and our Privacy Policy and the Terms of Use will 
                    change also. We may e-mail periodic  reminders of our notices and conditions, 
                    but you should check our Web site frequently to see recent changes. Unless 
                    stated otherwise, our current Privacy Notice  applies to all information that we 
                    have about you and your account. We stand behind the promises we make, however, 
                    and will never materially change our policies  and practices to make them less 
                    protective of customer information collected in the past without the consent of 
                    affected customers. </P>
            </DIV>
        </DIV>
    </DIV>
    <DIV>
    </DIV>
</BODY>
</HTML>

<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.home.bean.CompanyBusinessCategoryBean"%>
<%@page import="supply.medium.home.database.CompanyBusinessCategoryMaster"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.bean.CountryBean"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.bean.BusinessCategoryBean"%>
<%@page import="supply.medium.home.database.BusinessCategoryMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailAddressBean"%>
<%@page import="supply.medium.home.database.CompanyDetailAddressMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <!--        <LINK rel="stylesheet" href="inside/companyregistration.css">
                <LINK rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
                <LINK rel="stylesheet" 
                      href="inside/jquery-ui-1.9.2.custom_spinner.css">
                <LINK rel="stylesheet" 
                      href="inside/commonlayout.css">
                <LINK rel="stylesheet" href="inside/elements.css">
                <LINK rel="stylesheet" href="inside/Custome_Buttons.css">
                <LINK rel="stylesheet" 
                      href="inside/jquery.mCustomScrollbar.css">-->
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
    <BODY onload="lockUnlock('webkrit_content_loader');">
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
        <!--<link rel="stylesheet" href="/SupplyMedium/dilbag.css">-->
        <!---->
        <div class="mainpage" id="mainpage" style="min-height:900px;background-color:#f1f1f1">
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <link rel="stylesheet" href="inside/commonlayout.css">
            <link rel="stylesheet" href="inside/Custome_Buttons.css">
            <link rel="stylesheet" href="inside/companyProfile.css">
            <title>Company Profile</title>
            <form name="paypalForm" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_blank">
                <input type="hidden" name="cmd" value="_xclick">
                <input type="hidden" name="business" value="dsdilbag345@gmail.com">
                <input type="hidden" name="password" value="dilbagdssingh">
                <input type="hidden" name="custom" value="1123">
                <input type="hidden" name="item_name" value="Plan">
                <input type="hidden" id="amnt" name="amount" value="0">
                <input type="hidden" name="rm" value="1">
                <input type="hidden" id="rtrn" name="return" value="http://localhost:8084/SupplyMedium/paypal?result=2823265987646">
                <input type="hidden" id="cncl_rtrn" name="cancel_return" value="http://localhost:8084/SupplyMedium/paypal?result=1411632993823">
                <input type="hidden" name="cert_id" value="API Singature">
            </form>
            <div class="pagetitlecontainer">
                <div class="pagetitle">Company Profile</div>
            </div>
            <div id="sv_dtl_cnfrmtn_msg_dv" class="update_user_detail2" onClick="$('#sv_dtl_cnfrmtn_msg_dv').fadeOut();" style="width:270px;">Company profile has been updated</div>
            <div class="page">
                <form method="post" id="companyProfile" name="companyProfile" action="UpdateCompanyProfile" class="contentcontainer" novalidate="novalidate" encType="multipart/form-data" method="post">
                    <div class="content cpcontent" style="height:auto!important;">
                        <div style="float:left;margin-bottom:50px;">
                            <div class="profileContent" style="border-right: 1px solid #d3d3d3;">
                                <%                                String companyId = null;
                                    String businessCategory = null;
                                    String branch = null;
                                    String country = null;
                                    String address = null;
                                    String city = null;
                                    String state = null;
                                    String zipcode = null;
                                    String branch2 = "";
                                    String country2 = "";
                                    String address2 = "";
                                    String city2 = "";
                                    String state2 = "";
                                    String zipcode2 = "";
                                    String branch3 = "";
                                    String country3 = "";
                                    String address3 = "";
                                    String city3 = "";
                                    String state3 = "";
                                    String zipcode3 = "";
                                    String companyType = "";
                                    int addressCount = 0;
                                    ArrayList detailList = CompanyDetailAddressMaster.showCompanyDetailAddress(session.getAttribute("companyKey").toString());
                                    ArrayList bcList = CompanyBusinessCategoryMaster.showAllByCompanyKey(companyKey);
                                    String bc = "";
                                    CompanyBusinessCategoryBean cbcb = null;
                                    for (int i = 0; i < bcList.size(); i++) {
                                        cbcb = (CompanyBusinessCategoryBean) bcList.get(i);
                                        bc += BusinessCategoryMaster.showBusinessCategoryNameByKey(cbcb.getBusinessCategoryKey()).getBusinessCategoryName() + ",";
                                    }
                                    CompanyDetailAddressBean adab = null;
                                    for (int i = 0; i < detailList.size(); i++) {

                                        adab = (CompanyDetailAddressBean) detailList.get(i);
                                        if (i == 0) {
                                            companyId = adab.getCompanyId();
                                            businessCategory = adab.getBusinessCategory();
                                            branch = adab.getBranch();
                                            country = adab.getCountry();
                                            address = adab.getAddress();
                                            city = adab.getCity();
                                            state = adab.getState();
                                            state = StateMaster.showStateFromKey(state);
                                            if (state == null) {
                                                state = adab.getState();
                                            }
                                            zipcode = adab.getZipcode();
                                        } else if (i == 1) {
                                            branch2 = adab.getBranch();
                                            country2 = adab.getCountry();
                                            address2 = adab.getAddress();
                                            city2 = adab.getCity();
                                            state2 = adab.getState();
                                            state2 = StateMaster.showStateFromKey(state);
                                            if (state2 == null) {
                                                state2 = adab.getState();
                                            }
                                            zipcode2 = adab.getZipcode();
                                        } else if (i == 2) {
                                            branch3 = adab.getBranch();
                                            country3 = adab.getCountry();
                                            address3 = adab.getAddress();
                                            city3 = adab.getCity();
                                            state3 = adab.getState();
                                            state3 = StateMaster.showStateFromKey(state);
                                            if (state3 == null) {
                                                state3 = adab.getState();
                                            }
                                            zipcode3 = adab.getZipcode();
                                        }
                                        companyType = adab.getCompanyType();
                                        addressCount++;
                                    }
                                %>

                                <div class="Sub_Heading">
                                    Profile
                                </div>
                                <input type="hidden" id="hidden_business_cat" value="Automobiles and Parts,">
                                <input type="hidden" id="hidden_state" value="sdfsdfs">
                                <input type="hidden" id="hidden_country" value="Andorra">
                                <input type="hidden" id="hidden_branch" value="Corporate Office">
                                <div style="height: 30px;" class="row">
                                    <img style="margin-left: 220px;" onclick="$('#buyerradio').prop('checked', true);" src="inside/buyer.png" width="30" height="30">
                                    <img style="margin-left: 35px;" onclick="$('#Supplierradio').prop('checked', true);" src="inside/seller.png" width="30" height="30">
                                    <img style="margin-left: 20px;" onclick="$('#Bothradio').prop('checked', true);" src="inside/both.png" width="30" height="30">
                                </div>
                                <div class="row">
                                    <div class="label" style="width: 200px;">				Sign Up As<span class="mandatory">*</span>
                                    </div>
                                    <input style="margin: 0px;" id="buyerradio" class="required radiobtnlbl" name="signupas" value="Buyer" type="radio" <% if (companyType.equals("Buyer")) { %> checked <% } %>>
                                    <label style="margin: 0px;" class="radiobtnlbl" for="buyerradio">Buyer</label> &nbsp;				
                                    <input style="margin: 0px;" id="Supplierradio" class="radiobtnlbl" name="signupas" value="Supplier" type="radio" <% if (companyType.equals("Supplier")) { %> checked <% } %>>
                                    <label style="margin: 0px;" class="radiobtnlbl" for="Supplierradio">Supplier</label>&nbsp; 				
                                    <input style="margin: 0px;" id="Bothradio" class="radiobtnlbl" name="signupas" value="Buyers/Suppliers" type="radio" <% if (companyType.equals("Buyers/Suppliers")) { %> checked <% }%>>
                                    <label style="margin: 0px;" class="radiobtn radiobtnlbl" for="Bothradio">Buyer/Supplier</label>&nbsp;		</div>
                                <div>
                                    <div class="label">
                                        Company Name
                                    </div>
                                    <input type="text" autocomplete="off" readonly="" id="companyname" value="<%=session.getAttribute("companyName")%>" name="companyname" class="required textbox">
                                </div>
                                <!--div id="sign_up" style="display:none;">
                                <div class="row" id="signupas">
                                <div class="label">
                                                                        Sign Up As<span class="mandatory">*</span>
                                </div>
                                <input type="radio" name="signupas" value="Buyer"
                                                                           id="Supplierradio" class="radiobtnlbl" style="margin: 0px;" />
                                <label for="Supplierradio" class='radiobtnlbl'>Buyer</label>&nbsp;
                                                                    <input type="radio" name="signupas" value="Supplier"
                                                                           id="Supplierradio" class="radiobtnlbl" style="margin: 0px;" />
                                <label for="Supplierradio" class='radiobtnlbl'>Seller</label>&nbsp;
                                                                    <input type="radio" name="signupas" value="Buyers/Suppliers" id="Bothradio"
                                                                           class="radiobtnlbl" />
                                <label for="Bothradio"
                                                                           class=' radiobtnlbl'>Buyers/Suppliers</label>&nbsp;
                                                                </div>
                                </div-->

                                <div>
                                    <div class="label">
                                        Company Logo
                                    </div>
                                    <input type="file" class="logofile" style="font-size: 12px;opacity: 1;" name="complogo" id="complogo" title="The logo dimensions should be at most 500x500 pixels in size should not exceed 1 MB.  
                                           Supported formats are jpg, png, bmp, gif and tiff.">
                                </div>
                                <div>
                                    <div class="label">
                                        Business Category
                                    </div>
                                    <select class="customSelect required selectbox" onChange="slctd_ctgry(this.value);" name="businesscategory" id="businesscategory"  style="width: 165px;height:30px;display: inline-block;">
                                        <%
                                            ArrayList categoryList = BusinessCategoryMaster.showAll();
                                            BusinessCategoryBean sbcb = null;
                                            for (int i = 0; i < categoryList.size(); i++) {
                                                sbcb = (BusinessCategoryBean) categoryList.get(i);
                                        %>
                                        <option value="<%=sbcb.getBusinessCategoryName()%>"><%=sbcb.getBusinessCategoryName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                    <!--                                    <span class="customSelect required selectbox" style="width: 165px; display: inline-block;">
                                                                            <span class="customSelectInner" style="width: 165px; display: inline-block;">--Select Category--</span>
                                                                        </span>-->
                                </div>
                                <div id="ctgry_lstng">
                                    <input type="hidden" name="businesscategory2" id="businesscategory2" value="<%=bc%>">
                                    <div class="label">
                                        Selected Category
                                    </div>
                                    <ul class="textbox" id="slctd_ctgry_lst" style="list-style: none;height: auto;width: auto;">
                                        <%
                                            BusinessCategoryBean bcb = null;
                                            bcList = CompanyBusinessCategoryMaster.showAllByCompanyKey(companyKey);
                                            for (int i = 0; i < bcList.size(); i++) {
                                                bcb = BusinessCategoryMaster.showBusinessCategoryNameByKey(((CompanyBusinessCategoryBean) bcList.get(i)).getBusinessCategoryKey());
                                        %>
                                        <li id="cat<%=i%>" style="background:#f3f3f3;padding:3px 2px;">
                                            <%=bcb.getBusinessCategoryName()%>
                                            <span style="cursor:pointer;color:red;" onClick="javascript:rmv_ctgry('<%=bcb.getBusinessCategoryName()%>', 'cat<%=i%>')">X</span>
                                        </li>
                                        <%
                                            }
                                        %>
                                    </ul>
                                </div>
                                <div>
                                    <div class="label">
                                        Company Id
                                    </div>
                                    <input type="text" autocomplete="off" name="companyid" id="companyid" readonly="" value="<%=companyId%>" class="textbox required">
                                </div>
                                <div>
                                    <div class="label">
                                        Branch
                                    </div>
                                    <select class="customSelect required selectbox" name="branch_0_value" id="branch_0"  style="width: 165px; height:30px;  display: inline-block;">
                                        <option value="Store" <% if (branch.equals("Store")) {
                                                out.print("selected");
                                            } %>>Store</option>
                                        <option value="Site Office" <% if (branch.equals("Site Office")) {
                                                out.print("selected");
                                            } %>>Site Office</option>
                                        <option value="Corporate Office" <% if (branch.equals("Corporate Office")) {
                                                out.print("selected");
                                            } %>>Corporate Office</option>
                                        <option value="Warehouse / Distribution Center" <% if (branch.equals("Warehouse / Distribution Center")) {
                                                out.print("selected");
                                            } %>>Warehouse / Distribution Center</option>
                                    </select>
                                    <!--                                    <span class="customSelect selectbox" style="display: inline-block;">
                                                                            <span class="customSelectInner" style="width: 165px; display: inline-block;">Corporate Office</span>
                                                                        </span>-->
                                </div>
                                <div>
                                    <div class="label">
                                        Country
                                    </div>
                                    <select class="customSelect required selectbox" onChange="fetchState(this.value);
                                            changeDropDown(this.value)" name="countryregion_0_value" id="countryregion_0"  style="width: 165px; height:30px; display: inline-block;">
                                        <%
                                            ArrayList countryList = CountryMaster.showAll();
                                            CountryBean scb = null;
                                            for (int i = 0; i < countryList.size(); i++) {
                                                scb = (CountryBean) countryList.get(i);
                                        %>
                                        <option value="<%=scb.getCountryKey()%>" <% if (CountryMaster.showCountryFromKey(country).equals(scb.getCountryName())) {
                                                out.print("selected");
                                            }%>>
                                            <%=scb.getCountryName()%>
                                        </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                    <!--                                    <span class="customSelect selectbox required" style="display: inline-block;">
                                                                            <span class="customSelectInner" style="width: 165px; display: inline-block;">Andorra</span>
                                                                        </span>-->
                                </div>
                                <div style="height: 95px">
                                    <div class="label">
                                        Address
                                    </div>
                                    <textarea rows="4" id="address_0" name="address_0_value" style="margin-bottom: 5px;resize:none;width:175px;" class="textarea"><%=address%></textarea>
                                </div>
                                <div>
                                    <div class="label">
                                        City
                                    </div>
                                    <input type="text" autocomplete="off" value="<%=city%>" id="city_0" name="city_0_value" style="margin-bottom: 5px;" class="textbox">
                                </div>
                                <div>
                                    <div class="label" id="zipcode">
                                        Zip Code/Postal Code
                                    </div>
                                    <input type="text" autocomplete="off" value="<%=zipcode%>" id="zipcode_0" name="zipcode_0_value" style="margin-bottom: 5px;" class="textbox">
                                </div>
                                <div>
                                    <div class="label">
                                        State/Province
                                    </div>
                                    <div id="select_0_container">
                                        <!--                                        <select id="state_0" name="state_0_value" style="margin-bottom: 5px; -webkit-appearance: menulist-button; width: 188px; position: absolute; /* opacity: 0; */ height: 28px; font-size: 12px;" class="selectbox valid hasCustomSelect">
                                                                                    <option value="">--Select State--</option>
                                                                                </select>-->
                                        <span class="customSelect selectbox valid" style="margin-bottom: 5px; display: inline-block;">
                                            <span class="customSelectInner" style="width: 165px; display: inline-block;"><%=state%></span>
                                        </span>
                                        <input type="hidden" name="state_0" value="<%=state%>" >
                                    </div>
                                    <!--                                    <input style="display:block;margin-left:225px;margin-top:-32px;" type="text" autocomplete="off" name="state_text" class="textbox" id="state_text_0" value="sdfsdfs">-->
                                </div>
                                <table>
                                    <tbody>
                                        <tr>
                                            <td align="left" style="vertical-align: top;">
                                                <div class="label" style="width: 196px">
                                                    Add Address <br>
                                                    <input type="hidden" id="companyAddressCount" name="addresscount" value="<%=addressCount%>">
                                                    <input style="display: block;" type="button" name="addAddress" id="addAddtessButton" class="addAddress" onclick="showCompanyMultipleAddressPopup();">
                                                </div>
                                            </td>
                                            <td align="left" style="vertical-align: top;">
                                                <div id="addressExpandContainer">
                                                    <div id="addressExpander">
                                                    </div>
                                                </div>
                                            </td>
                                            <td id="AddressList">
                                                <DIV style="<% if (addressCount >= 2) {
                                                        out.print("display: block;");
                                                    } else {
                                                        out.print("display: none;");
                                                    }%>" id="addrright1" class="addrright">
                                                    <FIELDSET style="height: auto;" 
                                                              class="regnaddrfieldset">
                                                        <LEGEND>Address1:</LEGEND>
                                                        <TEXTAREA id="addaddrlbl1" class="addrlbl" disabled="">
                                                            <%=branch2 + ", " + country2 + ", " + address2 + ", " + city2 + ", " + state2 + ", " + zipcode2%>
</TEXTAREA>
</FIELDSET>
<INPUT id="removeaddressbtn1" class="removeaddress" onClick="removeUpdateDeleteCompanyMultipleAddress(2);" name="removeaddressbtn1" type="button" style="display: block;">
</DIV>
                                                <DIV style="<% if (addressCount >= 3) {
                                                        out.print("display: block;");
                                                    } else {
                                                        out.print("display: none;");
                                                    }%>" id="address2detail" class="addrdetail">
<DIV style="" id="addrright2" class="addrright">
<FIELDSET style="height: auto;" id="addaddrfield2" 
class="regnaddrfieldset">
<LEGEND>Address2:</LEGEND>
<TEXTAREA id="addaddrlbl2" class="addrlbl" disabled="">
                                                                <%=branch3 + ", " + country3 + ", " + address3 + ", " + city3 + ", " + state3 + ", " + zipcode3%>
</TEXTAREA>
</FIELDSET>
    <INPUT id="removeaddressbtn2" class="removeaddress" onClick="removeUpdateDeleteCompanyMultipleAddress(3);" name="removeaddressbtn2" type="button" style="display: block;">
</DIV>
</DIV>
                                            </td>
                                        <tr>
                                            <td colspan="3">
                                                <div class="ButtonContiner" style="margin-left:20px;width:auto;">
                                        <input type="hidden" id="pswrd" value="123456@a">
                                        <input type="hidden" id="eml" value="info@webkrit.com">
                                        <input id="Profile_Update_Cancel" style="margin-left:60px;margin-right: 75px" type="button" class="general-button gen-btn-Gray" value="Cancel">
                                        <input id="Profile_Update_OK" type="submit" class="general-button gen-btn-Orange" value="Save">
                                        <input type="hidden" id="firstAddrId" value="1">
                                    </div>
                                            </td>   
                                        </tr>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>


                            <!--                            <div class="ContenSeparator">
                                                        </div>-->
                            <div class="pricingContent">
                                <div class="Sub_Heading">		
                                    Pricing Plan
                                </div>
                                <!-- plan -->
                                <div class="row" style="height:40px;">
                                    <label class="keylbl"> Plan
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;Basic
                                    </label>
                                </div>
                                <!-- Maximum number of employees -->
                                <div class="row" style="height:30px;">
                                    <label class="keylbl"> Maximum number of employee licenses
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;25
                                    </label>
                                </div>
                                <!-- registered employees -->
                                <div class="row" style="height:40px;">
                                    <label class="keylbl"> Number of employees registered
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;2
                                    </label>
                                </div>
                                <!-- max tras vol-->
                                <div class="row" style="height:30px;">
                                    <label class="keylbl">Maximum transaction volume per month
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;$250,000
                                    </label>
                                </div>
                                <!-- transaction volume per month -->
                                <div class="row" style="height:30px;">
                                    <label class="keylbl"> Transaction volume per month
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;$0
                                    </label>
                                </div>
                                <!-- Remaining transaction for this month -->
                                <div class="row" style="height:50px;">
                                    <label class="keylbl"> Balance transaction allowed for this month
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;$250,000
                                    </label>
                                </div>
                                <div class="Sub_Heading">		
                                    Disk Quota
                                </div>
                                <div class="row" style="height:30px;">
                                    <label class="keylbl">
                                        <span style="color:green;">0.00%</span> of disk space used</label>
                                </div>
                                <div class="row">
                                    <label class="keylbl"> Used <span style="color:#f37d01;">0.00MB</span>
                                        of your <span style="color:#f37d01;">250.00MB </span>disk space
                                    </label>
                                </div>
                                <div class="Sub_Heading">		
                                    Payment Options
                                </div>
                                <div>
                                    <div class="pricinglable">
                                        Update Plan : 

                                        <input type="radio" id="Basic" name="plan" value="Basic" checked="" class="radiobtn required" onClick="$('#amnt').val('0');
                                                $('#rtrn').val($('#rtrn').val() + 'Basic');">
                                        <label for="Basic">Basic </label>
                                        <input type="radio" id="Plus" name="plan" value="Plus" class="radiobtn required" onClick="$('#amnt').val('49');
                                                $('#rtrn').val($('#rtrn').val() + 'Plus');">
                                        <label for="Plus">Plus </label>
                                        <input type="radio" id="Premium" name="plan" value="Premium" class="radiobtn required" onClick="$('#amnt').val('99');
                                                $('#rtrn').val($('#rtrn').val() + 'Premium');">
                                        <label for="Premium">Premium </label>
                                    </div>
                                </div>
                                <div>
                                    <!--div class="label">
                                                                        Select Payment Options
                                                                    </div>
                                    <select id="paymentmode_0" name="paymentmode_0_value"   disabled class="selectbox">
                                    <option value="Debit Card">Debit Card</option>
                                    <option value="Credit Card">Credit Card</option>
                                    </select-->
                                </div>
                                <div>
                                    <div class="label">
                                    </div>
                                    <!-- PayPal Logo -->
                                    <table border="0" cellpadding="10" cellspacing="0" align="center">
                                        <tbody>
                                            <tr>
                                                <td align="center">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="left">
                                                    <img src="inside/paypal.jpg" border="0" height="80" alt="PayPal Logo" title="Click for payment of plan" onClick="document.forms['paypalForm'].submit();">
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    
                                    <!-- PayPal Logo -->
                                    <!--input type="button" style="border:1px solid #0097be;height: 30px;width: 62px;"  disabled id="pay" name="payment" class="pop-button pop-button-White" value="Pay" -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <DIV style=" z-index: 1230;" id="address_popup1" class="Custome_Popup_Window">
                <DIV style="width: 600px !important; height: 500px; margin-top: 10%; margin-left:25%; position: fixed;" 
                     class="Cus_Popup_Outline" id="Cus_Popup_Outline1">
                    <DIV class="Popup_Tilte_NewGroup Gen_Popup_Title">
                        <DIV style="padding: 0px 0px 0px 15px; float: left;">Add					Address</DIV>
                        <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close" onclick="$('#address_popup1').fadeOut()">
                        </DIV>
                    </DIV>
                    <DIV style="margin-top: 50px; margin-left: 95px;">
                        <DIV class="row">
                            <DIV class="label">Branch</DIV>
                            <SELECT style="-webkit-appearance: menulist-button; width: 188px; position: absolute; /* opacity: 0; */ height: 28px; font-size: 11.8181819915771px;" 
                                    id="branch_1" class="selectbox hasCustomSelect valid" tabIndex="1" name="branch_1">
                                <OPTION value="Corporate Office" <% if (branch2.equals("Corporate Office")) {
                                        out.print("selected");
                                    } %>>Corporate Office</OPTION>
                                <OPTION value="Site Office" <% if (branch2.equals("Site Office")) {
                                        out.print("selected");
                                    } %>>Site Office</OPTION>
                                <OPTION value="Warehouse / Distribution Center" <% if (branch2.equals("Corporate Office")) {
                                        out.print("selected");
                                    } %>>Warehouse / Distribution Center</OPTION>
                                <OPTION value="Store" <% if (branch2.equals("Store")) {
                                        out.print("selected");
                                    } %>>Store</OPTION>
                            </SELECT>
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Country<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <SELECT id="countryregion_1"  style="-webkit-appearance: menulist-button; width: 188px; position: absolute; /* opacity: 0; */ height: 28px; font-size: 11.8181819915771px;"
                                    onchange="fetchStateForAddAddress1(this.value);//stateDropDown(this.value)" class="selectbox hasCustomSelect valid" 
                                    tabIndex="2" name="countryregion_1">
                                        <%
                                            //ArrayList countryList=SmCountryMaster.showAll();
                                            //SmCountryBean scb=null;
                                            for (int i = 0; i < countryList.size(); i++) {
                                                scb = (CountryBean) countryList.get(i);
                                        %>
                                        <option  <% if (country2.equals(scb.getCountryKey())) {
                                                out.print("selected");
                                            }%> value="<%=scb.getCountryKey()%>">
                                            <%=scb.getCountryName()%>
                                </option>
                                        <%
                                            }
                                        %>
                            </SELECT>
                            <LABEL id="countryregion_1err" 
                                   class="error" for="countryregion_1" generated="true">
                            </LABEL>
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Address</DIV>
                            <INPUT style="margin-bottom: 5px;" id="address_1" value="<%=address2%>"
                                   class="textbox" tabIndex="3" name="address_1" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">City</DIV>
                            <INPUT style="margin-bottom: 5px;" id="city_1" value="<%=city2%>" 
                                   class="textbox" tabIndex="4" name="city_1" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">State/Province</DIV>
                            <DIV id="select_1_container">
                                <SELECT style="-webkit-appearance: menulist-button; width: 188px; position: absolute; /* opacity: 0; */ height: 28px; font-size: 11.8181819915771px;" id="state_1" 
                                        class="selectbox hasCustomSelect valid" tabIndex="5" name="state_1">
                                    <OPTION value="<%=state2%>"><%=state2%></OPTION>
                                </SELECT>
                                <LABEL id="state_0err" 
                                       class="error">
                                </LABEL>
                            </DIV>
                            <INPUT style="margin-top: -33px; margin-left: 200px; display: none;" 
                                   id="state_text_1" class="textbox" name="state_text_1" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Zip Code/Postal Code</DIV>
                            <INPUT style="margin-bottom: 5px;"  value="<%=zipcode2%>"
                                   id="zipcode_1" class="textbox" tabIndex="6" name="zipcode_1" type="text" autocomplete="off">
                        </DIV>
                        <DIV style="margin-top: 20px; margin-left: 20px;" class="row">
                            <INPUT style="margin-right: 50px; margin-left: 50px;" id="addr1_popup_cancel" class="gen-btn-Gray" tabIndex="7" value="Cancel" type="button" onclick="$('#address_popup1').fadeOut()">
                            <INPUT id="saveaddr1btn" class="gen-btn-Orange" tabIndex="8" value="Ok" type="button" onclick="addUpdateDeleteCompanyMultipleAddress()">
                        </DIV>
                        <DIV style="margin: 0px; width: 0px; height: 0px; /* opacity: 0; */" id="addone_lastHiddenElement" 
                             class="row" tabIndex="9" href="#">
                        </DIV>
                    </DIV>
                </DIV>
            </DIV>
            <DIV style="display: none;" id="address_popup2" class="Custome_Popup_Window">
<DIV style="width: 600px !important; height: 500px; margin-top: 10%; margin-left:25%; position: fixed;" 
class="Cus_Popup_Outline">
<DIV class="Popup_Tilte_NewGroup Gen_Popup_Title">
<DIV style="padding: 5px 0px 0px 15px; float: left;">Add					Address</DIV>
<DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
</DIV>
</DIV>
<DIV style="margin-top: 50px; margin-left: 95px;">
<DIV class="row">
<DIV class="label">Branch</DIV>
<SELECT id="branch_2" class="selectbox hasCustomSelect valid" tabIndex="1"  style="-webkit-appearance: menulist-button; width: 188px; position: absolute; /* opacity: 0; */ height: 28px; font-size: 11.8181819915771px;"
name="branch_2">
<OPTION value="Corporate Office" <% if (branch3.equals("Corporate Office")) {
        out.print("selected");
    } %>>Corporate Office</OPTION>
                                <OPTION value="Site Office" <% if (branch3.equals("Site Office")) {
                                        out.print("selected");
                                    } %>>Site Office</OPTION>
                                <OPTION value="Warehouse / Distribution Center" <% if (branch3.equals("Corporate Office")) {
                                        out.print("selected");
                                    } %>>Warehouse / Distribution Center</OPTION>
                                <OPTION value="Store" <% if (branch3.equals("Store")) {
                                        out.print("selected");
                                    } %>>Store</OPTION>
</SELECT>
</DIV>
<DIV class="row">
<DIV class="label">Country<SPAN class="mandatory">*</SPAN>
</DIV>
    <SELECT id="countryregion_2" style="-webkit-appearance: menulist-button; width: 188px; position: absolute; /* opacity: 0; */ height: 28px; font-size: 11.8181819915771px;"
onchange="fetchStateForAddAddress2(this.value);//changeDropDown(this.value)" class="selectbox hasCustomSelect valid" 
tabIndex="2" name="countryregion_2">
                                        <%
                                            //ArrayList countryList=SmCountryMaster.showAll();
                                            //SmCountryBean scb=null;
                                            for (int i = 0; i < countryList.size(); i++) {
                                                scb = (CountryBean) countryList.get(i);
                                        %>
<option value="<%=scb.getCountryKey()%>" <% if (country3.equals(scb.getCountryKey())) {
                                                out.print("selected");
                                            }%>>
                                                <%=scb.getCountryName()%>
</option>
                                        <%
                                            }
                                        %>
</SELECT>
<LABEL id="countryregion_2err" 
class="error" for="countryregion_2" generated="true">
</LABEL>
</DIV>
<DIV class="row">
<DIV class="label">Address</DIV>
<INPUT style="margin-bottom: 5px;" id="address_2"  value="<%=address3%>"
class="textbox" tabIndex="3" name="address_2" type="text" autocomplete="off">
</DIV>
<DIV class="row">
<DIV class="label">City</DIV>
<INPUT style="margin-bottom: 5px;" id="city_2" value="<%=city3%>"
class="textbox" tabIndex="4" name="city_2" type="text" autocomplete="off">
</DIV>
<DIV class="row">
<DIV class="label">State/Province</DIV>
<DIV id="select_2_container">
<SELECT style="-webkit-appearance: menulist-button; width: 188px; position: absolute; /* opacity: 0; */ height: 28px; font-size: 11.8181819915771px;" id="state_2" 
class="selectbox hasCustomSelect valid" tabIndex="5" name="state_2">
<OPTION value="<%=state3%>"><%=state3%></OPTION>
</SELECT>
<LABEL id="state_0err" 
class="error">
</LABEL>
</DIV>
<INPUT style="margin-top: -33px; margin-left: 200px; display: none;" 
id="state_text_2" class="textbox" name="state_text_2" type="text" autocomplete="off">
</DIV>
<DIV class="row">
<DIV class="label">Zip Code/Postal Code</DIV>
<INPUT style="margin-bottom: 5px;" value="<%=zipcode3%>"
id="zipcode_2" class="textbox" tabIndex="6" name="zipcode_2" type="text" autocomplete="off">
</DIV>
<DIV style="margin-top: 20px; margin-left: 20px;" class="row">
<INPUT style="margin-right: 50px; margin-left: 50px;" id="addr1_popup_cancel" class="gen-btn-Gray" tabIndex="7" value="Cancel" type="button">
<INPUT id="saveaddr2btn" class="gen-btn-Orange" tabIndex="8" value="Ok" type="button" onclick="addUpdateDeleteCompanyMultipleAddress();">
</DIV>
</DIV>
</DIV>
<DIV style="margin: 0px; width: 0px; height: 0px; /* opacity: 0; */" id="addtwo_lastHiddenElement" 
class="row" tabIndex="9" href="#">
</DIV>
</DIV>                    
                </form>
            </div>
            <div>
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
            <title>Insert title here</title>
            <link rel="stylesheet" href="inside/Custome_Popup.css">
            <link rel="stylesheet" href="inside/ResetCSS.css">
            <link rel="stylesheet" href="inside/elements.css">
                            
            <!--  <form onSubmit="" method="post" id="Popup_Address"  class="Custome_Popup_Window">
            <table>
            <tr>
            <td style="vertical-align: middle;">
            <div class="Cus_Popup_Outline">
            <div class="Popup_Tilte_AddAddress Gen_Popup_Title">
            <div style="padding: 5px 0px 0px 15px;float:left">Add AddAddress</div>
            <div class="Popup_Close_AddAddress Gen_Cus_Popup_Close">
            </div>
            </div>
            <table>
            <tr>
            <td style="width: 200px;">Branch</td>
            <td>
            <select id="branch_pop" name="pop_branch_pop"  class="Cus_Popup_Field Cus_Pop_selectbox">
            </select>
            </td>
            </tr>
            <tr>
            <td>Country/Region<span class="mandatory">*</span>
            </td>
            <td>
            <select id='countryregion_pop' onchange="fetchState(this.id)" name='countryregion_pop'
                                                                            class="Cus_Popup_Field Cus_Pop_selectbox required" onchange="fetchState(this.id)">
            <option value="--Select Country--">--Select Country--</option>
            </select>
            </td>
            </tr>
            <tr>
            <td style="vertical-align: top;">Address</td>
            <td>
            <textarea id='AddAddress_pop' name='AddAddress_pop'
                                                                            style="margin-bottom: 5px;" class="Cus_Popup_Field" >
            </textarea>
            </td>
            </tr>
            <tr>
            <td>City</td>
            <td>
            <input type='text' id='city_pop' name='city_pop'
                                                                            style="margin-bottom: 5px;" class="Cus_Popup_Field" />
            </td>
            </tr>
            <tr>
            <td>State</td>
            <td>
            <select id='state_pop' name='state_pop' style="margin-bottom: 5px;"
                                                                            class="Cus_Popup_Field Cus_Pop_selectbox">
            <option value="--Select State--">--Select State--</option>
            </select>
            </td>
            </tr>
            <tr>
            <td>Zipcode</td>
            <td>
            <input type='text' id='zipcode_pop' name='zipcode_pop'
                                                                            style="margin-bottom: 5px;" class="Cus_Popup_Field" />
            </td>
            </tr>
            <tr>
            <td colspan="3" align="center">
            <input id="Popup_Address_OK" type='button' style="margin-right: 20px" class="general-button gen-btn-Orange" value="OK" />
            <input id="Popup_Address_Cancel" type='button' class="general-button gen-btn-Gray"   value="Cancel" />
            </td>
            </tr>
            </table>
            </div>
            </td>
            </tr>
            </table>
            </form>-->
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

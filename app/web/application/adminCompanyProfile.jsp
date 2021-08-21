<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.utility.DiskUsage"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.CompanyBusinessCategoryBean"%>
<%@page import="supply.medium.home.database.CompanyBusinessCategoryMaster"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.bean.CountryBean"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%@page import="supply.medium.home.bean.BusinessCategoryBean"%>
<%@page import="supply.medium.home.database.BusinessCategoryMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailAddressBean"%>
<%@page import="supply.medium.home.database.CompanyDetailAddressMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK rel="stylesheet" href="inside/user_home.css">
        <LINK rel="stylesheet" href="inside/dilbag.css">
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
        <SCRIPT type="text/JavaScript" src="inside/regfieldvalidator.js">
        </SCRIPT>
        <script type="text/javascript" language="javascript" src="inside/companyregistration.js">
        </script>
        <SCRIPT type="text/JavaScript" src="inside/regvalidator.js">
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
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onload="lockUnlock('webkrit_content_loader');" onkeydown="displayunicode(event);">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <DIV style="min-height: 700px; background-color: rgb(241, 241, 241);" id="mainpage" 
         class="mainpage">

        <form id="paypalForm" name="paypalForm" action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_blank">
            <input type="hidden" name="cmd" value="_xclick">
            <input type="hidden" name="business" value="supplymedium@gmail.com">
            <input type="hidden" name="lc" value="US">
            <input type="hidden" name="button_subtype" value="services">
            <input type="hidden" name="currency_code" value="USD">
            <input type="hidden" id="item_name" name="item_name" value="SupplyMedium Platform Charges" />
            <input type="hidden" id="amnt" name="amount" value="0"/>
            <input type="hidden" name="bn" value="PP-BuyNowBF:btn_paynowCC_LG.gif:NonHosted">
            <input type="hidden" id="rtrn" name="return" value="<%=SmProperties.emailUrl%>adminCompanyProfiles.jsp"/>
            <input type="hidden" id="cncl_rtrn" name="cancel_return" value="<%=SmProperties.emailUrl%>adminCompanyProfile.jsp" />
        </form>
        <LINK rel="stylesheet" href="inside/newsroom.css">
        <LINK rel="stylesheet" href="inside/newsroom_feed.css">
        <LINK rel="stylesheet"  href="inside/view_members.css">
        <div class="mainpage" id="mainpage" style="min-height:900px;background-color:#f1f1f1">
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <link rel="stylesheet" href="inside/commonlayout.css">
            <link rel="stylesheet" href="inside/Custome_Buttons.css">
            <link rel="stylesheet" href="inside/companyProfile.css">
            <title>Company Profile</title>
            <div class="pagetitlecontainer">
                <div class="pagetitle">Company Profile</div>
            </div>
            <div id="sv_dtl_cnfrmtn_msg_dv" class="update_user_detail2" onClick="$('#sv_dtl_cnfrmtn_msg_dv').fadeOut();" style="width:270px;">Company profile has been updated</div>
            <div class="page">
                <form method="post" id="companyProfile" name="companyProfile" action="UpdateCompanyProfile" class="contentcontainer" novalidate="novalidate" encType="multipart/form-data" method="post" onsubmit="return checkImageError()">
                    <div class="content cpcontent" style="height:auto!important;width:942px!important;">
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
                                            String companyTypes = "";
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
                                                companyTypes = adab.getCompanyType();
                                                addressCount++;
                                            }
                                %>
                                <div class="Sub_Heading">
                                    Profile
                                </div>
                                <div style="height: 30px;" class="row">
                                    <img style="margin-left: 190px;" onclick="$('#buyerradio').prop('checked', true);" src="inside/buyer.png" width="30" height="30">
                                    <img style="margin-left: 45px;" onclick="$('#Supplierradio').prop('checked', true);" src="inside/seller.png" width="30" height="30">
                                    <img style="margin-left: 30px;" onclick="$('#Bothradio').prop('checked', true);" src="inside/both.png" width="30" height="30">
                                </div>
                                <div class="row">
                                    <div class="label" style="width: 180px;">				Sign Up As<span class="mandatory">*</span>
                                    </div>
                                    <input style="margin: 0px;" id="buyerradio" class="required radiobtnlbl" name="signupas" value="Buyer" type="radio" <% if (companyTypes.equals("Buyer")) {%> checked <% }%>>
                                    <label style="margin: 0px;" class="radiobtnlbl" for="buyerradio">Buyer</label> &nbsp;				
                                    <input style="margin: 0px;" id="Supplierradio" class="radiobtnlbl" name="signupas" value="Supplier" type="radio" <% if (companyTypes.equals("Supplier")) {%> checked <% }%>>
                                    <label style="margin: 0px;" class="radiobtnlbl" for="Supplierradio">Supplier</label>&nbsp; 				
                                    <input style="margin: 0px;" id="Bothradio" class="radiobtnlbl" name="signupas" value="Buyers/Suppliers" type="radio" <% if (companyTypes.equals("Buyers/Suppliers")) {%> checked <% }%>>
                                    <label style="margin: 0px;" class="radiobtn radiobtnlbl" for="Bothradio">Buyer/Supplier</label>&nbsp;		</div>
                                <div>
                                    <div class="label">
                                        Company Name
                                    </div>
                                    <input type="text" autocomplete="off" readonly="" id="companyname" value="<%=session.getAttribute("companyName")%>" name="companyname" class="required textbox">
                                </div>
                                <div>
                                    <div class="label">
                                        Company Logo
                                    </div>
                                    <input type="file" style="width:180px;height: 20px;margin-top: 3px;float: left;cursor: pointer !important;font-size: 12px;opacity: 1;" name="complogo" id="complogo">

                                </div>
                                <div style="color:red;display:none;padding-left:150px;" id="fileupload_err"></div>
                                <div>
                                    <div class="label">
                                        Business Category
                                    </div>
                                    <select class="customSelect required selectbox" onChange="slctd_ctgry(this.value);" name="businesscategory" id="businesscategory"  style="width: 165px;height:30px;display: inline-block;">
                                        <OPTION value="0">--Select Category--</OPTION>
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
                                                    }%>>Store</option>
                                        <option value="Site Office" <% if (branch.equals("Site Office")) {
                                                        out.print("selected");
                                                    }%>>Site Office</option>
                                        <option value="Corporate Office" <% if (branch.equals("Corporate Office")) {
                                                        out.print("selected");
                                                    }%>>Corporate Office</option>
                                        <option value="Warehouse / Distribution Center" <% if (branch.equals("Warehouse / Distribution Center")) {
                                                        out.print("selected");
                                                    }%>>Warehouse / Distribution Center</option>
                                    </select>
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
                                        <span class="customSelect selectbox valid" style="margin-bottom: 5px; display: inline-block;">
                                            <span class="customSelectInner" style="width: 165px; display: inline-block;"><%=state%></span>
                                        </span>
                                        <input type="hidden" name="state_0" value="<%=state%>" >
                                    </div>
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
                                                    <input id="Profile_Update_Cancel" style="margin-left:60px;margin-right: 75px" type="button" class="general-button gen-btn-Gray" value="Cancel">
                                                    <input id="Profile_Update_OK" type="submit" class="general-button gen-btn-Orange" value="Save">
                                                </div>
                                            </td>   
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="pricingContent" style="width:432px;">
                                <div class="Sub_Heading">		
                                    User License
                                </div>
                                <!--<div class="row" style="height:40px;">
                                    <label class="keylbl"> Plan
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;<%="Basic"%>
                                    </label>
                                </div>-->
                                <div class="row" style="height:30px;">
                                    <label class="keylbl"> Maximum number of user licenses
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;2
                                    </label>
                                </div>
                                <div class="row" style="height:40px;">
                                    <label class="keylbl"> Number of user registered
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;<%=userCount1979%>
                                    </label>
                                </div>                                
                                <div class="row" style="height:40px;">
                                    <span style="color:green;">Unlimited Users<!--After 2 registered users, USD $1.99 per user per month will be charged--></span>
                                </div>
                                <div class="row" style="height:40px;">
                                </div>
                                <!--<div class="row" style="height:30px;">
                                    <label class="keylbl">Maximum transaction volume annually
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;$10000
                                    </label>
                                </div>-->
                                <div class="Sub_Heading">		
                                    Transaction Volume Usage
                                </div>
                                <!-- transaction volume per month -->
                                <div class="row" style="height:30px;">
                                    <label class="keylbl"> Transaction Volume Used
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;USD $<%=transactionMade1979%>
                                    </label>
                                </div>
                                <!-- Remaining transaction for this month -->
                                <div class="row" style="height:50px;">
                                    <label class="keylbl"> Transaction Volume Charges (1% charges)
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;USD $<%=transactionMade11979%>
                                    </label>
                                </div>                              
                                <div class="row" style="height:40px;">
                                    <span style="color:green;">SupplyMedium will donate 1% of profit to children welfare of developing countries.</span>
                                </div>
                                <div class="row" style="height:40px;">
                                </div>
                                <div class="Sub_Heading">		
                                    Disk Quota Usage
                                </div>
                                <div class="row" style="height:30px;">
                                    <label class="keylbl">
                                        <span style="color:green;"><%=usedDiskPercent1979%>%</span> of disk space used</label>
                                </div>
                                <div class="row">
                                    <label class="keylbl"> Used <span style="color:#f37d01;"><%=sizeDisk1979%>MB</span>
                                        of your <span style="color:#f37d01;">Unlimited Disk Space<!--100MB disk space--></span>
                                    </label>
                                </div>                                
                                <div class="row" style="height:40px;">
                                    <span style="color:green;">Unlimited Disk Space<!--After usage of first 100MB free space, USD $1.99 per 100MB annually will be charged.--></span>
                                </div>
                                <div class="row" style="height:40px;">
                                </div>
                                <div class="Sub_Heading">		
                                    Billing Details
                                </div>
                                <!-- transaction volume per month -->
                                <div class="row" style="height:30px;">
                                    <label class="keylbl"> User License Charges
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;USD $0<%//=(usersLic1979 * 1.99)%> per month
                                    </label>
                                </div>
                                <div class="row" style="height:30px;">
                                    <label class="keylbl"> Transaction Charges
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;USD $<%=transactionMade11979%> till today
                                    </label>
                                </div>
                                <div class="row" style="height:30px;">
                                    <label class="keylbl"> Storage Charges
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;USD $0<%//=charges1979%> per month
                                    </label>
                                </div>
                                <div class="row" style="height:30px;">
                                    <label class="keylbl" style="color:green;"> Total Charges
                                    </label>
                                    <label class="valuelbl" style="color:green;">:&nbsp;&nbsp;USD $<%=transactionMade11979%><%//=total1979%> per month
                                    </label>
                                </div>
                                <div class="row" style="height:30px;">
                                    <label class="keylbl"> Paid Amount
                                    </label>
                                    <label class="valuelbl">:&nbsp;&nbsp;USD $<%=paidCharges1979%> per month
                                    </label>
                                </div>
                                <div class="row" style="height:30px;">
                                    <label class="keylbl" style="color:green;"> Due Charges
                                    </label>
                                    <label class="valuelbl" style="color:green;">:&nbsp;&nbsp;USD $<%=transactionMade11979%><%//=total1979%> per month
                                    </label>
                                </div>
                                <div class="row" style="height:30px;">
                                    <span style="color:green;">SupplyMedium does not save your PayPal or Debit/Credit Card information</span>
                                </div>
                                <div class="row" style="height:30px;">
                                    <label class="keylbl">&nbsp;
                                    </label>
                                    <label class="valuelbl">
                                        <input type="button" value="Pay via Paypal" style="float:right;width:180px;" class="general-button gen-btn-Orange" onClick="javascript:$('#rtrn').val($('#rtrn').val()+'?amt=<%=transactionMade11979%>');$('#amnt').val('<%=transactionMade11979%>');document.forms['paypalForm'].submit();" />
                                    </label>
                                </div>
                                <div class="row" style="height:30px;margin:40px 0;">
                                    <img style="cursor: pointer;" src="inside/paypal.jpg" border="0" height="100" alt="PayPal Logo" title="Proceed to Pay"  onclick="$('#amnt').val('<%=transactionMade11979%>');document.forms['paypalForm'].submit();">
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
                                                    }%>>Corporate Office</OPTION>
                                        <OPTION value="Site Office" <% if (branch2.equals("Site Office")) {
                                                        out.print("selected");
                                                    }%>>Site Office</OPTION>
                                        <OPTION value="Warehouse / Distribution Center" <% if (branch2.equals("Corporate Office")) {
                                                        out.print("selected");
                                                    }%>>Warehouse / Distribution Center</OPTION>
                                        <OPTION value="Store" <% if (branch2.equals("Store")) {
                                                        out.print("selected");
                                                    }%>>Store</OPTION>
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
                                                    }%>>Corporate Office</OPTION>
                                        <OPTION value="Site Office" <% if (branch3.equals("Site Office")) {
                                                        out.print("selected");
                                                    }%>>Site Office</OPTION>
                                        <OPTION value="Warehouse / Distribution Center" <% if (branch3.equals("Corporate Office")) {
                                                        out.print("selected");
                                                    }%>>Warehouse / Distribution Center</OPTION>
                                        <OPTION value="Store" <% if (branch3.equals("Store")) {
                                                        out.print("selected");
                                                    }%>>Store</OPTION>
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
        </DIV>
        <%@include file="_invite.jsp" %>
    </BODY>
</HTML>

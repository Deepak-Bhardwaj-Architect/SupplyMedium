<%-- 
    Document   : companyRegistration
    Created on : Sep 18, 2014, 1:37:17 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.marketing.bean.MarketingPersonBean"%>
<%@page import="supply.medium.marketing.database.MarketingPersonMaster"%>
<%@page import="supply.medium.home.bean.BusinessCategoryBean"%>
<%@page import="supply.medium.home.database.BusinessCategoryMaster"%>
<%@page import="supply.medium.home.bean.CountryBean"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK rel="stylesheet" href="inside/companyregistration.css">
        <LINK rel="stylesheet" href="inside/pricing.css">
        <LINK rel="stylesheet" href="inside/layout.css">
        <LINK rel="stylesheet" href="inside/elements.css">
        <LINK rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <LINK rel="stylesheet" href="inside/Custome_Buttons.css">
        <LINK rel="stylesheet" href="inside/Custome_Popup.css">
        <SCRIPT type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.validate.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.customSelect.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/companyProfileValidator.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/additional-methods.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dropdownfiller.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/common.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/captcha.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/regfieldvalidator.js">
        </SCRIPT>
        <script type="text/javascript" language="javascript" src="inside/companyregistration.js">
        </script>
        <SCRIPT type="text/JavaScript" src="inside/regvalidator.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/multistepform.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/pricing.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dilbag.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/webkrit.js">
        </SCRIPT>
        <SCRIPT>

            $(document).ready(function () {
                $('select.selectbox').customSelect();

            });

        </SCRIPT>
        <script>
            function removeSpace()
            {
                var companyid=$("#companyid").val();
                if(companyid.indexOf(" ")!=-1)
                {
                    alert("Space id not allowed in Company Id");
                    companyid=companyid.replace(" ","");
                    $("#companyid").val(companyid);
                }
            }
        </script>
        <TITLE>Company Registration - SupplyMedium Inc.</TITLE>
    </HEAD>
    <BODY onLoad="generate_captcha('lcaptcha');" onkeydown="displayunicode(event);">
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
        <DIV class="pagetitle">New Company Registration</DIV>
    </DIV>
    <DIV class="contentcontainer">
        <DIV style="height: 780px;" id="content" class="content">
            <DIV style="width: 205px;" id="steps">
                <DIV style="margin-left: 20px;" id="step1" class="stepprocess">
                    <DIV class="stepno">1</DIV>
                </DIV>
                <DIV class="stepseparator">
                </DIV>
                <DIV id="step2" class="stepnext">
                    <DIV class="stepno">2</DIV>
                </DIV>
                <!--div class="stepseparator">
                </div>
                <div id="step3" class="stepnext">
                <div class="stepno">3</div>
                </div-->
                <DIV id="step1text" class="stepprocesstext">Company Info</DIV>
                <DIV style="width: 58px; padding-left: 33px;" id="step2text" class="stepnexttext">User 
                    Info</DIV>
                <!--div id="step3text" class="stepnexttext" style="padding-left:53px;width:58px;">Pricing</div-->
            </DIV>
            <!-- enctype="multipart/form-data" -->
            <FORM id="registrationform" encType="multipart/form-data" method="post" 
                  name="registrationform" action="CompanyRegistration">
                <DIV id="companyinfo">
                    <LINK rel="stylesheet" href="inside/custom_file_upload.css">
                    <DIV class="leftcontent">
                        <INPUT id="rowcount" name="count" value="1" type="hidden">
                        <INPUT id="addresscount" name="addresscount" value="1" type="hidden">
                        <DIV style="height: 30px;" class="row">
                            <IMG style="margin-left: 220px;" onclick="$('#buyerradio').prop('checked', true);" src="inside/buyer.png" 
                                 width="30" height="30">
                            <IMG style="margin-left: 35px;" onclick="$('#Supplierradio').prop('checked', true);" src="inside/seller.png" 
                                 width="30" height="30">
                            <IMG style="margin-left: 20px;" onclick="$('#Bothradio').prop('checked', true);" src="inside/both.png" 
                                 width="30" height="30">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">				Sign Up As<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <INPUT 
                                style="margin: 0px;" id="buyerradio" class="required radiobtnlbl" name="signupas" 
                                value="Buyer" type="radio">
                            <LABEL class="radiobtnlbl" 
                                   for="buyerradio">Buyer</LABEL> &nbsp;				<INPUT style="margin: 0px;" id="Supplierradio" 
                                   class="radiobtnlbl" name="signupas" value="Supplier" type="radio">
                            <LABEL class="radiobtnlbl" 
                                   for="Supplierradio">Supplier</LABEL>&nbsp; 				<INPUT id="Bothradio" class="radiobtnlbl" 
                                   name="signupas" value="Buyers/Suppliers" type="radio">
                            <LABEL class="radiobtn radiobtnlbl" 
                                   for="Bothradio">Buyer/Supplier</LABEL>&nbsp;		</DIV>
                        <DIV style="width: 420px;" class="row">
                            <DIV class="label">Logo</DIV>
                            <!--  <input type="file" class="file"  style="font-size: 12px;"
                                                            title="The logo dimensions should be at most 500x500 pixels in size should not exceed 1 MB.  Supported formats are jpg, png, bmp, gif and tiff."
                                                            name="logo" id="logo"  /> -->
                            <DIV class="logo_file_container">
                                <INPUT style="font-size: 12px;" id="logo" 
                                       class="logofile" title="The logo dimensions should be at most 500x500 pixels in size should not exceed 1 MB.  Supported formats are jpg, png, bmp, gif and tiff." 
                                       name="logo" type="file">
                            </DIV>
                            <LABEL id="logo_file_name" 
                                   class="logfilename_lbl">
                            </LABEL>
                            <LABEL id="fileupload_err" class="fileupload_err">
                            </LABEL>
                            <INPUT 
                                id="fileerror" name="fileerror" type="hidden">
                        </DIV>
                        <!--<div class="row">
                        <div class="label">Logo</div>
                        <div class="custom_file_upload">
                        <input type="text" autocomplete="off" class="file textbox" style="font-size: 12px;" 
                                                        title="The logo dimensions should be at most 500x500 pixels in size should not exceed 1 MB.  Supported formats are jpg, png, bmp, gif and tiff."
                                                        name="file_info" />
                        <div class="file_upload">
                        <input type="file" id="logo" name="file_upload">
                        </div>
                        <label  class="fileupload_err" style="margin-left:70px;"
                                                                id="fileupload_err">
                        </label>
                        <input type="hidden" name="fileerror" id="fileerror">
                        </div>
                        </div> -->
                        <DIV class="row">
                            <DIV class="label">				Company Name<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <INPUT 
                                id="companyname" class="required textbox" name="companyname" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">				Company Id<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <INPUT onkeyup="removeSpace()" 
                                   onblur="validateCompanyId(this.value);" id="companyid" class="textbox required"
                                   name="companyid" type="text" autocomplete="off">
                            <DIV id="companyidcheck">
                            </DIV>
                            <LABEL id="companyiderr" class="error" for="companyid" 
                                   generated="true">
                            </LABEL>
                            <INPUT id="companyidexist" name="companyidexist" type="hidden">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Introduced By</DIV>
                            <SELECT id="marketing_key" class="selectbox" name="marketing_key">
                            <%
                                ArrayList mpmAl=MarketingPersonMaster.showAll();
                                MarketingPersonBean objMpb=null;
                                for(int iMpm=0;iMpm<mpmAl.size();iMpm++)
                                {
                                    objMpb=(MarketingPersonBean)mpmAl.get(iMpm);
                            %>
                            <option value="<%=objMpb.getMarketing_person_key()%>"><%=objMpb.getMarketing_person_name()%>&nbsp;<%=objMpb.getContact_number()%></option>
                            <%
                                }
                            %>
                            </SELECT>
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Segment/Division Name</DIV>
                            <INPUT id="divisionname" class="textbox" 
                                   name="divisionname" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Business Unit Name</DIV>
                            <INPUT id="unitname" class="textbox" 
                                   name="unitname" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">				Business Category<SPAN 
                                    class="mandatory">*</SPAN>
                            </DIV>
                            <SELECT id="businesscategory" onChange="slctd_ctgry(this.value);" 
                                    class="selectbox required hasCustomSelect" name="businesscategory">
                                <OPTION>--Select Category--</OPTION>
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
                            </SELECT>
                            <LABEL id="businesscategoryerr" class="error" for="businesscategory" 
                                   generated="true">
                            </LABEL>
                        </DIV>
                        <DIV style="display: none;" id="ctgry_lstng" class="row">
                            <INPUT id="businesscategory2" 
                                   name="businesscategory2" type="hidden">
                            <DIV class="label">				Selected Categories<SPAN class="mandatory">
                                </SPAN>
                            </DIV>
                            <UL style="list-style: none; width: auto; height: auto;" id="slctd_ctgry_lst" 
                                class="textbox">
                            </UL>
                        </DIV>
                    </DIV>
                    <DIV style="height: 375px; margin-bottom: 100px;" id="compseparator" class="formseparator">
                    </DIV>
                    <DIV class="rightcontent">
                        <DIV id="address0">
                            <DIV class="row">
                                <DIV class="label">Branch</DIV>
                                <SELECT id="branch_0" class="selectbox" name="branch_0">
                                    <OPTION 
                                        value="Corporate Office">Corporate Office</OPTION>
                                    <OPTION 
                                        value="Site Office">Site Office</OPTION>
                                    <OPTION 
                                        value="Corporate Office">Warehouse / Distribution						Center</OPTION>
                                    <OPTION 
                                        value="Store">Store</OPTION>
                                </SELECT>
                            </DIV>
                            <DIV class="row">
                                <DIV class="label">					
                                    Country/Region<SPAN class="mandatory">*</SPAN>
                                </DIV>
                                <SELECT onChange="fetchState(this.value);
                                    getCountryCode(this.value);"
                                        class="selectbox required">
                                    <OPTION value="">--Select  Country--</OPTION>
                                    <%
                                                ArrayList countryList = CountryMaster.showAll();
                                                CountryBean scb = null;
                                                for (int i = 0; i < countryList.size(); i++) {
                                                    scb = (CountryBean) countryList.get(i);
                                    %>
                                    <option value="<%=scb.getCountryKey() + "*" + scb.getCountryCode()%>">
                                        <%=scb.getCountryName()%>
                                    </option>
                                    <%
                                                }
                                    %>
                                </SELECT>
                                <input type="hidden" id="countryregion_0" name="countryregion_0">
                                <LABEL id="countryregion_0err" style="margin-left:0px;" class="error" for="countryregion_0" 
                                       generated="true">
                                </LABEL>
                            </DIV>
                            <DIV class="row">
                                <DIV class="label">Address</DIV>
                                <INPUT style="margin-bottom: 5px;" id="address_0" 
                                       class="textbox" name="address_0" type="text" autocomplete="off">
                            </DIV>
                            <DIV class="row">
                                <DIV class="label">City</DIV>
                                <INPUT style="margin-bottom: 5px;" id="city_0" 
                                       class="textbox" name="city_0" type="text" autocomplete="off">
                            </DIV>
                            <DIV class="row">
                                <DIV class="label">State/Province</DIV>
                                <DIV id="select_0_container">
                                    <SELECT style="margin-bottom: 5px;" id="state_0" 
                                            class="selectbox" name="state_0">
                                        <OPTION>--Select State--</OPTION>
                                    </SELECT>
                                    <LABEL id="state_0err" class="error">
                                    </LABEL>
                                </DIV>
                                <INPUT style="margin-top: -33px; margin-left: 200px; display: none;" 
                                       id="state_text_0" class="textbox" name="state_text_0" type="text" autocomplete="off">
                            </DIV>
                            <DIV class="row">
                                <DIV class="label">Zip Code/Postal Code</DIV>
                                <INPUT style="margin-bottom: 5px;" 
                                       id="zipcode_0" class="textbox" name="zipcode_0" type="text" autocomplete="off">
                            </DIV>
                        </DIV>
                        <DIV id="address1detail" class="addrdetail">
                            <DIV id="addrleft1" class="addrleft">
                                <DIV id="addaddress1" class="label">Add Address</DIV>
                                <INPUT id="addaddressbtn1" class="addaddress" onClick="addAddress1();" name="addaddressbtn1" type="button">
                            </DIV>
                            <DIV style="display: none;" id="addrleft2" class="addrleft">
                                <DIV class="label">Add Address</DIV>
                                <INPUT id="addaddressbtn2" class="addaddress" onClick="addAddress2();" name="addaddressbtn2" type="button">
                            </DIV>
                            <DIV style="display: none;" id="addrright1" class="addrright">
                                <FIELDSET style="height: auto;" 
                                          class="regnaddrfieldset">
                                    <LEGEND>Address1:</LEGEND>
                                    <TEXTAREA id="addaddrlbl1" class="addrlbl" disabled="">
                                    </TEXTAREA>
                                </FIELDSET>
                                <INPUT id="removeaddressbtn1" class="removeaddress" onClick="removeAddress1();" name="removeaddressbtn1" type="button">
                            </DIV>
                        </DIV>
                        <DIV style="display: none;" id="address2detail" class="addrdetail">
                            <DIV style="display: none;" id="addrright2" class="addrright">
                                <FIELDSET style="height: auto;" id="addaddrfield2"
                                          class="regnaddrfieldset">
                                    <LEGEND>Address2:</LEGEND>
                                    <TEXTAREA id="addaddrlbl2" class="addrlbl" disabled="">
                                    </TEXTAREA>
                                </FIELDSET>
                                <INPUT id="removeaddressbtn2" class="removeaddress" onClick="removeAddress2();" name="removeaddressbtn2" type="button">
                            </DIV>
                        </DIV>
                        <DIV id="address2detail">
                        </DIV>
                    </DIV>
                    <SCRIPT type="text/javascript">
                        $(function () {

                            $(".Gen_Cus_Popup_Close").click(function ()
                            {
                                $(".Custome_Popup_Window").hide();
                            });
                            $("#addr1_popup_cancel").click(function ()
                            {
                                $(".Custome_Popup_Window").hide();
                            });
                        }
                    );


                    </SCRIPT>
                    <script>
                        $(document).ready(function () {
                            //alert("Height of div: " + $("div").height());
                            //    var popup= document.getElementById('Cus_Popup_Outline1').style.width;
                            var popup = 600;
                            var wins = $(document).width();
                            //    alert("width of window is : "+wins);
                            //    alert("width of popup is : "+popup);
                            paddingleft = (wins - popup) * 4 / 5;
                            //var content = myitem+200;
                            document.getElementById('Cus_Popup_Outline1').style.left = paddingleft + "px";
                        });
                    </script>
                    <DIV style=" z-index: 1230;" id="address_popup1" class="Custome_Popup_Window">
                        <DIV style=" top: 50%; width: 600px !important; height: 500px; margin-top: -250px; margin-left: -300px; position: fixed;"
                             class="Cus_Popup_Outline" id="Cus_Popup_Outline1">
                            <DIV class="Popup_Tilte_NewGroup Gen_Popup_Title">
                                <DIV style="padding: 0px 0px 0px 15px; float: left;">Add					Address</DIV>
                                <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                </DIV>
                            </DIV>
                            <DIV style="margin-top: 50px; margin-left: 95px;">
                                <DIV class="row">
                                    <DIV class="label">Branch</DIV>
                                    <SELECT style="width: 165px; display: inline;"
                                            id="branch_1" class="selectbox" tabIndex="1" name="branch_1">
                                        <OPTION value="Corporate Office">Corporate
                                            Office</OPTION>
                                        <OPTION value="Site Office">Site Office</OPTION>
                                        <OPTION value="Corporate Office">Warehouse
                                            / Distribution						Center</OPTION>
                                        <OPTION
                                            value="Store">Store</OPTION>
                                    </SELECT>
                                </DIV>
                                <DIV class="row">
                                    <DIV class="label">Country<SPAN class="mandatory">*</SPAN>
                                    </DIV>
                                    <SELECT id="countryregion_1"
                                            onchange="fetchStateForAddAddress1(this.value);//stateDropDown(this.value)" class="selectbox required"
                                            tabIndex="2" name="countryregion_1">
                                        <%
                                                    //ArrayList countryList=SmCountryMaster.showAll();
                                                    //SmCountryBean scb=null;
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
                                    <LABEL id="countryregion_1err"
                                           class="error" for="countryregion_1" generated="true">
                                    </LABEL>
                                </DIV>
                                <DIV class="row">
                                    <DIV class="label">Address</DIV>
                                    <INPUT style="margin-bottom: 5px;" id="address_1"
                                           class="textbox" tabIndex="3" name="address_1" type="text" autocomplete="off">
                                </DIV>
                                <DIV class="row">
                                    <DIV class="label">City</DIV>
                                    <INPUT style="margin-bottom: 5px;" id="city_1"
                                           class="textbox" tabIndex="4" name="city_1" type="text" autocomplete="off">
                                </DIV>
                                <DIV class="row">
                                    <DIV class="label">State/Province</DIV>
                                    <DIV id="select_1_container">
                                        <SELECT style="margin-bottom: 5px;" id="state_1"
                                                class="selectbox" tabIndex="5" name="state_1">
                                            <OPTION value="select">--Select
  						State--</OPTION>
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
                                    <INPUT style="margin-bottom: 5px;"
                                           id="zipcode_1" class="textbox" tabIndex="6" name="zipcode_1" type="text" autocomplete="off">
                                </DIV>
                                <DIV style="margin-top: 20px; margin-left: 20px;" class="row">
                                    <INPUT style="margin-right: 50px; margin-left: 50px;" id="addr1_popup_cancel" class="gen-btn-Gray" tabIndex="7" value="Cancel" type="button">
                                    <INPUT id="saveaddr1btn" class="gen-btn-Orange" tabIndex="8" value="Ok" type="button">
                                </DIV>
                                <DIV style="margin: 0px; width: 0px; height: 0px; opacity: 0;" id="addone_lastHiddenElement"
                                     class="row" tabIndex="9" href="#">
                                </DIV>
                            </DIV>
                        </DIV>
                    </DIV>
                    <SCRIPT type="text/javascript">
                        $(function () {

                            $(".Gen_Cus_Popup_Close").click(function ()
                            {
                                $(".Custome_Popup_Window").hide();
                            });

                            $("#addr2_popup_cancel").click(function ()
                            {
                                $(".Custome_Popup_Window").hide();
                            });

                        }
                    );

                    </SCRIPT>
                    <DIV style="display: none;" id="address_popup2" class="Custome_Popup_Window">
                        <DIV style="left: 50%; top: 50%; width: 600px !important; height: 500px; margin-top: -250px; margin-left: -300px; position: fixed;"
                             class="Cus_Popup_Outline">
                            <DIV class="Popup_Tilte_NewGroup Gen_Popup_Title">
                                <DIV style="padding: 5px 0px 0px 15px; float: left;">Add					Address</DIV>
                                <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                </DIV>
                            </DIV>
                            <DIV style="margin-top: 50px; margin-left: 95px;">
                                <DIV class="row" ?="">
                                     <DIV class="label">Branch</DIV>
                                    <SELECT id="branch_2" class="selectbox" tabIndex="1"
                                            name="branch_2">
                                        <OPTION value="Corporate Office">Corporate
                                            Office</OPTION>
                                        <OPTION value="Site Office">Site Office</OPTION>
                                        <OPTION value="Corporate Office">Warehouse
                                            / Distribution						Center</OPTION>
                                        <OPTION
                                            value="Store">Store</OPTION>
                                    </SELECT>
                                </DIV>
                                <DIV class="row">
                                    <DIV class="label">Country<SPAN class="mandatory">*</SPAN>
                                    </DIV>
                                    <SELECT id="countryregion_2"
                                            onchange="fetchStateForAddAddress2(this.value);//changeDropDown(this.value)" class="selectbox required"
                                            tabIndex="2" name="countryregion_2">
                                        <%
                                                    //ArrayList countryList=SmCountryMaster.showAll();
                                                    //SmCountryBean scb=null;
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
                                    <LABEL id="countryregion_2err"
                                           class="error" for="countryregion_2" generated="true">
                                    </LABEL>
                                </DIV>
                                <DIV class="row">
                                    <DIV class="label">Address</DIV>
                                    <INPUT style="margin-bottom: 5px;" id="address_2"
                                           class="textbox" tabIndex="3" name="address_2" type="text" autocomplete="off">
                                </DIV>
                                <DIV class="row">
                                    <DIV class="label">City</DIV>
                                    <INPUT style="margin-bottom: 5px;" id="city_2"
                                           class="textbox" tabIndex="4" name="city_2" type="text" autocomplete="off">
                                </DIV>
                                <DIV class="row">
                                    <DIV class="label">State/Province</DIV>
                                    <DIV id="select_2_container">
                                        <SELECT style="margin-bottom: 5px;" id="state_2"
                                                class="selectbox" tabIndex="5" name="state_2">
                                            <OPTION value="select">--Select
  						State--</OPTION>
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
                                    <INPUT style="margin-bottom: 5px;"
                                           id="zipcode_2" class="textbox" tabIndex="6" name="zipcode_2" type="text" autocomplete="off">
                                </DIV>
                                <DIV style="margin-top: 20px; margin-left: 20px;" class="row">
                                    <INPUT style="margin-right: 50px; margin-left: 50px;" id="addr1_popup_cancel" class="gen-btn-Gray" tabIndex="7" value="Cancel" type="button">
                                    <INPUT id="saveaddr2btn" class="gen-btn-Orange" tabIndex="8" value="Ok" type="button">
                                </DIV>
                            </DIV>
                        </DIV>
                        <DIV style="margin: 0px; width: 0px; height: 0px; opacity: 0;" id="addtwo_lastHiddenElement"
                             class="row" tabIndex="9" href="#">
                        </DIV>
                    </DIV>
                </DIV>
                <DIV id="userinfo">
                    <DIV class="leftcontent">
                        <DIV class="row">
                            <DIV class="label">First Name<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <INPUT id="firstname"
                                   class="required textbox" name="firstname" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Last Name<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <INPUT id="lastname"
                                   class="required textbox" name="lastname" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Title</DIV>
                            <SELECT id="usertitle" class="selectbox" name="title">
                                <OPTION
                                    value="MR">MR</OPTION>
                                <OPTION value="MRS">MRS</OPTION>
                                <OPTION
                                    value="MS">MS</OPTION>
                                <OPTION value="M/S">M/S</OPTION>
                            </SELECT>
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Department</DIV>
                            <INPUT id="department" class="textbox" name="department"
                                   type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Manager/Supervisor</DIV>
                            <INPUT id="managersupervisor" class="textbox"
                                   name="managersupervisor" type="text" autocomplete="off">
                        </DIV>
                    </DIV>
                    <DIV style="height: 150px; margin-bottom: 147px;" id="userseparator" class="formseparator">
                    </DIV>
                    <DIV class="rightcontent">
                        <DIV class="row">
                            <DIV class="label">Primary Phone<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <INPUT
                                style="width: 25px; margin-right: 5px; float: left;" id="countrycode" class="textbox"
                                disabled="" type="text" autocomplete="off">
                            <INPUT onBlur="validatePhoneNo(this.value);" style="width: 137px;"
                                   id="phone" class="required textbox number" name="phone" type="text" autocomplete="off">
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
                            <INPUT id="cell" class="textbox" name="cell" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Fax</DIV>
                            <INPUT id="fax" class="textbox" name="fax" type="text" autocomplete="off">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Email<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <INPUT onBlur="validateEmail(this.value)"
                                   id="email" class="email required textbox" name="email" type="text" autocomplete="off">
                            <SPAN id="emailcheck">
                            </SPAN>
                            <LABEL
                                id="emailerr" class="error" for="email" generated="true">
                            </LABEL>
                            <INPUT id="emailexist"
                                   name="emailexist" type="hidden">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Password<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <INPUT id="password"
                                   class="required textbox" title="Password should be of minimum 8 characters and maximum 15 characters. Password must contain at least one character,one number and one special character and starts with character (case sensitive)"
                                   name="password" type="password">
                        </DIV>
                        <DIV class="row">
                            <DIV class="label">Confirm Password<SPAN class="mandatory">*</SPAN>
                            </DIV>
                            <INPUT
                                id="retype" class="required textbox" name="retype" type="password">
                        </DIV>
                    </DIV>
                    <DIV class="pricing">
                        <INPUT id="RequestType" name="RequestType" value="NewRegistration"
                               type="hidden">
                        <INPUT name="RequestCode" value="1001" type="hidden">
                        <INPUT
                            style="display: none;" id="free" class="pricingradiobox" name="pricingoption"
                            value="Basic" type="radio">
                        <INPUT style="display: none;" id="plus" class="pricingradiobox"
                               name="pricingoption" value="Plus" type="radio">
                        <INPUT style="display: none;" id="premium"
                               class="pricingradiobox" name="pricingoption" value="Premium" type="radio">
                        <INPUT
                            style="display: none;" id="unlimited" class="pricingradiobox" name="pricingoption"
                            value="Unlimited" type="radio">
                        <DIV class="leftcontent">
                            <FIELDSET style="height: 90px;" class="fieldset">
                                <LEGEND style="margin-left: 10px;">Are
                                    you human?</LEGEND>
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
                                        <LABEL style="margin-left: 150px;"
                                               class="error" for="captcha" generated="true">
                                        </LABEL>
                                    </DIV>
                                </DIV>
                            </FIELDSET>
                            <!--
		<div id="captchacontainer">
                            <label id="lcaptcha" for="captcha" style="float:left;height:30px;font-size:20px">
                            </label>
                            <input type="captcha" name="captcha" id="captcha" class="textbox required" style="width:50px;"/>
                            <label for="captcha" generated="true" class="error" style="float:right;margin-left:5px;">
                            </label>
                            <input type="hidden" id="captchavalue" name="captchavalue" />
                            </div>-->
                        </DIV>
                        <DIV class="rightcontent">
                            <FIELDSET style="height: 90px;" class="fieldset">
                                <LEGEND style="margin-left: 10px;">Conditions</LEGEND>
                                <DIV style="width: 340px; height: 35px; margin-top: 5px;" id="condition1" class="row">
                                    <DIV class="checkContainer">
                                        <INPUT style="margin-top: 0px; float: left; cursor: pointer;"
                                               id="termsconditions" class="required" name="termsconditions" type="checkbox">
                                        <DIV>
                                        </DIV>
                                    </DIV>
                                    <!-- <label class="label" for="termsconditions" id="termsconditionslbl"
								style="width: 210px; height: 20px; line-height: 16px;margin-left:5px;text-decoration:underline;cursor:pointer;">Accept
								terms &amp; condition</label> -->
                                    <DIV style="width: 210px; height: 20px; line-height: 16px; text-decoration: underline; margin-left: 5px; cursor: pointer;"
                                         id="termsconditionslbl" class="label" for="termsconditions">Accept								terms
                                        &amp; condition</DIV>
                                    <LABEL style="width: 250px; margin-left: 0px;" class="error"
                                           for="termsconditions" generated="true">
                                    </LABEL>
                                </DIV>
                                <DIV style="width: 340px; height: 35px; cursor: pointer;" id="condition2" class="row">
                                    <DIV class="checkContainer">
                                        <INPUT style="margin-top: 0px; float: left; cursor: pointer;"
                                               id="policies" class="required" name="policies" type="checkbox">
                                        <DIV>
                                        </DIV>
                                    </DIV>
                                    <!-- <label class="label" for="policies" id="policieslbl"
								style="width: 280px; height: 20px; line-height: 16px;margin-left:5px;text-decoration:underline;cursor:pointer;">Accept
								privacy &amp; security policies</label> -->
                                    <DIV style="width: 280px; height: 20px; line-height: 16px; text-decoration: underline; margin-left: 5px; cursor: pointer;"
                                         id="policieslbl" class="label" for="policies">Accept								privacy &amp;
                                        security policies</DIV>
                                    <LABEL style="width: 250px; margin-left: 0px;" class="error"
                                           for="policies" generated="true">
                                    </LABEL>
                                </DIV>
                            </FIELDSET>
                            <FIELDSET style="height: 55px;" id="couponfieldset" class="fieldset">
                                <LEGEND
                                    style="margin-left: 10px;">Coupon Code</LEGEND>
                                <DIV style="width: 340px; height: 35px; padding-top: 5px; margin-top: 5px;" id="condition1"
                                     class="row">                                                    Discount Coupon
                                    (if any) <INPUT style="float: right;" id="couponcode" class="textbox" name="couponcode"
                                                    type="text" autocomplete="off">
                                </DIV>
                            </FIELDSET>
                        </DIV>
                        <DIV style="width: 30px; height: 70px; float: left;">
                        </DIV>
                        <DIV>
                            <INPUT style="left: 370px; top: 215px; position: absolute;" class="gen-btn-Orange" onClick="submitRegnForm();" value="Register" type="submit">
                        </DIV>
                        <LINK rel="stylesheet" href="inside/term.css">
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
                </DIV>
                <DIV id="pricing">
                    <DIV class="pricing">
                        <INPUT id="RequestType" name="RequestType" value="NewRegistration"
                               type="hidden">
                        <INPUT name="RequestCode" value="1001" type="hidden">
                        <DIV class="pricingheading">			Select appropriate package			</DIV>
                        <IMG class="mostpopularimg"
                             src="inside/most_popular.png">
                        <DIV class="pricingtag">
                            <DIV id="basic" class="bluetag_mo pricingtagimg">
                                <DIV class="tagtitle">Basic</DIV>
                                <DIV class="tagdescmed">Free for 30 days</DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                        <IMG class="tick" src="inside/blue_tick.png">
                                    </DIV>
                                    <DIV class="tagdescright">2 Employee license</DIV>
                                </DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                        <IMG class="tick" src="inside/blue_tick.png">
                                    </DIV>
                                    <DIV class="tagdescright">10 GB cloud storage</DIV>
                                </DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                        <IMG class="tick" src="inside/blue_tick.png">
                                    </DIV>
                                    <DIV class="tagdescright">				Transaction volume <BR>up					to $10K/month
                                    </DIV>
                                </DIV>
                                <DIV class="select">Select</DIV>
                            </DIV>
                            <DIV id="plus" class="greentag pricingtagimg">
                                <DIV class="tagtitle">Advance</DIV>
                                <DIV class="tagdescmed">$49/month</DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                        <IMG class="tick" src="inside/green_tick.png">
                                    </DIV>
                                    <DIV class="tagdescright">10 Employee license				</DIV>
                                </DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                        <IMG class="tick" src="inside/green_tick.png">
                                    </DIV>
                                    <DIV class="tagdescright">100 GB cloud storage</DIV>
                                </DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                        <IMG class="tick" src="inside/green_tick.png">
                                    </DIV>
                                    <DIV class="tagdescright">				Transaction volume <BR>up					to $100K/month
                                    </DIV>
                                </DIV>
                                <DIV class="select">Select</DIV>
                            </DIV>
                            <DIV id="premium" class="orangetag pricingtagimg">
                                <DIV class="tagtitle">Premium</DIV>
                                <DIV class="tagdescmed">$99/month</DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                        <IMG class="tick" src="inside/orange_tick.png">
                                    </DIV>
                                    <DIV class="tagdescright">				50 Employee license				</DIV>
                                </DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                        <IMG class="tick" src="inside/orange_tick.png">
                                    </DIV>
                                    <DIV class="tagdescright">500 GB cloud storage</DIV>
                                </DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                        <IMG class="tick" src="inside/orange_tick.png">
                                    </DIV>
                                    <DIV class="tagdescright">				Transaction volume <BR>up					to $500K/month
                                    </DIV>
                                </DIV>
                                <DIV class="select">Select</DIV>
                            </DIV>
                            <DIV id="unlimited" class="redtag pricingtagimg">
                                <DIV class="tagtitle">Unlimited</DIV>
                                <DIV class="tagdescmed">Request a Quote</DIV>
                                <DIV class="tagdesc">
                                    <DIV class="tagdescleft">
                                    </DIV>
                                    <DIV class="tagdescright w133">at info@supplymedium.com with no. of employees,
                                        required cloud storage, Transaction volume limit quantity/month				</DIV>
                                </DIV>
                                <DIV class="select">Select</DIV>
                            </DIV>
                        </DIV>
                        <INPUT style="display: none;" id="free"
                               class="pricingradiobox" name="pricingoption" value="Basic" type="radio">
                        <INPUT
                            style="display: none;" id="plus" class="pricingradiobox" name="pricingoption"
                            value="Plus" type="radio">
                        <INPUT style="display: none;" id="premium" class="pricingradiobox"
                               name="pricingoption" value="Premium" type="radio">
                        <INPUT style="display: none;"
                               id="unlimited" class="pricingradiobox" name="pricingoption" value="Unlimited"
                               type="radio">
                        <DIV class="leftcontent">
                            <FIELDSET style="height: 90px;" class="fieldset">
                                <LEGEND style="margin-left: 10px;">Are
                                    you human?</LEGEND>
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
                                        <LABEL style="margin-left: 150px;"
                                               class="error" for="captcha" generated="true">
                                        </LABEL>
                                    </DIV>
                                </DIV>
                            </FIELDSET>
                            <!--
		<div id="captchacontainer">
                            <label id="lcaptcha" for="captcha" style="float:left;height:30px;font-size:20px">
                            </label>
                            <input type="captcha" name="captcha" id="captcha" class="textbox required" style="width:50px;"/>
                            <label for="captcha" generated="true" class="error" style="float:right;margin-left:5px;">
                            </label>
                            <input type="hidden" id="captchavalue" name="captchavalue" />
                            </div>-->
                        </DIV>
                        <DIV class="rightcontent">
                            <FIELDSET style="height: 90px;" class="fieldset">
                                <LEGEND style="margin-left: 10px;">Conditions</LEGEND>
                                <DIV style="width: 340px; height: 35px; margin-top: 5px;" id="condition1" class="row">
                                    <DIV class="checkContainer">
                                        <INPUT style="margin-top: 0px; float: left; cursor: pointer;"
                                               id="termsconditions" class="required" name="termsconditions" type="checkbox">
                                        <DIV>
                                        </DIV>
                                    </DIV>
                                    <!-- <label class="label" for="termsconditions" id="termsconditionslbl"
								style="width: 210px; height: 20px; line-height: 16px;margin-left:5px;text-decoration:underline;cursor:pointer;">Accept
								terms &amp; condition</label> -->
                                    <DIV style="width: 210px; height: 20px; line-height: 16px; text-decoration: underline; margin-left: 5px; cursor: pointer;"
                                         id="termsconditionslbl" class="label" for="termsconditions">Accept								terms
                                        &amp; condition</DIV>
                                    <LABEL style="width: 250px; margin-left: 0px;" class="error"
                                           for="termsconditions" generated="true">
                                    </LABEL>
                                </DIV>
                                <DIV style="width: 340px; height: 35px; cursor: pointer;" id="condition2" class="row">
                                    <DIV class="checkContainer">
                                        <INPUT style="margin-top: 0px; float: left; cursor: pointer;"
                                               id="policies" class="required" name="policies" type="checkbox">
                                        <DIV>
                                        </DIV>
                                    </DIV>
                                    <!-- <label class="label" for="policies" id="policieslbl"
								style="width: 280px; height: 20px; line-height: 16px;margin-left:5px;text-decoration:underline;cursor:pointer;">Accept
								privacy &amp; security policies</label> -->
                                    <DIV style="width: 280px; height: 20px; line-height: 16px; text-decoration: underline; margin-left: 5px; cursor: pointer;"
                                         id="policieslbl" class="label" for="policies">Accept								privacy &amp;
                                        security policies</DIV>
                                    <LABEL style="width: 250px; margin-left: 0px;" class="error"
                                           for="policies" generated="true">
                                    </LABEL>
                                </DIV>
                            </FIELDSET>
                            <FIELDSET style="height: 55px;" id="couponfieldset" class="fieldset">
                                <LEGEND
                                    style="margin-left: 10px;">Coupon Code</LEGEND>
                                <DIV style="width: 340px; height: 35px; padding-top: 5px; margin-top: 5px;" id="condition1"
                                     class="row">                                                    Discount Coupon
                                    (if any) <INPUT style="float: right;" id="couponcode" class="textbox" name="couponcode"
                                                    type="text" autocomplete="off">
                                </DIV>
                            </FIELDSET>
                        </DIV>
                        <DIV style="width: 30px; height: 70px; float: left;">
                        </DIV>
                        <DIV>
                            <INPUT style="left: 540px; top: 574px; position: absolute;" class="gen-btn-Orange" onClick="submitRegnForm();" value="Register" type="submit">
                        </DIV>
                        <LINK rel="stylesheet" href="inside/term.css">
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
                </DIV>
            </FORM>
        </DIV>
    </DIV>
    <LINK rel="stylesheet" href="inside/regnfooter.css">
    <LINK rel="stylesheet" href="inside/footer.css">
    <DIV class="footer">
        <DIV class="footercontent">
            <DIV class="copyrights">Copyright 2015. <A style="color: rgb(255, 255, 255); text-decoration: none;"
                                                       href="http://supplymedium.com/" target="_blank">Supply Medium,
                    Inc.</A>
            </DIV>
        </DIV>
    </DIV>
    <LINK rel="stylesheet" href="inside/privacy.css">
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
        </DIV>
    </DIV>
    <DIV>
    </DIV>
</BODY>
</HTML>

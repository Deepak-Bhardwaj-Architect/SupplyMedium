<%@page import="supply.medium.home.database.CompanyBusinessCategoryMaster"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.bean.CompanyDetailsForVrBean"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.NaicBean"%>
<%@page import="supply.medium.home.database.NaicMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <BODY onLoad="lockUnlock('webkrit_content_loader');<% if (request.getParameter("name") != null) {%> showCompaniesNonVendorSearch('<%=request.getParameter("name")%>', 'Buyer');
                <% }%>
                Usr_anlys('Admin');
                customizeMenu();
                pypl_rslt('null');">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
    <LINK rel="stylesheet" href="inside/Cus_Toast.css">
    <%@include file="_header.jsp" %>
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/buyer_ven_reg.css">
        <link rel="stylesheet" href="inside/tablestyle.css">
        <title>
        </title>
        <div class="pagetitlecontainer">
            <!-- Page header -->
            <div class="pagetitle">Vendor Registration</div>
        </div>
        <div class="page">
            <!-- Page -->
            <div id="cntct_dtl_er" class="contact_detail_error" onClick="document.getElementById('cntct_dtl_er').style.display = 'none';">
            </div>
            <div class="contentcontainer" style="min-height:709px;">
                <!-- Content container -->
                <div id="supplier_ven_reg_content" style="">
                    <div class="main_tab_container">
                        <!-- This is the main tab bar container -->
                        <div id="req_queue_tab" class="main_tab_unselect">
                            <!-- This is the Request Queue tab -->
                            <a href="buyersVR.jsp" class="white">Request Queue</a></div>
                        <div id="add_buyer_tab" class="main_tab_select">
                            <!-- This is the Add Supplier tab -->
                            <a href="buyers.jsp" class="white">Add Buyer</a></div>
                    </div>
                    <div class="main_tab_sep">
                        <!-- This is the seperator div  -->
                    </div>
                    <div id="add_buyer_content" style="display: block;">
                        <!-- Vendor Registration Form Container -->
                        <div class="to_comp">
                            <label class="label" style="padding-left:20px;width:45px;color:white;line-height:25px;">To:</label>
                            <input type="text" autocomplete="off" id="to_company" style="width:920px;" class="textbox" onKeyUp="showCompaniesNonVendorSearch(this.value, 'Buyer');">
                        </div>
                        <form class="ven_reg" onsubmit="if ($('#w9form_flag').is(':checked')) {
                                    if ($('#w9form').val() === '') {
                                        ShowToast('Select W9 tax form', 1500);
                                        return false;
                                    }
                                }
                                ;
                                if ($('#selectedVendorKey').val().trim() === '') {
                                    ShowToast('Select buyer from search list before add a buyer', 1500);
                                    return false;
                                }" id="ven_reg_form" name="ven_reg_form" action="VendorRegistration" method="post" enctype="multipart/form-data">
                            <input type="hidden" id="selectedVendorKey" name="selectedVendorKey">
                            <input type="hidden" id="sendertype" name="sendertype" value="Supplier">
                            <div id="ven_search_result" class="com_search_result" style="display:none;">
                            </div>
                            <div class="ven_reg_head"> Vendor Registration
                            </div>
                            <%                                CompanyDetailsForVrBean obj = CompanyMaster.showCompanyDetailsForVrProcess(companyKey);
                                String result = CompanyBusinessCategoryMaster.showAllValuesByCompanyKey(companyKey);
                            %>
                            <div class="ven_reg_left">
                                <div class="row">
                                    <label class="label"> Company Name </label>
                                    <input type="text" autocomplete="off" id="company_name" name="company_name" class="textbox_disable" disabled="disabled" value="<%=obj.getCompanyName()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> Branch </label>
                                    <input type="text" autocomplete="off" id="branch_0" class="textbox_disable" disabled="disabled"  value="<%=obj.getBranch()%>">

                                </div>
                                <div class="row">
                                    <label class="label"> Country/Region </label>
                                    <input type="text" autocomplete="off" id="countryregion_0" class="textbox_disable" disabled="disabled" value="<%=CountryMaster.showCountryFromKey(obj.getCountry())%>">

                                </div>
                                <div class="row">
                                    <label class="label"> Address </label>
                                    <input type="text" autocomplete="off" id="address" name="address" class="textbox_disable" disabled="disabled" value="<%=obj.getAddress()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> City </label>
                                    <input type="text" autocomplete="off" id="city" name="city" class="textbox_disable" disabled="disabled" value="<%=obj.getCity()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> State/Province </label>
                                    <input type="text" autocomplete="off" id="state_0" class="textbox_disable" disabled="disabled" value="<%=StateMaster.showStateFromKey(obj.getState())%>">

                                </div>
                                <div class="row">
                                    <label class="label"> Zip Code/Postal Code </label>
                                    <input type="text" autocomplete="off" id="zipcode" name="zipcode" class="textbox_disable" disabled="disabled" value="<%=obj.getZipCode()%>">
                                </div>

                                <div style="height: 100px;" class="row" id="type">
                                    <div class="label">
                                        Type<span class="mandatory">
                                        </span>
                                    </div>
                                    <fieldset>
                                        <legend> Type </legend>
                                        <input type="radio" value="Individual/Sole Proprietor" class="radiobtn required" id="internetuser" name="comtype" checked="checked" style="vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;">
                                        <label style="vertical-align: middle; font-size: 12px; color: #282828; line-height:25px;" for="internetuser">
                                            Individual/Sole Proprietor &nbsp;</label>
                                        <br>
                                        <input type="radio" value="Corporation, Partnerships, other" style="vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;" class="radiobtn" name="comtype" id="transuser">
                                        <label style="vertical-align: middle; font-size: 12px; color: #282828;line-height:25px;" for="transuser">
                                            Corporation, Partnerships, other&nbsp;</label>
                                        <label style="margin-left: 0px !important; width: 150px;" class="error" generated="true" for="usertype">
                                        </label>
                                    </fieldset>
                                </div>
                            </div>
                            <div class="ven_reg_right">
                                <div class="row">
                                    <label class="label"> Business Contact Name </label>
                                    <input type="text" autocomplete="off" id="contact_name" name="cname" class="textbox_disable" disabled="disabled" value="<%=obj.getContactName()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> Contact Title/Department </label>
                                    <input type="text" autocomplete="off" id="titledept" name="titledept" class="textbox_disable" disabled="disabled" value="<%=obj.getDepartment()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> Contact Email </label>
                                    <input type="text" autocomplete="off" id="email" name="email" class="textbox_disable" disabled="disabled" value="<%=obj.getEmail()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> Phone </label>
                                    <input type="text" autocomplete="off" id="phone" name="phone" class="textbox_disable" disabled="disabled" value="<%=obj.getPhone()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> Cell </label>
                                    <input type="text" autocomplete="off" id="cell" name="cell" class="textbox_disable" disabled="disabled" value="<%=obj.getCell()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> Fax </label>
                                    <input type="text" autocomplete="off" id="fax" name="fax" class="textbox_disable" disabled="disabled" value="<%=obj.getFax()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> Type of Business </label>
                                    <input type="text" autocomplete="off" id="typeofbusiness" class="textbox_disable" disabled="" value="<%=obj.getTypeOfBusiness()%>">
                                </div>
                                <div class="row">
                                    <label class="label"> Business Category </label>
                                    <input type="text" autocomplete="off" id="businesscategory" class="textbox_disable" disabled="disabled" value="<%=result%>">

                                </div>
                                <div class="row">
                                    <label class="label"> Business Tax ID </label>
                                    <input type="text" autocomplete="off" id="taxid" name="taxid" class="textbox">
                                </div>
                                <div class="row">
                                    <label class="label"> NAICS Code </label>
                                    <SELECT name="naicscode" class="customSelect selectbox" style="height:30px;width:188px;">
                                        <OPTION value="">-- Select NAICS Code --</OPTION>
                                            <%                                    ArrayList countryList = NaicMaster.showAll();
                                                NaicBean nb = null;
                                                for (int i = 0; i < countryList.size(); i++) {
                                                    nb = (NaicBean) countryList.get(i);
                                            %>
                                        <option value="<%=nb.getNaicsCode()%>"> 
                                            <%=nb.getNaicsDescription()%>
                                        </option>
                                        <%
                                            }
                                        %>
                                    </SELECT>
                                    <!--                                    <span class="customSelect selectbox" style="width: 165px; display: inline-block;">
                                                                            <span class="customSelectInner" style="width: 165px; display: inline-block;">-- Select NAICS Code --</span>
                                                                        </span>-->
                                </div>
                                <div class="row">
                                    <label class="label">
                                    </label>
                                    <div class="checkContainer" style="margin-right:5px;">
                                        <input type="checkbox" name="w9form_flag" id="w9form_flag" onclick="$('#w9Form_upload_btn').toggle();" >
                                        <div>
                                        </div>
                                    </div>
                                    <label for="w9form_flag" style="width:300px !important;line-height:12px;font-family:Verdana;font-size:10px;"> W9 tax form will be submitted<br> to complete the registration process
                                    </label>
                                </div>
                                <div class="row" id="w9Form_upload_btn" style="display:none">
                                    <label class="label"> W9 Tax Form </label>
                                    <input type="file" name="w9form" id="w9form">
                                </div>
                            </div>
                            <div class="buss_size_container">
                                <div class="side_heading"> Business Size ( Select One )
                                </div>
                                <div class="buss_size_large">
                                    <div class="checkContainer">
                                        <input type="radio" checked="checked" name="buss_size" id="buss_large" value="Large">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text">
                                        <b>Large:<br>
                                        </b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A domestic concren which, including domestic and foreign divisions and affiliates, normally 
                                        employs 500 or more persons, is independently -or- publicly-owned or controlled and operated, 
                                        and which may be division of another domestic or foreign concern.</label>
                                </div>
                                <div class="buss_size_small">
                                    <div class="checkContainer">
                                        <input type="radio" name="buss_size" id="buss_small" value="Small">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text">
                                        <b>Small:<br>
                                        </b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Small business cocren" means a concren, including its affiliates, that is independently 
                                        owned and operated, not dominant in the field of operation in which it is bidding on government
                                        contracts, and qualified as a small business under the criteria in 13 CFR Part 121 and the
                                        size standard in FAR Clause 52.219-1, as well as the Small Business Act, Section 3.</label>
                                </div>
                            </div>
                            <div class="buss_clari_container">
                                <div class="side_heading"> Business Classification per the Federal Acquisition Regulations. Section 2.101,
                                    where applicable: (Select all that apply)
                                </div>
                                <div class="checkContainer">
                                    <input type="checkbox" name="Disadvantaged" id="Disadvantaged">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="Disadvantaged">
                                    <b>Disadvantaged:</b> In addition to meeting the requirements of the FAR definition, 
                                    any business classfying them selves as Disadvantaged must be certified by the Small Business 
                                    Administration and have that certification in good standing.
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="HubZone" id="HubZone">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="HubZone">
                                    <b>HUBZone:</b> In addition to meeting the requirements of the FAR definition, any business
                                    classifying themselves as a HUBZ one must be certified by the small Business Administraiotn and
                                    have that certification in good standing.
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="WomenOwned" id="WomenOwned">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="WomenOwned">Women-Owned
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="HandicappedOwned" id="HandicappedOwned">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="HandicappedOwned">handicapped-Owned
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="VETERANOWNED" id="VETERANOWNED">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="VETERANOWNED">Veteran-Owned
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="SDVETERANOWNED" id="SDVETERANOWNED">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="SDVETERANOWNED">Service-Disabled Veteran-Owned
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="HBCORMI" id="HBCORMI">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="HBCORMI">Historically-Back College or Minority Institution
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="MBE" id="MBE">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="MBE">Minority Business Enterprise
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="NonProfit" id="NonProfit">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="NonProfit">
                                    <b>Non-Profit:</b> Any business or organization that has received non-profit status
                                    under IRS Regulation 501(c)(3).
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="Foreign" id="Foreign">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="Foreign">
                                    <b>Foreign:</b> A concern which is not incorporated in the United States or an 
                                    unincorporated concern having its principle place of business outside the United States.
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="PublicSector" id="PublicSector">
                                    <div>
                                    </div>
                                </div>
                                <input type="checkbox" name="buss_small" id="buss_small">
                                <label class="des_text" for="PublicSector">
                                    <b>Public Sector:</b> An agency of the Federal or State Government, or a local municipality.
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="ANCORITNSB" id="ANCORITNSB">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="ANCORITNSB">Alaska Native Corporations Indian Tripe that is not a small business.
                                </label>
                                <div class="checkContainer">
                                    <input type="checkbox" name="ANCORITNCD" id="ANCORITNCD">
                                    <div>
                                    </div>
                                </div>
                                <label class="des_text" for="ANCORITNCD">
                                    <b>Alaska Native Corporations or Indian Tribe:</b> Not certified by the Small Business Administration as disadvantaged.
                                </label>
                            </div>
                            <div class="addl_det_container">
                                <div class="side_heading"> Additional Details
                                </div>
                                <textarea class="addr_det_text" id="additional_det" name="additional_det"></textarea>
                            </div>
                            <div class="ven_reg_btns">
                                <input type="hidden" id="usr_typ" value="Admin">
                                <input type="button" class="gen-btn-Gray" style="margin-right:50px;" value="Cancel">
                                <input type="submit" class="gen-btn-Orange" value="Send" id="add_vendor_btn" onclick="showLoading()">
                            </div>
                            <div id="vendor_reg_error" class="vendor_reg_error">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <%@include file="_VendorsOfSameBusinessCategory.jsp"%>
        </div>
        <%@include file="_footer.jsp" %>
        <div>
        </div>
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
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/RestCSS.css">
        <link rel="stylesheet" href="inside/elements.css">
        <%@include file="_invite.jsp" %>
        <script type="text/JavaScript" src="inside/restrict_menu.js">
        </script>
        <script>


                                            $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt_fieldvalidator.js");
                                            $.getScript("/SupplyMedium/Views/Registration/js/regvalidator.js");
                                            $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt.js", function (data, textStatus, jqxhr) {
                                                userOnload();
                                            });

        </script>
    </body>
</html>
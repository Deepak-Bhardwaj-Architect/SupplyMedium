<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="supply.medium.home.bean.CompanyDetailsForVrBean"%>
<%@page import="supply.medium.home.bean.VendorRegistrationBean"%>
<%@page import="supply.medium.home.database.VendorRegistrationMaster"%>
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
    <BODY onLoad="lockUnlock('webkrit_content_loader');Usr_anlys('Admin');
            customizeMenu();
            pypl_rslt('null');">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
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
            <div id="cntct_dtl_er" class="contact_detail_error" onClick="document.getElementById('cntct_dtl_er').style.display ='none';">
            </div>
            <div class="contentcontainer" style="min-height:709px;">
                <!-- Content container -->
                <div id="supplier_ven_reg_content" style="">
                    <div class="main_tab_container">
                        <!-- This is the main tab bar container -->
                        <div id="req_queue_tab" class="main_tab_select">
                            <!-- This is the Request Queue tab -->
                            <a href="suppliersVR.jsp" class="white">Request Queue</a></div>
                        <div id="add_buyer_tab" class="main_tab_unselect">
                            <!-- This is the Add Supplier tab -->
                            <a href="suppliers.jsp" class="white">Add Supplier</a></div>
                    </div>
                    <div class="main_tab_sep">
                        <!-- This is the seperator div  -->
                    </div>
                    <div id="req_queue_content">
                        <!-- Request queue content container. This contain two queues -->
                        <div class="sub_tab_container">
                            <!-- This is inner tab bar container -->
                            <div class="highlight" id="buyer_reg_tab" onclick="selectList('buyer_reg_tab');$('#buyer_req_content').show();$('#my_req_content').hide();">Received Request</div>
                            <!-- Buyer Request tab -->
                            <div class="normal" id="my_reg_tab" onclick="selectList('my_reg_tab');$('#buyer_req_content').hide();$('#my_req_content').show();">Sent Request</div>
                            <!-- My Request tab -->
                        </div>
                        <div class="buyer_req_content" id="buyer_req_content">
                            <!-- Buyer Request DataTable container -->
                            <div class="tablecontent" id="table_content1">
                                <div class="DT_border">
                                </div>
                                <div id="buyer_req_list_wrapper" class="dataTables_wrapper" role="grid">
                                    <div class="dataTables_filter" id="buyer_req_list_filter">
                                        <label>Search <input type="text" autocomplete="off" aria-controls="buyer_req_list">
                                        </label>
                                    </div>
                                    <div class="fixed_height">
                                        <table id="buyer_req_list" style="width: 997px;" class="dataTable" aria-describedby="buyer_req_list_info">
                                            <thead>
                                                <tr role="row">
                                                    <th class="rowBorder sorting_asc" role="columnheader" tabindex="0" aria-controls="buyer_req_list" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Company Name: activate to sort column descending">Company Name</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="buyer_req_list" rowspan="1" colspan="1" aria-label="Phone Number: activate to sort column ascending">Phone Number</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="buyer_req_list" rowspan="1" colspan="1" aria-label="Country: activate to sort column ascending">Country</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="buyer_req_list" rowspan="1" colspan="1" aria-label="Email: activate to sort column ascending">Email</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="buyer_req_list" rowspan="1" colspan="1" aria-label="Details: activate to sort column ascending">Details</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="buyer_req_list" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending">Status</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="buyer_req_list" rowspan="1" colspan="1" aria-label="Date: activate to sort column ascending">Date</th>
                                                </tr>
                                            </thead>
                                            <tbody role="alert" aria-live="polite" aria-relevant="all">
                                                
                                                    <%
                                        ArrayList vrReiceved = VendorRegistrationMaster.showAllByTypeAndStatus(companyKey,"received","Supplier", "");
                                        VendorRegistrationBean vrb = null;
                                        String stl="";
                                        CompanyDetailsForVrBean crb=null;
                                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                                        DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
                                        Date date=null;
                                        for (int i = 0; i < vrReiceved.size(); i++) {
                                            vrb = (VendorRegistrationBean) vrReiceved.get(i);
                                            if(i%2==0)
                                                stl="class='even'";
                                            else
                                                stl="class='odd'";
                                            if(companyKey.equals(vrb.getCompanyKeyFrom()))
                                                crb=CompanyMaster.showCompanyDetailsForVrProcess(vrb.getCompanyKeyTo());
                                            else if(companyKey.equals(vrb.getCompanyKeyTo()))
                                                crb=CompanyMaster.showCompanyDetailsForVrProcess(vrb.getCompanyKeyFrom());
                                           date = df.parse(vrb.getSentOn());
                                            %>
                                            <tr <%=stl %> <% if(vrb.getVrKey().equals(request.getParameter("nid"))){out.println("style='background:#c9c9c9;'");} %> onclick="javascript:showVrProcessData('<%=vrb.getVrKey()%>')">
                                                    <td><%=crb.getCompanyName() %></td>
                                                    <td><%=crb.getPhone() %></td>
                                                    <td><%=CountryMaster.showCountryFromKey(crb.getCountry()) %></td>
                                                    <td><%=crb.getEmail() %></td>
                                                    <td><%=vrb.getAdditionalDetails() %></td>
                                                    <td><%=vrb.getRequestStatus() %></td>
                                                    <td><%=df2.format(date) %></td>
                                                </tr>                                     
                                            <%
                                        }
                                        if(vrReiceved.size()==0)
                                        {
                                         %>
                                         <tr class="odd">
                                             <td valign="top" colspan="7" class="dataTables_empty">No Request found</td>
                                         </tr>
                                         <% } %>                                                
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="dataTables_info" id="buyer_req_list_info">Showing 0 to 0 of 0 entries</div>
                                    <div class="dataTables_paginate paging_full_numbers" id="buyer_req_list_paginate">
                                        <a tabindex="0" class="first paginate_button paginate_button_disabled" id="buyer_req_list_first">First</a>
                                     <a tabindex="0" class="previous paginate_button paginate_button_disabled" id="buyer_req_list_previous">Previous</a>
                                        <span>   
                                        </span>
                                        <a tabindex="0" class="next paginate_button paginate_button_disabled" id="buyer_req_list_next">Next</a>
                                        <a tabindex="0" class="last paginate_button paginate_button_disabled" id="buyer_req_list_last">Last</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="my_req_content" id="my_req_content" style="display:none;">
                            <!-- My Request DataTable container -->
                            <div class="tablecontent" id="table_content2">
                                <div class="DT_border">
                                </div>
                                <div id="my_req_list_wrapper" class="dataTables_wrapper" role="grid">
                                    <div class="dataTables_filter" id="my_req_list_filter">
                                        <label>Search <input type="text" autocomplete="off" aria-controls="my_req_list">
                                        </label>
                                    </div>
                                    <div class="fixed_height">
                                        <table id="my_req_list" style="width: 997px;" class="dataTable" aria-describedby="my_req_list_info">
                                            <thead>
                                                <tr role="row" >
                                                    <th class="rowBorder sorting_asc" role="columnheader" tabindex="0" aria-controls="my_req_list" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Company Name: activate to sort column descending">Company Name</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="my_req_list" rowspan="1" colspan="1" aria-label="Phone Number: activate to sort column ascending">Phone Number</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="my_req_list" rowspan="1" colspan="1" aria-label="Country: activate to sort column ascending">Country</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="my_req_list" rowspan="1" colspan="1" aria-label="Email: activate to sort column ascending">Email</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="my_req_list" rowspan="1" colspan="1" aria-label="Details: activate to sort column ascending">Details</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="my_req_list" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending">Status</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="my_req_list" rowspan="1" colspan="1" aria-label="Date: activate to sort column ascending">Date</th>
                                                </tr>
                                            </thead>
                                            <tbody role="alert" aria-live="polite" aria-relevant="all">
                                                
                                                    <%
                                         vrReiceved = VendorRegistrationMaster.showAllByTypeAndStatus(companyKey,"sent","Buyer", "");
                                         vrb = null;
                                         stl="";
                                         for (int i = 0; i < vrReiceved.size(); i++) {
                                            vrb = (VendorRegistrationBean) vrReiceved.get(i);
                                            if(i%2==0)
                                                stl="class='even'";
                                            else
                                                stl="class='odd'";
                                            if(companyKey.equals(vrb.getCompanyKeyFrom()))
                                                crb=CompanyMaster.showCompanyDetailsForVrProcess(vrb.getCompanyKeyTo());
                                            else if(companyKey.equals(vrb.getCompanyKeyTo()))
                                                crb=CompanyMaster.showCompanyDetailsForVrProcess(vrb.getCompanyKeyFrom());
                                           date = df.parse(vrb.getSentOn());
                                            %>
                                            <tr <%=stl %> <% if(vrb.getVrKey().equals(request.getParameter("nid"))){out.println("style='background:#c9c9c9;'");} %> onclick="javascript:showVrProcessData('<%=vrb.getVrKey()%>')">
                                                    <td><%=crb.getCompanyName() %></td>
                                                    <td><%=crb.getPhone() %></td>
                                                    <td><%=CountryMaster.showCountryFromKey(crb.getCountry()) %></td>
                                                    <td><%=crb.getEmail() %></td>
                                                    <td><%=vrb.getAdditionalDetails() %></td>
                                                    <td><%=vrb.getRequestStatus() %></td>
                                                    <td><%=df2.format(date) %></td>
                                                </tr>
                                            
                                            <%
                                        }
                                        if(vrReiceved.size()==0)
                                        {
                                         %>
                                         <tr class="odd">
                                             <td valign="top" colspan="7" class="dataTables_empty">No Request found</td>
                                         </tr>
                                         <% } %>                                                
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="dataTables_info" id="my_req_list_info">Showing 0 to 0 of 0 entries</div>
                                    <div class="dataTables_paginate paging_full_numbers" id="my_req_list_paginate">
                                        <a tabindex="0" class="first paginate_button paginate_button_disabled" id="my_req_list_first">First</a>
                                        <a tabindex="0" class="previous paginate_button paginate_button_disabled" id="my_req_list_previous">Previous</a>
                                        <span>
                                        </span>
                                        <a tabindex="0" class="next paginate_button paginate_button_disabled" id="my_req_list_next">Next</a>
                                        <a tabindex="0" class="last paginate_button paginate_button_disabled" id="my_req_list_last">Last</a>
                                    </div>
                                </div>
                            </div>
                        </div>
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
        <div style="display: none; z-index: 5000;" class="Custome_Popup_Window" id="supplier_form_popup">
            <div class="Cus_Popup_Outline" style="position: fixed; margin:auto;top: 60px; left: 133px;width: 1042px!important;height:500px!important;/*width: 1010px; height: 720px; margin-top:-360px;margin-left:-505px;*/border-radius: 0px;">
                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                    <div style="padding: 0px 0px 0px 15px; float: left">Filled Vendor registration Form</div>
                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                    </div>
                </div>
                <div id="supplier_form_popup_content" style="overflow:auto;height:435px;float:left; position:relative;margin-top:10px;margin-bottom:10px;" class="mCustomScrollbar _mCS_7">
                    <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_7" style="position:relative;height: 441px;width: 1015px;overflow: scroll;">
                        <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
                            <form class="ven_reg" id="popup_ven_reg_form" name="popup_ven_reg_form" action="VrAction" method="post">
                                
                            </form>
                            <form name="w9form_download_frm" id="w9form_download_frm" action="SupplyMedium/W9FormDownloadServlet" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="w9FormPath" id="w9FormPath">
                            </form>
                            <div id="vendor_reg_popup_error" class="vendor_reg_error">
                            </div>
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
        <title>Toast Window </title>
        <link rel="stylesheet" href="inside/Cus_Toast.css">
        <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
        <div id="Toast_Window" style="display:none;">
            <p class="Toast_Data">
            </p>
        </div>
        <!--  <script type="text/JavaScript" src="/SupplyMedium/Views/Utils/js/dropdownfiller.js">
        </script>
        <script type="text/JavaScript" src="/SupplyMedium/Views/Registration/js/companyProfileFiller.js">
        </script>
        <script type="text/JavaScript" src="/SupplyMedium/Views/VendorReg/js/supplier_ven_reg_DT.js">
        </script>
        <script type="text/JavaScript" src="/SupplyMedium/Views/VendorReg/js/ven_reg_addr_mgmt.js">
        </script>
        <script type="text/JavaScript" src="/SupplyMedium/Views/VendorReg/js/supplier_ven_reg_form.js">
        </script>
        <script type="text/JavaScript" src="/SupplyMedium/Views/VendorReg/js/supplier_ven_reg.js">
        </script>-->
    </div>
    <%@include file="_invite.jsp" %>
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
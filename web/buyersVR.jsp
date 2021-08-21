<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
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
                            <a href="buyersVR.jsp" class="white">Request Queue</a></div>
                        <div id="add_buyer_tab" class="main_tab_unselect">
                            <!-- This is the Add Supplier tab -->
                            <a href="buyers.jsp" class="white">Add Buyer</a></div>
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
                                        ArrayList vrReiceved = VendorRegistrationMaster.showAllByTypeAndStatus(companyKey,"received","Buyer", "");
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
                                         vrReiceved = VendorRegistrationMaster.showAllByTypeAndStatus(companyKey,"sent","Supplier", "");
                                         vrb = null;
                                         stl="";
                                         date=null;
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
                                           <tr <%=stl %> <% if(vrb.getVrKey().equals(request.getParameter("nid"))){out.println("style='background:#c9c9c9;'");} %>  onclick="javascript:showVrProcessData('<%=vrb.getVrKey()%>')">
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
        <form onSubmit="" method="post" style="display: none;" class="Custome_Popup_Window" id="Popup_Address">
            <div class="Cus_Popup_Outline" style="position:fixed;width:600px;height:500px;top:25%;left:25%">
                <div class="Popup_Tilte_NewGroup Gen_Popup_Title">
                    <div style="padding: 0px 0px 0px 15px; float: left">Add
                        Address</div>
                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" onclick="$('#supplier_form_popup').fadeOut();">
                    </div>
                </div>
                <div style="margin-top:50px;margin-left:95px;">
                    <div class="row">
                        <div class="label">Branch</div>
                        <select id="branch_pop" name="branch_pop" class="selectbox hasCustomSelect" style="-webkit-appearance: menulist-button; width: 188px; position: absolute; opacity: 0; height: 28px; font-size: 12px;">
                            <option value="">--Select Branch--</option>
                            <option value="Store">Store</option>
                            <option value="Site Office">Site Office</option>
                            <option value="Corporate Office">Corporate Office</option>
                            <option value="Warehouse / Distribution Center">Warehouse / Distribution Center</option>
                        </select>
                        <span class="customSelect selectbox" style="display: inline-block;">
                            <span class="customSelectInner" style="width: 165px; display: inline-block;">--Select Branch--</span>
                        </span>
                    </div>
                    <div class="row">
                        <div class="label">Country/Region<span class="mandatory">*</span>
                        </div>
                        <select id="countryregion_pop" name="countryregion_pop" class="selectbox required hasCustomSelect" onChange="fetchState(this.id)" style="-webkit-appearance: menulist-button; width: 188px; position: absolute; opacity: 0; height: 28px; font-size: 12px;">
                            <option value="">--Select Country--</option>
                            <option value="United States">United States</option>
                            <option value="Afghanistan">Afghanistan</option>
                            <option value="Albania">Albania</option>
                            <option value="Algeria">Algeria</option>
                            <option value="American Samoa">American Samoa</option>
                            <option value="Andorra">Andorra</option>
                            <option value="Angola">Angola</option>
                            <option value="Anguilla">Anguilla</option>
                            <option value="Antarctica">Antarctica</option>
                            <option value="Antigua and Barbuda">Antigua and Barbuda</option>
                            <option value="Argentina">Argentina</option>
                            <option value="Armenia">Armenia</option>
                            <option value="Aruba">Aruba</option>
                            <option value="Ascension">Ascension</option>
                            <option value="Australia">Australia</option>
                            <option value="Austria">Austria</option>
                            <option value="Azerbaijan">Azerbaijan</option>
                            <option value="Bahamas">Bahamas</option>
                            <option value="Bahrain">Bahrain</option>
                            <option value="Bangladesh">Bangladesh</option>
                            <option value="Barbados">Barbados</option>
                            <option value="Belarus">Belarus</option>
                            <option value="Belgium">Belgium</option>
                            <option value="Belize">Belize</option>
                            <option value="Benin">Benin</option>
                            <option value="Bermuda">Bermuda</option>
                            <option value="Bhutan">Bhutan</option>
                            <option value="Bolivia">Bolivia</option>
                            <option value="Bosnia and Herzegovina">Bosnia and Herzegovina</option>
                            <option value="Botswana">Botswana</option>
                            <option value="Brazil">Brazil</option>
                            <option value="Brunei">Brunei</option>
                            <option value="Bulgaria">Bulgaria</option>
                            <option value="Burkina Faso">Burkina Faso</option>
                            <option value="Burundi">Burundi</option>
                            <option value="C">C</option>
                            <option value="Cambodia">Cambodia</option>
                            <option value="Cameroon">Cameroon</option>
                            <option value="Canada">Canada</option>
                            <option value="Cape Verde">Cape Verde</option>
                            <option value="Cayman Islands">Cayman Islands</option>
                            <option value="Central African Republic">Central African Republic</option>
                            <option value="Chad">Chad</option>
                            <option value="Chile">Chile</option>
                            <option value="China">China</option>
                            <option value="Christmas Island">Christmas Island</option>
                            <option value="Cocos (Keeling) Islands">Cocos (Keeling) Islands</option>
                            <option value="Colombia">Colombia</option>
                            <option value="Comoros">Comoros</option>
                            <option value="Congo">Congo</option>
                            <option value="Cook Islands">Cook Islands</option>
                            <option value="Costa Rica">Costa Rica</option>
                            <option value="Croatia">Croatia</option>
                            <option value="Cuba">Cuba</option>
                            <option value="Cyprus">Cyprus</option>
                            <option value="Czech Republic">Czech Republic</option>
                            <option value="Denmark">Denmark</option>
                            <option value="Diego Garcia">Diego Garcia</option>
                            <option value="Djibouti">Djibouti</option>
                            <option value="Dominica">Dominica</option>
                            <option value="Dominican Republic">Dominican Republic</option>
                            <option value="East Timor">East Timor</option>
                            <option value="Ecuador">Ecuador</option>
                            <option value="Egypt">Egypt</option>
                            <option value="El Salvador">El Salvador</option>
                            <option value="Equatorial Guinea">Equatorial Guinea</option>
                            <option value="Eritrea">Eritrea</option>
                            <option value="Estonia">Estonia</option>
                            <option value="Ethiopia">Ethiopia</option>
                            <option value="Faeroe Islands">Faeroe Islands</option>
                            <option value="Falkland Islands">Falkland Islands</option>
                            <option value="Fiji">Fiji</option>
                            <option value="Finland">Finland</option>
                            <option value="France">France</option>
                            <option value="French Guiana">French Guiana</option>
                            <option value="French Polynesia">French Polynesia</option>
                            <option value="Gabon">Gabon</option>
                            <option value="Gambia">Gambia</option>
                            <option value="Georgia">Georgia</option>
                            <option value="Germany">Germany</option>
                            <option value="Ghana">Ghana</option>
                            <option value="Gibraltar">Gibraltar</option>
                            <option value="Greece">Greece</option>
                            <option value="Greenland">Greenland</option>
                            <option value="Grenada">Grenada</option>
                            <option value="Guadeloupe">Guadeloupe</option>
                            <option value="Guam">Guam</option>
                            <option value="Guatemala">Guatemala</option>
                            <option value="Guinea">Guinea</option>
                            <option value="Guinea-Bissau">Guinea-Bissau</option>
                            <option value="Guyana">Guyana</option>
                            <option value="Haiti">Haiti</option>
                            <option value="Honduras">Honduras</option>
                            <option value="Hong Kong">Hong Kong</option>
                            <option value="Hungary">Hungary</option>
                            <option value="Iceland">Iceland</option>
                            <option value="India">India</option>
                            <option value="Indonesia">Indonesia</option>
                            <option value="Iran">Iran</option>
                            <option value="Iraq">Iraq</option>
                            <option value="Ireland">Ireland</option>
                            <option value="Israel">Israel</option>
                            <option value="Italy">Italy</option>
                            <option value="Jamaica">Jamaica</option>
                            <option value="Japan">Japan</option>
                            <option value="Jordan">Jordan</option>
                            <option value="Kazakhstan">Kazakhstan</option>
                            <option value="Kenya">Kenya</option>
                            <option value="Kiribati">Kiribati</option>
                            <option value="Korea (North)">Korea (North)</option>
                            <option value="Korea (South)">Korea (South)</option>
                            <option value="Kuwait">Kuwait</option>
                            <option value="Kyrgyzstan">Kyrgyzstan</option>
                            <option value="Laos">Laos</option>
                            <option value="Latvia">Latvia</option>
                            <option value="Lebanon">Lebanon</option>
                            <option value="Lesotho">Lesotho</option>
                            <option value="Liberia">Liberia</option>
                            <option value="Libya">Libya</option>
                            <option value="Liechtenstein">Liechtenstein</option>
                            <option value="Lithuania">Lithuania</option>
                            <option value="Luxembourg ">Luxembourg </option>
                            <option value="Macau">Macau</option>
                            <option value="Macedonia">Macedonia</option>
                            <option value="Madagascar">Madagascar</option>
                            <option value="Malawi">Malawi</option>
                            <option value="Malaysia">Malaysia</option>
                            <option value="Maldives">Maldives</option>
                            <option value="Mali">Mali</option>
                            <option value="Malta">Malta</option>
                            <option value="Marshall Islands">Marshall Islands</option>
                            <option value="Martinique">Martinique</option>
                            <option value="Mauritania">Mauritania</option>
                            <option value="Mauritius">Mauritius</option>
                            <option value="Mayotte">Mayotte</option>
                            <option value="Mexico">Mexico</option>
                            <option value="Micronesia">Micronesia</option>
                            <option value="Moldova">Moldova</option>
                            <option value="Monaco">Monaco</option>
                            <option value="Mongolia">Mongolia</option>
                            <option value="Montserrat">Montserrat</option>
                            <option value="Morocco">Morocco</option>
                            <option value="Mozambique">Mozambique</option>
                            <option value="Myanmar">Myanmar</option>
                            <option value="Namibia">Namibia</option>
                            <option value="Nauru">Nauru</option>
                            <option value="Nepal">Nepal</option>
                            <option value="Netherlands">Netherlands</option>
                            <option value="Netherlands Antilles">Netherlands Antilles</option>
                            <option value="New Caledonia">New Caledonia</option>
                            <option value="New Zealand">New Zealand</option>
                            <option value="Nicaragua">Nicaragua</option>
                            <option value="Niger">Niger</option>
                            <option value="Nigeria">Nigeria</option>
                            <option value="Niue">Niue</option>
                            <option value="Norfolk Island">Norfolk Island</option>
                            <option value="Northern Marianas">Northern Marianas</option>
                            <option value="Norway">Norway</option>
                            <option value="Oman">Oman</option>
                            <option value="Pakistan">Pakistan</option>
                            <option value="Palau">Palau</option>
                            <option value="Palestinian Settlements">Palestinian Settlements</option>
                            <option value="Panama">Panama</option>
                            <option value="Papua New Guinea">Papua New Guinea</option>
                            <option value="Paraguay">Paraguay</option>
                            <option value="Peru">Peru</option>
                            <option value="Philippines">Philippines</option>
                            <option value="Poland">Poland</option>
                            <option value="Portugal">Portugal</option>
                            <option value="Puerto Rico">Puerto Rico</option>
                            <option value="Qatar">Qatar</option>
                            <option value="R">R</option>
                            <option value="Romania">Romania</option>
                            <option value="Russia">Russia</option>
                            <option value="Rwanda">Rwanda</option>
                            <option value="S">S</option>
                            <option value="Saint Helena">Saint Helena</option>
                            <option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
                            <option value="Saint Lucia">Saint Lucia</option>
                            <option value="Saint Pierre and Miquelon">Saint Pierre and Miquelon</option>
                            <option value="Saint Vincent and Grenadines">Saint Vincent and Grenadines</option>
                            <option value="Samoa">Samoa</option>
                            <option value="San Marino">San Marino</option>
                            <option value="Saudi Arabia">Saudi Arabia</option>
                            <option value="Senegal">Senegal</option>
                            <option value="Serbia">Serbia</option>
                            <option value="Seychelles">Seychelles</option>
                            <option value="Sierra Leone">Sierra Leone</option>
                            <option value="Singapore">Singapore</option>
                            <option value="Slovakia">Slovakia</option>
                            <option value="Slovenia">Slovenia</option>
                            <option value="Solomon Islands">Solomon Islands</option>
                            <option value="Somalia">Somalia</option>
                            <option value="South Africa">South Africa</option>
                            <option value="Spain">Spain</option>
                            <option value="Sri Lanka">Sri Lanka</option>
                            <option value="Sudan">Sudan</option>
                            <option value="Suriname">Suriname</option>
                            <option value="Swaziland">Swaziland</option>
                            <option value="Sweden">Sweden</option>
                            <option value="Switzerland">Switzerland</option>
                            <option value="Syria">Syria</option>
                            <option value="Taiwan">Taiwan</option>
                            <option value="Tajikistan">Tajikistan</option>
                            <option value="Tanzania">Tanzania</option>
                            <option value="Thailand">Thailand</option>
                            <option value="Togo">Togo</option>
                            <option value="Tokelau">Tokelau</option>
                            <option value="Tonga">Tonga</option>
                            <option value="Trinidad and Tobago">Trinidad and Tobago</option>
                            <option value="Tunisia">Tunisia</option>
                            <option value="Turkey">Turkey</option>
                            <option value="Turkmenistan">Turkmenistan</option>
                            <option value="Turks and Caicos Islands">Turks and Caicos Islands</option>
                            <option value="Tuvalu">Tuvalu</option>
                            <option value="US Virgin Islands">US Virgin Islands</option>
                            <option value="Uganda">Uganda</option>
                            <option value="Ukraine">Ukraine</option>
                            <option value="United Arab Emirates">United Arab Emirates</option>
                            <option value="United Kingdom">United Kingdom</option>
                            <option value="Uruguay">Uruguay</option>
                            <option value="Uzbekistan">Uzbekistan</option>
                            <option value="Vanuatu">Vanuatu</option>
                            <option value="Venezuela">Venezuela</option>
                            <option value="Vietnam">Vietnam</option>
                            <option value="Virgin Islands">Virgin Islands</option>
                            <option value="Wake Island">Wake Island</option>
                            <option value="Wallis and Futuna">Wallis and Futuna</option>
                            <option value="Yemen">Yemen</option>
                            <option value="Zambia">Zambia</option>
                            <option value="Zimbabwe">Zimbabwe</option>
                        </select>
                        <span class="customSelect selectbox required" style="display: inline-block;">
                            <span class="customSelectInner" style="width: 165px; display: inline-block;">India</span>
                        </span>
                        <label for="countryregion_1" generated="true" class="error" id="countryregion_1err" style="">
                        </label>
                    </div>
                    <div class="row">
                        <div class="label">Address</div>
                        <input type="text" autocomplete="off" id="AddAddress_pop" name="AddAddress_pop" style="margin-bottom: 5px;" class="textbox">
                    </div>
                    <div class="row">
                        <div class="label">City</div>
                        <input type="text" autocomplete="off" id="city_pop" name="city_pop" style="margin-bottom: 5px;" class="textbox">
                    </div>
                    <div class="row">
                        <div class="label">State/Province</div>
                        <select id="state_pop" name="state_pop" style="margin-bottom: 5px; -webkit-appearance: menulist-button; width: 188px; position: absolute; opacity: 0; height: 28px; font-size: 12px;" class="selectbox hasCustomSelect">
                            <option value="">--Select State--</option>
                        </select>
                        <span class="customSelect selectbox" style="margin-bottom: 5px; display: inline-block;">
                            <span class="customSelectInner" style="width: 165px; display: inline-block;">--Select State--</span>
                        </span>
                    </div>
                    <div class="row">
                        <div class="label">Zip Code/Postal Code</div>
                        <input type="text" autocomplete="off" id="zipcode_pop" name="zipcode_pop" style="margin-bottom: 5px;" class="textbox">
                    </div>
                    <div class="row" style="margin-top:20px;">
                        <input id="Popup_Address_Cancel" style="margin-left:50px;margin-right: 50px" type="button" class="gen-btn-Gray" value="Cancel">
                        <input id="Popup_Address_OK" type="button" class="gen-btn-Orange" value="Ok">
                    </div>
                </div>
            </div>
        </form>
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
        <title>Insert title here</title>
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
                                <div class="ven_reg_left">
                                    <input type="text" autocomplete="off" id="vr_key">
                                    <div class="row">
                                        <label class="label"> Company Name </label>
                                        <input type="text" autocomplete="off" id="company_name_popup" name="company_name" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Branch </label>
                                        <input type="text" autocomplete="off" id="branch_0_popup" name="branch_0" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Country/Region </label>
                                        <!-- <select class="selectbox" name="countryregion_0" id="countryregion_0" style="width:165px;">
                                        <option> Select Country </option>
                                        </select> -->
                                        <input type="text" autocomplete="off" id="countryregion_0_popup" name="countryregion_0" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Address </label>
                                        <input type="text" autocomplete="off" id="address_popup" name="address" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> City </label>
                                        <input type="text" autocomplete="off" id="city_popup" name="city" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> State/Province </label>
                                        <!-- <select class="selectbox" name="state_0" id="state_0" style="width:165px;">
                                        <option> Select State </option>
                                        </select> -->
                                        <input type="text" autocomplete="off" id="state_0_popup" name="state_0" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Zip Code/Postal Code </label>
                                        <input type="text" autocomplete="off" id="zipcode_popup" name="zipcode" class="textbox_disable" disabled="">
                                    </div>
                                    <div style="height: 100px;" class="row" id="type">
                                        <div class="label">
                                            Type<span class="mandatory">
                                            </span>
                                        </div>
                                        <fieldset>
                                            <legend> Type </legend>
                                            <input type="radio" value="Individual" class="radiobtn required" id="internetuser_popup" name="comtype" style="vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;">
                                            <label style="vertical-align: middle; font-size: 12px; color: #282828; line-height:25px;" for="internetuser">
                                                Individual/Sole Proprietor &nbsp;</label>
                                            <br>
                                            <input type="radio" value="Corporation" style="vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;" class="radiobtn" name="comtype" id="transuser_popup">
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
                                        <input type="text" autocomplete="off" id="contact_name_popup" name="cname" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Contact Title/Department </label>
                                        <input type="text" autocomplete="off" id="titledept_popup" name="titledept" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Contact Email </label>
                                        <input type="text" autocomplete="off" id="email_popup" name="email" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Phone </label>
                                        <input type="text" autocomplete="off" id="phone_popup" name="phone" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Cell </label>
                                        <input type="text" autocomplete="off" id="cell_popup" name="cell" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Fax </label>
                                        <input type="text" autocomplete="off" id="fax_popup" name="fax" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Type of Business </label>
                                        <!-- <select class="selectbox" name="typeofbusiness" id="typeofbusiness" style="width:165px;">
                                        <option> Select Business Type </option>
                                        </select> -->
                                        <input type="text" autocomplete="off" id="typeofbusiness_popup" name="typeofbusiness" class="textbox_disable">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Business Category </label>
                                        <!-- <select class="selectbox" name="businesscategory" id="businesscategory" style="width:165px;">
                                        <option> Select Business Category </option>
                                        </select> -->
                                        <input type="text" autocomplete="off" id="businesscategory_popup" name="businesscategory" class="textbox_disable" disabled="">
                                    </div>
                                    <div class="row">
                                        <label class="label"> Business Tax ID </label>
                                        <input type="text" autocomplete="off" id="taxid_popup" name="taxid" class="textbox">
                                    </div>
                                    <div class="row">
                                        <label class="label"> NAICS Code </label>
                                        <div id="naicscode_popup_select_div">
                                            <select class="selectbox hasCustomSelect" name="naicscode_popup_select" id="naicscode_popup_select" style="width: 188px; -webkit-appearance: menulist-button; position: absolute; opacity: 0; height: 28px; font-size: 12px;">
                                                <option value="" selected="selected">-- Select NAICS Code --</option>
                                                <option value="111 - Crop Production ">111 - Crop Production </option>
                                                <option value="112 - Animal Production and Aquaculture ">112 - Animal Production and Aquaculture </option>
                                                <option value="113 - Forestry and Logging ">113 - Forestry and Logging </option>
                                                <option value="114 - Fishing, Hunting and Trapping">114 - Fishing, Hunting and Trapping</option>
                                                <option value="115 - Support Activities for Agriculture and Forestry">115 - Support Activities for Agriculture and Forestry</option>
                                                <option value="211 - Oil and Gas Extraction">211 - Oil and Gas Extraction</option>
                                                <option value="212 - Mining (except Oil and Gas)">212 - Mining (except Oil and Gas)</option>
                                                <option value="213 - Support Activities for Mining">213 - Support Activities for Mining</option>
                                                <option value="221 - Utilities ">221 - Utilities </option>
                                                <option value="236 - Construction of Buildings">236 - Construction of Buildings</option>
                                                <option value="237 - Heavy and Civil Engineering Construction">237 - Heavy and Civil Engineering Construction</option>
                                                <option value="238 - Specialty Trade Contractors">238 - Specialty Trade Contractors</option>
                                                <option value="311 - Food Manufacturing">311 - Food Manufacturing</option>
                                                <option value="312 - Beverage and Tobacco Product Manufacturing">312 - Beverage and Tobacco Product Manufacturing</option>
                                                <option value="313 - Textile Mills">313 - Textile Mills</option>
                                                <option value="314 - Textile Product Mills">314 - Textile Product Mills</option>
                                                <option value="315 - Apparel Manufacturing">315 - Apparel Manufacturing</option>
                                                <option value="316 - Leather and Allied Product Manufacturing">316 - Leather and Allied Product Manufacturing</option>
                                                <option value="321 - Wood Product Manufacturing">321 - Wood Product Manufacturing</option>
                                                <option value="322 - Paper Manufacturing">322 - Paper Manufacturing</option>
                                                <option value="323 - Printing and Related Support Activities">323 - Printing and Related Support Activities</option>
                                                <option value="324 - Petroleum and Coal Products Manufacturing">324 - Petroleum and Coal Products Manufacturing</option>
                                                <option value="325 - Chemical Manufacturing">325 - Chemical Manufacturing</option>
                                                <option value="326 - Plastics and Rubber Products Manufacturing">326 - Plastics and Rubber Products Manufacturing</option>
                                                <option value="327 - Nonmetallic Mineral Product Manufacturing">327 - Nonmetallic Mineral Product Manufacturing</option>
                                                <option value="331 - Primary Metal Manufacturing">331 - Primary Metal Manufacturing</option>
                                                <option value="332 - Fabricated Metal Product Manufacturing">332 - Fabricated Metal Product Manufacturing</option>
                                                <option value="333 - Machinery Manufacturing">333 - Machinery Manufacturing</option>
                                                <option value="334 - Computer and Electronic Product Manufacturing">334 - Computer and Electronic Product Manufacturing</option>
                                                <option value="335 - Electrical Equipment, Appliance, and Component Manufacturing">335 - Electrical Equipment, Appliance, and Component Manufacturing</option>
                                                <option value="336 - Transportation Equipment Manufacturing">336 - Transportation Equipment Manufacturing</option>
                                                <option value="337 - Furniture and Related Product Manufacturing">337 - Furniture and Related Product Manufacturing</option>
                                                <option value="339 - Miscellaneous Manufacturing">339 - Miscellaneous Manufacturing</option>
                                                <option value="423 - Merchant Wholesalers, Durable Goods ">423 - Merchant Wholesalers, Durable Goods </option>
                                                <option value="424 - Merchant Wholesalers, Nondurable Goods ">424 - Merchant Wholesalers, Nondurable Goods </option>
                                                <option value="425 - Wholesale Electronic Markets and Agents and Brokers ">425 - Wholesale Electronic Markets and Agents and Brokers </option>
                                                <option value="441 - Motor Vehicle and Parts Dealers ">441 - Motor Vehicle and Parts Dealers </option>
                                                <option value="442 - Furniture and Home Furnishings Stores ">442 - Furniture and Home Furnishings Stores </option>
                                                <option value="443 - Electronics and Appliance Stores ">443 - Electronics and Appliance Stores </option>
                                                <option value="444 - Building Material and Garden Equipment and Supplies Dealers ">444 - Building Material and Garden Equipment and Supplies Dealers </option>
                                                <option value="445 - Food and Beverage Stores ">445 - Food and Beverage Stores </option>
                                                <option value="446 - Health and Personal Care Stores ">446 - Health and Personal Care Stores </option>
                                                <option value="447 - Gasoline Stations ">447 - Gasoline Stations </option>
                                                <option value="448 - Clothing and Clothing Accessories Stores ">448 - Clothing and Clothing Accessories Stores </option>
                                                <option value="451 - Sporting Goods, Hobby, Musical Instrument, and Book Stores ">451 - Sporting Goods, Hobby, Musical Instrument, and Book Stores </option>
                                                <option value="452 - General Merchandise Stores ">452 - General Merchandise Stores </option>
                                                <option value="453 - Miscellaneous Store Retailers ">453 - Miscellaneous Store Retailers </option>
                                                <option value="454 - Nonstore Retailers ">454 - Nonstore Retailers </option>
                                                <option value="481 - Air Transportation">481 - Air Transportation</option>
                                                <option value="482 - Rail Transportation">482 - Rail Transportation</option>
                                                <option value="483 - Water Transportation">483 - Water Transportation</option>
                                                <option value="484 - Truck Transportation">484 - Truck Transportation</option>
                                                <option value="485 - Transit and Ground Passenger Transportation">485 - Transit and Ground Passenger Transportation</option>
                                                <option value="486 - Pipeline Transportation">486 - Pipeline Transportation</option>
                                                <option value="487 - Scenic and Sightseeing Transportation">487 - Scenic and Sightseeing Transportation</option>
                                                <option value="488 - Support Activities for Transportation">488 - Support Activities for Transportation</option>
                                                <option value="491 - Postal Service">491 - Postal Service</option>
                                                <option value="492 - Couriers and Messengers">492 - Couriers and Messengers</option>
                                                <option value="493 - Warehousing and Storage">493 - Warehousing and Storage</option>
                                                <option value="511 - Publishing Industries (except Internet)">511 - Publishing Industries (except Internet)</option>
                                                <option value="512 - Motion Picture and Sound Recording Industries">512 - Motion Picture and Sound Recording Industries</option>
                                                <option value="515 - Broadcasting (except Internet)">515 - Broadcasting (except Internet)</option>
                                                <option value="517 - Telecommunications">517 - Telecommunications</option>
                                                <option value="518 - Data Processing, Hosting, and Related Services">518 - Data Processing, Hosting, and Related Services</option>
                                                <option value="519 - Other Information Services">519 - Other Information Services</option>
                                                <option value="521 - Monetary Authorities-Central Bank">521 - Monetary Authorities-Central Bank</option>
                                                <option value="522 - Credit Intermediation and Related Activities">522 - Credit Intermediation and Related Activities</option>
                                                <option value="523 - Securities, Commodity Contracts, and Other Financial Investments and Related Activities">523 - Securities, Commodity Contracts, and Other Financial Investments and Related Activities</option>
                                                <option value="524 - Insurance Carriers and Related Activities">524 - Insurance Carriers and Related Activities</option>
                                                <option value="525 - Funds, Trusts, and Other Financial Vehicles ">525 - Funds, Trusts, and Other Financial Vehicles </option>
                                                <option value="531 - Real Estate">531 - Real Estate</option>
                                                <option value="532 - Rental and Leasing Services">532 - Rental and Leasing Services</option>
                                                <option value="533 - Lessors of Nonfinancial Intangible Assets (except Copyrighted Works)">533 - Lessors of Nonfinancial Intangible Assets (except Copyrighted Works)</option>
                                                <option value="541 - Professional, Scientific, and Technical Services">541 - Professional, Scientific, and Technical Services</option>
                                                <option value="551 - Management of Companies and Enterprises">551 - Management of Companies and Enterprises</option>
                                                <option value="561 - Administrative and Support Services">561 - Administrative and Support Services</option>
                                                <option value="562 - Waste Management and Remediation Services">562 - Waste Management and Remediation Services</option>
                                                <option value="611 - Educational Services">611 - Educational Services</option>
                                                <option value="621 - Ambulatory Health Care Services">621 - Ambulatory Health Care Services</option>
                                                <option value="622 - Hospitals">622 - Hospitals</option>
                                                <option value="623 - Nursing and Residential Care Facilities">623 - Nursing and Residential Care Facilities</option>
                                                <option value="624 - Social Assistance">624 - Social Assistance</option>
                                                <option value="711 - Performing Arts, Spectator Sports, and Related Industries">711 - Performing Arts, Spectator Sports, and Related Industries</option>
                                                <option value="712 - Museums, Historical Sites, and Similar Institutions">712 - Museums, Historical Sites, and Similar Institutions</option>
                                                <option value="713 - Amusement, Gambling, and Recreation Industries">713 - Amusement, Gambling, and Recreation Industries</option>
                                                <option value="721 - Accommodation">721 - Accommodation</option>
                                                <option value="722 - Food Services and Drinking Places">722 - Food Services and Drinking Places</option>
                                                <option value="811 - Repair and Maintenance">811 - Repair and Maintenance</option>
                                                <option value="812 - Personal and Laundry Services">812 - Personal and Laundry Services</option>
                                                <option value="813 - Religious, Grantmaking, Civic, Professional, and Similar Organizations">813 - Religious, Grantmaking, Civic, Professional, and Similar Organizations</option>
                                                <option value="814 - Private Households">814 - Private Households</option>
                                                <option value="921 - Executive, Legislative, and Other General Government Support ">921 - Executive, Legislative, and Other General Government Support </option>
                                                <option value="922 - Justice, Public Order, and Safety Activities ">922 - Justice, Public Order, and Safety Activities </option>
                                                <option value="923 - Administration of Human Resource Programs ">923 - Administration of Human Resource Programs </option>
                                                <option value="924 - Administration of Environmental Quality Programs ">924 - Administration of Environmental Quality Programs </option>
                                                <option value="925 - Administration of Housing Programs, Urban Planning, and Community Development ">925 - Administration of Housing Programs, Urban Planning, and Community Development </option>
                                                <option value="926 - Administration of Economic Programs ">926 - Administration of Economic Programs </option>
                                                <option value="927 - Space Research and Technology ">927 - Space Research and Technology </option>
                                                <option value="928 - National Security and International Affairs ">928 - National Security and International Affairs </option>
                                            </select>
                                            <span class="customSelect selectbox" style="width: 165px; display: inline-block;">
                                                <span class="customSelectInner" style="width: 165px; display: inline-block;">-- Select NAICS Code --</span>
                                            </span>
                                        </div>
                                        <input type="text" autocomplete="off" id="naicscode_popup_text" name="naicscode_popup_text" class="textbox">
                                    </div>
                                    <div id="w9form_upload_ctrl">
                                        <div class="row">
                                            <label class="label">
                                            </label>
                                            <div class="checkContainer" style="margin-right:5px;">
                                                <input type="checkbox" name="w9form_flag_popup" id="w9form_flag_popup" onChange="w9checkBoxPopupClicked()">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="w9form_flag_popup" style="width:300px !important;line-height:12px;font-family:Verdana;font-size:10px;"> W9 tax form will be submitted<br> to complete the registration process
                                            </label>
                                        </div>
                                        <div class="row" id="w9Form_upload_popup_btn" style="display:none">
                                            <label class="label"> W9 Tax Form </label>
                                            <input type="file" name="popup_w9form" id="popup_w9form">
                                        </div>
                                    </div>
                                    <div id="w9form_download_ctrl">
                                        <div class="row">
                                            <a onClick="downloadW9form()" id="w9form_link" style="font-weight:underline;color:blue;"> Click here to download the W9Form</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="buss_size_container">
                                    <div class="side_heading"> Business Size ( Select One )
                                    </div>
                                    <div class="buss_size_large">
                                        <div class="checkContainer">
                                            <input type="radio" name="buss_size_popup" id="buss_large_popup" value="Large">
                                            <div>
                                            </div>
                                        </div>
                                        <label class="des_text" for="buss_large_popup">
                                            <b>Large:<br>
                                            </b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A domestic concren which, including domestic and foreign divisions and affiliates, normally 
                                            employs 500 or more persons, is independently -or- publicly-owned or controlled and operated, 
                                            and which may be division of another domestic or foreign concern.</label>
                                    </div>
                                    <div class="buss_size_small">
                                        <div class="checkContainer">
                                            <input type="radio" name="buss_size_popup" id="buss_small_popup" value="Small">
                                            <div>
                                            </div>
                                        </div>
                                        <label class="des_text" for="buss_small_popup">
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
                                        <input type="checkbox" name="Disadvantaged" id="Disadvantaged_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="Disadvantaged_popup">
                                        <b>Disadvantaged:</b> In addition to meeting the requirements of the FAR definition, 
                                        any business classfying them selves as Disadvantaged must be certified by the Small Business 
                                        Administration and have that certification in good standing.
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="HubZone" id="HubZone_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="HubZone_popup">
                                        <b>HUBZone:</b> In addition to meeting the requirements of the FAR definition, any business
                                        classifying themselves as a HUBZ one must be certified by the small Business Administraiotn and
                                        have that certification in good standing.
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="WomenOwned" id="WomenOwned_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="WomenOwned_popup">Women-Owned
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="HandicappedOwned" id="HandicappedOwned_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="HandicappedOwned_popup">handicapped-Owned
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="VETERANOWNED" id="VETERANOWNED_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="VETERANOWNED_popup">Veteran-Owned
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="SDVETERANOWNED" id="SDVETERANOWNED_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="SDVETERANOWNED_popup">Service-Disabled Veteran-Owned
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="HBCORMI" id="HBCORMI_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="HBCORMI_popup">Historically-Back College or Minority Institution
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="MBE" id="MBE_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="MBE_popup">Minority Business Enterprise
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="NonProfit" id="NonProfit_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="NonProfit_popup">
                                        <b>Non-Profit:</b> Any business or organization that has received non-profit status
                                        under IRs Regulation 501(c)(3).
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="Foreign" id="Foreign_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="Foreign_popup">
                                        <b>Foreign:</b> A concern which is not incorporated in the United States or an 
                                        unincorporated concern having its principle place of business outside the United States.
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="PublicSector" id="PublicSector_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <!-- <input type="checkbox" name="buss_small" id="buss_small" /> -->
                                    <label class="des_text" for="PublicSector_popup">
                                        <b>Public Sector:</b> An agency of the Federal or State Government, or a local municipality.
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="ANCORITNSB" id="ANCORITNSB_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="ANCORITNSB_popup">Alaska Native Corporations Indian Tripe that is not a small business.
                                    </label>
                                    <div class="checkContainer">
                                        <input type="checkbox" name="ANCORITNCD" id="ANCORITNCD_popup">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="des_text" for="ANCORITNCD_popup">
                                        <b>Alaska Native Corporations or Indian Tribe:</b> Not certified by the Small Business Administration as disadvantaged.
                                    </label>
                                </div>
                                <div class="addl_det_container">
                                    <div class="side_heading"> Additional Details
                                    </div>
                                    <textarea class="addr_det_text" id="additional_det_popup">
                                    </textarea>
                                </div>
                                <div class="addl_det_container" id="inquiry_details" style="display:none;">
                                </div>
                                <div class="addl_det_container" style="display:none;" id="add_inquiry_content">
                                    <div class="inquire_row" style="margin-bottom:20px;">
                                        <label class="inquire_by"> Inquire: </label>
                                        <textarea class="inquire_det" id="new_inquire_message">
                                        </textarea>
                                    </div>
                                    <input type="button" class="gen-btn-Orange" style=" margin-left:300px;" value="Cancel" id="cancel_btn">
                                    <input type="button" class="gen-btn-Orange" style="margin-left:30px;" value="Send" id="save_btn" onclick="showLoading()">
                                </div>
                                <div class="ven_reg_btns" id="cntrl_btns" style="width:630px;">
                                    <input type="button" class="gen-btn-Orange" style="margin-right:50px;" value="Cancel" id="cancel_regn_btn">
                                    <input type="button" class="gen-btn-Orange" style="margin-right:50px;" value="Reject" id="reject_regn_btn">
                                    <input type="button" class="gen-btn-Orange" style="margin-right:50px;" value="Accept" id="send_regn_btn">
                                    <input type="button" class="gen-btn-Orange" style="width:auto!important;padding-left:10px!important;padding-right:10px!important;" value="Respond to Inquiry" id="inquire_regn_btn">
                                </div>
                                <div class="ven_reg_btns" id="cntrl_btns_ok">
                                    <input type="button" class="gen-btn-Orange" style="margin-right:50px;" value="ok" onClick="$('#supplier_form_popup').hide();">
                                </div>
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
<%@page import="core.login.SessionData"%>
<%@page import="core.regn.MailingAddressData,core.companyprofile.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" import="java.util.*" import="utils.*" import="java.text.NumberFormat" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">




        <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Registration/css/companyProfile.css" />

        <title>Company Profile</title>



    </head>

    <jsp:useBean id="cmpProfile" scope="request"  class="core.companyprofile.CompanyProfileData" />
    <%
        System.out.println("mailingdata count = " + cmpProfile.mailingAddressArr_.size());
        MailingAddressData firstMailList = cmpProfile.mailingAddressArr_.get(0);
        Calendar lCDateTime = Calendar.getInstance();
       SessionData sessionObjp = (SessionData)session.getAttribute("UserSessionData");
      sessionObjp.pypl_scs=lCDateTime.getTimeInMillis()*2+"";
      Calendar lCDateTime2 = Calendar.getInstance();
      sessionObjp.pypl_fl=lCDateTime2.getTimeInMillis()+""; 
//out.print("sessionObjp.pypl_scs"+sessionObjp.pypl_scs+"<br/>"+"sessionObjp.pypl_fl"+sessionObjp.pypl_fl);
//        
//        rn.get_fail();
    %>
    <body>
        <form name="paypalForm" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post" target="_blank">
            <input type="hidden" name="cmd" value="_xclick" />
            <input type="hidden" name="business" value="dsdilbag345@gmail.com" />
            <input type="hidden" name="password" value="dilbagdssingh" />
            <input type="hidden" name="custom" value="1123" />
            <input type="hidden" name="item_name" value="Plan" />
            <input type="hidden" id="amnt" name="amount" value="0"/>
            <input type="hidden" name="rm" value="1" />
            <input type="hidden" id="rtrn" name="return" value="<%=AppConfigReader.instance( ).get( "JSP_PATH" ) %>/paypal?result=<%=sessionObjp.pypl_scs %>" />
            <input type="hidden" id="cncl_rtrn" name="cancel_return" value="<%=AppConfigReader.instance( ).get( "JSP_PATH" ) %>/paypal?result=<%=sessionObjp.pypl_fl %>" />
            <input type="hidden" name="cert_id" value="API Singature" />                                    
        </form>

        <%!
            public String ConvertDoubleToCurrency(double money) {
                String moneyString = "$";
                NumberFormat formatter = NumberFormat.getCurrencyInstance();
                moneyString += formatter.format(money);
                if (moneyString.endsWith(".00")) {
                    int centsIndex = moneyString.lastIndexOf(".00");
                    if (centsIndex != -1) {
                        moneyString = moneyString.substring(1, centsIndex);
                    }
                }
                return moneyString;
            }

                                            %>
        <%
            if (firstMailList.state_ == null) {
                firstMailList.state_ = "";
            }

        %>

        <div class="pagetitlecontainer">
            <div class="pagetitle">Company Profile</div>
        </div>

        <div id="sv_dtl_cnfrmtn_msg_dv" class="update_user_detail2" onclick="$('#sv_dtl_cnfrmtn_msg_dv').fadeOut();" style="width:270px;">Company profile has been updated</div>
        <div class="page">
            <form method="post" id="companyProfile" name="companyProfile" class="contentcontainer">		
                <div class="content cpcontent" >
                    <div style="float:left;margin-bottom:50px;">
                        <div class="profileContent">			
                            <div class="Sub_Heading">
                                Profile
                            </div>

                            <input type="hidden" id="hidden_business_cat" value="<%= cmpProfile.businessCategory_%>">
                            <input type="hidden" id="hidden_state" value="<%= firstMailList.state_%>">
                            <input type="hidden" id="hidden_country" value="<%= firstMailList.countryRegion_%>">
                            <input type="hidden" id="hidden_branch" value="<%= firstMailList.addressType_%>">
                            <div>
                                <div class="label">
                                    Company Name
                                </div>
                                <input type="text" readonly id="companyname" value='<jsp:getProperty property="companyName_" name="cmpProfile"/>' 	name="companyname" class="required textbox"> 
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
                                           class="radiobtnlbl" /> <label for="Bothradio"
                                           class=' radiobtnlbl'>Buyers/Suppliers</label>&nbsp;
                                </div>
                            </div-->
                            <input type="hidden" name="signupas" value="<jsp:getProperty property="businessType_" name="cmpProfile"/>" />
                            <div>
                                <div class="label">
                                    Company Logo
                                </div>
                                <input type="file" class="logofile"  style="font-size: 12px;" name="complogo" id="complogo"
                                       title="The logo dimensions should be at most 500x500 pixels in size should not exceed 1 MB.  
                                       Supported formats are jpg, png, bmp, gif and tiff." />  
                            </div>

                            <div>
                                <div     class="label">
                                    Business Category
                                </div>
                                <select onchange="slctd_ctgry(this.value);" name="businesscategory"  id="businesscategory" class="required selectbox" style="width:165px;">							
                                </select> 
                            </div>
                            <div id="ctgry_lstng">
                                <input type="hidden" name="businesscategory2" id="businesscategory2"/>
                                <div class="label">
                                    Selected Category
                                </div>
                                <ul class="textbox" id="slctd_ctgry_lst" style="list-style: none;height: auto;width: auto;"></ul>
                            </div>
                            <div>
                                <div class="label">
                                    Company Id
                                </div>
                                <input type="text" name="companyid" id="companyid" readonly value='<jsp:getProperty property="companyID_" name="cmpProfile"/>' class="textbox required" />
                            </div>
                            <div>
                                <div class="label">
                                    Branch
                                </div>
                                <select id="branch_0" name="branch_0_value" class="selectbox">							
                                </select>
                            </div>
                            <div>
                                <div class="label">
                                    Country
                                </div>
                                <select id="countryregion_0" name="countryregion_0_value" class="selectbox required" onchange="fetchState(this.id);
                                        changeDropDown(this.value)">							
                                </select>
                            </div>

                            <div style="height: 95px">
                                <div class="label">
                                    Address
                                </div>
                                <textarea rows="4"  id="address_0" name="address_0_value" style="margin-bottom: 5px;resize:none;width:175px;" class="textarea"><%= firstMailList.address_.trim()%></textarea>
                            </div>

                            <div>
                                <div class="label">
                                    City
                                </div>
                                <input type="text" value="<%= firstMailList.city_%>" id="city_0" name="city_<%= firstMailList.addrid_%>" style="margin-bottom: 5px;" class="textbox">
                            </div>
                            <div>
                                <div class="label" id="zipcode">
                                    Zip Code/Postal Code
                                </div>
                                <input type="text" value="<%= firstMailList.zipcode_%>" id="zipcode_0" name="zipcode_0_value" style="margin-bottom: 5px;" class="textbox">
                            </div>
                            <div>
                                <div class="label">
                                    State/Province
                                </div>


                                <% if (firstMailList.countryRegion_.equals("United States")) {
                                %>
                                <div id="select_0_container">
                                    <select id="state_0" name="state_0_value" style="margin-bottom: 5px;" class="selectbox valid" ></select></div>
                                <input style="display:none;margin-left:225px;margin-top:-32px;"   type="text" name="state_text" class="textbox" id="state_text_0" >
                                <%} else {
                                %> 
                                <div id="select_0_container" style="display:none;">
                                    <select id="state_0" name="state_0_value" style="margin-bottom: 5px;" class="selectbox valid" ></select></div>
                                <input style="display:block;margin-left:225px;margin-top:-32px;"   type="text" name="state_text" class="textbox" id="state_text_0" 
                                       value="<%= firstMailList.state_%>" >
                                <%}%>



                            </div>
                            <table>
                                <tr>
                                    <td align="left" style="vertical-align: top;">
                                        <div class="label" style="width: 196px">
                                            Add Address <br />
                                            <input style="display: none;" id="cmpKey" value="<%= cmpProfile.companyRegnKey_%>" />
                                            <%
                                                int nofAddress = cmpProfile.mailingAddressArr_.size();
                                                if (nofAddress < 2) {
                                            %>
                                            <input style="display: block;" type="button" name="addAddress" id="addAddress" class="addAddress" />
                                            <%
                                            } else {
                                            %>
                                            <input style="display: block;" type="button" name="addAddress" id="addAddress" class="addAddress" />
                                            <%
                                                }
                                            %>

                                        </div>
                                    </td>
                                    <td align="left" style="vertical-align: top;">
                                        <div id="addressExpandContainer">

                                            <div id="addressExpander"></div>								
                                            <%
                                                if (cmpProfile.mailingAddressArr_.size() < 2) {
                                            %>

                                            <input style="display: block;position: absolute;height: 30px;" type="button" id="addAddressEmpty" class="addAddressEmpty" />

                                            <%
                                                }
                                            %>							    
                                        </div>
                                    </td>
                                    <td id="AddressList">
                                        <%
                                            int i = 0;

                                            List<MailingAddressData> maillList = cmpProfile.mailingAddressArr_;

                                            for (MailingAddressData mailldata : maillList) {

                                                if (i != 0) {
                                        %>

                                        <div class="AddressContiner">									
                                            <fieldset>
                                                <legend> <%= mailldata.addressType_%></legend>
                                                <div class="AddressDetails">
                                                    <%= mailldata.address_%> , <%= mailldata.city_%>-<%= mailldata.zipcode_%>,<%= mailldata.state_%>, <%= mailldata.countryRegion_%>
                                                </div>
                                            </fieldset>
                                            <input type="button" name="removeaddressbtn_<%= mailldata.addrid_%>" class="removeaddress" onclick="removeCompanyAddress(this);">					

                                        </div>

                                        <%
                                                }

                                                i++;
                                            }

                                        %>			
                                    </td>
                                </tr>
                            </table>



                        </div>

                        <div class="ContenSeparator"> </div>

                        <div class="pricingContent">	
                            <div class="Sub_Heading">		
                                Pricing Plan
                            </div>

                            <!-- plan -->
                            <div class="row" style="height:40px;">
                                <label class="keylbl"> Plan
                                </label>
                                <label class="valuelbl">:&nbsp;&nbsp;<jsp:getProperty property="pricingOption_" name="cmpProfile"/>
                                </label>
                            </div>


                            <!-- Maximum number of employees -->
                            <div class="row" style="height:30px;">
                                <label class="keylbl"> Maximum number of employee licenses
                                </label>
                                <% double maxEmp = cmpProfile.maxEmployee_;
                                    if (maxEmp == 0) {
                                %>
                                <label class="valuelbl">:&nbsp;&nbsp;Unlimited</label>

                                <% } else {
                                %>

                                <label class="valuelbl">:&nbsp;&nbsp;<jsp:getProperty property="maxEmployee_" name="cmpProfile"/>
                                </label>

                                <% } %>

                            </div>



                            <!-- registered employees -->

                            <div class="row" style="height:40px;">
                                <label class="keylbl"> Number of employees registered
                                </label>
                                <label class="valuelbl">:&nbsp;&nbsp;<jsp:getProperty property="registeredEmployee_" name="cmpProfile"/>
                                </label>
                            </div>

                            <!-- max tras vol-->

                            <div class="row" style="height:30px;">
                                <label class="keylbl">Maximum transaction volume per month
                                </label>

                                <% double maxTrans = cmpProfile.maxEmployee_;
                                    if (maxEmp == 0) {
                                %>
                                <label class="valuelbl">:&nbsp;&nbsp;Unlimited</label>

                                <% } else {
                                %>

                                <label class="valuelbl">:&nbsp;&nbsp;<%=ConvertDoubleToCurrency(cmpProfile.maxTransaction_)%>
                                </label>

                                <% }%>


                            </div>

                            <!-- transaction volume per month -->

                            <div class="row" style="height:30px;">
                                <label class="keylbl"> Transaction volume per month
                                </label>
                                <label class="valuelbl">:&nbsp;&nbsp;<%=ConvertDoubleToCurrency(cmpProfile.thisMonthTrasaction_)%>
                                </label>
                            </div>


                            <!-- Remaining transaction for this month -->

                            <div class="row" style="height:50px;">
                                <label class="keylbl"> Balance transaction allowed for this month
                                </label>
                                <label class="valuelbl">:&nbsp;&nbsp;<%= ConvertDoubleToCurrency(cmpProfile.maxTransaction_ - cmpProfile.thisMonthTrasaction_)%>
                                </label>
                            </div>




                            <div class="Sub_Heading">		
                                Disk Quota
                            </div>

                            <div class="row" style="height:30px;">
                                <% double usedSpacePer = (cmpProfile.spaceOccupied_ / cmpProfile.totalSpace_) * 100;

                                    if (usedSpacePer > 80.0f) {%>
                                <label class="keylbl"><span style="color:red;"><%=String.format("%.2f", usedSpacePer)%>%</span> of disk space used</label>
                                <%
                                } else {
                                %>
                                <label class="keylbl"><span style="color:green;"><%=String.format("%.2f", usedSpacePer)%>%</span> of disk space used</label>
                                <%
                                    }
                                %>

                            </div>

                            <div class="row">
                                <label class="keylbl"> Used <span style="color:#f37d01;"><%=String.format("%.2f", cmpProfile.spaceOccupied_)%>MB</span>
                                    of your <span style="color:#f37d01;"><%=String.format("%.2f", cmpProfile.totalSpace_)%>MB </span>disk space
                                </label>
                            </div>

                            <div class="Sub_Heading">		
                                Payment Options
                            </div>

                            <div>
                                <div class="pricinglable">
                                    Update Plan : 
                                    <% if (cmpProfile.pricingOption_.equalsIgnoreCase("Basic")) {%>
                                    <input type="radio" id="Basic" name="plan" value="Basic" Checked class="radiobtn required" onclick="$('#amnt').val('0');$('#rtrn').val($('#rtrn').val()+'Basic');">
                                    <% } else { %>
                                    <input type="radio" id="Basic" name="plan" value="Basic" class="radiobtn required" onclick="$('#amnt').val('0');$('#rtrn').val($('#rtrn').val()+'Basic');">
                                    <%}%>
                                    <label for="Basic" >Basic </label>
                                    <% if (cmpProfile.pricingOption_.equalsIgnoreCase("Plus")) {%>
                                    <input type="radio" id="Plus" name="plan" value="Plus" Checked class="radiobtn required" onclick="$('#amnt').val('49');$('#rtrn').val($('#rtrn').val()+'Plus');">
                                    <% } else { %>
                                    <input type="radio" id="Plus" name="plan" value="Plus" class="radiobtn required" onclick="$('#amnt').val('49');$('#rtrn').val($('#rtrn').val()+'Plus');">
                                    <%}%>
                                    <label for="Plus" >Plus </label>
                                    <% if (cmpProfile.pricingOption_.equalsIgnoreCase("Premium")) {%>
                                    <input type="radio" id="Premium" name="plan" value="Premium" Checked  class="radiobtn required" onclick="$('#amnt').val('99');$('#rtrn').val($('#rtrn').val()+'Premium');">
                                    <% } else { %>
                                    <input type="radio" id="Premium" name="plan" value="Premium"  class="radiobtn required" onclick="$('#amnt').val('99');$('#rtrn').val($('#rtrn').val()+'Premium');">
                                    <%}%>
                                    <label for="Premium" >Premium </label>						 
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
                                
                                    <!-- PayPal Logo --><table border="0" cellpadding="10" cellspacing="0" align="center"><tr><td align="center"></td></tr><tr><td align="center"><img  src="https://www.paypalobjects.com/webstatic/en_IN/mktg/logos/pp_cc_mark_37x23.jpg" border="0" alt="PayPal Logo" title="Click for payment of plan" onclick="document.forms['paypalForm'].submit();"></td></tr></table><!-- PayPal Logo -->
                                                          
                                <!--input type="button" style="border:1px solid #0097be;height: 30px;width: 62px;"  disabled id="pay" name="payment" class="pop-button pop-button-White" value="Pay" -->
                            </div>


                        </div>
                    </div>	
                    <div class="ButtonContiner">
                        <% HttpSession session2 = request.getSession(true);
                            SessionData sessionObj = (SessionData) session2.getAttribute("UserSessionData");%>
                        <input type="hidden" id="pswrd" value="<%=sessionObj.password_%>" />
                        <input type="hidden" id="eml" value="<%=sessionObj.username_%>" />
                        <input id="Profile_Update_Cancel" style="margin-left:60px;margin-right: 75px"  type='button' class="general-button gen-btn-Gray"   value="Cancel" />

                        <input id="Profile_Update_OK" type='button' class="general-button gen-btn-Orange" value="Save" />

                    </div>

                    <input type="hidden" id="firstAddrId" value="<%=firstMailList.addrid_%>">
                </div>
            </form>
        </div>

        <%@include file="../..//Utils/jsp/footer.jsp" %>

        <%@include file="../..//Utils/jsp/Popup_Warning.jsp" %>

        <%@include file="Popup_AddAddress.jsp" %>

        <%@include file="../..//Utils/jsp/Cus_Toast.jsp" %>




        <script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/dropdownfiller.js"></script>
        <script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Registration/js/comapnyProfile.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Registration/js/companyProfileValidator.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/Views/Registration/js/companyProfileFiller.js"></script>

        <script>

                                        $('select.selectbox').customSelect();

                                        var businessCat = $("#hidden_business_cat").val();
                                        var country = $("#hidden_country").val();
                                        var state = $("#hidden_state").val();
                                        var branch = $("#hidden_branch").val();

                                        fetchBuisnessCategory(businessCat);
                                        fetchCountryStateList(country, state);
                                        fetchBranchList(branch);


        </script>
        <script>


        </script>
    </body>
</html>
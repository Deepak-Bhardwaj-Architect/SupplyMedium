<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.bean.CountryBean"%>
<%@page import="supply.medium.home.bean.BusinessCategoryBean"%>
<%@page import="supply.medium.home.database.BusinessCategoryMaster"%>
<%@page import="java.util.ArrayList"%>
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
        <LINK rel="stylesheet" href="inside/Supplier.css">
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
        <SCRIPT type="text/JavaScript" src="inside/dilbag.js">
        </SCRIPT>
        <TITLE>Supply Medium</TITLE>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onkeydown="displayunicode4(event,<%=session.getAttribute("userKey") %>);" onLoad="lockUnlock('webkrit_content_loader');advanceSearchCount('Buyer', '0', '0', '<%=session.getAttribute("companyKey")%>');
            Usr_anlys('Admin');
            customizeMenu();
            pypl_rslt('null');">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <!--    <DIV style="min-height: 700px; background-color: rgb(241, 241, 241);" id="mainpage" 
             class="mainpage">-->
    <TITLE>News Room</TITLE>
    <LINK rel="stylesheet" href="inside/newsroom.css">
    <LINK rel="stylesheet" href="inside/newsroom_feed.css">
    <LINK rel="stylesheet" 
          href="inside/view_members.css">
    <!--<link rel="stylesheet" href="/SupplyMedium/dilbag.css">-->
    <!---->
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <div class="pagetitlecontainer">
            <div class="pagetitle">Search Buyers</div>
        </div>
        <div  id="searchSupplier" class="content_container" style="padding-top: 0px;float:left;">		
            <div class="pageContent" style="width: 1010px;font-family: Verdana, Arial, sans-serif;padding-top:50px;min-height:700px;">

                <div id="search_supplier_content">
                    <div style="position: relative;float: none;">

                        <input type="hidden" id="actionType"/>
                        <input type="hidden" id="companyKey"/>
                        <input type="hidden" id="vrType"/>
                        <form id="basic_search_form" action="" style="width:100%;float:left;" onsubmit="return false;">
                            <div class="searchWindow">

                                <div class="searchRow" style="float:left;width:605px;">

                                    <div style="width:520px;float:left">

                                        <input id="searchTextBox" name="searchTextBox"  placeholder="Search by company name" type="text"  />

                                        <label for="searchTextBox" class="error" style=""></label>

                                    </div>

                                    <input id="searchbtn" type="button" class="searchbtn" align="right"  style="margin-right:15px;" onclick="showVrList('Buyer',$('#searchTextBox').val(),$('#basic_search_cat').val(),'<%=companyKey %>');"/>


                                </div>

                                <div class="searchRow" style="float:left;">

                                    <div style="width:250px;float:right;height:50px;padding-top:3px;">
                                        <SELECT id="basic_search_cat" name="basic_search_cat" class="customSelect selectbox" style="height:30px;width:250px;">
                                            <OPTION value="0">All Business Category</OPTION>
                                                <%
                                                    ArrayList bcList = BusinessCategoryMaster.showAll();
                                                    BusinessCategoryBean bcb = null;
                                                    for (int i = 0; i < bcList.size(); i++) {
                                                        bcb = (BusinessCategoryBean) bcList.get(i);
                                                %>
                                            <option value="<%=bcb.getBusinessCategoryKey()%>"> 
                                                <%=bcb.getBusinessCategoryName()%>
                                            </option>
                                            <%
                                                }
                                            %>
                                        </SELECT>

                                        <label for="basic_search_cat" class="error" ></label>

                                    </div>
                                </div>




                                <div class="searchrow" style="float:left;">
                                    <div class="checkContainer">
                                        <input id="searchRegistered" type="checkbox" />
                                        <div></div>
                                    </div>

                                    <label for="searchRegistered" style="line-height: 19px;">Search within registered buyers only</label>
                                </div>					

                            </div>
                        </form>

                        <form id="advance_search_form" >
                            <input id="btnadvancedSearch" type="button" class="general-button btn-Advanced-down" align="right" value="Advance search" onclick="$('#advancedSearchContainer').toggle(500);
                                    splr_al_srch();" style="background: #007382;"/>
                            <div class="advancedSearchContainer" style="display: none;" id="advancedSearchContainer">						
                                <div  style="margin: 15px 0px 5px 13px;height:30px;">
                                    <label class="advacedLabel" for="advanPartNumber" style="width: 125px;" >Business Category</label>
                                    <select id="Advanced_Selectbox"  class="customSelect selectbox" style="height:30px;width:250px;" onchange="splr_slctd_ctgry_srch(this.value);advanceSearchCount('Buyer', $('#searchTextBox').val(), $('#basic_search_cat').val(), '<%=companyKey%>');">
                                    <OPTION value="0">All Business Category</OPTION>
                                                <%
                                                    for (int i = 0; i < bcList.size(); i++) {
                                                        bcb = (BusinessCategoryBean) bcList.get(i);
                                                %>
                                            <option value="<%=bcb.getBusinessCategoryKey()%>"> 
                                                <%=bcb.getBusinessCategoryName()%>
                                            </option>
                                            <%
                                                }
                                            %>
                                    </select>                                                        
                                </div>
                                <div  style="margin: 15px 0px 5px 13px;height:30px;">
                                    <label class="advacedLabel" for="advanPartNumber" style="width: 125px;">Country</label>
                                    <select id="cntry" class="selectbox" style="width:200px;background-color:white;" onchange="splr_slctd_cntry_srch(this.value);advanceSearchCount('Buyer', $('#searchTextBox').val(), $('#basic_search_cat').val(), '<%=companyKey%>');">
                                        <OPTION value="0">--Select 
                                        Country--</OPTION>
                                        <%
                                            ArrayList countryList = CountryMaster.showAll();
                                            CountryBean scb = null;
                                            for (int i = 0; i < countryList.size(); i++) {
                                                scb = (CountryBean) countryList.get(i);
                                        %>
                                    <option value="<%=scb.getCountryKey() %>">
                                        <%=scb.getCountryName()%>
                                    </option>
                                    <%
                                        }
                                    %>
                                    </select>
                                    <label  class="advacedLabel" style="float: right;" id="splr_slctd_srch">0 Supplier Exists</label>
                                </div> 
                                <div  class="advancedRow"  style="margin: 15px 0px 5px 0px;height:30px;">
                                    <label class="advacedLabel" for="advanPartNumber" style="width: 125px;">Part Number</label>
                                    <input type="text" id="advanPartNumber1" class="advan_textbox" />
                                    <!--<input id="btnAddItem" type="button" class="general-button btn-Reset" value="Add Item" />-->  
                                </div>
                                <div id="PartNumbercontainer" class="advancedRow"  style="margin: 15px 0px 5px 0px;height:30px;">
                                    <label class="advacedLabel" for="advanName" style="width: 125px;">Product Name</label>
                                    <input type="text" id="advanName1" class="advan_textbox" />
                                </div>
<!--                                <div class="advancedRow">
                                    <label class="advacedLabel"></label>
                                    <div class="checkContainer">
                                        <input id="advancedCheck" type="checkbox" />
                                        <div></div>
                                    </div>
                                    <label for="advancedCheck" style="line-height: 19px">Show Result only if all of the items are found</label>

                                </div>-->
                                <div class="advancedRow" style="margin-bottom: 2px;height:30px;">
                                    <div>
                                        <div style="margin-left: 115px;" class="checkContainer">
                                            <input type="checkbox"  id="regSupplierCheck"/>
                                            <div></div>
                                        </div>
                                        <label for="regSupplierCheck" style="line-height: 19px">Search within registered suppliers only</label>
                                        <input id="btnReset" style="margin: 0px 10px 0px 0px" type="button" class="general-button btn-Reset" value="Reset" />  
                                        <input id="btnSearchNow"  style="width: 110px;margin: 0px 5px 0px 0px" type="button" class="general-button gen-btn-Orange" value="Search Now" onclick="showVrListAdvance('Buyer', $('#searchTextBox').val(), $('#basic_search_cat').val(), '<%=companyKey%>');"/>

                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>	
                                        <div class="searchHeading" id="searchHeading" style="display:none;">
                        Search Results
                    </div>

                    <div class="noResult" id="searchResults">No buyers found for this search criteria</div>

                    <div style="width:1010px;height:auto;float:left;display:none;" id="searchDataResults">
                        <div class="searchRegistertedResult">
                            <div  >
                                <span class="searchSubHeading">In My Network Buyer List</span>
                                <span class="searchNoResults" style="text-decoration: none;" id="reg_comp_count"></span>
                            </div>
                            <div id="regSearchResult"  class="SearchResult" >

                            </div>
                            <div style="margin-top: 30px;">
                                <input id="btnnonGenerateRFQ" type="button" value="Generate RFQ" class="general-button gen-btn-Orange" />
                            </div>

                        </div>

                        <div class="searchNonRegistertedResult">
                            <div  >
                                <span class="searchSubHeading">Out Of My Network Buyer List</span>
                                <span class="searchNoResults" style="text-decoration: none;" id="non_reg_comp_count"></span>
                            </div>
                            <div id="nonregSearchResult" class="SearchResult">

                            </div>
                            <div style="margin-top: 30PX;">
                                <!-- <input id="btnnonGenerateRFQ" type="button" value="Generate RFQ" class="general-button gen-btn-Orange" /> -->
                                <input id="btnContact" type="button" value="Contact" class="general-button gen-btn-Orange" />

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="search_ad" class="ad_container"></div>	
            
            <input type="hidden" id="emailId">
            <input type="hidden" id="rowno">
            <%@include file="_footer.jsp" %>
            <div>
            </div>
        </div>
    </div>
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

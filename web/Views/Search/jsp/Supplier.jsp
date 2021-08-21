<%@page import="db.utils.DBConnect"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Supplier</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Search/css/Supplier.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/jquery.mCustomScrollbar.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />

</head>
<body>

<script type="text/JavaScript">
$("#content_loader").hide();
</script>

<input type="hidden" value="Nothing" >
	<div class="pagetitlecontainer">
	<div class="pagetitle">Search Suppliers</div>
	</div>
	
	
	<div  id="searchSupplier" class="content_container" style="padding-top: 0px;float:left;">		
		<div class="pageContent" style="width: 1010px;font-family: Verdana, Arial, sans-serif;padding-top:50px;min-height:700px;">
		
			<div id="search_supplier_content" style="display:none;">
			<div style="position: relative;float: none;">
			
							
				  <form id="basic_search_form" action="" style="width:100%;float:left;" onsubmit="return basicSearch();">
					<div class="searchWindow">
					
						<div class="searchRow" style="float:left;width:605px;">
						
							<div style="width:520px;float:left">
							
								<input id="searchTextBox" name="searchTextBox"  placeholder="Search by name, categories, item description" type="text"  />
								
								<label for="searchTextBox" class="error" style=""></label>
								
							</div>
							
							<input id="searchbtn" type="button" class="searchbtn" align="right"  style="margin-right:15px;" onclick="basicSearch();"/>
							
							
						</div>
						
						<div class="searchRow" style="float:left;">
						
							<div style="width:250px;float:right;height:50px;padding-top:3px;">
									<select id="basic_search_cat" name="basic_search_cat" class="selectbox" style="width:250px;" >
								
									</select>
								
									<label for="basic_search_cat" class="error" ></label>
								
							</div>
						</div>
						
						
								
								
						<div class="searchrow" style="float:left;">
							<div class="checkContainer">
								<input id="searchRegistered" type="checkbox" />
								<div></div>
							</div>
						
							<label for="searchRegistered" style="line-height: 19px;">Search within registered suppliers only</label>
						</div>					
					
					</div>
				 </form>
				
				<form id="advance_search_form" >
                                    <input id="btnadvancedSearch" type="button" class="general-button btn-Advanced-down" align="right" value="Advance search" onclick="splr_al_srch();" style="background: #007382;"/>
				<div class="advancedSearchContainer">						
						<div  style="margin: 15px 0px 5px 13px;">
                                                        <label class="advacedLabel" for="advanPartNumber" style="width: 125px;" style="width: 125px;">Business Category</label>
                                                        <select id="Advanced_Selectbox" class="selectbox" style="width:200px;background-color:white; " onchange="splr_slctd_ctgry_srch(this.value)">
							</select>                                                        
						</div>
                                                <div  style="margin: 15px 0px 5px 13px;">
                                                        <label class="advacedLabel" for="advanPartNumber" style="width: 125px;">Country</label>
                                                        <select id="cntry" class="selectbox" style="width:200px;background-color:white;" onchange="splr_slctd_cntry_srch(this.value)">
                                        <option id="dflt_slct_cntry">All</option>
                                        <%
                                            String str = "SELECT * FROM sm_config_countries";

                                            Statement stmt = null;
                                            ResultSet rs = null;
                                            Connection con = null;
                                            try {
                                                con = DBConnect.instance().getConnection();
                                                stmt = con.createStatement();
                                                rs = stmt.executeQuery(str);

                                                while (rs.next()) {                                                    

                                               %><option><%=rs.getString("country_name") %></option><%

                                                }
                                            }  catch (Exception ex) {
                                                out.print("problem to get countries"+ex);
                                            } finally {
                                                try {
                                                    if (rs != null) {
                                                        rs.close();
                                                    }
                                                    if (stmt != null) {
                                                        stmt.close();
                                                    }
                                                    if (con != null) {
                                                        con.close();
                                                    }
                                                } catch (SQLException sQLException) {
                                                }
                                            }
                                        %>
                                    </select>
                                    <label  class="advacedLabel" style="float: right;" id="splr_slctd_srch">0 Supplier Exists</label>
						</div> 
						<div  class="advancedRow">
							<label class="advacedLabel" for="advanPartNumber" style="width: 125px;">Part Number</label>
							<input type="text" id="advanPartNumber1" class="advan_textbox" />
							<input id="btnAddItem" type="button" class="general-button btn-Reset" value="Add Item" />  
						</div>
						<div id="PartNumbercontainer" class="advancedRow">
							<label class="advacedLabel" for="advanName" style="width: 125px;">Name</label>
							<input type="text" id="advanName1" class="advan_textbox" />
						</div>
						<div class="advancedRow">
							<label class="advacedLabel"></label>
							<div class="checkContainer">
								<input id="advancedCheck" type="checkbox" />
								<div></div>
							</div>
							<label for="advancedCheck" style="line-height: 19px">Show Result only if all of the items are found</label>
							
						</div>
						<div class="advancedRow" style="margin-bottom: 2px;">
							<div>
							<div style="margin-left: 115px;" class="checkContainer">
								<input type="checkbox"  id="regSupplierCheck"/>
								<div></div>
							</div>
							<label for="regSupplierCheck" style="line-height: 19px">Search within registered suppliers only</label>
							<input id="btnReset" style="margin: 0px 10px 0px 0px" type="button" class="general-button btn-Reset" value="Reset" />  
							<input id="btnSearchNow"  style="width: 110px;margin: 0px 5px 0px 0px" type="button" class="general-button gen-btn-Orange" value="Search Now" onclick="advanceSearch();"/>
								
							</div>
						</div>
				</div>
				</form>
			</div>	
			<div class="searchHeading">
				Search Results
			</div>
			
			<div class="noResult" id="searchResults">No suppliers found for this search criteria</div>
			
			<div style="width:1010px;height:auto;float:left;">
			<div class="searchRegistertedResult">
					<div  >
							<span class="searchSubHeading">In My Network Supplier List</span>
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
							<span class="searchSubHeading">Out Of My Network Supplier List</span>
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
		
	</div>

<%@include file="Popup_AddItem.jsp" %>

<%@include file="../../Utils/jsp/Cus_Toast.jsp" %>

<%@include file="../../Utils/jsp/footer.jsp"%>

<script>
$.getScript( "${pageContext.request.contextPath}/Views/Search/js/Supplier.js", function( data, textStatus, jqxhr ) {});
</script>


</body>
</html>

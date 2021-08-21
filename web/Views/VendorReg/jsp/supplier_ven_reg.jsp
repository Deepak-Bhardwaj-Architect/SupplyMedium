<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
	
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/VendorReg/css/supplier_ven_reg.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/tablestyle.css" />

<title></title>
</head>
<body>
	 
<script type="text/JavaScript">
//$("#content_loader").hide();

</script>

	<div class="pagetitlecontainer">  <!-- Page header -->
		<div class="pagetitle">Vendor Registration</div>
	</div>
	
	
	<div class="page"> <!-- Page -->
            <div id="cntct_dtl_er" class="contact_detail_error" onclick="document.getElementById('cntct_dtl_er').style.display='none';"></div>
		<div class="contentcontainer" style="min-height:709px;"> <!-- Content container -->
		<div id="supplier_ven_reg_content" style="display:none;">
		
			<div class="main_tab_container">  <!-- This is the main tab bar container -->
			
				<div id="req_queue_tab" class="main_tab_select">  <!-- This is the Request Queue tab -->
				Request Queue</div>
				
				<div id="add_buyer_tab" class="main_tab_unselect">	<!-- This is the Add Buyer tab -->
				Add Buyer</div>
				
			</div>
			
			<div class="main_tab_sep"> <!-- This is the seperator div  -->
			</div>
			
			<div id="req_queue_content"  > <!-- Request queue content container. This contain two queues -->
			
				<div class="sub_tab_container"> <!-- This is inner tab bar container -->
				
					<div class="highlight" id="buyer_reg_tab">Received Request</div>  <!-- Buyer Request tab -->

					<div class="normal" id="my_reg_tab">Sent Request</div> <!-- My Request tab -->
				</div>
				
				<div class="buyer_req_content" id="buyer_req_content">  <!-- Buyer Request DataTable container -->

					<div class="tablecontent" id="table_content1">
						<div class="DT_border" >
						</div>
						<table id="buyer_req_list" style="width: 997px;">
						
							<thead >
							
								<tr>
									<th class="rowBorder">Company Name</th>
									<th class="rowBorder">Phone Number</th>
									<th class="rowBorder">Country</th>
									<th class="rowBorder">Email</th>
									<th class="rowBorder">Details</th>
									<th class="rowBorder">Status</th>
									<th class="rowBorder">Date</th>
								</tr>
								
							</thead>
								
							<tbody>
								
							</tbody>
							
						</table>
					</div>
				</div>
				
				<div class="my_req_content" id="my_req_content" style="display:none">	<!-- My Request DataTable container -->
				
					<div class="tablecontent" id="table_content2">
						<div class="DT_border" >
						</div>
						<table id="my_req_list" style="width: 997px;">
						
							<thead >
								<tr>
									<th class="rowBorder">Company Name</th>
									<th class="rowBorder">Phone Number</th>
									<th class="rowBorder">Country</th>
									<th class="rowBorder">Email</th>
									<th class="rowBorder">Details</th>
									<th class="rowBorder">Status</th>
									<th class="rowBorder">Date</th>
								</tr>
							</thead>
								
							<tbody>
								
							</tbody>
							
						</table>
					</div>
				</div>
			
			</div>
			<div id="add_buyer_content" style="display:none;"> <!-- Vendor Registration Form Container -->
				<div class="to_comp">
					<label class="label" style="padding-left:20px;width:45px;color:white;line-height:25px;">To:</label>
					<input type="text" id="to_company" style="width:920px;" class="textbox" onkeyup="getNRVendor();">
				</div>
				<form class="ven_reg" id="ven_reg_form" name="ven_reg_form" action="${pageContext.request.contextPath}/VendorRegnServlet" method="post" enctype="multipart/form-data" >
					<input type="hidden" id="selected_ven_key" />
				
					<div id="ven_search_result" class="com_search_result" style="display:none;">
						
					</div>
				
					<div class="ven_reg_head"> Vendor Registration
					</div>
					
					<div class="ven_reg_left">
					
						<div class="row">
							<label class="label"> Company Name </label>
							<input type="text" id="company_name" name="company_name" class="textbox_disable" disabled/>
						</div>
						
						
						<div class="row">
							<label class="label"> Branch </label>
							<input type="text" id="branch_0" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> Country/Region </label>
							<input type="text" id="countryregion_0" class="textbox_disable" disabled />
						</div>
						
						<div class="row">
							<label class="label"> Address </label>
							<input type="text" id="address" name="address" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> City </label>
							<input type="text" id="city" name="city" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> State/Province </label>
							<input type="text" id="state_0" class="textbox_disable" disabled />
						</div>
						
						
						<div class="row">
							<label class="label"> Zip Code/Postal Code </label>
							<input type="text" id="zipcode" name="zipcode" class="textbox_disable" disabled/>
						</div>
						
						<div class="row" style="min-height:100px;height:auto;">
							<table style="min-height:60px;">
							<tr>
								<td align="left" style="vertical-align: top;">
									<div class="label" style="width: 196px">
										Add Address <br />
									
									
										<input style="display: block;" type="button" name="addAddress" id="addAddress" class="addAddress removeaddress" />
																
									</div>
								</td>
								<td align="left" style="vertical-align: top;">
								<div id="addressExpandContainer">
							
									<div id="addressExpander"></div>											    
									</div>
								</td>
								<td id="AddressList">
									
								</td>
							</tr>
						</table>
					</div>
					
					<div style="height: 100px;" class="row" id="type">
					
						<div class="label">
							 Type<span class="mandatory"></span>
						</div>
						<fieldset>
							<legend> Type </legend>
							<input type="radio" value="Individual" class="radiobtn required" id="internetuser" name="comtype" checked="checked" style="vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;"> 
							<label style="vertical-align: middle; font-size: 12px; color: #282828; line-height:25px;" for="internetuser">
								Individual/Sole Proprietor &nbsp;</label><br> 
							<input type="radio" value="Corporation" style="vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;" class="radiobtn" name="comtype" id="transuser"> 
							<label style="vertical-align: middle; font-size: 12px; color: #282828;line-height:25px;" for="transuser">
								Corporation, Partnerships, other&nbsp;</label> 
							<label style="margin-left: 0px !important; width: 150px;" class="error" generated="true" for="usertype"></label>

						</fieldset>

					</div>
					
					</div>
					
					<div class="ven_reg_right">
					
						<div class="row">
							<label class="label"> Business Contact Name </label>
							<input type="text" id="contact_name" name="cname" class="textbox_disable" disabled />
						</div>
						
						
						<div class="row">
							<label class="label"> Contact Title/Department </label>
							<input type="text" id="titledept" name="titledept" class="textbox_disable" disabled />
						</div>
						
						
						<div class="row">
							<label class="label"> Contact Email </label>
							<input type="text" id="email" name="email" class="textbox_disable" disabled />
						</div>
						
						<div class="row">
							<label class="label"> Phone </label>
							<input type="text" id="phone" name="phone" class="textbox_disable" disabled/>
						</div>
						
						
						<div class="row">
							<label class="label"> Cell </label>
							<input type="text" id="cell" name="cell" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> Fax </label>
							<input type="text" id="fax" name="fax" class="textbox_disable" disabled />
						</div>
						
						<div class="row">
							<label class="label"> Type of Business </label>
							<input type="text" id="typeofbusiness" class="textbox_disable" disabled>
						</div>
						
						<div class="row">
							<label class="label"> Business Category </label>
							<input type="text" id="businesscategory" class="textbox_disable" disabled />
						</div>
						
						<div class="row">
							<label class="label"> Business Tax ID </label>
							<input type="text" id="taxid" name="taxid" class="textbox" />
						</div>
						
						<div class="row">
							<label class="label"> NAICS Code </label>
							<select class="selectbox" name="naicscode" id="naicscode" style="width:165px;">
							<option> Select NAICS Code </option>
							</select>
						</div>
						
						<div class="row">
							<label class="label">  </label>
							<div class="checkContainer" style="margin-right:5px;">
								<input type="checkbox" name="w9form_flag" id="w9form_flag" onchange="w9checkBoxClicked()"/>
							<div></div>
							</div>
							<label for="w9form_flag" style="width:300px !important;line-height:12px;font-family:Verdana;font-size:10px;"> W9 tax form will be submitted<br> to complete the registration process
							</label>
							
						</div>
						
						<div class="row" id="w9Form_upload_btn" style="display:none">
							<label class="label"> W9 Tax Form </label>
							<input type="file" name="w9form" id="w9form" />
						</div>
						
					</div>
					
					<div class="buss_size_container">
					
						<div class="side_heading"> Business Size ( Select One )
						</div>
						
						<div class="buss_size_large">
						
							<div class="checkContainer">
								<input type="radio" checked="checked" name="buss_size" id="buss_large" value="Large"/>
								<div></div>
							</div>
							
						
							
							<label class="des_text"><b>Large:<br></b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A domestic concren which, including domestic and foreign divisions and affiliates, normally 
							employs 500 or more persons, is independently -or- publicly-owned or controlled and operated, 
							and which may be division of another domestic or foreign concern.</label>
						</div>
						
						<div class="buss_size_small">
						
							<div class="checkContainer">
								<input type="radio" name="buss_size" id="buss_small" value="Small"/>
								<div></div>
							</div>
							
							
							
							<label class="des_text"><b>Small:<br></b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Small business cocren" means a concren, including its affiliates, that is independently 
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
							<input type="checkbox" name="Disadvantaged" id="Disadvantaged" />
						<div></div>
						</div>
						
						<label class="des_text" for="Disadvantaged" ><b>Disadvantaged:</b> In addition to meeting the requirements of the FAR definition, 
						any business classfying them selves as Disadvantaged must be certified by the Small Business 
						Administration and have that certification in good standing.
						</label>
						
						<div class="checkContainer" >
							<input type="checkbox" name="HubZone" id="HubZone" />
							<div></div>
						</div>
						
						<label class="des_text" for="HubZone"><b>HUBZone:</b> In addition to meeting the requirements of the FAR definition, any business
						 classifying themselves as a HUBZ one must be certified by the small Business Administraiotn and
						 have that certification in good standing.
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="WomenOwned" id="WomenOwned" />
						<div></div>
						</div>
						
						<label class="des_text" for="WomenOwned">Women-Owned
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="HandicappedOwned" id="HandicappedOwned" />
							<div></div>
						</div>
						<label class="des_text" for="HandicappedOwned">handicapped-Owned
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="VETERANOWNED" id="VETERANOWNED" />
							<div></div>
						</div>
						<label class="des_text" for="VETERANOWNED">Veteran-Owned
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="SDVETERANOWNED" id="SDVETERANOWNED" />
							<div></div>
						</div>
						<label class="des_text" for="SDVETERANOWNED">Service-Disabled Veteran-Owned
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="HBCORMI" id="HBCORMI" />
							<div></div>
						</div>
						<label class="des_text" for="HBCORMI">Historically-Back College or Minority Institution
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="MBE" id="MBE" />
							<div></div>
						</div>
						<label class="des_text" for="MBE">Minority Business Enterprise
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="NonProfit" id="NonProfit" />
							<div></div>
						</div>
						<label class="des_text" for="NonProfit"><b>Non-Profit:</b> Any business or organization that has received non-profit status
						under IRS Regulation 501(c)(3).
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="Foreign" id="Foreign" />
							<div></div>
						</div>
						<label class="des_text" for="Foreign"><b>Foreign:</b> A concern which is not incorporated in the United States or an 
						unincorporated concern having its principle place of business outside the United States.
						</label>
						<div class="checkContainer">
							<input type="checkbox" name="PublicSector" id="PublicSector" />
							<div></div>
						</div><input type="checkbox" name="buss_small" id="buss_small" />
						<label class="des_text" for="PublicSector"> <b>Public Sector:</b> An agency of the Federal or State Government, or a local municipality.
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="ANCORITNSB" id="ANCORITNSB" />
							<div></div>
						</div>
						<label class="des_text" for="ANCORITNSB">Alaska Native Corporations Indian Tripe that is not a small business.
						</label>
						
						
						<div class="checkContainer">
							<input type="checkbox" name="ANCORITNCD" id="ANCORITNCD" />
							<div></div>
						</div>
						<label class="des_text" for="ANCORITNCD"><b>Alaska Native Corporations or Indian Tribe:</b> Not certified by the Small Business Administration as disadvantaged.
						</label>
						
						
					</div>
					
					<div class="addl_det_container">
					
						<div class="side_heading"> Additional Details
						</div>
						<textarea class="addr_det_text" id="additional_det"></textarea>
					</div>
					
					<div class="ven_reg_btns">
                                                <%
                                                HttpSession session2 = request.getSession(true);
                                                SessionData sessionObj2 = (SessionData) session2.getAttribute("UserSessionData");
                                                %>
                                                <input type="hidden" id="usr_typ" value="<%=sessionObj2.userType_ %>"/>
                                                <input type="button" class="gen-btn-Gray" style="margin-right:50px;"  value="Cancel"/>
                                                <input type="button" class="gen-btn-Orange" value="Send" id="add_vendor_btn"/>
					</div>
					<div id="vendor_reg_error" class="vendor_reg_error"></div>
					
					
				</form>
			</div>
			
		</div>
		</div>
		<div id="search_ad" class="ad_container"></div>	
		
	</div>
	
	<%@include file="../../Utils/jsp/footer.jsp"%>
	
	<%@include file="../../Utils/jsp/Popup_Warning.jsp"%>
	
	<%@include file="ven_reg_addr_popup.jsp" %>
	
	<%@include file="supplier_popup.jsp" %>
	
	<%@include file="../../Utils/jsp/Cus_Toast.jsp" %>
	
	<%@include file="../../../session_check.jsp"%>
	
	
	<script>
	$.getScript( "${pageContext.request.contextPath}/Views/Utils/js/dropdownfiller.js");
	$.getScript( "${pageContext.request.contextPath}/Views/Registration/js/companyProfileFiller.js");
	$.getScript( "${pageContext.request.contextPath}/Views/VendorReg/js/supplier_ven_reg_DT.js", function( data, textStatus, jqxhr ) { 
		 
		initSupplierMyReqDataTable();
		
		initBuyerReqDataTable();
		
		getBusinessClassfication();	
		
		$("#cancel_regn_btn").click(cancelRegnBtnClicked);
		$("#reject_regn_btn").click(rejectBtnClicked);
		$("#send_regn_btn").click(sendBtnClicked);
		
		$("#inquire_regn_btn").click(inquireBtnClicked);
		
		
		$("#cancel_btn").click( cancelBtnClicked );
		$("#save_btn").click( saveBtnClicked );
	});
	
	$.getScript( "${pageContext.request.contextPath}/Views/VendorReg/js/ven_reg_addr_mgmt.js");
	$.getScript( "${pageContext.request.contextPath}/Views/VendorReg/js/supplier_ven_reg_form.js", function( data, textStatus, jqxhr ) { 
		$("#add_vendor_btn").click(addBuyer);
		
		getCompanyDetails();
		
		getSupplierPendingRegReq();
		
		getSupplierMyPendingRegReq();
		
		getNAICSCode();
		 
	});
	
	$.getScript( "${pageContext.request.contextPath}/Views/VendorReg/js/supplier_ven_reg.js", function( data, textStatus, jqxhr ) {
		 supplierOnload();
		 
		});
	
	</script>
	
	
	<!--  <script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/dropdownfiller.js"></script>
	
	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Registration/js/companyProfileFiller.js"></script>

	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/VendorReg/js/supplier_ven_reg_DT.js"></script>
	
	
	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/VendorReg/js/ven_reg_addr_mgmt.js"></script>
	
	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/VendorReg/js/supplier_ven_reg_form.js"></script>
	
	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/VendorReg/js/supplier_ven_reg.js"></script>-->
	
	

</body>
</html>
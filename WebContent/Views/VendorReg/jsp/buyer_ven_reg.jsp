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

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/VendorReg/css/buyer_ven_reg.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/tablestyle.css" />

<title></title>
</head>
<body onload="">


<script type="text/JavaScript">
//$("#content_loader").hide();

</script>

	<div class="pagetitlecontainer">  <!-- Page header -->
		<div class="pagetitle">Vendor Registration</div>
	</div>
	
	
	<div class="page"> <!-- Page -->
	
		<div class="contentcontainer" style="min-height:709px;"> <!-- Content container -->
		<div id="buyer_ven_reg_content" style="display:none;">
		
			<div class="main_tab_container">  <!-- This is the main tab bar container -->
			
				<div id="req_queue_tab" class="main_tab_select">  <!-- This is the Request Queue tab -->
				Request Queue</div>
				
				<div id="add_supplier_tab" class="main_tab_unselect">	<!-- This is the Add Supplier tab -->
				Add Supplier</div>
				
			</div>
			
			<div class="main_tab_sep"> <!-- This is the seperator div  -->
			</div>
			
			<div id="req_queue_content"  > <!-- Request queue content container. This contain two queues -->
			
				<div class="sub_tab_container"> <!-- This is inner tab bar container -->
				
					<div class="highlight" id="supplier_reg_tab">Supplier Request</div>  <!-- Supplier Request tab -->

					<div class="normal" id="my_reg_tab">My Request</div> <!-- My Request tab -->
				</div>
				
				<div class="supplier_req_content" id="supplier_req_content">  <!-- Supplier Request DataTable container -->

					<div class="tablecontent" id="table_content1">
						<div class="DT_border" >
						</div>
						<table id="supplier_req_list" style="width: 997px;">
						
							<thead >
							
								<tr>
									<th class="rowBorder">Company Name</th>
									<th class="rowBorder">Phone Number</th>
									<th class="rowBorder">Country</th>
									<th class="rowBorder">Email</th>
									<th class="rowBorder">Contact Name</th>
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
									<th class="rowBorder">Contact Name</th>
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
			<div id="add_supplier_content" style="display:none;"> <!-- Vendor Registration Form Container -->
				<div class="to_comp">
					<label class="label" style="padding-left:20px;width:45px;color:white;line-height:25px;">To:</label>
					<input type="text" id="to_company" style="width:920px;" class="textbox" onkeyup="getNRVendor();">
				</div>
				<div class="ven_reg" style="background-color:transparent;border:none;">
				
					<input type="hidden" id="selected_ven_key" />
				
					<div id="ven_search_result" class="com_search_result" style="display:none;">
						
					</div>
					
					<div class="buyer_ven_regn">
						<div class="buyer_ven_regn_head"> Request to Add Supplier
						</div>
						<div id="buyer_ven_regn_content" class="buyer_ven_regn_content">
							<div class="buyer_ven_regn_text">
							W9 tax form will be submitted to complete the registration process?
							</div>
							<div class="buyer_ven_regn_radio">
							
								<input type="radio" name="w9Form_required" value="Yes" id="yes" class="required radiobtnlbl" style="margin:0px;" />
								<label for="buyerradio" class='radiobtnlbl' style="width:100px;">Yes</label> &nbsp;
								
								<input type="radio" name="w9Form_required" value="No" id="no" class="radiobtnlbl" style="margin:0px;" checked="checked"/>
								<label for="Supplierradio" class='radiobtnlbl' >No</label>&nbsp; 
				
							</div>
							<div class="buyer_ven_regn_btn">
								<input type="button" class="gen-btn-Orange"  value="Send" id="add_vendor_btn"/>
							</div>
							
							<div id="vendor_reg_error" class="vendor_reg_error"></div>
							
						</div>
					</div>
					
				</div>
			
		</div>
		</div>
		</div>
		<div id="search_ad" class="ad_container"></div>	
	</div>
	
	<%@include file="../../Utils/jsp/footer.jsp"%>
	
	<%@include file="ven_reg_addr_popup.jsp" %>
	
	<%@include file="buyer_popup.jsp" %>
	
	<%@include file="../../Utils/jsp/Cus_Toast.jsp" %>
	
	 <%@include file="../../../session_check.jsp"%>
	 
	 
	
	<script>
	
	$.getScript( "${pageContext.request.contextPath}/Views/Registration/js/companyProfileFiller.js");
	$.getScript( "${pageContext.request.contextPath}/Views/VendorReg/js/buyer_ven_reg_DT.js", function( data, textStatus, jqxhr ) {
		 
		initBuyerMyReqDataTable();
		
		initsupplierReqDataTable();
		
		getBusinessClassfication();	
		
		$("#accept_btn").click(acceptBtnClicked);
		$("#reject_btn").click(rejectBtnClicked);
		$("#inquire_btn").click(inquireBtnClicked);
		
		$("#cancel_btn").click( cancelBtnClicked );
		$("#save_btn").click( saveBtnClicked );
	});
	
	$.getScript( "${pageContext.request.contextPath}/Views/VendorReg/js/ven_reg_addr_mgmt.js");
	$.getScript( "${pageContext.request.contextPath}/Views/VendorReg/js/buyer_ven_reg_form.js", function( data, textStatus, jqxhr ) {
		
		$("#add_vendor_btn").click(addSupplier);
		
		getBuyerPendingRegReq();
		
		getBuyerMyPendingRegReq();
		 
	});
	
	$.getScript( "${pageContext.request.contextPath}/Views/VendorReg/js/buyer_ven_reg.js", function( data, textStatus, jqxhr ) {
		 buyerOnload();
		 
		});
	
	</script>
	
	<!--  <script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Registration/js/companyProfileFiller.js"></script>

	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/VendorReg/js/buyer_ven_reg_DT.js"></script>
	
	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/VendorReg/js/buyer_ven_reg_form.js"></script>
	
	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/VendorReg/js/ven_reg_addr_mgmt.js"></script>
	
	<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/VendorReg/js/buyer_ven_reg.js"></script>-->
	


</body>
</html>
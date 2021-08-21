<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Transaction/RFQ/css/rfq.css" />


</head>

<body>

	<div class="sub_tab_container" id="rfq_submenu">
		<!-- This is RFQ inner tab bar container -->

		<div class="highlight" id="rfq_request_tab">My Request</div>
		<!-- My Request tab -->

		<div class="normal" id="rfq_form_tab">Generate RFQ</div>
		<!-- RFQ Form tab -->
	</div>
	
	<div id="rfq_request_content" style="position:relative;"> <!-- This is the RFQ queue. -->
		<div class="DT_border" >
		</div>
		<table id="rfq_list" style="width: 997px;padding-left:1px;">		
			<thead >
				<tr>
					<th class="rowBorder" style="border-left:1px solid #c8c8c8"></th>
					<th class="rowBorder">RFQ ID</th>
					<th class="rowBorder">Date</th>
					<th class="rowBorder">Company Name</th>
					<th class="rowBorder">Phone</th>
					<th class="rowBorder">Email</th>
					<th class="rowBorder">State</th>
					<th class="rowBorder">Status</th>
					<th class="rowBorder" style="border-right:1px solid #c8c8c8"></th>
				</tr>
			</thead>
								
			<tbody>
								
			</tbody>
		</table>
	</div>

	<div id="rfq_form_content" style="display:none;" class="rfq_form_content"> <!-- This is the RFQ form details -->
		<div class="to_comp">
					<label class="label" style="padding-left:20px;width:45px;color:white;line-height:25px;">To:</label>
					<input type="text"  placeholder="Search Registered Vendor" id="to_company" style="width:920px;" class="textbox" onkeyup="getRegVendor();">
		</div>
		
		<input type="hidden" id="selected_ven_key"/>
		
		
		<div class="rfq_form">
		<div id="ven_search_result" class="com_search_result" style="display:none;">		
			</div>
			
			
			<div class="rfq_buyer_det">
				<div class="sub_heading">Buyer Details</div>
				
				<div class="trans_row" style="float:left;">
					<label class="trans_label"> Buyers Name </label>
					<input type="text" class="textbox_disable" id="buyer_name" disabled />
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> Country </label>
					<input type="text" class="textbox_disable" id="buyer_country" disabled/>
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> State/Province </label>
					<input type="text" class="textbox_disable" id="buyer_state" disabled/>
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> City </label>
					<input type="text" class="textbox_disable" id="buyer_city" disabled/>
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> Address </label>
					<input type="text" class="textbox_disable" id="buyer_addr" disabled/>
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> Zip Code/Postal Code </label>
					<input type="text" class="textbox_disable" id="buyer_zipcode" disabled />
				</div>
				
				<div class="sub_heading" style="margin-top:20px;">Request For Quote</div>
				
				<div class="trans_row">
					<label class="trans_label"> Quote Reference </label>
					<input type="text" class="textbox" id="quote_ref" />
				</div>
				
				
			</div>
			<div class="addr_sep"></div>
			<div class="rfq_supplier_det">
				<div class="sub_heading">Supplier Details</div>
				
				<div class="trans_row">
					<div class="checkContainer"><input type="checkbox" class="checkbox" id="outside_supplier"><div></div></div>
				<label for="outside_supplier" class="trans_label" style="line-height:19px;margin-left:5px;">Outside Supplier</label>
				</div>
				
				<div class="supplier_address" style="width:100%;height:300px;">
				
					<div class="trans_row">
						<label class="trans_label"> Country </label>
						<input type="text" class="textbox_disable" id="supplier_country" disabled/>
					</div>
				
					<div class="trans_row">
						<label class="trans_label"> State/Province </label>
						<input type="text" class="textbox_disable" id="supplier_state" disabled/>
					</div>
				
					<div class="trans_row">
						<label class="trans_label"> City </label>
						<input type="text" class="textbox_disable" id="supplier_city" disabled/>
					</div>
				
					<div class="trans_row">
						<label class="trans_label"> Address </label>
						<input type="text" class="textbox_disable" id="supplier_addr" disabled/>
					</div>
				
					<div class="trans_row">
						<label class="trans_label"> Zip Code/Postal Code </label>
						<input type="text" class="textbox_disable" id="supplier_zipcode" disabled/>
					</div>
				
				</div>
				
				<div class="outside_sup_email_content" style="width:100%;height:300px;display:none">
				
					<div class="trans_row" style="margin-top:100px;">
						<label class="trans_label"> Email </label>
						<input type="text" class="textbox" id="email" />
					</div>
					
				</div>
				
				<div class="trans_row" style="margin-top:50px;">
					<label class="trans_label"> Recurring </label>
					<select id="recurring"  class="selectbox" style="width:165px;">
						<option value="weekly"> None </option>
						<option value="weekly"> Weekly </option>
						<option value="monthly"> Monthly </option>
						<option value="annually"> Annually </option>
					</select>
				</div>
			</div>
			<div class="items_head">
				<label class="trans_label" style="width:38px;margin-right:20px;">S.No</label>
				<label class="trans_label" style="width:188px;margin-right:20px;">Item Description</label>
				<label class="trans_label" style="width:98px;margin-right:20px;">Part No/SKU No</label>
				<label class="trans_label" style="width:98px;margin-right:54px;">Quantity</label>
				<label class="trans_label" style="width:118px;margin-right:20px;">Receive Date</label>
				
			</div>
			<div class="items" id="items">

				 <!-- <div id="item1" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;">
					<input type="text" class="textbox" id="sno1" style="width:30px;margin-right:20px;"/>
					<input type="text" class="textbox" id="item_desc1" style="width:175px;margin-right:20px;"/>
					<input type="text" class="textbox" id="part_no1" style="width:90px;margin-right:20px;"/>
					<input type="text" class="textbox" id="quantity1" style="width:90px;"/>
					<div class="quantity_unit" id="quantity_unit1" >KG</div>
					<input type="text" class="textbox" id="ship_date1" style="width:110px;margin-right:20px;"/>
					<input type="button" class="del_btn" id="del_btn_1" style="width:110px;margin-right:20px;"/>
				</div>
				
			 	<div id="item2" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;">
					<input type="text" class="textbox" id="sno2" style="width:30px;margin-right:20px;"/>
					<input type="text" class="textbox" id="item_desc2" style="width:175px;margin-right:20px;"/>
					<input type="text" class="textbox" id="part_no2" style="width:90px;margin-right:20px;"/>
					<input type="text" class="textbox" id="quantity2" style="width:90px;margin-right:20px;"/>
					<input type="text" class="textbox" id="ship_date2" style="width:110px;margin-right:20px;"/>
					<input type="button" class="del_btn" id="del_btn_2" style="width:110px;margin-right:20px;"/>
				</div>-->
				
			</div>
			
			<div style="width:100%;float:left;margin-left:40px;height:50px;">
				<input id="add_item_btn" type="button" class="add_item general-button" value="Add Item"/>
			</div>
			
			<div style="width:100%;float:left;margin-left:40px;height:50px;" id="rfq_terms_container">
				<div class="checkContainer"><input type="checkbox" class="checkbox" id="rfq_terms_cond"><div></div></div>
				<label  class="trans_label" style="line-height:19px;margin-left:5px;cursor:pointer;text-decoration:underline;" id="rfq_tc_link">Accept terms & conditions</label>
			</div>
			
			<div class="rfq_form_btns">
						<input type="button" class="gen-btn-Gray" style="margin-right:50px;" value="Reset" onclick="resetRFQForm()">
						<input type="button" class="gen-btn-blue" value="Send" id="add_rfq_btn" onclick="validateRFQ();">
			</div>
			
			<div class="rfq_error" id="rfq_form_error"> 
			</div>
			
		</div>
		
	</div>
	
	<%@include file="rfq_add_item_popup.jsp"%>
	<%@include file="rfq_popup.jsp"%>
	<%@include file="rfq_inquire_popup.jsp"%>
	

	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/RFQ/js/rfq_DT.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/RFQ/js/rfq_form.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/RFQ/js/rfq_popup_fom.js"></script>
	
		<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/RFQ/js/rfq_address.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/RFQ/js/rfq_search_vendor.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/RFQ/js/rfq.js"></script>
	
	
</body>
</html>
<%@page import="core.login.SessionData"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Transaction/Invoice/css/invoice.css" />

<script>
$( "#invoice_due_date" ).datepicker({ dateFormat: "dd-M-yy",minDate: 0 });
//$( "#bill_of_land" ).datepicker({ dateFormat: "dd-M-yy" });
$( "#date_shipped" ).datepicker({ dateFormat: "dd-M-yy",minDate: 0 });
</script>
</head>

<body>
    <%
        Calendar lCDateTime = Calendar.getInstance();
       SessionData sessionObjp = (SessionData)session.getAttribute("UserSessionData");
      sessionObjp.pypl_scs=lCDateTime.getTimeInMillis()*2+"";
      Calendar lCDateTime2 = Calendar.getInstance();
      sessionObjp.pypl_fl=lCDateTime2.getTimeInMillis()+""; 
    %> 
    <form name="paypalForm" action="https://www.sandbox.paypal.com/cgi-bin/webscr" method="post">
            <input type="hidden" name="cmd" value="_xclick" />
            <input type="hidden" name="business" value="dsdilbag345@gmail.com" />
            <input type="hidden" name="password" value="dilbagdssingh" />
            <input type="hidden" name="custom" value="1123" />
            <input type="hidden" name="item_name" value="Plan" />
            <input type="hidden" id="amnt" name="amount" value="0"/>
            <input type="hidden" name="rm" value="1" />
            <input type="hidden" id="rtrn" name="return" value="http://supplymedium.elasticbeanstalk.com/paypal?result=<%=sessionObjp.pypl_scs %>" />
            <input type="hidden" id="cncl_rtrn" name="cancel_return" value="http://supplymedium.elasticbeanstalk.com/paypal?result=<%=sessionObjp.pypl_fl %>" />
            <input type="hidden" name="cert_id" value="API Singature" />                                    
        </form>
	<div class="sub_tab_container" id="invoice_submenu">
		<!-- This is Invoice inner tab bar container -->

		<div class="highlight" id="invoice_request_tab">Payments</div>
		<!-- My Request tab -->
	</div>
	
	<div id="invoice_request_content" style="position:relative;"> <!-- This is the invoice queue. -->
		<div class="DT_border" >
		</div>
		<table id="invoice_list" style="width: 997px;padding-left:1px;">		
			<thead >
				<tr>
					<th class="rowBorder" style="border-left:1px solid #c8c8c8"></th>
					<th class="rowBorder">Invoice ID</th>
					<th class="rowBorder">Date</th>
					<th class="rowBorder">Company</th>
					<th class="rowBorder">Phone</th>
					<th class="rowBorder">Email</th>
					<th class="rowBorder">Payment</th>
					<th class="rowBorder">Status</th>
					<th class="rowBorder" style="border-right:1px solid #c8c8c8">Open</th>
				</tr>
			</thead>
								
			<tbody>
								
			</tbody>
		</table>
	</div>

	<div id="invoice_form_content" style="display:none;" class="invoice_form_content"> <!-- This is the invoice form details -->
		<div class="to_comp">
					<label class="label" style="padding-left:20px;width:65px;color:white;line-height:25px;"> Sold To:</label>
					<input type="text"  placeholder="Search Registered Vendor"  id="to_company" style="width:900px;" class="textbox" onkeyup="getRegVendor();">
		</div>
		
		<input type="hidden" id="selected_ven_key"/>
		
		
		<div class="invoice_form">
		<div id="ven_search_result" class="com_search_result" style="display:none;left:84px;top:-8px;">		
			</div>
			
			<input type="hidden" id="form_trans_id" value="-1"/>
			<div class="invoice_buyer_det">
				<div class="sub_heading">Invoice Details</div>
				
				<div class="trans_row" style="float:left;">
					<label class="trans_label"> Invoice Payment Due Date </label>
					<input type="text" class="textbox" id="invoice_due_date"  readonly/>
				</div>
				
				<div class="trans_row" style="float:left;">
					<label class="trans_label"> Purchase Order Number </label>
					<input type="text" class="textbox" id="po_no"  />
				</div>
				
				<div class="trans_row" style="float:left;">
					<label class="trans_label"> Please Remit To </label>
					<input type="text" class="textbox_disable" id="buyer_name"  disabled/>
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
				
				
			</div>
			<div class="addr_sep"></div>
			<div class="invoice_supplier_det">
				<div class="sub_heading">Buyer Details</div>
				
				<div class="trans_row">
					<div class="checkContainer"><input type="checkbox" class="checkbox" id="non_po_invoice"><div></div></div>
				<label for="non_po_invoice" class="trans_label" style="line-height:19px;margin-left:5px;">Non P.O Invoice</label>
				</div>
				
				
				<div class="trans_row">
					<div class="checkContainer"><input type="checkbox" class="checkbox" id="outside_supplier"><div></div></div>
				<label for="outside_supplier" class="trans_label" style="line-height:19px;margin-left:5px;">Outside Buyer</label>
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
				
				<div class="trans_row">
					<div class="checkContainer"><input type="checkbox" class="checkbox" id="is_diff_addr"><div></div></div>
					<label for="is_diff_addr" class="trans_label" style="line-height:19px;margin-left:5px;width:360px;line-height:15px;">In case the shipping address is different than one given above</label>
				</div>
				
				<div class="trans_row" >
						<label class="trans_label"> Email </label>
						<input type="text" class="textbox" id="diff_addr_email" disabled/>
				</div>
				
			</div>
			<div class="items_head">
				<label class="trans_label" style="width:40px;margin-right:10px;">S.No</label>
				<label class="trans_label" style="width:110px;margin-right:10px;">Item Description</label>
				<label class="trans_label" style="width:100px;margin-right:10px;">Part No/SKU No</label>
                                <label class="trans_label" style="width:100px;margin-right:10px;">Barcode</label>
				<label class="trans_label" style="width:105px;margin-right:10px;">Quantity Ordered</label>
				<label class="trans_label" style="width:105px;margin-right:10px;">Quantity Shipped</label>
				<label class="trans_label" style="width:95px;margin-right:10px;">Price per unit</label>
                                <label class="trans_label" style="width:60px;margin-right:10px;">Multiplier</label>
                                <label class="trans_label" style="width:118px;margin-right:10px;">Total</label>
			</div>
			<div class="items" id="items">

			</div>
			
			<div style="width:100%;float:left;margin-left:40px;height:50px;">
				<input id="add_item_btn" type="button" class="add_item general-button" value="Add Item"/>
			</div>
			
			<div class="price_det" id="price_det">
				<div class="price_det_content">
					<label class="price_det_lbl" id="tot_list_price_lbl"> Total List Price: </label>
					
					<label class="price_det_lbl" id="tot_list_price_amt"> 0 </label>
				</div>
				<div class="price_det_content">
					<label class="price_det_lbl" id="tax_lbl"> Tax in Percentage: </label>
					
					<label class="price_det_lbl" id="tax_amt" style="min-width:35px;width:auto;"></label><label>%</label>
				</div>
				<div class="price_det_content">
					<label class="price_det_lbl" id="frei_lbl"> Freight & Handling: </label>
					
					<label class="price_det_lbl" id="frei_hand_amt"> 10  </label>
				</div>
				<div class="price_det_content">
					<label class="price_det_lbl" id="tot_price_lbl"> Total Price: </label>
					<label class="price_det_lbl" id="tot_price_amt" ></label>
				</div>
			</div>
			
			
			<div class="sub_heading" style="padding-left:40px;">Bill of Landing Information</div>
			
			<div class="shipping_det_head">
				<label class="trans_label" style="width: 200px;margin-right: 9px;">Carrier</label>
				<label class="trans_label" style="width: 112px;margin-right: 17px;">Bill of Landing No.</label>
				<label class="trans_label" style="width:98px;margin-right: 63px;">Freight Weight</label>
				<label class="trans_label" style="width:98px;margin-right:25px;">Date Shipped</label>
			</div>
			<div class="shipping_det_content">
				<div style="float:left"><input type="text" class="textbox"  id="carrier" /></div>
				<input type="text" class="textbox" id="bill_of_land" style="margin-left:20px;width:100px">
				<input type="text" class="textbox" id="freight_weight" style="margin-left:20px;width:80px;">
				<div class="quantity_unit" id="quantity_freight_unit" >KG</div>
				
				<div class="quan_units" id="units_quantity_freight_unit" style="display:none;left:470px;">
				</div>
				<input type="text" class="textbox" id="date_shipped" style="margin-left:20px;width:100px;" readonly>
			</div>
			
			<div style="width:100%;float:left;margin-left:40px;height:50px;" id="invoice_terms_container">
				<div class="checkContainer"><input type="checkbox" class="checkbox" id="invoice_terms_cond"><div></div></div>
				<label for="invoice_terms_cond" class="trans_label" style="line-height:19px;margin-left:5px;cursor:pointer;text-decoration:underline;" id="invoice_tc_link">Accept terms & conditions</label>
			</div>
			
			<div class="invoice_form_btns">
						<input type="button" class="gen-btn-Gray" style="margin-right:50px;" value="Reset" onclick="resetInvoiceForm()">
						<input type="button" class="gen-btn-blue" value="Send" id="add_invoice_btn" onclick="validateInvoice();">
			</div>
			
			<div class="invoice_error" id="invoice_form_error"> 
			</div>
			
		</div>
		
	</div>
	

	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/Invoice/js/payment.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/Invoice/js/invoice_form.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/Invoice/js/invoice_popup_form.js"></script>
	
		<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/Invoice/js/invoice_address.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/Invoice/js/invoice_search_vendor.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Transaction/Invoice/js/invoice.js"></script>
	
	
</body>
</html>
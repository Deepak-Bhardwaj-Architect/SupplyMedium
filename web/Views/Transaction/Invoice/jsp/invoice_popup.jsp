<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Transaction/Invoice/css/invoice_popup.css" />
<script>

//$( "#popup_bill_of_land" ).datepicker({ dateFormat: "dd-M-yy" });
$( "#popup_date_shipped" ).datepicker({ dateFormat: "dd-M-yy" });
</script>
</head>
<body>

<div style="display: none; z-index: 1000;" class="Custome_Popup_Window"
		id="invoice_popup">
		<div class="Cus_Popup_Outline invoice_popup_outline">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title"
				style="border-radius: 0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Invoice</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>

			<div id="invoice_form_popup_content" style=margin:0px;float:left;height:auto;"
				class="invoice_form_content">
				<!-- This is the Invoice form details
				<div class="to_comp">
					<label class="label"
						style="padding-left: 20px; width: 45px; color: white; line-height: 25px;">To:</label>
					<input type="text" id="to_company_popup" disabled style="width: 890px;"
						class="textbox" onkeyup="getNRVendor();">
				</div> -->
				<div style="float: right;"><a onclick="this.href='invoice_pdf_download?trans_id='+$('#trans_id').val()+'&from='+$('#popup_buyer_name').val()+'&to='+$('#to_company_popup').val()+'&date='+$('#dt').val()" target="_blank">download</a></div>
                                <div class="invoice_popup_form" id="invoice_popup_form" style="margin-top:10px;overflow:auto;float:left;height:700px;width:99%;">
				
					<input type="hidden" id="invoice_id" />
					
					<input type="hidden" id="trans_id" />
					
					<input type="hidden" id="reply_to_comp" />
					
					<input type="hidden" id="reply_to_user" />
					
					<input type="hidden" id="status" />
					
					<input type="hidden" id="items_count" />
					
					<input type="hidden" id="popupSNo">
                                        
                                        <input type="hidden" id="dt">
					
			<div class="invoice_buyer_det">
				<div class="sub_heading">Invoice Details</div>
				
				<div class="trans_row" style="float:left;">
					<label class="trans_label"> Invoice Number</label>
					<input type="text" class="textbox_disable" id="popup_invoice_no"  />
				</div>
				
				<div class="trans_row" style="float:left;">
					<label class="trans_label"> Invoice Payment Due Date </label>
					<input type="text" class="textbox" id="popup_invoice_due_date"  />
				</div>
				
				<div class="trans_row" style="float:left;">
					<label class="trans_label"> Purchase Order Number </label>
					<input type="text" class="textbox_disable" id="popup_po_no"  />
				</div>
				
				<div class="trans_row" style="float:left;">
					<label class="trans_label"> Please Remit To </label>
					<input type="text" class="textbox_disable" id="popup_buyer_name"  disabled/>
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> Country </label>
					<input type="text" class="textbox_disable" id="popup_buyer_country" disabled/>
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> State/Province </label>
					<input type="text" class="textbox_disable" id="popup_buyer_state" disabled/>
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> City </label>
					<input type="text" class="textbox_disable" id="popup_buyer_city" disabled/>
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> Address </label>
					<input type="text" class="textbox_disable" id="popup_buyer_addr" disabled/>
				</div>
				
				<div class="trans_row">
					<label class="trans_label"> Zip Code/Postal Code </label>
					<input type="text" class="textbox_disable" id="popup_buyer_zipcode" disabled />
				</div>
				
				
			</div>
					<div class="addr_sep"></div>
					<div class="invoice_supplier_det">
						<div class="sub_heading">Buyer Details</div>

						<div class="trans_row">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="popup_non_po_invoice"><div></div></div>
							<label for="popup_non_po_invoice" class="trans_label" style="line-height:19px;margin-left:5px;">Non P.O Invoice</label>
						</div>
				
						<div class="trans_row">
							<div class="checkContainer">
								<input type="checkbox" class="checkbox" id="popup_outside_supplier">
								<div></div>
							</div>
							<label for="popup_outside_supplier" class="trans_label"
								style="line-height: 19px; margin-left: 5px;">Outside
								Supplier</label>
						</div>

						<div class="popup_supplier_address" style="width: 100%; height: 300px;">
							
							<div class="trans_row">
								<label class="trans_label"> Buyer Name </label> <input type="text"
                                                                                                                       class="textbox_disable" id="to_company_popup" disabled/>
							</div>
							
							<div class="trans_row">
								<label class="trans_label"> Country </label> <input type="text"
									class="textbox_disable" id="popup_supplier_country"  disabled/>
							</div>

							<div class="trans_row">
								<label class="trans_label"> State/Province </label> <input type="text"
									class="textbox_disable" id="popup_supplier_state"  disabled/>
							</div>

							<div class="trans_row">
								<label class="trans_label"> City </label> <input type="text"
									class="textbox_disable" id="popup_supplier_city"  disabled/>
							</div>

							<div class="trans_row">
								<label class="trans_label"> Address </label> <input type="text"
									class="textbox_disable" id="popup_supplier_addr"  disabled/>
							</div>

							<div class="trans_row">
								<label class="trans_label"> Zip Code/Postal Code </label> <input type="text"
									class="textbox_disable" id="popup_supplier_zipcode"  disabled/>
							</div>

						</div>

						<div class="popup_outside_sup_email_content"
							style="width: 480px; height: 300px; display: none">

							<div class="trans_row" style="margin-top: 100px;">
								<label class="trans_label"> Email </label> <input type="text"
									class="textbox" id="popup_email" />
							</div>

						</div>
						
						<div class="trans_row">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="popup_is_diff_addr"><div></div></div>
							<label for="popup_is_diff_addr" class="trans_label" style="line-height:19px;margin-left:5px;width:360px;line-height:15px;">In case the shipping address is different than one given above</label>
						</div>
				
                                                <div class="trans_row" style="width:445px;">
								<label class="trans_label" style="width:80px;"> Email </label>
								<input type="text" class="textbox" id="popup_diff_addr_email" />
                                                                <input style="margin-left:20px ;width:130px;" type="button" class="gen-btn-blue" value="Update Address" onclick="update_shiping_address($('#invoice_id').val(),$('#popup_diff_addr_email').val());"/>
						</div>

						
					</div>
					
					<div class="items_head">
				<label class="trans_label" style="width:38px;margin-right:22px;">S.No</label>
				<label class="trans_label" style="width:188px;margin-right:13px;">Item Description</label>
				<label class="trans_label" style="width:98px;margin-right:20px;">Part Number</label>
				<label class="trans_label" style="width:125px;margin-right:25px;">Quantity Ordered</label>
				<label class="trans_label" style="width:125px;margin-right:25px;">Quantity Shipped</label>
				<label class="trans_label" style="width:118px;margin-right:4px;">Price</label>
			</div>
					<div class="items" id="popup_items">
					</div>

					<div
						style="width: 900px; float: left; margin-left: 40px; height: 50px;">
						<input id="popup_add_item_btn" type="button"
							class="add_item general-button" value="Add Item" style="display:none;"/>
					</div>
					
					
					<div class="price_det" id="price_det">
						<div class="price_det_content">
								<label class="price_det_lbl" id="tot_list_price_lbl"> Total List Price: </label>
					
								<label class="price_det_lbl" id="popup_tot_list_price_amt"> 0 </label>
						</div>
						<div class="price_det_content">
							<label class="price_det_lbl" id="tax_lbl"> Tax in Percentage: </label>
					
							<label class="price_det_lbl" id="popup_tax_amt" style="min-width:35px;width:auto;"></label><label>%</label>
						</div>
						<div class="price_det_content">
							<label class="price_det_lbl" id="frei_lbl"> Freight & Handling: </label>
					
							<label class="price_det_lbl" id="popup_frei_hand_amt"> 10  </label>
						</div>
						<div class="price_det_content">
							<label class="price_det_lbl" id="tot_price_lbl"> Total Price: </label>
					
							<label class="price_det_lbl" id="popup_tot_price_amt"> 0 </label>
						</div>
					</div>
					
					<div class="sub_heading" style="padding-left:40px;">Bill of Landing Information</div>
			
			<div class="shipping_det_head">
				<label class="trans_label" style="width: 200px;margin-right:9px;">Carrier</label>
				<label class="trans_label" style="width:112px;margin-right:17px;">Bill of Landing No.</label>
				<label class="trans_label" style="width:138px;margin-right:22
				px;">Freight Weight</label>
				<label class="trans_label" style="width:98px;margin-right:25px;">Date Shipped</label>
			</div>
			<div class="shipping_det_content">
				<div style="float:left"><input type="text" class="textbox"  id="popup_carrier" /></div>
				<input type="text" class="textbox" id="popup_bill_of_land"  style="margin-left:20px;width:100px">
				<input type="text" class="textbox" id="popup_freight_weight" style="margin-left:20px;width:80px;">
				<div class="quantity_unit" id="popup_quantity_freight_unit" >KG</div>
				<div class="quan_units" id="units_popup_quantity_freight_unit" style="display:none;left:470px;">
				</div>
				
				<input type="text" class="textbox" id="popup_date_shipped" readonly style="margin-left:20px;width:100px;">
			</div>
			
					
					<div class="inquires" id="popup_inquires">
						
					</div>
					
					<div class="new_inquire" id="invoice_popup_new_inquire">
						<div class="inquire_row">
							<label class="inquire_by"> Inquire: </label>
							<textarea class="inquire_det" id="new_inquire_message">  </textarea>
							<input type="button" class="gen-btn-blue" style="margin-left:30px;margin-top:7px;" id="invoice_inquire_send"  value="Send" >
							<label class="invoice_error" style="width:300px;margin-left: 125px;margin-top: 10px;" id="inquire_error"></label>
						</div>
					</div>
					
					<div id="supplier_ctrls" class="supplier_controls">
						<input type="button" class="gen-btn-blue" style="margin-left:30px;" id="invoice_accept_btn" value="Accept">
						<input type="button" class="gen-btn-red" style="margin-left:30px;" id="invoice_reject_btn" value="Reject" id="add_invoice_btn" >
						<input type="button" class="gen-btn-blue" style="margin-left:30px;"  id="supp_invoice_inquire_btn" value="Inquire">
					</div>
					
					<div id="buyer_ctrls" class="buyer_controls">
						<input type="button" class="gen-btn-blue"  style="margin-left:30px;" id="invoice_edit_btn" value="Edit">
						<input type="button" class="gen-btn-blue"  style="margin-left:30px;" id="buy_invoice_inquire_btn" value="Inquire">
					</div>
					
					<div class="invoice_close" id="invoice_close" >
							<input type="button" class="gen-btn-blue"  style="margin-left:30px;" id="invoice_close_btn" value="Close">
					</div>
					
					<div class="invoice_update" id="invoice_update" style="display:none;">
						<input type="button" class="gen-btn-blue"  style="margin-left:30px;" id="invoice_update_btn" value="Update" > 
					</div>
					
					
					<div class="new_inquire" id="reject_reason" style="display:none">
					<div class="inquire_row">
							<label class="inquire_by"> Reason: </label>
							<select class="selectbox" id="reject_reason_text"></select>
							<input type="button" class="gen-btn-blue" style="margin-left:30px;margin-top:7px;" id="reject_reason_send"  value="Send" >
						</div>
						
					</div>
				
					<!--<div id="inquire_ctrls" class="inquire_controls" >
						  <input type="button" class="gen-btn-Gray" style="margin-left:30px;" id="invoice_inquire_cancel" value="Cancel">
						<input type="button" class="gen-btn-blue" style="margin-left:30px;" id="invoice_inquire_send"  value="Send" >
					</div>-->
					
					<div class="invoice_error" id="invoice_invoicepup_form_error"> 
					</div>
			

				</div>

			</div>
		</div>
	</div>

	

</body>
</html>
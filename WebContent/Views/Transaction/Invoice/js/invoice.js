var invoiceText="";
// This method is called while onload
$(document).ready(
		function() 
		{	
			initInvoiceDataTable();
			
			 getInvoiceMyAddress();
			 
			 fetchInvoiceRequest();
			 
			 getRejectReasons();
			 
			 getCourier();
			
			$("#invoice_request_tab").click(invoiceRequestTabClicked);
			
			$("#invoice_form_tab").click(invoiceFormTabClicked);
			
			$("#add_item_btn").click(showAddItemPopup);
			
			$( "#ship_date1" ).datepicker();
			
			$('#outside_supplier').change(outsideSupplierEvent);
			
			$('#non_po_invoice').change(nonPOEvent);
			
			$('#is_diff_addr').change(isDiffAddrEvent);
			
			$("#save_item_btn").click(saveItem);
			
			 $('select.selectbox').customSelect();
			 
			 
			 $("#invoice_accept_btn").click(acceptInvoice);
			 
			 $("#reject_reason_send").click(rejectInvoice);
			 
			 $("#invoice_reject_btn").click(showRejectReasonBox);
			 
			 $("#supp_invoice_inquire_btn").click(suppInquireBtnClicked);
			 
			 $("#buy_invoice_inquire_btn").click(buyInquireBtnClicked);
			 
			 $("#invoice_close_btn").click(closeInvoicePopup);
			 
			 
			 $("#invoice_inquire_send").click(inquireInvoice);
			 
			 $("#chat_inquire_save_btn").click(chatInquireInvoice);
			 
			 $("#quantity_ordered_unit").click(showQuantityUnits);
			 
			 $("#quantity_shipped_unit").click(showQuantityUnits);
			 
			 $("#quantity_freight_unit").click(showQuantityUnits);
			 
			 $("#popup_quantity_freight_unit").click(showQuantityUnits);
			 
			 $("#currency").click(showCurrencyList);
			 
			 
			 $("#invoice_edit_btn").click(editButtonClicked);
			 
			 $("#popup_add_item_btn").click(showAddItemPopup1);
			 
			 $("#invoice_update_btn").click(updateInvoice);
			 
			 $("#tax_amt").text(mainTaxPer);
			 
			 $("#invoice_popup_form").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
			 
			 $("#invoice_tc_link").click( invoiceTCClicked );
			 
				
			 fetchInvoiceRequest();
			 
			 
			 $("body").click(function()
					 {
						$("#units_quantity_ordered_unit").hide();
						
					 });
						
			$("#quantity_ordered_unit").click(function(event)
					{
						$("#units_quantity_ordered_unit").show();
						
						event.stopPropagation();
					});
			
			$("body").click(function()
				{
					$("#units_quantity_shipped_unit").hide();
				});
			$("#quantity_shipped_unit").click(function(event)
					{
						$("#units_quantity_shipped_unit").show();
						event.stopPropagation();
					});
			
			 $("body").click(function()
					 {
						$("#units_quantity_freight_unit").hide();
						
					 });
						
			$("#quantity_freight_unit").click(function(event)
					{
						$("#units_quantity_freight_unit").show();
						
						event.stopPropagation();
					});
			 
			$("body").click(function()
					{
						$("#currency_currency").hide();
					});
				$("#currency").click(function(event)
						{
							$("#currency_currency").show();
							event.stopPropagation();
						});
				
				
			 
		});


/* This method is called when user click the Invoice Terms and condition link */
function invoiceTCClicked()
{
	var toComp=$("#to_company").val();
	
	if(toComp!="")
	{
		$("#check_box_id").val("invoice_terms_cond");
	
		TCPopup( invoiceText );
	}
}


/* This method is called when user click the 'My Request' tab */

function invoiceRequestTabClicked()
{
	$("#invoice_request_content").show();
	$("#invoice_form_content").hide();
	
	$("#invoice_request_tab").removeClass("normal");
	$("#invoice_request_tab").addClass("highlight");
	
	$("#invoice_form_tab").removeClass("highlight");
	$("#invoice_form_tab").addClass("normal");
}

/* This method is called when user click the 'invoice Form' tab */

function invoiceFormTabClicked()
{
	$("#invoice_request_content").hide();
	$("#invoice_form_content").show();
	
	$("#invoice_request_tab").addClass("normal");
	$("#invoice_request_tab").removeClass("highlight");
	
	$("#invoice_form_tab").addClass("highlight");
	$("#invoice_form_tab").removeClass("normal");
}


/* This method is called when user click the 'Add Item' tab.
 * It shows the add item invoicepup view.Using this view user can
 * add the items to invoice*/

function showAddItemPopup()
{
	$("#popup_item_desc").val("");
	$("#popup_part_no").val("");
	$("#popup_quantity").val("");
	$("#popup_ship_date").val("");
	$("#pop_multiplier").val("");
	$("#popup_price").val("");
	
	$("#invoice_add_item_popup").show();
	
	$("#from_form").val("invoiceForm");
	
	$("#popup_item_desc").focus();
}


/* This method is called when user click the 'Add Item' tab from Edit view.
 * It shows the add item popup view.Using this view user can
 * add the items to invoice*/

function showAddItemPopup1()
{
	$("#popup_item_desc").val("");
	$("#popup_part_no").val("");
	$("#popup_quantity_ordered").val("");
	$("#popup_quantity_shipped").val("");
	$("#popup_price").val("");
	
	$("#invoice_add_item_popup").show();
	
	$("#from_form").val("PopUpInvoiceForm");
	
	$("#popup_item_desc").focus();
}
/* this method is called when user click the outside supplier checkbox.
 * User select the outside supplier check box, outside supplier email only display.
 * User unselect the outside supplier check box, supplier address details displayed.
 */
function outsideSupplierEvent()
{
	 if ($("#outside_supplier").is(':checked')) 
	 {
		 $(".supplier_address").hide();
		 $(".outside_sup_email_content").show();
		 $("#invoice_terms_container").hide();
		 
	 }
	 else
	 {
		 $(".supplier_address").show();
		 $(".outside_sup_email_content").hide();
		 $("#invoice_terms_container").show();
	 }
}

/* this method is called when user click the Non P.O Invoice checkbox.
 * User select the Non P.O Invoice check box, po number can be editable.
 * User unselect the Non P.O Invoice check box,  po number uneditable.
 */
function nonPOEvent()
{
	 if ($("#non_po_invoice").is(':checked')) 
	 {
		 $("#po_no").attr("disabled", "disabled");
	 }
	 else
	 {
		 $("#po_no").removeAttr("disabled"); 
	 }
}

/* this method is called when user click the is_diff_addr checkbox.
 * User select the is_diff_addr check box, po number can be editable.
 * User unselect the is_diff_addrr check box,  po number uneditable.
 */
function isDiffAddrEvent()
{
	 if ($("#is_diff_addr").is(':checked')) 
	 {
		 $("#diff_addr_email").removeAttr("disabled"); 
	 }
	 else
	 {
		 $("#diff_addr_email").attr("disabled", "disabled");
		
	 }
}
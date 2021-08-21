var poText="";

// This method is called while onload
$(document).ready(
		function() 
		{	
			initPODataTable();
			
			 getPOMyAddress();
			 
			 fetchPORequest();
			
			$("#po_request_tab").click(poRequestTabClicked);
			
			$("#po_form_tab").click(poFormTabClicked);
			
			$("#add_item_btn").click(showAddItemPopup);
			
			$( "#ship_date1" ).datepicker();
			
			$('#outside_supplier').change(outsideSupplierEvent);
			
			$("#save_item_btn").click(saveItem);
			
			 $('select.selectbox').customSelect();
			 
			 
			 $("#po_accept_btn").click(acceptPO);
			 
			 $("#reject_reason_send").click(rejectPO);
			 
			 $("#po_reject_btn").click(showRejectReasonBox);
			 
			 $("#supp_po_inquire_btn").click(suppInquireBtnClicked);
			 
			 $("#buy_po_inquire_btn").click(buyInquireBtnClicked);
			 
			 $("#po_close_btn").click(closePOPopup);
			 
			 
			 $("#po_inquire_send").click(inquirePO);
			 
			 $("#chat_inquire_save_btn").click(chatInquirePO);
			 
			 $("#quantity_unit").click(showQuantityUnits);
			 
			 
			 $("#currency").click(showCurrencyList);
			 
			 
			 $("#po_edit_btn").click(editButtonClicked);
			 
			 $("#popup_add_item_btn").click(showAddItemPopup1);
			 
			 $("#po_update_btn").click(updatePO);
			 
			 $("#tax_amt").text(mainTaxPer);
			 
			 /*$("#po_inquire_cancel").click(poInquireCancelBtnClicked);*/
			 
			 $("#po_popup_form").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
			 
			 $("#po_tc_link").click( poTCClicked );
			 
				
			 fetchPORequest();
			 
			 $("body").click(function()
					 {
						$("#units_quantity_unit").hide();
						
					 });
						
			$("#quantity_unit").click(function(event)
					{
						$("#units_quantity_unit").show();
						
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


/* This method is called when user click the RFQ Terms and condition link */
function poTCClicked()
{
	var toComp=$("#to_company").val();
	
	if(toComp!="")
	{
		$("#check_box_id").val("po_terms_cond");
	
		TCPopup( poText );
	}
}


/* This method is called when user click the 'My Request' tab */

function poRequestTabClicked()
{
	$("#po_request_content").show();
	$("#po_form_content").hide();
	
	$("#po_request_tab").removeClass("normal");
	$("#po_request_tab").addClass("highlight");
	
	$("#po_form_tab").removeClass("highlight");
	$("#po_form_tab").addClass("normal");
}

/* This method is called when user click the 'PO Form' tab */

function poFormTabClicked()
{
	$("#po_request_content").hide();
	$("#po_form_content").show();
	
	$("#po_request_tab").addClass("normal");
	$("#po_request_tab").removeClass("highlight");
	
	$("#po_form_tab").addClass("highlight");
	$("#po_form_tab").removeClass("normal");
}


/* This method is called when user click the 'Add Item' tab.
 * It shows the add item popup view.Using this view user can
 * add the items to PO*/

function showAddItemPopup()
{
	$("#popup_item_desc").val("");
	$("#popup_part_no").val("");
	$("#popup_quantity").val("");
	$("#popup_ship_date").val("");
	$("#pop_multiplier").val("");
	$("#popup_price").val("");
	
	$("#po_add_item_popup").show();
	
	$("#from_form").val("POForm");
	
	$("#popup_item_desc").focus();
}


/* This method is called when user click the 'Add Item' tab from Edit view.
 * It shows the add item popup view.Using this view user can
 * add the items to PO*/

function showAddItemPopup1()
{
	$("#popup_item_desc").val("");
	$("#popup_part_no").val("");
	$("#popup_quantity").val("");
	$("#popup_ship_date").val("");
	$("#pop_multiplier").val("");
	$("#popup_price").val("");
	
	$("#po_add_item_popup").show();
	
	
	$("#from_form").val("PopUpPOForm");
	
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
		 $("#po_terms_container").hide();
		 
	 }
	 else
	 {
		 $(".supplier_address").show();
		 $(".outside_sup_email_content").hide();
		 $("#po_terms_container").show();
	 }
}
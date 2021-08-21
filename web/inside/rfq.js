var rfqText = "";

// This method is called while onload
$(document).ready(
		function() 
		{	
			hideAjaxLoader();
			
			//initRFQDataTable();
			
			 //getRFQMyAddress();
			 
			// hideAjaxLoader();
			
			$("#rfq_request_tab").click(rfqRequestTabClicked);
			
			$("#rfq_form_tab").click(rfqFormTabClicked);
			
			$("#add_item_btn").click(showAddItemPopup);
			
			$( "#ship_date1" ).datepicker();
			
			$('#outside_supplier').change(outsideSupplierEvent);
			
			$("#save_item_btn").click(saveItem);
			
			 $('select.selectbox').customSelect();
			 
			 
			 $("#rfq_accept_btn").click(acceptRFQ);
			 
			 $("#rfq_reject_btn").click(rejectRFQ);
			 
			 $("#supp_rfq_inquire_btn").click(suppInquireBtnClicked);
			 
			 $("#buy_rfq_inquire_btn").click(buyInquireBtnClicked);
			 
			 $("#rfq_close_btn").click(closeRFQPopup);
			 
			 
			 $("#rfq_inquire_send").click(inquireRFQ);
			 
			 $("#chat_inquire_save_btn").click(chatInquireRFQ);
			 
			 $("#quantity_unit").click(showQuantityUnits);
			 
			 
			 $("#rfq_edit_btn").click(editButtonClicked);
			 
			 $("#popup_add_item_btn").click(showAddItemPopup1);
			 
			 $("#rfq_update_btn").click(updateRFQ);
			 
			 /*$("#rfq_inquire_cancel").click(rfqInquireCancelBtnClicked);*/
			 
			$("#rfq_popup_form").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
			
			$("#rfq_tc_link").click( rfqTCClicked );
			 
			
			 //fetchRFQRequest();
			 
			 $("body").click(function()
					 {
						$("#units_quantity_unit").hide();
					 });
						
			$("#quantity_unit").click(function(event)
					{
						$("#units_quantity_unit").show();
						event.stopPropagation();
					});
		});


/* This method is called when user click the RFQ Terms and condition link */
function rfqTCClicked()
{
	var toComp=$("#to_company").val();
	
	if(toComp!="")
	{
		$("#check_box_id").val("rfq_terms_cond");
		
		TCPopup( rfqText );
	}
	
}

/* This method is called when user click the 'My Request' tab */

function rfqRequestTabClicked()
{
	$("#rfq_request_content").show();
	$("#rfq_form_content").hide();
	
	$("#rfq_request_tab").removeClass("normal");
	$("#rfq_request_tab").addClass("highlight");
	
	$("#rfq_form_tab").removeClass("highlight");
	$("#rfq_form_tab").addClass("normal");
}

/* This method is called when user click the 'RFQ Form' tab */

function rfqFormTabClicked()
{
	$("#rfq_request_content").hide();
	$("#rfq_form_content").show();
	
	$("#rfq_request_tab").addClass("normal");
	$("#rfq_request_tab").removeClass("highlight");
	
	$("#rfq_form_tab").addClass("highlight");
	$("#rfq_form_tab").removeClass("normal");
	
	
}


/* This method is called when user click the 'Add Item' tab.
 * It shows the add item popup view.Using this view user can
 * add the items to RFQ*/

function showAddItemPopup()
{
	$("#popup_item_desc").val("");
	$("#popup_part_no").val("");
        $("#popup_brcd").val("");
	$("#popup_quantity").val("");
	$("#popup_ship_date").val("");
	
	$("#rfq_add_item_popup").show();
	
	$("#from_form").val("RFQForm");
	
	$("#popup_item_desc").focus();
}


/* This method is called when user click the 'Add Item' tab from Edit view.
 * It shows the add item popup view.Using this view user can
 * add the items to RFQ*/

function showAddItemPopup1()
{
	$("#popup_item_desc").val("");
	$("#popup_part_no").val("");
        $("#popup_brcd").val("");
	$("#popup_quantity").val("");
	$("#popup_ship_date").val("");
	
	$("#rfq_add_item_popup").show();
	
	$("#from_form").val("PopUpRFQForm");
	
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
		 
		 $("#rfq_terms_container").hide();
		 
	 }
	 else
	 {
		 $(".supplier_address").show();
		 $(".outside_sup_email_content").hide();
		 
		 $("#rfq_terms_container").show();
	 }
}
fetchCountry();
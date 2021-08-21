var action="";

/* This method is called when user click the Accept button in 
 * PO from. It change the Invoice status as POAccepted.
 */
function acceptInvoice()
{
	$("#invoice_accept_btn").attr("disabled", "disabled");
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var invoiceId = $("#invoice_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var status = $("#status").val();
	
	status = status.replace(" ","");
	
	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/InvoiceServlet",
		   data:{ 
		        'RequestType': 'ChangeStatus', 
		        'InvoiceId':invoiceId,
		        'TransId':transId,
		        'FromRegnKey':myRegnKey,
		        'FromUserKey':myUserKey,
		        'ToRegnKey':toRegnKey,
		        'ToUserKey':toUserKey,
		        'Status':status,
		        'Action':'Accept'
		    } ,
		   cache:false,
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				   closeInvoicePopup();
				   
				   ShowToast( resJSON.message,2000 );
                                   $("#invoice_accept_btn").removeAttr("disabled");
				   
				   fetchInvoiceRequest();
				}

				else 
				{
					closeInvoicePopup();
					
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
//			   alert("Exception");
			}
		 });
}

/* This is shows the reject reason text box */
function showRejectReasonBox()
{
	$("#reject_reason").show();
	
	$("#supplier_ctrls").hide();
}

/* This method is called when user click the Reject button in 
 * Invoice from. It change the PO status as PORejected.
 */
function rejectInvoice()
{
        
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var invoiceId = $("#invoice_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var status = $("#status").val();
	
	status = status.replace(" ","");
	
	var rejectReason = $("#reject_reason_text").val();
	
	showAjaxLoader();
        $("#invoice_reject_btn").attr("disabled", "disabled");
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/TransRejectServlet",
		   data:{ 
			   'FromRegnKey':myRegnKey,
			   'FromUserKey':myUserKey,
			   'ToRegnKey':toRegnKey,
		       'ToUserKey':toUserKey,
		       'TransId':transId,
		       'TransType':'Invoice',
		       'TransTypeId':invoiceId,
		       'RejectReason':rejectReason,
		       'Status':status,
		       'Action':'Reject'
		    } ,
		   cache:false,
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				   closeInvoicePopup();
				   
				   ShowToast( resJSON.message,2000 );
				   $("#invoice_reject_btn").removeAttr("disabled");
				   fetchInvoiceRequest();
				}

				else 
				{
					closeInvoicePopup();
					
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
//			   alert("Exception");
			}
		 });
}

/* This method called when Supplier click the inquire button.
 * After clicking this button it shows the inquire details entry
 * text box.here Supplier can enter the details and send to vendor.
 */
function suppInquireBtnClicked()
{
	$("#invoice_popup_new_inquire").show();
	
	$("#supplier_ctrls").hide();
	
	$("#inquire_ctrls").show();
	
	action = "Inquire";
}

/* This method called when Buyer click the inquire button.
 * After clicking this button it shows the inquire details entry
 * text box.here user Supplier enter the details and send to vendor.
 */
function buyInquireBtnClicked()
{
	$("#invoice_popup_new_inquire").show();
	
	$("#supplier_ctrls").hide();
	
	$("#buyer_ctrls").hide();
	
	$("#inquire_ctrls").show();
	
	action = "Update";
}

/* This method is called when user click the send button after entering
 * the inquire details. It change the PO status as POInquired.
 */
function inquireInvoice()
{
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var invoiceId = $("#invoice_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var details = $("#new_inquire_message").val().trim();
	
	var status = $("#status").val();
	
	status = status.replace(" ","");
	
	if( details === "" )
	{
		$("#inquire_error").text("Enter inquire details");
		
		return;
	}
	else
	{
		$("#inquire_error").text("");
	
	}
	
	showAjaxLoader();
        $("#supp_invoice_inquire_btn").attr("disabled", "disabled");
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/TransInquireServlet",
		   data:{ 
		        'RequestType': 'AddInquire',
		        'TransId':transId,
		        'TransType':'Invoice',
		        'TransTypeId':invoiceId,
		        'FromRegnKey':myRegnKey,
		        'FromUserKey':myUserKey,
		        'ToRegnKey':toRegnKey,
		        'ToUserKey':toUserKey,
		        'Details':details,
		        'Status':status,
		        'Action':action,
		        
		    } ,
		   cache:false,
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				   closeInvoicePopup();
					
				   ShowToast( resJSON.message,2000 );
				   $("#supp_invoice_inquire_btn").removeAttr("disabled");
				   fetchInvoiceRequest();
				}

				else 
				{
					 closeInvoicePopup();
					 
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
//			   alert("Exception");
			}
		 });
}

/* this method is used to close the Invoice popup view */

function closeInvoicePopup()
{
	$("#invoice_popup").hide();
	
	$("#from_form").val("POForm");
}

/* this is used to close the Inquire poup view */
function closeInquirePopup()
{
	$("#invoice_inquire_popup").hide();
}

/* This method is called when user click the inquire send button from Chat popup view */
function chatInquireInvoice()
{
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var invoiceId = $("#chat_invoice_id").val();
	
	var transId   = $("#chat_trans_id").val();
	
	var toRegnKey = $("#chat_reply_to_comp").val();
	
	var toUserKey = $("#chat_reply_to_user").val();
	
	var details   = $("#chat_new_inquire_message").val().trim();
	
	var status    = $("#chat_status").val();
	
	status = status.replace(" ","");
	
	if( details === "" )
	{
		$("#inquire_error").text("Enter inquire details");
		
		return;
	}
	else
	{
		$("#inquire_error").text("");
	
	}
	
	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/TransInquireServlet",
		   data:{ 
		        'RequestType': 'AddInquire',
		        'TransId':transId,
		        'TransType':'Invoice',
		        'TransTypeId':invoiceId,
		        'FromRegnKey':myRegnKey,
		        'FromUserKey':myUserKey,
		        'ToRegnKey':toRegnKey,
		        'ToUserKey':toUserKey,
		        'Details':details,
		        'Status':status,
		        'Action':action,
		        
		    } ,
		   cache:false,
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				   closeInquirePopup();
					
				   ShowToast( resJSON.message,2000 );
				   
				   fetchInvoiceRequest();
				}

				else 
				{
					closeInquirePopup();
					 
					ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
//			   alert("Exception");
			}
		 });
}


/* remove the invoice popup form item */
function deletePopupItem( sNo )
{
// Calculate total before delete the item
	
	var price = parseFloat($( "#popup_price"+sNo ).val());
	
	var totalListPrice = parseFloat($("#popup_tot_list_price_amt").text());
	
	totalListPrice = totalListPrice - price;
	
	$("#popup_tot_list_price_amt").text(totalListPrice);
	
	var tax = parseFloat($("#popup_tax_amt").text());
	
	var totPrice = calculateTotPrice( totalListPrice,tax );
	
	$("#popup_tot_price_amt").text(totPrice);
	
	$("#popup_item"+sNo).toggle();
	$("#popup_item"+sNo).remove();
	
	$("#popupSNo").val($("#popupSNo").val()-1);
}

/* This method is called when user click the edit button in Invoice popup view */

function editButtonClicked()
{
	$("#buyer_ctrls").hide();
	
	$("#invoice_update").show();
	
	var popupItems = parseInt($("#items_count").val());
	
	
	// This is used to enable the delete button
	for( var i=1;i<popupItems+1;i++ )
	{
				
		$("#del_btn_"+i).show();
		
		$('#popup_item_desc'+i).prop('disabled', false);
		
		$('#popup_part_no'+i).prop('disabled', false);
		
		
		$('#popup_quantity_ordered'+i).prop('disabled', false);
		
		$("#popup_quantity_ordered_unit" + i).click(showQuantityUnits);
		
		
		$('#popup_quantity_shipped'+i).prop('disabled', false);
		
		$("#popup_quantity_shipped_unit" + i).click(showQuantityUnits);
		
		
		$('#popup_price'+i).prop('disabled', false);
		
		$("#currency"+i).click(showCurrencyList);
	}
	
	$("#popup_add_item_btn").show();
	
	 $("#popup_carrier").removeAttr("disabled");
		
	 $("#popup_freight_weight").removeAttr("disabled");
	
	 $("#popup_quantity_freight_unit").removeAttr("disabled");
	
	 $("#popup_date_shipped").removeAttr("disabled");
	
	 $("#popup_bill_of_land").removeAttr("disabled");
}

/* this method is called when user click the Update button after editing the Invoice from details */

function updateInvoice()
{
	showAjaxLoader();
	
	var regnKey = $("#regnkey").val();
	
	var userKey = $("#EmailAddress").val();
	

	var invoiceId = $("#invoice_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var totalListPrice = $("#popup_tot_list_price_amt").text();
	
	var tax =  $("#popup_tax_amt").text();
	
	var totalPrice = $("#popup_tot_price_amt").text();
	
	var invoiceDueDate = $("#popup_invoice_due_date").val();
	
	
	var freightHandling = $("#popup_frei_hand_amt").text();
	
	
	var freightCarrier= $("#popup_carrier").val();
	
	var freightWeight= $("#popup_freight_weight").val();
	
	var freightWeightUnit= $("#popup_quantity_freight_unit").text();
	
	var shippedDate= $("#popup_date_shipped").val();
	
	var billOfLanding= $("#popup_bill_of_land").val();
	
	var poNum = $("#po_no").val();
	
	var invoiceObj = new Object();
	
	invoiceObj.fromRegnKey = regnKey;
	invoiceObj.fromUserKey = userKey;
	invoiceObj.toRegnKey  = toRegnKey;
	invoiceObj.toUserKey  = toUserKey;
	invoiceObj.invoiceId = invoiceId;
	invoiceObj.transId = transId;
	invoiceObj.action = "Update";
	
	invoiceObj.totalListPrice = totalListPrice;
	invoiceObj.taxPercentage= tax;
	invoiceObj.totalPrice = totalPrice;
	
	invoiceObj.freightHandling = freightHandling;
	invoiceObj.invoiceDueDate = invoiceDueDate;
	
	invoiceObj.freightCarrier = freightCarrier;
	invoiceObj.freightWeight = freightWeight;
	invoiceObj.freightWeightUnit = freightWeightUnit;
	invoiceObj.shippedDate = shippedDate;
	invoiceObj.billOfLanding = billOfLanding;
	
	invoiceObj.poNum = poNum;
	invoiceObj.isNonPoInvoice = 1;
	invoiceObj.isDiffAdd = 1;
	
	invoiceObj.isOutsideSupplier = 0;
	invoiceObj.outsideSupplierEmail = "";
	invoiceObj.outsideSupplierMailFlag = 0;
	
	
	var items = new Array();
	
	var itemsCount = parseInt($("#items_count").val());
	
	for(var i=1;i<=itemsCount;i++ )
	{
		if ($("#popup_item"+i).length>0) 
		{
		    var item = new Object();
		    item.itemDesc = $("#popup_item_desc"+i).val();
		    item.partNo =$("#popup_part_no"+i).val();
		    
		    item.quantityOrdered =$("#popup_quantity_ordered"+i).val();
		    item.quantityOrderedUnit =$("#popup_quantity_ordered_unit"+i).text();
		    
		    item.quantityShipped =$("#popup_quantity_shipped"+i).val();
		    item.quantityShippedUnit =$("#popup_quantity_shipped_unit"+i).text();
		 
		    item.price =$("#popup_price"+i).val();
		    item.currency =$("#popup_currency"+i).text();
		    
		    items.push( item );
		}
	}
	
	
	invoiceObj.items = items;
	
	var invoiceData = JSON.stringify(invoiceObj);
	
	
	if( items.length == 0 )
	{
		$("#invoice_popup_form_error").text("Add atleast one item to proceed.");
		
		return;
	}
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/InvoiceServlet",
		   dataType: 'json',
		   data: {
			   'RequestType':"UpdateInvoice",
			   'Invoice': invoiceData,
			   
			   },
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				  $("#invoice_popup").hide();
				   
				   ShowToast( resJSON.message,2000 );
				   
				   fetchInvoiceRequest();
				}

				else 
				{
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
//			   alert("Exception");
			}
		 });
}

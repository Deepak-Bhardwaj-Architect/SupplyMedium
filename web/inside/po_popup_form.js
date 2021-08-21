var action="";

/* This method is called when user click the Accept button in 
 * PO from. It change the PO status as POAccepted.
 */
function acceptPO()
{
	$("#po_accept_btn").attr("disabled", "disabled");
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var POId = $("#po_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var status = $("#status").val();
	
	status = status.replace(" ","");
	
	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/POServlet",
		   data:{ 
		        'RequestType': 'ChangeStatus', 
		        'POId':POId,
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
				   closePOPopup();
				   
				   ShowToast( resJSON.message,2000 );
				   $("#po_accept_btn").removeAttr("disabled");
				   fetchPORequest();
				}

				else 
				{
					closePOPopup();
					
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
 * PO from. It change the PO status as PORejected.
 */
function rejectPO()
{
        
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var POId = $("#po_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var status = $("#status").val();
	
	status = status.replace(" ","");
	
	var rejectReason = $("#reject_reason_text").val();
	
	
	
	showAjaxLoader();
        $("#po_reject_btn").attr("disabled", "disabled");
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/TransRejectServlet",
		   data:{ 
			   'FromRegnKey':myRegnKey,
			   'FromUserKey':myUserKey,
			   'ToRegnKey':toRegnKey,
		       'ToUserKey':toUserKey,
		       'TransId':transId,
		       'TransType':'PO',
		       'TransTypeId':POId,
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
				   closePOPopup();
				   
				   ShowToast( resJSON.message,2000 );
				   $("#po_reject_btn").removeAttr("disabled");
				   fetchPORequest();
				}

				else 
				{
					closePOPopup();
					
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
	$("#po_popup_new_inquire").show();
	
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
	$("#po_popup_new_inquire").show();
	
	$("#supplier_ctrls").hide();
	
	$("#buyer_ctrls").hide();
	
	$("#inquire_ctrls").show();
	
	action = "Update";
}

/* This method is called when user click the send button after entering
 * the inquire details. It change the PO status as POInquired.
 */
function inquirePO()
{
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var POId = $("#po_id").val();
	
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
        $("#supp_po_inquire_btn").attr("disabled", "disabled");
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/TransInquireServlet",
		   data:{ 
		        'RequestType': 'AddInquire',
		        'TransId':transId,
		        'TransType':'PO',
		        'TransTypeId':POId,
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
				   closePOPopup();
					
				   ShowToast( resJSON.message,2000 );
				   $("#supp_po_inquire_btn").removeAttr("disabled");
				   fetchPORequest();
				}

				else 
				{
					 closePOPopup();
					 
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

/* this method is used to close the PO popup view */

function closePOPopup()
{
	$("#po_popup").hide();
	
	$("#from_form").val("POForm");
}

/* this is used to close the Inquire poup view */
function closeInquirePopup()
{
	$("#po_inquire_popup").hide();
}

/* This method is called when user click the inquire send button from Chat popup view */
function chatInquirePO()
{
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var POId = $("#chat_po_id").val();
	
	var transId = $("#chat_trans_id").val();
	
	var toRegnKey = $("#chat_reply_to_comp").val();
	
	var toUserKey = $("#chat_reply_to_user").val();
	
	var details = $("#chat_new_inquire_message").val().trim();
	
	var status = $("#chat_status").val();
	
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
		        'TransType':'PO',
		        'TransTypeId':POId,
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
				   
				   fetchPORequest();
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


/* remove the po popup form item */
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

/* This method is called when user click the edit button in po popup view */

function editButtonClicked()
{
	$("#buyer_ctrls").hide();
	
	$("#po_update").show();
	
	var popupItems = parseInt($("#items_count").val());
	
	
	// This is used to enable the delete button
	for( var i=1;i<popupItems+1;i++ )
	{
		
		$("#del_btn_"+i).show();
		
		$('#popup_item_desc'+i).prop('disabled', false);
		
		$('#popup_part_no'+i).prop('disabled', false);
		
		
		$('#popup_quantity'+i).prop('disabled', false);
		
		$("#popup_quantity_unit" + i).click(showQuantityUnits);
		
		
		$('#popup_ship_date'+i).prop('disabled', false);
		
		$( "#popup_ship_date" + i ).datepicker({ dateFormat: "dd-M-yy" });
		
		
		$('#popup_price'+i).prop('disabled', false);
		
		$("#popup_currency"+i).click(showCurrencyList);
		
		
		$("#popup_multiplier"+i).prop('disabled', false);
	}
	
	$("#popup_add_item_btn").show();
}

/* this method is called when user click the Update button after editing the PO from details */

function updatePO()
{
	showAjaxLoader();
	
	var regnKey = $("#regnkey").val();
	
	var userKey = $("#EmailAddress").val();
	

	var POId = $("#po_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var totalListPrice = $("#popup_tot_list_price_amt").text();
	
	var tax =  $("#popup_tax_amt").text();
	
	var totalPrice = $("#popup_tot_price_amt").text();
	
	var poObj = new Object();
	
	poObj.fromRegnKey = regnKey;
	poObj.fromUserKey = userKey;
	poObj.toRegnKey  = toRegnKey;
	poObj.toUserKey  = toUserKey;
	poObj.poId = POId;
	poObj.transId = transId;
	poObj.action = "Update";
	
	poObj.totalListPrice = totalListPrice;
	poObj.taxPercentage= tax;
	poObj.totalPrice = totalPrice;
	
	poObj.isOutsideSupplier = 0;
	poObj.outsideSupplierEmail = "";
	poObj.outsideSupplierMailFlag = 0;

	
	
	var items = new Array();
	
	var itemsCount = parseInt($("#items_count").val());
	
	for(var i=1;i<=itemsCount;i++ )
	{
		if ($("#popup_item"+i).length>0) 
		{
		    var item = new Object();
		    item.itemDesc = $("#popup_item_desc"+i).val();
		    item.partNo =$("#popup_part_no"+i).val();
		    item.quantity =$("#popup_quantity"+i).val();
		    item.quantityUnit =$("#popup_quantity_unit"+i).text();
		    item.shipDate =$("#popup_ship_date"+i).val();
		    
		    item.price =$("#popup_price"+i).val();
		    item.currency =$("#popup_currency"+i).text();
		    item.multiplier =$("#popup_multiplier"+i).val();
		    
		    items.push( item );
		}
	}
	
	
	poObj.items = items;
	
	
	var poData = JSON.stringify(poObj);
	
	
	
	
	if( items.length == 0 )
	{
		$("#po_popup_form_error").text("Add atleast one item to proceed.");
		
		return;
	}
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/POServlet",
		   dataType: 'json',
		   data: {
			   'RequestType':"UpdatePO",
			   'PO': poData,
			   
			   },
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				  $("#po_popup").hide();
				   
				   ShowToast( resJSON.message,2000 );
				   
				   fetchPORequest();
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

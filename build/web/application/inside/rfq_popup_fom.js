var action="";

/* This method is called when user click the Accept button in 
 * RFQ from. It change the RFQ status as RFQAccepted.
 */
function acceptRFQ()
{        
        $("#rfq_accept_btn").attr("disabled", "disabled");
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var RFQId = $("#rfq_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var status = $("#status").val();
	
	status = status.replace(" ","");
	
	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/RFQServlet",
		   data:{ 
		        'RequestType': 'ChangeStatus', 
		        'RFQId':RFQId,
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
				   closeRFQPopup();
				   
				   ShowToast( resJSON.message,2000 );
				   $("#rfq_accept_btn").removeAttr("disabled");
				   fetchRFQRequest();
				}

				else 
				{
					closeRFQPopup();
					
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

/* This method is called when user click the Reject button in 
 * RFQ from. It change the RFQ status as RFQRejected.
 */
function rejectRFQ()
{
       
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var RFQId = $("#rfq_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var status = $("#status").val();
	
	status = status.replace(" ","");
	
	
	showAjaxLoader();
        $("#rfq_reject_btn").attr("disabled", "disabled");
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/RFQServlet",
		   data:{ 
			   'RequestType': 'ChangeStatus', 
		        'RFQId':RFQId,
		        'TransId':transId,
		        'FromRegnKey':myRegnKey,
		        'FromUserKey':myUserKey,
		        'ToRegnKey':toRegnKey,
		        'ToUserKey':toUserKey,
		        'Status':status,
		        'Action':'Reject'
		    } ,
		   cache:false,
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				   closeRFQPopup();
				   
				   ShowToast( resJSON.message,2000 );
				   $("#rfq_reject_btn").removeAttr("disabled");
				   fetchRFQRequest();
				}

				else 
				{
					closeRFQPopup();
					
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
	$("#rfq_popup_new_inquire").show();
	
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
	$("#rfq_popup_new_inquire").show();
	
	$("#supplier_ctrls").hide();
	
	$("#buyer_ctrls").hide();
	
	$("#inquire_ctrls").show();
	
	action = "Update";
}

/* This method is called when user click the send button after entering
 * the inquire details. It change the RFQ status as RFQInquired.
 */
function inquireRFQ()
{
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var RFQId = $("#rfq_id").val();
	
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
        $("#supp_rfq_inquire_btn").attr("disabled", "disabled");
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/TransInquireServlet",
		   data:{ 
		        'RequestType': 'AddInquire',
		        'TransId':transId,
		        'TransType':'RFQ',
		        'TransTypeId':RFQId,
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
				   closeRFQPopup();
					
				   ShowToast( resJSON.message,2000 );
				   $("#supp_rfq_inquire_btn").removeAttr("disabled");
				   fetchRFQRequest();
				}

				else 
				{
					 closeRFQPopup();
					 
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

/* this method is used to close the RFQ popup view */

function closeRFQPopup()
{
	$("#rfq_popup").hide();
	
	$("#from_form").val("RFQForm");
}

/* this is used to close the Inquire poup view */
function closeInquirePopup()
{
	$("#rfq_inquire_popup").hide();
}

/* This method is called when user click the inquire send button from Chat popup view */
function chatInquireRFQ()
{
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var RFQId = $("#chat_rfq_id").val();
	
	var transId = $("#chat_trans_id").val();
	
	var toRegnKey = $("#chat_reply_to_comp").val();
	
	var toUserKey = $("#chat_reply_to_user").val();
	
	var details = $("#chat_new_inquire_message").val().trim();
	
	var status = $("#chat_status").val();
	
	status = status.replace(" ","");
	
	if( details == "" )
	{
		$("#chat_inquire_error").text("Enter inquire details");
		
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
		        'TransType':'RFQ',
		        'TransTypeId':RFQId,
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
				   
				   fetchRFQRequest();
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


/* remove the rfq popup form item */
function deletePopupItem( sNo )
{
	
	$("#popup_item"+sNo).toggle();
	$("#popup_item"+sNo).remove();
	
	$("#popupSNo").val($("#popupSNo").val()-1);
}

/* This method is called when user click the edit button in rfq popup view */

function editButtonClicked()
{
	$("#buyer_ctrls").hide();
	
	$("#rfq_update").show();
	
	var popupItems = parseInt($("#items_count").val());
	
	
	// This is used to enable the delete button
	for( var i=1;i<popupItems+1;i++ )
	{
		$("#del_btn_"+i).show();
		
		$('#popup_item_desc'+i).prop('disabled', false);
		
		$('#popup_part_no'+i).prop('disabled', false);
		
		$('#popup_quantity'+i).prop('disabled', false);
		
		$('#popup_ship_date'+i).prop('disabled', false);
		
		$( "#popup_ship_date" + i ).datepicker({ dateFormat: "dd-M-yy" });
		
		$("#popup_quantity_unit" + i).click(showQuantityUnits);
	}
	
	$("#popup_add_item_btn").show();
}

/* this method is called when user click the Update button after editing the RFQ from details */

function updateRFQ()
{
	
	showAjaxLoader();
	
	var regnKey = $("#regnkey").val();
	
	var userKey = $("#EmailAddress").val();
	

	var rfqId = $("#rfq_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var quoteRef = $("#popup_quote_ref").val();
	
	
	
	var rfqObj = new Object();
	
	rfqObj.fromRegnKey = regnKey;
	rfqObj.fromUserKey = userKey;
	rfqObj.toRegnKey  = toRegnKey;
	rfqObj.toUserKey  = toUserKey;
	rfqObj.rfqId = rfqId;
	rfqObj.transId = transId;
	rfqObj.action = "Update";
	rfqObj.quoteRef   = quoteRef;
	
	rfqObj.isOutsideSupplier = 0;
	rfqObj.outsideSupplierEmail = "";
	rfqObj.outsideSupplierMailFlag = 0;

	
	
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
		    
		    
		    items.push( item );
		}
	}
	
	
	rfqObj.items = items;
	
	
	var rfqData = JSON.stringify(rfqObj);
	
	if( items.length == 0 )
	{
		$("#rfq_popup_form_error").text("Add atleast one item to proceed.");
		
		return;
	}
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/RFQServlet",
		   dataType: 'json',
		   data: {
			   'RequestType':"UpdateRFQ",
			   'RFQ': rfqData,
			   
			   },
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				  $("#rfq_popup").hide();
				   
				   ShowToast( resJSON.message,2000 );
				   
				   fetchRFQRequest();
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

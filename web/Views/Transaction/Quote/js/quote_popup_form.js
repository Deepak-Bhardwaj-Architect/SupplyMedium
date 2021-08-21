var action="";

/* This method is called when user click the Accept button in 
 * Quote from. It change the Quote status as QuoteAccepted.
 */
function acceptQuote()
{
	
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var quoteId = $("#quote_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var status = $("#status").val();
	
	status = status.replace(" ","");
	
	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/QuoteServlet",
		   data:{ 
		        'RequestType': 'ChangeStatus', 
		        'QuoteId':quoteId,
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
				   closeQuotePopup();
				   
				   ShowToast( resJSON.message,2000 );
				   
				   fetchQuoteRequest();
				}

				else 
				{
					closeQuotePopup();
					
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
			   alert("Exception");
			}
		 });
}

/* This method is called when user click the Reject button in 
 * Quote from. It change the Quote status as QuoteRejected.
 */
function rejectQuote()
{
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var quoteId = $("#quote_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var status = $("#status").val();
	
	status = status.replace(" ","");
	
	
	
	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/QuoteServlet",
		   data:{ 
			   'RequestType': 'ChangeStatus', 
		        'QuoteId':quoteId,
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
				   closeQuotePopup();
				   
				   ShowToast( resJSON.message,2000 );
				   
				   fetchQuoteRequest();
				}

				else 
				{
					closeQuotePopup();
					
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
			   alert("Exception");
			}
		 });
}


/* This method called when Supplier click the inquire button.
 * After clicking this button it shows the inquire details entry
 * text box.here Supplier can enter the details and send to vendor.
 */
function suppInquireBtnClicked()
{
	$("#quote_popup_new_inquire").show();
	
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
	$("#quote_popup_new_inquire").show();
	
	$("#supplier_ctrls").hide();
	
	$("#buyer_ctrls").hide();
	
	$("#inquire_ctrls").show();
	
	action = "Update";
}

/* This method is called when user click the send button after entering
 * the inquire details. It change the Quote status as QuoteInquired.
 */
function inquireQuote()
{
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var quoteId = $("#quote_id").val();
	
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

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/TransInquireServlet",
		   data:{ 
		        'RequestType': 'AddInquire',
		        'TransId':transId,
		        'TransType':'Quote',
		        'TransTypeId':quoteId,
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
				   closeQuotePopup();
					
				   ShowToast( resJSON.message,2000 );
				   
				   fetchQuoteRequest();
				}

				else 
				{
					 closeQuotePopup();
					 
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
			   alert("Exception");
			}
		 });
}

/* this method is used to close the Quote popup view */

function closeQuotePopup()
{
	$("#quote_popup").hide();
	
	$("#from_form").val("QuoteForm");
}

/* this is used to close the Inquire poup view */
function closeInquirePopup()
{
	$("#quote_inquire_popup").hide();
}

/* This method is called when user click the inquire send button from Chat popup view */
function chatInquireQuote()
{
	var myRegnKey = $("#regnkey").val();
	
	var myUserKey = $("#EmailAddress").val();
	
	var quoteId = $("#chat_quote_id").val();
	
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
		        'TransType':'Quote',
		        'TransTypeId':quoteId,
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
				   
				   fetchQuoteRequest();
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
			   
			   alert("Exception");
			}
		 });
}


/* remove the quote popup form item */
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
	
	// Remove the items from view
	
	$("#popup_item"+sNo).toggle();
	$("#popup_item"+sNo).remove();
	
	$("#popupSNo").val($("#popupSNo").val()-1);
}

/* This method is called when user click the edit button in quote popup view */

function editButtonClicked()
{
	$("#buyer_ctrls").hide();
	
	$("#quote_update").show();
	
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

/* this method is called when user click the Update button after editing the Quote from details */

function updateQuote()
{
	showAjaxLoader();
	
	var regnKey = $("#regnkey").val();
	
	var userKey = $("#EmailAddress").val();
	

	var quoteId = $("#quote_id").val();
	
	var transId = $("#trans_id").val();
	
	var toRegnKey = $("#reply_to_comp").val();
	
	var toUserKey = $("#reply_to_user").val();
	
	var totalListPrice = $("#popup_tot_list_price_amt").text();
	
	var tax =  $("#popup_tax_amt").text();
	
	var totalPrice = $("#popup_tot_price_amt").text();
	
	var quoteRef = $("#popup_quote_ref").val();
	
	var quoteObj = new Object();
	
	quoteObj.fromRegnKey = regnKey;
	quoteObj.fromUserKey = userKey;
	quoteObj.toRegnKey  = toRegnKey;
	quoteObj.toUserKey  = toUserKey;
	quoteObj.quoteId = quoteId;
	quoteObj.transId = transId;
	quoteObj.action = "Update";
	
	quoteObj.totalListPrice = totalListPrice;
	quoteObj.taxPercentage= tax;
	quoteObj.totalPrice = totalPrice;
	quoteObj.quoteRef   = quoteRef;
	
	quoteObj.isOutsideSupplier = 0;
	quoteObj.outsideSupplierEmail = "";
	quoteObj.outsideSupplierMailFlag = 0;

	
	
	
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
	
	
	quoteObj.items = items;
	
	
	var quoteData = JSON.stringify(quoteObj);
	
	
	
	
	if( items.length == 0 )
	{
		$("#quote_popup_form_error").text("Add atleast one item to proceed.");
		
		return;
	}
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/QuoteServlet",
		   dataType: 'json',
		   data: {
			   'RequestType':"UpdateQuote",
			   'Quote': quoteData,
			   
			   },
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				  $("#quote_popup").hide();
				   
				   ShowToast( resJSON.message,2000 );
				   
				   fetchQuoteRequest();
				}

				else 
				{
					 ShowToast( resJSON.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
			   alert("Exception");
			}
		 });
}

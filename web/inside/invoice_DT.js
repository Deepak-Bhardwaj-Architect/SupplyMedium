
$(document).ready( function() 
{
	$('#invoice_list tbody td #trans_count').die('click');
	
	$('#invoice_list tbody td #trans_count').live('click', function () 
	{
		var nTr = $(this).parents('tr')[0];
        
		if ( invoiceDataTable.fnIsOpen(nTr) )
		{
			/* This row is already open - close it */
				
			$(this).addClass('trans_count_expand');
          
			$(this).removeClass('trans_count_collapse');
          
			invoiceDataTable.fnClose( nTr );
		}
		else
		{
			/* Open this row */
			$(this).removeClass('trans_count_expand');
       	 
			$(this).addClass('trans_count_collapse');
             
			invoiceDataTable.fnOpen( nTr, fnTransDetails(invoiceDataTable, nTr), 'details' );
		}
	} );
});

function fnTransDetails ( invoiceDataTable, nTr )
{
    var aData = invoiceDataTable.fnGetData( nTr );
    
    var regnKey=$("#regnkey").val();

	
	
	var InvoiceId= aData[1];
	var date = "";
	var companyName="";
	var phone="";
	var email="";
	
	var status ="";
	
	
	var fromCompName= aData[3];
	var fromRegnKey= aData[4];
	var fromUserKey= aData[5];
	var fromState= aData[6];
	
	var toRegnKey= aData[13];
	var toUserKey= aData[14];
	var toCompName= aData[15];
	var toState= aData[17];

    
    var transArr= aData[26];
    
    var sOut = '<table>';
    
    for( var i=1;i<transArr.length;i++)
    {
    	var trans = transArr[i];
    	
    	var img ="";
    	
    	var state="";
    	
    	status = trans.status;
    	
    	if( status == "InvoiceCreated" )
		{
			status = "Invoice Created";
		}
		else if( status == "InvoiceInquire" )
		{
			status = "Invoice Inquire";
		}
		else if( "InvoiceAccepted" )
		{
			status = "Invoice Accepted";
		}
    	
    	date = trans.date;

    	if( trans.from == regnKey )
    	{
    		img += "<img src='Views/Transaction/TransCommon/images/trans_sent_icon.png' class='trans_sent'/>";
    	}
    	else
    	{
    		img += "<img src='Views/Transaction/TransCommon/images/trans_receive_icon.png' class='trans_receive'/>";
    	}
    	
    	if( trans.from == fromRegnKey )
    	{
    		companyName = fromCompName;
    		
    		phone = fromRegnKey;
    		
    		email = fromUserKey;
    		
    		state = fromState;
    	}
    	else
    	{
    		companyName = toCompName;
    		
    		phone 	= toRegnKey;
    		
    		email 	= toUserKey;
    		
    		state = toState;
    	}

    	
    	sOut +='<tr>';
    		sOut +='<td class="rowBorder" style="width:20px;">'+img+'</td>';
    	    sOut +='<td class="rowBorder" style="width:79px;">'+InvoiceId+'</td>';
    	    sOut +='<td class="rowBorder" style="width:103px;">'+date+'</td>';
    	    sOut +='<td class="rowBorder" style="width:127px;">'+companyName+'</td>';
    	    sOut +='<td class="rowBorder" style="width:106px;">'+phone+'</td>';
    	    sOut +='<td class="rowBorder" style="width:175px;">'+email+'</td>';
    	    sOut +='<td class="rowBorder" style="width:115px;">'+state+'</td>';
    	    sOut +='<td class="rowBorder" style="width:104px;">'+status+'</td>';
    	    sOut +='<td class="rowBorder" style="width:51px;">'+""+'</td>';
    	   sOut +='</tr>';
    }
    
    sOut +='</table>';
     
    return sOut;
}

var  invoiceDataTable;

/* It is used to initialize the Invoice datatable */

function initInvoiceDataTable()
{
	invoiceDataTable=$('#invoice_list').dataTable( {
		 
		
		 sDom: 'lfr<"fixed_height"t>ip',
							
		 "sPaginationType": "full_numbers",
							"bAutoWidth": false,
		 "aoColumnDefs": [
		                  { "sWidth": "20px", "aTargets": [ 0 ] },  // Sender symbol indication
			       	      { "sWidth": "78px", "aTargets": [ 1 ] },  // InvoiceId
			       	      { "sWidth": "104px", "aTargets": [ 2 ] },  // Invoice Date
			       	      
			       	      { "sWidth": "129px", "aTargets": [ 3 ] },  // From Company Name
			       	      { "sWidth": "107px", "aTargets": [ 4 ] },	 // From Phone number
			       	      { "sWidth": "174px", "aTargets": [ 5 ] },	 // From Email id
			       	      { "sWidth": "113px", "aTargets": [ 6 ] },  // from State
			       	      { "sWidth": "104px", "aTargets": [ 7 ] },	 // Status
			       	      { "sWidth": "49px", "aTargets": [ 8 ] },   // Empty
			       	      
			       	      { "bVisible":false,"aTargets":[9] }, 	// From country
     	                 { "bVisible":false,"aTargets":[10] }, 	// from city
     	                 { "bVisible":false,"aTargets":[11] }, 	// from address
     	                 { "bVisible":false,"aTargets":[12] },	// from zipcode
     	                 { "bVisible":false,"aTargets":[13] },	// to regnkey
     	                 { "bVisible":false,"aTargets":[14] },	// to user key
     	                 { "bVisible":false,"aTargets":[15] },	// to company name
     	                 { "bVisible":false,"aTargets":[16] },	// to country
     	                 { "bVisible":false,"aTargets":[17] },	// to state
     	                 { "bVisible":false,"aTargets":[18] },	// to city
     	                 { "bVisible":false,"aTargets":[19] },	// to address
     	                 { "bVisible":false,"aTargets":[20] },	// to zipcode
     	                 { "bVisible":false,"aTargets":[21] },	// is outside supplier
     	                 { "bVisible":false,"aTargets":[22] },	// outside supplier email
     	                 { "bVisible":false,"aTargets":[23] },	// reccurring
     	                 { "bVisible":false,"aTargets":[24] },	// quotation reference
     	                 { "bVisible":false,"aTargets":[25] },	// Items
     	                 { "bVisible":false,"aTargets":[26] },	// Transactions
     	                 { "bVisible":false,"aTargets":[27] },	// Inquires 
     	                 { "bVisible":false,"aTargets":[28] },	// TransId 
     	                 
     	                 
     	                 { "bVisible":false,"aTargets":[29] },	// TotalListprice
    	                 { "bVisible":false,"aTargets":[30] },	// tax 
    	                 { "bVisible":false,"aTargets":[31] },	// total price 
    	                 
    	                 { "bVisible":false,"aTargets":[32] },	// Invoice Number
    	                 { "bVisible":false,"aTargets":[33] },	// Po number
    	                 { "bVisible":false,"aTargets":[34] },	// Invoice Due Date
    	                 { "bVisible":false,"aTargets":[35] },	// Freight and Handling
    	                 
    	                 { "bVisible":false,"aTargets":[36] },	// carrier
    	                 { "bVisible":false,"aTargets":[37] },	// Bill of landing
    	                 { "bVisible":false,"aTargets":[38] },	// Freight weight
    	                 { "bVisible":false,"aTargets":[39] },	// Date shipped
    	                 { "bVisible":false,"aTargets":[40] },	// Freight weight unit
    	                 
    	                 { "bVisible":false,"aTargets":[41] },	// is different address
    	                 { "bVisible":false,"aTargets":[42] },	// is non po invoice
    	                 { "bVisible":false,"aTargets":[43] },	// different address email
    	                 
			       	    ],
			       
			
		 "bLengthChange":false,
		 "oLanguage": { "sSearch": "Search","sEmptyTable":"No Request found"},
		 "bSort": true,
	     "aaSorting": [[0,'asc']],
		 "bPaginate": true,
		 "bFilter": true,
		 "bInfo": true,
		 "iDisplayLength": 15,
		 "fnRowCallback":function(nRow,aData,iDisplayIndex,iDisplayIndexFull){
				
		 $("td",nRow).addClass('rowBorder');
		 
		 $(nRow).click(function()
		{
			 
			 
			var aTrs = invoiceDataTable.fnGetNodes();
					
			for ( var i=0 ; i<aTrs.length ; i++ )
			{				
				if ( $(aTrs[i]).hasClass('row_selected') )
				{
					$(aTrs[i]).removeClass('row_selected');
				}
						
			}
					
				$(nRow).addClass('row_selected');
					
				var aPos = invoiceDataTable.fnGetPosition( this );
					
				var aData = invoiceDataTable.fnGetData( aPos );
					
				showInvoicePopupForm( aData );
					
			});		
				
				return nRow;
		}
	} );
	
	
	//var myCars=new Array("Saab","Volvo","BMW");
	
	var transCountImg = "";
	
	transCountImg += "<div id='trans_count' class='trans_count_expand'>"+3+"</div>";
	
	var inquireChat = "<input type='button' class='inquire_chat_btn' onclick='showInquirePopup();'/>";
	
	
	
}

/* This method is used to fetch the pending invoice request. Then parse the request
 * and fill this into My Request datatable */

function fetchInvoiceRequest()
{
	var regnKey = $("#regnkey").val();
	
	var userKey = $("#EmailAddress").val();
	
	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/InvoiceServlet",
		   data:{ 
		        'RequestType': 'FetchInvoice', 
		        'RegnKey':regnKey,
		        'UserKey':userKey,
		    } ,
		   cache:false,
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				   
				   var invoiceArr = new Array(resJSON.invoicelist.length);
				   
				   invoiceArr = resJSON.invoicelist;
				   
					parseInvoiceRequest( invoiceArr );
					
					
				   //ShowToast( resJSON.message,2000 );
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


/* This method is used to show the Invoice popup form. 
 * It get the Invoice form data fromt the datatable and fill the 
 * popup form then dispaly the Invoice form in popup view.
 */

function showInvoicePopupForm( aData )
{
	$("#invoice_popup").show();
	
	var regnKey = $("#regnkey").val();

	var InvoiceId= aData[1];
	var InvoiceDate= aData[2];
	var fromCompName= aData[3];
	var fromRegnKey= aData[4];
	var fromUserKey= aData[5];
	var fromState= aData[6];
	var status= aData[7];
	var fromCountry= aData[10];
	var fromCity= aData[11];
	var fromAddress= aData[12];
	var fromZipcode= aData[13];
	var toRegnKey= aData[14];
	var toUserKey= aData[15];
	var toCompName= aData[16];
	var toCountry= aData[17];
	var toState= aData[18];
	var toCity= aData[19];
	var toAddress= aData[20];
	var toZipcode= aData[21];
	var isOutsideSupplier= aData[22];
	var outsideSupplierEmail= aData[23];
	var recurring= aData[24];
	var quotationRef= aData[25];
	var items= aData[26];
	var transaction= aData[27];
	var inquires= aData[28];
	var transId= aData[29];
	
	var totListPrice = aData[30];
	var tax = aData[31];
	var totPrice = aData[32];
	
	var invoiceNo = aData[33];
	var poNo	= aData[34];
	var invoiceDuedate = aData[35];
	var freightHandling = aData[36];
        $("#invc_tl").text("Invoice date: "+InvoiceDate)
	
	$("#popup_invoice_no").val(invoiceNo);
	$("#popup_po_no").val(poNo);
	$("#popup_invoice_due_date").val(invoiceDuedate);
	$("#popup_frei_hand_amt").val(freightHandling);
	
	
	var carrier = aData[36];
	var billOfLanding = aData[37];
	var freightWeight = aData[38];
	var dateShipped = aData[39];
	var freightWeightUnit = aData[40];
	
	var isDiffAddr = aData[42];
	var isNonPOInvoice = aData[43];
	var diffEmail = aData[44];
        $('#typ').val(aData[45]);
        $('#outside_supplier_address').val(aData[46]);
	
	
	if( isNonPOInvoice == 1 )
	{
		$("#popup_non_po_invoice").attr("checked",true);
	}
	else
	{
		$("#popup_non_po_invoice").attr("checked",false);
	}
	
	if( isDiffAddr == 1 )
	{
		$("#popup_is_diff_addr").attr("checked",true);
	}
	else
	{
		$("#popup_is_diff_addr").attr("checked",false);
	}
	
	$("#popup_diff_addr_email").val( diffEmail );
	
	$("#popup_carrier").val(carrier);
	$("#popup_carrier").trigger("update");
	$("#popup_carrier").attr("disabled","disabled");
	
	$("#popup_bill_of_land").val(billOfLanding);
	$("#popup_bill_of_land").attr("disabled","disabled");
	
	$("#popup_freight_weight").val(freightWeight);
	$("#popup_freight_weight").attr("disabled","disabled");
	
	$("#popup_quantity_freight_unit").val(dateShipped);
	$("#popup_quantity_freight_unit").attr("disabled","disabled");
	
	$("#popup_date_shipped").val(freightWeightUnit);
	
	$("#popup_date_shipped").attr("disabled","disabled");
	
  
	
	$("#status").val(status);
	
	$("#to_company_popup").val(toCompName);
        
        $("#dt").val(InvoiceDate);
	
	
	if( regnKey == fromRegnKey )  // For Buyer
	{
		$("#reply_to_comp").val(toRegnKey);
		
		$("#reply_to_user").val(toUserKey);
	}
	else if( regnKey == toRegnKey ) // For Supplier
	{
		$("#reply_to_comp").val(fromRegnKey);
		
		$("#reply_to_user").val(fromUserKey);
	}
	
	$("#inquire_ctrls").hide();
	
	$("#invoice_update").hide();
	
	if( status == "Invoice Created" )
	{
		$("#invoice_popup_new_inquire").hide();
		
		$("#buyer_ctrls").hide();
		
		if( aData[45] == "sent")  // For Buyer 
		{
			$("#supplier_ctrls").hide();
			
			$("#invoice_close").show();
		}
		else if( aData[45] == "rec" ) // For Supplier
		{
			$("#supplier_ctrls").show();
			
			$("#invoice_close").hide();
			
		}
	}
	else if( status ==  "Invoice Accepted" )
	{
		$("#supplier_ctrls").hide();
		
		$("#buyer_ctrls").hide();
		
		$("#invoice_close").show();
		
		$("#invoice_popup_new_inquire").hide();
	}
	else if( status == "Invoice Inquire" )
	{
		if( regnKey == fromRegnKey )  // For Buyer 
		{
			$("#supplier_ctrls").hide();
			
			$("#buyer_ctrls").show();
			
			$("#invoice_close").hide();
			
			$("#invoice_popup_new_inquire").hide();
			
		}
		else if( regnKey == toRegnKey ) // For Supplier
		{
			$("#supplier_ctrls").hide();
			
			$("#buyer_ctrls").hide();
			
			$("#invoice_close").show();
			
			$("#invoice_popup_new_inquire").hide();
			
		}
	}
	else if( status == "Rejected" )
	{
		$("#supplier_ctrls").hide();
		
		$("#buyer_ctrls").hide();
		
		$("#invoice_close").show();
		
		$("#invoice_popup_new_inquire").hide();
	}
	
	$("#invoice_id").val( InvoiceId );
	
	$("#trans_id").val( transId );
	
	$("#popup_buyer_name").val(fromCompName);
	
	$("#popup_buyer_country").val(fromCountry);
	
	var fromStateArr = fromState.split("<");
	
	if(fromStateArr.length>0)
		fromState = fromStateArr[0];
	
	$("#popup_buyer_state").val(fromState);
	
	$("#popup_buyer_city").val(fromCity);
	
	$("#popup_buyer_addr").val(fromAddress);
	
	$("#popup_buyer_zipcode").val(fromZipcode);
	
	
	
	$("#popup_supplier_name").val(toCompName);
	
	$("#popup_supplier_country").val(toCountry);
	
	$("#popup_supplier_state").val(toState);
	
	$("#popup_supplier_city").val(toCity);
	
	$("#popup_supplier_addr").val(toAddress);
	
	$("#popup_supplier_zipcode").val(toZipcode);
	
	$("#popup_tot_list_price_amt").text( totListPrice );
	
	$("#popup_tax_amt").text( tax );
	
	$("#popup_tot_price_amt").text( totPrice );
	
	if( isOutsideSupplier == 1 )
	{
		$('#popup_outside_supplier').prop('checked', true);
		
		$("#popup_email").val(outsideSupplierEmail);
		
		$(".popup_supplier_address").hide();
		
		$(".popup_outside_sup_email_content").show();
		
		
	}
	else
	{
		$('#popup_outside_supplier').prop('checked', false);
		
		$(".popup_supplier_address").show();
		
		$(".popup_outside_sup_email_content").hide();
	}
	
	$("#popup_items").empty();
	
	for(var i=0;i<items.length;i++)
	{
		var item = new Object();
		
		item = items[i];
		
		var itemDet = "";
		
		var sNo = i+1;
		
		itemDet += '<div id="popup_item'+sNo+'" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;position:relative;">';
		
		itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_sno'+sNo+'" style="width:30px;margin-right:20px;" disabled value="'+sNo+'";/>';
		
		itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_item_desc'+sNo+'" style="width:165px;margin-right:20px;" disabled  value="'+item.itemDesc+'";/>';
		itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_part_no'+sNo+'" style="width:90px;margin-right:20px;" disabled value="'+item.partNo+'";/>';
		
		itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_quantity_ordered'+sNo+'" style="width:90px;" disabled value="'+item.quantityOrdered+'";/>';
		itemDet += '<div class="quantity_unit" id="popup_quantity_ordered_unit'+sNo+'" >'+item.quantityOrderedUnit+'</div>';
		itemDet += '<div class="quan_units" id="units_popup_quantity_ordered_unit'+sNo+'" style="display:none;left:477px;"></div>';
		
		itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_quantity_shipped'+sNo+'" style="width:90px;" disabled value="'+item.quantityShipped+'";/>';
		itemDet += '<div class="quantity_unit" id="popup_quantity_shipped_unit'+sNo+'" >'+item.quantityShippedUnit+'</div>';
		itemDet += '<div class="quan_units" id="units_popup_quantity_shipped_unit'+sNo+'" style="display:none;left:629px;"></div>';
		
		
		itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_price'+sNo+'" style="width:60px;" disabled value="'+item.price+'";/>';
		itemDet += '<div class="quantity_unit" id="popup_currency'+sNo+'" >'+item.currency+'</div>';
		itemDet += '<div class="currency_list" id="currency_popup_currency'+sNo+'" style="display:none;left:689px;" ></div>';
		
		itemDet += '<input type="button" class="del_btn" id="del_btn_'+sNo+'" style="width:110px;margin-right:20px;display:none;" onclick="deletePopupItem('+sNo+');"/>';
		itemDet +='</div>';
		
		$("#popup_items").append( itemDet );
		
	}
	
	$("#items_count").val(items.length);
	
	$("#popupSNo").val(items.length);
	
	$("#popup_inquires").empty();
	
	
	for( var j=0;j<inquires.length;j++)
	{
		var inquire = new Object();
		
		inquire = inquires[j];
		
		var companyName = "";
		
		if( fromRegnKey == inquire.from )  
		{
			companyName = fromCompName;
		}
		else
		{
			companyName = toCompName;
		}
		
		var inquireDet = "";
		
		inquireDet += '<div class="inquire_row">';
		inquireDet += '<label class="inquire_by">'+ companyName+' </label>';
		inquireDet += '<div class="inquire_det" disabled>'+ inquire.details+' </div>';
		inquireDet += '</div>';
		
		$("#popup_inquires").append( inquireDet );
	}
	
	var regnKey = $("#regnkey").val();
	
	// This compnay is the buyer 
	if( regnKey == fromRegnKey )
	{
		$("#supplier_controls").hide();
		$("#buyer_controls").show();
	}
	else // This company is the supplier so show the accepe,reject,inquire buttons.
	{
		$("#supplier_controls").show();
		$("#buyer_controls").hide();
	}
	
	$("#invoice_popup_form").mCustomScrollbar("update");
}

/* This method is used to parse the pending po request json. And set the parsing details to 
 * My Request Data table.
 */
function parseInvoiceRequest( invoiceArr )
{
	invoiceDataTable.fnClearTable();
	
	
	if( invoiceArr.length == 0)
	{
		invoiceDataTable.fnSettings().oLanguage.sEmptyTable = "No Request Found";
	}
		
		
	
	for( var i=0;i<invoiceArr.length;i++)
	{
		
		var invoice = invoiceArr[i];
		var InvoiceId = invoice.invoiceId;
		
		var InvoiceDate = invoice.invoiceDate;
		
		var transId = invoice.transId;
		
		
		// Invoice Sender address details
		var fromRegnKey = invoice.fromRegnKey;
		
		var fromUserKey = invoice.fromUserKey;
		
		var fromCompName = invoice.fromName;
		
		var fromCountry = invoice.fromCountry;
		
		var fromState = invoice.fromState;
		
		var fromCity = invoice.fromCity;
		
		var fromAddress = invoice.fromAddress;
		
		var fromZipcode = invoice.fromZipcode;
		

		// invoice receiver address details
		
		var toRegnKey = invoice.toRegnKey;
		
		var toUserKey = invoice.toUserKey;
		
		var toCompName = invoice.toName;
		//alert(invoice.isOutsideBuyer);
		var isOutsideSupplier = invoice.isOutsideBuyer;
		//alert(isOutsideSupplier);
		var toCountry = "";
		
		var toState = "";
		
		var toCity = "";
		
		var toAddress = "";
		
		var toZipcode = "";
		
		if( isOutsideSupplier == 0 )
		{
			 toCountry = invoice.toCountry;
			
			 toState = invoice.toState;
			
			 toCity = invoice.toCity;
			
			 toAddress = invoice.toAddress;
			
			 toZipcode = invoice.toZipcode;
		}
		
		
		var status = invoice.status;
		
		if( status == "InvoiceCreated" )
		{
			status = "Invoice Created";
		}
		else if( status == "InvoiceInquire" )
		{
			status = "Invoice Inquire";
		}
		else if( status =="InvoiceAccepted" )
		{
			status = "Invoice Accepted";
		}
		
                var open="<img src='Views/Transaction/TransCommon/images/open.png'/>";
                
		var isOutsideBuyer = invoice.isOutsideBuyer;
		
		var outsideBuyerEmail = invoice.outsideBuyerEmail;
                var outsideSupplieraddressdetail="";
                if(isOutsideBuyer==="1")
                {
                   toUserKey=outsideBuyerEmail; 
                   var outsideSupplieraddress=invoice.outsideSupplieraddress.split(",")
                   toCompName=outsideSupplieraddress[0];
                   //alert(outsideSupplieraddress[3]);
                   toState=outsideSupplieraddress[3];
                   toRegnKey="";
                   outsideSupplieraddressdetail="&address="+outsideSupplieraddress[1]+"&city="+outsideSupplieraddress[2]+"&state="+outsideSupplieraddress[3]+"&country="+outsideSupplieraddress[4]+"&zipcode="+outsideSupplieraddress[5];
                }
		
		var invoiceRef = invoice.invoiceRef;
		
		var recurring = invoice.recurring;
		
		var items = invoice.items;
		
		var trans = invoice.trans;
		
		var inquires = invoice.inquires;
		
		var totListPrice = invoice.invoiceSubTotal;
		
		var tax = invoice.taxPercentage;
		
		var totPrice = invoice.invoiceTotal;
		
		var invoiceNum = invoice.invoiceNum;
		
		var poNum = invoice.poNo;
		
		var invoiceDueDate = invoice.invoiceDueDate;
		
		var freightHandling = invoice.freightHandling;
		
		var carrier = invoice.freightCarrier;
		
		var billOfLanding = invoice.billOfLanding;
		
		var freightWeight = invoice.freightWeight;
		
		var freightWeightUnit = invoice.freightWeightUnit;
		
		var dateShipped = invoice.shippedDate;
		
		var isDiffAddr = invoice.isDiffAdd;
		
		var diffEmail = invoice.diffEmail;
		
		var isNonPoInvoice = invoice.isNonPoInvoice;
		 
	
		
		var regnKey = $("#regnkey").val();
		
		var transImg = "";
		
		if( fromRegnKey == regnKey )
		{
			transImg += "<img src='Views/Transaction/TransCommon/images/trans_sent_icon.png' class='trans_sent'/>";
		}
		else
		{
			transImg += "<img src='Views/Transaction/TransCommon/images/trans_receive_icon.png' class='trans_receive'/>";
		}
		
		var transCount = trans.length;
		
		var transCountImg = "";
		
		if( transCount> 1)
		{
			transCountImg += "<div id='trans_count' class='trans_count_expand'>"+(transCount-1)+"</div>";
		}
		
		var inquireChat = "";
		
		var inquiresCount = inquires.length;
		
		if( inquiresCount>0 )
		{
			inquireChat += "<input type='button' class='inquire_chat_btn' onclick='showInquirePopup("+i+");'/>";
		}
		
		if (fromRegnKey == regnKey)
        {

            invoiceDataTable.fnAddData([transImg,InvoiceId,InvoiceDate,toCompName,toRegnKey,toUserKey,toState,status,open,
				transCountImg,fromCountry,fromCity,fromAddress,fromZipcode,fromRegnKey,fromUserKey,fromCompName,toCountry,fromState+inquireChat,
				toCity,toAddress,toZipcode,isOutsideBuyer,outsideBuyerEmail,recurring,invoiceRef,
				items,trans,inquires,transId,totListPrice,tax,totPrice,invoiceNum,poNum,invoiceDueDate,freightHandling,
				carrier,billOfLanding,freightWeight,freightWeightUnit,dateShipped,isDiffAddr,isNonPoInvoice,diffEmail,"sent",outsideSupplieraddressdetail]);
        }
        else
        {
            invoiceDataTable.fnAddData([transImg,InvoiceId,InvoiceDate,fromCompName,fromRegnKey,fromUserKey,fromState+inquireChat,status,open,
				transCountImg,fromCountry,fromCity,fromAddress,fromZipcode,toRegnKey,toUserKey,toCompName,toCountry,toState,
				toCity,toAddress,toZipcode,isOutsideBuyer,outsideBuyerEmail,recurring,invoiceRef,
				items,trans,inquires,transId,totListPrice,tax,totPrice,invoiceNum,poNum,invoiceDueDate,freightHandling,
				carrier,billOfLanding,freightWeight,freightWeightUnit,dateShipped,isDiffAddr,isNonPoInvoice,diffEmail,"rec",outsideSupplieraddressdetail]);
        }
		
	}
	
}


/* This method is used to show the inquire details for the particular Invoice in popup view */

function showInquirePopup( rowNo )
{
	var aData = invoiceDataTable.fnGetData( rowNo );
	
	var regnKey = $("#regnkey").val();
	
	var inquires = aData[27];
	
	var fromCompName= aData[3];
	var fromRegnKey= aData[4];
	var toCompName= aData[15];
	var InvoiceId= aData[1];
	var transId= aData[28];
	var status= aData[7];
	var fromUserKey= aData[5];
	var toUserKey= aData[14];
	var toRegnKey= aData[13];
	

	if( regnKey == fromRegnKey )  // For Buyer
	{
		$("#chat_reply_to_comp").val(toRegnKey);
		
		$("#chat_reply_to_user").val(toUserKey);
	}
	else if( regnKey == toRegnKey ) // For Supplier
	{
		$("#chat_reply_to_comp").val(fromRegnKey);
		
		$("#chat_reply_to_user").val(fromUserKey);
	}
	
	
	$("#chat_invoice_id").val(InvoiceId);
	
	$("#chat_trans_id").val(transId);
		
	$("#chat_status").val(status);
	
	
	
	$("#chat_invoice_inquires").empty();
	
	for( var i=0;i< inquires.length;i++ )
	{
		var companyName = "";
		
		var inquire = inquires[i];
		
		var details = inquire.details;
		
		if( fromRegnKey == inquire.from )  
		{
			companyName = fromCompName;
		}
		else
		{
			companyName = toCompName;
		}
		
		var inquireDiv = '<div class="inquire_row">';
		
		inquireDiv += '<label class="inquire_by">'+companyName+' </label>';
		
		inquireDiv += '<div class="inquire_det" disabled>'+details+'</div>';
		
		inquireDiv += '</div>';
		
		$("#chat_invoice_inquires").append( inquireDiv );
		
	}
	
	if( status == "Invoice Created" )
	{
		action = "Inquire";
		
		if( regnKey == fromRegnKey )  // For Buyer 
		{
			$("#chat_new_inquire").hide();
			$("#chat_inquire_save_btn").hide();
			
		}
		else if( regnKey == toRegnKey ) // For Supplier
		{
			$("#chat_new_inquire").show();
			$("#chat_inquire_save_btn").show();
		}
	}
	
	else if( status == "Invoice Inquire" )
	{
		action = "Update";
		
		if( regnKey == fromRegnKey )  // For Buyer 
		{
			$("#chat_new_inquire").show();
			$("#chat_inquire_save_btn").show();
			
		}
		else if( regnKey == toRegnKey ) // For Supplier
		{
			$("#chat_new_inquire").hide();
			$("#chat_inquire_save_btn").hide();
			
		}
	}
	else if( status == "Invoice Accepted" || status == "Rejected" )
	{
		$("#chat_new_inquire").hide();
		$("#chat_inquire_save_btn").hide();
	}
	
	if( regnKey == fromRegnKey ) 
	{
		$("#chat_company_name").text(fromCompName);
	}
	else
	{
		$("#chat_company_name").text(toCompName);
	}
	
	
	$("#invoice_inquire_popup").show();
	
	
}


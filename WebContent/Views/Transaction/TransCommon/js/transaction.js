var transTimer;


// This method is called while onload
//function transOnload()
//{

$(document).ready(function()
		{
			hideAjaxLoader();
	
			$("#content_loader").hide();
			
			$("#trans_super_container").show();
		
			$("#rfq_tab").click(showRFQ);
			
			$("#quote_tab").click(showQuote);
			
			$("#po_tab").click(showPO);
			
			$("#invoice_tab").click(showInvoice);
			
			
			 
		/*	clearInterval( transTimer );
			 
			 transTimer=setInterval(function(){
			 fetchLatestTransaction();
			 },1*10*1000);*/
			
			$('body').click(function() {
				$("#ven_search_result").hide();
				});
						
				
			
							
		});

/* This method is used to show the RFQ details while clicking the 
 * RFQ tab.
 */

function showRFQ()
{
	$("#trans_content").empty();
	
	$("#trans_content").hide();
	
	$("#trans_content").load("Views/Transaction/RFQ/jsp/rfq.jsp",function() 
	{
		$("#trans_content").show();
	});;;
	
	$("#rfq_tab").removeClass("main_tab_unselect");
	$("#rfq_tab").addClass("main_tab_select");
	
	$("#quote_tab").removeClass("main_tab_select");
	$("#quote_tab").addClass("main_tab_unselect");
	
	$("#po_tab").removeClass("main_tab_select");
	$("#po_tab").addClass("main_tab_unselect");
	
	$("#invoice_tab").removeClass("main_tab_select");
	$("#invoice_tab").addClass("main_tab_unselect");
	
}



/* This method is used to show the RFQ details while clicking the 
 * Quote tab.
 */

function showQuote()
{
	$("#trans_content").empty();
	
	$("#trans_content").hide();
	
	$("#trans_content").load("Views/Transaction/Quote/jsp/quote.jsp",function() 
	{
		
		$("#trans_content").show();
	});;;
	
	
	$("#rfq_tab").removeClass("main_tab_select");
	$("#rfq_tab").addClass("main_tab_unselect");
	
	$("#quote_tab").removeClass("main_tab_unselect");
	$("#quote_tab").addClass("main_tab_select");
	
	$("#po_tab").removeClass("main_tab_select");
	$("#po_tab").addClass("main_tab_unselect");
	
	$("#invoice_tab").removeClass("main_tab_select");
	$("#invoice_tab").addClass("main_tab_unselect");
}



/* This method is used to show the RFQ details while clicking the 
 * Purchase Order tab.
 */

function showPO()
{
	$("#trans_content").empty();
	
	$("#trans_content").hide();
	
	$("#trans_content").load("Views/Transaction/PO/jsp/purchase_order.jsp",function() 
	{
		$("#trans_content").show();
	});;;
	
	
	$("#rfq_tab").removeClass("main_tab_select");
	$("#rfq_tab").addClass("main_tab_unselect");
	
	$("#quote_tab").removeClass("main_tab_select");
	$("#quote_tab").addClass("main_tab_unselect");
	
	$("#po_tab").removeClass("main_tab_unselect");
	$("#po_tab").addClass("main_tab_select");
	
	$("#invoice_tab").removeClass("main_tab_select");
	$("#invoice_tab").addClass("main_tab_unselect");
}



/* This method is used to show the RFQ details while clicking the 
 * Invoice tab.
 */

function showInvoice()
{
	$("#trans_content").empty();
	
	$("#trans_content").hide();
	
	$("#trans_content").load("Views/Transaction/Invoice/jsp/invoice.jsp",function() 
	{
		$("#trans_content").show();
	});;;
	
	
	$("#rfq_tab").removeClass("main_tab_select");
	$("#rfq_tab").addClass("main_tab_unselect");
	
	$("#quote_tab").removeClass("main_tab_select");
	$("#quote_tab").addClass("main_tab_unselect");
	
	$("#po_tab").removeClass("main_tab_select");
	$("#po_tab").addClass("main_tab_unselect");
	
	$("#invoice_tab").removeClass("main_tab_unselect");
	$("#invoice_tab").addClass("main_tab_select");
}



function  fetchLatestTransaction()
{
	if ( typeof fetchRFQRequest == 'function' ) 
	{ 
		fetchRFQRequest();
	}
	if ( typeof fetchQuoteRequest == 'function' ) 
	{ 
		 fetchQuoteRequest();
	}
	if ( typeof fetchPORequest == 'function' ) 
	{ 
		fetchPORequest();
	}
	if ( typeof fetchInvoiceRequest == 'function' ) 
	{ 
		fetchInvoiceRequest();
	}
	
}


/* This method is used to open the TC Terms and condition popup */

function TCPopup( tcContent )
{
	if( tcContent == "" )
		$("#tc_content").text( "No Terms and Conditions" );
	else 	
		$("#tc_content").text( tcContent );
	
	$("#tc_content_popup").show();
}

/* It is used to get the TC for given company */

function getTC( vendorKey, transType )
{
	showAjaxLoader();

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/TransTcUploadServlet",
		data : {
			'RequestType' : 'FetchTransTc',
			'CompanyKey' : vendorKey,
		},
		cache : false,
		success : function(resultJson) {
			hideAjaxLoader();

			if (resultJson.result == "success") 
			{
				var transArr = new Array(resultJson.TransTc.length);

				transArr = resultJson.TransTc;

				for ( var i = 0; i < transArr.length; i++) 
				{
					var trans = transArr[i];

					if (trans.transType == "RFQ") 
					{
						rfqText = trans.tc;
					} 
					else if (trans.transType == "Quote")
					{
						quoteText = trans.tc;
					}
					else if (trans.transType == "PO") 
					{
						poText = trans.tc;
					} 
					else if (trans.transType == "Invoice") 
					{
						invoiceText = trans.tc;
					}
				}
			}

		}
	});
}




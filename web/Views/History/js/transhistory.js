var transTable;

function transHistoryOnload()
{
			
	$("#content_loader").hide();
			
	$("#history_content").show();
			
	initHistoryDataTable();
	
	$("#download_btn").click( downloadSpecificEDIFiles );
}

/* It is used to download the selected xml files from popup */
function downloadSpecificEDIFiles()
{
	var transTypes = "";
	
	if($('#rfq_checkbox').attr('checked'))
	{
		transTypes += "RFQ,";
	}
	if($('#quote_checkbox').attr('checked'))
	{
		transTypes += "Quote,";
	}
	if($('#po_checkbox').attr('checked'))
	{
		transTypes += "PO,";
	}
	if($('#invoice_checkbox').attr('checked'))
	{
		transTypes += "Invoice,";
	}
	
	transTypes = transTypes.substring(0, transTypes.length - 1);
	
	if( transTypes == "" )
	{
		ShowToast( "Select any file to download", 2000 );
		
		return;
	}
	
	var transId = $("#trans_id").val(  );
	
	$( "#download_info" ).hide();
	
	$("#sepcific_trans_id").val( transId );
	
	$("#trans_types").val( transTypes );
	
	$('#specific_trans_edi_download_form').submit();
}

/* Intialize the data table */
function initHistoryDataTable()
{
	 transTable=$('#TransList').dataTable( {
		 
		 "aoColumnDefs":[
							{"bVisible":false,"aTargets":[8] }
							],
							sDom: 'lfr<"fixed_height"t>ip',
							
							"sPaginationType": "full_numbers",
							"bAutoWidth": false,
		 "aoColumnDefs": [
			       	      { "sWidth": "200px", "aTargets": [ 0 ] },
			       	      { "sWidth": "200px", "aTargets": [ 1 ] },
			       	      { "sWidth": "200px", "aTargets": [ 2 ] },
			       	      { "sWidth": "200px", "aTargets": [ 3 ] },
			       	      { "sWidth": "129px", "aTargets": [ 4 ] },
			       	      { "sWidth": "70px",  "aTargets": [ 5 ] },
			       	      { "sWidth": "70px",  "aTargets": [ 6 ] }
			       	    ],
			
			
			       	 "bLengthChange":false,
			       	"oLanguage": { "sSearch": "Search","sEmptyTable":"No Transactions found"},
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
					
				});		
				
				return nRow;
			}
		} );
}


// Used to fetch the transaction details for the selected customers 
function getTransDetails( customerKey )
{	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	transTable.fnClearTable();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/FetchCustomerTransactionServlet",
		   data:{ 
		        'CompanyRegnKey':regnKey ,
		        'CustomerKey':customerKey,
		    } ,
		   cache:false,
		   success: function( cusData )
		   {
			   hideAjaxLoader();
			   
			   
			  if(cusData.result == "success")
			  {
				  $("#customer_history").addClass('normal');
				  $("#customer_history").removeClass('highlight');
					
				  $("#customer_history_content").hide();
					
				  $("#transaction_history_content").show();
					
				  	var cusArr = new Array( cusData.customers.length );
				  	
				  	cusArr = cusData.customers;
				  	
					var customer = cusArr[0];
					
			  		//var companyId 		= customer.customerId;
			  		var companyName		= customer.companyName;
					var address			= customer.address;
					var addressType		= customer.addressType;
					var city			= customer.city;
					var state			= customer.state;
					
					if( addressType == "null" )
						addressType ="";
					
					if( state=="null" )
						state="";				
					
					$("#cusName").text( companyName );
					$("#cusId").text( customerKey );
					$("#cusAddress").text( address );
					$("#city").text( city );
					$("#state").text( state );
					$("#addType").text( addressType );
				  	
				  	for( var i=0; i<cusData.customers.length; i++ )
					  {
					  		var customer = cusArr[i];
					  		
							var transArr	= new Array(customer.transactions.length);
							
							transArr = customer.transactions;
					  		
							for( var j=0; j<transArr.length; j++ )
							{
								var transaction	= transArr[j];
								
								var transId 	= transaction.transId;
								var date		= transaction.date;
								var amount		= transaction.amount;
								var status		= transaction.status;
                                                                //alert(status);
								var transStates = transaction.transStates;
								
								var addRemainderLink   = '<a style="text-decoration:underline;" onclick="showAddRemainderPopup(\''+transId+'\')" >Add Reminder</a>';	
								
								var ratingsLink   = '<a style="text-decoration:underline;" onclick="showAddRatingsPopup(\''+transId+'\')" >Rate this transaction</a>';	
								
								var downloadLink   = '<a style="text-decoration:underline;" onclick="showEDIFilePopup(\''+transId+'\',\''+transStates+'\')" >DownloadLink</a>';	
								
								transTable.fnAddData( [transId, date, amount, addRemainderLink,downloadLink, status, ratingsLink, ""] );
							}
					  }
				  }
			  
			  else
			 {
				
				 $('#usertblerr').text(cusData.message);
			  }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
				$('#usertblerr').text("Unexcepted error. Please try again.");
			}
		 });
}

function showEDIFilePopup( transId, transStates )
{
	$("#trans_id").val( transId );
	
	var transTypeArr = transStates.split(",");
	
	
	$('#rfq_checkbox').prop('checked', false);

	$('#quote_checkbox').prop('checked', false);
	
	$('#po_checkbox').prop('checked', false);

	$('#invoice_checkbox').prop('checked', false);
	
	$("#rfq_file").hide();
	
	$("#quote_file").hide();
	
	$("#po_file").hide();
	
	$("#invoice_file").hide();
	
	
	for( var i=0;i<transTypeArr.length;i++ )
	{
		var transType = transTypeArr[ i ];
		
		if( transType == "RFQ" )
		{
			$("#rfq_file").show();
		}
		else if( transType == "Quote" )
		{
			$("#quote_file").show();
		}
		else if( transType == "PO" )
		{
			$("#po_file").show();
		}
		else if( transType == "Invoice" )
		{
			$("#invoice_file").show();
		}
			
	}
	
	$( "#download_info" ).show();
}




// Used to show the add remainder popup view

function showAddRemainderPopup( transId )
{
	selectedTransId = transId;
	
	$("#add_remainder").show();
}

// Used to show the add ratings popup view 

function showAddRatingsPopup( transId )
{
	selectedTransId = transId;
	
	$("#add_ratings").show();
}

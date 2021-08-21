
var customerTable;
var lastSelectedRow=0;
var lastSelectedRowStatus="";

var deleteRow = 0;
var remainderMap ;

var selectedCustRegnKey ="";
var selectedTransId = "";

function cusHistoryOnload()
{
	$("#remainders_content").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	$("#add_ratings_star").text('');

		
		$("#cancel_mail_btn").click(function() {

		$("#email_composer").hide();
		$("#message_mail").val(null);

	});
	
	
	
	$("#send_mail_btn").click(sendMail);

	remainderMap = new Object();
	
	$("#content_loader").hide();

	$("#history_content").show();
	

	initDataTable();

	getCustomers();
	
	
	$('#customer_history').click(function() 
	{
		
		$("#customer_history").addClass('highlight');
		$("#customer_history").removeClass('normal');
		
		$("#customer_history_content").show();
		
		$("#transaction_history_content").hide();
	});
	
	
	$('#transaction_history').click(function() 
	{
		
		$("#customer_history").addClass('normal');
		$("#customer_history").removeClass('highlight');
                
                $("#transaction_history").addClass('gray_disable');
		$("#transaction_history").removeClass('normal');
                
                $("#payment_history").addClass('normal');
		$("#payment_history").removeClass('gray_disable');
		
		$("#customer_history_content").hide();
		
		$("#transaction_history_content").show();
	});
        $('#payment_history').click(function() 
	{
                $("#customer_history").addClass('normal');
		$("#customer_history").removeClass('highlight');
                
		$("#payment_history").addClass('gray_disable');
		$("#payment_history").removeClass('normal');
                
                $("#transaction_history").addClass('normal');
		$("#transaction_history").removeClass('gray_disable');
		
		$("#customer_history_content").hide();
		
		$("#transaction_history_content").show();
                getpaymentDetails()
	});
	
}




/* Intialize the data table */
function initDataTable()
{
	 customerTable=$('#CustomerList').dataTable( {
		 
		 "aoColumnDefs":[
							{"bVisible":false,"aTargets":[8] },
							],
							sDom: 'lfr<"fixed_height"t>ip',
							
							"sPaginationType": "full_numbers",
							"bAutoWidth": false,
		 "aoColumnDefs": [
			       	      { "sWidth": "200px", "aTargets": [ 0 ] },
			       	      { "sWidth": "220px", "aTargets": [ 1 ] },
			       	      { "sWidth": "200px", "aTargets": [ 2 ] },
			       	      { "sWidth": "200px", "aTargets": [ 3 ] },
			       	      { "sWidth": "129px", "aTargets": [ 4 ] },
			       	      { "sWidth": "120px", "aTargets": [ 5 ] },
			       	      { "sWidth": "120px", "aTargets": [ 6 ] },
			       	   	  { "sWidth": "100px", "aTargets": [ 7 ] }
			       	    ],
			
			
			       	 "bLengthChange":false,
			       	"oLanguage": { "sSearch": "Search","sEmptyTable":"No Customers found"},
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

/* fetch all the customers of the company */
function getCustomers()
{
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/FetchCustomerHistoryServlet",
		   data:{ 
		        'CompanyRegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function( cusData )
		   {
			   hideAjaxLoader();
			  
			  if(cusData.result == "success")
				  {
				  	
				  	var cusArr = new Array( cusData.customers.length );
				  	
				  	cusArr = cusData.customers;
				  	
				  
				  	for( var i=0; i<cusData.customers.length; i++ )
					  {
					  		var customer = cusArr[i];
					  		
					  		var companyId 		= customer.companyId;
					  								
							var email			= customer.email;
							var recentTransId	= customer.recentTransId;
							
							
														
							var ratingsCount	= customer.ratingsCount;
							
							
							var remindersArr	= new Array(customer.reminders.length);
							
							remindersArr = customer.reminders;
							
							remainderMap[ phoneNo ] = remindersArr;
                                                        
                                                        var phoneNo=customer.companyPhoneNo;
							
                                                        var phoneNo2			= '<a style="text-decoration:underline;" id="'+phoneNo+'">'+customer.companyPhoneNo+"</a>";
							
                                                        var companyName		='<a style="text-decoration:underline;" id="nm'+phoneNo+'">'+customer.companyName+"</a>"; 
                               
                                                        var address	       ='<a style="text-decoration:underline;" id="adrs'+phoneNo+'">'+customer.address+"</a>"; 
                               
                               //var historyLink 	= '<a style="text-decoration:underline;" id="'+phoneNo+'">history</a>';	
							
							var remainderLink   = '<a style="text-decoration:underline;" id="remainder_'+phoneNo+'">Reminders</a>';	
							
							var emailLink 		= '<a style="text-decoration:underline;" onclick="showMailComposer(\''+email+'\')">'+email+'</a>';	
							
							var ratings			= '<div id="customerRating_'+phoneNo+'"></div>';
							var documentLink ="";
							
							if(recentTransId!=-1)
							{
								 documentLink    = '<a style="text-decoration:underline;id="download" onclick="downloadRecentTransEDI(\''+recentTransId+'\')">Download</a>';
							}
							
							else
							{
								documentLink ="No Downloads";
							}
							
							$( "#"+phoneNo ).die();
							
							$( "#"+phoneNo ).live( "click", function()
							{
								showCustomerTransactions( this.id );
							});
                                                        
                                                        $( "#nm"+phoneNo ).die();
                                                        $( "#nm"+phoneNo ).live( "click", function()
							{
								showCustomerTransactions( phoneNo );
							});
                                                        
                                                        $( "#adrs"+phoneNo ).die();
                                                        $( "#adrs"+phoneNo ).live( "click", function()
							{
								showCustomerTransactions( phoneNo);
							});
							
							$( "#remainder_"+phoneNo ).die();
							
							$( "#remainder_"+phoneNo ).live( "click", function()
							{
								var arr = this.id.split("_");
								
								showRemainders( arr[1] );
							});
							
					  		
					  		customerTable.fnAddData( [phoneNo2, companyName, address, emailLink, remainderLink, documentLink, 
					  		                        ratings, remindersArr] );
					  		
					  		$( "#customerRating_"+phoneNo ).attr('data-average', (ratingsCount));
					  		$( "#customerRating_"+phoneNo ).text('');
					  		$( "#customerRating_"+phoneNo ).jRating(
					  		{
					  			isDisabled : true,
					  			type : 'small'
					  		});
				  		
					  }
				  }
			  
			  else
			 {
				 
				 $('#usertblerr').text(data.message);
			  }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
				
				$('#usertblerr').text("Unexcepted error. Please try again.");
			}
		 });
}


/* show the selected customer transaction */
function showCustomerTransactions( customerKey )
{
	selectedCustRegnKey = customerKey;
	
	getTransDetails( customerKey );
}

// Used to fetch the payment details for the selected customers 
function getpaymentDetails()
{
	showAjaxLoader();
	var regnKey = $('#regnkey').val();
	transTable.fnClearTable();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/FetchCustomerTransactionServlet",
		   data:{ 
		        'CompanyRegnKey':regnKey ,
		        'CustomerKey':selectedCustRegnKey,
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
					$("#cusId").text( selectedCustRegnKey );
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
                                                                if(status=="Accepted")
                                                                {
								var transStates = transaction.transStates;
								
								var addRemainderLink   = '<a style="text-decoration:underline;" onclick="showAddRemainderPopup(\''+transId+'\')" >Add Reminder</a>';	
								
								var ratingsLink   = '<a style="text-decoration:underline;" onclick="showAddRatingsPopup(\''+transId+'\')" >Rate this transaction</a>';	
								
								var downloadLink   = '<a style="text-decoration:underline;" onclick="showEDIFilePopup(\''+transId+'\',\''+transStates+'\')" >DownloadLink</a>';	
								
								transTable.fnAddData( [transId, date, amount, addRemainderLink,downloadLink, status, ratingsLink, ""] );
                                                               }
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

/* show the remainder popup view */
function showRemainders( customerKey )
{
	var remaidersArr = 	remainderMap[ customerKey ];
	
	$("#remainders_content .mCSB_container").empty();
	   
	 var table = "";
	   
	 table += '<table border="1" class="table_border" id="remainder_table">';
	 table += '<tr>';
	 table += '<th style="width:107px;text-align:center" class="table_head">PhoneNo</th>';
	 table += '<th style="width:167px;text-align:center;" class="table_head">Date and Time</th>';
	 table += '<th style="width:267px;text-align:center;" class="table_head">Reminder</th>';
	 table += '<th style="width:42px;text-align:center;" class="table_head"></th>';
	 table += '</tr>';
	   
	for ( var i=0;i<remaidersArr.length;i++ )
	{
		var remainder 			= remaidersArr[i];
		
		var transId 			= remainder.transId;
		
		var transRemainderId 	= remainder.transRemainderId;
		
		var regnKey 			= remainder.regnKey;
		
		var customerKey 		= remainder.customerKey;
		
		var remainderStr 		= remainder.remainderStr;
		
		
		var dueDate 			= remainder.dueDate;
		
		table += '<tr style="background-color: #f2f6f7;" id="trans_remainder_'+transRemainderId+'">';
		table += '<td class="table_data">'+customerKey+'</td>';
		table += '<td class="table_data">'+dueDate+'</td>';
		table += '<td class="table_data" style="text-align:left;">'+remainderStr+'</td>';
		table += '<td class="table_data" style="padding-left:10px;">';
		table += '<input type="button" class="remainder_del_btn" onclick=deleteRemainder("'+transRemainderId+'")></td>';
		table += '</tr>';
	}
	
	if( remaidersArr.length == 0 )
	{
		table += '<tr> <td colspan="4" style="text-align:center;">No Reminders</td> </tr>';
	}
	
	table += '</table>';
	
	$("#remainders_content .mCSB_container").append(table);
	
	$("#remainders_content").mCustomScrollbar("update");
	
	$("#remainders").show();
}

function sendMail( )
{
	var emailId =$("#hidden_mail").val();

	var message=$("#message_mail").val();
	
	
	if(message=="")
	{
		alert("Enter Your Message");
		
		return;
	}
	showAjaxLoader( );
	
	var mailObj=new Object();
	
	mailObj.customerKeys=emailId;
		
	mailObj.message=message;
	
	mailObj.companyName =$("#compName").val();
	
    mailObj.senderName =$("#firstName").val()+" "+$("#lastName").val();
    
    mailObj.senderKey = $("#EmailAddress").val();
		
	var mailData=JSON.stringify(mailObj);
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/EmailServlet",
		   dataType: 'json',
		   data:{ 
			   'RequestType':"SendHistoryMail",
			   'Email':mailData,
		       },
		    success: function( resJSON )
			{
				 hideAjaxLoader( );
				   
				  if(resJSON.result == "success")
				  {
					   ShowToast( resJSON.message, 2000 );
					   
					   $("#email_composer").hide();
				  }
				  else 
				  {
						ShowToast( resJSON.message, 2000 );
				  }
			},
		    error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader( );
			  
			}
	});
}


/* show the mail composer popup view */
function showMailComposer( email )
{
	$("#hidden_mail").val(email);
	$("#email_composer").show();
}

/* Used to downlaod the recent transaction EDI files */
function downloadRecentTransEDI( transId )
{
	/*$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/EDIFileDownloadServlet",
		   dataType: 'json',
		   data:{ 
			   'RequestType':"LatestEDIFiles",
			   'TransId':transId,
		       },
		    success: function( resJSON )
			{
				 hideAjaxLoader( );
				   
				  if(resJSON.result == "success")
				  {
					   
				  }
				  else 
				  {
						
				  }
			},
		    error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader( );
			  
			}
	});*/
	
	$("#recent_trans_id").val( transId );
	
	$('#recent_trans_edi_download_form').submit();
	
}



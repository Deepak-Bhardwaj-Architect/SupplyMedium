
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
		
		$("#customer_history_content").hide();
		
		$("#transaction_history_content").show();
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
					  		var companyName		= customer.companyName;
							var address			= customer.address;
							var email			= customer.email;
							var recentTransId	= customer.recentTransId;
							
							
														
							var ratingsCount	= customer.ratingsCount;
							var phoneNo			= customer.companyPhoneNo;
							
							var remindersArr	= new Array(customer.reminders.length);
							
							remindersArr = customer.reminders;
							
							remainderMap[ phoneNo ] = remindersArr;
							
							var historyLink 	= '<a style="text-decoration:underline;" id="'+phoneNo+'">history</a>';	
							
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
							
							$( "#remainder_"+phoneNo ).die();
							
							$( "#remainder_"+phoneNo ).live( "click", function()
							{
								var arr = this.id.split("_");
								
								showRemainders( arr[1] );
							});
							
					  		
					  		customerTable.fnAddData( [phoneNo, companyName, address, emailLink, historyLink, remainderLink, documentLink, 
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



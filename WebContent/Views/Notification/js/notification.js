function notificationOnload()
{
	$("#notification_content").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	getNotification();
}

/* This method is used to fetch the all the notification of the user */
function getNotification()
{
	var userKey = $("#EmailAddress").val();
	
	
	$.ajax(
	{
		type : 'POST',
		url : getBaseURL() + '/NotificationServlet',
		data :
		{
			'RequestType' : 'FetchNotification',
			'ReceiverKey' : userKey
		},
		cache : false,
		success : function(ResultObj)
		{

			if (ResultObj.result == "success")
			{
				var notificationArr = new Array();
				
				notificationArr = ResultObj.notifications;
				
				$("#notification_count").text("0");
				
				$("#notification_content .mCSB_container").empty();
				
				parseNotification( notificationArr );
			}
			

		},
		error : function()
		{


		}
	});
}

/* Used to parse the notification JSON and place in notification view */

function parseNotification( notificationArr )
{
	var dateGroupStr = "";
	
	var notificationCount = parseInt($("#notification_count").text(  ));
	
	notificationCount = notificationCount + notificationArr.length;
	
	if( notificationCount == 0 )
	{
		$("#notification_count").hide();
		
		$("#notification_count").text(0);
		
	}
	else
	{
		$("#notification_count").text( notificationCount );
		
		$("#notification_count").show();
	}
	
	
	for( var i=0;i<notificationArr.length;i++ )
	{
		var notifiData = notificationArr[i];
		
		var senderName = notifiData.senderName;
		
		var senderKey = notifiData.senderKey;
		
		var senderPhotoPath = notifiData.senderPhotoPath;
		
		var senderCompName = notifiData.senderCompName;
		
		var timeStamp = notifiData.createdTimestamp;
		
		var message  = notifiData.message;
		
		var notificationType = notifiData.notificationType;
	
		var notificationId = notifiData.notificationId;
		
		var dateArr = timeStamp.split(",");
		
		var date = dateArr[0];
		
		if( i==0 )
		{
			latestNotifiTimestamp = timeStamp;
			
			//alert( "latest time stamp="+latestNotifiTimestamp );
		}
		
		if(senderPhotoPath == 'null' || senderPhotoPath == "" )
			imagePath = getBaseURL() +"Views/PrivateMsg/images/no_image_msg.png";
		

		if( dateGroupStr != date )
		{
			
			var dateSep = '<div class="separator">'+date;;
			dateSep += '</div>';
			
			$("#notification_content .mCSB_container").append( dateSep );
		}
		
		
		dateGroupStr = date;
		
		var datetime = convertLocalFromGMT(timeStamp); // Convert GMT time to local time
		
		var dateTimeArr = datetime.split(" ");
		
		var time = "";
		
		if( dateTimeArr.length == 2)
		{
			time = tConvert (dateTimeArr[1]); // convert 24 hours to 12 hours
		}
		
		var connStr = "";
		var id="notifi_"+notificationId;
		
		connStr += '<div class="notificationcontainer" id="'+id+'">';
		connStr += '<div class="content_name">'+senderName+'';
		connStr += '</div>';
		connStr += '<div class="noti_time">'+time+'';
		connStr += '</div>';
		connStr += '<div class="content_det" >'+senderKey+'';
		connStr += '</div>';
		connStr += '<div class="content_det" >'+senderCompName+'';
		connStr += '</div>';
		connStr += '<div class="content_det" id="'+id+'_message">'+message+'';
		connStr += '</div>';
		connStr += '<div id="'+id+"_senderkey"+'" style="display:none;">'+senderKey+'</div>';
		
		if( notificationType == "NewConnectionRequest")
		{
			connStr += '<div class="requestcontainer">';
			connStr += '<input type="button" value="Accept" id="'+id+'_accept" class="acc_but">';
			connStr += '<input type="button" value="Reject" id="'+id+'_reject" class="rej_but">';
			//connStr += '<div class="lab_style">Ignore this message</div>';
			connStr += '</div>';
		}
		
							
		connStr += '<div id="'+id+'_type" style="display:none;">'+notificationType+'';
		connStr += '</div>';
		
		$("#"+id+"_accept").live("click",function( event )
		{
			 event.stopPropagation();
				 
			 var divIdSplitArr = this.id.split("_");
							 
			 var divId = divIdSplitArr[0]+"_"+divIdSplitArr[1];
							 
			 var _senderKey = $("#"+divId+"_senderkey").text();
							 
			 $("#notifi_"+divIdSplitArr[1]).remove();
								
			 $("#notification_"+divIdSplitArr[1]).remove();
							 
			 acceptConnection( _senderKey );
			 
			 deleteNotification( divIdSplitArr[1] );
		});
						
		$("#"+id+"_reject").live("click",function( event )
		{
			event.stopPropagation();

			var divIdSplitArr = this.id.split("_");
							 
			var divId = divIdSplitArr[0]+"_"+divIdSplitArr[1];
						 
			var _senderKey = $("#"+divId+"_senderkey").text();
							 
			$("#notifi_"+divIdSplitArr[1]).remove();
								
			$("#notification_"+divIdSplitArr[1]).remove();
							 
			rejectConnection( _senderKey );
			
			deleteNotification( divIdSplitArr[1] );
		});
		
		
		$("#"+id).live("click",function( event )
		{
			var arr = this.id.split("_");
			
			var _notificationId = arr[1];
			
			deleteNotification( _notificationId );
			
			var _notificationType = $("#"+this.id+"_type").text();
			
			var _message = $("#"+this.id+"_message").text();
			
			removeMenuSelection();
			
			
			if( _notificationType == "RFQ" )
				showRFQTab();
			else if( _notificationType == "Quote" )
				showQuoteTab();
			else if( _notificationType == "PO" )
				showPOTab();
			else if( _notificationType == "Invoice" )
				showInvoiceTab();
			else if( _notificationType == "VendorRegistration" )
				showVenRegTab( _message );
			else if( _notificationType == "NewsFeed" )
				showNewsFeedTab();
			else if( _notificationType == "PrivateMessage" )
				showPMTab();
			else if( _notificationType == "NewConnectionRequest" || _notificationType == "NewConnectionResponse")
				showConnectionTab();
			
		});
		
		$("#notification_content .mCSB_container").append( connStr );
		
		$("#notification_content").mCustomScrollbar("update");
	}
}


/* This method is used to delete the notification */

function deleteNotification( notificationId )
{

	$.ajax(
	{
		type : 'POST',
		url : getBaseURL() + '/NotificationServlet',
		
		data :
		{
			'RequestType' : 'DeleteNotification',
			'NotificationId' : notificationId
		},
		cache : false,
		success : function(ResultObj)
		{

			if (ResultObj.result == "success")
			{
				$("#notifi_"+notificationId).remove();
				
				$("#notification_"+notificationId).remove();
				
				var notificationCount = parseInt($("#notification_count").text(  ));
				
				notificationCount = notificationCount -1;
				
				if( notificationCount == 0 )
				{
					$("#notification_count").hide();
					$("#notification_count").text( 0 );
				}
					
				else
				{
					$("#notification_count").text( notificationCount );
					
					$("#notification_count").show();
				}
				
			}

		},
		error : function()
		{


		}
	});
}


/* used to remove the already selected menu highlight */
function removeMenuSelection()
{
	// Remove the Menu if alredy any one selected 
	var item=$(".nav_Main .navmainSelected");
	
	for(var i=0;i<item.length;i++) 
	{
		if(this.id!=item[i].id)
		{
			$(item[i]).removeClass("navmainSelected");
		}				
					
	}
	

	// hide the sub menus
	var item=$(".nav_Sub ul");
	
	for(var i=0;i<item.length;i++)
	{
		$(item[i]).hide();			
	}
	
	
}


function addSubMenu( mainMenuId )
{
	// Change the Main Menu Selected Item to highlight 
	$("#"+mainMenuId).addClass("navmainSelected");
	
	// Based on the Main Menu Select the Sub Menu 
	
	var SelectedID="#Sub"+mainMenuId;	
	
	var item=$(".nav_Sub ul");
	
	for(var i=0;i<item.length;i++)
	{
		if(SelectedID!=item[i].id)
		{
			$(item[i]).hide();
		}				
					
	}
	
	
	$(SelectedID).show(); // Show the Selected Sub Menu  
	
	// Unselect the Submenu If any on is selected 
	var item=$(SelectedID+" .navsubSelected");
	
	for(var i=0;i<item.length;i++)
	{
		$(item[i]).removeClass("navsubSelected");		
	}
	
}

function showRFQTab()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	addSubMenu( "Transactions" );
	
	$("#SubTransactions_Transaction").addClass("navsubSelected");
	
	$("#mainpage").load("Views/Transaction/TransCommon/jsp/transaction.jsp", function() 
	{
		//$("#content_loader").hide();
	});;
}


function showQuoteTab()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	addSubMenu( "Transactions" );
	
	$("#SubTransactions_Transaction").addClass("navsubSelected");
	
	$("#mainpage").load("Views/Transaction/TransCommon/jsp/transaction.jsp", function() 
	{
		$("#trans_content").empty();
		
		$('#trans_content').load('Views/Transaction/Quote/jsp/quote.jsp', function() 
		{
			$("#rfq_tab").removeClass("main_tab_select");
			$("#rfq_tab").addClass("main_tab_unselect");
			
			$("#quote_tab").removeClass("main_tab_unselect");
			$("#quote_tab").addClass("main_tab_select");
			
			$("#po_tab").removeClass("main_tab_select");
			$("#po_tab").addClass("main_tab_unselect");
			
			$("#invoice_tab").removeClass("main_tab_select");
			$("#invoice_tab").addClass("main_tab_unselect");
			
		});
	});;
}

function showPOTab()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	addSubMenu( "Transactions" );
	
	$("#SubTransactions_Transaction").addClass("navsubSelected");
	
	$("#mainpage").load("Views/Transaction/TransCommon/jsp/transaction.jsp", function() 
	{

		$("#trans_content").empty();
		
		$('#trans_content').load('Views/Transaction/PO/jsp/purchase_order.jsp', function() 
		{

			$("#rfq_tab").removeClass("main_tab_select");
			$("#rfq_tab").addClass("main_tab_unselect");
			
			$("#quote_tab").removeClass("main_tab_select");
			$("#quote_tab").addClass("main_tab_unselect");
			
			$("#po_tab").removeClass("main_tab_unselect");
			$("#po_tab").addClass("main_tab_select");
			
			$("#invoice_tab").removeClass("main_tab_select");
			$("#invoice_tab").addClass("main_tab_unselect");
			
		});
		
		
	});;
}


function showInvoiceTab()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	addSubMenu( "Transactions" );
	
	$("#SubTransactions_Transaction").addClass("navsubSelected");
	
	$("#mainpage").load("Views/Transaction/TransCommon/jsp/transaction.jsp", function() 
	{
		$("#trans_content").empty();
		
		$('#trans_content').load('Views/Transaction/Invoice/jsp/invoice.jsp', function() 
		{
			$("#rfq_tab").removeClass("main_tab_select");
			$("#rfq_tab").addClass("main_tab_unselect");
			
			$("#quote_tab").removeClass("main_tab_select");
			$("#quote_tab").addClass("main_tab_unselect");
			
			$("#po_tab").removeClass("main_tab_select");
			$("#po_tab").addClass("main_tab_unselect");
			
			$("#invoice_tab").removeClass("main_tab_unselect");
			$("#invoice_tab").addClass("main_tab_select");
			
		});
	});;
}


function showVenRegTab()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	if( message.indexOf("New Vendor") !== -1 || message.indexOf("answered") !== -1 )
	{
		addSubMenu( "Suppliers" );
		
		$("#SubSuppliers_Vendor_Registration").addClass("navsubSelected");
		
		$("#mainpage").load("Views/VendorReg/jsp/buyer_ven_reg.jsp", function() 
		{
			
		});
	}
	else
	{
		addSubMenu( "Buyers" );
		
		$("#SubBuyers_Vendor_Registration").addClass("navsubSelected");
		
		$("#mainpage").load("Views/VendorReg/jsp/supplier_ven_reg.js", function() 
		{
			
		});;
	}
}

function showNewsFeedTab()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	addSubMenu( "Network" );
	
	$("#SubNetwork_News_Feed").addClass("navsubSelected");
	
	$("#mainpage").load("Views/NewsRoom/jsp/newsroom.jsp", function() 
			{
		//$("#content_loader").hide();
	});
}

function showPMTab()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	addSubMenu( "Network" );
	
	$("#SubNetwork_Messages").addClass("navsubSelected");
	
	$("#mainpage").load("Views/PrivateMsg/jsp/private_msg.jsp", function() 
			{
		//$("#content_loader").hide();
	});
}

function showConnectionTab()
{
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	addSubMenu( "Network" );
	
	$("#SubNetwork_My_Connections").addClass("navsubSelected");
	
	$("#mainpage").load("Views/MyConn/jsp/connections.jsp", function() 
	{
		//$("#content_loader").hide();
	});
}

function showNotificationTab()
{
	
	removeMenuSelection();
	
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	addSubMenu( "Network" );
	
	$("#SubNetwork_Notification").addClass("navsubSelected");		
	
	$("#mainpage").load("Views/Notification/jsp/notification.jsp", function() 
			{
		//$("#content_loader").hide();
	});
}


/* It is used to accept the connection request. Once accepted both are friends. */
function acceptConnection( fromUserKey )
{
	var userKey 	= $("#EmailAddress").val();
	
	var name 		= $("#firstName").val()+" "+$("#lastName").val();
	var compKey 	= $("#regnkey").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnStatusServlet",
		data : {
			
			'RequestType' : 'AcceptConnection',
			'FromUserKey':fromUserKey,
			
			'ToUserKey':userKey,
			'ToRegnKey':compKey,
			'ToUserName':name,
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				 ShowToast( data.message,2000 );
			}

			else 
			{
				 ShowToast( data.message,2000 );
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
		}
	});
}

/* It is used to reject the connection request.*/
function rejectConnection( fromUserKey )
{
	
	var userKey 	= $("#EmailAddress").val();
	
	var name 		= $("#firstName").val()+" "+$("#lastName").val();
	var compKey 	= $("#regnkey").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnStatusServlet",
		data : {
			
			'RequestType' : 'RejectConnection',
			'FromUserKey':fromUserKey,
			
			
			'ToUserKey':userKey,
			'ToRegnKey':compKey,
			'ToUserName':name,
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				 ShowToast( data.message,2000 );
			}

			else 
			{
				 ShowToast( data.message,2000 );
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
		}
	});
}


var selConnUserKey = "";

var selectedConnId = "";

var selectedUserName = "";

var userName = "";

var mail="";

$(document).ready(
		function() 
		{
	$("#my_conn_list").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	$("#chatcontainer").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	getMyConnections();
	
	$("#post_message_btn").css('cursor','pointer');
	
	$("#post_message_btn").click( sendMessage );
	
	userName = $("#firstName").val()+" "+$("#lastName").val();
	
	
	$("#search_member_text").keydown(function (e) {

		   if (e.which == 9)
		   {
			   tabKeyPressedInSearchConn( );
		   }
		      
		});

	
});



/* 
 * This method is called when user press the tab key from search connection 
 * text box
 */

function tabKeyPressedInSearchConn( )
{
	if ($('#search_conn_0').length)
	{
	    selConnUserKey = $("#search_conn_0_email").text();
	    	
	    selectedUserName = $("#search_conn_0_name").text();
	    	
	    $("#search_result").hide();
	    	
	    $("#search_member_text").val( selectedUserName );
	    	
	    fetchMessage();
	}
	
}

/* This method is used to get the user connections
 *for the login user 
 */

function getMyConnections()
{
	showAjaxLoader();
	
	var userKey = $("#EmailAddress").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnNetworkServlet",
		data : {
			'RequestType' : 'MyMessageConnections',
			'UserKey' : userKey,
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				var connections = new Array( data.connections.length );
				
				connections = data.connections;
				
				parseMyConnections(connections);
				
				myConnCount = connections.length;
			}

			else 
			{
				
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
		}
	});
}

/* This method is used to parse the my connections json and
*  place the details in view.
*/
function parseMyConnections( connections )
{
	$("#my_conn_list .mCSB_container").empty();
	
	for ( var i=0;i<connections.length;i++) 
	{
		var connection = connections[i];
		
		var name = connection.toUserName;
		
		var email =connection.toUserKey;
			
		var companyName = connection.toCompName;
		
		var companyKey = connection.toCompKey;
		
		var imagePath = connection.photoPath;
		
		if(imagePath == 'null' || imagePath == "" )
			imagePath = getBaseURL() +"Views/PrivateMsg/images/no_image_msg.png";
		
		var connStr = "";
		
		var id="my_conn_"+i;
		
		
		connStr += '<div class="listcontainer" id="'+id+'">';

		connStr += '<input type="hidden" id='+id+"_email"+' value='+email+'>';
		connStr += '<input type="hidden" id='+id+"_compKey"+' value='+companyKey+'>';
		connStr += '<div class="img_right">';
		connStr +='<img src="'+imagePath+'" class="listimg" id="message_thumbnail">';
		connStr +='</div>';
		connStr +='<div class="list_right">';
		connStr +='<div class="name" id='+id+"_name"+'>'+name+'</div>';
		connStr +='<div class="cont" id='+id+"_compName"+'>'+companyName+'</div>';
		connStr +='<div class="cont" >'+email+'</div>';
		connStr +='</div>';
		connStr +='<input type="button" id='+id+"_del_btn"+' class="delete_messages_btn" style="display:none;" title="Delete Messages" onclick="deleteMessages(\''+email+'\')">';
		connStr += '<div class="listseperator"></div>';
		connStr +='</div>';
		
		$("#"+id).die("click");
		
		$("#"+id).live("click",function( event )
		{
			selConnUserKey 		= $("#"+this.id+"_email").val();
			
			selectedUserName 	= $("#"+this.id+"_name").text();
			
			fetchMessage();
			
			if(selectedConnId != this.id ) 
			{
				$("#"+selectedConnId).addClass("listcontainer");
				
				$("#"+selectedConnId).removeClass("listcontainer_selected");
				
				$("#"+selectedConnId+"_del_btn").hide();
				
				$("#"+this.id).removeClass("listcontainer");
				
				$("#"+this.id).addClass("listcontainer_selected");
				
				$("#"+this.id+"_del_btn").show();
				
				selectedConnId = this.id;
			}
			
			
		});
		
		$("#my_conn_list .mCSB_container").append(connStr);
		
		if (connection.photoPath != "null" &&  connection.photoPath != "")
		{
			
			createThumbnail( $("#message_thumbnail"),46,46 );
		}
		
		$("#my_conn_list").mCustomScrollbar("update");
	
	}
}

function deleteMessages( email )
{
	
	mail=email;
	
	
	var message = "This operation will delete all the Messages permanently. Do you want to delete?";
	
	showWarning( message );
	
}

function deleteConfirm()
{
	
	var fromuserKey = $("#EmailAddress").val();
	showAjaxLoader();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/PrivateMessageServlet",
		data : {
			'RequestType' : 'DeleteMessage',
			'FromUserKey' : fromuserKey,
			'ToUserKey' : mail,
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				$("#chatcontainer .mCSB_container").empty();
				
				$("#chatcontainer").mCustomScrollbar("update");
				getMyConnections();				
				ShowToast(data.message,2000);
			}

			else 
			{
				
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
		}
	});
}


/* This method is used to send the message to selected connection of the user */
function sendMessage()
{
	var fromuserKey = $("#EmailAddress").val();
	var message = $("#message").val();
	
	if(message.length==0)
		return;

	
	showAjaxLoader();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/PrivateMessageServlet",
		data : {
			'RequestType' : 'AddMessage',
			'FromUserKey' : fromuserKey,
			'ToUserKey' : selConnUserKey,
			'Message':message
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				
				ShowToast(data.message,2000);
				
				var str = '<div class="messagecontainer">';
				str += '<div class="message_left">';
				str += '<img src="'+$("#user_image_url").val()+'" class="msg_img">';
				str += '</div>';
				str += '<div class="message_right">';
				str += '<div class="msg_name">'+userName+'';
				str += '</div>';
				str += '<div class="msg_text"><p>'+message+'</p>';
				str += '</div>';
				str += '</div>';
				str += '</div>';
				
				$("#chatcontainer .mCSB_container").append( str );
				
				$("#chatcontainer").mCustomScrollbar("update");
				
				$("#chatcontainer").mCustomScrollbar("scrollTo","last");
				
				$("#message").val("");
			}

			else 
			{
				
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
		}
	});
}


/* This method is used to send the message to selected connection of the user */
function fetchMessage()
{
	showAjaxLoader();
	
	var fromuserKey = $("#EmailAddress").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/PrivateMessageServlet",
		data : {
			'RequestType' : 'FetchMessages',
			'FromUserKey' : fromuserKey,
			'ToUserKey' : selConnUserKey,

		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				var messageArr = new Array();
				
				messageArr = data.messages;
				
				parseMessages( messageArr );
				
			}

			else 
			{
				
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
		}
	});
}

/* This method is used to parse the message */
function parseMessages( messageArr )
{
	$("#chatcontainer .mCSB_container").empty();
	
	var dateGroupStr = "";
	
	for( var i=0;i<messageArr.length;i++ )
	{
		var messagedata = messageArr[i];
		
		//var messageId = messagedata.message_Id;
		
		var fromUserKey = messagedata.fromUserKey;
		
		//var toUserKey   = messagedata.toUserKey;
		
		var fromUserProfilePic = messagedata.fromUserProfilePic;
		
		//var toUserProfilePic = messagedata.toUserProfilePic;
		
		var message  = messagedata.message;
		
		var createdTime = messagedata.createdTime;
		
		var name = "";
		
		if( fromUserKey == $("#EmailAddress").val() )
		{
			name = userName;
		}
		else
		{
			name = selectedUserName;
		}

		
		if(fromUserProfilePic == 'null' || fromUserProfilePic == "" )
			fromUserProfilePic = getBaseURL() +"Views/PrivateMsg/images/no_image_msg.png";
		
		if( dateGroupStr != createdTime )
		{
			var dateSep = '<div class="timelinecontainer" id="'+createdTime+'">';
			dateSep += '<div class="marginline_left">';
			dateSep += '</div>';
			dateSep += '<div class="year">'+createdTime+'';
			dateSep += '</div>';
			dateSep += '<div class="marginline_right">';
			dateSep += '</div>';
			dateSep += '</div>';
			
			$("#chatcontainer .mCSB_container").append( dateSep );
		}
		
		
		dateGroupStr = createdTime;
		
		
		var str = '<div class="messagecontainer">';
		str += '<div class="message_left">';
		str += '<img src="'+fromUserProfilePic+'" class="msg_img" id="msg_img">';
		str += '</div>';
		str += '<div class="message_right">';
		str += '<div class="msg_name">'+name+'';
		str += '</div>';
		str += '<div class="msg_text"><p>'+message+'</p>';
		str += '</div>';
		str += '</div>';
		str += '</div>';
		
		$("#chatcontainer .mCSB_container").append( str );
		
		if (messagedata.fromUserProfilePic != "null" &&  messagedata.fromUserProfilePic != "")
		{
			
			createThumbnail( $("#msg_img"),46,46 );
		}
		
		$("#chatcontainer").mCustomScrollbar("update");
		
		$("#chatcontainer").mCustomScrollbar("scrollTo","last");
	}
}

/* user to get the members to add in the watch list */
function getSearchConnections( searchText )
{
	if( searchText == "" )
	{
		$("#search_result").hide();
		
		return;
	}
		
	var userKey = $("#EmailAddress").val();
	
	//showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/MyConnNetworkServlet",
		   data:{ 
		        'RequestType': 'SearchMyConnections', 
		        'UserKey':userKey,
		        'SearchText':searchText,
		    } ,
		   cache:false,
		   success: function( jsonData )
		   {
			  // hideAjaxLoader();
			   
			   if( jsonData.result == "success")
			   {
				   var connectionArr = new Array( jsonData.connections.length );
				  	
				   connectionArr = jsonData.connections;
					
				   parseSearchConnections( connectionArr );
				   
			   }
			  
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	//hideAjaxLoader();
		    	
		    	alert("error");
		    	 
		    	//$('#folderassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

/* It is used to parse the search connections and place in dropdown */
function parseSearchConnections( connectionArr ) 
{
	var userKey = $("#EmailAddress").val();
	
	$("#search_result").empty();
	
	$("#search_result").show();
	
	for( var i=0;i<connectionArr.length;i++ )
	{
		var connectionData = connectionArr[ i ];
		
		var username = "";
		var compname = "";
		var email  = "";
		var photoPath = connectionData.photoPath;
		if(photoPath == 'null' || photoPath == "" )
			photoPath = getBaseURL() +"Views/MyConn/images/no_image_small.png";
		
		if( connectionData.fromUserKey!= userKey )
		{
			username = connectionData.fromUserName;
			compname = connectionData.fromCompName;
			email  = connectionData.fromUserKey;
		}
		else
		{
			username = connectionData.toUserName;
			compname = connectionData.toCompName;
			email  = connectionData.toUserKey;
		}
		
		var str = '<div class="pm_listcontainer" id="search_conn_'+i+'">';
		str += '<div class="img_right">';
		str += '<img src="'+photoPath+'" class="listimg">';
		str += '</div>';
		str += '<div class="list_right">';
		str += '<div class="name" id="search_conn_'+i+'_name">'+username+'</div>';
		str += '<div class="cont" id="search_conn_'+i+'_compName">'+compname+'</div>';
		str += '<div class="cont" id="search_conn_'+i+'_email">'+email+'</div>';
		str += '</div>';
		str += '<div class="listseperator"></div>';
		str += '</div>';
		
		$("#search_result").append( str );
		
		$("#search_conn_"+i).live("click",function()
		{
			selConnUserKey = $("#"+this.id+"_email").text();
			
			selectedUserName = $("#"+this.id+"_name").text();
			
			$("#search_result").hide();
			
			$("#search_member_text").val( selectedUserName );
			
			fetchMessage();
		});
	}
}
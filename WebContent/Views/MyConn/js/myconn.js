var myConnUserName 	= "";
var myConnUserKey 	= "";
var myConnCompName 	= "";
var myConnCompKey 	= "";

var	selectedMyConnId = "";

var mail="";

var myConnCount	= 0;

/* This method is used to get the user connections
 * for the login user 
 */

function getMyConnections()
{
	showAjaxLoader();
	
	var userKey = $("#EmailAddress").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnNetworkServlet",
		data : {
			'RequestType' : 'MyConnections',
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

/* This method is used to parse the my connections json and place the 
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
			imagePath = getBaseURL() +"Views/MyConn/images/no_image_small.png";
		
		var connStr = "";
		
		var id="my_conn_"+i;
		
		if( i== 0 )
		{
			connStr += '<div class="listcontainer_selected" id="'+id+'">';
			getUserProfile( email,'my_conn_right' );
			
			myConnUserName 	= name;
			myConnUserKey 		= email;
			myConnCompName 	= companyName;
			myConnCompKey 		= companyKey;
			
			selectedMyConnId = id;
		}
		else
		{
			connStr += '<div class="listcontainer" id="'+id+'">';
		}
		
		connStr += '<input type="hidden" id='+id+"_email"+' value='+email+'>';
		connStr += '<input type="hidden" id='+id+"_compKey"+' value='+companyKey+'>';
		connStr += '<div class="img_right">';
		connStr +='<img src="'+imagePath+'" class="listimg"  id="my_conn_thumbnail">';
		connStr +='</div>';
		connStr +='<div class="list_right">';
		connStr +='<div class="name" id='+id+"_name"+'>'+name+'</div>';
		connStr +='<div class="cont" id='+id+"_compName"+'>'+companyName+'</div>';
		connStr +='<div class="cont" >'+email+'</div>';
		connStr +='</div>';
		
		connStr +='</div>';
		connStr +='</div>';
		
		
		$("#"+id).live("click",function( event )
		{
			myConnUserName 	= $("#"+this.id+"_name").text();
			myConnUserKey 		= $("#"+this.id+"_email").val();
			myConnCompName 	= $("#"+this.id+"_compKey").val();
			myConnCompKey 		= $("#"+this.id+"_compName").text();
			
			getUserProfile( myConnUserKey,"my_conn_right" );
			
			if(selectedMyConnId != this.id ) 
			{
				$("#"+selectedMyConnId).addClass("listcontainer");
				
				$("#"+selectedMyConnId).removeClass("listcontainer_selected");
				
				$("#"+selectedMyConnId+"_del_btn").hide();
				
				$("#"+this.id).removeClass("listcontainer");
				
				$("#"+this.id).addClass("listcontainer_selected");
				
				$("#"+this.id+"_del_btn").show();
				
				selectedMyConnId = this.id;
			}
		});
		
		$("#my_conn_list .mCSB_container").append(connStr);
		
		
		if (connection.photoPath != "null" &&  connection.photoPath != "")
		{
			
			createThumbnail( $("#my_conn_thumbnail"),46,46 );
		}
		
		$("#my_conn_list").mCustomScrollbar("update");
	
	}
}



function deleteConnections(email)
{
	mail=email;	
	
	var message = "This operation will delete the connections permanently. Do you want to delete?";
	
	showWarning( message );
}


function deleteConfirm()
{
	var fromUserKey=$("#EmailAddress").val();
	showAjaxLoader();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnStatusServlet",
		data : {
			'RequestType' : 'DeleteConnection',
			'FromUserKey' : fromUserKey,
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




/* This method is used to send the private message to selected user */
function sendPrivateMsg()
{
	
	$("#mainpage").empty();
	
	$("#content_loader").show();
	
	addSubMenu( "Network" );
	
	$("#SubNetwork_Messages").addClass("navsubSelected");
	
	$("#mainpage").load("Views/PrivateMsg/jsp/private_msg.jsp", function() 
	{
		selConnUserKey = myConnUserKey;
    	
	    selectedUserName = myConnUserName;
	    	
	    $("#search_result").hide();
	    	
	    $("#search_member_text").val( selectedUserName );
	    
	    $("#message").focus();
	    	
	    fetchMessage();
		
	});
}

/* It is open the email composer popup */
function openEmailPopup()
{
	
	$("#hidden_mail").val();
	$("#email_composer").show();
	$("#cancel_mail_btn").click(function()
	{
		$("#email_composer").hide();
		$("#message_mail").val("");
	});
	
	
}


/* Used to send the mail */
function sendMail( )
{
	var emailId =myConnUserKey;

	var message=$("#message_mail").val();
	
	var companyName=$("#compName").val();
	
	var senderName=$("#firstName").val()+" "+$("#lastName").val();
	
	var senderKey = $("#EmailAddress").val();
	
	showAjaxLoader( );
	
	var mailObj=new Object();
	
	mailObj.customerKeys = emailId;
		
	mailObj.message = message;
	
	mailObj.companyName = companyName;
	
	mailObj.senderName = senderName;
	
	mailObj.senderKey = senderKey;
		
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
					   $("#message_mail").val("");
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
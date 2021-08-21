var pendingConnUserName 	= "";
var pendingConnUserKey 		= "";
var pendingConnCompName 	= "";
var pendingConnCompKey 		= "";
var selectedPenConnId       = "";
var penConnCount			= 0;

/* This method is used to get the pending connections
 * for the login user 
 */

function getPendingConnections()
{
	showAjaxLoader();
	
	var userKey = $("#EmailAddress").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnNetworkServlet",
		data : {
			'RequestType' : 'PendingConnections',
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
				
				parsePendingConnections(connections,userKey);
				
				penConnCount = connections.length;
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

/* This method is used to parse the pending connections json and place the 
*  place the details in view.
*/
function parsePendingConnections( connections,key)
{       //alert(key);
	$("#pending_conn_list .mCSB_container").empty();
	
	for ( var i=0;i<connections.length;i++) 
	{
		var connection = connections[i];
		
		var name = connection.toUserName;
		
		var email =connection.toUserKey;
	 //alert(email);		
		var companyName = connection.toCompName;
		
		var companyKey = connection.toCompKey;
		
		var imagePath = connection.photoPath;
                
                var typ="";
                if(!connection.type=="")
                {
                typ="<div style='color:red'>"+connection.type+"</div>";    
                }                
		if(imagePath == 'null' || imagePath == "" )
			imagePath = getBaseURL() +"Views/MyConn/images/no_image_small.png";
		
		var connStr = "";
		
		var id="pending_conn_"+i;
		
		if( i == 0 )
		{
			connStr += '<div class="listcontainer_selected" id="'+id+'">';
			getUserProfile( email,'pending_conn_right',connection.type );
			pendingConnUserName 	= name;
			pendingConnUserKey 		= email;
			pendingConnCompName 	= companyName;
			pendingConnCompKey 		= companyKey;
			
			selectedPenConnId = id;
		}
		else
		{
			connStr += '<div class="listcontainer" id="'+id+'">';
		}
		connStr += '<input type="hidden" id='+id+"_email"+' value='+email+'>';
		connStr += '<input type="hidden" id='+id+"_compKey"+' value='+companyKey+'>';
		connStr += '<div class="img_right">';
		connStr +='<img src="'+imagePath+'" class="listimg">';
		connStr +='</div>';
		connStr +='<div class="list_right">';
		connStr +='<div class="name" id='+id+"_name"+' style="width:155px;">'+name+'</div>'+typ;
		connStr +='<div class="cont" id='+id+"_compName"+'>'+companyName+'</div>';
		connStr +='<div class="cont" >'+email+'</div>';
		connStr +='</div>';
		connStr += '<div class="listseperator"></div>';
		connStr +='</div>';
		
		$("#"+id).live("click",function( event )
		{
			pendingConnUserName 	= $("#"+this.id+"_name").text();
			pendingConnUserKey 		= $("#"+this.id+"_email").val();
			pendingConnCompKey	 	= $("#"+this.id+"_compKey").val();
			pendingConnCompName 	= $("#"+this.id+"_compName").text();
			
			getUserProfile( newConnUserKey,"new_conn_right",connection.type );
			
			
			$("#"+selectedPenConnId).addClass("listcontainer");
				
			$("#"+selectedPenConnId).removeClass("listcontainer_selected");
			
			$("#"+id).removeClass("listcontainer");
			
			$("#"+id).addClass("listcontainer_selected");
			
			selectedPenConnId = id;
			
		});
		
		$("#pending_conn_list .mCSB_container").append(connStr);
		
		$("#pending_conn_list").mCustomScrollbar("update");
	
		
		
	}
}


/* It is used to accept the connection request. Once accepted both are friends. */
function acceptConnection()
{
	var userKey 	= $("#EmailAddress").val();
	
	var name 		= $("#firstName").val()+" "+$("#lastName").val();
	var compKey 	= $("#regnkey").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnStatusServlet",
		data : {
			
			'RequestType' : 'AcceptConnection',
			'FromUserKey':pendingConnUserKey,
			'FromRegnKey':pendingConnCompKey,
			
			'ToUserKey':userKey,
			'ToRegnKey':compKey,
			'ToUserName':name
			
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				getPendingConnections();
				getMyConnections();
				
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
function rejectConnection()
{
	var userKey 	= $("#EmailAddress").val();
	
	var name 		= $("#firstName").val()+" "+$("#lastName").val();
	var compKey 	= $("#regnkey").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnStatusServlet",
		data : {
			
			'RequestType' : 'RejectConnection',
			'FromUserKey':pendingConnUserKey,
			'FromRegnKey':pendingConnCompKey,
			
			'ToUserKey':userKey,
			'ToRegnKey':compKey,
			'ToUserName':name
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				getPendingConnections();
				getMyConnections();
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

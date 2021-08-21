var newConnUserName 	= "";
var newConnUserKey 		= "";
var newConnCompName 	= "";
var newConnCompKey 		= "";
var	selectedNewConnId 	= "";
var newConnCount		= 0;

/* It is used to search the connection details for given search text
 */
function searchConnections()
{
	showAjaxLoader();
	
	var userKey = $("#EmailAddress").val();
	
	var searchText = $(".connectionsearch").val();
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnNetworkServlet",
		data : {
			'RequestType' : 'SearchConnections',
			'UserKey' : userKey,
			'SearchText' : searchText 
			
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				var connections = new Array( data.connections.length );
				
				connections = data.connections;
				
				parseConnections(connections);
				
				newConnCount = connections.length;
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



/* This method is used to parse the connections json and place the 
*  place the details in view.
*/
function parseConnections( connections )
{
	$("#new_conn_list .mCSB_container").empty();
	
	$(".detailcontainer").remove();
	
	$("#add_conn_btn").hide();
	
	for ( var i=0;i<connections.length;i++) 
	{
		var connection = connections[i];
		
		var name = connection.toUserName;
		
		var email =connection.toUserKey;
			 
		var companyName = connection.toCompName;
		
		var companyKey = connection.toRegnKey;
		
		var imagePath = connection.photoPath;
		
		if(imagePath == 'null' || imagePath == "" )
			imagePath = getBaseURL() +"Views/MyConn/images/no_image_small.png";
		
		var connStr = "";
		
		var id="new_conn_"+i;
		
		if( i== 0 )
		{
			connStr += '<div class="listcontainer_selected" id="'+id+'">';
			getUserProfile( email,'new_conn_right' );
			newConnUserName 	= name;
			newConnUserKey 		= email;
			newConnCompName 	= companyName;
			newConnCompKey 		= companyKey;
			
			selectedNewConnId = id;
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
		connStr +='<div class="name" id='+id+"_name"+'>'+name+'</div>';
		connStr +='<div class="cont" id='+id+"_compName"+'>'+companyName+'</div>';
		connStr +='<div class="cont" >'+email+'</div>';
		connStr +='</div>';
		connStr += '<div class="listseperator"></div>';
		connStr +='</div>';
		
		$("#"+id).live("click",function( event )
		{
			newConnUserName 	= $("#"+this.id+"_name").text();
			newConnUserKey 		= $("#"+this.id+"_email").val();
			newConnCompName 	= $("#"+this.id+"_compName").text();
			newConnCompKey 		= $("#"+this.id+"_compKey").val();
			
			getUserProfile( newConnUserKey,"new_conn_right" );
			
			if(selectedNewConnId != this.id ) 
			{
				$("#"+selectedNewConnId).addClass("listcontainer");
				
				$("#"+selectedNewConnId).removeClass("listcontainer_selected");
				
				$("#"+this.id).removeClass("listcontainer");
				
				$("#"+this.id).addClass("listcontainer_selected");
				
				selectedNewConnId = this.id;
			}
			
		});
		
		$("#new_conn_list .mCSB_container").append(connStr);
	}
	
	$("#new_conn_list").mCustomScrollbar("update");
}

/* This method is used to send the connection request to selected connection */ 

function addConnection()
{
        $("#add_conn_btn").attr("disabled", "disabled");
	var name 		= $("#firstName").val()+" "+$("#lastName").val();
	var userKey 	= $("#EmailAddress").val();
	var compName 	= $("#compName").val();
	var compKey 	= $("#regnkey").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnStatusServlet",
		data : {
			
			'RequestType' : 'AddConnection',
			'FromUserName':name,
			'FromUserKey':userKey,
			'FromCompName':compName,
			'FromRegnKey':compKey,
			
			'ToUserName':newConnUserName,
			'ToUserKey':newConnUserKey,
			'ToCompName':newConnCompName,
			'ToRegnKey':newConnCompKey
			
		},
		cache : false,
		success : function(data) 
		{
			
			hideAjaxLoader();
			$("#add_conn_btn").removeAttr("disabled");
			if (data.result == "success")
			{
				 ShowToast( data.message,2000 );
				 
				 var arr = selectedNewConnId.split("_");
				 
				 if( arr.length > 2 )
				 {
					 var connNum = arr[2];
					 
					 if( connNum == 0 )
					 {
						 connNum += 1;
						 
						 $("#"+selectedNewConnId).remove();
						 
						 selectedNewConnId = arr[0]+"_"+arr[1]+"_"+connNum;
						 
						 
						 if ($('#'+selectedNewConnId).length > 0) 
						 {
							$("#"+selectedNewConnId).removeClass("listcontainer");
								
							$("#"+selectedNewConnId).addClass("listcontainer_selected");
								
							newConnUserName 	= $("#"+selectedNewConnId+"_name").text();
							newConnUserKey 		= $("#"+selectedNewConnId+"_email").val();
							newConnCompName 	= $("#"+selectedNewConnId+"_compName").text();
							newConnCompKey 		= $("#"+selectedNewConnId+"_compKey").val();
								
							getUserProfile( newConnUserKey,"new_conn_right" );
						 }
						 else
						 {
							 
							 $(".detailcontainer").hide();
							 $("#add_conn_btn").hide();
						 }
					 }
					 else
					 {
						 connNum -= 1;
						 
						 $("#"+selectedNewConnId).remove();
						 
						 selectedNewConnId = arr[0]+"_"+arr[1]+"_"+connNum;
						 
						 $("#"+selectedNewConnId).removeClass("listcontainer");
							
						$("#"+selectedNewConnId).addClass("listcontainer_selected");
						
						newConnUserName 	= $("#"+selectedNewConnId+"_name").text();
						newConnUserKey 		= $("#"+selectedNewConnId+"_email").val();
						newConnCompName 	= $("#"+selectedNewConnId+"_compName").text();
						newConnCompKey 		= $("#"+selectedNewConnId+"_compKey").val();
						
						getUserProfile( newConnUserKey,"new_conn_right" );
					}
				 }
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

/* This method is used to get the selected connection profile details */
function getUserProfile( userKey,divId,typ )
{	
        //alert(typ);
        if(typ==="Sent")
        {
          $("#cntrl").hide();  
        } 
        else
        {
          $("#cntrl").show();  
        }
	showAjaxLoader();
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/MyConnProfileServlet",
		data : {
			'UserKey' : userKey,
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success")
			{
				$(".detailcontainer").show();
				
				$(".detailcontainer").remove();
				
				var detailStr = "";
				
				if(data.name=="null")
					data.name = "";
				
				if(data.email=="null")
					data.email = "";
				
				if(data.department=="null")
					data.department = "";
				
				if(data.role=="null")
					data.role = "";
				
				if(data.companyName=="null")
					data.companyName = "";
				
				if(data.companyType=="null")
					data.companyType = "";
				
				if(data.phoneNo=="null")
					data.phoneNo = "";
				
				if(data.cell=="null")
					data.cell = "";
				
				if(data.fax=="null")
					data.fax = "";
				
				if(data.address=="null")
					data.address = "";
				
				detailStr +=  '<div class="detailcontainer">';
				detailStr +='<div class="conleftcontainer">';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">Name</div>';
				detailStr +='<div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;'+data.name+'</div>';
				detailStr +='</div>';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">Email ID</div>';
				detailStr +='<div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;'+data.email+'</div>';
				detailStr +='</div>';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">Department</div>';
				detailStr +='<div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;'+data.department+'</div>';
				detailStr +='</div>';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">User Role</div>';
				detailStr +='<div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp; '+data.role+'</div>';
				detailStr +='</div>';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">Company Name</div>';
				detailStr +='<div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;'+data.companyName+'</div>';
				detailStr +='</div>';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">Company Type</div>';
				detailStr +='<div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;'+data.companyType+'</div>';
				detailStr +='</div>';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">Address</div>';
				detailStr +='<div class="valuelable">';
				detailStr +='<div style="float:left; width:5px;">:</div>';
				detailStr +='<div style="margin-left:36px; width:300px;overflow:hidden;	word-wrap:normal; text-align:justify; text-overflow:ellipsis; white-space: nowrap;"> ';
				detailStr +=data.address+'</div>';
				detailStr +='</div>';
				detailStr +='</div>';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">Phone No.</div>';
				detailStr +='<div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;'+data.phoneNo+'</div>';
				detailStr +='</div>';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">Cell</div>';
				detailStr +='<div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;'+data.cell+'</div>';
				detailStr +='</div>';
				
				detailStr +='<div class="lablecontainer">';
				detailStr +='<div class="detlable">Fax</div>';
				detailStr +='<div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;'+data.fax+'</div>';
				detailStr +='</div>';
				
				detailStr +='</div>';
				
				if(data.imagePath == 'null' || data.imagePath == "" )
					data.imagePath = getBaseURL() +"Views/MyConn/images/no_image_big.png";
				
				detailStr +='<div class="conrightcontainer">';
				detailStr +='<img src="'+data.imagePath+'" id="profile_image_big">';
				detailStr +='</div>';
				
				detailStr +='</div>';
				
				$("#"+divId).prepend(detailStr);
				
				
				if (data.imagePath != "null")
				{
					createThumbnail( $("#profile_image_big"),140,140 );
				}
				
				$("#add_conn_btn").hide();
				
				$("#conn_accept_btn").hide();
				
				$("#conn_reject_btn").hide();
				
				$("#send_priMsg_btn").hide();
				
				$("#send_email_btn").hide();
				
				
				if( divId == "new_conn_right")
				{
					$("#add_conn_btn").show();
					
				}
				else if( divId == "pending_conn_right" )
				{
					$("#conn_accept_btn").show();
					
					$("#conn_reject_btn").show();
				}
				else if( divId== "my_conn_right" )
				{
					$("#send_priMsg_btn").show();
					
					$("#send_email_btn").show();
				}
				
					
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
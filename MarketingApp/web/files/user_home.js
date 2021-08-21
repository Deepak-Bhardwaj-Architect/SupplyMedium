$(document).ready(function()
{
	
	//ShowToast("This is the test message",200000 );
	
	$("#invite_btn").click(SendMail);
	
	$("#show_invite_btn" ).on('mouseenter ', function(event)
	{
		$("#invite").animate({'margin-left':'-330px'});
		
		$("#show_btn").stop().animate({'border':'none','box-shadow':'0px 0px 0px #888888'});
		$("#show_btn").removeClass("show_btn");
		$("#show_btn").addClass("show_btn_new");
		
	});
	

	$("#invite").on("mouseleave",function(event)
	{
		$("#invite").animate({'margin-left':'0px'  });
		
		$("#text").val("");
		
		$("#show_btn").stop().animate({'border':'solid #0b90ae','box-shadow':'0px 0px 10px #888888'});
		$("#show_btn").addClass("show_btn");
		$("#show_btn").removeClass("show_btn_new");
	});
	
	$.chat({
	    // your user information
	    user: {
	        Id: $("#EmailAddress").val(),
	        Name: $("#firstName").val()+" "+$("#lastName").val(),
	        ProfilePictureUrl:  $("#user_image_url").val()
	    },
	    // text displayed when the other user is typing
	    typingText: ' is typing...',
	    // the title for the user's list window
	    titleText: 'Chat',
	    // text displayed when there's no other users in the room
	    emptyRoomText: "There's no one around here.",
	    // the adapter you are using. There are 2 implementations out of the box:
	    // SignalRAdapter and LongPollingAdapter (server independent).
	    adapter: new LongPollingAdapter
	});
	
	
});

//$(document).ready(function()
//{
//	
//});


function SendMail()
{
	var emailId=$("#text").val();

	var mailObj=new Object();
	
	mailObj.customerKeys=emailId;
	
	mailObj.message="";
	
	mailObj.companyName=$("#compName").val();
	
    mailObj.senderName=$("#firstName").val()+" "+$("#lastName").val();
    
    mailObj.senderKey = $("#EmailAddress").val();

	
	if( emailId == "" )
	{
		ShowToast( "Enter Email address", 2000 );
		return;
	}
	if(!IsEmail(emailId))
	{
		ShowToast( "Enter valid Email address", 2000 );
		return;
	}
	showAjaxLoader();
	 
	var mailData=JSON.stringify(mailObj);
	
	$.ajax({
		type: "POST",
		   url: getBaseURL()+"/EmailServlet",
		   dataType: 'json',
		   data:{ 
			   'RequestType':"SendInviteMail",
			   'Email':mailData,
		       },
		       success: function( resJSON )
				{
					 hideAjaxLoader( );
					   
					  if(resJSON.result == "success")
					  {
						   ShowToast( "Invite has been sent to "+emailId, 2000 );
						   
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

function inviteMailComposer(email)
{
	
	$("#invite").show();
}



function sendContactUsMail( regnKey,name,email,address )
{
	var mailObj=new Object();
	
	mailObj.customerKeys=regnKey;
		
	mailObj.message=address;
	
	mailObj.companyName ="";
	
    mailObj.senderName =name;
    
    mailObj.senderKey = email;
		
	var mailData=JSON.stringify(mailObj);
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/EmailServlet",
		   dataType: 'json',
		   data:{ 
			   'RequestType':"SendContactUsMail",
			   'Email':mailData,
		       },
		    success: function( resJSON )
			{
				 //hideAjaxLoader( );
				   
				  if(resJSON.result == "success")
				  {
					   ShowToast( "E-Mail has been sent successfully", 2000 );
				  }
				  else 
				  {
						ShowToast( resJSON.message, 2000 );
				  }
			},
		    error : function(xhr, textStatus, errorThrown) 
		    {
			   //hideAjaxLoader( );
			  
			}
	});
}
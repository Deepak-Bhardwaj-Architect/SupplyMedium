$(document).ready(function() {
	
    $("#loginform").validate({
    	//ignore: "",
    	rules: {
 
    	 password: 
        { 
              required: true,
        }, 
       
      },
      messages: {
    	  
    	  email:   {
              required        :   "Enter email address",
              email           :   "Please enter a valid email",
                   },
    	  

          password:   {
                 required        	:   "Enter password",
                   },
    	  
    	  
    	  
      }
    });
    
    if ($('#header').length)
    {
    	$('#header').remove();
    }
    
    if ($('#menucontainer').length)
    {
    	$('#menucontainer').remove();
    }
    
    if ($('#content_loader').length)
    {
    	$('#content_loader').remove();
    }
    if ($('#invite').length)
    {
    	$('#invite').remove();
    }
    if ($('.chat-window').length)
    {
    	$('.chat-window').remove();
    }
    
  });
var upperLowerFlag  = true;
var numericFlag		= true;
var splCharFlag		= true;
var maxChar			= 15;
var minChar			= 8;

$(document).ready(function() {
	
    $("#forgetpwdfrm").validate({
    	//ignore: "",
    	rules: {
      },
      messages: {
    	  
    	  email:   {
              required        :   "Enter email address",
              email           :   "Please enter a valid email",
                   }
  
      }
    });
  });

//$(document).ready(function() 
function loadValidator()
{
	
    $("#pwrresetfrm").validate({
    	//ignore: "",
    	rules: {
    		 password: 
    	        { 
    	              required: true,
    	              minlength:minChar,
    	              maxlength:maxChar,
    	              containuppercase:upperLowerFlag,
    	              containlowercase:upperLowerFlag,
    	              containnumeric:numericFlag,
    	              containspecialchar: splCharFlag,
    	        }, 
    	        retypepassword: 
    	        { 
    	              required: true, equalTo: "#password"
    	        }
      },
      messages: {
    	  
    	  retypepassword:   {
              required        :   "Retype password",
              equalTo           :   "Password mismatch",
                  },
        password:   {
               required        	:   "Enter password",
               minlength           :   "Password should contain minimum of "+minChar+"  characters",
               maxlength           :   "Password should contain maximum of "+maxChar+" characters",
                 },
  
      }
    });
  }

function getPasswordPolicy()
{
	//alet(uuid);
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "PasswordPolicyServlet",
		data : {
			'RequestType':'ResetPassword',
			'uuid' : $('#uuid').val(),
		},
		dataType : 'json',
		cache : false,
		success : function(result) 
		{
		
			$.each(result, function(key, value) 
					{
					//alert("key="+key+";Value="+value);
					if (key == "upperLowerCharFlag") 
					{
						if( value == 0 )
							{
								upperLowerFlag = false;
							}
						else
							{
								upperLowerFlag = true;
							}
					} 
					else if(key == "numericFlag")
					{
						if( value == 0 )
						{
							numericFlag = false;
						}
						else
						{
							numericFlag = true;
						}
					}
					else if(key == "splCharFlag")
					{
						if( value == 0 )
						{
							splCharFlag = false;
						}
						else
						{
							splCharFlag = true;
						}
					}
					else if(key == "minChar")
					{
						minChar  		= value;
					}
					
					
				});
				
				loadValidator();


		},
		error : function(xhr, textStatus, errorThrown) 
		{
			loadValidator();
		}
	});
}
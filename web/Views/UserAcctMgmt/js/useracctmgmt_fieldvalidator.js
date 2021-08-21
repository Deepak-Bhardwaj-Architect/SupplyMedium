
$(function() {
    
  });

var upperLowerFlag  = true;
var numericFlag		= true;
var splCharFlag		= true;
var maxChar			= 15;
var minChar			= 8;

function getPasswordPolicy( )
{
	var companykey = $('#regnkey').val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "PasswordPolicyServlet",
		data : {
			'RequestType':'UserSignup',
			'CompanyKey' : companykey,
		},
		dataType : 'json',
		cache : false,
		success : function(result) 
		{
			
			$.each(result, function(key, value) 
				{
				
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
			
			$("#newpasstxtid").rules("remove", "required minlength maxlength containuppercase " +
					"containlowercase startwithalphabet containspecialchar containnumeric");
			
			$("#newpasstxtid").rules("add", {
				 required: true,
	             minlength:minChar,
	             maxlength:maxChar,
	             containuppercase:upperLowerFlag,
	             containlowercase:upperLowerFlag,
	             startwithalphabet:true,
	             containspecialchar:splCharFlag,
	             containnumeric:numericFlag,
			    messages: {
			    	 required        	:   "Enter password",
			    	 minlength           :   "Password should contain minimum of "+minChar+" characters",
		             maxlength           :   "Password should contain maximum of "+maxChar+" characters",
		             containuppercase:"Password should contain atleast one upper case letter",
		             containlowercase:"Password should contain atleast one lower case letter",
		             startwithalphabet:"Password should starts with alphabet",
		             containspecialchar:"Password should contain atleast one special character",
		             containnumeric:"Password should contain atleast one numeric value",
			    }
			});
			
			//loadValidator();

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			//loadValidator();
		}
	});
}


function loadValidator()
{
	
    $("#useracct_resetpass").validate({
    	//ignore: "",
    	rules: {
    	
    	
    	currentpasstxt:
    	{
    		required:true
    	},
    	
    	newpasstxt: 
        { 
              required: true,
              minlength:minChar,
              maxlength:maxChar,
              containuppercase:upperLowerFlag,
              containlowercase:upperLowerFlag,
              startwithalphabet:true,
              containspecialchar:splCharFlag,
              containnumeric:numericFlag
        }, 
        retypepasstxt: 
        { 
              required: true, equalTo: "#newpasstxtid"
        },
      },
      messages: {

    	
    	currentpasstxt:   
    	 {
              required        :   "Enter the current password"
          },
    	  
          retypepassword:   
          {
                required        :   "Retype password",
                equalTo         :   "Password mismatch",
           },
          newpasstxt:   
          {
                 required        :   "Enter password",
                 minlength       :   "Password should contain minimum of "+minChar+" characters",
                 maxlength       :   "Password should contain maximum of "+maxChar+" characters",
                 containuppercase:"Password should contain atleast one upper case letter",
	             containlowercase:"Password should contain atleast one lower case letter",
	             startwithalphabet:"Password should starts with alphabet",
	             containspecialchar:"Password should contain atleast one special character",
	             containnumeric:"Password should contain atleast one numeric value",
           },
      }
    });
 
}
    
function notifyValidator()
{
   $("#useracct_notifyfrm").validate({
    	//ignore: "",
    	rules: {
    	
    	
		    	altemailtxt:
		    	{
		      		required:true,
		    		email:true
		    	},
		    	
		    	altmobiletxt:
		      	{
		    		integer:true,
		      		minlength:5,
		            maxlength:20
		      	}
      },
      messages: {

    	
		    	altemailtxt:   
		    	{
		    		required        :   "Email cant be empty",
		            email           :   "Please enter a valid email"
		        },
		    	  
		        altmobiletxt:
		      	{
		        	minlength:"Cell number should contain a minimum 5 characters",
		            maxlength:"Cell number should contain a maximum 20 characters",
		            integer:"Cell number must be numeric"
		      	}
      }
    });       
}


$(function() {
    $( document ).tooltip();
  });

var upperLowerFlag  = true;
var numericFlag		= true;
var splCharFlag		= true;
var maxChar			= 15;
var minChar			= 8;

function getPasswordPolicy( companykey )
{
	
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
			
			setPasswordToolTip();
			
			$("#password").rules("remove", "required minlength maxlength containuppercase " +
					"containlowercase startwithalphabet containspecialchar containnumeric");
			
			$("#password").rules("add", {
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


function setPasswordToolTip()
{
	var tooltipText = "Password should be minimum of "+minChar+" character maximum of "+maxChar+
	" characters. Password must contain ";
	if(upperLowerFlag)
	{
		tooltipText = tooltipText+"one uppper case letter, one lower case letter, ";
	}
	if(splCharFlag)
	{
		tooltipText = tooltipText+"one special character, ";
	}
	if(numericFlag)
	{
		tooltipText = tooltipText+"one numeric character, ";
	}
	
	tooltipText = tooltipText+"starts with alphabet. ";
	
	
	
	$('#password').prop('title', tooltipText);
}

function loadValidator()
{
	
    $("#signupform").validate({
    	//ignore: "",
    	rules: {
    	firstname:
    	{
    		required: true,
            minlength:3,
            maxlength:25
            
    	},
    	lastname:
    	{
    		required: true,
            minlength:3,
            maxlength:25
    	},
    	phone:
    	{
    		required: true,
            minlength:5,
            maxlength:20,
            phoneUS:false,
            integer:true
    	},

    	address:
    	{
    		minlength:3,
            maxlength:55
    	},
    	city:
    	{
    		minlength:3,
            maxlength:30
    	},
    	
    	zipcode:
    	{
    		minlength:3,
            maxlength:15
    	},
    	
    	department:
    	{
    		minlength:3,
            maxlength:30
    	},
    	managersupervisor:
    	{
    		minlength:3,
            maxlength:30	
    	},
    	cell:
    	{
    		minlength:5,
            maxlength:20,
            integer:true
    	},
    	fax:
    	{
    		minlength:5,
            maxlength:20,
            integer:true
    	},
    	
    	password: 
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
        retypepassword: 
        { 
              required: true, equalTo: "#password"
        },
        captcha: 
        { 
              required: true, equalTo: "#captchavalue"
        },
        
      },
      messages: {

    	countryregion_0: "Select Country",
    	companyname: "Select Company",
    	 
      	firstname:
      	{
      		required: "Enter your first name",
      		 minlength:"First name should contain a minimum 3  characters",
             maxlength:"First name should contain a maximum 25  characters"

      	},
      	lastname:
      	{
      		required: "Enter your last name",
      		 minlength:"Last name should contain a minimum 3  characters",
             maxlength:"Last name should contain a maximum 25  characters"

      	},
      	phone:
      	{
      		required: "Enter company phone number",
      		minlength:"Phone number should contain a minimum 5  characters",
            maxlength:"Phone number should contain a maximum 20  characters",
            phoneUS:"Enter valid phone number",
            integer:"Phone number must be numeric"

      	},
      	
      	divisionname:
      	{
      		 minlength:"Division name should contain a minimum 3  characters",
             maxlength:"Division name should contain a maximum 50  characters"

      	},
      	unitname:
      	{
      		 minlength:"Unit name should contain a minimum 3  characters",
             maxlength:"Unit name should contain a maximum 50  characters"
      	},
      	address:
      	{
     		 minlength:"Address should contain a minimum 3  characters",
             maxlength:"Address should contain a maximum 55  characters"
      	},
      	
      	city:
      	{
      		 minlength:"City name should contain a minimum 3  characters",
             maxlength:"City name should contain a maximum 30  characters"
      	},
      	
      	zipcode:
      	{
      		 minlength:"Zipcode should contain a minimum 3  characters",
             maxlength:"Zipcode should contain a maximum 15  characters"
      	},
      	department:
      	{
      		 minlength:"Department should contain a minimum 3  characters",
             maxlength:"Department should contain a maximum 30  characters"
      	},
      	managersupervisor:
      	{
      		 minlength:"Manager/Supervisor should contain a minimum 3  characters",
             maxlength:"Manager/Supervisor should contain a maximum 30  characters"

      	},
      	cell:
      	{
      		 minlength:"Cell number should contain a minimum 5  characters",
             maxlength:"Cell number should contain a maximum 20  characters",
             integer:"Cell number must be numeric"
	
      	},
      	fax:
      	{
      		 minlength:"Fax should contain a minimum 5  characters",
             maxlength:"Fax should contain a maximum 20  characters",
             integer:"Fax must be numeric"
		
      	},
    	  
    	email:   
    	 {
              required        :   "Email cant be empty.",
              email           :   "Please enter a valid email",
          },
    	  
          retypepassword:   
          {
                required        :   "Retype password",
                equalTo         :   "Password mismatch",
           },
          password:   
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
    	  
    	  termsconditions:"Accept terms and conditions",
    	  policies:"privacy and security policies", 
    	  usertype:"Select user type",
    	  
    	  captcha: 
    	  {
    		  required        :   "Enter captcha value",
              equalTo         :   "Enter correct captha value",
          }
      }
    });
    
    setPasswordToolTip();
}

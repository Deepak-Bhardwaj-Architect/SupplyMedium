$(function() {
    
	getPasswordPolicy( ); loadValidator( ); 
	
  });

var upperLowerFlag  = true;
var numericFlag		= true;
var splCharFlag		= true;
var maxChar			= 15;
var minChar			= 8;

function changePassword()
{
	if( $('#changepassfrm').valid() == false )
		return;

	var userKey = $("#UserKey").val();
	var regnKey = $("#RegnKey").val();
		
	var oldPass = $('#OldPassword').val();
	var newPass = $('#NewPassword').val();
	
	$('#password').val(newPass);
	
	//showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserAcctMgmtServlet",
		   data:
		   { 
			    'RequestType':'ChangePassword',
		        'UserKey':userKey,
		        'RegnKey':regnKey,
		        'OldPassword':oldPass,
		        'NewPassword':newPass,
		   },
		   cache:false,
		   success: function( notifydata ) 
		   {
		//	   hideAjaxLoader();
			 
			  if(notifydata.result == "success")
			  {	
				   $("#changepass_loginsubmit_frm").submit();

				 /* var data = new FormData();
										
				  data.append('email', userKey);
					
				  data.append('password', newPass);
				  
				  $.ajax({
					   type: "POST",
					   url: form.attr('action'),
					   data:data,
					   contentType : false,
					   processData : false,
					   success: function( profileData ) 
					   {
						  // hideAjaxLoader();
						 
						  if(profileData.result == "success")
						  {
							 alert( "Login Success" );
						  }
						  
						  else
						  {
							 // alert("failed");
							 // $("#reseterror").text(profileData.message);
						  }
					   },
					    error : function(xhr, textStatus, errorThrown) 
					    {
					    	 //$("#reseterror").text("Unexcepted error occur. Please try again.");
					    	alert("exception");
					    	 
//					    	 /hideAjaxLoader();
						}
					 });*/
			  }
			  
			  else
			 {
				  //alert("failed");
				  $("#reseterror").text(notifydata.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	 $("#reseterror").text("Unexcepted error occur. Please try again.");
		    	 
		    	 //hideAjaxLoader();
			}
		 });
}


function getPasswordPolicy( )
{
	var companykey = $('#RegnKey').val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "PasswordPolicyServlet",
		data : {
			'RequestType':'UserSignup',
			'CompanyKey': companykey,
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
			
			$("#NewPassword").rules("remove", "required minlength maxlength containuppercase " +
					"containlowercase startwithalphabet containspecialchar containnumeric");
			
			$("#NewPassword").rules("add", {
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
	
    $("#changepassfrm").validate({
    	//ignore: "",
    	rules: {
    	
    	
    	OldPassword:
    	{
    		required:true
    	},
    	
    	NewPassword: 
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
              required: true, equalTo: "#NewPassword"
        },
      },
      messages: {

    	
    	  OldPassword:   
    	 {
              required        :   "Enter the current password"
          },
    	  
          retypepassword:   
          {
                required        :   "Retype password",
                equalTo         :   "Password mismatch",
           },
           NewPassword:   
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

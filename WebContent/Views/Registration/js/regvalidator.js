function validatePhoneNo( phoneNo )
{
	var isvalid=$("#phone").valid();
	
	if(!isvalid)
	{
		return;
	}
	
	$('#phonenocheck').addClass('loader');
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/RegnValidationServlet",
		   data:{ 
		        'phoneno': phoneNo, 
		        'fieldname':'phoneno',
		    } ,
		   cache:false,
		   success: function(result)
		   {
			   if(result == 0)
				{
				   $('#phonenocheck').removeClass('loader');
				   $('#phonenocheck').removeClass('validtick');
				   $('#phonenocheck').addClass('invalidsymbol');
				   $('#phoneerr').text('Phone number already exist.');
				   $('#phoneerr').show();
				   $('#phoneexist').val("1");
				}
			   else
				{
				   $('#phonenocheck').removeClass('loader');
				   $('#phonenocheck').removeClass('invalidsymbol');
				   $('#phonenocheck').addClass('validtick');
				   $('#phoneerr').text('');
				   $('#phoneerr').hide();
				   $('#phoneexist').val("0");
				}
		  
		   },
		    error: function(xhr, textStatus, errorThrown){
		    	$('#phonenocheck').removeClass('loader');
				   $('#phonenocheck').removeClass('validtick');
				   $('#phonenocheck').addClass('invalidsymbol');
				   $('#phoneerr').text('Request failed. Try again.');
				   $('#phoneerr').show();
				   $('#phoneexist').val("1");
		     }
		 });
}


function validateEmail( email )
{
	var isvalid=$("#email").valid();
	
	if(!isvalid)
	{
		return;
	}
	
	$('#emailcheck').addClass('loader');
	
	var companyName ="";
	
	if($("#RequestType").val() == 'NewRegistration')  // For company Registration 
	{
		companyName = $("#companyname").val();
	}
	else if($("#RequestType").val() == 'NewSignup')  // Admin add new user
	{
		companyName = $("#companyname").val();
	}
	else  // For user sign up
	{
		companyName = $('#companyname option:selected').text();
	}
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/RegnValidationServlet",
		   data:{ 
		        'email': email, 
		        'companyname':companyName,
		        'fieldname':'email',
		    } ,
		   cache:false,
		   success: function(result)
		   {
			   $('#emailcheck').removeClass('loader');
			   
			   if(result == 0)  // valid email
				{
				   $('#emailcheck').removeClass('invalidsymbol');
				   $('#emailcheck').addClass('validtick');
				   $('#emailerr').text('');
				   $('#emailerr').hide();
				   $('#emailexist').val("0");
				}
		  
			   else
				{
				   var errorMsg ="";
				   
				   if( result == 1 )
					 errorMsg = "Email address already exist";
				   else if( result == 2 )
					  errorMsg = "Not valid "+companyName+" domain";
				   else if( result == -1 )
					  errorMsg = "Unexpected error. Try Again.";
				   else if( result == -2 )
					   errorMsg = "Unexpected error. Try Again.";
						   
				   $('#emailcheck').removeClass('loader');
				   $('#emailcheck').removeClass('validtick');
				   $('#emailcheck').addClass('invalidsymbol');
				   $('#emailerr').text(errorMsg);
				   $('#emailerr').show();
				   $('#emailexist').val("1");
				}
			   
		   },
		    error: function(xhr, textStatus, errorThrown){
		    	 $('#emailcheck').removeClass('loader');
				   $('#emailcheck').removeClass('validtick');
				   $('#emailcheck').addClass('invalidsymbol');
				   $('#emailerr').text('Request failed. Try again.');
				   $('#emailerr').show();
				   $('#emailexist').val("1");
		     }
		 });
}

function getCountryCode ( countryName )
{
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/RegnValidationServlet",
		   data:{ 
		        'countryname': countryName, 
		        'fieldname':'countrycode',
		    } ,
		   cache:false,
		   success: function(result)
		   {
			   if (result != -1) {
				$('#countrycode').val(result);

				if (result == 1) {
					$("#signupform").validate();
					$("#phone").rules("remove");

					$("#phone")
					  .rules(
					  "add",
					  {
					    required:true,
					    minlength:5,
					    maxlength:20,
					    phoneUS:true,
					    messages:{
					    required: "Enter company phone number",
					    minlength:"Phone number should contain a minimum of 5  characters",
					    maxlength:"Phone number should contain a maximum of 20  characters",
					    phoneUS:"Enter valid phone number"
					    }
					  });
				} else {
					$("#signupform").validate();
					$("#phone").rules("remove", "phoneUS");
					$("#phone").rules("add", "integer");

				}

			}
		  
		   },
		    error: function(xhr, textStatus, errorThrown){
		    	
		     }
		 });
}




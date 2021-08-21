var upperLowerFlag  = true;
var numericFlag		= true;
var splCharFlag		= true;
var maxChar			= 15;
var minChar			= 8;

$('.tooltip').tooltipster(
{
		interactive: false
});

$(document).ready(function() {

	//Click the termsconditions link to open the terms and conditon popup
	$('#termsconditionslbl').click(function() 
	{
		
		 $('#terms_lastHiddenElement').focus(function(){
		        $('#termsacceptbtn').focus();
		    });
			 
		 $('#policy_lastHiddenElement').focus(function(){
			   	$('#policiesacceptbtn').focus();
			 });
		
		//if ($('#termsconditions').is(':checked'))
		//{
			$("#terms_popup").show();
			$('#termsacceptbtn').focus();
		//}
		//else
		//{
			//$('#termsconditions').prop('checked', false);
		//}
		
	});

	// Click the policies link to open the privacy and policy popup
	$('#policieslbl').click(function() 
	{
		//if ($('#policies').is(':checked'))
		//{
			$("#policies_popup").show();
			$('#policiesacceptbtn').focus();
		//}
		//else
		//{
			//$('#policies').prop('checked', false);
		//}
		
	});

// Click accept button in terms and condition popup
$('#termsacceptbtn').click(function()
{
	$('#termsconditions').prop('checked', true);
	$("#terms_popup").hide();
	$('#termsconditions').focus();
});

// Click decline button in terms and condition popup
$('#termsdeclinebtn').click(function() 
{
	$('#termsconditions').prop('checked', false);

	$("#terms_popup").hide();
	$('#termsconditions').focus();
});

// Click accept button in privacy and policies popup
$('#policiesacceptbtn').click(function()
{
	$('#policies').prop('checked', true);
	$("#policies_popup").hide();
	$('#policies').focus();
});

// Click decline button in privacy and policies popup
$('#policiesdeclinebtn').click(function() 
{
	$('#policies').prop('checked', false);

	$("#policies_popup").hide();
	$('#policies').focus();
});
});

function resetUserSignupFrm( formId )
{
	// reset the form fields
	resetForm(formId);
	
	// Reload the captcha
	generate_captcha('lcaptcha');
	
	// hide company type
	 $('#comtype').hide();
	 
	 // Reset the select box
	 $( "select" ).trigger('update');
	 
	 // Reset the phone number check result image
	 $('#phonenocheck').removeClass('validtick');
	 $('#phonenocheck').removeClass('invalidsymbol');
	  
	 // Reset the email check result image
	  $('#emailcheck').removeClass('validtick');
	  $('#emailcheck').removeClass('invalidsymbol');
	
	
}

function submitSignupForm()
{
	var isvalid=$("#signupform").valid();
	
	if(!isvalid)
		return false;
	
	if( $('#emailexist').val() == "1" || $('#companyexist').val() == "1" || 
			$('#canaddmember').val() == "0" || $('#phoneexist').val()== "1")
	{
		return false;
	}
}

function canAddMember( companykey )
{
	var isvalid=$("#companyname").valid();
	
	if(!isvalid)
	{
		return;
	}
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/RegnValidationServlet",
		   data:{ 
		        'compkey': companykey, 
		        'fieldname':'member',
		    } ,
		   cache:false,
		   success: function(result)
		   {
			   if(result == 0)
				{
				   $('#canaddmember').val('0');
				   $('#companyerr').show();
				   $('#companyerr').text('No more user allowed for this company');
				}
			   else
				{
				   $('#canaddmember').val('1');
				   $('#companyerr').hide();
				   $('#companyerr').text('');
				}
		  
		   },
		    error: function(xhr, textStatus, errorThrown){
				   $('#canaddmember').val('0');
				   $('#companyerr').show();
				   $('#companyerr').text('Request failed. Try again.');
		     }
		 });
}


function getCompanyType ( companykey )
{
	var isvalid=$("#companyname").valid();
	
	if(!isvalid)
	{
		return;
	}
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/RegnValidationServlet",
		   data:{ 
		        'compkey': companykey, 
		        'fieldname':'companytype',
		    } ,
		   cache:false,
		   success: function(result)
		   {
			   
			   if( result == 1 )
				{
				   $('#comtype').show();
				   $('#comtypeimg').css("background-image","url(../images/buyer.png)");
				   $('#comptypename').text( 'Buyer' );
				   
				}
			   else if( result== 2)
				{
				  
				   $('#comtype').show();
				   $('#comtypeimg').css("background-image","url(../images/seller.png)");
				   $('#comptypename').text( 'Supplier' );
				  
				}
			   else if( result == 3 )
			   {
				   $('#comtype').show();
				   $('#comtypeimg').css("background-image","url(../images/both.png)");
				   $('#comptypename').text( 'Buyer & Supplier' );
			   }
			   else
			   {
				   $('#comtype').hide();
			}
		  
		   },
		    error: function(xhr, textStatus, errorThrown){
		    	
		     }
		 });
}


function changeStateDropDown(countryName)
{
	
	if(countryName=="United States")
	{
		$("#select_0_container").show();
		$("#state_textbox").hide();
	}
	else
	{
		$("#state_textbox").show();
		$("#select_0_container").hide();
	}

}




$(document).ready(function() {
  // Handler for .ready() called.
	getAccountPolicies();
	
	$("#content_loader").hide();
	
	$("#accpolicies_content").show();
	
});


function enablePassComp()
{
	 $("#upperlower").removeAttr("disabled");
	 $("#numeric").removeAttr("disabled");
	 $("#non_alpha").removeAttr("disabled");
}


function disablePassComp()
{
	 $("#upperlower").attr("disabled", true);
	 $("#numeric").attr("disabled", true);
	 $("#non_alpha").attr("disabled", true);
	 $("#pass_comp_error").text("");
	 
}


function getAccountPolicies()
{
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/AccountPolicyServlet",
		   data:{ 
			    'RequestType':'GetPolicies',
		        'CompanyRegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function( policydata ) 
		   {
			   hideAjaxLoader();
			 
			  if(policydata.result == "success")
			  {
				  //password policies
				  
					$('#pass_his').val(policydata.pass_his);
					 $('#max_pass').val(policydata.max_pass);
					 $('#min_pass').val(policydata.min_pass);
					 $('#pass_length').val(policydata.pass_length);
					 
					 
					 var maxAge = policydata.max_pass;
					 var minAge = policydata.min_pass;
					 
						$("#email_noti").spinner('option', 'min', 1);
						$("#email_noti").spinner('option', 'max', (maxAge-minAge));
					//	email_noti_spinner.val("1");
					 
					 $('#email_noti').val(policydata.email_noti);
					 
					 if( policydata.daily_rem ==1 )
					 {
						 $('#daily_rem').attr("checked",true);
					 }
					 else
					 {
						 $('#daily_rem').attr("checked",false);
					 }
					 
					 //password complxity
					 if( policydata.pass_comp == 0)
					 {
						 $('#upperlower').attr("checked",false);
						 $('#numeric').attr("checked",false);
						 $('#non_alpha').attr("checked",false);
						 
						 $('#pass_comp_ena').attr("checked",false);
						 $('#pass_comp_dis').attr("checked",true);
						 
						 disablePassComp();
						
					 }
					 else
					 {
						
						 if( policydata.upperlower ==1 )
							 $('#upperlower').attr("checked",true);
						  else
							  $('#upperlower').attr("checked",false);
						 
						 if( policydata.numeric ==1 )
							 $('#numeric').attr("checked",true);
						  else
							  $('#numeric').attr("checked",false);
						 
						 if( policydata.non_alpha ==1 )
							 $('#non_alpha').attr("checked",true);
						  else
							  $('#non_alpha').attr("checked",false);
						 
						 $('#pass_comp_ena').attr("checked",true);
						 $('#pass_comp_dis').attr("checked",false);
						 $('#pass_comp_ena').attr("checked",true);
						 $('#pass_comp_dis').attr("checked",false);
						 
						 enablePassComp();
					 }
					 
					 
					 //Account lockout policies
					 
					 $('#lockout').val(policydata.lockout);
					 $('#lockout_dur').val(policydata.lockout_dur);
					 $('#reset_counter').val(policydata.reset_counter);
						
					 if( policydata.unlock_by_admin ==1 )
					 {
						 $('#unlock_by_admin').attr("checked",true);
					 }
					 else
					 {
						 $('#unlock_by_admin').attr("checked",false);
					 }
					 
					 
					 // session
					 
					 $('#session').val(policydata.session);
					 
					 $("#acc_policy_err").text("");
			  }
			  
			  else
			 {
				  $("#acc_policy_err").text(policydata.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	  $("#acc_policy_err").text("Unexcepted error occur. Please try again.");
		    	  
		    	  hideAjaxLoader();
			}
		 });
}

function setAccountPolicies()
{
	var pass_his = $('#pass_his').val();
	var max_pass = $('#max_pass').val();
	var min_pass = $('#min_pass').val();
	var pass_length= $('#pass_length').val();
	var email_noti = $('#email_noti').val();
	
	var daily_rem = 0;
	
	if ($('#daily_rem').is(':checked'))
	{
		 daily_rem = 1;
	}
	
	
	var uppperlower = 0;
	var numeric = 0;
	var non_alpha = 0;
	var pass_comp = 0;
	
	
	if($("#pass_comp_ena").prop("checked"))
	{
		pass_comp = 1;
		
		if ($('#upperlower').is(':checked'))
			uppperlower=1;
		if ($('#numeric').is(':checked'))
			numeric=1;
		if ($('#non_alpha').is(':checked'))
			non_alpha=1;
		
		// password complexity validation
		if( uppperlower ==0 && numeric == 0 && non_alpha==0 )
		{
			$("#pass_comp_error").text("Check atleast one password complexity");
			
			return;
		}
		else
		{
			$("#pass_comp_error").text("");
		}
	
	}
	else
	{
		$("#pass_comp_error").text("");
	}
	
	
	var lockout = $('#lockout').val();
	var lockout_dur = $('#lockout_dur').val();
	var reset_counter = $('#reset_counter').val();
	
	var unlock_by_admin =0;
	
	if ($('#unlock_by_admin').is(':checked'))
		unlock_by_admin=1;
	
	var session = $('#session').val();
	
	var regnKey = $('#regnkey').val();
	
	 showAjaxLoader();
	
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/AccountPolicyServlet",
		   data:{ 
			    'RequestType':'RevisePolicies',
		        'CompanyRegnKey':regnKey,
		       
		        'pass_his':pass_his,
		        'max_pass':max_pass,
		        'min_pass':min_pass,
		        'pass_length':pass_length,
		        'email_noti':email_noti,
		        'daily_rem':daily_rem,
		        
		        'pass_comp':pass_comp,
		        'numeric':numeric,
		        'non_alpha':non_alpha,
		        'upper&lower':uppperlower,
		        
		        'lockout':lockout,
		        'lockout_dur':lockout_dur,
		        'reset_counter':reset_counter,
		        'unlock_by_admin':unlock_by_admin,
		        
		        'session':session,
		    } ,
		   cache:false,
		   success: function( policydata ) 
		   {
			   hideAjaxLoader();
			 
			  if(policydata.result == "success")
			  {
				  ShowToast( policydata.message,2000 );
				  
				  $("#acc_policy_err").text("");
			  }
			  
			  else
			 {
				  alert("failed");
				  $("#acc_policy_err").text(policydata.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	 $("#acc_policy_err").text("Unexcepted error occur. Please try again.");
		    	 
		    	 hideAjaxLoader();
			}
		 });
}
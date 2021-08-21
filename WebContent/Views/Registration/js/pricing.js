$(document).ready(function() {

	 
	 $('#terms_lastHiddenElement').focus(function(){
        $('#termsacceptbtn').focus();
    });
	 
	 $('#policy_lastHiddenElement').focus(function(){
	        $('#policiesacceptbtn').focus();
	    });
	
	// clicking the basic pricing tag
	$("#basic").click(function() {
		$('#basic').removeClass('bluetag');
		$('#basic').addClass('bluetag_mo');

		$('#plus').addClass('greentag');
		$('#plus').removeClass('greentag_mo');

		$('#premium').addClass('orangetag');
		$('#premium').removeClass('orangetag_mo');

		var $radios = $('input:radio[name=pricingoption]');

		$radios.filter('[value=Free]').attr('checked', true);

	});

	// clicking the plus pricing tag
	$("#plus").click(function() {
		$('#basic').addClass('bluetag');
		$('#basic').removeClass('bluetag_mo');

		$('#plus').removeClass('greentag');
		$('#plus').addClass('greentag_mo');

		$('#premium').addClass('orangetag');
		$('#premium').removeClass('orangetag_mo');

		var $radios = $('input:radio[name=pricingoption]');

		$radios.filter('[value=Plus]').attr('checked', true);

	});
	
	// clicking the premium pricing tag
	$("#premium").click(function() {
		$('#basic').addClass('bluetag');
		$('#basic').removeClass('bluetag_mo');

		$('#plus').addClass('greentag');
		$('#plus').removeClass('greentag_mo');

		$('#premium').removeClass('orangetag');
		$('#premium').addClass('orangetag_mo');

		var $radios = $('input:radio[name=pricingoption]');

		$radios.filter('[value=Premium]').attr('checked', true);

	});

	//Click the termsconditions link to open the terms and conditon popup
	$('#termsconditionslbl').click(function() 
	{
		
		//if ($('#termsconditions').is(':checked'))
		//{
			$("#terms_popup").show();
		//	$("#termspopupid").focus();
			$('#termsacceptbtn').focus();
		//}
		//else
		//{
			//$('#termsconditions').prop('checked', false);
		//}
		
	});

	// Click the policies link to open the privacy and policy popup
	$('#policieslbl').click(function()
	//$('#policydivid').click(function()
	{
		//if ($('#policies').is(':checked'))
		//{
			$("#policies_popup").show();
			//$("#policies_popup").focus();
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

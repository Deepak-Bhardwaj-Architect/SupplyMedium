<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/UserMgmt/css/account_policies.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />



 


<title></title>
</head>
<body onload="">

<script type="text/JavaScript">
$("#content_loader").hide();
</script>

	 <%@include file="../../../session_check.jsp"%>
	<div class="pagetitlecontainer">
		<div class="pagetitle">Account Policies</div>
	</div>
	<div class="page">
		<div class="contentcontainer">
			<div class="content spinner" style="padding: 0px; width: 1002px;height:720px;display:none;" id="accpolicies_content">
				<div class="policiesleft">
				<div class="pass_policies">
					<div class="policyhead" style="margin:0px;">
						Password Policies
						</div>

						<div class="policyrow" >
							<label class="label" style="font-size: 12px;"> Enforce
								password history</label> <input id="pass_his"
								style="width: 34px; height: 20px; float: left;" name="value">
							<label class="label" style="font-size: 12px; float: right;">
								days</label>
						</div>

						<div class="policyrow">
							<label class="label" style="font-size: 12px;"> Maximum
								password age</label> <input id="max_pass"
								style="width: 34px; height: 20px; float: left;" name="value">
							<label class="label" style="font-size: 12px; float: right;">
								days</label>
						</div>

						<div class="policyrow">
							<label class="label" style="font-size: 12px;"> Minimum
								password age</label> <input id="min_pass"
								style="width:34px; height: 20px; float: left;" name="value">
							<label class="label" style="font-size: 12px; float: right;">
								days</label>
						</div>

						<div class="policyrow">
							<label class="label" style="font-size: 12px;"> Minimum
								password Length</label> <input id="pass_length"
								style="width: 34px; height: 20px; float: left;" name="value">
							<label class="label" style="font-size: 12px; float: right;">
								characters</label>
						</div>

						<div class="policyrow">
							<label class="label" style="font-size: 12px;"> Send
								E-mail notifications </label> <input id="email_noti"
								style="width: 34px; height: 20px; float: left;" name="value">
							<label class="label" style="font-size: 12px; float: right;">
								days before password expiry</label>
						</div>
						
						<div class="policyrow" >

							<div class="checkContainer"><input type="checkbox" id="daily_rem"
								class="ap_check_lbl" ><div></div></div>
							<label for="daily_rem" class="ap_check_lbl" style="width:450px;">
								Send daily remainder after this date</label>
						</div>

					
					</div>
					<div class="pass_compl">
					
					<fieldset class="policy_fieldset">
					<legend class="ap_legend" >Password Complexity
					</legend>
					<div class="policyrow" style="height:30px;width:350px">
						<input name="pass_comp" id="pass_comp_ena" value="Enabled" type="radio" onclick='enablePassComp();' checked='checked' class="ap_check_lbl">
						<label for="pass_comp_enb" class="ap_check_lbl">Enabled</label>
						<input name="pass_comp"  id="pass_comp_dis" value="Disabled" type="radio" onclick='disablePassComp();' class="ap_check_lbl">
						<label for="pass_comp_dis" class="ap_check_lbl">Disabled</label>
						</div>
						<div class="policyrow" style="height:30px;width:350px">
						<div class="checkContainer"><input type="checkbox" id="upperlower"
								class="ap_check_lbl" name="value"><div></div></div>
							<label  for="upperlower" style="width:350px;" class="ap_check_lbl">
								Upper and lower case letters</label>
								</div>
								
								<div class="policyrow" style="height:30px;width:300px">
								<div class="checkContainer"><input type="checkbox" id="numeric"
								 class="ap_check_lbl"><div></div></div>
							<label  for="numeric" class="ap_check_lbl" style="width:300px;">
								Numeric characters</label>
								</div>
								
								<div class="policyrow" style="height:30px;width:350px">
								<div class="checkContainer"><input type="checkbox" id="non_alpha"
								 class="ap_check_lbl"><div></div></div>
							<label  for="non_alpha" class="ap_check_lbl"style="width:300px;">
								Non-Alphanumeric charcters</label>
								</div>
					</fieldset>
					<div id="pass_comp_error" class=""></div>
					</div>
				</div>
				
				<div class="policiesright">
				<div class="lockout_policies">
					<div class="policyhead">
						Account Lockout Policies
							</div>
						<div class="policyrow" >
							<label class="label" style="font-size: 12px;"> Account
								will lockout after</label> <input id="lockout"
								style="width: 34px; height: 20px; float: left;" name="value">
							<label class="label" style="font-size: 12px; float: right;">
								invalid login attempts</label>
						</div>

						<div class="policyrow" >
							<label class="label" style="font-size: 12px;"> Lockout
								duration</label> <input id="lockout_dur"
								style="width: 34px; height: 20px; float: left;" name="value">
							<label class="label" style="font-size: 12px; float: right;">
								minutes</label>
						</div>
						
						<div class="policyrow" >
							<label  class="label" style="font-size: 12px;"> Reset lockout counter after</label> <input id="reset_counter"
								style="width: 34px; height: 20px; float: left;" name="value">
							<label class="label" style="font-size: 12px; float: right;">
								minutes</label>
						</div>

						<div class="policyrow" style="margin-bottom:20px;">

							<div class="checkContainer"><input type="checkbox" id="unlock_by_admin"
								 class="ap_check_lbl"><div></div></div>
							<label for="unlock_by_admin" class="ap_check_lbl"  style="width:450px;">
								Account must be unlocked by an administrator</label>
						</div>
				

				</div>
				<div class="session_mgmt">
				<div class="policyhead">
						Session Management
				</div>
				<div class="policyrow">
							<label class="label" style="font-size: 12px;"> End
								session after</label> <input id="session"
								style="width: 34px; height: 20px; float: left;" name="value">
							<label class="label" style="font-size: 12px; float: right;">
								minutes of inactivity</label>
				</div>
				</div>
				</div>
				<input type="button" value="Save" class="gen-btn-Orange ap_upt_btn" onclick="setAccountPolicies();" />
				<div id="acc_policy_err" ></div>
			</div>
		</div>
	</div>
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	 <%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	 
	 <%@include file="../../Utils/jsp/footer.jsp"%>

<script type="text/javascript">
	$(function() {

		var pass_his_spinner = $("#pass_his").spinner();
		pass_his_spinner.spinner('option', 'min', 0);
		pass_his_spinner.spinner('option', 'max', 5);
		pass_his_spinner.val("0");

		var min_pass_spinner = $("#min_pass").spinner();
		min_pass_spinner.spinner('option', 'min', 0);
		min_pass_spinner.spinner('option', 'max', 998);
		min_pass_spinner.val("0");

		var max_pass_spinner = $("#max_pass").spinner();
		max_pass_spinner.spinner('option', 'min', 1);
		max_pass_spinner.spinner('option', 'max', 999);
		max_pass_spinner.val("1");

		var pass_length_spinner = $("#pass_length").spinner();
		pass_length_spinner.spinner('option', 'min', 5);
		pass_length_spinner.spinner('option', 'max', 10);
		pass_length_spinner.val("5");

		var email_noti_spinner = $("#email_noti").spinner();
		email_noti_spinner.spinner('option', 'min', 1);
		email_noti_spinner.spinner('option', 'max', 10);
		email_noti_spinner.val("1");

		var lockout_spinner = $("#lockout").spinner();
		lockout_spinner.spinner('option', 'min', 3);
		lockout_spinner.spinner('option', 'max', 10);
		lockout_spinner.val("3");

		var lockout_dur = $("#lockout_dur").spinner();
		lockout_dur.spinner('option', 'min', 0);
		lockout_dur.spinner('option', 'max', 1440);
		lockout_dur.val("0");
		
		var reset_counter = $("#reset_counter").spinner();
		reset_counter.spinner('option', 'min', 0);
		reset_counter.spinner('option', 'max', 10);
		reset_counter.val("0");

		var session = $("#session").spinner();
		session.spinner('option', 'min', 5);
		session.spinner('option', 'max', 60);
		session.val("5");

	}

	);
	
//$.getScript( "${pageContext.request.contextPath}/Views/UserMgmt/js/account_policies.js", function( data, textStatus, jqxhr ) {});
</script>

<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/UserMgmt/js/account_policies.js"></script>

</body>


	
</html>
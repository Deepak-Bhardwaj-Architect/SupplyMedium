<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Registration/css/companyregistration.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Registration/css/usersignup.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/layout.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/tooltipster.css" />
	
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />


<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery-1.9.0.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery.validate.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery.customSelect.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery-ui-1.10.0.custom.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/additional-methods.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/dropdownfiller.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/common.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery.tooltipster.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Registration/js/usersignup.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Registration/js/signupfieldsvalidator.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Registration/js/regvalidator.js"></script>

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Registration/js/captcha.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Registration/js/regvalidator.js"></script>

<script>
	$(document).ready(function() {
		$('.tooltip').tooltipster({
			interactive : true,
			theme : '.my-custom-theme'
		});
		
		 $('select.selectbox').customSelect();
	});



</script>

</head>
<body onload="fetchCountry();generate_captcha('lcaptcha');loadValidator();">
	<%@include file="../../Utils/jsp/header.jsp"%>

	<div class="pagetitlecontainer">
		<div class="pagetitle">New User Sign Up</div>
	</div>

	<div class="contentcontainer">
		<div class="content">
			<form action="${pageContext.request.contextPath}/UserSignupServlet"
				id="signupform" method="post" onsubmit="return submitSignupForm();">

				<div class="leftcontent">
					<div class="row">
						<div class="label">
							Country<span class="mandatory">*</span>
						</div>
						<select id='countryregion_0'  name='countryregion'
							class="required selectbox"
							onchange="fetchState(this.id);fetchCompany(this.value);getCountryCode(this.value);changeStateDropDown(this.value)">
							<option value="">--Select Country--</option>
						</select> <label for="countryregion_0" generated="true" class="error"
							id="countryregion_0err"></label>
					</div>
					<div class="row">
						<div class="label">
							Select Company<span class="mandatory">*</span>
						</div>
						<div style="float:left;height:30px;width:220px;">
						<select id='companyname' name='companyname' class="selectbox required" style=""
							onchange="canAddMember(this.value);getCompanyType(this.value);getPasswordPolicy(this.value);">
							<option value="">--Select--</option>
						</select>
						<div class="infopopup tooltip"
							title="Company not registered?<br/><a href='${pageContext.request.contextPath}/Views/Registration/jsp/companyregistration.jsp' style='color:blue;'>Click here</a> to register">
						</div>
						</div>

						<label for="companyname" generated="true" class="error"
							id="companyerr"></label> <input type="hidden" name="companyexist"
							id="companyexist" /> <input type="hidden" name="canaddmember"
							id="canaddmember" />

					</div>

					<div class="row" id="comtype" style="display: none">
						<div class="label">Company Registered As:</div>
						<div class="label" style="width: auto" id="comptypename"></div>
						<div class="comtypeimg" id="comtypeimg"></div>
					</div>

					<div class="row">
						<div class="label">Title</div>
						<select name="title" class="selectbox">
							<option value="MR">MR</option>
							<option value="MRS">MRS</option>
                                                        <option value="MS">MS</option>
							<option value="M/S">M/S</option>
						</select>
					</div>

					<div class="row">
						<div class="label">
							First Name<span class="mandatory">*</span>
						</div>
						<input type="text" name="firstname" id="firstname"
							class="required textbox" />
					</div>
					<div class="row">
						<div class="label">
							Last Name<span class="mandatory">*</span>
						</div>
						<input type="text" name="lastname" id="lastname"
							class="required textbox" />
					</div>

					<div class="row">
						<div class="label">Address</div>
						<input type="text" name="address" class="textbox" />
					</div>
					<div class="row">
						<div class="label">City</div>
						<input type="text" name="city" class="textbox" />
					</div>
					<div class="row">
						<div class="label">State/Province</div>
						<div id="select_0_container"><select id='state_0' name='state' class="selectbox">
							<option>--Select State--</option>
						</select> 
						<label for="state_0" generated="true" class="error"
							id="state_0err"></label></div>
							
							<input   style="display:none;margin-left:200px;margin-top:-27px;"   type="text" name="state_text" class="required textbox" id="state_textbox">
					</div>
					<div class="row">
						<div class="label">Zip Code/Postal Code</div>
						<input type="text" name="zipcode" class="textbox" />
					</div>
					<fieldset class="fieldset" style="height:80px;">
						<legend>Are you human?</legend>
						<div class="row" style="padding-top:10px;width:350px;">
							<div >
								<label class="captchalabel captchabg" id="lcaptcha" for="captcha"
									></label><input
									type="captcha" name="captcha" id="captcha"
									class="required capthatextbox"  />
									<input type="button" class="captchareload" onclick="generate_captcha('lcaptcha');"><br /> <input
									type="hidden" id="captchavalue" name="captchavalue" />
									<label for="captcha" generated="true" class="error" style="margin-left:100px;"></label>
							</div>
						</div>
					</fieldset>

				</div>
				<div class="formseparator" style="margin-top: 20px; height: 480px;"></div>
				<div class="rightcontent">
					<!--<div class="row" style="height: 100px;">-->
						<!--<div class="label">
							User Type<span class="mandatory">*</span>
						</div>
						<fieldset class="fieldset" style="width: 160px;">
							<legend> User Type </legend>-->
                                                        <input type="hidden" value="IntranetUser" name="usertype" id="internetuser" />
<!--                                                        <input type="radio" style="vertical-align: middle; font-size: 12px;margin:0px;" disabled name="usertype" id="internetuser"
								checked class="radiobtn required" value="IntranetUser"
								style="vertical-align:middle;font-size: 12px;" /> 
							<label for="internetuser"
								style="vertical-align: middle; font-size: 12px; color: #282828">
								Company User (Intranet) &nbsp;</label><BR /> -->
<!--							<input type="radio" id="transuser"
                                                               name="usertype"  readonly disabled class="radiobtn" style="vertical-align: middle; font-size: 12px;margin:0px;"
								value="TransactionUser"
								style="vertical-align:middle;font-size: 12px;" /> 
							<label for="transuser"
								style="vertical-align: middle; font-size: 12px; color: #282828">
								Regular UserIndividual User (Regular) &nbsp;</label> 
							<label for="usertype" checked generated="true"
								class="error" style="margin-left: 0px !important; width: 150px;"></label>-->

						<!--</fieldset>-->

					<!--</div>-->
					<div class="row">
						<div class="label">
							Email<span class="mandatory">*</span>
						</div>
						<input type="text" name="email" id="email"
							class="required textbox email" onblur="validateEmail(this.value)" />
						<span id="emailcheck"></span> <label for="email" generated="true"
							class="error" id="emailerr"></label> <input type="hidden"
							name="emailexist" id="emailexist" />
					</div>
					<div class="row">
						<div class="label">
							Password<span class="mandatory">*</span>
						</div>
						<input type="password" name="password" id="password"
							class="textbox required" />
					</div>
					<div class="row">
						<div class="label">
							Confirm Password<span class="mandatory">*</span>
						</div>
						<input type="password" name="retypepassword" id="retypepassword"
							class="textbox required" />
					</div>
					<div class="row">
						<div class="label">Department</div>
						<input type="text" name="department" class="textbox" />
					</div>
					<div class="row">
						<div class="label">Manager/Supervisor</div>
						<input type="text" name="managersupervisor" class="textbox" />
					</div>
					<div class="row">
						<div class="label">
							Phone<span class="mandatory">*</span>
						</div>
						<input type="text" id="countrycode" class="textbox" disabled style="text-align:center;float:left;width:25px;margin-right:5px;"/>
						<input type="text" name="phone" id="phone"
							class="required textbox" onblur="validatePhoneNo(this.value)" style="width:137px;"/>
							<div id="phonenocheck"></div>
							<label for="phone" generated="true" class="error" id="phoneerr"></label>
							<input type="hidden" name="phoneexist" id="phoneexist"  />
						
					</div>
					<div class="row">
						<div class="label">Cell</div>
						<input type="text" name="cell" class="textbox" />
					</div>
					<div class="row">
						<div class="label">Fax</div>
						<input type="text" name="fax" class="textbox" />
					</div>
					<fieldset class="fieldset" style="height:80px;">
						<legend>Conditions</legend>
						<div class="row" style="height: 35px; width: 320px; " id="condition1">
							<div class="checkContainer"><input type="checkbox" class="required"
								id="termsconditions" name="termsconditions" style="float: left;cursor:pointer;" /><div></div></div>
							<!-- <label class="label" for="termsconditions" id="termsconditionslbl"
								style="width: 210px; height: 20px; line-height: 16px;padding-left:5px;text-decoration:underline;cursor:pointer;">Accept
								terms &amp; condition</label> -->
							
							<div class="label" for="termsconditions" id="termsconditionslbl"
								style="width: 210px; height: 20px; line-height: 16px;padding-left:5px;text-decoration:underline;cursor:pointer;">Accept
								terms &amp; condition</div>	
							
							<label for="termsconditions" generated="true" class="error"
								style="margin-left: 0px; width: 250px;"></label>
						</div>

						<div class="row" style="height: 35px; width: 320px;cursor:pointer;" id="condition2">
							<div class="checkContainer"><input type="checkbox" name="policies" id="policies"
								class="required" style="float: left;cursor:pointer;" /><div></div></div>
							<!-- <label class="label" for="policies" id="policieslbl"
								style="width: 280px; height: 20px; line-height: 16px;padding-left:5px;text-decoration:underline;cursor:pointer;">Accept
								privacy &amp; security policies</label> -->
							
							<div class="label" for="policies" id="policieslbl"
								style="width: 280px; height: 20px; line-height: 16px;padding-left:5px;text-decoration:underline;cursor:pointer;">Accept
								privacy &amp; security policies</div>	
								
							<label for="policies" generated="true" class="error"
								style="margin-left: 0px; width: 250px;"></label>
						</div>

					</fieldset>
				</div>
				<div style="margin-left: 380px; margin-top: 45px; float: left;">

					 <input
						type="button"  value="Reset"
						onclick="resetUserSignupFrm('signupform');" class="gen-btn-Gray" />
						<input type="submit" style="margin-left: 60px;" value="Submit" class="gen-btn-Orange" /> <input
						type="hidden" name="RequestType" value="NewSignup" />
				</div>

			</form>

			<%
			    String result = (String)request.getAttribute( "result" );
			    if( result != null )
			    {
					out.println( result );
			    }
			%>

		</div>
		<%@include file="termspopup.jsp"%>
		<%@include file="policypopup.jsp"%>
	</div>

	<%@include file="../../Utils/jsp/regnfooter.jsp"%>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/UserMgmt/css/usermgmt.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/tablestyle.css" />
		
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/UserMgmt/css/usermgmt_popup.css" />


<title>Supply Medium</title>
</head>



<body>
	 
	 <script type="text/JavaScript">
$("#content_loader").hide();
</script>

	<div class="pagetitlecontainer">
	<div class="pagetitle">Users</div>
	</div>
	<div class="page">
		<div class="contentcontainer" style="min-height:0px;">
			<form name="usermgmtfrm" id="usermgmtfrm" style="min-height:600px;">
			<div id="usermgmt_content" style="display:block">
				<div class="tabbar">
				
				<div class="usermgmterr" id="usertblerr"></div>
				<div class="highlight" id="user_details" style="display:block;">User Details</div>

				<div class="normal" id="new_users" style="display:block;">New User</div>
				</div>

			
		
				<div class="userdetailscontent" id="userdetails_content" style="display:block;">

					<div class="tablecontent" id="table_content" style="position:relative;">
					<div class="DT_border" >
					</div>
						<table id="UserList" style="width: 997px;">
							<thead >
								<tr>
									<th class="rowBorder">Name</th>
									<th class="rowBorder">City</th>
									<th class="rowBorder">Email</th>
									<th class="rowBorder">Status</th>
									<th class="rowBorder">State</th>
									<th class="rowBorder"></th>
								</tr>
								</thead>
								
								<tbody>
								
								</tbody>
							
						</table>
						
					</div>
				</div>

				<div class="newusercontent" id="newuser_content" style="display:none;">
					<div class="contenthead" id="content_head">Add a New User to Signup</div>
						
					<div class="contentdetail" id="content_detail" style="padding-top:60px;">

						<div class="row" style="margin-left: 60px;">
							<div class="label" style="font-size:13px;">
								First Name<span class="mandatory">*</span>
							</div>
							<input type="text" id="firstname" name="firstname"
								class="textbox" />
						</div>
						<div class="row" style="margin-left: 60px;">
							<div class="label" style="font-size:13px;">
								Last Name<span class="mandatory">*</span>
							</div>
							<input type="text" id="lastname" name="lastname"
								class="textbox" />
						</div>
						<div class="row" style="margin-left: 60px;">
							<div class="label" style="font-size:13px;">
								E-mail ID<span class="mandatory">*</span>
							</div>
							<input type="text" name="email" id="email"
							class="required textbox email" onblur="validateEmail(this.value)" />
						<span id="emailcheck"></span> <label for="email" generated="true"
							class="error" id="emailerr"></label> <input type="hidden"
							name="emailexist" id="emailexist" />
						</div>
						<div class="row" style="margin-left: 60px;">
							<div class="label" style="font-size:13px;">
								Phone<span class="mandatory">*</span>
							</div>
							<input type="text" name="phone" id="phone"
							class="required textbox" onblur="validatePhoneNo(this.value)" />
							<div id="phonenocheck"></div>
							<label for="phone" generated="true" class="error" id="phoneerr"></label>
							<input type="hidden"
							name="phoneexist" id="phoneexist" />
						</div>
						<div class="row" style="margin-left: 60px;">
							<div class="label" style="font-size:13px;">
								Mobile
							</div>
							<input type="text" id="cell" name="cell"
								class="textbox" />
						</div>
						
						<div class="row" style="margin-left: 60px;">
							<div class="label" style="font-size:13px;">
								City
							</div>
							<input type="text" id="city" name="city"
								class="textbox" />
						</div>
						
						<div class="row" style="margin-left:60px;">
						<div class="label" style="font-size:13px;">
							Country<span class="mandatory">*</span>
						</div>
						<select id='countryregion_0'  name='countryregion'
							class="required selectbox"
							onchange="fetchState(this.id);changeStateDropDown(this.value)">
							<option value="">--Select Country--</option>
						</select> <label for="countryregion_0" generated="true" class="error"
							id="countryregion_0err"></label>
					</div>
					
					
					<div class="row" style="margin-left:60px;">
						<div class="label"style="font-size:13px;">State/Province</div>
						<div id="select_0_container"><select id='state_0' name='state' class="selectbox">
							<option>--Select State--</option>
						</select> 
						<label for="state_0" generated="true" class="error"
							id="state_0err"></label></div>
							
							<input   style="display:none;margin-left:200px;margin-top:-27px;"   type="text" name="state_text" class="textbox" id="state_text">
					</div>
						
						
						<div class="row" style="margin-left: 60px;">
							<div class="label" style="font-size:13px;">
								Zip Code/Postal Code
							</div>
							<input type="text" id="zipcode" name="zipcode"
								class="textbox" />
						</div>

						<div style="margin-left: 350px; margin-top: 14px; float: left;">

							 <input
								type="button" value="Reset"
								class="gen-btn-Gray" onclick="reset('usermgmtfrm');resetNewUserFrm();"/>
								<input type="button" value="Add User" style="margin-left: 30px;"  class="gen-btn-Orange" onclick="addUser();" /> <input
								type="hidden" id="RequestType" name="RequestType" value="NewSignup" />
								
						</div>
						
						<div id="newusererr" class="usermgmterr"></div>
					</div>
					
				</div>
				</div>
			</form>
		</div>
		</div>
		
	 <%@include file="../../Utils/jsp/Popup_Warning.jsp"%>
	 <%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	 <%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	  <%@include file="popup_policies.jsp"%>
	  
	  <%@include file="../../Utils/jsp/footer.jsp"%>
	  
<script>


$.getScript( "${pageContext.request.contextPath}/Views/UserMgmt/js/usermgmt_fieldvalidator.js");
$.getScript( "${pageContext.request.contextPath}/Views/Registration/js/regvalidator.js");
$.getScript( "${pageContext.request.contextPath}/Views/UserMgmt/js/usermgmt.js", function( data, textStatus, jqxhr ) {
	userOnload();
	 
	});

</script>

</body>



</html>
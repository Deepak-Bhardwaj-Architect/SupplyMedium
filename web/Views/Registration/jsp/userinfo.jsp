<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="leftcontent">

		<div class="row">
			<div class="label">First Name<span class="mandatory">*</span></div>
			<input type="text" name="firstname" id="firstname" class="required textbox" />
		</div>
		<div class="row">
			<div class="label">Last Name<span class="mandatory">*</span></div>
			<input type="text" name="lastname" id="lastname" class="required textbox" />
		</div>
		<div class="row">
			<div class="label">Title</div>
			<select name="title" class="selectbox" id="usertitle">
				<option value="MR">MR</option>
				<option value="MRS">MRS</option>
                                <option value="MS">MS</option>
                                <option value="M/S">M/S</option>
			</select>
		</div>
		<div class="row">
			<div class="label">Department</div>
			<input type="text" name="department" id="department" class="textbox" />
		</div>
		<div class="row">
			<div class="label">Manager/Supervisor</div>
			<input type="text" name="managersupervisor" id="managersupervisor" class="textbox" />
		</div>
	</div>
	<div id="userseparator" class="formseparator" style="height:290px;margin-bottom:147px;"></div>
	<div class="rightcontent">

		<div class="row">
			<div class="label">Primary Phone<span class="mandatory">*</span></div>
			<input type="text" id="countrycode" class="textbox" disabled style="float:left;width:25px;margin-right:5px;"/>
			<input type="text" name="phone" id="phone" onblur="validatePhoneNo(this.value);"
				class="required textbox" style="width:137px;"/> <div id="phonenocheck"></div>
				<label for="phone" generated="true" class="error" id="phoneerr"></label>
				<input type="hidden" name="phoneexist" id="phoneexist"  />
		</div>
		<div class="row">
			<div class="label">Cell</div>
			<input type="text" name="cell" id="cell" class="textbox" />
		</div>
		<div class="row">
			<div class="label">Fax</div>
			<input type="text" name="fax" id="fax" class="textbox" />
		</div>
		<div class="row">
			<div class="label">Email<span class="mandatory">*</span></div>
			<input type="text" name="email" id="email" class="email required textbox"
				onblur="validateEmail(this.value)" />
				<span id="emailcheck"></span>
				<label for="email" generated="true" class="error" id="emailerr"></label>
				<input type="hidden" name="emailexist" id="emailexist"  />
		</div>
		
		<div class="row">
			<div class="label">Password<span class="mandatory">*</span></div>
			<input type="password" name="password" id="password" class="required textbox" 
			title="Password should be of minimum 8 characters and maximum 15 characters. Password must contain at least one character,one number and one special character and starts with character (case sensitive)" />
		</div>
		
		<div class="row">
			<div class="label">Confirm Password<span class="mandatory">*</span></div>
			<input type="password" name="retype" id="retype" class="required textbox" />
		</div>
	</div>
</body>
</html>
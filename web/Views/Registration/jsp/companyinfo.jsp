<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/custom_file_upload.css" />

</head>
<body>
	<div class="leftcontent">
		<input type="hidden" name="count" id="rowcount" value="1" /> <input
			type="hidden" name="addresscount" id="addresscount" value="1">

		<div class="row" style="height: 30px;">
			<img
				src="${pageContext.request.contextPath}/Views/Registration/images/buyer.png"
				width="30px" height="30px" style="margin-left: 220px"> <img
				src="${pageContext.request.contextPath}/Views/Registration/images/seller.png"
				width="30px" height="30px" style="margin-left: 35px"> <img
				src="${pageContext.request.contextPath}/Views/Registration/images/both.png"
				width="30px" height="30px" style="margin-left: 20px">
		</div>

		<div class="row">
			<div class="label">
				Sign Up As<span class="mandatory">*</span>
			</div>
			<input type="radio" name="signupas" value="Buyer" id="buyerradio"
				class="required radiobtnlbl" style="margin:0px;"/>
				<label for="buyerradio" class='radiobtnlbl'>Buyer</label> &nbsp;
				<input type="radio" name="signupas" value="Supplier" id="Supplierradio" class="radiobtnlbl" style="margin:0px;" />
				<label for="Supplierradio" class='radiobtnlbl' >Seller</label>&nbsp; 
				<input type="radio" name="signupas" value="Buyers/Suppliers" id="Bothradio" class="radiobtnlbl" checked/>
				<label for="Bothradio" class='radiobtn radiobtnlbl'>Buyer/Supplier</label>&nbsp;
		</div>
		<div class="row" style="width:420px;">
			<div class="label">Logo</div>
			<!--  <input type="file" class="file"  style="font-size: 12px;"
				title="The logo dimensions should be at most 500x500 pixels in size should not exceed 1 MB.  Supported formats are jpg, png, bmp, gif and tiff."
				name="logo" id="logo"  /> -->
				
				
			<div class="logo_file_container">
				<input type="file" class="logofile"  style="font-size: 12px;" name="logo" id="logo"
				title="The logo dimensions should be at most 500x500 pixels in size should not exceed 1 MB.  Supported formats are jpg, png, bmp, gif and tiff."
				  /> 
			</div>
			<label id="logo_file_name" class="logfilename_lbl"></label>
			
				<label  class="fileupload_err"
					id="fileupload_err"></label>
				<input type="hidden" name="fileerror" id="fileerror">
				
				
		</div>
		
		<!--<div class="row">
			<div class="label">Logo</div>
			<div class="custom_file_upload">
				<input type="text" class="file textbox" style="font-size: 12px;" 
				title="The logo dimensions should be at most 500x500 pixels in size should not exceed 1 MB.  Supported formats are jpg, png, bmp, gif and tiff."
				name="file_info" />
				<div class="file_upload">
					<input type="file" id="logo" name="file_upload">
				</div>
				<label  class="fileupload_err" style="margin-left:70px;"
					id="fileupload_err"></label>
				<input type="hidden" name="fileerror" id="fileerror">
			</div>
		</div> -->
		
		<div class="row">
			<div class="label">
				Company Name<span class="mandatory">*</span>
			</div>
			<input type="text" id="companyname" name="companyname"
				class="required textbox" />
		</div>
		<div class="row">
			<div class="label">
				Company Id<span class="mandatory">*</span>
			</div>
			<input type="text" name="companyid" id="companyid"
				class="textbox required" onblur="validateCompanyId(this.value);" /> <div id="companyidcheck"></div>
				<label for="companyid" generated="true" class="error" id="companyiderr"></label>
				<input type="hidden" name="companyidexist" id="companyidexist"  />
		</div>
		<div class="row">
			<div class="label">
				Business Category<span class="mandatory">*</span>
			</div>
			
                    <select onchange="slctd_ctgry(this.value);" name="businesscategory" id="businesscategory"
				class="selectbox required"><option>--Select
					 Category--</option></select>                     
					<label for="businesscategory" generated="true"
				class="error" id="businesscategoryerr"></label>
		</div>
                <div class="row" style="display:none" id="ctgry_lstng">
                    <input type="hidden" name="businesscategory2" id="businesscategory2">
                    <div class="label">
				Selected Categories<span class="mandatory"></span>
			</div>
                    <ul class="textbox" id="slctd_ctgry_lst" style="list-style: none;height: auto;width: auto;"></ul>
                </div>    
		<div class="row">
			<div class="label">Segment/Division Name</div>
			<input type="text" name="divisionname" id="divisionname"
				class="textbox" />
		</div>                
		<div class="row">
			<div class="label">Business Unit Name</div>
			<input type="text" name="unitname" id="unitname" class="textbox" />
		</div>
		
	</div>
	<div id="compseparator" class="formseparator"
		style="height: 375px; margin-bottom: 100px;"></div>
	<div class="rightcontent">
		<div id="address0">
			<div class="row">
				<div class="label">Branch</div>
				<select id="branch_0" name="branch_0" class="selectbox">
					<option value="Corporate Office">Corporate Office</option>
					<option value="Site Office">Site Office</option>
					<option value="Corporate Office">Warehouse / Distribution
						Center</option>
					<option value="Store">Store</option>
				</select>
			</div>
			<div class="row">
				<div class="label">
					Country/Region<span class="mandatory">*</span>
				</div>
				<select id='countryregion_0' name='countryregion_0'
					class="selectbox required" onchange="fetchState(this.id);getCountryCode(this.value);changeStateDropDown(this.value)">
					<option value="">--Select Country--</option></select>
				<label for="countryregion_0" generated="true" class="error"
					id="countryregion_0err"></label>
			</div>

			<div class="row">
				<div class="label">Address</div>
				<input type='text' id='address_0' name='address_0'
					style="margin-bottom: 5px;" class="textbox" />
			</div>

			<div class="row">
				<div class="label">City</div>
				<input type='text' id='city_0' name='city_0'
					style="margin-bottom: 5px;" class="textbox" />
			</div>

			<div class="row">
				<div class="label">State/Province</div>
				
				
				<div id="select_0_container"><select id='state_0' name='state_0'
					style="margin-bottom: 5px;" class="selectbox">
					<option>--Select State--</option></select> <label 
					 class="error" id="state_0err"></label></div>
					 <input   style="display:none;margin-left:200px;margin-top:-33px;"   type="text" name="state_text_0" class="textbox" id="state_text_0">
			</div>
			<div class="row">
				<div class="label">Zip Code/Postal Code</div>
				<input type='text' id='zipcode_0' name='zipcode_0'
					style="margin-bottom: 5px;" class="textbox" />
			</div>
		</div>

		

		

		<div id="address1detail" class="addrdetail">
			<div class="addrleft" id="addrleft1">
				<div class="label" id="addaddress1">Add Address</div>
				<input type="button" name="addaddressbtn1" id="addaddressbtn1"
					class="addaddress" onclick="addAddress1();" />
			</div>
			
			<div class="addrleft" id="addrleft2" style="display: none;">
				<div class="label">Add Address</div>
				<input type="button" name="addaddressbtn2" id="addaddressbtn2"
					class="addaddress" onclick="addAddress2();" />
			</div>
			
			<div class="addrright" id="addrright1" style="display: none;">
				<fieldset class="regnaddrfieldset" style="height:auto;">
					<legend>Address1:</legend>
					<textarea id="addaddrlbl1" class="addrlbl" disabled></textarea>
				</fieldset>
				<input type="button" name="removeaddressbtn1" id="removeaddressbtn1"
					class="removeaddress" onclick="removeAddress1();" />
			</div>

		</div>

		<div id="address2detail" class="addrdetail" style="display: none;">
			
			<div class="addrright" id="addrright2" style="display: none;">
				<fieldset class="regnaddrfieldset" id="addaddrfield2"  style="height:auto;">
					<legend>Address2:</legend>
					<textarea id="addaddrlbl2" class="addrlbl" disabled></textarea>

				</fieldset>
				<input type="button" name="removeaddressbtn2" id="removeaddressbtn2"
					class="removeaddress" onclick="removeAddress2();" />
			</div>

		</div>
		
		

		<div id="address2detail"></div>
		
	</div>
	
</body>
</html>
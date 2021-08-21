<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
$(function(){
	
	$(".Gen_Cus_Popup_Close").click(function()	
	{
		$(".Custome_Popup_Window").hide();
	});
	$("#addr1_popup_cancel").click(function()
		{
			$(".Custome_Popup_Window").hide();
		});
}	
);


</script>
</head>
<body>

	<div style="display: none;z-index:1230" class="Custome_Popup_Window"
		id="address_popup1" >
		<div class="Cus_Popup_Outline" style="position:fixed;width:600px!important;height:500px;top:50%;left:50%;margin-top:-250px;margin-left:-300px;">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title">
				<div style="padding: 0px 0px 0px 15px; float: left">Add
					Address</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>
		
		<div style="margin-top:50px;margin-left:95px;">
			<div class="row" >
				<div class="label">Branch</div>

				<select id="branch_1" name="branch_1" class="selectbox" style="display:inline;width:165px;" tabindex="1">
					<option value="Corporate Office">Corporate Office</option>
					<option value="Site Office">Site Office</option>
					<option value="Corporate Office">Warehouse / Distribution
						Center</option>
					<option value="Store">Store</option>
				</select>
			</div>


			<div class="row">
				<div class="label">Country<span class="mandatory">*</span></div>

				<select id='countryregion_1' name='countryregion_1'
					class="selectbox required" onchange="fetchState(this.id);stateDropDown(this.value)"  tabindex="2"></select>
				<label for="countryregion_1" generated="true" class="error"
					id="countryregion_1err" style=""></label>
			</div>

			<div class="row">
				<div class="label">Address</div>

				<input type='text' id='address_1' name='address_1'
					style="margin-bottom: 5px;" class="textbox" tabindex="3" />
			</div>

			<div class="row">
				<div class="label">City</div>

				<input type='text' id='city_1' name='city_1'
					style="margin-bottom: 5px;" class="textbox" tabindex="4" />
			</div>

			<div class="row">
				<div class="label">State/Province</div>

				<div id="select_1_container"><select id='state_1' name='state_1' style="margin-bottom: 5px;"
					class="selectbox" tabindex="5"><option value="select">--Select
						State--</option></select>
						<label 
					 class="error" id="state_0err"></label></div>
					 <input   style="display:none;margin-left:200px;margin-top:-33px;"   type="text" name="state_text_1" class="textbox" id="state_text_1">
			</div>

			<div class="row">
				<div class="label">Zip Code/Postal Code</div>

				<input type='text' id='zipcode_1' name='zipcode_1'
					style="margin-bottom: 5px;" class="textbox" tabindex="6" />
			</div>

			<div class="row" style="margin-top:20px;margin-left:20px;">
				 <input id="addr1_popup_cancel" style="margin-left:50px;margin-right: 50px"
					type='button' class="gen-btn-Gray" value="Cancel" tabindex="7" />
					<input id="saveaddr1btn" type='button' 
					class="gen-btn-Orange" value="Ok" tabindex="8" />
			</div>
			
			<div class="row" style="margin:0px; width:0px; opacity:0; height:0px; "id="addone_lastHiddenElement" href="#"  tabindex="9">
			</div>
		</div>
			
		</div>
		

	</div>

</body>
</html>
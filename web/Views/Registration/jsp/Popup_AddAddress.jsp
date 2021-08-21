<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="core.companyprofile.*" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/ResetCSS.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<script type="text/javascript">
$(function(){
	
	$(".Gen_Cus_Popup_Close").click(function(){$("#Popup_Address").hide();});
	$("#Popup_Address_Cancel").click(function(){$("#Popup_Address").hide();});
	
}	
);

</script>
</head>
<body>

<form onSubmit="" method="post" style="display: none;" class="Custome_Popup_Window"
		id="Popup_Address">
		<div class="Cus_Popup_Outline" style="position:fixed;width:600px!important;height:500px;top:50%;left:50%;margin-top:-250px;margin-left:-300px;">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title">
				<div style="padding: 0px 0px 0px 15px; float: left">Add
					Address</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>
		
		<div style="margin-top:50px;margin-left:95px;">
			<div class="row" >
				<div class="label">Branch</div>

				<select tabindex="1" id="branch_pop" name="branch_pop" class="selectbox"  style="width:165px;">
				
				</select>
			</div>


			<div class="row">
				<div class="label">Country<span class="mandatory">*</span></div>

				<select tabindex="2" id='countryregion_pop' name='countryregion_pop'
					class="selectbox required" onchange="fetchState(this.id);dropDown(this.value)"  style="width:165px;"></select>
				<label for="countryregion_1" generated="true" class="error"
					id="countryregion_1err" style=""></label>
			</div>

			<div class="row">
				<div class="label">Address</div>

				<input tabindex="3" type='text' id='AddAddress_pop' name='AddAddress_pop'
					style="margin-bottom: 5px;" class="textbox" />
			</div>

			<div class="row">
				<div class="label">City</div>

				<input tabindex="3" type='text' id='city_pop' name='city_pop'
					style="margin-bottom: 5px;" class="textbox" />
			</div>

			<div class="row">
				<div class="label">State/Province</div>

				<div id="select_1_container"><select tabindex="5" id='state_pop' name='state_pop' style="margin-bottom: 5px;width:165px;"
					class="selectbox"  ><option value="select" >--Select
						State--</option></select>
					</div>
							
							<input  tabindex="5" style="display:none;margin-left:200px;margin-top:-32px;"   type="text" name="state_text" class="textbox" id="state_text">
			</div>

			<div class="row">
				<div class="label">Zip Code/Postal Code</div>

				<input tabindex="6" type='text' id='zipcode_pop' name='zipcode_pop'
					style="margin-bottom: 5px;" class="textbox" />
			</div>

			<div class="row" style="margin-top:20px;margin-left:20px;">
			
					 <input tabindex="8" id="Popup_Address_Cancel" style="margin-left:50px;margin-right: 50px"
					type='button' class="gen-btn-Gray" value="Cancel" />
					
					<input tabindex="7" id="Popup_Address_OK" type='button' 
					class="gen-btn-Orange" value="Ok" />
					
			</div>
			</div>

		</div>


	</form>


<!--  <form onSubmit="" method="post" id="Popup_Address"  class="Custome_Popup_Window">

	<table>
		<tr>
			<td style="vertical-align: middle;">
				<div class="Cus_Popup_Outline">
					<div class="Popup_Tilte_AddAddress Gen_Popup_Title">
					 	<div style="padding: 5px 0px 0px 15px;float:left">Add AddAddress</div>
					 	<div class="Popup_Close_AddAddress Gen_Cus_Popup_Close"></div>
					</div>
					<table>
						<tr>
							<td style="width: 200px;">Branch</td>
							<td>
								<select id="branch_pop" name="pop_branch_pop"  class="Cus_Popup_Field Cus_Pop_selectbox">
								</select>
							</td>
						</tr>
						<tr>
							<td>Country/Region<span class="mandatory">*</span></td>
							<td>
								<select id='countryregion_pop' onchange="fetchState(this.id)" name='countryregion_pop'
								class="Cus_Popup_Field Cus_Pop_selectbox required" onchange="fetchState(this.id)">	
								<option value="--Select Country--">--Select Country--</option>								
								
								</select>
							</td>
						</tr>
						<tr>
							<td style="vertical-align: top;">Address</td>
							<td>
								<textarea id='AddAddress_pop' name='AddAddress_pop'
								style="margin-bottom: 5px;" class="Cus_Popup_Field" ></textarea>
							</td>
						</tr>
						<tr>
							<td>City</td>
							<td>
								<input type='text' id='city_pop' name='city_pop'
								style="margin-bottom: 5px;" class="Cus_Popup_Field" />
							</td>
						</tr>
						<tr>
							<td>State</td>
							<td>
								<select id='state_pop' name='state_pop' style="margin-bottom: 5px;"
								class="Cus_Popup_Field Cus_Pop_selectbox">
								<option value="--Select State--">--Select State--</option>

								</select>
							</td>
						</tr>
						<tr>
							<td>Zipcode</td>
							<td>
								<input type='text' id='zipcode_pop' name='zipcode_pop'
								style="margin-bottom: 5px;" class="Cus_Popup_Field" />
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input id="Popup_Address_OK" type='button' style="margin-right: 20px" class="general-button gen-btn-Orange" value="OK" />
								<input id="Popup_Address_Cancel" type='button' class="general-button gen-btn-Gray"   value="Cancel" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
	
</form>-->

</body>
</html>
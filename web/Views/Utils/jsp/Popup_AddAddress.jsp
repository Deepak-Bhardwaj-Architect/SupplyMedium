<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
<script type="text/javascript">
$(function(){
	
	$(".Cus_Popup_Close").click(function(){$("#Custome_Popup_Window").hide();});
	$("#Popup_Cancel").click(function(){$("#Custome_Popup_Window").hide();});
	
}	
);

</script>
</head>
<body>

<div  style="display: block;" id="Custome_Popup_Window">

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
							<td style="width: 150px;">Branch</td>
							<td>
								<select id="branch_1" name="branch_1" class="Cus_Popup_Field Cus_Pop_selectbox">
									<option value="Corporate Office">Corporate Office</option>
									<option value="Site Office">Site Office</option>
									<option value="Corporate Office">Warehouse / Distribution									Center</option>
									<option value="Store">Store</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>Country/Region</td>
							<td>
								<select id='countryregion_1' name='countryregion_1'
								class="Cus_Popup_Field Cus_Pop_selectbox required" onchange="fetchState(this.id)"></select>
							</td>
						</tr>
						<tr>
							<td>AddAddress</td>
							<td>
								<input type='text' id='AddAddress_1' name='AddAddress_1'
								style="margin-bottom: 5px;" class="Cus_Popup_Field" />
							</td>
						</tr>
						<tr>
							<td>City</td>
							<td>
								<input type='text' id='city_1' name='city_1'
								style="margin-bottom: 5px;" class="Cus_Popup_Field" />
							</td>
						</tr>
						<tr>
							<td>State</td>
							<td>
								<select id='state_1' name='state_1' style="margin-bottom: 5px;"
								class="Cus_Popup_Field Cus_Pop_selectbox"><option value="select">--Select
									State--</option></select>
							</td>
						</tr>
						<tr>
							<td>Zipcode</td>
							<td>
								<input type='text' id='zipcode_2' name='zipcode_2'
								style="margin-bottom: 5px;" class="Cus_Popup_Field" />
							</td>
						</tr>
						<tr>
							<td colspan="3" align="center">
								<input type='button' style="margin-right: 50px" class="pop-button pop-button-Orange" value="Okey" />
								<input id="Popup_Cancel" type='button' class="pop-button pop-button-Gray"   value="Cancel" />
							</td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
	</table>
	
</div>

</body>
</html>
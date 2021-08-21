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

<div style="display: block;" id="Custome_Popup_Window">

	<table>
		<tr>
			<td style="vertical-align: middle;">
				<div class="Popup_Outline_NewGroup Cus_Popup_Outline">
					<div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
					 	<div style="padding: 5px 0px 0px 15px;float:left">User Settings</div>
					 	<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
					</div>
					<table>
						<tr>
							<td colspan="2" style="text-align: right;">
								<input type='button' style="margin: -20px 50px -25px 0px;;" class="pop-button pop-button-red" value="Delete Account" />
							</td>
						</tr>
						
						<tr>							
							<td colspan="2" class="Pop_Heading_USet">Account Registration</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>User must change password at next login</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Account is disabled</td>
						</tr>
						<tr>
							<td colspan="2" class="Pop_Heading_USet">Account Expiration</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>End of <input type="text" style="width: 20px;border: 1px solid #F87B05;padding: 1px 2px;" />  days</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Never</td>
						</tr>
						
						<tr>
							<td colspan="3" align="center">
								<input type='button' style="margin: 20px 50px 0px 0px;" class="pop-button pop-button-Orange" value="SAVE" />
								
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
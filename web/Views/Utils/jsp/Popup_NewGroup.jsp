<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery-1.9.0.js"></script>
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
					 	<div style="padding: 5px 0px 0px 15px;float:left">New Group</div>
					 	<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
					</div>
					<table>
						
						<tr>
							<td>Group Name</td>
							<td>
								<input type='text' id='GroupName' name='GroupName'
								style="margin-bottom: 5px;" class="Cus_Popup_Field" />
							</td>
						</tr>
						
						<tr>
							<td colspan="3" align="center">
								<input type='button' style="margin: 20px 50px 0px 0px;" class="pop-button pop-button-Orange" value="SAVE" />
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
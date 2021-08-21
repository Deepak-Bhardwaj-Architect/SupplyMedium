<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/popup_warning.css" />

<script type="text/javascript">
$(function(){
	
	$(".Cus_Popup_Close").click(function(){$("#warning_container").hide();});
	$("#Popup_Cancel").click(function(){$("#warning_container").hide();});
	
	$('#okbtn').click(
			function()
			{
				deleteConfirm();
				
				$("#warning_container").hide();
			}
		);
	
	$('#Popup_Cancel').click(
			function()
			{
				cancelInfo();
				
				$("#warning_container").hide();
			}
		);
}	
);

function showWarning( message )
{
	$('#war_message').text(message);
	
	$("#warning_container").show();
	
	$("#Popup_Cancel").focus();
}

</script>
</head>
<body>

<div id="warning_container" style="display: none;">

<div  id="warning_popup">
	<div id="war_head">
		<label id="war_head_title" >Warning</label>
	</div>
	<div id="war_body">
		<label id="war_message">
		
		</label>
		<div id="war_btns">
			<input type='button' id="okbtn" style="float:left;margin-right:30px;" class="pop-button pop-button-White"  value="Yes" />
			<input id="Popup_Cancel"  style="float:left;" type='button' class="pop-button pop-button-White"   value="No" />
		</div>
	</div>
	
	
</div>
</div>


</body>
</html>
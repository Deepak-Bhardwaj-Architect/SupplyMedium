<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script>
$(document).ready( function()
{
	$("#tc_content_close").click(function()
	{
		$("#tc_content_popup").hide();
		
	});
});
</script>
<body>

<div style="display: none; z-index: 1000;" class="Custome_Popup_Window"
		id="tc_content_popup" style="">
		<div class="Cus_Popup_Outline add_content_outline">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title"
				style="border-radius: 0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Content</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" id="tc_content_close"></div>
			</div>
			
			<textarea class="content_txtarea" id="content_txtarea">
			</textarea>
			
			<input type="button" class="gen-btn-blue" value="Save" style="margin-left: 290px;" id="content_save_btn">
			
		</div>
	</div>

</body>
</html>
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
	
	$("#tc_accept_btn").click(function()
	{
		$("#tc_content_popup").hide();
		
		var checkBoxId = $("#check_box_id").val();
		
		$('#'+checkBoxId).prop('checked', true);
		
	});
	
	$("#tc_reject_btn").click(function()
	{
		$("#tc_content_popup").hide();
		
		var checkBoxId = $("#check_box_id").val();
		
		$('#'+checkBoxId).prop('checked', false);
				
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
			
			<textarea class="tc_content" id="tc_content" disabled>
			</textarea>
			
			<input type="button" class="gen-btn-blue" value="Accept" style="margin-left: 180px;" id="tc_accept_btn">
			
			<input type="button" class="gen-btn-red" value="Reject" style="margin-left: 90px;" id="tc_reject_btn">
			
			<input type="hidden" id="check_box_id">
			
		</div>
	</div>

</body>
</html>
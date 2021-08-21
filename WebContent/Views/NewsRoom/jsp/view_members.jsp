<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Members</title>
<script>
$(".Gen_Cus_Popup_Close").click( function()
{
	$("#member_container").hide();
});
</script>
</head>
<body>
<div class="container Cus_Popup_Outline" id="member_container" style="display:none;">
	<div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
			<div style="padding: 0px 0px 0px 15px; float: left">Members</div>
			<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
	</div>
			
	<div class="member_container">
		
		<div class="add_member_container">
			<div class="add_member" id="members_count"></div>
			<div class="textbox_container">
				<input type="text" placeholder="Find a Member" class="newsroom_textbox">
				<img src="${pageContext.request.contextPath}/Views/NewsRoom/images/search_lens.png" style="float:left; cursor:pointer;">
			</div>	
		</div>
			
		<div class="button_container">
			<div class="del_button" id="delete_members_btn">Delete</div>
		</div>
			
	</div>
		
	<div class="memberlist_container" id="memberlist_container">
		
	</div>
		
</div>

</body>
</html>
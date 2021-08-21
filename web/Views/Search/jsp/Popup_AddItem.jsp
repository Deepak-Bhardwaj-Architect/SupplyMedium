<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Search/css/Popup_AddItem.css" />
<script type="text/javascript">
$(function(){
	
	$("#Popup_AddItem_Cancel").click(function(){$("#Popup_AddItem").hide();});
	$(".Gen_Cus_Popup_Close").click(function(){$("#Popup_AddItem").hide();});
	
}	
);

</script>
</head>
<body>

	<div style="display: none; background-color: transparent;"
		id="Popup_AddItem" class="Custome_Popup_Window">


		<div class="Popup_Outline_AddItem Cus_Popup_Outline">
			
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;-webkit-border-radius: 0px;-moz-border-radius: 0px;">
					 	Add Item
					<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>
					
			<div style="text-align: left; margin-top: 20px;">
				<div class="Popup_label">
					<label for="txtPopupPartNumber">Part Number</label>
				</div>
				<input type="text" id="txtPopupPartNumber" /> <br />
				<div class="Popup_label">
					<label for="txtPopupPartName">Name</label>
				</div>
				<input type="text" id="txtPopupPartName" />
			</div>
			<div style="text-align: center;">

				<input id="Popup_AddItem_Add" type='button'
					style="margin: 15px 16px 0px 0px;"
					class="general-button gen-btn-Orange" value="Add" /> <input
					id="Popup_AddItem_Cancel" type='button'
					class="general-button gen-btn-Gray" value="Cancel" />
			</div>
		</div>


	</div>

</body>
</html>
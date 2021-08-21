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
	
	$(".Gen_Cus_Popup_Close").click(function(){$("#edit_group_popup").hide();});
	$("#edit_cancel_group").click(function(){$("#edit_group_popup").hide();});
	
	$('#edit_group_input').keyup(function() 
	{
		$('#edit_group_input_err').text();
	});
}	
);

</script>
</head>
<body>

<div style="display: none;" id="edit_group_popup" class="Custome_Popup_Window">

	<table>
		<tr>
			<td style="vertical-align: middle;">
				<div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout" >
					<div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
					 	Rename Group
					 	<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
					</div>

					<div class="popupcontent">
					<div class="row">
						<div class="label" style="width:150px;">Group Name</div>
						<input type='text' id='edit_group_input' name='edit_group_input'
							 class="textbox required" style="width:300px;height:20px;"/>
						<label id="edit_group_input_err" style="margin-left:150px" class="error"></label> 
					</div>

					<div class="row" style="margin-top:60px;margin-left:120px;">
						
						<input id="edit_cancel_group" type='button' style="margin-right:20px"
								class="gen-btn-Gray" value="Cancel" />
						<input type='button' id="edit_group"
								 class="gen-btn-Orange"
								value="Save" /> 
					</div>
					</div>

					</div>
			</td>
		</tr>
	</table>
	
</div>

</body>
</html>
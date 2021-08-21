<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />

</head>
<body>

<div style="display: none;" id="policies_popup" class="Custome_Popup_Window">

	<table>
		<tr>
			<td style="vertical-align: middle;">
				<div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout" >
					<div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
					 	User Settings
					 	<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
					</div>

						<div class="popupcontent">

							<div class="policies_sub_head">Account Restriction</div>
							<div class="policies_row" >
								<div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="change_password" /><div></div></div> <label
									for="change_password" class="policies_checkbox_lbl">User must change password at
									next login</label>
							</div>
							<div class="policies_row" >
								<div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="acc_disable" /><div></div></div> <label
									for="acc_disable" class="policies_checkbox_lbl">Account is disabled</label>
							</div>
							<div class="policies_sub_head">Account Expiration</div>
							<div class="policies_row" >
								<div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="exp_end" /><div></div></div> <label for="exp_end" class="policies_checkbox_lbl">End
									of</label>&nbsp; <input id="policies_spinner" style="width:20px;"name="value"><label>&nbsp;days</label>
							</div>
							<div class="policies_row">
								<div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="exp_never" /><div></div></div> <label for="exp_never" class="policies_checkbox_lbl">Never</label>
							</div>
							<input onclick="savePolicies();" style="margin-top:20px;margin-left:200px;" type="submit" value="Save" class="gen-btn-Orange" />
						</div>
					</div>
			</td>
		</tr>
	</table>
	
</div>
<input type="hidden" id="emailId" />
<input type="hidden" id="rowno" />

<script type="text/javascript">
$(function(){
	
	$(".Gen_Cus_Popup_Close").click(function(){$("#policies_popup").hide();});
	//$("#rename_cancel").click(function(){$("#edit_group_popup").hide();});
	
	var spinner = $( "#policies_spinner" ).spinner();
	$('#policies_spinner').spinner('option', 'min', 0);
	$('#policies_spinner').spinner('option', 'max', 2013);
	$('#policies_spinner').val("0");
}	
);
  </script>
</body>
</html>
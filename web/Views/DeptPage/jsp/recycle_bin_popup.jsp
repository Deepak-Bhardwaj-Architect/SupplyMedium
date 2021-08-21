<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/RestCSS.css" />
<script type="text/javascript">
$(function(){
	
	$(".Gen_Cus_Popup_Close").click(function(){$("#recycle_bin_popup").hide();});
	$("#cancel_dept").click(function(){$("#recycle_bin_popup").hide();});
}	
);

$(document).ready(function()
		{
			
			$(".dataTables_scrollBody").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
		});

</script>

</head>
<body>
	<div style="display: none;" id="recycle_bin_popup"
		class="Custome_Popup_Window">

		<div class="deptpopuplayout Popup_Outline_NewGroup Cus_Popup_Outline ">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;-webkit-border-radius:0px;-moz-border-radius:0px;">
				Recycle Bin
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>

			<div class="deptpopupcontent">
				<div class="data_table_content">
					<table id="file_list" class="file_list">
						<thead >
							<tr>
								<th class="rowBorder">File</th>
								<th class="rowBorder">Date</th>
								<th class="rowBorder">Size</th>
								<th class="rowBorder">Type</th>
							</tr>
						</thead>
						<tbody>
					
						</tbody>

					</table>
				</div>
				<div class="btn_container">
				
					<input type="button" value="Restore" id="restorebtn"
						class="gen-btn-Orange restorebtn" />
						
						<input type="button" value="Empty Recycle Bin" id="empty_recycle_btn"
						class="gen-btn-Orange restorebtn" style="width:160px;margin-left:20px;"/>
				</div>

			</div>

		</div>

	</div>
</body>
</html>
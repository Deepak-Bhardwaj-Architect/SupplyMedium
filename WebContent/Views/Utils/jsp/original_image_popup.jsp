<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/original_image_popup.css" />

<script type="text/javascript">
$(function()
{

	$("#image_head").click(function()
	{
		$("#image_container").hide();
		});
	}	

);

function showFullSizeImage( url )
{
	$("#image_container").show();
	$("#image_body").empty();
	var imageTag =  "<img src='" + url+ "' class='original_img' id='orgi_image'/>";
	$("#image_body").append(imageTag);
	
	createThumbnail( $("#orgi_image"),900,600 );
}
</script>
</head>

<body>

<div id="image_container" style="display: none;">
<center>
<div  id="image_popup">
	<div id="image_head">
		<input id="Popup_Cancel"   style="float:right;font-size:20px;background-color:transparent;border:none;color:#ccc;cursor:pointer;padding-right:5px;" type="button" value="X" />
	</div>
	<div id="image_body">
		
	</div>
	
	
</div>
</center>
</div>

</body>
</html>
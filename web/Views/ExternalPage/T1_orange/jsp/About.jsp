<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SupplyMedium</title>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery-1.9.0.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/common.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/custome_controls.css" />
<link rel="stylesheet" href="../css/T1_orange_common.css" />
<link rel="stylesheet" href="../css/T1_about.css" />
<link rel="stylesheet" href="../css/T1_common.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/ResetCSS.css" />
<script type="text/javascript">
	$(function() {
		$("#T_menu_abouts").addClass("selected");
	});
</script>
</head>
<body>
	<jsp:include page="/Views/Utils/jsp/header.jsp"></jsp:include>
	
	<form method="post" id="T_container" class="content_container" style="padding-top: 0px;">	
		
		<%@include file="Banner.jsp" %>	
		
		
		<div class="T_content_container">
			<div id="T_menu_about">
				<div class="T_about_title T_hading_Title">
					@#ABOUT_TITLE
				</div>
				<div class="T_about_text">
					<p>
						<img alt="" style="display:@#ABOUT_IMAGE;float:right;padding-left:20px;max-width:500px;" src="../images/@#IMG_NAME_1">		
						@#ABOUT_CONTENT
						
					</p>
				</div>
				
				
				
				
			</div>
		</div>
		
		
		<%@include file="Copyrights.jsp" %>
		
		
	</form>
	
	
	
	
		<jsp:include page="/Views/Utils/jsp/regnfooter.jsp"></jsp:include>

	<jsp:include page="/Views/Utils/jsp/Cus_Toast.jsp"></jsp:include>

</body>
</html>
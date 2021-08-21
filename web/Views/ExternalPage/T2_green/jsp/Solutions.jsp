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
<link rel="stylesheet" href="../css/T2_green_common.css" />
<link rel="stylesheet" href="../css/T2_solutions.css" />
<link rel="stylesheet" href="../css/T2_common.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/ResetCSS.css" />
<script type="text/javascript">
	$(function() {
		$("#T_menu_solutions").addClass("selected");
	});
</script>
</head>
<body>
	<jsp:include page="/Views/Utils/jsp/header.jsp"></jsp:include>
	
	<form method="post" id="T_container" class="content_container" style="padding-top: 0px;">	
		
		<%@include file="Banner.jsp" %>	
		
		<div class="T_content_container">
			<div class="T_menu_Product">
				<div class="T_video_container_Two" style="display:@#VIDEO_FULL_D"> 
					<ul>
						<li class="video" style="display: @#VIDEO_1_V_D;">
							<iframe width="100%" height="100%" src="@#VIDEOURL_1" frameborder="0" allowfullscreen></iframe>
						</li>
						<li class="videodesc" style="display: @#VIDEO_1_C_D;">
							<p>
								@#VIDEO_CONTENT_1
							</p> 
						</li>
					</ul>					
				</div>
				<div class="T_video_container_Two" style="display:@#VIDEO_HR_LINE_D"> 
					<ul>
						<li class="video" style="display: @#VIDEO_2_V_D;">
							<iframe width="100%" height="100%" src="@#VIDEOURL_2" frameborder="0" allowfullscreen></iframe>
						</li>
						<li class="videodesc" style="display: @#VIDEO_2_C_D;">
							<p>
								@#VIDEO_CONTENT_2
							</p> 
						</li>
					</ul>					
				</div>
				<br style="background: #e4e4e4" />

				<div class="T_image_container">
					<div class="T_products_list" >
						<div class="@#IMG_CONTAINER_1_CSS" style="display: @#IMG_1_FULL_D;">
							<img alt="" src="../images/@#IMG_NAME_1">
						</div>
						<label class="T_hading_Title" style="display: @#IMG_TITLE_1_D;">@#IMG_TITLE_1</label>
						<p style="display: @#IMG_CONTENT_1_D;">@#IMG_CONTENT_1</p>
					</div>
					
					<div class="T_products_list" >
						<div class="@#IMG_CONTAINER_2_CSS" style="display: @#IMG_2_FULL_D;">
							<img alt="" src="../images/@#IMG_NAME_2" >
						</div>
						<label class="T_hading_Title" style="display: @#IMG_TITLE_2_D;">@#IMG_TITLE_2</label>
						<p style="display: @#IMG_CONTENT_2_D;">@#IMG_CONTENT_2</p>
					</div>
					
					<div class="T_products_list" >
						<div class="@#IMG_CONTAINER_3_CSS" style="display: @#IMG_3_FULL_D;">
							<img alt="" src="../images/@#IMG_NAME_3">
						</div>
						<label class="T_hading_Title" style="display: @#IMG_TITLE_3_D;">@#IMG_TITLE_3</label>
						<p style="display: @#IMG_CONTENT_3_D;">@#IMG_CONTENT_3</p>
					</div>
					
					<div class="T_products_list" >
						<div class="@#IMG_CONTAINER_4_CSS" style="display: @#IMG_4_FULL_D;">
							<img alt="" src="../images/@#IMG_NAME_4">
						</div>
						<label class="T_hading_Title" style="display: @#IMG_TITLE_4_D;">@#IMG_TITLE_4</label>
						<p style="display: @#IMG_CONTENT_4_D;">@#IMG_CONTENT_4</p>
					</div>
					
				</div>
			</div>
		</div>
		
		
		<%@include file="Copyrights.jsp" %>
		
		
	</form>
	
	
	
	
	<jsp:include page="/Views/Utils/jsp/regnfooter.jsp"></jsp:include>

	<jsp:include page="/Views/Utils/jsp/Cus_Toast.jsp"></jsp:include>

</body>
</html>
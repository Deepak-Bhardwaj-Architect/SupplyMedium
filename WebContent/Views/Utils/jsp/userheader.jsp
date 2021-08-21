<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/userheader.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/notifi_dropdown.css" />
<title>Insert title here</title>
</head>
<body>
<%@include file="../../../session_check.jsp"%>
<div class="header" id="header"> 
	<div class="headercontent"> 
	
		<div class="header_left">
		
		<div id="comp_logo" style="display:none;">
			<a href="${pageContext.request.contextPath}/index.jsp" style="cursor:pointer;">
				<img class="company_logo" id="company_logo" width="90px" height="90px" style="border:none;margin-top:10px;float:left;"/>
			</a>
			<label class="company_label"  id="company_label"></label>
		</div>
		
		<div class="logo" id="supplymedium_logo" style="position:relative;display:none;">
			<a href="${pageContext.request.contextPath}/index.jsp" style="cursor:pointer;">
			<img src="${pageContext.request.contextPath}/Views/Utils/images/logo.png" width="325px" height="100px" style="border:none;margin-top:10px;">
			</a>
		</div>
			
		</div>
			
		<div class="header_right">
			<div class='userdetails'>
				
					<div class="acc_menu" id="acc_menu_link">
						<a  style="cursor:pointer;color:#009abc !important;" >My Account</a>
					</div>
					
					<div  id="acc_menu_content" class="acc_menu_content" style="display:none;">
						<a style="margin-top:19px;" class="acc_menu_item"  onclick="showUserAccSettings();">Account Settings</a>
						<a href="${pageContext.request.contextPath}/LogoutServlet" class="acc_menu_item" onclick="invalidateSession();">Logout</a>
					</div>
					
					<div class="notification" id="notification">
					</div>
					
					<div class="notification_badge" id="notification_count" style="display:none;"></div>
					
					<div class="username" id="username">
					</div>
					
					<img class="userimage" id="header_userimage" />
										
			</div>
			<div class="header_links_container">
				<a class="header_link" id="dashboard_link" style="width: 70px;"> Dashboard</a>
				<a class="header_link" id="news_link" style="width: 54px;"> News   | </a>
				<a class="header_link" id="website_link" style="width: 130px;"> Company Website   | </a>
			</div>
			
		</div>	
		
		<%@include file="notifi_dropdown.jsp"%>
	</div>

</div>


<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/notifi_dropdown.js"></script>
	
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/userheader.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/layout.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Registration/css/login.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery-1.9.0.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery-ui-1.10.0.custom.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery.validate.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/additional-methods.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/common.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Registration/js/login.js"></script>
	
<title>Supply Medium</title>
</head>
<%String uuid = (String)request.getParameter( "key" );  %>
<body onload="getPasswordPolicy()">

<%@include file="../../Utils/jsp/header.jsp"%>



	<div class="pagetitlecontainer">
		<div class="pagetitle"></div>
	</div>

	<div class="logincontentcontainer">
		<div class="logincontent">
		<div class="title" >Password Reset</div>
		<form action="${pageContext.request.contextPath}/PasswordResetServlet"
			method="post" name="pwrresetfrm" id="pwrresetfrm" style="margin-top:70px;margin-left:100px;">
		<div class="row"> <input type="hidden" id="uuid" name="uuid" value="<%= uuid %>">
						<div class="label" style="width:220px;">New Password<span class="mandatory">*</span></div>
						<input type="password" name="password" id="password" 
							class="textbox required" />
					</div>
					<div class="row">
						<div class="label" style="width:220px;">Confirm Password<span class="mandatory">*</span></div>
						<input type="password" name="retypepassword" id="retypepassword"
							class="textbox required" />
					</div>
					
					
					<div class="row" style="margin-left:170px;margin-top:30px;">
					
		<input type="submit" name="confirm" value="Confirm" class="gen-btn-Orange"/>
		</div>
		
		<div class="loginerr" id="loginerr" style="margin-right:100px;">
						
						<%
							String loginerr = (String)request.getAttribute( "error" );
							if( loginerr != null )
							{
								out.print( loginerr );
							}
						%>
						
					</div>
		</form>
		</div>
		</div>

<%@include file="../../Utils/jsp/regnfooter.jsp"%>

</body>
</html>
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
	src="${pageContext.request.contextPath}/Views/Registration/js/login.js"></script>
	
<title>Supply Medium</title>
</head>
<body>
<%@include file="../../Utils/jsp/header.jsp"%>

	<div class="pagetitlecontainer">
		<div class="pagetitle" >Forget Password</div>
	</div>

	<div class="logincontentcontainer">
		<div class="logincontent">
		<div class="title" style="margin-bottom:50px;">Forget your password?</div>
		<form action="${pageContext.request.contextPath}/ForgetPasswordServlet" method="post" name="forgetpwdfrm" id="forgetpwdfrm">
		<div class="row" style="width:650px;height:50px;">
			<div class="label" style="width:650px;text-align:center">Enter your Email address</div>
		</div>
		
		<div class="row" style="margin-left:100px;height:70px;">
			<div class="label" style="text-align:center">Email Address</div>
			<input type="text" class="textbox required email" name="email"/>
		</div>
		<div class="row" style="margin-left:280px;height:70px;">
		<input type="submit" name="send" value="Send" class="gen-btn-Orange"/>
		</div>
		<div class="loginerr" id="loginerr" style="width:100%;">
						
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
</body>
</html>
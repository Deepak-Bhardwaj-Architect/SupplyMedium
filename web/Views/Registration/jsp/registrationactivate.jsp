<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Activation</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/layout.css" />

</head>
<body>
<%@include file="../../Utils/jsp/header.jsp"%>
<div class="pagetitlecontainer">
		<div class="pagetitle">Registration Activation</div>
	</div>
	<div class="contentcontainer">
		<div class="content">
		
			<%
 			 String result = (String) request.getAttribute("result");
				if(result!=null)
				{
					out.println(result);
				}
			 %>
			
		</div>
	</div>
	<%@include file="../../Utils/jsp/regnfooter.jsp"%>
</body>
</html>
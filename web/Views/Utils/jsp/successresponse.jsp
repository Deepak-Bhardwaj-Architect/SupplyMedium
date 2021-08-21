<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/layout.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/successresponse.css" />

</head>
<body>
<%@include file="../../Utils/jsp/header.jsp"%>
<div class="pagetitlecontainer">
		<div class="pagetitle"></div>
	</div>
	<div class="contentcontainer">
		<div class="successcontent">
			<div class="successtitle"><%
			
					String resulttype = request.getParameter("resulttype");
					if(resulttype!=null)
					{
						out.println(resulttype);
					}
					%></div>
			<div class="resimg">
			
			  	<% if(resulttype != null && resulttype.equals( "Confirmation" ) )
				{
					out.println("<img src='/SupplyMedium/Views/Utils/images/success.png'>");
				}
				else if(resulttype != null && resulttype.equals( "Failed" ))
				{
					out.println("<img src='/SupplyMedium/Views/Utils/images/failed.png'>");
				} 
				else
				{
					out.println("<img src='/SupplyMedium/Views/Utils/images/success.png'>");
				}
				%>
				
			</div>
			<div class="resmsg">
			<% 
			String result = (String) request.getParameter("result");
			if(result!=null)
			{
                            result=result.replace("build/web/index.jsp","");
                            //result=result.replace("/null/","/");
					out.println(result);
			}
				
			%>
			</div>
		</div>
	</div>
	<%@include file="../../Utils/jsp/regnfooter.jsp"%>
</body>
</html>
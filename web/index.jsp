<%@page import="core.login.SessionData"%>
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
<link rel="stylesheet" href="${pageContext.request.contextPath}/index.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />

<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery-1.9.0.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery-ui-1.10.0.custom.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/jquery.validate.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/index.js"></script>
<title>SupplyMedium</title>
</head>
<body>
	<%
	 	SessionData sessionObj = (SessionData)session.getAttribute("UserSessionData");
	
		if( sessionObj != null )
		{
			response.sendRedirect("/SupplyMedium/companyadminhome.jsp");
		}
	
		Cookie [ ] cookies = request.getCookies( );

		boolean foundCookie = false;
		String email = null;
		String password = null;

		if( cookies != null )
		{

			for( int i = 0; i < cookies.length; i++ )
			{
				Cookie c = cookies[i];
				
				if( c.getName( ).equals( "email" ) )
				{
					email = c.getValue( );
				}
				if( c.getName( ).equals( "password" ) )
				{
					password = c.getValue( );
				}
			}
			
			if( email != null && password != null )
			{
				foundCookie = true;
			}
		}

		
	%>

	<%@include file="Views/Utils/jsp/header.jsp"%>

	<div class="pagetitlecontainer">
		<div class="pagetitle">Sign In</div>
	</div>
 
	<div class="logincontentcontainer" 
	<%if( foundCookie )
			    out.println( "style='display:none'" );
		else out.println( "style='display:block'" );
		%> >
		<div class="logincontent" >
			<div class="signintitle">Sign In</div>
			<form name="loginform" id="loginform" method="post"
				action="${pageContext.request.contextPath}/LoginServlet" >
				<div class="loginleft">

					<div class="emailcontainer">
						<div class="loginlbl ">Email</div>
						<input type="text" name="email" id="email"
							<%if( foundCookie )
			    out.println( "value='" + email + "'" );%>
							class="required email textbox" style="width: 290px;" />
					</div>
					<div class="passwordcontainer">
						<div class=" loginlbl">Password</div>
						<input type="password" name="password" id="password"
							<%if( foundCookie )
			    out.println( "value='" + password + "'" );%>
							class="required textbox" style="width: 290px;" />
					</div>
					<div class="remembercontainer">
						<div class="checkContainer"><input type="checkbox" name="remember" id="remember" style="cursor:pointer;"
							  /><div></div></div>
						<label for="remember" class=" loginlbl" style="line-height: 18px; margin-left: 5px;cursor:pointer;">Remember
							me on this computer</label>
					</div>

					<div class="loginerr" id="loginerr">
						<%
							String loginerr = (String)request.getAttribute( "error" );
						//out.println( "From JSP: "+loginerr );
						
							if( loginerr != null )
							{
								out.print( loginerr );
							}
						%>

						
					</div>

					
				</div>
				<div class="loginright" style="height:175px;">

					<div class=" loginlbl labellink">
						New to Supply Medium ? <br /> <a class="color:#0097b9;"
							href="Views/Registration/jsp/companyregistration.jsp"
							id="registerlink"> Register a company or Business for free </a>
					</div>

					<div class=" loginlbl labellink" style="margin-bottom: 20px;">
						Don't have an account ?<br /> <a class="color:#0097b9;"
							href="Views/Registration/jsp/usersignup.jsp" id="signuplink">
							Signup for free </a>
					</div>

					<div class=" loginlbl" style="color: #f37d01"><a href="Views/Registration/jsp/forgetpassword.jsp">Forgot Password?</a></div>

				</div>
				
				<input type="submit" value="Login" class="gen-btn-Orange"
						style=" margin-top: 5px;margin-left:250px; " />
						
			</form>
		</div>
		
		
	</div>
	
	<div class="loading" 
	<%if( foundCookie )
			    out.println( "style='display:block'" );
		else out.println( "style='display:none'" );
		%> >
		 <img src="${pageContext.request.contextPath}/Views/Utils/images/ajax_loader_big.gif" style="width:70px;height:70px;margin:auto;"/>
		</div>

	<%@include file="Views/Utils/jsp/regnfooter.jsp"%>
	
	<%
							if( foundCookie )
							{
								out.print( "<script type='text/JavaScript'>" );
								out.print( "$('#loginform').submit();" );
								out.print( "</script>" );
							}
						%>
</body>
</html>
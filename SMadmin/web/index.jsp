<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="files/layout.css">
        <link rel="stylesheet" href="files/login.css">
        <link rel="stylesheet" href="files/Custome_Buttons.css">
        <link rel="stylesheet" href="files/regnfooter.css">
        <link rel="stylesheet" href="files/header.css">
        <title>SupplyMedium</title>
    </head>
    <body>
        <div class="header" id="regnheader"> 
            <div class="headercontent"> 
                <div class="logo">
                    <img src="files/logo.png" width="325px" height="100px" style="border:none;margin-top:10px;">
                </div>
            </div>
        </div>
        <div class="pagetitlecontainer">
            <div class="pagetitle">Supply Medium Administration Console</div>
        </div>	 
        <div class="logincontentcontainer" style="display:block">
            <div class="logincontent">
                <div class="signintitle">Sign In</div>
                <form name="loginform" id="loginform" method="post" action="CheckLoginCredential">
                    <div class="loginleft">
                        <div class="emailcontainer">
                            <div class="loginlbl ">Email</div>
                        </div>
                        <div class="passwordcontainer">
                            <div class=" loginlbl">Password</div>
                        </div>
                    </div>
                    <div class="loginleft">
                        <div class=" loginlbl labellink">
                            <input type="text" name="email" id="email" class="required email textbox" style="width: 290px;">
                        </div>
                        <div class=" loginlbl labellink" style="margin-bottom: 20px;">
                            <input type="password" name="password" id="password" class="required textbox" style="width: 290px;">
                            <input type="submit" value="Login" class="gen-btn-Orange" style=" margin-top: 25px;">
                        </div>
                        <%
                            if (request.getParameter("fail") != null) {
                                String msg = "";
                                if (request.getParameter("fail").trim().equals("4")) {
                                    msg = "Problem to check login crdential";
                                } else if (request.getParameter("fail").trim().equals("3")) {
                                    msg = "Email or password not correct";
                                } else if (request.getParameter("fail").trim().equals("1")) {
                                    msg = "You are blocked from your admin";
                                } else if (request.getParameter("fail").trim().equals("5")) {
                                    msg = "Your session has been expired login again";
                                }
                        %>
                        <div style="color: red;"><%=msg%></div>
                        <% }%>
                    </div>							
                </form>
            </div>			
        </div>
        <div class="footer">  
            <div class="footercontent">
                <div class="copyrights">Copyrights Social Medium, Inc with copyright year as 2014</div>		
            </div>
        </div>
    </body>
</html>
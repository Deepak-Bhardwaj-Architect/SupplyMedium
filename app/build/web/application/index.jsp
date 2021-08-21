<%-- 
    Document   : index
    Created on : Sep 18, 2014, 1:35:12 PM
    Author     : LenovoB560
--%>
<%@page import="java.io.File"%>
<%@page import="supply.medium.home.mailing.HTMLMailComposer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK 
            rel="stylesheet" href="inside/layout.css">
        <LINK rel="stylesheet" 
              href="inside/elements.css">
        <LINK rel="stylesheet" href="inside/index.css">
        <LINK rel="stylesheet" href="inside/Custome_Buttons.css">
        <SCRIPT type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.validate.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/index.js">
        </SCRIPT>
        <TITLE>Home - SupplyMedium Inc.</TITLE>
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
        <%
            if(session.getAttribute("companyKey")!=null)
            {
                response.sendRedirect("home.jsp");
            }
        %>
    </HEAD>
    <BODY>
    <LINK rel="stylesheet" href="inside/header.css">
    <DIV id="regnheader" class="header">
        <DIV class="headercontent">
            <DIV class="logo">
                <A href="index.jsp">
                    <IMG 
                        style="border: none; margin-top: 10px;" src="inside/logo.png" 
                        width="325" height="100">
                </A>
            </DIV>
        </DIV>
    </DIV>
    <DIV class="pagetitlecontainer">
        <DIV class="pagetitle">Sign In</DIV>
    </DIV>
    <DIV style="display: block;" class="logincontentcontainer">
        <DIV class="logincontent">
            <DIV class="signintitle">Sign In</DIV>
            <FORM id="loginform" method="post" name="loginform" action="LoginAccess">
                <DIV class="loginleft">
                    <DIV class="emailcontainer">
                        <DIV class="loginlbl ">Email</DIV>
                        <INPUT style="width: 290px;" id="email" class="required email textbox" 
                               name="email" type="text">
                    </DIV>
                    <DIV class="passwordcontainer">
                        <DIV class=" loginlbl">Password</DIV>
                        <INPUT style="width: 290px;" id="password" 
                               class="required textbox" name="password" type="password">
                    </DIV>
                    <DIV class="remembercontainer">
                    </DIV>
                    <DIV id="loginerr" class="loginerr">
                        <%
                            if(request.getAttribute("error")!=null)
                            {
                                out.print("Invalid Email address & Password");
                            }
                        %>
                    </DIV>
                </DIV>
                <DIV style="height: 175px;" class="loginright">
                    <DIV class="loginlbl labellink">
                        New to Supply Medium ? <BR>
                        <A id="registerlink" 
                           class="color:#0097b9;" href="companyRegistration.jsp"> 
                            Register a company or Business for free </A>
                        <br/>(No Credit/Debit Card Required)
                    </DIV>
                    <DIV style="margin-bottom: 30px;" class="loginlbl labellink">
                        Don't have an account ?<BR>
                        <A id="signuplink" class="color:#0097b9;" href="userRegistration.jsp">
                            Signup for free </A>
                    </DIV>
                    <DIV style="color: rgb(243, 125, 1);" class=" loginlbl labellink">
                        <A href="forgotPassword.jsp">Forgot 
                            Password?</A>
                    </DIV>
                </DIV>
                <INPUT style="margin-top: 5px; margin-left: 229px;" class="gen-btn-Orange" value="Login" type="submit">
            </FORM>
        </DIV>
    </DIV>
    <LINK rel="stylesheet" href="inside/regnfooter.css">
    <LINK rel="stylesheet" href="inside/footer.css">
    <TITLE>Insert title 
        here</TITLE>
    <DIV class="footer">
        <DIV class="footercontent">
            <DIV class="copyrights">Copyright 2015. <A style="color: rgb(255, 255, 255); text-decoration: none;"
                                                       href="http://supplymedium.com/" target="_blank">Supply Medium,
                    Inc.</A>
            </DIV>
        </DIV>
    </DIV>
    <LINK rel="stylesheet" href="inside/privacy.css">
    <TITLE>
    </TITLE>
    <SCRIPT type="text/javascript">
        $(function() {

            $(".Gen_Cus_Popup_Close").click(function() {
                $(".Custome_Popup_Window").hide();
            });
            $("#policiesdeclinebtn").click(function() {
                $(".Custome_Popup_Window").hide();
            });

        }
        );

    </SCRIPT>
    <DIV>
    </DIV>
</BODY>
</HTML>

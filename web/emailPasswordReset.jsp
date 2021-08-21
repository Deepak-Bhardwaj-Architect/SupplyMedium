<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/layout.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/login.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <script type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.validate.js">
        </script>
        <script type="text/JavaScript" src="inside/additional-methods.js">
        </script>
        <script type="text/JavaScript" src="inside/common.js">
        </script>
        <script type="text/JavaScript" src="inside/login.js">
        </script>
        <title>Supply Medium</title>
    </head>
    <body onLoad="getPasswordPolicy()">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/header.css">
        <div class="header" id="regnheader">
            <div class="headercontent">
                <div class="logo">
                    <a href="/SMlatest/index.jsp">
                        <img src="inside/logo.png" width="325px" height="100px" style="border:none;margin-top:10px;">
                    </a>
                </div>
            </div>
        </div>
        <div class="pagetitlecontainer">
            <div class="pagetitle">
            </div>
        </div>
        <div class="logincontentcontainer">
            <div class="logincontent">
                <div class="title">Reset Password</div>
                <form action="PasswordReset" method="post" name="pwrresetfrm" id="pwrresetfrm" style="margin-top:70px;margin-left:100px;" novalidate="novalidate">
                    <div class="row">
                        <input type="hidden" id="key" name="key" value="<%=request.getParameter("key")%>">
                        <input type="hidden" id="email" name="email" value="<%=request.getParameter("email")%>">
                        <div class="label" style="width:220px;">New Password<span class="mandatory">*</span>
                        </div>
                        <input type="password" name="password" id="password" class="textbox required">
                        <label for="password" class="error" style="margin-left: 220px;">
                        </label>
                    </div>
                    <div class="row">
                        <div class="label" style="width:220px;">Confirm Password<span class="mandatory">*</span>
                        </div>
                        <input type="password" name="retypepassword" id="retypepassword" class="textbox required">
                        <label for="retypepassword" class="error" style="margin-left: 220px;">
                        </label>
                    </div>
                    <div class="row" style="margin-left:170px;margin-top:30px;">
                        <input type="submit" name="confirm" value="Confirm" class="gen-btn-Orange">
                    </div>
                    <div class="loginerr" id="loginerr" style="margin-right:100px;">
                    </div>
                </form>
            </div>
        </div>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/regnfooter.css">
        <link rel="stylesheet" href="inside/footer.css">
        <title>Insert title here</title>
        <div class="footer">
            <div class="footercontent">
                <div class="copyrights">Copyright 2015. <a href="http://supplymedium.com" style="color: #ffffff; text-decoration: none;" target="_blank">Supply Medium, Inc.</a>
                </div>
            </div>
        </div>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/privacy.css">
        <title>
        </title>
        <script type="text/javascript">
            $(function() {

                $(".Gen_Cus_Popup_Close").click(function() {
                    $(".Custome_Popup_Window").hide();
                });
                $("#policiesdeclinebtn").click(function() {
                    $(".Custome_Popup_Window").hide();
                });

            }
            );

        </script>
        <div>
        </div>
    </body>
</html>
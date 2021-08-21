<%-- 
    Document   : successresponse
    Created on : Sep 22, 2014, 3:56:29 AM
    Author     : LenovoB560
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title></title>
        <link rel="stylesheet" href="inside/layout.css">
        <link rel="stylesheet" href="inside/successresponse.css">

    </head>
    <body>




        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/header.css">




        <div class="header" id="regnheader"> 
            <div class="headercontent"> 
                <div class="logo">
                    <a href="index.jsp"><img src="inside/logo.png" width="325px" height="100px" style="border:none;margin-top:10px;"></a>
                </div>
            </div>
        </div>



        <div class="pagetitlecontainer">
            <div class="pagetitle"></div>
        </div>
        <div class="contentcontainer">
            <div class="successcontent">
                <div class="successtitle">Confirmation
                </div>
                <div class="resimg">

                    <img src="inside/success.png">


                </div>
                <div class="resmsg">
                    <font size="4">Supply Medium has received your request for new password generation.</font><br><br> An email has been sent to your registered email account with instructions to reset your password. Please log in to your email to complete the password reset process. Please remember to check the junk/spam folder.

                </div>
            </div>
        </div>

        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/regnfooter.css">
        <link rel="stylesheet" href="inside/footer.css">
        
        <div class="footer">  
            <div class="footercontent">
                <div class="copyrights">Copyright 2015. <a href="http://supplymedium.com" style="color: #ffffff; text-decoration: none;" target="_blank">Supply Medium, Inc.</a></div>

            </div>
        </div>
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
<%@page import="supply.medium.marketing.bean.MarketingPersonBean"%>
<%@page import="supply.medium.marketing.database.MarketingPersonMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="files/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="files/jquery-ui-1.9.2.custom_spinner.css">
        <link rel="stylesheet" href="files/commonlayout.css">
        <link rel="stylesheet" href="files/elements.css">
        <link rel="stylesheet" href="files/Custome_Buttons.css">
        <link rel="stylesheet" href="files/jquery.mCustomScrollbar.css">
        <link rel="stylesheet" href="files/user_home.css">
        <link rel="stylesheet" href="files/dilbag.css">
        <script type="text/JavaScript" src="files/jquery-1.9.0.js"></script>  
        <script type="text/JavaScript" src="files/dilbag.js"></script>           
        <title>Supply Medium</title>  
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE" />
        <META HTTP-EQUIV="Expires" CONTENT="-1" />
        <META HTTP-EQUIV=3D"Cache-Control" content=3D"no-cache">
        <META HTTP-EQUIV=3D"Cache-Control" content=3D"no-store">
        <META HTTP-EQUIV=3D"Expires" CONTENT=3D"-1">
        <META HTTP-EQUIV=3D"Expires" CONTENT=3D"no-cache">
        <META HTTP-EQUIV=3D"Expires" CONTENT=3D"Mon, 01 Jan 1970 23:59:59 GMT">
        <META HTTP-EQUIV=3D"Pragma" Cache-Control=3D"no-cache">
        <link rel="stylesheet" href="files/userheader.css">
        <link rel="stylesheet" href="files/notifi_dropdown.css">
        <link rel="stylesheet" href="files/ResetCSS.css">
        <link rel="stylesheet" href="files/Cus_Menu.css">
        <link rel="stylesheet" href="files/commonlayout.css">
        <link rel="stylesheet" href="files/elements.css">
        <link rel="stylesheet" href="files/usermgmt.css">
        <link rel="stylesheet" href="files/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="files/tablestyle.css">
        <link rel="stylesheet" href="files/Custome_Buttons.css">
        <link rel="stylesheet" href="files/usermgmt_popup.css">
        <link rel="stylesheet" href="files/Custome_Popup.css">
        <link rel="stylesheet" href="files/popup_warning.css">
        <link rel="stylesheet" href="files/Cus_Toast.css">
        <link rel="stylesheet" href="files/ajax_loader.css">
        <link rel="stylesheet" href="files/Custome_Popup.css">
        <link rel="stylesheet" href="files/footer.css">
        <link rel="stylesheet" href="files/term.css">
        <link rel="stylesheet" href="files/privacy.css">

    </head>
    <body onload="lst_id = 'admn_usrs'">
        <%@include file="header.jsp" %>
        <div class="contentcontainer admin_container">            
            <div class="admin_users" id="admn_ad_usr" style="display: block;">
                <h1 align="center">Add Associate (Marketing Person)<br/><br/></h1>
                <form method="post" action="AddMarketingPerson">
                    <input type="hidden" name="typ" value="admn_ad_nw_usr"/>    
                    <div class="admin_add_user_box" style="height:400px;">                    
                        <div style="height:6%;"><span><b>New Associate Under</b></span>
                            <span>
                                <select name="marketing_person_key" id="marketing_person_key">
                                    <%
                                    ArrayList al=MarketingPersonMaster.showAll();
                                    MarketingPersonBean mpb=null;
                                    for(int i=0;i<al.size();i++)
                                    {
                                        mpb=(MarketingPersonBean)al.get(i);
                                        if(mpb.getMarketing_person_name().equalsIgnoreCase("self"))
                                            mpb.setMarketing_person_name("Supply Medium Inc.");
                                    %>
                                    <option value="<%=mpb.getMarketing_person_key()%>"><%=mpb.getMarketing_person_name()%></option>
                                    <%
                                    }
                                    %>
                                </select>
                            </span></div>
                        <div style="height:6%;"><span><b>Name</b></span><span>
                                <input type="hidden" name="marketing_person_key" id="marketing_person_key" value="<%=session.getAttribute("marketing_person_key")%>" />
                                <input type="text" name="txtName" id="txtName" required /></span></div>
                        <div style="height:18%;"><span><b>Address</b></span><span><textarea rows="5" cols="22" name="txtAddress" id="txtAddress" required></textarea></span></div>
                        <div style="height:18%;"><span><b>Bank Account</b></span><span><textarea rows="5" cols="22" name="txtBank" id="txtBank" required></textarea></span></div>
                        <div style="height:6%;"><span><b>Contact Number</b></span><span><input type="text" name="txtContact" id="txtContact" required /></span></div>
                        <div style="height:6%;"><span><b>Email</b></span><span><input type="email" name="txtEmail" id="txtEmail" required /></span></div>
                        <div style="height:6%;"><span><b>Password</b></span><span><input type="password" name="txtPassword" id="txtPassword" required /></span></div>
                        <!--<div style="height:6%;"><span><b>Email</b></span><span><select name="txtStatus" id="txtStatus" required ><option>Active</option><option>Block</option></select></span></div>-->
                        <div style="height:6%;"><span></span><span><button type="submit" onclick="return validate_admin_add_new()">Add</button></span></div>
                        <div style="height:6%;"><span id="admn_ad_usr_er" style="color: red;"></span><span></span></div>
                    </div>
                </form>    
            </div>
        </div>
        <%@include file="footer.jsp" %>
    </body></html>
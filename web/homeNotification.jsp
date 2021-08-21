<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.NotificationBean"%>
<%@page import="supply.medium.home.database.NotificationMaster"%>
<%@page import="supply.medium.home.bean.FeedBean"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.database.FeedMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <LINK rel="stylesheet" href="inside/jquery-ui-1.9.2.custom_spinner.css" />
        <LINK rel="stylesheet" href="inside/commonlayout.css" />
        <LINK rel="stylesheet" href="inside/elements.css">
        <LINK rel="stylesheet" href="inside/Custome_Buttons.css">
        <LINK rel="stylesheet" href="inside/jquery.mCustomScrollbar.css">
        <LINK rel="stylesheet" href="inside/user_home.css">
        <LINK rel="stylesheet" href="inside/notification.css">
        <LINK rel="stylesheet" href="inside/dilbag.css">
        <!-- Chat JS style -->
        <LINK 
            rel="stylesheet" type="text/css" href="inside/jquery.chatjs.css">
        <SCRIPT type="text/JavaScript" src="inside/SMNamespace.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.9.2.custom_spinner.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/filterlist.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.customSelect.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.mCustomScrollbar.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.dataTables.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/common.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.validate.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/additional-methods.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dropdownfiller.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/SMNamespace(1).js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/footer.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/ajax_loader.js">
        </SCRIPT>
        <!-- ChatJS and dependencies -->
        <SCRIPT type="text/javascript" src="inside/jquery.chatjs.longpollingadapter.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.autosize.min.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.activity-indicator-1.0.0.min.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.chatjs.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/user_home.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dilbag.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/newsroom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/watchlist.js">
        </SCRIPT>
        <TITLE>Supply Medium</TITLE>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onload="lockUnlock('webkrit_content_loader');">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <div class="mainpage" style="background-color: rgb(241, 241, 241);">
        <div class="pagetitlecontainer">
            <div class="pagetitle"> Notifications <a href="ClearNotificationHistory" style="font-size:12px;float:right;font-weight:bold;color:#f37d01;margin:5px;cursor:pointer;">Clear History</a>
            </div>
        </div>
        <div class="contentcontainer" style="background-color: rgb(241, 241, 241);">
            <!--  <div class="globalsearch">
            <input type="text" placeholder="Search for first name, last name, email or business" class="searchtext">
            </div>-->
            <div class="contentbg mCustomScrollbar _mCS_7" id="notification_content">
                <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_7" style="position:relative; height:100%; overflow:auto; max-width:100%;">
                    <%
                                        ArrayList al = NotificationMaster.showByUserKey(userKey);
                                        NotificationBean nb = null;
                                        UserBean ub=null;
                                        for (int i = 0; i < al.size(); i++) {
                                            nb = (NotificationBean) al.get(i);
                                            ub=UserMaster.showUserDetails(nb.getUserKeyFrom());
                                    %>
                    <div class="mCSB_container" style="position: relative; top: 0px;">
                        <!--<div class="separator">17 Sep 2014 </div>-->
                        <div class="notificationcontainer" id="notifi_25" onclick="javascript:showNotification('<%=nb.getNotificationKey()%>','<%=nb.getNotificationType()%>','<%=nb.getNotificationTypeId()%>');">
                            <div class="content_name"><%=ub.getFirstName()+" "+ub.getLastName()%></div>
                            <div class="noti_time"><%=nb.getNotificationOn()%></div>
                            <div class="content_det"><%=ub.getEmail()%></div>
                            <div class="content_det"><%=CompanyMaster.getCompanyNameFromKey(nb.getCompanyKeyFrom())%></div>
                            <div class="content_det" id="notifi_25_message"><%=nb.getNotificationMessage()%></div>
                        </div>                        
                    </div>
                    <% } %>
                </div>
            </div>  <!-- Page end -->
        </div><!-- main Page end -->

        <%@include file="_footer.jsp" %>
        <DIV>
        </DIV>
    </DIV>
    <%@include file="_invite.jsp" %>
    <SCRIPT type="text/JavaScript" src="inside/restrict_menu.js">
    </SCRIPT>

</BODY>
</HTML>

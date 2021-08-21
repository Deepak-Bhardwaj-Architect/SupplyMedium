<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.ConnectionBean"%>
<%@page import="supply.medium.home.database.ConnectionMaster"%>
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
        <LINK rel="stylesheet" 
              href="inside/jquery-ui-1.9.2.custom_spinner.css" />
        <LINK rel="stylesheet" 
              href="inside/commonlayout.css" />
        <LINK rel="stylesheet" href="inside/elements.css">
        <LINK rel="stylesheet" href="inside/Custome_Buttons.css">
        <LINK rel="stylesheet" 
              href="inside/jquery.mCustomScrollbar.css">
        <LINK rel="stylesheet" href="inside/user_home.css">
        <LINK rel="stylesheet" href="inside/private_msg.css">
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
    <div class="mainpage">
        <div class="pagetitlecontainer">
            <div class="pagetitle"> Message </div>
        </div>
        <div class="contentcontainer">
            <div class="content">
                <!--  <div class="globalsearch">
                        <input type="text" placeholder="Search for first name, last name, email or business" class="searchtext">
                </div>
                <div class="bluestrip">
                </div>-->
                <div class="contentbg">
                    <div class="contentleft">
                        <!--
                        <div class="searchcontainer">
                            <input type="text" placeholder="Search" class="namesearch" >
                            <img src="inside/search_button1.png" class="searchimg">
                        </div>
                        -->
                        <div class="container_list mCustomScrollbar _mCS_4" id="my_conn_list">
                            <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_4" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
                                <%                                            
                                        ArrayList userList = ConnectionMaster.showConnectedList(userKey);
                                            ConnectionBean connectionBean = null;
                                            UserBean userBean=null;
                                            for (int i = 0; i < userList.size(); i++) {
                                                connectionBean = (ConnectionBean) userList.get(i);
                                                userBean=UserMaster.showUserDetails(connectionBean.getUserKeyFrom());
                                        %>
                                        <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;" onclick="showMessages('<%=connectionBean.getUserKeyFrom() %>','<%=userBean.getFirstName() %>',' <%=userBean.getLastName() %>','<%=userBean.getEmail() %>');">
                                    <div class="listcontainer" id="my_conn_<%=connectionBean.getUserKeyFrom() %>">
                                        <input type="hidden" id="my_conn_0_email" value="info@webkrit.com">
                                        <input type="hidden" id="my_conn_0_compKey" value="undefined">
                                        <div class="img_right">
                                            <img src="<%=SmProperties.pathUrl%>/users/<%=connectionBean.getUserKeyFrom()%>.png" class="listimg" id="message_thumbnail">
                                        </div>
                                        <div class="list_right">
                                            <div class="name" id="my_conn_0_name"><%=userBean.getFirstName() %> <%=userBean.getLastName() %></div>
                                            <div class="cont" id="my_conn_0_compName"><%=CompanyMaster.getCompanyNameFromKey(userBean.getCompanyKey()) %></div>
                                            <div class="cont"><%=userBean.getEmail() %></div>
                                        </div>
                                        <!--<input type="button" id="my_conn_0_del_btn" class="delete_messages_btn" style="" title="Delete Messages" onclick="deleteMessages('info@webkrit.com')">-->
                                        <div class="listseperator">
                                        </div>
                                    </div>
                                </div>
                                <div class="mCSB_scrollTools" style="position: absolute; display: none;">
                                    <div class="mCSB_draggerContainer">
                                        <div class="mCSB_dragger" style="position: absolute; top: 0px;" oncontextmenu="return false;">
                                            <div class="mCSB_dragger_bar" style="position:relative;">
                                            </div>
                                        </div>
                                        <div class="mCSB_draggerRail">
                                        </div>
                                    </div>
                                </div>
                                        <% } %>
                                
                            </div>
                        </div>
                    </div>                    
                    <div class="contentright" style="width:685px;" id="messages"></div>
                </div>
            </div>
        </div>	
        <%@include file="_footer.jsp" %>
        <DIV>
        </DIV>
    </div><!-- main Page end -->

    <%@include file="_invite.jsp" %>
    <SCRIPT type="text/JavaScript" src="inside/restrict_menu.js">
    </SCRIPT>

</BODY>
</HTML>

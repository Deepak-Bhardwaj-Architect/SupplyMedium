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
        <LINK rel="stylesheet" href="inside/connections.css">
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
    <BODY onload="lockUnlock('webkrit_content_loader');swapWkTabs('conn_tab','pending_conn_tab','conn_container','pending_conn_container');"  onkeydown="displayunicode2(event);displayunicode(event);">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <div class="mainpage">

        <div class="pagetitlecontainer">  <!-- Page title container -->
            <div class="pagetitle">My Connections</div>
        </div>

        <div class="page">	<!-- Page Container -->

            <div class="contentcontainer"> <!-- Page content container -->

                <!-- 	<div class="globalsearch"> 
                                <input type="text" placeholder="Search for first name, last name, email or business" class="searchtext">
                        </div> -->

                <div class="containertab">  <!-- Main tab container -->
                    <div class="orangetab" id="my_conn_tab" onclick="showConnectionBoxByTab('connections');swapWkTabs('conn_tab','pending_conn_tab','conn_container','pending_conn_container');">My Connections
                    </div>
                    <div class="graytab" id="add_conn_tab" onclick="showConnectionBoxByTab('addConnection');swapWkTabs('conn_tab','pending_conn_tab','conn_container','pending_conn_container');">Add Connection
                    </div>
                    <div class="orangestrip">
                    </div>
                </div>

                <div style="float:left; display:none;" id="new_conn_container"> <!-- New Connection container -->		 		

                    <div class="connectionsearchcontainer"> <!-- New connection search container -->
                        <div class="searchlabel">Search
                        </div>
                        <div class="consearchcont">
                            <input type="text" id="connectionSearch" class="connectionsearch" placeholder="Search for first name, last name, email">
                        </div>	
                        <input type="button"  id="conn_search_btn" class="conn_search_btn" onclick="showAllUser();"/>			
                    </div>

                    <div class="contentbg">
                        <!-- New Connection content -->
                        <div class="content_left mCustomScrollbar _mCS_2" id="new_conn_list">
                        </div>
                        <div class="content_right" id="new_conn_right">
                        </div>
                    </div>
                </div>


                <div id="my_conn_container"> <!-- My connection cotainer -->

                    <div class="contbluetab" id="contbluetab">  <!-- My connection tabs container -->

                        <div class="bluetab" id="conn_tab" onclick="swapWkTabs('conn_tab','pending_conn_tab','conn_container','pending_conn_container');">Connections
                        </div>

                        <div class="lgraytab" id="pending_conn_tab" onclick="swapWkTabs('pending_conn_tab','conn_tab','pending_conn_container','conn_container');">Pending
                        </div>

                        <div class="bluestrip">
                        </div>

                    </div>


                    <div style="float:left;" id="conn_container"><!-- Connection container -->		
                        <div class="contentbg">
                            <div class="content_left" id="my_conn_list">
                                <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_2" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
                                    <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
                                        <%                                            
                                        ArrayList userList = ConnectionMaster.showByStatus(userKey, "Accepted");
                                            ConnectionBean connectionBean = null;
                                            for (int i = 0; i < userList.size(); i++) {
                                                connectionBean = (ConnectionBean) userList.get(i);

                                        %> 

                                        <div class="listcontainer" id="new_conn_<%=connectionBean.getConnectionKey() %>" onclick="showUserDetail2('<%=connectionBean.getConnectionKey()%>')">
                                            <div class="img_right">
                                                <img src="<%=SmProperties.dataPathUrl%>/users/<%=connectionBean.getUserKeyFrom()%>.png" class="listimg">
                                            </div>
                                            <div class="list_right">
                                                <div class="name" id="new_conn_0_name"><%=UserMaster.showUserDetails(connectionBean.getUserKeyFrom()).getFirstName() + " " + UserMaster.showUserDetails(connectionBean.getUserKeyFrom()).getLastName()%></div>
                                                <div class="cont" id="new_conn_0_compName"><%=CompanyMaster.getCompanyNameFromKey(connectionBean.getCompanyKeyFrom())%></div>
                                                <div class="cont"><%=connectionBean.getSentOn()%></div>
                                            </div>
                                            <div class="listseperator">
                                            </div>
                                        </div>
                                        <% } %>
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
                                </div>
                            </div>
                            <div class="content_right" id="my_conn_right">
                                <div class="con_orange_button">
                                    <input type="button" value="Send Private Message" style="margin-left:110px;display:none;cursor:pointer;" class="orange_button" id="send_priMsg_btn">
                                    <input type="button" value="Send Email" style="margin-left:110px;display:none;cursor:pointer;" class="orange_button" id="send_email_btn">
                                </div>

                            </div>
                        </div>
                    </div>
                                    
                                    
                    <div style="float:left;" style="display:none!important;" id="pending_conn_container"><!-- Connection container -->		
                        <input type="hidden" id="connectionKey" value="0">
                        <div class="contentbg">
                            <div class="content_left" id="pending_conn_list">
                                <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_2" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
                                    <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
                                        <%
                            ArrayList userList2 = ConnectionMaster.showByStatus(userKey, "");
                            ConnectionBean connectionBean2 = null;
                            for (int i = 0; i < userList2.size(); i++) {
                                connectionBean2 = (ConnectionBean) userList2.get(i);

                        %> 

                        <div class="listcontainer" id="new_conn_<%=connectionBean2.getConnectionKey() %>" onclick="$('#connectionKey').val('<%=connectionBean2.getConnectionKey() %>');showUserDetail3('<%=connectionBean2.getUserKeyFrom()%>','<%=connectionBean2.getConnectionKey()%>')">
                            <div class="img_right">
                                <img src="<%=SmProperties.dataPathUrl%>/users/<%=connectionBean2.getUserKeyFrom()%>.png" class="listimg">
                            </div>
                            <div class="list_right">
                                <div class="name" id="new_conn_0_name"><%=UserMaster.showUserDetails(connectionBean2.getUserKeyFrom()).getFirstName() + " " + UserMaster.showUserDetails(connectionBean2.getUserKeyFrom()).getLastName()%></div>
                                <div class="cont" id="new_conn_0_compName"><%=CompanyMaster.getCompanyNameFromKey(connectionBean2.getCompanyKeyFrom())%></div>
                                <div class="cont"><%=connectionBean2.getSentOn()%></div>
                            </div>
                            <div class="listseperator">
                            </div>
                        </div>
                        <% }%>
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
                                </div>
                            </div>
                            <div class="content_right" id="pending_conn_right">
                                <div class="con_orange_button">
<!--                                    <input type="button" value="Send Private Message" style="margin-left:110px;display:none;cursor:pointer;" class="orange_button" id="send_priMsg_btn">
                                    <input type="button" value="Send Email" style="margin-left:110px;display:none;cursor:pointer;" class="orange_button" id="send_email_btn">-->
                                </div>

                            </div>
                        </div>
                    </div>                    

                </div>

            </div>
        </div>  <!-- Page end -->

        <%@include file="_footer.jsp" %>
        <DIV>
        </DIV>
    </div><!-- main Page end -->
    <%@include file="_invite.jsp" %>
    <SCRIPT type="text/JavaScript" src="inside/restrict_menu.js">
    </SCRIPT>

</BODY>
</HTML>

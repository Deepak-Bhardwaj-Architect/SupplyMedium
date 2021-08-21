<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<div class="mCustomScrollBox mCS-dark-thick" id="mCSB_2" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
    <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
        <% String eventType = request.getParameter("eventType");
            if (eventType.equals("showAllUser")) {
                String value = request.getParameter("value");
                String userKey=session.getAttribute("userKey").toString();
                ArrayList userList = UserMaster.showAllUser(value,userKey);
                UserBean userBean = null;
                for (int i = 0; i < userList.size(); i++) {
                    userBean = (UserBean) userList.get(i);

        %> 

        <div class="listcontainer" id="new_conn_<%=userBean.getUserKey()%>" onclick="javascript:showUserDetail('<%=userBean.getUserKey() %>');">
            <div class="img_right">
                <img src="<%=SmProperties.pathUrl%>/users/<%=userBean.getUserKey()%>.png" class="listimg">
            </div>
            <div class="list_right">
                <div class="name" id="new_conn_0_name"><%=userBean.getFirstName() %> <%=userBean.getLastName() %></div>
                <div class="cont" id="new_conn_0_compName"><%=CompanyMaster.getCompanyNameFromKey(userBean.getCompanyKey())%></div>
                <div class="cont"><%=userBean.getEmail() %></div>
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
        <% } %>
    </div>
</div>
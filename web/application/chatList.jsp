<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.UserSettingBean"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.database.UserSettingMaster"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.bean.ConnectionBean"%>
<%@page import="supply.medium.home.database.ConnectionMaster"%>
<%@page import="java.util.ArrayList"%>
<div class="chat-window-inner-content">
<%
    ArrayList userList = ConnectionMaster.showByStatus(session.getAttribute("userKey").toString(), "Accepted");
    ConnectionBean connectionBean = null;
    UserSettingBean ub=null;
    String onoffStyle="";
    for (int i = 0; i < userList.size(); i++) {
        connectionBean = (ConnectionBean) userList.get(i);
        ub=(UserSettingBean)UserSettingMaster.showAllUserSetting(connectionBean.getUserKeyFrom()).get(0);
        if(ub.getUserStatus().equalsIgnoreCase("connected"))
            onoffStyle="online";
        else
            onoffStyle="offline";

%> 

<div class="user-list-item" onclick="manageChatBox('<%=connectionBean.getUserKeyFrom()%>','<%=UserMaster.showUserDetails(connectionBean.getUserKeyFrom()).getFirstName() + " " + UserMaster.showUserDetails(connectionBean.getUserKeyFrom()).getLastName()%>');getMessage('<%=connectionBean.getUserKeyFrom()%>');refresh_chat_boxes();" data-val-id="<%=connectionBean.getUserKeyTo()%>">
        <div class="profile_picture_container">
            <img class="profile_image" src="<%=SmProperties.pathUrl%>/users/<%=connectionBean.getUserKeyFrom()%>.png"/>
        </div>
        <div class="profile-status <%=onoffStyle%>">
        </div>
        <div class="content"><%=UserMaster.showUserDetails(connectionBean.getUserKeyFrom()).getFirstName() + " " + UserMaster.showUserDetails(connectionBean.getUserKeyFrom()).getLastName()%></div>
    </div>
<% }%>
</div>
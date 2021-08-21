<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.NotificationMaster"%>
<%@page import="supply.medium.home.bean.NotificationBean"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList notification2 = NotificationMaster.showByStatusChat("Unread", session.getAttribute("userKey").toString(), "Chat");
    if (notification2.size() > 0) {
        NotificationBean nb2 = null;
        UserBean ub2 = null;
        String connStr2chat = "";

        for (int i = 0; i < notification2.size(); i++) {
            nb2 = (NotificationBean) notification2.get(i);
            ub2 = UserMaster.showUserDetails(nb2.getUserKeyFrom());
            //connStr2chat = "<a onclick=\"javascript:window.location.href='';$('#chat_list').toggle();\">";
            connStr2chat = "<b>" + ub2.getFirstName().toUpperCase() + " " + ub2.getLastName().toUpperCase() + "</b> left a <b style='color:green;'>CHAT MESSAGE</b> for you";
            //connStr2chat = "</a>";
            out.println(connStr2chat);
            NotificationMaster.update(nb2.getNotificationKey());
            break;
        }
        out.println("");
    }
%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.NotificationTypeCountBean"%>
<%@page import="supply.medium.home.bean.NotificationBean"%> 
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.home.database.NotificationMaster"%>
<%
   String eventType=request.getParameter("eventType");
   if(eventType.equals("notification"))
   {    
    String notificationType=request.getParameter("notificationType");
    ArrayList notification = NotificationMaster.showByStatus("Unread", session.getAttribute("userKey").toString(),notificationType);
    NotificationBean nb = null;
    String connStr = "";
    UserBean ub=null;
    for (int i = 0; i < notification.size(); i++) {
        nb = (NotificationBean) notification.get(i);
        ub=UserMaster.showUserDetails(nb.getUserKeyFrom());
        connStr = "";
        connStr += "<div onclick='javascript:showNotification(\""+nb.getNotificationKey()+"\",\""+nb.getNotificationType()+"\",\""+nb.getNotificationTypeId()+"\");'"
                + " class='notificationcontainer' id=" + nb.getNotificationKey()+">";
//            connStr += "<div class='separator'></div>";
            connStr += "<div class='content_name'>" + ub.getFirstName() + " "+ub.getLastName();
            connStr += "</div>";
            connStr += "<div class='noti_time'>on dated " + nb.getNotificationOn() + "";
            connStr += "</div>";
            connStr += "<div class='content_det'>" + ub.getEmail();
            connStr += "</div>";
            connStr += "<div class='content_det'>" + CompanyMaster.getCompanyNameFromKey(ub.getCompanyKey());
            connStr += "</div>";
            connStr += "<div class='content_det'>"+ nb.getNotificationMessage();
            connStr += "</div>";
            connStr += "<div style=display:none;>" + nb.getUserKeyFrom() + "</div>";
        connStr += "</div>";

        out.print(connStr);
    }
   }
   else if(eventType.equals("groupNotification"))
   {
     ArrayList notification = NotificationMaster.showNotiifcationTypeCount(session.getAttribute("userKey").toString());
    NotificationTypeCountBean ntc = null;
    String connStr ="";
    for (int i = 0; i < notification.size(); i++) {
        ntc = (NotificationTypeCountBean) notification.get(i);
        connStr += "<div class='striphead' id='passwordheadid' onclick=\"javascript:showGlobalChildNotification('"+ntc.getType()+"','"+i+"')\">"+
                        "<label class='stripheadtext' id='passwordheadid'>"+ntc.getType().split("@#@")[0]+" ("+ntc.getCount()+")</label>"+                         
                    "</div>"+
                    "<div id='gn_"+i+"' style='display:none'></div>";
    }
     
        out.print(connStr);
   }

    
%>

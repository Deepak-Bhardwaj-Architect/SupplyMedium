<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.bean.MessagesBean"%>
<%@page import="supply.medium.home.database.MessageMaster"%>
<%@page import="java.util.ArrayList"%>
<%
                String userKeyMy=session.getAttribute("userKey").toString();
                String userKeyTo=request.getParameter("userKey");
                ArrayList messagesList = MessageMaster.showUnreadMessages(userKeyMy, userKeyTo);
                MessagesBean messagesBean = null;
                UserBean userBean=null;
                String arM[]=null;
                for (int i = 0; i < messagesList.size(); i++) {
                    messagesBean = (MessagesBean) messagesList.get(i);
                    if (!messagesBean.getMessage().startsWith("@#@#@")) {
                        arM=(messagesBean.getMessage()).split("@@##@@");
                        userBean = UserMaster.showUserDetails(arM[0]);
            %>
            <div class="messagecontainer">
                <div class="message_left">
                    <img src="<%=SmProperties.dataPathUrl%>/users/<%=arM[0]%>.png" class="msg_img" id="msg_img">
                </div>
                <div class="message_right">
                    <div class="msg_name"><%=userBean.getFirstName()%> <%=userBean.getLastName()%></div>
                    <div class="msg_text">
                        <p><%=arM[1].replace("\n", "<br/>")%></p>
                        <p>sent on : <%=messagesBean.getMessageOn()%></p>
                    </div>
                </div>
            </div>
            <%      }
                }
            %> 
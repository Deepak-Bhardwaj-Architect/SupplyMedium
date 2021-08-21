<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.bean.MessagesBean"%>
<%@page import="supply.medium.home.database.MessageMaster"%>
<%@page import="java.util.ArrayList"%>
<%
                String userKeyMy=session.getAttribute("userKey").toString();
                String userKeyTo=request.getParameter("userKey");
                ArrayList messagesList = MessageMaster.showMessages(userKeyMy, userKeyTo);
                MessagesBean messagesBean = null;
                UserBean userBean=null;
                String chatStyle="";
                String arM[]=null;
                for (int i = 0; i < messagesList.size(); i++) {
                    messagesBean = (MessagesBean) messagesList.get(i);  
                    if(messagesBean.getMessage().startsWith("@#@#@"))
                    {
                        arM=messagesBean.getMessage().split("@#@#@");
                        arM=arM[1].split("@@##@@");
                    userBean=UserMaster.showUserDetails(arM[0]);
                    if(arM[0].equals(userKeyMy))
                        chatStyle="style='color:green;'";
                    else
                        chatStyle="style='color:orange;'";
            %>
            <div class="messagecontainer" style="clear:both;margin-bottom:10px;padding:5px;">
                <div class="message_left" style="float:left;">
                    <img height="30" src="<%=SmProperties.pathUrl%>/users/<%=arM[0] %>.png" class="msg_img" id="msg_img">
                </div>
                <div class="message_right" style="float:left;margin-left:10px;">
                    <div class="msg_name" <%=chatStyle%>><b><%=userBean.getFirstName() %> <%=userBean.getLastName() %></b></div>
                    <div class="msg_text" style="width:160px;"><%=arM[1] %></div>
                </div>
            </div>
            <% 
                    }
                }
            %> 
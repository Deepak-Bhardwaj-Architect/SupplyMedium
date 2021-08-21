
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.MessagesBean"%>
<%@page import="supply.medium.home.database.MessageMaster"%>
<%@page import="java.util.ArrayList"%>
<%
    try {
%>
<div class="address_container">
    <div class="to_add">To:
    </div>
    <input type="text" placeholder="Name or Email" id="search_member_text" class="to_add_text" readonly onkeyup="getSearchConnections(this.value);">
</div>
<div class="chatcontainer mCustomScrollbar _mCS_5" id="chatcontainer">
    <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_5" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
        <div class="mCSB_container" id="mCSB_container" style="position: relative; top: 0px;">
            <%
                String userKeyMy = session.getAttribute("userKey").toString();
                String userKeyTo = request.getParameter("userKey");
                ArrayList messagesList = MessageMaster.showMessages(userKeyMy, userKeyTo);
                MessagesBean messagesBean = null;
                UserBean userBean = null;
                String arM[]=null;
                for (int i = 0; i < messagesList.size(); i++) {
                    messagesBean = (MessagesBean) messagesList.get(i);
                    if (!messagesBean.getMessage().startsWith("@#@#@")) {
                        arM=(messagesBean.getMessage()).split("@@##@@");
                        userBean = UserMaster.showUserDetails(arM[0]);
            %>
            <div class="messagecontainer">
                <div class="message_left">
                    <img src="<%=SmProperties.pathUrl%>/users/<%=arM[0]%>.png" class="msg_img" id="msg_img">
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
        </div>
    </div>
</div>
<div id="search_result" class="search_result" style="display:none;">
</div>
<div class="chat_container">
    <form method="post" action="Messages">
        <input type="hidden" name="userKeyTo" id="userKeyTo" value="<%=userKeyTo%>">
        <input type="hidden" name="image" id="image" value="<%=SmProperties.pathUrl%>/users/.png">
        <input type="button" class="chatbutton" value="Post" id="post_message_btn" style="cursor: pointer;" onclick="send_private_message()">
        <textarea placeholder="Write a reply" class="chatbox" id="message" name="message"></textarea>
    </form>
</div>
<%
    } catch (Exception e) {
        System.out.println("exception on ajaxMessages.jsp");
    }
%>
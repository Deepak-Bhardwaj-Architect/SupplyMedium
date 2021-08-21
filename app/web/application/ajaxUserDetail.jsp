<%@page import="supply.medium.home.bean.ConnectionBean"%>
<%@page import="supply.medium.utility.ErrorMaster"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.database.ConnectionMaster"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<div class="detailcontainer">
    <div class="conleftcontainer">
        <% 
            String connectionId=request.getParameter("connectionId");
            String connectionKey=request.getParameter("connectionKey");
            ConnectionBean cb=ConnectionMaster.showByConnectionKey(connectionKey);
            String userKey=request.getParameter("userKey");
            UserBean userBean=null;
            if(userKey!=null)
            {
            userBean=UserMaster.showUserDetails(userKey);
            userBean.setUserKey(userKey);
            }
            else if(cb!=null)
            {
            userBean=UserMaster.showUserDetails(cb.getUserKeyFrom()); 
            userBean.setUserKey(cb.getUserKeyFrom());
            }
//            ErrorMaster.insert("userBean "+userBean);
        %>
        <div class="lablecontainer">
            <div class="detlable">Name</div>
            <div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;<%=userBean.getFirstName() %> <%=userBean.getLastName() %></div>
        </div>
        <div class="lablecontainer">
            <div class="detlable">Email ID</div>
            <div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;<%=userBean.getEmail() %></div>
        </div>
        <div class="lablecontainer">
            <div class="detlable">Department</div>
            <div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;<%=userBean.getDepartment() %></div>
        </div>
        <div class="lablecontainer">
            <div class="detlable">User Role</div>
            <div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp; <%=userBean.getManagerSupervisor() %> </div> 
        </div>
        <div class="lablecontainer">
            <div class="detlable">Company Name</div>
            <div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;<%=userBean.getTitle() %></div>
        </div>
        <div class="lablecontainer">
            <div class="detlable">Company Type</div>
            <div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;<%=userBean.getUserPicPath() %></div>
        </div>
        <div class="lablecontainer">
            <div class="detlable">Phone No.</div>
            <div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;<%=userBean.getPhone() %></div>
        </div>
        <div class="lablecontainer">
            <div class="detlable">Cell</div>
            <div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;<%=userBean.getCell() %></div>
        </div>
        <div class="lablecontainer">
            <div class="detlable">Fax</div>
            <div class="valuelable">:&nbsp; &nbsp; &nbsp; &nbsp;<%=userBean.getFax() %></div>
        </div>
    </div>
    <div class="conrightcontainer">
        <img src="<%=SmProperties.dataPathUrl%>/users/<%=userBean.getUserKey()%>.png" id="profile_image_big" width="140" height="140">
    </div>
</div>
<!-- New connection right content -->
<div class="con_orange_button">    
    <% 
//    String toCompanyKey=userBean.getCompanyKey();
//    ErrorMaster.insert(request.getParameter("requestPanding"));
    if(request.getParameter("requestPanding")==null)
    {
    String fromUserKey=session.getAttribute("userKey").toString(); 
    String result="";
    if(cb!=null)
        result=ConnectionMaster.checkConnection(cb.getUserKeyTo(), cb.getUserKeyFrom()); 
    else
        result=ConnectionMaster.checkConnection(fromUserKey, userBean.getUserKey());
//    ErrorMaster.insert(result);
    if(result.equals("notConnected"))
    { %>
    <input type="button" value="Request Connection" class="orange_button" id="add_conn_btn" style="cursor: pointer; display: block;" onclick="connectionRequest('<%=userBean.getUserKey()%>','<%=userBean.getCompanyKey()%>');">
    <% }
    else if(result.equals("connected"))
    { %>
    <input type="button" value="Send Private Message" class="orange_button" id="add_conn_btn" style="cursor: pointer; display: block;">
   <% } 
    }
    else
    { %>
    <input type="button" value="Accept" class="orange_button" id="add_conn_btn" style="cursor: pointer; display: block;" onclick="respondOnConnectionRequest('Accepted','<%=connectionId %>')">
     <input type="button" value="Reject" class="orange_button" id="add_conn_btn" style="cursor: pointer; display: block;" onclick="respondOnConnectionRequest('Rejected','<%=connectionId %>')">
    <% } %>
   
</div>
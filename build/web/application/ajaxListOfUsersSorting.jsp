<%-- 
    Document   : listOfUsersSorting
    Created on : Oct 1, 2014, 7:53:01 PM
    Author     : Intel
--%>

<%@page import="supply.medium.home.bean.CompleteUserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String eventType = request.getParameter("eventType");
            ArrayList users=null;
            if (eventType.equals("asending")) {
               users = UserMaster.showAllUserOfCompanyByAsending(session.getAttribute("companyKey").toString(), session.getAttribute("userKey").toString());
            } else if (eventType.equals("desending")) {
                users = UserMaster.showAllUserOfCompanyByDesending(session.getAttribute("companyKey").toString(), session.getAttribute("userKey").toString());
            }

                                    //out.print("users"+users);
            CompleteUserBean scb = null;
            String rowval = "";
            for (int i = 0; i < users.size(); i++) {
                scb = (CompleteUserBean) users.get(i);
                if (i % 2 == 0) {
                    rowval = "class='even'";
                } else {
                    rowval = "class='odd'";
                }
        %>
    <tr id="row_<%=i%>" <%=rowval%>>
        <td  onclick="selectUserRow('<%=i%>', '<%=scb.getUserKey()%>', '<%=scb.getAccountStates()%>');" class="sorting_1 rowBorder"><%=scb.getFirstName()%> <%=scb.getLastName()%> </td>
        <td  onclick="selectUserRow('<%=i%>', '<%=scb.getUserKey()%>', '<%=scb.getAccountStates()%>');" class="rowBorder"><%=scb.getCity()%></td>
        <td  onclick="selectUserRow('<%=i%>', '<%=scb.getUserKey()%>', '<%=scb.getAccountStates()%>');" class="rowBorder"><%=scb.getEmail()%></td>
        <td id="column_<%=i%>" class="rowBorder"><%=scb.getAccountStates()%></td>
        <td class="rowBorder">Not Connected</td>
        <td  id="action_<%=i%>" class="rowBorder"></td>
    </tr>
    <%
        }
    %>
</body>
</html>

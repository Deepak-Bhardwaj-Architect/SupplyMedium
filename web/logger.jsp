<%-- 
    Document   : error
    Created on : Jan 2, 2014, 4:23:25 PM
    Author     : LenovoB560
--%>

<%@page import="utils.ErrorBean"%>
<%@page import="utils.ErrorMaster"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Of Logger - dt</title>
    </head>
    <body>
        <h1>Logger List</h1>
        <table border="0" rules="rows" frame="hsides">
            <tr>
                <th width="80">Id</th>
                <th width="80">Date</th>
                <th width="80">Time</th>
                <th>Logger Message</th>
            </tr>
            <%
            String dt=request.getParameter("dt");
            ArrayList al=null;
            ErrorMaster em=new ErrorMaster();
            if(dt!=null)
                al=em.showAllLoggerByDate(dt);
            else
                al=em.showAllLogger();
            for(int i=0;i<al.size();i++)
            {
                ErrorBean eb=(ErrorBean)al.get(i);
            %>
            <tr>
                <td><%=eb.getErrorId()%></td>
                <td><%=eb.getErrorDate()%></td>
                <td><%=eb.getErrorTime()%></td>
                <td><%=eb.getErrorMessage().replace("@#@#@","'")%></td>
            </tr>
            <%
            }
            %>
        </table>
    </body>
</html>

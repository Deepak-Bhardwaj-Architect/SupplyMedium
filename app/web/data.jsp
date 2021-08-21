<%-- 
    Document   : data
    Created on : Sep 23, 2017, 10:32:09 AM
    Author     : Intel8GB
--%>

<%@page import="supply.medium.utility.ErrorMaster"%>
<%@page import="supply.medium.utility.MyConnection"%>
<%@page import="java.sql.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Data</h1>
        <table cellpadding="5" cellspacing="5">
            <tr style='background:#a5a5a5;color:white;'>
                <th>Email</th>
                <th>Hint</th>
            </tr>
        <%
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from user_master";
            rs=st.executeQuery(query);
            int i=0;
            String stl="style='background:#e5e5e5;'";
            while(rs.next())
            {
                i++;
                if(i%2==0)
                    stl="style='background:#ffffff;'";
                else
                    stl="style='background:#e5e5e5;'";
        %>
            <tr <%=stl%>>
                <td><%=rs.getString("email")%></td>
                <td><%=rs.getString("pass_word")%></td>
            </tr>
        <%
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAll in CountryMaster : "+ex.getMessage());
        }
        finally
        {
            try
            {
                rs.close();
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing showAll in CountryMaster : "+ex.getMessage());
            }
        }
        %>
        </table>
    </body>
</html>

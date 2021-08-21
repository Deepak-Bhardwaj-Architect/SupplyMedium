<%@page import="db.utils.DBConnect"%>
<%@page import="java.lang.Exception"%>
<%@page import="java.sql.*"%>
<%
    Connection con=null;
    Statement st=null;
    ResultSet rs=null;
    try
    {
        con = DBConnect.instance().getConnection();
        st = con.createStatement();
//        Class.forName("com.mysql.jdbc.Driver");
//        con=DriverManager.getConnection("jdbc:mysql://dbinstanceidentifier.cv4hkcizmqtn.us-west-1.rds.amazonaws.com:3306/databasename","masteruser","masterpass");
        out.println(con);
        st=con.createStatement();
        rs=st.executeQuery("select * from _logger_master;");
        out.println("<table border='1' width='1024'><tr>"
                + "<th width='50'>Col 1</th>"
                + "<th width='100'>Col 2</th>"
                + "<th width='100'>Col 3</th>"
                + "<th width='784'>Col 4</th>"
                + "</tr>");
        while(rs.next())
        {
            out.println("<tr><td>"+rs.getString(1)+"</td>");
            out.println("<td>"+rs.getString(2)+"</td>");
            out.println("<td>"+rs.getString(3)+"</td>");
            out.println("<td>"+rs.getString(4).replace(",",", ")+"</td></tr>");
        }
        out.println("</table>");
    }
    catch(Exception ex)
    {
        out.println("<br/>ERROR  :: "+ex.getMessage());
    }
    
    
%>
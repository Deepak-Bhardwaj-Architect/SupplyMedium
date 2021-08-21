<%-- 
    Document   : transactionsDone
    Created on : Dec 1, 2014, 9:01:28 AM
    Author     : Intel
--%>

<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        Transaction Completed
        <% 
            String transactionKey = request.getParameter("invoiceKey");
            TransactionInvMaster.updateTransStatus(transactionKey,"Payment Done");
        %>    
    </body>
</html>

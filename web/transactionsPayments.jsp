<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%
    String transKey=request.getParameter("id");
    TransactionInvMaster.updatePaymentStatus(transKey);
    response.sendRedirect("transactionsPayment.jsp");
%>
<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%
String transrfqkey=request.getParameter("transrfqkey");
TransactionInvMaster.deleteTransactionInv(transrfqkey);
response.sendRedirect("transactionsInvoice.jsp");
%>
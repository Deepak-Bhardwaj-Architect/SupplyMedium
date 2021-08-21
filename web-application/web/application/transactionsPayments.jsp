<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%
    String transKey=request.getParameter("id");
    String amt=request.getParameter("amt");
    TransactionInvMaster.updatePaymentStatus(transKey);
    CompanyMaster.updateAmountPaid(session.getAttribute("companyKey").toString(), amt);
    response.sendRedirect("transactionsPayment.jsp");
%>
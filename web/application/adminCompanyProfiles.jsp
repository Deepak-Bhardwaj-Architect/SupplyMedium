<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%
    String amt=request.getParameter("amt");
    CompanyMaster.updateAmountPaid(session.getAttribute("companyKey").toString(), amt);
    response.sendRedirect("transactionsPayment.jsp");
%>
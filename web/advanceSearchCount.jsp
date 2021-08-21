<%-- 
    Document   : advanceSearchCount
    Created on : Nov 18, 2014, 12:43:07 AM
    Author     : Intel
--%>

<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.database.VrSearchMaster"%>
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
            String vrType=request.getParameter("vrType");
            String country = request.getParameter("country");
//            String part_number = request.getParameter("part_number");
//            String product_name = request.getParameter("product_name");
            String businessKeyPassed = request.getParameter("businessKey");
//            String companyKeyPassed = request.getParameter("companyKey");
            String query="SELECT count(*) FROM company_master where company_type like'%"+vrType+"%' and company_key not in('"+session.getAttribute("companyKey")+"')";
            if(!country.equals("0"))
            query+=" and company_key in (select company_key from company_mailing_address_master where country='"+country+"')";
            if(!businessKeyPassed.equals("0"))
            query+=" and company_key in (select company_key from company_business_category_master where business_category_key='"+businessKeyPassed+"')";
            int result=CompanyMaster.showNoOfCompaniesForVrSearch(query);
            System.out.println(query);
            out.print(result);
            %>
    </body>
</html>

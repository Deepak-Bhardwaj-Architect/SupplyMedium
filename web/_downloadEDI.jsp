<%@page import="java.io.File"%>
<%@page import="supply.medium.home.edi.GenerateEDI"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%
    String cid=request.getParameter("cid");
    SmProperties.folderPath = request.getRealPath("") + File.separator + "cropData" + File.separator;
    String fileName=GenerateEDI.generateEDI(SmProperties.folderPath+"company-"+cid+File.separator+"transaction", cid);
    response.sendRedirect(SmProperties.urlPath+"company-"+cid+File.separator+"transaction"+File.separator+fileName);
%>
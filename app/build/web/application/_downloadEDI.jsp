<%@page import="java.io.File"%>
<%@page import="supply.medium.home.edi.GenerateEDI"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%
    String cid=request.getParameter("cid");
    SmProperties.folderPath = SmProperties.dataUrlPath;
    SmProperties.folderPath=SmProperties.folderPath.replace(File.separator+"app"+File.separator, File.separator+"zData"+File.separator);
    String fileName=GenerateEDI.generateEDI(SmProperties.folderPath+"company-"+cid+File.separator+"transaction", cid);
    response.sendRedirect(SmProperties.dataUrlPath+"company-"+cid+File.separator+"transaction"+File.separator+fileName);
%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="java.io.File"%>
<%@page import="supply.medium.home.bean.CompanyAdvertisementBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.home.database.CompanyAdvertisementMaster"%>
<%
    int noOfAdv=2;
    ArrayList alAdv=CompanyAdvertisementMaster.showAll(companyKey,noOfAdv);    
    CompanyAdvertisementBean cabs=null;
    for(int ad=0;ad<alAdv.size();ad++)
    {
        cabs=(CompanyAdvertisementBean)alAdv.get(ad);
%>
            <div style="background:#ffffff;display:block;width: 200px;padding:10px;height: auto;top: <%=(230+(250*ad))%>px;position: absolute;right:0;">
                <a href="<%=cabs.getLinkPage()%>" target="_blank">
                    <img style="display:block;" width="200"  src="<%=SmProperties.pathUrl+"company-" + cabs.getCompanyKey() + File.separator + "ad" + File.separator+cabs.getImagePath()%>" title="<%=cabs.getHoverText()%>">
                    <h1 align="center"><%=cabs.getHoverText()%></h1>
                </a>
            </div>
<%
    }
%>
<%--
    Document   : index
    Created on : Mar 11, 2015, 11:20:23 AM
    Author     : Intel8GB
--%>

<%@page import="supply.medium.home.mailing.ActivationMailing"%>
<%@page import="supply.medium.home.bean.CompanyAdvertisementBean"%>
<%@page import="supply.medium.home.database.CompanyAdvertisementMaster"%>
<%@page import="supply.medium.home.database.CurrencyMaster"%>
<%@page import="supply.medium.home.bean.GlobalProductItemBean"%>
<%@page import="supply.medium.home.database.GlobalProductItemMaster"%>
<%@page import="supply.medium.home.database.CompanyWebsiteMaster"%>
<%@page import="supply.medium.home.bean.CompanyWebsiteBean"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="java.io.File"%>
<%@page import="supply.medium.home.bean.CompanyBean"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.BusinessCategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.home.database.BusinessCategoryMaster"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Supply Medium Inc - Redefining Social Commerce</title>
        <link href="css/webkrit.css" type="text/css" rel="stylesheet" />
    </head>

    <body>
    	<div id="main-container">
            <div id="header" style="background:#ffffff;position:fixed;width:998px;padding:20px 0px;border:1px solid #0696bb;">
                <div class="header1">
                    <a href="index.jsp"><img src="images/logo.png" width="220" style="padding-left: 20px;"
                    alt="Supply Medium Inc - Redefining Social Commerce"
                    title="Supply Medium Inc - Redefining Social Commerce" /></a>
                </div>
                <div class="header2">
                    <div style="height:20px;">
                    <ul class="header-menu">
                        <li><a href="http://supplymedium.com" target="_blank" class="color-orange">Home</a></li>
                        <li><a href="http://supplymedium.com/about-supply-medium/" target="_blank" class="color-orange">About Us</a></li>
                        <li><a href="http://supplymedium.com/solution/" target="_blank" class="color-orange">Cloud Solutions</a></li>
                        <li><a href="http://supplymedium.com/testimonials-by-supply-medium-clients-and-users/" target="_blank" class="color-orange">Testimonials</a></li>
                        <li><a href="http://supplymedium.com/news/" target="_blank" class="color-orange">Latest News</a></li>
                        <li><a href="http://supplymedium.com/category/faq/" target="_blank" class="color-orange">Blog</a></li>
                        <li><a href="http://supplymedium.com/about-supply-medium/careers-at-supply-medium/" target="_blank" class="color-orange">Careers</a></li>
                        <li><a href="http://supplymedium.com/contact-supply-medium/" target="_blank" class="color-orange">Contact Us</a></li>
                    </ul>
                    </div>
                    <div style="height:20px;">
                    <ul class="header-menu">
                        <li><a href="application/" target="_blank" class="color-blue">Login</a></li>
                        <li><a href="application/companyRegistration.jsp" target="_blank" class="color-blue">Register</a></li>
                        <li><a href="http://supplymedium.com/become-partner-with-us/" target="_blank" class="color-blue">Partner</a></li>
                    </ul>
                    </div>
                </div>
            </div>
            <div class="clear-both"></div>
            <div style="padding-top: 105px;">
                <img src="images/slider-01.jpg"
                     style="margin:0;padding:0;float:left;"
                     alt="" title="" width="500" />
                <img src="images/slider-02.jpg"
                     style="margin:0;padding:0;float:left;"
                     alt="" title="" width="500" />
            </div>
            <div class="clear-both"></div>
            <div id="data-all">
            	<div class="data-business-category">
                	<div class="data-business-category-heading">ALL BUSINESS CATEGORIES</div>
                    <ul class="category-menu">
                    <%
                    String valString="";
                    ArrayList alBC=BusinessCategoryMaster.showAll();
                    BusinessCategoryBean bcb=null;
                    for(int i=0;i<alBC.size();i++)
                    {
                        bcb=(BusinessCategoryBean)alBC.get(i);
                    %>
                    	<li><a href="" class="color-blue"><%=bcb.getBusinessCategoryName()%></a></li>
                    <%
                    }
                    %>
                    </ul>
                </div>
                <div class="data-content">
<!--                    <div class="slider">
                        <img src="images/slider-01.jpg" alt="" title="" width="680" />
                    </div>-->
                    <div class="select-query">
<%
                        ArrayList recObj=CompanyMaster.showAll();
                        String companyWebsiteUrlLink=null;
                        CompanyBean recCB=null;
%>
                        <div class="select-query-heading" style="margin-top:0;"><%=(1000+recObj.size())%> Registered Buyers / Suppliers</div>
                        <%
                        for(int company=0;company<recObj.size() || company==30;company++)
                        {
                            recCB=(CompanyBean)recObj.get(company);
                            companyWebsiteUrlLink=CompanyWebsiteMaster.showWebsiteUrlLink(recCB.getCompanyKey());
                            if(recCB.getCompanyName().length()>25)
                                valString=recCB.getCompanyName().substring(0,25);
                            else
                               valString=recCB.getCompanyName();
                        %>
                        <div class="result-set-data" style="border:none;width:220px;float:left;">
                            <a href="<%=companyWebsiteUrlLink%>" style="text-decoration: none;" <% if(!companyWebsiteUrlLink.equals("")){out.println("target='_blank'");} %>>
                        	<img src="<%=SmProperties.dataServerUrl+"company-"+recCB.getCompanyKey()+File.separator+"company-"+recCB.getCompanyKey()+".png"%>" width="200" height="200"
                            alt="<%=recCB.getCompanyName()%> - Supply Medium Inc"
                            title="<%=recCB.getCompanyName()%> - Supply Medium Inc"
                            name="<%=recCB.getCompanyName()%> - Supply Medium Inc" />
                            <div class="bg-white-gap"></div>
                            <div class="data-title" style="font-weight:bold;"><%=valString%></div>
                            </a>
                        </div>
                        <%
                        }
                        %>
                    </div>
                    <div class="clear-both"></div>
                    <div class="select-query">
                        <%
                        ArrayList alAdv=CompanyAdvertisementMaster.showAll();
                        CompanyAdvertisementBean cabs=null;
                        %>
                        <div class="select-query-heading"><%=(500+alAdv.size())%> Latest Offers</div>
                        <%
                        for(int ad=0;ad<alAdv.size() || ad==30;ad++)
                        {
                            cabs=(CompanyAdvertisementBean)alAdv.get(ad);
                            if(cabs.getHoverText().length()>25)
                                valString=cabs.getHoverText().substring(0,25);
                            else
                               valString=cabs.getHoverText();
                        %>
                        <div class="result-set-data" style="border:none;width:220px;float:left;">
                            <a href="<%=cabs.getLinkPage()%>" style="text-decoration: none;" target="_blank">
                                <img height="220" width="220"
                                src="<%=SmProperties.dataServerUrl+"company-" + cabs.getCompanyKey() + File.separator + "ad" + File.separator+cabs.getImagePath()%>"
                                title="<%=cabs.getHoverText()%>" alt="<%=cabs.getHoverText()%>">
                                <h1 title="<%=cabs.getHoverText()%>" style="background-color: #ff7623;padding:10px 0;font-size:14px;width:100%;color:white;" align="center"><%=valString%></h1>
                            </a>
                        </div>
                        <%
                        }
                        %>
                    </div>
                    <div class="clear-both"></div>
                    <div class="select-query">
                    <%
                        ArrayList alGPIM=GlobalProductItemMaster.showAll();
                        GlobalProductItemBean gpib=null;
                        String productImageName="";
                    %>
                    	<div class="select-query-heading"><%=(3000+alGPIM.size())%> Products Available</div>
                        <%
                        for(int product=0;product<alGPIM.size() || product==30 ;product++)
                        {
                            gpib=(GlobalProductItemBean)alGPIM.get(product);

                            if(gpib.getPicsCount().equals(""))
                            {
                                     productImageName = "application/inside/no-product-image.jpg";
                            }
                            else
                            {
                                    String pics[]=gpib.getPicsCount().split("@#@");
                                    for(int i=0;i<1;i++)
                                    productImageName = SmProperties.dataServerUrl +"products/"+pics[i].replace("@#@","");
                            }                            
                            if(gpib.getItemName().length()>25)
                                valString=gpib.getItemName().substring(0,25);
                            else
                               valString=gpib.getItemName();
                        %>
                        <div class="result-set-data" style="border:none;width:220px;float:left;margin-bottom:50px;">
                            <img src="<%=productImageName%>" width="220" height="200"
                            alt="<%=gpib.getItemName()%> is available for sale at Supply Medium Inc"
                            title="<%=gpib.getItemName()%> is available for sale at Supply Medium Inc"
                            name="<%=gpib.getItemName()%> is available for sale at Supply Medium Inc" />
                            <div class="bg-white-gap"></div>
                            <div class="data-title"><%=valString%></div>
                            <div class="data-price"><%=CurrencyMaster.showCodeByKey(gpib.getCurrencyKey())%> <%=gpib.getPrice()%></div>
                            <% if(!gpib.getTax().equals("0.00")){%><div class="data-price" style="color:red;font-weight:bold;font-size:14px;"><%=gpib.getTax().replace(".00","")%>%<br/>Discount</div><% } %>
                            <div class="data-title" style="height:14px;">&nbsp;</div>

                        </div>
                        <%
                        }
                        %>
                    </div>
                </div>
            </div>
            <div class="clear-both"></div>
            <div id="footer">
                <h1>Copyright 2015. 
                    <a target="_blank" style="color:#fff;" href="http://supplymedium.com">Supply Medium, Inc.</a> | 
                    <a target="_blank" style="color:#fff;" href="http://supplymedium.in:8080/MarketingApp/">MarketingApp</a> | 
                    <a target="_blank" style="color:#fff;" href="http://supplymedium.in:8080/app/superadmin/">SuperAdmin</a>
                </h1></div>
        </div>
    </body>
</html>

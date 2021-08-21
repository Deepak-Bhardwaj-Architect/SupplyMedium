<%-- 
    Document   : index
    Created on : Mar 11, 2015, 11:20:23 AM
    Author     : Intel8GB
--%>

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
            <div id="header">
                <div class="header1">
                    <img src="images/logo.png" width="220" 
                    alt="Supply Medium Inc - Redefining Social Commerce" 
                    title="Supply Medium Inc - Redefining Social Commerce" />
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
                        <li><a href="http://supplymedium.in:8080/app/application/" target="_blank" class="color-blue">Login</a></li>
                        <li><a href="http://supplymedium.in:8080/app/application/companyRegistration.jsp" target="_blank" class="color-blue">Register</a></li>
                        <li><a href="http://supplymedium.com/become-partner-with-us/" target="_blank" class="color-blue">Partner</a></li>
                    </ul>
                    </div>
                </div>
            </div>
            <div class="clear-both"></div>
            <div id="data-all">
            	<div class="data-business-category">
                	<div class="data-business-category-heading">ALL BUSINESS CATEGORIES</div>
                    <ul class="category-menu">
                    <%
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
                	<div class="slider">
                    	<img src="images/slider.png" width="680" height="100"
                        alt="Supply Medium Inc - Redefining Social Commerce"
                        title="Supply Medium Inc - Redefining Social Commerce" />
                    </div>
                    <div class="select-query">
                    	<div class="select-query-heading">PRODUCTS</div>
                        <div class="result-set-data">
                        	<img src="images/product.png" width="128"
                            alt="Product's Name - Supply Medium Inc"
                            title="Product's Name - Supply Medium Inc"
                            name="Product's Name - Supply Medium Inc" />
                            <div class="bg-white-gap"></div>
                            <div class="data-title">Product Name</div>
                            <div class="data-price">Product Price</div>
                        </div>
                    </div>
                    
                    
                    <div class="clear-both"></div>
                    <div class="select-query">
                    	<div class="select-query-heading">TOP BUYERS / SUPPLIERS</div>
                        <div class="result-set-data">
                        	<img src="images/webkrit.png" width="128"
                            alt="Company's Name - Supply Medium Inc"
                            title="Company's Name - Supply Medium Inc"
                            name="Company's Name - Supply Medium Inc" />
                            <div class="bg-white-gap"></div>
                            <div class="data-title">Company Name</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clear-both"></div>
            <div id="footer"><h1>Copyright 2015. Supply Medium, Inc.</h1></div>
        </div>
    </body>
</html>

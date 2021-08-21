<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.utility.DiskUsage"%>
<%@page import="supply.medium.home.database.TransactionInvMaster"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.database.AccountPolicyMaster"%>
<%@page import="supply.medium.home.bean.GroupUserBean"%>
<%@page import="supply.medium.home.database.GroupUserMaster"%>
<%@page import="supply.medium.home.bean.DepartmentPermBean"%>
<%@page import="supply.medium.home.bean.GroupPermBean"%>
<%@page import="supply.medium.home.database.DepartmentPermMaster"%>
<%@page import="supply.medium.home.database.GroupPermMaster"%>
<%@page import="supply.medium.home.bean.CompanyWebsiteBean"%>
<%@page import="supply.medium.home.database.CompanyWebsiteMaster"%>
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.utility.MemoryTest"%>
<SCRIPT type="text/JavaScript" src="inside/webkrit.js">
        </SCRIPT>
        <LINK rel="stylesheet" href="inside/Cus_Toast.css">
        <%@page import="java.io.File"%>
<script type="text/javascript">
$(function(){
	$("#Open_Toast").click(function()
	{		
		ShowToast("Inforamtion Saved Successfully...",2000);
	});	
});

function ShowToast(Message,ShowTime)
{	
	if(typeof(ShowTime)==='undefined') ShowTime = 5000;
	var Toast_Obj=$("#Toast_Window");	
	var outerWidth=$(Toast_Obj).outerWidth()/2;	
	Toast_Obj.fadeIn(1000);
	$(".Toast_Data").html(Message);
	Toast_Obj.delay(ShowTime);
	Toast_Obj.fadeOut(1000);
}

</script>
        <%@page import="supply.medium.utility.ErrorMaster"%>
        <%@page import="supply.medium.utility.SmProperties"%>
    <%
            response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
            response.setHeader("Pragma","no-cache"); //HTTP 1.0
            response.setDateHeader ("Expires", 0); //prevent caching at the proxy server
            response.addHeader("Cache-Control","no-store");
            if(session==null || session.getAttribute("companyKey")==null)
            {
                response.sendRedirect("index.jsp");
            }
            try
            {
                MemoryTest.test("header start");
                System.gc();
                MemoryTest.test("header end");
                final String userKey=session.getAttribute("userKey").toString();
                final String companyKey=session.getAttribute("companyKey").toString();
                final String userName=session.getAttribute("userName").toString();
                final String email=session.getAttribute("email").toString();
                final String companyName=session.getAttribute("companyName").toString();
                final String companyTypeMy=session.getAttribute("companyType").toString();
                String companyPic=SmProperties.pathUrl+"company-"+companyKey+"/company-"+companyKey+".png";
                String userPic=SmProperties.pathUrl+"users/"+userKey+".png";
                final String userType=session.getAttribute("userType").toString();
                
                ArrayList websiteUrl2 = CompanyWebsiteMaster.showWebsiteUrl(session.getAttribute("companyKey").toString());
                String urlName2="";
                String websiteUrlLink2="";     
                CompanyWebsiteBean cwb2 = null;
                for (int i = 0; i < websiteUrl2.size(); i++) {
                    cwb2 = (CompanyWebsiteBean) websiteUrl2.get(i);
                     urlName2=cwb2.getUrlName();
                     websiteUrlLink2 = cwb2.getWebsiteUrl();
                }

                // company profile page data starts
                int userCount1979 = UserMaster.showUserCount(companyKey);
                int usersLic1979 = 0;
                if (userCount1979 - 2 > 0) {
                    usersLic1979 = userCount1979 - 2;
                }
                double transactionMade1979 = TransactionInvMaster.showTotalTransactionVolume(companyKey);
                double transactionMade11979 = transactionMade1979 / 100;
                transactionMade11979 = Math.round(transactionMade11979 * 100);
                transactionMade11979 = transactionMade11979 / 100;

                SmProperties.folderPath = request.getRealPath("") +File.separator+"cropData"+File.separator+ "company-" + companyKey;
                long sizeDisk1979 = DiskUsage.getFileFolderSize(new File(SmProperties.folderPath)) / (1024 * 1024);
                long allowedDisk1979 = 100;
                double usedDiskPercent1979 = 100 * sizeDisk1979 / allowedDisk1979;
                double charges1979 = 0;
                long consumptionDisk1979 = 0;
                if (sizeDisk1979 > allowedDisk1979) {
                    consumptionDisk1979 = sizeDisk1979 - allowedDisk1979;
                }
                ///
                if ((consumptionDisk1979 % 100) > 0) {
                    consumptionDisk1979++;
                }
                ///
                charges1979 = (consumptionDisk1979 / 100) * 1.99;
                ///
                charges1979 = Math.round(charges1979 * 100);
                charges1979 = charges1979 / 100;

                double total1979 = transactionMade11979 + (usersLic1979 * 1.99) + charges1979;
                total1979 = Math.round(total1979 * 100);
                total1979 = total1979 / 100;

                CompanyMaster.updatePlatformAmountGenerated(session.getAttribute("companyKey").toString(), ""+total1979);
                CompanyMaster.updatePlatformAmountGenerated(companyKey, ""+total1979);
                String chargesAll1979=CompanyMaster.showPlatformCharges(companyKey);
                double genratedCharges1979=Double.parseDouble(chargesAll1979.split("@#@")[0]);
                genratedCharges1979 = Math.round(genratedCharges1979 * 100);
                genratedCharges1979 = genratedCharges1979 / 100;
                double paidCharges1979=Double.parseDouble(chargesAll1979.split("@#@")[1]);
                paidCharges1979 = Math.round(paidCharges1979 * 100);
                paidCharges1979 = paidCharges1979 / 100;
                total1979=genratedCharges1979;

                total1979=total1979-paidCharges1979;
                total1979 = Math.round(total1979 * 100);
                total1979 = total1979 / 100;

                // company profile page data ends
                
                ArrayList alg=GroupUserMaster.showGroupsOfUser(userKey);
                GroupUserBean gub=null;
                GroupPermBean gpb=null;
                String adminTab="no";
                String listUserTab="no";
                String addUser="no";
                String delUser="no";
                String groupTab="no";
                String addGroup="no";
                String delGroup="no";
                String postAncMain="no";
                String userAccMan="no";
                String venTab="no";
                String addVen="no";
                String delVen="no";
                
                for(int i=0;i<alg.size();i++)
                {
                    gub=(GroupUserBean)alg.get(i);
                    gpb=GroupPermMaster.showPermByGroupKey(gub.getGroupKey());
                    if(gpb.getAdd_group().equalsIgnoreCase("yes"))
                    {
                        addGroup="yes";
                        groupTab="yes";
                        adminTab="yes";
                    }
                    if(gpb.getDelete_group().equalsIgnoreCase("yes"))
                    {
                        delGroup="yes";
                        groupTab="yes";
                        adminTab="yes";
                    }
                    if(gpb.getAdd_user().equalsIgnoreCase("yes"))
                    {
                        addUser="yes";
                        listUserTab="yes";
                        adminTab="yes";
                    }
                    if(gpb.getDelete_user().equalsIgnoreCase("yes"))
                    {
                        delUser="yes";
                        listUserTab="yes";
                        adminTab="yes";
                    }
                    if(gpb.getPost_announcements().equalsIgnoreCase("yes"))
                    {
                        postAncMain="yes";
                    }
                    if(gpb.getAccess_user_mngmt().equalsIgnoreCase("yes"))
                    {
                        listUserTab="yes";
                        adminTab="yes";
                        userAccMan="yes";
                    }
                    if(gpb.getAdd_vendor().equalsIgnoreCase("yes"))
                    {
                        venTab="yes";
                        addVen="yes";
                    }
                    if(gpb.getDelete_vendor().equalsIgnoreCase("yes"))
                    {
                        delVen="yes";
                    }
                    if(userType.equals("Admin"))
                    {                        
                        adminTab="yes";
                        listUserTab="yes";
                        addUser="yes";
                        delUser="yes";
                        groupTab="yes";
                        addGroup="yes";
                        delGroup="yes";
                        postAncMain="yes";
                        userAccMan="yes";
                        venTab="yes";
                        addVen="yes";
                        delVen="yes";
                    }
                }                
        %>
        <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE" />
        <META HTTP-EQUIV="Expires" CONTENT="-1" />
        <META HTTP-EQUIV=3D"Cache-Control" content=3D"no-cache">
        <META HTTP-EQUIV=3D"Cache-Control" content=3D"no-store">
        <META HTTP-EQUIV=3D"Expires" CONTENT=3D"-1">
        <META HTTP-EQUIV=3D"Expires" CONTENT=3D"no-cache">
        <META HTTP-EQUIV=3D"Expires" CONTENT=3D"Mon, 01 Jan 1970 23:59:59 GMT">
        <META HTTP-EQUIV=3D"Pragma" Cache-Control=3D"no-cache">
        <DIV id="header" class="header">
        <div class="headercontent">
            <div class="header_left">
                <div id="comp_logo" style="display: block;">
                    <a href="index.jsp" style="cursor:pointer;">
                        <img src="<%=companyPic%>" class="company_logo" title="<%=companyName%>" alt="<%=companyName%>" width="90px" height="90px" style="border:none;margin-top:10px;float:left;">
                    </a>
                    <label class="company_label" id="company_label" title="<%=companyName%>" alt="<%=companyName%>"><%=companyName%></label>
                </div>
            </div>
            <div class="header_right">
                <div class="userdetails" style="margin-top:40px;">
                    <div class="acc_menu" id="acc_menu_link">
                        <a style="cursor:pointer;color:#009abc !important;">My Account</a>
                    </div>
                    <div id="acc_menu_content" class="acc_menu_content" style="display: none;">
                        <a href="http://supplymedium.com/category/faq/" target="_blank" style="margin-top:19px;" class="acc_menu_item" onClick="window.location.href='';">Help Manual</a>
                        <a href="userAccountSettings.jsp" class="acc_menu_item" onClick="showUserAccSettings();">Account Settings</a>
                        <a href="Logout" class="acc_menu_item" onClick="invalidateSession();">Logout</a>
                    </div>
                    <div style="width:20px;height:20px;float:right;cursor:pointer;" onclick="showGlobalNotification();">
                        <div class="notification" id="notification" onclick="showGlobalNotification();">
                        <div class="notification_badge1" id="notification_count1" onclick="showGlobalNotification();"></div></div>
                    </div>
                    <div class="username" id="username">Welcome <%=userName%></div>
                    <img class="userimage" id="header_userimage" src="<%=userPic %>">
                </div>
                <div class="header_links_container">
                    <a class="header_link" id="dashboard_link" href="homeDashboard.jsp">Dashboard</a>
                    <a class="header_link" id="news_link" href="home.jsp">News&nbsp;&nbsp;|&nbsp;</a>
                    <%
                    if(!websiteUrlLink2.equals(""))
                    {
                    %>
                    <a class="header_link" id="website_link" href="<%=websiteUrlLink2 %>" onclick="window.location.href=''" title="Company Website" target="_blank"><%=urlName2%>&nbsp;&nbsp;|&nbsp;</a>
                <% } %>
                </div>
            </div>
            <div class="notification_container" id="notification_container" style="display: none;">
                <div class="arrow_box">
                </div>
                <div class="drop_box">
                    <div class="title">Global Notification</div>
                    <div class="white_space">
                        <div class="data_container mCustomScrollbar _mCS_1" id="noti_dropdown_content">
                            <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_1" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
                                <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
                                </div>
                                <div class="mCSB_scrollTools" style="position: absolute; display: none;">
                                    <div class="mCSB_draggerContainer">
                                        <div class="mCSB_dragger" style="position: absolute; top: 0px;" oncontextmenu="return false;">
                                            <div class="mCSB_dragger_bar" style="position:relative;">
                                            </div>
                                        </div>
                                        <div class="mCSB_draggerRail">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="see_more" id="see_more"><a href="homeNotification.jsp">See More</a></div>
                </div>
            </div>
        </div>
    </DIV>
    <SCRIPT type="text/JavaScript" src="inside/notifi_dropdown.js">
    </SCRIPT>
    <SCRIPT type="text/JavaScript" src="inside/userheader.js">
    </SCRIPT>
    <LINK rel="stylesheet" 
          href="inside/ResetCSS.css">
    <LINK rel="stylesheet" href="inside/Cus_Menu.css">
        <%
                String pageName="";
                pageName=request.getServletPath().replace("/application/","");
        %>
    <div id='menucontainer'>
        <div id='Menu'>
            <!-- Main Menu  -->
            <div class='nav_Main Gen_nav' style='width:994px;'>
                <ul class='MenuType1 DisplayMenu '>
                    <li id='Network'
                    <%
                    if(pageName.equals("home.jsp")
                            || pageName.equals("homeMyFeeds.jsp")
                            || pageName.equals("homeMessages.jsp")
                            || pageName.equals("homeNotification.jsp")
                            || pageName.equals("homeDashboard.jsp")
                            || pageName.equals("homeRatings.jsp")
                            || pageName.equals("homeMyConnections.jsp"))
                    {
                        out.println(" class='navmainSelected'");
                    }
                    %>
                    >
                        <a title='Home' href='home.jsp'>Home</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='menu_separtor' id='menu_separtor_Admin'>
                        </div>
                    </li>
                    <li id='Corporate'
                    <%
                    if(pageName.equals("corporate.jsp")
                            || pageName.equals("corporateDepartmentPage.jsp")
                            || pageName.equals("corporateMainInternalPage.jsp"))
                    {
                        out.println(" class='navmainSelected'");
                    }
                    %>
                    >
                        <a title='Corporate' href='corporate.jsp'>Corporate</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='menu_separtor' id='menu_separtor_Corporate'>
                        </div>
                    </li>
<%
if(!userType.equals("Intranet User") || venTab.equals("yes"))
{
%>
                    <li id='Search'
                    <%
                if(pageName.equals("search.jsp")
                        || pageName.equals("searchBuyer.jsp"))
                {
                    out.println(" class='navmainSelected'");
                }
                if(companyTypeMy.equals("Buyers/Suppliers"))
                {
                    out.println("><a title='Search' href='search.jsp'>Search</a></li>");
                }
                if(companyTypeMy.equals("Supplier"))
                {
                    out.println("><a title='Search' href='searchBuyer.jsp'>Search</a></li>");
                }
                if(companyTypeMy.equals("Buyer"))
                {
                    out.println("><a title='Search' href='search.jsp'>Search</a></li>");
                }
                if(companyTypeMy.equals("Buyers/Suppliers") 
                        || companyTypeMy.equals("Supplier"))
                {
%>                    
                    <li style='width: 2px'>
                        <div class='menu_separtor' id='menu_separtor_Search'>
                        </div>
                    </li>
                    <li id='Buyers'
                        <%
                        if(pageName.equals("buyers.jsp")
                                || pageName.equals("buyersVR.jsp"))
                        {
                            out.println(" class='navmainSelected'");
                        }
                        %>
                    >
                        <a title='Buyers' href='buyers.jsp'>Buyers</a>
                    </li>
<%
                }
                if(companyTypeMy.equals("Buyers/Suppliers") 
                        || companyTypeMy.equals("Buyer"))
                {
%>                    
                    <li style='width: 2px'>
                        <div class='menu_separtor' id='menu_separtor_Buyers'>
                        </div>
                    </li>
                    <li id='Suppliers'
                        <%
                        if(pageName.equals("suppliers.jsp")
                                || pageName.equals("suppliersVR.jsp"))
                        {
                            out.println(" class='navmainSelected'");
                        }
                        %>
                    >
                        <a title='Suppliers' href='suppliers.jsp'>Suppliers</a>
                    </li>
<%
                }
}
if(!userType.equals("Intranet User"))
{
%>                    
                    <li style='width: 2px'>
                        <div class='menu_separtor' id='menu_separtor_Suppliers'>
                        </div>
                    </li>
                    <li id='Transactions'
                        <%
                        if(pageName.equals("transactions.jsp")
                                || pageName.equals("transactionsProductCatalogAdd.jsp")
                                || pageName.equals("transactionsProductCatalogCSV.jsp")
                                || pageName.equals("transactionsProductCatalog.jsp")
                                || pageName.equals("transactionsTaC.jsp")
                                || pageName.equals("transactionsTaCq.jsp")
                                || pageName.equals("transactionsTaCp.jsp")
                                || pageName.equals("transactionsTaCi.jsp")
                                || pageName.equals("transactionsTransactionHistory.jsp")
                                || pageName.equals("transactionsTransactionHistoryCustomer.jsp")
                                || pageName.equals("transactionsTransactionHistoryPayment.jsp")
                                || pageName.equals("transactionsQuote.jsp")
                                || pageName.equals("transactionsQuotes.jsp")
                                || pageName.equals("transactionsPurchaseOrder.jsp")
                                || pageName.equals("transactionsInvoice.jsp")
                            || pageName.equals("transactionsInvoices.jsp")
                                || pageName.equals("transactionsPayment.jsp"))
                        {
                            out.println(" class='navmainSelected'");
                        }
                        %>
                    >
                         <a title='Transactions' href='transactions.jsp'>Transactions</a> 
                    </li>
                    <li style='width: 2px'>
                        <div class='menu_separtor' id='menu_separtor_Transactions'>
                        </div>
                    </li>
<%
}
if(adminTab.equalsIgnoreCase("yes") || userType.equals("Admin"))
{
%>
                    <li id='Admin' 
                        <%
                        if(pageName.equals("admin.jsp")
                                || pageName.equals("adminAccountPolicies.jsp")
                                || pageName.equals("adminAdvertisement.jsp")
                                || pageName.equals("adminCompanyProfile.jsp")
                                || pageName.equals("adminCompanyProfiles.jsp")
                                || pageName.equals("adminBank.jsp")
                                || pageName.equals("adminCompanyWebsite.jsp")
                                || pageName.equals("adminDepartments.jsp")
                                || pageName.equals("adminGroups.jsp")
                                || pageName.equals("adminListUsers.jsp")
                                || pageName.equals("adminManageFolders.jsp")
                                || pageName.equals("adminNewUser.jsp"))
                        {
                            out.println(" class='navmainSelected'");
                        }
                        %>
                    >
                        <a title='Admin' <% if(userType.equals("Admin")){%>href='admin.jsp'<%} else if(!userType.equals("Admin") 
                            && listUserTab.equalsIgnoreCase("no") 
                            && adminTab.equalsIgnoreCase("yes")){%>href='adminGroups.jsp'<%}else{%>href='adminListUsers.jsp'<%}%>>Admin</a>
                    </li>
<%
}
%>
                </ul>
            </div>
            <!-- Sub Menu  -->
            <div class='nav_Sub Gen_nav'>
                <ul id='SubNetwork'
                    <%
                    if(pageName.equals("home.jsp")
                            || pageName.equals("homeMyFeeds.jsp")
                            || pageName.equals("homeMessages.jsp")
                            || pageName.equals("homeNotification.jsp")
                            || pageName.equals("homeDashboard.jsp")
                            || pageName.equals("homeDashboardCC.jsp")
                            || pageName.equals("homeDashboardCR.jsp")
                            || pageName.equals("homeDashboardTC.jsp")
                            || pageName.equals("homeRatings.jsp")
                            || pageName.equals("homeMyConnections.jsp"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                    <li id='SubNetwork_News_Feed'
                    <%
                    if(pageName.equals("home.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Network Feeds' href='home.jsp'>Network Feeds</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubNetwork_News_Feed'>
                        </div>
                    </li>
                    <li id='SubCorporate_User_Page'
                    <%
                    if(pageName.equals("homeMyFeeds.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='My Feeds' href='homeMyFeeds.jsp'>My Feeds</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubCorporate_User_Page'>
                        </div>
                    </li>
                    <li id='SubNetwork_Messages'
                    <%
                    if(pageName.equals("homeMessages.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Messages' href='homeMessages.jsp'>Messages</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubNetwork_Messages'>
                        </div>
                    </li>
                    <li id='SubNetwork_Notification'
                    <%
                    if(pageName.equals("homeNotification.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Notification' href='homeNotification.jsp'>Notification</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubNetwork_Notification'>
                        </div>
                    </li>
                    <li id='SubNetwork_Dashboard'
                    <%
                    if(pageName.equals("homeDashboard.jsp")
                            || pageName.equals("homeDashboardCC.jsp")
                            || pageName.equals("homeDashboardCR.jsp")
                            || pageName.equals("homeDashboardTC.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Dashboard' href='homeDashboard.jsp'>Dashboard</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubNetwork_Dashboard'>
                        </div>
                    </li>
                    <li id='SubNetwork_Ratings'
                    <%
                    if(pageName.equals("homeRatings.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Ratings' href='homeRatings.jsp'>Ratings</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubNetwork_Ratings'>
                        </div>
                    </li>
                    <li id='SubNetwork_My_Connections'
                    <%
                    if(pageName.equals("homeMyConnections.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='My Connections' href='homeMyConnections.jsp'>My Connections</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor'>
                        </div>
                    </li>
                </ul>
                <ul id='SubCorporate'
                    <%
                    if(pageName.equals("corporate.jsp")
                            || pageName.equals("corporateDepartmentPage.jsp")
                            || pageName.equals("corporateMainInternalPage.jsp"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                    <li id='SubCorporate_Internal_Page'
                    <%
                    if(pageName.equals("corporate.jsp")
                            || pageName.equals("corporateMainInternalPage.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Main' href='corporate.jsp'>Main</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubCorporate_Internal_Page'>
                        </div>
                    </li>
                    <li id='SubCorporate_Department_Page'
                    <%
                    if(pageName.equals("corporateDepartmentPage.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Department Page' href='corporateDepartmentPage.jsp'>Department Page</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubCorporate_Department_Page'>
                        </div>
                    </li>
                </ul>
<%
if(companyTypeMy.equals("Buyers/Suppliers"))
{
%>
                <ul id='SubSearch'
                    <%
                    if(pageName.equals("search.jsp")
                            || pageName.equals("searchBuyer.jsp"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                    <li id='SubSearch_Search_Supplier'
                    <%
                    if(pageName.equals("search.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Supplier' href='search.jsp'>Supplier</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubSearch_Search_Supplier'>
                        </div>
                    </li>
                    <li id='SubSearch_Search_Buyer'
                    <%
                    if(pageName.equals("searchBuyer.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Buyer' href='searchBuyer.jsp'>Buyer</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor'>
                        </div>
                    </li>
                </ul>
<%
}
if(companyTypeMy.equals("Buyer"))
{
%>
                <ul id='SubSearch'
                    <%
                    if(pageName.equals("search.jsp"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                    <li id='SubSearch_Search_Supplier'
                    <%
                    if(pageName.equals("search.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Supplier' href='search.jsp'>Supplier</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor'>
                        </div>
                    </li>
                </ul>
<%
}
if(companyTypeMy.equals("Supplier"))
{
%>
                <ul id='SubSearch'
                    <%
                    if(pageName.equals("searchBuyer.jsp"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                    <li id='SubSearch_Search_Buyer'
                    <%
                    if(pageName.equals("searchBuyer.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Buyer' href='searchBuyer.jsp'>Buyer</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor'>
                        </div>
                    </li>
                </ul>
<%
}
%>
                <ul id='SubBuyers'
                    <%
                    if(pageName.equals("buyers.jsp")
                            || pageName.equals("buyersVR.jsp"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                    <li id='SubBuyers_Vendor_Registration'
                    <%
                    if(pageName.equals("buyers.jsp")
                            || pageName.equals("buyersVR.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Vendor Registration' href='buyersVR.jsp'>Vendor Registration</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubBuyers_Vendor_Registration'>
                        </div>
                    </li>
                </ul>
                <ul id='SubSuppliers'
                    <%
                    if(pageName.equals("suppliers.jsp")
                            || pageName.equals("suppliersVR.jsp"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                    <li id='SubSuppliers_Vendor_Registration'
                    <%
                    if(pageName.equals("suppliers.jsp")
                            || pageName.equals("suppliersVR.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Vendor Registration' href='suppliersVR.jsp'>Vendor Registration</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubSuppliers_Vendor_Registration'>
                        </div>
                    </li>
                </ul>
                <ul class='Sub_display' id='SubTransactions'
                    <%
                    if(pageName.equals("transactions.jsp")
                            || pageName.equals("transactionsQuote.jsp")
                            || pageName.equals("transactionsPurchaseOrder.jsp")
                            || pageName.equals("transactionsInvoice.jsp")
                            || pageName.equals("transactionsInvoices.jsp")
                            || pageName.equals("transactionsPayment.jsp")
                            || pageName.equals("transactionsPayments.jsp")
                            || pageName.equals("transactionsProductCatalog.jsp")
                            || pageName.equals("transactionsProductCatalogCSV.jsp")
                            || pageName.equals("transactionsProductCatalogAdd.jsp")
                            || pageName.equals("transactionsTaC.jsp")
                            || pageName.equals("transactionsTaCq.jsp")
                            || pageName.equals("transactionsTaCp.jsp")
                            || pageName.equals("transactionsTaCi.jsp")
                            || pageName.equals("transactionsTransactionHistory.jsp")
                            || pageName.equals("transactionsTransactionHistoryCustomer.jsp")
                            || pageName.equals("transactionsTransactionHistoryPayment.jsp")
                            || pageName.equals("transactionsTransactionHistory.jsp"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                    <li id='SubTransactions_Transaction'
                    <%
                    if(pageName.equals("transactions.jsp")
                            || pageName.equals("transactionsQuote.jsp")
                            || pageName.equals("transactionsPurchaseOrder.jsp")
                            || pageName.equals("transactionsPurchaseOrders.jsp")
                            || pageName.equals("transactionsInvoice.jsp")
                            || pageName.equals("transactionsInvoices.jsp")
                            || pageName.equals("transactionsPayment.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >
                        <a title='Transaction' href='transactions.jsp'>Transaction</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubTransactions_Transaction'>
                        </div>
                    </li>
                    <li id='SubTransactions_History'>
                        <a title='History' href='transactionsTransactionHistory.jsp'
                    <%
                    if(pageName.equals("transactionsTransactionHistory.jsp")
                                || pageName.equals("transactionsTransactionHistoryCustomer.jsp")
                                || pageName.equals("transactionsTransactionHistoryPayment.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >Transaction History</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubTransactions_Histroy'>
                        </div>
                    </li>
                    <li id='SubTransactions_TC'>
                        <a title='Terms &amp; Conditions' href='transactionsTaC.jsp'
                    <%
                    if(pageName.equals("transactionsTaC.jsp")
                        || pageName.equals("transactionsTaCq.jsp")
                        || pageName.equals("transactionsTaCp.jsp")
                        || pageName.equals("transactionsTaCi.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >Terms &amp; Conditions</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubTransactions_TC'>
                        </div>
                    </li>
                    <li id='SubCorporate_Product_Catalog'>
                        <a title='Product Catalog' href='transactionsProductCatalog.jsp'
                    <%
                    if(pageName.equals("transactionsProductCatalog.jsp")
                            || pageName.equals("transactionsProductCatalogAdd.jsp")
                            || pageName.equals("transactionsProductCatalogCSV.jsp")
                            )
                    {
                        out.println(" class='navsubSelected'");
                    }
                    %>
                    >Product Catalog</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubCorporate_product_catalog'>
                        </div>
                    </li>
                </ul>
                <ul id='SubAdmin'
                    <%
                    if(pageName.equals("admin.jsp")
                            || pageName.equals("adminListUsers.jsp")
                            || pageName.equals("adminAccountPolicies.jsp")
                            || pageName.equals("adminAdvertisement.jsp")
                            || pageName.equals("adminCompanyProfile.jsp")
                                || pageName.equals("adminCompanyProfiles.jsp")
                            || pageName.equals("adminCompanyWebsite.jsp")
                            || pageName.equals("adminBank.jsp")
                            || pageName.equals("adminDepartments.jsp")
                            || pageName.equals("adminGroups.jsp")
                            || pageName.equals("adminListUsers.jsp")
                            || pageName.equals("adminManageFolders.jsp")
                            || pageName.equals("adminNewUser.jsp"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                    <li id='SubAdmin_User'>
                        <a title='Users' href='adminListUsers.jsp'
                    <%
                    if(pageName.equals("admin.jsp")
                            || pageName.equals("adminListUsers.jsp")
                            || pageName.equals("adminNewUser.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    if(userType.equals("Admin"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else if(!userType.equals("Admin") &&  listUserTab.equalsIgnoreCase("yes"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >Users</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubAdmin_User'>
                        </div>
                    </li>
                    <li id='SubAdmin_Group'
                    <%
                    if(pageName.equals("adminGroups.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    
                    if(userType.equals("Admin"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else if(!userType.equals("Admin") && groupTab.equalsIgnoreCase("yes"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                        <a title='Groups' href='adminGroups.jsp'>Groups</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubAdmin_Group'>
                        </div>
                    </li>
                    <li id='SubAdmin_Departments'
                    <%
                    if(pageName.equals("adminDepartments.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    if(userType.equals("Admin"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                        <a title='Departments' href='adminDepartments.jsp'>Departments</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubAdmin_Departments'>
                        </div>
                    </li>
<!--                    <li id='SubAdmin_Manage_Folders'
                    <%
                    if(pageName.equals("adminManageFolders.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    if(userType.equals("Admin"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                        <a title='Manage Folders' href='adminManageFolders.jsp'>Manage Folders</a>
                    </li>-->
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubAdmin_Manage_Folders'>
                        </div>
                    </li>
                    <li id='SubAdmin_Account_Policies'
                    <%
                    if(pageName.equals("adminAccountPolicies.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    if(userType.equals("Admin"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                        <a title='Account_Policies' href='adminAccountPolicies.jsp'>Account Policies</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubAdmin_Account_Policies'>
                        </div>
                    </li>
                    <li id='SubAdmin_Company_Profile'
                    <%
                    if(pageName.equals("adminCompanyProfile.jsp")
                    || pageName.equals("adminCompanyProfiles.jsp") )
                    {
                        out.println(" class='navsubSelected'");
                    }
                    if(userType.equals("Admin"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                        <a title='Company Profile' href='adminCompanyProfile.jsp'>Company Profile</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubAdmin_Company_Profile'>
                        </div>
                    </li>
                    <%
                    if(companyTypeMy.equals("Supplier") || companyTypeMy.equals("Buyers/Suppliers"))
                    {
                    %>
                    <li id='SubAdmin_Company_Profile'
                    <%
                    if(pageName.equals("adminBank.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    if(userType.equals("Admin"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                        <a title='Bank Account Info' href='adminBank.jsp'>Bank Account Info</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubAdmin_Company_Profile'>
                        </div>
                    </li>
                    <%
                    }
                    %>
                    <li id='SubAdmin_Company_Website'
                    <%
                    if(pageName.equals("adminCompanyWebsite.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    if(userType.equals("Admin"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                        <a title='Company Website' href='adminCompanyWebsite.jsp'>Company Website</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor' id='Sub_menu_seperator_SubCorporate_ad'>
                        </div>
                    </li>
                    <li id='SubCorporate_Ad'
                    <%
                    if(pageName.equals("adminAdvertisement.jsp"))
                    {
                        out.println(" class='navsubSelected'");
                    }
                    if(userType.equals("Admin"))
                    {
                        out.println(" style='display:block;'");
                    }
                    else
                    {
                        out.println(" style='display:none;'");
                    }
                    %>
                    >
                        <a title='Advertisement' href='adminAdvertisement.jsp'>Advertisement</a>
                    </li>
                    <li style='width: 2px'>
                        <div class='Sub_menu_separtor'>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div id="screen_lock"><!-- screen lock div closed in invite -->
        <div id="webkrit_content_loader" style="height:100%;width:100%;background:#fff;opacity:.8; position: fixed; z-index: 99999;">
        <IMG style="margin-left: 45%; margin-top: 25%;" 
          src="inside/ajax_loader_big.gif">
        </div>
                    

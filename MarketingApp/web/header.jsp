<%
    if(session.getAttribute("marketing_person_key")==null)
        response.sendRedirect("index.jsp");
    else
    {
%>
<div class="header" id="header">
    <div class="headercontent">
        <div class="header_left">
            <div id="comp_logo" style=""> <a href="home.jsp" style="cursor: pointer;"> <img alt="Supply Medium" class="company_logo" id="company_logo" style="margin-top: 10px; float: left; width: 325px; height: 100px;" src="files/logo.png" align="left"> </a><label class="company_label" id="company_label"></label> </div>
            <div class="logo" id="supplymedium_logo" style="position: relative; display: none;"> <a href="/index.jsp" style="cursor: pointer;"> <img src="files/mugshot.png" style="border: medium none ; float: left; margin-top: 10px;" height="90" width="90"> </a> <label class="company_label" id="company_label" style="width: auto; float: left;"></label>
            </div>
        </div>
        <div class="header_right">
            <div class="userdetails">
                <div class="username" id="username">Welcome <%=session.getAttribute("person_name")%><br/><br/><a href="logout.jsp">Logout</a></div>
            </div>
        </div>
    </div>
</div>
<div id="menucontainer">
    <div id="Menu"><!-- Main Menu  -->
        <div class="nav_Main Gen_nav" style="background: #058aa7;color: white;padding:0 35%;">
            <ul class="MenuType1 DisplayMenu" style="background: #058aa7;color: white;">
                <li style="text-align: center;margin: auto;width:100%;">
                    Supply Medium Marketing Console
                </li>
            </ul>

        </div>
        <!-- Sub Menu  -->
        <div class="nav_Sub Gen_nav">
            <ul id="SubAdmin" style="display: block;">
                <li id="SubAdmin_User"><a href="home.jsp">Dashboard</a></li>
                <%
                if(session.getAttribute("marketing_person_key").toString().equals("0"))
                {
                %>
                <li style="width: 15px;">&nbsp;</li>
                <li id="SubAdmin_User"><a href="listMarketingPerson.jsp">Our Marketing Associates</a></li>
                <li style="width: 15px;">&nbsp;</li>
                <li id="SubAdmin_User"><a href="addMarketingPerson.jsp">Add Marketing Associate</a></li>
                <li style="width: 15px;">&nbsp;</li>
                <%
                }
                %>
            </ul>  
        </div>
    </div>
</div>
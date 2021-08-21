<div class="header" id="header">
    <div class="headercontent">
        <div class="header_left">
            <div id="comp_logo" style=""> <a href="/index.jsp" style="cursor: pointer;"> <img alt="Supply Medium" class="company_logo" id="company_logo" style="margin-top: 10px; float: left; width: 325px; height: 100px;" src="files/logo.png" align="left"> </a><label class="company_label" id="company_label"></label> </div>
            <div class="logo" id="supplymedium_logo" style="position: relative; display: none;"> <a href="/index.jsp" style="cursor: pointer;"> <img src="files/mugshot.png" style="border: medium none ; float: left; margin-top: 10px;" height="90" width="90"> </a> <label class="company_label" id="company_label" style="width: auto; float: left;"></label>
            </div>
        </div>
        <div class="header_right">
            <div class="userdetails">
                <div class="username" id="username">Welcome Admin<br/><br/><a href="index.jsp">Logout</a></div>
            </div>
        </div>
    </div>
</div>
<div id="menucontainer">
    <div id="Menu"><!-- Main Menu  -->
        <div class="nav_Main Gen_nav" style="background: #058aa7;color: white;padding:0 35%;">
            <ul class="MenuType1 DisplayMenu" style="background: #058aa7;color: white;">
                <li style="text-align: center;margin: auto;width:100%;">
                    Supply Medium Administration Console
                </li>
            </ul>

        </div>
        <!-- Sub Menu  -->
        <div class="nav_Sub Gen_nav">
            <%
             String usr="",cmpny="";   
             if(request.getAttribute("slctd_tb")!=null)
             {
                 if(request.getAttribute("slctd_tb").equals("usr"))
             {
                 usr="navsubSelected";
             }
                 else if(request.getAttribute("slctd_tb").equals("cmpny"))
             {
                 cmpny="navsubSelected";
             }   
             }
            %>
            <ul id="SubAdmin" style="display: block;">
                    <%
                    if(session.getAttribute("admn_id").toString().equals("admin"))
                    {
                    %>
                <li id="SubAdmin_User" class="<%=usr%>"><a title="Admins" href="home.jsp">Admins</a></li>
                <li style="width: 2px;"> <br>
                    <%
                    }
                    %>
                </li>
                <li id="SubAdmin_Group" class="<%=cmpny%>"><a title="Company" href="company.jsp">Company</a></li>
                
            </ul>  
        </div>
    </div>
</div>
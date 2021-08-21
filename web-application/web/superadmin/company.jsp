<%@page import="supply.medium.utility.DiskUsage"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="supply.medium.superadmin.SuperAdminQuery"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="files/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="files/jquery-ui-1.9.2.custom_spinner.css">
        <link rel="stylesheet" href="files/commonlayout.css">
        <link rel="stylesheet" href="files/elements.css">
        <link rel="stylesheet" href="files/Custome_Buttons.css">
        <link rel="stylesheet" href="files/jquery.mCustomScrollbar.css">
        <link rel="stylesheet" href="files/user_home.css">
        <link rel="stylesheet" href="files/dilbag.css">
        <script type="text/JavaScript" src="files/jquery-1.9.0.js"></script>  
        <script type="text/JavaScript" src="files/dilbag.js"></script>           
        <title>Supply Medium</title>  
        <link rel="stylesheet" href="files/userheader.css">
        <link rel="stylesheet" href="files/notifi_dropdown.css">
        <link rel="stylesheet" href="files/ResetCSS.css">
        <link rel="stylesheet" href="files/Cus_Menu.css">
        <link rel="stylesheet" href="files/commonlayout.css">
        <link rel="stylesheet" href="files/elements.css">
        <link rel="stylesheet" href="files/usermgmt.css">
        <link rel="stylesheet" href="files/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="files/tablestyle.css">
        <link rel="stylesheet" href="files/Custome_Buttons.css">
        <link rel="stylesheet" href="files/usermgmt_popup.css">
        <link rel="stylesheet" href="files/Custome_Popup.css">
        <link rel="stylesheet" href="files/popup_warning.css">
        <link rel="stylesheet" href="files/Cus_Toast.css">
        <link rel="stylesheet" href="files/ajax_loader.css">
        <link rel="stylesheet" href="files/Custome_Popup.css">
        <link rel="stylesheet" href="files/footer.css">
        <link rel="stylesheet" href="files/term.css">
        <link rel="stylesheet" href="files/privacy.css">

    </head>
    <body onload="lst_id = 'admn_usrs'">
        <% request.setAttribute("slctd_tb", "cmpny"); %>
        <%@include file="header.jsp" %>
        <div id="activate_deactvate_account" class="rfq_post_confirmation" style="width:400px;height:175px;margin-left:28%;"><center><p><br/>Change Status<br/><br/>
                    <input type="radio" name="status" id="activate" title="Activate" onclick="cmpny_sts = 'Active';"/>Activate <input type="radio" name="status" id="deactivate" title="Deactivate" onclick="cmpny_sts = 'Deactive';"/> Deactivate <br/><br/>
                    <input type="button" class="gen-btn-blue" value="UPDATE" style="color: red" onclick="company_status_update();
                    $('#activate_deactvate_account').fadeOut();"/><input type="button" class="gen-btn-blue" value="NO" onclick="$('#activate_deactvate_account').fadeOut();" style="margin-left:30px;"/></p></center>
        </div>
        <div class="contentcontainer admin_container">
            <div class="nav_Sub Gen_nav">
                <ul id="SubAdmin" style="display: block;">
                    <li id="SubAdmin_User" class="navsubSelected" onclick="fade_in_out('admn_usrs');"><a title="Users">List of registered company</a></li>
                </ul>
            </div>
            <div class="admin_companies">
                <div class="admin_companies_div" style="background:#008bbd;color:white;padding-top: 0px;padding-bottom: 0px;">
                    <span style=width:200px;">Company Name</span>
                    <span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;width:150px;">Type</span>
                    <span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;width:400px;">Address, State, Country</span>
                    <span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;width:100px;">Storage Used</span>
                    <span>More Detail</span></div>    
                <%
                    HttpSession ses = request.getSession();
                    if (ses.getAttribute("admn_id") == null) {
                        response.sendRedirect("index.jsp?fail=5");
                    }

                    try {
                        String clr = "#f2f6f7";
                        SuperAdminQuery db_qrs = new SuperAdminQuery();
                        ResultSet rs = db_qrs.select("select company_name,"
                    + "company_id,"
                    + "company_type,"
                    + "branch,"
                    + "country,"
                    + "address,"
                    + "city,"
                    + "state,"
                    + "zipcode,"
                    + "company_type,"
                    + "company_master.company_key "
                    + "from company_master join company_mailing_address_master on "
                    + "company_master.company_key=company_mailing_address_master.company_key");
                        while (rs.next()) {
                            out.print("<div class='admin_companies_div' style='background:" + clr + "'>"
                                    + "<span style='width:200px;'>" + rs.getString("company_name") + "</span>"
                                    + "<span style='width:150px;'>" + rs.getString("company_type").replace("/", " / ") + "</span>"
                                    + "<span style='width:400px;'>" + rs.getString("address") +" "+ rs.getString("state") +" "+ rs.getString("country") + "</span>");
                        SmProperties.folderPath = request.getRealPath("") + File.separator + "cropData" + File.separator+"company-"+rs.getString("company_master.company_key");
                        long sizeDisk=DiskUsage.getFileFolderSize(new File(SmProperties.folderPath))/(1024*1024);
                        long allowedDisk=100;
                        double usedDiskPercent=100*sizeDisk/allowedDisk;
                             out.print("<span style='width:100px;'>" + sizeDisk + "MB ( "+usedDiskPercent+"% )" + "</span>"
                                    + "<span style='cursor:pointer'  onclick=\"javascript:cmpny_mr_dtl('" + rs.getString("company_master.company_key") + "');\">More Detail</span>"
                                    + "<div id='cmpny_mr_dtl_" + rs.getString("company_master.company_key") + "' class='admin_companies_more_detail' style='background:" + clr + "'>Getting data...</div></div>");
                            if (clr.equals("#f2f6f7")) {
                                clr = "#e2e4df";
                            } else if (clr.equals("#e2e4df")) {
                                clr = "#f2f6f7";
                            }
                        }

                    } catch (Exception e) {
                        System.out.print("error to get user list: " + e);
                    }
                %>                    
            </div>            
        </div>            
        <%@include file="footer.jsp" %>
    </body></html>
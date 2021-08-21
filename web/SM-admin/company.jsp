<%@page import="java.sql.ResultSet"%>
<%@page import="a.wk.sm.database.db_queries"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="./files/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="./files/jquery-ui-1.9.2.custom_spinner.css">
        <link rel="stylesheet" href="./files/commonlayout.css">
        <link rel="stylesheet" href="./files/elements.css">
        <link rel="stylesheet" href="./files/Custome_Buttons.css">
        <link rel="stylesheet" href="./files/jquery.mCustomScrollbar.css">
        <link rel="stylesheet" href="./files/user_home.css">
        <link rel="stylesheet" href="./files/dilbag.css">
        <!--
        <script type="text/JavaScript" src="./files/SMNamespace.js"></script>  
        <script type="text/JavaScript" src="./files/jquery-1.9.0.js"></script>  
        <script type="text/JavaScript" src="./files/jquery-ui-1.10.0.custom.js"></script>  
        <script type="text/JavaScript" src="./files/jquery-ui-1.9.2.custom_spinner.js"></script>  
        <script type="text/JavaScript" src="./files/filterlist.js"></script>  
        <script type="text/JavaScript" src="./files/jquery.customSelect.js"></script>  
        <script type="text/javascript" src="./files/jquery.mCustomScrollbar.js"></script>  
        <script src="./files/jquery.mousewheel.min.js"></script>  
        <script type="text/JavaScript" src="./files/jquery.tooltipster.js"></script>  
        <script type="text/JavaScript" src="./files/jquery.dataTables.js"></script>  
        <script type="text/JavaScript" src="./files/common.js"></script>  
        <script type="text/JavaScript" src="./files/jquery.validate.js"></script>  
        <script type="text/JavaScript" src="./files/additional-methods.js"></script>  
        <script type="text/JavaScript" src="./files/dropdownfiller.js"></script>  
        <script type="text/JavaScript" src="./files/SMNamespace%281%29.js"></script>  
        <script type="text/JavaScript" src="./files/footer.js"></script>  
        <script type="text/JavaScript" src="./files/ajax_loader.js"></script>
        <script src="./files/jquery.chatjs.longpollingadapter.js" type="text/javascript"></script>  
        <script src="./files/jquery.autosize.min.js" type="text/javascript"></script>  
        <script src="./files/jquery.activity-indicator-1.0.0.min.js" type="text/javascript"></script>  
        <script src="./files/jquery.chatjs.js" type="text/javascript"></script>  
        <script type="text/JavaScript" src="./files/user_home.js"></script-->  
        <script type="text/JavaScript" src="./files/jquery-1.9.0.js"></script>  
        <script type="text/JavaScript" src="./files/dilbag.js"></script>           
        <title>Supply Medium</title>  
        <link rel="stylesheet" href="./files/userheader.css">
        <link rel="stylesheet" href="./files/notifi_dropdown.css">
        <link rel="stylesheet" href="./files/ResetCSS.css">
        <link rel="stylesheet" href="./files/Cus_Menu.css">
        <link rel="stylesheet" href="./files/commonlayout.css">
        <link rel="stylesheet" href="./files/elements.css">
        <link rel="stylesheet" href="./files/usermgmt.css">
        <link rel="stylesheet" href="./files/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="./files/tablestyle.css">
        <link rel="stylesheet" href="./files/Custome_Buttons.css">
        <link rel="stylesheet" href="./files/usermgmt_popup.css">
        <link rel="stylesheet" href="./files/Custome_Popup.css">
        <link rel="stylesheet" href="./files/popup_warning.css">
        <link rel="stylesheet" href="./files/Cus_Toast.css">
        <link rel="stylesheet" href="./files/ajax_loader.css">
        <link rel="stylesheet" href="./files/Custome_Popup.css">
        <link rel="stylesheet" href="./files/footer.css">
        <link rel="stylesheet" href="./files/term.css">
        <link rel="stylesheet" href="./files/privacy.css">

    </head>
    <body onload="lst_id = 'admn_usrs'">
        <% request.setAttribute("slctd_tb","cmpny"); %>
        <%@include file="/SM-admin/header.jsp" %>
        <!--div class="admin_companies_more_detail" id="cmpny_mr_dtl" onclick="$('#cmpny_mr_dtl').fadeOut()">
            <h2>Credentials</h2><br/>
            Disk Quote<br/>
            No. of Users<br/>
            Group</br>
            Department<br/>
        </div-->
        <div class="contentcontainer admin_container">
            <div class="nav_Sub Gen_nav">
                <ul id="SubAdmin" style="display: block;">
                    <li id="SubAdmin_User" class="navsubSelected" onclick="fade_in_out('admn_usrs');"><a title="Users">List of registered company</a></li>
                </ul>
            </div>
            <div class="admin_companies">
                <div class="admin_companies_div" style="background:#008bbd;color:white;padding-top: 0px;padding-bottom: 0px;"><span>Name</span><span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;">Type</span><span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;">Pricing Option</span><span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;">Email id</span><span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;">Address</span><span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;">Country</span><span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;">Contact Number</span><span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;">Status</span><span>More Detail</span></div>    
                <%
                    HttpSession ses = request.getSession();
                    if (ses.getAttribute("admn_id") == null) {
                        response.sendRedirect("/SupplyMedium/SM-admin/index.jsp?fail=5");
                    }

                    try {
                        String clr = "#f2f6f7";
                        db_queries db_qrs = new db_queries();
                        ResultSet rs = db_qrs.select("select cr.company_name,cr.company_type,cr.pricing_option,cr.company_phoneno,cr.company_status,cr.email,md.address,md.country_name from company_registration cr join mailing_address md on cr.email=md.email");
                        while (rs.next()) {                            
                            out.print("<div class='admin_companies_div' style='background:" + clr + "'><span>" + rs.getString("cr.company_name") + "</span><span>" + rs.getString("cr.company_type") + "</span><span>" + rs.getString("cr.pricing_option") + "</span><span>" + rs.getString("cr.email") + "</span><span>" + rs.getString("md.address") + "</span><span>" + rs.getString("md.country_name") + "</span><span>" + rs.getString("cr.company_phoneno") + "</span><span>" + rs.getString("cr.company_status") + "</span><span style='cursor:pointer' onclick=\"javascript:cmpny_mr_dtl('" + rs.getString("cr.company_phoneno") + "','" + rs.getString("cr.company_name") + "');\">More Detail</span><div id='cmpny_mr_dtl_" + rs.getString("cr.company_phoneno") + "' class='admin_companies_more_detail' style='background:" + clr + "'>Getting data...</div></div>");                            
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
        <%@include file="/SM-admin/fotter.jsp" %>

        <!--<script type="text/JavaScript" src="./files/restrict_menu.js"></script>-->
    </body></html>
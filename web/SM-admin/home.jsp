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
        <% request.setAttribute("slctd_tb","usr"); %>
        <%@include file="/SM-admin/header.jsp" %>
        <div class="contentcontainer admin_container">
            <div class="nav_Sub Gen_nav">
                <ul id="SubAdmin" style="display: block;">
                    <li id="SubAdmin_User2" class="navsubSelected" onclick="fade_in_out('admn_usrs');document.getElementById('SubAdmin_User2').className='navsubSelected';document.getElementById('SubAdmin_Group2').className='';"><a title="Users">List of User</a></li>
                    <li style="width: 2px;"> <br>
                    </li> 
                    <li id="SubAdmin_Group2" onclick="fade_in_out('admn_ad_usr');document.getElementById('SubAdmin_Group2').className='navsubSelected';document.getElementById('SubAdmin_User2').className='';"><a title="Groups">Add User</a></li>
                    <li style="width: 2px;"> <br>
                    </li>
                </ul>
            </div>
            <div class="admin_users" id="admn_usrs">
                <div class="admin_users_div" style="background:#008bbd;color:white;padding-top: 0px;padding-bottom: 0px;"><span>Email Id</span><span style="border-left:1px solid #ffffff;border-right:1px solid #ffffff;">Status</span><span>Update/remove</span></div>    
                <%
                    HttpSession ses = request.getSession();
                    if (ses.getAttribute("admn_id") == null) {
                        response.sendRedirect("/SupplyMedium/SM-admin/index.jsp?fail=5");
                    }

                    try {
                        String clr = "#f2f6f7";
                        int sts = 3;
                        String usr_sts = "Block";
                        db_queries db_qrs = new db_queries();
                        ResultSet rs = db_qrs.select("select email,password,status,wk_user_id from wk_user where status !=2");
                        while (rs.next()) {
                            sts = rs.getInt("status");
                            if (sts == 0) {
                                usr_sts = "Active";
                            } else {
                                usr_sts = "Block";
                            }
                            out.print("<div id='usr_" + rs.getInt("wk_user_id") + "' class='admin_users_div' style='background:" + clr + "'><span>" + rs.getString("email") + "</span><span>" + usr_sts + "</span><span><a onclick=\"javascript:chng_admn_usr_dtl('" + rs.getInt("wk_user_id") + "','" + rs.getString("email") + "','" + rs.getInt("status") + "','" + rs.getString("password") + "')\"; style='cursor:pointer'>Update</a>&nbsp;&nbsp;&nbsp<a onclick=admn_dlt_usr('" + rs.getInt("wk_user_id") + "') style='cursor:pointer'>Remove</a></span></div>");
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
            <div class="admin_users" id="admn_ad_usr" style="display: none;">
                <form method="post" action="/SupplyMedium/controller">
                    <input type="hidden" name="typ" value="admn_ad_nw_usr"/>    
                    <div class="admin_add_user_box">                    
                        <div><span><b>Email</b></span><span><input type="text" name="eml" id="eml" onblur="vldt_usr_eml(this.value);"/></span></div>
                        <div><span><b>Password</b></span><span><input type="password" name="pswrd" id="pswrd"/></span></div>
                        <div><span><b>Confirm Password</b></span><span><input type="password" name="cnfrm_pswrd" id="cnfrm_pswrd"/></span></div>
                        <div><span></span><span><button type="submit" onclick="return validate_admin_add_new()">Add</button></span></div>
                        <div><span id="admn_ad_usr_er" style="color: red;"></span><span></span></div>
                    </div>
                </form>    
            </div>
        </div>
        <%@include file="/SM-admin/fotter.jsp" %>

        <!--<script type="text/JavaScript" src="./files/restrict_menu.js"></script>-->
    </body></html>
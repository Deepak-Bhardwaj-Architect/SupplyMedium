<%@page import="supply.medium.home.bean.CompleteUserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<!-- saved from url=(0048)http://localhost:8084/SupplyMedium/user_home.jsp -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK 
            rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <LINK rel="stylesheet" 
              href="inside/jquery-ui-1.9.2.custom_spinner.css">
        <LINK rel="stylesheet" 
              href="inside/commonlayout.css">
        <LINK rel="stylesheet" href="inside/elements.css">
        <LINK rel="stylesheet" href="inside/Custome_Buttons.css">
        <LINK rel="stylesheet" 
              href="inside/jquery.mCustomScrollbar.css">
        <LINK rel="stylesheet" href="inside/user_home.css">
        <LINK rel="stylesheet" href="inside/dilbag.css">
        <!-- Chat JS style -->
        <LINK 
            rel="stylesheet" type="text/css" href="inside/jquery.chatjs.css">
        <SCRIPT type="text/JavaScript" src="inside/SMNamespace.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.9.2.custom_spinner.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/filterlist.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.customSelect.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.mCustomScrollbar.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.dataTables.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/common.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.validate.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/additional-methods.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dropdownfiller.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/SMNamespace(1).js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/footer.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/ajax_loader.js">
        </SCRIPT>
        <!-- ChatJS and dependencies -->
        <SCRIPT type="text/javascript" src="inside/jquery.chatjs.longpollingadapter.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.autosize.min.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.activity-indicator-1.0.0.min.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.chatjs.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/user_home.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dilbag.js">
        </SCRIPT>
        <TITLE>Supply Medium</TITLE>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onLoad="lockUnlock('webkrit_content_loader');Usr_anlys('Admin');
            customizeMenu();
            pypl_rslt('null');">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
<!--    <DIV style="min-height: 700px; background-color: rgb(241, 241, 241);" id="mainpage" 
         class="mainpage">-->
        <TITLE>News Room</TITLE>
        <LINK rel="stylesheet" href="inside/newsroom.css">
        <LINK rel="stylesheet" href="inside/newsroom_feed.css">
        <LINK rel="stylesheet" 
              href="inside/view_members.css">
        <!--<link rel="stylesheet" href="/SupplyMedium/dilbag.css">-->
        <!---->
        <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <!--<link rel="stylesheet" href="inside/commonlayoutusr.css">
            <link rel="stylesheet" href="inside/elementsusr.css">
            <link rel="stylesheet" href="inside/jquery-ui-1.10.0.customusr.css">
            <link rel="stylesheet" href="inside/Custome_Buttonsusr.css">-->
            <link rel="stylesheet" href="inside/usermgmt.css">
            <link rel="stylesheet" href="inside/tablestyle.css">
            <link rel="stylesheet" href="inside/usermgmt_popup.css">
            <title>Supply Medium</title>
            <div class="pagetitlecontainer">
                <div class="pagetitle">Users</div>
            </div>
            <div id="sv_dtl_cnfrmtn_msg_dv" class="update_user_detail2" onClick="$('#sv_dtl_cnfrmtn_msg_dv').fadeOut();">User detail has been updated</div>
            <div class="page">
                <div class="contentcontainer" style="min-height:0px;">
                    <form name="usermgmtfrm" id="usermgmtfrm" style="min-height:600px;" novalidate="novalidate">
                        <div id="usermgmt_content" style="display:block">
                            <div class="tabbar">
                                <div class="usermgmterr" id="usertblerr">
                                </div>
                                <div class="highlight" id="user_details" style="display:block;"><a href="adminListUsers.jsp">List Of Users</a></div>
                                <div class="normal deactive" id="new_users" style="display:block;"><a href="adminNewUser.jsp">Add User</a></div>
                            </div>
                            <div class="userdetailscontent" id="userdetails_content" style="display: block;">
                                <div class="tablecontent" id="table_content" style="position:relative;">
                                    <div class="DT_border">
                                    </div>
                                    <div id="UserList_wrapper" class="dataTables_wrapper" role="grid">
                                        <div class="dataTables_filter" id="UserList_filter">
                                            <label>Search <input type="text" autocomplete="off" aria-controls="UserList">
                                            </label>
                                        </div>
                                        <div class="fixed_height">
                                            <table id="UserList" style="width: 997px;" class="dataTable" aria-describedby="UserList_info">
                                                <thead>
                                                    <tr role="row">
                                                        <th class="rowBorder sorting_asc" role="columnheader" tabindex="0" aria-controls="UserList" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Name: activate to sort column descending" style="width: 200px;">Name</th>
                                                        <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="UserList" rowspan="1" colspan="1" aria-label="City: activate to sort column ascending" style="width: 200px;">City</th>
                                                        <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="UserList" rowspan="1" colspan="1" aria-label="Email: activate to sort column ascending" style="width: 200px;">Email</th>
                                                        <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="UserList" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending" style="width: 200px;">Status</th>
                                                        <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="UserList" rowspan="1" colspan="1" aria-label="State: activate to sort column ascending" style="width: 129px;">State</th>
                                                        <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="UserList" rowspan="1" colspan="1" aria-label="Action: activate to sort column ascending" style="width: 70px;" onclick="if(selectedBy===null){ selectedBy='asending'; }else if(selectedBy==='asending'){selectedBy='desending'; }else if(selectedBy==='desending'){selectedBy='asending'; };showCompanyUserByAsending();">Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody role="alert" aria-live="polite" aria-relevant="all" id="listOfUser">
                                               <%
                                     ArrayList users = UserMaster.showAllUserOfCompany(session.getAttribute("companyKey").toString(), session.getAttribute("userKey").toString());
                                    //out.print("users"+users);
                                            
                                     CompleteUserBean scb = null;
                                     String rowval="";
                                    for (int i = 0; i < users.size(); i++) {
                                        scb = (CompleteUserBean) users.get(i);
                                        if(i%2==0)
                                            rowval="class='even'";
                                        else
                                            rowval="class='odd'";
                                %>
                                <tr id="row_<%=i %>" <%=rowval%>>
                                                    <td  onclick="selectUserRow('<%=i %>','<%=scb.getUserKey() %>','<%=scb.getAccountStates() %>');" class="sorting_1 rowBorder"><%=scb.getFirstName()%> <%=scb.getLastName()%> </td>
                                                    <td  onclick="selectUserRow('<%=i %>','<%=scb.getUserKey() %>','<%=scb.getAccountStates() %>');" class="rowBorder"><%=scb.getCity()%></td>
                                                    <td  onclick="selectUserRow('<%=i %>','<%=scb.getUserKey() %>','<%=scb.getAccountStates() %>');" class="rowBorder"><%=scb.getEmail()%></td>
                                                    <td id="column_<%=i %>" class="rowBorder"><%=scb.getAccountStates()%></td>
                                                    <td class="rowBorder">Not Connected</td>
                                                    <td  id="action_<%=i %>" class="rowBorder"></td>
                                                </tr>
                                <%
                                    }
                                %> 
<!--                                                <tr class="odd row_selected">
                                                    <td class="sorting_1 rowBorder">lokesh kakkar</td>
                                                    <td class="rowBorder">Zirakpur</td>
                                                    <td class="rowBorder">kakkar.lokesh@gmail.com</td>
                                                    <td class="rowBorder">
                                                        <select name="sel0" style="z-index:200;width:120px;margin-right:5px;margin-top:5px;" class="" id="sel0">
                                                            <option value="Pending">Pending</option>
                                                            <option value="Activated">Active</option>
                                                            <option value="Rejected">Rejected</option>
                                                            <option value="Blocked">Blocked</option>
                                                        </select>
                                                        <input type="button" style="cursor:pointer" class="status_save_btn" title="Save Status" onclick="saveStatus(0, & quot; Pending & quot; )">
                                                    </td>
                                                    <td class="rowBorder">Not Connected</td>
                                                    <td class="rowBorder">
                                                        <img src="inside/user_edit_btn.png" style="cursor:pointer;z-index:100;margin-right:10px;" title="Update User Settings" onclick="javascript:openEditView(0, 'kakkar.lokesh@gmail.com')">
                                                        <img src="inside/user_delete_btn.png" style="cursor:pointer;z-index:101;" title="Delete User" onclick="deleteUser(0);">
                                                    </td>
                                                </tr>-->
                                            </tbody>
                                            </table>
                                        </div>
                                        <div class="dataTables_info" id="UserList_info">Showing 1 to 1 of 1 entries</div>
                                        <div class="dataTables_paginate paging_full_numbers" id="UserList_paginate">
                                            <a tabindex="0" class="first paginate_button paginate_button_disabled" id="UserList_first">First</a>
                                            <a tabindex="0" class="previous paginate_button paginate_button_disabled" id="UserList_previous">Previous</a>
                                            <span>
                                                <a tabindex="0" class="paginate_active">1</a>
                                            </span>
                                            <a tabindex="0" class="next paginate_button paginate_button_disabled" id="UserList_next">Next</a>
                                            <a tabindex="0" class="last paginate_button paginate_button_disabled" id="UserList_last">Last</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Insert title here</title>
            <link rel="stylesheet" href="inside/Custome_Popup.css">
            <link rel="stylesheet" href="inside/popup_warning.css">
            <div id="warning_container" style="display: none;">
                <div id="warning_popup">
                    <div id="war_head">
                        <label id="war_head_title">Warning</label>
                    </div>
                    <div id="war_body">
                        <label id="war_message">
                        This operation will delete the user permanently. Do you want to continue?    
                        </label>
                        <div id="war_btns">
                            <input type="button" id="okbtn" style="float:left;margin-right:30px;" class="pop-button pop-button-White" value="Yes" onclick="deleteSelectedUser();$('#warning_container').fadeOut();">
                            <input id="Popup_Cancel" style="float:left;" type="button" class="pop-button pop-button-White" value="No" onclick="$('#warning_container').fadeOut();">
                        </div>
                    </div>
                </div>
            </div>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Toast Window </title>
            <link rel="stylesheet" href="inside/Cus_Toast.css">
            <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
            <div id="Toast_Window" style="display:none;">
                <p class="Toast_Data">
                </p>
            </div>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Insert title here</title>
            <link rel="stylesheet" href="inside/Custome_Popup.css">
            <div style="display: none;" id="policies_popup" class="Custome_Popup_Window">
                <table>
                    <tbody>
                        <tr>
                            <td style="vertical-align: middle;">
                                <div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout" style="margin:0px ! important;height:480px  ! important; ">
                                    <div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
                                        User Settings
                                        <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" onclick="$('#policies_popup').fadeOut();">
                                        </div>
                                    </div>
                                    <div style="width:100%;height:auto!important;z-index:999999;background:#ffffff;overflow:hidden!important;" id="user_settings">
                                    Getting Data...
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <input type="hidden" id="emailId">
            <input type="hidden" id="rowno">
            <%@include file="_footer.jsp" %>
            <div>
            </div>
        </div>
        <%@include file="_invite.jsp" %>
        <SCRIPT type="text/JavaScript" src="inside/restrict_menu.js">
        </SCRIPT>
        <SCRIPT>


            $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt_fieldvalidator.js");
            $.getScript("/SupplyMedium/Views/Registration/js/regvalidator.js");
            $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt.js", function(data, textStatus, jqxhr) {
                userOnload();

            });

        </SCRIPT>
    </BODY>
</HTML>

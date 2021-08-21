<%@page import="supply.medium.home.bean.GroupBean"%>
<%@page import="supply.medium.home.database.GroupMaster"%>
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
        <SCRIPT type="text/JavaScript" src="inside/foldermanage.js">
        </SCRIPT>
        <TITLE>Supply Medium</TITLE>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onLoad="lockUnlock('webkrit_content_loader');
            Usr_anlys('Admin');
            customizeMenu();
            pypl_rslt('null');">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <DIV style="min-height: 700px; background-color: rgb(241, 241, 241);" id="mainpage" 
         class="mainpage">
        <TITLE>News Room</TITLE>
        <LINK rel="stylesheet" href="inside/newsroom.css">
        <LINK rel="stylesheet" href="inside/newsroom_feed.css">
        <LINK rel="stylesheet" 
              href="inside/view_members.css">
        <!--<link rel="stylesheet" href="/SupplyMedium/dilbag.css">-->
        <!---->
        <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Supply Medium</title>
            <link rel="stylesheet" href="inside/commonlayout.css">
            <link rel="stylesheet" href="inside/elements.css">
            <link rel="stylesheet" href="inside/managefolder.css">
            <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
            <link rel="stylesheet" href="inside/Custome_Buttons.css">
            <link rel="stylesheet" href="inside/jquery.mCustomScrollbar.css">
            <div class="pagetitlecontainer">
                <div class="pagetitle">Manage Folders</div>
                <div class="pagetitle" style="color:black;font-size:12px;height:48px;line-height:14px;">
                    Manage Folder section is used to provide the privileges to group users / department groups by Admin
                    <br> to provide the permissions to access the files as Read Only / Read and Write
                </div>
            </div>
            <div class="page">
                <input type="hidden" id="companyKey" value="<%=session.getAttribute("companyKey") %>" />
                <div class="contentcontainer" style="min-height: 600px; background-color: white;">
                    <div id="manage_fol_content" style="">
                        <div class="groupstyle">
                            <div class="sectiontitle">
                                <div class="sectiontext">Group Users</div>
                            </div>
                            <div class="mf_group mCustomScrollbar _mCS_3" id="mf_group">
                                <%                                    ArrayList groupList = GroupMaster.showCompanyGroup(companyKey);
                                    GroupBean gb = null;
                                    for (int i = 0; i < groupList.size(); i++) {
                                        gb = (GroupBean) groupList.get(i);
                                %>
                                <div class="mf_main" id="group_box_<%=gb.getGroupKey()%>" onclick="">
                                    <div id="group_plus_minus_<%=gb.getGroupKey()%>" class="expand_plus" onclick="showGroupUser('<%=gb.getGroupKey()%>');"></div>
                                    <% if (i == 0) { %>
                                    <div class="group_bottom"></div>
                                    <% } else if (i == groupList.size()-1) { %>
                                    <div class="group_top"></div>
                                    <% } else { %>
                                    <div class="group"></div>
                                    <% }%> 
                                     
                                    <div class="mf_main_text" id="group_seprator_<%=gb.getGroupKey()%>"><%=gb.getGroupName()%></div>
                                       
                                </div>
                                    <div style="float:left;display:none; " id="group_users_<%=gb.getGroupKey()%>">
                                    </div>
                                 <% }%>
                            </div>
                        </div>
                        <div class="deptstyle">
                            <div class="deptlist">
                                <div class="sectiontitle">
                                    <div class="sectiontext">Department groups</div>
                                </div>
<!--                                <div id="mf_dept" class="mf_dept_content">
                                    <div class="mf_main" id="34534534:development">
                                        <div id="collapse_34534534:development" class="collapse_minus">
                                        </div>
                                        <div class="group_bottom">
                                        </div>
                                        <div class="mf_main_text">development (2)</div>
                                    </div>
                                    <div style="float: left;" id="34534534:development_content">
                                        <div class="mf_sub" id="34534534:videos_development">
                                            <div class="t_shape">
                                            </div>
                                            <div class="folder_only_closed">
                                            </div>
                                            <div class="mf_content_text" id="34534534:videos_development_text">videos</div>
                                        </div>
                                        <div class="mf_sub" id="34534534:images_development">
                                            <div class="t_shape">
                                            </div>
                                            <div class="folder_only_closed">
                                            </div>
                                            <div class="mf_content_text" id="34534534:images_development_text">images</div>
                                        </div>
                                    </div>
                                    <div class="mf_main" id="34534534:testing">
                                        <div id="collapse_34534534:testing" class="collapse_minus">
                                        </div>
                                        <div class="group_top">
                                        </div>
                                        <div class="mf_main_text">testing (0)</div>
                                    </div>
                                    <div style="float: left;" id="34534534:testing_content">
                                    </div>
                                </div>-->
                            </div>
                            <div class="folderaccess">
                                <br>
                                <div class="checkContainer" style="margin-left:10px;">
                                    <input type="checkbox" name="read" id="read">
                                    <div>
                                    </div>
                                </div>
                                <label class="mf_content_text" style="line-height:17px;" for="read">&nbsp;Read Only
                                </label>
                                <br>
                                <br>
                                <div class="checkContainer" style="margin-left:10px;">
                                    <input type="checkbox" name="readwrite" id="readwrite">
                                    <div>
                                    </div>
                                </div>
                                <label class="mf_content_text" style="line-height:17px;" for="readwrite">&nbsp;Read and Write
                                </label>
                            </div>
                        </div>
                        <div class="mf_button">
                            <input type="button" class="gen-btn-Orange" onClick="updatePrivileges();" value="Update">
                        </div>
                    </div>
                </div>
            </div>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <%@include file="_footer.jsp"%>
            <div>
            </div>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Toast Window </title>
            <link rel="stylesheet" href="inside/Cus_Toast.css">
            <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
            <div id="Toast_Window" style="display:none;">
                <p class="Toast_Data">
                </p>
            </div>
        </div>
        <%@include file="_invite.jsp"%>
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

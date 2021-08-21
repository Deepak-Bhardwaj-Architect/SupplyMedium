<%@page import="supply.medium.home.bean.GroupBean"%>
<%@page import="supply.medium.home.database.GroupMaster"%>
<%@page import="supply.medium.home.bean.DepartmentBean"%>
<%@page import="supply.medium.home.database.DepartmentMaster"%>
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
        <SCRIPT type="text/JavaScript" src="inside/deptmgmt.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/deptpopup.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/companydepartment.js">
        </SCRIPT>
        <TITLE>Supply Medium</TITLE>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onload="lockUnlock('webkrit_content_loader');showNonExistingUserInDepartment();showExistingUserInDepartment();showNonExistingGroupInDepartment();showExistingGroupInDepartment();showNonExistingFolderInDepartment();showExistingFolderInDepartment();">
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
            <link rel="stylesheet" href="inside/commonlayout.css">
            <link rel="stylesheet" href="inside/elements.css">
            <link rel="stylesheet" href="inside/deptmgmt.css">
            <link rel="stylesheet" href="inside/deptpopup.css">
            <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
            <link rel="stylesheet" href="inside/Custome_Buttons.css">
            <div class="pagetitlecontainer">
                <div class="pagetitle">Departments</div>
            </div>
            <div class="page" style="height:1010px!important;">
                <input type="hidden" id="companyKey" value="<%=session.getAttribute("companyKey") %>" />
                <input type="hidden" id="userKey" value="<%=session.getAttribute("userKey") %>" />
                <input type="hidden" id="departmentKey" />
                <input type="hidden" id="departmentName" />
                <input type="hidden" id="departmentUserKey" />
                <input type="hidden" id="groupKey" />
                <input type="hidden" id="folderKey" />
                
                <div class="contentcontainer" style="height:1010px!important;">
                    <div class="content" id="dept_content" style="height:1010px!important;">
                        <form id="deptmgmtfrm" name="deptmgmtfrm" style="float:left;height:968px!important;">
                            <div class="deptlist">
                                <div class="deptmgmtbtns">
                                    <input type="button" class="okbtn new_dept_btn" id="adddeptbtn" value="Add" style="margin-right:5px;">
                                    <input type="button" class="okbtn edit_dept_btn" id="renamedeptbtn" value="Rename" style="margin-right:5px;">
                                    <input type="button" class="delbtn del_dept_btn" id="deldeptbtn" value="Delete">
                                </div>
                                <div class="deptlistsearch" style="margin-right: 30px;">
                                    <input type="text" autocomplete="off" id="searchdept" class="searchdept" onKeyUp="myfilter.set(this.value)">
                                </div>
                                <div class="assignhead">Assign Users to Department</div>
                                <br>
                                <br>
                                <!-- Departments list -->
                                <div class="deptlistbox">
                                    <div class="deptlisthead ctrlheading">List of Departments</div>
                                    <select class="deptlistsel listbox" name="deptlistsel" id="deptlistsel" size="5" onChange="$('#departmentKey').val(this.value);showNonExistingUserInDepartment();showExistingUserInDepartment();showNonExistingGroupInDepartment();showExistingGroupInDepartment();showNonExistingFolderInDepartment();showExistingFolderInDepartment();//getDeptData(this.value);">
                                        <% 
                                        String companyKey2=session.getAttribute("companyKey").toString();
                                        ArrayList countryList = DepartmentMaster.showCompanyDepartment(companyKey2);
                                        DepartmentBean scb = null;
                                        for (int i = 0; i < countryList.size(); i++) {
                                        scb = (DepartmentBean) countryList.get(i);
                                        %>
                                        <option value="<%=scb.getDepartmentKey()%>" <% if(i==0){ out.print("selected"); } %>><%=scb.getDepartmentName() %></option>
                                        <% } %>
                                    </select>
                                </div>
                                <div class="gap">
                                </div>
                                <!-- Assign Users to department -->
                                <div class="deptgroupbox">
                                    <div class="groupboxhead ctrlheading">Existing Users</div>
                                    <select multiple="multiple" class="existusersel listbox" name="existusersel" id="existusersel" size="5">
                                        
                                    </select>
                                </div>
                                <div class="addremove">
                                    <div class="addbtn" id="adduserbtn">
                                    </div>
                                    <div class="removebtn" id="removeuserbtn">
                                    </div>
                                </div>
                                <div class="deptgroupbox">
                                    <div class="groupboxhead ctrlheading">Department Users</div>
                                    <select multiple="multiple" class="deptusersel listbox" name="deptusersel" id="deptusersel" size="5">
                                    </select>
                                </div>
                            </div>
                            <div class="deptassign">
                                <!-- Department Management and user assign errors -->
                                <div id="deptmgmterr" class="depterror">
                                </div>
                                <div id="userassignerr" class="depterror">
                                </div>
                                <!-- Assign Department and folder to department headings-->
                                <div class="headings">
                                    <div class="assignhead" style="margin-right: 195px;">Assign
                                        Groups to Department</div>
                                    <div class="assignhead" style="width:270px;">Assign Folders to Department</div>
                                    <div class="managefolder">
                                        <input type="button" class="okbtn new_fol_btn" id="addfolderbtn" value="Add Folder" style="margin-right:5px;">
                                        <input type="button" class="delbtn del_fol_btn" id="deletefolderbtn" value="Delete Folder">
                                    </div>
                                </div>
                                <!-- Assign Department to department -->
                                <div class="deptgroupbox">
                                    <div class="groupboxhead ctrlheading">Existing Groups</div>
                                    <select class="existgroupsel listbox" name="vvv" id="existgroupsel" size="5">
                                    </select>
                                </div>
                                <div class="addremove">
                                    <div class="addbtn" id="addgroupbtn">
                                    </div>
                                    <div class="removebtn" id="removegroupbtn">
                                    </div>
                                </div>
                                <div class="deptgroupbox">
                                    <div class="groupboxhead ctrlheading">Department Groups</div>
                                    <select class="pregroupsel listbox" name="pregroupsel" id="pregroupsel" size="5">
                                    </select>
                                </div>
                                <div class="gap">
                                </div>
                                <!-- Assign folder to department -->
                                <div class="deptgroupbox">
                                    <div class="groupboxhead ctrlheading">Existing Folders</div>
                                    <select class="existfoldersel listbox" name="existfoldersel" id="existfoldersel" size="5">
                                        
                                    </select>
                                </div>
                                <div class="addremove">
                                    <div class="addbtn" id="assignfolderbtn">
                                    </div>
                                    <div class="removebtn" id="removefolderbtn">
                                    </div>
                                </div>
                                <div class="deptgroupbox">
                                    <div class="groupboxhead ctrlheading">Departments Folders</div>
                                    <select class="deptfoldersel listbox" name="deptfoldersel" id="deptfoldersel" size="5">
                                    </select>
                                </div>
                            </div>
                            <!-- Department assign and folder assign error -->
                            <div id="groupassignerr" class="depterror">
                            </div>
                            <div id="folderassignerr" class="depterror">
                            </div>
                            <!-- Privileges settings -->
<!--                            <div class="deptpriv">
                                <fieldset class="privfieldset">
                                    <legend>Privileges</legend>
                                    <div class="listitem">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="addfolder">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="addfolder" class="prilbl">Add Folder on Department Page</label>
                                        <br>
                                    </div>
                                    <div class="listitem">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="delfolder">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="delfolder" class="prilbl">Delete Folder on Department Page</label>
                                        <br>
                                    </div>
                                    <div class="listitem">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="addfile">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="addfile" class="prilbl">Add Files on the Department page</label>
                                        <br>
                                    </div>
                                    <div class="listitem">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="delfile">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="delfile" class="prilbl">Delete Files on the Department Page</label>
                                        <br>
                                    </div>
                                    <div class="listitem">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="postAnno">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="postAnno" class="prilbl">Post Announcement</label>
                                        <br>
                                    </div>
                                      <div class="listitem">
                                    <div class="checkContainer">
                                    <input type="checkbox" class="checkbox" id="managefold">
                                    <div>
                                    </div>
                                    </div>
                                    <label for="managefold" class="prilbl">Manage Folder</label>
                                    <BR />
                                    </div>
                                    <div class="deptupt">
                                        <input type="button" id="updatepribtn" value="Update" class="gen-btn-Orange">
                                    </div>
                                    <div id="deptprierr" class="depterror">
                                    </div>
                                </fieldset>
                            </div>-->
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
                                        </label>
                                        <div id="war_btns">
                                            <input type="button" id="okbtn" style="float:left;margin-right:30px;" class="pop-button pop-button-White" value="Yes">
                                            <input id="Popup_Cancel" style="float:left;" type="button" class="pop-button pop-button-White" value="No">
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
                            <link rel="stylesheet" href="inside/RestCSS.css">
                            <div style="display: none;" id="add_group_popup" class="Custome_Popup_Window">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td style="vertical-align: middle;">
                                                <div class="Popup_Outline_NewDepartment Cus_Popup_Outline popuplayout">
                                                    <div class="Popup_Tilte_NewDepartment Gen_Popup_Title">
                                                        New Department
                                                        <div class="Popup_Close_NewDepartment Gen_Cus_Popup_Close">
                                                        </div>
                                                    </div>
                                                    <div class="popupcontent">
                                                        <div class="row">
                                                            <div class="label" style="width:150px;">Department Name</div>
                                                            <input type="text" autocomplete="off" id="new_dept_input" name="new_dept_input" class="textbox required" style="width:300px;height:20px;">
                                                            <label id="new_dept_input_err" style="margin-left:150px" class="error">
                                                            </label>
                                                        </div>
                                                        <div class="row" style="margin-top:60px;margin-left:120px;">
                                                            <input id="cancel_dept" onclick="$('#add_group_popup').fadeOut();" type="button" style="margin-right:20px" class="gen-btn-Gray" value="Cancel">
                                                            <input type="button" id="save_dept" class="gen-btn-Orange" value="Save">
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                            <title>Insert title here</title>
                            <link rel="stylesheet" href="inside/Custome_Popup.css">
                            <link rel="stylesheet" href="inside/RestCSS.css">
                            <div style="display: none;" id="edit_group_popup" class="Custome_Popup_Window">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td style="vertical-align: middle;">
                                                <div class="Popup_Outline_NewDepartment Cus_Popup_Outline popuplayout">
                                                    <div class="Popup_Tilte_NewDepartment Gen_Popup_Title ">
                                                        Rename Department
                                                        <div class="Popup_Close_NewDepartment Gen_Cus_Popup_Close">
                                                        </div>
                                                    </div>
                                                    <div class="popupcontent">
                                                        <div class="row">
                                                            <div class="label" style="width:150px;">Department Name</div>
                                                            <input type="text" autocomplete="off" id="edit_dept_input" name="edit_dept_input" class="textbox required" style="width:300px;height:20px;">
                                                            <label id="edit_dept_input_err" style="margin-left:150px" class="error">
                                                            </label>
                                                        </div>
                                                        <div class="row" style="margin-top:60px;margin-left:120px;">
                                                            <input id="edit_cancel_dept" onclick="$('#edit_group_popup').fadeOut();" type="button" style="margin-right:20px" class="gen-btn-Gray" value="Cancel">
                                                            <input type="button" id="edit_dept" class="gen-btn-Orange" value="Save">
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                            <title>Insert title here</title>
                            <link rel="stylesheet" href="inside/Custome_Popup.css">
                            <link rel="stylesheet" href="inside/ResetCSS.css">
                            <div style="display: none;" id="add_folder_popup" class="Custome_Popup_Window">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td style="vertical-align: middle;">
                                                <div class="Popup_Outline_NewDepartment Cus_Popup_Outline popuplayout">
                                                    <div class="Popup_Tilte_NewDepartment Gen_Popup_Title ">
                                                        New Folder Creation
                                                        <div class="Popup_Close_NewDepartment Gen_Cus_Popup_Close">
                                                        </div>
                                                    </div>
                                                    <div class="popupcontent">
                                                        <div class="row">
                                                            <div class="label" style="width:150px;">Folder Name</div>
                                                            <input type="text" autocomplete="off" id="new_folder_input" name="new_folder_input" class="textbox required" style="width:300px;height:20px;">
                                                            <label id="new_folder_input_err" style="margin-left:150px" class="error">
                                                            </label>
                                                        </div>
                                                        <div class="row" style="margin-top:60px;margin-left:120px;">
                                                            <input id="cancel_folder"  onclick="$('#add_folder_popup').fadeOut();" type="button" style="margin-right:20px" class="gen-btn-Gray" value="Cancel">
                                                            <input type="button" id="save_folder" class="gen-btn-Orange" value="Save">
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <%@include file="_footer.jsp"%>
            <div>
            </div>
            <!--  <script defer="defer" type="text/JavaScript" src="/SupplyMedium/Views/DeptMgmt/js/deptmgmt.js">
            </script>
            <script defer="defer" type="text/JavaScript" src="/SupplyMedium/Views/Utils/js/common.js">
            </script>
            <script defer="defer" type="text/JavaScript" src="/SupplyMedium/Views/DeptMgmt/js/deptvalidator.js">
            </script>
            <script defer="defer" type="text/JavaScript" src="/SupplyMedium/Views/DeptMgmt/js/userassign.js">
            </script>
            <script defer="defer" type="text/JavaScript" src="/SupplyMedium/Views/DeptMgmt/js/foldermgmt.js">
            </script>
            <script defer="defer" type="text/JavaScript" src="/SupplyMedium/Views/DeptMgmt/js/deptpopup.js">
            </script>
            <script defer="defer" type="text/JavaScript" src="/SupplyMedium/Views/DeptMgmt/js/groupassign.js">
            </script>
            <script defer="defer" type="text/JavaScript" src="/SupplyMedium/Views/DeptMgmt/js/folderassign.js">
            </script>
            <script defer="defer" type="text/JavaScript" src="/SupplyMedium/Views/DeptMgmt/js/deptprivileges.js">
            </script>-->
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

<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.GroupBean"%>
<%@page import="supply.medium.home.database.GroupMaster"%>
<%@page import="java.util.ArrayList"%>
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
        <SCRIPT type="text/JavaScript" src="inside/groupmgmt.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/grouppopup.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/companygroup.js">
        </SCRIPT>        
        <TITLE>Supply Medium</TITLE>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onload="lockUnlock('webkrit_content_loader');showNonExistingUserInGroup();showExistingUserInGroup();">
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
            <link rel="stylesheet" href="inside/groupmgmt.css">
            <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
            <link rel="stylesheet" href="inside/Custome_Buttons.css">
            <div class="pagetitlecontainer">
                <div class="pagetitle">Groups</div>
            </div>
            <div class="page">
                <input type="hidden" id="companyKey" value="<%=session.getAttribute("companyKey") %>" />
                <input type="hidden" id="userKey" value="<%=session.getAttribute("userKey") %>" />
                <input type="hidden" id="groupKey" />
                <input type="hidden" id="groupName" />
                <input type="hidden" id="groupUserKey" />
                <div class="contentcontainer" style="min-height:700px;">
                    <div class="content" id="group_content" style="">
                        <form name="groupmgmtfrm" id="groupmgmtfrm" style="float:left;">
                            <div class="grouplist">
                                <div class="groupmgmtbtns">
                                    <input type="button" class="okbtn new_group_btn" id="addgroupbtn" value="Add" style="margin-right:5px;">
                                    <input type="button" class="okbtn edit_group_btn" id="renamegroupbtn" value="Rename" style="margin-right:5px;">
                                    <input type="button" class="delbtn del_group_btn" id="delgroupbtn" value="Delete">
                                </div>
                                <br>
                                <br>
                                <div class="grouplistbox">
                                    <div class="grouplisthead ctrlheading">
                                        List of User Groups
                                    </div>
                                    <div class="searchbar">
                                        <input type="text" autocomplete="off" class="searchbartext" style="background-image:url('inside/search-large.png');" id="searchgroups" onKeyUp="searchGroup()">
                                    </div>
                                    <select multiple="multiple" class="grouplistsel listbox" name="grouplistsel" id="grouplistsel" size="5" onChange="$('#groupKey').val(this.value);showNonExistingUserInGroup();showExistingUserInGroup();//getGroupData(this.value);">
                                        <%
                                        ArrayList groupList = GroupMaster.showCompanyGroup(companyKey);
                                        GroupBean gb = null;
                                        for (int i = 0; i < groupList.size(); i++) {
                                        gb = (GroupBean) groupList.get(i);
                                        %>
                                        <option value="<%=gb.getGroupKey()%>" <% if(i==0){ out.print("selected"); } %>><%=gb.getGroupName() %></option>
                                        <% } %>                                        
                                    </select>
                                </div>
                                <div class="horizontalbar1">
                                </div>
                                <div class="horizontalbar2" style="margin-right:32px;">
                                </div>
                                <div class="groupassign">
                                    <div class="existgroupbox">
                                        <div class="existgrouphead ctrlheading">
                                            Existing Users
                                        </div>
                                        <div class="searchbar" style="width:225px;">
                                            <input type="text" autocomplete="off" class="searchbartext" style="background-image:url('inside/search-small.png');width:187px;" id="searchExistingGropUser" >
                                        </div>
                                        <select multiple="multiple" class="existusersel listbox" name="existusersel" id="existusersel" size="5">
                                            
                                        </select>
                                    </div>
                                    <div class="addremove">
                                        <div class="addbtn" id="adduserbtn">
                                        </div>
                                        <div class="removebtn" id="removeuserbtn">
                                        </div>
                                    </div>
                                    <div class="pregroupbox">
                                        <div class="pregrouphead ctrlheading">
                                            Group Users
                                        </div>
                                        <div class="searchbar" style="width:225px;">
                                            <input type="text" autocomplete="off" class="searchbartext" style="background-image:url('inside/search-small.png');width:187px;" id="searchexistingusers" onKeyUp="groupUserfilter.set(this.value)">
                                        </div>
                                        <select multiple="multiple" class="pregroupsel listbox" name="pregroupsel" id="pregroupsel" size="5">
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div id="groupmgmterr" class="grouperror">
                            </div>
                            <div id="userassignerr" class="grouperror">
                            </div>
                            <div class="grouppriv">
                                <fieldset class="privfieldset">
                                    <!--<legend>Privileges</legend>-->
                                    <legend>&nbsp;</legend>
                                    <div class="privleft">
<!--                                        <div class="listitem">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="addusers">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="addusers" class="group_pri_lbl">Add New Users</label>
                                        </div>
                                        <div class="listitem">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="delusers">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="delusers" class="group_pri_lbl">Delete Users</label>
                                        </div>-->
                                        <!-- <div class="listitem">
                                        <div class="checkContainer">
                                        <input type="checkbox" class="checkbox"  id="uploaddoc">
                                        <div>
                                        </div>
                                        </div>
                                        <label for="uploaddoc" class="group_pri_lbl">Upload Documents</label>
                                        </div>
                                        <div class="listitem">
                                        <div class="checkContainer">
                                        <input type="checkbox" class="checkbox"  id="deldoc">
                                        <div>
                                        </div>
                                        </div>
                                        <label for="deldoc" class="group_pri_lbl">Delete Documents</label>
                                        </div> -->
<!--                                        <div class="listitem">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="addbuyers">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="addbuyers" class="group_pri_lbl">Add Buyers/Suppliers</label>
                                        </div>
                                        <div class="listitem">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="delbuyers">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="delbuyers" class="group_pri_lbl">Delete Buyers/Suppliers</label>
                                        </div>
                                        <div class="listitem">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="accessusermgmt">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="accessusermgmt" class="group_pri_lbl">Access User Management</label>
                                        </div>
                                    </div>
                                    <div class="horizontalbar1" style="height:110px; margin-left:20px;">
                                    </div>
                                    <div class="horizontalbar2" style="height:110px;">
                                    </div>
                                    <div class="privright">
                                        <div class="listitem">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="postanncemnt">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="postanncemnt" class="group_pri_lbl">Post Announcements</label>
                                        </div>-->
                                        <!--  <div class="listitem">
                                        <div class="checkContainer">
                                        <input type="checkbox" class="checkbox" id="delanncemnt">
                                        <div>
                                        </div>
                                        </div>
                                        <label for="delanncemnt" class="group_pri_lbl">Delete Announcements</label>
                                        </div>-->
<!--                                        <div class="listitem">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="rate">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="rate" class="group_pri_lbl">Rate Buyers/Suppliers</label>
                                        </div>
                                        <div class="listitem">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="creategroup">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="creategroup" class="group_pri_lbl">Create User Group</label>
                                        </div>
                                        <div class="listitem">
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="delgroup">
                                                <div>
                                                </div>
                                            </div>
                                            <label for="delgroup" class="group_pri_lbl">Delete User Group</label>
                                        </div>-->
                                        <!--  <div class="listitem">
                                        <div class="checkContainer">
                                        <input type="checkbox" class="checkbox"  id="applythemes">
                                        <div>
                                        </div>
                                        </div>
                                        <label for="applythemes" class="group_pri_lbl">Apply Themes and Orders</label>
                                        </div>
                                        <div class="listitem">
                                        <div class="checkContainer">
                                        <input type="checkbox" class="checkbox"  id="managefolder">
                                        <div>
                                        </div>
                                        </div>
                                        <label for="managefolder" class="group_pri_lbl">Manage Folder</label>
                                        </div>-->
                                    </div>
<!--                                    <div class="groupupt">
                                        <input type="button" id="updatepribtn" class="gen-btn-Orange" value="Update">
                                    </div>-->
                                    <div id="groupprierr" class="grouperror">
                                    </div>
                                </fieldset>
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
                            <div style="display: none;" id="add_group_popup" class="Custome_Popup_Window">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td style="vertical-align: middle;">
                                                <div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout">
                                                    <div class="Popup_Tilte_NewGroup Gen_Popup_Title">
                                                        New Group
                                                        <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                                        </div>
                                                    </div>
                                                    <div class="popupcontent">
                                                        <div class="row">
                                                            <div class="label" style="width:150px;">Group Name</div>
                                                            <input type="text" autocomplete="off" id="new_group_input" name="new_group_input" class="textbox required" style="width:300px;height:20px;">
                                                            <label id="new_group_input_err" style="margin-left:150px" class="error">
                                                            </label>
                                                        </div>
                                                        <div class="row" style="margin-top:60px;margin-left:120px;">
                                                            <input onclick="$('#add_group_popup').fadeOut();" id="cancel_group" type="button" style="margin-right:20px" class="gen-btn-Gray" value="Cancel">
                                                            <input type="button" id="save_group" class="gen-btn-Orange" value="Save">
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
                            <div style="display: none;" id="edit_group_popup" class="Custome_Popup_Window">
                                <table>
                                    <tbody>
                                        <tr>
                                            <td style="vertical-align: middle;">
                                                <div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout">
                                                    <div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
                                                        Rename Group
                                                        <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                                        </div>
                                                    </div>
                                                    <div class="popupcontent">
                                                        <div class="row">
                                                            <div class="label" style="width:150px;">Group Name</div>
                                                            <input type="text" autocomplete="off" id="edit_group_input" name="edit_group_input" class="textbox required" style="width:300px;height:20px;">
                                                            <label id="edit_group_input_err" style="margin-left:150px" class="error">
                                                            </label>
                                                        </div>
                                                        <div class="row" style="margin-top:60px;margin-left:120px;">
                                                            <input id="edit_cancel_group" onclick="$('#edit_group_popup').fadeOut();" type="button" style="margin-right:20px" class="gen-btn-Gray" value="Cancel">
                                                            <input type="button" id="edit_group" class="gen-btn-Orange" value="Save">
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

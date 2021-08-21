<%@page import="supply.medium.home.bean.DepartmentUserBean"%>
<%@page import="supply.medium.home.bean.DepartmentUserBean"%>
<%@page import="supply.medium.home.database.DepartmentUserMaster"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.FeedBean"%>
<%@page import="supply.medium.home.database.FeedMaster"%>
<%@page import="supply.medium.home.bean.DepartmentBean"%>
<%@page import="supply.medium.home.database.DepartmentMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="inside/jquery-ui-1.9.2.custom_spinner.css">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/jquery.mCustomScrollbar.css">
        <link rel="stylesheet" href="inside/user_home.css">
        <link rel="stylesheet" href="inside/dilbag.css">
        <!-- Chat JS style -->
        <link rel="stylesheet" type="text/css" href="inside/jquery.chatjs.css">
        <script type="text/JavaScript" src="inside/SMNamespace.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.9.2.custom_spinner.js">
        </script>
        <script type="text/JavaScript" src="inside/filterlist.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.customSelect.js">
        </script>
        <script type="text/javascript" src="inside/jquery.mCustomScrollbar.js">
        </script>
        <script src="inside/jquery.mousewheel.min.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </script>
<!--        <script type="text/JavaScript" src="inside/jquery.dataTables.js">
        </script>-->
        <script type="text/JavaScript" src="inside/common.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.validate.js">
        </script>
        <script type="text/JavaScript" src="inside/additional-methods.js">
        </script>
        <script type="text/JavaScript" src="inside/dropdownfiller.js">
        </script>
        <script type="text/JavaScript" src="inside/SMNamespace(1).js">
        </script>
        <script type="text/JavaScript" src="inside/footer.js">
        </script>
        <script type="text/JavaScript" src="inside/ajax_loader.js">
        </script>
        <!-- ChatJS and dependencies -->
        <script src="inside/jquery.chatjs.longpollingadapter.js" type="text/javascript">
        </script>
        <script src="inside/jquery.autosize.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.activity-indicator-1.0.0.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.chatjs.js" type="text/javascript">
        </script>
        <script type="text/JavaScript" src="inside/user_home.js">
        </script>
        <script type="text/JavaScript" src="inside/dilbag.js">
        </script>
        <script type="text/JavaScript" src="inside/corporate.js">
        </script>
        <title>Supply Medium</title>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
    </head>
    <body onLoad="lockUnlock('webkrit_content_loader');<% if(request.getParameter("open")!=null) { if(request.getParameter("open").equals("announcement")){ %> $('#annomt_content').show();$('#folder_content').hide(); <% } } if(request.getParameter("key")!=null) { %> $('#departmentKey').val('<%=request.getParameter("key") %>'); showDepartmentfeed(); <% } %> <% if(request.getParameter("deptKey")!=null) { %>$('#departmentKey').val('<%=request.getParameter("deptKey") %>');showFolderOfDepartment('<%=request.getParameter("deptKey") %>');$('#corporateDepartmentSelectedFolder').val('<%=request.getParameter("folderName") %>')<% } %>" onkeydown="displayunicode(event);">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp" %>
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/deptpage.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/deptpage_popup.css">
        <link rel="stylesheet" href="inside/DP_feed.css">
        <link rel="stylesheet" href="inside/deptpopup.css">
        <link rel="stylesheet" href="inside/recycle_bin_tablestyle.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/jquery.mCustomScrollbar.css">
        <div class="pagetitlecontainer">
            <div class="pagetitle">
                <div style="width:320px;float:left;">Department Page</div>
                <label id="DP_error_lbl" class="DP_error">
                </label>
            </div>
        </div>
        <div class="page">
            <div class="contentcontainer" style="min-height:675px;">
                <div class="leftcontent">
                    <!-- Left content -->
                    <div class="deptlist_container" id="deptlist_container">
                        <!-- Department list container -->
                        <div class="deptlist_head">
                            <!-- Department list heading  -->
                            Department List</div>
                        <div class="deptlist mCustomScrollbar _mCS_7" id="deptlist">
                            <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_7" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
                                <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
                                    <!-- Department lists -->
                                    <% 
                                        String userKey2=session.getAttribute("userKey").toString();
                                        ArrayList countryList = DepartmentMaster.showDepartmentList(userKey2);
                                        DepartmentBean db = null;  
                                        for (int i = 0; i < countryList.size(); i++) {
                                        db = (DepartmentBean) countryList.get(i);
                                        %>
                                        <input type="button" class="dept_row odd_row" id="<%=db.getDepartmentKey()%>" value="<%=db.getDepartmentName() %>" onClick=" $('#departmentKey').val('<%=db.getDepartmentKey()%>');showFolderOfDepartment('<%=db.getDepartmentKey()%>');showDepartmentfeed();">
                                        <% } %>           
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
                    <div class="recommend_container">
                        <!-- Recommend supplier or buyer container -->
                        <div class="recommend_head">
                            <!-- Recommend supplier or buyer heading -->
                            Recommended Buyer/Supplier</div>
                        <%@include file="_recBuySup.jsp"%>
                    </div>
                </div>
                <div class="rightcontent">
                    <!-- Right content -->
                    <div class="folder_container">
                        <!-- Folder and files container -->
                        <div class="folder_head" id="folder_head" onclick="$('#annomt_content').slideUp();$('#folder_content').slideDown();">
                            <!-- Folder and files head -->
                            <div class="folder_head_text">Folder List</div>
                            <%
                                        
                ArrayList ald=DepartmentUserMaster.showDepartmentsOfUser(userKey);
                DepartmentUserBean dub=null;
                DepartmentPermBean dpb=null;
                String addFile="no";
                String delFile="no";
                String addFolder="no";
                String delFolder="no";
                String annoc="no";
                
                for(int i=0;i<ald.size();i++)
                {
                    dub=(DepartmentUserBean)ald.get(i);
                    dpb=DepartmentPermMaster.showPermByDeptKey(dub.getDepartmentKey());
                    if(dpb.getAdd_file().equalsIgnoreCase("yes"))
                    {
                        addFile="yes";
                    }
                    if(dpb.getDelete_file().equalsIgnoreCase("yes"))
                    {
                        delFile="yes";
                    }
                    if(dpb.getAdd_folder().equalsIgnoreCase("yes"))
                    {
                        addFolder="yes";
                    }
                    if(dpb.getDelete_folder().equalsIgnoreCase("yes"))
                    {
                        delFolder="yes";
                    }
                    if(dpb.getPost_announcements().equalsIgnoreCase("yes"))
                    {
                        annoc="yes";
                    }
                }                
                            %>
                            <div class="down_arrow" id="folder_arrow">
                            </div>
                            <form name="file_upload_frm" id="file_upload_frm" action="UploadCorporateDepartmentFolderFile" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="corporateDepartmentSelectedFolder2" id="corporateDepartmentSelectedFolder2" value="0" />
                                <input type="hidden" name="departmentKey2" id="departmentKey2" value="0" />
                                <div class="folder_ctrls" id="folder_ctrls" style="">
                                    <div class="file_import_btn_container" title="Upload File" alt="Upload File">
<%
                                if((!userType.equals("Admin") 
                    && addFile.equalsIgnoreCase("yes")) || userType.equals("Admin"))
                {
%>
                                        <input type="file" id="file_import_btn" name="file_import_btn" class="import_btn" title="Upload File" alt="Upload File" onchange="return uploadCorporateDepartmentFolderFile();">
<%                    
                }
%>                                    
                                    </div>
<%
                                if((!userType.equals("Admin") 
                    && addFolder.equalsIgnoreCase("yes")) || userType.equals("Admin"))
                {
%>
                                    <input type="button" id="add_folder_btn" class="add_btn" title="Add Folder" onclick="createFolderInDept();">
<%                    
                }
%>                                    
<%
                                if((!userType.equals("Admin") 
                    && delFolder.equalsIgnoreCase("yes")) || userType.equals("Admin"))
                {
%>
                                    <input type="button" id="del_folder_btn" class="del_btn" title="Delete Folder" onclick="deleteCorporateDepartmentFolder();">
<%                    
                }
%>                                    
                                </div>
                            </form>
                            <form name="file_download_frm" id="file_download_frm" action="DeptFileDownloadServlet" method="post" enctype="multipart/form-data">
                                <div class="file_ctrls" id="file_ctrls" style="display:none;">
                                    <a title="Download File" alt="Download File" onclick="this.href=$('#corporateDepartmentFileUrl').val()" target="_blank" ><input type="button" id="file_export_btn" class="export_btn" title="Download File"></a>
<%
                                if((!userType.equals("Admin") 
                    && delFile.equalsIgnoreCase("yes")) || userType.equals("Admin"))
                {
%>
                                    <input type="button" id="del_file_btn" class="del_btn" title="Delete File" onclick="deleteCorporateDepartmentFolderFile();">
<%                    
                }
%>                                    
                                    <input type="hidden" id="selected_file_id" name="FileId">
                                    <input type="hidden" id="download_req_type" name="RequestType" value="DownloadFile">
                                </div>
                            </form>
                        </div>
                        <div class="folder_content" id="folder_content">
                            <!-- Folder and files content -->
                        </div>
                    </div>
                    <div class="annomt_container">
                        <!-- Announcement container -->
                        <div class="annomt_head" id="annomt_head" onclick="$('#annomt_content').slideDown();$('#folder_content').slideUp(); if($('#departmentKey').val()==='0'){ ShowToast('Select Department',2000); }">
                            <!-- Announcement head -->
                            <label style="width:100px;float:left;">Announcement</label>
                            <div class="right_arrow" id="annomt_arrow">
                            </div>
                        </div>
                        <div class="annomt_content" style="display:none;" id="annomt_content">
                            <!-- Announcement content -->
                            <div id="new_annomt" class="new_annomt">
                                <form id="company_feed_frm" onsubmit="if($('#departmentKey').val()==='0'){ ShowToast('Select Department before Post',2000); return false; };return feedValidater($('#annomt_title').val(),$('#annomt_desc').val()); " style="float:left" name="dept_feed_frm" action="CreateFeed" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="redirectTo" value="announcement"/>
                                    <input type="hidden" name="feedType" value="department feed"/>                                    
                                    <input type="hidden" name="isFeedCompanyWide" id="isFeedCompanyWide" value="no" />
                                    <input type="hidden" name="departmentKey" id="departmentKey" value="0" />
                                    <div class="annomt_btns">
                                        <div class="checkContainer">
                                            <input type="checkbox" class="checkbox" id="comp_post" onclick="if(document.getElementById('comp_post').checked){document.getElementById('isFeedCompanyWide').value='yes';}else{ document.getElementById('isFeedCompanyWide').value='no'; } ">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="comp_post" style="color: #464646;">Company wide post</label>
<%
                                if((!userType.equals("Admin") 
                    && annoc.equalsIgnoreCase("yes")) || userType.equals("Admin"))
                {
%>
                                        <input onclick="showLoading()" type="submit" class="gen-btn-Orange post_feed_btn" id="post_annomt_btn" value="Post">
<%                    
                }
%>  
                                    </div>
                                    <input type="text" autocomplete="off" class="annomt_title" name="feedTitle" id="annomt_title" placeholder="Announcement Title">
                                    <textarea placeholder="Description" class="annomt_desc" name="feedDescription" id="annomt_desc"></textarea>
                                </form>
                            </div>
                            <div id="com_feeds" class="feeds" style="width: 550px;">
                            
                            </div>
                            <div class="no_feeds" id="no_feeds" style="display:none;"> No announcements available yet
                            </div>
                            <div class="annomt_list" id="annomt_list">
                            </div>
                            <div id="feed_loader" style="display:none;">
                                <img src="inside/ajax_loader.gif" style="margin-left:480px;">
                            </div>
                            <div id="no_more_feed" style="display:none;">
                                No more feeds available
                            </div>
                        </div>
<!--                        <div class="recycle_bin" id="recycle_bin">
                        </div>-->
                    </div>
                </div>
            </div>
        </div>
        <title>Insert title here</title>
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/ResetCSS.css">
        <div style="display: none;" id="add_folder_popup" class="Custome_Popup_Window">
            <table>
                <tbody>
                    <tr>
                        <td style="vertical-align: middle;">
                            <div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout">
                                <div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
                                    New Folder Creation
                                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
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
                                        <input id="cancel_folder" type="button" onclick="$('#add_folder_popup').fadeOut();" style="margin-right:20px" class="gen-btn-Gray" value="Cancel">
                                        <input type="button" id="save_folder" class="gen-btn-Orange" onclick="createCorporateDepartmentFolder()"  value="Save">
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <title>Insert title here</title>
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/RestCSS.css">
        <div style="display: none;" id="recycle_bin_popup" class="Custome_Popup_Window">
            <div class="deptpopuplayout Popup_Outline_NewGroup Cus_Popup_Outline ">
                <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;-webkit-border-radius:0px;-moz-border-radius:0px;">
                    Recycle Bin
                    <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                    </div>
                </div>
                <div class="deptpopupcontent">
                    <div class="data_table_content">
                        <div id="file_list_wrapper" class="dataTables_wrapper" role="grid">
                            <div class="dataTables_scroll">
                                <div class="dataTables_scrollHead" style="overflow: hidden; position: relative; border: 0px; width: 100%;">
                                    <div class="dataTables_scrollHeadInner" style="width: 702px; padding-right: 0px;">
                                        <table class="file_list dataTable" style="margin-left: 0px; width: 702px;">
                                            <thead>
                                                <tr role="row">
                                                    <th class="rowBorder sorting_asc" role="columnheader" tabindex="0" aria-controls="file_list" rowspan="1" colspan="1" aria-sort="ascending" aria-label="File: activate to sort column descending" style="width: 175px;">File</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="file_list" rowspan="1" colspan="1" aria-label="Date: activate to sort column ascending" style="width: 175px;">Date</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="file_list" rowspan="1" colspan="1" aria-label="Size: activate to sort column ascending" style="width: 175px;">Size</th>
                                                    <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="file_list" rowspan="1" colspan="1" aria-label="Type: activate to sort column ascending" style="width: 175px;">Type</th>
                                                </tr>
                                            </thead>
                                        </table>
                                    </div>
                                </div>
                                <div class="dataTables_scrollBody" style="overflow: auto; height: 350px; width: 100%;">
                                    <table id="file_list" class="file_list dataTable" style="margin-left: 0px; width: 100%;">
                                        <thead>
                                            <tr role="row" style="height: 0px;">
                                                <th class="rowBorder sorting_asc" role="columnheader" tabindex="0" aria-controls="file_list" rowspan="1" colspan="1" aria-sort="ascending" aria-label="File: activate to sort column descending" style="padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px; width: 175px;">
                                                </th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="file_list" rowspan="1" colspan="1" aria-label="Date: activate to sort column ascending" style="padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px; width: 175px;">
                                                </th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="file_list" rowspan="1" colspan="1" aria-label="Size: activate to sort column ascending" style="padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px; width: 175px;">
                                                </th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="file_list" rowspan="1" colspan="1" aria-label="Type: activate to sort column ascending" style="padding-top: 0px; padding-bottom: 0px; border-top-width: 0px; border-bottom-width: 0px; height: 0px; width: 175px;">
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                                            <tr class="odd">
                                                <td valign="top" colspan="4" class="dataTables_empty">No data available in table</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btn_container">
                        <input type="button" value="Restore" id="restorebtn" class="gen-btn-Orange restorebtn">
                        <input type="button" value="Empty Recycle Bin" id="empty_recycle_btn" class="gen-btn-Orange restorebtn" style="width:160px;margin-left:20px;">
                    </div>
                </div>
            </div>
        </div>
        <title>Toast Window </title>
        <link rel="stylesheet" href="inside/Cus_Toast.css">
        <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
        
            <%@include file="_footer.jsp" %>
            <div>
            </div>
        </div>
        <%@include file="_invite.jsp" %>
    <script type="text/JavaScript" src="inside/restrict_menu.js">
    </script>

        <link rel="stylesheet" type="text/css" href="inside/deptpage.css">
        <link rel="stylesheet" type="text/css" href="inside/deptpage_popup.css">
        <link rel="stylesheet" type="text/css" href="inside/DP_feed.css">
        <link rel="stylesheet" type="text/css" href="inside/recycle_bin_tablestyle.css">
        <script type="text/JavaScript" src="inside/DP_recycle_bin.js">
        </script>
        <script type="text/JavaScript" src="inside/DP_folder_operation.js">
        </script>
        <script type="text/JavaScript" src="inside/DP_folder_loader.js">
        </script>
        <script type="text/JavaScript" src="inside/DP_file_operation.js">
        </script>
        <script type="text/JavaScript" src="inside/DP_feed.js">
        </script>
        <script type="text/JavaScript" src="inside/deptpage.js">
        </script>
        <script type="text/JavaScript" src="inside/company_reco.js">
        </script>


    <script type="text/javascript">
$(function(){
	
	$(".Gen_Cus_Popup_Close").click(function(){$("#recycle_bin_popup").hide();});
	$("#cancel_dept").click(function(){$("#recycle_bin_popup").hide();});
}	
);

$(document).ready(function()
		{
			
			$(".dataTables_scrollBody").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
		});

</script>

	<div style="display: none;" id="recycle_bin_popup"
		class="Custome_Popup_Window">

		<div class="deptpopuplayout Popup_Outline_NewGroup Cus_Popup_Outline ">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;-webkit-border-radius:0px;-moz-border-radius:0px;">
				Recycle Bin
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>

			<div class="deptpopupcontent">
				<div class="data_table_content">
					<table id="file_list" class="file_list">
						<thead >
							<tr>
								<th class="rowBorder">File</th>
								<th class="rowBorder">Date</th>
								<th class="rowBorder">Size</th>
								<th class="rowBorder">Type</th>
							</tr>
						</thead>
						<tbody>
					
						</tbody>

					</table>
				</div>
				<div class="btn_container">
				
					<input type="button" value="Restore" id="restorebtn"
						class="gen-btn-Orange restorebtn" />
						
						<input type="button" value="Empty Recycle Bin" id="empty_recycle_btn"
						class="gen-btn-Orange restorebtn" style="width:160px;margin-left:20px;"/>
				</div>

			</div>

		</div>

	</div>
</body>
</html>
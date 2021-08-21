<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/DeptPage/css/deptpage.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/DeptPage/css/deptpage_popup.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/DeptPage/css/DP_feed.css" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/DeptMgmt/css/deptpopup.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/DeptPage/css/recycle_bin_tablestyle.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/jquery.mCustomScrollbar.css" />
	

	
	
<title>Department Page</title>
</head>
<body onload="hideAjaxLoader()">

<%@include file="../../../session_check.jsp"%>

<script type="text/JavaScript">
$("#content_loader").hide();
</script>
	
	<div class="pagetitlecontainer">
	<div class="pagetitle" >
		<div style="width:320px;float:left;">Department Page</div>
		<label id="DP_error_lbl" class="DP_error"></label>
	</div>
	</div>
	
	<div class="page">
	<div class="contentcontainer" style="min-height:675px;">
	
	<div class="leftcontent"> <!-- Left content -->
	
		<div class="deptlist_container" id="deptlist_container"> <!-- Department list container -->
		
			<div class="deptlist_head" > <!-- Department list heading  -->
			Department List</div>
			
			<div class="deptlist" id="deptlist"> <!-- Department lists -->
				
			</div>
			
		</div>
		
		<div class="recommend_container"> <!-- Recommend supplier or buyer container -->
		
			<div class="recommend_head">  <!-- Recommend supplier or buyer heading -->
			Recommended Buyer/Supplier</div>
			
			<div class="recommend_content" id="recommend_content"> <!-- Recommend supplier or buyer content body -->
			
			</div>
			
		</div>
	</div>
	
	<div class="rightcontent"> <!-- Right content -->
	
		<div class="folder_container"> <!-- Folder and files container -->
		
	
		
			<div class="folder_head" id="folder_head"> <!-- Folder and files head -->
			<div class="folder_head_text">Folder List</div>
			<div class="down_arrow" id="folder_arrow"></div>
			
			<form name="file_upload_frm" id="file_upload_frm" action="${pageContext.request.contextPath}/DeptFilesMgmtServlet" method="post" enctype="multipart/form-data">
			<div class="folder_ctrls" id="folder_ctrls" style="display:none;">
				<div class="file_import_btn_container">
					<input type="file" id="file_import_btn" name ="file_import_btn" class="import_btn" title="Import File"/>
				</div>
				<input type="button" id="add_folder_btn" class="add_btn" title="Add Folder"/>
				<input type="button" id="del_folder_btn" class="del_btn" title="Delete Folder"/>
			</div>
			</form>
			
			<form name="file_download_frm" id="file_download_frm" action="${pageContext.request.contextPath}/DeptFileDownloadServlet" method="post" enctype="multipart/form-data">
			<div class="file_ctrls" id="file_ctrls" style="display:none;">
				<input type="button" id="file_export_btn" class="export_btn" title="Download File"/>
				<input type="button" id="del_file_btn" class="del_btn" title="Delete File"/>
				<input type="hidden" id="selected_file_id" name="FileId" />
				<input type="hidden" id="download_req_type" name="RequestType" value="DownloadFile"/>
			</div>
			</form>
			
			</div>
			
			<div class="folder_content" id="folder_content"> <!-- Folder and files content -->
				
			
			</div>
			
			
		</div>
		
		<div class="annomt_container" > <!-- Announcement container -->
		
			<div class="annomt_head" id="annomt_head"> <!-- Announcement head -->
				<label style="width:100px;float:left;">Announcement</label>
				<div class="right_arrow" id="annomt_arrow"></div>
			</div>
			
			<div class="annomt_content" style="display:none;" id="annomt_content">  <!-- Announcement content -->
				<div id="new_annomt" class="new_annomt">
					<form id="company_feed_frm" style="float:left" name="dept_feed_frm" action="${pageContext.request.contextPath}/DeptFeedServlet" method="post" enctype="multipart/form-data" >
					<div class="annomt_btns" >
						<div class="checkContainer"><input type="checkbox" class="checkbox" id="comp_post"><div></div></div>
							<label for="comp_post" style="color: #464646;">Company wide post</label>
						<input type="button" class="gen-btn-Orange post_feed_btn" id="post_annomt_btn" value="Post">
					</div>
					<input type="text" class="annomt_title" name="annomt_title" id="annomt_title" placeholder="Announcement Title"/>
					<textarea  placeholder="Description" class="annomt_desc" name="annomt_desc" id="annomt_desc"></textarea>
					</form>
				</div>
				
				<div class="no_feeds" id="no_feeds" style="display:none;"> No announcements available yet
				</div>
				<div class="annomt_list" id="annomt_list">

						<!-- 	<div id="feed_1" class="annomt">
								<div class="date">Posted at - 04-Apr-2013 18:00</div>
								<div class="annomt_name">Test Feed</div>
								<div class="annomt_summary">When we are inserting a record
									into the database table and the primary key is an
									auto-increment or auto-generated key, then the insert query
									will generate it dynamically. The below example shows how to
									get this key after insert statement. After perfoming
									executeUpdate() method on PreparedStatement, call
									getGeneratedKeys() method on PreparedStatement. It will return
									you ResultSet, from which you can get auto increment column
									values.</div>
							</div>
							
							<div id="feed_1" class="annomt">
								<div class="date">Posted at - 04-Apr-2013 18:00</div>
								<div class="annomt_name">Test Feed</div>
								<div class="annomt_summary">When we are inserting a record
									into the database table and the primary key is an
									auto-increment or auto-generated key, then the insert query
									will generate it dynamically. The below example shows how to
									get this key after insert statement. After perfoming
									executeUpdate() method on PreparedStatement, call
									getGeneratedKeys() method on PreparedStatement. It will return
									you ResultSet, from which you can get auto increment column
									values.</div>
							</div>
							
							<div id="feed_1" class="annomt">
								<div class="date">Posted at - 04-Apr-2013 18:00</div>
								<div class="annomt_name">Test Feed</div>
								<div class="annomt_summary">When we are inserting a record
									into the database table and the primary key is an
									auto-increment or auto-generated key, then the insert query
									will generate it dynamically. The below example shows how to
									get this key after insert statement. After perfoming
									executeUpdate() method on PreparedStatement, call
									getGeneratedKeys() method on PreparedStatement. It will return
									you ResultSet, from which you can get auto increment column
									values.</div>
							</div>
							
							<div id="feed_1" class="annomt">
								<div class="date">Posted at - 04-Apr-2013 18:00</div>
								<div class="annomt_name">Test Feed</div>
								<div class="annomt_summary">When we are inserting a record
									into the database table and the primary key is an
									auto-increment or auto-generated key, then the insert query
									will generate it dynamically. The below example shows how to
									get this key after insert statement. After perfoming
									executeUpdate() method on PreparedStatement, call
									getGeneratedKeys() method on PreparedStatement. It will return
									you ResultSet, from which you can get auto increment column
									values.</div>
							</div>
							
							<div id="feed_1" class="annomt">
								<div class="date">Posted at - 04-Apr-2013 18:00</div>
								<div class="annomt_name">Test Feed</div>
								<div class="annomt_summary">When we are inserting a record
									into the database table and the primary key is an
									auto-increment or auto-generated key, then the insert query
									will generate it dynamically. The below example shows how to
									get this key after insert statement. After perfoming
									executeUpdate() method on PreparedStatement, call
									getGeneratedKeys() method on PreparedStatement. It will return
									you ResultSet, from which you can get auto increment column
									values.</div>
							</div> -->
							
							
						</div>	
						
						<div id="feed_loader" style="display:none;">
							<img src="${pageContext.request.contextPath}/Views/Utils/images/ajax_loader.gif" style="margin-left:480px;"/>
						</div>
	
	
						<div id="no_more_feed" style="display:none;">
								No more feeds available
						</div>
			
		</div>
		
		<div class="recycle_bin" id="recycle_bin">
		</div>
	</div>
	
	</div>
	</div>
	</div>
	 <%@include file="../../DeptMgmt/jsp/Popup_NewFolder.jsp"%>
	<%@include file="recycle_bin_popup.jsp"%>
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	  <%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	  
	  <%@include file="../../Utils/jsp/footer.jsp"%>
	  
	 <script>


$.getScript( "${pageContext.request.contextPath}/Views/DeptPage/js/company_reco.js", function( data, textStatus, jqxhr ) 
{
	if( $("#add_vendor_flag").val() == 1)
	{
		getRecoCompanies();
		
		$(".recommend_container").show();
	}
	else 
	{
		$(".recommend_container").hide();
	}
			 
});


$.getScript( "${pageContext.request.contextPath}/Views/DeptPage/js/DP_folder_loader.js");

$.getScript( "${pageContext.request.contextPath}/Views/DeptPage/js/DP_folder_operation.js");
$.getScript( "${pageContext.request.contextPath}/Views/DeptPage/js/DP_file_operation.js");

$.getScript( "${pageContext.request.contextPath}/Views/DeptPage/js/deptpage.js", function( data, textStatus, jqxhr ) 
{
	deptPageOnload();
	 
});


$.getScript( "${pageContext.request.contextPath}/Views/DeptPage/js/DP_recycle_bin.js");

$.getScript( "${pageContext.request.contextPath}/Views/DeptPage/js/DP_feed.js");

</script>
	  
	
	<!--  <script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/DeptPage/js/company_reco.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/DeptPage/js/DP_folder_loader.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/DeptPage/js/DP_folder_operation.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/DeptPage/js/DP_file_operation.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/DeptPage/js/deptpage.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/DeptPage/js/DP_recycle_bin.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/DeptPage/js/DP_feed.js"></script>-->
	
	
	
</body>
</html>
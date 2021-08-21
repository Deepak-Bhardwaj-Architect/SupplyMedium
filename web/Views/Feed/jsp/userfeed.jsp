<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Feed/css/userfeed.css" />
	
<title>Supply Medium</title>


</head>
<body onload=''>

<script type="text/JavaScript">
cnt = 1, lst_id = null, slctd_fldr = "",slctd_fldr_nm = "", slctd_fl = "", slctd_fl2 = "";    
$("#content_loader").hide();
</script>

	 <%@include file="../../../session_check.jsp"%>
         <div id="ad_usr_fldr" class="rfq_post_confirmation" style="height:140px;margin-left:35%;width:340px;"><center><p>Enter folder name<br/><br/>
                     <input style="margin-left: 0px;width: 300px;height:30px;" type="text" class="feedTitle" name="fldr_nm" id="fldr_nm" placeholder="Folder name:"/><br/><br/><br><input type="button" class="gen-btn-blue" value="Save" onclick="add_user_folder();$('#ad_usr_fldr').fadeOut();" style="margin-left:10px;"/><input type="button" class="gen-btn-blue" value="Close" onclick="$('#ad_usr_fldr').fadeOut();" style="margin-left:10px;"/></p></center>
        </div>
         <div id="ad_usr_fldr_fl" class="rfq_post_confirmation" style="height:140px;margin-left:35%;width:340px;"><center><p>Select folder file<br/><br/>
                 <form name="frm_fl_nm">
                     <input style="margin-left: 0px;width: 300px;height:30px;background:#f1f1f1 ;border:0px;  " type="file" class="feedTitle" name="fl_nm2" id="fl_nm2"/><br/><br/><br><input type="button" class="gen-btn-blue" value="Save" onclick="add_user_folder_file();$('#ad_usr_fldr_fl').fadeOut();" style="margin-left:10px;"/><input type="button" class="gen-btn-blue" value="Close" onclick="$('#ad_usr_fldr_fl').fadeOut();" style="margin-left:10px;"/>
                 </form>
                 </p></center>
        </div>
	<div class="pagetitlecontainer">
	<div class="pagetitle">My Feeds Page</div>
	</div>
	<div class="page">
	<div class="contentcontainer">
	<div class="feed_content">
	<div id="user_page_content">
            <div style="width:230px;float: left;background: #FFFFFF">                
                <div style="width:230px;height:250px; float: left;border: 1px solid #d3d3d3;">
                    <div class="recommend_head" style="width:225px;text-align: center;">My Briefcase (Folders)</div><p style="float: right;"><input type="button" id="add_folder_btn" class="add_btn" title="Add Folder" onclick="$('#ad_usr_fldr').fadeIn();$('#fldr_nm').val('');"><input type="button" id="del_folder_btn" class="del_btn" title="Delete Folder" onclick="delete_user_folder();"></p>
                    <div style="overflow: auto;width:230px;height:190px;">
                    <ul class="folder_ul" id="usr_fld_lst">                        
                    </ul>
                    </div>
                </div>
                <div style="width:230px;height:350px;float: left;border: 1px solid #d3d3d3">
                    <div class="recommend_head" style="width:225px;text-align: center;">My Briefcase (Files)</div><div style="float: right;"><input type="button" id="upload_file" class="import_btn" title="Import File" onclick="$('#ad_usr_fldr_fl').fadeIn();"/><a onclick="this.href=$('#slected_user_folder_url').val()+'/'+slctd_fl2;" target="_blank"><input type="button" id="download_file" class="export_btn" title="Download File"/></a><input type="button" id="del_folder_btn" class="del_btn" title="Delete File" onclick="delete_user_file();"/></div>
                <div style="overflow: auto;width:230px;height:290px;">
                    <ul class="folder_ul" id="usr_fld_fls_lst">                         
                    </ul>
                    </div>
                </div>                     
            </div>
            <div style="float: right;width:760px;">
	<!-- New feed creation -->
        <div id="newfeed" class="newfeed" style="width:750px;">
		<form id="user_feed_frm" style="width:750px;" style="float:left" name="user_feed_frm" action="${pageContext.request.contextPath}/UserFeedServlet" method="post" enctype="multipart/form-data" >
		  <label class="feed_err_lbl" id="feed_err_lbl">
		</label>
                  <label class="file_name_lbl" id="file_name_lbl" style="color: #f37d01;font-family:Verdana; font-size: 18px;">Feeds</label>
		<div class="feed_btns">
			<input type="button" class="gen-btn-Orange post_feed_btn" id="post_feed_btn" value="Post">
			<div class="new_feed_image_container">
				<input type="file" class="new_feed_image"  name="new_feed_image" id="new_feed_image" onchange="getFileName();"/>
			</div>
		</div>
                <input style="margin-left: 0px;" type="text" class="feedTitle" name="feedTitle" id="feedTitle" placeholder="Feed Title:"/>
		<textarea style="margin-left: 0px;" class="feedDesc" name="feedDesc" id="feedDesc" placeholder="Feed Description:"></textarea>
		</form>
	</div>
	
	<div class="no_feeds" id="no_feeds" style="display:none;"> No announcements available yet
	</div>
		
	<!-- List down the old feeds -->
        <div id="feeds" class="feeds" style="margin-left:0px;">
	  	
	</div>
        </div>
	<div id="feed_loader" style="display:none;">
		<img src="${pageContext.request.contextPath}/Views/Utils/images/ajax_loader.gif" style="margin-left:480px;"/>
	</div>
	<div id="no_more_feed" style="display:none;">
		No more feeds available
	</div>
		
	</div>
	</div>
	</div>
        </div>

	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	
	<%@include file="../../Utils/jsp/original_image_popup.jsp"%>
	
	  <%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	  
	  <%@include file="../../Utils/jsp/footer.jsp"%>
	
	<script>

$.getScript( "${pageContext.request.contextPath}/Views/Feed/js/userfeed.js", function( data, textStatus, jqxhr ) {
	
	userFeedOnload();
	});

</script>
	
	
	<!--  <script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Feed/js/userfeed.js"></script>-->

</body>



</html>
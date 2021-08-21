<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Feed/css/company_feed.css" />
	
<title>Supply Medium</title>


</head>
<body onload='hideAjaxLoader();'>

<script type="text/JavaScript">
cnt = 1, lst_id = null, slctd_fldr = "",slctd_fldr_nm = "", slctd_fl = "", slctd_fl2 = ""; 
$("#content_loader").hide();
</script>

	 <%@include file="../../../session_check.jsp"%>
         <div id="ad_usr_fldr" class="rfq_post_confirmation" style="height:140px;margin-left:35%;width:340px;"><center><p>Enter folder name<br/><br/>
                     <input style="margin-left: 0px;width: 300px;height:30px;" type="text" class="feedTitle" name="fldr_nm" id="fldr_nm" placeholder="Folder name:"/><br/><br/><br><input type="button" class="gen-btn-blue" value="Save" onclick="add_cmpny_folder();$('#ad_usr_fldr').fadeOut();" style="margin-left:10px;"/><input type="button" class="gen-btn-blue" value="Close" onclick="$('#ad_usr_fldr').fadeOut();" style="margin-left:10px;"/></p></center>
        </div>
         <div id="ad_usr_fldr_fl" class="rfq_post_confirmation" style="height:140px;margin-left:35%;width:340px;"><center><p>Select folder file<br/><br/>
                 <form name="frm_fl_nm">
                     <input style="margin-left: 0px;width: 300px;height:30px;background:#f1f1f1 ;border:0px;  " type="file" class="feedTitle" name="fl_nm2" id="fl_nm2"/><br/><br/><br><input type="button" class="gen-btn-blue" value="Save" onclick="add_cmpny_folder_file();$('#ad_usr_fldr_fl').fadeOut();" style="margin-left:10px;"/><input type="button" class="gen-btn-blue" value="Close" onclick="$('#ad_usr_fldr_fl').fadeOut();" style="margin-left:10px;"/>
                 </form>
                 </p></center>
        </div>
	<div class="pagetitlecontainer">
	<div class="pagetitle">Company Internal Page</div>
	</div>
	<div class="page">
	<div class="contentcontainer">
	<div id="internal_page_content" style="display:none;">
        <div style="width:200px;float: left;background: #FFFFFF">                
                <div style="width:200px;height:250px; float: left;border: 1px solid #d3d3d3;">
                    <div class="recommend_head" style="width:195px;text-align: center;">My Briefcase (Folders)</div><p style="float: right;"><input type="button" id="add_folder_btn" class="add_btn" title="Add Folder" onclick="$('#ad_usr_fldr').fadeIn();$('#fldr_nm').val('');"><input type="button" id="del_folder_btn" class="del_btn" title="Delete Folder" onclick="delete_company_folder();"></p>
                    <div style="overflow: auto;width:200px;height:190px;">
                    <ul class="folder_ul" id="usr_fld_lst" style="width:155px;">                        
                    </ul>
                    </div>
                </div>
                <div style="width:200px;height:350px;float: left;border: 1px solid #d3d3d3">
                    <div class="recommend_head" style="width:195px;text-align: center;">My Briefcase (Files)</div><div style="float: right;"><input type="button" id="upload_file" class="import_btn" title="Import File" onclick="$('#ad_usr_fldr_fl').fadeIn();"/><a onclick="this.href=$('#slected_company_folder_url').val()+'/'+slctd_fl2;" target="_blank"><input type="button" id="download_file" class="export_btn" title="Download File"/></a><input type="button" id="del_folder_btn" class="del_btn" title="Delete File" onclick="delete_company_file();"/></div>
                <div style="overflow: auto;width:200px;height:290px;">
                    <ul class="folder_ul" id="usr_fld_fls_lst" style="width:155px;">                        
                    </ul>
                    </div>
                </div>                     
            </div>    
	<div class="feed_content" style="width: 550px;margin-left:8px;">
	
	<!-- New feed creation -->
	<div id="newfeed" class="newfeed">
		<form id="company_feed_frm" style="float:left" name="company_feed_frm" action="${pageContext.request.contextPath}/CompanyFeedServlet" method="post" enctype="multipart/form-data" >
		  <!--label class="feed_err_lbl" id="feed_err_lbl">
		</label>
		<label class="file_name_lbl" id="file_name_lbl"></label-->
		<div class="feed_btns">
			  <input type="button" class="gen-btn-Orange post_feed_btn" id="post_feed_btn" value="Post" style="margin-left:25px;">
			<!--  <div class="new_feed_image_container">
				<input type="file" class="new_feed_image"  name="new_feed_image"/>
			</div>-->
		</div>
                <input type="text" class="feedTitle" name="feedTitle" id="feedTitle" placeholder="Feed Title:" style="width: 550px;padding: 0px 0px 0px 0px;"/>
		<textarea class="feedDesc" name="feedDesc" id="feedDesc" placeholder="Feed Description:" style="width: 550px;padding: 0px 0px 0px 0px;"></textarea>
		</form>
	</div>
	
	<div class="no_feeds" id="no_feeds" style="display:none;width: 550px;"> No announcements available yet
	</div>
	
	<!-- List down the old feeds -->
	<div id="com_feeds" class="feeds" style="width: 550px;">
		
	  
	</div>
	
	<div id="feed_loader" style="display:none;">
		<img src="${pageContext.request.contextPath}/Views/Utils/images/ajax_loader.gif" style="margin-left:480px;"/>
	</div>
	
	
	<div id="no_more_feed" style="display:none;width: 550px;">
		No more feeds available
	</div>
		
	</div>
	
	<div class="recommend_container"> <!-- Recommend supplier or buyer container -->
		
			<div class="recommend_head">  <!-- Recommend supplier or buyer heading -->
			Recommended Buyer/Supplier</div>
			
			<div class="recommend_content" id="recommend_content"> <!-- Recommend supplier or buyer content body -->
			
			</div>
			
	</div>
		
		
	</div>
	</div>
	
	</div>
	
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	  <%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	  
	  <%@include file="../../Utils/jsp/footer.jsp"%>
	
<script>



$.getScript( "${pageContext.request.contextPath}/Views/DeptPage/js/company_reco.js", function( data, textStatus, jqxhr ) {
	
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
$.getScript( "${pageContext.request.contextPath}/Views/Feed/js/companyfeed.js", function( data, textStatus, jqxhr ) {
	
	companyFeedOnload();
});

</script>

	
	<!-- <script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/DeptPage/js/company_reco.js"></script>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Feed/js/companyfeed.js"></script> -->
	
	
	

</body>


</html>
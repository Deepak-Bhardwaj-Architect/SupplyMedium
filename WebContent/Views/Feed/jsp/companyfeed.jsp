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
<body onload="hideAjaxLoader()">

<script type="text/JavaScript">
$("#content_loader").hide();
</script>

	 <%@include file="../../../session_check.jsp"%>
	<div class="pagetitlecontainer">
	<div class="pagetitle">Company Internal Page</div>
	</div>
	<div class="page">
	<div class="contentcontainer">
	<div id="internal_page_content" style="display:none;">
	<div class="feed_content">
	
	<!-- New feed creation -->
	<div id="newfeed" class="newfeed">
		<form id="company_feed_frm" style="float:left" name="company_feed_frm" action="${pageContext.request.contextPath}/CompanyFeedServlet" method="post" enctype="multipart/form-data" >
		  <label class="feed_err_lbl" id="feed_err_lbl">
		</label>
		<label class="file_name_lbl" id="file_name_lbl"></label>
		<div class="feed_btns">
			  <input type="button" class="gen-btn-Orange post_feed_btn" id="post_feed_btn" value="Post" style="margin-left:25px;">
			<!--  <div class="new_feed_image_container">
				<input type="file" class="new_feed_image"  name="new_feed_image"/>
			</div>-->
		</div>
		<input type="text" class="feedTitle" name="feedTitle" id="feedTitle" placeholder="Feed Title:"/>
		<textarea class="feedDesc" name="feedDesc" id="feedDesc" placeholder="Feed Description:"></textarea>
		</form>
	</div>
	
	<div class="no_feeds" id="no_feeds" style="display:none;"> No announcements available yet
	</div>
	
	<!-- List down the old feeds -->
	<div id="com_feeds" class="feeds">
		
	  
	</div>
	
	<div id="feed_loader" style="display:none;">
		<img src="${pageContext.request.contextPath}/Views/Utils/images/ajax_loader.gif" style="margin-left:480px;"/>
	</div>
	
	
	<div id="no_more_feed" style="display:none;">
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
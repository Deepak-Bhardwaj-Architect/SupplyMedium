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
<body onload="">

<script type="text/JavaScript">
$("#content_loader").hide();
</script>

	 <%@include file="../../../session_check.jsp"%>
	<div class="pagetitlecontainer">
	<div class="pagetitle">User Page</div>
	</div>
	<div class="page">
	<div class="contentcontainer">
	<div class="feed_content">
	<div id="user_page_content">
	
	<!-- New feed creation -->
	<div id="newfeed" class="newfeed">
		<form id="user_feed_frm" style="float:left" name="user_feed_frm" action="${pageContext.request.contextPath}/UserFeedServlet" method="post" enctype="multipart/form-data" >
		  <label class="feed_err_lbl" id="feed_err_lbl">
		</label>
		<label class="file_name_lbl" id="file_name_lbl"></label>
		<div class="feed_btns">
			<input type="button" class="gen-btn-Orange post_feed_btn" id="post_feed_btn" value="Post">
			<div class="new_feed_image_container">
				<input type="file" class="new_feed_image"  name="new_feed_image" id="new_feed_image" onchange="getFileName();"/>
			</div>
		</div>
		<input type="text" class="feedTitle" name="feedTitle" id="feedTitle" placeholder="Feed Title:"/>
		<textarea class="feedDesc" name="feedDesc" id="feedDesc" placeholder="Feed Description:"></textarea>
		</form>
	</div>
	
	<div class="no_feeds" id="no_feeds" style="display:none;"> No announcements available yet
	</div>
		
	<!-- List down the old feeds -->
	<div id="feeds" class="feeds">
	  	
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
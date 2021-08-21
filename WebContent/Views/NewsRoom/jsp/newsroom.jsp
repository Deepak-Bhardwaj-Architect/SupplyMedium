<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Room</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/NewsRoom/css/newsroom.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/NewsRoom/css/newsroom_feed.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/NewsRoom/css/view_members.css">

</head>
<body>
<%@include file="../../../session_check.jsp"%>

<script type="text/JavaScript">
$("#content_loader").hide();
hideAjaxLoader();
</script>

<div class="mainpage">
		<div class="pagetitlecontainer">
			<div class="pagetitle"> Newsroom </div>
		</div>
		<div class="contentcontainer">
			<div class="content">
			<!--  	<div class="globalsearch">
					<input type="text" placeholder="Search for first name, last name, email or business" class="searchtext">
				</div>-->
				<div class="contentbg">
					<div class="content_left">
						<div class="content_label content_label_selected" id="newsroom">Newsroom 
						</div>
						<div class="content_label" id="create_watchlist">Create Watchlist
						</div>
						<div class="content_label_only" style="margin-top:20px;">Watchlist
						</div>
						<div id="watchlists">
							
						</div>
					</div>
					<div class="content_right">
						<div class="cont_container">
							<div class="content_title" id="NR_content_title">Newsroom
							</div>
							
							<div class="text_container" id="watchlist_head" style="display:none;">
								<input type="text" class="text_box" placeholder="Add Members to Watchlist" id="search_mem_textbox" onkeyup="getSearchConnections( this.value );">
								<div class="view_mem" id="view_mem">
								</div>
								<div id="search_result" class="search_result" style="display:none;">
								</div>
							</div>
							
							<!--  <div class="new_feed_container">
								<div class="button_container" style="float:right;width:70px;height:20px;">
									<img src="${pageContext.request.contextPath}/Views/NewsRoom/images/add_image.png" class="add_image">
									<div class="post_but">Post</div>
								</div>
								<input type="text" class="feed_text_box" placeholder="Feed Title">
								<textarea class="text_area"></textarea>
							</div>-->

							<div id="newfeed" class="newfeed">
								<form id="user_feed_frm" style="float: left"
									name="user_feed_frm" action="/SupplyMedium/UserFeedServlet"
									method="post" enctype="multipart/form-data">
									<label class="feed_err_lbl" id="feed_err_lbl"></label> <label
										class="file_name_lbl" id="file_name_lbl"></label>
									<div class="feed_btns">
										<input type="button" class="gen-btn-Orange post_feed_btn"
											id="post_feed_btn" value="Post">
										<div class="new_feed_image_container">
											<input type="file" class="new_feed_image"
												name="new_feed_image" id="new_feed_image"
												onchange="getFileName();">
										</div>
									</div>
									<input type="text" class="feedTitle" name="feedTitle"
										id="feedTitle" placeholder="Feed Title:">
									<textarea class="feedDesc" name="feedDesc" id="feedDesc"
										placeholder="Feed Description:"></textarea>
								</form>
							</div>

							<div id="feeds" class="feeds">
							</div>
						</div>
					</div>	
				</div>
			</div>
		</div>	
	</div>	

<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
<%@include file="../../Utils/jsp/ajax_loader.jsp"%>
<%@include file="../../Utils/jsp/footer.jsp"%>
<%@include file="../../Utils/jsp/Popup_Warning.jsp"%>
<%@include file="add_watchlist.jsp"%>
<%@include file="view_members.jsp"%>
<%@include file="../../Utils/jsp/original_image_popup.jsp"%>

<script>

$.getScript( "${pageContext.request.contextPath}/Views/NewsRoom/js/newsroom_feed.js");
$.getScript( "${pageContext.request.contextPath}/Views/NewsRoom/js/watchlist_feed.js");

$.getScript( "${pageContext.request.contextPath}/Views/NewsRoom/js/watchlist_members.js", function( data, textStatus, jqxhr ) 
{
	$("#delete_members_btn").click( removeMembers );		
			 
});

$.getScript( "${pageContext.request.contextPath}/Views/NewsRoom/js/newsroom.js", function( data, textStatus, jqxhr ) 
{
	newsroomOnload();
	 
});
$.getScript( "${pageContext.request.contextPath}/Views/NewsRoom/js/watchlist.js", function( data, textStatus, jqxhr ) 
{
	fetchWatchLists();
	$("#save_watchlist").click( saveWatchList );
	 
});

</script>

</body>
</html>
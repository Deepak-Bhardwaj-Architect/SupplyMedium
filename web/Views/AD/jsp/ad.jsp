<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Cp1252">
<title>Advertisement</title>
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/AD/css/ad.css" />
</head>
<body>
<script type="text/JavaScript">
$("#content_loader").hide();
</script>

<%@include file="../../../session_check.jsp"%>
	<div class="mainpage" style="height:735px;">
		<div class="pagetitlecontainer">
			<div class="pagetitle">Create Your Ads</div>
		</div>

		<div class="contentcontainer">

			<div class="content" style="height:583px;">

				<form id="form_ad" name="form_ad"
					action="${pageContext.request.contextPath}/AdServlet" method="post"
					enctype="multipart/form-data">


					<div class="content_left">
						<div class="label_container">
							<label class="title_label">Hover text for your creative</label> 
							<input type="text" class="text_box" id="alternateText" name="alternateText" /> 
							<label class="hint_label">This is what users will see in some browsers when their mouse hovers over the ad.</label>
						</div>

						<div class="label_container">
							<label class="title_label">Link</label>
							 <input type="text" class="text_box" id="link" name="link" /> 
							 <label class="hint_label">Enter the url of landing page</label>
						</div>



						<div class="label_container">
							<label class="title_label" style="width: 160px;">Upload your creative</label> 
                                                        <input id="ad_file" name="ad_file" class="file_upload_control" type="file" value="Choose file" onclick="this.title='';" onmouseover="this.title='The image dimensions should be 200x150 pixels';">
							<div class="ad_error" id="ad_error"></div>
						</div>
					</div>

					<div class="content_right">
						<div class="pic_label">Preview</div>
						<img class="imge" id="preview_image" />
					</div>


					<div class="orange_button" id="btn_save">Save</div>
				</form>
				<form id="hidden_form" name="hidden_form" action="${pageContext.request.contextPath}/ImageValidationServlet" method="post"
					enctype="multipart/form-data">
				</form>
			</div>
		</div>
	</div>


	<%@include file="../../Utils/jsp/footer.jsp"%>
	<%@include file="../../Utils/jsp/Popup_Warning.jsp"%>
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	<%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	
	<script>

$.getScript( "${pageContext.request.contextPath}/Views/AD/js/ad.js", function( data, textStatus, jqxhr ) {
	
	adOnload();
	});

</script>
	
	
	
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/TransTC/css/trans_tc.css" />
</head>
<body>
<script type="text/JavaScript">
$("#content_loader").hide();
</script>

<%@include file="../../../session_check.jsp"%>
	<div class="mainpage" style="height:830px;">
		<div class="pagetitlecontainer">
			<div class="pagetitle"> Transaction Settings </div>
		</div>
		<div class="contentcontainer">
			<div class="tc_content">
				<div class="menu_container">
					<div class="tab_container">
						<div class="tab_orange" id="rfq_tab">RFQ</div>
						<div class="tab_gray" id="quote_tab">Quote</div>
						<div class="tab_gray" id="po_tab">Purchase Order</div>
						<div class="tab_gray" id="invoice_tab">Invoice</div>
					</div>
					<div style="width:1002px; height:12px; float:left; background-color:#de7b13"></div>
				</div>
				
				<div class="transet_container">
				
					<div class="transet_title" id="transet_titile">RFQ Terms and Conditions</div>
					
					<div class="transet_content" id="no_tc" >
					
						<div class="transet_lable">Upload Terms &amp; Conditions</div>
						
	
	
						
						<div class="transet_upload_cont">
							<form id="tc_form" name="tc_form" action="${pageContext.request.contextPath}/FileReadServlet" method="post"
					enctype="multipart/form-data">
					<input type="file" class="browse_btn" name="file_upload" id="file_upload" >
					</form>
							<a class="upload_txt_cont" style="width:230px;cursor:pointer; margin-left:320px;" onclick="performClick(document.getElementById('file_upload'));">
								<img src="${pageContext.request.contextPath}/Views/TransTC/images/add_file.png" class="upload_img">
								<label class="upload_txt_lable" style="width:195px;">Click to upload as Text file</label>
							</a>
							
							<div class="transet_or" style="margin:30px 0px 30px 0px;">(or)</div>
							
							<div class="upload_txt_cont" id="upload_txt_cont" style="margin-left:270px;">
							
								<img src="${pageContext.request.contextPath}/Views/TransTC/images/add_file.png" class="upload_img">
								<div class="upload_txt_lable">Click to copy/paste Terms &amp; Conditions</div>
							</div>
						</div>
					</div>
					
					<div class="transet_content"  id="tc_content" style="display:none;">
						<div class="edit_container"  id="edit_container">
							<div class="edit_lable">Edit</div>
							<img src="${pageContext.request.contextPath}/Views/TransTC/images/trans_edit.png" id="edit_btn">
						</div>
						<textarea class="transet_lable_content" id="transet_label_content" disabled>
						</textarea>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="tc_content_popup.jsp"%>
	<%@include file="../../Utils/jsp/footer.jsp"%>
	<%@include file="../../Utils/jsp/Popup_Warning.jsp"%>
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	<%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	
	<script>

$.getScript( "${pageContext.request.contextPath}/Views/TransTC/js/trans_tc.js", function( data, textStatus, jqxhr ) {
	
	tcOnload();
	});

</script>
	

	  	
</body>

</html>

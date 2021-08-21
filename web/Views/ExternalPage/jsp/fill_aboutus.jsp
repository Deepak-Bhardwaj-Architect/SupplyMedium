<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<fieldset class="external_fieldset" style="width:700px;">
		<legend class="external_legend">Title</legend>
		<input type="text" class="external_textbox" id="aboutus_fill_image_text_1">
		<div class="content_lbl">Content</div>
		<div class="content_text" id="aboutus_fill_image_content_1" style="display:none;width:590px;"></div>
		
		<div class="content_add" id="aboutus_fill_image_add_content_1">
			<input type="button" class="add_content_btn" id="aboutus_title_1_add_btn"/>
			<div class="add_content_text">Click to add content</div>
		</div>
		
		<input type="button"  class="external_edit_btn" id="aboutus_title_1_edit_btn" style="display:none;">
	</fieldset>
	<div class="external_image_content" style="width:710px;margin-bottom:70px;">
		<form id="aboutus_file_upload_form_1" name="aboutus_file_upload_form_1" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadsevlet" style="margin-left:100px;padding-top:30px;">
				<input id="aboutus_file_upload_control_1"  name="aboutus_file_upload_control_1" class="file_upload_control"  type="file" value="Choose file" />
				<input type="button" id="aboutus_file_upload_btn_1" class="gen-btn-blue ext_browse_btn" value="Browse" />																
		</form>
		
		<div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
			<img class="img_prev" id="aboutus_img_prev_1" src="${pageContext.request.contextPath}/Views/ExternalPage/images/default_img.png" />
		</div>
		
		<input type="button" class="remove_image_btn" id="aboutus_img_prev_1_del" style="display:none;"/>
		
	</div>
	
<!--  
<div>
	<div class="divrow">
		<label>Title*</label>
	</div>
	<input id="aboutus_fill_image_text_1" class="textbox" type="text" style="float: left;"  /> 
	
	<div class="file_upload_container">			
			<form id="aboutus_file_upload_form_1" name="aboutus_file_upload_form_1" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadsevlet">
				<input id="aboutus_file_upload_control_1"  name="aboutus_file_upload_control_1" class="file_upload_control"  type="file" value="File Chossee" />
				<input type="button" id="aboutus_file_upload_btn_1" class="general-button gen-btn-upload" value="Browse" />																
			</form>
	</div>
	<div class="PreviewImageContainer" style="margin: 5px 0px 0px 450px;">
		<img class="img_prev" id="aboutus_img_prev_1" src="${pageContext.request.contextPath}/Views/ExternalPage/images/default_img.png" />
	</div>
</div>

<div>
	<div class="divrow">
		<label>Content*</label>
	</div>
	<textarea id="aboutus_fill_image_content_1" rows="10" cols="90" style="width: 820px;height: 175px"></textarea>
</div>-->

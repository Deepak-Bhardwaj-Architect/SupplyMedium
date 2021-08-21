<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<div>
	<div class="divrow" style="margin:20px 0px 20px 20px;width:425px;">
		<div style="float:left;width:109px;line-height:67px;">Video URL</div>
		<input id="home_fill_video_url" class="textbox video_url_textbox" type="text" style="float: left;margin-top:20px;width:276px;height:22px;margin: 20px 0px 0px 13px;padding: 0px 10px 0px 10px;"  />
		
		<div class="video_info" style="padding-left:122px;">
			<label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
			<div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br/>8L8n2DjJzNg)"></div>
		</div>
		
		
	</div>
	 
	<div id="home_fill_video_url_Error"  class="ErrorImg" ></div>
	
</div>



<fieldset class="external_fieldset" style="width:700px;">
		<legend class="external_legend">Title</legend>
		<input type="text" class="external_textbox" id="home_fill_video_title">
		<div class="content_lbl">Content</div>
		<div class="content_text" id="home_fill_content" style="display:none;width:590px;"></div>
		
		<div class="content_add" id="home_fill_image_add_content_1">
			<input type="button" class="add_content_btn" id="home_title_1_add_btn"/>
			<div class="add_content_text">Click to add content</div>
		</div>
		
		<input type="button"  class="external_edit_btn" id="home_title_1_edit_btn" style="display:none;">
	</fieldset>
	<div class="external_image_content" style="width:710px;margin-bottom:70px;">
		<form id="home_file_upload_form_1" name="home_file_upload_form_1" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadsevlet" style="margin-left:100px;padding-top:30px;">
				<input id="home_file_upload_control_1"  name="home_file_upload_control_1" class="file_upload_control"  type="file" value="Choose file" />
				<input type="button" id="file_upload_btn_1" class="gen-btn-blue ext_browse_btn" value="Browse" />																
		</form>
		
		<div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
			<img class="img_prev" id="home_img_prev_1" src="${pageContext.request.contextPath}/Views/ExternalPage/images/default_img.png" />
		</div>
		
		<input type="button" class="remove_image_btn" id="home_img_prev_1_del" style="display:none;"/>
		
	</div>


<!--  

<div>
	<div class="divrow">
		<label>Title* </label>
	</div>
	<input id="home_fill_video_title" class="textbox" type="text" />
</div>

<div>
	<div class="divrow">
		<label>Content*</label>
	</div>
	<textarea id="home_fill_content" rows="10" cols="90"></textarea>
</div>

<div class="basic_row" style="margin-top: 40px">
		<div class="divrow">
			<label>Upload Image</label>
		</div>
		<div class="file_upload_textbox" style="display:none;">
			<input id="home_fill_image_text_1" class="img_txt textbox" type="text" style="245px;" />
		</div>
		<div class="file_upload_container">			
			<form id="home_file_upload_form_1" name="home_file_upload_form_1" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadsevlet">
				<input id="home_file_upload_control_1"  name="home_file_upload_control_1" class="file_upload_control"  type="file" value="File Chossee" />
				<input type="button" id="file_upload_btn_1" class="general-button gen-btn-upload" value="Browse" />																
			</form>
		</div>
</div>	
	
	
<div class="basic_row" >
		<div class="divrow" style="display:none;">
			<label>Content</label>
		</div>
		<textarea id="home_fill_image_content_1" style="width: 240px;height:65px;display:none"></textarea>
		<div class="PreviewImageContainer" style="position: absolute;margin: -50px 0px 0px 230px;">
			<img class="img_prev" id="home_img_prev_1" src="${pageContext.request.contextPath}/Views/ExternalPage/images/default_img.png" />
		</div>
</div>-->

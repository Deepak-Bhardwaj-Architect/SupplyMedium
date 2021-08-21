<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<div class="fill_left_content">

	<fieldset class="external_fieldset" style="padding-top:4px;">
		<legend class="external_legend">Video URL 1</legend>
		<input type="text" class="external_textbox"  id="product_fill_video_url_1">
		
		<div class="video_info">
			<label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
			<div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br/>8L8n2DjJzNg)"></div>
		</div>
		<div class="content_lbl">Content</div>
		
		<div class="content_text" id="product_video_fill_content_1" style="display:none"></div>
		
		<div class="content_add" id="product_video_fill_add_content_1">
			<input type="button" class="add_content_btn" id="product_video_1_add_btn"/>
			<div class="add_content_text">Click to add content</div>
		</div>
		
		<input type="button" class="external_edit_btn" id="product_video_1_edit_btn" style="display:none;">
	</fieldset>
	
	<fieldset class="external_fieldset">
		<legend class="external_legend">Title 1</legend>
		<input type="text" class="external_textbox" id="product_fill_image_text_1">
		<div class="content_lbl">Content</div>
		<div class="content_text" id="product_fill_image_content_1" style="display:none"></div>
		
		<div class="content_add" id="product_fill_image_add_content_1">
			<input type="button" class="add_content_btn"  id="product_title_1_add_btn"/>
			<div class="add_content_text">Click to add content</div>
		</div>
		
		<input type="button" class="external_edit_btn" id="product_title_1_edit_btn" style="display:none;">
	</fieldset>
	
	<div class="external_image_content">
		<form id="product_file_upload_form_1" name="product_file_upload_form_1" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadsevlet" style="margin-left:100px;padding-top:30px;">
				<input id="product_file_upload_control_1"  name="product_file_upload_control_1" class="file_upload_control"  type="file" value="Choose file" />
				<input type="button" id="file_upload_btn_1" class="gen-btn-blue ext_browse_btn" value="Browse"/>																
		</form>
		
		<div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
			<img class="img_prev" id="img_prev_1" src="${pageContext.request.contextPath}/Views/ExternalPage/images/default_img.png" />
		</div>
		
		<input type="button" class="remove_image_btn" id="img_prev_1_del" style="display:none;"/>
		
	</div>
	
	<fieldset class="external_fieldset">
		<legend class="external_legend">Title 2</legend>
		<input type="text" class="external_textbox" id="product_fill_image_text_2">
		<div class="content_lbl">Content</div>
		<div class="content_text" id="product_fill_image_content_2" style="display:none"></div>
		
		<div class="content_add" id="product_fill_image_add_content_2">
			<input type="button" class="add_content_btn"  id="product_title_2_add_btn"/>
			<div class="add_content_text">Click to add content</div>
		</div>
		
		<input type="button" class="external_edit_btn" id="product_title_2_edit_btn" style="display:none;">
	</fieldset>
	<div class="external_image_content">
		<form id="product_file_upload_form_2" name="product_file_upload_form_2" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadsevlet" style="margin-left:100px;padding-top:30px;">
				<input id="product_file_upload_control_2"  name="product_file_upload_control_2" class="file_upload_control"  type="file" value="Choose file" />
				<input type="button" id="file_upload_btn_2" class="gen-btn-blue ext_browse_btn" value="Browse" />																
		</form>
		
		<div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
			<img class="img_prev" id="img_prev_2" src="${pageContext.request.contextPath}/Views/ExternalPage/images/default_img.png" />
		</div>
		
		<input type="button" class="remove_image_btn" id="img_prev_2_del" style="display:none;"/>
		
	</div>
	


</div>

<div class="fill_right_separator" style="height:728px;">
</div>

<div class="fill_right_content">
	
	<fieldset class="external_fieldset" style="padding-top:4px;">
		<legend class="external_legend">Video URL 2</legend>
		<input type="text" class="external_textbox" id="product_fill_video_url_2">
		
		<div class="video_info">
			<label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
			<div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br/>8L8n2DjJzNg)"></div>
		</div>
		
		<div class="content_lbl">Content</div>
		<div class="content_text" id="product_video_fill_content_2" style="display:none"></div>
		
		<div class="content_add" id="product_video_fill_add_content_2">
			<input type="button" class="add_content_btn" id="product_video_2_add_btn"/>
			<div class="add_content_text">Click to add content</div>
		</div>
		<input type="button"  class="external_edit_btn" id="product_video_2_edit_btn" style="display:none;">
	</fieldset>
	
	<fieldset class="external_fieldset">
		<legend class="external_legend">Title 3</legend>
		<input type="text" class="external_textbox" id="product_fill_image_text_3">
		<div class="content_lbl">Content</div>
		<div class="content_text" id="product_fill_image_content_3" style="display:none"></div>
		
		<div class="content_add" id="product_fill_image_add_content_3">
			<input type="button" class="add_content_btn"  id="product_title_3_add_btn"/>
			<div class="add_content_text">Click to add content</div>
		</div>
		
		<input type="button"  class="external_edit_btn" id="product_title_3_edit_btn" style="display:none;">
	</fieldset>
	
	<div class="external_image_content">
		<form id="product_file_upload_form_3" name="product_file_upload_form_3" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadsevlet" style="margin-left:100px;padding-top:30px;">
				<input id="product_file_upload_control_3"  name="product_file_upload_control_3" class="file_upload_control"  type="file" value="Choose file" />
				<input type="button" id="file_upload_btn_3" class="gen-btn-blue ext_browse_btn" value="Browse" />																
		</form>
		
		<div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
			<img class="img_prev" id="img_prev_3" src="${pageContext.request.contextPath}/Views/ExternalPage/images/default_img.png" />
		</div>
		
		<input type="button" class="remove_image_btn" id="img_prev_3_del" style="display:none;"/>
		
	</div>
	
	<fieldset class="external_fieldset">
		<legend class="external_legend">Title 4</legend>
		<input type="text" class="external_textbox" id="product_fill_image_text_4">
		<div class="content_lbl">Content</div>
		<div class="content_text" id="product_fill_image_content_4" style="display:none"></div>
		
		<div class="content_add" id="product_fill_image_add_content_4">
			<input type="button" class="add_content_btn" id="product_title_4_add_btn"/>
			<div class="add_content_text">Click to add content</div>
		</div>
		
		<input type="button"  class="external_edit_btn" id="product_title_4_edit_btn" style="display:none;">
	</fieldset>
	<div class="external_image_content">
		<form id="product_file_upload_form_4" name="product_file_upload_form_4" method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/uploadsevlet" style="margin-left:100px;padding-top:30px;">
				<input id="product_file_upload_control_4"  name="product_file_upload_control_4" class="file_upload_control"  type="file" value="Choose file" />
				<input type="button" id="file_upload_btn_4" class="gen-btn-blue ext_browse_btn" value="Browse" />																
		</form>
		
		<div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
			<img class="img_prev" id="img_prev_4" src="${pageContext.request.contextPath}/Views/ExternalPage/images/default_img.png" />
		</div>
		
		<input type="button" class="remove_image_btn" id="img_prev_4_del" style="display:none;"/>
		
	</div>
</div>



$(function()
{
	

	/* home Upload - 1 */
	$("#home_file_upload_control_1").change(function()
	{
		
		readURLImage(this,"#home_img_prev_1");
		
		home_hasImage_1 = true;

	});
	
	
/* home content add and edit control */
	
	$("#home_title_1_add_btn").click(function()
	{
				
		showContentPopup( "","home_fill_content", "home_fill_image_add_content_1", "home_title_1_edit_btn");
	});
					
	$("#home_title_1_edit_btn").click(function()
	{
		var content = $("#home_fill_content").text();
				
		showContentPopup( content,"home_fill_content", "home_fill_image_add_content_1", "home_title_1_edit_btn");
	});
	
	$("#home_img_prev_1_del").click(function()
	{
		removeImage( "home_file_upload_form_1", "home_img_prev_1", "home_img_prev_1_del" );
		
		home_hasImage_1 = false;
				
	});
	
});

/*
 * ----------------------------------------------------------------------------------------------------
 * 
 * HOME MENU CONTENT
 * 
 * ----------------------------------------------------------------------------------------------------
 */
var staus_Home_btn = false;

function save_content_home_button_status()
{
	staus_Home_btn = true;

	var urlclass = $("#home_fill_video_url_Error").attr("class");

	if (urlclass == "URLErrorImg")
	{
		staus_Home_btn = false;
	}

	if ($("#home_fill_video_title").val() == "")
	{
		staus_Home_btn = false;
	}

	if ($("#home_fill_content").text() == "")
	{
		staus_Home_btn = false;
	}

	return staus_Home_btn;

	// $("#btn_fill_save").prop("disabled", staus_Home_btn);

}

function Load_Content_Home()
{

	// Getting the Content fill Home Data

	var SendingData =
	{
		'Action' : 'ContentHome_Read',
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

	};

	doAjaxRequest("POST", "TemplateDataServlet", SendingData, fn_show_content_home_completed, null);
	

}


function save_content_home()
{
	var result=validate_Content_Home();

	if(result == "TRUE")
	{
		
		
		var formdata = new FormData();
		
		formdata.append('Action', 'ContentHome_Save');
		formdata.append('cmpRegnKey', ExternalPageData.cmpRegnKey);
		formdata.append('externalPageID', ExternalPageData.externalPageID);
		formdata.append('compnayURLName', ExternalPageData.compnayURLName);
		formdata.append('pageType', ExternalPageData.pageType);
		
		formdata.append('Video_URL', $("#home_fill_video_url").val());
		formdata.append('Video_Title',  $("#home_fill_video_title").val());
		formdata.append('Content', $("#home_fill_content").text());
		
		formdata.append('image_1_data',  home_file_upload_form_1.home_file_upload_control_1.files[0]);
		formdata.append('image_1_title', $("#home_fill_image_text_1").val());
		formdata.append('image_1_content', $("#home_fill_image_content_1").val());
		formdata.append('image_1_hasimage', home_hasImage_1);
		
		formdata.append("image_1_imgName",home_image_1_imgName_);
		
		/*var Data =
		{
			'Action' : 'ContentHome_Save',
			'cmpRegnKey' : ExternalPageData.cmpRegnKey,
			'externalPageID' : ExternalPageData.externalPageID,
			'compnayURLName' : ExternalPageData.compnayURLName,
			'pageType' : ExternalPageData.pageType,
	
			"Video_URL" : $("#home_fill_video_url").val(),
			"Video_Title" : $("#home_fill_video_title").val(),
			"Content" : $("#home_fill_content").val(),
			
			"image_1_data": home_file_upload_form_1.home_file_upload_control_1.files[0],
			"image_1_title": $("#home_fill_image_text_1").val(),
			"image_1_content": $("#home_fill_image_content_1").val(),
			"image_1_hasimage":home_hasImage_1
		};*/
	
		doAjaxRequestUpload("POST", "TemplateDataServlet", formdata, fn_save_content_home_completed, null);
		
	}
	else
	{
		//ShowToast(result);
	}

}

function validate_Content_Home()
{
	isHomeCompleted = false;

	if ($("#home_fill_video_url_Error").hasClass("URLErrorImg"))
	{
		return "Enter Valid URL for Video";
	}

	
	if ($("#home_fill_video_title").val() == "")
	{
		return "Enter the home page Title";
	}

	if ($("#home_fill_content").text() == "")
	{
		return "Enter the home page content";
	}


	isHomeCompleted= true;
	
	return "TRUE";

}

var fn_save_content_home_completed = function(ResultObj)
{
	if (ResultObj.result != "success")
	{
		Toast.Show("Unable to save the Home Data Pl Try again");
	}
	else
	{
		if (ResultObj.result == "success")
		{
			//ShowToast("Saved Successfully");
		}
	}
};

var home_hasImage_1 = false;
var home_image_1_imgName_ = "";

var fn_show_content_home_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		$("#home_fill_video_url").val(ResultObj.Video_URL);

		$("#home_fill_video_title").val(ResultObj.Video_Title);
		
		if( ResultObj.Content == "" || ResultObj.Content == null )
		{
			enableAddContent( "home_fill_image_add_content_1","home_fill_content","home_title_1_edit_btn" );
		}
		else
		{
			enableContent( ResultObj.Content,"home_fill_image_add_content_1","home_fill_content","home_title_1_edit_btn");
			
		}
		home_hasImage_1=ResultObj.image_1_hasimage_;
		
		if( home_hasImage_1 )
		{
			$("#home_img_prev_1_del").show();
		}
		
		home_image_1_imgName_= ResultObj.image_1_imgName_;
		if(ResultObj.image_1_hasimage_)
			$("#home_img_prev_1").attr('src', ResultObj.image_directory+home_image_1_imgName_);
	}

	save_content_home_button_status();
	
	validate_Content_Home();
};
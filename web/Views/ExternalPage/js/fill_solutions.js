/**
 * Load Content solutions
 */

$(function()
{

	/* Picture Upload - 1 */
	$("#solutions_file_upload_control_1").change(function()
	{
		readURLImage(this, "#solutions_img_prev_1");
		
		solutions_hasImage_1 = true;

	});

	$("#solutions_file_upload_control_2").change(function()
	{
		readURLImage(this, "#solutions_img_prev_2");
		
		solutions_hasImage_2 = true;
		
	});

	$("#solutions_file_upload_control_3").change(function()
	{
		readURLImage(this, "#solutions_img_prev_3");
		
		solutions_hasImage_3 = true;
	});

	$("#solutions_file_upload_control_4").change(function()
	{
		readURLImage(this, "#solutions_img_prev_4");
		
		solutions_hasImage_4 = true;
	});
	
/* solutions content add and edit control */
	
	$("#solutions_video_1_add_btn").click(function()
	{
		showContentPopup( "","solutions_video_fill_content_1", "solutions_video_fill_add_content_1", "solutions_video_1_edit_btn");
	});
			
	$("#solutions_video_1_edit_btn").click(function()
	{
		var content = $("#solutions_video_fill_content_1").text();
		
		showContentPopup( content,"solutions_video_fill_content_1", "solutions_video_fill_add_content_1", "solutions_video_1_edit_btn");
	});
			
	$("#solutions_video_2_add_btn").click(function()
	{
		showContentPopup( "","solutions_video_fill_content_2", "solutions_video_fill_add_content_2", "solutions_video_2_edit_btn");
	});
			
	$("#solutions_video_2_edit_btn").click(function()
	{
		var content = $("#solutions_video_fill_content_2").text();
		
		showContentPopup( content,"solutions_video_fill_content_2", "solutions_video_fill_add_content_2", "solutions_video_2_edit_btn");
	});
		
	$("#solutions_title_1_add_btn").click(function()
	{
		showContentPopup( "","solutions_fill_image_content_1", "solutions_fill_image_add_content_1", "solutions_title_1_edit_btn");
	});
	
	$("#solutions_title_1_edit_btn").click(function()
	{
		var content = $("#solutions_fill_image_content_1").text();
		
		showContentPopup( content,"solutions_fill_image_content_1", "solutions_fill_image_add_content_1", "solutions_title_1_edit_btn");
	});
	
	$("#solutions_title_2_add_btn").click(function()
	{
		showContentPopup( "","solutions_fill_image_content_2", "solutions_fill_image_add_content_2", "solutions_title_2_edit_btn");
	});
	
	$("#solutions_title_2_edit_btn").click(function()
	{
		var content = $("#solutions_fill_image_content_2").text();
		
		showContentPopup( content,"solutions_fill_image_content_2", "solutions_fill_image_add_content_2", "solutions_title_2_edit_btn");
	});
	
	$("#solutions_title_3_add_btn").click(function()
	{
		showContentPopup( "","solutions_fill_image_content_3", "solutions_fill_image_add_content_3", "solutions_title_3_edit_btn");
	});
	
	$("#solutions_title_3_edit_btn").click(function()
	{
		var content = $("#solutions_fill_image_content_3").text();
		
		showContentPopup( content,"solutions_fill_image_content_3", "solutions_fill_image_add_content_3", "solutions_title_3_edit_btn");
	});
	
	$("#solutions_title_4_add_btn").click(function()
	{
		showContentPopup( "","solutions_fill_image_content_4", "solutions_fill_image_add_content_4", "solutions_title_4_edit_btn");
	});
	
	$("#solutions_title_4_edit_btn").click(function()
	{
		var content = $("#solutions_fill_image_content_4").text();
		
		showContentPopup( content,"solutions_fill_image_content_4", "solutions_fill_image_add_content_4", "solutions_title_4_edit_btn");
	});
	
	$("#solutions_img_prev_1_del").click(function()
	{
		removeImage( "solutions_file_upload_form_1", "solutions_img_prev_1", "solutions_img_prev_1_del" );
		
		solutions_hasImage_1 = false;
	});
	
	$("#solutions_img_prev_2_del").click(function()
	{
		removeImage( "solutions_file_upload_form_2", "solutions_img_prev_2", "solutions_img_prev_2_del" );	
		
		solutions_hasImage_2 = false;
	});
	
	$("#solutions_img_prev_3_del").click(function()
	{
		removeImage( "solutions_file_upload_form_3", "solutions_img_prev_3", "solutions_img_prev_3_del" );	
		
		solutions_hasImage_3 = false;
	});
	
	$("#solutions_img_prev_4_del").click(function()
	{
		removeImage( "solutions_file_upload_form_4", "solutions_img_prev_4", "solutions_img_prev_4_del" );	
		
		solutions_hasImage_4 = false;
	});

});

function Load_Content_solutions()
{
	// Getting the Content fill solutions Data

	var SendingData =
	{
		'Action' : 'ContentSolutions_Read',
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

	};

	doAjaxRequest("POST", "TemplateDataServlet", SendingData, fn_show_content_solutions_completed, null);

}

var solutions_hasImage_1 = false;
var solutions_hasImage_2 = false;
var solutions_hasImage_3 = false;
var solutions_hasImage_4 = false;

var solutions_image_1_imgName_ = "";
var solutions_image_2_imgName_ = "";
var solutions_image_3_imgName_ = "";
var solutions_image_4_imgName_ = "";

var fn_show_content_solutions_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		$("#solutions_fill_video_url_1").val(ResultObj.videoURL_1_);
		$("#solutions_fill_video_url_2").val(ResultObj.videoURL_2_);

		if( ResultObj.videoURL_1_content_ == "" || ResultObj.videoURL_1_content_ == null )
		{
			enableAddContent( "solutions_video_fill_add_content_1","solutions_video_fill_content_1","solutions_video_1_edit_btn");
		}
		else
		{
			enableContent( ResultObj.videoURL_1_content_,"solutions_video_fill_add_content_1","solutions_video_fill_content_1","solutions_video_1_edit_btn");
		}
		
		if( ResultObj.videoURL_2_content_ == "" || ResultObj.videoURL_2_content_ == null )
		{
			enableAddContent( "solutions_video_fill_add_content_2","solutions_video_fill_content_2","solutions_video_2_edit_btn");
		}
		else
		{
			enableContent( ResultObj.videoURL_2_content_,"solutions_video_fill_add_content_2","solutions_video_fill_content_2","solutions_video_2_edit_btn");
			
		}

		$("#solutions_fill_image_text_1").val(ResultObj.image_1_title_);
		$("#solutions_fill_image_text_2").val(ResultObj.image_2_title_);
		$("#solutions_fill_image_text_3").val(ResultObj.image_3_title_);
		$("#solutions_fill_image_text_4").val(ResultObj.image_4_title_);

		if( ResultObj.image_1_content == "" || ResultObj.image_1_content == null )
		{
			enableAddContent( "solutions_fill_image_add_content_1","solutions_fill_image_content_1","solutions_title_1_edit_btn" );	
		}
		else
		{
			enableContent( ResultObj.image_1_content,"solutions_fill_image_add_content_1","solutions_fill_image_content_1","solutions_title_1_edit_btn");
		}
		
		if( ResultObj.image_2_content == "" || ResultObj.image_2_content == null )
		{
			enableAddContent( "solutions_fill_image_add_content_2","solutions_fill_image_content_2","solutions_title_2_edit_btn" );
		}
		else
		{
			enableContent( ResultObj.image_2_content,"solutions_fill_image_add_content_2","solutions_fill_image_content_2","solutions_title_2_edit_btn");
		}
		
		if( ResultObj.image_3_content == "" || ResultObj.image_3_content == null )
		{
			enableAddContent( "solutions_fill_image_add_content_3","solutions_fill_image_content_3","solutions_title_3_edit_btn" );
		}
		else
		{
			enableContent( ResultObj.image_3_content,"solutions_fill_image_add_content_3","solutions_fill_image_content_3","solutions_title_3_edit_btn");
		}
		
		if( ResultObj.image_4_content == "" || ResultObj.image_4_content == null )
		{
			enableAddContent( "solutions_fill_image_add_content_4","solutions_fill_image_content_4","solutions_title_4_edit_btn" );
		}
		else
		{
			enableContent( ResultObj.image_4_content,"solutions_fill_image_add_content_4","solutions_fill_image_content_4","solutions_title_4_edit_btn");
			
		}

		solutions_hasImage_1 = ResultObj.image_1_hasimage_;
		solutions_hasImage_2 = ResultObj.image_2_hasimage_;
		solutions_hasImage_3 = ResultObj.image_3_hasimage_;
		solutions_hasImage_4 = ResultObj.image_4_hasimage_;

		solutions_image_1_imgName_ = ResultObj.image_1_imgName_;
		solutions_image_2_imgName_ = ResultObj.image_2_imgName_;
		solutions_image_3_imgName_ = ResultObj.image_3_imgName_;
		solutions_image_4_imgName_ = ResultObj.image_4_imgName_;

		if(ResultObj.image_1_hasimage_)
		{
			$("#solutions_img_prev_1").attr('src', ResultObj.image_directory+solutions_image_1_imgName_);
			$("#solutions_img_prev_1_del").show();
		}
		
		if(ResultObj.image_2_hasimage_)
		{
			$("#solutions_img_prev_2").attr('src', ResultObj.image_directory+solutions_image_2_imgName_);
			$("#solutions_img_prev_2_del").show();
		}
		
		if(ResultObj.image_3_hasimage_)
		{
			$("#solutions_img_prev_3").attr('src', ResultObj.image_directory+solutions_image_3_imgName_);
			$("#solutions_img_prev_3_del").show();
		}
		
		if(ResultObj.image_4_hasimage_)
		{
			$("#solutions_img_prev_4").attr('src', ResultObj.image_directory+solutions_image_4_imgName_);
			$("#solutions_img_prev_4_del").show();
		}
		
		validate_content_Solutions();
	}

};

// -------------------------------------------------------------------
// Save Service
// -------------------------------------------------------------------

function save_content_solutions()
{
	var result = validate_content_Solutions();
	if (result == "TRUE")
	{
		var formdata = new FormData();
		formdata.append('Action', 'ContentSolutions_Save');
		formdata.append('cmpRegnKey', ExternalPageData.cmpRegnKey);
		formdata.append('externalPageID', ExternalPageData.externalPageID);
		formdata.append('compnayURLName', ExternalPageData.compnayURLName);
		formdata.append('pageType', ExternalPageData.pageType);

		formdata.append("videoURL_1_", $("#solutions_fill_video_url_1").val());
		formdata.append("videoURL_1_content_", $("#solutions_video_fill_content_1").text());

		formdata.append("videoURL_2_", $("#solutions_fill_video_url_2").val());
		formdata.append("videoURL_2_content_", $("#solutions_video_fill_content_2").text());

		formdata.append("image_1_data", solutions_file_upload_form_1.solutions_file_upload_control_1.files[0]);
		formdata.append("image_1_title", $("#solutions_fill_image_text_1").val());
		formdata.append("image_1_content", $("#solutions_fill_image_content_1").text());

		formdata.append("image_2_data", solutions_file_upload_form_2.solutions_file_upload_control_2.files[0]);
		formdata.append("image_2_title", $("#solutions_fill_image_text_2").val());
		formdata.append("image_2_content", $("#solutions_fill_image_content_2").text());

		formdata.append("image_3_data", solutions_file_upload_form_3.solutions_file_upload_control_3.files[0]);
		formdata.append("image_3_title", $("#solutions_fill_image_text_3").val());
		formdata.append("image_3_content", $("#solutions_fill_image_content_3").text());

		formdata.append("image_4_data", solutions_file_upload_form_4.solutions_file_upload_control_4.files[0]);
		formdata.append("image_4_title", $("#solutions_fill_image_text_4").val());
		formdata.append("image_4_content", $("#solutions_fill_image_content_4").text());

		formdata.append("image_1_hasimage_", solutions_hasImage_1);
		formdata.append("image_2_hasimage_", solutions_hasImage_2);
		formdata.append("image_3_hasimage_", solutions_hasImage_3);
		formdata.append("image_4_hasimage_", solutions_hasImage_4);

		formdata.append("image_1_imgName_", solutions_image_1_imgName_);
		formdata.append("image_2_imgName_", solutions_image_2_imgName_);
		formdata.append("image_3_imgName_", solutions_image_3_imgName_);
		formdata.append("image_4_imgName_", solutions_image_4_imgName_);

		doAjaxRequestUpload("POST", "TemplateDataServlet", formdata, fn_save_content_solutions_completed, null);
	}
	else
	{
		//ShowToast(result);
	}
}

function validate_content_Solutions()
{
	var hasonecontent = false;
	
	isSolutionsCompleted = false;

	// Video content 1 validation
	if ($("#solutions_fill_video_url_1").val() != "")
	{
		if ($("#solutions_fill_video_url_1_Error").hasClass("URLErrorImg"))
		{
			return "Enter valid URL for video 1";
		}

		if ($("#solutions_video_fill_content_1").text() == "")
		{
			return "Enter video 1 Content";
		}

		hasonecontent = true;
	}
	
	// Video content 2 validation

	if ($("#solutions_fill_video_url_2").val() != "")
	{
		if ($("#solutions_fill_video_url_2_Error").hasClass("URLErrorImg"))
		{
			return "Enter valid URL for video 2";
		}

		if ($("#solutions_video_fill_content_2").text() == "")
		{
			return "Enter video 2 content";
		}

		hasonecontent = true;
	}
	
	// Image content 1 validation 
	
	if( $("#solutions_fill_image_text_1").val() != "" && $("#solutions_fill_image_content_1").text() != "" )
	{
		hasonecontent = true;
	}
	
	else if ( $("#solutions_fill_image_text_1").val() != "" || $("#solutions_fill_image_content_1").text() != "" )
	{
		
		if( $("#solutions_fill_image_text_1").val() == "" && $("#solutions_fill_image_content_1").text() != "" )
			return "Enter Title 1";
		
		if( $("#solutions_fill_image_content_1").text() == "" && $("#solutions_fill_image_text_1").val() != "" )
			return "Enter Content 1";
	}
	else if( $("#solutions_fill_image_text_1").val() == "" || $("#solutions_fill_image_content_1").text() == "" )
	{
		if(solutions_hasImage_1)
		{
			return "Enter Title1 and Content1";
		}
	}
	
	// Image content 2 validation 
	
	if( $("#solutions_fill_image_text_2").val() != "" && $("#solutions_fill_image_content_2").text() != "" )
	{
		hasonecontent = true;
	}
	
	else if ($("#solutions_fill_image_text_2").val() != "" || $("#solutions_fill_image_content_2").text() != "")
	{
		if($("#solutions_fill_image_text_2").val() == "" && $("#solutions_fill_image_content_2").text() != "")
			return "Enter Title 2";
		
		if($("#solutions_fill_image_content_2").text() == "" && $("#solutions_fill_image_text_2").val() != "")
			return "Enter Content 2";
	}
	else if( $("#solutions_fill_image_text_2").val() == "" || $("#solutions_fill_image_content_2").text() == "" )
	{
		if(solutions_hasImage_2)
		{
			return "Enter Title2 and Content2";
		}
	}
	
	// Image content 3 validation 
	
	if( $("#solutions_fill_image_text_3").val() != "" && $("#solutions_fill_image_content_3").text() != "" )
	{
		hasonecontent = true;
	}
	
	else if ($("#solutions_fill_image_text_3").val() != "" || $("#solutions_fill_image_content_3").text() != "")
	{
		if($("#solutions_fill_image_text_3").val() == "" && $("#solutions_fill_image_content_3").text() != "")
			return "Enter Title 3";
		
		if($("#solutions_fill_image_content_3").text() == "" && $("#solutions_fill_image_text_3").val() != "")
			return "Enter Content 3";
	}
	else if( $("#solutions_fill_image_text_3").val() == "" || $("#solutions_fill_image_content_3").text() == "" )
	{
		if(solutions_hasImage_3)
		{
			return "Enter Title3 and Content3";
		}
	}
	
	// Image content 4 validation 
	
	if( $("#solutions_fill_image_text_4").val() != "" && $("#solutions_fill_image_content_4").text() != "" )
	{
		hasonecontent = true;
	}
	
	else if ($("#solutions_fill_image_text_4").val() != "" || $("#solutions_fill_image_content_4").text() != "")
	{
		if($("#solutions_fill_image_text_4").val() == "" && $("#solutions_fill_image_content_4").text() != "")
			return "Enter Title 4";
		
		if($("#solutions_fill_image_content_4").text() == "" && $("#solutions_fill_image_text_4").val() != "")
			return "Enter Content 4";
	}
	else if( $("#solutions_fill_image_text_4").val() == "" || $("#solutions_fill_image_content_4").text() == "" )
	{
		if(solutions_hasImage_4)
		{
			return "Enter Title4 and Content4";
		}
	}
	
	// final validation
	
	//alert( hasonecontent );

	if (!hasonecontent)
	{
		return "Enter atleast one content";
	}

	isSolutionsCompleted = true;
	
	return "TRUE";

}

var fn_save_content_solutions_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		//ShowToast("Saved Successfully");
	}
};

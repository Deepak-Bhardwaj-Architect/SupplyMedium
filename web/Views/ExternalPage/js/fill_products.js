$(function()
{

	/* Picture Upload - 1 */
	$("#product_file_upload_control_1").change(function()
	{
		readURLImage(this,"#img_prev_1");
		
		product_hasImage_1 = true;

	});

	$("#product_file_upload_control_2").change(function()
	{
		readURLImage(this,"#img_prev_2");
		
		product_hasImage_2 = true;
	});

	$("#product_file_upload_control_3").change(function()
	{		
		readURLImage(this,"#img_prev_3");
		
		product_hasImage_3 = true;
	});

	$("#product_file_upload_control_4").change(function()
	{		
		readURLImage(this,"#img_prev_4");
		
		product_hasImage_4 = true;
	});
	
/* product content add and edit control */
	
	$("#product_video_1_add_btn").click(function()
	{
		
		showContentPopup( "","product_video_fill_content_1", "product_video_fill_add_content_1", "product_video_1_edit_btn");
	});
			
	$("#product_video_1_edit_btn").click(function()
	{
		var content = $("#product_video_fill_content_1").text();
		
		showContentPopup( content,"product_video_fill_content_1", "product_video_fill_add_content_1", "product_video_1_edit_btn");
	});
			
	$("#product_video_2_add_btn").click(function()
	{
		showContentPopup( "","product_video_fill_content_2", "product_video_fill_add_content_2", "product_video_2_edit_btn");
	});
			
	$("#product_video_2_edit_btn").click(function()
	{
		var content = $("#product_video_fill_content_2").text();
		
		showContentPopup( content,"product_video_fill_content_2", "product_video_fill_add_content_2", "product_video_2_edit_btn");
	});
		
	$("#product_title_1_add_btn").click(function()
	{
		showContentPopup( "","product_fill_image_content_1", "product_fill_image_add_content_1", "product_title_1_edit_btn");
	});
	
	$("#product_title_1_edit_btn").click(function()
	{
		var content = $("#product_fill_image_content_1").text();
		
		showContentPopup( content,"product_fill_image_content_1", "product_fill_image_add_content_1", "product_title_1_edit_btn");
	});
	
	$("#product_title_2_add_btn").click(function()
	{
		showContentPopup( "","product_fill_image_content_2", "product_fill_image_add_content_2", "product_title_2_edit_btn");
	});
	
	$("#product_title_2_edit_btn").click(function()
	{
		var content = $("#product_fill_image_content_2").text();
		
		showContentPopup( content,"product_fill_image_content_2", "product_fill_image_add_content_2", "product_title_2_edit_btn");
	});
	
	$("#product_title_3_add_btn").click(function()
	{
		
		showContentPopup( "","product_fill_image_content_3", "product_fill_image_add_content_3", "product_title_3_edit_btn");
	});
	
	$("#product_title_3_edit_btn").click(function()
	{
		var content = $("#product_fill_image_content_3").text();
		
		showContentPopup( content,"product_fill_image_content_3", "product_fill_image_add_content_3", "product_title_3_edit_btn");
	});
	
	$("#product_title_4_add_btn").click(function()
	{
		showContentPopup( "","product_fill_image_content_4", "product_fill_image_add_content_4", "product_title_4_edit_btn");
	});
	
	$("#product_title_4_edit_btn").click(function()
	{
		var content = $("#product_fill_image_content_4").text();
		
		showContentPopup( content,"product_fill_image_content_4", "product_fill_image_add_content_4", "product_title_4_edit_btn");
	});
	
	$("#img_prev_1_del").click(function()
	{
		removeImage( "product_file_upload_form_1", "img_prev_1", "img_prev_1_del" );
		
		product_hasImage_1 = false;
		
	});
	
	$("#img_prev_2_del").click(function()
	{
		removeImage( "product_file_upload_form_2", "img_prev_2", "img_prev_2_del" );	
		
		product_hasImage_2 = false;
		
	});
	
	$("#img_prev_3_del").click(function()
	{
		removeImage( "product_file_upload_form_3", "img_prev_3", "img_prev_3_del" );	
		
		product_hasImage_3 = false;
	});
	
	$("#img_prev_4_del").click(function()
	{
		removeImage( "product_file_upload_form_4", "img_prev_4", "img_prev_4_del" );	
		
		product_hasImage_4 = false;
	});

});

function Load_Content_Product()
{
	// Getting the Content fill Product Data
	
	var SendingData =
	{
		'Action' : 'ContentProduct_Read',
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

	};

	doAjaxRequest("POST", "TemplateDataServlet", SendingData, fn_show_content_product_completed, null);
	
}

var product_hasImage_1=false;
var product_hasImage_2=false;
var product_hasImage_3=false;
var product_hasImage_4=false;


var product_image_1_imgName_="";
var product_image_2_imgName_="";
var product_image_3_imgName_="";
var product_image_4_imgName_="";

var fn_show_content_product_completed=function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		$("#product_fill_video_url_1").val(ResultObj.videoURL_1_);
		
		$("#product_fill_video_url_2").val(ResultObj.videoURL_2_);
		
		if( ResultObj.videoURL_1_content_ == "" || ResultObj.videoURL_1_content_ == null )
		{
			enableAddContent( "product_video_fill_add_content_1","product_video_fill_content_1","product_video_1_edit_btn");
		}
		else
		{
			enableContent( ResultObj.videoURL_1_content_,"product_video_fill_add_content_1","product_video_fill_content_1","product_video_1_edit_btn");
		}
		
		if( ResultObj.videoURL_2_content_ == "" || ResultObj.videoURL_2_content_ == null )
		{
			enableAddContent( "product_video_fill_add_content_2","product_video_fill_content_2","product_video_2_edit_btn");
		}
		else
		{
			enableContent( ResultObj.videoURL_2_content_,"product_video_fill_add_content_2","product_video_fill_content_2","product_video_2_edit_btn");
		}
		
		$("#product_fill_image_text_1").val(ResultObj.image_1_title_);
		$("#product_fill_image_text_2").val(ResultObj.image_2_title_);
		$("#product_fill_image_text_3").val(ResultObj.image_3_title_);
		$("#product_fill_image_text_4").val(ResultObj.image_4_title_);

		
		if( ResultObj.image_1_content == "" || ResultObj.image_1_content == null )
		{
			enableAddContent( "product_fill_image_add_content_1","product_fill_image_content_1","product_title_1_edit_btn" );	
		}
		else
		{
			enableContent( ResultObj.image_1_content,"product_fill_image_add_content_1","product_fill_image_content_1","product_title_1_edit_btn");
		}
		
		if( ResultObj.image_2_content == "" || ResultObj.image_2_content == null )
		{
			enableAddContent( "product_fill_image_add_content_2","product_fill_image_content_2","product_title_2_edit_btn" );
		}
		else
		{
			enableContent( ResultObj.image_2_content,"product_fill_image_add_content_2","product_fill_image_content_2","product_title_2_edit_btn");
		}
		
		if( ResultObj.image_3_content == "" || ResultObj.image_3_content == null )
		{
			enableAddContent( "product_fill_image_add_content_3","product_fill_image_content_3","product_title_3_edit_btn" );
		}
		else
		{
			enableContent( ResultObj.image_3_content,"product_fill_image_add_content_3","product_fill_image_content_3","product_title_3_edit_btn");
		}
		
		if( ResultObj.image_4_content == "" || ResultObj.image_4_content == null )
		{
			enableAddContent( "product_fill_image_add_content_4","product_fill_image_content_4","product_title_4_edit_btn" );
		}
		else
		{
			enableContent( ResultObj.image_4_content,"product_fill_image_add_content_4","product_fill_image_content_4","product_title_4_edit_btn");
			
		}
		
		product_hasImage_1=ResultObj.image_1_hasimage_;
		product_hasImage_2=ResultObj.image_2_hasimage_;
		product_hasImage_3=ResultObj.image_3_hasimage_;
		product_hasImage_4=ResultObj.image_4_hasimage_;
		
		product_image_1_imgName_= ResultObj.image_1_imgName_;
		product_image_2_imgName_= ResultObj.image_2_imgName_;
		product_image_3_imgName_= ResultObj.image_3_imgName_;
		product_image_4_imgName_= ResultObj.image_4_imgName_;
		
		
		if(ResultObj.image_1_hasimage_)
		{
			$("#img_prev_1").attr('src', ResultObj.image_directory+product_image_1_imgName_);
			$("#img_prev_1_del").show();
		}
		
		if(ResultObj.image_2_hasimage_)
		{
			$("#img_prev_2").attr('src', ResultObj.image_directory+product_image_2_imgName_);
			$("#img_prev_2_del").show();
		}
		
		if(ResultObj.image_3_hasimage_)
		{
			$("#img_prev_3").attr('src', ResultObj.image_directory+product_image_3_imgName_);
			$("#img_prev_3_del").show();
		}
		
		if(ResultObj.image_4_hasimage_)
		{
			$("#img_prev_4").attr('src', ResultObj.image_directory+product_image_4_imgName_);
			$("#img_prev_4_del").show();
		}
		
		validate_content_Products();
	}

};


/*
 * ----------------------------------------------------------------------------------------------------
 * 
 * PRODUCT
 * 
 * ----------------------------------------------------------------------------------------------------
 */

function save_content_product()
{

	var result=validate_content_Products();
	
	if(result=="TRUE")
	{
		
		var formdata = new FormData();
		formdata.append('Action', 'ContentProduct_Save');
		formdata.append('cmpRegnKey', ExternalPageData.cmpRegnKey);
		formdata.append('externalPageID', ExternalPageData.externalPageID);
		formdata.append('compnayURLName', ExternalPageData.compnayURLName);
		formdata.append('pageType', ExternalPageData.pageType);
	
		formdata.append("videoURL_1_", $("#product_fill_video_url_1").val());
		formdata.append("videoURL_1_content_", $("#product_video_fill_content_1").text());
		
		formdata.append("videoURL_2_", $("#product_fill_video_url_2").val());
		formdata.append("videoURL_2_content_", $("#product_video_fill_content_2").text());
		
		formdata.append("image_1_data", product_file_upload_form_1.product_file_upload_control_1.files[0]);
		formdata.append("image_1_title", $("#product_fill_image_text_1").val());
		formdata.append("image_1_content", $("#product_fill_image_content_1").text());
		
		formdata.append("image_2_data", product_file_upload_form_2.product_file_upload_control_2.files[0]);
		formdata.append("image_2_title", $("#product_fill_image_text_2").val());
		formdata.append("image_2_content", $("#product_fill_image_content_2").text());
		
		formdata.append("image_3_data", product_file_upload_form_3.product_file_upload_control_3.files[0]);
		formdata.append("image_3_title", $("#product_fill_image_text_3").val());
		formdata.append("image_3_content", $("#product_fill_image_content_3").text());
		
		formdata.append("image_4_data", product_file_upload_form_4.product_file_upload_control_4.files[0]);
		formdata.append("image_4_title", $("#product_fill_image_text_4").val());
		formdata.append("image_4_content", $("#product_fill_image_content_4").text());
		
		formdata.append("image_1_hasimage_",product_hasImage_1);
		formdata.append("image_2_hasimage_",product_hasImage_2);
		formdata.append("image_3_hasimage_",product_hasImage_3);
		formdata.append("image_4_hasimage_",product_hasImage_4);
		
		formdata.append("image_1_imgName_",product_image_1_imgName_);
		formdata.append("image_2_imgName_",product_image_2_imgName_);
		formdata.append("image_3_imgName_",product_image_3_imgName_);
		formdata.append("image_4_imgName_",product_image_4_imgName_);
	
		doAjaxRequestUpload("POST", "TemplateDataServlet", formdata, fn_save_content_product_completed, null);
	}
	else
	{
		//ShowToast(result);
	}

}

function validate_content_Products()
{
	var hasonecontent = false;
	
	isProductsCompleted = false;

	// Video content 1 validation
	if ($("#product_fill_video_url_1").val() != "")
	{
		if ($("#product_fill_video_url_1_Error").hasClass("URLErrorImg"))
		{
			return "Enter valid URL for video 1";
		}

		if ($("#product_video_fill_content_1").text() == "")
		{
			return "Enter video 1 content";
		}

		hasonecontent = true;
	}
	
	// Video content 2 validation

	if ($("#product_fill_video_url_2").val() != "")
	{
		if ($("#product_fill_video_url_2_Error").hasClass("URLErrorImg"))
		{
			return "Enter Valid URL for Video 2";
		}

		if ($("#product_video_fill_content_2").text() == "")
		{
			return "Enter video 2 content";
		}

		hasonecontent = true;
	}
	
	// Image content 1 validation 
	
	if( $("#product_fill_image_text_1").val() != "" && $("#product_fill_image_content_1").text() != "" )
	{
		hasonecontent = true;
	}
	
	else if ( $("#product_fill_image_text_1").val() != "" || $("#product_fill_image_content_1").text() != "" )
	{
		
		if( $("#product_fill_image_text_1").val() == "" && $("#product_fill_image_content_1").text() != "" )
			return "Enter Title 1";
		
		if( $("#product_fill_image_content_1").text() == "" && $("#product_fill_image_text_1").val() != "" )
			return "Enter Content 1";
	}
	else if( $("#product_fill_image_text_1").val() == "" || $("#product_fill_image_content_1").text() == "" )
	{
		if(product_hasImage_1)
		{
			return "Enter Title1 and Content1";
		}
	}
	
	// Image content 2 validation 
	
	if( $("#product_fill_image_text_2").val() != "" && $("#product_fill_image_content_2").text() != "" )
	{
		hasonecontent = true;
	}
	
	else if ($("#product_fill_image_text_2").val() != "" || $("#product_fill_image_content_2").text() != "")
	{
		if($("#product_fill_image_text_2").val() == "" && $("#product_fill_image_content_2").text() != "")
			return "Enter Title 2";
		
		if($("#product_fill_image_content_2").text() == "" && $("#product_fill_image_text_2").val() != "")
			return "Enter Content 2";
	}
	else if( $("#product_fill_image_text_2").val() == "" || $("#product_fill_image_content_2").text() == "" )
	{
		if(product_hasImage_2)
		{
			return "Enter Title2 and Content2";
		}
	}
	
	// Image content 3 validation 
	
	if( $("#product_fill_image_text_3").val() != "" && $("#product_fill_image_content_3").text() != "" )
	{
		hasonecontent = true;
	}
	
	else if ($("#product_fill_image_text_3").val() != "" || $("#product_fill_image_content_3").text() != "")
	{
		if($("#product_fill_image_text_3").val() == "" && $("#product_fill_image_content_3").text() != "")
			return "Enter Title 3";
		
		if($("#product_fill_image_content_3").text() == "" && $("#product_fill_image_text_3").val() != "")
			return "Enter Content 3";
	}
	else if( $("#product_fill_image_text_3").val() == "" || $("#product_fill_image_content_3").text() == "" )
	{
		if(product_hasImage_3)
		{
			return "Enter Title3 and Content3";
		}
	}
	
	// Image content 4 validation 
	
	if( $("#product_fill_image_text_4").val() != "" && $("#product_fill_image_content_4").text() != "" )
	{
		hasonecontent = true;
	}
	
	else if ($("#product_fill_image_text_4").val() != "" || $("#product_fill_image_content_4").text() != "")
	{
		if($("#product_fill_image_text_4").val() == "" && $("#product_fill_image_content_4").text() != "")
			return "Enter Title 4";
		
		if($("#product_fill_image_content_4").text() == "" && $("#product_fill_image_text_4").val() != "")
			return "Enter Content 4";
	}
	else if( $("#product_fill_image_text_4").val() == "" || $("#product_fill_image_content_4").text() == "" )
	{
		if(product_hasImage_4)
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

	isProductsCompleted = true;
	
	return "TRUE";

}

var fn_save_content_product_completed = function(ResultObj)
{
	
		if (ResultObj.result == "success")
		{
			//ShowToast("Saved Successfully");
		}
};
/**
 * Load Content aboutus
 */

$(function()
{

	/* Picture Upload - 1 */
	$("#aboutus_file_upload_control_1").change(function()
	{
		readURLImage(this, "#aboutus_img_prev_1");

	});
	
	$("#aboutus_title_1_add_btn").click(function()
	{
		showContentPopup( "","aboutus_fill_image_content_1", "aboutus_fill_image_add_content_1", "aboutus_title_1_edit_btn");
	});
			
	$("#aboutus_title_1_edit_btn").click(function()
	{
		var content = $("#aboutus_fill_image_content_1").text();
				
		showContentPopup( content,"aboutus_fill_image_content_1", "aboutus_fill_image_add_content_1", "aboutus_title_1_edit_btn");
	});
	$("#aboutus_img_prev_1_del").click(function()
	{
		removeImage( "aboutus_file_upload_form_1", "aboutus_img_prev_1", "aboutus_img_prev_1_del" );
						
	});

});

function Load_Content_aboutus()
{
	// Getting the Content fill aboutus Data

	var SendingData =
	{
		'Action' : 'ContentAboutUs_Read',
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

	};

	doAjaxRequest("POST", "TemplateDataServlet", SendingData, fn_show_content_aboutus_completed, null);

}

var aboutus_hasImage_1 = false;

var aboutus_image_1_imgName_ = "";

var fn_show_content_aboutus_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		$("#aboutus_fill_image_text_1").val(ResultObj.image_1_title_);
		
		if( ResultObj.image_1_content == "" || ResultObj.image_1_content == null )
		{
			enableAddContent( "aboutus_fill_image_add_content_1","aboutus_fill_image_content_1","aboutus_title_1_edit_btn" );
		}
		else
		{
			enableContent( ResultObj.image_1_content,"aboutus_fill_image_add_content_1","aboutus_fill_image_content_1","aboutus_title_1_edit_btn");
			
		}
		

		if(ResultObj.image_1_hasimage_)
		{
			$("#aboutus_img_prev_1_del").show();
		}
		
		aboutus_hasImage_1 = ResultObj.image_1_hasimage_;

		aboutus_image_1_imgName_ = ResultObj.image_1_imgName_;

		if (ResultObj.image_1_hasimage_)
			$("#aboutus_img_prev_1").attr('src', ResultObj.image_directory + aboutus_image_1_imgName_);
		
		validate_Content_AboutUs();

	}

};

// -------------------------------------------------------------------
// Save Service
// -------------------------------------------------------------------

function save_content_aboutus()
{
	var result = validate_Content_AboutUs();
	
	if (result == "TRUE")
	{
		var formdata = new FormData();
		formdata.append('Action', 'ContentAboutUs_Save');
		formdata.append('cmpRegnKey', ExternalPageData.cmpRegnKey);
		formdata.append('externalPageID', ExternalPageData.externalPageID);
		formdata.append('compnayURLName', ExternalPageData.compnayURLName);
		formdata.append('pageType', ExternalPageData.pageType);
		formdata.append("image_1_data", aboutus_file_upload_form_1.aboutus_file_upload_control_1.files[0]);
		formdata.append("image_1_title", $("#aboutus_fill_image_text_1").val());
		formdata.append("image_1_content", $("#aboutus_fill_image_content_1").text());
		formdata.append("image_1_hasimage_", aboutus_hasImage_1);
		formdata.append("image_1_imgName_", aboutus_image_1_imgName_);
		doAjaxRequestUpload("POST", "TemplateDataServlet", formdata, fn_save_content_aboutus_completed, null);
	}
	else
	{
		//ShowToast(result);
	}

}

function validate_Content_AboutUs()
{
	
	isAboutUsCompleted = false;
	 
	if ($("#aboutus_fill_image_text_1").val() == "")
	{
		return "Fill the Title";
	}

	if ($("#aboutus_fill_image_content_1").text() == "" || $("#aboutus_fill_image_content_1").text() == "null" )
	{
		return "Fill the Content";
	}

	isAboutUsCompleted = true;
	
	return "TRUE";
}

var fn_save_content_aboutus_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		//ShowToast("Saved Successfully");
	}
};

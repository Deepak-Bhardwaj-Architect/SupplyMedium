/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any. - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

var fileUploadthis;
var currentSelectedItem="";

var isHomeCompleted;
var isProductsCompleted;
var isSolutionsCompleted;
var isServicesCompleted;
var isAboutUsCompleted;
var isContactUsCompleted;

$(function()
{

	/* Previous Button */
	$("#btn_fillcontent_previous").click(function()
	{
		currentSelectedItem=$("#menu_selection_selectbox").val();
		//show_structure();
		previousContent();
		
			
	});
	
	/* previous button small */
	
	$("#btn_fillcontent_previews_small").click( function()
	{
		currentSelectedItem=$("#menu_selection_selectbox").val();
		//show_structure();
		previousContent();
		
	});

	/* Next Button */
	$("#btn_fillcontent_next").click(function()
	{
		nextContent();
		
	});
	
	/* Save Button */
	$("#btn_fill_save").click(function()
	{
		//saveContent();
		
		nextContent();
		
	});
	
	/* Select Box */

	$("#menu_selection_selectbox").change(function()
			
	{
		$(".fill_sel_menu").hide();

		$("#" + $("#menu_selection_selectbox").val()).show();
		

	});

	/* VideoURL ----------- */

	$(".video_url_textbox").blur(function()
	{
		checkURL(this);
	});
});

function removeImage( formId, previewId, delBtnId )
{
	$('#'+formId)[0].reset();
	
	$("#"+previewId).attr('src', getBaseURL()+"/Views/ExternalPage/images/default_img.png");
	
	$("#"+delBtnId).hide();
}

function showContentPopup( text,contentId, addContentId, editBtnId)
{
	$("#content_txtarea").val( text );
	
	$("#add_content_popup").show();
	
	$("#content_txtarea").focus();
	
	tmpStr = $("#content_txtarea").val();
	$("#content_txtarea").val('');
	$("#content_txtarea").val(tmpStr);
									
	$("#content_div_id").val( contentId );
					
	$("#add_content_div_id").val( addContentId );
	
	$("#edit_content_div_id").val( editBtnId );
}

function saveContent()
{
	var selectedValue=$("#menu_selection_selectbox").val();
	
	if ( selectedValue == "fill_home" )
	{
		save_content_home();
	}
	else if (selectedValue == "fill_products" )
	{
		save_content_product();
	}
	else if (selectedValue == "fill_services" )
	{
		save_content_services();
	}
	else if (selectedValue == "fill_solutions" )
	{
		save_content_solutions();
	}
	else if (selectedValue == "fill_aboutus" )
	{
		save_content_aboutus();
	}		
	else if (selectedValue == "fill_contactus" )
	{
		save_content_contactus();
	}
}

function nextContent()
{
	$("#fill_content_err").hide();
	
	var selIndex = $("#menu_selection_selectbox").prop("selectedIndex");
	
	saveContent();
	
	setNextStepImage( (selIndex+1) );
	
	selIndex = selIndex + 1;
	
	setAllCompletedStepImage();
	
	if( selIndex >= $('#menu_selection_selectbox option').size() )
	{
		var result =  isValidContent();
		
		if( result == 'TRUE')
			show_template();
	}
	else
	{
		var selValue = $('#menu_selection_selectbox :nth-child('+(selIndex+1)+')').val();
		
		$(".fill_sel_menu").hide();

		$("#" + selValue ).show();
		
		$("#menu_selection_selectbox").val(selValue);
		
		setProcessStepImage( (selIndex+1) );
	}
	
}

function setAllCompletedStepImage()
{
	var size = $('#menu_selection_selectbox option').size();
	
	for( var i=0; i<size; i++ )
	{
		var selValue = $('#menu_selection_selectbox :nth-child('+(i+1)+')').text();
		
		if( selValue == "Home" )
		{
			if( isHomeCompleted )
				setCompleteStepImage( (i+1) );
		}
		else if( selValue == "Products" )
		{
			if(isProductsCompleted)
				setCompleteStepImage( (i+1) );
		}
		else if( selValue == "Solutions" )
		{
			if(isSolutionsCompleted)
				setCompleteStepImage( (i+1) );
		}
		else if( selValue == "Services" )
		{
			if( isServicesCompleted )
				setCompleteStepImage( (i+1) );
		}
		else if( selValue == "About Us" )
		{
			if( isAboutUsCompleted )
				setCompleteStepImage( (i+1) );
		}
		else if( selValue == "Contact Us")
		{
			
			if( isContactUsCompleted )
				setCompleteStepImage( (i+1) );
		}
	}
}

function setCompleteStepImage( stepNo )
{
	$("#step_"+stepNo).removeClass('stepnext');
	
	$("#steptext_"+stepNo).removeClass('stepnexttext');
	
	$("#step_"+stepNo).addClass('stepcompleted');
	
	$("#steptext_"+stepNo).addClass('stepcompletedtext');
	
	$("#step_"+stepNo).removeClass('stepprocess');
	
	$("#steptext_"+stepNo).removeClass('stepprocesstext');
}

function setProcessStepImage( stepNo )
{
	$("#step_"+stepNo).removeClass('stepnext');
	
	$("#steptext_"+stepNo).removeClass('stepnexttext');
	
	$("#step_"+stepNo).removeClass('stepcompleted');
	
	$("#steptext_"+stepNo).removeClass('stepcompletedtext');
	
	$("#step_"+stepNo).addClass('stepprocess');
	
	$("#steptext_"+stepNo).addClass('stepprocesstext');
}

function setNextStepImage( stepNo )
{
	$("#step_"+stepNo).addClass('stepnext');
	
	$("#steptext_"+stepNo).addClass('stepnexttext');
	
	$("#step_"+stepNo).removeClass('stepcompleted');
	
	$("#steptext_"+stepNo).removeClass('stepcompletedtext');
	
	$("#step_"+stepNo).removeClass('stepprocess');
	
	$("#steptext_"+stepNo).removeClass('stepprocesstext');
	
}

function previousContent()
{
	$("#fill_content_err").hide();
	
	var selIndex = $("#menu_selection_selectbox").prop("selectedIndex");
	
	saveContent();
	
	setNextStepImage( (selIndex+1) );
	
	selIndex = selIndex - 1;
	
	setAllCompletedStepImage();
	
	
	if( selIndex == -1 )
	{
		show_structure();
	}
	else
	{
		var selValue = $('#menu_selection_selectbox :nth-child('+(selIndex+1)+')').val();
		
		$(".fill_sel_menu").hide();

		$("#" + selValue ).show();
		
		$("#menu_selection_selectbox").val(selValue);
		
		setProcessStepImage( (selIndex+1) );
	}
}


function isValidContent()
{
	var Result=validate_Content_Home();
	
	$("#fill_content_err").show();
	
	if(Result!="TRUE")
	{
		$("#fill_content_err").text( Result );
		
		$(".fill_sel_menu").hide();
		
		var value = "fill_home";
		
		
		var text = "Home";
		
		var index = $("#menu_selection_selectbox > option:contains("+text+")").index();
		
		setProcessStepImage( (index+1) );
		

		$("#"+value).show();
		
		$("#menu_selection_selectbox").val(value);
		
		return "FALSE";
	}
	
	if ($("#menu_products").attr('checked') == 'checked')
	{
		Result=validate_content_Products();
		
		if(Result!="TRUE")
		{
			$("#fill_content_err").text( Result );
			
			$(".fill_sel_menu").hide();
			
			var value = "fill_products";
			
			var text = "Products";
			
			var index = $("#menu_selection_selectbox > option:contains("+text+")").index();
			
			setProcessStepImage( (index+1) );
			
			
			

			$("#"+value).show();
			
			$("#menu_selection_selectbox").val(value);
			
			return "FALSE";
		}
	}
	
	if ($("#menu_solutions").attr('checked') == 'checked')
	{
		Result=validate_content_Solutions();
		
		if(Result!="TRUE")
		{
			$("#fill_content_err").text( Result );
			
			$(".fill_sel_menu").hide();
			
			var value = "fill_solutions";
			
			
			
			var text = "Solutions";
			
			var index = $("#menu_selection_selectbox > option:contains("+text+")").index();
			
			setProcessStepImage( (index+1) );
			
			
			

			$("#"+value).show();
			
			$("#menu_selection_selectbox").val(value);
			
			return "FALSE";
		}
	}
	
	if ($("#menu_service").attr('checked') == 'checked')
	{		
		Result=validate_content_Services();
		
		if(Result!="TRUE")
		{
			$("#fill_content_err").text( Result );
			
			$(".fill_sel_menu").hide();
			
			var value = "fill_services";
			
			var text = "Services";
			
			var index = $("#menu_selection_selectbox > option:contains("+text+")").index();
			
			setProcessStepImage( (index+1) );
			

			$("#"+value).show();
			
			$("#menu_selection_selectbox").val(value);
			
			return "FALSE";
		}		
	}
	
	Result=validate_Content_AboutUs();
	if(Result!="TRUE")
	{
		$("#fill_content_err").text( Result );
		
		$(".fill_sel_menu").hide();
		
		var value = "fill_aboutus";
		
		var text = "About Us";
		
		var index = $("#menu_selection_selectbox > option:contains("+text+")").index();
		
		setProcessStepImage( (index+1) );
		

		$("#"+value).show();
		
		$("#menu_selection_selectbox").val(value);
		
		return "FALSE";
	}
	
	
	Result=validate_content_contactus();
	if(Result!="TRUE")
	{
		$("#fill_content_err").text( Result );
		
		$(".fill_sel_menu").hide();
		
		var value = "fill_contactus";
		
		var text = "Contact Us";
		
		var index = $("#menu_selection_selectbox > option:contains("+text+")").index();
		
		setProcessStepImage( (index+1) );
		

		$("#"+value).show();
		
		$("#menu_selection_selectbox").val(value);
		
		return "FALSE";
	}
	
	$("#fill_content_err").hide();
	
	return "TRUE";
	
}

function readURLImage(input,imageDivID)
{
	var ext = input.files[0].name.split('.').pop().toLowerCase();
	if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
	    
		alert('invalid file format!');
		input.files[0]="";
	    return
	}
	
	
	if (input.files && input.files[0])
	{
		var reader = new FileReader();

		reader.onload = function(e)
		{
			$(imageDivID).attr('src', e.target.result);
			
			$(imageDivID+"_del").show();
			
		};

		reader.readAsDataURL(input.files[0]);
	}
}

function show_content()
{

	$("#web_page_structure").hide();
	$("#fill_content").show();
	$("#template").hide();

	// Re assigning the Menu Selection Select Box

	var menuSelBox = $("#menu_selection_selectbox");
	menuSelBox.empty();

	menuSelBox.append('<option value="fill_home">Home</option>');

	if ($("#menu_products").attr('checked') == 'checked')
	{
		menuSelBox.append('<option value="fill_products">Products</option>');
	}

	if ($("#menu_solutions").attr('checked') == 'checked')
	{
		menuSelBox.append('<option  value="fill_solutions">Solutions</option>');
	}

	if ($("#menu_service").attr('checked') == 'checked')
	{
		menuSelBox.append('<option value="fill_services">Services</option>');
	}

	menuSelBox.append('<option value="fill_aboutus">About Us</option>');
	menuSelBox.append('<option value="fill_contactus">Contact Us</option>');

	if(isValueExist(currentSelectedItem))
	{
		document.getElementById("menu_selection_selectbox").value=currentSelectedItem;
	}
	else
	{
			document.getElementById("menu_selection_selectbox").selectedIndex =0;
			$(".fill_sel_menu").hide();
			$("#" + $("#menu_selection_selectbox").val()).show();	
	}
			
	$("#menu_webstructure").removeClass();
	$("#menu_webstructure").addClass("temp_menu_normal");
	$("#menu_fill_content").removeClass();
	$("#menu_fill_content").addClass("temp_menu_selected");
	$("#menu_template").removeClass();
	$("#menu_template").addClass("temp_menu_normal");
	
	setContentSteps();
	
}

function setContentSteps()
{
	var size = $('#menu_selection_selectbox option').size();
	
	$("#steps").empty();
	
	if( size == 6 )
		$("#steps").css("width","630px");
	else if( size == 5 )
		$("#steps").css("width","520px");
	else if( size == 4 )
		$("#steps").css("width","410px");
		
	for( var i=0; i<size; i++ )
	{
		var selValue = $('#menu_selection_selectbox :nth-child('+(i+1)+')').text();
		
		var stepStr = "";
		
		if( i== 0 )
		{
			stepStr = '<div id="step_'+(i+1)+'" style="margin-left:0px;" class="stepprocess">';
			stepStr += '<div class="stepno" id="stepno'+(i+1)+'">'+(i+1)+'</div>';
			stepStr += '<div class="stepprocesstext" id="steptext_'+(i+1)+'">'+selValue+'</div>';
		}
		else
		{
			stepStr = '<div id="step_'+(i+1)+'" style="margin-left:0px;" class="stepnext">';
			stepStr += '<div class="stepno" id="stepno'+(i+1)+'">'+(i+1)+'</div>';
			stepStr += '<div class="stepnexttext" id="steptext_'+(i+1)+'">'+selValue+'</div>';
		}
			
		stepStr += '</div>';
		
		$("#step_"+(i+1)).live("click",function(e)
		{
			$("#fill_content_err").hide();
			
			var selIndex = $("#menu_selection_selectbox").prop("selectedIndex");
			
			saveContent();
			
			setNextStepImage( (selIndex+1) );
			
			setAllCompletedStepImage();
			
			var arr = this.id.split("_");
			
			
			
			selIndex = arr[1]-1;
			
			if( selIndex >= $('#menu_selection_selectbox option').size() )
			{
				var result =  isValidContent();
				
				if( result == 'TRUE')
					show_template();
			}
			else
			{
				var selValue = $('#menu_selection_selectbox :nth-child('+(selIndex+1)+')').val();
				
				$(".fill_sel_menu").hide();

				$("#" + selValue ).show();
				
				$("#menu_selection_selectbox").val(selValue);
				
				setProcessStepImage( (selIndex+1) );
			}
		});
		
		if( i != (size-1))
			stepStr += '<div class="stepseparator"></div>';
			
		$("#steps").append( stepStr );
		
		
	}
	
	setAllCompletedStepImage();
	
	setProcessStepImage( 1 );
}

function isValueExist(valuee)
{
	var exists=false;
	$('#menu_selection_selectbox option').each(function(){
		
	    if (this.value == valuee) {
	    	exists=true;	        
	    }
	});
	
	return exists;
}

function checkURL(divthis)
{
	 if($(divthis).val()!="")
	 {
		var Data =
		{
			'Action' : 'isValid_URL',
			'URL' : $(divthis).val(),
		};
	
		doAjaxRequestWithParameter("POST", "TemplateDataServlet", Data, fn_checkURL_completed, null, divthis.id);
	}
	 else
		 {
		 	$("#" + divthis.id + "_Error").removeClass();		 	
		 	$("#" + divthis.id + "_Error").addClass("URLNormalImg");
		 }
}

var fn_checkURL_completed = function(ResultOBJ, divID)
{
	if (ResultOBJ.result == "success")
	{
		$("#" + divID + "_Error").removeClass();

		if (ResultOBJ.isValid_URL)
		{
			$("#" + divID + "_Error").addClass("URLSuccessImg");
		}
		else
		{
			$("#" + divID + "_Error").addClass("URLErrorImg");
		}
	}
};




function enableAddContent( addContentId,contentId,editBtnId )
{
	$("#"+contentId).hide();
	$("#"+editBtnId).hide();
	
	$("#"+addContentId).show();
}

function enableContent( text,addContentId,contentId,editBtnId)
{
	$("#"+contentId).show();
	$("#"+contentId).text( text );
	$("#"+editBtnId).show();
	
	$("#"+addContentId).hide();
}

function fileUpload(formid, fileUploadthis)
{

	var form = $(formid);

	var data = new FormData();

	var regnKey = "9789077580";

	var userKey = "anbu_gpt@yahoo.co.in";

	data.append('RequestType', 'ImageUpload');

	data.append('RegnKey', regnKey);

	data.append('UserKey', userKey);

	data.append('ImageUpload', fileUploadthis.files[0]);

	$.ajax(
	{
		type : "POST",
		url : form.attr('action'),
		data : data,
		contentType : false,
		processData : false,
		success : function(data)
		{


			if (data.result == "success")
			{

				$('#feedTitle').val("");

				$('#feedDesc').val("");

				var feedArr = new Array(data.feeds.length);

				feedArr = data.feeds;

				placeFeeds(feedArr, "top");

			}

		},
		error : function(xhr, textStatus, errorThrown)
		{

		}
	});

}

function doAjaxRequestUpload(requestType, servletName, data, completeSuccessFun, ErrorFun)
{

	$.ajax(
	{
		type : requestType,
		url : getBaseURL() + '/' + servletName,
		data : data,
		contentType : false,
		processData : false,
		success : function(ResultObj)
		{
			if (completeSuccessFun != null)
				completeSuccessFun(ResultObj);
		},
		error : function()
		{
			if (ErrorFun != null)
				ErrorFun();
		}
	});
}
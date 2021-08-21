/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any. - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

var ExternalPageID = 0;

var regnKey = $("#regnkey").val();

var ExternalPageData =
{
	'cmpRegnKey' : regnKey,
	'externalPageID' : '',
	'compnayURLName' : '',
	'pageType' : '',
};



/* Constructor */
$(function()
{
	$( '.tooltip' ).tooltip();
	
	$("#content_loader").hide();
	
	/* External web radio button click function */
	$("#radio_ExternalWeb").click(function()
	{
		$(".template_link").hide();
		$(".external_link").show();
		$(".external_message").hide();
		$("#txt_external_link").val("");
	});

	$("#radio_ExternalWeb").click(); // By Default Selection

	/* Template web radio button click function */
	$("#radio_Template").click(function()
	{
		$(".template_link").show();
		$(".external_link").hide();
	});

	/* LINK Button Click */
	$("#btn_external_link").click(validateCompanyExternalWebLink);
	$("#btn_external_retry").click(validateCompanyExternalWebLink);

	/* BTN Internal Template Processed */
	$("#btn_template_proceed").click(function()
	{
		if ($("#text_company_url").val() == "")
		{
			ShowToast("Enter the company URL Name.");
			return;
		}

		sendInserUpdateRequest(ExternalPageTemplate_Completed);

	});

	/*
	 * $("#menu_webstructure").click(show_structure);
	 * $("#menu_fill_content").click(show_content);
	 * $("#menu_template").click(show_template);
	 */

	LoadExternalPageInfo();
	
	$("#content_save_btn").click(function()
	{
		var contentDivId = $("#content_div_id").val();
		
		var addContentDivId = $("#add_content_div_id").val();
		
		var editContentDivId = $("#edit_content_div_id").val();
		
		var content = $("#content_txtarea").val();
		
		if( content == "" )
		{
			$("#"+addContentDivId).show();
			
			$("#"+contentDivId).hide();
			
			$("#"+editContentDivId).hide();
			
			$("#"+contentDivId).text("");
		}
		else
		{
			$("#"+addContentDivId).hide();
			
			$("#"+contentDivId).show();
			
			$("#"+contentDivId).text(content);
			
			$("#"+editContentDivId).show();
		}
		
		$("#add_content_popup").hide();
		
	});
	
	


});

function ExternalPageTemplate_Completed(ResultObj)
{
	if (ResultObj.result == "success")
	{

		ExternalPageData.cmpRegnKey = regnKey;
		var companyURLName = $("#text_company_url").val();
		
		ExternalPageData.compnayURLName = companyURLName;
		ExternalPageData.pageType = 'ExternalPage';
		if (ExternalPageData.externalPageID === undefined)
		{

			ExternalPageID = ResultObj.insertAddrId;
			ExternalPageData.externalPageID = ExternalPageID;
		}

		$("#webselection").hide();
		$("#template_conatiner").show();

		getAlltheDataforTemplate();
	}

	else if (ResultObj.result == "companyNameURLExists")
	{
		ShowToast('Company name already exists. Please try with different company name');
	}
	else
	{
		ShowToast('An error occured while linking to External Page. Please try again later');
	}
}

function LoadExternalPageInfo()
{
	$.ajax(
	{
		type : 'POST',
		url : getBaseURL() + '/externalpageservlet',
		data :
		{
			'Action' : 'LoadExternalPage',
			'cmpRegnKey' : regnKey
		},
		cache : false,
		success : function(ResultObj)
		{

			if (ResultObj.result == "success")
			{
				ExternalPageData.cmpRegnKey = ResultObj.companyregKey;
				ExternalPageData.compnayURLName = ResultObj.compnayURLName;
				ExternalPageData.pageType = ResultObj.pageType;
				ExternalPageData.externalPageID = ResultObj.insertAddrId;

				ExternalPageID = ResultObj.insertAddrId;

				$("#text_company_url").val(ResultObj.compnayURLName);

				if (ResultObj.pageType == "ExternalLink")
				{
					$('#radio_ExternalWeb').attr("checked", "checked");
					Load_Content_WebSite();
					
				}
				else if (ResultObj.pageType == "ExternalPage")
				{
					$('#radio_Template').attr("checked", "checked");
					$(".template_link").show();
					$(".external_link").hide();
					$("#btn_template_proceed").val("Edit");
				}
			}

		},
		error : function()
		{

			ExternalPageID = 0;

		}
	});
}

function validateCompanyExternalWebLink()
{
	var linkURL = $("#txt_external_link").val();
	var companyURLName = $("#text_company_url").val();

	if (companyURLName == "")
	{
		ShowToast("Enter the company URL.");
		return;
	}

	if (linkURL != "")
	{
		sendInserUpdateRequest(validateCompanyExternalWebLink_Completed);
	}
	else
	{
		ShowToast("Enter the website URL.");
	}
}

function validateCompanyExternalWebLink_Completed(ResultObj)
{
	if (ResultObj.result == "success")
	{

		ExternalPageID = ResultObj.insertAddrId;
		
		var companyURLName = $("#text_company_url").val();
		
		ExternalPageData.compnayURLName = companyURLName;

		$("#external_message_img").removeClass();
		$("#external_message_img").addClass("external_success");

		$("#img_external_loader").hide();
		$("#external_message_text").html('Your website has been successfully linked to your "External Page" of Supply Medium');
		$(".external_message").show();

		$("#btn_external_retry").hide();
		
		sendtheExternalWebSiteInfo();

	}
	else if (ResultObj.result == "companyNameURLExists")
	{
		$("#external_message_img").removeClass();
		$("#external_message_img").addClass("external_failed");

		$("#img_external_loader").hide();
		$("#external_message_text").html('Company Name already exists. Please try with different company name');
		$(".external_message").show();

		$("#btn_external_retry").show();
	}
	else
	{
		$("#external_message_img").removeClass();
		$("#external_message_img").addClass("external_failed");

		$("#img_external_loader").hide();
		$("#external_message_text").html('An error occurred while linking to External Page. Please try again later');
		$(".external_message").show();

		$("#btn_external_retry").show();
	}
}

function Load_Content_WebSite()
{
	// Getting the Content fill contactus Data

	var SendingData =
	{
		'Action' : 'ExternalWebSite_Read',
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

	};

	doAjaxRequest("POST", "TemplateDataServlet", SendingData, fn_show_Load_Content_WebSite_completed, null);

}

var fn_show_Load_Content_WebSite_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		$("#txt_external_link").val(ResultObj.webURL);
	}

};

function sendtheExternalWebSiteInfo()
{
	
	var Data =
	{
		'Action' : 'ExternalWebSite_Save',
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

		"webURL" : $("#txt_external_link").val()
	};

	doAjaxRequest("POST", "TemplateDataServlet", Data, fn_save_content_ExternalWebSite_completed, null);
}

var fn_save_content_ExternalWebSite_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		GeneratePageforExternalWebSite();
	}
};

function GeneratePageforExternalWebSite()
{
	var SendingData =
	{
		'PageGenerateType' : "WebSite",
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

	};

	doAjaxRequest("POST", "PageGeneratorServlet", SendingData, fn_templatePreviewWebSite_completed, null);
}

var fn_templatePreviewWebSite_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		var win = window.open(getBaseURL() + '/' + ResultObj.CMPpath,'_blank');
		win.focus();
	}
};

function sendInserUpdateRequest(completedFunName)
{
	var linkURL = $("#txt_external_link").val();
	var companyURLName = $("#text_company_url").val();

	$("#img_external_loader").show();

	var sendingdata = "";

	if (ExternalPageID == 0)
	{
		sendingdata =
		{
			'Action' : 'InsertExternalPage',
			'cmpRegnKey' : regnKey,
			'externalPageURL' : linkURL,
			'compnayURLName' : companyURLName,
			'pageType' : $('input:radio[name=websel]:checked').val()
		};
	}
	else
	{
		sendingdata =
		{
			'Action' : 'UpdateExternalPage',
			'cmpRegnKey' : regnKey,
			'externalPageURL' : linkURL,
			'compnayURLName' : companyURLName,
			'pageType' : $('input:radio[name=websel]:checked').val()
		};
	}

	$.ajax(
	{
		type : 'POST',
		url : getBaseURL() + '/externalpageservlet',
		data : sendingdata,
		cache : false,
		success : function(ResultObj)
		{
			$("#img_external_loader").hide();
			completedFunName(ResultObj);
		},
		error : function()
		{
			$("#img_external_loader").hide();
			ShowToast("Unable to connect to server.");

		}
	});
}

function show_template()
{

	$("#web_page_structure").hide();
	$("#fill_content").hide();
	$("#template").show();

	$("#menu_webstructure").removeClass();
	$("#menu_webstructure").addClass("temp_menu_normal");
	$("#menu_fill_content").removeClass();
	$("#menu_fill_content").addClass("temp_menu_normal");
	$("#menu_template").removeClass();
	$("#menu_template").addClass("temp_menu_selected");
}

function doAjaxRequest(requestType, servletName, data, completeSuccessFun, ErrorFun)
{

	$.ajax(
	{
		type : requestType,
		url : getBaseURL() + '/' + servletName,
		data : data,
		cache : false,
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

function doAjaxRequestWithParameter(requestType, servletName, data, completeSuccessFun, ErrorFun, Params1)
{

	$.ajax(
	{
		type : requestType,
		url : getBaseURL() + '/' + servletName,
		data : data,
		cache : false,
		success : function(ResultObj)
		{
			if (completeSuccessFun != null)
				completeSuccessFun(ResultObj, Params1);
		},
		error : function()
		{
			if (ErrorFun != null)
				ErrorFun();
		}
	});
}

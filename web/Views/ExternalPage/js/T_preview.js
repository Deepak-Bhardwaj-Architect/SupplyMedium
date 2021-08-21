/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any. - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

var TMainType = "";
var TSubType = "";

$(function()
{

	/* Previous Button */
	$("#btn_tempreview_previous").click(show_content);

	$(".tempreview_list li").click(function()
	{
		$(".tempreview_list li").removeClass();
		$(this).addClass("selected");
		TMainType = $(this).attr("mainType");
		TSubType = $(this).attr("SubType");
	});

	$("#btn_tempreview_finish").click(generateTempPage);
	$("#btn_tempreview_preview").click(generateTempPage);

});

var fn_templatePreview_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		var win = window.open(getBaseURL() + '/' + ResultObj.CMPpath, '_blank');
		win.focus();
	}
};

function generateTempPage()
{
	if (TMainType == "" || TSubType == "")
	{
		ShowToast("Select Template Type");
	}
	else
	{
		var SendingData =
		{
			'PageGenerateType' : "Template",
			'templateMainType' : TMainType,
			'templateSubType' : TSubType,
			'cmpRegnKey' : ExternalPageData.cmpRegnKey,
			'externalPageID' : ExternalPageData.externalPageID,
			'compnayURLName' : ExternalPageData.compnayURLName,
			'pageType' : ExternalPageData.pageType,

		};

		doAjaxRequest("POST", "PageGeneratorServlet", SendingData, fn_templatePreview_completed, null);

	}
}

/**
 * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 * Copyright (c) 2006-2013 Tekton Technologies (P) Ltd. All Rights Reserved.
 * This product and related documentation is protected by copyright and
 * distributed under licenses restricting its use, copying, distribution and
 * decompilation. No part of this product or related documentation may be
 * reproduced in any form by any means without prior written authorization of
 * Tekton Technologies (P) Ltd. and its licensors, if any. - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
 */

$(function()
{

	/* Cancel Button */
	$("#btn_webstructure_cancel").click(function()
	{
		$("#webselection").show();
		$("#template_conatiner").hide();
	});

	/* Next Button */
	$("#btn_webstructure_next").click(
			function()
			{
				if ($("#menu_products").attr('checked') == 'checked' || $("#menu_solutions").attr('checked') == 'checked'
						|| $("#menu_service").attr('checked') == 'checked')
				{
					WebStructure_sendDatatoServer();
					show_content();
				}
				else
				{
					ShowToast("Choose at least any one from the Product or Soultions or Service");
				}
			});

});

function show_structure()
{
	$("#web_page_structure").show();
	$("#fill_content").hide();
	$("#template").hide();

	$("#menu_webstructure").removeClass();
	$("#menu_webstructure").addClass("temp_menu_selected");
	$("#menu_fill_content").removeClass();
	$("#menu_fill_content").addClass("temp_menu_normal");
	$("#menu_template").removeClass();
	$("#menu_template").addClass("temp_menu_normal");

}

function getAlltheDataforTemplate()
{
	show_structure();
	
	var SendingData =
	{
		'Action' : 'WebStructureData_Read',
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

	};

	doAjaxRequest("POST", "TemplateDataServlet", SendingData, fn_show_structure_completed, null);

	Load_Content_Home();
	Load_Content_Product();
	Load_Content_Services();
	Load_Content_solutions();
	Load_Content_aboutus();
	Load_Content_contactus();
}

function WebStructure_sendDatatoServer()
{
	var Data =
	{
		'Action' : 'WebStructureData_Save',
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

		"Home" : $("#menu_home").attr('checked') == 'checked' ? true : false,
		"Products" : $("#menu_products").attr('checked') == 'checked' ? true : false,
		"Solutions" : $("#menu_solutions").attr('checked') == 'checked' ? true : false,
		"Service" : $("#menu_service").attr('checked') == 'checked' ? true : false,
		"About_US" : $("#menu_about").attr('checked') == 'checked' ? true : false,
		"ContactUS" : $("#menu_contact").attr('checked') == 'checked' ? true : false
	};

	doAjaxRequest("POST", "TemplateDataServlet", Data, null, null);

}

var fn_show_structure_completed = function show_structure_completed(ResultObj)
{
	if (ResultObj.result == "success")
	{
		if (ResultObj.Products == true)
		{
			$("#menu_products").attr("checked", "checked");
		}

		if (ResultObj.Solutions == true)
		{
			$("#menu_solutions").attr("checked", "checked");
		}

		if (ResultObj.Service == true)
		{
			$("#menu_service").attr("checked", "checked");
		}
	}
};
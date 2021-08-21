/**
 * Load Content contactus
 */

function Load_Content_contactus()
{
	// Getting the Content fill contactus Data

	var SendingData =
	{
		'Action' : 'ContentContatctUs_Read',
		'cmpRegnKey' : ExternalPageData.cmpRegnKey,
		'externalPageID' : ExternalPageData.externalPageID,
		'compnayURLName' : ExternalPageData.compnayURLName,
		'pageType' : ExternalPageData.pageType,

	};

	doAjaxRequest("POST", "TemplateDataServlet", SendingData, fn_show_content_contactus_completed, null);

}

var fn_show_content_contactus_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		$("#contact_country_1").val(ResultObj.country_1);
		$("#contact_address_1").val(ResultObj.address_1);
		$("#contact_city_1").val(ResultObj.city_1);
		$("#contact_state_1").val(ResultObj.state_1);
		$("#contact_zipcode_1").val(ResultObj.zipcode_1);
		$("#contact_inquiry_1").val(ResultObj.inquiry_1);
		
		validate_content_contactus();
	}

};

// -------------------------------------------------------------------
// Save Service
// -------------------------------------------------------------------

function save_content_contactus()
{
	var result = validate_content_contactus();
	if (result == "TRUE")
	{
		var Data =
		{
			'Action' : 'ContentContatctUs_Save',
			'cmpRegnKey' : ExternalPageData.cmpRegnKey,
			'externalPageID' : ExternalPageData.externalPageID,
			'compnayURLName' : ExternalPageData.compnayURLName,
			'pageType' : ExternalPageData.pageType,

			"country_1" : $("#contact_country_1").val(),
			"address_1" : $("#contact_address_1").val(),
			"city_1" : $("#contact_city_1").val(),
			"state_1" : $("#contact_state_1").val(),
			"zipcode_1" : $("#contact_zipcode_1").val(),
			"inquiry_1" : $("#contact_inquiry_1").val()
		};

		doAjaxRequest("POST", "TemplateDataServlet", Data, fn_save_content_contactus_completed, null);
	}
	else
	{
		//ShowToast(result);
	}
}

function validate_content_contactus()
{
	isContactUsCompleted = false;
	
	if ($("#contact_country_1").val() == "")
	{
		return "Enter country name";
	}

	if ($("#contact_address_1").val() == "")
	{
		return "Enter address";
	}

	if ($("#contact_city_1").val() == "")
	{
		return "Enter city";
	}

	if ($("#contact_state_1").val() == "")
	{
		return "Enter state";
	}

	if ($("#contact_zipcode_1").val() == "")
	{
		return "Enter zipcode";
	}

	if ($("#contact_inquiry_1").val() == "")
	{
		return "Enter inquiry info";
	}
	
	isContactUsCompleted = true;

	return "TRUE";
}

var fn_save_content_contactus_completed = function(ResultObj)
{
	if (ResultObj.result == "success")
	{
		//ShowToast("Saved Successfully");
	}
};

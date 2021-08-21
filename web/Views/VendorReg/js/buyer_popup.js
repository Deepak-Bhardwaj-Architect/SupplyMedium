/*This JS decides what are the controls/buttons/fields
 * are editable to the buyer, if he clicks a request from
 * request queue and views the vendor regn form 
 */


/* Based on the current status, this method decides 
 * what are all the controls to be viewed/eited etc*/

function customizeBuyerPopUp( )
{
	var currentStatus = $('#regn_status').val();
	
	if( currentStatus == "New Request" )
	{
		buyerNewRequestCustomize( );
		return;
	}
	else if( currentStatus == "Form Sent" )
	{
		buyerFormSentCustomize( );
		return;
	}
	else if( currentStatus == "Inquire" )
	{
		buyerInquireCustomize( );
		return;
	}
	else if( currentStatus == "Inquire Answered" )
	{
		buyerInquireAnsdCustomize( );
		return;
	}
}

/*This method is used to customize the popup view to the 
 * buyer by disabling all the controls.  (Here, he can
 * simply view the popup
 */

function buyerNewRequestCustomize( )
{
	//$('#company_name')
	$("#company_name_popup").attr("disabled", "disabled");
	$("#branch_0_popup").attr("disabled", "disabled");
	$("#countryregion_0_popup").attr("disabled", "disabled");
	
	$("#address_popup").attr("disabled", "disabled");

	$('#city_popup').attr("disabled", "disabled");
	$('#state_0_popup').attr("disabled", "disabled");
	
	$('#zipcode_popup').attr("disabled", "disabled");
	
	//if( companytype == 0 )
	//{
		$('#internetuser_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		//$('#transuser').attr('checked', true);
		$('#transuser_popup').attr("disabled", "disabled");
	//}
	
	$('#contact_name_popup').attr("disabled", "disabled");
	
	$('#titledept_popup').attr("disabled", "disabled");
	
	$('#email_popup').attr("disabled", "disabled");
	
	$('#phone_popup').attr("disabled", "disabled");
	
	$('#cell_popup').attr("disabled", "disabled");
	
	$('#fax_popup').attr("disabled", "disabled");
	
	$('#taxid_popup').attr("disabled", "disabled");
	
	$('#typeofbusiness_popup').attr("disabled", "disabled");
	
	$('#businesscategory_popup').attr("disabled", "disabled");
	
	$('#naicscode_popup').attr("disabled", "disabled");
	
	//if( w9taxformflag == 0 )
	//{
		$('#w9_tax_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		//$('#w9_tax').attr('checked', true);
	//}
	
	//if( businesssize == 0 )
	//{
		$('#buss_large_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		$('#buss_small_popup').attr("disabled", "disabled");
	//}
	
	$('#additional_det_popup').attr("disabled", "disabled");
	
	$('#Disadvantaged_popup').attr("disabled", "disabled");
	
	$('#HubZone_popup').attr("disabled", "disabled");
	
	$('#WomenOwned_popup').attr("disabled", "disabled");
	
	$('#HandicappedOwned_popup').attr("disabled", "disabled");
	
	$('#VETERANOWNED_popup').attr("disabled", "disabled");
	
	$('#SDVETERANOWNED_popup').attr("disabled", "disabled");
	
	$('#HBCORMI_popup').attr("disabled", "disabled");
	
	$('#MBE_popup').attr("disabled", "disabled");
	
	$('#NonProfit_popup').attr("disabled", "disabled");
	
	$('#Foreign_popup').attr("disabled", "disabled");
	
	$('#PublicSector_popup').attr("disabled", "disabled");
	
	$('#ANCORITNSB_popup').attr("disabled", "disabled");
	
	$('#ANCORITNCD_popup').attr("disabled", "disabled");
	
/*	$('#accept_btn').attr("disabled", "disabled");
	
	$('#reject_btn').attr("disabled", "disabled");
	
	$('#cancel_btn').attr("disabled", "disabled"); */
	
	$('#accept_btn').hide();
	
	$('#reject_btn').hide();
	
	$('#inquire_btn').hide();

	$('.ven_reg_btns').empty();
	
	var okBtnDiv = '<input type="button" class="gen-btn-Orange" style="margin-left:83px;"  value="OK" id="ok_btn" onclick="closeForm()"/>';
	
	$('.ven_reg_btns').append(okBtnDiv);
	
}

function closeForm()
{
	//$('#ok_btn').hide();
	$('#buyer_form_popup').hide();	
}

/*This method is used to customize the popup view to the
 * buyer such that, buyer can make three actions
 * 1. Accept button click
 * 2. Inquire button click
 * 3. Reject button click*/

function buyerFormSentCustomize( )
{
	$("#company_name_popup").attr("disabled", "disabled");
	$("#branch_0_popup").attr("disabled", "disabled");
	$("#countryregion_0_popup").attr("disabled", "disabled");
	
	$("#address_popup").attr("disabled", "disabled");

	$('#city_popup').attr("disabled", "disabled");
	$('#state_0_popup').attr("disabled", "disabled");
	
	$('#zipcode_popup').attr("disabled", "disabled");
	
	//if( companytype == 0 )
	//{
		$('#internetuser_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		//$('#transuser').attr('checked', true);
		$('#transuser_popup').attr("disabled", "disabled");
	//}
	
	$('#contact_name_popup').attr("disabled", "disabled");
	
	$('#titledept_popup').attr("disabled", "disabled");
	
	$('#email_popup').attr("disabled", "disabled");
	
	$('#phone_popup').attr("disabled", "disabled");
	
	$('#cell_popup').attr("disabled", "disabled");
	
	$('#fax_popup').attr("disabled", "disabled");
	
	$('#taxid_popup').attr("disabled", "disabled");
	
	$('#typeofbusiness_popup').attr("disabled", "disabled");
	
	$('#businesscategory_popup').attr("disabled", "disabled");
	
	$('#naicscode_popup').attr("disabled", "disabled");
	
	//if( w9taxformflag == 0 )
	//{
		$('#w9_tax_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		//$('#w9_tax').attr('checked', true);
	//}
	
	//if( businesssize == 0 )
	//{
		$('#buss_large_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		$('#buss_small_popup').attr("disabled", "disabled");
	//}
	
	$('#additional_det_popup').attr("disabled", "disabled");
	
	$('#Disadvantaged_popup').attr("disabled", "disabled");
	
	$('#HubZone_popup').attr("disabled", "disabled");
	
	$('#WomenOwned_popup').attr("disabled", "disabled");
	
	$('#HandicappedOwned_popup').attr("disabled", "disabled");
	
	$('#VETERANOWNED_popup').attr("disabled", "disabled");
	
	$('#SDVETERANOWNED_popup').attr("disabled", "disabled");
	
	$('#HBCORMI_popup').attr("disabled", "disabled");
	
	$('#MBE_popup').attr("disabled", "disabled");
	
	$('#NonProfit_popup').attr("disabled", "disabled");
	
	$('#Foreign_popup').attr("disabled", "disabled");
	
	$('#PublicSector_popup').attr("disabled", "disabled");
	
	$('#ANCORITNSB_popup').attr("disabled", "disabled");
	
	$('#ANCORITNCD_popup').attr("disabled", "disabled");
	
/*	$('#accept_btn').attr("disabled", "disabled");
	
	$('#reject_btn').attr("disabled", "disabled");
	
	$('#cancel_btn').attr("disabled", "disabled"); */
	
}

/*This method is used to customize the popup view to the 
 * buyer by disabling all the controls. (Here, he can
 * simply view the popup*/

function buyerInquireCustomize( )
{
	$("#company_name_popup").attr("disabled", "disabled");
	$("#branch_0_popup").attr("disabled", "disabled");
	$("#countryregion_0_popup").attr("disabled", "disabled");
	
	$("#address_popup").attr("disabled", "disabled");

	$('#city_popup').attr("disabled", "disabled");
	$('#state_0_popup').attr("disabled", "disabled");
	
	$('#zipcode_popup').attr("disabled", "disabled");
	
	//if( companytype == 0 )
	//{
		$('#internetuser_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		//$('#transuser').attr('checked', true);
		$('#transuser_popup').attr("disabled", "disabled");
	//}
	
	$('#contact_name_popup').attr("disabled", "disabled");
	
	$('#titledept_popup').attr("disabled", "disabled");
	
	$('#email_popup').attr("disabled", "disabled");
	
	$('#phone_popup').attr("disabled", "disabled");
	
	$('#cell_popup').attr("disabled", "disabled");
	
	$('#fax_popup').attr("disabled", "disabled");
	
	$('#taxid_popup').attr("disabled", "disabled");
	
	$('#typeofbusiness_popup').attr("disabled", "disabled");
	
	$('#businesscategory_popup').attr("disabled", "disabled");
	
	$('#naicscode_popup').attr("disabled", "disabled");
	
	//if( w9taxformflag == 0 )
	//{
		$('#w9_tax_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		//$('#w9_tax').attr('checked', true);
	//}
	
	//if( businesssize == 0 )
	//{
		$('#buss_large_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		$('#buss_small_popup').attr("disabled", "disabled");
	//}
	
	$('#additional_det_popup').attr("disabled", "disabled");
	
	$('#Disadvantaged_popup').attr("disabled", "disabled");
	
	$('#HubZone_popup').attr("disabled", "disabled");
	
	$('#WomenOwned_popup').attr("disabled", "disabled");
	
	$('#HandicappedOwned_popup').attr("disabled", "disabled");
	
	$('#VETERANOWNED_popup').attr("disabled", "disabled");
	
	$('#SDVETERANOWNED_popup').attr("disabled", "disabled");
	
	$('#HBCORMI_popup').attr("disabled", "disabled");
	
	$('#MBE_popup').attr("disabled", "disabled");
	
	$('#NonProfit_popup').attr("disabled", "disabled");
	
	$('#Foreign_popup').attr("disabled", "disabled");
	
	$('#PublicSector_popup').attr("disabled", "disabled");
	
	$('#ANCORITNSB_popup').attr("disabled", "disabled");
	
	$('#ANCORITNCD_popup').attr("disabled", "disabled");
	
/*	$('#accept_btn').attr("disabled", "disabled");
	
	$('#reject_btn').attr("disabled", "disabled");
	
	$('#cancel_btn').attr("disabled", "disabled"); */
	
	$('#accept_btn').hide();
	
	$('#reject_btn').hide();
	
	$('#inquire_btn').hide();

	$('.ven_reg_btns').empty();
	
	var okBtnDiv = '<input type="button" class="gen-btn-Orange" style="margin-left:83px;"  value="OK" id="ok_btn" onclick="closeForm()"/>';
	
	$('.ven_reg_btns').append(okBtnDiv);
}

/*This method is used to customize the popup view to the
 * buyer such that, buyer can make three actions
 * 1. Accept button click
 * 2. Inquire button click
 * 3. Reject button click*/

function buyerInquireAnsdCustomize( )
{
	$("#company_name_popup").attr("disabled", "disabled");
	$("#branch_0_popup").attr("disabled", "disabled");
	$("#countryregion_0_popup").attr("disabled", "disabled");
	
	$("#address_popup").attr("disabled", "disabled");

	$('#city_popup').attr("disabled", "disabled");
	$('#state_0_popup').attr("disabled", "disabled");
	
	$('#zipcode_popup').attr("disabled", "disabled");
	
	//if( companytype == 0 )
	//{
		$('#internetuser_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		//$('#transuser').attr('checked', true);
		$('#transuser_popup').attr("disabled", "disabled");
	//}
	
	$('#contact_name_popup').attr("disabled", "disabled");
	
	$('#titledept_popup').attr("disabled", "disabled");
	
	$('#email_popup').attr("disabled", "disabled");
	
	$('#phone_popup').attr("disabled", "disabled");
	
	$('#cell_popup').attr("disabled", "disabled");
	
	$('#fax_popup').attr("disabled", "disabled");
	
	$('#taxid_popup').attr("disabled", "disabled");
	
	$('#typeofbusiness_popup').attr("disabled", "disabled");
	
	$('#businesscategory_popup').attr("disabled", "disabled");
	
	$('#naicscode_popup').attr("disabled", "disabled");
	
	//if( w9taxformflag == 0 )
	//{
		$('#w9_tax_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		//$('#w9_tax').attr('checked', true);
	//}
	
	//if( businesssize == 0 )
	//{
		$('#buss_large_popup').attr("disabled", "disabled");
	//}
	//else
	//{
		$('#buss_small_popup').attr("disabled", "disabled");
	//}
	
	$('#additional_det_popup').attr("disabled", "disabled");
	
	$('#Disadvantaged_popup').attr("disabled", "disabled");
	
	$('#HubZone_popup').attr("disabled", "disabled");
	
	$('#WomenOwned_popup').attr("disabled", "disabled");
	
	$('#HandicappedOwned_popup').attr("disabled", "disabled");
	
	$('#VETERANOWNED_popup').attr("disabled", "disabled");
	
	$('#SDVETERANOWNED_popup').attr("disabled", "disabled");
	
	$('#HBCORMI_popup').attr("disabled", "disabled");
	
	$('#MBE_popup').attr("disabled", "disabled");
	
	$('#NonProfit_popup').attr("disabled", "disabled");
	
	$('#Foreign_popup').attr("disabled", "disabled");
	
	$('#PublicSector_popup').attr("disabled", "disabled");
	
	$('#ANCORITNSB_popup').attr("disabled", "disabled");
	
	$('#ANCORITNCD_popup').attr("disabled", "disabled");
	
/*	$('#accept_btn').attr("disabled", "disabled");
	
	$('#reject_btn').attr("disabled", "disabled");
	
	$('#cancel_btn').attr("disabled", "disabled"); */
}


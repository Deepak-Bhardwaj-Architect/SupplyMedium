/*This JS decides what are the controls/buttons/fields
 * are editable to the viewer, if he clicks a request from
 * request queue and views the vendor regn form 
 */


/* Based on the current status, this method decides 
 * what are all the controls to be viewed/eited etc*/

function customizeSupplierPopUp( )
{
	var currentStatus = $('#regn_status').val();
	
	//alert( currentStatus );
	
	if( currentStatus == "New Request" )
	{
		supplierRequestCustomize( );
		return;
	}
	else if( currentStatus == "Form Sent" )
	{
		supplierFormSentCustomize( );
		return;
	}
	else if( currentStatus == "Inquire" )
	{
		supplierInquireCustomize( );
		return;
	}
	else if( currentStatus == "Inquiry Answered" )
	{
		supplierInquireAnsdCustomize( );
		return;
	}
}

/*This method is used to customize the popup view to the
 * supplier such that, supplier can make three actions
 * 1. Cancel button click
 * 2. Reject button click
 * 3. Send button click
 */


function supplierRequestCustomize( )
{
	//alert("This one is called");
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
	
	//$('#taxid').attr("disabled", "disabled");
	
	$('#typeofbusiness_popup').attr("disabled", "disabled");
	
	$('#businesscategory_popup').attr("disabled", "disabled");
	
	$("#naicscode_popup_text").hide();
	
	$("#naicscode_popup_select_div").show();
	
	
	
	//$('#naicscode').attr("disabled", "disabled");
	
	//$('#w9_tax').attr("disabled", "disabled");

	//$('#buss_large').attr("disabled", "disabled");

	//$('#buss_small').attr("disabled", "disabled");
	
	//$('#additional_det').attr("disabled", "disabled");
	
	//$('#Disadvantaged').attr("disabled", "disabled");
	
	//$('#HubZone').attr("disabled", "disabled");
	
	//$('#WomenOwned').attr("disabled", "disabled");
	
	//$('#HandicappedOwned').attr("disabled", "disabled");
	
	//$('#VETERANOWNED').attr("disabled", "disabled");
	
	//$('#SDVETERANOWNED').attr("disabled", "disabled");
	
	//$('#HBCORMI').attr("disabled", "disabled");
	
	//$('#MBE').attr("disabled", "disabled");
	
	//$('#NonProfit').attr("disabled", "disabled");
	
	//$('#Foreign').attr("disabled", "disabled");
	
	//$('#PublicSector').attr("disabled", "disabled");
	
	//$('#ANCORITNSB').attr("disabled", "disabled");
	
	//$('#ANCORITNCD').attr("disabled", "disabled");
	
/*	$('.ven_reg_btns').empty();
		
	var btnDiv = '<input type="button" class="gen-btn-Orange" style="margin-right:65px;"  value="Cancel" id="cancel_btn" onclick="closeForm()"/>';
	btnDiv = btnDiv + '<input type="button" class="gen-btn-Orange" style="margin-right:65px;"  value="Reject" id="reject_btn" onclick="rejectBtnClicked()"/>';
	btnDiv = btnDiv + '<input type="button" class="gen-btn-Orange" style="margin-right:65px;"  value="Send" id="send_btn" onclick="updateVenRegForm()"/>';
	
	$('.ven_reg_btns').append(btnDiv);*/
	
	$('#inquire_regn_btn').hide();
}

function closeForm()
{
	$('#supplier_form_popup').hide();	
}

/*This method is used to customize the popup view to the
 * supplier such that, supplier can view the form
 */

function supplierFormSentCustomize( )
{
	$("#company_name_popup").attr("disabled", "disabled");
	
	$("#branch_0_popup").attr("disabled", "disabled");
	
	$("#countryregion_0_popup").attr("disabled", "disabled");
	
	$("#address_popup").attr("disabled", "disabled");

	$('#city_popup').attr("disabled", "disabled");
	
	$('#state_0_popup').attr("disabled", "disabled");
	
	$('#zipcode_popup').attr("disabled", "disabled");
	
	$('#internetuser_popup').attr("disabled", "disabled");

	$('#transuser_popup').attr("disabled", "disabled");
	
	$('#contact_name_popup').attr("disabled", "disabled");
	
	$('#titledept_popup').attr("disabled", "disabled");
	
	$('#email_popup').attr("disabled", "disabled");
	
	$('#phone_popup').attr("disabled", "disabled");
	
	$('#cell_popup').attr("disabled", "disabled");
	
	$('#fax_popup').attr("disabled", "disabled");
	
	$('#taxid_popup').attr("disabled", "disabled");
	
	$('#typeofbusiness_popup').attr("disabled", "disabled");
	
	$('#businesscategory_popup').attr("disabled", "disabled");
	
	$('#naicscode_popup_text').attr("disabled", "disabled");
	
	$('#w9_tax_popup').attr("disabled", "disabled");  //W9 form starts here

	$('#buss_large_popup').attr("disabled", "disabled");

	$('#buss_small_popup').attr("disabled", "disabled");
	
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
	
	$("#naicscode_popup_text").show();
	
	$("#naicscode_popup_select_div").css("display","none");
	
	
	
/*	$('#accept_btn').attr("disabled", "disabled");
	
	$('#reject_btn').attr("disabled", "disabled");
	
	$('#cancel_btn').attr("disabled", "disabled"); */
	
	
	$('.ven_reg_btns').empty();
	
	var btnDiv = '<input type="button" class="gen-btn-Orange" style="margin-left:83px;"  value="Ok" id="ok_btn_popup" onclick="closeForm()"/>';
	
	$('.ven_reg_btns').append(btnDiv);
}

/*This method is used to customize the popup view to the 
 * buyer by disabling all the controls. (Here, he can
 * simply view the popup*/

function supplierInquireCustomize( )
{
	$("#company_name_popup").attr("disabled", "disabled");
	
	$("#branch_0_popup").attr("disabled", "disabled");
	
	$("#countryregion_0_popup").attr("disabled", "disabled");
	
	$("#address_popup").attr("disabled", "disabled");

	$('#city_popup').attr("disabled", "disabled");
	
	$('#state_0_popup').attr("disabled", "disabled");
	
	$('#zipcode_popup').attr("disabled", "disabled");
	
	$('#internetuser_popup').attr("disabled", "disabled");

	$('#transuser_popup').attr("disabled", "disabled");
	
	$('#contact_name_popup').attr("disabled", "disabled");
	
	$('#titledept_popup').attr("disabled", "disabled");
	
	$('#email_popup').attr("disabled", "disabled");
	
	$('#phone_popup').attr("disabled", "disabled");
	
	$('#cell_popup').attr("disabled", "disabled");
	
	$('#fax_popup').attr("disabled", "disabled");
	
	$('#taxid_popup').attr("disabled", "disabled");
	
	$('#typeofbusiness_popup').attr("disabled", "disabled");
	
	$('#businesscategory_popup').attr("disabled", "disabled");
	
	$('#naicscode_popup_text').attr("disabled", "disabled");
	
	$('#w9_tax_popup').attr("disabled", "disabled");  //W9 form starts here

	$('#buss_large_popup').attr("disabled", "disabled");

	$('#buss_small_popup').attr("disabled", "disabled");
	
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
	
	$("#naicscode_popup_text").show();
	
	$("#naicscode_popup_select_div").hide();
	
	
/*	$('#accept_btn').attr("disabled", "disabled");
	
	$('#reject_btn').attr("disabled", "disabled");
	
	$('#cancel_btn').attr("disabled", "disabled"); */
	
	$('#reject_regn_btn').hide();
	
	$('#send_regn_btn').hide();
	
	$('#inquire_regn_btn').show();
}


/*This method is used to customize the popup view to the
 * supplier such that, supplier can view the form
 */

function supplierInquireAnsdCustomize( )
{
	$("#company_name_popup").attr("disabled", "disabled");
	
	$("#branch_0_popup").attr("disabled", "disabled");
	
	$("#countryregion_0_popup").attr("disabled", "disabled");
	
	$("#address_popup").attr("disabled", "disabled");

	$('#city_popup').attr("disabled", "disabled");
	
	$('#state_0_popup').attr("disabled", "disabled");
	
	$('#zipcode_popup').attr("disabled", "disabled");
	
	$('#internetuser_popup').attr("disabled", "disabled");

	$('#transuser_popup').attr("disabled", "disabled");
	
	$('#contact_name_popup').attr("disabled", "disabled");
	
	$('#titledept_popup').attr("disabled", "disabled");
	
	$('#email_popup').attr("disabled", "disabled");
	
	$('#phone_popup').attr("disabled", "disabled");
	
	$('#cell_popup').attr("disabled", "disabled");
	
	$('#fax_popup').attr("disabled", "disabled");
	
	$('#taxid_popup').attr("disabled", "disabled");
	
	$('#typeofbusiness_popup').attr("disabled", "disabled");
	
	$('#businesscategory_popup').attr("disabled", "disabled");
	
	$('#naicscode_popup_text').attr("disabled", "disabled");
	
	$('#w9_tax_popup').attr("disabled", "disabled");  //W9 form starts here

	$('#buss_large_popup').attr("disabled", "disabled");

	$('#buss_small_popup').attr("disabled", "disabled");
	
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
	
	$("#naicscode_popup_text").show();
	
	$("#naicscode_popup_select_div").hide();
	
	
/*	$('#accept_btn').attr("disabled", "disabled");
	
	$('#reject_btn').attr("disabled", "disabled");
	
	$('#cancel_btn').attr("disabled", "disabled"); */
	
	
	$('.ven_reg_btns').empty();
	
	var btnDiv = '<input type="button" class="gen-btn-Orange" style="margin-right:65px;"  value="Ok" id="ok_btn_popup" onclick="closeForm()"/>';
	
	$('.ven_reg_btns').append(btnDiv);
}
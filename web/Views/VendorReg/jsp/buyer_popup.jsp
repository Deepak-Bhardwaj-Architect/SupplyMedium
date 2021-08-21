<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

	<script>
		$(".Gen_Cus_Popup_Close").click(function() {
			$("#buyer_form_popup").hide();
		});
	</script>
	<div style="display: none; z-index: 5000;" class="Custome_Popup_Window"
		id="buyer_form_popup">
		<div class="Cus_Popup_Outline"
			style="position: fixed;  top: 60px; left: 133px; margin:auto;width: 1042px!important;height:500px!important;/*width: 1010px; height: 720px;margin-top:-360px;margin-left:-505px;*/border-radius:0px;">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Filled
					Vendor registration Form</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>

			<div id="buyer_form_popup_content" style="overflow:auto;height:435px;float:left; position:relative;margin-top:10px;">
			
			
			<div class="ven_reg_left">
						<input type="hidden" id="vendor_regn_id" />
						<input type="hidden" id="regn_id" />
						<input type="hidden" id="regn_status" />
						<input type="hidden" id="vendor_regnrec_id" />
						
						<div class="row">
							<label class="label"> Company Name </label>
							<input type="text" id="company_name_popup" name="company_name" class="textbox_disable" disabled/>
						</div>
						
						
						<div class="row">
							<label class="label"> Branch </label>
							<input type="text" id="branch_0_popup" name="branch_0" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> Country/Region </label>
							<input type="text" id="countryregion_0_popup" name="countryregion_0" class="textbox_disable" disabled/>
							
						</div>
						
						<div class="row">
							<label class="label"> Address </label>
							<input type="text" id="address_popup" name="address" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> City </label>
							<input type="text" id="city" name="city_popup" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> State/Province </label>
							<!-- <select class="selectbox" name="state_0" id="state_0" style="width:165px;">
							<option> Select State </option>
							</select> -->
							
							<input type="text" id="state_0_popup" name="state_0" class="textbox_disable" disabled/>
							
						</div>
						
						
						<div class="row">
							<label class="label"> Zip Code/Postal Code </label>
							<input type="text" id="zipcode_popup" name="zipcode" class="textbox_disable" disabled/>
						</div>
						
						
					
					<div style="height: 100px;" class="row" id="type">
					
						<div class="label">
							 Type<span class="mandatory"></span>
						</div>
						<fieldset id="companylevel">
							<legend> Company Level </legend>
							<input type="radio" value="Individual" class="radiobtn required" id="internetuser_popup" name="comtype" style="vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;"> 
							<label style="vertical-align: middle; font-size: 12px; color: #282828; line-height:25px;" for="internetuser">
								Individual/Sole Proprietor &nbsp;</label><br> 
							<input type="radio" value="Corporation" style="vertical-align: middle; font-size: 12px;margin:0px;line-height:25px;" class="radiobtn" name="comtype" id="transuser_popup"> 
							<label style="vertical-align: middle; font-size: 12px; color: #282828;line-height:25px;" for="transuser">
								Corporation, Partnerships, other&nbsp;</label> 
							<label style="margin-left: 0px !important; width: 150px;" class="error" generated="true" for="usertype"></label>

						</fieldset>

					</div>
					
					</div>
					
					<div class="ven_reg_right">
					
						<div class="row">
							<label class="label"> Business Contact Name </label>
							<input type="text" id="contact_name_popup" name="cname" class="textbox_disable" disabled/>
						</div>
						
						
						<div class="row">
							<label class="label"> Contact Title/Department </label>
							<input type="text" id="titledept_popup" name="titledept" class="textbox_disable" disabled/>
						</div>
						
						
						<div class="row">
							<label class="label"> Contact Email </label>
							<input type="text" id="email_popup" name="email" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> Phone </label>
							<input type="text" id="phone_popup" name="phone" class="textbox_disable" disabled/>
						</div>
						
						
						<div class="row">
							<label class="label"> Cell </label>
							<input type="text" id="cell_popup" name="cell" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> Fax </label>
							<input type="text" id="fax_popup" name="fax" class="textbox_disable" disabled/>
						</div>
						
						<div class="row">
							<label class="label"> Type of Business </label>
							<!-- <select class="selectbox" name="typeofbusiness" id="typeofbusiness" style="width:165px;">
							<option> Select Business Type </option>
							</select> -->
							
							<input type="text" id="typeofbusiness_popup" name="typeofbusiness" class="textbox_disable" disabled/>
							
						</div>
						
						<div class="row">
							<label class="label"> Business Category </label>
							<!-- <select class="selectbox" name="businesscategory" id="businesscategory" style="width:165px;">
							<option> Select Business Category </option>
							</select> -->
							
							<input type="text" id="businesscategory_popup" name="businesscategory" class="textbox_disable" disabled/>
							
						</div>
						
						<div class="row">
							<label class="label"> Business Tax ID </label>
							<input type="text" id="taxid_popup" name="taxid" class="textbox" />
						</div>
						
						
						<div class="row">
							<label class="label"> NAICS Code </label>
							<!-- <select class="selectbox" name="naicscode" id="naicscode" style="width:165px;">
							<option> Select NAICS Code </option>
							</select> -->
							
							<input type="text" id="naicscode_popup" name="naicscode" class="textbox" />
							
						</div>
						
						<div id="w9form_upload_ctrl">
						<div class="row">
							<label class="label">  </label>
							<div class="checkContainer" style="margin-right:5px;">
								<input type="checkbox" name="w9form_flag_popup" id="w9form_flag_popup" onchange="w9checkBoxPopupClicked()"/>
							<div></div>
							</div>
							<label for="w9form_flag_popup" style="width:300px !important;line-height:12px;font-family:Verdana;font-size:10px;"> W9 tax form will be submitted<br> to complete the registration process
							</label>
							
						</div>
					
						</div>
						
						<div id="w9form_download_ctrl">
						<div class="row">
							
						<a onclick="downloadW9form()" id="w9form_link" style="font-weight:underline;color:blue;"> Click here to download the W9Form</a>
								
						</div>
						</div>
						
					
					</div>
					
					<div class="buss_size_container">
					
						<div class="side_heading"> Business Size ( Select One )
						</div>
						
						<div class="buss_size_large">
						
							<div class="checkContainer">
								<input type="radio" name="buss_size" id="buss_large_popup" value="Large"/>
								<div></div>
							</div>
							
						
							
							<label class="des_text"><b>Large:<br></b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;A domestic concren which, including domestic and foreign divisions and affiliates, normally 
							employs 500 or more persons, is independently -or- publicly-owned or controlled and operated, 
							and which may be division of another domestic or foreign concern.</label>
						</div>
						
						<div class="buss_size_small">
						
							<div class="checkContainer">
								<input type="radio" name="buss_size" id="buss_small_popup" value="Small"/>
								<div></div>
							</div>
							
							
							
							<label class="des_text"><b>Small:<br></b> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"Small business cocren" means a concren, including its affiliates, that is independently 
							owned and operated, not dominant in the field of operation in which it is bidding on government
							contracts, and qualified as a small business under the criteria in 13 CFR Part 121 and the
							size standard in FAR Clause 52.219-1, as well as the Small Business Act, Section 3.</label>
						</div>
						
					</div>
					
					<div class="buss_clari_container">
					
						<div class="side_heading"> Business Classification per the Federal Acquisition Regulations. Section 2.101,
						 where applicable: (Select all that apply)
						</div>
						
						<div class="checkContainer">
							<input type="checkbox" name="Disadvantaged" id="Disadvantaged_popup" />
						<div></div>
						</div>
						
						<label class="des_text"><b>Disadvantaged:</b> In addition to meeting the requirements of the FAR definition, 
						any business classfying them selves as Disadvantaged must be certified by the Small Business 
						Administration and have that certification in good standing.
						</label>
						
						<div class="checkContainer" >
							<input type="checkbox" name="HubZone" id="HubZone_popup" />
							<div></div>
						</div>
						
						<label class="des_text"><b>HUBZone:</b> In addition to meeting the requirements of the FAR definition, any business
						 	classifying themselves as a HUBZ one must be certified by the small Business Administraiotn and
						 	have that certification in good standing.
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="WomenOwned" id="WomenOwned_popup" />
						<div></div>
						</div>
						
						<label class="des_text">Women-Owned
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="HandicappedOwned" id="HandicappedOwned_popup" />
							<div></div>
						</div>
						<label class="des_text">handicapped-Owned
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="VETERANOWNED" id="VETERANOWNED_popup" />
							<div></div>
						</div>
						<label class="des_text">Veteran-Owned
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="SDVETERANOWNED" id="SDVETERANOWNED_popup" />
							<div></div>
						</div>
						<label class="des_text">Service-Disabled Veteran-Owned
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="HBCORMI" id="HBCORMI_popup" />
							<div></div>
						</div>
						<label class="des_text">Historically-Back College or Minority Institution
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="MBE" id="MBE_popup" />
							<div></div>
						</div>
						<label class="des_text">Minority Business Enterprise
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="NonProfit" id="NonProfit_popup" />
							<div></div>
						</div>
						<label class="des_text"><b>Non-Profit:</b> Any business or organization that has received non-profit status
						under IRS Regulation 501(c)(3).
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="Foreign" id="Foreign_popup" />
							<div></div>
						</div>
						<label class="des_text"><b>Foreign:</b> A concern which is not incorporated in the United States or an 
						unincorporated concern having its principle place of business outside the United States.
						</label>
						<div class="checkContainer">
							<input type="checkbox" name="PublicSector" id="PublicSector_popup" />
							<div></div>
						</div><!-- <input type="checkbox" name="buss_small" id="buss_small" /> -->
						<label class="des_text"><b>Public Sector:</b> An agency of the Federal or State Government, or a local municipality.
						</label>
						
						<div class="checkContainer">
							<input type="checkbox" name="ANCORITNSB" id="ANCORITNSB_popup" />
							<div></div>
						</div>
						<label class="des_text">Alaska Native Corporations Indian Tripe that is not a small business.
						</label>
						
						
						<div class="checkContainer">
							<input type="checkbox" name="ANCORITNCD" id="ANCORITNCD_popup" />
							<div></div>
						</div>
						<label class="des_text"><b>Alaska Native Corporations or Indian Tribe:</b> Not certified by the Small Business Administration as disadvantaged.
						</label>
						
						
					</div>
					
					<div class="addl_det_container">
					
						<div class="side_heading"> Additional Details
						</div>
						<textarea class="addr_det_text" id="additional_det_popup"></textarea>
					</div>
					
					<div class="addl_det_container" id="inquiry_details"style="display:none;">
					
						
					</div>
					
										
					<div class="addl_det_container" style="display:none;" id="add_inquiry_content">
					
						<div class="inquire_row" style="margin-bottom:20px;">
							<label class="inquire_by"> Inquire: </label>
							<textarea class="inquire_det" id="new_inquire_message">  </textarea>
							
						</div>
						<input type="button" class="gen-btn-Orange"  style=" margin-left:300px;" value="Cancel" id="cancel_btn"/>
						<input type="button" class="gen-btn-Orange" style="margin-left:30px;" value="Send" id="save_btn"/>
						
					</div>
					
					<div class="ven_reg_btns" id="cntrl_btns">
						<input type="button" class="gen-btn-Orange" style="margin-right:50px;"  value="Accept" id="accept_btn"/>
						<input type="button" class="gen-btn-Orange " style="margin-right:50px;"  value="Reject" id="reject_btn"/>
						<input type="button" class="gen-btn-Orange" style="margin-right:50px;width:auto!important;padding-left:10px!important;padding-right:10px!important;"  value="Respond to Inquiry" id="inquire_btn"/>
					</div>
                                        <div class="ven_reg_btns" id="cntrl_btns_ok">
                                            <input type="button" class="gen-btn-Orange" style="margin-right:50px;"  value="ok" onclick="$('#buyer_form_popup').hide();"/>						
					</div>
					
					<form name="w9form_download_frm" id="w9form_download_frm" action="${pageContext.request.contextPath}/W9FormDownloadServlet" method="post" enctype="multipart/form-data">
								<input type="hidden" name="w9FormPath" id="w9FormPath"/>
					</form>
			</div>
		</div>
	</div>
	
	<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/VendorReg/js/buyer_popup.js"></script>
	
</body>
</html>
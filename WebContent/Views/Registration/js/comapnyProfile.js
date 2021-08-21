/*
 *  Purpose Registering the Events
 */

var addressId_;
var btnObj_;


$(function() {
	
	$("#content_loader").hide();
	
	/* Address Expander */
	$("#addressExpander").click(function(){
		AddressExpander(this);
	});	
	AddressExpander();
	
	if($("#companytype").val()=="Supplier")
	{
		$("#Supplierradio").prop('checked',true);
		
		$("#sign_up").show();

	}
	
	else if($("#companytype").val()=="Both")
	{
		$("#Bothradio").prop('checked',true);
		$("#zipcode").val(zipcode);
	}
	
	
	/* Popup Add Address OK */
	
	$("#Popup_Address_OK").click(addCompanyAddress);
	
	
	/* Add Address Button */
	$("#addAddress").click(AddMailButtonClick);
	
	$("#addAddressEmpty").click(AddMailButtonClick);
	
	
	/* Profile Update OK */
	$("#Profile_Update_OK").click(updateCompanyProfile);
	
		
	/* Profile Cancel */
	$("#Profile_Update_Cancel").click(function(){
		
		location.reload();

	});
	
	
});
	


/*
 * Register the Add Mail Function * 
 */

function AddMailButtonClick()
{
	$("#Popup_Address").show();
	
	$("#branch_pop").val("");
	$("#countryregion_pop").val("");
	$("#state_pop").val("");
	
	document.getElementById('branch_pop').selectedIndex = 0;
	document.getElementById('countryregion_pop').selectedIndex = 0;
	document.getElementById('state_pop').selectedIndex = 0;
	
	$("#branch_pop").trigger("update");
	$("#countryregion_pop").trigger("update");
	$("#state_pop").trigger("update");
	
	$('#AddAddress_pop').val("");
	$('#city_pop').val("");
	$('#zipcode_pop').val("");
	
	//formValidator.resetForm();
}

/*
 * Address Expander Click Function
 */

var addExpander=true;
function AddressExpander(thisobj)
{	
	
	
		if(addExpander)
		{
			addExpander=false;
			
			$(".removeaddress").hide();
			
						
			//$(".addrMoreInfo").show();
			$("#AddressList .AddressContiner").hide();
			$("#AddressList .AddressContiner:first-child").show();
			$("#AddressList .AddressContiner:first-child .AddressDetails").css("height","15px");
			
			$(thisobj).css("background-position","0px 0px");
		}
		else
		{
			addExpander=true;
			$(".removeaddress").show();

			//$(".addrMoreInfo").hide();
			$("#AddressList .AddressContiner").show();			
			$("#AddressList .AddressContiner:first-child .AddressDetails").css("height","auto");

			$(thisobj).css("background-position","0px -29px");
		}
}

/*
 * 
 * Add The Address to this Company
 * 
 */

function addCompanyAddress() {
	var isSuccess=false;
	var valid=$("#Popup_Address").valid();
	
	if(!valid)
	{
		
		return;
	}
	
	var state="";
	
	var countryName=$("#countryregion_pop  option:selected").val();
	
	if (countryName=="United States")
	{
		
		state=$("#state_pop  option:selected").val()=="--Select State--"?"":$("#state_pop  option:selected").val();
	}
	else
	{	
		
		state=$("#state_text").val();
	}
	

	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/CompanyAddressServlet",
		data : {
			
			'operation':'AddMail',			
			'branch' : $("#branch_pop  option:selected").val(),
			'country' : $("#countryregion_pop  option:selected").val(),
			'address' : $("#AddAddress_pop").val().trim(),
			'city' : $("#city_pop").val(),
			'state' : state,
			'zipcode' : $("#zipcode_pop").val(),
			
		},
		dataType : 'json',
		cache : false,
		success : function(result) {

			$.each(result, function(key, value) {
				
				if(key=="result")
				{
					    if(value=="success")
						{
					    	isSuccess=true;
						}
				}
				
				if(key=="insertAddrId")
				{
					if(isSuccess)
					{ 
						
						var country = $( "#countryregion_pop  option:selected" ).val();
						var addr    =$( "#AddAddress_pop" ).val();
						var city = $( "#city_pop" ).val();
						var state = $( "#state_pop  option:selected" ).val();
						var zipcode = $( "#zipcode_pop" ).val();
						
						
						var fullAddress = country;
						
						if(addr !='')
						{
							fullAddress = fullAddress+","+addr;
						}
						if(city !='')
						{
							fullAddress = fullAddress+","+city;
						}
						if(state !='')
						{
							fullAddress = fullAddress+","+state;
						}
						if(zipcode !='')
						{
							fullAddress = fullAddress+","+zipcode;
						}
						
						var AddressElement='<div class="AddressContiner">'+									
						'<fieldset>'+
						 '<legend> '+$("#branch_pop  option:selected").val()+'</legend>'+
						 '<div class="AddressDetails">'+fullAddress+
						'</div>'+
						'</fieldset>'+
						'<input type="button" name="removeaddressbtn_'+value+'" class="removeaddress" style="display: inline-block;" onclick="removeCompanyAddress(this);">'+				
					'</div>';

						$("#AddressList").append(AddressElement);
						
						if ($('#addAddressEmpty').length)
						{
							$("#addAddressEmpty").remove();
							AddressExpander($("#addressExpander"));
						}
						
						$("#Popup_Address").hide();
						ShowToast("Mail Address Added Successfully");
						
					}
				}
				
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			ShowToast("Request failed. Try again.");
		}
	});
}


/*
 * 
 * Remove the Address from the Company
 * 
 */

function removeCompanyAddress(btnObj)
{
	
	var name = btnObj.name;
	
	var addressId = name.split('_');
	
	addressId=addressId[1];
	if(addressId<0)
		return; 
	
	addressId_ = addressId;
	btnObj_	= btnObj;
	
	showWarning("This operation will remove mail Address from the Company Profile,  " +
			"Do you want to continue?");

}

/* Removing the Mail Address from the Company */
function deleteConfirm()
{

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/CompanyAddressServlet",
		data : {
			'operation':'RemoveMail',
			'addressId' : addressId_,			
		},
		dataType : 'json',
		cache : false,
		success : function(result) {

			$.each(result, function(key, value) {


				if(key="result")
				{
						if(value="success")
						{
							if($("#AddressList").children().length==1)
							{
								$("#addressExpandContainer").html('<div id="addressExpander"></div><input style="display: block;position: absolute;height: 30px;" type="button" id="addAddressEmpty" class="addAddressEmpty">');
								$("#addAddressEmpty").click(AddMailButtonClick);
								AddressExpander($("#addressExpander"));
								
							}
							$(btnObj_).parent().remove();
							
							ShowToast("Mail Address Removed Successfully");
						}
						
						$("#Popup_Warning").hide();
				}
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			ShowToast("Request failed. Try again.");
		}
	});
}


/*
 * 
 * Update the Company Profile
 * 
 */

function updateCompanyProfile() {
	var signupas=$('input[name=signupas]:radio:checked').val();
	
	var state="";
	var countryName=$("#countryregion_0  option:selected").val();
	
	if (countryName=="United States")
	{
		
		state=$("#state_0  option:selected").val();
	}
	else
	{	
		
		state=$("#state_text_0").val();
	}
	
	
	var valid=$("#companyProfile").valid();

	if(!valid)
	{
		//ShowToast("Fill All the Required Information");		
		return;
	}
	//var address_id = $("#city_0").attr("name").split('_');
	//alert("address_id="+address_id);
	//address_id=address_id[1];
	
	
	address_id=$("#firstAddrId").val();
	
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/ProfileUpdateServlet",
		data : {
			
			'companyname' : $("#companyname").val(),
			'signupas':signupas,			
			'businesscategory' : $("#businesscategory  option:selected").val(),
			'companyid' : $("#companyid").val(),
			'plan' : $('input:radio[name=plan]:checked').val(),
			'themeSelect' : $("#themeSelect  option:selected").val(),
			'country' : $("#countryregion_0  option:selected").val(),
			'branch' : $("#branch_0  option:selected").val(),
			'address' : $("#address_0").val(),
			'city' : $("#city_0").val(),
			'state' : state,
			'zipcode' : $("#zipcode_0").val(),
			'address_id':address_id
			
		},
		dataType : 'json',
		cache : false,
		success : function(result) {

			$.each(result, function(key, value) {

				if(key=="result")
				{
					    if(value=="success")
						{
					    	$("#zipcode").val(zipcode);
					    	
					    	
					    	if(signupas=="Both")
					    	{
					    		$("#sign_up").hide();				    		
					    		$("#companytype").val( "Both" );
					    		$("#zipcode").val(zipcode);
					    	}
					    	ShowToast("Updated Successfully");		
						}
				}
				
				
			});
		},
		error : function(xhr, textStatus, errorThrown) {
			ShowToast("Request failed. Try again.");	
		}
	});
	
	}

function changeDropDown(countryName)
{
	
	if (countryName=="United States")
	{
		
		$("#select_0_container").show();
		$("#state_text_0").hide();
	}
	else
	{	
		
		$("#state_text_0").show();
		$("#select_0_container").hide();
	}

}

function dropDown(countryName)
{
	if (countryName=="United States")
	{
		$("#select_1_container").show();
		$("#state_text").hide();
	}
	else
	{	
		$("#state_text").show();
		$("#select_1_container").hide();
	}
}



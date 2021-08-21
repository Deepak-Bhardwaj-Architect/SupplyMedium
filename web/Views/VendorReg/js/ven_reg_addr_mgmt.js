/*
 *  Purpose Registering the Events
 */

var addressId_;
var btnObj_;

$(function(){
	
	/* Address Expander */
	$("#addressExpander").click(function(){
		AddressExpander(this);
	});	
	AddressExpander();
	

	
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
	
	document.getElementById('branch_pop').selectedIndex = 0;
	document.getElementById('countryregion_pop').selectedIndex = 0;
	document.getElementById('state_pop').selectedIndex = 0;
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

function addCompanyAddress() 
{
	var isSuccess=false;
	var valid=$("#Popup_Address").valid();
	
	if(!valid)
	{
		return;
	}
	var companyRegnKey=$("#regnkey").val();
	var fromUserKey=$("#EmailAddress").val();
	var state=$("#state_pop  option:selected").val()=="--Select State--"?"":$("#state_pop  option:selected").val();
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/VRMailingServlet",
		data : {
			'RequestType':'AddAddress',	
			'addressType' : $("#branch_pop  option:selected").val(),
			'countryRegion' : $("#countryregion_pop  option:selected").val(),
			'address' : $("#AddAddress_pop").val(),
			'city' : $("#city_pop").val(),
			'state' : state,
			'zipcode' : $("#zipcode_pop").val(),
			'companyRegnKey':companyRegnKey,
			'emailid':fromUserKey,
			
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
					    	
					    	$("#Popup_Address").hide();
						}
				}
				
				if(key=="insertAddrId")
				{
					if(isSuccess)
					{ 
						var AddressElement='<div class="AddressContiner">'+									
						'<fieldset>'+
						 '<legend> '+$("#branch_pop  option:selected").val()+'</legend>'+
						 '<div class="AddressDetails">'+
						 	$("#AddAddress_pop").val()+'<br />'+$("#city_pop").val()+" "+
							$("#zipcode_pop").val()+'<br />'+
							$("#state_pop  option:selected").val()+','+$("#countryregion_pop  option:selected").val()+
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


function getVendorAddress()
{
	alert("get vendor address");
	
	var companyRegnKey=$("#regnkey").val();
	
	alert("regnKey="+companyRegnKey);
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/VRMailingServlet",
		data : {
			'RequestType':'FetchAddress',
			'companyRegnKey':companyRegnKey,
				
		},
		dataType : 'json',
		cache : false,
		success: function( addrJSONObj )
        {
            if( addrJSONObj.result == "success")
            {
            	 var addrArr = new Array(addrJSONObj.Addresses.length);

                 addrArr = addrJSONObj.Addresses;
                 
            	 var arrCount = addrArr.length;
            	    
            	    for( var i=0;i<arrCount;i++)
            	    {
            	        var arrdata = addrArr[i];
            	        
            	        var address = arrdata.address;
            	        
            	        var city    = arrdata.city;
            	        
            	        var state   = arrdata.state;
            	        
            	        var zipcode  = arrdata.zipcode;
            	        
            	        var branch   = arrdata.addressType;
            	        
            	        var country  = arrdata.country;
            	        
            	        var addrId  =arrdata.addrId;
            	        
            	       
            	            var AddressElement='<div class="AddressContiner">'+                                    
            	            '<fieldset>'+
            	             '<legend> '+branch+'</legend>'+
            	             '<div class="AddressDetails">'+
            	                 address+'<br />'+city+
            	                '-'+zipcode+'<br />'+
            	                state+','+country+
            	            '</div>'+
            	            '</fieldset>'+
            	            '<input type="button" name="removeaddressbtn_'+addrId+'" class="removeaddress" style="display: inline-block;" onclick="removeCompanyAddress(this);">'+                
            	        '</div>';

            	            $("#AddressList").append(AddressElement);
            	            
            	            if ($('#addAddressEmpty').length)
            	            {
            	                $("#addAddressEmpty").remove();
            	                AddressExpander($("#addressExpander"));
            	            }
            	       
            	    }
            }
            else
            {
               //alert("else");
            }
            
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
	
	var addressNameArr = name.split('_');
	
	var addressId=addressNameArr[1];
	
	if(addressId< 0 )
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
		url : getBaseURL() + "/VRMailingServlet",
		data : {
			'RequestType':'DeleteAddress',
			'operation':'RemoveMail',
			'addrid' : addressId_,			
		},
		dataType : 'json',
		cache : false,
		success : function(result) {

			$.each(result, function(key, value) {


				if(key="result")
				{
						if(value="success")
						{
							/*if($("#AddressList").children().length==1)
							{
								$("#addressExpandContainer").html('<div id="addressExpander"></div><input style="display: block;position: absolute;height: 30px;" type="button" id="addAddressEmpty" class="addAddress">');
								$("#addAddressEmpty").click(AddMailButtonClick);
								AddressExpander($("#addressExpander"));
								
							}*/
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
			'businesscategory' : $("#businesscategory  option:selected").val(),
			'companyid' : $("#companyid").val(),
			'plan' : $('input:radio[name=plan]:checked').val(),
			'themeSelect' : $("#themeSelect  option:selected").val(),
			'country' : $("#countryregion_0  option:selected").val(),
			'branch' : $("#branch_0  option:selected").val(),
			'address' : $("#address_0").val(),
			'city' : $("#city_0").val(),
			'state' : $("#state_0  option:selected").val(),
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


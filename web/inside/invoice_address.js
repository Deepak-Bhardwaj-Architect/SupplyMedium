
/* This method is used to get the Invoice buyer address */
function getInvoiceMyAddress()
{
	var regnKey = $("#regnkey").val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/CompanyFullProfileServlet",
		   data:{ 
		        'RegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function( profileJSONObj )
		   {
			   hideAjaxLoader();
			   
			   if(profileJSONObj.result == "success")
				{
				   var profileData = profileJSONObj.profiledetails;
                   
                   var addrArr = new Array(profileData.addressdata.length);

                   addrArr = profileData.addressdata;
                   
                   var arrdata = addrArr[0];
                   
                   var companyName = profileData.companyname;
                   
                   var businessType = profileData.businesstype;
                   
                   if( businessType == "Buyer")
                   {
                	   $("#invoice_form_tab").hide();
                	}
                   
                   var address = arrdata.address;
                   
                   var city     = arrdata.city;
                   
                   var state    = arrdata.state;
                   
                   if(state==null)
                   {
                	   $("#buyer_state").val("");
                   }
                   else
                	{
                	   $("#buyer_state").val(state);
                	}
                   
                   
                   var zipcode    = arrdata.zipcode;
                   
                   var country    = arrdata.country;
                   
                  $("#buyer_name").val(companyName);
                   
                  $("#buyer_country").val(country);
                   
                  //$("#buyer_state").val(state);
                   
                  $("#buyer_city").val(city);
                   
                  $("#buyer_addr").val(address);
                   
                  $("#buyer_zipcode").val(zipcode);
                   
				}

				else 
				{
					 ShowToast( profileJSONObj.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
//			   alert("Exception");
			}
		 });
}

/* This method is used to get the Invoice Supplier address
 * This request send once user select the supplier from supplier
 * search result.
 */

function getInvoiceSupplierAddress( regnKey )
{
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/CompanyFullProfileServlet",
		   data:{ 
			   'RegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function( profileJSONObj )
		   {
			   hideAjaxLoader();
			   
			   if(profileJSONObj.result == "success")
				{
				   
				   var profileData = profileJSONObj.profiledetails;

                   
                   var addrArr = new Array(profileData.addressdata.length);

                   addrArr = profileData.addressdata;
                   
                   var arrdata = addrArr[0];
                   
                   var companyName = profileData.companyname;
                   
                   var address = arrdata.address;
                   
                   var city     = arrdata.city;
                   
                   var state    = arrdata.state;
                                   
                   var zipcode    = arrdata.zipcode;
                   
                   var country    = arrdata.country;
                   
                  $("#supplier_name").val(companyName);
                   
                  $("#supplier_country").val(country);
                   
                  $("#supplier_state").val(state);
                   
                  $("#supplier_city").val(city);
                   
                  $("#supplier_addr").val(address);
                   
                  $("#supplier_zipcode").val(zipcode);
                  
				}

				else 
				{
					 ShowToast( profileJSONObj.message,2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			   
//			   alert("Exception");
			}
		 });
}

/* this method is used to get the courier names from server */
function getCourier()
{
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/TransConfigServlet",
		data : {
			'TransConfigType' : 'GetCarriers',
		},
		dataType : 'json',
		cache : false,
		success : function(data) {
			
			var $select = $('#carrier');
			var $select1 = $("#popup_carrier");
			// Remove the old drop down box datas
			$select.find('option').remove();
			$select1.find('option').remove();

			if( data.result == "success") // success result
			   {
				
				$('<option>').val('').text('--Select Carrier--').appendTo(
						$select);
				$('<option>').val('').text('--Select Carrier--').appendTo(
						$select1);
				
					$.each(data, function(key, value) // get the key,value one by one 
					{
						if( value != "success")  // leave 'result':'success' pair
						{
							$('<option>').val(key).text(value).appendTo(
									$select);
							$('<option>').val(key).text(value).appendTo(
									$select1);
							
						}
							
					});
					
					sortSelectBox('carrier');
					sortSelectBox('popup_carrier');
					
					$("#carrier option:first-child").attr("selected", true);
					
					$select.trigger("update");
					
					$select1.trigger("update");
					
					 $('#carrier option').prop('disabled', false);
					 
					 $("#popup_carrier option:first-child").attr("selected", true);
						
					 $('#popup_carrier option').prop('disabled', false);
					
			   }
			   else  // failed result
			   {
				 
				  
				  
			   }
			
			  $select.trigger('update');

		},
		error : function(xhr, textStatus, errorThrown)  // other errors
		{
			
		}
	});
}

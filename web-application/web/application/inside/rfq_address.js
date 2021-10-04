
/* This method is used to get the RFQ buyer address */
function getRFQMyAddress()
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
                   
                   if( businessType == "Supplier")
                   {
                	   $("#rfq_form_tab").hide();
                	}
                   
                   var address = arrdata.address;
                   
                   var city     = arrdata.city;
                   
                   var state    = arrdata.state;
                   
                  /* if(state==null)
                   {
                	   //alert("state if="+state);
                	   $("#buyer_state").val("");
                   }
                   else
                	{
                	   //alert("state else="+state);
                	   $("#buyer_state").val(state);
                	}*/
                   
                   var zipcode    = arrdata.zipcode;
                   
                   var country    = arrdata.country;
                   
                  $("#buyer_name").val(companyName);
                  
                  $("#buyer_country").val(country);
                  
                  $("#buyer_state").val(state);
                   
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

/* This method is used to get the RFQ Supplier address
 * This request send once user select the supplier from supplier
 * search result.
 */

function getRFQSupplierAddress( regnKey )
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

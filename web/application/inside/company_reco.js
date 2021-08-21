/*
 * This method is used to get the recommended companies for given company
 * regn key. 
 */
function getRecoCompanies()
{
	var regnKey = $("#regnkey").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/RecoServlet",
		data : {
			'RegnKey' : regnKey
		},
		cache : false,
		success : function(data) 
		{
			
			if (data.result == "success") 
			{
				var companies = data.companies;
				
				for ( var i = 0; i < companies.length; i++) 
				{
					var company = companies[i];
					
					var recoStr = "";
					
					if( (i%2) == 0)
					{
						recoStr += '<div class="rec_comp odd_row" id='+company.companyKey+'>';
					}
					else
					{
						recoStr +=  '<div class="rec_comp even_row" id='+company.companyKey+'>';
					}
					
					recoStr += '<div class="rec_comp_left">';
					
					recoStr += '<div class="rec_comp_img">';
					
					if( company.logoURL == 'null' || company.logoURL == '' )
					{
						recoStr += '<img src="Views/Utils/images/no_logo.png" style="width:60px;height:60px;border:none;"/>';
					}
					else
					{
						recoStr += '<img src="'+company.logoURL+'" style="width:60px;height:60px;border:none;"/>';
					}
					
					recoStr += '</div></div>';
					
					recoStr += '<div class="rec_comp_right">';
					
					
				
					recoStr += '<div class="rec_comp_name">'+company.companyName+'</div>';
					
					
					recoStr += '<input type="button" class="comp_reco_del" value="X" id="reco_del_'+ company.companyKey + '"  style="display:none;"/>';
						
					recoStr += '<div class="rec_comp_addr">'+company.country;
					
					if( company.city !='null' && company.city != "")
						
						recoStr += ','+company.city;
					
					recoStr += '</div>';
					
					/* company.companyType */
							
					recoStr += '<input type="button" class="gen-btn-Lblue rec_comp_btn" value="Add Vendor" onclick="addVendor(\''+company.companyKey+'\',\''+company.companyName+'\',\''+company.companyType+'\')">';
								
					recoStr += '</div></div>';
					
					$("#recommend_content .mCSB_container").append(recoStr);
					
					$("#recommend_content").mCustomScrollbar("update");
					
					$("#reco_del_" + company.companyKey).on('click', function(event) {
						
						var divid = event.target.id;
						
						var idSplitArr = divid.split("_");

						if (idSplitArr.length > 2) 
						{
							var regnkey = idSplitArr[2];

							deleteRecoComp(regnkey);
						}

					});

					
					$("#" + company.companyKey).on('mouseenter', function(event) {
						
						var divid = event.target.id;
						
						$("#reco_del_" + divid).show();

					});

					$("#" + company.companyKey).on('mouseleave', function(event) 
					{
						var divid = event.target.id;
						
						$("#reco_del_" + divid).hide();
					});
				}
				
				
			
			}
			else 
			{
//				alert("failed");
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
//			alert("failed");
		}
	});

}

/* It is used to delete the recommendation company from view */

function deleteRecoComp ( comKey )
{
	
	$("#"+comKey).slideUp("fast",function ()
			{
				$("#" + comKey).remove();
			});
}


function addVendor( regnKey,companyName,companyType )
{	
	var homeCompanyType = $("#companytype").val();
	
	if( companyType =="Buyer" )
	{
		// Goto Add Buyer
		showAddBuyerForm( regnKey, companyName );
	}
	else if( companyType=="Supplier" )
	{
		// Goto Add Supplier
		showAddSupplierForm( regnKey, companyName );
	}
	else if( companyType=="Both" )
	{
		if( homeCompanyType =="Buyer" )
		{
			// Goto add Supplier
			showAddSupplierForm( regnKey, companyName );
		}
		else if( homeCompanyType=="Supplier" )
		{
			// Goto add buyer
			showAddBuyerForm( regnKey, companyName );
		}
		else 
		{
			// Goto add buyer
			showAddBuyerForm( regnKey, companyName );
		}
	}
	 
}


function showAddBuyerForm( vendorKey, name )
{
	$("#Corporate").removeClass("navmainSelected");
	$("#Suppliers").addClass("navmainSelected");
	
	$("#mainpage").empty();
	
	highLightMenu( "Buyers" );
	
	$("#mainpage").load("Views/VendorReg/jsp/supplier_ven_reg.jsp",  function() 
	{
		$("#add_buyer_tab").removeClass("main_tab_unselect");
		$("#add_buyer_tab").addClass("main_tab_select");
		
		$("#add_buyer_content").show( );
		
		$("#req_queue_tab").removeClass("main_tab_select");
		$("#req_queue_tab").addClass("main_tab_unselect");
		
		$("#req_queue_content").hide( );
		
		$("#selected_ven_key").val(vendorKey);
		$("#to_company").val(name);
		
		//loadVRPage( );	
	});
	
	
}

function showAddSupplierForm( vendorKey, name )
{
	$("#Search").removeClass("navmainSelected");
	$("#Buyers").addClass("navmainSelected");
	
	$("#mainpage").empty();
	
	highLightMenu( "Suppliers" );
	
	$("#mainpage").load("Views/VendorReg/jsp/buyer_ven_reg.jsp",  function() 
	{
		$("#add_supplier_tab").removeClass("main_tab_unselect");
		$("#add_supplier_tab").addClass("main_tab_select");
		
		$("#add_supplier_content").show( );
		
		$("#req_queue_tab").removeClass("main_tab_select");
		$("#req_queue_tab").addClass("main_tab_unselect");
		
		$("#req_queue_content").hide( );
		
		$("#selected_ven_key").val(vendorKey);
		$("#to_company").val(name);
	});
	
	
}


function highLightMenu( menuId)
{
	// Change the Main Menu Selected Item to highlight 
	$( "#"+menuId ).addClass("navmainSelected");
	
	// Remove the Menu if alredy any one selected 
	var item=$(".nav_Main .navmainSelected");
	
	for(var i=0;i<item.length;i++) 
	{
		if(menuId!=item[i].id)
		{
			$(item[i]).removeClass("navmainSelected");
		}				
					
	}

	
	// Based on the Main Menu Select the Sub Menu 
	
	var SelectedID="#Sub"+menuId;	
	
	var item=$(".nav_Sub ul");
	
	for(var i=0;i<item.length;i++)
	{
		if(SelectedID!=item[i].id)
		{
			$(item[i]).hide();
		}				
					
	}
	
	$(SelectedID).show(); // Show the Selected Sub Menu  
	
	// Unselect the Submenu If any on is selected 
	var item=$(SelectedID+" .navsubSelected");
	
	for(var i=0;i<item.length;i++)
	{
		$(item[i]).removeClass("navsubSelected");		
	}
	
	// By Default Select the First Sub Menu 
	var item=$(SelectedID +" li");
	$(item[0]).addClass("navsubSelected");
}


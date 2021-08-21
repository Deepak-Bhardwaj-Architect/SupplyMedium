$(document).ready(function()
{
		$( "#footer_terms" ).die( "click" );
		
		$("#footer_terms").live( "click", function()
		{
			$("#terms_commonpopup").show();
		});
		
		$( "#footer_privacy" ).die( "click" );
		
		$("#footer_privacy").live( "click", function()
		{
			$("#privacy_commonpopup").show();
			
		});
	
		$("#footer_admin").live( "click", function()
		{
		
		if( $("#access_user_mgmt_flag").val( ) == 0 )
		{
				restrictSubMenu( );
		}
		else
		{
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/UserMgmt/jsp/usermgmt.jsp", function() 
			{
				//$("#content_loader").hide();
				
				setCookie("CurrentPage","UserMgmt");
				
				hightLightSelectedMenu( "Admin" );
				
			});;
		}
	});
		
		$( "#footer_corp" ).die( "click" );
		
		$("#footer_corp").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			
			$("#mainpage").load("Views/Feed/jsp/userfeed.jsp", function() 
			{
				//$("#content_loader").hide();
				
				hightLightSelectedMenu( "Corporate" );
			});;
			
		});
		
		$( "#footer_search" ).die( "click" );
		
		$("#footer_search").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			if( companyType == "Buyer" || companyType == "Both" )
			{
				$("#mainpage").load("Views/Search/jsp/Supplier.jsp", function() 
						{
					hightLightSelectedMenu( "Search" );
				});;
				$("#SubSearch_Search_Buyer").removeClass( "navsubSelected" );
				$("#SubSearch_Search_Supplier").addClass( "navsubSelected" );
			}
			else if( companyType == "Supplier" )
			{
				$("#mainpage").load("Views/Search/jsp/Buyer.jsp", function() 
						{
					hightLightSelectedMenu( "Search" );
				});;
				$("#SubSearch_Search_Supplier").removeClass( "navsubSelected" );
				$("#SubSearch_Search_Buyer").addClass( "navsubSelected" );
			}
			
		});
	
		
		$( "#footer_supplier" ).die( "click" );
		
		$("#footer_supplier").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/VendorReg/jsp/buyer_ven_reg.jsp", function() 
			{
				//$("#content_loader").hide();
				
				hightLightSelectedMenu( "Suppliers" );
			});;
			
		});
		
		$( "#footer_buyers" ).die( "click" );
		
		$("#footer_buyers").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/VendorReg/jsp/supplier_ven_reg.jsp", function() 
			{
				//$("#content_loader").hide();
				hightLightSelectedMenu( "Buyers" );
			});;
			
		});
		
		$( "#footer_trans" ).die( "click" );
		
		$("#footer_trans").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/Transaction/TransCommon/jsp/transaction.jsp", function() 
			{
				//$("#content_loader").hide();
				hightLightSelectedMenu( "Transactions" );
			});;
			
		});
		

		$( "#footer_network" ).die( "click" );
		
		$("#footer_network").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/NewsRoom/jsp/newsroom.jsp", function() 
			{
				//$("#content_loader").hide();
				hightLightSelectedMenu( "Network" );
			});
			
		});
		
		customizeFooterMenu( );
	
});

function hightLightSelectedMenu( menuId )
{
	// Change the Main Menu Selected Item to highlight 
	$("#"+menuId).addClass("navmainSelected");
	
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

var companyType = $("#companytype").val();
var userType = $("#usertype_for_menu").val();

function customizeFooterMenu( )
{
	if( companyType == "Buyer" )
	{
		if( userType == "Admin" )
		{
			adminBuyerFooterMenu( );
		}
		else if( userType == "TransactionUser" )
		{
			transBuyerFooterMenu( );
		}
		else if( userType == "IntranetUser" )
		{
			intranetFooterMenu( );
		}
	}
	else if( companyType == "Supplier" )
	{
		if( userType == "Admin" )
		{
			adminSupplierFooterMenu( );
		}
		else if( userType == "TransactionUser" )
		{
			transSupplierFooterMenu( );
		}
		else if( userType == "IntranetUser" )
		{
			intranetFooterMenu( );
		}
	}
	else if( companyType == "Both" )
	{
		if( userType == "Admin" )
		{
			adminBothFooterMenu( );
		}
		else if( userType == "TransactionUser" )
		{
			transBothFooterMenu( );
		}
		else if( userType == "IntranetUser" )
		{
			intranetFooterMenu( );
		}
	}
}

function adminBuyerFooterMenu( )
{
	$("#footer_buyers").remove();
	$("#footer_buyers_sep").remove();
}

function adminSupplierFooterMenu( )
{
	/* 6 menus */
	
	$("#footer_supplier").remove();
	$("#footer_supplier_sep").remove();
}


function adminBothFooterMenu( )
{
	
}

function transBuyerFooterMenu( )
{		
	loadAdminFooterMenu( );
	
	$("#footer_buyer").remove();
	$("#footer_buyer_sep").remove();
	
	
}

function transSupplierFooterMenu( )
{
	loadAdminFooterMenu( );
	
	$("#footer_supplier").remove();
	$("#footer_supplier_sep").remove();
}

function transBothFooterMenu( )
{
	loadAdminFooterMenu( );
}

function intranetFooterMenu( )
{
	/* 2 menus */
	
	
	$("#footer_admin").remove();
	$("#footer_admin_sep").remove();
	
	$("#footer_search").remove();
	$("#footer_search_sep").remove();

	$("#footer_supplier").remove();
	$("#footer_supplier_sep").remove();
	
	$("#footer_buyers").remove();
	$("#footer_buyers_sep").remove();

	
	$("#footer_trans").remove();
	$("#footer_trans_sep").remove();
	

}


/*This method will load admin menu only if
 * suitable privileges available to the logged in user
 * 
 * This is applicable for users other than admin*/

function loadAdminFooterMenu( )
{
	var adminPri = isAdminPri( );
	
	if( adminPri == 2 )/* 5 menus */
	{
		$("#Admin").remove( );
		$("#menu_separtor_Admin").remove( );
		
		$("#footer_admin").remove();
		$("#footer_admin_sep").remove();
		

	}
}

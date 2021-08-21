var companyType = $("#companytype").val();
var userType = $("#usertype_for_menu").val();


//7 - 144.1, 6 - 167.4, 5,2 - 201.2

var sevenMenuWidth = "143";
var sixMenuWidth = "167";
var fiveMenuWidth = "201"; //Same for two menu

/*This will customize the menu base on 
 * 
 * 1. CompanyType
 * 
 * 2. UserType
 */

function customizeMenu( )
{
//	alert(companyType+","+userType);
	if( companyType == "Buyer" )
	{
		if( userType == "Admin" )
		{
			adminBuyerMenu( );
		}
		else if( userType == "TransactionUser" )
		{
			transBuyerMenu( );
		}
		else if( userType == "IntranetUser" )
		{
			intranetMenu( );
		}
	}
	else if( companyType == "Supplier" )
	{
		if( userType == "Admin" )
		{
			adminSupplierMenu( );
		}
		else if( userType == "TransactionUser" )
		{
			transSupplierMenu( );
		}
		else if( userType == "IntranetUser" )
		{
			intranetMenu( );
		}
	}
	else if( companyType == "Both" )
	{
		if( userType == "Admin" )
		{
			adminBothMenu( );
		}
		else if( userType == "TransactionUser" )
		{
			transBothMenu( );
		}
		else if( userType == "IntranetUser" )
		{
			intranetMenu( );
		}
	}
}

function adminBuyerMenu( )
{
	/* 6 menus */

	adjustMenuWidth( sixMenuWidth ); // 7 - 144.1, 6 - 167.4, 5,2 - 201.2
	
	$("#Admin").show( );
	$("#menu_separtor_Admin").show( );
	
	$("#Corporate").show( );
	$("#menu_separtor_Corporate").show( );
	
	$("#Search").show( );
	$("#menu_separtor_Search").show( );
	
	$("#Suppliers").show( );
	$("#menu_separtor_Suppliers").show( );
	
	$("#Buyers").remove( );
	$("#menu_separtor_Buyers").remove( );
	
	
	
	$("#Transactions").show( );
	$("#menu_separtor_Transactions").show( );
	
	$("#Network").show( );
	
	//To load sub menus

	$("#SubSearch_Search_Supplier").show( );
	
	$("#Sub_menu_seperator_SubSearch_Search_Supplier").remove( );
	
	$("#SubSearch_Search_Buyer").remove( );
	
	// Remove the product catalog submenu
	
	$("#Sub_menu_seperator_SubCorporate_Department_Page").remove();
	
	$("#SubCorporate_Product_Catalog").remove();
	
	//To correct last menu width
	$("#Network").css("width", "164");
}

function adminSupplierMenu( )
{
	/* 6 menus */
	
	adjustMenuWidth( sixMenuWidth ); // 7 - 144.1, 6 - 167.4, 5,2 - 201.2
	
	$("#Admin").show( );
	$("#menu_separtor_Admin").show( );
	
	$("#Corporate").show( );
	$("#menu_separtor_Corporate").show( );
	
	$("#Search").show( );
	$("#menu_separtor_Search").show( );

	
	$("#Suppliers").remove( );
	$("#menu_separtor_Suppliers").remove( );

	
	$("#Buyers").show( );
	$("#menu_separtor_Buyers").show( );
	
	$("#Transactions").show( );
	$("#menu_separtor_Transactions").show( );
	
	$("#Network").show( );
	
	//To load sub menus
	
	$("#SubSearch_Search_Supplier").remove( );
	
	$("#Sub_menu_seperator_SubSearch_Search_Supplier").remove( );
	
	$("#SubSearch_Search_Buyer").show( );	
	
	//To correct last menu width
	$("#Network").css("width", "164");
}


function adminBothMenu( )
{
	/* 7 menus */
	
	adjustMenuWidth( sevenMenuWidth ); // 7 - 144.1, 6 - 167.4, 5,2 - 201.2

	$("#Admin").show( );
	$("#menu_separtor_Admin").show( );
	
	$("#Corporate").show( );
	$("#menu_separtor_Corporate").show( );
	
	$("#Search").show( );
	$("#menu_separtor_Search").show( );
	
	$("#Suppliers").show( );
	$("#menu_separtor_Suppliers").show( );
	
	$("#Buyers").show( );
	$("#menu_separtor_Buyers").show( );
	
	$("#Transactions").show( );
	$("#menu_separtor_Transactions").show( );
	
	$("#Network").show( );

	//To correct last menu width
	$("#Network").css("width", "139");
	
	//To load sub menus
	
	$("#SubSearch_Search_Supplier").show( );
	$("#Sub_menu_seperator_SubSearch_Search_Supplier").show( );
	$("#SubSearch_Search_Buyer").show( );
}

function transBuyerMenu( )
{		
	loadAdminMenu( );
	
	$("#Corporate").show( );
	$("#menu_separtor_Corporate").show( );
	
	$("#Search").show( );
	$("#menu_separtor_Search").show( );
	
	$("#Suppliers").show( );
	$("#menu_separtor_Suppliers").show( );
	
	$("#Buyers").remove( );
	$("#menu_separtor_Buyers").remove( );
	
	
	$("#Transactions").show( );
	$("#menu_separtor_Transactions").show( );
	
	$("#Network").show( );
	
	//To load sub menus

	$("#SubSearch_Search_Supplier").show( );
	
	$("#Sub_menu_seperator_SubSearch_Search_Supplier").remove( );
	$("#SubSearch_Search_Buyer").remove( );
	
	
	$("#Sub_menu_seperator_SubCorporate_Department_Page").remove();
	
	$("#SubCorporate_Product_Catalog").remove();
	
	

//	//To correct last menu width
//	$("#Network").css("width", "197");
}

function transSupplierMenu( )
{
	loadAdminMenu( );
	
	$("#Corporate").show( );
	$("#menu_separtor_Corporate").show( );
	
	$("#Search").show( );
	$("#menu_separtor_Search").show( );
	

	
	$("#Suppliers").remove( );
	$("#menu_separtor_Suppliers").remove( );
	
	
	$("#Buyers").show( );
	$("#menu_separtor_Buyers").show( );
	
	$("#Transactions").show( );
	$("#menu_separtor_Transactions").show( );
	
	$("#Network").show( );

	
	//To load sub menus


	
	$("#SubSearch_Search_Supplier").remove( );
	$("#Sub_menu_seperator_SubSearch_Search_Supplier").remove( );
	
	$("#SubSearch_Search_Buyer").show( );
	
	

	
//	$("#mainpage").empty();
//	
//	$("#mainpage").load("Views/Feed/jsp/userfeed.jsp");
//	
//
//
//	
//	$("#SubAdmin").remove();
//
//	$("#Corporate").addClass("nav_Main_second_menu");
//
//	$("#Admin").removeClass("navmainSelected");
//
//	$("#Corporate").addClass("navmainSelected");
//	//	
//	$("#SubCorporate").show( );
// 
//	
//	$("#SubCorporate_User_Page").addClass( "navsubSelected" );
	
	//To correct last menu width
	//$("#Network").css("width", "197");
	
}

function transBothMenu( )
{
	loadAdminMenu( );
	
	$("#Corporate").show( );
	$("#menu_separtor_Corporate").show( );
	
	$("#Search").show( );
	$("#menu_separtor_Search").show( );
	
	$("#Suppliers").show( );
	$("#menu_separtor_Suppliers").show( );
	
	$("#Buyers").show( );
	$("#menu_separtor_Buyers").show( );
	
	$("#Transactions").show( );
	$("#menu_separtor_Transactions").show( );
	
	$("#Network").show( );

	//To load sub menus

	$("#SubSearch_Search_Supplier").show( );
	$("#Sub_menu_seperator_SubSearch_Search_Supplier").show( );
	$("#SubSearch_Search_Buyer").show( );
	
	
//	$("#mainpage").empty();
//	
//	$("#mainpage").load("Views/Feed/jsp/userfeed.jsp");
//	
//	
//	$("#SubAdmin").remove( );
//	
//
//	$("#Corporate").addClass("nav_Main_second_menu");
//	
//	$("#Admin").removeClass( "navmainSelected" );
//	
//	$("#Corporate").addClass( "navmainSelected" );
//	
//	$("#SubCorporate").show( );
// 
//	
//	$("#SubCorporate_User_Page").addClass( "navsubSelected" );
	
	//To correct last menu width
	//$("#Network").css("width", "164");
	
}

function intranetMenu( )
{
	/* 2 menus */
	
	adjustMenuWidth( fiveMenuWidth ); // 7 - 144.1, 6 - 167.4, 5,2 - 201.2
	
	$("#Admin").remove( );
	$("#menu_separtor_Admin").remove( );
	
	
	$("#Corporate").show( );
	$("#menu_separtor_Corporate").show( );

	
	$("#Search").remove( );
	$("#menu_separtor_Search").remove( );
	
	
	
	$("#Suppliers").remove( );
	$("#menu_separtor_Suppliers").remove( );
	

	$("#Buyers").remove( );
	$("#menu_separtor_Buyers").remove( );
	

	
	$("#Transactions").remove( );
	$("#menu_separtor_Transactions").remove( );
	
	
	
	$("#Network").show( );
	
  // Remove the product catalog submenu
	
	$("#Sub_menu_seperator_SubCorporate_Department_Page").remove();
	
	$("#SubCorporate_Product_Catalog").remove();
	
	//To load sub menus
	
	$("#mainpage").empty();
	
	$("#mainpage").load("Views/Feed/jsp/userfeed.jsp");

	$("#SubAdmin").remove( );

	$("#Corporate").addClass("nav_Main_second_menu");

	$("#Admin").removeClass( "navmainSelected" );
	
	$("#Corporate").addClass( "navmainSelected" );
	
	
//	
	$("#SubCorporate").show( );
 
	$("#SubCorporate_User_Page").addClass( "navsubSelected" );
	
	$("#Network").removeClass( "nav_Main ul li:LAST-CHILD" );
	$("#Network").addClass( "nav_Main_last_menu" );
}


/*This method will load admin menu only if
 * suitable privileges available to the logged in user
 * 
 * This is applicable for users other than admin*/

function loadAdminMenu( )
{
	var adminPri = isAdminPri( );
	
	//alert( "Admin Menu show val = " + adminPri );
	
	if( adminPri == 0 )  /* 6 menus */
	{
		$("#Admin").show( );
		$("#menu_separtor_Admin").show( );
		
		if( companyType == "Both" )
		{
			adjustMenuWidth( sevenMenuWidth );
		}
		else
		{
			adjustMenuWidth( sixMenuWidth );
			//To correct last menu width
			$("#Network").css("width", "164");
		}
	}
	else if( adminPri == 1 )  /* 6 menus */
	{
		$("#Admin").show( );
		$("#menu_separtor_Admin").show( );
		
		if( companyType == "Both" )
		{
			adjustMenuWidth( sevenMenuWidth );
		}
		else
		{
			adjustMenuWidth( sixMenuWidth );
			//To correct last menu width
			$("#Network").css("width", "164");
		}
				
		restrictSubMenu( );
	}
	else if( adminPri == 2 )/* 5 menus */
	{
		$("#Admin").remove( );
		$("#menu_separtor_Admin").remove( );
		
		

		$("#mainpage").empty();
		
		$("#mainpage").load("Views/Feed/jsp/userfeed.jsp");
		
		$("#SubAdmin").remove( );

		$("#Corporate").addClass("nav_Main_second_menu");
		
		$("#Admin").removeClass( "navmainSelected" );

		$("#Corporate").addClass( "navmainSelected" );
		
		$("#SubCorporate").show( );
	 
		$("#SubCorporate_User_Page").addClass( "navsubSelected" );

		
		if( companyType == "Both" )
		{
			adjustMenuWidth( sixMenuWidth );
			//To correct last menu width
			$("#Network").css("width", "164");
		}
		else
		{
			adjustMenuWidth( fiveMenuWidth );
			//To correct last menu width
			$("#Network").css("width", "197");
		}
	}
}

function isAdminPri( )
{
	if( ($("#access_user_mgmt_flag").val() == 1) )
	{
		return 0;  //All admin privileges available for this privilege
	}
	else if( ( $("#add_user_flag").val() == 1 ) ||  
	
		( $("#delete_user_flag").val() == 1 ) || 
	
		( $("#create_group_flag").val() == 1 ) ||
	
		( $("#delete_group_flag").val() == 1 ) ||
	
		( $("#manage_folder_flag").val() == 1) )
	{
		return 1;  //Admin menu should be shown
	}
	else
	{
		return 2; //Should not show admin menu to this user
	}
}

function restrictSubMenu( )
{	
	$("#SubAdmin_Departments").remove( );
	$("#Sub_menu_seperator_SubAdmin_Departments").remove( );
	
	$("#SubAdmin_Account_Policies").remove( );
	$("#Sub_menu_seperator_SubAdmin_Account_Policies").remove( );
	
	$("#SubAdmin_Company_Profile").remove( );
	$("#Sub_menu_seperator_SubAdmin_Company_Profile").remove( );
	
	$("#SubAdmin_Company_Website").remove( );
	
	if( $( "#add_user_flag" ).val( ) == 1 || $( "#delete_user_flag" ).val( ) == 1 )
	{
		$("#mainpage").empty();
		
		$("#mainpage").load("Views/UserMgmt/jsp/usermgmt.jsp");
		
		$("#SubAdmin_User").addClass( "navsubSelected" );
		$("#SubAdmin_Group").removeClass( "navsubSelected" );
		$("#SubAdmin_Manage_Folders").removeClass( "navsubSelected" );
	}
	else
	{
		$( "#SubAdmin_User" ).remove( );
		$("#Sub_menu_seperator_SubAdmin_User").remove( );
	}

	if( $( "#create_group_flag" ).val( ) == 1 || $( "#delete_group_flag" ).val( ) == 1 )
	{
		if( $( "#add_user_flag" ).val( ) == 0 && $( "#delete_user_flag" ).val( ) == 0 )
		{
			$("#mainpage").empty();
			
			$("#mainpage").load("Views/UserMgmt/jsp/groupmgmt.jsp");
			
			$( "#SubAdmin_User" ).remove( );
			
			$("#Sub_menu_seperator_SubAdmin_User").remove( );
			
			$("#SubAdmin_Group").addClass( "navsubSelected" );
		}	
	}
	else
	{
		$( "#SubAdmin_Group" ).remove( );
		$("#Sub_menu_seperator_SubAdmin_Group").remove( );
	}
	
	if( $( "#manage_folder_flag" ).val( ) == 1 )
	{
		if ( ( $( "#add_user_flag" ).val( ) == 0 && $( "#delete_user_flag" ).val( ) == 0 ) && 
			 (	$( "#create_group_flag" ).val( ) == 0 && $( "#delete_group_flag" ).val( ) == 0 ) )
		{
			$("#mainpage").empty();
			
			$("#mainpage").load("Views/ManageFolder/jsp/managefolder.jsp");
			
			$( "#SubAdmin_User" ).remove( );
			
			$("#Sub_menu_seperator_SubAdmin_User").remove( );
			
			$( "#SubAdmin_Group" ).remove( );
			
			$( "#Sub_menu_seperator_SubAdmin_Group" ).remove( );
			
			$("#SubAdmin_Manage_Folders").addClass( "navsubSelected" );
		}
	}
	else
	{
		$( "#SubAdmin_Manage_Folders" ).remove( );
		$("#Sub_menu_seperator_SubAdmin_Manage_Folders").remove( );
	}
}

function adjustMenuWidth( width )
{
    $("#Admin").css("width", width);
    $("#Corporate").css("width", width);
    $("#Search").css("width", width);
    $("#Suppliers").css("width", width);
    $("#Buyers").css("width", width);
    $("#Transactions").css("width", width);
    $("#Network").css("width", width);
}

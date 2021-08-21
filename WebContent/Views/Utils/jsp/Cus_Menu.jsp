<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu Program</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/ResetCSS.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Cus_Menu.css" />
<script type="text/javascript">

var newsRoomlatestFeedTimer;

var userLatestFeedTimer;


var watchListFeedTimer;

	$(function(){		
		// Main Menu Click Function  
		$(".nav_Main li").click(function(){
			
			clearTimeout( newsRoomlatestFeedTimer );
			
			clearTimeout( userLatestFeedTimer );
			
			clearTimeout( watchListFeedTimer );
			
			// Change the Main Menu Selected Item to highlight 
			$(this).addClass("navmainSelected");
			
			// Remove the Menu if alredy any one selected 
			var item=$(".nav_Main .navmainSelected");
			
			for(var i=0;i<item.length;i++) 
			{
				if(this.id!=item[i].id)
				{
					$(item[i]).removeClass("navmainSelected");
				}				
							
			}

			
			
			// Based on the Main Menu Select the Sub Menu 
			
			var SelectedID="#Sub"+this.id;	
			
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
			
			
		});
		
		/* Sub Menu Selection */
		
		$(".nav_Sub li").click(function(){			
				
			clearTimeout( newsRoomlatestFeedTimer );
			
			clearTimeout( userLatestFeedTimer );
			
			clearTimeout( watchListFeedTimer );
			
				/* Remove the navSelected if alredy any one selected  */
				var item=$(".nav_Sub .navsubSelected");
				for(var i=0;i<item.length;i++)
				{
					$(item[i]).removeClass("navsubSelected");		
								
				}
				
				$(this).addClass("navsubSelected");
			});
		
		
		/* Initialize the Menu First Time When the Page is Loaded */ 

		/* Fist Menu Item Selection by Default */
 		var item=$("#Menu .DisplayMenu li");
		$(item[0]).addClass("navmainSelected");

		var SelectedID="#Sub"+item[0].id;
		$(SelectedID).show();			
		var item=$(SelectedID +" li");
		$(item[0]).addClass("navsubSelected"); 
		//alert("1");

	});
	
	function selectSubMenu( submenuName )
	{
		/* Remove the navSelected if alredy any one selected  */
		var item=$(".nav_Sub .navsubSelected");
		
		for(var i=0;i<item.length;i++)
		{
			$(item[i]).removeClass("navsubSelected");		
						
		}
		
		$( "#"+submenuName ).addClass("navsubSelected");
	}
	
	$(document).ready(function()
	{
			$("#Admin").live( "click", function(){
				
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
						
					});;
				}
			});
			
			$("#SubAdmin_User").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/UserMgmt/jsp/usermgmt.jsp", function() 
				{
					$("#content_loader").hide();
					
					setCookie("CurrentPage","UserMgmt");
				});;
				
			});
			
		
			$("#SubAdmin_Group").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/UserMgmt/jsp/groupmgmt.jsp", function() 
				{
					$("#content_loader").hide();
					
					setCookie("CurrentPage","GroupMgmt");
				});;
				
			});
			
			$("#SubAdmin_Departments").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/DeptMgmt/jsp/deptmgmt.jsp", function() 
				{
					$("#content_loader").hide();
				});;
				
			});
			
			$("#SubAdmin_Account_Policies").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/UserMgmt/jsp/account_policies.jsp", function() 
				{
					$("#content_loader").hide();
				});;
				
			});
			
			$("#SubAdmin_Company_Profile").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("${pageContext.request.contextPath}/CompanyProfileLoaderServlet", function() 
				{
					//$("#content_loader").hide();
				});;
				
			});
			
			$("#SubAdmin_Manage_Folders").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/ManageFolder/jsp/managefolder.jsp", function() 
				{
					//$("#content_loader").hide();
					
					 lastSelectedLsId = "";
					 lastSelectedRsId = "";
				});;
				
			});
			
			$("#SubAdmin_Company_Website").live( "click", function(){
				
				//if( $("#regnkey").val()== "4044081111" || $("#regnkey").val()=="4084985186")
				//{
					
					$("#mainpage").empty();
				
					$("#content_loader").show();
				
					$("#mainpage").load("Views/ExternalPage/jsp/CompanyWebsite.jsp", function() 
					{
					
					});;
				
				//}
			});
			
			$("#Corporate").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/Feed/jsp/userfeed.jsp", function() 
				{
					userLatestFeedTimer = setInterval(function(){
						fetchUserLatestFeeds();
						},10*1000);

				});;
				
			});
			
			$("#SubCorporate_User_Page").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/Feed/jsp/userfeed.jsp", function() 
				{
					userLatestFeedTimer = setInterval(function(){
					fetchUserLatestFeeds();
					},10*1000);

				});;
				
			});
			
			$("#SubCorporate_Internal_Page").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/Feed/jsp/companyfeed.jsp", function() 
				{
					//$("#content_loader").hide();
				});;
				
			});
			
			$("#SubCorporate_Department_Page").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/DeptPage/jsp/deptpage.jsp", function() 
				{

					 //selectedDeptKey = "";
					 selectedFolderKey = "";
					 selectedFileKey = "";
					//$("#content_loader").hide();
				});;
				
			});
			
			
			$("#SubCorporate_Product_Catalog").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/Corporate/jsp/productcatalog.jsp", function() 
				{
					//$("#content_loader").hide();
				});;
				
			});
			
			

			
			$("#SubSearch_Search_Supplier").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/Search/jsp/Supplier.jsp", function() 
				{
					//$("#content_loader").hide();
				});;
				
			});
			
			$("#Search").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				if( companyType == "Buyer" || companyType == "Both" )
				{
					$("#mainpage").load("Views/Search/jsp/Supplier.jsp", function() 
							{
						//$("#content_loader").hide();
					});;
					$("#SubSearch_Search_Buyer").removeClass( "navsubSelected" );
					$("#SubSearch_Search_Supplier").addClass( "navsubSelected" );
				}
				else if( companyType == "Supplier" )
				{
					$("#mainpage").load("Views/Search/jsp/Buyer.jsp", function() 
							{
						//$("#content_loader").hide();
					});;
					$("#SubSearch_Search_Supplier").removeClass( "navsubSelected" );
					$("#SubSearch_Search_Buyer").addClass( "navsubSelected" );
				}
				
			});
			
			$("#SubSearch_Search_Buyer").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/Search/jsp/Buyer.jsp", function() 
				{
					//$("#content_loader").hide();
				});;
				
			});
			
			$("#Buyers").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/VendorReg/jsp/supplier_ven_reg.jsp", function() 
				{
					//$("#content_loader").hide();
				});;
				
			});
			
		$("#Suppliers").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/VendorReg/jsp/buyer_ven_reg.jsp", function() 
				{
					//$("#content_loader").hide();
				});;
				
			});
			
			$("#SubBuyers_Vendor_Registration").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/VendorReg/jsp/supplier_ven_reg.jsp", function() 
				{
					//$("#content_loader").hide();
				});;
				
			});
			
		$("#SubSuppliers_Vendor_Registration").live( "click", function(){
				
				$("#mainpage").empty();
				
				$("#content_loader").show();
				
				$("#mainpage").load("Views/VendorReg/jsp/buyer_ven_reg.jsp", function() 
				{
					//$("#content_loader").hide();
				});;
				
			});
			
		$("#Transactions").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/Transaction/TransCommon/jsp/transaction.jsp", function() 
					{
				//$("#content_loader").hide();
			});;
			
		});
		
		$("#SubTransactions_Transaction").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/Transaction/TransCommon/jsp/transaction.jsp", function() 
					{
				//$("#content_loader").hide();
			});;
			
		});
		
		
		$("#SubTransactions_History").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/History/jsp/customerhistory.jsp", function() 
					{
				//$("#content_loader").hide();
			});
			
		});
		
		$("#SubTransactions_TC").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/TransTC/jsp/trans_tc.jsp", function() 
					{
				//$("#content_loader").hide();
			});
			
		});
		
	$("#Network").live( "click", function(){
			
			$("#mainpage").empty();
			
			$("#content_loader").show();
			
			$("#mainpage").load("Views/NewsRoom/jsp/newsroom.jsp", function() 
			{
				 newsRoomlatestFeedTimer=setInterval(function(){
					fetchNewsRoomLatestFeeds();
					},10*1000);
				 
				 watchListlatestFeedTimer=setInterval(function(){
					 fetchWatchListLatestFeeds();
				},10*1000);
				 
			});
			
		});
	
	$("#SubNetwork_News_Feed").live( "click", function(){
		
		$("#mainpage").empty();
		
		$("#content_loader").show();
		
		$("#mainpage").load("Views/NewsRoom/jsp/newsroom.jsp", function() 
		{
			 newsRoomlatestFeedTimer=setInterval(function(){
				fetchNewsRoomLatestFeeds();
				},10*1000);
			 
			 watchListlatestFeedTimer=setInterval(function(){
				 fetchWatchListLatestFeeds();
			},10*1000);
			
		});
		
	});
	
	$("#SubNetwork_My_Connections").live( "click", function(){
		
		$("#mainpage").empty();
		
		$("#content_loader").show();
		
		$("#mainpage").load("Views/MyConn/jsp/connections.jsp", function() 
				{
			//$("#content_loader").hide();
		});
		
	});
	
	
	$("#SubNetwork_Messages").live( "click", function(){
		
		$("#mainpage").empty();
		
		$("#content_loader").show();
		
		$("#mainpage").load("Views/PrivateMsg/jsp/private_msg.jsp", function() 
				{
			//$("#content_loader").hide();
		});
		
	});
	
	$("#SubNetwork_Notification").live( "click", function(){
		
		$("#mainpage").empty();
		
		$("#content_loader").show();
		
		$("#mainpage").load("Views/Notification/jsp/notification.jsp", function() 
				{
			//$("#content_loader").hide();
		});
		
	});
	
	$("#SubNetwork_Ratings").live( "click", function(){
		
		$("#mainpage").empty();
		
		$("#content_loader").show();
		
		$("#mainpage").load("Views/UserRatings/jsp/userratings.jsp", function() 
				{
			//$("#content_loader").hide();
		});
		
	});
	
	$("#SubNetwork_Dashboard").live( "click", function(){
		
		$("#mainpage").empty();
		
		$("#content_loader").show();
		
		$("#mainpage").load("Views/Dashboard/jsp/dashboard.jsp", function() 
				{
			//$("#content_loader").hide();
		});
		
	});
	
	
	

	$("#SubCorporate_Ad").live( "click", function(){
		
		$("#mainpage").empty();
		
		$("#content_loader").show();
		
		$("#mainpage").load("Views/AD/jsp/ad.jsp", function() 
				{
			//$("#content_loader").hide();
		});
		
	});
		
	
	
	
		

});
	
</script>
</head>
<body>
<div id="menucontainer">
	<div id="Menu">
	
		<!-- Main Menu  -->
		<div class="nav_Main Gen_nav">			
			<ul class="MenuType1 DisplayMenu "  >
					<li id="Admin"><a  title="Admin" >Admin</a></li>
					<li  style="width: 2px"><div class="menu_separtor" id="menu_separtor_Admin"></div></li>
					<li id="Corporate"><a  title="Corporate" >Corporate</a></li>
					<li style="width: 2px"><div class="menu_separtor" id="menu_separtor_Corporate"></div></li>
					<li id="Search"><a  title="Search" >Search</a></li>
					<li style="width: 2px"><div class="menu_separtor" id="menu_separtor_Search"></div></li>
				 	<li id="Suppliers"><a href="#" title="Suppliers" >Suppliers</a></li>
					<li style="width: 2px"><div class="menu_separtor" id="menu_separtor_Suppliers"></div></li>	
					<li id="Buyers"><a  title="Buyers" >Buyers</a></li>
					<li style="width: 2px"><div class="menu_separtor" id="menu_separtor_Buyers"></div></li>
					<li id="Transactions"><a  title="Transactions" >Transactions</a></li>
					<li style="width: 2px"><div class="menu_separtor" id="menu_separtor_Transactions"></div></li>
				    <li id="Network"><a  title="Network" >Network</a></li>
			</ul>
			<ul class="MenuType2 HideMenu ">
					<li id="Corporate"><a  title="Corporate" >Corporate</a></li>
					<li style="width: 2px"><div class="menu_separtor"></div></li>
					<li id="Search"><a  title="Search" >Search</a></li>
					<li style="width: 2px"><div class="menu_separtor"></div></li>
					<li id="Suppliers"><a  title="Suppliers" >Suppliers / Buyer</a></li>
					<li style="width: 2px"><div class="menu_separtor"></div></li>
					<li id="Transactions"><a  title="Transactions" >Transactions</a></li>
					<li style="width: 2px"><div class="menu_separtor"></div></li>
				    <li id="Network"><a  title="Network" >Network</a></li>
			</ul>
			<ul class="MenuType2 HideMenu">
					<li id="Corporate"><a  title="Corporate" >Corporate</a></li>
					<li style="width: 2px"><div class="menu_separtor"></div></li>
					<li id="Network"><a  title="Network" >Network</a></li> 
					
			</ul>
		</div>
		
		<!-- Sub Menu  -->
		<div class="nav_Sub Gen_nav">
			<ul id="SubAdmin" >
					<li id="SubAdmin_User"><a  title="User" >Users</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubAdmin_User"></div></li>
					<li id="SubAdmin_Group"><a  title="Group" >Groups</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubAdmin_Group"></div></li>
					<li id="SubAdmin_Departments"><a  title="Departments" >Departments</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubAdmin_Departments"></div></li>
					<li id="SubAdmin_Manage_Folders"><a  title="Manage Folders" >Manage Folders</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubAdmin_Manage_Folders"></div></li>
					<li id="SubAdmin_Account_Policies"><a  title="Account_Policies" >Account Policies</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubAdmin_Account_Policies"></div></li>
					<li id="SubAdmin_Company_Profile">Company Profile</li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubAdmin_Company_Profile"></div></li>
					<li id="SubAdmin_Company_Website"><a title="Company Website" >Company Website</a></li>
					
			</ul>
			<ul id="SubCorporate">
					<li id="SubCorporate_User_Page"><a  title="User Page" >User Page</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubCorporate_User_Page"></div></li>
					<li id="SubCorporate_Internal_Page"><a  title="Internal Page" >Internal Page</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubCorporate_Internal_Page"></div></li>
					<li id="SubCorporate_Department_Page"><a  title="Department Page" >Department Page</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubCorporate_Department_Page"></div></li>
					<li id="SubCorporate_Product_Catalog"><a  title="Product Catalog" >Product Catalog</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubCorporate_product_catalog"></div></li>
					<li id="SubCorporate_Ad"><a  title="Ad" >Advertisement</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubCorporate_ad"></div></li>
					

			</ul>
			<ul id="SubSearch">
					<li id="SubSearch_Search_Supplier"><a  title="Supplier" >Supplier</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubSearch_Search_Supplier"></div></li>
					<li id="SubSearch_Search_Buyer"><a  title="Buyer" >Buyer</a></li>
					

			</ul>
			<ul id="SubBuyers">
					<li id="SubBuyers_Vendor_Registration"><a  title="Vendor Registration" >Vendor Registration</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubBuyers_Vendor_Registration"></div></li>
			</ul>
			
			<ul id="SubSuppliers">
					<li id="SubSuppliers_Vendor_Registration"><a  title="Vendor Registration" >Vendor Registration</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubSuppliers_Vendor_Registration"></div></li>
			</ul>
			
			<ul  class="Sub_display" id="SubTransactions">
					<li id="SubTransactions_Transaction"><a  title="Transaction" >Transaction</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor"  id="Sub_menu_seperator_SubTransactions_Transaction"></div></li>
					<li id="SubTransactions_History"><a  title="History" >History</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor"  id="Sub_menu_seperator_SubTransactions_Histroy"></div></li>
					<li id="SubTransactions_TC"><a  title="History" >Terms & Conditions</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor"  id="Sub_menu_seperator_SubTransactions_TC"></div></li>
					

			</ul>
			<ul id="SubNetwork">
					<li id="SubNetwork_News_Feed"><a  title="News Feed" >News Feed</a></li>
					<li  style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubNetwork_News_Feed"></div></li>
					<li id="SubNetwork_Messages"><a  title="Messages" >Messages</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubNetwork_Messages"></div></li>
					<li id="SubNetwork_Notification"><a  title="Departments" >Notification</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubNetwork_Notification"></div></li>
					<li id="SubNetwork_Dashboard"><a title="Dashboard" >Dashboard</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubNetwork_Dashboard"></div></li>
					<li id="SubNetwork_Ratings"><a  title="Ratings" >Ratings</a></li>
					<li style="width: 2px"><div class="Sub_menu_separtor" id="Sub_menu_seperator_SubNetwork_Ratings"></div></li>
					<li id="SubNetwork_My_Connections"><a  title="My Connections" >My Connections</a></li>
					
			</ul>
		</div>
	</div>
	</div>
</body>
</html>
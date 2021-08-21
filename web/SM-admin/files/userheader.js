$(document).ready(function() {
  
	$( "#news_link" ).die( "click" );
	$("#news_link").live( "click", function(){
		
		removeMenuSelection();
		
		$("#mainpage").empty();
		
		$("#content_loader").show();
		
		addSubMenu( "Network" );
		
		$("#SubNetwork_News_Feed").addClass("navsubSelected");		
		
		$("#mainpage").load("Views/NewsRoom/jsp/newsroom.jsp", function() 
				{
			//$("#content_loader").hide();
		});
		
	});
	
	$( "#dashboard_link" ).die( "click" );
	$("#dashboard_link").live( "click", function(){
		
		removeMenuSelection();
		
		$("#mainpage").empty();
		
		$("#content_loader").show();
		
		addSubMenu( "Network" );
		
		$("#SubNetwork_Dashboard").addClass("navsubSelected");		
		
		$("#mainpage").load("Views/Dashboard/jsp/dashboard.jsp", function() 
				{
			//$("#content_loader").hide();
		});
		
	});
	$("#noti_dropdown_content").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	$(".acc_menu").click(function(e){$("#acc_menu_content").toggle(); e.stopPropagation();});
	
	LoadExternalPageLink();
	
	$("#notification").click(function(e){$("#notification_container").toggle(); e.stopPropagation();$("#noti_dropdown_content").mCustomScrollbar("update");if(document.getElementById('notification_container').style.display!=="none"){clear_notification();}});
	
	$("#notification_count").click(function(e){$("#notification_container").toggle(); e.stopPropagation();if(document.getElementById('notification_container').style.display!=="none"){clear_notification();}});
	
	$("#see_more").click( showNotificationTab );
	
	getNotification();
	
	companyLogoUrl=	$("#company_logo_url").val();
	companyLabel=	$("#compName").val();
        //alert(companyLogoUrl+"   "+companyLabel);
	if( companyLogoUrl != "" && companyLogoUrl != 'null')
	 {
		$("#comp_logo").show();
		$("#supplymedium_logo").hide();
		
		$("#company_label").append(companyLabel);
		$("#company_logo").attr("src", companyLogoUrl+"?timestamp=" + new Date().getTime()).load(function() 
				 
				    {
				    	 pic_real_width = this.width;  
					        
					        pic_real_height = this.height; 
					        
					        width = 90;
					        height = 90;
					        
							
							 if (width > pic_real_width) { width = pic_real_width; }
								if (height > pic_real_height) { height = pic_real_height; }
					   
							//pick the smaller ratio of width or height, and resize to that
							var ratio = width / pic_real_width;
							if(height / pic_real_height < ratio)
								ratio = height / pic_real_height;
					   
							//resize the image to the new dimensions (rounded to 2 places)
							img.width = Math.round(pic_real_width * ratio * 100)/100;
							img.height = Math.round(pic_real_height * ratio * 100)/100;
				    	 //createThumbnail($('#header_userimage'),20,20 );
							    				
					});
		
	 }
	  else
	  {
		  $("#comp_logo").hide();
		  $("#supplymedium_logo").show();
	  }
	
	
	
});

function addSubMenu( mainMenuId )
{
	// Change the Main Menu Selected Item to highlight 
	$("#"+mainMenuId).addClass("navmainSelected");
	
	// Based on the Main Menu Select the Sub Menu 
	
	var SelectedID="#Sub"+mainMenuId;	
	
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
	
}


$('body').click(function()
{
	$("#acc_menu_content").hide();
//	$("#show_invite_btn" ).show();
	$("#notification_container").hide();
//	$("#show_invite_btn").stop().animate({'border':'1px solid black','box-shadow':'0px 0px 0px #888888'});
});


function showUserAccSettings()
{
	$("#mainpage").load("Views/UserAcctMgmt/jsp/useracctmgmt.jsp");
	
	// Unselect the already selected menu
	var item=$(".nav_Main .navmainSelected");
	
	for(var i=0;i<item.length;i++) 
	{
		$(item[i]).removeClass("navmainSelected");						
	}
	
	// hide the sub menus
	var item=$(".nav_Sub ul");
	
	for(var i=0;i<item.length;i++)
	{
		$(item[i]).hide();			
	}
}



function LoadExternalPageLink()
{
	regnKey = $("#regnkey").val();
	
	
	
	$.ajax(
	{
		type : 'POST',
		url : getBaseURL() + '/externalpageservlet',
		data :
		{
			'Action' : 'GetExternalPageLink',
			'cmpRegnKey' : regnKey
		},
		cache : false,
		success : function(ResultObj)
		{

			if (ResultObj.result == "success")
			{
				
				$("#website_link").attr("href", ResultObj.websiteurl);
			}

		},
		error : function()
		{

			ExternalPageID = 0;

		}
	});
}




function userAcctMgmtOnload()
{
	$("#content_loader").hide();

	fillAllTimeSelectBox();
	getPasswordPolicy();
	getUserProfile();
	getNotificationSettings();
	getWorkingHours();
	notifyValidator();

	$('select.selectbox').customSelect();

	$("#ui-datepicker-div").addClass("calender");

	$("#stopnotify_todatepickerid").addClass("calender");

	$("#stopnotify_fromdatepickerid").datepicker({
		dateFormat : "dd-M-yy"
	});
	$("#stopnotify_todatepickerid").datepicker({
		dateFormat : "dd-M-yy"
	});

	$("#stopnotify_fromdatepickerid").datepicker();
	$("#stopnotify_todatepickerid").datepicker();

	$("#passcancelbtnid").click(function() {

		resetForm("useracct_resetpass");
		cancelBtnClick();
	});

	$("#passsavebtnid").click(

	resetPassword);

	$("#notify_cancelbtnid").click(function() {

		cancelBtnClick();
	});

	$("#notify_savebtnid").click(saveNotificationSettings);

	$("#wh_cancelbtnid").click(function() {

		cancelBtnClick();
	});

	$("#wh_savebtnid").click(

	saveWorkinghoursSettings);

	notifyChkBoxClick();

	whChkBoxClick();

	$("#uploadpicheadid").click(uploadPicHeadClicked);
	$("#passwordheadid").click(passwordHeadClicked);
	$("#notifyheadid").click(notifyHeadClicked);
	$("#workinghoursheadid").click(whHeadClicked);

	$("#profilepicid").change(function() {
		readURLImage(this, "#profile_thumbnail");
	});
}

function cancelBtnClick()
{
	if ($('#useracct_profilefrm').is(':visible'))
	{
		$("#useracct_profilefrm").slideUp();
		
		$("#profile_arrow").removeClass('down_arrow');
		$("#profile_arrow").addClass('right_arrow');
	}
	if ($('#useracct_resetpass').is(':visible'))
	{
		$("#useracct_resetpass").slideUp();
		
		$("#password_arrow").removeClass('down_arrow');
		$("#password_arrow").addClass('right_arrow'); 
	}
	if ($('#useracct_notifyfrm').is(':visible'))
	{
		$("#useracct_notifyfrm").slideUp();
		
		$("#notify_arrow").removeClass('down_arrow');
		$("#notify_arrow").addClass('right_arrow'); 
	}	
	if ($('#useracct_whfrm').is(':visible'))
	{
		$("#useracct_whfrm").slideUp();
		
		$("#wh_arrow").removeClass('down_arrow');
		$("#wh_arrow").addClass('right_arrow'); 
	}	 
}

function whChkBoxClick()
{
	//sundaychkid sundayfromtimeid sundaytotimeid
	$('#sundaychkid').click(function() {
		
		if ($('#sundaychkid').is(':checked'))
		{
			$('#sundayfromtimeid').prop('disabled', false);
			$('#sundaytotimeid').prop('disabled', false);
		}
		else
		{
			$('#sundayfromtimeid').prop('disabled', 'disabled');
			$('#sundaytotimeid').prop('disabled', 'disabled');
		}
		
	});
	
	$('#mondaychkid').click(function() {
		
		if ($('#mondaychkid').is(':checked'))
		{
			$('#mondayfromtimeid').prop('disabled', false);
			$('#mondaytotimeid').prop('disabled', false);
		}
		else
		{
			$('#mondayfromtimeid').prop('disabled', 'disabled');
			$('#mondaytotimeid').prop('disabled', 'disabled');
		}
		
	});
	
	$('#tuesdaychkid').click(function() {
		
		if ($('#tuesdaychkid').is(':checked'))
		{
			$('#tuesdayfromtimeid').prop('disabled', false);
			$('#tuesdaytotimeid').prop('disabled', false);
		}
		else
		{
			$('#tuesdayfromtimeid').prop('disabled', 'disabled');
			$('#tuesdaytotimeid').prop('disabled', 'disabled');
		}
		
	});
	
	$('#wednesdaychkid').click(function() {
		
		if ($('#wednesdaychkid').is(':checked'))
		{
			$('#wednesdayfromtimeid').prop('disabled', false);
			$('#wednesdaytotimeid').prop('disabled', false);
		}
		else
		{
			$('#wednesdayfromtimeid').prop('disabled', 'disabled');
			$('#wednesdaytotimeid').prop('disabled', 'disabled');
		}
		
	});
	
	$('#thursdaychkid').click(function() {
		
		if ($('#thursdaychkid').is(':checked'))
		{
			$('#thursdayfromtimeid').prop('disabled', false);
			$('#thursdaytotimeid').prop('disabled', false);
		}
		else
		{
			$('#thursdayfromtimeid').prop('disabled', 'disabled');
			$('#thursdaytotimeid').prop('disabled', 'disabled');
		}
		
	});
	
	$('#fridaychkid').click(function() {
		
		if ($('#fridaychkid').is(':checked'))
		{
			$('#fridayfromtimeid').prop('disabled', false);
			$('#fridaytotimeid').prop('disabled', false);
		}
		else
		{
			$('#fridayfromtimeid').prop('disabled', 'disabled');
			$('#fridaytotimeid').prop('disabled', 'disabled');
		}
		
	});
	
	$('#saturdaychkid').click(function() {
		
		if ($('#saturdaychkid').is(':checked'))
		{
			$('#saturdayfromtimeid').prop('disabled', false);
			$('#saturdaytotimeid').prop('disabled', false);
		}
		else
		{
			$('#saturdayfromtimeid').prop('disabled', 'disabled');
			$('#saturdaytotimeid').prop('disabled', 'disabled');
		}
		
	});
	
}

function notifyChkBoxClick()
{
	$('#altemailchkid').click(function() {
		
		if ($('#altemailchkid').is(':checked'))
		{
			$('#altemailtxtid').val( $('#EmailAddress').val() );
			$('#altemailtxtid').prop('disabled', 'disabled');
		}
		else
		{
			$('#altemailtxtid').val("");
			$('#altemailtxtid').prop('disabled', false); 
		}
		
	});
	
	$('#whchkid').click(function() {
		
		if ($('#whchkid').is(':checked'))
		{
			$('#whselectid').prop('disabled', false);
		}
		else
		{
			$('#whselectid').prop('disabled', 'disabled');
		}
		
	});
	
	$('#outsidewhchkid').click(function() {
		
		if ($('#outsidewhchkid').is(':checked'))
		{
			$('#outsidewhselectid').prop('disabled', false);
		}
		else
		{
			$('#outsidewhselectid').prop('disabled', 'disabled');
		}
		
	});
	
	$('#stopnotifychkid').click(function() {
		
		if ($('#stopnotifychkid').is(':checked'))
		{
			$('#stopnotify_fromdatepickerid').prop('disabled', false);
			$('#stopnotify_todatepickerid').prop('disabled', false);
		}
		else
		{
			$('#stopnotify_fromdatepickerid').prop('disabled', 'disabled');
			$('#stopnotify_todatepickerid').prop('disabled', 'disabled');
		}
		
	});
}

function uploadPicHeadClicked()
{
	if($("#useracct_profilefrm").css('display') == 'none')
		{
			$("#useracct_profilefrm").slideDown();
			$("#useracct_resetpass").slideUp();
			$("#useracct_notifyfrm").slideUp();
			$("#useracct_whfrm").slideUp();
			
			$("#profile_arrow").removeClass('right_arrow');
			$("#profile_arrow").addClass('down_arrow');
			
			$("#password_arrow").removeClass('down_arrow');
			$("#password_arrow").addClass('right_arrow'); 
			
			$("#notify_arrow").removeClass('down_arrow');
			$("#notify_arrow").addClass('right_arrow');
			
			$("#wh_arrow").removeClass('down_arrow');
			$("#wh_arrow").addClass('right_arrow'); 
		}
}

function passwordHeadClicked()
{
	if($("#useracct_resetpass").css('display') == 'none')
	{
		$("#useracct_profilefrm").slideUp();
		$("#useracct_resetpass").slideDown();
		$("#useracct_notifyfrm").slideUp();
		$("#useracct_whfrm").slideUp();
		
		$("#profile_arrow").removeClass('down_arrow');
		$("#profile_arrow").addClass('right_arrow');
		
		$("#password_arrow").removeClass('right_arrow');
		$("#password_arrow").addClass('down_arrow'); 
		
		$("#notify_arrow").removeClass('down_arrow');
		$("#notify_arrow").addClass('right_arrow');
		
		$("#wh_arrow").removeClass('down_arrow');
		$("#wh_arrow").addClass('right_arrow'); 
	}
}

function notifyHeadClicked()
{
	if($("#useracct_notifyfrm").css('display') == 'none')
	{
		$("#useracct_profilefrm").slideUp();
		$("#useracct_resetpass").slideUp();
		$("#useracct_notifyfrm").slideDown();
		$("#useracct_whfrm").slideUp();
		
		$("#profile_arrow").removeClass('down_arrow');
		$("#profile_arrow").addClass('right_arrow');
		
		$("#password_arrow").removeClass('down_arrow');
		$("#password_arrow").addClass('right_arrow'); 
		
		$("#notify_arrow").removeClass('right_arrow');
		$("#notify_arrow").addClass('down_arrow');
		
		$("#wh_arrow").removeClass('down_arrow');
		$("#wh_arrow").addClass('right_arrow'); 
	}
}

function whHeadClicked()
{
	if($("#useracct_whfrm").css('display') == 'none')
	{
		$("#useracct_profilefrm").slideUp();
		$("#useracct_resetpass").slideUp();
		$("#useracct_notifyfrm").slideUp();
		$("#useracct_whfrm").slideDown();
		
		$("#profile_arrow").removeClass('down_arrow');
		$("#profile_arrow").addClass('right_arrow');
		
		$("#password_arrow").removeClass('down_arrow');
		$("#password_arrow").addClass('right_arrow'); 
		
		$("#notify_arrow").removeClass('down_arrow');
		$("#notify_arrow").addClass('right_arrow');
		
		$("#wh_arrow").removeClass('right_arrow');
		$("#wh_arrow").addClass('down_arrow'); 
	}
}

/*
 * To get user profile for given user key to 
 * load the details in onload
 */

function getUserProfile( )
{
	var userKey = $("#EmailAddress").val();
	showAjaxLoader();
	
	var img;
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserAcctMgmtServlet",
		   data:{ 
			    'RequestType':'GetProfilePicture',
		        'UserKey':userKey,
		    } ,
		   cache:false,
		   success: function( profileData ) 
		   {
			   hideAjaxLoader();
			
			  if(profileData.result == "success")
			  {		
				 
				    img = ("#profilepicture")[0]; // Get my img elem
					
					var pic_real_width, pic_real_height,width,height;
					
					if (typeof profileData.ProfilePicPath === "undefined" || profileData.ProfilePicPath == "" ) 
					{
					   return;
					}
					
					 $("#user_image_url").val(profileData.ProfilePicPath);
				 	
				    $("#profilepicture").attr("src", profileData.ProfilePicPath+"?timestamp=" + new Date().getTime()).load(function() 
				    {
				    	    pic_real_width = this.width;  
					        
					        pic_real_height = this.height; 
					        
					        width = 140;
					        height = 150;
					        
							
							 if (width > pic_real_width) { width = pic_real_width; }
								if (height > pic_real_height) { height = pic_real_height; }
					   
							//pick the smaller ratio of width or height, and resize to that
							var ratio = width / pic_real_width;
							if(height / pic_real_height < ratio)
								ratio = height / pic_real_height;
					   
							//resize the image to the new dimensions (rounded to 2 places)
							img.width = Math.round(pic_real_width * ratio * 100)/100;
							img.height = Math.round(pic_real_height * ratio * 100)/100;
							//createThumbnail( $("#profilepicture"),116,126 );
					    				
				     });
				    
				    img = $("#header_userimage")[0]; // Get my img elem
				    
				    $("#header_userimage").attr("src", profileData.ProfilePicPath+"?timestamp=" + new Date().getTime()).load(function() 
				    {
				    	 pic_real_width = this.width;  
					        
					        pic_real_height = this.height; 
					        
					        width = 20;
					        height = 20;
					        
							
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
				  $('#profilepic_err').text(profileData.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	 hideAjaxLoader();
		    	  $("#profilepic_err").text("Unexcepted error occur. Please try again.");
		    	  
			}
		 });
}

/*
 * To get user notification settings for given
 * user key to load the details in onload
 */

function getNotificationSettings( )
{
	var userKey = $("#EmailAddress").val();
	
	showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserAcctMgmtServlet",
		   data:{ 
			    'RequestType':'GetNotificationSettings',
		        'UserKey':userKey,
		    } ,
		   cache:false,
		   success: function( notifyDataJson ) 
		   {
			  hideAjaxLoader();
			 
			  if(notifyDataJson.result == "success")
			  {
					
					$('#altemailtxtid').val(notifyDataJson.NotifyEmail);
					
					if( notifyDataJson.NotifyMobile != "null")
					
						$('#altmobiletxtid').val(notifyDataJson.NotifyMobile);
					
					if( notifyDataJson.WorkingHoursFlag == 0 )
					{
						$('#whchkid').attr('checked', false);
						$('#whselectid').val(notifyDataJson.NotifyWhMode);
						$('#whselectid').prop('disabled', 'disabled');
						
						$("#whselectid").trigger('update');
					}
					else
					{
						$('#whchkid').attr('checked', true);
						$('#whselectid').val(notifyDataJson.NotifyWhMode);
						$('#whselectid').prop('disabled', false);
						
						$("#whselectid").trigger('update');
					}
					
					if( notifyDataJson.NonWorkingHoursFlag == 0 )
					{
						$('#outsidewhchkid').attr('checked', false);
						$('#outsidewhselectid').val(notifyDataJson.NotifyNonWhMode);
						$('#outsidewhselectid').prop('disabled', 'disabled');
						
						$("#outsidewhselectid").trigger('update');
					}
					else
					{
						$('#outsidewhchkid').attr('checked', true);
						$('#outsidewhselectid').val(notifyDataJson.NotifyNonWhMode);
						$('#outsidewhselectid').prop('disabled', false);
						
						$("#outsidewhselectid").trigger('update');
					}
					
					if( notifyDataJson.NotifyStopFlag == 0 )
					{
						$('#stopnotifychkid').attr('checked', false);
						$('#stopnotify_fromdatepickerid').val(notifyDataJson.NotifyStopFromTime);
						$('#stopnotify_todatepickerid').val(notifyDataJson.NotifyStopToTime);
						$('#stopnotify_fromdatepickerid').prop('disabled', 'disabled');
						$('#stopnotify_todatepickerid').prop('disabled', 'disabled');
					}
					else
					{
						$('#stopnotifychkid').attr('checked', true);
						$('#stopnotify_fromdatepickerid').val(notifyDataJson.NotifyStopFromTime);
						$('#stopnotify_todatepickerid').val(notifyDataJson.NotifyStopToTime);
						$('#stopnotify_fromdatepickerid').prop('disabled', false);
						$('#stopnotify_todatepickerid').prop('disabled', false);
					}
					
			  }
			  
			 else
			 {
				  $("#notify_err").text(notifyDataJson.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	  $("#notify_err").text("Unexcepted error occur. Please try again.");
		    	  
		    	  hideAjaxLoader();
			}
		 });
}

/*To get working hours table for given
 * user key to working hours details
 * in onload
 */

function getWorkingHours( )
{
	var userKey = $("#EmailAddress").val();
	
	showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserAcctMgmtServlet",
		   data:{ 
			    'RequestType':'GetWorkingHours',
		        'UserKey':userKey,
		    } ,
		   cache:false,
		   success: function( whData ) 
		   {
			  hideAjaxLoader();
			 
			  if(whData.result == "success")
			  {
					if( whData.SunWorkingFlag == 0 )
					{
						$('#sundaychkid').attr('checked', false);
						$('#sundayfromtimeid').val(whData.SunFromTime);
						$('#sundaytotimeid').val(whData.SunToTime);
						$('#sundayfromtimeid').prop('disabled', 'disabled');
						$('#sundayTotimeid').prop('disabled', 'disabled');
						
						$("#sundayfromtimeid").trigger('update');
						$("#sundaytotimeid").trigger('update');
					}
					else
					{
						$('#sundaychkid').attr('checked', true);
						$('#sundayfromtimeid').val(whData.SunFromTime);
						$('#sundaytotimeid').val(whData.SunToTime);
						$('#sundayfromtimeid').prop('disabled', false);
						$('#sundayTotimeid').prop('disabled', false);
						
						$("#sundayfromtimeid").trigger('update');
						$("#sundaytotimeid").trigger('update');
					}
					
					if( whData.MonWorkingFlag == 0 )
					{
						$('#mondaychkid').attr('checked', false);
						$('#mondayfromtimeid').val(whData.MonFromTime);
						$('#mondaytotimeid').val(whData.MonToTime);
						$('#mondayfromtimeid').prop('disabled', 'disabled');
						$('#mondayTotimeid').prop('disabled', 'disabled');
						
						$("#mondayfromtimeid").trigger('update');
						$("#mondaytotimeid").trigger('update');
					}
					else
					{
						$('#mondaychkid').attr('checked', true);
						$('#mondayfromtimeid').val(whData.MonFromTime);
						$('#mondaytotimeid').val(whData.MonToTime);
						$('#mondayfromtimeid').prop('disabled', false);
						$('#mondayTotimeid').prop('disabled', false);
						
						$("#mondayfromtimeid").trigger('update');
						$("#mondaytotimeid").trigger('update');
					}
					
					if( whData.TueWorkingFlag == 0 )
					{
						$('#tuesdaychkid').attr('checked', false);
						$('#tuesdayfromtimeid').val(whData.TueFromTime);
						$('#tuesdaytotimeid').val(whData.TueToTime);
						$('#tuesdayfromtimeid').prop('disabled', 'disabled');
						$('#tuesdayTotimeid').prop('disabled', 'disabled');
						
						$("#tuesdayfromtimeid").trigger('update');
						$("#tuesdaytotimeid").trigger('update');
					}
					else
					{
						$('#tuesdaychkid').attr('checked', true);
						$('#tuesdayfromtimeid').val(whData.TueFromTime);
						$('#tuesdaytotimeid').val(whData.TueToTime);
						$('#tuesdayfromtimeid').prop('disabled', false);
						$('#tuesdayTotimeid').prop('disabled', false);
						
						$("#tuesdayfromtimeid").trigger('update');
						$("#tuesdaytotimeid").trigger('update');
					}
					
					if( whData.WedWorkingFlag == 0 )
					{
						$('#wednesdaychkid').attr('checked', false);
						$('#wednesdayfromtimeid').val(whData.WedFromTime);
						$('#wednesdaytotimeid').val(whData.WedToTime);
						$('#wednesdayfromtimeid').prop('disabled', 'disabled');
						$('#wednesdayTotimeid').prop('disabled', 'disabled');
						
						$("#wednesdayfromtimeid").trigger('update');
						$("#wednesdaytotimeid").trigger('update');
					}
					else
					{
						$('#wednesdaychkid').attr('checked', true);
						$('#wednesdayfromtimeid').val(whData.WedFromTime);
						$('#wednesdaytotimeid').val(whData.WedToTime);
						$('#wednesdayfromtimeid').prop('disabled', false);
						$('#wednesdayTotimeid').prop('disabled', false);
						
						$("#wednesdayfromtimeid").trigger('update');
						$("#wednesdaytotimeid").trigger('update');
					}
					
					if( whData.ThuWorkingFlag == 0 )
					{
						$('#thursdaychkid').attr('checked', false);
						$('#thursdayfromtimeid').val(whData.ThuFromTime);
						$('#thursdaytotimeid').val(whData.ThuToTime);
						$('#thursdayfromtimeid').prop('disabled', 'disabled');
						$('#thursdayTotimeid').prop('disabled', 'disabled');
						
						$("#thursdayfromtimeid").trigger('update');
						$("#thursdaytotimeid").trigger('update');
					}
					else
					{
						$('#thursdaychkid').attr('checked', true);
						$('#thursdayfromtimeid').val(whData.ThuFromTime);
						$('#thursdaytotimeid').val(whData.ThuToTime);
						$('#thursdayfromtimeid').prop('disabled', false);
						$('#thursdayTotimeid').prop('disabled', false);
						
						$("#thursdayfromtimeid").trigger('update');
						$("#thursdaytotimeid").trigger('update');
					}
					
					if( whData.FriWorkingFlag == 0 )
					{
						$('#fridaychkid').attr('checked', false);
						$('#fridayfromtimeid').val(whData.FriFromTime);
						$('#fridaytotimeid').val(whData.FriToTime);
						$('#fridayfromtimeid').prop('disabled', 'disabled');
						$('#fridayTotimeid').prop('disabled', 'disabled');
						
						$("#fridayfromtimeid").trigger('update');
						$("#fridaytotimeid").trigger('update');
					}
					else
					{
						$('#fridaychkid').attr('checked', true);
						$('#fridayfromtimeid').val(whData.FriFromTime);
						$('#fridaytotimeid').val(whData.FriToTime);
						$('#fridayfromtimeid').prop('disabled', false);
						$('#fridayTotimeid').prop('disabled', false);
						
						$("#fridayfromtimeid").trigger('update');
						$("#fridaytotimeid").trigger('update');
					}
					
					if( whData.SatWorkingFlag == 0 )
					{
						$('#saturdaychkid').attr('checked', false);
						$('#saturdayfromtimeid').val(whData.SatFromTime);
						$('#saturdaytotimeid').val(whData.SatToTime);
						$('#saturdayfromtimeid').prop('disabled', 'disabled');
						$('#saturdayTotimeid').prop('disabled', 'disabled');
						
						$("#saturdayfromtimeid").trigger('update');
						$("#saturdaytotimeid").trigger('update');
					}
					else
					{
						$('#saturdaychkid').attr('checked', true);
						$('#saturdayfromtimeid').val(whData.SatFromTime);
						$('#saturdaytotimeid').val(whData.SatToTime);
						$('#saturdayfromtimeid').prop('disabled', false);
						$('#saturdayTotimeid').prop('disabled', false);
						
						$("#saturdayfromtimeid").trigger('update');
						$("#saturdaytotimeid").trigger('update');
					}
			  }
			  
			 else
			 {
				  $("#wh_err").text(whData.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	  $("#wh_err").text("Unexcepted error occur. Please try again.");
		    	  
		    	  hideAjaxLoader();
			}
		 });
}

/*
 * To save updated user profile details
 */

function saveProfile( )
{
	
	var userKey = $("#EmailAddress").val();
	
	var regnKey = $("#regnkey").val();
	
	showAjaxLoader();
	
	var form = $("#useracct_profilefrm");

	var data = new FormData();
	
	data.append('RequestType', 'UpdateProfilePic');
	
	data.append('UserKey', userKey);
	
	data.append('RegnKey', regnKey);

	data.append('ProfilePicPath', document.useracct_profilefrm.profilepictxt.files[0]);
	
	$.ajax({
		   type: "POST",
		   url: form.attr('action'),
		   data:data,
		   contentType : false,
			processData : false,
		   success: function( profileData ) 
		   {
			   hideAjaxLoader();
			 
			  if(profileData.result == "success")
			  {
				  getUserProfile();
				  
				  ShowToast( profileData.message,2000 );
				  
			  }
			  
			  else
			 {
				 // alert("failed");
				  $("#profilepic_err").text(profileData.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	 $("#profilepic_err").text("Unexcepted error occur. Please try again.");
		    	 
		    	 hideAjaxLoader();
			}
		 });
}



function readURLImage(input,imageDivID)
{
	var ext = input.files[0].name.split('.').pop().toLowerCase();
	
	var uploadErrDiv = $("#profilepic_err");
	
	if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) 
	{
	    
		uploadErrDiv.text("invalid file formate!"); 
		input.files[0]="";
		
	    return ;
	}
	
    if(input.files[0].size > 1000141)
	 {
	    	uploadErrDiv.text("File size more than 1MB");
	    	input.files[0]="";
	    	return ;
	 }
	
	
	if (input.files && input.files[0])
	{
		var reader = new FileReader();

		reader.onload = function(e)
		{
			$(imageDivID).attr('src', e.target.result).load(function() 
			{
		        var pic_real_width = this.width;   // Note: $(this).width() will not
		        var pic_real_height = this.height; // work for in memory images.
		        if(pic_real_width > 500 || pic_real_height > 500 )
		        {
		     	   uploadErrDiv.text("Image dimension should be lesser than or equal to 500x500");
		     	   
		     	   return ;
		        }
		        else
		        {
		        	saveProfile( );
		        	uploadErrDiv.text("");
		        }
		        
		    });;
		};

		reader.readAsDataURL(input.files[0]);
	}
}

/*
 * To save updated user notification settings
 */

function saveNotificationSettings( )
{
	if( $('#useracct_notifyfrm').valid() == false )
		return;
	
	var userKey = $("#EmailAddress").val();
	
	var altEmail = $('#altemailtxtid').val();
	var altMobile = $('#altmobiletxtid').val();
	
	var whChkFlag = 0;
	var outWhChkFlag = 0;
	var stopNotifyFlag = 0;
	
	var whMode;
	var outWhMode;
	var stopNotifyFrom;
	var stopNotifyTo;
	
	if ( $('#whchkid').is(':checked') )
	{
		whChkFlag = 1;
		whMode = $('#whselectid').val();
	}
	else
	{
		whChkFlag = 0;
		whMode = $('#whselectid').val();
	}
	
	if( $('#outsidewhchkid').is(':checked') )
	{
		outWhChkFlag = 1;
		outWhMode = $('#outsidewhselectid').val();
	}
	else
	{
		outWhChkFlag = 0;
		outWhMode = $('#outsidewhselectid').val();
	}	
	
	if( $('#stopnotifychkid').is(':checked') )
	{
		stopNotifyFlag = 1;
		stopNotifyFrom = $('#stopnotify_fromdatepickerid').val();
		stopNotifyTo = $('#stopnotify_todatepickerid').val();
	}
	else
	{
		stopNotifyFlag = 0;
		stopNotifyFrom = $('#stopnotify_fromdatepickerid').val();
		stopNotifyTo = $('#stopnotify_todatepickerid').val();
	}
	
	//alert(outWhMode);
	
	//alert( altEmail + ",\n" + altMobile + ",\n" + outWhMode + ",\n" + stopNotifyFlag + ",\n" + stopNotifyFrom + ",\n" + stopNotifyTo + ",\n" + whMode + ",\n" + whChkFlag + ",\n" + outWhChkFlag );
	//return;
	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserAcctMgmtServlet",
		   data:{ 
			    'RequestType':'UpdateNotificationSettings',
		        'UserKey':userKey,
		       
		        'NotifyEmail':altEmail,
		        'NotifyMobile':altMobile,
		        'NotifyNonWhMode':outWhMode,
		        'NotifyStopFlag':stopNotifyFlag,
		        'NotifyStopFromTime':stopNotifyFrom,
		        'NotifyStopToTime':stopNotifyTo,
		        'NotifyWhMode':whMode,
		        'WorkingHoursFlag':whChkFlag,
		        'NonWorkingHoursFlag':outWhChkFlag,
		    } ,
		   cache:false,
		   success: function( jsonResult ) 
		   {
			   hideAjaxLoader();
			 
			  if(jsonResult.result == "success")
			  {
				  ShowToast( jsonResult.message,2000 );
			  }
			  
			  else
			 {
				  //alert("failed");
				  $("#notify_err").text(jsonResult.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	 $("#notify_err").text("Unexcepted error occur. Please try again.");
		    	 
		    	 hideAjaxLoader();
			}
		 });
}

/*
 * To save updated user working hours settings
 */

function saveWorkinghoursSettings( )
{
	var userKey = $("#EmailAddress").val();
	
	var sunFlag = 0;
	var monFlag = 0;
	var tueFlag = 0;
	var wedFlag = 0;
	var thuFlag = 0;
	var friFlag = 0;
	var satFlag = 0;
	
	var sunFromTime =$('#sundayfromtimeid').val();
	var sunToTime =$('#sundaytotimeid').val();
	var monFromTime =$('#mondayfromtimeid').val();
	var monToTime =$('#mondaytotimeid').val();
	var tueFromTime =$('#tuesdayfromtimeid').val();
	var tueToTime =$('#tuesdaytotimeid').val();
	var wedFromTime =$('#wednesdayfromtimeid').val();
	var wedToTime =$('#wednesdaytotimeid').val();
	var thuFromTime =$('#thursdayfromtimeid').val();
	var thuToTime =$('#thursdaytotimeid').val();
	var friFromTime =$('#fridayfromtimeid').val();
	var friToTime =$('#fridaytotimeid').val();
	var satFromTime =$('#saturdayfromtimeid').val();
	var satToTime =$('#saturdaytotimeid').val();
	
	
	if ( $('#sundaychkid').is(':checked') )
	{
		sunFlag = 1;
	}
	else
	{
		sunFlag = 0;
	}
	
	if ( $('#sundaychkid').is(':checked') )
	{
		monFlag = 1;
	}
	else
	{
		monFlag = 0;
	}
	
	if ( $('#tuesdaychkid').is(':checked') )
	{
		tueFlag = 1;
	}
	else
	{
		tueFlag = 0;
	}
	
	if ( $('#wednesdaychkid').is(':checked') )
	{
		wedFlag = 1;
	}
	else
	{
		wedFlag = 0;
	}
	
	if ( $('#thursdaychkid').is(':checked') )
	{
		thuFlag = 1;
	}
	else
	{
		thuFlag = 0;
	}
	
	if ( $('#fridaychkid').is(':checked') )
	{
		friFlag = 1;
	}
	else
	{
		friFlag = 0;
	}
	
	if ( $('#saturdaychkid').is(':checked') )
	{
		satFlag = 1;
	}
	else
	{
		satFlag = 0;
	}

	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserAcctMgmtServlet",
		   data:{ 
			    'RequestType':'UpdateWorkingHours',
		        'UserKey':userKey,
		       
		        'SunWorkingFlag':sunFlag,
		        'MonWorkingFlag':monFlag,
		        'TueWorkingFlag':tueFlag,
		        'WedWorkingFlag':wedFlag,
		        'ThuWorkingFlag':thuFlag,
		        'FriWorkingFlag':friFlag,
		        'SatWorkingFlag':satFlag,
		        
		        'SunFromTime':sunFromTime,
		        'SunToTime':sunToTime,
		        'MonFromTime':monFromTime,
		        'MonToTime':monToTime,
		        'TueFromTime':tueFromTime,
		        'TueToTime':tueToTime,
		        'WedFromTime':wedFromTime,
		        'WedToTime':wedToTime,
		        'ThuFromTime':thuFromTime,
		        'ThuToTime':thuToTime,
		        'FriFromTime':friFromTime,
		        'FriToTime':friToTime,
		        'SatFromTime':satFromTime,
		        'SatToTime':satToTime,
		    } ,
		   cache:false,
		   success: function( notifydata ) 
		   {
			   hideAjaxLoader();
			 
			  if(notifydata.result == "success")
			  {
				  ShowToast( notifydata.message,2000 );
				  
				 // $("#wh_err").text("");
			  }
			  
			  else
			 {
				  //alert("failed");
				  $("#wh_err").text(notifydata.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	 $("#wh_err").text("Unexcepted error occur. Please try again.");
		    	 
		    	 hideAjaxLoader();
			}
		 });
}

/*
 * To reset updated password for given user key
 */

function resetPassword( )
{	
	if( $('#useracct_resetpass').valid() == false )
		return;

	var userKey = $("#EmailAddress").val();
	var regnKey = $("#regnkey").val();
		
	var oldPass = $('#currentpasstxtid').val();
	var newPass = $('#newpasstxtid').val();
	
	showAjaxLoader();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserAcctMgmtServlet",
		   data:
		   { 
			    'RequestType':'ResetPassword',
		        'UserKey':userKey,
		        'RegnKey':regnKey,
		        'OldPassword':oldPass,
		        'NewPassword':newPass,
		   },
		   cache:false,
		   success: function( notifydata ) 
		   {
			   hideAjaxLoader();
			 
			  if(notifydata.result == "success")
			  {
				  ShowToast( notifydata.message,2000 );
				  
				  //$("#user_acct_mgmt_err").text("");
			  }
			  
			  else
			 {
				  //alert("failed");
				  $("#pass_err").text(notifydata.message);
				  $("#pass_err").show();
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	 $("#pass_err").show();
		    	 $("#pass_err").text("Unexcepted error occur. Please try again.");
		    	 
		    	 hideAjaxLoader();
			}
		 });
}

function fillAllTimeSelectBox()
{
	fillTimeSelectBox( $('#sundayfromtimeid') );
	fillTimeSelectBox( $('#sundaytotimeid') );
	
	fillTimeSelectBox( $('#mondayfromtimeid') );
	fillTimeSelectBox( $('#mondaytotimeid') );
	
	fillTimeSelectBox( $('#tuesdayfromtimeid') );
	fillTimeSelectBox( $('#tuesdaytotimeid') );
	
	fillTimeSelectBox( $('#wednesdayfromtimeid') );
	fillTimeSelectBox( $('#wednesdaytotimeid') );
	
	fillTimeSelectBox( $('#thursdayfromtimeid') );
	fillTimeSelectBox( $('#thursdaytotimeid') );
	
	fillTimeSelectBox( $('#fridayfromtimeid') );
	fillTimeSelectBox( $('#fridaytotimeid') );
	
	fillTimeSelectBox( $('#saturdayfromtimeid') );
	fillTimeSelectBox( $('#saturdaytotimeid') );
}
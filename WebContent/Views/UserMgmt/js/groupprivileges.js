function getPrivileges( groupKey )
{
	//resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/GroupPrivilegesServlet",
		   data:{ 
			   'RequestType': 'FindGroupPrivileges',  
		       'RegnKey':regnKey,
		       'GroupKey':groupKey
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   if( data.AddUsers == "1")
				   {
					   $('#addusers').prop('checked', true);
				   }
				   else
				   {
					   $('#addusers').prop('checked', false);
				   }
				   
				   if( data.DeleteUsers == "1")
				   {
					   $('#delusers').prop('checked', true);
				   }
				   else
				   {
					   $('#delusers').prop('checked', false);
				   }
				   
				   if( data.UploadDoc == "1")
				   {
					   $('#uploaddoc').prop('checked', true);
				   }
				   else
				   {
					   $('#uploaddoc').prop('checked', false);
				   }
				  
				   if( data.DeleteDoc == "1")
				   {
					   $('#deldoc').prop('checked', true);
				   }
				   else
				   {
					   $('#deldoc').prop('checked', false);
				   }
				  
				   if( data.AddBuyers == "1")
				   {
					   $('#addbuyers').prop('checked', true);
				   }
				   else
				   {
					   $('#addbuyers').prop('checked', false);
				   }
				   
				   if( data.DeleteBuyers == "1")
				   {
					   $('#delbuyers').prop('checked', true);
				   }
				   else
				   {
					   $('#delbuyers').prop('checked', false);
				   }
				   
				   
				   if( data.AccessUserMgmt == "1")
				   {
					   $('#accessusermgmt').prop('checked', true);
				   }
				   else
				   {
					   $('#accessusermgmt').prop('checked', false);
				   }
				   
				   if( data.PostAnnouncement == "1")
				   {
					   $('#postanncemnt').prop('checked', true);
				   }
				   else
				   {
					   $('#postanncemnt').prop('checked', false);
				   }
				   
				   if( data.DeleteAnnouncement == "1")
				   {
					   $('#delanncemnt').prop('checked', true);
				   }
				   else
				   {
					   $('#delanncemnt').prop('checked', false);
				   }
				  
				   if( data.Rate == "1")
				   {
					   $('#rate').prop('checked', true);
				   }
				   else
				   {
					   $('#rate').prop('checked', false);
				   }
				  
				   if( data.CreateGroup == "1")
				   {
					   $('#creategroup').prop('checked', true);
				   }
				   else
				   {
					   $('#creategroup').prop('checked', false);
				   }
				   
				   if( data.DeleteGroup == "1")
				   {
					   $('#delgroup').prop('checked', true);
				   }
				   else
				   {
					   $('#delgroup').prop('checked', false);
				   }
				   if( data.ApplyTheme == "1")
				   {
					   $('#applythemes').prop('checked', true);
				   }
				   else
				   {
					   $('#applythemes').prop('checked', false);
				   }
				   
				   if( data.ManageFolders == "1")
				   {
					   $('#managefolder').prop('checked', true);
				   }
				   else
				   {
					   $('#managefolder').prop('checked', false);
				   }				   
				  
			   }
			   else
			   {
				   $('#groupprierr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {   
		    	hideAjaxLoader();
		    	$('#groupprierr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

function setPrivileges()
{
	
	if ($("#grouplistsel")[0].selectedIndex < 0)
       return;
    
	
	var addUsers = "0";
	var deleteUsers = "0";
	var uploadDoc = "0";
	var deleteDoc = "0";
	var addBuyers = "0";
	var deleteBuyers = "0";
	var accessUserMgmt = "0";
	var postAnnouncement = "0";
	var deleteAnnouncement = "0";
	var rate = "0";
	var createGroup = "0";
	var deleteGroup = "0";
	var applyTheme = "0";
	var manageFolder = "0";
	   
	


	if ($('#addusers').is(':checked')) 
	{
		addUsers = "1";
	}

	if ($('#delusers').is(':checked')) 
	{
		deleteUsers = "1";
	}

	if ($('#uploaddoc').is(':checked')) 
	{
		uploadDoc = "1";
	}

	if ($('#deldoc').is(':checked')) 
	{
		deleteDoc = "1";
	}

	if ($('#addbuyers').is(':checked')) 
	{
		addBuyers = "1";
	}

	if ($('#delbuyers').is(':checked')) 
	{
		deleteBuyers = "1";
	}

	if ($('#accessusermgmt').is(':checked')) 
	{
		accessUserMgmt = "1";
	}

	if ($('#postanncemnt').is(':checked')) 
	{
		postAnnouncement = "1";
	}

	if ($('#delanncemnt').is(':checked')) 
	{
		deleteAnnouncement = "1";
	}

	if ($('#rate').is(':checked')) 
	{
		rate = "1";
	}

	if ($('#creategroup').is(':checked')) 
	{
		createGroup = "1";
	}

	if ($('#delgroup').is(':checked')) 
	{
		deleteGroup = "1";
	}

	if ($('#applythemes').is(':checked')) 
	{
		applyTheme = "1";
	}


	if ($('#managefolder').is(':checked')) 
	{
		manageFolder = "1";
	}	 
	
	var groupKey = $("#grouplistsel option:selected").val();
	
	//resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/GroupPrivilegesServlet",
	   
		   data:{ 
			   'RequestType': 'UpdatePrivileges',  
		       'RegnKey':regnKey,
		       'GroupKey':groupKey,
		       'addusers':addUsers,
		       'delusers':deleteUsers,
		       'uploaddoc':uploadDoc,
		       'deldoc':deleteDoc,
		       'addbuyers':addBuyers,
		       'delbuyers':deleteBuyers,
		       'accessusermgmt':accessUserMgmt,
		       'postanncemnt':postAnnouncement,
		       'delanncemnt':deleteAnnouncement,
		       'rate':rate,
		       'creategroup':createGroup,
		       'delgroup':deleteGroup,
		       'applythemes':applyTheme,
		       'managefolder':manageFolder,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   ShowToast( data.message,2000 );
			   }
			   else
			   {
				   $('#groupprierr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {   
		    	hideAjaxLoader();                                                                                    
		    	$('#groupprierr').text( "Unexpected error occur. Please try again." );
			}
		 });
}
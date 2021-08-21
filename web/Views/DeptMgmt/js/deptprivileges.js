function getPrivileges( deptKey )
{
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/DeptPrivilegesServlet",
		   data:{ 
			   'RequestType': 'FindPrivileges',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   if( data.AddFolder == "1")
				   {
					   $('#addfolder').prop('checked', true);
				   }
				   else
				   {
					   $('#addfolder').prop('checked', false);
				   }
				   
				   if( data.DeleteFolder == "1")
				   {
					   $('#delfolder').prop('checked', true);
				   }
				   else
				   {
					   $('#delfolder').prop('checked', false);
				   }
				   
				   if( data.AddFile == "1")
				   {
					   $('#addfile').prop('checked', true);
				   }
				   else
				   {
					   $('#addfile').prop('checked', false);
				   }
				  
				   if( data.DeleteFile == "1")
				   {
					   $('#delfile').prop('checked', true);
				   }
				   else
				   {
					   $('#delfile').prop('checked', false);
				   }
				  
				   if( data.PostAnnouncement == "1")
				   {
					   $('#postAnno').prop('checked', true);
				   }
				   else
				   {
					   $('#postAnno').prop('checked', false);
				   }
				   
				   if( data.ManageFolder == "1")
				   {
					   $('#managefold').prop('checked', true);
				   }
				   else
				   {
					   $('#managefold').prop('checked', false);
				   }
				  
			   }
			   else
			   {
				   $('#deptprierr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {   
		    	hideAjaxLoader();
		    	$('#deptprierr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

function setPrivileges()
{
	
	if ($("#deptlistsel")[0].selectedIndex < 0)
	       return;
	
	
	var addFolder = "0";
	var deleteFolder = "0";
	var addFile = "0";
	var deleteFile="0";
	var postAnnouncement = "0";
	var manageFolder = "0";
	
	if ($('#addfolder').is(':checked')) 
	{
		 addFolder = "1";
	} 
	if ($('#delfolder').is(':checked')) 
	{
		deleteFolder = "1";
	} 
	if ($('#addfile').is(':checked')) 
	{
		addFile = "1";
	} 
	if ($('#delfile').is(':checked')) 
	{
		deleteFile = "1";
	} 
	if ($('#postAnno').is(':checked')) 
	{
		postAnnouncement = "1";
	} 
	if ($('#managefold').is(':checked')) 
	{
		manageFolder = "1";
	} 
	
	var deptKey = $("#deptlistsel option:selected").val();
	
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/DeptPrivilegesServlet",
		   data:{ 
			   'RequestType': 'UpdatePrivileges',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey,
		       'AddFolder':addFolder,
		       'DeleteFolder':deleteFolder,
		       'AddFile':addFile,
		       'DeleteFile':deleteFile,
		       'PostAnnouncement':postAnnouncement,
		       'ManageFolder':manageFolder
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
				   $('#deptprierr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {   hideAjaxLoader();                                                                                    
		    	$('#deptprierr').text( "Unexpected error occur. Please try again." );
			}
		 });
}
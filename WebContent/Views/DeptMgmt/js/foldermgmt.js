// Used to create new folder
function addFolder()
{
	
	
	if($("#new_folder_input").val() != "")
	{
		var folderName = $("#new_folder_input").val().trim();
		
		resetAllErrorMsg();
		
		showAjaxLoader();
		
		
		
		var regnKey = $('#regnkey').val();
		
		$.ajax({
			   type: "POST",
			   url: getBaseURL()+"/FolderMgmtServlet",
			   data:{ 
			        'RequestType': 'AddNewFolder', 
			        'RegnKey':regnKey,
			        'FolderName':folderName
			    } ,
			   cache:false,
			   success: function( data )
			   {
				   hideAjaxLoader();
				   
				   $("#add_folder_popup").hide();
				   
				   if( data.result == "success")
				   {
					   
					   ShowToast( data.message,2000 );
					   
					   var deptKey = $("#deptlistsel option:selected").val();
					   
					   getAllFolders( deptKey );
					   
				   }
				   else
				   {
					   $('#folderassignerr').text(data.message);
				   }
				   
			   },
			    error : function(xhr, textStatus, errorThrown) 
			    {
			    	hideAjaxLoader();
			    	
			    	$('#folderassignerr').text( "Unexpected error occur. Please try again." );
				}
			 });
	}
	else
	{
		$('#new_folder_input_err').text("Enter Folder Name");
	}
}


//Remove the folder
function deleteFolder()
{
	if ($("#existfoldersel")[0].selectedIndex < 0) 
	{
       alert("select any Folder");
       
       return;
    }
	
	var folderKey = $("#existfoldersel option:selected").val();
	
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
		
	$.ajax({
		type: "POST",
		url: getBaseURL()+"/FolderMgmtServlet",
		data:{ 
			    'RequestType': 'RemoveFolder', 
			    'RegnKey':regnKey,
			    'FolderKey':folderKey,
			  } ,
			cache:false,
			success: function( data )
			{
				hideAjaxLoader();
				
				if( data.result == "success")
				{
					ShowToast( data.message,2000 );
					   
					 getAllDepartments();
				}
				else
				{
					$('#folderassignerr').text(data.message);
				}
				   
			 },
			 error : function(xhr, textStatus, errorThrown) 
			 {
				 hideAjaxLoader();
				 
				 $('#folderassignerr').text( "Unexpected error occur. Please try again." );	
			 }
	 });
	
}



function showAddFolderPopup()
{
	$("#new_folder_input").val("");
	 $( "#add_folder_popup" ).show();
	 $("#new_folder_input").focus();
	 $('#new_folder_input_err').text("");
}

function hideAddFolderPopup()
{
	$( "#add_folder_popup" ).hide();
}

/*  It is used to create a folder in supply medium and
 *  assign the folder to selected department
 */

function createFolder()
{
	
	
	resetErrorDiv();
	
	var folderName = $("#new_folder_input").val();
	
	var regnKey = $("#regnkey").val();
	
	if(folderName=="")
	{
		ShowToast("Please Enter the FolderName");
		return;
	}
	
	showAjaxLoader();
	
	
	$.ajax({
		 type: "POST",
		   url: getBaseURL()+"/FolderMgmtServlet",
		   data:{ 
			   'RequestType':'DPAddNewFolder',
			   'RegnKey' 	: regnKey,
			   //'DeptKey'     : selectedDeptKey.replace("%20"," "),
			   'DeptKey'     : replaceAll("%20"," ", selectedDeptKey ),
			   'FolderName' : folderName
		    } ,
		   cache:false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			hideAddFolderPopup();

			if (data.result == "success")
			{
				deptClicked( selectedDeptKey );
				
				ShowToast( data.message,2000 );
			}

			else 
			{
				
				ShowToast( data.message,2000 );
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
			hideAddFolderPopup();
			
			setErrorMsg( "Unexpected error occur. Please try again." );
			
		}
	});
}

/*
 * It is used to delete the folder from department page.
 * Then it goes to recycle bin.
 */

function removeFolder()
{
	if(selectedFolderKey=="")
	{
			ShowToast("Select the folder for delete operation");
			return;
	}
	
	
	showAjaxLoader();
	
	resetErrorDiv();
	
	var regnKey = $("#regnkey").val();
	
	$.ajax({
		 type: "POST",
		   url: getBaseURL()+"/FolderMgmtServlet",
		   data:{ 
			   'RequestType':'DPRemoveFolderToRB',
			   'RegnKey' 	: regnKey,
			   //'DeptKey'     : selectedDeptKey.replace("%20"," "),
			   'DeptKey'     : replaceAll("%20"," ", selectedDeptKey ),
			   //'FolderKey' : selectedFolderKey.replace("%20", " "),
			   'FolderKey' : replaceAll("%20", " ", selectedFolderKey ),
		    } ,
		   cache:false,
		success : function(data) 
		{
			hideAjaxLoader();

			if (data.result == "success")
			{
				deptClicked( selectedDeptKey );
				
				ShowToast( data.message,2000 );
			}

			else 
			{
				
				ShowToast( data.message,2000 );
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
			setErrorMsg( "Unexpected error occur. Please try again." );
			
		}
	});
}

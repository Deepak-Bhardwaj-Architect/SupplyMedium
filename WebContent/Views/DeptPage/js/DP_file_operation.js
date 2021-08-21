/* This method called when user select the uplaod file 
 * from browser view. Then it is used to upload the file
 * server using ajax request.
 */

function uploadFile()
{	
	var form = $("#file_upload_frm");
	var data = new FormData();
	var regnKey = $("#regnkey").val();
	//
	var uploadfile=document.file_upload_frm.file_import_btn.files[0];
	
	 var maxfilesize=1024*1024;


	resetErrorDiv();

	data.append('RequestType','AddFiles');
	data.append('RegnKey'	, regnKey);
	//data.append('DeptKey', selectedDeptKey.replace("%20", " "));
	data.append('DeptKey', replaceAll("%20", " ", selectedDeptKey ));
	//data.append('FolderKey'	,selectedFolderKey.replace("%20", " "));
	data.append('FolderKey'	, replaceAll("%20", " ", selectedFolderKey ) );
	data.append('UploadFile', document.file_upload_frm.file_import_btn.files[0]);
	
	//

	if(uploadfile.size>maxfilesize)//1mb
	{
		setErrorMsg( "Upload file should be less than or equal to 1 MB" );
		
		return;
		
	}
	
	
	
	showAjaxLoader();
	
	$.ajax({
		type : "POST",
		url : form.attr('action'),
		data : data,
		contentType : false,
		processData : false,
		success : function(data) 
		{
			hideAjaxLoader();

			
			if (data.result == "success")
			
			{
				document.file_upload_frm.reset();
				
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

/* This method is used to remove the file from folder. Once it remove from the folder 
 * it is placed in recycle bin for 30 days after that it is removed permanently.With
 * in 30 days we can restore the file.
 */
function deleteFile()
{
	
	showAjaxLoader();
	
	resetErrorDiv();
	
	$.ajax({
		 type: "POST",
		   url: getBaseURL()+"/DeptFilesMgmtServlet",
		   data:{ 
			   'RequestType':'RemoveFiles',
			   //'FileId'     : selectedFileKey.replace("%20"," ")
			   'FileId'     : replaceAll("%20"," ", selectedFileKey )
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


/* It is used to download the selected file from folder.
 */
function downloadFile()
{
	
	resetErrorDiv();
	
	$("#selected_file_id").val(selectedFileKey);

	$('#file_download_frm').submit();
}
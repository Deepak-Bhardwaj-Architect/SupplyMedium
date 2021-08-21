/* This method is called when user click the department which is displayed
 * in left side of the department page.
 */
function deptClicked( deptKey )
{
	oTable.fnClearTable();
	
	changeDeptHighlight( deptKey );
	
	//deptKey = deptKey.replace("%20"," ");
	deptKey = replaceAll("%20"," ", deptKey );
	
	userKey = $("#EmailAddress").val();
	
	$("#folder_content").empty();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/DeptFolderFetchServlet",
		data : {
			'DeptKey' : deptKey,
			'UserKey' : userKey,
		},
		cache : false,
		success : function(data) {

			if (data.result == "success") 
			{
				var folderArr = new Array( data.folders.length );
			  	
				folderArr = data.folders;
				
				parseFolders( folderArr );
				
				setFoldersClickEvent();
				
				setFilesClickEvent();	
				
				resetFeedValues();
				
				fetchFeeds();
				
			}
			else 
			{
				$("#del_folder_btn").hide();
				
				$("#file_import_btn").hide();
				
				ShowToast( data.message,2000 );
				
				resetFeedValues();
				
				fetchFeeds();
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			setErrorMsg( "Unexpected error occur. Please try again." );
		}
	});

}

function setFoldersClickEvent()
{
	$('.folder_row').on("click", function() {
			
			$("#folder_ctrls").show();
			
			$("#file_ctrls").hide();
			
			if (selectedFolderKey ==this.id )
			{
				$(jqq(selectedFolderKey+"_content")).slideToggle();
			}
			else
			{
				$(jqq(selectedFolderKey)).removeClass("folder_highlight");
				
				$(jqq(selectedFolderKey+"_content")).slideUp();
				
				selectedFolderKey = this.id;
				
				$(jqq(selectedFolderKey)).addClass("folder_highlight");
				
				$(jqq(selectedFolderKey+"_content")).slideDown();
			}
			
			
		});
}

function setFilesClickEvent()
{
	$('.file_row').live("click", function() {
		
		$("#file_ctrls").show();
	
		$("#folder_ctrls").hide();
		
		$(jqq(selectedFileKey)).removeClass("file_highlight");
		
		selectedFileKey = this.id;
		
		$(jqq(selectedFileKey)).addClass("file_highlight");
		
		//$(jqq(selectedFileKey+"_content")).show();
	});
}

/* This method is used to parse the folder arr to folder object and
 * create the folder div and bind the folder div to view.
 */

function parseFolders( folderDataArr )
{
	var folderCount = folderDataArr.length;
	
	var liveFolderCount = 0;
	
	var isFirstFolderSelected = false;

	for( var i=0; i< folderCount; i++ )
	  {
	  		var folderData = folderDataArr[i];
	  		
	  		var fileDataArr = new Array( folderData.files.length );
		  	
	  		fileDataArr = folderData.files;
	  		
	  		var position = "";
	  		
	  		if( i == 0 )
	  		{
	  			if( folderCount == 1 )
		  		{
		  			position = "top_one";
		  		}
	  			else
	  			{
	  				position = "top";
	  			}
	  			
	  		}
	  		else if( i == folderDataArr.length -1 )
	  		{
	  			position = "bottom";
	  		}
	  		else
	  		{
	  			position = "middle";
	  		}
	  		
	  		addFolder( folderData, position,i );
			
	  		parseFiles( fileDataArr,folderData );
	  		
	  		if( folderData.recycleFlag == 0)
	  		{
	  			liveFolderCount++;
	  			
	  			if( !isFirstFolderSelected )
	  			{
	  				var folderKey = replaceAll(" ","%20", folderData.folderKey );
	  				
	  				$("#folder_ctrls").show();
	  				
	  				$("#file_ctrls").hide();
	  					
	  				selectedFolderKey = folderKey;
	  					
	  				$(jqq(selectedFolderKey)).addClass("folder_highlight");
	  					
	  				isFirstFolderSelected = true;
	  			}
	  		}
	  			
	  		
	  		
	  		
	  }
	
	
	if( liveFolderCount == 0 )
	{
		$("#del_folder_btn").hide();
		$("#file_import_btn").hide();
	}
	else
	{
		$("#del_folder_btn").show();
		$("#file_import_btn").show();
	}
	
}

/* This method is used to parse the file arr to file object and
 * create the file div and bind the file div to view.
 */

function parseFiles( fileDataArr,folderData )
{
	//var folderKey = folderData.folderKey.replace(" ","%20");
	var folderKey = replaceAll(" ","%20", folderData.folderKey );
	
	var fileDiv = '<div style="display:none;float:left;" id="'+folderKey+'_content">';
	
	for( var i=0; i<fileDataArr.length; i++ )
	  {
	  		var fileData = fileDataArr[i];
	  		
	  		var position = "";
	  		
	  		if( i == 0 )
	  		{
	  			position = "top";
	  		}
	  		else if( i == fileDataArr.length -1 )
	  		{
	  			position = "bottom";
	  		}
	  		else
	  		{
	  			position = "middle";
	  		}
	  		
	  		result  = createFile( fileData, position, i );
	  		
	  		if( result != 0 )
	  			fileDiv +=  result;
	  }
	
	fileDiv +='</div>';
	
	$("#folder_content").append( fileDiv );
}

/*
 * This method is used to create the html elements for folder. And append the 
 * html element to page.
 */

function addFolder( folderData, position, rowNo )
{
	
	
	if( folderData.recycleFlag == 0)
	{
		var folderDiv ="";
		
		//var folderKey = folderData.folderKey.replace(" ","%20");
		var folderKey = replaceAll(" ","%20", folderData.folderKey );
		
		var totalFilesCount = folderData.files.length;
		
		var filesCount = 0;
		
		for( var i=0;i<totalFilesCount;i++)
		{
			var fileData = folderData.files[i];
			
			if( fileData.recycleFlag == 0)
			{
				filesCount ++;
			}
		}
		
		if( (rowNo%2) ==0 )
		{
			folderDiv='<div class="folder_row odd_row" id="'+folderKey+'">';
		}
		else
		{
			folderDiv='<div class="folder_row even_row" id="'+folderKey+'">';
		}
		
		if( position == 'top' )
		{
			folderDiv += '<div class="folder folder_closed_top"></div>';
		}
		else if( position == 'bottom' )
		{
			folderDiv += '<div class="folder folder_closed_bot"></div>';
		}
		else if( position == 'top_one' )
		{
			folderDiv += '<div class="folder folder_closed_top_one"></div>';
		}
		else
		{
			folderDiv += '<div class="folder folder_closed_mid"></div>';
		}
		
		
		folderDiv +='<div class="folder_name">'+folderData.folderName+" ("+filesCount+")"+'</div></div>';
		
		$("#folder_content").append( folderDiv );
		
		
	}
	else
	{
		var folderName = folderData.folderName;
		
		var folderKey = folderData.folderKey;
		
		oTable.fnAddData( [folderName,"","","Folder",folderKey] );
		
		return 0;
	}
	
	
}

/*
 * This method is used to create the html elements for file. And append the 
 * html element to page.
 */

function createFile( fileData, position, rowNo )
{
	if( fileData.recycleFlag == 0)
	{
		var fileDiv = '';
		
		//var fileId = fileData.fileId.replace(" ","%20");
		var fileId = replaceAll(" ","%20", fileData.fileId );
	
		if( (rowNo%2) ==0 )
		{
			fileDiv +='<div class="file_row odd_row" id="'+fileId+'">';
		}
		else
		{
			fileDiv +='<div class="file_row even_row" id="'+fileId+'">';
		}
	
		if( position == 'top' )
		{
			fileDiv += '<div class="file file_mid"></div>';
		}
		else if( position == 'bottom' )
		{
			fileDiv += '<div class="file file_mid"></div>';
		}
		else
		{
			fileDiv += '<div class="file file_mid"></div>';
		}
	
		fileDiv +='<div class="file_name">'+fileData.fileName+"."+fileData.fileType+'</div></div>';
	
		return fileDiv;
	}
	else
	{
		var fileName = fileData.fileName;
		var createdDate = fileData.createdDate;
		var size = fileData.fileSize;
		var type = fileData.fileType;
		var fileId = fileData.fileId;
		
		oTable.fnAddData( [fileName,createdDate,size,type,fileId] );
		
		return 0;
	}
}

function changeDeptHighlight( deptKey )
{	
	selectedFolderKey = "";
	
	$(jqq( selectedDeptKey )).removeClass( "dept_highlight" );
	
	selectedDeptKey = deptKey;
	
	$(jqq( selectedDeptKey )).addClass( "dept_highlight" );
	
}
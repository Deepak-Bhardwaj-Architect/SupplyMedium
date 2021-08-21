var selectedFileId = "";
var selectedFileType = "";

$(document).ready(function() {
	
	initDataTable();
	
	$("#restorebtn").click(restore);
	$("#empty_recycle_btn").click(emptyRecycleBin);
});

/* It is used to initialize the datatable for recycle files */

function initDataTable() 
{
	oTable = $('#file_list').dataTable(
			{
				 "aoColumnDefs":[
									
									{ "sWidth": "175px", "aTargets": [ 0 ] },
						       	    { "sWidth": "175px", "aTargets": [ 1 ] },
						       	    { "sWidth": "175px", "aTargets": [ 2 ] },
						       	    { "sWidth": "175px", "aTargets": [ 3 ] }
						       	    
								],	
								
								 "aoColumnDefs":[{ "bVisible":false,"aTargets":[4] }],
				"bFilter": false,
				
				"bInfo": false,
				"iDisplayLength": 50,
				"bLengthChange":false,
				"bPaginate": false,
				"sScrollY": 350,
				"bAutoWidth": false,
				"fnRowCallback":function(nRow,aData,iDisplayIndex,iDisplayIndexFull){
					
					
					$("td",nRow).addClass('rowBorder');
					
					
					$(nRow).click(function()
					{
						var aTrs = oTable.fnGetNodes();
						
						for ( var i=0 ; i<aTrs.length ; i++ )
						{				
							if ( $(aTrs[i]).hasClass('row_selected') )
							{
								$(aTrs[i]).removeClass('row_selected');
							}
							
						}
						
						$(nRow).addClass('row_selected');
						
						var aPos = oTable.fnGetPosition( this );
						
						var aData = oTable.fnGetData( aPos );
						
						selectedFileId = aData[4];
						
						selectedFileType = aData[3];
						
					});		
					
					return nRow;
				}
			});
}

function restore()
{
	if( selectedFileType == "Folder" )
	{
		restoreFolder( );
	}
	else
	{
		restoreFile();
	}
}

/* This method is used to restore the Fodler */

function restoreFolder()
{
	var regnKey = $("#regnkey").val();
	
	
	showAjaxLoader();
	
	resetErrorDiv();
	
	if(selectedFileId == "")
//		alert("Select the file of folder to restore");
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/FolderMgmtServlet",
		data : {
			'RequestType' : 'DPRestoreFolderFromRB',
			'FolderKey':selectedFileId,
			'RegnKey':regnKey,
			//'DeptKey':selectedDeptKey.replace("%20", " "),
			'DeptKey': replaceAll("%20", " ", selectedDeptKey ),
		},
		cache : false,
		success : function(data) 
		{
			
			hideAjaxLoader();
			
			if (data.result == "success") 
			{
				ShowToast( data.message,2000 );
				
				deptClicked( selectedDeptKey );
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

/* This method is used to restore the file */

function restoreFile()
{
	
	showAjaxLoader();
	
	resetErrorDiv();
	
	if(selectedFileId == "")
//		alert("Select the file to restore");
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/DeptFilesMgmtServlet",
		data : {
			'RequestType' : 'RestoreFiles',
			'FileId':selectedFileId
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success") 
			{
				ShowToast( data.message,2000 );
				
				deptClicked( selectedDeptKey );
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

/* This method is used to clean the recycle bin */
function emptyRecycleBin()
{
	
	showAjaxLoader();
	
	resetErrorDiv();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/DeptFilesMgmtServlet",
		data : {
			'RequestType' : 'EmptyRecycleBin',
			//'DeptKey'     : selectedDeptKey.replace("%20"," "),
			'DeptKey'     : replaceAll("%20"," ", selectedDeptKey ),
		},
		cache : false,
		success : function(data) {

			hideAjaxLoader();
			if (data.result == "success") 
			{
				ShowToast( data.message,2000 );
				
				deptClicked( selectedDeptKey );
				
				oTable.fnClearTable();
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



var selectedDeptKey = "";
var selectedFolderKey = "";
var selectedFileKey = "";
var oTable;

/* Define the onload function */

function deptPageOnload()
{
//	hideAjaxLoader();
//	
//	$("#content_loader").hide();
//	
//	getAllDepartments();
	
	$('#file_import_btn').on("change", function(e)
	{ 
		e.preventDefault();
		uploadFile();
		
		
	});
	
	$('#file_import_btn').live("click", function(e)
	{ 
		e.stopPropagation();
		
				
	});
	
	$( '#folder_head' ).live('click', function ( e )
	{
	    if ( $( e.target ).is( 'input:file' ) )
	    { 
	    	e.stopPropagation();
	    } 
	    else 
	    {
	    	folderHeadClicked();
	    }        
	});

	
	
		
	//$("#folder_head").click(folderHeadClicked);
	
	$("#annomt_head").click(annomtHeadClicked);
	
	$("#recycle_bin").click(showRecycleFiles);
	
	$(".dataTables_scrollBody").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	$("#file_export_btn").dblclick(function(e) {
		   //do something
		   e.stopPropagation();
		});
	
	
	// Add new folder popup creation
	$('#add_folder_btn').click(
			function(e) {
				
				showAddFolderPopup();
				
				 e.stopPropagation();
			});
	
	// Click save button in new folder creation
	$('#save_folder').click(
			function(e) {
				createFolder();
			});
			
	$("#deptlist").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	$("#folder_ctrls").show();
	
	
	$("#del_file_btn").click(function(e){
		deleteFile();
		 e.stopPropagation();
	});
	
	$("#file_export_btn").click(function(e){
		downloadFile();
		 e.stopPropagation();
	});
	
	
	$("#del_folder_btn").click(function(e){
		removeFolder();
		 e.stopPropagation();
	});
	
	
	$("#recommend_content").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
	
	
}


/* This method is called when user click the 
 * folders head div
 */

function folderHeadClicked()
{
	if($('#folder_content').css('display') == 'none')
	{
		$("#folder_content").slideDown();
		$("#annomt_content").hide();
		$("#recycle_bin").show();
		
		$("#folder_arrow").removeClass('right_arrow');
		$("#folder_arrow").addClass('down_arrow');
		
		$("#annomt_arrow").removeClass('down_arrow');
		$("#annomt_arrow").addClass('right_arrow');
		
	}
	else
	{
		$("#folder_content").slideUp();
		$("#recycle_bin").hide();
		$("#annomt_content").slideDown();
		
		$("#folder_arrow").removeClass('down_arrow');
		$("#folder_arrow").addClass('right_arrow');
		
		$("#annomt_arrow").removeClass('right_arrow');
		$("#annomt_arrow").addClass('down_arrow');
	}
}


/* This method is called when user click the Announcement
 * head div
 */

function annomtHeadClicked()
{
	if($('#annomt_content').css('display') == 'none')
	{
		$("#folder_content").slideUp();
		$("#recycle_bin").hide();
		$("#annomt_content").slideDown();
		
		$("#folder_arrow").removeClass('down_arrow');
		$("#folder_arrow").addClass('right_arrow');
		
		$("#annomt_arrow").removeClass('right_arrow');
		$("#annomt_arrow").addClass('down_arrow');
		
	}
	else
	{
		$("#folder_content").slideDown();
		$("#annomt_content").hide();
		$("#recycle_bin").show();
		
		$("#folder_arrow").removeClass('right_arrow');
		$("#folder_arrow").addClass('down_arrow');
		
		$("#annomt_arrow").removeClass('down_arrow');
		$("#annomt_arrow").addClass('right_arrow');
	}
}


/* It is used to show the Recycle bin files in popup view */
function showRecycleFiles()
{
	$("#recycle_bin_popup").show();
}

/* It is used to reset the error div */

function resetErrorDiv()
{
	$("#DP_error_lbl").text("");
}

/* It is used to display the error message in department page */
function setErrorMsg( message )
{
	$("#DP_error_lbl").text(message);
}

function getAllDepartments()
{
	
	var userKey = $("#EmailAddress").val();
	
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/DPFetchDeptServlet",
		data : {
			'UserKey' : userKey,
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();

			if (data.result == "success")
			{
				
				var deptArr = new Array( data.depts.length );
			  	
				deptArr = data.depts;
				
				parseDept( deptArr );
				
			}

			else 
			{
				$("#feed_err_lbl").val(data.message);
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
			
			$("#feed_err_lbl").text("Unexcepted error occurred. Please try again");
		}
	});

}

function parseDept( deptArr )
{
	
	for( var i=0;i<deptArr.length;i++ )
	{
		var deptStr = "";
		
		var dept = deptArr[i];
		
		//var deptKey =  dept.deptKey.replace(" ","%20");
		
		var deptKey =  replaceAll(" ","%20", dept.deptKey);
		
		 if( (i%2) ==0 )
		 {
			 deptStr += '<input type="button" class="dept_row odd_row" id='+deptKey+' value="'+ dept.deptName+'" onclick="deptClicked(\''+ deptKey+'\');"/>';
		 }
		 else
		 {
			 deptStr += '<input type="button" class="dept_row even_row" id='+deptKey+' value="'+ dept.deptName+'" onclick="deptClicked(\''+ deptKey+'\');"/>';
		 }
		 $("#deptlist .mCSB_container").append(deptStr);
                 //deptClicked(deptKey);
		// $("#deptlist").append(deptStr);
		 $("#deptlist").mCustomScrollbar("update");
		 
		 if( i==0 )
		{
			 //deptClicked( deptKey );
		}
	}
	
	
}
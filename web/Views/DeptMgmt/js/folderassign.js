//Used to get all the folders which is not available in given company
function getAllFolders( deptKey )
{
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/FolderDeptMapServlet",
		   data:{ 
			   'RequestType': 'ExceptFolder',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   var $select = $('#existfoldersel');

					$select.find('option').remove();
					
					$.each(data, function(key, value) 
					{
						if( value != "success")
							$('<option>').val(key).text(value).appendTo($select);
					});
					
					sortSelectBox( 'existfoldersel' );
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

//Used to get all the department folders
function getDeptFolders( deptKey )
{
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/FolderDeptMapServlet",
		   data:{ 
			   'RequestType': 'FindFolder', 
		       'RegnKey':regnKey,
		       'DeptKey':deptKey
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   var $select = $('#deptfoldersel');

					$select.find('option').remove();
					
					$.each(data, function(key, value) 
					{
						if( value != "success")
							$('<option>').val(key).text(value).appendTo($select);
					});
					
					sortSelectBox( 'deptfoldersel' );
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

// This is used to assign folders to department
function assignFoders()
{
	var folderKeyArr = [];
	
	var i = 0;
	
	$("#existfoldersel option:selected").each(function()
	{
		folderKeyArr[i] = $(this).val();
		 
	});
	
	if( folderKeyArr.length == 0 )
		return;
	
	var deptKey = $("#deptlistsel option:selected").val();
	
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/FolderDeptMapServlet",
		   data:{ 
			   'RequestType': 'AddFolder',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey,
		       'FolderKeys' :folderKeyArr,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   ShowToast( data.message,2000 );
				   
				   $('#existfoldersel option:selected').remove()
					.appendTo('#deptfoldersel');
				   
				   sortSelectBox( 'deptfoldersel' );
				   
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

// This is used to remove the relationship between folders and department

function removeFolders()
{
	
	var folderKeyArr = [];
	var i = 0;
	
	$("#deptfoldersel option:selected").each(function()
			{
		folderKeyArr[i] = $(this).val();
		 
		});
	
	if( folderKeyArr.length == 0 )
		return;
	
	var deptKey = $("#deptlistsel option:selected").val();
	
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/FolderDeptMapServlet",
		   data:{ 
			   'RequestType': 'RemoveFolder',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey,
		       'FolderKeys' :folderKeyArr,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   ShowToast( data.message,2000 );
				   
				   $('#deptfoldersel option:selected').remove()
					.appendTo('#existfoldersel');
				   
				   sortSelectBox( 'existfoldersel' );
				   
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

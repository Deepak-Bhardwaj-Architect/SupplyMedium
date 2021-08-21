
$(document).ready(function() {
  // Handler for .ready() called.
	
	
	
	
});


var myfilter;

//Used to get all the departments of given company
function getAllDepartments()
{
	
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/DeptFetchServlet",
		   data:{ 
		        'RegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   
				   var $select = $('#deptlistsel');

					$select.find('option').remove();
					
					$.each(data, function(key, value) 
					{
						if( value != "success")
							$('<option>').val(key).text(value).appendTo($select);
					});
					
					
					if( $('select#deptlistsel option').length > 0 )
					{
						sortSelectBox( 'deptlistsel' );
						
						$("#deptlistsel option:first-child").attr("selected", true);
						
						var deptKey = $("#deptlistsel option:selected").val();
						
						getDeptData(deptKey );
						
						myfilter  = new filterlist(document.deptmgmtfrm.deptlistsel);
						
					}
					else
					{
						 $('#deptmgmterr').text( "There are no departments to be listed" );	
						 
						 clearFormData();
					}
					
			   }
			   else
			   {
				   
				   $('#deptmgmterr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	 $('#deptmgmterr').text( "Unexpected error occur. Please try again." );	
			}
		 });
}


// Used to create the new department
function addDept()
{
	if($("#new_dept_input").val() != "")
	{
		var deptName = $("#new_dept_input").val().trim();
		
		resetAllErrorMsg();
		
		showAjaxLoader();
		
		var regnKey = $('#regnkey').val();

		
		$.ajax({
			   type: "POST",
			   url: getBaseURL()+"/DeptMgmtServlet",
			   data:{ 
			        'RequestType': 'AddNewDept', 
			        'RegnKey':regnKey,
			        'DeptName':deptName
			    } ,
			   cache:false,
			   success: function( data )
			   {
				   hideAjaxLoader();
				   
				   $("#add_group_popup").hide();
				   
				   if( data.result == "success")
				   {
					   ShowToast( data.message,2000 );
					   
					   getAllDepartments();
				   }
				   else
				   {
					   $('#deptmgmterr').text(data.message);
				   }
				   
			   },
			    error : function(xhr, textStatus, errorThrown) 
			    {
			    	hideAjaxLoader();
			    	
			    	$('#deptmgmterr').text( "Unexpected error occur. Please try again." );
				}
			 });
	}
	else
	{
		$('#new_dept_input_err').text("Enter Department Name");
	}
	
}


// Used to update the department name
function editDept()
{
	
	if($("#edit_dept_input").val() != "")
	{
		var deptKey = $("#deptlistsel option:selected").val();
		
		var deptName = $("#edit_dept_input").val();
		
		resetAllErrorMsg();
		
		showAjaxLoader();
		
		var regnKey = $('#regnkey').val();
		
		$.ajax({
			   type: "POST",
			   url: getBaseURL()+"/DeptMgmtServlet",
			   data:{ 
			        'RequestType': 'UpdateDept', 
			        'RegnKey':regnKey,
			        'DeptKey':deptKey,
			        'DeptName':deptName,
			    } ,
			   cache:false,
			   success: function( data )
			   {
				   hideAjaxLoader();
				   
				   $("#edit_group_popup").hide();
				   
				   if( data.result == "success")
				   {
					   
					   ShowToast( data.message,2000 );
					   
					   getAllDepartments();
				   }
				   else
				   {
					   $('#deptmgmterr').text(data.message);
				   }
				   
			   },
			    error : function(xhr, textStatus, errorThrown) 
			    {
			    	hideAjaxLoader();
			    	
			    	$('#deptmgmterr').text( "Unexpected error occur. Please try again." );
				}
			 });
	}
	else
	{
		$('#edit_dept_input_err').text("Enter Department Name");
	}
}

function deleteDept()
{
	
	if ($("#deptlistsel")[0].selectedIndex < 0) 
	{
       alert("select any department");
       
       return;
    }
	
	message = "This operation will delete all items inside the department, "+ $("#deptlistsel option:selected").text()+". Do you want to continue?";
	
	showWarning(message);
}

function deleteConfirm()
{
	var deptKey = $("#deptlistsel option:selected").val();
	
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
		
	$.ajax({
		type: "POST",
		url: getBaseURL()+"/DeptMgmtServlet",
		data:{ 
			    'RequestType': 'RemoveDept', 
			    'RegnKey':regnKey,
			    'DeptKey':deptKey,
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
					$('#deptmgmterr').text(data.message);
				}
				   
			 },
			 error : function(xhr, textStatus, errorThrown) 
			 {
				 hideAjaxLoader();
				 
				 $('#deptmgmterr').text( "Unexpected error occur. Please try again." );	
			 }
	 });
	
}



// Used to fill the selected department details in form
function getDeptData( deptKey )
{
	if ($("#deptlistsel")[0].selectedIndex < 0) 
	{
       return;
    }
	
	getAllUsers( deptKey );
	getDeptUsers( deptKey );
	
	getAllGroups( deptKey );
	getDeptGroups( deptKey );
	
	getAllFolders( deptKey );
	getDeptFolders( deptKey );
	
	getPrivileges( deptKey );
	
}

// Used to delete all error message currently display in form
function resetAllErrorMsg()
{
	$('#deptmgmterr').text("");
	$('#userassignerr').text("");
	$('#groupassignerr').text("");
	$('#folderassignerr').text("");
	$('#deptprierr').text("");
}


//It remove the all old data from form
function clearFormData()
{
	$('#existusersel').find('option').remove();
	$('#deptusersel').find('option').remove();
	
	$('#existgroupsel').find('option').remove();
	$('#pregroupsel').find('option').remove();
	 
	$('#existfoldersel').find('option').remove();
	$('#deptfoldersel').find('option').remove();

	$('#addfolder').prop('checked', false);

	$('#delfolder').prop('checked', false);

	$('#addfile').prop('checked', false);

	$('#delfile').prop('checked', false);

	$('#postAnno').prop('checked', false);

	$('#managefold').prop('checked', false);
	   	
}




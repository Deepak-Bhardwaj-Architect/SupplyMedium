//Used to get all the groups which is not available in given company
function getAllGroups( deptKey )
{
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/GroupDeptMapServlet",
		   data:{ 
			   'RequestType': 'ExceptGroup',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   var $select = $('#existgroupsel');

					$select.find('option').remove();
					
					$.each(data, function(key, value) 
					{
						if( value != "success")
							$('<option>').val(key).text(value).appendTo($select);
					});
					
					sortSelectBox( 'existgroupsel' );
			   }
			   else
			   {
				   $('#groupassignerr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	$('#groupassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

//Used to get all the department groups
function getDeptGroups( deptKey )
{
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/GroupDeptMapServlet",
		   data:{ 
			   'RequestType': 'FindGroup', 
		       'RegnKey':regnKey,
		       'DeptKey':deptKey,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   var $select = $('#pregroupsel');

					$select.find('option').remove();
					
					$.each(data, function(key, value) 
					{
						if( value != "success")
							$('<option>').val(key).text(value).appendTo($select);
					});
					
					sortSelectBox( 'pregroupsel' );
			   }
			   else
			   {
				   $('#groupassignerr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	$('#groupassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

// This is used to assign groups to department
function assignGroups()
{
	
	
	var groupKeyArr = [];
	var i = 0;
	
	$("#existgroupsel option:selected").each(function()
	{
			groupKeyArr[i] = $(this).val();
		 
	});
	
	if( groupKeyArr.length == 0 )
		return;
	
	var deptKey = $("#deptlistsel option:selected").val();
	
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/GroupDeptMapServlet",
		   data:{ 
			   'RequestType': 'AddGroup',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey,
		       'GroupKeys' :groupKeyArr,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   ShowToast( data.message,2000 );
				   
				   $('#existgroupsel option:selected').remove()
					.appendTo('#pregroupsel');
				   
				   sortSelectBox( 'pregroupsel' );
				   
				  
			   }
			   else
			   {
				   $('#groupassignerr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	$('#groupassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
	
}

// This is used to remove the relationship between user groups and department

function removeGroups()
{
	var groupKeyArr = [];
	var i = 0;
	
	$("#pregroupsel option:selected").each(function()
	{
			groupKeyArr[i] = $(this).val();
		 
	});
	
	if( groupKeyArr.length == 0 )
		return;
	
	var deptKey = $("#deptlistsel option:selected").val();
	
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/GroupDeptMapServlet",
		   data:{ 
			   'RequestType': 'RemoveGroup',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey,
		       'GroupKeys' :groupKeyArr,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   ShowToast( data.message,2000 );
				   
				   $('#pregroupsel option:selected').remove().
					appendTo('#existgroupsel');
				   
				   sortSelectBox( 'existgroupsel' );
				   
			   }
			   else
			   {
				   $('#groupassignerr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	$('#groupassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
	
}

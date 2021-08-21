//Used to get all the users which is not available in given company
function getAllUsers( deptKey )
{
	//alert(deptKey);
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserDeptMapServlet",
		   data:{ 
			   'RequestType': 'ExceptUser',  
			   
		       'RegnKey':regnKey,
		       'DeptKey':deptKey
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   var $select = $('#existusersel');

					$select.find('option').remove();
					
					$.each(data, function(key, value) 
					{
						if( value != "success")
							$('<option>').val(key).text(value).appendTo($select);
					});
					
					sortSelectBox( 'existusersel' );
					
			   }
			   else
			   {
				   $('#userassignerr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {    
		    	hideAjaxLoader();      
		    	
		    	$('#userassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

//Used to get all the department users
function getDeptUsers( deptKey )
{
        //alert('enter');
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserDeptMapServlet",
		   data:{ 
			   'RequestType': 'FindUser', 
		       'RegnKey':regnKey,
		       'DeptKey':deptKey
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   var $select = $('#deptusersel');

					$select.find('option').remove();
					
					$.each(data, function(key, value) 
					{
						if( value != "success")
							$('<option>').val(key).text(value).appendTo($select);
					});
					
					sortSelectBox( 'deptusersel' );
			   }
			   else
			   {
				   $('#userassignerr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	$('#userassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

// This is used to assign folders to department
function assignUsers()
{
	var userKeyArr = [];
	var userNameArr = [];
	var i = 0;
	
	$("#existusersel option:selected").each(function()
			{
				userKeyArr[i] = $(this).val();
				userNameArr[i] = $(this).text();
		 i++;
		});
	
	if( userKeyArr.length == 0 )
		return;
	
	var deptKey = $("#deptlistsel option:selected").val();
	
	resetAllErrorMsg();
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserDeptMapServlet",
		   data:{ 
			   'RequestType': 'AddUser',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey,
		       'UserKeys' :userKeyArr,
		       'UserNames':userNameArr,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   ShowToast( data.message,2000 );
				   
				   $("#deptusersel")[0].selectedIndex = -1;
				   
				  $('#existusersel option:selected').remove()
					.appendTo('#deptusersel');
				   
				   sortSelectBox( 'deptusersel' );
			   }
			   else
			   {
				   $('#userassignerr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	$('#userassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
	
}

// This is used to remove the relationship between folders and department

function removeUsers()
{
	var userKeyArr = [];
	
	var userNameArr = [];
	
	var i = 0;
	
	$("#deptusersel option:selected").each(function()
		{
		userKeyArr[i] = $(this).val();
		userNameArr[i] = $(this).text();
		i++;
		 
		});
	
	if( userKeyArr.length == 0 )
		return;
	
	var deptKey = $("#deptlistsel option:selected").val();
	
	resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserDeptMapServlet",
		   data:{ 
			   'RequestType': 'RemoveUser',  
		       'RegnKey':regnKey,
		       'DeptKey':deptKey,
		       'UserKeys' :userKeyArr,
		       'UserNames': userNameArr,
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   if( data.result == "success")
			   {
				   ShowToast( data.message,2000 );
				   
				   $("#existusersel")[0].selectedIndex = -1;
				   
				   $('#deptusersel option:selected').remove()
					.appendTo('#existusersel');
				
				   sortSelectBox( 'existusersel' );
			   }
			   else
			   {
				   $('#userassignerr').text(data.message);
			   }
			   
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	$('#userassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
	
}

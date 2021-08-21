function assignUsersToGroup()
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
	
	var groupKey = $("#grouplistsel option:selected").val();
	
	
	//resetAllErrorMsg();
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserGroupMapServlet",
		   data:{ 
			   'RequestType': 'AddUser',  
		       'RegnKey':regnKey,
		       'GroupKey':groupKey,
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
				   
				   $("#pregroupsel")[0].selectedIndex = -1;
				   
				   $('#existusersel option:selected').remove()
					.appendTo('#pregroupsel');
				   
				   sortSelectBox( 'pregroupsel' );
				   
				   // Reset the search filter 
				   
				   groupUserfilter = new filterlist(document.groupmgmtfrm.pregroupsel);
				   
				   existingUserfilter = new filterlist(document.groupmgmtfrm.existusersel);
				   
				  
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

// This is used to remove the relationship between group and users

function removeUsersFromGroup()
{
	var userKeyArr = [];
	var userNameArr = [];
	var i = 0;
	
	$("#pregroupsel option:selected").each(function()
		{
		userKeyArr[i] = $(this).val();
		userNameArr[i] = $(this).text();
		i++;
		 
		});
	
	if( userKeyArr.length == 0 )
		return;
	
	var groupKey = $("#grouplistsel option:selected").val();
	
	//resetAllErrorMsg();
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserGroupMapServlet",
		   data:{ 
			   'RequestType': 'RemoveUser',  
		       'RegnKey':regnKey,
		       'GroupKey':groupKey,
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
				   
				   $("#existusersel")[0].selectedIndex = -1;
				   
				   $('#pregroupsel option:selected').remove()
					.appendTo('#existusersel');
				   
				   sortSelectBox( 'existusersel' );
				   
				   // Reset the search filter 
				   
				   groupUserfilter = new filterlist(document.groupmgmtfrm.pregroupsel);
				   
				   existingUserfilter = new filterlist(document.groupmgmtfrm.existusersel);
				
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

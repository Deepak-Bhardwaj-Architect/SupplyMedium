/*This method is used to get group's folder access privileges*/
function getGroupPri()
{
	var regnKey = $("#regnkey").val();
	//var groupKey; 
	//var folderKey;

	//showAjaxLoader();
	
	//folderKey_ = folderKey_.replace( "%20", " " );
	
	folderKey_ = replaceAll( "%20", " ", folderKey_ );
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ManageFolderServlet",
		   data:{ 
			    'RequestType':'GetGroupPrivileges',
		        'GroupKey':groupKey_,
		        'RegnKey':regnKey,
		        'FolderKey':folderKey_,
		        'DeptName':deptName_,
		    } ,
		   cache:false,
		   success: function( groupFolderAccData )
		   {
			   //alert("success");
			   //hideAjaxLoader();
			 ////alert(profileData.ProfilePicPath);
			  if( groupFolderAccData.result == "success" )
			  {			  
				  var readFlag = groupFolderAccData.readflag;
				  var readWriteFlag = groupFolderAccData.readwriteflag;
				  
				  //alert( "Read Permission: " + readFlag + "\n ReadWrite Permision: " + readWriteFlag );
				  
				  if( readFlag==0 )
				  {
					  $('#read').attr('checked', false);
				  }
				  else
				  {
					  $('#read').attr('checked', true);
				  }
				  
				  if( readWriteFlag==0 )
				  {
					  $('#readwrite').attr('checked', false);
				  }
				  else
				  {
					  $('#readwrite').attr('checked', true);
				  }
			  }
			  
			  else
			 {
				  //alert("failed");
				//  $('#profilepic_err').text(profileData.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown)
		    {
		    	 //hideAjaxLoader();
		    	//alert("Exception");
		    	 // $("#profilepic_err").text("Unexcepted error occur. Please try again.");
		    	  
			}
		 });
}

/*This method is used to get user's folder access privileges*/
function getUserPri()
{
	var regnKey = $("#regnkey").val();
	//var groupKey; 
	//var folderKey;

	//showAjaxLoader();
	
	//folderKey_ = folderKey_.replace( "%20", " " );
	
	folderKey_ = replaceAll( "%20", " ", folderKey_ );
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ManageFolderServlet",
		   data:{ 
			    'RequestType':'GetUserPrivileges',
		        'UserKey':userKey_,
		        'RegnKey':regnKey,
		        'FolderKey':folderKey_,
		        'DeptName':deptName_,
		    } ,
		   cache:false,
		   success: function( userFolderAccData )
		   {
			   //alert("success");
			   //hideAjaxLoader();
			 ////alert(profileData.ProfilePicPath);
			   
			  if(userFolderAccData.result == "success")
			  {			  
				  var readFlag = userFolderAccData.readflag;
				  var readWriteFlag = userFolderAccData.readwriteflag;
				  
				  //alert( "Read Permission: " + readFlag + "\n ReadWrite Permision: " + readWriteFlag );
				  
				  if( readFlag==0 )
				  {
					  $('#read').attr('checked', false);
				  }
				  else
				  {
					  $('#read').attr('checked', true);
				  }
				  
				  if( readWriteFlag==0 )
				  {
					  $('#readwrite').attr('checked', false);
				  }
				  else
				  {
					  $('#readwrite').attr('checked', true);
				  }
				  
			  }
			  
			  else
			 {
				  //alert("failed");
				//  $('#profilepic_err').text(profileData.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown)
		    {
		    	 //hideAjaxLoader();
		    	//alert("Exception");
		    	 // $("#profilepic_err").text("Unexcepted error occur. Please try again.");
		    	  
			}
		 });
}

function updatePrivileges()
{
	if( folderKey_ == "")
	{
		//alert("Please select a folder to assign!");
		return;
	}
	if( lastSelected == "user" )
	{
		//alert("Calling user for "+ lastSelected);
		updateUserFolderPri();
	}
	else
	{
		//alert("Calling group for "+ lastSelected);
		updateGroupFolderPri();
	}
}

/*This method is used to update the group's folder access privileges*/
function updateGroupFolderPri()
{
	var regnKey = $("#regnkey").val();
	//var groupKey = '408-498-5186:HR';
	//var folderKey = '408-498-5186:Folder1';
	var readFlag = 0;
	var readWriteFlag = 0;
	
	if ( $('#read').is(':checked') )
	{
		readFlag = 1;
	}
	else
	{
		readFlag = 0;
	}
	if ( $('#readwrite').is(':checked') )
	{
		readWriteFlag = 1;
	}
	else
	{
		readWriteFlag = 0;
	}
	
	//showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ManageFolderServlet",
		   data:{ 
			    'RequestType':'UpdateGroupPrivileges',
		        'GroupKey': groupKey_,
		        'RegnKey':regnKey,
		        'FolderKey':folderKey_,
		        'DeptName':deptName_,
		        'ReadFlag':readFlag,
		        'ReadWriteFlag':readWriteFlag,
		    } ,
		   cache:false,
		   success: function( depts )
		   {
			   ////alert("success");
			   //hideAjaxLoader();
			 //////alert(profileData.ProfilePicPath);
			  if(depts.result == "success")
			  {	
				  //alert("success");
				  ShowToast( depts.message,2000 );  
			  }
			  
			  else
			 {
				  ////alert("failed");
				//  $('#profilepic_err').text(profileData.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown)
		    {
		    	 //hideAjaxLoader();
		    	////alert("Exception");
		    	 // $("#profilepic_err").text("Unexcepted error occur. Please try again.");
		    	  
			}
		 });
}

/*This method is used to update the user's folder access privileges*/
function updateUserFolderPri()
{
	var regnKey = $("#regnkey").val();
	//var groupKey = '408-498-5186:HR';
	//var folderKey = '408-498-5186:Folder1';
	var readFlag = 0;
	var readWriteFlag = 0;
	
	if ( $('#read').is(':checked') )
	{
		readFlag = 1;
	}
	else
	{
		readFlag = 0;
	}
	if ( $('#readwrite').is(':checked') )
	{
		readWriteFlag = 1;
	}
	else
	{
		readWriteFlag = 0;
	}
	
	//showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ManageFolderServlet",
		   data:{ 
			    'RequestType':'UpdateUserPrivileges',
		        'UserKey': userKey_,
		        'RegnKey':regnKey,
		        'FolderKey':folderKey_,
		        'DeptName':deptName_,
		        'ReadFlag':readFlag,
		        'ReadWriteFlag':readWriteFlag,
		    } ,
		   cache:false,
		   success: function( depts )
		   {
			   ////alert("success");
			   //hideAjaxLoader();
			 //////alert(profileData.ProfilePicPath);
			  if(depts.result == "success")
			  {			  
				  ShowToast( depts.message,2000 );
			  }
			  
			  else
			 {
				  ////alert("failed");
				//  $('#profilepic_err').text(profileData.message);
			 }
		   },
		    error : function(xhr, textStatus, errorThrown)
		    {
		    	 //hideAjaxLoader();
		    	////alert("Exception");
		    	 // $("#profilepic_err").text("Unexcepted error occur. Please try again.");
		    	  
			}
		 });
}
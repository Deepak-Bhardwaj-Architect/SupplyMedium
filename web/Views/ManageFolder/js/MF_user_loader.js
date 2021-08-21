function parseUsers( userDataArr, groupData )
{	
	var groupKeySplitArr = groupData.groupkey.split(":");
		
	var groupName ="";
		
	if( groupKeySplitArr.length>1 )
			groupName = groupKeySplitArr[1];
		
	var groupContentDiv = '<div style="display:none;float:left;" id='+groupData.groupkey+'_content>';
	
	var result = "";
	
	for( var i=0; i<userDataArr.length; i++ )
	  {
	  		var userData = userDataArr[i];
	  		
	  		result += addUser( userData, i, groupName );
	  		
	  		var userDivId = userData.userkey+"_"+groupName;
	  		
	  		$(jqq(userDivId)).die();
	  		
	  		$(jqq(userDivId)).live('click', function( event ) 
	  		{
	  			
	  			userKey_ = userData.userkey;
	  			
	  			lastSelected = "user";
	  			
	  			var newUserDivId = this.id;
	  			
	  			if( newUserDivId != lastSelectedLsId || lastSelectedLsId == "" )
	  			{
	  				$(jqq(newUserDivId+"_text")).addClass("mf_rowselected");
		  			
		  			if(lastSelectedLsId != "")
		  			{
		  				$(jqq(lastSelectedLsId+"_text")).removeClass("mf_rowselected");
		  			}
		  			
		  			lastSelectedLsId = newUserDivId;
		  			
		  			lastSelectedRsId = "";
		  			
		  			folderKey_ = "";
		  			listNUDepts();
	  			}
	  			
	  			
	  			
	  			
	  		});
	  }
	
	groupContentDiv += result;
	
	$('#mf_group').append( groupContentDiv );
	
	
}

function addUser( userData, i, groupName )
{	
	
	var userDivId = userData.userkey+"_"+groupName;
	
	var userDiv = '<div class="mf_sub" id='+userDivId+'>';
	
	userDiv += '<div class="t_shape"></div>';

	userDiv += '<div class="user"></div>';
	
	userDiv +='<div class="mf_content_text" id="'+userDivId+'_text">'+userData.firstname+'</div></div>';
	
	return userDiv;
	
	
}


/*This method is used to list all the Non group users for given company regn key*/
function listUsers()
{
	var regnKey = $("#regnkey").val();
	
	showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ManageFolderServlet",
		   data:{ 
			    'RequestType':'ListNonGroupUsers',
		        'RegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function( userData ) 
		   {
			   
			  hideAjaxLoader();
			  
			  if(userData.result == "success")
			  {			  
				  
				  var userArr = new Array( userData.users.length );
				  
				  userArr = userData.users;
				 
				  parseOnlyUsers( userArr );
			  }
			  
			  else
			 {
				
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	$("#profilepic_err").text("Unexcepted error occur. Please try again.");
		    	  
			}
		 });
}



function  parseOnlyUsers( userArr )
{
	
	for( var i=0; i<userArr.length; i++ )
	  {
	  		var userData = userArr[i];
	  		
	  		addOnlyUser( userData, i );
	  }
}

function addOnlyUser( userData, i )
{
	var userDiv = '<div class="mf_main_users" id='+userData.userkey+'>';
	
	userDiv += '<div class="user" style="margin-left:6px;"></div>';
	
	userDiv +='<div class="mf_content_text" id="'+userData.userkey+'_text">'+userData.firstname+'</div></div>';
	
	//$("#mf_users").append( userDiv );
	$('#mf_group').append( userDiv );
	
	$(jqq(userData.userkey)).die();
	
	$(jqq(userData.userkey)).live('click', function() {
	  
		userKey_ = userData.userkey;
		
		lastSelected = "user";
		
		if( userData.userkey != lastSelectedLsId || lastSelectedLsId == "" )
		{
			$(jqq(userData.userkey+"_text")).addClass("mf_rowselected");
			
			if(lastSelectedLsId != "")
			{
				$(jqq(lastSelectedLsId+"_text")).removeClass("mf_rowselected");
			}
			
			lastSelectedLsId = userData.userkey;
			
			lastSelectedRsId = "";
			
			folderKey_ = "";
			
			listNUDepts();
		}
		
		
	});
}
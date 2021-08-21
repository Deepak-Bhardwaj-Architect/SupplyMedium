/*This method is used to list all the departments for given group key, where
 * this group should not be assigned to any of the listed departments
 */
function listNGDepts()
{
	var regnKey = $("#regnkey").val();
	//showAjaxLoader();
	//alert("Request for list group depts");

	//alert("BeforeParsing - " + groupKey_);
	
	//groupKey_ = groupKey_.replace( "%20", " " );
	
	groupKey_ = replaceAll("%20"," ", groupKey_);

	//alert("AfterParsing - " + groupKey_);
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ManageFolderServlet",
		   data:{ 
			    'RequestType':'ListNonGroupDepts',
		        'GroupKey':groupKey_,
		        'RegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function(deptjsonObj ) 
		   {
			  
			  if(deptjsonObj.result == "success")
			  {			  
				  ////alert(profileData.ProfilePicPath);
					//$('#profilepicid').val(profileData.ProfilePicPath);
				  
				  var deptArr = new Array( deptjsonObj.depts.length );
				  
				  deptArr = deptjsonObj.depts;
				 
				  parseDepts( deptArr );
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

function parseDepts( deptArr )
{
	$("#mf_dept").empty();
	
	for( var i=0; i<deptArr.length; i++ )
	  {
	  		var deptData = deptArr[i];
	  		
	  		var folderDataArr = new Array( deptData.folderdata.length );
		  	
	  		folderDataArr = deptData.folderdata;
	  		
	  		var position = "";
	  		
	  		if( i == 0 )
	  		{
	  			position = "top";
	  		}
	  		else if( i == deptArr.length -1 )
	  		{
	  			position = "bottom";
	  		}
	  		else
	  		{
	  			position = "middle";
	  		}
	  		
	  		addDept( deptData, position );
	  		
	  		parseFolders( folderDataArr,deptData );
	  }
}

function addDept( deptData, position )
{
	
	deptData.deptkey = replaceAll( " ","%20", deptData.deptkey );
	
	var deptDiv = '<div class="mf_main" id='+deptData.deptkey+'>';
	
	deptDiv += '<div id="collapse_'+deptData.deptkey+'" class="expand_plus"></div>';
	
	if( position == 'top' )
	{
		deptDiv += '<div class="group_bottom"></div>';
	}
	else if( position == 'bottom' )
	{
		deptDiv += '<div class="group_top"></div>';
	}
	else
	{
		deptDiv += '<div class="group"></div>';
	}
	
	deptDiv +='<div class="mf_main_text">'+deptData.deptname+" ("+deptData.folderdata.length+")"+'</div></div>';
	
	// Click event for Plus and minus icons
	$(jqq("collapse_"+deptData.deptkey)).die();
	
	$(jqq("collapse_"+deptData.deptkey)).live('click', function( event ) 
	{	
		event.stopPropagation();
		 
		$(jqq(deptData.deptkey+"_content")).slideToggle('fast', function() 
				{
			
			if ($(jqq(deptData.deptkey+"_content")).css('display') == 'none') 
			{
				$(jqq("collapse_"+deptData.deptkey)).addClass("expand_plus");
				$(jqq("collapse_"+deptData.deptkey)).removeClass("collapse_minus");
			}
			
			else 
			{
				$(jqq("collapse_"+deptData.deptkey)).removeClass("expand_plus");
				$(jqq("collapse_"+deptData.deptkey)).addClass("collapse_minus");
				
			}
		  });
		
		
	});
	
	$("#mf_dept").append( deptDiv );
	
}

function parseFolders( folderDataArr, deptData )
{
	var deptKeySplitArr = deptData.deptkey.split(":");
		
	var deptName ="";
	
	var result="";
		
	if( deptKeySplitArr.length>1 )
		deptName = deptKeySplitArr[1];
	
	var deptContentDiv = '<div style="display:none;float:left;" id='+deptData.deptkey+'_content>';
		
	for( var i=0; i<folderDataArr.length; i++ )
	 {
	  		var folderData = folderDataArr[i];
	  		
	  		result += addFolder( folderData, i,deptName );
	  		
	  		var folderDivId = folderData.folderkey+"_"+deptName;
	  		
	  		$(jqq(folderDivId)).die();
	  		
	  		$(jqq(folderDivId)).live('click', function()
	  		{
	  			deptName_ = deptName;
	  		
	  			folderDivId = this.id;
	  			
	  			var folderKeyArr = folderDivId.split("_");
	  			
	  			folderKey_ = folderKeyArr[0];
	  			
	  			if( folderDivId != lastSelectedRsId || lastSelectedRsId == "" )
	  			{
	  				$(jqq(folderDivId+"_text")).addClass("mf_rowselected");
		  			
		  			if(lastSelectedRsId != "")
		  			{
		  				$(jqq(lastSelectedRsId+"_text")).removeClass("mf_rowselected");
		  			}
		  			
		  			lastSelectedRsId = folderDivId;
		  			
		  			if( lastSelected == "group")
		  			{
		  				getGroupPri();
		  			}
		  			else
		  			{
		  				getUserPri();
		  			}
	  			}
	  			
	  			
	  		});
	 }
	
	deptContentDiv += result;
	
	$('#mf_dept').append( deptContentDiv );
}

function addFolder( folderData, i,deptName )
{	
	folderData.folderkey = replaceAll( " ","%20", folderData.folderkey );
	
	var folderDivId = folderData.folderkey+"_"+deptName;
	
	var folderDiv = '<div class="mf_sub" id='+folderDivId+'>';

	folderDiv += '<div class="t_shape"></div>';

	folderDiv += '<div class="folder_only_closed"></div>';
	
	folderDiv +='<div class="mf_content_text" id="'+folderDivId+'_text">'+folderData.foldername+'</div></div>';
	
	
	return folderDiv;
}


/*This method is used to list all the departments for given user key, where
 * this user should not be assigned to any of the listed departments
 */
function listNUDepts()
{
	//alert(userKey_);
	var regnKey = $("#regnkey").val();
	//showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ManageFolderServlet",
		   data:{ 
			    'RequestType':'ListNonUserDepts',
		        'UserKey':userKey_,
		        'RegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function(deptjsonObj ) 
		   {
			  
			  if(deptjsonObj.result == "success")
			  {			  
				  ////alert(profileData.ProfilePicPath);
					//$('#profilepicid').val(profileData.ProfilePicPath);
				  
				  var deptArr = new Array( deptjsonObj.depts.length );
				  
				  deptArr = deptjsonObj.depts;
				 
				  parseDepts( deptArr );
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
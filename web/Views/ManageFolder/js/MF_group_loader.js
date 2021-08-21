var deptKey_;
var groupKey_;
var userKey_;
var folderKey_;
var lastSelected;
var lastSelectedLsId;
var lastSelectedRsId;
var deptName_;



function parseGroups( groupArr )

{
	$("#mf_group").empty();
	
	for( var i=0; i<groupArr.length; i++ )
	  {
	  		var groupData = groupArr[i];
	  		
	  		var userDataArr = new Array( groupData.userdata.length );
		  	
	  		userDataArr = groupData.userdata;
	  		
	  		var position = "";
	  		
	  		if( i == 0 )
	  		{
	  			position = "top";
	  			
	  		}
	  		else if( i == groupArr.length -1 )
	  		{
	  			position = "bottom";
	  		}
	  		else
	  		{
	  			position = "middle";
	  		}
	  		
	  		
	  		addGroup( groupData, position );
			
	  		parseUsers( userDataArr, groupData );
	  }
	
	$(".mf_group").mCustomScrollbar("update");
	
	
}

function addGroup( groupData, position )
{
	
	//groupData.groupkey = groupData.groupkey.replace(" ","%20");
	
	groupData.groupkey = replaceAll(" ","%20", groupData.groupkey);
	
	
	var groupDiv = '<div class="mf_main" id='+groupData.groupkey+'>';
	
	groupDiv += '<div id="collapse_'+groupData.groupkey+'" class="expand_plus"></div>';
	
	if( position == 'top' )
	{
		groupDiv += '<div class="group_bottom"></div>';
	}
	else if( position == 'bottom' )
	{
		groupDiv += '<div class="group_top"></div>';
	}
	else
	{
		groupDiv += '<div class="group"></div>';
	}
	
	groupDiv +='<div class="mf_main_text" id="'+groupData.groupkey+'_text">'+groupData.groupname+'</div></div>';
	
	
	
	// Click event for Plus and minus icons
	
	$(jqq("collapse_"+groupData.groupkey)).die();
	
	$(jqq("collapse_"+groupData.groupkey)).live('click', function( event ) 
	{	
		event.stopPropagation();
		 
		$(jqq(groupData.groupkey+"_content")).slideToggle('fast', function() 
				{
			if ($(jqq(groupData.groupkey+"_content")).css('display') == 'none') 
			{
				$(jqq("collapse_"+groupData.groupkey)).addClass("expand_plus");
				$(jqq("collapse_"+groupData.groupkey)).removeClass("collapse_minus");
			}
			
			else 
			{
				$(jqq("collapse_"+groupData.groupkey)).removeClass("expand_plus");
				$(jqq("collapse_"+groupData.groupkey)).addClass("collapse_minus");
				
			}
		  });
		
		
	});
	
	// Click event for Group
	$(jqq(groupData.groupkey)).die();
	
	$(jqq(groupData.groupkey)).live('click', function( event ) 
	{		
		lastSelected = "group";		
		
		if( groupData.groupkey != lastSelectedLsId || lastSelectedLsId == "" )
		{
			$(jqq(groupData.groupkey+"_text")).addClass("mf_rowselected");
			
			if(lastSelectedLsId != "")
			{
				$(jqq(lastSelectedLsId+"_text")).removeClass("mf_rowselected");
			}

			groupKey_ = groupData.groupkey;
			
			lastSelectedLsId = groupData.groupkey;
			
			lastSelectedRsId = "";
			
			folderKey_ = "";
			
			listNGDepts();
		}
		
		
		
			
	});
	
	$("#mf_group").append( groupDiv );
	
	
}

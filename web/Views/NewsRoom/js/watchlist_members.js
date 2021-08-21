/* This method is used to add the member to selected watchlist */
function addMemberToWatchList( memberKey )
{
	showAjaxLoader();
	
	var membersObj = new Object();
	
	var members = new Array();
	
	var member  = new Object();
	
	member.userKey = memberKey;
	
	member.watchListId = selectedWatchListId;
	
	members.push( member );
	
	membersObj.members = members;
	
	var membersData = JSON.stringify(membersObj);
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/WLMemberServlet",
		   data:{ 
		        'RequestType': 'AddMembers', 
		        'MembersJSON':membersData,
		    } ,
		   cache:false,
		   success: function( jsonData )
		   {
			   hideAjaxLoader();
			   
			   if( jsonData.result == "success")
			   {
				   fetchWatchListMembers( selectedWatchListId );
				   
				   ShowToast( jsonData.message,2000 );
				   
				   $("#search_result").hide();
				   
			   }
			  
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	 alert("error");
		    	 
		    	//$('#folderassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

/* It is used to fetch the all the members of the given watchlist id */

function fetchWatchListMembers( watchListId )
{
	
	$("#watchlist_del_" + watchListId).hide();

	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/WLMemberServlet",
		   data:{ 
		        'RequestType': 'FetchMembers', 
		        'WatchListId':watchListId,
		    } ,
		   cache:false,
		   success: function( jsonData )
		   
		   {
			   hideAjaxLoader();
			   
			   if( jsonData.result == "success")
			   {
				   
				   var watchListMemArr = new Array( jsonData.members.length );
				  	
				   watchListMemArr = jsonData.members;
					
					parseWatchlistMembers( watchListMemArr );
				   
			   }
			  
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	 alert("error");
		    	 
		    	//$('#folderassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

/* used to parse the watchlist members lists */

function parseWatchlistMembers( watchListMemArr )
{
	$("#memberlist_container").empty();
	
	$("#members_count").text("Members("+watchListMemArr.length+")");
	
	$("#view_mem").text("View Members("+watchListMemArr.length+")");
	
	for( var i=0;i<watchListMemArr.length;i++ )
	{
		var member = watchListMemArr[ i ];
		
		var memberName = member.memberName;
		var compname = member.companyName;
		var memberKey= member.memberKey;
		var photoPath = member.photoPath;
		if(photoPath == 'null' || photoPath == "" )
			photoPath = getBaseURL() +"Views/MyConn/images/no_image_small.png";
		
		var str = '<div class="memberlist_cont" id="member_'+(i+1)+'">';
		str += '<img src="'+photoPath+'" class="img_none">';
		str += '<div class="member_right">';
		str +=  '<div class="text_label" style="font-size:15px; color:#262626;">'+memberName+'</div>';
		str += '<div class="text_label">'+compname+'</div>';
		str	+= '<div class="text_label" id="member_'+(i+1)+'_email">'+memberKey+'</div>';
		str += '</div></div>';
		
		$("#member_"+(i+1)).die("click");
		
		$("#member_"+(i+1)).live("click",function()
		{
			if($("#"+this.id).hasClass("memberlist_cont_selected"))
			{
				$("#"+this.id).removeClass("memberlist_cont_selected");
			}
			else
			{
				$("#"+this.id).addClass("memberlist_cont_selected");
			}
		});
		
		$("#memberlist_container").append( str );
	}
}

/* this method is used to remove the selected members from watchlist */
function removeMembers()
{
	var selectedMemArr = new Array();
	
	$('.memberlist_cont_selected').each(function() 
	{
		selectedMemArr.push( this.id );
	});
	
	if( selectedMemArr.length == 0 )
	{
		ShowToast("Please select the member to delete");
		
		return;
	}
	
	
	showAjaxLoader();
	
	var membersObj = new Object();
	
	var members = new Array();
	
	for( var i=0;i<selectedMemArr.length;i++ )
	{
		var member  = new Object();
		
		member.userKey = $("#"+selectedMemArr[i]+"_email").text();
		
		member.watchListId = selectedWatchListId;
		
		members.push( member );
	}
	
	membersObj.members = members;
	
	var membersData = JSON.stringify(membersObj);
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/WLMemberServlet",
		   data:{ 
		        'RequestType': 'DeleteMembers', 
		        'MembersJSON':membersData,
		    } ,
		   cache:false,
		   success: function( jsonData )
		   {
			   hideAjaxLoader();
			   
			   if( jsonData.result == "success")
			   {
				   
				   ShowToast( jsonData.message,2000 );
				   
				   fetchWatchListMembers( selectedWatchListId );
			   }
			  
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	 alert("error");
		    	 
		    	//$('#folderassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
	
	
}

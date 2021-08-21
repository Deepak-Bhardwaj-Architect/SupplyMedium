//Used to create new watchlist
function saveWatchList()
{
	if($("#new_watchlist_input").val() != "")
	{
		var watchlistName = $("#new_watchlist_input").val().trim();
		
		var regnKey = $("#regnkey").val();
		var userKey = $("#EmailAddress").val();
		
		showAjaxLoader();
		
		$.ajax({
			   type: "POST",
			   url: getBaseURL()+"/WatchListServlet",
			   data:{ 
			        'RequestType': 'AddWatchLists', 
			        'RegnKey':regnKey,
			        'WatchListName':watchlistName,
			        'UserKey':userKey
			    } ,
			   cache:false,
			   success: function( data )
			   {
				   hideAjaxLoader();
				   
				   $("#add_watchlist_popup").hide();
				   
				   if( data.result == "success")
				   {
					   fetchWatchLists();
					   
					   ShowToast( data.message,2000 );
				   }
				  
			   },
			    error : function(xhr, textStatus, errorThrown) 
			    {
			    	hideAjaxLoader();
			    	
			    	//$('#folderassignerr').text( "Unexpected error occur. Please try again." );
				}
			 });
	}
	else
	{
		$('#new_watchlist_input_err').text("Enter Watchlist Name");
	}
}

// Fetach all the watchlist of the user
function fetchWatchLists()
{
	var regnKey = $("#regnkey").val();
	var userKey = $("#EmailAddress").val();
	
	showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/WatchListServlet",
		   data:{ 
		        'RequestType': 'FetchWatchLists', 
		        'RegnKey':regnKey,
		        'UserKey':userKey
		    } ,
		   cache:false,
		   success: function( jsonData )
		   {
			   hideAjaxLoader();
			   
			   if( jsonData.result == "success")
			   {
				   var watchListArr = new Array( jsonData.watchLists.length );
				  	
				   watchListArr = jsonData.watchLists;
					
					parseWatchlists( watchListArr );
			   }
			  
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	//$('#folderassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
}

// This method is used to parse the watchlist json and set in GUI

function parseWatchlists( watchListArr )
{
	$("#watchlists").empty();
	
	for( var i=0; i<watchListArr.length; i++ )
	 {
	 	var watchListData = watchListArr[i];
	 	
	 	var watchListName = watchListData.watchListname;
	 	
	 	var watchListId   = watchListData.watchListId;
	 	
	 	var str = '<div class="content_label_sub" id="watchlist_'+watchListId+'" style="margin-left:20px; width:160px;">';
	 	
	 	str +='<div class="watchlist_name_lbl" id="watchlist_'+watchListId+'_name">' +watchListName+'</div>';
	 	
	 	str += '<input type="button" style="display:none;" class="delete_watchlist_btn" id="watchlist_del_'+watchListId+'" /><div>';
	 	
	 	$("#watchlists").append(str);
	 	
	 	$("#watchlist_del_"+watchListId).live("click",function(e)
	 	{
	 		e.stopPropagation();
	 		
	 		var divid = this.id;

			var idSplitArr = divid.split("_");

			if (idSplitArr.length > 2) 
			{
				var watchListId = idSplitArr[2];

				deleteWatchList( watchListId );

			}
			
	 		
	 	});
	 	
	 	$("#watchlist_"+watchListId).live("click", function()
	 	{
	 		watchListClicked( this.id );
	 	});	
	 	
	 	$("#watchlist_"+watchListId).on('mouseenter', function(event) {

			var divid = this.id;

			var idSplitArr = divid.split("_");

			if (idSplitArr.length > 1) 
			{
				var watchListId = idSplitArr[1];

				$("#watchlist_del_" + watchListId).show();

			}

		});

		$("#watchlist_"+watchListId).on('mouseleave', function(event) 
		{
			var divid = this.id;

			var idSplitArr = divid.split("_");

			if (idSplitArr.length > 1) 
			{
				var watchListId = idSplitArr[1];

				$("#watchlist_del_" + watchListId).hide();

			}
		});

	 }	
}

/* Used to delete the watchlist */
function deleteWatchList( watchListId )
{
	var regnKey = $("#regnkey").val();
	var userKey = $("#EmailAddress").val();
	
	showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/WatchListServlet",
		   data:{ 
		        'RequestType': 'DeleteWatchLists', 
		        'RegnKey':regnKey,
		        'WatchListId':watchListId,
		        'UserKey':userKey
		    } ,
		   cache:false,
		   success: function( data )
		   {
			   hideAjaxLoader();
			   
			   $("#add_watchlist_popup").hide();
			   
			   if( data.result == "success")
			   {
				   
				   ShowToast( data.message,2000 );
				   
				   $("#watchlist_"+watchListId).remove();
				   
				   newRoomClicked();
				   
			   }
			  
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
		    	
		    	//$('#folderassignerr').text( "Unexpected error occur. Please try again." );
			}
		 });
}
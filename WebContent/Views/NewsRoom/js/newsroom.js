var selectedWatchListId = 0;
var selectedWatchListDivId = 0;
var selectedList = "Newsroom";

function newsroomOnload()
{
	$("#create_watchlist").click( openCreateWatchListPopup );
	
	

	
	$("#view_mem").click( function(){$("#member_container").show();});
	
	$("#newsroom").click( newRoomClicked );
	
	
}

// This method open the create watchlist popup
function openCreateWatchListPopup()
{

	$("#add_watchlist_popup").show();
	
	$("#new_watchlist_input").val("");
	
	$("#new_watchlist_input").focus();
	
	
}



/* This method is called while user click the watchlist */
function watchListClicked( watchListDivId )
{
	
	var idSplitArr = watchListDivId.split("_");

	if (idSplitArr.length > 1) 
	{
		selectedWatchListId = idSplitArr[1];
	}
	
	$("#"+selectedWatchListDivId).removeClass("content_label_selected");
	
	$("#newsroom").removeClass("content_label_selected");
	
	selectedWatchListDivId = watchListDivId;
	
	fetchWatchListMembers( selectedWatchListId );
	
	selectedList = "WatchList";
	
	$("#"+selectedWatchListDivId).addClass("content_label_selected");
	
	$("#watchlist_head").show();
	
	selectedList = $("#"+selectedWatchListDivId+"_name").text();
	
	$("#NR_content_title").text( selectedList );
	
	$("#newfeed").hide();
	
	fetchedFeedCount = 0;
	processing = false;
	pageTop = 0;
	lastFeedId = 0;
	latestFeedId = 0;
	
	$("#feeds").empty();
	
	fetchWatchListFeeds();
}

/* This method is called while user click the newsroom link */
function newRoomClicked()
{
	$("#"+selectedWatchListDivId).removeClass("content_label_selected");
	
	$("#newsroom").addClass("content_label_selected");
	
	selectedList = "Newsroom";
	
	$("#watchlist_head").hide();
	
	$("#newfeed").show();
	
	$("#NR_content_title").text( selectedList );
	
	fetchedFeedCount = 0;
	processing = false;
	pageTop = 0;
	lastFeedId = 0;
	latestFeedId = 0;
	
	$("#feeds").empty();
	
	fetchFeeds();
}

/* user to get the members to add in the watch list */
function getSearchConnections( searchText )
{
	if( searchText == "" )
	{
		$("#search_result").hide();
		
		return;
	}
		
	var userKey = $("#EmailAddress").val();
	
	showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/MyConnNetworkServlet",
		   data:{ 
		        'RequestType': 'SearchMyConnections', 
		        'UserKey':userKey,
		        'SearchText':searchText,
		    } ,
		   cache:false,
		   success: function( jsonData )
		   {
			   hideAjaxLoader();
			   
			   if( jsonData.result == "success")
			   {
				   var connectionArr = new Array( jsonData.connections.length );
				  	
				   connectionArr = jsonData.connections;
					
				   parseSearchConnections( connectionArr );
				   
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

/* It is used to parse the search connections and place in dropdown */
function parseSearchConnections( connectionArr ) 
{
	var userKey = $("#EmailAddress").val();
	
	$("#search_result").empty();
	
	$("#search_result").show();
	
	for( var i=0;i<connectionArr.length;i++ )
	{
		var connectionData = connectionArr[ i ];
		
		var userName = "";
		var compname = "";
		var email  = "";
		var photoPath = connectionData.photoPath;
		if(photoPath == 'null' || photoPath == "" )
			photoPath = getBaseURL() +"Views/MyConn/images/no_image_small.png";
		
		if( connectionData.fromUserKey!= userKey )
		{
			userName = connectionData.fromUserName;
			compname = connectionData.fromCompName;
			email  = connectionData.fromUserKey;
		}
		else
		{
			userName = connectionData.toUserName;
			compname = connectionData.toCompName;
			email  = connectionData.toUserKey;
		}
		
		var str = '<div class="listcontainer" id="search_conn_'+i+'">';
		str += '<div class="img_right">';
		str += '<img src="'+photoPath+'" class="listimg">';
		str += '</div>';
		str += '<div class="list_right">';
		str += '<div class="name" id="search_conn_'+i+'_name">'+userName+'</div>';
		str += '<div class="cont" id="search_conn_'+i+'_compName">'+compname+'</div>';
		str += '<div class="cont" id="search_conn_'+i+'_email">'+email+'</div>';
		str += '</div>';
		str += '<div class="listseperator"></div>';
		str += '</div>';
		
		$("#search_result").append( str );
		
		$("#search_conn_"+i).die("click");
		
		$("#search_conn_"+i).live("click",function()
		{
			var memberKey = $("#"+this.id+"_email").text();
			
			addMemberToWatchList( memberKey );
			
			$("#search_result").hide();
			
			$("#search_mem_textbox").val("");
		});
	}
}


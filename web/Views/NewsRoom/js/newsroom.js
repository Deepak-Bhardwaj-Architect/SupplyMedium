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

function likes_comments( id ) 
{
    var feedContent="";
    var lk_cmnt=null;
    var lk=0;
    var cmnt=0;
    $.ajax({
		type : "POST",
                dataType: 'text',
		url : "dilbag",
		data : {
			'id' : id
		},		
		success : function(data)
		{
                    lk_cmnt=data.toString().split("*");
                    if(lk_cmnt.length===2)
                    {
                        lk=lk_cmnt[0];
                        cmnt=lk_cmnt[1];
                    }   
                    feedContent +="<div style='float:right;margin-top:-30px;'>";
                        feedContent +="<div style='background:#FF9933;color:white;border:1px solid #F8F8F8;float:left;text-align:center;cursor:pointer;' onclick=\"javascript:alert('clicked');save_like_comment('"+id+"','like');\"'>"+lk+" Like</div>";
                        feedContent +="<div style='background:#FF9933;color:white;border:1px solid #F8F8F8;float:left;text-align:center;cursor:pointer;' onclick=\"javascript:hide_show('cmnt_"+id+"');select_comment('"+id+"');\">"+cmnt+" Comment</div>";
                        feedContent +="<div style='background:#FF9933;color:white;width:50px;border:1px solid #F8F8F8;float:left;text-align:center;cursor:pointer;' onclick=\"javascript:hide_show('optn_"+id+"');\">Action</div>";
                            feedContent +="<ul id='optn_"+id+"' style='background:#FF9933;float:left;display:none;'>";
                                feedContent +="<li style='background:#FF9933;color:white;width:40px;border:1px solid #F8F8F8;float:left;text-align:center;cursor:pointer;'>Hide</li>";
                                feedContent +="<li style='background:#FF9933;color:white;width:40px;border:1px solid #F8F8F8;text-align:center;cursor:pointer;'>Mute</li>";
                                feedContent +="<li style='background:#FF9933;color:white;width:40px;border:1px solid #F8F8F8;text-align:center;cursor:pointer;'>Spam</li>";
                            feedContent +="</ul>";                        
                    feedContent +="</div>";
                    feedContent +="<div style='background:#F8F8F8;float:left;width:650px;display:none;padding:15px;' id='cmnt_"+id+"'>";
                        feedContent +="<textarea id='cmntddd_"+id+"' placeholder='Write comment' style='width:600px;height:50px;background:#FFFFFF;'></textarea>";
                        feedContent +="<div style='background:#FF9933;color:white;width:30px;border:1px solid #F8F8F8;float:right;text-align:center;cursor:pointer;vertical-align: bottom;' onclick=\"javascript:save_like_comment('"+id+"',document.getElementById('cmntddd_"+id+"').value);\">Post</div>";
                    feedContent +="<ul id='all_comment_"+id+"' style='background:#F8F8F8;float:left;width:600px;padding-top:15px;'></ul></div>";
                    document.getElementById('mn_'+id).innerHTML=feedContent;                    
                    //return data;
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			//return "0*0";
		}
	});        
}

function save_like_comment(id,cmnt)
{
    document.getElementById("cmntddd_"+id).value="";
    $.ajax({
		type : "POST",
                dataType: 'text',
		url : "dilbag",
		data : {
			'ids' : id,
                        'cmnt':cmnt
		},		
		success : function(data)
		{
                    //alert(data)
                    select_comment(id)
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			//alert('error')
		}
	}); 
}
function select_comment(id)
{
    $.ajax({
		type : "POST",
                dataType: 'text',
		url : "dilbag",
		data : {
			'ids' : id,
                        'slct_cmnt':'slct_cmnt'
		},		
		success : function(data)
		{
                    
                   document.getElementById('all_comment_'+id).innerHTML=data;                    
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			alert('error'+errorThrown);
		}
	});
}

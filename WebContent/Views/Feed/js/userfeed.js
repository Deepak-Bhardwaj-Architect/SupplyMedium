var fetchedFeedCount = 0;

var processing = false;

var pageTop = 0;

var lastFeedId = 0;

var isValidExt = true;

var latestFeedId = 0;


function userFeedOnload()
{
			
			$("#content_loader").hide();
			
			$("#user_page_content").show();
			
			fetchFeeds();

			hideAjaxLoader();
			
			$('#feedTitle').focus(function() {
				resetErrLbl();
				});
			
			$('#feedDesc').focus(function() {
				resetErrLbl();
				});
			
			$(document).scroll(
					function(e)
					{

						if (processing)
							return false;

						if (pageTop < $(window).scrollTop()) 
						{
							pageTop = $(window).scrollTop();
						} 
						else 
						{
							return false;
						}
						if ($(window).scrollTop() >= $(document).height()
								- $(window).height() - 500)
						{
							
							processing = true;

							fetchFeeds();

						}
					});
		}

/* This is the click event for post button in view*/
$("#post_feed_btn").click(function() {

	postFeed();
});




$(window).unload(function() 
		{

		    alert("leaving from user page");
		});
/* This method is used to post the feed to server through AJAX call.
 * Once it get the success notification it add the posted feed in 
 * feed list view. It get the failed notification from server it 
 * shows the failed message. */

function postFeed() {

	var form = $("#user_feed_frm");

	var data = new FormData();

	var feedTitle = $('#feedTitle').val();
	
	var feedDesc = $('#feedDesc').val();

	var regnKey = $('#regnkey').val();

	var userKey = $('#EmailAddress').val();
	
	if( feedTitle == "" )
	{
		setError("Enter feed title");
		
		return;
	}
	else if( feedDesc == "" )
	{
		setError("Enter feed Description");
		
		return;
	}
	
	showAjaxLoader();
	
	data.append('RequestType', 'NewFeed');

	data.append('Feed', feedDesc);
	
	data.append('FeedTitle', feedTitle);

	data.append('RegnKey', regnKey);
	
	data.append('UserKey', userKey);

	data.append('FeedImage', document.user_feed_frm.new_feed_image.files[0]);
	
	$.ajax({
		type : "POST",
		url : form.attr('action'),
		data : data,
		contentType : false,
		processData : false,
		success : function(data) 
		{
			hideAjaxLoader();

			if (data.result == "success")
			{

				$('#feedTitle').val("");

				$('#feedDesc').val("");
				
				$("#file_name_lbl").text( "" );
				
				document.user_feed_frm.reset();

				var feedArr = new Array(data.feeds.length);

				feedArr = data.feeds;

				placeFeeds(feedArr, "top");
				
				showRemoveEmptyMsg();

			}

			else 
			{
				$("#feed_err_lbl").val(data.message);
			}

		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
			
			$("#feed_err_lbl").text("Unexcepted error occurred. Please try again");
		}
	});

}


//It is used to fetch the latest user feeds from server. 
function fetchUserLatestFeeds() {
	//alert("latestFeedId:"+latestFeedId);
	var regnKey = $('#regnkey').val();

	var userKey = $('#EmailAddress').val();

	$("#feed_loader").show();
	
	resetErrLbl();
	
	processing = true;

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/UserFeedServlet",
		data : {
			'RequestType' : 'LatestFetchFeed',
			'RegnKey' : regnKey,
			'UserKey' : userKey,
			'LatestFeedId':latestFeedId,
		},
		cache : false,
		success : function(data) {
			

			if (data.result == "success") 
			{

				if (data.feeds.length == 0) 
				{
					$("#no_more_feed").show();

					processing = false;

					$("#feed_loader").hide();
					
					showRemoveEmptyMsg();
				
					return;

				}

				placeFeeds(data.feeds, "top");
				
				showRemoveEmptyMsg();

			}

			else 
			{
				$("#feed_loader").hide();
				
				processing = false;
				
				$("#feed_err_lbl").val(data.message);
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			processing = false;
			
			$("#feed_loader").hide();
			
			$("#feed_err_lbl").text("Unexcepted error occurred. Please try again");
		}
	});

}


// It is used to fetch the user feeds from server. 10 feeds at a time
function fetchFeeds() {

	var regnKey = $('#regnkey').val();

	var userKey = $('#EmailAddress').val();

	$("#feed_loader").show();
	
	resetErrLbl();
	
	processing = true;

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/UserFeedServlet",
		data : {
			'RequestType' : 'FetchFeed',
			'RegnKey' : regnKey,
			'UserKey' : userKey,
			'StartsFrom' : fetchedFeedCount,
			'Count' : 10,
			'LastFeedId':lastFeedId,
		},
		cache : false,
		success : function(data) {
			

			if (data.result == "success") 
			{

				if (data.feeds.length == 0) 
				{
					$("#no_more_feed").show();

					processing = false;

					$("#feed_loader").hide();
					
					showRemoveEmptyMsg();
					
					return;

				}

				placeFeeds(data.feeds, "bottom");
				
				showRemoveEmptyMsg();

			}

			else 
			{
				$("#feed_loader").hide();
				
				processing = false;
				
				$("#feed_err_lbl").val(data.message);
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			processing = false;
			
			$("#feed_loader").hide();
			
			$("#feed_err_lbl").text("Unexcepted error occurred. Please try again");
		}
	});

}

function showRemoveEmptyMsg()
{
	if( (!$.trim($("#feeds").text()).length && !$("#feeds").children().length))  // Checking div is empty
	{
		$("#no_feeds").show();
	}
	
	else
	{
		$("#no_feeds").hide();
	}
}

/* It is used to delete the user feed */

function deleteFeed(feedId) {
	
	showAjaxLoader();
	
	resetErrLbl();

	var regnKey = $('#regnkey').val();

	var userKey = $('#EmailAddress').val();

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/UserFeedServlet",
		data : {
			'RequestType' : 'DeleteFeed',
			'RegnKey' : regnKey,
			'UserKey' : userKey,
			'FeedId' : feedId
		},
		cache : false,
		success : function(data)
		{
			
			hideAjaxLoader();

			if (data.result == "success")
			{
				ShowToast(data.message, 2000);
				
				$("#feed_"+feedId).slideUp("slow",function ()
				{
					$("#feed_" + feedId).remove();
					
					showRemoveEmptyMsg();
				});

			}

			else 
			{
				$("#feed_err_lbl").val(data.message);
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
			$("#feed_err_lbl").text("Unexcepted error occurred. Please try again");
		}
	});
}


/* It is used to get the feeds as a parameter and form the feed to html
 * elements and place the html in feeds div. If position parameter is 'top'
 * place the feed in top of the div otherwise it place the feed in bottom
 * of the div*/

function placeFeeds(feeds, position) 
{

	for ( var i = 0; i < feeds.length; i++) 
	{
		
		var feed = feeds[i];

		var feedDesc = feed.feedDesc;
		
		var feedTitle = feed.feedTitle;

		var feedId = feed.userFeedId;
		
		//alert("feed id="+feedId);
		
		var imageURL = feed.imageURL;
		
		var createdTime = feed.createdTime;

		var feedContent = "<div id='feed_"
				+ feedId
				+ "' class='feed' style='display:none;'>";
		
		feedContent = feedContent + "<div class='dateDel'>";
		
		feedContent = feedContent + "<div class='date'>" + createdTime
				+ "</div>";
		
		feedContent = feedContent
				+ "<input type='button' class='feed_del' id='feed_del_"
				+ feedId + "' onclick='deleteFeed(" + feedId
				+ ")'style='display:none;'/>";
		
		feedContent = feedContent + "</div>";
		
		feedContent = feedContent + "<div class='feed_head'>" + feedTitle
				+ "</div>";
		
		feedContent = feedContent + "<div class='feed_summary'>";


		if (imageURL != "null")
		{
			feedContent = feedContent + "<img src='" + imageURL
			+ "'  id='thumb_"+feedId+"' onclick='showFeedImage(this.src)' class='feed_image'/>";
			
			$("#thumb_"+feedId).hide();
		}
			
		
		feedContent = feedContent + "<p class='feed_text'>" + feedDesc + "<p>";

		feedContent = feedContent + "</div></div>";
		
		if(parseInt(feedId)>latestFeedId)
			latestFeedId = feedId;
		
		

		if (position == "top")
		{
			$("#feeds").prepend(feedContent);
			
			$("#feed_"+feedId).slideDown();
		}
			
		else
		{
			$("#feeds").append(feedContent);
			
			lastFeedId = feedId;
			
			$("#feed_"+feedId).fadeIn();
			
		}
		if (imageURL != "null")
		{
			createThumbnail( $("#thumb_"+feedId),200,200 );
		}
		
			
		/*var groupDiv = '<div id="group_'+groupKey+'" class="class1">';
		groupDiv += '<div id="groupplusbtn" class="" ></div>';
		
		$("$contentdiv").append(groupDiv);
		
		$("$groupplusbtn").click(plusBtnClicked);*/

		$("#feed_" + feedId).on('mouseenter', function(event) {

			var divid = this.id;

			var idSplitArr = divid.split("_");

			if (idSplitArr.length > 1) 
			{
				var newFeedId = idSplitArr[1];

				$("#feed_del_" + newFeedId).show();

			}

		});

		$("#feed_" + feedId).on('mouseleave', function(event) 
		{
			var divid = this.id;

			var idSplitArr = divid.split("_");

			if (idSplitArr.length > 1) 
			{
				var newFeedId = idSplitArr[1];

				$("#feed_del_" + newFeedId).hide();

			}
		});

		fetchedFeedCount++;

		$("#feed_loader").hide();
		
		
	}
	
	processing = false;
}

/* After selecting the feed image this method called. It is used to get the 
 * file name from browse button and display in label
 */

function getFileName()
{
	
	var ext = $('#new_feed_image').val().split('.').pop().toLowerCase();
	
	var file = $('#new_feed_image')[0].files[0];
	
	$("#file_name_lbl").text( file.name );
	
	if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1)
	{
		$("#feed_err_lbl").text("Invalid extension!");
		
		isValidExt = false;
		
		return;
		
	}
	
	 
	 $("#feed_err_lbl").text("");
	 
	 isValidExt = true;
}


/* This function is used to reset the error label */
function resetErrLbl()
{
	$("#feed_err_lbl").text("");
}

function setError( errorMsg )
{
	$("#feed_err_lbl").text(errorMsg);
}


function showFeedImage( url )
{
	
	showFullSizeImage( url );
}
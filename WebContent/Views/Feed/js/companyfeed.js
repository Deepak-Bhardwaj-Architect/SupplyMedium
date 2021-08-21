var fetchedFeedCount = 0;

var processing = false;

var pageTop = 0;

var lastFeedId = 0;

var isValidExt = true;

/*var myVar=setInterval(
		function()
		{
			fetchCompLatestFeeds();
			},30*1000);*/

function companyFeedOnload()
{
			
			$("#content_loader").hide();
			
			$("#internal_page_content").show();
			
			if($("#post_annomt_flag").val() == 0 )
			{
				$("#newfeed").hide();
				$(".recommend_container").css("margin-top","10px");
			}

			fetchFeeds();
			
			hideAjaxLoader();
			
			
			
			$("#recommend_content").mCustomScrollbar({theme:"dark-thick",scrollInertia:150});
			
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

/* This method is used to post the feed to server through AJAX call.
 * Once it get the success notification it add the posted feed in 
 * feed list view. It get the failed notification from server it 
 * shows the failed message. */

function postFeed() {
	
	var form = $("#company_feed_frm");

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

	//data.append('FeedImage', document.user_feed_frm.new_feed_image.files[0]);
	


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

				var feedArr = new Array(data.feeds.length);

				feedArr = data.feeds;

				placeCompanyFeeds(feedArr, "top");
				
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
			
			alert("exception="+errorThrown,textStatus);
			
			$("#feed_err_lbl").text("Unexcepted error occurred. Please try again");
		}
	});

}

/* this method is decide shoe the empty message or remove the empty message */ 
function showRemoveEmptyMsg()
{
	if( (!$.trim($("#com_feeds").text()).length && !$("#com_feeds").children().length))  // Checking div is empty
	{
		$("#no_feeds").show();
	}
	
	else
	{
		$("#no_feeds").hide();
	}
}



// It is used to fetch the company feeds from server. 10 feeds at a time
function fetchFeeds() {

	var regnKey = $('#regnkey').val();

	var userKey = $('#EmailAddress').val();

	$("#feed_loader").show();
	
	resetErrLbl();
	
	processing = true;
	
	$.ajax({
		
		type : "POST",
		url : getBaseURL() + "/CompanyFeedServlet",
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

				placeCompanyFeeds(data.feeds, "bottom");
				
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

/* It is used to delete the company feed */

function deleteFeed(feedId) {
	
	showAjaxLoader();
	
	resetErrLbl();

	var regnKey = $('#regnkey').val();

	var userKey = $('#EmailAddress').val();

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/CompanyFeedServlet",
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


//It is used to fetch the user feeds from server. 10 feeds at a time
function fetchCompLatestFeeds() {
	
	var regnKey = $('#regnkey').val();

	var userKey = $('#EmailAddress').val();

	$("#feed_loader").show();
	
	resetErrLbl();
	
	processing = true;

	$.ajax({
		type : "POST",
		url : getBaseURL() + "/CompanyFeedServlet",
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

				placeCompanyFeeds(data.feeds, "top");
				
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


/* It is used to get the feeds as a parameter and form the feed to html
 * elements and place the html in feeds div. If position parameter is 'top'
 * place the feed in top of the div otherwise it place the feed in bottom
 * of the div*/

function placeCompanyFeeds(feeds, position) {

	var userKey = $('#EmailAddress').val();
	
	for ( var i = 0; i < feeds.length; i++) 
	{ 
		var feed = feeds[i];

		var feedDesc = feed.feedDesc;
		
		var feedTitle = feed.feedTitle;

		var feedId = feed.companyFeedId;
		
		var userName = feed.userName;
		
		var email 	 = feed.email;
		
		var userPictureURL = feed.userPictureURL;
		
		var createdTime = feed.createdTime;

		var feedContent = "<div id='feed_"
				+ feedId
				+ "' class='feed' style='display:none;'>";
		
		feedContent = feedContent + "<div class='publisher_details'>";
		
		feedContent =feedContent + "<div>";
		
		if( userPictureURL == "null" || userPictureURL == "" )
		{
			feedContent = feedContent + "<img src='Views/Feed/images/no_image_icon.png' class='publisher_photo'/>";
		}
		else
		{
			feedContent = feedContent + "<img src='" + userPictureURL+ "' class='publisher_photo'/>";
		}
		
		feedContent =feedContent +"</div>";
		
		feedContent =feedContent +"<div class='publisher_name'>"+userName+"</div>";
		
		feedContent = feedContent + "<div class='date'>" + createdTime
				+ "</div>";
		
		
		if( userKey == email )
		{
			feedContent = feedContent
			+ "<input type='button' class='feed_del' id='feed_del_"
			+ feedId + "' onclick='deleteFeed(" + feedId
			+ ")'style='display:none;'/>";
		}
		
		feedContent = feedContent + "</div>";
		
		feedContent = feedContent + "<div class='feed_head'>" + feedTitle
				+ "</div>";
		
		feedContent = feedContent + "<div class='feed_summary'>";
		

		feedContent = feedContent + "<p class='feed_text'>" + feedDesc + "<p>";

		feedContent = feedContent + "</div></div>";

		if (position == "top")
		{
			$("#com_feeds").prepend(feedContent);
			
			$("#feed_"+feedId).slideDown();
		}
			
		else
		{
			$("#com_feeds").append(feedContent);
			
			lastFeedId = feedId;
			
			$("#feed_"+feedId).fadeIn();
			
		}
			

		if (userKey == email)
		{

			$("#feed_" + feedId).on('mouseenter', function(event) 
			{

				var divid = this.id;

				var idSplitArr = divid.split("_");

				if (idSplitArr.length > 1) {
					var newFeedId = idSplitArr[1];

					$("#feed_del_" + newFeedId).show();

				}

			});
			

			$("#feed_" + feedId).on('mouseleave', function(event) 
			{
				var divid = this.id;

				var idSplitArr = divid.split("_");

				if (idSplitArr.length > 1) {
					var newFeedId = idSplitArr[1];

					$("#feed_del_" + newFeedId).hide();

				}
			});
		}

		fetchedFeedCount++;

		$("#feed_loader").hide();
		
		
	}
	
	processing = false;
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
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
			
		get_cmpny_folders();	
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
                //alert(userPictureURL);
		
		var createdTime = feed.createdTime;

		var feedContent = "<div id='feed_"
				+ feedId
				+ "' class='feed' style='display:none;width:530px;padding:10px;'>";
		
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
		
		feedContent =feedContent +"<div class='publisher_name' style='width:280px;'>"+userName+"</div>";
		
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
                feedContent = feedContent + "</div>";
                feedContent +="<div id='mn_"+feedId+"' style='height:0px;margin-top:-50px;'>";
                feedContent +="</div>";
		feedContent = feedContent + "</div>";
                likes_comments(feedId);

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
function likes_comments( id ) 
{
    var feedContent="";
    var lk_cmnt=null;
    var lk=0;
    var cmnt=0;
    $.ajax({
		type : "POST",
		url : "dilbag",
		data : {
                        'typ': 'cmpny_fd_lk_cmnt',
			'cmpny_id' : id
		},		
		success : function(data)
		{
                    lk_cmnt=data.toString().split("*");
                    if(lk_cmnt.length===2)
                    {
                        if(lk_cmnt[0]<2)
                        {    
                        lk=lk_cmnt[0]+" Like";
                        }
                        else
                        {
                         lk=lk_cmnt[0]+" Likes";   
                        }
                        if(lk_cmnt[1]<2)
                        {
                         cmnt=lk_cmnt[1]+" Comment";   
                        }   
                        else
                        {
                         cmnt=lk_cmnt[1]+" Comments";   
                        }    
                    }   
                    feedContent +="<div class='dilbag-lca' style='width:45%;'>";
                        feedContent +="<div id='lk_"+id+"' class='dilbag-like' onclick=\"javascript:save_like_comment('"+id+"','like',document.getElementById('lks_cnt_"+id+"').value);\"'>"+lk+"</div><input type='hidden' value='"+lk+"' id='lks_cnt_"+id+"'/>";
                        feedContent +="<div id='cmnt2_"+id+"' class='dilbag-comment' onclick=\"javascript:hide_show('cmnt_"+id+"');select_comment('"+id+"');\">"+cmnt+"</div><input type='hidden' value='"+cmnt+"' id='cmnts_cnt_"+id+"'/>";
                        feedContent +="<div style='width:100px;float:left;'>";
                            //feedContent +="<div class='dilbag-action' onclick=\"javascript:hide_show('optn_"+id+"');\">Action</div>";
                            feedContent +="<select class='dilbag-action' id='optn_"+id+"'>";
                                feedContent +="<option class='dilbag-action-list'>-Action-</option>";
                                feedContent +="<option class='dilbag-action-list'>Hide</option>";
                                feedContent +="<option class='dilbag-action-list'>Mute</option>";
                                feedContent +="<option class='dilbag-action-list'>Spam</option>";
                            feedContent +="</select>";   
                        feedContent +="</div>";
                    feedContent +="</div>";
                    feedContent +="<div style='background:#F8F8F8;float:left;width:650px;display:none;padding:15px;' id='cmnt_"+id+"'>";
                        feedContent +="<textarea id='cmntddd_"+id+"' placeholder='Write comment' style='width:600px;height:50px;background:#FFFFFF;'></textarea>";
                        feedContent +="<div style='background:#FF9933;color:white;width:30px;border:1px solid #F8F8F8;float:right;text-align:center;cursor:pointer;vertical-align: bottom;' onclick=\"javascript:save_like_comment('"+id+"',document.getElementById('cmntddd_"+id+"').value,document.getElementById('cmnts_cnt_"+id+"').value);\">Post</div>";
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

function save_like_comment(id,cmnt,cnt)
{
    
    var cnt2=parseInt(cnt)+1;
    var val="";
    if(cmnt==="like")
    {
      if(cnt2<2)
      {
          val=cnt2+" Like";
          document.getElementById('lks_cnt_'+id).value=cnt2;
      }
      else
      {
         val=cnt2+" Likes"; 
         document.getElementById('lks_cnt_'+id).value=cnt2;
      }    
      document.getElementById("lk_"+id).innerHTML=val;  
    }
    else
    {
      if(cnt2<2)
      {
          val=cnt2+" Comment";
          document.getElementById('cmnts_cnt_'+id).value=cnt2;
      }
      else
      {
         val=cnt2+" Comments"; 
         document.getElementById('cmnts_cnt_'+id).value=cnt2;
      }    
      document.getElementById("cmnt2_"+id).innerHTML=val;  
    }    
    document.getElementById("cmntddd_"+id).value="";
    $.ajax({
		type : "POST",
		url : "dilbag",
		data : {
			'typ': 'sv_cmpny_fd_lk_cmnt',
                        'cmpny_id' : id,
                        'cmpny_cmnt':cmnt
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
		url : "dilbag",
		data : {
                        'typ': 'opn_cmpny_fd_cmnt',
			'cmpny_id' : id                        
		},		
		success : function(data)
		{
                    //alert(data);
                   document.getElementById('all_comment_'+id).innerHTML=data;                    
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			alert('error'+errorThrown);
		}
	});
}
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
                 get_user_folders();                       
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
//                feedDesc.replace("&cln",":");
//                alert(feedDesc);
                
		
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
                        if((imageURL.substring(imageURL.lastIndexOf(".")+1)==="xls"||(imageURL.substring(imageURL.lastIndexOf(".")+1)==="xlsx")))
                        {
                         feedContent = feedContent + "<img src='http:\\\\localhost:8080\\SupplyMedium\\Views\\NewsRoom\\images\\excel.png'  id='thumb_"+feedId+"' onclick='showFeedImage(this.src)' class='feed_image'/>";
			  $("#thumb_"+feedId).hide();   
                        }
                        else if((imageURL.substring(imageURL.lastIndexOf(".")+1)==="doc"||(imageURL.substring(imageURL.lastIndexOf(".")+1)==="docx")))
                        {
                            feedContent = feedContent + "<img src='http:\\\\localhost:8080\\SupplyMedium\\Views\\NewsRoom\\images\\word.png'  id='thumb_"+feedId+"' onclick='showFeedImage(this.src)' class='feed_image'/>";
			    $("#thumb_"+feedId).hide(); 
                        }
                        else if((imageURL.substring(imageURL.lastIndexOf(".")+1)==="pdf"))
                        {
                            feedContent = feedContent + "<img src='http:\\\\localhost:8080\\SupplyMedium\\Views\\NewsRoom\\images\\pdf.png'  id='thumb_"+feedId+"' onclick='showFeedImage(this.src)' class='feed_image'/>";
			    $("#thumb_"+feedId).hide(); 
                        }
                        else
                        {
                        feedContent = feedContent + "<img src='" + imageURL
			+ "'  id='thumb_"+feedId+"' onclick='showFeedImage(this.src)' class='feed_image'/>";
			$("#thumb_"+feedId).hide();
                        }
		}
			
		
		feedContent = feedContent + "<p class='feed_text'>" + feedDesc + "<p>";
                feedContent = feedContent + "</div>";
                feedContent +="<div id='mn_"+feedId+"' style='height:0px;margin-top:-50px;'>";
                feedContent +="</div>";
		feedContent = feedContent + "</div>";
                
                likes_comments(feedId);
		
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
	
	if($.inArray(ext, ['gif','png','jpg','jpeg','doc','docx','xls','xlsx','pdf']) == -1)
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
function likes_comments( id ) 
{
    var feedContent="";
    var lk_cmnt=null;
    var lk="Like";
    var cmnt="Comment";
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
                    feedContent +="<div class='dilbag-lca'>";
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
                    //alert(data);
                   document.getElementById('all_comment_'+id).innerHTML=data;                    
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			alert('error'+errorThrown);
		}
	});
}
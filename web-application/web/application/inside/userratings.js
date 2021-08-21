/**
 * Ratings
 */

/* Get the Comany List for this company */

function userRatingsOnload()
{
	hideAjaxLoader();

	$(".rattingvaluebigmain").jRating(
	{
		isDisabled : true,
		type : 'big'
	});

	$(".rattingvaluesmall").jRating(
	{
		isDisabled : true,
		type : 'small'
	});

	$(".ratingscompanylistInner").mCustomScrollbar(
	{
		autoHideScrollbar : false,
		theme : "dark-thick",
		scrollInertia : 150
	});

	$("#ratingreviewlist").mCustomScrollbar(
	{
		autoHideScrollbar : false,
		theme : "dark-thick",
		scrollInertia : 150
	});

	$("#btnratingswritereview").click(function()
	{
		$("#popup_review_title").val('');
		$("#popup_rating_review").val('');
		
		SM.Network.UserRatings.currentRatingTemp = 0;
		$("#rattingvaluebigmainpopup").text('');
		$("#rattingvaluebigmainpopup").jRating(
		{
			isDisabled : false,
			type : 'big',
			onClick : function(element, rate)
			{
				SM.Network.UserRatings.currentRatingTemp = rate;
			}

		});
		$("#UserRatingsEditPopup").show();

	});

	setTimeout(function()
	{
		$("#ratingreviewlist").mCustomScrollbar("update");
		$(".ratingscompanylistInner").mCustomScrollbar("update");

	}, 100);

	// Get the List of Company List on Load
	SM.Network.UserRatings.getCompanyList();

}


SM.Network.UserRatings.getCompanyList = function()
{
	showAjaxLoader();

	var regnkey = $("#regnkey").val();

	$.ajax(
	{
		type : "POST",
		url : getBaseURL() + "/FetchCompanyRatingServlet",
		data :
		{
			'regn_rel_key' : regnkey
		},
		cache : false,
		success : function(data)
		{
			hideAjaxLoader();

			if (data.result == "success")
			{
				$("#ratingscompanylistcontainer").text('');
				
				if(data.UserRatingList.length == 0 )
				{
					
					$(".ratingsreview").hide();
				}
				else
				{
					$(".ratingsreview").show();
					
					for ( var i = 0; i < data.UserRatingList.length; i++)
					{
						var cmpInfo = data.UserRatingList[i];
						if (i == 0)
						{
							SM.Network.UserRatings.AddCompanyList(cmpInfo._regn_rel_key.companyPhoneNo_, cmpInfo._company_name,
									cmpInfo._logoPath, cmpInfo._avgRating, cmpInfo._rating, "selectedItem",cmpInfo._company_id, false);
							
							SM.Network.UserRatings.getCompanyReviewList(cmpInfo._regn_rel_key.companyPhoneNo_, cmpInfo._company_name,
									cmpInfo._logoPath, cmpInfo._avgRating, cmpInfo._rating,cmpInfo._company_id, 'firstone');
						}
						else
						{
							SM.Network.UserRatings.AddCompanyList(cmpInfo._regn_rel_key.companyPhoneNo_, cmpInfo._company_name,
									cmpInfo._logoPath, cmpInfo._avgRating, cmpInfo._rating, " ",cmpInfo._company_id, false);
						}
					}
				}
				
				

			}
		},
		error : function(xhr, textStatus, errorThrown)
		{
			hideAjaxLoader();
			ShowToast("Unexcepted error. Please try again.");
		}
	});
};

SM.Network.UserRatings.AddCompanyList = function(companykey, companyname, companylogpath, ratingvalue, numberofrating,
		itemselected,companyid, isreturn)
{     
        //alert(companyid); 
	companylogpath = companylogpath == "null" || companylogpath == undefined || companylogpath == '' ? getBaseURL()
			+ 'Views/UserRatings/images/ratings_company_nologo_Large.png' : companylogpath;

	var temp = '<div class="ratingcompanylistrow ' + itemselected
			+ '" onclick="SM.Network.UserRatings.getCompanyReviewList('' + companykey + '','' + companyname
			+ '','' + companylogpath + '','' + ratingvalue + '','' + numberofrating + '','' + companyid + '',this)">'
			+ '<div class="ratinglistcompanylog">'
			+ '<div class="ratingcompanyimageholder" style="width: 46px;height: 46px;margin: 0px;">' + '<img src="'
			+ companylogpath + '" />' + '</div>' + '</div>' + '<div class="ratingcomapnydetails">' + '<div class="ratting_row">'
			+ '<label class="fontsize14px">' + companyname + '</label>' + '</div>' + '<div class="ratting_row">'
			+ '<div class="rating_star_small_base rating_star_' + ratingvalue + '"></div>' + '</div>'
			+ '<div class="ratting_row">' + '<label class="fontsize12px">Based on ' + numberofrating + ' ratings</label>'
			+ '</div>' + '</div>' + '</div>';

	var textvalue = $("#ratingscompanylistcontainer").text();
	temp = textvalue.length > 14 ? '<div class="ratingcompanyrowsepartor"></div>' + temp : temp;
	if (isreturn)
	{
		return temp;
	}
	else
	{
		$("#ratingscompanylistcontainer").append(temp);
	}

};

SM.Network.UserRatings.getCompanyReviewList = function(companykey, companyname, companylogpath, ratingvalue, numberofrating,companyid,
		thisdiv)
{
	SM.Network.UserRatings.currentCompanyData.companyKey = companykey;
	SM.Network.UserRatings.currentCompanyData.companyName = companyname;
        SM.Network.UserRatings.currentCompanyData.companyid = companyid;
	SM.Network.UserRatings.currentCompanyData.companylog = companylogpath;
	SM.Network.UserRatings.currentCompanyData.ratingvalue = ratingvalue;
	SM.Network.UserRatings.currentCompanyData.numberofarting = numberofrating;

	$(".ratingcompanyname").text(companyname);
	$(".rattingvaluebigmain").attr('data-average', ratingvalue);
	$(".rattingvaluebigmain").text('');
	$(".rattingvaluebigmain").jRating(
	{
		isDisabled : true,
		type : 'big'
	});
	$(".rating_number_value_main").text(numberofrating);
	$("#ratingcompanyimageholder").empty();

	if ( companylogpath == "null" || companylogpath == undefined || companylogpath == '' )
	{
		document.getElementById("rattingcompanyimagefilemain").innerHTML = '<img src="' + getBaseURL()
				+ 'Views/UserRatings/images/ratings_company_nologo_Large.png">';
	}
	else
	{

		document.getElementById("rattingcompanyimagefilemain").innerHTML = '<img  src="' + companylogpath + '">';
	}
	
	

	if (thisdiv != "firstone")
	{

		$(".ratingcompanylistrow").removeClass("selectedItem");

		$(thisdiv).addClass("selectedItem");
	}

	SM.Network.UserRatings.getCompanyReviewListfromDB();

};

SM.Network.UserRatings.getCompanyReviewListfromDB = function()
{
	showAjaxLoader();

	$.ajax(
	{
		type : "POST",
		url : getBaseURL() + "/FetchUserRatingServlet",
		data :
		{
			'regn_rel_key' : SM.Network.UserRatings.currentCompanyData.companyKey
		},
		cache : false,
		success : function(data)
		{
			hideAjaxLoader();

			if (data.result == "success")
			{
				$("#ratingreviewlistplaceholder").text('');
				for ( var i = 0; i < data.UserRatingList.length; i++)
				{
					var reviewdata = data.UserRatingList[i];
					
					if( reviewdata._review_title != ""  && reviewdata._comments != "" )
					{
						SM.Network.UserRatings.AddReviewList(reviewdata._review_title, reviewdata._ratings,
								reviewdata._reviewer_name, reviewdata._reviewdateFormated, reviewdata._comments);
					}
					
				}
				
				$("#ratingreviewlist").mCustomScrollbar("update");

			}
		},
		error : function(xhr, textStatus, errorThrown)
		{
			hideAjaxLoader();
			ShowToast("Unexcepted error. Please try again.");
		}
	});
};

SM.Network.UserRatings.AddReviewList = function(review_title, ratingvalue, username, dataofreview, reviewdescription)
{
	var temp = '<div class="ratingreviewrow">' + '<span class="reviewrowtitle">' + review_title
			+ '</span> <div class="rating_star_small_base rating_star_' + ratingvalue + '"></div>'
			+ '<div style="float: none;width: 100%;height: 5px"></div>' + '<span class="ratingreviewinfo">by ' + username
			+ ' -  ' + dataofreview + '</span>' + '<div class="reviewcomments">' + reviewdescription + '</div>' + '</div>';
	$("#ratingreviewlistplaceholder").append(temp);
};

SM.Network.UserRatings.PostReview = function()
{
	var reviewTitle = $("#popup_review_title").val();
	var reviewdescription = $("#popup_rating_review").val();
	
	
	if( reviewTitle == "" && reviewdescription != "" )
	{
		ShowToast("Enter title for review");
		return;
	}
	if( reviewTitle != "" && reviewdescription == "" )
	{
		ShowToast("Enter description for review");
		return;
	}
		
	$("#UserRatingsEditPopup").hide();

	showAjaxLoader();

	$.ajax(
	{
		type : "POST",
		url : getBaseURL() + "/UserRatingsServlet",
		data :
		{
			'regn_rel_key' : SM.Network.UserRatings.currentCompanyData.companyKey,
			'user_profile_key' : $("#EmailAddress").val(),
                        'company_id' :SM.Network.UserRatings.currentCompanyData.companyid,
			'review_title' : reviewTitle,
			'comments' : reviewdescription,
			'ratings' : SM.Network.UserRatings.currentRatingTemp
		},
		cache : false,
		success : function(data)
		{
			hideAjaxLoader();

			if (data.result == "success")
			{
				SM.Network.UserRatings.getCompanyReviewListfromDB();

				$(".rattingvaluebigmain").attr('data-average', data.avg_ratings);
				$(".rattingvaluebigmain").text('');
				$(".rattingvaluebigmain").jRating(
				{
					isDisabled : true,
					type : 'big'
				});

				$(".rating_number_value_main").text(data.no_of_ratings);

				var onclickString = "SM.Network.UserRatings.getCompanyReviewList('"
						+ SM.Network.UserRatings.currentCompanyData.companyKey + "','"
						+ SM.Network.UserRatings.currentCompanyData.companyName + "','"
						+ SM.Network.UserRatings.currentCompanyData.companylog + "','"
						+ data.avg_ratings + "','"
						+ data.no_of_ratings + "',this)";

				var companyratingMain = $("#ratingscompanylistcontainer").find(".selectedItem");				
				companyratingMain.attr('onclick', onclickString);

				var companyrating = $("#ratingscompanylistcontainer").find(".selectedItem").find(".rating_star_small_base");
				companyrating.removeClass();
				companyrating.addClass("rating_star_small_base");
				companyrating.addClass("rating_star_" + data.avg_ratings);

				var companyratingvalue = $("#ratingscompanylistcontainer").find(".selectedItem").find(".fontsize12px");
				companyratingvalue.text("Based on " + data.no_of_ratings + " ratings");
				

			}

			$("#popup_review_title").val('');
			$("#popup_rating_review").val('');
		},
		error : function(xhr, textStatus, errorThrown)
		{
			hideAjaxLoader();
			ShowToast("Unexcepted error. Please try again.");
		}
	});
};

SM.Network.UserRatings.currentRatingTemp = 0;
SM.Network.UserRatings.currentCompanyData =
{
	'companyKey' : '',
	'companyName' : '',
        'companyid' : '',
	'companylog' : '',
	'ratingvalue' : '',
	'numberofarting' : '',
};


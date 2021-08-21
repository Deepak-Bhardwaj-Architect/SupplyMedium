function resetForm(formId)
{
	document.forms[formId].reset();
	
	$("label.error").hide();
	//$(".error").removeClass("error");
	$(".error").addClass("border");
	
}

function getBaseURL() {
    var url = location.href;  // entire url including querystring - also: window.location.href;
    var baseURL = url.substring(0, url.indexOf('/', 14));

    if (baseURL.indexOf('http://localhost') != -1) 
    {
        // Base Url for localhost
        var url = location.href;  // window.location.href;
        var pathname = location.pathname;  // window.location.pathname;
        var index1 = url.indexOf(pathname);
        var index2 = url.indexOf("/", index1 + 1);
        var baseLocalUrl = url.substr(0, index2);

        return baseLocalUrl + "/";
    }
    else 
    {
        // Root Url for domain name
        //return baseURL + "/SupplyMedium/";
    	return baseURL + "/SupplyMedium/";
    }

}


// this is the helper method for sortSelectBox  to sort the select box elements
function NASort(a, b) 
{    
    if (a.innerHTML == 'NA') {
        return 1;   
    }
    else if (b.innerHTML == 'NA') {
        return -1;   
    }       
    return (a.innerHTML > b.innerHTML) ? 1 : -1;
}

// used to sort the select box values
function sortSelectBox( id )
{
	$( '#'+id+' option' ).sort(NASort).appendTo( '#'+id );
}

// this is used to fill the time select box
function fillTimeSelectBox( selectBoxObj )
{
	for( var i=0; i<24; i++ )
	{
		if( i < 10)
		{
			value = "0"+i+":"+"00:00";
			key = "0"+i+":"+"00:00";
		}
		else
		{
			value = i+":"+"00:00";
			key = i+":"+"00:00";
		}
		
		$('<option>').val(key).text(value).appendTo( selectBoxObj );
	}

}

function jqq( myid ) {
	 
   // return "#" + myid.replace( /(:|\.|\[|\]|\@|\s)/g, "\\$1" );
	return "#" + myid.replace(/([;&,\.\+\*\~':"\!\^#$%@\[\]\(\)=>\|])/g, '\\$1');
  
}

function jq( myid ) {
	 
    return "#" + myid.replace( /(:|\.|\[|\])/g, "\\$1" );
 
}

/* This method is used to create the thumbnail image for given height and width */

function createThumbnail( imgObj,width,height )
{
	
	var img = imgObj[0]; // Get my img elem
	
	var pic_real_width, pic_real_height;
	
	
	imgObj .attr("src", $(img).attr("src")).load(function() 
	 {
	        pic_real_width = this.width;  
	        
	        pic_real_height = this.height; 
	        
	       
			 if (width > pic_real_width) { width = pic_real_width; }
				if (height > pic_real_height) { height = pic_real_height; }
	   
			//pick the smaller ratio of width or height, and resize to that
			var ratio = width / pic_real_width;
			if(height / pic_real_height < ratio)
				ratio = height / pic_real_height;
	   
			//resize the image to the new dimensions (rounded to 2 places)
			img.width = Math.round(pic_real_width * ratio * 100)/100;
			img.height = Math.round(pic_real_height * ratio * 100)/100;
			
			imgObj.show();
	   });
}

/* This method is used to check the given value is numeric or not. If 
 * it is numeric return true otherwise return false */

function isNumber(n) 
{
	  return !isNaN(parseFloat(n)) && isFinite(n);
}

/* This methos is used to set the cookies */
function setCookie(cookieName,cookieValue,nDays) 
{
	 var today = new Date();
	 var expire = new Date();
	 if (nDays==null || nDays==0) nDays=1;
	 expire.setTime(today.getTime() + 3600000*24*nDays);
	 document.cookie = cookieName+"="+escape(cookieValue)
	                 + ";expires="+expire.toGMTString();
}

/* this method is used to retrive the cookies value from given cookie name */
function readCookie(cookieName) {
	 var theCookie=" "+document.cookie;
	 var ind=theCookie.indexOf(" "+cookieName+"=");
	 if (ind==-1) ind=theCookie.indexOf(";"+cookieName+"=");
	 if (ind==-1 || cookieName=="") return "";
	 var ind1=theCookie.indexOf(";",ind+1);
	 if (ind1==-1) ind1=theCookie.length; 
	 return unescape(theCookie.substring(ind+cookieName.length+2,ind1));
	}

function replaceAll(find, replace, str) {
	  return str.replace(new RegExp(find, 'g'), replace);
	}

function fetchAd( adjacentDivClassName )
{
	showAjaxLoader();
	
	var regnKey = $("#regnkey").val();
	
	var email = $("#EmailAddress").val();
	
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/AdServlet",
		   dataType: 'json',
		   data: {
			   'RequestType':"FetchAdvertisement",
			   //'RegnKey': '4044081253',
			   'RegnKey': regnKey,
			   'UserKey':email,
			   'NoOfAds':5
			   
			   },
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				  
				   var addArray = new Array();
				   
				   addArray = resJSON.ads;
				   
				   parseAds( addArray );
				   
				   var adjacentDivWidth = parseInt($("."+adjacentDivClassName).css('width').replace("px", "")) ;
				   
				  // var adjacentDivMargin = parseInt($("."+adjacentDivClassName).css('marginLeft').replace("px", ""));
				   
				    var adjacentDivMargin = $('.'+adjacentDivClassName).offset().left;
				   
				   var marginLeft = adjacentDivWidth + adjacentDivMargin;
				   
				   $(".ad_container").css('marginLeft',marginLeft+3 );
				   
				}

				else 
				{
					ShowToast( resJSON.message, 2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader();
			}
		 });
}

function parseAds( adArray )
{
	$("#search_ad").empty();
	
	for( var i=0;i<adArray.length;i++ )
	{
		var ad = adArray[ i ];
		
		var adId = ad.adId;
		
		//var companyKey = ad.companyKey;
		
		//var customerKey = ad.customerKey;
		
		var alternateText = ad.alternateText;
		
		var link = ad.link;
		
		var adImagePath = ad.adImagePath;
		
		var adStr = "";
		
		adStr += '<a href="'+link+'">';
		adStr += '<img src="'+adImagePath+'" class="ad_image" id="ad_'+adId+'" title="'+alternateText+'"/>';
		adStr += '</a>';
		
		$("#search_ad").append( adStr );
		
	}
}



function IsEmail(email) 
{
	  var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return regex.test(email);
}

function convertLocalFromGMT( gmt )
{
	var date = new Date(gmt); 
	date.setTime(date.getTime() - date.getTimezoneOffset()*60*1000);
	
	var dateStr = "";
	
	var year = date.getFullYear();
	var month = date.getMonth();
	var day = date.getDate();
	
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var seconds = date.getSeconds();
	
	if( month < 10 )
		month = "0"+month;
	if( day < 10 )
		day = "0"+day;
	
	if( hours < 10 )
		hours = "0"+hours;
	if( minutes < 10 )
		minutes = "0"+minutes;
	if( seconds < 10 )
		seconds = "0"+seconds;
	
	
	
	dateStr = year+"-"+month+"-"+day+" "+hours+":"+
	minutes+":"+seconds;
	
	return dateStr;
}

function tConvert (time)
{
	  // Check correct time format and split into components
	  time = time.toString ().match (/^([01]\d|2[0-3])(:)([0-5]\d)(:[0-5]\d)?$/) || [time];

	  if (time.length > 1) { // If time format correct
	    time = time.slice (1);  // Remove full string match value
	    time[5] = +time[0] < 12 ? ' AM' : ' PM'; // Set AM/PM
	    time[0] = +time[0] % 12 || 12; // Adjust hours
	  }
	  return time.join (''); // return adjusted time or original string
}

	


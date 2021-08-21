
function addRatings( ratingsCount )
{
	showAjaxLoader();
	
	var ratingsObj = new Object();
	var regnKey = $("#regnkey").val();
	
	ratingsObj.regnKey 		= regnKey; 
	ratingsObj.customerKey 	= selectedCustRegnKey; 
	ratingsObj.starCount  	= ratingsCount/2;
	ratingsObj.transId 		= selectedTransId; 
	
	
	var ratingsData = JSON.stringify(ratingsObj);
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/RatingsServlet",
		   dataType: 'json',
		   data: {
			   'RequestType':"AddRatings",
			   'TransRatings': ratingsData,
			   
			   },
		   success: function( resJSON )
		   {
			   hideAjaxLoader();
			   
			   if(resJSON.result == "success")
				{
				   $("#add_ratings").hide();
				   
				   getCustomers();
				   
				   ShowToast( resJSON.message, 2000 );
				   
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
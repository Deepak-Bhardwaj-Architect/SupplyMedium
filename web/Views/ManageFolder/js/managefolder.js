

$(document).ready(function(){
	
	
	
});


/*This method is used to list all the groups for the given company regn key */
function listAllGroups()
{
	var regnKey = $("#regnkey").val();
	
	showAjaxLoader();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ManageFolderServlet",
		   data:{ 
			    'RequestType':'ListAllGroups',
		        'RegnKey':regnKey,
		    } ,
		   cache:false,
		   success: function( groupData ) 
		   {
			   
			  hideAjaxLoader();
			  
			  listUsers();
			 
			  if(groupData.result == "success")
			  {			  
				  var groupArr = new Array( groupData.groups.length );
				  
				  groupArr = groupData.groups;
				 
				  parseGroups( groupArr );
			  }
			  
			  else
			 {
				  
			 }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	 hideAjaxLoader();
		    	
		    	 $("#profilepic_err").text("Unexcepted error occur. Please try again.");
		    	  
			}
		 });
}

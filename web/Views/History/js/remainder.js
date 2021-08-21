/* used to add the new remainder for selected transaction */
function addReminder()
{
	var reminder = $("#new_remainder").val();
	var dueDate	 = $("#remainder_due_date").val();
	
	var regnKey  = $("#regnkey").val();
	
	
	if( reminder == "" )
	{
		ShowToast( "Enter Reminder message", 2000 );
		return;
	}
	if( dueDate == "")
	{
		ShowToast( "Enter Reminder Due Date", 2000 );
		return;
	}
	
	showAjaxLoader( );
	
	var reminderObj = new Object();
	
	reminderObj.regnKey 	= regnKey; 
	
	reminderObj.customerKey = selectedCustRegnKey;
	
	reminderObj.reminder  	= reminder;
	
	reminderObj.transId 	= selectedTransId;
	
	reminderObj.dueDate		= dueDate; 
	
	var reminderData = JSON.stringify(reminderObj);
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ReminderServlet",
		   dataType: 'json',
		   data: {
			   'RequestType':"AddRemainder",
			   'TransReminder': reminderData,
			   
			   },
		   success: function( resJSON )
		   {
			   hideAjaxLoader( );
			   
			   if(resJSON.result == "success")
				{
				  ShowToast( resJSON.message, 2000 );
				  
				  $("#add_remainder").hide( );
				}

				else 
				{
					ShowToast( resJSON.message, 2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader( );
			}
		 });
}


/* Delete the remainder*/

function deleteRemainder( transRemainderId )
{
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/ReminderServlet",
		   dataType: 'json',
		   data: {
			   'RequestType':"DeleteReminder",
			   'transRemainderId': transRemainderId,
			   
			   },
		   success: function( resJSON )
		   {
			   hideAjaxLoader( );
			   
			   if(resJSON.result == "success")
				{
				  ShowToast( resJSON.message, 2000 );
				  
				  $("#trans_remainder_"+transRemainderId).remove( );
				}

				else 
				{
					ShowToast( resJSON.message, 2000 );
				}
			   
		   },
		   error : function(xhr, textStatus, errorThrown) 
		    {
			   hideAjaxLoader( );
			}
		 });
}
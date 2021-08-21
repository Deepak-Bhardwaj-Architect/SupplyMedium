
$(document).ready(function () {
    // Handler for .ready() called.



});


//Used to create the new Group
function addGroup()
{
//	if($("#new_group_input").val() != "")
//	{
//		var groupName = $("#new_group_input").val().trim();
//		
//		resetAllErrorMsg();
//		
//		showAjaxLoader();
//		
//		var regnKey = $('#regnkey').val();
//		
//		$.ajax({
//			   type: "POST",
//			   url: getBaseURL()+"/GroupMgmtServlet",
//			   data:{ 
//			        'RequestType': 'NewUserGroup', 
//			        'RegnKey':regnKey,
//			        'GroupName':groupName
//			    } ,
//			   cache:false,
//			   success: function( data )
//			   {
//				   hideAjaxLoader();
//				   
//				   $("#add_group_popup").hide();
//				   
//				   if( data.result == "success")
//				   {
//					   ShowToast( data.message,2000 );
//					   
//					   getAllGroups();
//				   }
//				   else
//				   {
//					   $('#groupmgmterr').text(data.message);
//				   }
//				   
//			   },
//			    error : function(xhr, textStatus, errorThrown) 
//			    {
//			    	hideAjaxLoader();
//			    	
//			    	$('#groupmgmterr').text( "Unexpected error occur. Please try again." );
//				}
//			 });
//	}
//	else
//	{
//		$('#new_group_input_err').text("Enter Group Name");
//	}

}

//Used to update the group name
function editGroup()
{

    if ($("#edit_group_input").val() != "")
    {
        var groupKey = $("#grouplistsel option:selected").val();

        var groupName = $("#edit_group_input").val();

        resetAllErrorMsg();

        showAjaxLoader();

        var regnKey = $('#regnkey').val();

        $.ajax({
            type: "POST",
            url: getBaseURL() + "/GroupMgmtServlet",
            data: {
                'RequestType': 'UpdateUserGroup',
                'RegnKey': regnKey,
                'GroupKey': groupKey,
                'GroupName': groupName,
            },
            cache: false,
            success: function (data)
            {
                hideAjaxLoader();

                $("#edit_group_popup").hide();

                if (data.result == "success")
                {

                    ShowToast(data.message, 2000);

                    getAllGroups();
                }
                else
                {
                    $('#groupmgmterr').text(data.message);
                }

            },
            error: function (xhr, textStatus, errorThrown)
            {
                hideAjaxLoader();

                $('#groupmgmterr').text("Unexpected error occur. Please try again.");
            }
        });
    }
    else
    {
        $('#edit_group_input_err').text("Enter Group Name");
    }
}


// Used to delete a group
function deleteGroup()
{


    if ($("#grouplistsel")[0].selectedIndex < 0)
    {
        showWarning("select any group");

        return;
    }

    message = "This operation will delete all items inside the Group, " + $("#grouplistsel option:selected").text() + ". Do you want to continue?";

    showWarning(message);
}


function deleteConfirm()
{
    var groupKey = $("#grouplistsel option:selected").val();

    resetAllErrorMsg();

    showAjaxLoader();

    var regnKey = $('#regnkey').val();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/GroupMgmtServlet",
        data: {
            'RequestType': 'DeleteUserGroup',
            'RegnKey': regnKey,
            'GroupKey': groupKey,
        },
        cache: false,
        success: function (data)
        {
            hideAjaxLoader();

            if (data.result == "success")
            {
                ShowToast(data.message, 2000);

                getAllGroups();
            }
            else
            {
                $('#groupmgmterr').text(data.message);
            }

        },
        error: function (xhr, textStatus, errorThrown)
        {
            hideAjaxLoader();

            $('#groupmgmterr').text("Unexpected error occur. Please try again.");
        }
    });

}

function getAllGroups()
{
    resetAllErrorMsg();

    showAjaxLoader();

    var regnKey = $('#regnkey').val();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/GroupFetchServlet",
        data: {
            'RegnKey': regnKey,
            'RequestType': 'GetAllUserGroups',
        },
        cache: false,
        success: function (data)
        {
            // alert(data.result);
            hideAjaxLoader();

            if (data.result == "success")
            {

                var $select = $('#grouplistsel');

                $select.find('option').remove();

                $.each(data, function (key, value)
                {
                    if (value != "success")
                        $('<option>').val(key).text(value).appendTo($select);
                });


                if ($('select#grouplistsel option').length > 0)
                {
                    sortSelectBox('grouplistsel');

                    $("#grouplistsel option:first-child").attr("selected", true);

                    var groupKey = $("#grouplistsel option:selected").val();

                    getGroupData(groupKey);

                    groupfilter = new filterlist(document.groupmgmtfrm.grouplistsel);

                }
                else
                {
                    $('#groupmgmterr').text("There are no groups to be listed");

                    clearFormData();
                }

            }
            else
            {

                $('#groupmgmterr').text(data.message);
            }

        },
        error: function (xhr, textStatus, errorThrown)
        {
            hideAjaxLoader();

            $('#groupmgmterr').text("Unexpected error occur. Please try again.");
        }
    });
}

function getGroupData(groupKey)
{
    if ($("#grouplistsel")[0].selectedIndex < 0)
    {
        return;
    }

    getNonGroupUsers(groupKey);
    getGroupUsers(groupKey);
    getPrivileges(groupKey);
}

function getNonGroupUsers(groupKey)
{

    resetAllErrorMsg();

    showAjaxLoader();

    var regnKey = $('#regnkey').val();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/UserGroupMapServlet",
        data: {
            'RequestType': 'NonGroupUsers',
            'RegnKey': regnKey,
            'GroupKey': groupKey
        },
        cache: false,
        success: function (data) {
            //hideAjaxLoader();

            if (data.result == "success") {
                var $select = $('#existusersel');

                $select.find('option').remove();

                $.each(data, function (key, value)
                {
                    if (value != "success")
                        $('<option>').val(key).text(value).appendTo($select);
                });

                sortSelectBox('existusersel');

                existingUserfilter = new filterlist(document.groupmgmtfrm.existusersel);

            } else {
                $('#userassignerr').text(data.message);
            }

        },
        error: function (xhr, textStatus, errorThrown) {
            hideAjaxLoader();

            $('#userassignerr').text(
                    "Unexpected error occur. Please try again.");
        }
    });
}


function getGroupUsers(groupKey)
{
    resetAllErrorMsg();

    showAjaxLoader();

    var regnKey = $('#regnkey').val();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/UserGroupMapServlet",
        data: {
            'RequestType': 'FindUser',
            'RegnKey': regnKey,
            'GroupKey': groupKey
        },
        cache: false,
        success: function (data)
        {
            hideAjaxLoader();



            if (data.result == "success")
            {
                var $select = $('#pregroupsel');

                $select.find('option').remove();

                $.each(data, function (key, value)
                {
                    if (value != "success")
                        $('<option>').val(key).text(value).appendTo($select);
                });

                sortSelectBox('pregroupsel');

                groupUserfilter = new filterlist(document.groupmgmtfrm.pregroupsel);
            }
            else
            {
                $('#userassignerr').text(data.message);
            }

        },
        error: function (xhr, textStatus, errorThrown)
        {
            hideAjaxLoader();

            $('#userassignerr').text("Unexpected error occur. Please try again.");
        }
    });
}

//Used to delete all error message currently display in form
function resetAllErrorMsg()
{
    $('#groupmgmterr').text("");
    $('#userassignerr').text("");
    $('#groupprierr').text("");
}


// It remove the all old data from form
function clearFormData()
{

    // Remove the old drop down box datas
    $('#pregroupsel').find('option').remove();

    $('#existusersel').find('option').remove();


    $('#addusers').prop('checked', false);

    $('#delusers').prop('checked', false);

    $('#uploaddoc').prop('checked', false);

    $('#deldoc').prop('checked', false);

    $('#addbuyers').prop('checked', false);

    $('#delbuyers').prop('checked', false);

    $('#accessusermgmt').prop('checked', false);

    $('#postanncemnt').prop('checked', false);

    $('#delanncemnt').prop('checked', false);

    $('#rate').prop('checked', false);

    $('#creategroup').prop('checked', false);

    $('#delgroup').prop('checked', false);

    $('#applythemes').prop('checked', false);

    $('#managefolder').prop('checked', false);


}



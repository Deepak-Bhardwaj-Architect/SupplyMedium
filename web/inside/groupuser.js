/* 
 * 
 * 
 * 
 */
function addUserToGroup()
{ 
   $('#userKey').val($("#existusersel option:selected").val());
   $('#groupKey').val($("#grouplistsel option:selected").val());
   //alert($('#groupKey').val());
   
   $.ajax({
        type: "POST",
        dataType: 'text',
        url: "GroupUser",
        data:
                {
                    'eventType': 'addUserToGroup',
                    'companyKey':$('#companyKey').val(),
                    'groupKey' : $('#groupKey').val(),
                    'userKey' : $('#userKey').val()
                },
        success: function(data)
        {
            //alert(data);
            ShowToast("User Added in Group", 2000);  
            showNonExistingUserInGroup();
            showExistingUserInGroup();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
           // alert("error :"+errorThrown)
        }
    });  
}

function deleteUserFromGroup()
{
    $('#userKey').val($("#pregroupsel option:selected").val());
    $('#groupKey').val($("#grouplistsel option:selected").val());
        $.ajax({
        type: "POST",
        dataType: 'text',
        url: "GroupUser",
        data:
                {
                    'eventType': 'deleteUserFromGroup',
                    'companyKey':$('#companyKey').val(),
                    'groupKey' : $('#groupKey').val(),
                    'userKey' : $('#userKey').val()  
                },
        success: function(data)
        {
            ShowToast("User Removed from Group", 2000);  
            showNonExistingUserInGroup();
            showExistingUserInGroup();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
           // alert("error :"+errorThrown)
        }
    });  
}


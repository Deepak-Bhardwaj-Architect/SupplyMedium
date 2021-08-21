/* 
 * 
 * 
 * 
 */
function addGroup()
{
    $("#groupName").val($("#new_group_input").val().trim());
    if ($("#groupName").val() != "")
    {
        $("#add_group_popup").fadeOut();
        $("#new_group_input").val("");
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "CompanyGroup",
            data:
                    {
                        'eventType': 'addGroup',
                        'companyKey': $('#companyKey').val(),
                        'groupName': $('#groupName').val()
                    },
            success: function(data)
            {
                ShowToast("Group Created", 2000);
                showCompanyGroup();
                //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
            },
            error: function(xhr, textStatus, errorThrown)
            {
                // alert("error :"+errorThrown)
            }
        });
    }
    else
    {
        $('#new_group_input_err').text("Enter Group Name");
    }
}

function updateGroup()
{
    $('#groupKey').val($("#grouplistsel option:selected").val());
    $('#groupName').val($("#edit_group_input").val());
    $("#edit_group_popup").fadeOut();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyGroup",
        data:
                {
                    'eventType': 'updateGroup',
                    'groupKey': $('#groupKey').val(),
                    'groupName': $('#groupName').val()
                },
        success: function(data)
        {
            ShowToast("Group renamed", 2000);
            showCompanyGroup();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            alert("error :" + errorThrown)
        }
    });
}

function deleteGroup()
{
    $('#groupKey').val($("#grouplistsel option:selected").val());
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyGroup",
        data:
                {
                    'eventType': 'deleteGroup',
                    'groupKey': $('#groupKey').val()
                },
        success: function(data)
        {
            ShowToast("Group Deleted", 2000);
            showCompanyGroup();

        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });
}

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
                    'companyKey': $('#companyKey').val(),
                    'groupKey': $('#groupKey').val(),
                    'userKey': $('#userKey').val()
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
                    'companyKey': $('#companyKey').val(),
                    'groupKey': $('#groupKey').val(),
                    'userKey': $('#userKey').val()
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

function checkBrowserIsIe()
{
    var returnValue = navigator.appName;
    var val = returnValue.indexOf("Microsoft");
    return val;
}

function showCompanyGroup()
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyGroup",
        data:
                {
                    'eventType': 'showCompanyGroup',
                    'companyKey': $('#companyKey').val()
                },
        success: function(data)
        {
            //alert("data :"+data);
//            var browser=checkBrowserIsIe();
//            alert(browser);
//            if(browser==-1)
//            {
//                alert('if');
//                document.getElementById("grouplistsel").innerHTML = data;
//            }
//            else
//            {
//                alert('else'+data);
                $("#grouplistsel").html('');
                $("#grouplistsel").append(data);
                //alert(document.getElementById("grouplistsel").innerHTML+":"+data);
            //}
            //alert(document.getElementById("grouplistsel").value)
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });
}
function showNonExistingUserInGroup()
{
    $('#groupKey').val($("#grouplistsel option:selected").val());
    if ($('#groupKey').val() !== "")
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "GroupUser",
            data:
                    {
                        'eventType': 'showNonExistingUserInGroup',
                        'companyKey': $('#companyKey').val(),
                        'groupKey': $('#groupKey').val(),
                    },
            success: function(data)
            {
                //alert("data :"+data);
               // document.getElementById('existusersel').innerHTML = data;
                $("#existusersel").html('');
                $("#existusersel").append(data);
                //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
            },
            error: function(xhr, textStatus, errorThrown)
            {
                alert("error :" + errorThrown)
            }
        });
    }
}
function showExistingUserInGroup()
{
    $('#groupKey').val($("#grouplistsel option:selected").val());
    if ($('#groupKey').val() !== "")
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "GroupUser",
            data:
                    {
                        'eventType': 'showExistingUserInGroup',
                        'companyKey': $('#companyKey').val(),
                        'groupKey': $('#groupKey').val(),
                    },
            success: function(data)
            {
                //alert("data :"+data);
                //document.getElementById('pregroupsel').innerHTML = data;
                $("#pregroupsel").html('');
                $("#pregroupsel").append(data);
                //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
            },
            error: function(xhr, textStatus, errorThrown)
            {
                alert("error :" + errorThrown)
            }
        });
    }
}

function searchGroup()
{

    var groupName = $("#searchgroups").val();

    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyGroup",
        data:
                {
                    'eventType': 'searchGroup',
                    'companyKey': $('#companyKey').val(),
                    'groupName': groupName
                },
        success: function(data)
        {
            document.getElementById('grouplistsel').innerHTML = data;
        },
        error: function(xhr, textStatus, errorThrown)
        {
            // alert("error :"+errorThrown)
        }
    });
}


function searchExistingUsersOnGroupPage()
{

    var groupName = $("#searchExistingGropUser").val();

    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyGroup",
        data:
                {
                    'eventType': 'searchGroup',
                    'companyKey': $('#companyKey').val(),
                    'groupName': groupName
                },
        success: function(data)
        {
            document.getElementById('grouplistsel').innerHTML = data;
        },
        error: function(xhr, textStatus, errorThrown)
        {
            // alert("error :"+errorThrown)
        }
    });
}


function showGroupPrev(groupKey)
{
    //alert(groupKey);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "PermGroup",
        data:
                {
                    'groupKey': groupKey
                },
        success: function(data)
        {
            //alert(data);
            document.getElementById('showGroupPerm').innerHTML = data;
            //$('#groupPermId').show();
        },
        error: function(xhr, textStatus, errorThrown)
        {
            // alert("error :"+errorThrown)
        }
    });
}

function updateGroupPrev()
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "PermGroupUpdate",
        data:
                {
                    'groupKey': document.getElementById('groupPermId').value,
                    'addusers':document.getElementById('addusers').checked,
                    'delusers':document.getElementById('delusers').checked,
                    'addbuyers':document.getElementById('addbuyers').checked,
                    'delbuyers':document.getElementById('delbuyers').checked,
                    'accessusermgmt':document.getElementById('accessusermgmt').checked,
                    'postanncemnt':document.getElementById('postanncemnt').checked,
                    'ratevendor':document.getElementById('ratevendor').checked,
                    'creategroup':document.getElementById('creategroup').checked,
                    'delgroup':document.getElementById('delgroup').checked
                },
        success: function(data)
        {
//            alert(data);
//            document.getElementById('showGroupPerm').innerHTML = data;
            ShowToast("Group privileges updated", 2000);
            //$('#groupPermId').show();
        },
        error: function(xhr, textStatus, errorThrown)
        {
            // alert("error :"+errorThrown)
        }
    });
}
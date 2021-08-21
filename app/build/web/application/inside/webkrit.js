var chatid = "";
var chatboxes = 0;
var chatids = "";// new Array();

$(document).on("click", "a", function (e) {
    showLoading();
});

function isOutSideOrNot(obje) {

	if(obje.checked)
        {
            $('#ven_search_result').hide();
        }
}



function deleteInv(transrfqkey)
{
    var res=confirm("Do you want to delete the invoice no. "+transrfqkey);
    if(res)
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "ajaxDeleteInv.jsp",
            data:
            {
                'transrfqkey': transrfqkey
            },
            success: function (data)
            {
                //alert(data);               
            },
            error: function (xhr, textStatus, errorThrown)
            {
                //alert(errorThrown);
            }
        });
    }
    window.location.href="transactionsInvoice.jsp";
    
}

function lockUnlock(val)
{
    $('#' + val).hide();
    document.getElementById("screen_lock").disabled = false;
    showChatNotification();
    setTimeout(showChatNotification(),300);
}

function showLoading()
{
    $('#webkrit_content_loader').show();
    document.getElementById("screen_lock").disabled = false;
}


function fetchState(countryCode)
{
    var countryKeyNCode = countryCode.split("*");
    //    alert("hi");
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "FetchStateByCountry",
        data:
        {
            'fetchFor': 'country',
            'countryCode': countryKeyNCode[0]
        },
        success: function (data)
        {
            //            alert(data);
            document.getElementById('select_0_container').innerHTML = data;
            document.getElementById('countryregion_0').value = countryKeyNCode[0];
            document.getElementById('countrycode').value = countryKeyNCode[1];
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function fetchStateForAddAddress1(countryCode)
{
    //alert(countryCode);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "FetchStateByCountry",
        data:
        {
            'fetchFor': 'address1',
            'countryCode': countryCode
        },
        success: function (data)
        {
            document.getElementById('select_1_container').innerHTML = data;
        //            alert(data);

        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function fetchStateForAddAddress2(countryCode)
{
    //alert(countryCode);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "FetchStateByCountry",
        data:
        {
            'fetchFor': 'address2',
            'countryCode': countryCode
        },
        success: function (data)
        {
            document.getElementById('select_2_container').innerHTML = data;
        //            alert(data);

        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function fetchCompany(countryCode)
{
    var countryKeyNCode = countryCode.split("*");
    //    alert("hi "+countryKeyNCode[0]);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "FetchCompanyByCountry",
        data:
        {
            'countryCode': countryKeyNCode[0]
        },
        success: function (data)
        {
            //alert("@" + data + "@");
            $('#companyname').html(data);
//            document.getElementById('companyname').innerHTML = data;

        },
        error: function (xhr, textStatus, errorThrown)
        {
        //            alert("Error : " + errorThrown);
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function validatePhoneNo(phoneNo)
{
    //    alert(phoneNo);
    var isvalid = $("#phone").valid();

    if (!isvalid)
    {
        return;
    }
    $('#companyidcheck').addClass('loader');
    $.ajax({
        type: "POST",
        url: "VerifiactionAndValidatioin",
        data: {
            'verify': "phoneNo",
            'phoneNo': phoneNo
        },
        cache: false,
        success: function (result)
        {
            //            alert(result)
            if (result == "1")
            {
                $('#phonenocheck').removeClass('loader');
                $('#phonenocheck').removeClass('validtick');
                $('#phonenocheck').addClass('invalidsymbol');
                $('#phoneerr').text('Phone number already exist.');
                $('#phoneerr').show();
                $('#phoneexist').val("1");
            }
            else
            {
                $('#phonenocheck').removeClass('loader');
                $('#phonenocheck').removeClass('invalidsymbol');
                $('#phonenocheck').addClass('validtick');
                $('#phoneerr').text('');
                $('#phoneerr').hide();
                $('#phoneexist').val("0");
            }

        },
        error: function (xhr, textStatus, errorThrown) {
            //            alert(errorThrown);
            $('#phonenocheck').removeClass('loader');
            $('#phonenocheck').removeClass('validtick');
            $('#phonenocheck').addClass('invalidsymbol');
            $('#phoneerr').text('Request failed. Try again.');
            $('#phoneerr').show();
            $('#phoneexist').val("1");
        }
    });
}


function validateCompanyId(companyId)
{
    var isvalid = $("#companyid").valid();
    //alert('1');
    if (!isvalid)
    {
        //alert('2');
        return;
    }
    //alert('3');
    $('#companyidcheck').addClass('loader');
    //alert('4');
    $.ajax({
        type: "POST",
        url: "VerifiactionAndValidatioin",
        data: {
            'verify': "companyId",
            'companyId': companyId
        },
        cache: true,
        success: function (result)
        {
            if (result === "1")
            {
                $('#companyidcheck').removeClass('loader');
                $('#companyidcheck').removeClass('validtick');
                $('#companyidcheck').addClass('invalidsymbol');
                $('#companyiderr').text('Company Id already exist.');
                $('#companyiderr').show();
                $('#companyidexist').val("1");
            }
            else
            {
                $('#companyidcheck').removeClass('loader');
                $('#companyidcheck').removeClass('invalidsymbol');
                $('#companyidcheck').addClass('validtick');
                $('#companyiderr').text('');
                $('#companyiderr').hide();
                $('#companyidexist').val("0");
            }

        },
        error: function (xhr, textStatus, errorThrown) {
//            alert("1 : "+errorThrown);
            $('#companyidcheck').removeClass('loader');
            $('#companyidcheck').removeClass('validtick');
            $('#companyidcheck').addClass('invalidsymbol');
            $('companyiderr').text('Request failed. Try again.');
            $('#companyiderr').show();
            $('#companyidexist').val("1");
        }
    });
}


function validateEmail(email)
{
    //alert('enter');
    var isvalid = $("#email").valid();

    if (!isvalid)
    {
        return;
    }

    $('#emailcheck').addClass('loader');

    var companyName = "";

    if ($("#RequestType").val() == 'NewRegistration')  // For company Registration 
    {
        companyName = $("#companyname").val();
    }
    else if ($("#RequestType").val() == 'NewSignup')  // Admin add new user
    {
        companyName = $("#companyname").val();
    }
    else  // For user sign up
    {
        companyName = $('#companyname option:selected').text();
    }

    $.ajax({
        type: "POST",
        url: "VerifiactionAndValidatioin",
        data: {
            'verify': "email",
            'email': email
        },
        cache: false,
        success: function (result)
        {
            //alert(result);
            $('#emailcheck').removeClass('loader');

            if (result === "0")  // valid email
            {
                $('#emailcheck').removeClass('invalidsymbol');
                $('#emailcheck').addClass('validtick');
                $('#emailerr').text('');
                $('#emailerr').hide();
                $('#emailexist').val("0");
            }

            else
            {
                var errorMsg = "";
                errorMsg = "Email address already exist";
                $('#emailcheck').removeClass('loader');
                $('#emailcheck').removeClass('validtick');
                $('#emailcheck').addClass('invalidsymbol');
                $('#emailerr').text(errorMsg);
                $('#emailerr').show();
                $('#emailexist').val("1");
            }

        },
        error: function (xhr, textStatus, errorThrown) {
            $('#emailcheck').removeClass('loader');
            $('#emailcheck').removeClass('validtick');
            $('#emailcheck').addClass('invalidsymbol');
            $('#emailerr').text('Request failed. Try again.');
            $('#emailerr').show();
            $('#emailexist').val("1");
        }
    });
}

function showImagePreview(input, id)
{
    if (input.files && input.files[0])
    {
        var filerdr = new FileReader();
        filerdr.onload = function (e)
        {
            $('#' + id).attr('src', e.target.result);
        }
        filerdr.readAsDataURL(input.files[0]);
    }
}

var lastSelectedUser = null;
var lastUserStatus = null;
var selectedUserKey = null;
function selectUserRow(select, userKey, status, delstat, accMan)
{
    //alert(""+delstat);
    if (delstat == "yes")
        delstat = 'display:block;';
    else if (delstat == "no")
        delstat = 'display:none;';
    if (accMan == "yes")
        accMan = 'display:block;';
    else if (accMan == "no")
        accMan = 'display:none;';
    if (lastSelectedUser !== null)
    {
        $('#row_' + lastSelectedUser).removeClass("row_selected");
        $('#column_' + lastSelectedUser).html(lastUserStatus);
        $('#action_' + lastSelectedUser).html("");
    }
    lastSelectedUser = select;
    lastUserStatus = status;
    var options = "";
    if (status === "Activated")
    {
        options += "<option value='Activated'>Active</option>" +
    "<option value='Blocked'>Block</option>";
    }
    else if (status === "Blocked")
    {
        options += "<option value='Blocked'>Block</option>" +
    "<option value='Activated'>Active</option>";
    }
    else if (status === "Rejected")
    {
        options += "<option value='Rejected'>Reject</option>" +
    "<option value='Activated'>Active</option>" +
    "<option value='Blocked'>Blocked</option>";
    }
    else
    {
        options += "<option value='Pending'>Pending</option>" +
    "<option value='Activated'>Active</option>" +
    "<option value='Blocked'>Blocked</option>";
    }
    var actionImages = "<img src='inside/user_edit_btn.png' style='cursor:pointer;z-index:100;margin-right:10px;float: left;" + accMan + "' title='Update User Settings' onclick=\"javascript:showUserSettingForAdmin('" + userKey + "');\">" +
    "<img src='inside/user_delete_btn.png' style='cursor:pointer;z-index:101;float: left;" + delstat + "' title='Delete User' onclick=\"javascript:$('#warning_container').show();\">";
    $('#row_' + select).addClass("row_selected");
    $('#column_' + select).html("<select id='select_" + select + "' style='z-index:200;width:120px;margin-right:5px;margin-top:5px;'>" + options + "</select>" +
        "<input type='button' style='cursor:pointer' class='status_save_btn'" +
        "title='Save Status' onclick=\"javascript:updateUserStatus('" + userKey + "',$('#select_" + select + "').val(),'" + select + "');\">");
    $('#action_' + select).html(actionImages);
    selectedUserKey = userKey;
}

function updateUserStatus(userKey, status, select)
{
    lastSelectedUser = null;
    lastUserStatus = null;
    $('#row_' + select).removeClass("row_selected");
    $('#column_' + select).html(status);
    $('#action_' + select).html("");
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "UpdateUserSettingByAdmin",
        data:
        {
            'eventType': 'updateUserStatus',
            'userKey': userKey,
            'status': status
        },
        success: function (data)
        {
            ShowToast("User Status Updated", '2000');
            window.location.href = 'adminListUsers.jsp';
        },
        error: function (xhr, textStatus, errorThrown)
        {
            ShowToast("User Status not Updated", 2000);
        }
    });
}

function deleteSelectedUser()
{
    if (selectedUserKey !== null)
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "UpdateUserSettingByAdmin",
            data:
            {
                'eventType': 'deleteUser',
                'userKey': selectedUserKey
            },
            success: function (data)
            {
                selectedUserKey = null;
                $('#row_' + lastSelectedUser).slideUp();
                ShowToast("User Deleted", 2000);

            },
            error: function (xhr, textStatus, errorThrown)
            {
                ShowToast("User not Deleted", 2000);
            }
        });
    }
    else
    {
        ShowToast("User not Deleted", 2000);
    }
}

function showUserSettingForAdmin(userKey)
{
    $('#policies_popup').fadeIn();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxUserSettingForAdmin.jsp",
        data:
        {
            'userKey': userKey
        },
        success: function (data)
        {
            $('#user_settings').html(data);

        },
        error: function (xhr, textStatus, errorThrown)
        {
            ShowToast("Problen in Getting User Data", 2000);
        }
    });
}

function validateUserSettingUpdatedByAdmin()
{
    if (document.getElementById('firstNameId').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('frstnm_vldtn').style.display = 'block';
        document.getElementById('firstNameId').setAttribute("style", "border-color: red;");
        return false;
    }
    else
    {
        document.getElementById('frstnm_vldtn').style.display = 'none';
        document.getElementById('firstNameId').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (document.getElementById('lastNameId').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('lstnm_vldtn').style.display = 'block';
        document.getElementById('lastNameId').setAttribute("style", "border-color: red;");
        return false;
    }
    else
    {
        document.getElementById('lstnm_vldtn').style.display = 'none';
        document.getElementById('lastNameId').setAttribute("style", "border-color: #a5b7bb;");
    }

    if (document.getElementById('phoneId').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('phn_vldtn').style.display = 'block';
        document.getElementById('phoneId').setAttribute("style", "border-color: red;");
        return false;
    }
    else
    {
        document.getElementById('phn_vldtn').style.display = 'none';
        document.getElementById('phoneId').setAttribute("style", "border-color: #a5b7bb;");
    }

    document.getElementById('mbl_vldtn').style.display = 'none';
    document.getElementById('mobileId').setAttribute("style", "border-color: #a5b7bb;");

    if (document.getElementById('phoneId').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('phoneId').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('phoneId').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('phn_vldtn').style.display = 'block';
                document.getElementById('phoneId').setAttribute("style", "border-color: red;");
                return false;
            }
        }
    }
    if (document.getElementById('mobileId').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('mobileId').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('mobileId').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('mbl_vldtn').style.display = 'block';
                document.getElementById('mobileId').setAttribute("style", "border-color: red;");
                return false;
            }
        }
    }
}
var lastSelectedBox = "useracct_profilefrm";
function openNCloseUsettingBox(id)
{
    if (lastSelectedBox !== id)
    {
        $("#useracct_profilefrm").slideUp();
        $("#useracct_resetpass").slideUp();
        $("#useracct_notifyfrm").slideUp();
        $("#updt_dtl_bx").slideUp();
        $("#useracct_whfrm").slideUp();
        $("#" + id).slideDown();
        lastSelectedBox = id;
    }
}

function updateUserProfileImage(input)
{

    //    var ext = input.files[0].name.split('.').pop().toLowerCase();
    //
    //    var uploadErrDiv = $("#profilepic_err");
    //
    //    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg', 'bmp']) == -1)
    //    {
    //
    //        uploadErrDiv.text("invalid file formate!");
    //        input.files[0] = "";
    //    }
    //    else if (input.files[0].size > 1000141)
    //    {
    //        uploadErrDiv.text("File size more than 1MB");
    //        input.files[0] = "";
    //    }
    //    else
    //    {
    $("#useracct_profilefrm").submit();
// }
}

function changeUserPassword(password)
{
    var currentPassword = $('#currentpasstxtid').val();
    var newPasswoed = $('#newpasstxtid').val();
    var cofirmPassword = $('#retypepasstxtid').val();
    var passw = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}$/;
    if (currentPassword === "" || currentPassword === null)
    {
        $('#currentTypePasswordError').html("Enter Current Password");
    }
    else if (currentPassword !== password)
    {
        $('#currentTypePasswordError').html("Enter Correct Current Password");
    }
    else if (newPasswoed === "" || newPasswoed === null)
    {
        $('#typePasswordError').html("Enter New Password");
    }
    else if (!newPasswoed.match(passw))
    {
        //        alert(newPasswoed);
        $('#typePasswordError').html("Password contain at least one numeric digit, one uppercase and one lowercase letter");
    }
    else if (cofirmPassword === "" || cofirmPassword === null)
    {
        $('#reTypePasswordError').html("Enter Re-Type Password");
    }
    else if (newPasswoed !== cofirmPassword)
    {
        $('#reTypePasswordError').html("New Password and Re-Type Password Must be Same");
    }
    else
    {
        showLoading();
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "UserAccountSetting",
            data:
            {
                'eventType': 'updateUserPassword',
                'password': newPasswoed
            },
            success: function (data)
            {
                // alert("data"+data);
                ShowToast("Password Updated", 2000);

            },
            error: function (xhr, textStatus, errorThrown)
            {
                //alert("error :"+errorThrown);
                ShowToast("Password not Updated", 2000);
            }
        });
    }
}

function saveWorkinghoursSettings()
{
    var sunFlag = 0;
    var monFlag = 0;
    var tueFlag = 0;
    var wedFlag = 0;
    var thuFlag = 0;
    var friFlag = 0;
    var satFlag = 0;

    var sunFromTime = $('#sundayfromtimeid').val();
    var sunToTime = $('#sundaytotimeid').val();
    var monFromTime = $('#mondayfromtimeid').val();
    var monToTime = $('#mondaytotimeid').val();
    var tueFromTime = $('#tuesdayfromtimeid').val();
    var tueToTime = $('#tuesdaytotimeid').val();
    var wedFromTime = $('#wednesdayfromtimeid').val();
    var wedToTime = $('#wednesdaytotimeid').val();
    var thuFromTime = $('#thursdayfromtimeid').val();
    var thuToTime = $('#thursdaytotimeid').val();
    var friFromTime = $('#fridayfromtimeid').val();
    var friToTime = $('#fridaytotimeid').val();
    var satFromTime = $('#saturdayfromtimeid').val();
    var satToTime = $('#saturdaytotimeid').val();


    if ($('#sundaychkid').is(':checked'))
    {
        sunFlag = 1;
    }
    else
    {
        sunFlag = 0;
    }

    if ($('#sundaychkid').is(':checked'))
    {
        monFlag = 1;
    }
    else
    {
        monFlag = 0;
    }

    if ($('#tuesdaychkid').is(':checked'))
    {
        tueFlag = 1;
    }
    else
    {
        tueFlag = 0;
    }

    if ($('#wednesdaychkid').is(':checked'))
    {
        wedFlag = 1;
    }
    else
    {
        wedFlag = 0;
    }

    if ($('#thursdaychkid').is(':checked'))
    {
        thuFlag = 1;
    }
    else
    {
        thuFlag = 0;
    }

    if ($('#fridaychkid').is(':checked'))
    {
        friFlag = 1;
    }
    else
    {
        friFlag = 0;
    }

    if ($('#saturdaychkid').is(':checked'))
    {
        satFlag = 1;
    }
    else
    {
        satFlag = 0;
    }

    showAjaxLoader();

    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "UpdateWorkingHours",
        data: {
            'RequestType': 'UpdateWorkingHours',
            //'UserKey': userKey,
            'SunWorkingFlag': sunFlag,
            'MonWorkingFlag': monFlag,
            'TueWorkingFlag': tueFlag,
            'WedWorkingFlag': wedFlag,
            'ThuWorkingFlag': thuFlag,
            'FriWorkingFlag': friFlag,
            'SatWorkingFlag': satFlag,
            'SunFromTime': sunFromTime,
            'SunToTime': sunToTime,
            'MonFromTime': monFromTime,
            'MonToTime': monToTime,
            'TueFromTime': tueFromTime,
            'TueToTime': tueToTime,
            'WedFromTime': wedFromTime,
            'WedToTime': wedToTime,
            'ThuFromTime': thuFromTime,
            'ThuToTime': thuToTime,
            'FriFromTime': friFromTime,
            'FriToTime': friToTime,
            'SatFromTime': satFromTime,
            'SatToTime': satToTime
        },
        //cache: false,
        success: function (notifydata)
        {
            hideAjaxLoader();
            ShowToast("Working hours Saved", 2000);
        },
        error: function (xhr, textStatus, errorThrown)
        {
            $("#wh_err").text("Unexcepted error occur. Please try again.");

            hideAjaxLoader();
        }
    });
}

function updateNotificationSetting()
{
    showLoading();
    var newEmail = $('#newEmail').val();
    var useRegisteredEmail = $('#useRegisteredEmail').val();
    var newMobileNo = $('#newMobileNo').val();
    var workinghoursNotify = $('#workinghoursNotify').val();
    var nonworkinghoursNotify = $('#nonworkinghoursNotify').val();
    var workinghoursMode = $('#workinghoursMode').val();
    var nonworkinghoursMode = $('#nonworkinghoursMode').val();
    var stopNotify = $('#stopNotify').val();
    var stoptimeFrom = $('#stoptimeFrom').val();
    var stoptimeTo = $('#stoptimeTo').val();

    var flag = "true";
    if (newEmail !== "")
    {
        document.getElementById('newEmailError').style.display = 'none';
        document.getElementById('newEmail').setAttribute("style", "border-color: #a5b7bb;");
        var atpos = newEmail.indexOf("@");
        var dotpos = newEmail.lastIndexOf(".");
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= newEmail.length) {
            document.getElementById('newEmailError').style.display = 'block';
            document.getElementById('newEmail').setAttribute("style", "border-color: red;");
            flag = "false";
        }
    }
    else
    {
        document.getElementById('newEmailError').style.display = 'none';
        document.getElementById('newEmail').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (newMobileNo !== "")
    {
        document.getElementById('newMobileNoError').style.display = 'none';
        document.getElementById('newMobileNo').setAttribute("style", "border-color: #a5b7bb;");
        var i;
        for (i = 0; i < newMobileNo.length; i++)
        {
            var c = newMobileNo.charAt(i);
            if (isNaN(c))
            {
                document.getElementById('newMobileNoError').style.display = 'block';
                document.getElementById('newMobileNo').setAttribute("style", "border-color: red;");
                flag = "false";
            }
        }
    }
    else
    {
        document.getElementById('newMobileNoError').style.display = 'none';
        document.getElementById('newMobileNo').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (flag === "true")
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "UserAccountSetting",
            data:
            {
                'eventType': 'updateNotificationSetting',
                'newEmail': newEmail,
                'useRegisteredEmail': useRegisteredEmail,
                'newMobileNo': newMobileNo,
                'workinghoursNotify': workinghoursNotify,
                'nonworkinghoursNotify': nonworkinghoursNotify,
                'workinghoursMode': workinghoursMode,
                'nonworkinghoursMode': nonworkinghoursMode,
                'stopNotify': stopNotify,
                'stoptimeFrom': stoptimeFrom,
                'stoptimeTo': stoptimeTo
            },
            success: function (data)
            {
                //alert("data :"+data);
                if (data === "1")
                {
                    ShowToast("Notification Setting Updated", 2000);
                }
                else
                {
                    ShowToast("Notification Setting Not Updated", 2000);
                }

            },
            error: function (xhr, textStatus, errorThrown)
            {
                //alert("error :"+errorThrown);
                ShowToast("Notification Setting Not Updated", 2000);
            }
        });
    }
}
var slctd_dept = "";
function showFolderOfDepartment(departmentKey)
{
    if (slctd_dept !== "")
    {
        document.getElementById(slctd_dept).style.background = "#c8c8c8";
        document.getElementById(slctd_dept).style.setProperty("color", "#000");
    }
    document.getElementById(departmentKey).style.background = "#039bc2";
    document.getElementById(departmentKey).style.setProperty("color", "#ffffff");
    slctd_dept = departmentKey;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxDepartmentFolder.jsp",
        data:
        {
            'departmentKey': departmentKey
        },
        success: function (data)
        {
            //alert("data :"+data);
            document.getElementById('folder_content').innerHTML = data;
        //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function (xhr, textStatus, errorThrown)
        {
            ShowToast("Error in Folder Fetching", 2000);
        }
    });
}
var lastFolderId = null;
var lastFileId = null;
function corprateDepartmentFolder(folderId)
{
    $('#file_ctrls').hide();
    $('#folder_ctrls').show();

    if (lastFolderId !== null)
    {
        $('#' + lastFolderId).removeClass("folder_highlight");
    }
    if (lastFileId !== null)
    {
        $('#' + lastFileId).removeClass("file_highlight");
    }
    $('#' + folderId).addClass("folder_highlight");
    lastFolderId = folderId;
}

function corprateDepartmentFile(fileId)
{
    $('#folder_ctrls').hide();
    $('#file_ctrls').show();

    if (lastFileId !== null)
    {
        $('#' + lastFileId).removeClass("file_highlight");
    }
    $('#' + fileId).addClass("file_highlight");
    lastFileId = fileId;
}

function createCorporateDepartmentFolder()
{
    var departmentKey = $('#departmentKey').val();
    var folderName = $('#new_folder_input').val();
    if (folderName === "")
    {
        ShowToast("Enter folder name before save", 1000);
    }
    else
    {
        $('#add_folder_popup').fadeOut();
        $('#new_folder_input').val("");
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "FolderManagementCorporatePage",
            data:
            {
                'eventType': 'createFolder',
                'departmentKey': departmentKey,
                'folderName': folderName
            },
            success: function (data)
            {
                if (data === "Folder created")
                {
                    ShowToast("Folder created", 1500);
                    showFolderOfDepartment(departmentKey);
                }
                else if (data === "Folder already exist")
                {
                    ShowToast("Folder already exist", 2000);
                }
            },
            error: function (xhr, textStatus, errorThrown)
            {
                ShowToast("Folder not created ", 2000);
            }
        });
    }
}
function deleteCorporateDepartmentFolder()
{
    var departmentKey = $('#departmentKey').val();
    if (departmentKey === "" || departmentKey === "0")
    {
        ShowToast("Select Department before delete a folder or file", 1000);
        return false;
    }
    var folderName = $('#corporateDepartmentSelectedFolder').val();
    var folderKey = $('#corporateDepartmentSelectedFolderKey').val();
    if (folderName === "")
    {
        ShowToast("Select Folder or File before delete", 1000);
    }
    else
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "FolderManagementCorporatePage",
            data:
            {
                'eventType': 'deleteFolder',
                'departmentKey': departmentKey,
                'folderName': folderName,
                'folderKey': folderKey
            },
            success: function (data)
            {
                ShowToast("Folder Deleted", 1500);
                showFolderOfDepartment(departmentKey);

            },
            error: function (xhr, textStatus, errorThrown)
            {
                //alert("error :"+errorThrown);
                ShowToast("Folder not deleted", 2000);
            }
        });
    }
}

function createFolderInDept()
{
    $('#departmentKey2').val($('#departmentKey').val());
    var deptVal = $('#departmentKey2').val();
    //alert(deptVal);
    if (deptVal === "" || deptVal === "0")
    {
        ShowToast("Select Department and folder before create a new folder", 1000);
        return false;
    }
    else
    {
        $('#add_folder_popup').fadeIn();
    }
}

function uploadCorporateDepartmentFolderFile()
{
    var folderName = $('#corporateDepartmentSelectedFolder').val();

    $('#corporateDepartmentSelectedFolder2').val(folderName);
    if (folderName === "")
    {
        ShowToast("Select folder before upload file", 1000);
        return false;
    }
    else
    {
        $('#departmentKey2').val($('#departmentKey').val());
        var deptVal = $('#departmentKey2').val();
        //alert(deptVal);
        if (deptVal === "" || deptVal === "0")
        {
            ShowToast("Select Department and folder before upload file", 1000);
            return false;
        }
        else
        {
            $('#file_upload_frm').submit();
        }
    }
}
function deleteCorporateDepartmentFolderFile()
{
    var departmentKey = $('#departmentKey').val();
    var folderName = $('#corporateDepartmentSelectedFolder').val();
    var fileName = $('#corporateDepartmentSelectedFile').val();
    if (folderName === "")
    {
        ShowToast("Select folder before delete", 1000);
    }
    else
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "FolderManagementCorporatePage",
            data:
            {
                'eventType': 'deleteFile',
                'folderName': folderName,
                'fileName': fileName
            },
            success: function (data)
            {
                ShowToast("File Deleted", 1500);
                showFolderOfDepartment(departmentKey);

            },
            error: function (xhr, textStatus, errorThrown)
            {
                //alert("error :"+errorThrown);
                ShowToast("File not deleted", 2000);
            }
        });
    }
}

var selectedBy = null;
function showCompanyUserByAsending()
{
    //alert(selectedBy);
    if (selectedBy !== null)
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "ajaxListOfUsersSorting.jsp",
            data:
            {
                'eventType': selectedBy
            },
            success: function (data)
            {
                //alert("data :"+data);
                document.getElementById('listOfUser').innerHTML = data;
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
            },
            error: function (xhr, textStatus, errorThrown)
            {
            //alert("error :"+errorThrown)
            }
        });
    }
}


function showCompanyMultipleAddressPopup()
{
    var companyAddressCount = parseInt($('#companyAddressCount').val());
    if (companyAddressCount === 1)
    {
        $('#address_popup1').fadeIn();
    }
    else if (companyAddressCount === 2)
    {
        $('#address_popup2').fadeIn();
    }
}

function addUpdateDeleteCompanyMultipleAddress()
{
    var companyAddressCount = parseInt($('#companyAddressCount').val());
    //alert(companyAddressCount);
    if (companyAddressCount === 1)
    {
        var branch = $("#branch_1").val();
        var country = $("#countryregion_1").val();
        var addr = $("#address_1").val();
        var city = $("#city_1").val();
        var state = $("#state_1").val();
        var zipcode = $("#zipcode_1").val();
        var fullAddress = branch + ', ' + country;

        if (addr != '')
        {
            fullAddress = fullAddress + "," + addr;
        }
        if (city != '')
        {
            fullAddress = fullAddress + "," + city;
        }
        if (state != '')
        {
            fullAddress = fullAddress + "," + state;
        }
        if (zipcode != '')
        {
            fullAddress = fullAddress + "," + zipcode;
        }
        $("#address_popup1").hide();

        $("#addaddrlbl1").text(fullAddress);

        $("#addrright1").show();

        $("#addrleft1").hide();

        //$("#address2detail").show();

        $("#addrleft2").show();
        $('#compseparator').css({
            'height': '475px',
            'margin-bottom': '50px'
        });
        $('#canBtnDiv').css({
            'top': '165px'
        });
        companyAddressCount++;
        $('#companyAddressCount').val(companyAddressCount);
    }
    else if (companyAddressCount === 2)
    {
        var branch = $("#branch_2").val();
        var country = $("#countryregion_2").val();
        var addr = $("#address_2").val();
        var city = $("#city_2").val();
        var state = $("#state_2").val();
        var zipcode = $("#zipcode_2").val();

        var fullAddress = branch + ', ' + country;

        if (addr != '')
        {
            fullAddress = fullAddress + "," + addr;
        }
        if (city != '')
        {
            fullAddress = fullAddress + "," + city;
        }
        if (state != '')
        {
            fullAddress = fullAddress + "," + state;
        }
        if (zipcode != '')
        {
            fullAddress = fullAddress + "," + zipcode;
        }


        $("#addaddrlbl2").text(fullAddress);

        $("#address_popup2").hide();

        $("#address2detail").show();

        $("#addrright2").show();

        $("#addrleft2").hide();

        $('#compseparator').css({
            'height': '495px',
            'margin-bottom': '50px'
        });
        $('#canBtnDiv').css({
            'top': '185px'
        });
        companyAddressCount++;
        $('#companyAddressCount').val(companyAddressCount);
        $('#addAddtessButton').hide();
    }
}

function removeUpdateDeleteCompanyMultipleAddress(value)
{
    var companyAddressCount = parseInt($('#companyAddressCount').val());
    if (companyAddressCount === 3 && value === 2)
    {
        $("#addrright2").hide();
        $("#addrleft2").show();
        $("#addaddrlbl1").text($("#addaddrlbl2").text());
        $("#branch_1").val($("#branch_2").val());
        $("#countryregion_1").val($("#countryregion_2").val());
        $("#address_1").val($("#address_2").val());
        $("#city_1").val($("#city_2").val());
        $("#state_1").val($("#state_2").val());
        $("#zipcode_1").val($("#zipcode_2").val());
        companyAddressCount--;
        $('#companyAddressCount').val(companyAddressCount);
        $('#addAddtessButton').show();
    }
    else if (companyAddressCount === 3 && value === 3)
    {
        $("#addrright2").hide();
        $("#addrleft2").show();
        $('#compseparator').css({
            'height': '475px',
            'margin-bottom': '50px'
        });
        $('#canBtnDiv').css({
            'top': '165px'
        });
        companyAddressCount--;
        $('#companyAddressCount').val(companyAddressCount);
        $('#addAddtessButton').show();
    }
    else if (companyAddressCount === 2 && value === 2)
    {
        $("#addrright1").hide();
        $("#addrleft2").hide();
        $("#addrleft1").show();
        $("#address2detail").hide();
        $('#compseparator').css({
            'height': '375px',
            'margin-bottom': '100px'
        });
        $('#canBtnDiv').css({
            'top': '115px'
        });
        companyAddressCount--;
        $('#companyAddressCount').val(companyAddressCount);
        $('#addAddtessButton').show();
    }
}

function showCompaniesNonVendorSearch(companyName, companyType)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompaniesNonVendorSearch",
        data:
        {
            'companyName': companyName,
            'companyType': companyType
        },
        success: function (data)
        {
            document.getElementById('ven_search_result').style.display = "block";
            if (data === "")
            {
                document.getElementById('ven_search_result').innerHTML = "<div id='ven_empty' class='filter_comp'>No result found</div>";
            }
            else
            {
                document.getElementById('ven_search_result').innerHTML = data;
            }
        },
        error: function (xhr, textStatus, errorThrown)
        {
        }
    });
}

function showCompaniesVendorSearch(companyName, companyType)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompaniesVendorSearch",
        data:
        {
            'companyName': companyName,
            'companyType': companyType
        },
        success: function (data)
        {
            document.getElementById('ven_search_result').style.display = "block";
            if (data === "")
            {
                document.getElementById('ven_search_result').innerHTML = "<div id='ven_empty' class='filter_comp'>No result found</div>";
            }
            else
            {
                document.getElementById('ven_search_result').innerHTML = data;
            }
        },
        error: function (xhr, textStatus, errorThrown)
        {
        }
    });
}
lastSelectedList = 'buyer_reg_tab';
function selectList(id)
{
    if (lastSelectedList !== null)
    {
        $('#' + lastSelectedList).removeClass('highlight');
        $('#' + lastSelectedList).addClass('normal');
    }
    $('#' + id).removeClass('normal');
    $('#' + id).addClass('highlight');
    lastSelectedList = id;
}

function swapWkTabs2(id1, id2)
{
    $('#' + id2).removeClass('highlight');
    $('#' + id2).addClass('normal');
    $('#' + id1).removeClass('normal');
    $('#' + id1).addClass('highlight');
}

function showVrProcessData(processData)
{
    $('#supplier_form_popup').fadeIn();
    //$('#vr_key').val(processData);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ShowVrDetails",
        data:
        {
            'vrKey': processData
        },
        success: function (data)
        {
            //alert(data);
            document.getElementById('popup_ven_reg_form').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert("error :"+errorThrown)
        }
    });
}

var itemsCount = 1;
function cancelRfqItem()
{
    $('#rfq_add_item_popup').fadeOut();

    $('#popup_item_desc').val('');
    $('#popup_part_no').val('');
    $('#popup_brcd').val('');
    $('#popup_quantity').val('');
    $('#popup_ship_date').val('');

    $('#popup_item_name').val('');
    $('#popup_item_descir').val('');
    $('#popup_part_no').val('');
    $('#popup_sku_no').val('');
    $('#popup_brcd').val('');
    $('#item_search_result').hide();
    $('#resulted-data').hide();
}
function addRfqItem()
{
    var selectItem = document.getElementById('selectItem').checked;
    var addItem = document.getElementById('addItem').checked;
    //    alert(selectItem+":"+addItem);
    if (selectItem == true)
    {
        $('#rfq_item_form_error').html("");
        var itemDet = "";
        var lblkey = $("#selectedItemKey").val();
        var lblin = $("#lblin").html();
        var lblid = $("#lblid").html();
        var lblpn = $("#lblpn").html();
        var lblsn = $("#lblsn").html();
        var lblbc = $("#lblbc").html();
        var quantity = $("#popup_quantity").val();
        var quantityType = $("#selectedQuantityTypeKey").val();
        var shipDate = $("#popup_ship_date").val();
        if ($('#popup_item_desc').val().trim() === "")
        {
            $('#rfq_item_form_error').html("Select Product");
            return false;
        }
        if (quantity.trim() === "")
        {
            $('#rfq_item_form_error').html("Enter Valid Quantity");
            return false;
        }
        else if (!$.isNumeric(quantity))
        {
            $('#rfq_item_form_error').html("Enter Valid Quantity");
            return false;
        }
        if ($('#popup_ship_date').val().trim() === "")
        {
            $('#rfq_item_form_error').html("Enter Ship Date");
            return false;
        }
        itemDet += '<div id="item' + itemsCount + '" class="item" style="float:left;margin-left:40px;margin-right:10px;">';
        itemDet += '<li style="width: 786px;margin-left: -744px;padding-left: 792px;">';
        //        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:28px;margin-right:10px;" readonly value="' + itemsCount + '"/>';
        itemDet += '<input type="hidden" name="item_key" autocomplete="off" class="textbox" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + lblkey + '"/>';
        itemDet += '<input type="text" name="item_name" autocomplete="off" class="textbox" maxlength=80 id="item_name' + itemsCount + '" style="width:98px;margin-right:10px;" readonly  value="' + lblin + '"/>';
        itemDet += '<input type="text" name="item_desc" autocomplete="off" class="textbox" maxlength=80 id="item_desc' + itemsCount + '" style="width:118px;margin-right:10px;" readonly  value="' + lblid + '"/>';
        itemDet += '<input type="text" name="item_part_no" autocomplete="off" class="textbox" maxlength=80 id="part_no' + itemsCount + '" style="width:58px;margin-right:10px;" readonly value="' + lblpn + '"/>';
        itemDet += '<input type="text" name="item_sku_no" autocomplete="off" class="textbox" maxlength=80 id="sku_no' + itemsCount + '" style="width:58px;margin-right:10px;" readonly value="' + lblsn + '"/>';
        itemDet += '<input type="text" name="item_barcode" autocomplete="off" class="textbox" maxlength=80 id="brcd_no' + itemsCount + '" style="width:58px;margin-right:10px;" readonly value="' + lblbc + '"/>';
        itemDet += '<input type="text" name="item_quantity" autocomplete="off" class="textbox" maxlength=7 id="quantity' + itemsCount + '" style="width:58px;" readonly value="' + quantity + '"/>';
        itemDet += '<div class="quantity_unit" name="item_quantity_unit" id="quantity_unit' + itemsCount + '" >' + quantityType + '</div>';
        itemDet += '<input type="hidden" name="item_quantity_unit" autocomplete="off" class="textbox" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + quantityType + '"/>';
        itemDet += '<input type="text" name="item_ship_date" autocomplete="off" class="textbox" id="ship_date' + itemsCount + '" style="width:118px;margin-right:10px;" readonly value="' + shipDate + '"/>';
        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:10px;" onclick=$("#item' + itemsCount + '").remove();document.getElementById("allRfQItems").value=document.getElementById("allRfQItems").value.replace("(' + lblkey + '^' + lblpn + '^' + lblbc + '^' + quantity + '^' + quantityType + '^' + shipDate + '),",""); />';
        itemDet += '</li>';
        itemDet += '</div>';

        $("#items").append(itemDet);
        $("#allRfQItems").val($("#allRfQItems").val() + "(" + lblkey + "^" + lblpn + '^' + lblbc + '^' + quantity + "^" + quantityType + "^" + shipDate + "),");

        itemsCount++;

        $('#rfq_add_item_popup').fadeOut();
        $('#popup_item_desc').val('');
        $('#popup_part_no').val('');
        $('#popup_brcd').val('');
        $('#popup_quantity').val('');
        $('#popup_ship_date').val('');
    }
    else if (addItem == true)
    {
        $('#rfq_item_form_error2').html("");
        var lblin = document.getElementById('popup_item_name').value;
        var lblid = document.getElementById('popup_item_descir').value;
        var lblpn = document.getElementById('popup_part_no').value;
        var lblsn = document.getElementById('popup_sku_no').value;
        var lblbc = document.getElementById('popup_brcd').value;

        if (lblin.trim() === "")
        {
            $('#rfq_item_form_error2').html("Enter Name");
            return false;
        }
        if (lblid.trim() === "")
        {
            $('#rfq_item_form_error2').html("Enter Description");
            return false;
        }
        if (lblpn.trim() === "")
        {
            $('#rfq_item_form_error2').html("Enter Batch No");
            return false;
        }
        if (lblsn.trim() === "")
        {
            $('#rfq_item_form_error2').html("SKU No");
            return false;
        }
        //        if(lblbc.trim()==="")
        //        {
        //          $('#rfq_item_form_error2').html("Barcode");
        //            return false;
        //        }

        document.getElementById('popup_item_name').value = '';
        document.getElementById('popup_item_descir').value = '';
        document.getElementById('popup_part_no').value = '';
        document.getElementById('popup_sku_no').value = '';
        document.getElementById('popup_brcd').value = '';



        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "AddGlobalProductItem",
            data:
            {
                'iname': lblin,
                'idesc': lblid,
                'ipart': lblpn,
                'isku': lblsn,
                'ibar': lblbc
            },
            success: function (data)
            {
                $('#rfq_item_form_error2').html("Product added successfully...");
            },
            error: function (xhr, textStatus, errorThrown)
            {
            }
        });
    }
}

function cancelQteItem()
{
    $('#quote_add_item_popup').fadeOut();

    $('#popup_item_desc').val('');
    $('#popup_part_no').val('');
    $('#popup_brcd').val('');
    $('#popup_quantity').val('');
    $('#popup_ship_date').val('');
    $('#popup_price').val('');
    $('#dscnt').val('1');
    $('#pop_multiplier').val('0');

    $('#popup_item_name').val('');
    $('#popup_item_descir').val('');
    $('#popup_part_no').val('');
    $('#popup_sku_no').val('');
    $('#popup_brcd').val('');

    $('#item_search_result').hide();
    $('#resulted-data').hide();


}

function addQteItem()
{
    var selectItem = document.getElementById('selectItem').checked;
    var addItem = document.getElementById('addItem').checked;
    //    alert(selectItem+":"+addItem);
    if (selectItem == true)
    {
        $('#rfq_item_form_error').html("");
        var itemDet = "";

        var lblkey = $("#selectedItemKey").val();
        var lblin = $("#lblin").html();
        var lblid = $("#lblid").html();
        var lblpn = $("#lblpn").html();
        var lblsn = $("#lblsn").html();
        var lblbc = $("#lblbc").html();
        var quantity = $("#popup_quantity").val();
        var quantityType = $("#selectedQuantityUnit").val();
        var shipDate = $("#popup_ship_date").val();
        var popup_price = $("#popup_price").val();
        var currency = $("#selectedCurrencyUnit").val();
        var dscnt = $("#dscnt").val();
        var pop_multiplier = $("#pop_multiplier").val();
        if ($('#popup_item_desc').val().trim() === "")
        {
            $('#rfq_item_form_error').html("Select Product");
            return false;
        }
        if (quantity.trim() === "")
        {
            $('#rfq_item_form_error').html("Enter Valid Quantity");
            return false;
        }
        else if (!$.isNumeric(quantity))
        {
            $('#rfq_item_form_error').html("Enter Valid Quantity");
            return false;
        }
        if ($('#popup_ship_date').val().trim() === "")
        {
            $('#rfq_item_form_error').html("Enter Ship Date");
            return false;
        }
        if (popup_price.trim() === "")
        {
            $('#rfq_item_form_error').html("Enter Valid Price");
            return false;
        }
        else if (!$.isNumeric(popup_price))
        {
            $('#rfq_item_form_error').html("Enter Valid Price");
            return false;
        }
        itemDet += '<div id="item' + (100 + itemsCount) + '" class="item" style="width:970px;float:left;margin-left:15px;margin-right:0px;">';
        //        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:20px;margin-right:10px;" readonly value="' + itemsCount + '"/>';
        itemDet += '<li style="width: 924px;margin-left: -890px;padding-left: 924px;">';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox" name="item_key" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + lblkey + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="item_name' + itemsCount + '" style="width:98px;margin-right:10px;" readonly  value="' + lblin + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="item_desc' + itemsCount + '" style="width:118px;margin-right:10px;" readonly  value="' + lblid + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="part_no" maxlength=80 id="part_no' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + lblpn + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="sku_no' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + lblsn + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="brcd_no" maxlength=80 id="brcd_no' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + lblbc + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="quantity" maxlength=7  id="quantity' + itemsCount + '" style="width:28px;" readonly value="' + quantity + '"/>';
        itemDet += '<div class="quantity_unit" id="quantity_unit' + itemsCount + '" style="margin-right:10px;" >' + quantityType + '</div>';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox" name="quantityUnitKey" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + quantityType + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="ship_date" id="ship_date' + itemsCount + '" style="width:74px;margin-right:10px;" readonly value="' + shipDate + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="price" id="ppunit' + itemsCount + '" style="width:50px;margin-right:0px;" readonly value="' + popup_price + '"/>';
        itemDet += '<div class="currency_unit" id="currency1" style="margin-right:10px;">' + currency + '</div>';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox currency_key" name="currencyKey" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + currency + '"/>';
        itemDet += '<input type="text" name="discount" autocomplete="off" class="textbox" id="multipl' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + dscnt + '"/>';
        itemDet += '<input type="text" name="total" autocomplete="off" class="textbox" id="totl' + itemsCount + '" style="width:65px;margin-right:10px;" readonly value="' + pop_multiplier + '"/>';
        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:10px;" onclick=$("#item' + (100+itemsCount) + '").remove();delPrice(' + pop_multiplier + ');$("#item' + (100 + itemsCount) + '").html("");document.getElementById("allQteItems").value=document.getElementById("allQteItems").value.replace("(' + lblkey + "^" + lblpn + '^' + lblbc + "^" + quantity + '^' + quantityType + '^' + shipDate + "^" + popup_price + "^" + currency + "^" + dscnt + '),",""); />';
        itemDet+='</li>';
        itemDet+='</div>';
        $('.currency_unit').html(currency);
        $('.currency_key').val(currency);


        $("#items").append(itemDet);
        $("#allQteItems").val($("#allQteItems").val() + "(" + lblkey + "^" + lblpn + '^' + lblbc + "^" + quantity + '^' + quantityType + '^' + shipDate + "^" + popup_price + "^" + currency + "^" + dscnt + "),");
        //        alert($("#allQteItems").val());
        itemsCount++;

        tl_clcltn();

        $('#quote_add_item_popup').fadeOut();
        $('#po_add_item_popup').fadeOut();
        $('#invoice_add_item_popup').fadeOut();
        $('#popup_item_desc').val('');
        $('#popup_part_no').val('');
        $('#popup_brcd').val('');
        $('#popup_quantity').val('');
        $('#popup_ship_date').val('');
        $('#popup_price').val('');
        $('#dscnt').val('1');
        $('#pop_multiplier').val('0');
    }
    else if (addItem == true)
    {
        $('#rfq_item_form_error2').html("");
        var lblin = document.getElementById('popup_item_name').value;
        var lblid = document.getElementById('popup_item_descir').value;
        var lblpn = document.getElementById('popup_part_no').value;
        var lblsn = document.getElementById('popup_sku_no').value;
        var lblbc = document.getElementById('popup_brcd').value;

        if (lblin.trim() === "")
        {
            $('#rfq_item_form_error2').html("Enter Name");
            return false;
        }
        if (lblid.trim() === "")
        {
            $('#rfq_item_form_error2').html("Enter Description");
            return false;
        }
        if (lblpn.trim() === "")
        {
            $('#rfq_item_form_error2').html("Enter Batch No");
            return false;
        }
        if (lblsn.trim() === "")
        {
            $('#rfq_item_form_error2').html("SKU No");
            return false;
        }
        //        if(lblbc.trim()==="")
        //        {
        //          $('#rfq_item_form_error2').html("Barcode");
        //            return false;
        //        }

        document.getElementById('popup_item_name').value = '';
        document.getElementById('popup_item_descir').value = '';
        document.getElementById('popup_part_no').value = '';
        document.getElementById('popup_sku_no').value = '';
        document.getElementById('popup_brcd').value = '';

        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "AddGlobalProductItem",
            data:
            {
                'iname': lblin,
                'idesc': lblid,
                'ipart': lblpn,
                'isku': lblsn,
                'ibar': lblbc
            },
            success: function (data)
            {
                $('#rfq_item_form_error2').html("Product added successfully...");
            },
            error: function (xhr, textStatus, errorThrown)
            {
            }
        });
    }
}

function addQteItem2()
{
    var selectItem = document.getElementById('selectItem').checked;
    var addItem = document.getElementById('addItem').checked;
    //    alert(selectItem+":"+addItem);
    if (selectItem == true)
    {
        var itemDet = "";

        var lblkey = $("#selectedItemKey").val();
        var lblin = $("#lblin").html();
        var lblid = $("#lblid").html();
        var lblpn = $("#lblpn").html();
        var lblsn = $("#lblsn").html();
        var lblbc = $("#lblbc").html();
        var quantity = $("#popup_quantity").val();
        var quantityType = $("#popup_quantity_type").text();
        var shipDate = $("#popup_ship_date").val();
        var popup_price = $("#popup_price").val();
        var currency = $("#currency").text();
        var dscnt = $("#dscnt").val();
        var pop_multiplier = $("#pop_multiplier").val();

        itemDet += '<div id="item' + (100 + itemsCount) + '" class="item" style="width:970px;float:left;margin-left:15px;margin-right:0px;">';
        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:20px;margin-right:10px;" readonly value="' + itemsCount + '"/>';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox" name="item_key" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + lblkey + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="item_name' + itemsCount + '" style="width:98px;margin-right:10px;" readonly  value="' + lblin + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="item_desc' + itemsCount + '" style="width:118px;margin-right:10px;" readonly  value="' + lblid + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="part_no" maxlength=80 id="part_no' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + lblpn + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="sku_no' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + lblsn + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="brcd_no" maxlength=80 id="brcd_no' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + lblbc + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="quantity" maxlength=7  id="quantity' + itemsCount + '" style="width:28px;" readonly value="' + quantity + '"/>';
        itemDet += '<div class="quantity_unit" id="quantity_unit' + itemsCount + '" style="margin-right:10px;" >' + quantityType + '</div>';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox" name="quantityUnitKey" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + quantityType + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="ship_date" id="ship_date' + itemsCount + '" style="width:74px;margin-right:10px;" readonly value="' + shipDate + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="price" id="ppunit' + itemsCount + '" style="width:50px;margin-right:0px;" readonly value="' + popup_price + '"/>';
        itemDet += '<div class="quantity_unit" id="currency1" style="margin-right:10px;">' + currency + '</div>';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox" name="currencyKey" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + currency + '"/>';
        itemDet += '<input type="text" name="discount" autocomplete="off" class="textbox" id="multipl' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + dscnt + '"/>';
        itemDet += '<input type="text" name="total" autocomplete="off" class="textbox" id="totl' + itemsCount + '" style="width:65px;margin-right:10px;" readonly value="' + pop_multiplier + '"/>';
        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:10px;" onclick=delPrice(' + pop_multiplier + ');$("#item' + (100 + itemsCount) + '").html("");document.getElementById("allQteItems").value=document.getElementById("allQteItems").value.replace("(' + lblkey + "^" + lblpn + '^' + lblbc + "^" + quantity + '^' + quantityType + '^' + shipDate + "^" + popup_price + "^" + currency + "^" + dscnt + '),",""); /></div>';


        $("#items").append(itemDet);
        $("#allQteItems").val($("#allQteItems").val() + "(" + lblkey + "^" + lblpn + '^' + lblbc + "^" + quantity + '^' + quantityType + '^' + shipDate + "^" + popup_price + "^" + currency + "^" + dscnt + "),");
        //        alert($("#allQteItems").val());
        itemsCount++;

        tl_clcltn();

        $('#quote_add_item_popup').fadeOut();
        $('#po_add_item_popup').fadeOut();
        $('#invoice_add_item_popup').fadeOut();
        $('#popup_item_desc').val('');
        $('#popup_part_no').val('');
        $('#popup_brcd').val('');
        $('#popup_quantity').val('');
        $('#popup_ship_date').val('');
        $('#popup_price').val('');
        $('#dscnt').val('1');
        $('#pop_multiplier').val('0');
    }
    else if (addItem == true)
    {
        var lblin = document.getElementById('popup_item_name').value;
        var lblid = document.getElementById('popup_item_descir').value;
        var lblpn = document.getElementById('popup_part_no').value;
        var lblsn = document.getElementById('popup_sku_no').value;
        var lblbc = document.getElementById('popup_brcd').value;

        document.getElementById('popup_item_name').value = '';
        document.getElementById('popup_item_descir').value = '';
        document.getElementById('popup_part_no').value = '';
        document.getElementById('popup_sku_no').value = '';
        document.getElementById('popup_brcd').value = '';

        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "AddGlobalProductItem",
            data:
            {
                'iname': lblin,
                'idesc': lblid,
                'ipart': lblpn,
                'isku': lblsn,
                'ibar': lblbc
            },
            success: function (data)
            {
                alert("Product added successfully... \nNow search and select it from existed product calatog")
            },
            error: function (xhr, textStatus, errorThrown)
            {
            }
        });
    }
}

function cancelInvItem()
{
    $('#invoice_add_item_popup').fadeOut();

    $('#popup_item_desc').val('');
    $('#popup_part_no').val('');
    $('#popup_brcd').val('');
    $('#popup_quantity_ordered').val('');
    $('#popup_quantity').val('');
    //    $('#popup_ship_date').val('');
    $('#popup_price').val('');
    $('#dscnt').val('1');
    $('#pop_multiplier').val('0');

    $('#popup_item_name').val('');
    $('#popup_item_descir').val('');
    $('#popup_part_no').val('');
    $('#popup_sku_no').val('');
    $('#popup_brcd').val('');

    $('#item_search_result').hide();
    $('#resulted-data').hide();
}

function addInvItem()
{
    var selectItem = document.getElementById('selectItem').checked;
    var addItem = document.getElementById('addItem').checked;
    //    alert(selectItem+":"+addItem);
    if (selectItem == true)
    {
        $('#rfq_item_form_error').html("");
        var itemDet = "";

        var lblkey = $("#selectedItemKey").val();
        var lblin = $("#lblin").html();
        var lblid = $("#lblid").html();
        var lblpn = $("#lblpn").html();
        var lblsn = $("#lblsn").html();
        var lblbc = $("#lblbc").html();
        var popup_quantity_ordered = $("#popup_quantity_ordered").val();
        var quantity_ordered_unit = $("#selectedQuantityUnit").val();
        var popup_quantity = $("#popup_quantity").val();
        var quantity_shipped_unit = $("#selectedQuantityUnit2").val();
        //        var shipDate = $("#popup_ship_date").val();
        var popup_price = $("#popup_price").val();
        var currency = $("#selectedCurrencyUnit").val();
        var dscnt = $("#dscnt").val();
        var pop_multiplier = $("#pop_multiplier").val();

        if ($('#popup_item_desc').val().trim() === "")
        {
            $('#rfq_item_form_error').html("Select Product");
            return false;
        }
        if (popup_quantity_ordered.trim() === "")
        {
            $('#rfq_item_form_error').html("Enter Valid Quantity");
            return false;
        }
        else if (!$.isNumeric(popup_quantity_ordered))
        {
            $('#rfq_item_form_error').html("Enter Valid Quantity");
            return false;
        }
        if (popup_quantity.trim() === "")
        {
            $('#rfq_item_form_error').html("Enter Valid Shipped Quantity");
            return false;
        }
        else if (!$.isNumeric(popup_quantity))
        {
            $('#rfq_item_form_error').html("Enter Valid Shipped Quantity");
            return false;
        }
        //        if($('#popup_ship_date').val().trim()==="")
        //        {
        //            $('#rfq_item_form_error').html("Enter Ship Date");
        //            return false;
        //        }
        if (popup_price.trim() === "")
        {
            $('#rfq_item_form_error').html("Enter Valid Price");
            return false;
        }
        else if (!$.isNumeric(popup_price))
        {
            $('#rfq_item_form_error').html("Enter Valid Price");
            return false;
        }

        itemDet += '<div id="item' + itemsCount + '" class="item" style="width:970px;float:left;margin-left:15px;margin-right:0px;">';
        //        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:20px;margin-right:10px;" readonly value="' + itemsCount + '"/>';
        itemDet += '<li style="width: 912px;margin-left: -840px;padding-left: 884px;">';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox" name="item_key" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + lblkey + '"/>';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + lblkey + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="item_name' + itemsCount + '" style="width:98px;margin-right:10px;" readonly  value="' + lblin + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="item_desc' + itemsCount + '" style="width:118px;margin-right:10px;" readonly  value="' + lblid + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 name="part_no" id="part_no' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + lblpn + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="sku_no' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + lblsn + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 name="brcd_no" id="brcd_no' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + lblbc + '"/>';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox" maxlength=7 name="quantity" id="quantity_ordered' + itemsCount + '" style="width:28px;" readonly value="' + popup_quantity_ordered + '"/>';
        itemDet += '<div class="quantity_unit" id="quantity_ordered_unit' + itemsCount + '" style="margin-right:10px;display:none;" >' + quantity_ordered_unit + '</div>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 name="quantityShipped" id="quantity' + itemsCount + '" style="width:60px;" readonly value="' + popup_quantity + '"/>';
        itemDet += '<div class="quantity_unit" id="quantity_shipped_unit' + itemsCount + '" style="margin-right:10px;" >' + quantity_shipped_unit + '</div>';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox" name="quantityUnitKey" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + quantity_shipped_unit + '"/>';
        //        itemDet += '<input type="text" autocomplete="off" class="textbox" id="ship_date' + itemsCount + '" style="width:74px;margin-right:10px;" readonly value="' + shipDate + '"/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" name="price" id="ppunit' + itemsCount + '" style="width:50px;margin-right:0px;" readonly value="' + popup_price + '"/>';
        itemDet += '<div class="currency_unit" id="currency1" style="margin-right:10px;">' + currency + '</div>';
        itemDet += '<input type="hidden" autocomplete="off" class="textbox currency_key" name="currencyKey" id="itemKey' + itemsCount + '" style="width:30px;margin-right:10px;" readonly value="' + currency + '"/>';
        itemDet += '<input type="text" name="discount" autocomplete="off" class="textbox" id="multipl' + itemsCount + '" style="width:48px;margin-right:10px;" readonly value="' + dscnt + '"/>';
        itemDet += '<input type="text" name="total"  autocomplete="off" class="textbox" id="totl' + itemsCount + '" style="width:65px;margin-right:10px;" readonly value="' + pop_multiplier + '"/>';
        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:10px;" onclick=$("#item' + itemsCount + '").remove();delPrice(' + pop_multiplier + ');$("#item' + itemsCount + '").html("");document.getElementById("allQteItems").value=document.getElementById("allQteItems").value.replace("(' + lblkey + "^" + lblpn + '^' + lblbc + "^" + popup_quantity_ordered + '^' + quantity_ordered_unit + '^' + popup_quantity + '^' + quantity_shipped_unit + "^" + popup_price + "^" + currency + "^" + dscnt + '),",""); />';
        itemDet+='</li>';
        itemDet+='</div>';
        $('.currency_unit').html(currency);
        $('.currency_key').val(currency);

        $("#items").append(itemDet);
        $("#allInvItems").val($("#allInvItems").val() + "(" + lblkey + "^" + lblpn + '^' + lblbc + "^" + popup_quantity_ordered + '^' + quantity_ordered_unit + '^' + popup_quantity + '^' + quantity_shipped_unit + "^" + popup_price + "^" + currency + "^" + dscnt + "),");
        //alert($("#allInvItems").val());
        itemsCount++;

        tl_clcltn();

        $('#quote_add_item_popup').fadeOut();
        $('#po_add_item_popup').fadeOut();
        $('#invoice_add_item_popup').fadeOut();
        $('#popup_item_desc').val('');
        $('#popup_part_no').val('');
        $('#popup_brcd').val('');
        $('#popup_quantity_ordered').val('');
        $('#popup_quantity').val('');
        //        $('#popup_ship_date').val('');
        $('#popup_price').val('');
        $('#dscnt').val('1');
        $('#pop_multiplier').val('0');
    }
    else if (addItem == true)
    {
        var lblin = document.getElementById('popup_item_name').value;
        var lblid = document.getElementById('popup_item_descir').value;
        var lblpn = document.getElementById('popup_part_no').value;
        var lblsn = document.getElementById('popup_sku_no').value;
        var lblbc = document.getElementById('popup_brcd').value;

        $('#rfq_item_form_error2').html("");

        if (lblin.trim() === "")
        {
            $('#rfq_item_form_error2').html("Enter Name");
            return false;
        }
        if (lblid.trim() === "")
        {
            $('#rfq_item_form_error2').html("Enter Description");
            return false;
        }
        if (lblpn.trim() === "")
        {
            $('#rfq_item_form_error2').html("Enter Batch No");
            return false;
        }
        if (lblsn.trim() === "")
        {
            $('#rfq_item_form_error2').html("SKU No");
            return false;
        }
        if (lblbc.trim() === "")
        {
            $('#rfq_item_form_error2').html("Barcode");
            return false;
        }

        document.getElementById('popup_item_name').value = '';
        document.getElementById('popup_item_descir').value = '';
        document.getElementById('popup_part_no').value = '';
        document.getElementById('popup_sku_no').value = '';
        document.getElementById('popup_brcd').value = '';

        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "AddGlobalProductItem",
            data:
            {
                'iname': lblin,
                'idesc': lblid,
                'ipart': lblpn,
                'isku': lblsn,
                'ibar': lblbc
            },
            success: function (data)
            {
                alert("Product added successfully... \nNow search and select it from existed product calatog")
            },
            error: function (xhr, textStatus, errorThrown)
            {
            }
        });
    }
}


function mult()
{
    var rt = document.getElementById("popup_price").value;
    if (rt == NaN)
        rt = 0;
    var qt = document.getElementById("popup_quantity").value;
    if (qt == NaN)
        qt = 0;
    var dscnt = document.getElementById("dscnt").value;
    if (dscnt == NaN)
        dscnt = 1;
    var mlt = rt * qt * dscnt;
    if (mlt.toString() == "NaN")
    {
        document.getElementById("pop_multiplier").value = "0";
    }
    else
    {
        document.getElementById("pop_multiplier").value = mlt;
    }

}

function mult2(count)
{
    var rt = document.getElementById("popup_price_" + count).value;
    if (rt == NaN)
        rt = 0;
    var qt = document.getElementById("popup_quantity_" + count).value;
    if (qt == NaN)
        qt = 0;
    var dscnt = document.getElementById("dscnt_" + count).value;
    if (dscnt == NaN)
        dscnt = 1;
    var mlt = rt * qt * dscnt;
    if (mlt.toString() == "NaN")
    {
        document.getElementById("total_" + count).value = "0";
    }
    else
    {
        document.getElementById("total_" + count).value = mlt;
    }

}

function tl_clcltn()
{
    //alert('start');
    var tl_lst_prc = parseFloat($("#tot_list_price_amt").val());
    //alert($('#pop_multiplier').val()); 
    var mltpl = parseFloat($('#pop_multiplier').val());
    tl_lst_prc = tl_lst_prc + mltpl;
    //alert(tl_lst_prc);
    $("#tot_list_price_amt").val(tl_lst_prc);
    var frt = $("#frei_hand_amt").val();
    //    alert(frt);
    if (frt == "undefined" || frt == undefined)
        frt = 0;
    $("#tot_price_amt").val(calculateTotPrice(tl_lst_prc, parseFloat($('#qt_tx').val()), frt));
}

function tl_clcltn_RfqToQoute(count)
{
    var tl_lst_prc = parseFloat($("#total_" + count).val());
    $("#tot_list_price_amt").val(tl_lst_prc);
    var frt = $("#frei_hand_amt").val();
    //    alert(frt);
    if (frt == "undefined" || frt == undefined)
        frt = 0;
    $("#tot_price_amt").val(calculateTotPrice(tl_lst_prc, parseFloat($('#qt_tx').val()), frt));
}

/* this method is used to calculate the total price */
function calculateTotPrice(totListPrice, tax)
{
    var taxAmt = 0;

    var totPrice = 0;

    taxAmt = (totListPrice * tax) / 100;

    totPrice = parseFloat(totListPrice) + parseFloat(taxAmt);

    return totPrice;
}

function delPrice(val)
{
    var totListPriceamt = $("#tot_list_price_amt").val();
    totListPriceamt = totListPriceamt - val;
    $("#tot_list_price_amt").val(totListPriceamt);
    $("#tot_price_amt").val(calculateTotPrice(totListPriceamt, parseFloat($('#qt_tx').val())));
    if ($("#tot_price_amt").val() == NaN || $("#tot_price_amt").val() == "NaN")
        $("#tot_price_amt").val("0");
}

function clclt_trans_amnt(val)
{
    if (val === "")
    {
        $("#tx_er").text("Please fill tax value in numeric or 0(zero)");
        val = 0;
    }
    else
    {
        $("#tx_er").text("");
        var frt = $("#frei_hand_amt").val();
        //    alert(frt);
        if (frt == "undefined" || frt == undefined)
            frt = 0;
        $("#tot_price_amt").val(calculateTotPrice(parseFloat($("#tot_list_price_amt").val()), parseFloat(val), frt));
    }
}

function clclt_frt_amnt(val)
{
    var tl_lst_prc = parseFloat($("#tot_list_price_amt").val());

    //$("#tot_list_price_amt").val(tl_lst_prc);

    var qt = $('#qt_tx').val();
    if (val === "")
    {
        //        $("#tx_er").text("Please fill tax value in numeric or 0(zero)");
        val = 0;
    }
    else
    {
        //        $("#tx_er").text("");
        var frt = $("#frei_hand_amt").val();
        //    alert(frt);
        if (frt == "undefined" || frt == undefined)
            frt = 0;
        $("#tot_price_amt").val(calculateTotPrice(tl_lst_prc, qt, frt));
    }
}

function showRfqPopupData(rfqKey, companyKey, buyerKey)
{
    $('#rfqKey').val(rfqKey);
    if (companyKey == buyerKey)
    {
        $('#rfq_accept_btn').hide();
        $('#rfq_reject_btn').hide();
    }
    else
    {
        $('#rfq_accept_btn').show();
        $('#rfq_reject_btn').show();
    }


}

function showQuotePopupData(rfqKey, companyKey, buyerKey)
{
    $('#rfqKey').val(rfqKey);
    if (companyKey == buyerKey)
    {
        $('#quote_accept_btn').hide();
        $('#quote_reject_btn').hide();
    }
    else
    {
        $('#quote_accept_btn').show();
        $('#quote_reject_btn').show();
    }


}
function showPoPopupData(poKey, companyKey, buyerKey)
{
    $('#poKey').val(poKey);
    if (companyKey == buyerKey)
    {
        $('#po_accept_btn').hide();
        $('#po_reject_btn').hide();
    }
    else
    {
        $('#po_accept_btn').show();
        $('#po_reject_btn').show();
    }


}

function showAnotherVendorDetails(companyKey, type)
{
    //$('#supplier_form_popup').fadeIn();
    //$('#vr_key').val(processData);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ShowAnotherVendorDetails",
        data:
        {
            'companyKey': companyKey
        },
        success: function (data)
        {
            var mydata = data.split("@#@");
            if (type == "Supplier")
            {
                $('#supplier_country').val(mydata[0]);
                $('#supplier_state').val(mydata[1]);
                $('#supplier_city').val(mydata[2]);
                $('#supplier_addr').val(mydata[3]);
                $('#supplier_zipcode').val(mydata[4]);
            }
            else if (type == "Buyer")
            {
                $('#buyer_country').val(mydata[0]);
                $('#buyer_state').val(mydata[1]);
                $('#buyer_city').val(mydata[2]);
                $('#buyer_addr').val(mydata[3]);
                $('#buyer_zipcode').val(mydata[4]);
            }
        },
        error: function (xhr, textStatus, errorThrown)
        {
        }
    });
}

function rfqFormValidation()
{
    document.getElementById('rfq_form_error').style.display = 'none';
    if (document.getElementById('outside_supplier').checked === true) {
        document.getElementById('email').setAttribute("style", "border-color: #a5b7bb;");
        var atpos = $('#email').val().indexOf("@");
        var dotpos = $('#email').val().lastIndexOf(".");
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= $('#email').val().length) {
            document.getElementById('rfq_form_error').innerHTML = "Enter valid outside email address";
            document.getElementById('rfq_form_error').style.display = 'block';
            document.getElementById('email').setAttribute("style", "border-color: red;");
            $('#email').focus();
            return false;
        }
    }
    else
    {
        if ($('#selectedVendorKey').val() === '0')
        {
            document.getElementById('rfq_form_error').innerHTML = "Select company from search company list before send RFQ";
            document.getElementById('rfq_form_error').style.display = 'block';
            $('#to_company').focus();
            return false;
        }
    }
    if ($('#allRfQItems').val() === '')
    {
        document.getElementById('rfq_form_error').innerHTML = "Add at least one item";
        document.getElementById('rfq_form_error').style.display = 'block';
        $('#add_item_btn').focus();
        return false;
    }
    if (document.getElementById('rfq_terms_cond').checked === false)
    {
        document.getElementById('rfq_form_error').innerHTML = "Accept terms & conditions before send RFQ";
        document.getElementById('rfq_form_error').style.display = 'block';
        $('#rfq_terms_cond').focus();
        return false;
    }
    showWarningBroadcast('');
//    return true;
}
function quoteFormValidation()
{
    document.getElementById('quote_form_error').style.display = 'none';
    if (document.getElementById('outside_supplier').checked === true) {
        document.getElementById('email').setAttribute("style", "border-color: #a5b7bb;");
        var atpos = $('#email').val().indexOf("@");
        var dotpos = $('#email').val().lastIndexOf(".");
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= $('#email').val().length) {
            document.getElementById('quote_form_error').innerHTML = "Enter valid outside email address";
            document.getElementById('quote_form_error').style.display = 'block';
            document.getElementById('email').setAttribute("style", "border-color: red;");
            $('#email').focus();
            return false;
        }
    }
    else
    {
        if ($('#selectedVendorKey').val() === '0')
        {
            document.getElementById('quote_form_error').innerHTML = "Select company from search company list before send Quote";
            document.getElementById('quote_form_error').style.display = 'block';
            $('#to_company').focus();
            return false;
        }
    }
    if ($('#allQteItems').val() === '')
    {
        document.getElementById('quote_form_error').innerHTML = "Add at least one item";
        document.getElementById('quote_form_error').style.display = 'block';
        $('#add_item_btn').focus();
        return false;
    }
    if (document.getElementById('quote_terms_cond').checked === false)
    {
        document.getElementById('quote_form_error').innerHTML = "Accept terms & conditions before send Quote";
        document.getElementById('quote_form_error').style.display = 'block';
        $('#rfq_terms_cond').focus();
        return false;
    }
    showLoading();
}
function poFormValidation()
{
    document.getElementById('po_form_error').style.display = 'none';
    if (document.getElementById('outside_supplier').checked === true) {
        document.getElementById('email').setAttribute("style", "border-color: #a5b7bb;");
        var atpos = $('#email').val().indexOf("@");
        var dotpos = $('#email').val().lastIndexOf(".");
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= $('#email').val().length) {
            document.getElementById('po_form_error').innerHTML = "Enter valid outside email address";
            document.getElementById('po_form_error').style.display = 'block';
            document.getElementById('email').setAttribute("style", "border-color: red;");
            $('#email').focus();
            return false;
        }
    }
    else
    {
        if ($('#selectedVendorKey').val() === '0')
        {
            document.getElementById('po_form_error').innerHTML = "Select company from search company list before send PO";
            document.getElementById('po_form_error').style.display = 'block';
            $('#to_company').focus();
            return false;
        }
    }
    if ($('#allQteItems').val() === '')
    {
        document.getElementById('po_form_error').innerHTML = "Add at least one item";
        document.getElementById('po_form_error').style.display = 'block';
        $('#add_item_btn').focus();
        return false;
    }
    if (document.getElementById('po_terms_cond').checked === false)
    {
        document.getElementById('po_form_error').innerHTML = "Accept terms & conditions before send PO";
        document.getElementById('po_form_error').style.display = 'block';
        $('#po_terms_cond').focus();
        return false;
    }
    else
    {
        showLoading();
        return true;
    }
}
function invoiceFormValidation()
{
    document.getElementById('invoice_form_error').style.display = 'none';
    if (document.getElementById('outside_supplier').checked === true) {
        document.getElementById('email').setAttribute("style", "border-color: #a5b7bb;");
        var atpos = $('#email').val().indexOf("@");
        var dotpos = $('#email').val().lastIndexOf(".");
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= $('#email').val().length) {
            document.getElementById('invoice_form_error').innerHTML = "Enter valid outside email address";
            document.getElementById('invoice_form_error').style.display = 'block';
            document.getElementById('email').setAttribute("style", "border-color: red;");
            $('#email').focus();
            return false;
        }
    }
    else
    {
        if ($('#selectedVendorKey').val() === '0')
        {
            document.getElementById('invoice_form_error').innerHTML = "Select company from search company list before send Invoice";
            document.getElementById('invoice_form_error').style.display = 'block';
            $('#to_company').focus();
            return false;
        }
    }
    if ($('#invoice_due_date').val() === "")
    {
        document.getElementById('invoice_form_error').innerHTML = "Enter Invoice Payment Due Date";
        document.getElementById('invoice_form_error').style.display = 'block';
        $('#invoice_due_date').focus();
        return false;
    }
    if ($('#allInvItems').val() === '')
    {
        document.getElementById('invoice_form_error').innerHTML = "Add at least one item";
        document.getElementById('invoice_form_error').style.display = 'block';
        $('#add_item_btn').focus();
        return false;
    }
    if ($('#bill_of_landing').val() !== "" || $('#freight_weight').val() !== "" || $('#date_shipped').val() !== "")
    {
        if ($('#bill_of_landing').val() === "")
        {
            document.getElementById('invoice_form_error').innerHTML = "Enter bill of landing";
            document.getElementById('invoice_form_error').style.display = 'block';
            $('#bill_of_landing').focus();
            return false;
        }
        if ($('#freight_weight').val() === "")
        {
            document.getElementById('invoice_form_error').innerHTML = "Enter Freight Weight";
            document.getElementById('invoice_form_error').style.display = 'block';
            $('#freight_weight').focus();
            return false;
        }
        var fw_val=$('#freight_weight').val();
        var i12=0;
        var ch_val="";
        for(i12=0;i12<fw_val.length;i12++)
        {
            ch_val=fw_val.charAt(i12);
            if (ch_val!="0" && ch_val!="1" && ch_val!="2" &&
                ch_val!="3" && ch_val!="4" && ch_val!="5" &&
                ch_val!="6" && ch_val!="7" && ch_val!="8" &&
                ch_val!="9")
            {
                document.getElementById('invoice_form_error').innerHTML = "Freight Weight must be Numeric only";
                document.getElementById('invoice_form_error').style.display = 'block';
                $('#freight_weight').focus();
                return false;
                break;
            }
        }
        if ($('#date_shipped').val() === "")
        {
            document.getElementById('invoice_form_error').innerHTML = "Enter Shipped Date";
            document.getElementById('invoice_form_error').style.display = 'block';
            $('#date_shipped').focus();
            return false;
        }
    }
    if (document.getElementById('invoice_terms_cond').checked === false)
    {
        document.getElementById('invoice_form_error').innerHTML = "Accept terms & conditions before send Invoice";
        document.getElementById('invoice_form_error').style.display = 'block';
        $('#invoice_terms_cond').focus();
        return false;
    }
    else
    {
        showLoading();
        return true;
    }
}

function validatedBank()
{
    var r1=f1.r1.value;
    if(r1=="bank")
    {
        if($('#t1').val() == "" || $('#t2').val() == ""
        || $('#t3').val() == "" || $('#t4').val() == ""
        || $('#t5').val() == "")
        {
            document.getElementById('bank_error').innerHTML = "Bank Account related Info should not be blank";
            return false;
        }
    }
    else if(r1=="paypal")
    {
        if($('#t6').val()== "" || $('#t7').val() == "")
        {
            document.getElementById('bank_error').innerHTML = "Paypal Account related Info should not be blank";
            return false;
        }
    }
    return true;
}

function spinUp(numValue)
{
    numValue++;
    return numValue;
}

function spinDown(numValue, min)
{
    if (numValue > min)
        numValue--;
    return numValue;
}

var setCount = 1;
function addItemInProductCatalog()
{
    var TemHTML = '<tr id="tempDiv_' + setCount + '">';
    TemHTML += '<td style="width: 40px">\n\
            <img src="inside/remove_item.png" onclick=\"javascript:document.getElementById(\'tempDiv_' + setCount + '\').innerHTML=\'\';\" style="cursor:pointer;z-index:100;margin-right:10px;" title="Remove" />\n\
            </td>';
    TemHTML += '<td style="font-size: 13px;width: 50px">' + setCount + '\
            </td>';
    TemHTML += '<td style="font-size: 13px;width: 100px">' + $("#item_name").val() + '\
            <input type="hidden" value="' + $("#item_name").val() + '" name="txtPname"/></td>';
    TemHTML += '<td style="font-size: 13px;width: 130px;">' + $("#item_description").val() + '\
            <input type="hidden" value="' + $("#item_description").val() + '" name="txtPdesc"/></td>';
    TemHTML += '<td style="font-size: 13px;width: 100px;">' + $("#item_part_no").val() + '\
            <input type="hidden" value="' + $("#item_part_no").val() + '" name="txtPpno"/></td>';
    TemHTML += '<td style="font-size: 13px;width: 100px;">' + $("#item_sku").val() + '\
            <input type="hidden" value="' + $("#item_sku").val() + '" name="txtPsku"/></td>';
    TemHTML += '<td style="font-size: 13px;width: 100px;">' + $("#brcd").val() + '\
            <input type="hidden" value="' + $("#brcd").val() + '" name="txtPbrcd"/></td>';
    TemHTML += '<td style="font-size: 13px;width: 100px;">' + $("#item_quantity").val() + " " + $("#add_quantity_unit").text() + '\
            <input type="hidden" value="' + $("#item_quantity").val() + '" name="txtPqty"/>\n\
            <input type="hidden" value="' + $("#add_quantity_unit").text() + '" name="txtPqtyunit"/></td>';
    TemHTML += '<td style="font-size: 13px;width: 100px;">' + $("#add_currency").text() + " " + $("#item_price").val() + '\
            <input type="hidden" value="' + $("#add_currency").text() + '" name="txtPcur"/>\n\
            <input type="hidden" value="' + $("#item_price").val() + '" name="txtPprice"/></td>';
    TemHTML += '<td style="font-size: 13px;width: 100px;">' + $("#item_tax").val() + '\
            <input type="hidden" value="' + $("#item_tax").val() + '" name="txtPtax"/></td>';
    TemHTML += '<td style="font-size: 13px;width: 100px;">' + ((fileNames.split(", ").length) - 1) + ' Selected<input id="nameOffile" type="hidden" value=' + fileNames + '>\n\
            <input type="hidden" value="' + fileNames + '" name="txtPfileNames"/></td>';
    TemHTML += '<td style="font-size: 13px;width: 140px;display:none;">false\n\
            </td>';
    TemHTML += '</tr>';
    setCount++;
    $("#TempValueHolderDiv").append(TemHTML);
}

var fileNames = "";
function addProductPic(file)
{
    var data2 = new FormData();
    for (var i = 0; i < file.files.length; i++) {
        data2.append('file_name' + i, file.files[i]);
    }
    $.ajax({
        url: 'AddProductToTemp',
        type: 'POST',
        data: data2,
        cache: false,
        //dataType: 'json',
        processData: false, // Don't process the files
        contentType: false, // Set content type to false as jQuery will tell the server its a query string request
        success: function (data)
        {
            fileNames = data;
        //alert(data);
        //opn_fldr_fls(slctd_fldr_nm,slctd_fldr);
        //$("#fl_nm2").val("");
        },
        error: function (jqXHR, textStatus, errorThrown)
        {
        // Handle errors here
        //            alert('ERRORS: ' + errorThrown);
        // STOP LOADING SPINNER
        }
    });
}

function showGlobalItemSearch(startVal)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "SearchExistingItemForTransaction",
        data:
        {
            'startVal': startVal
        },
        success: function (data)
        {
            //alert(data);
            document.getElementById('item_search_result').style.display = "block";
            if (data === "")
            {
                document.getElementById('item_search_result').innerHTML = "<div id='ven_empty' class='filter_comp'>No result found</div>";
            }
            else
            {
                document.getElementById('item_search_result').innerHTML = data;
            }
        //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function (xhr, textStatus, errorThrown)
        {
        // alert("error :"+errorThrown)
        }
    });
}

function showProductDetails(processData)
{
    $('#ProdCatalogEdit').fadeIn();
    //$('#vr_key').val(processData);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ShowProductDetails",
        data:
        {
            'itemKey': processData
        },
        success: function (data)
        {
            document.getElementById('ShowProductDetailsByAjax').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert("error :"+errorThrown)
        }
    });
}
function feedValidater(title, desc)
{
    if (title === "")
    {
        ShowToast("Enter feed title", 1000);
        return false;
    }
    else if (desc === "")
    {
        ShowToast("Enter feed description", 1000);
        return false;
    }
}

function showGlobalNotification()
{
    //alert(countryCode);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxGlobalNotification.jsp",
        data:
        {
            'eventType': 'groupNotification'
        //'countryCode': countryCode
        },
        success: function (data)
        {
            //alert(data);
            //$('#notification_container').toggle();
            document.getElementById('mCSB_1').innerHTML = data;
        //$('#notification_count').fadeOut();
        //            alert(data);

        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        //ShowToast("Address not Updated", 2000);
        }
    });
}

var lastNotificationTypeId = "";
function showGlobalChildNotification(notificationType, count)
{
    //alert(countryCode);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxGlobalNotification.jsp",
        data:
        {
            'eventType': 'notification',
            'notificationType': notificationType
        //'countryCode': countryCode
        },
        success: function (data)
        {
            //alert(data);
            document.getElementById('gn_' + count).innerHTML = data;
            if (lastNotificationTypeId !== "")
            {
                $('#' + lastNotificationTypeId).slideUp();
            }
            $('#gn_' + count).slideDown();
            lastNotificationTypeId = 'gn_' + count;
        //            alert(data);

        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        //ShowToast("Address not Updated", 2000);
        }
    });
}

setTimeout(showGlobalNotificationCount(), 300);
setTimeout(showChatNotification(),300);

function showGlobalNotificationCount()
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "GlobalNotification",
        data:
        {
            'actionType': 'count'
        //'countryCode': countryCode
        },
        success: function (data)
        {
            document.getElementById('notification_count1').innerHTML = data;
            var latest_val = $("#notification_count").html();
            //if(latest_val!="0")
            refresh_chat_boxes();
            setTimeout(showGlobalNotificationCount(), 300);
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function showChatNotification()
{
    //alert(countryCode);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "_chatNotification.jsp",
        data:
        {
            'actionType': 'count'
        },
        success: function (data)
        {
            ShowToast2(data,5000);
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function clearMessages(my, others)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DeleteMessages",
        data:
        {
            'my': my,
            'others': others
        },
        success: function (data)
        {
            ShowToast("Conversation Deleted", 2000);
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function showNotification(notificationId, notificationType, notificationTypeId)
{
    var url = "";
    var ntArray = notificationType.split("@#@");
    if (ntArray[0] === "Message")
    {
        url = "homeMessages.jsp?nid=" + notificationTypeId;
    }
    if (ntArray[0] === "Vendor Registration")
    {
        if (ntArray[1] === "buyer")
            url = "buyersVR.jsp?nid=" + notificationTypeId;
        else
            url = "suppliersVR.jsp?nid=" + notificationTypeId;
    }
    if (ntArray[0] === "Rating")
    {
        url = "homeRatings.jsp?nid=" + notificationTypeId;
    }
    if (ntArray[0] === "My Connection")
    {
        url = "homeMyConnections.jsp?nid=" + notificationTypeId;
    }
    if (ntArray[0] === "RFQ")
    {
        url = "transactions.jsp?nid=" + notificationTypeId;
    }
    if (ntArray[0] === "Quote")
    {
        url = "transactionsQuote.jsp?nid=" + notificationTypeId;
    }
    if (ntArray[0] === "PO")
    {
        url = "transactionsPurchaseOrder.jsp?nid=" + notificationTypeId;
    }
    if (ntArray[0] === "INV")
    {
        url = "transactionsInvoice.jsp?nid=" + notificationTypeId;
    }

    var what_to_do = "Notification Id (" + notificationId + ") ";
    what_to_do += "will be displayed on concerning page (" + notificationType + ") ";
    what_to_do += "using Notification Type Id (" + notificationTypeId + ")";

    updateNotificationStatus(notificationId,url);
//    var result=0;
//    for(loop=1;loop<=1;loop++)
//        result+=updateNotificationStatus(notificationId);

//    setTimeout(updateNotificationStatus(notificationId), 1000);
//    showLoading();
//    window.location.href = url;
//    }
}

function updateNotificationStatus(notificationId)
{
    //    alert("enter : "+notificationId);
    //    var resVal=0;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "GlobalNotification",
        data:
        {
            'actionType': 'updateNotification',
            'notificationKey': ''+notificationId
        },
        success: function (data)
        {
        //            alert("success : "+notificationId);
        //            ShowToast("updateNotificationStatus data : "+data,2000);
        //            return parseInt(""+data);
        //            resVal+=parseInt(""+data);
        //            return resVal;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //            alert("error : "+notificationId);
        //            resVal+=parseInt(""+data);
        //            return "0";
        //            ShowToast("errorThrown at updateNotificationStatus() : "+errorThrown, 2000);
        }
    });
//    return resVal;
}

function updateNotificationStatus(notificationId,url)
{
    //    alert("enter : "+notificationId);
    //    var resVal=0;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "GlobalNotification",
        data:
        {
            'actionType': 'updateNotification',
            'notificationKey': ''+notificationId
        },
        success: function (data)
        {
            //            alert("success : "+notificationId);
            //            ShowToast("updateNotificationStatus data : "+data,2000);
            //            return parseInt(""+data);
            //            resVal+=parseInt(""+data);
            //            return resVal;
            showLoading();
            window.location.href = url;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //            alert("error : "+notificationId);
        //            resVal+=parseInt(""+data);
        //            return "0";
        //            ShowToast("errorThrown at updateNotificationStatus() : "+errorThrown, 2000);
        }
    });
//    return resVal;
}

function showVREnquireBox()
{
    $('#add_inquiry_content').slideDown();
    $('#cntrl_btns').fadeOut();
}

function hideVREnquireBox()
{
    $('#add_inquiry_content').slideUp();
    $('#cntrl_btns').fadeIn();
}

function validateInquireMessage()
{
    if ($('#new_inquire_message').val() === "")
    {
        ShowToast("Enter Inquire Message", 1000);
        return false;
    }
}

function transactionEnquireBox()
{
    $('#rfq_popup_new_inquire').slideDown();
//$('#cntrl_btns').fadeOut();
}

//function transactionEnquireBox()
//{
//    $('#popup_inquires').slideUp();
//    //$('#cntrl_btns').fadeIn();
//}

function validateTransactionInquireMessage()
{
    if ($('#new_inquire_message').val() === "")
    {
        ShowToast("Enter Inquire Message", 1000);
        return false;
    }
    showLoading();
}

function showRfqDetailInPopup(rfqKey, buyerKey, supplierKey)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxShowRfqDetails.jsp",
        data:
        {
            'rfqKey': rfqKey,
            'buyerKey': buyerKey,
            'supplierKey': supplierKey
        },
        success: function (data)
        {
            document.getElementById('rfq_popup').innerHTML = data;
        //  alert(data);

        },
        error: function (xhr, textStatus, errorThrown)
        {
        // alert("errorThrown :"+errorThrown);
        }
    });
}

function showQuoteDetailInPopup(quoteKey, buyerKey, supplierKey)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxShowQuoteDetails.jsp",
        data:
        {
            'quoteKey': quoteKey,
            'buyerKey': buyerKey,
            'supplierKey': supplierKey
        },
        success: function (data)
        {
            document.getElementById('quote_popup').innerHTML = data;
        //            alert(data);

        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function showPoDetailInPopup(poKey, buyerKey, supplierKey)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxShowPoDetails.jsp",
        data:
        {
            'poKey': poKey,
            'buyerKey': buyerKey,
            'supplierKey': supplierKey
        },
        success: function (data)
        {
            document.getElementById('po_popup').innerHTML = data;
        //            alert(data);

        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}
function showInvDetailInPopup(invoiceKey, buyerKey, supplierKey)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxShowInvDetails.jsp",
        data:
        {
            'invoiceKey': invoiceKey,
            'buyerKey': buyerKey,
            'supplierKey': supplierKey
        },
        success: function (data)
        {
            document.getElementById('invoice_popup').innerHTML = data;
        //            alert(data);

        },
        error: function (xhr, textStatus, errorThrown)
        {
            ShowToast(errorThrown);
        }
    });
}
function showVrList(actionType, companyName, businessKey, companyKey)
{
    $('#regSearchResult').hide();
    $('#nonregSearchResult').hide();
    $('#actionType').val(actionType);
    $('#companyName').val(companyName);
    $('#businessKey').val(businessKey);
    $('#companyKey').val(companyKey);

    if (document.getElementById('searchRegistered').checked)
    {
        showInNetwork();
    }
    else
    {
        showInNetwork();
        showOutNetwork();
    }
    $('#searchHeading').show();
    //    $('#searchResults').show();
    $('#searchDataResults').show();
}

function showInNetwork()
{
    var actionType = $('#actionType').val();
    var companyName = $('#searchTextBox').val();
    var businessKey = $('#basic_search_cat').val();
    var companyKey = $('#companyKey').val();
    var vrType = $('#vrType').val();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
        {
            'businessType': 'inNetwork',
            'actionType': actionType,
            'companyName': companyName,
            'businessKey': businessKey,
            'companyKey': companyKey,
            'vrType': vrType
        },
        success: function (data)
        {
            $('#regSearchResult').show();
            document.getElementById('regSearchResult').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function showOutNetwork()
{
    var actionType = $('#actionType').val();
    var companyName = $('#searchTextBox').val();
    var businessKey = $('#basic_search_cat').val();
    var companyKey = $('#companyKey').val();
    var vrType = $('#vrType').val();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
        {
            'businessType': 'outNetwork',
            'actionType': actionType,
            'companyName': companyName,
            'businessKey': businessKey,
            'companyKey': companyKey,
            'vrType': vrType
        },
        success: function (data)
        {
            $('#nonregSearchResult').show();
            document.getElementById('nonregSearchResult').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function advanceSearchCount(actionType, companyName, businessKey, companyKey)
{
    $('#actionType').val(actionType);
    $('#companyName').val(companyName);
    $('#businessKey').val(businessKey);
    $('#companyKey').val(companyKey);
    var actionType = $('#actionType').val();
    var companyName = $('#searchTextBox').val();
    var businessKey = $('#Advanced_Selectbox').val();
    var companyKey = $('#companyKey').val();
    var vrType = $('#vrType').val();
    var country = $('#cntry').val();
    var part_number = $('#advanPartNumber1').val();
    var product_name = $('#advanName1').val();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "advanceSearchCount.jsp",
        data:
        {
            'businessType': 'inNetworkAdvance',
            'actionType': actionType,
            'companyName': companyName,
            'businessKey': businessKey,
            'companyKey': companyKey,
            'vrType': vrType,
            'country': country,
            'part_number': part_number,
            'product_name': product_name
        },
        success: function (data)
        {
            //alert(data);
            $('#splr_slctd_srch').html(data + " " + actionType + "(s) Exists");
        //$('#splr_slctd_srch').html($('#advance_search_count').val());
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function showVrListAdvance(actionType, companyName, businessKey, companyKey)
{
    //    alert();
    $('#regSearchResult').html('');
    $('#nonregSearchResult').html('');
    $('#actionType').val(actionType);
    $('#companyName').val(companyName);
    $('#businessKey').val(businessKey);
    $('#companyKey').val(companyKey);

    if (document.getElementById('regSupplierCheck').checked)
    {
        showInNetworkAdvance();
    }
    else
    {
        showInNetworkAdvance();
        showOutNetworkAdvance();
    }
    $('#searchHeading').show();
    //    $('#searchResults').show();
    $('#searchDataResults').show();
}
//var count=0;
function showInNetworkAdvance()
{
    var actionType = $('#actionType').val();
    var companyName = $('#searchTextBox').val();
    var businessKey = $('#Advanced_Selectbox').val();
    var companyKey = $('#companyKey').val();
    var vrType = $('#vrType').val();
    var country = $('#cntry').val();
    var part_number = $('#advanPartNumber1').val();
    var product_name = $('#advanName1').val();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
        {
            'businessType': 'inNetworkAdvance',
            'actionType': actionType,
            'companyName': companyName,
            'businessKey': businessKey,
            'companyKey': companyKey,
            'vrType': vrType,
            'country': country,
            'part_number': part_number,
            'product_name': product_name
        },
        success: function (data)
        {
            document.getElementById('regSearchResult').innerHTML = data;
        // count=parseInt($('#in_advance_search_count').val());

        //$('#splr_slctd_srch').html($('#advance_search_count').val());
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function showOutNetworkAdvance()
{
    var actionType = $('#actionType').val();
    var companyName = $('#searchTextBox').val();
    var businessKey = $('#Advanced_Selectbox').val();
    var companyKey = $('#companyKey').val();
    var vrType = $('#vrType').val();
    var country = $('#cntry').val();
    var part_number = $('#advanPartNumber1').val();
    var product_name = $('#advanName1').val();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
        {
            'businessType': 'outNetworkAdvance',
            'actionType': actionType,
            'companyName': companyName,
            'businessKey': businessKey,
            'companyKey': companyKey,
            'vrType': vrType,
            'country': country,
            'part_number': part_number,
            'product_name': product_name
        },
        success: function (data)
        {
            document.getElementById('nonregSearchResult').innerHTML = data;
        //count+=parseInt($('#out_advance_search_count').val());
        //$('#splr_slctd_srch').html(count+" Supplier Exists");
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}

var lastEventType = "connections";

function showConnectionBoxByTab(eventType)
{
    if (eventType === "addConnection")
    {
        if (lastEventType !== "addConnection")
        {
            $('#new_conn_container').fadeIn();
            $('#conn_container').fadeOut();
            $('#contbluetab').fadeOut();

            $('#my_conn_tab').removeClass("orangetab");
            $('#my_conn_tab').addClass("graytab");
            $('#add_conn_tab').removeClass("graytab");
            $('#add_conn_tab').addClass("orangetab");

            lastEventType = "addConnection";
        }
    }
    else if (eventType === "connections")
    {
        if (lastEventType !== "connections")
        {
            $('#new_conn_container').fadeOut();
            $('#conn_container').fadeIn();
            $('#contbluetab').fadeIn();

            $('#my_conn_tab').removeClass("graytab");
            $('#my_conn_tab').addClass("orangetab");
            $('#add_conn_tab').removeClass("orangetab");
            $('#add_conn_tab').addClass("graytab");
        }

        lastEventType = "connections";
    }
    else if (eventType === "pending")
    {

}
}


function swapWkTabs(id1, id2, tab1, tab2)
{
    $('#' + id1).removeClass('lgraytab');
    $('#' + id1).addClass('bluetab');
    $('#' + id2).removeClass('bluetab');
    $('#' + id2).addClass('lgraytab');
    $('#' + tab1).show();
    $('#' + tab2).hide();
}

function showAllUser()
{
    $('#new_conn_list').html('');
    $('#new_conn_right').html('');
    var value = $('#connectionSearch').val();
    if (value !== "")
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "ajaxAllUser.jsp",
            data:
            {
                'eventType': 'showAllUser',
                'value': value
            },
            success: function (data)
            {          // alert(data);
                document.getElementById('new_conn_list').innerHTML = data;
            },
            error: function (xhr, textStatus, errorThrown)
            {
            //alert("errorThrown :"+errorThrown);
            }
        });
    }
}


var last_selected_user = "";
function showUserDetail(userKey)//,div_id)
{
    //    alert(userKey);
    //    $('#'+div_id).removeClass('listcontainer');
    //    $('#'+div_id).removeClass('listcontainer_selected');
    if (last_selected_user !== "")
    {
        $('#new_conn_' + last_selected_user).removeClass('listcontainer_selected');
        $('#new_conn_' + last_selected_user).addClass('listcontainer');
    }
    $('#new_conn_' + userKey).removeClass('listcontainer');
    $('#new_conn_' + userKey).addClass('listcontainer_selected');
    last_selected_user = userKey;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxUserDetail.jsp",
        data:
        {
            'userKey': userKey
        },
        success: function (data)
        {
            //            alert(data);
            document.getElementById('new_conn_right').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}

var last_selected_connection = "";
function showUserDetail2(connectionKey)
{
    if (last_selected_connection !== "")
    {
        $('#new_conn_' + last_selected_connection).removeClass('listcontainer_selected');
        $('#new_conn_' + last_selected_connection).addClass('listcontainer');
    }
    $('#new_conn_' + connectionKey).removeClass('listcontainer');
    $('#new_conn_' + connectionKey).addClass('listcontainer_selected');
    last_selected_connection = connectionKey;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxUserDetail.jsp",
        data:
        {
            'connectionKey': connectionKey
        },
        success: function (data)
        {
            //            alert(data);
            document.getElementById('my_conn_right').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}

var last_selected_connection2 = "";
function showUserDetail3(userKey, connectionId)
{
    if (last_selected_connection2 !== "")
    {
        $('#new_conn_' + last_selected_connection2).removeClass('listcontainer_selected');
        $('#new_conn_' + last_selected_connection2).addClass('listcontainer');
    }
    $('#new_conn_' + connectionId).removeClass('listcontainer');
    $('#new_conn_' + connectionId).addClass('listcontainer_selected');
    last_selected_connection2 = connectionId;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxUserDetail.jsp",
        data:
        {
            'requestPanding': 'requestPanding',
            'connectionId': connectionId,
            'userKey': userKey
        },
        success: function (data)
        {
            //            alert(data);
            document.getElementById('pending_conn_right').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //ShowToast("Address not Updated", 2000);
        }
    });
}

function connectionRequest(userKeyTo, companyKeyTo)
{
    $('#add_conn_btn').val('Request Sent')
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Connection",
        data:
        {
            'actionType': 'insert',
            'userKeyTo': userKeyTo,
            'companyKeyTo': companyKeyTo
        },
        success: function (data)
        {
            //alert("data"+data);
            ShowToast("Connection request sent", 2000);
            window.location.href = 'homeMyConnections.jsp';
        //            alert(data);
        //document.getElementById('new_conn_right').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("Connection request failed", 2000);
        }
    });
}

function respondOnConnectionRequest(status, connectionId)//userKeyTo,userKeyFrom,companyKeyTo)
{
    //    var connectionKey=$('#connectionKey').val();
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "Connection",
        data:
        {
            'actionType': 'statusUpdate',
            //                    'userKeyTo': userKeyTo,
            //                    'userKeyFrom': userKeyFrom,
            //                    'companyKeyTo': companyKeyTo,
            //                    'connectionKey': connectionKey,
            'connectionId': connectionId,
            'status': status
        },
        success: function (data)
        {
            //alert("data"+data);
            ShowToast("Connection request " + status, 2000);
            window.location.href = 'homeMyConnections.jsp';
        //            alert(data);
        //document.getElementById('new_conn_right').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("Connection request not " + status, 2000);
        }
    });
}

function moveStarBg(count)
{
    for (i = 1; i <= 5; i++)
    {
        $("#strting" + i).removeClass("fillStarts");
    }
    for (i = 1; i <= count; i++)
    {
        $("#strting" + i).addClass("fillStarts");
    }
}

function moveOutStarBg()
{
    for (i = 1; i <= 5; i++)
    {
        $("#strting" + i).removeClass("fillStarts");
    }
}

function setStarBg(count)
{
    //    alert(count);

    for (i = 1; i <= count; i++)
    {
        $("#strting" + i).addClass("fillStarts");
    }
    $("#starRatingWK").val(count);
    $("#rating").val(count);
}

var last_selected_connection3 = "";
function showRatingDetail(companyName, companyKey, rating)
{
    //alert('1');
    if (last_selected_connection3 !== "")
    {
        $('#ratingscompanylistcontainer' + last_selected_connection3).removeClass('selectedItem');
    }
    $('#ratingscompanylistcontainer' + companyKey).addClass('selectedItem');
    last_selected_connection3 = companyKey;
    //alert('2');
    $('#companyKeyTo').val(companyKey);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxRatingDetail.jsp",
        data:
        {
            'companyName': companyName,
            'companyKey': companyKey,
            'rating': rating
        },
        success: function (data)
        {
            //alert(data);
            document.getElementById('ratingDetail').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        }
    });
}
var last_selected_connection4 = "";
function showMessages(userKey, first_name, last_name, email)
{
    if (last_selected_connection4 !== "")
    {
        $('#my_conn_' + last_selected_connection4).removeClass('listcontainer_selected');
        $('#my_conn_' + last_selected_connection4).addClass('listcontainer');
    }
    $('#my_conn_' + userKey).removeClass('listcontainer');
    $('#my_conn_' + userKey).addClass('listcontainer_selected');
    last_selected_connection4 = userKey;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxMessages.jsp",
        data:
        {
            'userKey': userKey
        },
        success: function (data)
        {
            //alert(data);
            document.getElementById('messages').innerHTML = data;
            setInterval(showUpdatedMessages(userKey), 1000);
            $('#search_member_text').val(first_name + " " + last_name + " (" + email + ")");
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //            alert(errorThrown);
        }
    });
}

function showUpdatedMessages(userKey)
{
    //    if (last_selected_connection4 !== "")
    //    {
    //        $('#my_conn_' + last_selected_connection4).removeClass('listcontainer_selected');
    //        $('#my_conn_' + last_selected_connection4).addClass('listcontainer');
    //    }
    //    $('#my_conn_' + userKey).removeClass('listcontainer');
    //    $('#my_conn_' + userKey).addClass('listcontainer_selected');
    //    last_selected_connection4 = userKey;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxShowUpdateMessage.jsp",
        data:
        {
            'userKey': userKey
        },
        success: function (data)
        {
            //alert(data);
            $('#mCSB_container').append(data);
            setTimeout(showUpdatedMessages(userKey), 300);
            setTimeout(showChatNotification(),300);
        },
        error: function (xhr, textStatus, errorThrown)
        {
        // alert(errorThrown);
        }
    });
}

function addProductValidate()
{
    if ($('#item_name').val() === "")
    {
        ShowToast("Enter Product Name");
        return false;
    }
    //var numbers = /^[0-9]+$/;
    var qty = $('#item_quantity').val();
    if (qty === "")
    {
        ShowToast("Enter Quantity");
        return false;
    }
    var ch = "";
    for (var i = 0; i < qty.length; i++)
    {
        ch = qty.charAt(i);
        if (!(ch >= 0 && ch <= 9) && ch != ".")
        {
            ShowToast("Quantity must be numeric ( i.e. 12 or 12.36 )");
            return false;
        }
    }
    var qty = $('#item_price').val();
    if (qty === "")
    {
        ShowToast("Enter Price");
        return false;
    }
    var ch = "";
    for (var i = 0; i < qty.length; i++)
    {
        ch = qty.charAt(i);
        if (!(ch >= 0 && ch <= 9) && ch != ".")
        {
            ShowToast("Price must be numeric ( i.e. 12 or 12.36 )");
            return false;
        }
    }
    var qty = $('#item_tax').val();
    if (qty === "")
    {
        ShowToast("Enter Tax");
        return false;
    }
    var ch = "";
    for (var i = 0; i < qty.length; i++)
    {
        ch = qty.charAt(i);
        if (!(ch >= 0 && ch <= 9) && ch != ".")
        {
            ShowToast("Tax must be numeric ( i.e. 12 or 12.36 )");
            return false;
        }
    }
    showLoading();
    return true;
//showLoading();
}
function chatList()
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "chatList.jsp",
        data:
        {
        },
        success: function (data)
        {
            document.getElementById('chat_list').innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {

        }
    });
}

var c_boxes = new Array();

function manageChatBox(id, name)
{
    var i = 0;
    for (i = 0; i < chatids.length; i++)
    {
        if (id === chatids[i])
        {
            //alert("already chating with this user");
            $('#chat-content-' + id).toggle();
            break;
        }
    }
    if (chatids.length === i)
    {
        chatid += "@#@" + id;
        chatids = chatid.split("@#@");
        chatboxes++;
        var result = "<div class='chat-window' id='chat-box-" + id + "' style='max-height:231px;right:" + (240 * chatboxes) + "px;'>\n\
                        <div class='chat-window-title' onclick=\"$('#chat-content-" + id + "').toggle()\">\n\
                            <div class='text'>" + name + "<div class='close' style='background:none;' onclick='javascript:$(\"#chat-box-" + id + "\").remove();closeChatBox(\"" + id + "\",\"" + id + "\", \"" + name + "\");'>X</div></div>\n\
                        </div>\n\
                        <div class='chat-window-content' id='chat-content-" + id + "'>\n\
                            <div id='chatcontent" + id + "' style='margin-bottom:50px;overflow-y: scroll!important;height:165px;'></div>\n\
                            <div style='bottom: 0;position: fixed;'>\n\
                                <input type='text' onfocus=\"javascript:$('#chat_to_user_key').val('" + id + "');\" id='chattext" + id + "'/>\n\
                                <input type='button' id='chatbtn" + id + "' onclick=\"javascript:saveMessage();\" value='Send'> \n\
                            </div>\n\
                        </div>\n\
                    </div>";
        $('#chat_boxes').append(result);
    //$('#chat-content-' + id).toggle();
    }
}

function closeChatBox(id, myid, name)
{
    chatid = chatid.replace("@#@" + id, "");
    chatids = chatid.split("@#@");
    chatboxes--;

    c_boxes = new Array();

    for (i = 0; i < chatids.length; i++)
    {
        if (id !== chatids[i] || chatids[i] != "")
            c_boxes.push(chatids[i]);
    }
    reArrangeChatBoxes();
}

function reArrangeChatBoxes()
{
    for (i = 0; i < c_boxes.length; i++)
    {
        $('#chat-box-' + c_boxes[i]).css('z-index', '2').animate({
            'right': (240 * i) + "px"
        });
        $('#chattext' + c_boxes[i]).css('z-index', '3').animate({
            'right': (240 * i) + "px"
        });
        $('#chatbtn' + c_boxes[i]).css('z-index', '4').animate({
            'right': (240 * i) + "px"
        });
        $('#chat-content-' + c_boxes[i]).toggle();
        $('#chat-box-' + c_boxes[i]).animate({
            right: (240 * i) + "px"
        }, 1500);
    }
}

function displayunicode(e) {
    var unicode = e.keyCode ? e.keyCode : e.charCode;
    if (unicode === 13)
    {
        saveMessage();
    }
}

function displayunicode3(e, k) {
    var unicode = e.keyCode ? e.keyCode : e.charCode;
    if (unicode === 13)
    {
        showVrList('Supplier', $('#searchTextBox').val(), $('#basic_search_cat').val(), k);
        return false;
    }
    return true;
}

function displayunicode4(e, k) {
    var unicode = e.keyCode ? e.keyCode : e.charCode;
    if (unicode === 13)
    {
        showVrList('Buyer', $('#searchTextBox').val(), $('#basic_search_cat').val(), k);
    }
}

function displayunicode2(e) {
    var unicode = e.keyCode ? e.keyCode : e.charCode;
    if (unicode === 13)
    {
        showAllUser();
    }
}

function saveMessage()
{
    var Message = $('#chattext' + $('#chat_to_user_key').val()).val();
    $('#chattext' + $('#chat_to_user_key').val()).val('');
    if ($('#chat_to_user_key').val() !== "0")
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "ChatMessages",
            data:
            {
                'userKeyTo': $('#chat_to_user_key').val(),
                'message': Message
            },
            success: function (data)
            {
                //alert(data);
                //getMessage();
                $('#chat_to_user_key').focus();
            },
            error: function (xhr, textStatus, errorThrown)
            {
            //alert(errorThrown);
            }
        });
    }
}

function getMessage(chat_user_to_key)
{
    // alert(chat_user_to_key);    
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxShowChatMessages.jsp",
        data:
        {
            'userKey': chat_user_to_key
        },
        success: function (data)
        {
            //alert(data);
            document.getElementById('chatcontent' + chat_user_to_key).innerHTML = data;
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        }
    });
}

//refresh_chat_boxes();

function refresh_chat_boxes()
{
    //alert(chatids.length);
    chatList();
    for (var i = 0; i < chatids.length; i++)
    {
        getUnreadMessage(chatids[i]);
    }
}
function getUnreadMessage(chat_user_to_key)
{
    // alert(chat_user_to_key);    
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "ajaxShowChatUnreadMessages.jsp",
        data:
        {
            'userKey': chat_user_to_key
        },
        success: function (data)
        {
            //alert(data);
            $('#chatcontent' + chat_user_to_key).append(data);
        },
        error: function (xhr, textStatus, errorThrown)
        {
        //alert(errorThrown);
        }
    });
}
function send_private_message()
{
    var user_key = $('#userKeyTo').val();
    var message = $('#message').val();
    var image = $('#image').val();
    // alert(image);
    if (message !== "")
    {
        //        showLoading();
        $('#message').val('');
        $('#message').focus();
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "Messages",
            data:
            {
                'userKeyTo': user_key,
                'message': message
            },
            success: function (data)
            {
                //alert(data);
                document.getElementById('chatcontent' + chat_user_to_key).innerHTML = data;
            },
            error: function (xhr, textStatus, errorThrown)
            {
            //                alert(errorThrown);
            }
        });
    }
}

function ShowToast(Message, ShowTime)
{
    if (typeof (ShowTime) === 'undefined')
        ShowTime = 2000;

    var Toast_Obj = $("#Toast_Window");
    var outerWidth = $(Toast_Obj).outerWidth() / 2;
    //Toast_Obj.show(500);
    Toast_Obj.fadeIn(500);
    //Toast_Obj.slideToggle(500)
    $(".Toast_Data").html(Message);
    Toast_Obj.delay(ShowTime);
    //Toast_Obj.hide(500);
    Toast_Obj.fadeOut(2000);
//Toast_Obj.slideToggle(500);
}

function ShowToast2(Message, ShowTime)
{
    //        Toast_Obj.fadeIn(500);
    //    alert("'"+Message+"'");
    Message=Message.replace("\n\
\n\
\n\
\n\
\n\
", "");
    $(".Toast_Data2").html(Message);
    var msg=$(".Toast_Data2").html();
    
    if(msg!="")
    {
        if (typeof (ShowTime) === 'undefined')
            ShowTime = 5000;

        var Toast_Obj = $("#Toast_Window2");
        //$(".Toast_Data2").html(Message);
        Toast_Obj.slideToggle(500);
        Toast_Obj.delay(ShowTime);
        Toast_Obj.slideToggle(500);
    }
}

//function submitTCform()
//{
//    alert('hi 2');
//    $('#tc_form').submit();
//    alert('hi 3');
//}
//
//function uploadFileClick()
//{
//    alert("hi 1");
//    $('#file_upload').click();
//    alert("hi 4");
//}
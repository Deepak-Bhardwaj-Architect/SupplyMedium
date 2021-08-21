/* 
 * 
 * 
 * 
 */
function addUserToDepartment()
{ 
   $('#userKey').val($("#existusersel option:selected").val());
   $('#departmentKey').val($("#deptlistsel option:selected").val());
   //alert($('#departmentKey').val());
   
   $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'addUserToDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey' : $('#departmentKey').val(),
                    'userKey' : $('#userKey').val()
                },
        success: function(data)
        {
            //alert(data);
            ShowToast("User Added in Department", 2000);  
            showNonExistingUserInDepartment();
            showExistingUserInDepartment();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
           // alert("error :"+errorThrown)
        }
    });  
}

function deleteUserFromDepartment()
{
        $('#userKey').val($("#deptusersel option:selected").val());
        $('#departmentKey').val($("#deptlistsel option:selected").val());
        $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'deleteUserFromDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey' : $('#departmentKey').val(),
                    'userKey' : $('#userKey').val() 
                },
        success: function(data)
        {
            ShowToast("User Removed from Department", 2000);  
            showNonExistingUserInDepartment();
            showExistingUserInDepartment();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
           // alert("error :"+errorThrown)
        }
    });  
}


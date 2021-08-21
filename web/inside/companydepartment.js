/* 
 * 
 * 
 * 
 */
function addDepartment()
{
$("#departmentName").val($("#new_dept_input").val().trim());
    if($("#departmentName").val() !== "")
	{
   $("#add_group_popup").fadeOut();
   $("#new_dept_input").val("");         
   $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyDepartment",
        data:
                {
                    'eventType': 'addDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentName' : $('#departmentName').val()      
                },
        success: function(data)
        {
            ShowToast("Department Created", 2000);  
            showCompanyDepartment();
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
		$('#new_department_input_err').text("Enter Department Name");
	}
}

function updateDepartment()
{
$('#departmentKey').val($("#deptlistsel option:selected").val());
$('#departmentName').val($("#edit_dept_input").val());
$("#edit_group_popup").fadeOut();
   $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyDepartment",
        data:
                {
                    'eventType': 'updateDepartment',
                    'departmentKey':$('#departmentKey').val(),
                    'departmentName':$('#departmentName').val()
                },
        success: function(data)
        {
            //alert(data);
            ShowToast("Department renamed", 2000); 
            showCompanyDepartment();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            alert("error :"+errorThrown)
        }
    });    
}

function deleteDepartment()
{
 $('#departmentKey').val($("#deptlistsel option:selected").val());
 $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyDepartment",
        data:
                {
                    'eventType': 'deleteDepartment',
                    'departmentKey' : $('#departmentKey').val()
                },
        success: function(data)
        {
            ShowToast("Department Deleted", 2000);
            showCompanyDepartment();
            
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });   
}

function addUserToDepartment()
{ 
   $('#userKey').val($("#existusersel option:selected").val());
   $('#departmentKey').val($("#deptlistsel option:selected").val());
   //alert($('#departmentKey').val());
   if($('#userKey').val().trim!=="")
   {
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
   else
   {
       ShowToast("Select User before Assign to Department", 2000);
   }
}

function deleteUserFromDepartment()
{
        $('#userKey').val($("#deptusersel option:selected").val());
        $('#departmentKey').val($("#deptlistsel option:selected").val());
        if($('#userKey').val().trim!=="")
        {
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
   else
   {
       ShowToast("Select User before Remove from Department", 2000);
   }
}



function showCompanyDepartment()
{
  $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyDepartment",
        data:
                {
                    'eventType': 'showCompanyDepartment',
                    'companyKey':$('#companyKey').val()
                },
        success: function(data)
        {
            //alert("data :"+data);
            //document.getElementById('deptlistsel').innerHTML = data;
            $("#deptlistsel").html('');
                $("#deptlistsel").append(data);
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });  
}
function showNonExistingUserInDepartment() 
{   
 $('#departmentKey').val($("#deptlistsel option:selected").val());
 if($('#departmentKey').val()!=="")
 {
        $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'showNonExistingUserInDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey':$('#departmentKey').val(),
                },
        success: function(data)
        {
            //alert("data :"+data);
            //document.getElementById('existusersel').innerHTML = data;
            $("#existusersel").html('');
                $("#existusersel").append(data);
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });
}
}
function showExistingUserInDepartment() 
{
 $('#departmentKey').val($("#deptlistsel option:selected").val());
 if($('#departmentKey').val()!=="")
 {
 $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'showExistingUserInDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey':$('#departmentKey').val(),
                },
        success: function(data)
        {
            //document.getElementById('deptusersel').innerHTML = data;
            $("#deptusersel").html('');
                $("#deptusersel").append(data);
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });
}
}
function addGroupToDepartment()
{ 
   $('#groupKey').val($("#existgroupsel option:selected").val());
   $('#departmentKey').val($("#deptlistsel option:selected").val());
   //alert($('#departmentKey').val());
   if($('#groupKey').val()!=="")
   {
   $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'addGroupToDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey' : $('#departmentKey').val(),
                    'groupKey' : $('#groupKey').val()
                },
        success: function(data)
        {
            //alert(data);
            ShowToast("Group Added in Department", 2000);  
            showNonExistingGroupInDepartment();
            showExistingGroupInDepartment();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            alert("error :"+errorThrown)
        }
    });  
}
else
{
 ShowToast("Select Group before Assign to Department", 2000);   
}
}

function deleteGroupFromDepartment()
{ 
   $('#groupKey').val($("#pregroupsel option:selected").val());
   $('#departmentKey').val($("#deptlistsel option:selected").val());
   //alert($('#departmentKey').val());
   if($('#groupKey').val()!=="")
   {
   $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'deleteGroupFromDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey' : $('#departmentKey').val(),
                    'groupKey' : $('#groupKey').val()
                },
        success: function(data)
        {
            //alert(data);
            ShowToast("Group Added in Department", 2000);  
            showNonExistingGroupInDepartment();
            showExistingGroupInDepartment();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });  
}
else
{
 ShowToast("Select Group before Remove from Department", 2000);   
} 
}
function showNonExistingGroupInDepartment() 
{   
 $('#departmentKey').val($("#deptlistsel option:selected").val());
 if($('#departmentKey').val()!=="")
 {
        $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'showNonExistingGroupInDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey':$('#departmentKey').val(),
                },
        success: function(data)
        {
            //alert("data :"+data);
            //document.getElementById('existgroupsel').innerHTML = data;
            $("#existgroupsel").html('');
                $("#existgroupsel").append(data);
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });
}
}
function showExistingGroupInDepartment() 
{
 $('#departmentKey').val($("#deptlistsel option:selected").val());
 if($('#departmentKey').val()!=="")
 {    
 $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'showExistingGroupInDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey':$('#departmentKey').val(),
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
            //alert("error :"+errorThrown)
        }
    });
}
}

function addFolder()
{ 
   if($('#new_folder_input').val().trim()!=="")
   {
   var folderName=$('#new_folder_input').val();
   $('#add_folder_popup').fadeOut();
   $('#new_folder_input').val("");
        $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'addFolder',
                    'companyKey':$('#companyKey').val(),
                    'folderName' : folderName
                },
        success: function(data)
        {
            ShowToast("Folder Added", 2000);  
            showNonExistingFolderInDepartment();
            //showExistingFolderInDepartment();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            alert("error :"+errorThrown)
        }
    });  
}
else
{
 $('#new_folder_input_err').html("Enter Valid Folder Name");   
}
}

function deleteFolder()
{ 
   $('#folderKey').val($("#existfoldersel option:selected").val());
   //alert($('#folderKey').val());
   if($('#folderKey').val().trim()!=="")
   {
   $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'deleteFolder',
                    'folderKey':$('#folderKey').val()                    
                },
        success: function(data)
        {
            //alert(data);
            ShowToast("Folder Deleted", 2000);  
            showNonExistingFolderInDepartment();
            showExistingFolderInDepartment();
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });  
}
else
{
 ShowToast("Select folder before Delete", 2000);   
} 
}
function showNonExistingFolderInDepartment() 
{   
 $('#departmentKey').val($("#deptlistsel option:selected").val());
 if($('#departmentKey').val()!=="")
 {
        $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'showNonExistingFolderInDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey':$('#departmentKey').val(),
                },
        success: function(data)
        {
            //alert("data :"+data);
            //document.getElementById('existfoldersel').innerHTML = data;
            $("#existfoldersel").html('');
                $("#existfoldersel").append(data);
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });
}
}
function showExistingFolderInDepartment() 
{
 $('#departmentKey').val($("#deptlistsel option:selected").val());
 if($('#departmentKey').val()!=="")
 {    
 $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'showExistingFolderInDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey':$('#departmentKey').val(),
                },
        success: function(data)
        {
            //alert("data :"+data);
            //document.getElementById('deptfoldersel').innerHTML = data;
            $("#deptfoldersel").html('');
                $("#deptfoldersel").append(data);
            //document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("error :"+errorThrown)
        }
    });
}
}

function addFolderToDepartment()
{ 
   $('#folderKey').val($("#existfoldersel option:selected").val());
   $('#departmentKey').val($("#deptlistsel option:selected").val());
   //alert($('#departmentKey').val());
   if($('#folderKey').val().trim!=="")
   {
   $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'addFolderToDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey' : $('#departmentKey').val(),
                    'folderKey' : $('#folderKey').val()
                },
        success: function(data)
        {
            //alert(data);
            ShowToast("Folder Added in Department", 2000);  
            showNonExistingFolderInDepartment();
            showExistingFolderInDepartment();
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
       ShowToast("Select Folder before Assign to Department", 2000);
   }
}

function deleteFolderFromDepartment()
{
        $('#folderKey').val($("#deptfoldersel option:selected").val());
        $('#departmentKey').val($("#deptlistsel option:selected").val());
        if($('#folderKey').val().trim!=="")
        {
        $.ajax({
        type: "POST",
        dataType: 'text',
        url: "DepartmentUser",
        data:
                {
                    'eventType': 'deleteFolderFromDepartment',
                    'companyKey':$('#companyKey').val(),
                    'departmentKey' : $('#departmentKey').val(),
                    'folderKey' : $('#folderKey').val() 
                },
        success: function(data)
        {
            ShowToast("User Removed from Department", 2000);  
            showNonExistingFolderInDepartment();
            showExistingFolderInDepartment();
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
       ShowToast("Select Folder before Remove from Department", 2000);
   }
}





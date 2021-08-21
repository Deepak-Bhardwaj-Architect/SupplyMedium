/* 
 * 
 * 
 * 
 */
var cnt = 1, lst_id = null, slctd_fldr = "",slctd_fldr_nm = "", slctd_fl = "", slctd_fl2 = "";
function add_user_folder()
{
    if ($("#fldr_nm").val().replace(/^\s+|\s+$/g, '') === "")
    {
        ShowToast("Enter folder name before save", 1000);
    }
    else
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "CompanyFolder",
            data:
                    {
                        'typ': 'crt_usr_fldr',
                        'fldr_nm': $("#fldr_nm").val()

                    },
            success: function(data)
            {
                if (data === "Folder created")
                {
                    ShowToast("Folder created", 1500);
                    get_user_folders($("#fldr_nm").val());
                }
                else if (data === "Folder already exist")
                {
                    ShowToast("Folder already exist", 2000);
                }
            },
            error: function(xhr, textStatus, errorThrown)
            {
                ShowToast("Folder not created error:" + errorThrown, 2000);
            }
        });
    }
}
function get_user_folders(folder_name)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyFolder",
        data:
                {
                    'typ': 'gt_usr_fldrs', 
                    'folder_name': folder_name    
                },
        success: function(data)
        {
            document.getElementById('usr_fld_lst').innerHTML = data;
            if($('#selected_folder_name').val().trim()!=="")
            {
                opn_fldr_fls($('#selected_folder_name').val(),$('#selected_folder_id').val())
            }
        },
        error: function(xhr, textStatus, errorThrown)
        {
            ShowToast("error to get folder:" + errorThrown, 2000);
        }
    });
}

function opn_fldr_fls(fldr_nm,fldr_id)
{   
    slctd_fl = "";
    slctd_fl2 = "";
    if (slctd_fldr !== "")
    {
        document.getElementById(slctd_fldr).style.background = "#c8c8c8";
        document.getElementById(slctd_fldr).style.setProperty("color", "#000");
    }
    document.getElementById(fldr_id).style.background = "#039bc2";
    document.getElementById(fldr_id).style.setProperty("color", "#ffffff");
    slctd_fldr = fldr_id;
    slctd_fldr_nm=fldr_nm;
    $('#folderName').val(fldr_nm);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyFolder",
        data:
                {
                    'typ': 'gt_usr_fldrs_fls',
                    'fldr_nm': fldr_nm
                },
        success: function(data)
        {
            document.getElementById('usr_fld_fls_lst').innerHTML = data;
        },
        error: function(xhr, textStatus, errorThrown)
        {
            ShowToast("error to get folder:" + errorThrown, 2000);
        }
    });
}

function slct_fldr_fl(fl_nm, fl_nm2)
{
    if (slctd_fl !== "")
    {
        document.getElementById(slctd_fl).style.background = "#c8c8c8";
        document.getElementById(slctd_fl).style.setProperty("color", "#000");
    }
    document.getElementById(fl_nm).style.background = "#039bc2";
    document.getElementById(fl_nm).style.setProperty("color", "#ffffff");
    slctd_fl = fl_nm;
    slctd_fl2 = fl_nm2;
}

function add_user_folder_file()
{
    if (slctd_fldr_nm === "")
    {
        ShowToast("Select folder before upload file", 1000);
        return false;
    }
    if($('#fileName').val().trim()==="")
    {
        ShowToast("Choose a file to upload", 1000);
        return false;
    }
//    else
//    {
//        var file = document.frm_fl_nm.fl_nm2.files[0];//$("#fl_nm2").prop("files")[0];
//        var formdata = new FormData();
//    //alert(file.name);
//        //data2.append('typ','upld_foldr_fl');
//        formdata.append('slctd_fldr', slctd_fldr_nm);
//        formdata.append('file_name2', file);
//        $.ajax({
//            url: 'FolderManagementMyFeed',
//            type: 'POST',
//            data: formdata,
//            cache: false,
//            //dataType: 'json',
//            processData: false, // Don't process the files
//            contentType: false, // Set content type to false as jQuery will tell the server its a query string request
//            success: function(data)
//            {
//                //alert(data);
//                opn_fldr_fls(slctd_fldr_nm,slctd_fldr);
//                $("#fl_nm2").val("");
//            },
//            error: function(jqXHR, textStatus, errorThrown)
//            {
//                // Handle errors here
//                alert('ERRORS: ' + errorThrown);
//                // STOP LOADING SPINNER
//            }
//        });
//    }
}
function add_cmpny_folder()
{
    if ($("#fldr_nm").val().replace(/^\s+|\s+$/g, '') === "")
    {
        ShowToast("Enter folder name before save", 1000);
    }
    else
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "CompanyFolder",
            data:
                    {
                        'typ': 'crt_cmpny_fldr',
                        'fldr_nm': $("#fldr_nm").val()

                    },
            success: function(data)
            {
                if (data === "Folder created")
                {
                    get_cmpny_folders($("#fldr_nm").val());
                    $('#fldr_nm').val('');
                    ShowToast("Folder created", 1500);                    
                }
                else if (data === "Folder already exist")
                {
                    ShowToast("Folder already exist", 2000);
                }
            },
            error: function(xhr, textStatus, errorThrown)
            {
                ShowToast("Folder not created error:" + errorThrown, 2000);
            }
        });
    }
}
function get_cmpny_folders(folder_name)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyFolder",
        data:
                {
                    'typ': 'gt_cmpny_fldrs',
                    'folder_name':folder_name

                },
        success: function(data)
        {
            document.getElementById('usr_fld_lst').innerHTML = data;
            if($('#selected_folder_name').val().trim()!=="")
            {
                opn_cmpny_fldr_fls($('#selected_folder_name').val(),$('#selected_folder_id').val());
            }
        },
        error: function(xhr, textStatus, errorThrown)
        {
            ShowToast("error to get folder:" + errorThrown, 2000);
        }
    });
}

function opn_cmpny_fldr_fls(fldr_nm,fldr_id)
{
    slctd_fl = "";
    slctd_fl2 = "";
    if (slctd_fldr !== "")
    {
        document.getElementById(slctd_fldr).style.background = "#c8c8c8";
        document.getElementById(slctd_fldr).style.setProperty("color", "#000");
    }
    document.getElementById(fldr_id).style.background = "#039bc2";
    document.getElementById(fldr_id).style.setProperty("color", "#ffffff");
    slctd_fldr = fldr_id;
    slctd_fldr_nm=fldr_nm;
    $('#folderName').val(fldr_nm);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "CompanyFolder",
        data:
                {
                    'typ': 'gt_cmpny_fldrs_fls',
                    'fldr_nm': fldr_nm
                },
        success: function(data)
        {
            document.getElementById('usr_fld_fls_lst').innerHTML = data;
        },
        error: function(xhr, textStatus, errorThrown)
        {
            ShowToast("error to get folder:" + errorThrown, 2000);
        }
    });
}


function add_cmpny_folder_file()
{
    if (slctd_fldr_nm === "")
    {
        ShowToast("Select folder before upload file", 1000);
        return false;
    }
    if($('#fileName').val().trim()==="")
    {
      ShowToast("Select file to upload ", 1000);
        return false;  
    }
    else
    {
//        var file = document.frm_fl_nm.fl_nm2.files[0];//$("#fl_nm2").prop("files")[0];
//        var data2 = new FormData();
////    alert(file.name);
//        data2.append('slctd_fldr', slctd_fldr_nm);
//        data2.append('file_name2', file);
//        $.ajax({
//            url: 'FolderManagementInternalPage',
//            type: 'POST',
//            data: data2,
//            cache: false,
//            //dataType: 'json',
//            processData: false, // Don't process the files
//            contentType: false, // Set content type to false as jQuery will tell the server its a query string request
//            success: function(data)
//            {
//                //alert(data);
//                opn_cmpny_fldr_fls(slctd_fldr_nm,slctd_fldr);
//                $("#fl_nm2").val("");
//            },
//            error: function(jqXHR, textStatus, errorThrown)
//            {
//                //alert('error :'+errorThrown);
//                // Handle errors here
//                //alert('ERRORS: ' + textStatus);
//                // STOP LOADING SPINNER
//            }
//        });
    }
}

function delete_user_folder()
{
    if (slctd_fldr_nm === "")
    {
        ShowToast("Select folder before delete", 1000);
    }
    else
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "CompanyFolder",
            data:
                    {
                        'typ': 'dlt_usr_fldr',
                        'fldr_nm': slctd_fldr_nm
                    },
            success: function(data)
            {
                ShowToast("Folder deleted", 1500);
                get_user_folders(slctd_fldr_nm);
                $('#usr_fld_fls_lst').html('');
            },
            error: function(xhr, textStatus, errorThrown)
            {
                ShowToast("folder not deleted", 2000);
            }
        });
    }
}

function delete_company_folder()
{
    if (slctd_fldr_nm === "")
    {
        ShowToast("Select folder before delete", 1000);
    }
    else
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "CompanyFolder",
            data:
                    {
                        'typ': 'dlt_cmpny_fldr',
                        'fldr_nm': slctd_fldr_nm
                    },
            success: function(data)
            {
                ShowToast("Folder deleted", 1500);
                get_cmpny_folders(slctd_fldr_nm);
                $('#usr_fld_fls_lst').html('');
            },
            error: function(xhr, textStatus, errorThrown)
            {
                ShowToast("folder not deleted", 2000);
            }
        });
    }
}

function delete_user_file()
{
    if (slctd_fl2 === "")
    {
        ShowToast("Select file before delete", 1000);
    }
    else
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "CompanyFolder",
            data:
                    {
                        'typ': 'dlt_usr_fl',
                        'fldr_nm': slctd_fldr_nm,
                        'fl_nm': slctd_fl2
                    },
            success: function(data)
            {
                ShowToast("file deleted", 1500);
                opn_fldr_fls(slctd_fldr_nm,slctd_fldr);
            },
            error: function(xhr, textStatus, errorThrown)
            {
                ShowToast("file not deleted", 2000);
            }
        });
    }
}
function delete_company_file()
{
    if (slctd_fl2 === "")
    {
        ShowToast("Select file before delete", 1000);
    }
    else
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "CompanyFolder",
            data:
                    {
                        'typ': 'dlt_cmpny_fl',
                        'fldr_nm': slctd_fldr_nm,
                        'fl_nm': slctd_fl2
                    },
            success: function(data)
            {
                ShowToast("file deleted", 1500);
                opn_cmpny_fldr_fls(slctd_fldr_nm,slctd_fldr);
            },
            error: function(xhr, textStatus, errorThrown)
            {
                ShowToast("file not deleted", 2000);
            }
        });
    }
}


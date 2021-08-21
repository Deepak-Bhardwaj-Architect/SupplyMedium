var cnt = 1, lst_id = null, slctd_fldr = "",slctd_fldr_nm = "", slctd_fl = "", slctd_fl2 = "";
function Usr_anlys(typ)
{
    //alert(typ);
    if(typ==="Intranet User")
    {  
    //document.getElementById("Admin").style.display="none";    
   // alert(document.getElementById("Admin").style.display);    
     $("#Admin").hide();
$("#menu_separtor_Transactions").hide();
//alert($("#Admin").hide());
$("#Transactions").hide();
$("#menu_separtor_Suppliers").hide();
$("#Suppliers").hide();
$("#menu_separtor_Buyers").hide();
$("#Buyers").hide();
$("#menu_separtor_Search").hide();
$("#Search").hide();
$("#menu_separtor_Corporate").hide();
$("#Corporate").hide();   
    }   
    else if(typ==="Regular")
    {
      $("#Admin").hide();
      $("#menu_separtor_Transactions").hide();  
    }
}

function slctd_ctgry(ctgry)
{
    //alert(cnt);
    if (document.getElementById("businesscategory2").value.indexOf(ctgry) === -1)
    {
    //alert('1');    
        document.getElementById("slctd_ctgry_lst").innerHTML += "<li id='cats" + cnt + "' style='background:#B0B0B0;'>" + ctgry + " <span style='cursor:pointer;color:red;' onclick=\"javascript:rmv_ctgry('" + ctgry + "','cats" + cnt + "')\">X</span></li>";
   // alert('2');     
        document.getElementById("businesscategory2").value += ctgry + ",";
   // alert('3');    
        if (document.getElementById("businesscategory2").value !== "" || document.getElementById("businesscategory2").value !== null)
        {
            document.getElementById("ctgry_lstng").style.display = "block";
        }
   //  alert(cnt);    
        cnt++;
    // alert(cnt);   
    }
}
function rmv_ctgry(ctgry, id)
{
    document.getElementById("businesscategory2").value = document.getElementById("businesscategory2").value.replace(ctgry + ",", '');
    document.getElementById(id).style.display = 'none'
}

function hide_show(id)
{
    /* if(lst_id!==null||lst_id!=="")
     {
     
     }*/
    if (document.getElementById(id).style.display === 'none')
    {
        document.getElementById(id).style.display = 'block';
    }
    else
    {
        document.getElementById(id).style.display = 'none';
    }
}

function vldt_usr_stngs()
{
    if (document.getElementById('frstnm').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('frstnm_vldtn').style.display = 'block';
        document.getElementById('frstnm').setAttribute("style", "border-color: red;");
    }
    else
    {
        document.getElementById('frstnm_vldtn').style.display = 'none';
        document.getElementById('frstnm').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (document.getElementById('lstnm').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('lstnm_vldtn').style.display = 'block';
        document.getElementById('lstnm').setAttribute("style", "border-color: red;");
    }
    else
    {
        document.getElementById('lstnm_vldtn').style.display = 'none';
        document.getElementById('lstnm').setAttribute("style", "border-color: #a5b7bb;");
    }

    if (document.getElementById('phn').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('phn_vldtn').style.display = 'block';
        document.getElementById('phn').setAttribute("style", "border-color: red;");
    }
    else
    {
        document.getElementById('phn_vldtn').style.display = 'none';
        document.getElementById('phn').setAttribute("style", "border-color: #a5b7bb;");
    }

    document.getElementById('mbl_vldtn').style.display = 'none';
    document.getElementById('mbl').setAttribute("style", "border-color: #a5b7bb;");

    if (document.getElementById('phn').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('phn').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('phn').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('phn_vldtn').style.display = 'block';
                document.getElementById('phn').setAttribute("style", "border-color: red;");
            }
        }
    }
    if (document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('mbl_vldtn').style.display = 'block';
                document.getElementById('mbl').setAttribute("style", "border-color: red;");
            }
        }
    }
}

function sv_usr_stngs(eml)
{
    var flag = "true";
    if (document.getElementById('frstnm').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('frstnm_vldtn').style.display = 'block';
        document.getElementById('frstnm').setAttribute("style", "border-color: red;");
        flag = "flase";
    }
    else
    {
        document.getElementById('frstnm_vldtn').style.display = 'none';
        document.getElementById('frstnm').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (document.getElementById('lstnm').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('lstnm_vldtn').style.display = 'block';
        document.getElementById('lstnm').setAttribute("style", "border-color: red;");
        flag = "flase";
    }
    else
    {
        document.getElementById('lstnm_vldtn').style.display = 'none';
        document.getElementById('lstnm').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (document.getElementById('phn').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('phn_vldtn').style.display = 'block';
        document.getElementById('phn').setAttribute("style", "border-color: red;");
        flag = "flase";
    }
    else
    {
        document.getElementById('phn_vldtn').style.display = 'none';
        document.getElementById('phn').setAttribute("style", "border-color: #a5b7bb;");
    }

    document.getElementById('mbl_vldtn').style.display = 'none';
    document.getElementById('mbl').setAttribute("style", "border-color: #a5b7bb;");

    if (document.getElementById('phn').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('phn').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('phn').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('phn_vldtn').style.display = 'block';
                document.getElementById('phn').setAttribute("style", "border-color: red;");
                flag = "flase";
            }
        }
    }
    if (document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('mbl_vldtn').style.display = 'block';
                document.getElementById('mbl').setAttribute("style", "border-color: red;");
                flag = "flase";
            }
        }
    }
    if (flag === "true")
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "VrSearch",
            data:
                    {
                        'typ': 'sv_usr_dtl',
                        'eml': eml,
                        'frstnm': document.getElementById('frstnm').value,
                        'lstnm': document.getElementById('lstnm').value,
                        'phn': document.getElementById('phn').value,
                        'mbl': document.getElementById('mbl').value,
                        'cty': document.getElementById('cty').value,
                        'zpcd': document.getElementById('zpcd').value,
                        'dprtmnt': document.getElementById('dprtmnt').value,
                        'usr_rl': document.getElementById('usr_rl').value,
                        'fx': document.getElementById('fx').value,
                        'usr_tp': document.getElementById('usr_tp').value
                    },
            success: function(data)
            {
                $('#sv_dtl_cnfrmtn_msg_dv').fadeIn();
                setTimeout("$('#sv_dtl_cnfrmtn_msg_dv').fadeOut();cl()", 2000);
            },
            error: function(xhr, textStatus, errorThrown)
            {

            }
        });
    }
}

function cl()
{
    $("#mainpage").load("Views/UserMgmt/jsp/usermgmt.jsp", function()
    {
        //$("#content_loader").hide();

        setCookie("CurrentPage", "UserMgmt");

    });
}

function vldt_usr_dtl()
{
    if (document.getElementById('frstnm').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('frstnm_vldtn').style.display = 'block';
        document.getElementById('frstnm').setAttribute("style", "border-color: red;");
    }
    else
    {
        document.getElementById('frstnm_vldtn').style.display = 'none';
        document.getElementById('frstnm').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (document.getElementById('lstnm').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('lstnm_vldtn').style.display = 'block';
        document.getElementById('lstnm').setAttribute("style", "border-color: red;");
    }
    else
    {
        document.getElementById('lstnm_vldtn').style.display = 'none';
        document.getElementById('lstnm').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (document.getElementById('phn').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('phn_vldtn').style.display = 'block';
        document.getElementById('phn').setAttribute("style", "border-color: red;");
    }
    else
    {
        document.getElementById('phn_vldtn').style.display = 'none';
        document.getElementById('phn').setAttribute("style", "border-color: #a5b7bb;");
    }

    document.getElementById('mbl_vldtn').style.display = 'none';
    document.getElementById('mbl').setAttribute("style", "border-color: #a5b7bb;");

    if (document.getElementById('phn').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('phn').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('phn').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('phn_vldtn').style.display = 'block';
                document.getElementById('phn').setAttribute("style", "border-color: red;");
            }
        }
    }
    if (document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('mbl_vldtn').style.display = 'block';
                document.getElementById('mbl').setAttribute("style", "border-color: red;");
            }
        }
    }
}

function sv_usr_dtl(eml)
{
    var flag = "true";
    if (document.getElementById('frstnm').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('frstnm_vldtn').style.display = 'block';
        document.getElementById('frstnm').setAttribute("style", "border-color: red;");
        flag = "flase";
    }
    else
    {
        document.getElementById('frstnm_vldtn').style.display = 'none';
        document.getElementById('frstnm').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (document.getElementById('lstnm').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('lstnm_vldtn').style.display = 'block';
        document.getElementById('lstnm').setAttribute("style", "border-color: red;");
        flag = "flase";
    }
    else
    {
        document.getElementById('lstnm_vldtn').style.display = 'none';
        document.getElementById('lstnm').setAttribute("style", "border-color: #a5b7bb;");
    }
    if (document.getElementById('phn').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('phn_vldtn').style.display = 'block';
        document.getElementById('phn').setAttribute("style", "border-color: red;");
        flag = "flase";
    }
    else
    {
        document.getElementById('phn_vldtn').style.display = 'none';
        document.getElementById('phn').setAttribute("style", "border-color: #a5b7bb;");
    }

    document.getElementById('mbl_vldtn').style.display = 'none';
    document.getElementById('mbl').setAttribute("style", "border-color: #a5b7bb;");

    if (document.getElementById('phn').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('phn').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('phn').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('phn_vldtn').style.display = 'block';
                document.getElementById('phn').setAttribute("style", "border-color: red;");
                flag = "flase";
            }
        }
    }
    if (document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var i;
        for (i = 0; i < document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '').length; i++)
        {
            var c = document.getElementById('mbl').value.replace(/^\s+|\s+$/g, '').charAt(i);
            if (isNaN(c))
            {
                document.getElementById('mbl_vldtn').style.display = 'block';
                document.getElementById('mbl').setAttribute("style", "border-color: red;");
                flag = "flase";
            }
        }
    }
    if (flag === "true")
    {
        $.ajax({
            type: "POST",
            dataType: 'text',
            url: "VrSearch",
            data:
                    {
                        'typ': 'sv_usr_dtl2',
                        'eml': eml,
                        'frstnm': document.getElementById('frstnm').value,
                        'lstnm': document.getElementById('lstnm').value,
                        'phn': document.getElementById('phn').value,
                        'mbl': document.getElementById('mbl').value,
                        'cty': document.getElementById('cty').value,
                        'zpcd': document.getElementById('zpcd').value,
                        'adrs': document.getElementById('adrs').value,
                        'st': document.getElementById('st').value,
                        'fx': document.getElementById('fx').value
                    },
            success: function(data)
            {
                $('#sv_dtl_cnfrmtn_msg_dv').fadeIn();
                setTimeout("$('#sv_dtl_cnfrmtn_msg_dv').fadeOut()", 2000);
                // showUserAccSettings();
            },
            error: function(xhr, textStatus, errorThrown)
            {

            }
        });
    }
}

function  splr_al_srch()
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'splr_al_srch'

                },
        success: function(data)
        {
            //alert("data"+data);            
            document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {

        }
    });
}
function  splr_slctd_ctgry_srch(ctgry)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'splr_slctd_ctgry_srch',
                    'ctgry': ctgry

                },
        success: function(data)
        {
            //alert(document.getElementById('cntry').value);           
            document.getElementById("dflt_slct_cntry").selected = "true";
            //alert(document.getElementById('cntry').value);
            document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {

        }
    });
}
function  splr_slctd_cntry_srch(cntry)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'splr_slctd_cntry_srch',
                    'slctd_ctgry': document.getElementById('Advanced_Selectbox').value,
                    'cntry': cntry

                },
        success: function(data)
        {
            document.getElementById('splr_slctd_srch').innerHTML = data + " Supplier Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {

        }
    });
}

function  byr_al_srch()
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'byr_al_srch'

                },
        success: function(data)
        {
            //alert("data"+data);            
            document.getElementById('byr_slctd_srch').innerHTML = data + " Buyer Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {

        }
    });
}
function  byr_slctd_ctgry_srch(ctgry)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'byr_slctd_ctgry_srch',
                    'ctgry': ctgry

                },
        success: function(data)
        {
            //alert(document.getElementById('cntry').value);           
            document.getElementById("dflt_slct_cntry").selected = "true";
            //alert(document.getElementById('cntry').value);
            document.getElementById('byr_slctd_srch').innerHTML = data + " Buyer Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {

        }
    });
}
function  byr_slctd_cntry_srch(cntry)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'byr_slctd_cntry_srch',
                    'slctd_ctgry': document.getElementById('Advanced_Selectbox').value,
                    'cntry': cntry

                },
        success: function(data)
        {
            document.getElementById('byr_slctd_srch').innerHTML = data + " Buyer Exists";
        },
        error: function(xhr, textStatus, errorThrown)
        {

        }
    });
}

function clear_notification()
{
    $("#notification_count").hide();
    $("#notification_count").text(0);
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'clr_ntfctn_cnt',
                    'eml': $("#EmailAddress").val()

                },
        success: function(data)
        {
            //alert(data);
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
        }
    });
}
function rmv_frm_ntwrk(ky, ntwrk_typ)
{
    //alert('ok');
    $("#" + ky).fadeOut();
    //document.getElementById(ntwrk_typ).style.display='none';
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'rmv_frm_ntwrk',
                    'ky': ky,
                    'ntwrk_typ': ntwrk_typ

                },
        success: function(data)
        {
            //alert(data);
        },
        error: function(xhr, textStatus, errorThrown)
        {

        }
    });
}

function  mx_mn_lst_sld(prt_n)
{
    //alert($('#selected_ven_key').val());
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'mx_mn_lst_sld',
                    'prt_n': prt_n,
                    'ven_key': $('#selected_ven_key').val()

                },
        success: function(data)
        {
            alert(data);
        },
        error: function(xhr, textStatus, errorThrown)
        {

        }
    });
}

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
            url: "VrSearch",
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
                    get_user_folders();
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
function get_user_folders()
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'gt_usr_fldrs'

                },
        success: function(data)
        {
            document.getElementById('usr_fld_lst').innerHTML = data;
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
        document.getElementById(slctd_fldr).style.setProperty("color", "#black");
    }
    document.getElementById(fldr_id).style.background = "#039bc2";
    document.getElementById(fldr_id).style.setProperty("color", "#ffffff");
    slctd_fldr = fldr_id;
    slctd_fldr_nm=fldr_nm;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
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
        document.getElementById(slctd_fl).style.setProperty("color", "#black");
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
    }
    else
    {
        var file = document.frm_fl_nm.fl_nm2.files[0];//$("#fl_nm2").prop("files")[0];
        var data2 = new FormData();
//    alert(file.name);
        //data2.append('typ','upld_foldr_fl');
        data2.append('slctd_fldr', slctd_fldr_nm);
        data2.append('file_name2', file);
        $.ajax({
            url: 'VrSearch_upload',
            type: 'POST',
            data: data2,
            cache: false,
            //dataType: 'json',
            processData: false, // Don't process the files
            contentType: false, // Set content type to false as jQuery will tell the server its a query string request
            success: function(data)
            {
                //alert(data);
                opn_fldr_fls(slctd_fldr_nm,slctd_fldr);
                $("#fl_nm2").val("");
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                // Handle errors here
                alert('ERRORS: ' + errorThrown);
                // STOP LOADING SPINNER
            }
        });
    }
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
            url: "VrSearch",
            data:
                    {
                        'typ': 'crt_cmpny_fldr',
                        'fldr_nm': $("#fldr_nm").val()

                    },
            success: function(data)
            {
                if (data === "Folder created")
                {
                    ShowToast("Folder created", 1500);
                    get_cmpny_folders()
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
function get_cmpny_folders()
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'gt_cmpny_fldrs'

                },
        success: function(data)
        {
            document.getElementById('usr_fld_lst').innerHTML = data;
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
        document.getElementById(slctd_fldr).style.setProperty("color", "#black");
    }
    document.getElementById(fldr_id).style.background = "#039bc2";
    document.getElementById(fldr_id).style.setProperty("color", "#ffffff");
    slctd_fldr = fldr_id;
    slctd_fldr_nm=fldr_nm;
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
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
    }
    else
    {
        var file = document.frm_fl_nm.fl_nm2.files[0];//$("#fl_nm2").prop("files")[0];
        var data2 = new FormData();
//    alert(file.name);
        data2.append('slctd_fldr', slctd_fldr_nm);
        data2.append('file_name2', file);
        $.ajax({
            url: 'VrSearch_upload2',
            type: 'POST',
            data: data2,
            cache: false,
            //dataType: 'json',
            processData: false, // Don't process the files
            contentType: false, // Set content type to false as jQuery will tell the server its a query string request
            success: function(data)
            {
                //alert(data);
                opn_cmpny_fldr_fls(slctd_fldr_nm,slctd_fldr);
                $("#fl_nm2").val("");
            },
            error: function(jqXHR, textStatus, errorThrown)
            {
                // Handle errors here
                //alert('ERRORS: ' + textStatus);
                // STOP LOADING SPINNER
            }
        });
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
            url: "VrSearch",
            data:
                    {
                        'typ': 'dlt_usr_fldr',
                        'fldr_nm': slctd_fldr_nm
                    },
            success: function(data)
            {
                ShowToast("Folder deleted", 1500);
                get_user_folders();
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
            url: "VrSearch",
            data:
                    {
                        'typ': 'dlt_cmpny_fldr',
                        'fldr_nm': slctd_fldr_nm
                    },
            success: function(data)
            {
                ShowToast("Folder deleted", 1500);
                get_cmpny_folders();
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
            url: "VrSearch",
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
            url: "VrSearch",
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
function update_shiping_address(auto, address)
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'updt_shpng_adrs',
                    'auto': auto,
                    'address': address
                },
        success: function(data)
        {
            ShowToast("Address Updated", 1500);
        },
        error: function(xhr, textStatus, errorThrown)
        {
            ShowToast("Address not Updated", 2000);
        }
    });
}
function get_shiping_address()
{
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'gt_shpng_adrs',
                    'ky': $('#selected_ven_key').val()
                },
        success: function(data)
        {
            //alert(data);
            document.getElementById('cmpny_adrs_lst').innerHTML = data;
            //ShowToast("Address Updated", 1500);
        },
        error: function(xhr, textStatus, errorThrown)
        {
            //ShowToast("Address not Updated", 2000);
        }
    });
}
function pypl_rslt(rslt)
{
    if (rslt !== 'null')
    {
        ShowToast(rslt, 2000);
    }
}

function cnfrm_clr_ntfctns_hstry()
{
    $('#cnfrm_clr_ntfctn_hstry').fadeIn();
}

function clr_ntfctn_hstry()
{
    $('#notification_content').text("");
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'clr_ntfctn_hstry',
                    'val': $('#EmailAddress').val()
                },
        success: function(data)
        {
            //alert(data);

        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            //ShowToast("Address not Updated", 2000);
        }
    });
}

function clclt_trans_amnt(val)
{
    if (val === "")
    {
        $("#tx_er").text("Please fill tax value in numeric or 0(zero)");        
        val=0;
    }
//    if (!val === parseInt(val))
//    {
//        alert('2');
//        val=0;
//    }
    $("#tot_price_amt").text(calculateTotPrice(parseFloat($("#tot_list_price_amt").text()), parseFloat(val)));
}

function update_user_privilages(id)
{ 
   var prvilages="0"; 
   if(document.getElementById("addusers").checked===true) 
   {
    prvilages="1";   
   }
   else
   {
    prvilages="0";    
   }
   if(document.getElementById("delusers").checked===true) 
   {
    prvilages+="1";   
   }
   else
   {
    prvilages+="0";    
   }
   prvilages+="0";
   prvilages+="0";
   if(document.getElementById("addbuyers").checked===true) 
   {
    prvilages+="1";   
   }
   else
   {
    prvilages+="0";    
   }
   prvilages+="0";
   if(document.getElementById("accessusermgmt").checked===true) 
   {
    prvilages+="1";   
   }
   else
   {
    prvilages+="0";    
   }
   if(document.getElementById("postanncemnt").checked===true) 
   {
    prvilages+="1";   
   }
   else
   {
    prvilages+="0";    
   }
   prvilages+="0";
   if(document.getElementById("rate").checked===true) 
   {
    prvilages+="1";   
   }
   else
   {
    prvilages+="0";    
   }
   if(document.getElementById("creategroup").checked===true) 
   {
    prvilages+="1";   
   }
   else
   {
    prvilages+="0";    
   }
   if(document.getElementById("delgroup").checked===true) 
   {
    prvilages+="1";   
   }
   else
   {
    prvilages+="0";    
   }
   prvilages+="0";
   prvilages+="0";
   $('#notification_content').text("");
    $.ajax({
        type: "POST",
        dataType: 'text',
        url: "VrSearch",
        data:
                {
                    'typ': 'update_user_privileges',
                    'val': id,
                    'prvileges':prvilages
                },
        success: function(data)
        {
            //alert(data);
            ShowToast("User Privileges Updated", 2000);

        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown);
            ShowToast("User Privileges not Updated", 2000);
        }
    });
}









var cnt = 1, lst_id = null, lst_id2 = null, usr_dt = null, eml_sts = 1,cmpny_mr_dtl2="";
function slctd_ctgry(ctgry)
{
    if (document.getElementById("businesscategory2").value.indexOf(ctgry) === -1)
    {
        document.getElementById("slctd_ctgry_lst").innerHTML += "<li id='cat" + cnt + "' style='background:#B0B0B0;'>" + ctgry + " <span style='cursor:pointer;color:red;' onclick=rmv_ctgry('" + ctgry + "','cat" + cnt + "')>X</span></li>";
        document.getElementById("businesscategory2").value += ctgry + ",";
        if (document.getElementById("businesscategory2").value !== "" || document.getElementById("businesscategory2").value !== null)
        {
            document.getElementById("ctgry_lstng").style.display = "block";
        }
        cnt++;
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

function fade_in_out(id)
{
    //alert(lst_id);
    if (lst_id !== null)
    {
        $('#' + lst_id).fadeOut();
    }
    $('#' + id).fadeIn();
    lst_id = id;
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
            url: "dilbag",
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
                $("#mainpage").load("Views/UserMgmt/jsp/usermgmt.jsp", function()
                {
                    //$("#content_loader").hide();

                    setCookie("CurrentPage", "UserMgmt");

                });
            },
            error: function(xhr, textStatus, errorThrown)
            {

            }
        });
    }
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
            url: "dilbag",
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
                        'fx': document.getElementById('fx').value
                    },
            success: function(data)
            {
                showUserAccSettings();
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
        url: "dilbag",
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
        url: "dilbag",
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
        url: "dilbag",
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
        url: "dilbag",
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
        url: "dilbag",
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
        url: "dilbag",
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
        url: "dilbag",
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
        url: "dilbag",
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

function vldt_usr_eml(val)
{
    $.ajax({
        type: "POST",
        url: "/SMlatest/SuperAdminPower",
        data:
                {
                    'typ': 'vldt_usr_eml',
                    'val': val
                },
        success: function(data)
        {
            eml_sts = parseInt(data.toString());
            if (eml_sts === 1)
            {
                $('#admn_ad_usr_er').html("Email already exist");
            }
            else if (eml_sts === 0)
            {
                $('#admn_ad_usr_er').html("");
            }
            else
            {
                $('#admn_ad_usr_er').html("Problem to validate email");
            }
        },
        error: function(xhr, textStatus, errorThrown)
        {
            $('#admn_ad_usr_er').html("Problem to validate email: " + errorThrown);
        }
    });
}

function validate_admin_add_new()
{
    if (document.getElementById('eml').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('admn_ad_usr_er').innerHTML = "Enter valid email";
        return false;
    }
    else if (document.getElementById('eml').value.replace(/^\s+|\s+$/g, '') !== "")
    {
        var x = document.getElementById('eml').value.replace(/^\s+|\s+$/g, '');
        var atpos = x.indexOf("@");
        var dotpos = x.lastIndexOf(".");
        if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length)
        {
            document.getElementById('admn_ad_usr_er').innerHTML = "Enter valid email";
            return false;
        }
    }
    else if (eml_sts === 1)
    {
        document.getElementById('admn_ad_usr_er').innerHTML = "Email already registered";
        return false;
    }
    else if (document.getElementById('pswrd').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('admn_ad_usr_er').innerHTML = "Enter user password";
        return false;
    }
    else if (document.getElementById('cnfrm_pswrd').value.replace(/^\s+|\s+$/g, '') === "")
    {
        document.getElementById('admn_ad_usr_er').innerHTML = "Enter confirm password";
        return false;
    }
    else if (!(document.getElementById('pswrd').value.replace(/^\s+|\s+$/g, '') === document.getElementById('cnfrm_pswrd').value.replace(/^\s+|\s+$/g, '')))
    {
        document.getElementById('admn_ad_usr_er').innerHTML = "password and confirm password must be same";
        return false;
    }
}

function chng_admn_usr_dtl(id, eml, sts, pswrd)
{
    var actv = "", blck = "";
    if (sts === 0)
    {
        actv = "Selected";
    }
    else if (sts === 1)
    {
        blck = "Selected";
    }
    if (usr_dt !== null && lst_id2 !== null)
    {
        document.getElementById(lst_id2).innerHTML = usr_dt;
    }
    lst_id2 = 'usr_' + id;
    usr_dt = document.getElementById('usr_' + id).innerHTML;
    document.getElementById('usr_' + id).innerHTML = "";
    document.getElementById('usr_' + id).innerHTML = "<span><input type='text' value='" + eml + "' id='eml_" + id + "'/></span><span><input type='text' value='" + pswrd + "' id='pswrd_" + id + "'/><select id='sts_" + id + "'><option value='0' " + actv + " >Active</option><option value='1' " + blck + ">Block</option></select></span><span><a onclick=updt_admn_usr_dtl_updt('" + id + "') style='cursor:pointer'>Update</a>&nbsp;&nbsp;&nbsp<a onclick=cncl_admn_usr_dtl_updt(); style='cursor:pointer'>Cancel</a></span>";
}

function cncl_admn_usr_dtl_updt()
{
    if (usr_dt !== null && lst_id2 !== null)
    {
        document.getElementById(lst_id2).innerHTML = usr_dt;
    }
}

function updt_admn_usr_dtl_updt(id)
{
    var sts = "", usr_eml = document.getElementById("eml_" + id).value, usr_sts = document.getElementById("sts_" + id).value, usr_pswrd = document.getElementById("pswrd_" + id).value;
    if (document.getElementById('sts_' + id).value === "0")
    {
        sts = "Active";
    }
    else if (document.getElementById('sts_' + id).value === "1")
    {
        sts = "Block";
    }
    if (usr_dt !== null && lst_id2 !== null)
    {
        document.getElementById(lst_id2).innerHTML = "<span>" + document.getElementById('eml_' + id).value + "</span><span>" + sts + "</span><span><a onclick=\"javascript:chng_admn_usr_dtl('" + id + "','" + document.getElementById('eml_' + id).value + "','" + document.getElementById('sts_' + id).value + "','" + document.getElementById('pswrd_' + id).value + "');\" style='cursor:pointer'>Update</a>&nbsp;&nbsp;&nbsp<a style='cursor:pointer'>Remove</a></span>";
        usr_dt = document.getElementById(lst_id2).innerHTML;
    }
    $.ajax({
        type: "POST",
        url: "/SMlatest/SuperAdminPower",
        data:
                {
                    'typ': 'admn_updt_usr',
                    'id': id,
                    'usr_eml': usr_eml,
                    'usr_sts': usr_sts,
                    'usr_pswrd': usr_pswrd

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

function admn_dlt_usr(id)
{
    $("#usr_" + id).fadeOut();
    $.ajax({
        type: "POST",
        url: "/SMlatest/SuperAdminPower",
        data:
                {
                    'typ': 'admn_dlt_usr',
                    'id': id

                },
        success: function(data)
        {
            // alert(data);
        },
        error: function(xhr, textStatus, errorThrown)
        {
            // alert(errorThrown);
        }
    });
}

function cmpny_mr_dtl(nmbr, nm)
{
    //alert('enter');
    if(cmpny_mr_dtl2!=="")
    {
     $("#cmpny_mr_dtl_" + cmpny_mr_dtl2).slideUp();   
    }    
    cmpny_mr_dtl2=nmbr;
    
    if (document.getElementById("cmpny_mr_dtl_" + nmbr).style.display === "block")
    {
        $("#cmpny_mr_dtl_" + nmbr).slideUp();
    }
    else
    {
        $("#cmpny_mr_dtl_" + nmbr).slideDown();
        $.ajax({
            type: "POST",
            url: "/SMlatest/SuperAdminPower",
            data:
                    {
                        'typ': 'cmpny_mr_dtl',
                        'nmbr': nmbr,
                        'nm': nm

                    },
            success: function(data)
            {
                //alert(data);
                $("#cmpny_mr_dtl_" + nmbr).html(data);
            },
            error: function(xhr, textStatus, errorThrown)
            {
                 //alert(xhrtextStatus+errorThrown);
            }
        });
    }
}

var cmpny_key="",cmpny_sts="";
function company_status(key,status)
{
  cmpny_key=key,cmpny_sts=status;
  if(cmpny_sts==="Active")
  {
     document.getElementById('deactivate').checked = false;
     document.getElementById('activate').checked = true;
  }
  else if(cmpny_sts==="Deactive")
  {
     document.getElementById('activate').checked = false;
     document.getElementById('deactivate').checked = true;
  }
  $('#activate_deactvate_account').fadeIn();
}

function company_status_update()
{
  if(cmpny_key!=="")
  {
      $('#cmpny_'+cmpny_key).text(cmpny_sts);
      $.ajax({
            type: "POST",
            url: "/SMlatest/SuperAdminPower",
            data:
                    {
                        'typ': 'updt_cmpny_sts',
                        'cmpny_key': cmpny_key,
                        'cmpny_sts':cmpny_sts
                    },
            success: function(data)
            {
                //alert(data);
                //$("#cmpny_mr_dtl_" + nmbr).html(data);
            },
            error: function(xhr, textStatus, errorThrown)
            {
                //alert(errorThrown);
            }
        });
  }
}






var buyerReqTable;
var myReqtable;
var businessClassificationArr;

/* It is used to initialize the My Request datatable */
function initBuyerReqDataTable()
{
    buyerReqTable = $('#buyer_req_list').dataTable({
        sDom: 'lfr<"fixed_height"t>ip',
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "aoColumnDefs": [
            {"sWidth": "150px", "aTargets": [0]}, // Vendor Company Name
            {"sWidth": "150px", "aTargets": [1]}, // Vendor Phone Number
            {"sWidth": "150px", "aTargets": [2]}, // Vendor Country
            {"sWidth": "150px", "aTargets": [3]}, // Vendor Email
            {"sWidth": "150px", "aTargets": [4]}, // Vendor Contact Name
            {"sWidth": "150px", "aTargets": [5]}, // VR Status
            {"sWidth": "150px", "aTargets": [6]}, // Created date
        ],
        "aoColumnDefs":[
            {"bVisible": false, "aTargets": [7]}, // Vendor Branch
            {"bVisible": false, "aTargets": [8]}, // Vendor Address
            {"bVisible": false, "aTargets": [9]}, // Vendor City
            {"bVisible": false, "aTargets": [10]}, // Vendor State
            {"bVisible": false, "aTargets": [11]}, // Vendor Zipcode
            {"bVisible": false, "aTargets": [12]}, // Vendor Type
            {"bVisible": false, "aTargets": [13]}, // Vendor Department
            {"bVisible": false, "aTargets": [14]}, // Vendor Cell
            {"bVisible": false, "aTargets": [15]}, // Vendor Fax
            {"bVisible": false, "aTargets": [16]}, // Vendor Taxid
            {"bVisible": false, "aTargets": [17]}, // Vendor Type of Business
            {"bVisible": false, "aTargets": [18]}, // Vendor Business Category
            {"bVisible": false, "aTargets": [19]}, // Vendor NAICS Code
            {"bVisible": false, "aTargets": [20]}, // Vendor W9Form Flag
            {"bVisible": false, "aTargets": [21]}, // Vendor W9Form Path
            {"bVisible": false, "aTargets": [22]}, // Vendor Business size
            {"bVisible": false, "aTargets": [23]}, // Vendor Business Calssification
            {"bVisible": false, "aTargets": [24]}, // Vendor Additional Details
            {"bVisible": false, "aTargets": [25]}, // Vendor Regn id

            {"bVisible": false, "aTargets": [26]}, // regn key - request sender

            {"bVisible": false, "aTargets": [27]}, // Company Name
            {"bVisible": false, "aTargets": [28]}, // Phone Number
            {"bVisible": false, "aTargets": [29]}, // Country
            {"bVisible": false, "aTargets": [30]}, // Email
            {"bVisible": false, "aTargets": [31]}, // Contact Name			       	                 

            {"bVisible": false, "aTargets": [32]}, // Branch
            {"bVisible": false, "aTargets": [33]}, // Address
            {"bVisible": false, "aTargets": [34]}, // City
            {"bVisible": false, "aTargets": [35]}, // State
            {"bVisible": false, "aTargets": [36]}, // Zipcode
            {"bVisible": false, "aTargets": [37]}, // Type
            {"bVisible": false, "aTargets": [38]}, // Department
            {"bVisible": false, "aTargets": [39]}, // Cell
            {"bVisible": false, "aTargets": [40]}, // Fax
            {"bVisible": false, "aTargets": [41]}, // Type of Business
            {"bVisible": false, "aTargets": [42]}, // Business Category

            {"bVisible": false, "aTargets": [43]}, // request sender type
            {"bVisible": false, "aTargets": [44]}, // inquire details arr object

            {"bVisible": false, "aTargets": [45]}, // VendorRegn Key - request receiver

            // { "bVisible":false,"aTargets":[26] },
            //{ "bVisible":false,"aTargets":[27] },
            // { "bVisible":false,"aTargets":[28] },

     ],
                "bLengthChange": false,
        "oLanguage": {"sSearch": "Search", "sEmptyTable": "No Request found"},
        "bSort": true,
        "aaSorting": [[0, 'asc']],
        "bPaginate": true,
        "bFilter": true,
        "bInfo": true,
        "iDisplayLength": 15,
        "fnRowCallback": function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {


            $("td", nRow).addClass('rowBorder');


            $(nRow).click(function()
            {

                var aTrs = buyerReqTable.fnGetNodes();

                //alert( aTrs[0]);

                for (var i = 0; i < aTrs.length; i++)
                {
                    if ($(aTrs[i]).hasClass('row_selected'))
                    {
                        $(aTrs[i]).removeClass('row_selected');
                    }

                }

                $(nRow).addClass('row_selected');

                var aPos = buyerReqTable.fnGetPosition(this);

                var aData = buyerReqTable.fnGetData(aPos);

                showSuppliersForm(aData);

            });

            return nRow;
        }
    });
}

/* It is used to initialize the Buyer Request datatable */
function initSupplierMyReqDataTable()
{
    myReqtable = $('#my_req_list').dataTable(
            {
                sDom: 'lfr<"fixed_height"t>ip',
                "sPaginationType": "full_numbers",
                "bAutoWidth": false,
                "aoColumnDefs": [
                    {"sWidth": "150px", "aTargets": [0]}, // Vendor Company Name
                    {"sWidth": "150px", "aTargets": [1]}, // Vendor Phone Number
                    {"sWidth": "150px", "aTargets": [2]}, // Vendor Country
                    {"sWidth": "150px", "aTargets": [3]}, // Vendor Email
                    {"sWidth": "150px", "aTargets": [4]}, // Vendor Contact Name
                    {"sWidth": "150px", "aTargets": [5]}, // VR Status
                    {"sWidth": "150px", "aTargets": [6]}, // Created date
                ],
                "aoColumnDefs":[
                    {"bVisible": false, "aTargets": [7]}, // Vendor Branch
                    {"bVisible": false, "aTargets": [8]}, // Vendor Address
                    {"bVisible": false, "aTargets": [9]}, // Vendor City
                    {"bVisible": false, "aTargets": [10]}, // Vendor State
                    {"bVisible": false, "aTargets": [11]}, // Vendor Zipcode
                    {"bVisible": false, "aTargets": [12]}, // Vendor Type
                    {"bVisible": false, "aTargets": [13]}, // Vendor Department
                    {"bVisible": false, "aTargets": [14]}, // Vendor Cell
                    {"bVisible": false, "aTargets": [15]}, // Vendor Fax
                    {"bVisible": false, "aTargets": [16]}, // Vendor Taxid
                    {"bVisible": false, "aTargets": [17]}, // Vendor Type of Business
                    {"bVisible": false, "aTargets": [18]}, // Vendor Business Category
                    {"bVisible": false, "aTargets": [19]}, // Vendor NAICS Code
                    {"bVisible": false, "aTargets": [20]}, // Vendor W9Form Flag
                    {"bVisible": false, "aTargets": [21]}, // Vendor W9Form Path
                    {"bVisible": false, "aTargets": [22]}, // Vendor Business size
                    {"bVisible": false, "aTargets": [23]}, // Vendor Business Calssification
                    {"bVisible": false, "aTargets": [24]}, // Vendor Additional Details
                    {"bVisible": false, "aTargets": [25]}, // Vendor Regn id

                    {"bVisible": false, "aTargets": [26]}, // regn key - request sender

                    {"bVisible": false, "aTargets": [27]}, // Company Name
                    {"bVisible": false, "aTargets": [28]}, // Phone Number
                    {"bVisible": false, "aTargets": [29]}, // Country
                    {"bVisible": false, "aTargets": [30]}, // Email
                    {"bVisible": false, "aTargets": [31]}, // Contact Name			       	                 

                    {"bVisible": false, "aTargets": [32]}, // Branch
                    {"bVisible": false, "aTargets": [33]}, // Address
                    {"bVisible": false, "aTargets": [34]}, // City
                    {"bVisible": false, "aTargets": [35]}, // State
                    {"bVisible": false, "aTargets": [36]}, // Zipcode
                    {"bVisible": false, "aTargets": [37]}, // Type
                    {"bVisible": false, "aTargets": [38]}, // Department
                    {"bVisible": false, "aTargets": [39]}, // Cell
                    {"bVisible": false, "aTargets": [40]}, // Fax
                    {"bVisible": false, "aTargets": [41]}, // Type of Business
                    {"bVisible": false, "aTargets": [42]}, // Business Category

                    {"bVisible": false, "aTargets": [43]}, // request sender type
                    {"bVisible": false, "aTargets": [44]}, // inquire details arr object

                    {"bVisible": false, "aTargets": [45]}, // VendorRegn Key - request receiver

                    // { "bVisible":false,"aTargets":[26] },
                    //{ "bVisible":false,"aTargets":[27] },
                    // { "bVisible":false,"aTargets":[28] },

                ],
                        "bLengthChange": false,
                "oLanguage": {
                    "sSearch": "Search",
                    "sEmptyTable": "No Request found"
                },
                "bSort": true,
                "aaSorting": [[0, 'asc']],
                "bPaginate": true,
                "bFilter": true,
                "bInfo": true,
                "iDisplayLength": 15,
                "fnRowCallback": function(nRow, aData, iDisplayIndex,
                        iDisplayIndexFull) {
                    //alert('ok');                
                    $("td", nRow).addClass('rowBorder');

                    $(nRow).click(function() {

                        var aTrs = myReqtable.fnGetNodes();

                        for (var i = 0; i < aTrs.length; i++)
                        {
                            if ($(aTrs[i]).hasClass('row_selected'))
                            {
                                $(aTrs[i]).removeClass('row_selected');
                            }

                        }

                        $(nRow).addClass('row_selected');

                        var aPos = myReqtable.fnGetPosition(this);

                        var aData = myReqtable.fnGetData(aPos);

                        showSuppliersForm(aData);
                    });

                    //alert(nRow);
                    return nRow;
                }
            });

}

function showSuppliersForm(aData)
{
    //alert('ok');
    /*For both popups, the supplier details only will be shown as per the 
     requirements.
     
     If supplier tab is clicked, he will be buyer. Now the pop up will be filled
     with the currently logged in person's company details since here currently 
     logged in user's company will have supplier details */


    //var companyname = aData[0];
    var companyname = aData[27];

    //var phoneno = aData[1];
    var phoneno = aData[28];

    //var country = aData[2];
    var country = aData[29];

    //var emailid = aData[3];
    var emailid = aData[30];

    //var businesscontact = aData[4];
    var businesscontact = aData[31];

    //var regnstatus = aData[5];
    var regnstatus = aData[5];
    //alert(regnstatus);

    //var venRegnKey = aData[6];
    var venRegnKey = aData[45];

    //var branch = aData[7];
    var branch = aData[32] == 'null' ? "" : aData[32];

    //var address = aData[8];
    var address = aData[33];

    //var city = aData[9];
    var city = aData[34];

    //var state = aData[10];
    var state = aData[35];

    //var zipcode = aData[11];
    var zipcode = aData[36];

    //var companytype = aData[12];
    var companytype = aData[37];

    //var department = aData[13];	
    var department = aData[38];

    //var cellno = aData[14];
    var cellno = aData[39];

    //var faxno = aData[15];
    var faxno = aData[40];

    //var businesstaxid = aData[16];
    var businesstaxid = aData[16] == 'null' ? "" : aData[16];
    

    //var businessType = aData[17];
    var businessType = aData[41];

    //var businesscategory = aData[18];
    var businesscategory = aData[42];

    //var naicscode = aData[19];
    var naicscode = aData[19];

    //var w9taxformflag = aData[20];
    var w9taxformflag = aData[20];

    //var w9taxformpath = aData[21];
    var w9taxformpath = aData[21];

    //var businesssize = aData[22];
    var businesssize = aData[22];

    //var businessclassification = aData[23];
    var businessclassification = aData[23];

    //var additionaldetails = aData[24];
    var additionaldetails = aData[24] == 'null' ? "" : aData[24];

    //var venReqId = aData[25];
    var venReqId = aData[25];

    //var regnKey = aData[26];
    var regnKey = aData[26];

    //var inquiryarr = aData[44];
    var inquiryarr = aData[44];


    //var companylevel = aData[25];

    //var companylevel = aData[25];

    if (inquiryarr.length > 0)
    {
        $('#inquiry_details').css("display", "block");

        var inquiryDiv = "";

        inquiryDiv = inquiryDiv + '<div class="side_heading"> Inquiry Details</div>';

        var companyName = "";

        for (var i = 0; i < inquiryarr.length; i++)
        {
            if (i % 2 == 0) {
                companyName = aData[0];
            } else {
                companyName = aData[27];
            }


            inquiryDiv = inquiryDiv + '<div class="inquire_row">';
            inquiryDiv = inquiryDiv + '<label class="inquire_by">' + companyName + '</label>';
            inquiryDiv = inquiryDiv + '<div class="inquire_det">' + inquiryarr[i].inquiredetails + '</div>';
        }

        //alert(inquiryDiv);

        $('#inquiry_details').empty();

        $('#inquiry_details').append(inquiryDiv);
    }

    $('#vendor_regnrec_id').val(venReqId);

    $('#company_name_popup').val(companyname);
    $('#branch_0_popup').val(branch);
    $('#countryregion_0_popup').val(country);

    $('#address_popup').val(address);
    $('#city_popup').val(city);
    $('#state_0_popup').val(state);

    $('#zipcode_popup').val(zipcode);

    if (companytype == 0)
    {
        $('#internetuser_popup').attr('checked', false);
    }
    else
    {
        //$('#transuser').attr('checked', true);
        $('#internetuser_popup').attr('checked', true);
    }

    $('#contact_name_popup').val(businesscontact);

    $('#regn_status').val(regnstatus);

    $("#vendor_regn_id").val(venRegnKey);

    $("#regn_id").val(regnKey);

    $('#titledept_popup').val(department);

    $('#email_popup').val(emailid);

    $('#phone_popup').val(phoneno);

    $('#cell_popup').val(cellno);

    $('#fax_popup').val(faxno);

    $('#taxid_popup').val(businesstaxid);

    $('#typeofbusiness_popup').val(businessType);

    $('#businesscategory_popup').val(businesscategory);

    $('#naicscode_popup').val(naicscode);

    if (businesssize == "Large")
    {
        $('#buss_small_popup').attr('checked', false);
        $('#buss_large_popup').attr('checked', true);
    }
    else
    {
        $('#buss_small_popup').attr('checked', true);
        $('#buss_large_popup').attr('checked', false);
    }

    $("#w9form_flag_popup").attr("disabled", "disabled");

    if (w9taxformflag == 1)
    {
        $("#w9form_flag_popup").attr("checked", true);

        $("#w9Form_upload_popup_btn").show();
    }
    else
    {
        $("#w9form_flag_popup").attr("checked", false);
    }

    $('#additional_det_popup').val(additionaldetails);

    //$('#w9_tax').val(w9taxformpath);

    parseBusinessClassfication(businessclassification);

    //alert(regnstatus);
    if(regnstatus == "Accepted") 
    {
      $("#cntrl_btns").hide();
      $("#cntrl_btns_ok").show();
    }    
    else if (regnstatus == "New Request")
    {
        getCompanyDetails();
         $("#cntrl_btns").show();
        $("#w9form_upload_ctrl").show();
        $("#cntrl_btns_ok").hide();
    }
    else
    {
        $("#cntrl_btns").show();
        $("#w9form_upload_ctrl").hide();
        $("#cntrl_btns_ok").hide();
    }


    if (w9taxformpath.length == 0 || w9taxformpath == 'null')
    {

        $("#w9form_download_ctrl").hide();
    }
    else
    {
        $("#w9form_download_ctrl").show();

        $("#w9FormPath").val(w9taxformpath);

    }

    $("#supplier_form_popup").show();

    customizeSupplierPopUp();

    $("#supplier_form_popup_content").mCustomScrollbar("update");
}

function getBusinessClassfication()
{
    $.ajax({
        type: "POST",
        url: getBaseURL() + "/BusinessClassificationServlet",
        cache: false,
        success: function(resJSON)
        {
            if (resJSON.result == "success")
            {
                businessClassificationArr = new Array(Object.keys(resJSON).length);

                businessClassificationArr = Object.keys(resJSON);

                //alert( businessClassificationArr.length );
            }

            else
            {
                // ShowToast( resJSON.message,2000 );
                //alert( "Failed" );
            }

        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("Exception");
        }
    });
}

function parseBusinessClassfication(businessclassification)
{
    var busiClassArr = businessclassification.split(":");

    for (var i = 0; i < busiClassArr.length; i++)
    {
        if (isObjExistInJSON(busiClassArr[i], businessClassificationArr) == 0)
        {
            $('#' + busiClassArr[i] + '_popup').attr('checked', true);
        }
        else
        {
            $('#' + busiClassArr[i] + '_popup').attr('checked', false);
        }
    }
}

function isObjExistInJSON(obj, resJSON)
{
    for (var i = 0; i < resJSON.length; i++)
    {
        var resJSONVal = resJSON[i];
        if (obj == resJSONVal)
        {
            return 0;
        }
    }
    return -1;
}

function rejectBtnClicked()
{
    var regnKey = $('#regn_id').val();

    var venRegnKey = $("#vendor_regn_id").val();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/VendorRegnServlet",
        data: {
            'RequestType': 'UpdateStatus',
            'Action': 'reject',
            'VendorKey': venRegnKey,
            'RegnKey': regnKey,
        },
        cache: false,
        success: function(resJSON)
        {
            if (resJSON.result == "success")
            {
                $('#supplier_form_popup').hide();
                refreshDT( );
                ShowToast(resJSON.message, 2000);
            }

            else
            {
                $('#supplier_form_popup').hide();
                ShowToast(resJSON.message, 2000);
            }

        },
        error: function(xhr, textStatus, errorThrown)
        {
            $('#supplier_form_popup').hide();
            //alert("Exception");
        }
    });
}


function cancelRegnBtnClicked()
{
    $('#supplier_form_popup').hide();
}

function sendBtnClicked()
{
    updateVenRegForm();
}

function inquireBtnClicked()
{
    //alert("inquire btn clicked");
    $('#cancel_regn_btn').hide();
    $('#inquire_regn_btn').hide();
    //$('#send_regn_btn').hide();

    $('#add_inquiry_content').css("display", "block");
}

function cancelBtnClicked()
{
    $('#cancel_regn_btn').show();
    $('#inquire_regn_btn').show();
//	$('#send_regn_btn').show();

    $('#add_inquiry_content').css("display", "none");
}

function saveBtnClicked()
{
    changeInquireStatus();
}

function changeInquireStatus()
{
    var regnKey = $('#regn_id').val();

    var venRegnKey = $("#vendor_regn_id").val();

    var venRegnId = $('#vendor_regnrec_id').val();

    //alert(venRegnId);
//	alert( venRegnKey );
    //alert(regnKey);
    //return;
    var inquiryDetails = $("#new_inquire_message").val();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/VendorRegnServlet",
        data: {
            'RequestType': 'AddInquiry',
            'Action': 'inquiryanswered',
            'VendorKey': venRegnKey,
            'RegnKey': regnKey,
            'VendorRegnKey': venRegnKey,
            'InquireDetails': inquiryDetails,
            'VendorRegnId': venRegnId,
        },
        cache: false,
        success: function(resJSON)
        {
            if (resJSON.result == "success")
            {
                $('#add_inquiry_content').css("display", "none");
                $("#supplier_form_popup").hide();
                refreshDT( );
                ShowToast(resJSON.message, 2000);
            }

            else
            {
                $('#add_inquiry_content').css("display", "none");
                $("#supplier_form_popup").hide();
                ShowToast(resJSON.message, 2000);
            }

        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("Exception");
        }
    });
}

function downloadW9form()
{
    $('#w9form_download_frm').submit();
}

function refreshDT( )
{
    $("#mainpage").empty();

    $("#content_loader").show();

    $("#mainpage").load("Views/VendorReg/jsp/supplier_ven_reg.jsp", function()
    {
        $("#content_loader").hide();

        $("#req_queue_content").show();
        $("#add_buyer_content").hide();

        $("#req_queue_tab").removeClass("main_tab_unselect");
        $("#req_queue_tab").addClass("main_tab_select");

        $("#add_buyer_tab").removeClass("main_tab_select");
        $("#add_buyer_tab").addClass("main_tab_unselect");

    });
    ;
}
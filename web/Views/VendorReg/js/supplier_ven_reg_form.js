function fetchCountry() {

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/EntityLoaderServlet",
        data: {
            'entityname': 'country',
        },
        dataType: 'json',
        cache: false,
        success: function(data) {

            // Set value for country0 select box
            var $select0 = $('#countryregion');
            $select0.find('option').remove();

            if (data.result == "success")
            {
                $('<option>').val('').text('--Select Country--').appendTo($select0);

                $.each(data, function(key, value)
                {
                    if (value != "success")
                    {
                        $('<option>').val(key).text(value).appendTo($select0);

                        $('#countryregion_0err').text("");

                    }

                });

                // Sort the country in alphabetical order

                sortSelectBox('countryregion');

                $("#countryregion option[value='United States']").remove();

                $("<option value='United States'>United States</option>").insertAfter("#countryregion option:first");
            }
            else
            {
                $select0.find('option').remove();

                $('<option>').val('').text('--N/A--').appendTo(
                        $select0);

            }

            $("#countryregion option:first-child").attr("selected", true);

            $select0.trigger('update');
        },
        error: function(xhr, textStatus, errorThrown)
        {
            $('#countryregion_0err').text("Request failed. Try again.");
        }
    });
}



/*
 * It is used to get the company details.
 */

function getCompanyDetails()
{
    //alert("Getting company details");

    var regnKey = $('#regnkey').val();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/CompanyFullProfileServlet",
        data: {
            'RegnKey': regnKey,
        },
        cache: false,
        success: function(profileJSONObj)
        {
            if (profileJSONObj.result == "success")
            {
                var profileData = profileJSONObj.profiledetails;

                parseComProfile(profileData);

                var addrArr = new Array(profileData.addressdata.length);

                addrArr = profileData.addressdata;

                parseComAddrData(addrArr);
            }
            else
            {
                //alert("else");
            }

        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert("failed");
        }
    });
}



function parseComProfile(profileData)
{

    var companyName = profileData.companyname;

    var contactName = profileData.businesscontactname;

    var titleDept = profileData.department;

    var email = profileData.email;

    var phone = profileData.phone;

    var cell = profileData.cell;

    var fax = profileData.fax;

    var businesscat = profileData.businesscategory;

    var businessType = profileData.businesstype;


    $("#company_name").val(companyName);

    $("#company_name").attr("disabled", "disabled");

    $("#contact_name").val(contactName);

    $("#contact_name").attr("disabled", "disabled");

    $("#titledept").val(titleDept);

    $("#titledept").attr("disabled", "disabled");

    $("#email").val(email);

    $("#email").attr("disabled", "disabled");

    $("#phone").val(phone);

    $("#phone").attr("disabled", "disabled");

    $("#cell").val(cell);

    $("#cell").attr("disabled", "disabled");

    $("#fax").val(fax);

    $("#fax").attr("disabled", "disabled");

    $("#typeofbusiness").val(businessType);

    fetchBuisnessCategory(businesscat);

    $("#businesscategory").attr("disabled", "disabled");



}

function parseComAddrData(addrArr)
{
    var arrCount = addrArr.length;

    for (var i = 0; i < arrCount; i++)
    {
        var arrdata = addrArr[i];

        var address = arrdata.address;

        var city = arrdata.city;

        var state = arrdata.state;

        var zipcode = arrdata.zipcode;

        var branch = arrdata.branch;

        var country = arrdata.country;

        var addrId = arrdata.addressId;

        if (i == 0)
        {


            $("#address").val(address);

            $("#address").attr("disabled", "disabled");

            $("#city").val(city);

            $("#city").attr("disabled", "disabled");

            fetchCountryStateList(country, state);

            $("#countryregion_0").attr("disabled", "disabled");

            $("#state_0").val(state);

            $("#state_0").attr("disabled", "disabled");

            $("#zipcode").val(zipcode);

            $("#zipcode").attr("disabled", "disabled");

            fetchBranchList(branch);

            $("#branch_0").attr("disabled", "disabled");
        }
        /*else
         {
         var AddressElement='<div class="AddressContiner">'+                                    
         '<fieldset>'+
         '<legend> '+branch+'</legend>'+
         '<div class="AddressDetails">'+
         address+'<br />'+city+
         '-'+zipcode+'<br />'+
         state+','+country+
         '</div>'+
         '</fieldset>'+
         '<input type="button" name="removeaddressbtn_'+addrId+'" class="removeaddress" style="display: inline-block;" onclick="removeCompanyAddress(this);">'+                
         '</div>';
         
         $("#AddressList").append(AddressElement);
         
         if ($('#addAddressEmpty').length)
         {
         $("#addAddressEmpty").remove();
         AddressExpander($("#addressExpander"));
         }
         }*/
    }
}

/*
 This method is used to get the non registered the buyer from 
 typing search string.
 */

function getNRVendor()
{
    var regnKey = $('#regnkey').val();

    var searchStr = $("#to_company").val();

    if (searchStr.length == 0)
    {
        $("#ven_search_result").hide();
        return;
    }

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/VendorRegnServlet",
        data: {
            'RequestType': 'ListNRVendors',
            'RegnKey': regnKey,
            'SearchStr': searchStr,
            'RequestSenderType': 'Buyer'
        },
        cache: false,
        success: function(vendorsJSON)
        {
            if (vendorsJSON.result == "success")
            {
                var vendorArr = new Array(vendorsJSON.NRvendors.length);

                vendorArr = vendorsJSON.NRvendors;

                parseVendors(vendorArr);
            }
            else
            {

            }

        },
        error: function(xhr, textStatus, errorThrown)
        {
            // alert("failed");
        }
    });
}


function parseVendors(vendorArr)
{

    $("#ven_search_result").empty();

    for (var i = 0; i < vendorArr.length; i++)
    {

        var vendor = vendorArr[i];

        var vendorKey = vendor.companykey;

        var vendorName = vendor.companyname;

        var venDiv = '<div id="ven_' + vendorKey + '" class="filter_comp">';

        venDiv += vendorName + '</div>';

        $("#ven_search_result").append(venDiv);

        $("#ven_search_result").show();


        $("#ven_" + vendorKey).on('click', function(event) {

            var divid = event.target.id;

            var idSplitArr = divid.split("_");

            if (idSplitArr.length > 1)
            {
                var vendorKey = idSplitArr[1];

                $("#selected_ven_key").val(vendorKey);

                $("#ven_search_result").hide();

                $("#to_company").val($("#" + divid).text());

            }

        });
    }

    if (vendorArr.length == 0)
    {
        var venDiv = '<div id="ven_empty" class="filter_comp">';

        venDiv += 'No result found</div>';

        $("#ven_search_result").append(venDiv);

        $("#ven_search_result").show();
    }
}

/*
 This method is used to get the all pending vendor registration request recieved 
 by the Supplier.
 */

function getSupplierMyPendingRegReq()
{
    var regnKey = $('#regnkey').val();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/VendorRegnServlet",
        data: {
            'RequestType': 'ListMyRequests',
            'RegnKey': regnKey,
            'RequestSenderType': 'Supplier',
        },
        cache: false,
        success: function(pendingMyVenRegReqJSON)
        {
            if (pendingMyVenRegReqJSON.result == "success")
            {


                var myPendingVenReqArr = new Array(pendingMyVenRegReqJSON.vendors.length);

                myPendingVenReqArr = pendingMyVenRegReqJSON.vendors;

                parseMyPendingVenReq(myPendingVenReqArr);
            }

            else
            {
                ShowToast(pendingMyVenRegReqJSON.message, 2000);
            }

        },
        error: function(xhr, textStatus, errorThrown)
        {

        }
    });
}

/* this method is used to fill the NAICS code drop down box */
function getNAICSCode()
{
    $.ajax({
        type: "POST",
        url: getBaseURL() + "/TransConfigServlet",
        data: {
            'TransConfigType': 'GetNAICSCodes',
        },
        dataType: 'json',
        cache: false,
        success: function(data) {
            var $select = $('#naicscode');

            var $select1 = $("#naicscode_popup_select");

            $select.find('option').remove();

            $select1.find('option').remove();

            if (data.result == "success")
            {

                $('<option>').val('').text('-- Select NAICS Code --').appendTo($select);

                $('<option>').val('').text('-- Select NAICS Code --').appendTo($select1);

                $.each(data, function(key, value)
                {
                    if (value != "success")
                    {
                        $('<option>').val(key + " - " + value).text(key + " - " + value).appendTo($select);
                        $('<option>').val(key + " - " + value).text(key + " - " + value).appendTo($select1);

                    }

                });

                sortSelectBox('naicscode');
                sortSelectBox('naicscode_popup_select');

                $("#naicscode option:first-child").attr("selected", true);
                $("#naicscode_popup_select option:first-child").attr("selected", true);

                $('#naicscode option').prop('disabled', false);
                $('#naicscode_popup_select option').prop('disabled', false);


            }
            else
            {
                $select.find('option').remove();
                $select1.find('option').remove();

                $('<option>').val('').text('--N/A--').appendTo(
                        $select);
                $('<option>').val('').text('--N/A--').appendTo(
                        $select1);

                $('#naicscode option').prop('disabled', true);
                $('#naicscode_popup_select option').prop('disabled', true);

            }

            $select.trigger('update');
            $select1.trigger('update');

        },
        error: function(xhr, textStatus, errorThrown)
        {
            $('#companyerr').text("Request failed. Try again.");
        }
    });
}

/*
 This method is used to get the all pending vendor registration request sent
 by the buyer
 */

function getSupplierPendingRegReq()
{
    var regnKey = $('#regnkey').val();

//alert( regnKey );

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/VendorRegnServlet",
        data: {
            'RequestType': 'ListOtherVendorsRequest',
            'RegnKey': regnKey,
            'RequestSenderType': 'Buyer',
        },
        cache: false,
        success: function(pendingVenRegReqJSON)
        {
            //alert(pendingVenRegReqJSON.result)
            if (pendingVenRegReqJSON.result == "success")
            {

                var pendingReqArr = new Array(pendingVenRegReqJSON.vendors.length);

                pendingReqArr = pendingVenRegReqJSON.vendors;

                parsePendingVenReq(pendingReqArr);
            }

            else
            {
                ShowToast(pendingVenRegReqJSON.message, 2000);
            }

        },
        error: function(xhr, textStatus, errorThrown)
        {
            //alert(errorThrown); 
        }
    });
}

function parseMyPendingVenReq(pendingReqArr)
{

    myReqtable.fnClearTable();

    for (var i = 0; i < pendingReqArr.length; i++)
    {

        var pendingReq = pendingReqArr[i];

        var companyname = pendingReq.companyname;

        var vendorcompanyname = pendingReq.vendorcompanyname;


        var phoneno = pendingReq.phoneno;

        var vendorphoneno = pendingReq.vendorphoneno;


        var emailid = pendingReq.emailid;

        var vendoremailid = pendingReq.vendoremailid;


        var businesscontact = pendingReq.businesscontact;

        var vendorbusinesscontact = pendingReq.vendorbusinesscontact;


        var regnstatus = pendingReq.regnstatus;


        var venRegnKey = pendingReq.vendorregnkey;


        var companytype = pendingReq.companytype;

        var vendorcompanytype = pendingReq.vendorcompanytype;


        var businesstype = companytype;

        var vendorbusinesstype = vendorcompanytype;


        var department = pendingReq.department;

        var vendordepartment = pendingReq.vendordepartment;


        var cellno = pendingReq.cellno;

        var vendorcellno = pendingReq.vendorcellno;


        var faxno = pendingReq.faxno;

        var vendorfaxno = pendingReq.vendorfaxno;


        var businesstaxid = pendingReq.businesstaxid;


        var businesscategory = pendingReq.businesscategory;

        var vendorbusinesscategory = pendingReq.vendorbusinesscategory;


        var addresstype = pendingReq.addresstype;

        var vendoraddresstype = pendingReq.vendoraddresstype;


        var address = pendingReq.address;

        var vendoraddress = pendingReq.vendoraddress;


        var city = pendingReq.city;

        var vendorcity = pendingReq.vendorcity;


        var country = pendingReq.country;

        var vendorcountry = pendingReq.vendorcountry;


        var state = pendingReq.state;

        var vendorstate = pendingReq.vendorstate;


        var zipcode = pendingReq.zipcode;

        var vendorzipcode = pendingReq.vendorzipcode;


        var naicscode = pendingReq.naicscode;

        var w9taxformflag = pendingReq.w9taxformflag;

        var w9taxformpath = pendingReq.w9taxformpath;

        var businesssize = pendingReq.businesssize;

        var businessclassification = pendingReq.businessclassification;

        var additionaldetails = pendingReq.additionaldetails;

        var venReqId = pendingReq.vendorregnid;

        var regnKey = pendingReq.regnkey;

        var createdDate = pendingReq.createdtimestamp;

        //var companylevel = pendingReq.companylevel;

        var requestsendertype = pendingReq.requestsendertype;

        var inquiryarr = pendingReq.vendorinquirearr;

        myReqtable.fnAddData([
            vendorcompanyname, //aData[0]
            vendorphoneno, //aData[1]
            vendorcountry, //aData[2]
            vendoremailid, //aData[3]
            vendorbusinesscontact, //aData[4]
            regnstatus, //aData[5]

            createdDate, //aData[6]

            vendoraddresstype, //aData[7]
            vendoraddress, //aData[8]
            vendorcity, //aData[9]
            vendorstate, //aData[10]
            vendorzipcode, //aData[11]

            vendorcompanytype, //aData[12]
            vendordepartment, //aData[13]
            vendorcellno, //aData[14]
            vendorfaxno, //aData[15]

            businesstaxid, //aData[16]
            businesstype, //aData[17]
            businesscategory, //aData[18]

            naicscode, //aData[19]
            w9taxformflag, //aData[20]
            w9taxformpath, //aData[21]
            businesssize, //aData[22]
            businessclassification, //aData[23]
            additionaldetails, //aData[24]

            venReqId, //aData[25]
            regnKey, //aData[26]

            companyname, //aData[27]
            phoneno, //aData[28]
            country, //aData[29]
            emailid, //aData[30]
            businesscontact, //aData[31]

            addresstype, //aData[32]
            address, //aData[33]
            city, //aData[34]
            state, //aData[35]
            zipcode, //aData[36]

            companytype, //aData[37]
            department, //aData[38]
            cellno, //aData[39]
            faxno, //aData[40]

            //businesstaxid,//aData[16]
            vendorbusinesstype, //aData[41]
            vendorbusinesscategory, //aData[42]

            requestsendertype, //aData[43]
            inquiryarr, //aData[44]

            venRegnKey            //aData[45]

        ]);
    }
}


function parsePendingVenReq(pendingReqArr)
{
    buyerReqTable.fnClearTable();

    for (var i = 0; i < pendingReqArr.length; i++)
    {

        var pendingReq = pendingReqArr[i];

        var companyname = pendingReq.companyname;
        var vendorcompanyname = pendingReq.vendorcompanyname;


        var phoneno = pendingReq.phoneno;

        var vendorphoneno = pendingReq.vendorphoneno;


        var emailid = pendingReq.emailid;

        var vendoremailid = pendingReq.vendoremailid;


        var businesscontact = pendingReq.businesscontact;

        var vendorbusinesscontact = pendingReq.vendorbusinesscontact;


        var regnstatus = pendingReq.regnstatus;


        var venRegnKey = pendingReq.vendorregnkey;


        var companytype = pendingReq.companytype;

        var vendorcompanytype = pendingReq.vendorcompanytype;


        var businesstype = companytype;

        var vendorbusinesstype = vendorcompanytype;


        var department = pendingReq.department;

        var vendordepartment = pendingReq.vendordepartment;


        var cellno = pendingReq.cellno;

        var vendorcellno = pendingReq.vendorcellno;


        var faxno = pendingReq.faxno;

        var vendorfaxno = pendingReq.vendorfaxno;


        var businesstaxid = pendingReq.businesstaxid;


        var businesscategory = pendingReq.businesscategory;

        var vendorbusinesscategory = pendingReq.vendorbusinesscategory;


        var addresstype = pendingReq.addresstype;

        var vendoraddresstype = pendingReq.vendoraddresstype;


        var address = pendingReq.address;

        var vendoraddress = pendingReq.vendoraddress;


        var city = pendingReq.city;

        var vendorcity = pendingReq.vendorcity;


        var country = pendingReq.country;

        var vendorcountry = pendingReq.vendorcountry;


        var state = pendingReq.state;

        var vendorstate = pendingReq.vendorstate;


        var zipcode = pendingReq.zipcode;

        var vendorzipcode = pendingReq.vendorzipcode;


        var naicscode = pendingReq.naicscode;

        var w9taxformflag = pendingReq.w9taxformflag;

        var w9taxformpath = pendingReq.w9taxformpath;

        var businesssize = pendingReq.businesssize;

        var businessclassification = pendingReq.businessclassification;

        var additionaldetails = pendingReq.additionaldetails;

        var venReqId = pendingReq.vendorregnid;

        var regnKey = pendingReq.regnkey;

        var createdDate = pendingReq.createdtimestamp;

        //var companylevel = pendingReq.companylevel;

        var requestsendertype = pendingReq.requestsendertype;

        var inquiryarr = pendingReq.vendorinquirearr;

        buyerReqTable.fnAddData([
            vendorcompanyname, //aData[0]
            vendorphoneno, //aData[1]
            vendorcountry, //aData[2]
            vendoremailid, //aData[3]
            vendorbusinesscontact, //aData[4]
            regnstatus, //aData[5]

            createdDate, //aData[6]
            vendoraddresstype, //aData[7]
            vendoraddress, //aData[8]
            vendorcity, //aData[9]
            vendorstate, //aData[10]
            vendorzipcode, //aData[11]

            vendorcompanytype, //aData[12]
            vendordepartment, //aData[13]
            vendorcellno, //aData[14]
            vendorfaxno, //aData[15]

            businesstaxid, //aData[16]
            businesstype, //aData[17]
            businesscategory, //aData[18]

            naicscode, //aData[19]
            w9taxformflag, //aData[20]
            w9taxformpath, //aData[21]
            businesssize, //aData[22]
            businessclassification, //aData[23]
            additionaldetails, //aData[24]

            venReqId, //aData[25]
            regnKey, //aData[26]

            companyname, //aData[27]
            phoneno, //aData[28]
            country, //aData[29]
            emailid, //aData[30]
            businesscontact, //aData[31]

            addresstype, //aData[32]
            address, //aData[33]
            city, //aData[34]
            state, //aData[35]
            zipcode, //aData[36]

            companytype, //aData[37]
            department, //aData[38]
            cellno, //aData[39]
            faxno, //aData[40]

            //businesstaxid,//aData[16]
            vendorbusinesstype, //aData[41]
            vendorbusinesscategory, //aData[42]

            requestsendertype, //aData[43]
            inquiryarr, //aData[44]
            venRegnKey           //aData[45]

        ]);

    }
}

function addBuyer()
{
    var regnKey = $("#regnkey").val();

    var vendorKey = $("#selected_ven_key").val();

    if (vendorKey == "")
    {
        $("#vendor_reg_error").text("Select buyer");

        return;
    }
    if ($("#state_0").val().replace(/^\s+|\s+$/g, '') === "" || $("#zipcode").val().replace(/^\s+|\s+$/g, '') === "")
    {
        if ($("#usr_typ").val() === "Admin")
        {
            $("#cntct_dtl_er").fadeIn()
            $("#cntct_dtl_er").text("Update your contact detail for adding a vendor");
            setTimeout("$('#cntct_dtl_er').fadeOut()",5000)
        }
        else
        {
            $("#cntct_dtl_er").fadeIn()
            $("#cntct_dtl_er").text("Contact your company admin to update your company contact detail for adding a vendor");
            setTimeout("$('#cntct_dtl_er').fadeOut()",5000)
        }
        return;
    }
    var additionalDet = $("#additional_det").val();

    var contactName = $("#contact_name").val();

    var bussSize = $('input[name=buss_size]:radio:checked').val();

    var bussTaxId = $("#taxid").val();

    var companyLevel = $('input[name=comtype]:radio:checked').val();

    var NAICSCode = $("#naicscode").val();

    var W9TaxFormFlag = 0;

    if ($('#w9form_flag').is(':checked'))
    {
        W9TaxFormFlag = 1;

        if ($("#w9form").val() == "")
        {
            $("#vendor_reg_error").text("Select the W9 Tax Form");

            return;
        }
    }
    else
    {
        W9TaxFormFlag = 0;
    }

    showAjaxLoader();

    var bussinessClafiArr = new Array();

    if ($('#Disadvantaged').is(':checked'))
    {
        bussinessClafiArr.push("Disadvantaged");
    }

    if ($('#HubZone').is(':checked'))
    {
        bussinessClafiArr.push("HubZone");
    }

    if ($('#WomenOwned').is(':checked'))
    {
        bussinessClafiArr.push("WomenOwned");
    }


    if ($('#HandicappedOwned').is(':checked'))
    {
        bussinessClafiArr.push("HandicappedOwned");
    }


    if ($('#VETERANOWNED').is(':checked'))
    {
        bussinessClafiArr.push("VETERANOWNED");
    }


    if ($('#SDVETERANOWNED').is(':checked'))
    {
        bussinessClafiArr.push("SDVETERANOWNED");
    }


    if ($('#HBCORMI').is(':checked'))
    {
        bussinessClafiArr.push("HBCORMI");
    }

    if ($('#MBE').is(':checked'))
    {
        bussinessClafiArr.push("MBE");
    }


    if ($('#NonProfit').is(':checked'))
    {
        bussinessClafiArr.push("NonProfit");
    }


    if ($('#Foreign').is(':checked'))
    {
        bussinessClafiArr.push("Foreign");
    }


    if ($('#PublicSector').is(':checked'))
    {
        bussinessClafiArr.push("PublicSector");
    }


    if ($('#ANCORITNSB').is(':checked'))
    {
        bussinessClafiArr.push("ANCORITNSB");
    }

    if ($('#ANCORITNCD').is(':checked'))
    {
        bussinessClafiArr.push("ANCORITNCD");
    }

    var businessClafi = "";

    for (var i = 0; i < bussinessClafiArr.length; i++)
    {
        if (i == 0)
        {
            businessClafi += bussinessClafiArr[0];
        }
        else
        {
            businessClafi += ":" + bussinessClafiArr[i];
        }
    }



    var W9TaxFormPath = "";

    var form = $("#ven_reg_form");

    var data = new FormData();

    data.append('RequestType', 'AddVendor');

    data.append('RegnKey', regnKey);

    data.append('VendorKey', vendorKey);

    data.append('AdditionalDetails', additionalDet);

    data.append('BusinessClassification', businessClafi);

    data.append('BusinessContact', contactName);

    data.append('BusinessSize', bussSize);

    data.append('BusinessTaxid', bussTaxId);

    data.append('CompanyLevel', companyLevel);

    data.append('NAICSCode', NAICSCode);

    data.append('W9TaxFormFlag', W9TaxFormFlag);

    data.append('W9TaxFormPath', W9TaxFormPath);

    data.append('RequestSenderType', 'Supplier');

    data.append('Action', 'formsent');

    data.append('w9Form', document.ven_reg_form.w9form.files[0]);


    //alert("NAICSCode="+NAICSCode);




    $.ajax({
        type: "POST",
        url: form.attr('action'),
        data: data,
        contentType: false,
        processData: false,
        success: function(data)
        {
            hideAjaxLoader();

            if (data.result == "success")
            {

                ShowToast(data.message, 2000);

                resetVenRegForm();


            }

            else
            {
                //alert( "Failed" );
                ShowToast(data.message, 2000);
            }

        },
        error: function(xhr, textStatus, errorThrown)
        {
            hideAjaxLoader();

            //alert("Exception");
        }
    });
}

/* it is used to reset the vendor registration form */
function  resetVenRegForm()
{
    $("#selected_ven_key").val("");
    $("#vendor_reg_error").text("");
    $("#taxid").val("");
    $("#naicscode").val("-- NAICS Code --");
    $("#to_company").val("");
    $("#w9form").val("");
    $("#additional_det").val("");
    $('#buss_small_popup').attr('checked', false);
    $('#Disadvantaged').attr('checked', false);
    $('#HubZone').attr('checked', false);
    $('#WomenOwned').attr('checked', false);
    $('#HandicappedOwned').attr('checked', false);
    $('#VETERANOWNED').attr('checked', false);
    $('#SDVETERANOWNED').attr('checked', false);
    $('#HBCORMI').attr('checked', false);
    $('#MBE').attr('checked', false);
    $('#NonProfit').attr('checked', false);
    $('#Foreign').attr('checked', false);
    $('#PublicSector').attr('checked', false);
    $('#ANCORITNSB').attr('checked', false);
    $('#ANCORITNCD').attr('checked', false);
}

function updateVenRegForm()
{
    var regnKey = $("#regnkey").val();

    //var vendorKey = $("#selected_ven_key").val();
    var vendorKey = $("#vendor_regn_id").val();

    if (vendorKey == "")
    {
        $("#vendor_reg_popup_error").text("Select buyer");

        return;
    }

    var additionalDet = $("#additional_det_popup").val();

    var contactName = $("#contact_name_popup").val();

    var bussSize = $('input[name=buss_size_popup]:radio:checked').val();

    var bussTaxId = $("#taxid_popup").val();

    var companyLevel = $('input[name=comtype]:radio:checked').val();

    var NAICSCode = $("#naicscode_popup_select").val();

    var W9TaxFormFlag = 0;

    if ($('#w9form_flag_popup').is(':checked'))
    {
        W9TaxFormFlag = 1;

        if ($("#popup_w9form").val() == "")
        {
            $("#vendor_reg_popup_error").text("Select the W9 Tax Form");

            return;
        }
    }
    else
    {
        W9TaxFormFlag = 0;
    }



    var bussinessClafiArr = new Array();

    if ($('#Disadvantaged_popup').is(':checked'))
    {
        bussinessClafiArr.push("Disadvantaged");
    }

    if ($('#HubZone_popup').is(':checked'))
    {
        bussinessClafiArr.push("HubZone");
    }

    if ($('#WomenOwned_popup').is(':checked'))
    {
        bussinessClafiArr.push("WomenOwned");
    }


    if ($('#HandicappedOwned_popup').is(':checked'))
    {
        bussinessClafiArr.push("HandicappedOwned");
    }


    if ($('#VETERANOWNED_popup').is(':checked'))
    {
        bussinessClafiArr.push("VETERANOWNED");
    }


    if ($('#SDVETERANOWNED_popup').is(':checked'))
    {
        bussinessClafiArr.push("SDVETERANOWNED");
    }


    if ($('#HBCORMI_popup').is(':checked'))
    {
        bussinessClafiArr.push("HBCORMI");
    }

    if ($('#MBE_popup').is(':checked'))
    {
        bussinessClafiArr.push("MBE");
    }


    if ($('#NonProfit_popup').is(':checked'))
    {
        bussinessClafiArr.push("NonProfit");
    }


    if ($('#Foreign_popup').is(':checked'))
    {
        bussinessClafiArr.push("Foreign");
    }


    if ($('#PublicSector_popup').is(':checked'))
    {
        bussinessClafiArr.push("PublicSector");
    }


    if ($('#ANCORITNSB_popup').is(':checked'))
    {
        bussinessClafiArr.push("ANCORITNSB");
    }

    if ($('#ANCORITNCD_popup').is(':checked'))
    {
        bussinessClafiArr.push("ANCORITNCD");
    }

    var businessClafi = "";

    for (var i = 0; i < bussinessClafiArr.length; i++)
    {
        if (i == 0)
        {
            businessClafi += bussinessClafiArr[0];
        }
        else
        {
            businessClafi += ":" + bussinessClafiArr[i];
        }
    }

    var vendorRegnId = $("#vendor_regnrec_id").val();

    var W9TaxFormPath = "";

    var form = $("#popup_ven_reg_form");

    var data = new FormData();

    data.append('RequestType', 'Update');

    data.append('RegnKey', regnKey);

    data.append('VendorKey', vendorKey);

    data.append('AdditionalDetails', additionalDet);

    data.append('BusinessClassification', businessClafi);

    data.append('BusinessContact', contactName);

    data.append('BusinessSize', bussSize);

    data.append('BusinessTaxid', bussTaxId);

    data.append('CompanyLevel', companyLevel);

    data.append('NAICSCode', NAICSCode);

    data.append('W9TaxFormFlag', W9TaxFormFlag);

    data.append('W9TaxFormPath', W9TaxFormPath);

    data.append('VendorRegnId', vendorRegnId);

    data.append('Action', 'formsent');

    data.append('w9Form', document.popup_ven_reg_form.popup_w9form.files[0]);

    showAjaxLoader();


    $.ajax({
        type: "POST",
        url: form.attr('action'),
        data: data,
        contentType: false,
        processData: false,
        success: function(data)
        {
            hideAjaxLoader();

            if (data.result == "success")
            {
                $('#supplier_form_popup').hide();

                ShowToast(data.message, 2000);

                $("#selected_ven_key").val("");

                $("#vendor_reg_popup_error").text("");
            }

            else
            {

                $('#supplier_form_popup').hide();

                ShowToast(data.message, 2000);
            }

        },
        error: function(xhr, textStatus, errorThrown)
        {
            hideAjaxLoader();

            $('#supplier_form_popup').hide();

        }
    });
}

function w9checkBoxClicked()
{
    if ($('#w9form_flag').is(':checked'))
    {
        $("#w9Form_upload_btn").show();
    }
    else
    {
        $("#w9Form_upload_btn").hide();
    }
}

function w9checkBoxPopupClicked()
{
    if ($('#w9form_flag_popup').is(':checked'))
    {
        $("#w9Form_upload_popup_btn").show();
    }
    else
    {
        $("#w9Form_upload_popup_btn").hide();
    }
}
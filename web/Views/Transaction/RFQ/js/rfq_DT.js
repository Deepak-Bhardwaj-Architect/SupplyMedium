
$(document).ready(function()
{
    $('#rfq_list tbody td #trans_count').die('click');

    $('#rfq_list tbody td #trans_count').live('click', function()
    {

        var nTr = $(this).parents('tr')[0];

        if (rfqDataTable.fnIsOpen(nTr))
        {
            /* This row is already open - close it */

            $(this).addClass('trans_count_expand');

            $(this).removeClass('trans_count_collapse');

            rfqDataTable.fnClose(nTr);

        }
        else
        {
            /* Open this row */
            $(this).removeClass('trans_count_expand');

            $(this).addClass('trans_count_collapse');

            rfqDataTable.fnOpen(nTr, fnTransDetails(rfqDataTable, nTr), 'details');
        }
    });
});

function fnTransDetails(rfqDataTable, nTr)
{
    var aData = rfqDataTable.fnGetData(nTr);

    var regnKey = $("#regnkey").val();



    var RFQId = aData[1];
    var date = "";
    var companyName = "";
    var phone = "";
    var email = "";

    var status = "";


    var fromCompName = aData[3];
    var fromRegnKey = aData[4];
    var fromUserKey = aData[5];
    var fromState = aData[6];


    var toRegnKey = aData[13];
    var toUserKey = aData[14];
    var toCompName = aData[15];
    var toState = aData[17];


    var transArr = aData[26];

    var sOut = '<table>';

    for (var i = 1; i < transArr.length; i++)
    {
        var trans = transArr[i];

        var img = "";

        var state = "";

        status = trans.status;

        if (status == "RFQCreated")
        {
            status = "RFQ Created";
        }
        else if (status == "RFQInquire")
        {
            status = "RFQ Inquire";
        }
        else if ("RFQAccepted")
        {
            status = "RFQ Accepted";
        }


        date = trans.date;

        if (trans.from == regnKey)
        {
            img += "<img src='Views/Transaction/TransCommon/images/trans_sent_icon.png' class='trans_sent'/>";
        }
        else
        {
            img += "<img src='Views/Transaction/TransCommon/images/trans_receive_icon.png' class='trans_receive'/>";
        }

        if (trans.from == fromRegnKey)
        {
            companyName = fromCompName;

            phone = fromRegnKey;

            email = fromUserKey;

            state = fromState;
        }
        else
        {
            companyName = toCompName;

            phone = toRegnKey;

            email = toUserKey;

            state = toState;
        }


        sOut += '<tr>';
        sOut += '<td class="rowBorder" style="width:20px;">' + img + '</td>';
        sOut += '<td class="rowBorder"  style="width:76px;">' + RFQId + '</td>';
        sOut += '<td class="rowBorder"  style="width:110px;">' + date + '</td>';
        sOut += '<td class="rowBorder" style="width:135px;">' + companyName + '</td>';
        sOut += '<td class="rowBorder" style="width:110px;">' + phone + '</td>';
        sOut += '<td class="rowBorder" style="width:160px;">' + email + '</td>';
        sOut += '<td class="rowBorder" style="width:111px;">' + state + '</td>';
        sOut += '<td class="rowBorder" style="width:105px;">' + status + '</td>';
        sOut += '<td class="rowBorder" style="width:48px;">' + "" + '</td>';
        sOut += '</tr>';
    }

    sOut += '</table>';

    return sOut;
}

var rfqDataTable;

/* It is used to initialize the RFQ datatable */

function initRFQDataTable()
{
    rfqDataTable = $('#rfq_list').dataTable({
        sDom: 'lfr<"fixed_height"t>ip',
        "sPaginationType": "full_numbers",
        "bAutoWidth": false,
        "aoColumnDefs": [
            {"sWidth": "20px", "aTargets": [0]}, // Sender symbol indication
            {"sWidth": "76px", "aTargets": [1]}, // RFQId
            {"sWidth": "110px", "aTargets": [2]}, // RFQ Date

            {"sWidth": "134px", "aTargets": [3]}, // From Company Name
            {"sWidth": "110px", "aTargets": [4]}, // From Phone number
            {"sWidth": "160px", "aTargets": [5]}, // From Email id
            {"sWidth": "112px", "aTargets": [6]}, // from State
            {"sWidth": "105px", "aTargets": [7]}, // Status
            {"sWidth": "46px", "aTargets": [8]}, // Empty

            {"bVisible": false, "aTargets": [9]}, // From country
            {"bVisible": false, "aTargets": [10]}, // from city
            {"bVisible": false, "aTargets": [11]}, // from address
            {"bVisible": false, "aTargets": [12]}, // from zipcode
            {"bVisible": false, "aTargets": [13]}, // to regnkey
            {"bVisible": false, "aTargets": [14]}, // to user key
            {"bVisible": false, "aTargets": [15]}, // to company name
            {"bVisible": false, "aTargets": [16]}, // to country
            {"bVisible": false, "aTargets": [17]}, // to state
            {"bVisible": false, "aTargets": [18]}, // to city
            {"bVisible": false, "aTargets": [19]}, // to address
            {"bVisible": false, "aTargets": [20]}, // to zipcode
            {"bVisible": false, "aTargets": [21]}, // is outside supplier
            {"bVisible": false, "aTargets": [22]}, // outside supplier email
            {"bVisible": false, "aTargets": [23]}, // reccurring
            {"bVisible": false, "aTargets": [24]}, // quotation reference
            {"bVisible": false, "aTargets": [25]}, // Items
            {"bVisible": false, "aTargets": [26]}, // Transactions
            {"bVisible": false, "aTargets": [27]}, // Inquires 
            {"bVisible": false, "aTargets": [28]}, // TransId 
            {"bVisible": false, "aTargets": [29]}, // isQuoteCreated
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
                var aTrs = rfqDataTable.fnGetNodes();

                for (var i = 0; i < aTrs.length; i++)
                {
                    if ($(aTrs[i]).hasClass('row_selected'))
                    {
                        $(aTrs[i]).removeClass('row_selected');
                    }

                }

                $(nRow).addClass('row_selected');

                var aPos = rfqDataTable.fnGetPosition(this);

                var aData = rfqDataTable.fnGetData(aPos);

                showRFQPopupForm(aData);

            });

            return nRow;
        }
    });


    //var myCars=new Array("Saab","Volvo","BMW");

    var transCountImg = "";

    transCountImg += "<div id='trans_count' class='trans_count_expand'>" + 3 + "</div>";

    var inquireChat = "<input type='button' class='inquire_chat_btn' onclick='showInquirePopup();'/>";



}

/* This method is used to fetch the pending RFQ request. Then parse the request
 * and fill this into My Request datatable */

function fetchRFQRequest()
{
    var regnKey = $("#regnkey").val();

    var userKey = $("#EmailAddress").val();

    showAjaxLoader();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/RFQServlet",
        data: {
            'RequestType': 'FetchRFQ',
            'RegnKey': regnKey,
            'UserKey': userKey,
        },
        cache: false,
        success: function(resJSON)
        {
            hideAjaxLoader();

            if (resJSON.result == "success")
            {

                var rfqArr = new Array(resJSON.rfqlist.length);

                rfqArr = resJSON.rfqlist;

                parseRFQRequest(rfqArr);

                //ShowToast( resJSON.message,2000 );
            }

            else
            {
                ShowToast(resJSON.message, 2000);
            }

        },
        error: function(xhr, textStatus, errorThrown)
        {
            hideAjaxLoader();

            alert("Exception");
        }
    });
}


/* This method is used to show the RFQ popup form. 
 * It get the rfq form data fromt the datatable and fill the 
 * popup form then dispaly the RFQ form in popup view.
 */

function showRFQPopupForm(aData)
{
    //alert(aData);
    $("#rfq_popup").show();

    var regnKey = $("#regnkey").val();


    var RFQId = aData[1];
    var RFQDate = aData[2];
    
   /* var fromCompName = aData[16];
    var fromRegnKey = aData[14];
    var fromUserKey = aData[15];
    var fromState = aData[18];
    var status = aData[7];
    var fromCountry = aData[17];
    var fromCity = aData[19];
    var fromAddress = aData[20];
    var fromZipcode = aData[21];
    
    var toRegnKey = aData[4];
    var toUserKey = aData[5];
    var toCompName = aData[3];
    var toCountry = aData[10];
    var toState = aData[6];
    var toCity = aData[11];
    var toAddress = aData[12];
    var toZipcode = aData[13];*/
    
    var fromCompName = aData[3];
    var fromRegnKey = aData[4];
    var fromUserKey = aData[5];
    var fromState = aData[6];
    var status = aData[7];
    var fromCountry = aData[10];
    var fromCity = aData[11];
    var fromAddress = aData[12];
    var fromZipcode = aData[13];
    
    var toRegnKey = aData[14];
    var toUserKey = aData[15];
    var toCompName = aData[16];
    var toCountry = aData[17];
    var toState = aData[18];
    var toCity = aData[19];
    var toAddress = aData[20];
    var toZipcode = aData[21];
    
    var isOutsideSupplier = aData[22];
    var outsideSupplierEmail = aData[23];
    var recurring = aData[24];
    var quotationRef = aData[25];
    var items = aData[26];
   // alert(items);
    var transaction = aData[27];
    var inquires = aData[28];
    var transId = aData[29];
    var isQuoteCreated = aData[30];

    $("#status").val(status);

    $("#popup_quote_ref").val(quotationRef);

    $("#to_company_popup").val(toCompName);

    $("#dt").val(RFQDate);

    if (regnKey == fromRegnKey)  // For Buyer
    {
        $("#reply_to_comp").val(toRegnKey);

        $("#reply_to_user").val(toUserKey);
    }
    else if (regnKey == toRegnKey) // For Supplier
    {
        $("#reply_to_comp").val(fromRegnKey);

        $("#reply_to_user").val(fromUserKey);
    }

    $("#inquire_ctrls").hide();

    if (status == "Approve RFQ")
    {
        $("#rfq_popup_new_inquire").hide();

        $("#buyer_ctrls").hide();

        $("#rfq_update").hide();
        
       // alert(regnKey+" nd "+fromRegnKey+" nd "+toRegnKey);
        
        if (aData[31] == "sent")  // For Buyer 
        {
            $("#supplier_ctrls").hide();

            $("#rfq_close").show();
        }
        else if (aData[31] == "rec") // For Supplier
        {
            $("#supplier_ctrls").show();

            $("#rfq_close").hide();

        }
    }

    else if (status == "RFQ Inquire")
    {
        if (regnKey == fromRegnKey)  // For Buyer 
        {
            $("#supplier_ctrls").hide();

            $("#buyer_ctrls").show();

            $("#rfq_close").hide();

            $("#rfq_popup_new_inquire").hide();

        }
        else if (regnKey == toRegnKey) // For Supplier
        {
            $("#supplier_ctrls").hide();

            $("#buyer_ctrls").hide();

            $("#rfq_close").show();

            $("#rfq_popup_new_inquire").hide();

        }
    }
    else if (status == "Rejected")
    {
        $("#supplier_ctrls").hide();

        $("#buyer_ctrls").hide();

        $("#rfq_close").show();

        $("#rfq_popup_new_inquire").hide();
    }

    else  // This is for RFQ Accepted ready for Generate Quote
    {
        $("#supplier_ctrls").hide();

        $("#buyer_ctrls").hide();

        $("#rfq_close").show();

        $("#rfq_popup_new_inquire").hide();
    }

    $("#rfq_id").val(RFQId);

    $("#trans_id").val(transId);

    $("#popup_buyer_name").val(fromCompName);

    $("#popup_buyer_country").val(fromCountry);

    var fromStateArr = fromState.split("<");

    if (fromStateArr.length > 0)
        fromState = fromStateArr[0];

    $("#popup_buyer_state").val(fromState);

    $("#popup_buyer_city").val(fromCity);

    $("#popup_buyer_addr").val(fromAddress);

    $("#popup_buyer_zipcode").val(fromZipcode);



    $("#popup_supplier_name").val(toCompName);

    $("#popup_supplier_country").val(toCountry);

    $("#popup_supplier_state").val(toState);

    $("#popup_supplier_city").val(toCity);

    $("#popup_supplier_addr").val(toAddress);

    $("#popup_supplier_zipcode").val(toZipcode);


    if (isOutsideSupplier == 1)
    {

        $('#popup_outside_supplier').prop('checked', true);

        $("#popup_email").val(outsideSupplierEmail);

        $(".popup_supplier_address").hide();

        $(".popup_outside_sup_email_content").show();


    }
    else
    {
        $('#popup_outside_supplier').prop('checked', false);

        $(".popup_supplier_address").show();

        $(".popup_outside_sup_email_content").hide();
    }

    $("#popup_items").empty();
    
    for (var i = 0; i < items.length; i++)
    {
        var item = new Object();

        item = items[i];
        
        //alert(item.itemDesc);
        
        var itemDet = "";

        var sNo = i + 1;

        itemDet += '<div id="popup_item' + sNo + '" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;position:relative;">';

        itemDet += '<input type="text" class="textbox" id="popup_sno' + sNo + '" style="width:30px;margin-right:20px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" class="textbox" id="popup_item_desc' + sNo + '" style="width:175px;margin-right:20px;" disabled  value="' + item.itemDesc + '";/>';
        itemDet += '<input type="text" class="textbox" id="popup_part_no' + sNo + '" style="width:90px;margin-right:20px;" disabled value="' + item.partNo + '";/>';

        itemDet += '<input type="text" class="textbox" id="popup_quantity' + sNo + '" style="width:90px;" disabled value="' + item.quantity + '";/>';
        itemDet += '<div class="quantity_unit" id="popup_quantity_unit' + sNo + '" >' + item.quantityUnit + '</div>';
        itemDet += '<div class="quan_units" id="units_popup_quantity_unit' + sNo + '" style="display:none;left:487px;"></div>';

        itemDet += '<input type="text" class="textbox" id="popup_ship_date' + sNo + '" style="width:110px;margin-right:20px;" disabled value="' + item.shipDate + '";/>';
        itemDet += '<input type="button" class="del_btn" id="del_btn_' + sNo + '" style="width:110px;margin-right:20px;display:none;" onclick="deletePopupItem(' + sNo + ');"/>';
        itemDet += '</div>';

        $("#popup_items").append(itemDet);



        //$("#popup_quantity_unit"+sNo).attr('disabled','disabled');


        //$("#popup_quantity_unit"+sNo).prop('disabled',true);

    }

    $("#items_count").val(items.length);

    $("#popupSNo").val(items.length);

    $("#popup_inquires").empty();


    for (var j = 0; j < inquires.length; j++)
    {
        var inquire = new Object();

        inquire = inquires[j];

        var companyName = "";

        if (fromRegnKey == inquire.from)
        {
            companyName = fromCompName;
        }
        else
        {
            companyName = toCompName;
        }

        var inquireDet = "";

        inquireDet += '<div class="inquire_row">';
        inquireDet += '<label class="inquire_by">' + companyName + ' </label>';
        inquireDet += '<div class="inquire_det" disabled>' + inquire.details + ' </div>';
        inquireDet += '</div>';

        $("#popup_inquires").append(inquireDet);
    }

    var regnKey = $("#regnkey").val();

    // This compnay is the buyer 
    if (regnKey == fromRegnKey)
    {
        $("#supplier_controls").hide();
        $("#buyer_controls").show();
    }
    else // This company is the supplier so show the accepe,reject,inquire buttons.
    {
        $("#supplier_controls").show();
        $("#buyer_controls").hide();
    }

    $("#rfq_popup_form").mCustomScrollbar("update");
}

/* This method is used to parse the pending rfq request json. And set the parsing details to 
 * My Request Data table.
 */
function parseRFQRequest(rfqArr)
{
    rfqDataTable.fnClearTable();

    for (var i = 0; i < rfqArr.length; i++)
    {

        var rfq = rfqArr[i];

        var RFQId = rfq.RFQId;

        var RFQDate = rfq.RFQDate;

        var transId = rfq.transId;

        // RFQ Sender address details
        var fromRegnKey = rfq.fromRegnKey;

        var fromUserKey = rfq.fromUserKey;

        var fromCompName = rfq.fromName;

        var fromCountry = rfq.fromCountry;

        var fromState = rfq.fromState;

        var fromCity = rfq.fromCity;

        var fromAddress = rfq.fromAddress;

        var fromZipcode = rfq.fromZipcode;


        // RFQ receiver address details

        var toRegnKey = rfq.toRegnKey;

        if (toRegnKey == "")
            continue;

        var toUserKey = "";

        if (rfq.toUserKey != "null")
            toUserKey = rfq.toUserKey;

        var toCompName = "";

        if (rfq.toName != "null")
            toCompName = rfq.toName;



        var isOutsideSupplier = rfq.isOutsideSupplier;

        var toCountry = "";

        var toState = "";

        var toCity = "";

        var toAddress = "";

        var toZipcode = "";

        if (isOutsideSupplier == 0)
        {
            toCountry = rfq.toCountry;

            toState = rfq.toState;

            toCity = rfq.toCity;

            toAddress = rfq.toAddress;

            toZipcode = rfq.toZipcode;
        }



        var isQuoteCreated = rfq.isQuoteCreated;

        var status = rfq.status;

        var regnKey = $("#regnkey").val();

        if (status == "RFQCreated")
        {
            if (fromRegnKey == regnKey)
        {
            status = "RFQ Genrated";
        }
        else
        {
            status = "Approve RFQ";
        }
        }
        else if (status == "RFQInquire")
        {
            status = "RFQ Inquire";
        }
        else if ("RFQAccepted")
        {
            status = "RFQ Accepted";
        }

        var open = "<img src='Views/Transaction/TransCommon/images/open.png'/>";


        var outsideSupplierEmail = rfq.outsideSupplierEmail;

        var quotationRef = rfq.quotationRef;

        var recurring = rfq.recurring;

        var items = rfq.items;

        var trans = rfq.trans;

        var inquires = rfq.inquires;

        var transImg = "";

        if (fromRegnKey == regnKey)
        {
            transImg += "<img src='Views/Transaction/TransCommon/images/trans_sent_icon.png' class='trans_sent'/>";
        }
        else
        {
            transImg += "<img src='Views/Transaction/TransCommon/images/trans_receive_icon.png' class='trans_receive'/>";
        }

        var transCount = trans.length;

        var transCountImg = "";

        if (transCount > 1)
        {
            transCountImg += "<div id='trans_count' class='trans_count_expand'>" + (transCount - 1) + "</div>";
        }

        var inquireChat = "";

        var inquiresCount = inquires.length;

        if (inquiresCount > 0)
        {
            inquireChat += "<input type='button' class='inquire_chat_btn' onclick='showInquirePopup(" + i + ");'/>";
        }

        if (fromRegnKey != regnKey && status == "RFQ Accepted") // Company is a supplier
        {

            if (isQuoteCreated == 0)
            {
                status = "<input type='button' value='Generate Quote' class='generate_trans_btn' onclick='generateQuote(" + i + ");'>";
            }

            else
            {
                status = "Quote Genrated";
            }

        }

        if (fromRegnKey == regnKey)
        {
           
            rfqDataTable.fnAddData([transImg, RFQId, RFQDate, toCompName , toRegnKey, toUserKey, fromState + inquireChat, status, open,
                transCountImg, fromCountry, fromCity, fromAddress, fromZipcode,fromRegnKey ,fromUserKey ,fromCompName, toCountry, toState,
                toCity, toAddress, toZipcode, isOutsideSupplier, outsideSupplierEmail, recurring, quotationRef,
                items, trans, inquires, transId, isQuoteCreated,"sent"]);
        }
        else
        {
           rfqDataTable.fnAddData([transImg, RFQId, RFQDate, fromCompName, fromRegnKey, fromUserKey, fromState + inquireChat, status, open,
                transCountImg, fromCountry, fromCity, fromAddress, fromZipcode, toRegnKey, toUserKey, toCompName, toCountry, toState,
                toCity, toAddress, toZipcode, isOutsideSupplier, outsideSupplierEmail, recurring, quotationRef,
                items, trans, inquires, transId, isQuoteCreated,"rec"]); 
        }
    }

}


/* This method is used to show the inquire details for the particular RFQ in popup view */

function showInquirePopup(rowNo)
{
    var aData = rfqDataTable.fnGetData(rowNo);

    var regnKey = $("#regnkey").val();

    var inquires = aData[27];

    var fromCompName = aData[3];
    var fromRegnKey = aData[4];
    var toCompName = aData[15];
    var RFQId = aData[1];
    var transId = aData[28];
    var status = aData[7];
    var fromUserKey = aData[5];
    var toUserKey = aData[14];
    var toRegnKey = aData[13];


    if (regnKey == fromRegnKey)  // For Buyer
    {
        $("#chat_reply_to_comp").val(toRegnKey);

        $("#chat_reply_to_user").val(toUserKey);
    }
    else if (regnKey == toRegnKey) // For Supplier
    {
        $("#chat_reply_to_comp").val(fromRegnKey);

        $("#chat_reply_to_user").val(fromUserKey);
    }


    $("#chat_rfq_id").val(RFQId);

    $("#chat_trans_id").val(transId);

    $("#chat_status").val(status);



    $("#chat_rfq_inquires").empty();

    for (var i = 0; i < inquires.length; i++)
    {
        var companyName = "";

        var inquire = inquires[i];

        var details = inquire.details;

        if (fromRegnKey == inquire.from)
        {
            companyName = fromCompName;
        }
        else
        {
            companyName = toCompName;
        }

        var inquireDiv = '<div class="inquire_row">';

        inquireDiv += '<label class="inquire_by">' + companyName + ' </label>';

        inquireDiv += '<div class="inquire_det" disabled>' + details + '</div>';

        inquireDiv += '</div>';

        $("#chat_rfq_inquires").append(inquireDiv);

    }

    if (status == "RFQ Created")
    {
        action = "Inquire";

        if (regnKey == fromRegnKey)  // For Buyer 
        {
            $("#chat_new_inquire").hide();
            $("#chat_inquire_save_btn").hide();

        }
        else if (regnKey == toRegnKey) // For Supplier
        {
            $("#chat_new_inquire").show();

            $("#chat_inquire_save_btn").show();

            $("#chat_new_inquire_message").focus();

            $("#chat_new_inquire_message").val("");
        }
    }

    else if (status == "RFQ Inquire")
    {
        action = "Update";

        if (regnKey == fromRegnKey)  // For Buyer 
        {
            $("#chat_new_inquire").show();
            $("#chat_inquire_save_btn").show();

            $("#chat_new_inquire_message").focus();

            $("#chat_new_inquire_message").val("");

        }
        else if (regnKey == toRegnKey) // For Supplier
        {
            $("#chat_new_inquire").hide();
            $("#chat_inquire_save_btn").hide();

        }
    }
    else if (status == "RFQ Accepted" || status == "Rejected")
    {
        $("#chat_new_inquire").hide();
        $("#chat_inquire_save_btn").hide();
    }

    if (regnKey == fromRegnKey)
    {
        $("#chat_company_name").text(fromCompName);
    }
    else
    {
        $("#chat_company_name").text(toCompName);
    }


    $("#rfq_inquire_popup").show();

}

/* This method is used to open the Quote form and fill the form with RFQ Data */

function generateQuote(rowNo)
{
    //alert('enter');
    var aData = rfqDataTable.fnGetData(rowNo);
    showQuoteFromRFQ(aData);
}



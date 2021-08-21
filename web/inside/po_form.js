
sNo = 0;
itemsCount = 0;

var poObj = new Object();

/* This method is used to add the po item in po form
 * from po item popup view
 */
function saveItem()
{
    var itemDet = "";

    var itemDesc = $("#popup_item_desc").val();

    var partNo = $("#popup_part_no").val();

    var brcd = $("#popup_brcd").val();

    var quantity = $("#popup_quantity").val();

    var quantityUnit = $("#quantity_unit").text();

    var shipDate = $("#popup_ship_date").val();

    var priceperunit = parseFloat($("#popup_price").val());

    var currency = $("#currency").text();

    var dscnt = $("#pop_dscnt").val();

    var multiplier = $("#pop_multiplier").val();

    if (itemDesc == "")
    {
        $("#po_item_form_error").text("Enter the Item description.");

        $("#popup_item_desc").focus();

        return;
    }
    else if (partNo == "")
    {
        $("#po_item_form_error").text("Enter the Batch/Lot No.");

        $("#popup_part_no").focus();

        return;
    }
    else if (quantity == "")
    {
        $("#po_item_form_error").text("Enter the Quantity");

        $("#popup_quantity").focus();

        return;
    }

    else if (shipDate == "")
    {
        $("#po_item_form_error").text("The shipping date is not selected");

        $("#popup_ship_date").focus();

        return;
    }
    else if (priceperunit == "")
    {
        $("#po_item_form_error").text("Select the priceperunit");

        $("#popup_price").focus();

        return;
    }
    else if (multiplier == "")
    {
        $("#po_item_form_error").text("Select the multiplier");

        $("#pop_multiplier").focus();

        return;
    }
    else if (!isNumber(quantity))
    {
        $("#po_item_form_error").text("Please use only numbers, and periods");

        $("#popup_quantity").focus();

        return;
    }
    else if (!isNumber(priceperunit))
    {
        $("#po_item_form_error").text("Please use only numbers, and periods");

        $("#popup_price").focus();

        return;
    }
    else if ((quantity.length + priceperunit.length + multiplier.length) > 12)
    {
        $("#po_item_form_error").text("Total price value is out of expected range");

        return;

    }

    $("#po_item_form_error").text("");

    $("#po_add_item_popup").hide();



    // Add item button clicked from PO creation form
    if ($("#from_form").val() == "POForm")
    {
        sNo = sNo + 1;

        itemsCount = itemsCount + 1;

        itemDet += '<div id="item' + itemsCount + '" class="item" style="width:1010px;float:left;margin-left:10px;margin-right:20px;">';

        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:30px;margin-right:20px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="item_desc' + itemsCount + '" style="width:100px;margin-right:20px;" disabled  value="' + itemDesc + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="part_no' + itemsCount + '" style="width:90px;margin-right:20px;" disabled value="' + partNo + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="brcd' + itemsCount + '" style="width:90px;margin-right:20px;" disabled value="' + brcd + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="quantity' + itemsCount + '" style="width:60px;" disabled value="' + quantity + '";/>';
        itemDet += '<div class="quantity_unit" id="quantity_unit' + itemsCount + '" >' + quantityUnit + '</div>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" id="ship_date' + itemsCount + '" style="width:85px;margin-right:20px;" disabled value="' + shipDate + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="price' + itemsCount + '" style="width:60px;" disabled value="' + priceperunit + '";/>';
        itemDet += '<div class="quantity_unit" id="currency' + itemsCount + '" >' + currency + '</div>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=5 id="dscnt' + itemsCount + '" style="width:50px;margin-right:20px;" disabled value="' + dscnt + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=5 id="multiplier' + itemsCount + '" style="width:85px;margin-right:20px;" disabled value="' + multiplier + '";/>';

        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:0px;" onclick="deleteItem(' + itemsCount + ');"/></div>';

        $("#items").append(itemDet);

//        var totalListPrice = parseFloat($("#tot_list_price_amt").text());
//
//        totalListPrice = totalListPrice + (quantity * priceperunit * multiplier);
//
//        $("#tot_list_price_amt").text(totalListPrice);
//
//        var tax = parseFloat($("#tax_amt").text());
//
//        var totPrice = calculateTotPrice(totalListPrice, tax);
//
//        $("#tot_price_amt").text(totPrice);
    }
    else  // Add item button clicked from PO Edit from poup view
    {


        sNo = parseInt($("#popupSNo").val()) + 1;

        itemsCount = parseInt($("#items_count").val()) + 1;

        $("#items_count").val(itemsCount);

        $("#popupSNo").val(sNo);


        itemDet += '<div id="popup_item' + itemsCount + '" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;">';

        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:30px;margin-right:20px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_item_desc' + itemsCount + '" style="width:165px;margin-right:20px;" disabled  value="' + itemDesc + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_part_no' + itemsCount + '" style="width:90px;margin-right:20px;" disabled value="' + partNo + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="popup_quantity' + itemsCount + '" style="width:90px;" disabled value="' + quantity + '";/>';
        itemDet += '<div class="quantity_unit" id="popup_quantity_unit' + itemsCount + '" >' + quantityUnit + '</div>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_ship_date' + itemsCount + '" style="width:80px;margin-right:20px;" disabled value="' + shipDate + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="popup_price' + itemsCount + '" style="width:60px;" disabled value="' + priceperunit + '";/>';
        itemDet += '<div class="quantity_unit" id="popup_currency' + itemsCount + '" >' + currency + '</div>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=5 id="popup_multiplier' + itemsCount + '" style="width:65px;margin-right:20px;" disabled value="' + multiplier + '";/>';

        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:20px;" onclick="deletePopupItem(' + itemsCount + ');"/></div>';

//        var totalListPrice = parseFloat($("#popup_tot_list_price_amt").text());
//
//        totalListPrice = totalListPrice + (quantity * priceperunit * multiplier);
//
//        $("#popup_tot_list_price_amt").text(totalListPrice);
//
//        var tax = parseFloat($("#popup_tax_amt").text());
//
//        var totPrice = calculateTotPrice(totalListPrice, tax);
//
//        $("#popup_tot_price_amt").text(totPrice);

        $("#popup_items").append(itemDet);
    }


}



/* This method is used to delete the item from PO form */
function deleteItem(itemsCount)
{

// Calculate total before delete the item

    var priceperunit = parseFloat($("#price" + itemsCount).val());

    var multiplier = parseFloat($("#multiplier" + itemsCount).val());

    var quantity = $("#quantity" + itemsCount).val();

    var totalListPrice = parseFloat($("#tot_list_price_amt").text());

    //totalListPrice = totalListPrice - (quantity * priceperunit * multiplier);
    totalListPrice = totalListPrice - multiplier;

    $("#tot_list_price_amt").text(totalListPrice);

    var tax = parseFloat($("#qt_tx").val());

    var totPrice = calculateTotPrice(totalListPrice, tax);

    $("#tot_price_amt").text(totPrice);


    $("#item" + itemsCount).toggle();
    $("#item" + itemsCount).remove();

    sNo = sNo - 1;
}



function validatePO()
{
    var regnKey = $("#regnkey").val();

    var userKey = $("#EmailAddress").val();

    var toRegnKey = $("#selected_ven_key").val();
    
    var touserKey = $("#selected_ven_user_key").val();

    var totalListPrice = $("#tot_list_price_amt").text();

    var tax = $("#qt_tx").val();

    var totalPrice = $("#tot_price_amt").text();

    var transId = $("#form_trans_id").val();


    //alert("regnKey"+regnKey+"userKey"+userKey+"toRegnKey"+toRegnKey+"touserKey"+touserKey);


    poObj.fromRegnKey = regnKey;
    poObj.fromUserKey = userKey;
    poObj.toRegnKey = toRegnKey;
    poObj.touserKey = touserKey;
    poObj.totalListPrice = totalListPrice;
    poObj.taxPercentage = tax;
    poObj.totalPrice = totalPrice;
    poObj.transId = transId;
    poObj.action = "CreatePO";



    if ($('#outside_supplier').is(":checked"))
    {
        poObj.isOutsideSupplier = 1;
        poObj.outsideSuppliername = $("#otsd_splr_nm").val();
        poObj.outsideSupplierEmail = $("#email").val();
        poObj.outsideSuppliercountry = $("#countryregion_0  option:selected").val();
        poObj.outsideSupplierstate = $("#state_text").val();
        poObj.outsideSuppliercity = $("#otsd_splr_cty").val();
        poObj.outsideSupplieraddress = $("#otsd_splr_adrs").val();
        poObj.outsideSupplierzipcode = $("#otsd_splr_zpcd").val();
        if (poObj.outsideSuppliercountry==="United States")
		{
			
			poObj.outsideSupplierstate=$("#state_0  option:selected").val();
			
		}
		else
		{	
			
			poObj.outsideSupplierstate=$("#state_text").val();
			
		}

        if (poObj.outsideSupplierEmail == "")
        {
            $("#po_form_error").text("Outside supplier E-Mail field is empty ");

            $("#email").focus();

            return;
        }
    }
    else
    {
        poObj.isOutsideSupplier = 0;

        if ($("#to_company").val() == "" || toRegnKey == "")
        {
            $("#po_form_error").text("Select the Supplier to send the Purchase Order Form.");

            $("#to_company").focus();

            return;
        }
        else if (!$('#po_terms_cond').is(":checked"))
        {
            $("#po_form_error").text("Accept Terms and Conditions to proceed.");

            $("#po_terms_cond").focus();

            return;
        }

    }



    var items = new Array();

    for (var i = 1; i <= itemsCount; i++)
    {

        if ($("#item" + i).length > 0)
        {
            var item = new Object();
            item.itemDesc = $("#item_desc" + i).val().trim();

            if (item.itemDesc == "")
            {
                $("#po_form_error").text("Enter all the required field values");

                $("#item_desc" + i).focus();

                return;
            }

            item.partNo = $("#part_no" + i).val().trim();
            if (item.partNo == "")
            {
                $("#po_form_error").text("Enter all the required field values");

                $("#part_no" + i).focus();

                return;
            }
            
            item.brcd = $("#brcd" + i).val().trim();
            if (item.brcd == "")
            {
                $("#po_form_error").text("Enter all the required field values");

                $("#brcd" + i).focus();

                return;
            }

            item.quantity = $("#quantity" + i).val().trim();
            if (item.quantity == "")
            {
                $("#po_form_error").text("Enter all the required field values");

                $("#quantity" + i).focus();

                return;
            }

            else if (!isNumber(item.quantity))
            {
                $("#po_form_error").text("Please use only numbers and periods");

                $("#quantity" + i).focus();

                return;
            }

            item.quantityUnit = $("#quantity_unit" + i).text().trim();
            if (item.quantityUnit == "")
            {
                $("#po_form_error").text("Enter all the required field values");

                $("#quantity_unit" + i).focus();

                return;
            }

            item.shipDate = $("#ship_date" + i).val();
            if (item.shipDate == "")
            {
                $("#po_form_error").text("Enter all the required field values");

                $("#ship_date" + i).focus();

                return;
            }

            item.price = $("#price" + i).val().trim();
            if (item.price == "")
            {
                $("#po_form_error").text("Enter all the required field values");

                $("#price" + i).focus();

                return;
            }

            else if (!isNumber(item.price))
            {
                $("#po_form_error").text("Please use only numbers and periods");

                $("#price" + i).focus();

                return;
            }

            item.currency = $("#currency" + i).text().trim();
            if (item.currency == "")
            {
                $("#po_form_error").text("Enter all the required field values");

                $("#currency" + i).focus();

                return;
            }

            item.multiplier = $("#multiplier" + i).val().trim();
            if (item.multiplier == "")
            {
                $("#po_form_error").text("Enter all the required field values");

                $("#multiplier" + i).focus();

                return;
            }

            else if (!isNumber(item.multiplier))
            {
                $("#po_form_error").text("Please use only numbers");

                $("#multiplier" + i).focus();

                return;
            }


            items.push(item);
        }
    }



    poObj.items = items;




    if (items.length == 0)
    {

        $("#po_form_error").text("Add at least one item to proceed.");

        return;
    }

    if ($('#outside_supplier').is(":checked"))
    {
        deleteConfirm()
        //showWarning("Do you want to send the SupplyMedium invitation E-Mail to an \"outside supplier\"?");
    }
    else
    {
        poObj.outsideSupplierMailFlag = 1;

        sendPO();
    }
}

function deleteConfirm()
{
    poObj.outsideSupplierMailFlag = 1;

    sendPO();
}

function cancelInfo()
{

    poObj.outsideSupplierMailFlag = 0;

    sendPO();
}







/* This method is used to send the new PO form to selected supplier
 * from registered supplier list.
 */
function sendPO()
{
    $("#add_po_btn").attr("disabled", "disabled");
    var poData = JSON.stringify(poObj);

    showAjaxLoader();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/POServlet",
        dataType: 'json',
        data: {
            'RequestType': "POCreation",
            'PO': poData,
        },
        success: function(resJSON)
        {
            hideAjaxLoader();

            if (resJSON.result == "success")
            {
                resetPOForm();
                $("#add_po_btn").removeAttr("disabled");
                ShowToast(resJSON.message, 2000);

                fetchPORequest();
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


/* This method is used to reset the PO from details */
function resetPOForm()
{
    $("#to_company").val("");

    $("#outside_supplier").attr("checked", false);

    $("#supplier_country").val("");

    $("#supplier_state").val("");

    $("#supplier_city").val("");

    $("#supplier_addr").val("");

    $("#supplier_zipcode").val("");

    $("#email").val("");

    $("#po_terms_cond").attr("checked", false);

    $("#items").empty();

    $("#po_form_error").text("");

    $("#selected_ven_key").val("");

    $("#tot_list_price_amt").text("0");

    $("#tot_price_amt").text("0");
}

/* this method is used to reset the error label */
function resetErrorLbl()
{
    $("#po_form_error").text("");

}


/* this method is used to calculate the total price */
function calculateTotPrice(totListPrice, tax)
{
    var taxAmt = 0;

    var totPrice = 0;

    taxAmt = (totListPrice * tax) / 100;

    totPrice = totListPrice + taxAmt;

    return totPrice;
}
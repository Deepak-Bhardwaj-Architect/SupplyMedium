
sNo = 0;
itemsCount = 0;

var invoiceObj = new Object();

/* This method is used to add the invoice item in invoice form
 * from invoice item popup view
 */
function saveItem()
{
    var itemDet = "";

    var itemDesc = $("#popup_item_desc").val();

    var partNo = $("#popup_part_no").val();

    var brcd = $("#popup_brcd").val();

    var quantityOrdered = $("#popup_quantity_ordered").val();

    var quantityOrderedUnit = $("#quantity_ordered_unit").text();

    var quantityShipped = $("#popup_quantity_shipped").val();

    var quantityShippedUnit = $("#quantity_shipped_unit").text();

    var price = parseFloat($("#popup_price").val());

    var currency = $("#currency").text();

    var dscnt = parseFloat($("#pop_dscnt").val());

    var multiplier = parseFloat($("#pop_multiplier").val());


    if (itemDesc == "")
    {
        $("#invoice_item_form_error").text("Enter the Item description.");

        $("#popup_item_desc").focus();

        return;
    }
    else if (partNo == "")
    {
        $("#invoice_item_form_error").text("Enter the Part No.");

        $("#popup_part_no").focus();

        return;
    }
    else if (quantityOrdered == "")
    {
        $("#invoice_item_form_error").text("Enter the Quantity Ordered");

        $("#popup_quantity_ordered").focus();

        return;
    }


    else if (quantityShipped == "")
    {
        $("#invoice_item_form_error").text("Enter the Quantity Shipped");

        $("#popup_quantity_shipped").focus();

        return;
    }

    else if (price == "")
    {
        $("#invoice_item_form_error").text("Select the price");

        $("#popup_price").focus();

        return;
    }

    else if (!isNumber(quantityOrdered))
    {
        $("#invoice_item_form_error").text("Please use only numbers and periods");

        $("#popup_quantity_ordered").focus();

        return;
    }
    else if (!isNumber(quantityShipped))
    {
        $("#invoice_item_form_error").text("Please use only numbers");

        $("#popup_quantity_shipped").focus();

        return;
    }
    else if (!isNumber(price))
    {
        $("#invoice_item_form_error").text("Please use only numbers and periods");

        $("#popup_price").focus();

        return;
    }
    else if ((quantityOrdered.length + price.length) > 12)
    {
        $("#invoice_item_form_error").text("Total price value is out of expected range");

        return;

    }

    $("#invoice_item_form_error").text("");

    $("#invoice_add_item_popup").hide();



    // Add item button clicked from Invoice creation form
    if ($("#from_form").val() == "invoiceForm")
    {
        sNo = sNo + 1;

        itemsCount = itemsCount + 1;

        itemDet += '<div id="item' + itemsCount + '" class="item" style="width:970px;float:left;margin-left:10px;margin-right:20px;">';

        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:30px;margin-right:10px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="item_desc' + itemsCount + '" style="width:100px;margin-right:10px;" disabled  value="' + itemDesc + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="part_no' + itemsCount + '" style="width:90px;margin-right:10px;" disabled value="' + partNo + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="brcd' + itemsCount + '" style="width:90px;margin-right:10px;" disabled value="' + brcd + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="quantity_ordered' + itemsCount + '" style="width:60px;" disabled value="' + quantityOrdered + '";/>';
        itemDet += '<div class="quantity_unit" id="quantity_ordered_unit' + itemsCount + '" style="margin-right:10px;">' + quantityOrderedUnit + '</div>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="quantity_shipped' + itemsCount + '" style="width:60px;" disabled value="' + quantityShipped + '";/>';
        itemDet += '<div class="quantity_unit" id="quantity_shipped_unit' + itemsCount + '" style="margin-right:10px;">' + quantityShippedUnit + '</div>';


        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="price' + itemsCount + '" style="width:60px;" disabled value="' + price + '";/>';
        itemDet += '<div class="quantity_unit" id="currency' + itemsCount + '" style="margin-right:10px;">' + currency + '</div>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="dscnt' + itemsCount + '" style="width:50px;margin-right:10px;" disabled value="' + dscnt + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="multiplier' + itemsCount + '" style="width:100px;margin-right:10px;" disabled value="' + multiplier + '";/>';

        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:10px;" onclick="deleteItem(' + itemsCount + ');"/></div>';

        $("#items").append(itemDet);

//        var totalListPrice = parseFloat($("#tot_list_price_amt").text());
//
//        totalListPrice = totalListPrice + (price * quantityOrdered);
//
//        $("#tot_list_price_amt").text(totalListPrice);
//
//        var tax = parseFloat($("#tax_amt").text());
//
//        var totPrice = calculateTotPrice(totalListPrice, tax);
//
//        $("#tot_price_amt").text(totPrice);
    }
    else  // Add item button clicked from invoice Edit from popup view
    {


        sNo = parseInt($("#popupSNo").val()) + 1;

        itemsCount = parseInt($("#items_count").val()) + 1;

        $("#items_count").val(itemsCount);

        $("#popupSNo").val(sNo);


        itemDet += '<div id="popup_item' + itemsCount + '" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;">';

        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:30px;margin-right:20px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="popup_item_desc' + itemsCount + '" style="width:165px;margin-right:20px;" disabled  value="' + itemDesc + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="popup_part_no' + itemsCount + '" style="width:90px;margin-right:20px;" disabled value="' + partNo + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="popup_quantity_ordered' + itemsCount + '" style="width:90px;" disabled value="' + quantityOrdered + '";/>';
        itemDet += '<div class="quantity_unit" id="popup_quantity_ordered_unit' + itemsCount + '" >' + quantityOrderedUnit + '</div>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="popup_quantity_shipped' + itemsCount + '" style="width:90px;" disabled value="' + quantityShipped + '";/>';
        itemDet += '<div class="quantity_unit" id="popup_quantity_shipped_unit' + itemsCount + '" >' + quantityShippedUnit + '</div>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="popup_price' + itemsCount + '" style="width:60px;" disabled value="' + price + '";/>';
        itemDet += '<div class="quantity_unit" id="popup_currency' + itemsCount + '" >' + currency + '</div>';



        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:20px;" onclick="deletePopupItem(' + itemsCount + ');"/></div>';

//        var totalListPrice = parseFloat($("#popup_tot_list_price_amt").text());
//
//        totalListPrice = totalListPrice + (price * quantityOrdered);
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





/* This method is used to delete the item from invoice form */
function deleteItem(itemsCount)
{
// Calculate total before delete the item

    var price = parseFloat($("#multiplier" + itemsCount).val());

    var totalListPrice = parseFloat($("#tot_list_price_amt").text());

    totalListPrice = totalListPrice - price;

    $("#tot_list_price_amt").text(totalListPrice);

    var tax = parseFloat($("#qt_tx").val());

    var totPrice = calculateTotPrice(totalListPrice, tax);

    $("#tot_price_amt").text(totPrice);
    $("#item" + itemsCount).toggle();
    $("#item" + itemsCount).remove();

    sNo = sNo - 1;
}

function validateInvoice()
{
    //alert('1');
    var regnKey = $("#regnkey").val();

    var userKey = $("#EmailAddress").val();

    var toRegnKey = $("#selected_ven_key").val();
    
    var toUserKey = $("#selected_ven_ids").val();

    var totalListPrice = $("#tot_list_price_amt").text();
    //alert('2');

    var tax = $("#qt_tx").val();

    var totalPrice = $("#tot_price_amt").text();

    var freightHandling = $("#frei_hand_amt").text();

    var invoiceDueDate = $("#invoice_due_date").val();
    //alert('3');

    var freightCarrier = $("#carrier").val();

    var freightWeight = $("#freight_weight").val();

    var freightWeightUnit = $("#quantity_freight_unit").text();

    var shippedDate = $("#date_shipped").val();

    var billOfLanding = $("#bill_of_land").val();

    var poNum = $("#po_no").val();

    var transId = $("#form_trans_id").val();

    var selected_address = $("#selected_address").val();

    var isDiffAddr = 0;

    if ($('#is_diff_addr').is(':checked'))
    {
        isDiffAddr = 1;
    }

    var diffEmail = $("#diff_addr_email").val();



    invoiceObj.fromRegnKey = regnKey;
    invoiceObj.fromUserKey = userKey;
    invoiceObj.toRegnKey = toRegnKey;
    invoiceObj.toUserKey = toUserKey;
    invoiceObj.totalListPrice = totalListPrice;
    invoiceObj.taxPercentage = tax;
    invoiceObj.totalPrice = totalPrice;
    invoiceObj.freightHandling = freightHandling;
    invoiceObj.invoiceDueDate = invoiceDueDate;

    invoiceObj.freightCarrier = freightCarrier;
    if (freightWeight.replace(/^\s+|\s+$/g, '') === "")
    {
        freightWeight = "0";
    }
    if (shippedDate.replace(/^\s+|\s+$/g, '') === "")
    {
        shippedDate = "00-Jun-0000";
    }
    if (billOfLanding.replace(/^\s+|\s+$/g, '') === "")
    {
        billOfLanding = "0";
    }
    else
    {
        if (!isNumber(billOfLanding))
        {
            $("#invoice_form_error").text("Please use only numbers");

            $("#bill_of_land").focus();

            return;

        }
    }
    invoiceObj.freightWeight = freightWeight;
    invoiceObj.freightWeightUnit = freightWeightUnit;
    invoiceObj.shippedDate = shippedDate;
    invoiceObj.billOfLanding = billOfLanding;

    invoiceObj.poNum = poNum;
    invoiceObj.isNonPoInvoice = 1;
    invoiceObj.isDiffAdd = isDiffAddr;
    invoiceObj.transId = transId;
    invoiceObj.action = "CreateInvoice";
    invoiceObj.diffEmail = diffEmail;
    invoiceObj.selected_address = selected_address;

    //alert('4');
    if ($('#outside_supplier').is(":checked"))
    {
        invoiceObj.isOutsideSupplier = 1;
        invoiceObj.outsideSuppliername = $("#otsd_splr_nm").val();
        invoiceObj.outsideSupplierEmail = $("#email").val();
        invoiceObj.outsideSuppliercountry = $("#countryregion_0  option:selected").val();
        invoiceObj.outsideSupplierstate = $("#state_text").val();
        invoiceObj.outsideSuppliercity = $("#otsd_splr_cty").val();
        invoiceObj.outsideSupplieraddress = $("#otsd_splr_adrs").val();
        invoiceObj.outsideSupplierzipcode = $("#otsd_splr_zpcd").val();
        if (invoiceObj.outsideSuppliercountry==="United States")
		{
			
			invoiceObj.outsideSupplierstate=$("#state_0  option:selected").val();
			
		}
		else
		{	
			
			invoiceObj.outsideSupplierstate=$("#state_text").val();
			
		}

        if (invoiceObj.outsideSupplierEmail == "")
        {
            $("#invoice_form_error").text("Outside Buyer E-Mail field is empty");

            $("#email").focus();

            return;
        }
    }
    else
    {
        invoiceObj.isOutsideSupplier = 0;

        if ($("#to_company").val() == "" || toRegnKey == "")
        {
            $("#invoice_form_error").text("Select the Buyer to send the Invoice Form.");

            $("#to_company").focus();

            return;
        }
        else if (!$('#invoice_terms_cond').is(":checked"))
        {
            $("#invoice_form_error").text("Accept Terms and Conditions to proceed.");

            $("#invoice_terms_cond").focus();

            return;
        }
    }

    var items = new Array();

    for (var i = 1; i <= itemsCount; i++)
    {

        if ($("#item" + i).length > 0)
        {
            var item = new Object();

            item.itemDesc = $("#item_desc" + i).val();

            if (item.itemDesc == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#item_desc" + i).focus();

                return;
            }
            item.partNo = $("#part_no" + i).val();
            if (item.partNo == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#part_no" + i).focus();

                return;
            }

            item.brcd = $("#brcd" + i).val();
            if (item.brcd == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#brcd" + i).focus();

                return;
            }

            item.quantityOrdered = $("#quantity_ordered" + i).val();
            if (item.quantityOrdered == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#quantity_ordered" + i).focus();

                return;
            }
            item.quantityOrderedUnit = $("#quantity_ordered_unit" + i).text();
            if (item.quantityOrderedUnit == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#quantity_ordered_unit" + i).focus();

                return;
            }

            item.quantityShipped = $("#quantity_shipped" + i).val();
            if (item.quantityShipped == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#quantity_shipped" + i).focus();

                return;
            }
            item.quantityShippedUnit = $("#quantity_shipped_unit" + i).text();
            if (item.quantityShippedUnit == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#quantity_shipped_unit" + i).focus();

                return;
            }

            item.price = $("#price" + i).val();
            if (item.price == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#price" + i).focus();

                return;
            }
            item.currency = $("#currency" + i).text();
            if (item.currency == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#currency" + i).focus();

                return;
            }
            //alert('ok');
            item.multiplier=$("#dscnt" + i).val();
           // alert(item.multiplier);
            if (item.multiplier == "")
            {
                $("#invoice_form_error").text("Enter all the required field values");

                $("#dscnt" + i).focus();

                return;
            }

            items.push(item);
        }
    }



    invoiceObj.items = items;




    if (invoiceDueDate == "")
    {
        $("#invoice_form_error").text("Invoice due date is not selected");

        $("#invoice_due_date").focus();

        return;
    }
    else if (items.length == 0)
    {

        $("#invoice_form_error").text("Add at least one item to proceed.");

        $("#invoice_terms_cond").focus();

        return;
    }
    /*else if( freightCarrier == ""  )
     {
     $("#invoice_form_error").text("Select the carrier.");
     
     $("#carrier").focus();
     
     return;
     }
     else if( billOfLanding == ""  )
     {
     $("#invoice_form_error").text("Enter the bill of landing number.");
     
     $("#bill_of_land").focus();
     
     return;
     }
     else if( !isNumber(billOfLanding) )
     {
     $("#invoice_form_error").text("Please use only numbers");
     
     $("#bill_of_land").focus();
     
     return;
     
     }
     else if( freightWeight == ""  )
     {
     $("#invoice_form_error").text("Enter the Freight Weight");
     
     $("#freight_weight").focus();
     
     return;
     }*/

    if ($('#outside_supplier').is(":checked"))
    {
        deleteConfirm()
        //showWarning("Do you want to send the SupplyMedium invitation E-Mail to an \"Outside supplier\"?");
    }
    else
    {
        invoiceObj.outsideSupplierMailFlag = 1;

        sendInvoice();
    }
}


function deleteConfirm()
{
    invoiceObj.outsideSupplierMailFlag = 1;

    sendInvoice();
}

function cancelInfo()
{

    invoiceObj.outsideSupplierMailFlag = 0;

    sendInvoice();
}

/* This method is used to send the new invoice form to selected supplier
 * from registered supplier list.
 */
function sendInvoice()
{
    $("#add_invoice_btn").attr("disabled", "disabled");
    var invoiceData = JSON.stringify(invoiceObj);

    showAjaxLoader();

    $.ajax({
        type: "POST",
        url: getBaseURL() + "/InvoiceServlet",
        dataType: 'json',
        data: {
            'RequestType': "InvoiceCreation",
            'Invoice': invoiceData,
        },
        success: function(resJSON)
        {
            hideAjaxLoader();

            if (resJSON.result == "success")
            {
                resetInvoiceForm();
                
                $("#add_invoice_btn").removeAttr("disabled"); 
                
                ShowToast(resJSON.message, 2000);

                fetchInvoiceRequest();
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


/* This method is used to reset the Invoice from details */
function resetInvoiceForm()
{
    $("#to_company").val("");

    $("#outside_supplier").attr("checked", false);

    $("#supplier_country").val("");

    $("#supplier_state").val("");

    $("#supplier_city").val("");

    $("#supplier_addr").val("");

    $("#supplier_zipcode").val("");

    $("#email").val("");

    $("#invoice_terms_cond").attr("checked", false);

    $("#items").empty();

    $("#invoice_form_error").text("");

    $("#selected_ven_key").val("");

    $("#tot_list_price_amt").text("0");

    $("#tot_price_amt").text("0");

    $("#carrier").val("");

    // Carrier details reset

    $("#carrier").trigger("update");

    $("#bill_of_land").val("");

    $("#freight_weight").val("");

    $("#date_shipped").val("");

    $("#invoice_due_date").val("");

    $("#po_no").val("");
}

/* this method is used to reset the error label */
function resetErrorLbl()
{
    $("#invoice_form_error").text("");

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


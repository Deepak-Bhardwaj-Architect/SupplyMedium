
sNo = 0;
itemsCount = 0;

var quoteObj = new Object();


/* This method is used to add the quote item in quote form
 * from quote item popup view
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

    var dscnt = $("#dscnt").val();

    var multiplier = $("#pop_multiplier").val();

    if (itemDesc == "")
    {
        $("#quote_item_form_error").text("Enter the Item description.");

        $("#popup_item_desc").focus();

        return;
    }
    else if (partNo == "")
    {
        $("#quote_item_form_error").text("Enter the Batch/Lot No.");

        $("#popup_part_no").focus();

        return;
    }
    else if (quantity == "")
    {
        $("#quote_item_form_error").text("Enter the Quantity");

        $("#popup_quantity").focus();

        return;
    }


    else if (shipDate == "")
    {
        $("#quote_item_form_error").text("The shipping date is not selected");

        $("#popup_ship_date").focus();

        return;
    }
    else if (priceperunit == "")
    {
        $("#quote_item_form_error").text("Select the priceperunit");

        $("#popup_price").focus();

        return;
    }
    else if (multiplier == "")
    {
        $("#quote_item_form_error").text("Select the multiplier");

        $("#pop_multiplier").focus();

        return;
    }
    else if (!isNumber(quantity))
    {
        $("#quote_item_form_error").text("Please use only numbers and periods");

        $("#popup_quantity").focus();

        return;
    }
    else if (!isNumber(priceperunit))
    {
        $("#quote_item_form_error").text("Please use only numbers and periods");

        $("#popup_quantity").focus();

        return;
    }

    else if ((quantity.length + priceperunit.length + multiplier.length) > 12)
    {
        $("#quote_item_form_error").text("Total price value is out of expected range");

        return;
    }

    $("#quote_item_form_error").text("");

    $("#quote_add_item_popup").hide();



    // Add item button clicked from Quote creation form
    if ($("#from_form").val() == "QuoteForm")
    {
        sNo = sNo + 1;

        itemsCount = itemsCount + 1;

        itemDet += '<div id="item' + itemsCount + '" class="item" style="width:1000px;float:left;margin-left:10px;margin-right:20px;">';

        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:30px;margin-right:20px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="item_desc' + itemsCount + '" style="width:100px;margin-right:20px;" disabled  value="' + itemDesc + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="part_no' + itemsCount + '" style="width:90px;margin-right:20px;" disabled value="' + partNo + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="brcd' + itemsCount + '" style="width:60px;margin-right:20px;" disabled value="' + brcd + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="quantity' + itemsCount + '" style="width:60px;" disabled value="' + quantity + '";/>';
        itemDet += '<div class="quantity_unit" id="quantity_unit' + itemsCount + '" >' + quantityUnit + '</div>';


        itemDet += '<input type="text" autocomplete="off" class="textbox" id="ship_date' + itemsCount + '" style="width:85px;margin-right:20px;" disabled value="' + shipDate + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="price' + itemsCount + '" style="width:60px;" disabled value="' + priceperunit + '";/>';
        itemDet += '<div class="quantity_unit" id="currency' + itemsCount + '" >' + currency + '</div>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=5 id="dscnt' + itemsCount + '" style="width:35px;margin-right:20px;" disabled value="' + dscnt + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=5 id="multiplier' + itemsCount + '" style="width:85px;margin-right:20px;" disabled value="' + multiplier + '";/>';

        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:5px;" onclick="deleteItem(' + itemsCount + ');"/></div>';

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
    else  // Add item button clicked from Quote Edit from poup view
    {


        sNo = parseInt($("#popupSNo").val()) + 1;

        itemsCount = parseInt($("#items_count").val()) + 1;

        $("#items_count").val(itemsCount);

        $("#popupSNo").val(sNo);


        itemDet += '<div id="popup_item' + itemsCount + '" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;">';

        itemDet += '<input type="text" autocomplete="off" class="textbox" id="sno' + itemsCount + '" style="width:30px;margin-right:20px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="popup_item_desc' + itemsCount + '" style="width:165px;margin-right:20px;" disabled  value="' + itemDesc + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=80 id="popup_part_no' + itemsCount + '" style="width:90px;margin-right:20px;" disabled value="' + partNo + '";/>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="popup_quantity' + itemsCount + '" style="width:90px;" disabled value="' + quantity + '";/>';
        itemDet += '<div class="quantity_unit" id="popup_quantity_unit' + itemsCount + '" >' + quantityUnit + '</div>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" id="popup_ship_date' + itemsCount + '" style="width:80px;margin-right:20px;" disabled value="' + shipDate + '";/>';

        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=7 id="popup_price' + itemsCount + '" style="width:60px;" disabled value="' + priceperunit + '";/>';
        itemDet += '<div class="quantity_unit" id="popup_currency' + itemsCount + '" >' + currency + '</div>';
        itemDet += '<input type="text" autocomplete="off" class="textbox" maxlength=5 id="popup_multiplier' + itemsCount + '" style="width:65px;margin-right:20px;" disabled value="' + multiplier + '";/>';

        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:20px;" onclick="deletePopupItem(' + itemsCount + ');"/></div>';

       // var totalListPrice = parseFloat($("#popup_tot_list_price_amt").text());

       // totalListPrice = totalListPrice + (quantity * priceperunit * multiplier);

       // $("#popup_tot_list_price_amt").text(totalListPrice);

      //  var tax = parseFloat($("#popup_tax_amt").text());

      //  var totPrice = calculateTotPrice(totalListPrice, tax);

       // $("#popup_tot_price_amt").text(totPrice);

        $("#popup_items").append(itemDet);
    }


}




/* This method is used to delete the item from Quote form */
function deleteItem(itemsCount)
{
    var priceperunit = $("#price" + itemsCount).val();

    var quantity = $("#quantity" + itemsCount).val();

    var multiplier = parseFloat($("#multiplier" + itemsCount).val());

    var totalListPrice = parseFloat($("#tot_list_price_amt").text());

    //totalListPrice = totalListPrice - (quantity * priceperunit * multiplier);
    totalListPrice = totalListPrice - multiplier;

    $("#tot_list_price_amt").text(totalListPrice);

    var tax = $("#qt_tx").val();

    var totPrice = calculateTotPrice(totalListPrice, tax);

    $("#tot_price_amt").text(totPrice);

    $("#item" + itemsCount).toggle();

    $("#item" + itemsCount).remove();

    sNo = sNo - 1;
}


function validateQuote()
{
    var regnKey = $("#regnkey").val();

    var userKey = $("#EmailAddress").val();

    var toRegnKey = $("#selected_ven_key").val();
    
    var toUserKey = $("#selected_ven_id").val();

    var totalListPrice = $("#tot_list_price_amt").text();

    var tax = $("#qt_tx").val();

    var totalPrice = $("#tot_price_amt").text();

    var transId = $("#form_trans_id").val();

    var quoteRef = $("#quote_ref").val();

    //alert("listprice="+totalListPrice+"Tax="+tax+"totalPrice="+totalPrice);




    quoteObj.fromRegnKey = regnKey;
    quoteObj.fromUserKey = userKey;
    quoteObj.toRegnKey = toRegnKey;
    quoteObj.toUserKey = toUserKey;
    quoteObj.totalListPrice = totalListPrice;
    quoteObj.taxPercentage = tax;
    quoteObj.totalPrice = totalPrice;
    quoteObj.transId = transId;
    quoteObj.quoteRef = quoteRef;
    quoteObj.action = "CreateQuote";


    if ($('#outside_supplier').is(":checked"))
    {
        quoteObj.isOutsideSupplier = 1;
        quoteObj.outsideSuppliername = $("#otsd_splr_nm").val();
        quoteObj.outsideSupplierEmail = $("#email").val();
        quoteObj.outsideSuppliercountry = $("#countryregion_0  option:selected").val();
        quoteObj.outsideSupplierstate = $("#state_text").val();
        quoteObj.outsideSuppliercity = $("#otsd_splr_cty").val();
        quoteObj.outsideSupplieraddress = $("#otsd_splr_adrs").val();
        quoteObj.outsideSupplierzipcode = $("#otsd_splr_zpcd").val();
        if (quoteObj.outsideSuppliercountry==="United States")
		{
			
			quoteObj.outsideSupplierstate=$("#state_0  option:selected").val();
			
		}
		else
		{	
			
			quoteObj.outsideSupplierstate=$("#state_text").val();
			
		}

        if (quoteObj.outsideSupplierEmail == "")
        {
            $("#quote_form_error").text("Outside Buyer E-Mail field is empty");

            $("#email").focus();

            return;
        }
    }
    else
    {
        quoteObj.isOutsideSupplier = 0;

        if ($("#to_company").val() == "" || toRegnKey == "")
        {
            $("#quote_form_error").text("Select the Buyer to send the Quote Form.");

            $("#to_company").focus();

            return;
        }
        else if (!$('#quote_terms_cond').is(":checked"))
        {
            $("#quote_form_error").text("Accept Terms and Conditions to proceed.");

            $("#quote_terms_cond").focus();

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
                $("#quote_form_error").text("Enter all the required field values");

                return;
            }

            item.partNo = $("#part_no" + i).val().trim();
            if (item.partNo == "")
            {
                $("#quote_form_error").text("Enter all the required field values");

                return;
            }

            item.brcd = $("#brcd" + i).val().trim();
            if (item.brcd == "")
            {
                $("#quote_form_error").text("Enter all the required field values");

                return;
            }

            item.quantity = $("#quantity" + i).val().trim();
            if (item.quantity == "")
            {
                $("#quote_form_error").text("Enter all the required field values");
                return;
            }

            else if (!isNumber(item.quantity))
            {
                $("#quote_form_error").text("Please use only numbers and periods");

                return;
            }

            item.quantityUnit = $("#quantity_unit" + i).text().trim();
            if (item.quantityUnit == "")
            {
                $("#quote_form_error").text("Enter all the required field values");
                return;
            }

            item.shipDate = $("#ship_date" + i).val();
            if (item.shipDate == "")
            {
                $("#quote_form_error").text("Enter all the required field values");
                return;
            }

            item.price = $("#price" + i).val().trim();
            if (item.price == "")
            {
                $("#quote_form_error").text("Enter all the required field values");
                return;
            }

            else if (!isNumber(item.price))
            {
                $("#quote_form_error").text("Please use only numbers and periods ");

                return;
            }

            item.currency = $("#currency" + i).text().trim();
            if (item.currency == "")
            {
                $("#quote_form_error").text("Enter all the required field values");
                return;
            }

            item.multiplier = $("#multiplier" + i).val().trim();
            if (item.multiplier == "")
            {
                $("#quote_form_error").text("Enter all the required field values");
                return;
            }

            else if (!isNumber(item.multiplier))
            {
                $("#quote_form_error").text("Please use only numbers");

                return;
            }


            items.push(item);
        }
    }


    quoteObj.items = items;




    if (items.length == 0)
    {

        $("#quote_form_error").text("Add at least one item to proceed.");

        return;
    }

    if ($('#outside_supplier').is(":checked"))
    {
        deleteConfirm();
        //showWarning("Do you want to send the SupplyMedium invitation E-Mail to an \"Outside supplier\"?");
    }
    else
    {
        quoteObj.outsideSupplierMailFlag = 0;

        sendQuote();
    }
}


function deleteConfirm()
{
    quoteObj.outsideSupplierMailFlag = 1;

    sendQuote();
}

function cancelInfo()
{

    quoteObj.outsideSupplierMailFlag = 1;

    sendQuote();
}



/* This method is used to send the new Quote form to selected supplier
 * from registered supplier list.
 */
function sendQuote()
{
   
   $("#add_quote_btn").attr("disabled", "disabled");
    var quoteData = JSON.stringify(quoteObj);

    showAjaxLoader();
   
    $.ajax({
        type: "POST",
        url: getBaseURL() + "/QuoteServlet",
        dataType: 'json',
        data: {
            'RequestType': "QuoteCreation",
            'Quote': quoteData,
        },
        success: function(resJSON)
        {
            hideAjaxLoader();

            if (resJSON.result === "success")
            {
                resetQuoteForm();
                $("#add_quote_btn").removeAttr("disabled"); 
                ShowToast(resJSON.message, 2000);

                fetchQuoteRequest();
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


/* This method is used to reset the Quote from details */
function resetQuoteForm()
{
    $("#to_company").val("");

    $("#outside_supplier").attr("checked", false);

    $("#buyer_country").val("");

    $("#buyer_state").val("");

    $("#buyer_city").val("");

    $("#buyer_addr").val("");

    $("#buyer_zipcode").val("");

    $("#email").val("");

    $("#quote_terms_cond").attr("checked", false);

    $("#items").empty();

    $("#quote_form_error").text("");

    $("#selected_ven_key").val("");

    $("#tot_list_price_amt").text("0");

    $("#tot_price_amt").text("0");

    $("#quote_ref").val("");
}

/* this method is used to reset the error label */
function resetErrorLbl()
{
    $("#quote_form_error").text("");

}


///* this method is used to calculate the total price */
//function calculateTotPrice(totListPrice, tax)
//{
//    var taxAmt = 0;
//
//    var totPrice = 0;
//
//    taxAmt = (totListPrice * tax) / 100;
//
//    totPrice = totListPrice + taxAmt;
//
//    return totPrice;
//}
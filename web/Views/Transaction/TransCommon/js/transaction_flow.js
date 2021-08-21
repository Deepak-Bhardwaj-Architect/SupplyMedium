/* This method is called when user click the Generate Quote link from RFQ Table.
 * This method get the RFQ Data and fill the Quote form with the RFQ Data.
 */
function showQuoteFromRFQ(aData)
{

    $("#trans_content").empty();

    $('#trans_content').load('Views/Transaction/Quote/jsp/quote.jsp', function()
    {

        $("#rfq_tab").removeClass("main_tab_select");
        $("#rfq_tab").addClass("main_tab_unselect");

        $("#quote_tab").removeClass("main_tab_unselect");
        $("#quote_tab").addClass("main_tab_select");

        $("#po_tab").removeClass("main_tab_select");
        $("#po_tab").addClass("main_tab_unselect");

        $("#invoice_tab").removeClass("main_tab_select");
        $("#invoice_tab").addClass("main_tab_unselect");

        quoteFormTabClicked();

        fillQuoteForm(aData);
    });
}

/* This method is used to fill the quote the using the RFQ Details */

function fillQuoteForm(aData)
{

    var RFQId = aData[1];
    var RFQDate = aData[2];
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
    var rfqitems = aData[26];
    var transaction = aData[27];
    var inquires = aData[28];
    var transId = aData[29];


    var fromStateArr = fromState.split("<");

    if (fromStateArr.length > 0)
        fromState = fromStateArr[0];

    $("#to_company").val(fromCompName);

    $("#selected_ven_key").val(fromRegnKey);

    // Used to fetch the terms and condition for selected company
    getTC(fromRegnKey, "Quote");

    $("#supplier_name").val(toCompName);

    $("#supplier_country").val(toCountry);

    $("#supplier_state").val(toState);

    $("#supplier_city").val(toCity);

    $("#supplier_addr").val(toAddress);

    $("#supplier_zipcode").val(toZipcode);

    $("#buyer_name").val(fromCompName);

    $("#buyer_country").val(fromCountry);

    $("#buyer_state").val(fromState);

    $("#buyer_city").val(fromCity);

    $("#buyer_addr").val(fromAddress);

    $("#buyer_zipcode").val(fromZipcode);

    $("#form_trans_id").val(transId);

    $("#quote_ref").val(quotationRef);

    for (var i = 0; i < rfqitems.length; i++)
    {
        sNo = sNo + 1;

        itemsCount = itemsCount + 1;

        var item = rfqitems[i];
        //alert(item.barcode_id);
        var itemDet = "";


        itemDet += '<div id="item' + itemsCount + '" class="item" style="width:1000px;float:left;margin-left:10px;margin-right:20px;">';

        itemDet += '<input type="text" class="textbox" id="sno' + itemsCount + '" style="width:30px;margin-right:20px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" class="textbox"  maxlength=80 id="item_desc' + itemsCount + '" style="width:100px;margin-right:20px;" disabled  value="' + item.itemDesc + '";/>';
        itemDet += '<input type="text" class="textbox"  maxlength=80 id="part_no' + itemsCount + '" style="width:90px;margin-right:20px;" disabled value="' + item.partNo + '";/>';
        itemDet += '<input type="text" class="textbox"  maxlength=80 id="brcd' + itemsCount + '" style="width:60px;" disabled value="' + item.barcode_id + '";/>';
        itemDet += '<input type="text" class="textbox"  maxlength=7 id="quantity' + itemsCount + '" style="width:60px;"  value="' + item.quantity + '"; onkeyup="claculate_invoice(' + itemsCount + ');"/>';
        itemDet += '<div class="quantity_unit" id="quantity_unit' + itemsCount + '" >' + item.quantityUnit + '</div>';
        itemDet += '<div class="quan_units" id="units_quantity_unit' + itemsCount + '" style="display:none;left:452px;"></div>';

        itemDet += '<input type="text" class="textbox" id="ship_date' + itemsCount + '" style="width:85px;margin-right:20px;" value="' + item.shipDate + '" readonly/>';


        itemDet += '<input type="text"  maxlength=7 class="textbox" id="price' + itemsCount + '" style="width:60px;" onkeyup="claculate_invoice(' + itemsCount + ');"/>';
        itemDet += '<div class="quantity_unit" id="currency' + itemsCount + '" >' + "USD" + '</div>';
        itemDet += '<div class="currency_list" id="currency_currency' + itemsCount + '" style="display:none;left:689px;" ></div>';

        itemDet += '<input type="text" class="textbox"  maxlength=7 value="1" id="dscnt' + itemsCount + '" style="width:35px;margin-right:20px;" onkeyup="claculate_invoice(' + itemsCount + ');" />';
        itemDet += '<input type="text" class="textbox"  maxlength=7 id="multiplier' + itemsCount + '" style="width:85px;margin-right:20px;" disabled />';

        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:20px;" onclick="deleteItem(' + itemsCount + ');"/></div>';

        $("#items").append(itemDet);


        $("#quantity_unit" + itemsCount).click(showQuantityUnits);
        $("#currency" + itemsCount).click(showCurrencyList);

        $("#ship_date" + itemsCount).datepicker({dateFormat: "dd-M-yy"});



        var totalListPrice = parseFloat($("#tot_list_price_amt").text());

        totalListPrice = totalListPrice + "";

        $("#tot_list_price_amt").text(totalListPrice);

        var tax = parseFloat($("#tax_amt").text());

        var totPrice = calculateTotPrice(totalListPrice, tax);

        $("#tot_price_amt").text(totPrice);
    }

}

function claculate_invoice(val)
{
    var unts=$("#quantity"+val).val();
    var prc=$("#price"+val).val();
    var dscnt=$("#dscnt"+val).val();
    var tl=unts*prc*dscnt;
    if(tl.toString()==="NaN")
    {
      $("#multiplier"+val).val(0);   
    }
    else
    {    
    $("#multiplier"+val).val(tl);   
    }
}

/* This method is called when user click the Generate PO link from Quote Table.
 * This method get the Quote Data and fill the PO form with the Quote Data.
 */
function showPOFromQuote(aData)
{
    $("#trans_content").empty();

    $('#trans_content').load('Views/Transaction/PO/jsp/purchase_order.jsp', function()
    {

        $("#rfq_tab").removeClass("main_tab_select");
        $("#rfq_tab").addClass("main_tab_unselect");

        $("#quote_tab").removeClass("main_tab_select");
        $("#quote_tab").addClass("main_tab_unselect");

        $("#po_tab").removeClass("main_tab_unselect");
        $("#po_tab").addClass("main_tab_select");

        $("#invoice_tab").removeClass("main_tab_select");
        $("#invoice_tab").addClass("main_tab_unselect");

        poFormTabClicked();

        fillPOForm(aData);
    });
}

/* This method is used to fill the PO the using the Quote Details */

function fillPOForm(aData)
{
    //alert(aData[3]);
    var QuoteId = aData[1];
    var QuoteDate = aData[2];
    var fromCompName = aData[4];
    var fromRegnKey = aData[5];
    var fromUserKey = aData[6];
    var fromState = aData[7];
    var status = aData[8];
    //alert(aData[11]);
    var fromCountry = aData[11];
    var fromCity = aData[12];
    var fromAddress = aData[13];

    var fromZipcode = aData[14];
    var toRegnKey = aData[15];
    var toUserKey = aData[16];
    var toCompName = aData[17];
    var toCountry = aData[18];
    var toState = aData[19];
    var toCity = aData[20];
    var toAddress = aData[21];
    var toZipcode = aData[22];
    var isOutsideSupplier = aData[23];
    var outsideSupplierEmail = aData[24];
    var recurring = aData[25];
    var quotationRef = aData[26];
    var items = aData[27];
    var transaction = aData[28];
    var inquires = aData[29];
    var transId = aData[30];

    var totListPrice = aData[31];
    var tax = aData[32];
    var totPrice = aData[33];

    $("#to_company").val(fromCompName);

    $("#selected_ven_key").val(fromRegnKey);
    $("#selected_ven_user_key").val(fromUserKey);

    // Used to fetch the terms and condition for selected company
    getTC(fromRegnKey, "PO");

    $("#supplier_name").val(toCompName);

    $("#supplier_country").val(toCountry);

    $("#supplier_state").val(toState);

    $("#supplier_city").val(toCity);

    $("#supplier_addr").val(toAddress);

    $("#supplier_zipcode").val(toZipcode);



    $("#buyer_name").val(fromCompName);

    $("#buyer_country").val(fromCountry);

    $("#buyer_state").val(fromState);

    $("#buyer_city").val(fromCity);

    $("#buyer_addr").val(fromAddress);

    $("#buyer_zipcode").val(fromZipcode);

    $("#form_trans_id").val(transId);

    for (var i = 0; i < items.length; i++)
    {
        sNo = sNo + 1;

        itemsCount = itemsCount + 1;

        var item = items[i];

        var itemDet = "";

        itemDet += '<div id="item' + itemsCount + '" class="item" style="width:1030px;float:left;margin-left:10px;margin-right:20px;">';

        itemDet += '<input type="text" class="textbox" id="sno' + itemsCount + '" style="width:30px;margin-right:20px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" class="textbox" maxlength=80 id="item_desc' + itemsCount + '" style="width:100px;margin-right:20px;" disabled  value="' + item.itemDesc + '";/>';
        itemDet += '<input type="text" class="textbox" maxlength=80 id="part_no' + itemsCount + '" style="width:90px;margin-right:20px;" disabled value="' + item.partNo + '";/>';
        itemDet += '<input type="text" class="textbox" maxlength=80 id="brcd' + itemsCount + '" style="width:90px;margin-right:20px;" disabled value="' + item.barcode_id + '";/>';
        itemDet += '<input type="text" class="textbox" maxlength=7 id="quantity' + itemsCount + '" style="width:60px;"  value="' + item.quantity + '"; onkeyup="claculate_invoice(' + itemsCount + ');"/>';
        itemDet += '<div class="quantity_unit" id="quantity_unit' + itemsCount + '" >' + item.quantityUnit + '</div>';
        itemDet += '<div class="quan_units" id="units_quantity_unit' + itemsCount + '" style="display:none;left:452px;"></div>';


        itemDet += '<input type="text" class="textbox" id="ship_date' + itemsCount + '" style="width:85px;margin-right:20px;"  value="' + item.shipDate + '";/>';

        itemDet += '<input type="text" class="textbox" maxlength=7 id="price' + itemsCount + '" style="width:60px;"  value="' + item.price + '"; onkeyup="claculate_invoice(' + itemsCount + ');""/>';
        itemDet += '<div class="quantity_unit" id="currency' + itemsCount + '" >' + item.currency + '</div>';
        itemDet += '<div class="currency_list" id="currency_currency' + itemsCount + '" style="display:none;left:689px;" ></div>';

        itemDet += '<input type="text" class="textbox"  maxlength=7 value="1" id="dscnt' + itemsCount + '" style="width:50px;margin-right:20px;" onkeyup="claculate_invoice(' + itemsCount + ');" />';
        itemDet += '<input type="text" class="textbox" maxlength=7 id="multiplier' + itemsCount + '" style="width:85px;margin-right:20px;"  value="' + item.multiplier + '"; disabled />';

        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:20px;" onclick="deleteItem(' + itemsCount + ');"/></div>';

        $("#items").append(itemDet);

        $("#quantity_unit" + itemsCount).click(showQuantityUnits);

        $("#currency" + itemsCount).click(showCurrencyList);

        $("#ship_date" + itemsCount).datepicker({dateFormat: "dd-M-yy"});

        $("#tot_list_price_amt").text(totListPrice);

        $("#tot_price_amt").text(totPrice);
    }

}

function claculate_po(val)
{
    var unts=$("#quantity"+val).val();
    var prc=$("#price"+val).val();
    var dscnt=$("#dscnt"+val).val();
    var tl=unts*prc*dscnt;
    if(tl.toString()==="NaN")
    {
      $("#multiplier"+val).val(0);   
    }
    else
    {    
    $("#multiplier"+val).val(tl);   
    }
}

/* This method is called when user click the Generate Invocie link from PO Table.
 * This method get the PO Data and fill the Invoice form with the PO Data.
 */
function showInvocieFromPO(aData)
{
    $("#trans_content").empty();

    $('#trans_content').load('Views/Transaction/Invoice/jsp/invoice.jsp', function()
    {

        $("#rfq_tab").removeClass("main_tab_select");
        $("#rfq_tab").addClass("main_tab_unselect");

        $("#quote_tab").removeClass("main_tab_select");
        $("#quote_tab").addClass("main_tab_unselect");

        $("#po_tab").removeClass("main_tab_select");
        $("#po_tab").addClass("main_tab_unselect");

        $("#invoice_tab").removeClass("main_tab_unselect");
        $("#invoice_tab").addClass("main_tab_select");

        invoiceFormTabClicked();

        fillInvoiceForm(aData);
    });
}

/* This method is used to fill the PO the using the Quote Details */

function fillInvoiceForm(aData)
{

    var QuoteId = aData[1];
    var QuoteDate = aData[2];
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
    var toAddress = aData[10];
    var toZipcode = aData[21];
    var isOutsideSupplier = aData[22];
    var outsideSupplierEmail = aData[23];
    var recurring = aData[24];
    var poRef = aData[25];
    var items = aData[26];
    var transaction = aData[27];
    var inquires = aData[28];
    var transId = aData[29];

    var totListPrice = aData[30];
    var tax = aData[31];
    var totPrice = aData[32];

    $("#po_no").val(poRef);

    $("#to_company").val(fromCompName);

    $("#selected_ven_key").val(fromRegnKey);

    // Used to fetch the terms and condition for selected company
    getTC(fromRegnKey, "Invoice");

    $("#supplier_name").val(toCompName);

    $("#supplier_country").val(toCountry);

    $("#supplier_state").val(toState);

    $("#supplier_city").val(toCity);

    $("#supplier_addr").val(toAddress);

    $("#supplier_zipcode").val(toZipcode);



    $("#buyer_name").val(fromCompName);

    $("#buyer_country").val(fromCountry);

    $("#buyer_state").val(fromState);

    $("#buyer_city").val(fromCity);

    $("#buyer_addr").val(fromAddress);

    $("#buyer_zipcode").val(fromZipcode);

    $("#form_trans_id").val(transId);
  
    for (var i = 0; i < items.length; i++)
    { 
        var item = items[i];
        var mltplr=parseInt(item.multiplier)/parseInt(item.quantity)/parseInt(item.price);
        var itemDet = "";

        sNo = sNo + 1;

        itemsCount = itemsCount + 1;

        itemDet += '<div id="item' + itemsCount + '" class="item" style="width:970px;float:left;margin-left:10px;margin-right:20px;">';

        itemDet += '<input type="text" class="textbox" id="sno' + itemsCount + '" style="width:30px;margin-right:10px;" disabled value="' + sNo + '";/>';

        itemDet += '<input type="text" class="textbox" maxlength=80 id="item_desc' + itemsCount + '" style="width:100px;margin-right:10px;" disabled  value="' + item.itemDesc + '";/>';
        itemDet += '<input type="text" class="textbox" maxlength=80 id="part_no' + itemsCount + '" style="width:90px;margin-right:10px;" disabled value="' + item.partNo + '";/>';
        itemDet += '<input type="text" class="textbox" maxlength=80 id="brcd' + itemsCount + '" style="width:90px;margin-right:10px;" disabled value="' + item.barcode_id + '";/>';
        itemDet += '<input type="text" class="textbox" maxlength=7 id="quantity_ordered' + itemsCount + '" style="width:60px;"  value="' + item.quantity + '"; disabled="" onblur="calculateInvoiceTotal()"/>';
        itemDet += '<div class="quantity_unit" id="quantity_ordered_unit' + itemsCount + '" style="margin-right:10px;">' + item.quantityUnit + '</div>';
        itemDet += '<div class="quan_units" id="units_quantity_ordered_unit' + itemsCount + '" style="display:none;left:452px;"></div>';

        itemDet += '<input type="text" class="textbox" maxlength=7 id="quantity_shipped' + itemsCount + '" style="width:60px;" />';
        itemDet += '<div class="quantity_unit" id="quantity_shipped_unit' + itemsCount + '" style="margin-right:10px;">' + "KG" + '</div>';
        itemDet += '<div class="quan_units" id="units_quantity_shipped_unit' + itemsCount + '" style="display:none;left:574px;"></div>';

        itemDet += '<input type="text" class="textbox" maxlength=7 id="price' + itemsCount + '" style="width:60px;" disabled=""  value="' + item.price + '"; onblur="calculateInvoiceTotal()"/>';
        itemDet += '<div class="quantity_unit" id="currency' + itemsCount + '" >' + item.currency + '</div>';
        itemDet += '<div class="currency_list" id="currency_currency' + itemsCount + '" style="display:none;left:696px;" ></div>';
        itemDet += '<input type="text" class="textbox" maxlength="7" id="dscnt' + itemsCount + '" style="width:50px;margin-right:10px;" disabled="" value="' + mltplr + '";>';
        itemDet += '<input type="text" class="textbox" maxlength="7" id="multiplier' + itemsCount + '" style="width:100px;margin-right:10px;" disabled="" value="' + item.multiplier + '";>'; 
        itemDet += '<input type="button" class="del_btn" id="del_btn_' + itemsCount + '" style="width:110px;margin-right:20px;" onclick="deleteItem(' + itemsCount + ');"/></div>';

        $("#items").append(itemDet);

        $("#quantity_shipped_unit" + itemsCount).click(showQuantityUnits);

        $("#quantity_ordered_unit" + itemsCount).click(showQuantityUnits);

        $("#currency" + itemsCount).click(showCurrencyList);

        $("#ship_date" + itemsCount).datepicker({dateFormat: "dd-M-yy"});


        $("#tot_list_price_amt").text(totListPrice);

        $("#tot_price_amt").text(totPrice);
    }

}

function calculateTotal()
{
    var totalListPrice = 0.0;

    for (var i = 1; i <= itemsCount; i++)
    {
        if (isNaN(parseFloat($("#price" + i).val())))
            continue;

        totalListPrice += parseFloat($("#price" + i).val() * $("#multiplier" + i).val() * $("#quantity" + i).val());
    }


    $("#tot_list_price_amt").text(totalListPrice);

    var tax = parseFloat($("#tax_amt").text());

    var totPrice = calculateTotPrice(totalListPrice, tax);

    $("#tot_price_amt").text(totPrice);
}


function calculateInvoiceTotal()
{
    var totalListPrice = 0.0;

    for (var i = 1; i <= itemsCount; i++)
    {
        if (isNaN(parseFloat($("#price" + i).val())))
            continue;

        totalListPrice += parseFloat($("#price" + i).val() * $("#quantity_ordered" + i).val());
    }


    $("#tot_list_price_amt").text(totalListPrice);

    var tax = parseFloat($("#tax_amt").text());

    var totPrice = calculateTotPrice(totalListPrice, tax);

    $("#tot_price_amt").text(totPrice);
}


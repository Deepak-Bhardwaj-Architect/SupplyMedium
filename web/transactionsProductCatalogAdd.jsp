<%@page import="supply.medium.home.database.CurrencyMaster"%>
<%@page import="supply.medium.home.bean.CurrencyBean"%>
<%@page import="supply.medium.home.database.QuantityTypeMaster"%>
<%@page import="supply.medium.home.bean.QuantityTypeBean"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0048)/SupplyMedium/user_home.jsp -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="inside/jquery-ui-1.9.2.custom_spinner.css">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/jquery.mCustomScrollbar.css">
        <link rel="stylesheet" href="inside/user_home.css">
        <link rel="stylesheet" href="inside/productcatalog.css">
        <link rel="stylesheet" href="inside/productcatalogpopup.css">
        <link rel="stylesheet" href="inside/dilbag.css">
        <!-- Chat JS style -->
        <link rel="stylesheet" type="text/css" href="inside/jquery.chatjs.css">
        <script type="text/JavaScript" src="inside/SMNamespace.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.9.2.custom_spinner.js">
        </script>
        <script type="text/JavaScript" src="inside/filterlist.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.customSelect.js">
        </script>
        <script type="text/javascript" src="inside/jquery.mCustomScrollbar.js">
        </script>
        <script src="inside/jquery.mousewheel.min.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.dataTables.js">
        </script>
        <script type="text/JavaScript" src="inside/common.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.validate.js">
        </script>
        <script type="text/JavaScript" src="inside/additional-methods.js">
        </script>
        <script type="text/JavaScript" src="inside/dropdownfiller.js">
        </script>
        <script type="text/JavaScript" src="inside/SMNamespace(1).js">
        </script>
        <script type="text/JavaScript" src="inside/footer.js">
        </script>
        <script type="text/JavaScript" src="inside/ajax_loader.js">
        </script>
        <!-- ChatJS and dependencies -->
        <script src="inside/jquery.chatjs.longpollingadapter.js" type="text/javascript">
        </script>
        <script src="inside/jquery.autosize.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.activity-indicator-1.0.0.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.chatjs.js" type="text/javascript">
        </script>
        <script type="text/JavaScript" src="inside/user_home.js">
        </script>
        <script type="text/JavaScript" src="inside/dilbag.js">
        </script>
        <title>Supply Medium</title>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
    </head>
    <body onLoad="lockUnlock('webkrit_content_loader');">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="/SupplyMedium/Views/Corporate/css/productcatalog.css">
        <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="inside/tablestyle.css">
        <link rel="stylesheet" href="/SupplyMedium/Views/UserMgmt/css/usermgmt_popup.css">
        <title>Supply Medium</title>
        <div class="pagetitlecontainer">
            <div class="pagetitle">Product Catalog</div>
        </div>
        <div class="page">
            <div class="contentcontainer" style="min-height: 700px;">
                <div id="catalog_content" style="">
                    <div class="tabbar">
                        <div class="ProductCatalogError" id="ProductCatalogErrorID">
                        </div>
                        <div class="normal" id="catalog_details"><a class="white-reverse" href="transactionsProductCatalog.jsp">Catalog</a></div>
                        <div class="highlight" id="new_items"><a class="white" href="transactionsProductCatalogAdd.jsp">Add Item</a></div>
                        <div class="normal" id="add_csv_items"><a class="white-reverse" href="transactionsProductCatalogCSV.jsp">Add CSV File</a></div>
                    </div>
                    <div class="newCatalogcontent" id="newcatalog_content" style="display: block;margin-top:0;">
                        <div class="contenthead" id="content_head">Add Item</div>
                        <div class="contentdetail" id="content_detail" style="padding-top: 30px;">
                            <div class="row" style="margin-left: 25px;width: 100%">
                                <form name="frm_fl_nm" onsubmit="return addProductValidate();" action="addProductToCatalog2" method="post" encType="multipart/form-data" >
                                    <div class="label" style="font-size: 13px;width: 120px">
                                        Item Name

                                        <input type="text" autocomplete="off" id="item_name" name="itemName" style="width: 100px" class="textbox">
                                    </div>
                                    <div class="label" style="font-size: 13px;width: 130px;">
                                        Item Description
                                        <input type="text" autocomplete="off" id="item_description" name="itemDesc" style="width: 100px" class="textbox">
                                    </div>
                                    <div class="label" style="font-size: 13px;width: 90px;">
                                        Batch No
                                        <input type="text" autocomplete="off" id="item_part_no" name="partNo" style="width: 70px" class="textbox">
                                    </div>
                                    <div class="label" style="font-size: 13px;width: 90px;">
                                        SKU
                                        <input type="text" autocomplete="off" id="item_sku" name="SKUno" style="width: 70px" class="textbox">
                                    </div>
                                    <div class="label" style="font-size: 13px;width: 90px;">
                                        Barcode
                                        <input type="text" autocomplete="off" id="brcd" name="barcode" style="width: 70px" class="textbox">
                                    </div>
                                    <div class="label" style="font-size: 13px;width: 100px;position:relative;">
                                        Quantity
                                        <input type="text" autocomplete="off" id="item_quantity" name="quantity" style="width: 50px" class="textbox">
                                        <div class="currency" id="add_quantity_unit" onclick="$('#selectQuantityUnit').toggle();">PCS</div>
                                        <input type="hidden" value="PCS" id="selectedQuantityUnit" name="quantityKey"/>
                                        <div class="currency_list" style="display: none; left: 82px; top: 57px;" id="units_add_quantity_unit">
                                        </div>
                                    </div>
                                    <div class="label" style="width: 120px;font-size:13px;position:relative;">
                                        Price &nbsp;&nbsp;&nbsp;
                                        <input type="text" autocomplete="off" id="item_price" name="price" style="width: 70px" class="textbox">
                                        <div class="currency" id="add_Currency_unit" onclick="$('#selectCurrencyUnit').toggle();">USD</div>
                                        <input type="hidden" value="USD" id="selectedCurrencyUnit" name="currencyKey"/>
                                        <div class="currency_list" id="add_currency_add_currency" style="display: none; left: 82px; top: 57px;">
                                        </div>
                                    </div>
                                    <div class="label" style="font-size: 13px;width: 100px;">
                                        Tax
                                        <input type="text" autocomplete="off" id="item_tax" name="tax" style="width:80px" class="textbox">
                                    </div>
                                    <div class="label" style="font-size: 13px;width: 100px;">
                                        Product Image
                                        <input type="file" autocomplete="off" id="item_tax" name="itemPics" style="width:90px;height: 20px;margin-top: 3px;float: left;cursor: pointer !important;font-size: 12px;opacity: 1;" multiple>
                                    </div>
                                    <div class="label" style="font-size: 13px;width: 120px;display:none;">
                                        Hide Price
                                        <div class="checkContainer" style="float: none;margin-left: 22px;margin-top:5px;">
                                            <input type="checkbox" class="checkbox" id="HidePrice">
                                            <div>
                                            </div>
                                        </div>
                                    </div>
                                    <input id="AddItemCatalogBtn" style="margin:50px 56px 71px 430px;" type="submit" value="Add" class="general-button gen-btn-bluewhite">
                                </form>
                            </div>
                            <div id="selectQuantityUnit" style="position: absolute;margin-left:607px;margin-top:7px;padding:0px 2px; display: none; border:1px solid #000; z-index: 99999;width:50px;height:80px;overflow-y:scroll;">
                                <%                                ArrayList categoryList = QuantityTypeMaster.showAll();
                                    QuantityTypeBean qtb = null;
                                    for (int i = 0; i < categoryList.size(); i++) {
                                        qtb = (QuantityTypeBean) categoryList.get(i);
                                        out.print("<span style='cursor:pointer;' onclick=\"javascript:$('#selectedQuantityUnit').val('" + qtb.getQuantityCode() + "');$('#add_quantity_unit').html('" + qtb.getQuantityCode() + "');$('#selectQuantityUnit').fadeOut();\">" + qtb.getQuantityCode() + "</span></br>");

                                    }
                                %>
                            </div>
                            <div id="selectCurrencyUnit" style="position: absolute;margin-left:727px;margin-top:7px;padding:0px 2px; display: none; border:1px solid #000; z-index: 99999;width:50px;height:80px;overflow-y:scroll;">
                                <%
                                    ArrayList currencyList = CurrencyMaster.showAll();
                                    CurrencyBean cb = null;
                                    for (int i = 0; i < currencyList.size(); i++) {
                                        cb = (CurrencyBean) currencyList.get(i);
                                        out.print("<span style='cursor:pointer;' onclick=\"javascript:$('#selectedCurrencyUnit').val('" + cb.getCurrencyCode() + "');$('#add_Currency_unit').html('" + cb.getCurrencyCode() + "');$('#selectCurrencyUnit').fadeOut();\">" + cb.getCurrencyCode() + "</span></br>");

                                    }
                                %>
                            </div>         
                            <!--<hr style="">
                            <form action="AddProductToCatalog" name="ProductCatlogFormAddWindow" id="ProductCatlogFormAddWindow" method="post">
                                 <div id="TempAddedValue">
                                     <table id="TempValueHolderDiv">
                                         <tbody>
                                             <tr>
                                                 <th style="width: 40px">
                                                 </th>
                                                 <th style="font-size: 13px;width: 50px">
                                                     S.No
                                                 </th>
                                                 <th style="font-size: 13px;width: 100px">
                                                     Item Name
                                                 </th>
                                                 <th style="font-size: 13px;width: 130px;">
                                                     Item Description
                                                 </th>
                                                 <th style="font-size: 13px;width: 100px;">
                                                     Part Number
                                                 </th>
                                                 <th style="font-size: 13px;width: 100px;">
                                                     SKU
                                                 </th>
                                                 <th style="font-size: 13px;width: 100px;">
                                                     Barcode
                                                 </th>
                                                 <th style="font-size: 13px;width: 100px;">
                                                     Quantity
                                                 </th>
                                                 <th style="width: 100px;">
                                                     Price
                                                 </th>
                                                 <th style="font-size: 13px;width: 100px;">
                                                     Tax
                                                 </th>
                                                 <th style="font-size: 13px;width: 100px;">
                                                     Images
                                                 </th>
                                                 <th style="font-size: 13px;width: 100px;display:none">
                                                     Hide Price
                                                 </th>
                                             </tr>
                                         </tbody>
                                     </table>
                                 </div>
                                 <div style="margin-left: 350px; margin-top: 60px;margin-bottom:20px; float: left;">
                                     <input type="reset" value="Reset" class="gen-btn-Gray">
                                     <input type="submit" value="Save" style="margin-left: 30px;" class="gen-btn-Orange" onClick="SM.Corporate.ProductCatalog.SaveAllCatalogInfos();">
                                 </div>
                             </form>-->
                            <div id="newusererr" class="usermgmterr">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <title>Insert title here</title>
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/popup_warning.css">
        <div id="warning_container" style="display: none;">
            <div id="warning_popup">
                <div id="war_head">
                    <label id="war_head_title">Warning</label>
                </div>
                <div id="war_body">
                    <label id="war_message">
                    </label>
                    <div id="war_btns">
                        <input type="button" id="okbtn" style="float:left;margin-right:30px;" class="pop-button pop-button-White" value="Yes">
                        <input id="Popup_Cancel" style="float:left;" type="button" class="pop-button pop-button-White" value="No">
                    </div>
                </div>
            </div>
        </div>
        <title>Toast Window </title>
        <link rel="stylesheet" href="inside/Cus_Toast.css">
        <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
        <div id="Toast_Window" style="display:none;">
            <p class="Toast_Data">
            </p>
        </div>
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/RestCSS.css">
        <link rel="stylesheet" href="/SupplyMedium/Views/Corporate/css/productcatalogpopup.css">
        <div style="display: none;" id="ProdCatalogEdit" class="Custome_Popup_Window">
            <table>
                <tbody>
                    <tr>
                        <td style="vertical-align: middle;">
                            <div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout">
                                <div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
                                    Edit Item
                                    <div id="ProdCatalogEdit_Close" class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                    </div>
                                </div>
                                <div class="popupcontent">
                                    <form action="/SupplyMedium/post" id="ProductCatlogForm" novalidate="novalidate">
                                        <div class="div_row">
                                            <label class="popup_label" for="popup_item_name"> Item </label>
                                            <input id="popup_item_name" name="popup_item_name" type="text" autocomplete="off" class="catalog_textbox_popup textbox">
                                        </div>
                                        <div class="div_row">
                                            <label class="popup_label" for="popup_item_description"> Description </label>
                                            <input id="popup_item_description" name="popup_item_description" type="text" autocomplete="off" class="catalog_textbox_popup textbox">
                                        </div>
                                        <div class="div_row">
                                            <label class="popup_label" for="popup_item_part_no"> Part </label>
                                            <input id="popup_item_part_no" name="popup_item_part_no" type="text" autocomplete="off" class="catalog_textbox_popup textbox">
                                        </div>
                                        <div class="div_row">
                                            <label class="popup_label" for="popup_item_sku"> SKU </label>
                                            <input id="popup_item_sku" name="popup_item_sku" type="text" autocomplete="off" class="catalog_textbox_popup textbox">
                                        </div>
                                        <div class="div_row" style="position:relative;">
                                            <label class="popup_label" for="popup_item_quantity"> Quantity </label>
                                            <input id="popup_item_quantity" style="width: 146px;top: 223px" name="popup_item_quantity" type="text" autocomplete="off" class="catalog_textbox_popup textbox">
                                            <div class="currency" id="popup_quantity_unit">
                                            </div>
                                            <div class="currency_list" id="units_popup_quantity_unit" style="display:none;left:258px;">
                                            </div>
                                        </div>
                                        <div class="div_row" style="position:relative;">
                                            <label class="popup_label" for="popup_item_price"> Price </label>
                                            <input id="popup_item_price" style="width: 146px" name="popup_item_price" type="text" autocomplete="off" class="catalog_textbox_popup textbox">
                                            <div class="currency" id="popup_currency">
                                            </div>
                                            <div class="currency_list" id="popup_currency_popup_currency" style="display:none;left:258px;">
                                            </div>
                                        </div>
                                        <div class="div_row" style="margin-bottom:30px;">
                                            <label class="popup_label" for="popup_item_tax"> Tax </label>
                                            <input id="popup_item_tax" name="popup_item_tax" type="text" autocomplete="off" class="catalog_textbox_popup textbox">
                                        </div>
                                        <div class="div_row" style="display:none">
                                            <label class="popup_label" style="line-height: 18px"> Hide Price </label>
                                            <div class="checkContainer">
                                                <input type="checkbox" class="checkbox" id="popup_HidePrice" value="false">
                                                <div>
                                                </div>
                                            </div>
                                        </div>
                                        <input onClick="SM.Corporate.ProductCatalog.RevertInfo();" style="margin-top:-5px;margin-left:140px;" type="button" value="Revert" class="gen-btn-Gray">
                                        <input onClick="SM.Corporate.ProductCatalog.saveCatalogInfo();" style="margin-top:-5px;margin-left:20px;" type="button" value="Save" class="gen-btn-Orange">
                                    </form>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <input type="hidden" id="emailId">
        <input type="hidden" id="rowno">
        <%@include file="_footer.jsp"%>
        <div>
        </div>
    </div>
    <%@include file="_invite.jsp"%>
    <script type="text/JavaScript" src="inside/restrict_menu.js">
    </script>
    <script>


                                            $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt_fieldvalidator.js");
                                            $.getScript("/SupplyMedium/Views/Registration/js/regvalidator.js");
                                            $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt.js", function(data, textStatus, jqxhr) {
                                                userOnload();

                                            });

    </script>
</body>
</html>
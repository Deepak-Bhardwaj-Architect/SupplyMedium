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
                        <div class="normal" id="new_items"><a class="white-reverse" href="transactionsProductCatalogAdd.jsp">Add Item</a></div>
                        <div class="highlight" id="add_csv_items"><a class="white" href="transactionsProductCatalogCSV.jsp">Add CSV File</a></div>
                    </div>
                    <div class="newCatalogImport" id="CatalogImport_content" style="display: block;margin-top:0;">
                        <div class="contenthead" id="content_head">Add CSV File</div>
                        <div class="contentdetail" id="content_detail" style="padding-top: 60px;">
                            <div class="ChooseLableCSV">Choose a CSV file to add items
                                in Bulk</div>
                            <form id="file_upload_form_1" name="file_upload_form_1" action="UploadProductFromCsv"
                                  method="post" enctype="multipart/form-data" >
                                <div style="margin:50px 0px 0px 300px;">
                                    <input id="catalog_import_text" type="text" readonly="readonly" />
                                    <div class="file_upload_container">
                                        <input id="catalog_import_upload_control"
                                               name="catalog_import_upload_control" class="file_upload_control"
                                               type="file" value="File Chossee" />
                                        <input type="button" style="margin-left: 10px;border-radius:0px;"
                                               id="file_upload_btn_1" class="general-button gen-btn-Orange"
                                               value="Browse" />

                                        <a href="inside/Product_catalog_template.csv" target="_blank" id="helpbtn" class="general-button gen-btn-Orange" style="height:17px;margin-left:10px;width:240px;" title="Click here to download the product catalog csv template.">Download Sample Template</a>
                                    </div>

                                    <input type="submit" style="margin-top: 20px;border-radius:0px;" class="general-button gen-btn-Orange" value="Upload" />
                                </div>
                            </form>
                            <div>
                                <div id="Upload_Result">
                                </div>
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
        <title>Insert title here</title>
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
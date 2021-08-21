<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />

        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/Views/Corporate/css/productcatalog.css" />
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/Views/Utils/css/tablestyle.css" />


        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/Views/UserMgmt/css/usermgmt_popup.css" />


        <title>Supply Medium</title>
    </head>



    <body onload="">

        <script type="text/JavaScript">
            $("#content_loader").hide();
        </script>

        <div class="pagetitlecontainer">
            <div class="pagetitle">Product Catalog</div>
        </div>
        <div class="page">
            <div class="contentcontainer" style="min-height: 700px;">
                <div id="catalog_content" style="display:none;">


                    <div class="tabbar">
                        <div class="ProductCatalogError" id="ProductCatalogErrorID"></div>
                        <div class="highlight" id="catalog_details">Catalog</div>

                        <div class="normal" id="new_items">Add Item</div>
                        <div class="normal" id="add_csv_items">Add CSV File</div>
                    </div>



                    <div class="productcatalogcontent" id="productcatalog_content">

                        <div class="tablecontent" id="table_content"
                             style="position: relative;">
                            <div class="DT_border"></div>
                            <table id="ProductCatalogList" style="width: 997px;">
                                <thead>
                                    <tr>
                                        <th class="rowBorder">Item</th>
                                        <th class="rowBorder">Description</th>
                                        <th class="rowBorder">Part Number</th>
                                        <th class="rowBorder">SKU</th>
                                        <th class="rowBorder">Barcode</th>
                                        <th class="rowBorder">Quantity</th>
                                        <th class="rowBorder">Price</th>
                                        <th class="rowBorder">Tax</th>						
                                        <th class="rowBorder">Option</th>
                                    </tr>
                                </thead>

                                <tbody>

                                </tbody>

                            </table>

                        </div>
                    </div>

                    <div class="newCatalogcontent" id="newcatalog_content">
                        <div class="contenthead" id="content_head">Add Item</div>

                        <div class="contentdetail" id="content_detail"
                             style="padding-top: 30px;">

                            <div class="row" style="margin-left: 25px;width: 100%">
                                <form action="post" id="ProductCatlogFormAddWindow">

                                    <div class="label" style="font-size: 13px;width: 120px">
                                        Item Name

                                        <input type="text" id="item_name" name="item_name" style="width: 100px" class="textbox" />
                                    </div>

                                    <div class="label" style="font-size: 13px;width: 130px;">
                                        Item Description
                                        <input type="text" id="item_description" name="item_description" style="width: 100px" class="textbox" />
                                    </div>

                                    <div class="label" style="font-size: 13px;width: 120px;">
                                        Part Number
                                        <input type="text" id="item_part_no" name="item_part_no" style="width: 100px" class="textbox" />
                                    </div>

                                    <div class="label" style="font-size: 13px;width: 120px;">
                                        SKU
                                        <input type="text" id="item_sku" name="item_sku" style="width: 100px" class="textbox" />
                                    </div>

                                    <div class="label" style="font-size: 13px;width: 120px;">
                                        Barcode
                                        <input type="text" id="brcd" name="brcd" style="width: 100px" class="textbox" />
                                    </div>

                                    <div class="label" style="font-size: 13px;width: 120px;position:relative;">
                                        Quantity
                                        <input type="text" id="item_quantity" name="item_quantity" style="width: 70px" class="textbox" />
                                        <div class="currency" id="add_quantity_unit" ></div>
                                        <div class="currency_list" style="display:none;left:82px;top:57px;"  id="units_add_quantity_unit"></div>
                                    </div>

                                    <div class="label" style="width: 120px;font-size:13px;position:relative;">
                                        Price &nbsp;&nbsp;&nbsp;
                                        <input type="text" id="item_price" name="item_price" style="width: 70px" class="textbox" />
                                        <div class="currency" id="add_currency" ></div>
                                        <div class="currency_list" id="add_currency_add_currency" style="display: none;left:82px;top:57px; "></div>
                                    </div>

                                    <div class="label" style="font-size: 13px;width: 120px;">
                                        Tax
                                        <input type="text" id="item_tax" name="item_tax" style="width: 100px" class="textbox" />
                                    </div>

                                    <div class="label" style="font-size: 13px;width: 120px;display:none;">
                                        Hide Price
                                        <div class="checkContainer" style="float: none;margin-left: 22px;margin-top:5px;">
                                            <input type="checkbox" class="checkbox" id="HidePrice">
                                            <div></div>
                                        </div>
                                    </div>

                                </form>
                            </div>	
                            <input id="AddItemCatalogBtn" onclick="SM.Corporate.ProductCatalog.AddCatalogInfo()" type="button" value="Add" class="general-button gen-btn-bluewhite" onclick="addUser();" /> 
                            <hr style="" />

                            <div id="TempAddedValue">						
                                <table id="TempValueHolderDiv">
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
                                        <th style="font-size: 13px;width: 100px;display:none">
                                            Hide Price
                                        </th>
                                    </tr>

                                </table>
                            </div>

                            <div style="margin-left: 350px; margin-top: 60px;margin-bottom:20px; float: left;">

                                <input type="button" value="Reset" class="gen-btn-Gray"
                                       onclick="SM.Corporate.ProductCatalog.ResetAddForm();" /> 
                                <input
                                    type="button" value="Save" style="margin-left: 30px;"
                                    class="gen-btn-Orange" onclick="SM.Corporate.ProductCatalog.SaveAllCatalogInfos();" /> 
                            </div>

                            <div id="newusererr" class="usermgmterr"></div>
                        </div>

                    </div>

                    <div class="newCatalogImport" id="CatalogImport_content">
                        <div class="contenthead" id="content_head">Add CSV File</div>

                        <div class="contentdetail" id="content_detail"
                             style="padding-top: 60px;">
                            <div class="ChooseLableCSV">Choose a CSV file to add items
                                in Bulk</div>
                            <div style="margin:50px 0px 0px 300px;">
                                <input id="catalog_import_text" type="text" readonly="readonly" />
                                <div class="file_upload_container">
                                    <form id="file_upload_form_1" name="file_upload_form_1"
                                          method="post" enctype="multipart/form-data"
                                          action="${pageContext.request.contextPath}/uploadsevlet" >
                                        <input id="catalog_import_upload_control"
                                               name="catalog_import_upload_control" class="file_upload_control"
                                               type="file" value="File Chossee" />
                                        <input type="button" style="margin-left: 10px;border-radius:0px;"
                                               id="file_upload_btn_1" class="general-button gen-btn-Orange"
                                               value="Browse" />

                                        <input type="button" id="helpbtn" class="general-button gen-btn-Orange" style="margin-left:10px;width:40px;" value="?" title="Click here to download the product catalog csv template."/>
                                    </form>
                                </div>

                                <input type="button" onclick="SM.Corporate.ProductCatalog.uploadtheCSV()"  style="margin-top: 20px;border-radius:0px;" class="general-button gen-btn-Orange" value="Upload" />
                            </div>

                            <div>
                                <div id="Upload_Result"></div>
                            </div>
                        </div>



                    </div>

                </div>
            </div>
        </div>

        <%@include file="../../Utils/jsp/Popup_Warning.jsp"%>
        <%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
        <%@include file="../../Utils/jsp/ajax_loader.jsp"%>
        <%@include file="productcatalog_edit.jsp"%>

        <%@include file="../../Utils/jsp/footer.jsp"%>


        <script>
            $.getScript("${pageContext.request.contextPath}/Views/Corporate/js/productcatalog_fieldvalidator.js", function(data, textStatus, jqxhr) {
            });
            $.getScript("${pageContext.request.contextPath}/Views/Corporate/js/productcatalog.js", function(data, textStatus, jqxhr) {
            });
            $.getScript("${pageContext.request.contextPath}/Views/Registration/js/regvalidator.js", function(data, textStatus, jqxhr) {
            });



        </script>







    </body>

</html>
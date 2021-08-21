<%@page import="supply.medium.home.database.CurrencyMaster"%>
<%@page import="supply.medium.home.database.QuantityTypeMaster"%>
<%@page import="supply.medium.home.bean.GlobalProductItemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="supply.medium.home.database.GlobalProductItemMaster"%>
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
    <body onLoad="lockUnlock('webkrit_content_loader');" onkeydown="displayunicode(event);">
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
                <div id="catalog_content">
                    <div class="tabbar">
                        <div class="ProductCatalogError" id="ProductCatalogErrorID">
                        </div>
                        <div class="highlight" id="catalog_details"><a class="white" href="transactionsProductCatalog.jsp">Catalog</a></div>
                        <div class="normal" id="new_items"><a class="white-reverse" href="transactionsProductCatalogAdd.jsp">Add Item</a></div>
                        <div class="normal" id="add_csv_items"><a class="white-reverse" href="transactionsProductCatalogCSV.jsp">Add CSV File</a></div>
                    </div>
                    <div class="productcatalogcontent" id="productcatalog_content" style="display: block;">
                        <div class="tablecontent" id="table_content" style="position: relative;">
                            <div class="DT_border">
                            </div>
                            <div id="ProductCatalogList_wrapper" class="dataTables_wrapper" role="grid">
<!--                                <div class="dataTables_filter" id="ProductCatalogList_filter">
                                    <label>
                                        <input type="text" autocomplete="off" aria-controls="ProductCatalogList">
                                    </label>
                                </div>-->
                                <div class="fixed_height">
                                    <table id="ProductCatalogList" style="width: 997px;" class="dataTable" aria-describedby="ProductCatalogList_info">
                                        <thead>
                                            <tr role="row">
                                                <th class="rowBorder sorting_asc" role="columnheader" tabindex="0" aria-controls="ProductCatalogList" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Item: activate to sort column descending" style="width: 200px;">Item</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="ProductCatalogList" rowspan="1" colspan="1" aria-label="Description: activate to sort column ascending" style="width: 250px;">Description</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="ProductCatalogList" rowspan="1" colspan="1" aria-label="Part Number: activate to sort column ascending" style="width: 150px;">Part Number</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="ProductCatalogList" rowspan="1" colspan="1" aria-label="SKU: activate to sort column ascending" style="width: 150px;">SKU</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="ProductCatalogList" rowspan="1" colspan="1" aria-label="Barcode: activate to sort column ascending" style="width: 100px;">Barcode</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="ProductCatalogList" rowspan="1" colspan="1" aria-label="Quantity: activate to sort column ascending" style="width: 100px;">Quantity</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="ProductCatalogList" rowspan="1" colspan="1" aria-label="Price: activate to sort column ascending" style="width: 80px;">Price</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="ProductCatalogList" rowspan="1" colspan="1" aria-label="Tax: activate to sort column ascending" style="width: 80px;">Tax</th>
                                                <th class="rowBorder sorting" role="columnheader" tabindex="0" aria-controls="ProductCatalogList" rowspan="1" colspan="1" aria-label="Option: activate to sort column ascending" style="width:129px;">Image</th>
                                            </tr>
                                        </thead>
                                        <tbody role="alert" aria-live="polite" aria-relevant="all">
                                            <%
                                            ArrayList al=GlobalProductItemMaster.showAllByCompanyKey(companyKey);
                                            GlobalProductItemBean gpib=null;
                                            String stl="";
                                            if(al.size()==0)
                                            {
                                            %>
                                            <tr class="odd">
                                                <td valign="top" colspan="9" class="dataTables_empty">No data available in table</td>
                                            </tr>
                                            <%
                                            }
                                            else for(int i=0;i<al.size();i++)
                                            {
                                                gpib=(GlobalProductItemBean)al.get(i);
                                                if(i%2==0)
                                                    stl="class='even'";
                                                else
                                                    stl="class='odd'";
                                            %>
                                            <tr <%=stl%> onclick="showProductDetails('<%=gpib.getItemKey()%>');">
                                                <td><%=gpib.getItemName()%></td>
                                                <td><%=gpib.getItemDescription()%></td>
                                                <td><%=gpib.getPartNo()%></td>
                                                <td><%=gpib.getSKUno()%></td>
                                                <td><%=gpib.getBarcode()%></td>
                                                <td align="right"><%=gpib.getQuantity()+" "+QuantityTypeMaster.showCodeByKey(gpib.getQuantityKey())%></td>
                                                <td align="right"><%=CurrencyMaster.showCodeByKey(gpib.getCurrencyKey())+" "+gpib.getPrice()%></td>
                                                <td align="right"><%=gpib.getTax()%></td>
                                                <td><% if(gpib.getPicsCount().equals("")){out.println(0);}else{out.println(gpib.getPicsCount().split("@#@").length);}%></td>                                                
                                            </tr>
                                            <%
                                            }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="dataTables_info" id="ProductCatalogList_info">Showing 0 to 0 of 0 entries</div>
                                <div class="dataTables_paginate paging_full_numbers" id="ProductCatalogList_paginate">
                                    <a tabindex="0" class="first paginate_button paginate_button_disabled" id="ProductCatalogList_first">First</a>
                                    <a tabindex="0" class="previous paginate_button paginate_button_disabled" id="ProductCatalogList_previous">Previous</a>
                                    <span>
                                    </span>
                                    <a tabindex="0" class="next paginate_button paginate_button_disabled" id="ProductCatalogList_next">Next</a>
                                    <a tabindex="0" class="last paginate_button paginate_button_disabled" id="ProductCatalogList_last">Last</a>
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
        
        <title>Insert title here</title>
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/RestCSS.css">
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
                                <div id="ShowProductDetailsByAjax">
                                    
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
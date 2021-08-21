<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Transaction/PO/css/purchase_order.css" />


    </head>

    <body>

        <div class="sub_tab_container" id="po_submenu">
            <!-- This is PO inner tab bar container -->

            <div class="highlight" id="po_request_tab">My Request</div>
            <!-- My Request tab -->

            <div class="normal" id="po_form_tab">Generate PO</div>
            <!-- PO Form tab -->
        </div>

        <div id="po_request_content" style="position:relative;"> <!-- This is the PO queue. -->
            <div class="DT_border" >
            </div>
            <table id="po_list" style="width: 997px;padding-left:1px;">		
                <thead >
                    <tr>
                        <th class="rowBorder" style="border-left:1px solid #c8c8c8"></th>
                        <th class="rowBorder">PO ID</th>
                        <th class="rowBorder">Date</th>
                        <th class="rowBorder">Company</th>
                        <th class="rowBorder">Phone</th>
                        <th class="rowBorder">Email</th>
                        <th class="rowBorder">State</th>
                        <th class="rowBorder">Status</th>
                        <th class="rowBorder" style="border-right:1px solid #c8c8c8">Open</th>
                    </tr>
                </thead>

                <tbody>

                </tbody>
            </table>
        </div>

        <div id="po_form_content" style="display:none;" class="po_form_content"> <!-- This is the PO form details -->
            <div class="to_comp">
                <label class="label" style="padding-left:20px;width:45px;color:white;line-height:25px;">To:</label>
                <input type="text"  placeholder="Search Registered Vendor"  id="to_company" style="width:920px;" class="textbox" onkeyup="getRegVendor();">
            </div>

            <input type="hidden" id="selected_ven_key"/>
            <input type="hidden" id="selected_ven_user_key"/>


            <div class="po_form" style="width: 1025px;">
                <div id="ven_search_result" class="com_search_result" style="display:none;">		
                </div>

                <input type="hidden" id="form_trans_id" value="-1"/>
                <div class="po_buyer_det">
                    <div class="sub_heading">Buyer Details</div>

                    <div class="trans_row" style="float:left;">
                        <label class="trans_label"> Supplier Name </label>
                        <input type="text" class="textbox_disable" id="buyer_name" disabled />
                    </div>

                    <div class="trans_row">
                        <label class="trans_label"> Country </label>
                        <input type="text" class="textbox_disable" id="buyer_country" disabled/>
                    </div>

                    <div class="trans_row">
                        <label class="trans_label"> State/Province </label>
                        <input type="text" class="textbox_disable" id="buyer_state" disabled/>
                    </div>

                    <div class="trans_row">
                        <label class="trans_label"> City </label>
                        <input type="text" class="textbox_disable" id="buyer_city" disabled/>
                    </div>

                    <div class="trans_row">
                        <label class="trans_label"> Address </label>
                        <input type="text" class="textbox_disable" id="buyer_addr" disabled/>
                    </div>

                    <div class="trans_row">
                        <label class="trans_label"> Zip Code/Postal Code </label>
                        <input type="text" class="textbox_disable" id="buyer_zipcode" disabled />
                    </div>

                    <!--  <div class="sub_heading" style="margin-top:20px;">Request For PO</div>
                    
                    <div class="trans_row">
                            <label class="trans_label"> PO Reference </label>
                            <input type="text" class="textbox" id="companyName" />
                    </div>-->


                </div>
                <div class="addr_sep"></div>
                <div class="po_supplier_det">
                    <div class="sub_heading">Supplier Details</div>

                    <div class="trans_row">
                        <div class="checkContainer"><input type="checkbox" class="checkbox" id="outside_supplier"><div></div></div>
                        <label for="outside_supplier" class="trans_label" style="line-height:19px;margin-left:5px;">Outside Supplier</label>
                    </div>

                    <div class="supplier_address" style="width:100%;height:300px;">

                        <div class="trans_row">
                            <label class="trans_label"> Country </label>
                            <input type="text" class="textbox_disable" id="supplier_country" disabled/>
                        </div>

                        <div class="trans_row">
                            <label class="trans_label"> State/Province </label>
                            <input type="text" class="textbox_disable" id="supplier_state" disabled/>
                        </div>

                        <div class="trans_row">
                            <label class="trans_label"> City </label>
                            <input type="text" class="textbox_disable" id="supplier_city" disabled/>
                        </div>

                        <div class="trans_row">
                            <label class="trans_label"> Address </label>
                            <input type="text" class="textbox_disable" id="supplier_addr" disabled/>
                        </div>

                        <div class="trans_row">
                            <label class="trans_label"> Zip Code/Postal code </label>
                            <input type="text" class="textbox_disable" id="supplier_zipcode" disabled/>
                        </div>

                    </div>

                    <div class="outside_sup_email_content" style="width:100%;height:300px;display:none">

                        <div class="trans_row">
                            <label class="trans_label"> Country </label>
                            <input type="text" class="textbox" id="otsd_splr_cntry"/>
                        </div>
                        <div class="trans_row">
                            <label class="trans_label"> State/Province </label>
                            <input type="text" class="textbox" id="otsd_splr_st"/>
                        </div>

                        <div class="trans_row">
                            <label class="trans_label"> City </label>
                            <input type="text" class="textbox" id="otsd_splr_cty"/>
                        </div>

                        <div class="trans_row">
                            <label class="trans_label"> Address </label>
                            <input type="text" class="textbox" id="otsd_splr_adrs"/>
                        </div>

                        <div class="trans_row">
                            <label class="trans_label"> Zip Code/Postal Code </label>
                            <input type="text" class="textbox" id="otsd_splr_zpcd"/>
                        </div>
                        <div class="trans_row">
                            <label class="trans_label"> Email </label>
                            <input type="text" class="textbox" id="email" />
                        </div>

                    </div>

                    <div class="trans_row" style="margin-top:50px;">
                        <label class="trans_label"> Recurring </label>
                        <select id="recurring"  class="selectbox" style="width:165px;">
                            <option value="weekly"> None </option>
                            <option value="weekly"> Weekly </option>
                            <option value="monthly"> Monthly </option>
                            <option value="annually"> Annually </option>
                        </select>
                    </div>
                </div>
                <div class="items_head" style="padding-left:15px;">
                    <label class="trans_label" style="width:38px;margin-right:22px;">S.No</label>
                    <label class="trans_label" style="width:117px;margin-right:13px;">Item Description</label>
                    <label class="trans_label" style="width:100px;margin-right:20px;">Part No/SKU No</label>
                    <label class="trans_label" style="width:100px;margin-right:20px;">Barcode</label>
                    <label class="trans_label" style="width:98px;margin-right:25px;">Quantity</label>
                    <label class="trans_label" style="width:118px;margin-right:-2px;">Receive Date</label>
                    <label class="trans_label" style="width:118px;margin-right:4px;">Price per unit</label>
                    <label class="trans_label" style="width:60px;margin-right:20px;">Multiplier</label>
                    <label class="trans_label" style="width:100px;margin-right:20px;">Total</label>
                </div>
                <div class="items" id="items">

                    <!-- <div id="item1" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;">
                           <input type="text" class="textbox" id="sno1" style="width:30px;margin-right:20px;"/>
                           <input type="text" class="textbox" id="item_desc1" style="width:175px;margin-right:20px;"/>
                           <input type="text" class="textbox" id="part_no1" style="width:90px;margin-right:20px;"/>
                           <input type="text" class="textbox" id="quantity1" style="width:90px;"/>
                           <div class="quantity_unit" id="quantity_unit1" >KG</div>
                           <input type="text" class="textbox" id="ship_date1" style="width:110px;margin-right:20px;"/>
                           <input type="button" class="del_btn" id="del_btn_1" style="width:110px;margin-right:20px;"/>
                   </div>
                   
                   <div id="item2" class="item" style="width:900px;float:left;margin-left:40px;margin-right:20px;">
                           <input type="text" class="textbox" id="sno2" style="width:30px;margin-right:20px;"/>
                           <input type="text" class="textbox" id="item_desc2" style="width:175px;margin-right:20px;"/>
                           <input type="text" class="textbox" id="part_no2" style="width:90px;margin-right:20px;"/>
                           <input type="text" class="textbox" id="quantity2" style="width:90px;margin-right:20px;"/>
                           <input type="text" class="textbox" id="ship_date2" style="width:110px;margin-right:20px;"/>
                           <input type="button" class="del_btn" id="del_btn_2" style="width:110px;margin-right:20px;"/>
                   </div>-->

                </div>

                <div style="width:100%;float:left;margin-left:40px;height:50px;">
                    <input id="add_item_btn" type="button" class="add_item general-button" value="Add Item"/>
                </div>

                <div class="price_det" id="price_det">
                    <div class="price_det_content">
                        <label class="price_det_lbl" id="tot_list_price_lbl"> Total List Price: </label>

                        <label class="price_det_lbl" id="tot_list_price_amt"> 0 </label>
                    </div>
                    <div class="price_det_content">
                        <label class="price_det_lbl" id="tax_lbl"> Tax in Percentage: </label>

                        <label class="price_det_lbl" id="tax_amt" style="min-width:35px;width:auto;"></label><label>%</label>
                    </div>
                    <div class="price_det_content">
                        <label class="price_det_lbl" id="tot_price_lbl"> Total Price: </label>

                        <label class="price_det_lbl" id="tot_price_amt" ></label>
                    </div>
                </div>


                <div style="width:100%;float:left;margin-left:40px;height:50px;" id="po_terms_container">
                    <div class="checkContainer"><input type="checkbox" class="checkbox" id="po_terms_cond"><div></div></div>
                    <label for="po_terms_cond" class="trans_label" style="line-height:19px;margin-left:5px;cursor:pointer;text-decoration:underline;" id="po_tc_link">Accept terms & conditions</label>
                </div>

                <div class="po_form_btns">
                    <input type="button" class="gen-btn-Gray" style="margin-right:50px;" value="Reset" onclick="resetPOForm()">
                    <input type="button" class="gen-btn-blue" value="Send" id="add_po_btn" onclick="validatePO();">
                </div>

                <div class="po_error" id="po_form_error"> 
                </div>

            </div>

        </div>

        <%@include file="po_add_item_popup.jsp"%>
        <%@include file="po_popup.jsp"%>
        <%@include file="po_inquire_popup.jsp"%>

        <script type="text/JavaScript"
        src="${pageContext.request.contextPath}/Views/Transaction/PO/js/po_DT.js"></script>

        <script type="text/JavaScript"
        src="${pageContext.request.contextPath}/Views/Transaction/PO/js/po_form.js"></script>

        <script type="text/JavaScript"
        src="${pageContext.request.contextPath}/Views/Transaction/PO/js/po_popup_form.js"></script>

        <script type="text/JavaScript"
        src="${pageContext.request.contextPath}/Views/Transaction/PO/js/po_address.js"></script>

        <script type="text/JavaScript"
        src="${pageContext.request.contextPath}/Views/Transaction/PO/js/po_search_vendor.js"></script>

        <script type="text/JavaScript"
        src="${pageContext.request.contextPath}/Views/Transaction/PO/js/purchase_order.js"></script>


    </body>
</html>
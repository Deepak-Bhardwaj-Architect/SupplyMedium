<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>


<script type="text/javascript">
$(function(){
	
	$(".Gen_Cus_Popup_Close").click(function()	
	{
		$(".Custome_Popup_Window").hide();
		
	});
	$("#invoice_add_item_popup_cancel").click(function()
	{
			$(".Custome_Popup_Window").hide();
			
	});
}	
);
</script>
<script>
    function mult()
    {
        var rt=document.getElementById("popup_price").value;
        if(rt==NaN)
            rt=0;
        var qt=document.getElementById("popup_quantity_ordered").value;
        if(qt==NaN)
            qt=0;
        var dscnt=document.getElementById("pop_dscnt").value;
        if(dscnt==NaN)
            dscnt=0;
        var mlt=rt*qt*dscnt;
        document.getElementById("pop_multiplier").value=mlt;
    }
    function tl_clcltn()
    {
      //alert('start');
        var tl_lst_prc=parseFloat($("#tot_list_price_amt").text());
        //alert($('#pop_multiplier').val()); 
        var mltpl=parseFloat($('#pop_multiplier').val());
        tl_lst_prc=tl_lst_prc+mltpl;
        //alert(tl_lst_prc);
       $("#tot_list_price_amt").text(tl_lst_prc);  
       $("#tot_price_amt").text(calculateTotPrice(tl_lst_prc,parseFloat($('#qt_tx').val())));
    }
</script>
</head>
<body>
<div style="display: none;z-index:2000;" class="Custome_Popup_Window"
		id="invoice_add_item_popup">
		<div class="Cus_Popup_Outline invoice_add_item_popup_outline" >
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Add
					Item</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>
			
			<input type="hidden" id="from_form" />
		
		<div style="margin-top:0px;margin-left:10px;float:left;position:relative;">
		
			<div class="items_head">
				<label class="trans_label" style="width:110px;margin-right:10px;">Item Description</label>
				<label class="trans_label" style="width:100px;margin-right:10px;">Part No/SKU No</label>
                                <label class="trans_label" style="width:100px;margin-right:10px;">Barcode</label>
				<label class="trans_label" style="width: 130px;margin-right:10px;">Quantity Ordered</label>
				<label class="trans_label" style="width:135px;margin-right:10px;">Quantity Shipped</label>
				<label class="trans_label" style="width:130px;margin-right:10px;">Price per unit</label>
                                <label class="trans_label" style="width:60px;margin-right:10px;">Multiplier</label>
                                <label class="trans_label" style="width:98px;margin-right:10px;">Total</label>
			</div>
			
			
			<div class="item" style="width: 975px;float:left;margin-left:10px;margin-right:0px;position:relative;">
			
				<input type="text" class="textbox" id="popup_item_desc"  maxlength=80 style="width:100px;margin-right:10px;">
				
                                <input type="text" class="textbox" id="popup_part_no"  maxlength=80 style="width:90px;margin-right:10px;" onblur="mx_mn_lst_sld(this.value);">
                                <input type="text" class="textbox" id="popup_brcd"  maxlength=80 style="width:90px;margin-right:10px;">
				
				<input type="text" onkeyup="mult()"  class="textbox" id="popup_quantity_ordered"  maxlength=7 style="width:90px;">
				
                                <div class="quantity_unit" id="quantity_ordered_unit" style="margin-right:10px;">KG</div>
				
				<div class="quan_units" id="units_quantity_ordered_unit" style="display: none;left: 442px;">
				</div>
				
				<input type="text" class="textbox" id="popup_quantity_shipped"  maxlength=7 style="width:90px;">
				
				<div class="quantity_unit" id="quantity_shipped_unit" style="margin-right:10px;">KG</div>
				
				<div class="quan_units" id="units_quantity_shipped_unit" style="display: none;left:579px;"></div>
				
				<input type="text" onkeyup="mult()" class="textbox" id="popup_price"  maxlength=5 style="width:90px;">
				
				<div class="currency" id="currency" style="margin-right:10px;">USD</div>
				
				<div class="currency_list" id="currency_currency" style="display: none;left:726px;">
                                    <div class="currency_name" id="currency_USD">USD</div>
                                    <div class="currency_name" id="currency_INR">INR</div>                                        
                                </div>
				<input type="text" class="textbox" value="1" id="pop_dscnt" maxlength=5 style="width:50px;margin-right:10px;" onkeyup="mult()"/>
				<input type="text" class="textbox" value="0" readonly id="pop_multiplier" maxlength=5 style="width:110px;margin-right:10px;"/>
			</div>
			
			<div class="row" style="margin-top:40px;float:left;margin-left: 173px;">
				 <input id="invoice_add_item_popup_cancel" style="margin-left:100px;margin-right: 50px" type="button" class="gen-btn-Gray" value="Cancel">
                                 <input id="save_item_btn" type="button" class="gen-btn-blue" value="Add" tabindex="8" onclick="tl_clcltn();">
			</div>
			
			<div class="invoice_error" id="invoice_item_form_error" style="width:820px;"> 
			</div>
			
			
		</div>

		</div>
		<a id="addone_lastHiddenElement" href="#" style="opacity: 0; " tabindex="9">test</a>

	</div>


</body>
</html>
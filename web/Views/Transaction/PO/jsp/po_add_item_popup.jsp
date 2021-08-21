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
	$("#po_add_item_popup_cancel").click(function()
		{
			$(".Custome_Popup_Window").hide();
			
		});
	
	$( "#popup_ship_date" ).datepicker({ dateFormat: "dd-M-yy",minDate: 0 });
}	
);


</script>
<script>
    function mult()
    {
        var rt=document.getElementById("popup_price").value;
        if(rt==NaN)
            rt=0;
        var qt=document.getElementById("popup_quantity").value;
        if(qt==NaN)
            qt=0;
        var dscnt=document.getElementById("pop_dscnt").value;
        if(dscnt==NaN)
            dscnt=0;
        var mlt=rt*qt*dscnt;
        document.getElementById("pop_multiplier").value=mlt;
    }
</script>
</head>
<body>
<div style="display: none;z-index:2000;" class="Custome_Popup_Window"
		id="po_add_item_popup">
    <div class="Cus_Popup_Outline po_add_item_popup_outline" style="width:1060px !important">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Add
					Item</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>
			
			<input type="hidden" id="from_form" />
		
		<div style="margin-top:0px;margin-left:10px;float:left;">
		
			<div class="items_head">
				<label class="trans_label" style="width:130px;margin-right:16px;">Item Description</label>
				<label class="trans_label" style="width:100px;margin-right:19px;">Part No/SKU No</label>
                                <label class="trans_label" style="width:100px;margin-right:19px;">Barcode</label>
				<label class="trans_label" style="width:100px;margin-right:53px;">Quantity</label>
				<label class="trans_label" style="width:105px;margin-right:25px;">Receive Date</label>
				<label class="trans_label" style="width:120px;margin-right:32px;">Price per unit</label>
				<label class="trans_label" style="width:60px;margin-right:20px;">Multiplier</label>
                                <label class="trans_label" style="width:118px;margin-right:20px;">Total</label>
			</div>
			
			
			<div class="item" style="width:1050px;float:left;margin-left:10px;margin-right:0px;position:relative;">
			
				<input type="text" class="textbox" id="popup_item_desc" maxlength=80 style="width:115px;margin-right:20px;"/>
				
				<input type="text" class="textbox" id="popup_part_no" maxlength=80  style="width:90px;margin-right:20px;"/>
                                
                                <input type="text" class="textbox" id="popup_brcd" maxlength=80  style="width:90px;margin-right:20px;"/>
				
				<input type="text" class="textbox" onkeyup="mult()" id="popup_quantity" maxlength=7 style="width:90px;"/>
				
				<div class="quantity_unit" id="quantity_unit" >KG</div>
				
				<div class="quan_units" id="units_quantity_unit" style="display:none;">
				</div>
				
				<input type="text" class="textbox" id="popup_ship_date" style="width:100px;margin-right:20px;" readonly/>
				
				<input type="text" class="textbox" onkeyup="mult()" id="popup_price"  maxlength=7 style="width:90px;"/>
				
				<div class="currency" id="currency" >USD</div>
				
				<div class="currency_list" id="currency_currency" style="display:none;">
				</div>
                                
                                <input type="text" class="textbox" id="pop_dscnt" maxlength=5 style="width:50px;margin-right:20px;" value="1" onkeyup="mult()"/>
				
				<input type="text" class="textbox" value="0" readonly id="pop_multiplier" maxlength=5 style="width:110px;margin-right:20px;"/>
				
				
			</div>
			
			<div class="row" style="margin-top:40px;float:left;margin-left:250px;">
				 <input id="po_add_item_popup_cancel" style="margin-left:100px;margin-right: 50px"
					type='button' class="gen-btn-Gray" value="Cancel"  />
					<input id="save_item_btn" type='button' 
					class="gen-btn-blue" value="Add" tabindex="8" />
			</div>
			
			<div class="po_error" id="po_item_form_error" style="width:970px;"> 
			</div>
			
			
		</div>

		</div>
		<a id="addone_lastHiddenElement" href="#" style="opacity: 0; " tabindex="9">test</a>

	</div>


</body>
</html>
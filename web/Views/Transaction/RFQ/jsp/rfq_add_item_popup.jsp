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
	$("#rfq_add_item_popup_cancel").click(function()
		{
			$(".Custome_Popup_Window").hide();
			
		});
	
	$( "#popup_ship_date" ).datepicker({
		dateFormat: "dd-M-yy",
		minDate: 0
		
		});
}	
);


</script>
</head>
<body>
<div style="display: none;z-index:2000;" class="Custome_Popup_Window"
		id="rfq_add_item_popup">
		<div class="Cus_Popup_Outline rfq_add_item_popup_outline" >
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Add
					Item</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>
			
			<input type="hidden" id="from_form" />
		
		<div style="margin-top:0px;margin-left:60px;float:left;position:relative;">
		
			<div class="items_head">
				<label class="trans_label" style="width:188px;margin-right:20px;">Item Description</label>
				<label class="trans_label" style="width:98px;margin-right:20px;">Part No/SKU No</label>
                                <label class="trans_label" style="width:98px;margin-right:20px;">Barcode</label>
				<label class="trans_label" style="width:98px;margin-right:52px;">Quantity</label>
				<label class="trans_label" style="width:118px;margin-right:20px;">Receive Date</label>
			</div>
			
			
			<div class="item" style="width:740px;float:left;margin-left:40px;margin-right:20px;position:relative;">
				<input type="text" class="textbox" id="popup_item_desc" maxlength=80 style="width:168px;margin-right:20px;"/>
				<input type="text" class="textbox" id="popup_part_no" maxlength=80 style="width:90px;margin-right:20px;"/>
                                <input type="text" class="textbox" id="popup_brcd" maxlength=80 style="width:90px;margin-right:20px;"/>
				<input type="text" class="textbox" id="popup_quantity" maxlength=7 style="width:90px;"/>
				<div class="quantity_unit" id="quantity_unit" >KG</div>
				<div class="quan_units" id="units_quantity_unit" style="display:none;left:540px;">
				</div>
				<input type="text" class="textbox" id="popup_ship_date" style="width:110px;margin-right:20px;" readonly/>
			</div>
			
			<div class="row" style="margin-top:40px;float:left;margin-left:100px;">
				 <input id="rfq_add_item_popup_cancel" style="margin-left:100px;margin-right: 50px"
					type='button' class="gen-btn-Gray" value="Cancel"  />
					<input id="save_item_btn" type='button' 
					class="gen-btn-blue" value="Add" tabindex="8" />
			</div>
			
			<div class="rfq_error" id="rfq_item_form_error" style="width:620px;"> 
			</div>
			
			
		</div>

		</div>
		<a id="addone_lastHiddenElement" href="#" style="opacity: 0; " tabindex="9">test</a>

	</div>


</body>
</html>
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
	$("#quote_add_item_popup_cancel").click(function()
		{
			$(".Custome_Popup_Window").hide();
			
		});
	
	$( "#popup_ship_date" ).datepicker({ 
		dateFormat: "dd-M-yy",minDate: 0 });
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
        var dscnt=document.getElementById("dscnt").value;
        if(dscnt==NaN)
            dscnt=1;
        var mlt=rt*qt*dscnt;
        if(mlt.toString()=="NaN")
        {   
        document.getElementById("pop_multiplier").value="0";
        }
        else
        {    
        document.getElementById("pop_multiplier").value=mlt; 
        }
        
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
		id="quote_add_item_popup">
    <div class="Cus_Popup_Outline quote_add_item_popup_outline" style="width: 1100px !important;">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius:0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Add
					Item</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>
			
			<input type="hidden" id="from_form" />
		
		<div style="margin-top:0px;margin-left:15px;float:left;">
		
			<div class="items_head">
				<label class="trans_label" style="width:130px;margin-right:10px;">Item Description</label>
				<label class="trans_label" style="width:130px;margin-right:10px;">Part No/SKU No</label>
                                <label class="trans_label" style="width:110px;margin-right:10px;">Barcode</label>
				<label class="trans_label" style="width:140px;margin-right:10px;">Quantity</label>
				<label class="trans_label" style="width:130px;margin-right:10px;">Receive Date</label>
				<label class="trans_label" style="width:140px;margin-right:10px;">Price per unit</label>
				<label class="trans_label" style="width:110px;margin-right:10px;">Multiplier</label>
                                <label class="trans_label" style="width:90px;margin-right:10px;">Total</label>
			</div>
			
			
			<div class="item" style="width:1095px;float:left;margin-left:10px;margin-right:0px;position:relative;">
			
				<input type="text" class="textbox" id="popup_item_desc" maxlength=80 style="width:110px;margin-right:20px;"/>
				
				<input type="text" class="textbox" id="popup_part_no" maxlength=80 style="width:110px;margin-right:20px;"/>
                                
                                <input type="text" class="textbox" id="popup_brcd" maxlength=80 style="width:90px;margin-right:20px;"/>
				
				<input type="text" class="textbox" onkeyup="mult()" id="popup_quantity" maxlength=7 style="width:88px;"/>
				
				<div class="quantity_unit" id="quantity_unit" >KG</div>
				
				<div class="quan_units" id="units_quantity_unit" style="display:none;left:500px;">
				</div>
				
				<input type="text" class="textbox" id="popup_ship_date" style="width:110px;margin-right:20px;" readonly/>
				
                                <input type="text" class="textbox" onkeyup="mult()" id="popup_price" maxlength=7 style="width:90px;"/>
				
				<div class="currency" id="currency" >USD</div>
				
				<div class="currency_list" id="currency_currency" style="display:none;left: 794px;">
				</div>
				
				<input type="text" class="textbox" value="1" id="dscnt" onkeyup="mult()" maxlength=5 style="width:90px;margin-right:20px;"/>
                                <input type="text" class="textbox" value="0" readonly id="pop_multiplier" maxlength=5 style="width:90px;margin-right:20px;"/>
				
				
			</div>
			
			<div class="row" style="margin-top:40px;float:left;margin-left:250px;">
				 <input id="quote_add_item_popup_cancel" style="margin-left:100px;margin-right: 50px"
					type='button' class="gen-btn-Gray" value="Cancel"  />
					<input id="save_item_btn" type='button' 
                                               class="gen-btn-blue" value="Add" tabindex="8" onclick="tl_clcltn();"/>
			</div>
			
			<div class="quote_error" id="quote_item_form_error" style="width:980px"> 
			</div>
			
			
		</div>

		</div>
		<a id="addone_lastHiddenElement" href="#" style="opacity: 0; " tabindex="9">test</a>

	</div>


</body>
</html>
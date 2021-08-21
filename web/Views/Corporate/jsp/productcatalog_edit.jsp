<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/RestCSS.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Corporate/css/productcatalogpopup.css" />

<script type="text/javascript">

	$(function()
	{

		$("#ProdCatalogEdit_Close").click(function()
		{
			$("#ProdCatalogEdit").hide();
		});

		$("#popup_currency").click(function()
		{
			SM.Corporate.ProductCatalog.showCurrencyList(this.id,"#popup_currency_popup_currency");
		});
		$("#popup_quantity_unit").click(function()
		{
			SM.Corporate.ProductCatalog.showQuantityList(this.id,"#units_popup_quantity_unit");
		});
	});
</script>

</head>
<body>

<div style="display: none;" id="ProdCatalogEdit" class="Custome_Popup_Window">
	<table>
		<tr>
			<td style="vertical-align: middle;">
				<div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout" >
					<div class="Popup_Tilte_NewGroup Gen_Popup_Title ">
					 	Edit Item
					 	<div id="ProdCatalogEdit_Close" class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
					</div>

						<div class="popupcontent">
							<form action="post" id="ProductCatlogForm">
	
								<div class="div_row" >
									<label class="popup_label" for="popup_item_name"> Item </label>
									<input id="popup_item_name" name="popup_item_name" type="text" class="catalog_textbox_popup textbox">								
								</div>
								<div class="div_row" >
									<label class="popup_label" for="popup_item_description"> Description </label>
									<input  id="popup_item_description" name="popup_item_description"  type="text" class="catalog_textbox_popup textbox">								
								</div>
								<div class="div_row" >
									<label class="popup_label" for="popup_item_part_no"> Part </label>
									<input  id="popup_item_part_no" name="popup_item_part_no"  type="text" class="catalog_textbox_popup textbox">								
								</div>
								<div class="div_row" >
									<label class="popup_label" for="popup_item_sku"> SKU </label>
									<input  id="popup_item_sku" name="popup_item_sku"  type="text" class="catalog_textbox_popup textbox">								
								</div>
								<div class="div_row" style="position:relative;">
									<label class="popup_label" for="popup_item_quantity"> Quantity </label>
									<input id="popup_item_quantity" style="width: 146px;top: 223px" name="popup_item_quantity" type="text" class="catalog_textbox_popup textbox">
									<div class="currency" id="popup_quantity_unit" ></div>
									<div class="currency_list"  id="units_popup_quantity_unit" style="display:none;left:258px;"></div>
																	
								</div>
								<div class="div_row" style="position:relative;">
									<label class="popup_label" for="popup_item_price"> Price </label>
									<input id="popup_item_price" style="width: 146px" name="popup_item_price" type="text" class="catalog_textbox_popup textbox">		
									<div class="currency" id="popup_currency" ></div>
									<div class="currency_list" id="popup_currency_popup_currency" style="display:none;left:258px;"></div>															
								</div>
								<div class="div_row" style="margin-bottom:30px;" >
									<label class="popup_label" for="popup_item_tax"> Tax </label>
									<input id="popup_item_tax" name="popup_item_tax" type="text" class="catalog_textbox_popup textbox">								
								</div>
								  <div class="div_row" style="display:none">
									<label class="popup_label" style="line-height: 18px"> Hide Price </label>
									<div class="checkContainer">
										<input type="checkbox" class="checkbox" id="popup_HidePrice" value="false">
										<div></div>
									</div>							
								</div>
								<input onclick="SM.Corporate.ProductCatalog.RevertInfo();" style="margin-top:-5px;margin-left:140px;" type="button" value="Revert" class="gen-btn-Gray" />
								<input onclick="SM.Corporate.ProductCatalog.saveCatalogInfo();" style="margin-top:-5px;margin-left:20px;" type="button" value="Save" class="gen-btn-Orange" />
							</form>
						</div>
					</div>
			</td>
		</tr>
	</table>
	
</div>
<input type="hidden" id="emailId" />
<input type="hidden" id="rowno" />

<script type="text/javascript">
$(function(){
	
	$(".Gen_Cus_Popup_Close").click(function(){$("#policies_popup").hide();});
	//$("#rename_cancel").click(function(){$("#edit_group_popup").hide();});
	
	var spinner = $( "#policies_spinner" ).spinner();
	$('#policies_spinner').spinner('option', 'min', 0);
	$('#policies_spinner').spinner('option', 'max', 10);
	$('#policies_spinner').val("0");
}	
);
  </script>
</body>
</html>
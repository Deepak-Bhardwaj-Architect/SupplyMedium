<script>
$("#download_close").click(function()
{
	$("#download_info").hide();
});
</script>

<div class="Custome_Popup_Window" style="display:none;" id="download_info">
<div class="Cus_Popup_Outline add_remainder_outline">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title"
				style="border-radius: 0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Download EDI</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" id="download_close"></div>
			</div>
		
		<div class="file_container">
		
		<div class="cont_file" id="rfq_file" style="display:none;">
				<input type="checkbox" class="chkbx" id="rfq_checkbox" >
				<label for="rfq_checkbox" class="lblstyl">rfq.xml</label>
		</div>
		
		
		<div class="cont_file" id="quote_file" style="display:none;">
				<input type="checkbox" class="chkbx" id="quote_checkbox" >
				<label for="quote_checkbox" class="lblstyl">quote.xml</label>
		</div>
		
		
		<div class="cont_file" id="po_file" style="display:none;">
				<input type="checkbox" class="chkbx" id="po_checkbox" >
				<label for="po_checkbox" class="lblstyl">purchase_order.xml</label>
		</div>
		
		<div class="cont_file" id="invoice_file" style="display:none;">
				<input type="checkbox" class="chkbx" id="invoice_checkbox" >
				<label for="invoice_checkbox" class="lblstyl">invoice.xml</label>
		</div>
		
		<input type="hidden" id="trans_id" />
		
		</div>
		
		<input type="button" class="gen-btn-Orange" id="download_btn" value="Download" style="margin-left:230px;margin-top:20px;"/>
		
</div>
</div>



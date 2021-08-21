<script>
$("#remainders_close").click(function()
{
	$("#remainders").hide();
});
</script>

<div class="Custome_Popup_Window" style="display:none;" id="remainders">
<div class="Cus_Popup_Outline remainders_outline">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title"
				style="border-radius: 0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Reminders</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" id="remainders_close"></div>
			</div>
	
		<div id="remainders_content">
		
		</div>
	
</div>
</div>
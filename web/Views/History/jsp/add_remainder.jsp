<script>
$("#add_remainder_close").click(function()
{
	$("#add_remainder").hide();
});
</script>

<div class="Custome_Popup_Window" style="display:none;" id="add_remainder">
<div class="Cus_Popup_Outline add_remainder_outline">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title"
				style="border-radius: 0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Reminders</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" id="add_remainder_close"></div>
			</div>
		
		<div class="remainder_container">
			<div class="add_rem_left_label">Reminder
			</div>
			<textarea class="text_area" placeholder="What do you want to be reminded of ?" id="new_remainder"></textarea>
		</div>
		
		<div class="remainder_container">
			<div class="add_rem_left_label">Date and Time
			</div>
			<div class="dat">
				<input type="text"  class="calendar_style" id="remainder_due_date" readonly >
			</div>
		</div>
		
		<input type="button" class="gen-btn-Orange" id="save_remainder_btn" value="Save" style="margin-left:230px;margin-top:20px;"/>
		
</div>
</div>

<script>
$(".Popup_Close_NewGroup").click(function()
{
	$("#email_composer").hide();
});
</script>

<div class="Custome_Popup_Window " style="display:none;" id="email_composer">
<div class="Cus_Popup_Outline remainders_outline email_composer_outline">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title"
				style="border-radius: 0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">Send Email</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>

		<div class="left_label">Message:</div>
		<div class="right_text">
		<textarea class="textarea" id="message_mail" placeholder="Send a message"></textarea>
		</div>
		<input type="hidden" id="hidden_mail"/> 
		<div class="button">
			<input type="button" class="gen-btn-Gray" id="cancel_mail_btn"style="margin-top:10px;margin-right:20px;float:left" value="Cancel">
			<input type="button" class="gen-btn-Orange"  id="send_mail_btn"style="margin-top:10px;" value="Send" />
		</div>

</div>
</div>
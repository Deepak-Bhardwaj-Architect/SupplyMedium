<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div style="display: none; z-index: 1000;" class="Custome_Popup_Window"
		id="rfq_inquire_popup">
		<div class="Cus_Popup_Outline rfq_inquire_outline">
			<div class="Popup_Tilte_NewGroup Gen_Popup_Title"
				style="border-radius: 0px;">
				<div style="padding: 0px 0px 0px 15px; float: left">RFQ Inquire Details</div>
				<div class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
			</div>
			
			<input type="hidden" id="chat_rfq_id" />
					
			<input type="hidden" id="chat_trans_id" />
					
			<input type="hidden" id="chat_reply_to_comp" />
					
			<input type="hidden" id="chat_reply_to_user" />
					
			<input type="hidden" id="chat_status" />
			
			<div id="chat_rfq_inquires">
			</div>
			
			<div id="chat_new_inquire">
				<div class="inquire_row">
					<label class="inquire_by" id="chat_company_name">  </label>
					<textarea class="inquire_det" id="chat_new_inquire_message" >  </textarea>
					<label class="rfq_error" style="width:300px;margin-left: 125px;margin-top: 10px;" id="chat_inquire_error"></label>
				</div>
			</div>
			
			<input type="button" class="gen-btn-blue" value="Send" style="margin-left: 250px;margin-top: 30px;margin-bottom: 20px;" id="chat_inquire_save_btn">
			
		</div>
	</div>

</body>
</html>
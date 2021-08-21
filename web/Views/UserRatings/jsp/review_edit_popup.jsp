<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/UserRatings/css/revieweditpopup.css" />

<script type="text/javascript">

	$(function()
	{

		$("#UserRatingsEditPopup_Close").click(function()
		{
			$("#UserRatingsEditPopup").hide();
			$("#rattingvaluebigmainpopup").text('');
		});

	});
</script>
</head>

<div style="display: none;" id="UserRatingsEditPopup" class="Custome_Popup_Window">
	<table>
		<tr>
			<td style="vertical-align: middle;">
				<div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout" style="border-right: 0px;-webkit-border-radius: 0px;" >
					<div class="Popup_Tilte_NewGroup Gen_Popup_Title " style="border-right: 0px;-webkit-border-radius: 0px;">
					 	Ratings
					 	<div id="UserRatingsEditPopup_Close" class="Popup_Close_NewGroup Gen_Cus_Popup_Close"></div>
					</div>

						<div class="popupcontent">						
	
								<div class="div_row" >
									<label class="popup_label" for="popup_review_title" > Title </label>
									<input id="popup_review_title" name="popup_review_title" type="text" maxlength="99" style="width: 401px" class="catalog_textbox_popup textbox">								
								</div>
								<div class="div_row" >
									<label class="popup_label" for="popup_item_description" > Review </label>
									<textarea  id="popup_rating_review" name="popup_rating_review" maxlength="250" class="catalog_textbox_popup textbox" style="resize:none;">		
									</textarea>						
								</div>
								<div style="margin: 125px 60px 0px 110px">
									<div id="rattingvaluebigmainpopup" style="float: left;" data-average="0"></div>
									<input onclick="SM.Network.UserRatings.PostReview();" style="width: 50px;float: right;height: 30px;" type="button" value="Post" class="general-button ratngs-btn-review" />
								</div>
							
						</div>
					</div>
			</td>
		</tr>
	</table>
	
</div>

</html>
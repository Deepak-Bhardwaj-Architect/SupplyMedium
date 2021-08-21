<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SupplyMedium</title>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery-1.9.0.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/common.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/ExternalPage/js/contactus_validate.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/custome_controls.css" />
<link rel="stylesheet" href="../css/T2_green_common.css" />
<link rel="stylesheet" href="../css/T2_contact.css" />
<link rel="stylesheet" href="../css/T2_common.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/ResetCSS.css" />
<script type="text/javascript">
	$(function() {
		$("#T_menu_contact").addClass("selected");
		$("#btn_contact_clear").click(function()
				{
					$("#name").val("");
					$("#email").val("");
					$("#address").val("");
				});
				$("#btn_contact_submit").click(function()
				{
					var regnKey = $("#regnKey").val();
					var name 	= $("#name").val();
					var email 	= $("#email").val();
					var address = $("#address").val();
					
					if( name == "" || email == "" || address == "" )
					{
						ShowToast( "Fill all the fields", 2000 );
						
						return;
					}
						
					
					sendContactUsMail( regnKey, name, email, address );
					
					$("#name").val("");
					$("#email").val("");
					$("#address").val("");
				});
	});
</script>
</head>
<body>
	<jsp:include page="/Views/Utils/jsp/header.jsp"></jsp:include>
	
	<form method="post" id="T_container" class="content_container" style="padding-top: 0px;">	
		
		<%@include file="Banner.jsp" %>	
		
		<div class="T_content_container">
			<div id="T_menu_contact_det">
				<div class="T_contact_address">
					<div class="company_name">@#COMPANYNAME</div>
					<div class="address">
						@#C_ADDRESS <br> 
						@#C_STATE @#C_ZIPCODE <br>
						@#C_COUNTRY
					</div>
					
					<div class="inquiry">Inquiry : @#INQUIRY </div>
					<input type="hidden" id="regnKey" value="@#REGNKEY">
					
				</div>
				
				<div style="line-height: 50px;" class="T_contact_title T_hading_Title">
					Get in Touch
				</div>
				
				<input id="name" placeholder="Name" class="text_inputbox" type="text"  /> <br>
				
				<input id="email" placeholder="Email" class="text_inputbox" type="text" /> <br>
				
				<textarea id="address" placeholder="Address" style="width: 369px;height: 119px"  class="text_inputbox" rows="" cols=""></textarea><br>
				
				<div style="margin:10px 0 0 255px;">
					<input id="btn_contact_clear"  type="button" class="general-button gen-btn-Green" value="Clear" />
					<input id="btn_contact_submit" type="button" class="general-button gen-btn-Green" value="Submit" />
				</div>
			</div>
		</div>
		
		<%@include file="Copyrights.jsp" %>
		
		
	</form>
	
	
		<jsp:include page="/Views/Utils/jsp/regnfooter.jsp"></jsp:include>

	<jsp:include page="/Views/Utils/jsp/Cus_Toast.jsp"></jsp:include>

</body>
</html>
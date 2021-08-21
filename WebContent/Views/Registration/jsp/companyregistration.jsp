<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Registration/css/companyregistration.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Registration/css/pricing.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/layout.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />

<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery-1.9.0.js"></script>
	
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery-ui-1.10.0.custom.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery.validate.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery.customSelect.js"></script>
<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/Utils/js/additional-methods.js"></script> 
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/dropdownfiller.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/common.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Registration/js/captcha.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Registration/js/regfieldvalidator.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Registration/js/companyregistration.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Registration/js/regvalidator.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Registration/js/multistepform.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Registration/js/pricing.js"></script>

<script>

$(document).ready(function(){
    $('select.selectbox').customSelect();
    
});

</script>

<title>Company Registration</title>
</head>

<body onload="fetchCategory(); fetchCountry(); setRows(1);generate_captcha('lcaptcha');" >
	<%@include file="../../Utils/jsp/header.jsp"%>
	<div class="pagetitlecontainer">
		<div class="pagetitle">New Company Registration</div>
	</div>

	<div class="contentcontainer">
		<div class="content" id="content" style="height:780px;">
			<div id="steps">

				<div id="step1" style="margin-left:20px;" class="stepprocess">
					<div class="stepno">1</div>
				</div>
				<div class="stepseparator"></div>
				<div id="step2" class="stepnext">
					<div class="stepno">2</div>
				</div>
				<div class="stepseparator"></div>
				<div id="step3" class="stepnext">
					<div class="stepno">3</div>
				</div>

				<div id="step1text" class="stepprocesstext" >Company Info</div>

				<div id="step2text" class="stepnexttext" style="padding-left:33px;width:58px;">User Info</div>

				<div id="step3text" class="stepnexttext" style="padding-left:53px;width:58px;">Pricing</div>

			</div>
			
			<!-- enctype="multipart/form-data" -->
			
			<form id="hidden_form" name="hidden_form" action="${pageContext.request.contextPath}/ImageValidationServlet" method="post"
					enctype="multipart/form-data">
			</form>

			<form action="${pageContext.request.contextPath}/CompanyRegnServlet" id="registrationform" name="registrationform"
				onSubmit="" method="post"  enctype="multipart/form-data">
				
				<div id="companyinfo">
					<%@include file="companyinfo.jsp"%>
					<%@include file="Popup_AddAddress1.jsp"%>
					<%@include file="Popup_AddAddress2.jsp"%>
				</div>
				<div id="userinfo">
					<%@include file="userinfo.jsp"%>
				</div>
				<div id="pricing">
					<%@include file="pricing.jsp"%>
				</div>
				
				
			</form>
		</div>
	</div>

	
	<%@include file="../../Utils/jsp/regnfooter.jsp"%>
</body>

</html>
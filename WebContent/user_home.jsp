<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.9.2.custom_spinner.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/jquery.mCustomScrollbar.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/user_home.css" />

	
<!-- Chat JS style -->

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/Views/ChatJs/Styles/jquery.chatjs.css" />




<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/SMNamespace.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery-1.9.0.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery-ui-1.10.0.custom.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery-ui-1.9.2.custom_spinner.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/filterlist.js"></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery.customSelect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery.mCustomScrollbar.js"></script>

<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery.tooltipster.js" ></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery.dataTables.js" ></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/common.js" ></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/jquery.validate.js" ></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/additional-methods.js" ></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/dropdownfiller.js" ></script>
<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Corporate/js/SMNamespace.js"></script>

<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/footer.js"></script>

<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/ajax_loader.js"></script>



  <!-- ChatJS and dependencies -->
  <script src="${pageContext.request.contextPath}/Views/ChatJs/Scripts/jquery.chatjs.longpollingadapter.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Views/ChatJs/Scripts/jquery.autosize.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Views/ChatJs/Scripts/jquery.activity-indicator-1.0.0.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/Views/ChatJs/Scripts/jquery.chatjs.js" type="text/javascript"></script>

<script type="text/JavaScript" src="${pageContext.request.contextPath}/user_home.js"></script>



<title>Supply Medium</title>
</head>
	<body  onload="customizeMenu( );">

		<%@include file="Views/Utils/jsp/userheader.jsp"%>
	
		<%@include file="Views/Utils/jsp/Cus_Menu.jsp"%>

	  	<img src="${pageContext.request.contextPath}/Views/Utils/images/ajax_loader_big.gif" id="content_loader" 
	  style="top:50%;left:50%;margin-top:-35px;margin-left:-35px;position:absolute;display:none;" />
	
		<div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
	
			<%@include file="Views/UserMgmt/jsp/usermgmt.jsp"%>
				
		</div>
	
</body>

<script type="text/JavaScript" src="${pageContext.request.contextPath}/Views/UserMgmt/js/restrict_menu.js"></script>

</html>
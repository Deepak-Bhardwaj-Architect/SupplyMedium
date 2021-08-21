<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Notification</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Notification/css/notification.css">
</head>
<body>

<%@include file="../../../session_check.jsp"%>

<script type="text/JavaScript">
$("#content_loader").hide();
hideAjaxLoader();
</script>
<div id="cnfrm_clr_ntfctn_hstry" class="rfq_post_confirmation" style="width:400px;height:175px;margin-left:28%;"><center><p><br/><br/>Do you want to clear notification history ?<br/><br/><br/><br/>
            <input type="button" class="gen-btn-blue" value="YES" style="color: red" onclick="clr_ntfctn_hstry();$('#cnfrm_clr_ntfctn_hstry').fadeOut();"/><input type="button" class="gen-btn-blue" value="NO" onclick="$('#cnfrm_clr_ntfctn_hstry').fadeOut();" style="margin-left:30px;"/></p></center>
</div>
<div class="mainpage">
		<div class="pagetitlecontainer">
                    <div class="pagetitle"> Notifications <input type="button" value="CLEAR HISTORY" style="float:right; color:red;margin-top:5px;padding:4px;cursor:pointer;" onclick="cnfrm_clr_ntfctns_hstry()"></div>
		</div>
		<div class="contentcontainer">
			
				<!--  <div class="globalsearch">
					<input type="text" placeholder="Search for first name, last name, email or business" class="searchtext">
				</div>-->
				<div class="contentbg" id="notification_content">
					
					
				</div>
		</div>	
	</div>
	
<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
<%@include file="../../Utils/jsp/ajax_loader.jsp"%>
<%@include file="../../Utils/jsp/footer.jsp"%>

<script>

$.getScript( "${pageContext.request.contextPath}/Views/Notification/js/notification.js", function( data, textStatus, jqxhr ) {
	
	notificationOnload();
	});

</script>
	

</body>
</html>
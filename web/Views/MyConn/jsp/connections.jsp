<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Connection</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/MyConn/css/connections.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/History/css/email_composer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
</head>
<body>
<%@include file="../../../session_check.jsp"%>

<script type="text/JavaScript">
$("#content_loader").hide();
hideAjaxLoader();
</script>

<div class="mainpage">
	
	<div class="pagetitlecontainer">  <!-- Page title container -->
		<div class="pagetitle">My Connections</div>
	</div>
	
	<div class="page">	<!-- Page Container -->
	
		<div class="contentcontainer"> <!-- Page content container -->
		
		<!-- 	<div class="globalsearch"> 
				<input type="text" placeholder="Search for first name, last name, email or business" class="searchtext">
			</div> -->
				
			<div class="containertab">  <!-- Main tab container -->
				<div class="orangetab" id="my_conn_tab">My Connections
				</div>
				<div class="graytab" id="add_conn_tab">Add Connection
				</div>
				<div class="orangestrip">
				</div>
			</div>
				
			<div style="float:left; display:none;" id="new_conn_container"> <!-- New Connection container -->		 		
				
				<div class="connectionsearchcontainer"> <!-- New connection search container -->
					<div class="searchlabel">Search
					</div>
					<div class="consearchcont">
						<input type="text" class="connectionsearch" placeholder="Search for first name, last name, email">
					</div>	
					<input type="button"  id="conn_search_btn" class="conn_search_btn"/>			
				</div>
				
				<div class="contentbg"> <!-- New Connection content -->
				
					<div class="content_left" id="new_conn_list">  <!-- New connection left content -->
					
						
						
					</div>
					
					<div class="content_right" id="new_conn_right">  <!-- New connection right content -->
						<div class="con_orange_button">
							<input type="button" value="Request Connection" class="orange_button" id="add_conn_btn" style="display:none;cursor:pointer">
						</div>
						
					</div>
				</div>
			</div>
			
			
			<div id="my_conn_container"> <!-- My connection cotainer -->
			
				<div class="contbluetab">  <!-- My connection tabs container -->
				
					<div class="bluetab" id="conn_tab">Connections
					</div>
					
					<div class="lgraytab" id="pending_conn_tab" >Pending
					</div>
					
					<div class="bluestrip">
					</div>
					
				</div>
			
			
			<div style="float:left;" id="conn_container"><!-- Connection container -->		
				<div class="contentbg">
					<div class="content_left" id="my_conn_list">
					</div>
					<div class="content_right" id="my_conn_right">
					<div class="con_orange_button">
							<input type="button" value="Send Private Message" style="margin-left:110px;display:none;cursor:pointer;" class="orange_button" id="send_priMsg_btn">
							<input type="button" value="Send Email" style="margin-left:110px;display:none;cursor:pointer;" class="orange_button" id="send_email_btn">
						</div>
						
					</div>
				</div>
			</div>
			
			
			<div id="pending_conn_container" style="display:none">  <!-- Pending connection container -->
				<div class="contentbg">
				
					<div class="content_left" id="pending_conn_list">
					</div>
					
					<div class="content_right" id="pending_conn_right">
						<div class="con_orange_button" id="cntrl">
							<input type="button" value="Accept" class="orange_but" id="conn_accept_btn" style="display:none;cursor:pointer;">
							<input type="button" value="Reject" class="red_but" id="conn_reject_btn" style="display:none;cursor:pointer;">
						</div>
						
					</div>
				</div>
			</div>
			
		</div>
		
	</div>
</div>  <!-- Page end -->
</div><!-- main Page end -->
	


<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
<%@include file="../../Utils/jsp/ajax_loader.jsp"%>
<%@include file="../../History/jsp/email_composer.jsp"%>
<%@include file="../../Utils/jsp/footer.jsp"%>
<%@include file="../../Utils/jsp/Popup_Warning.jsp"%>

<script>



$.getScript( "${pageContext.request.contextPath}/Views/MyConn/js/myconn.js", function( data, textStatus, jqxhr ) {
	getMyConnections();
	$("#send_priMsg_btn").click( sendPrivateMsg );
	$("#send_email_btn").click( openEmailPopup );
	$("#send_mail_btn").click( sendMail );
	});
$.getScript( "${pageContext.request.contextPath}/Views/MyConn/js/newconn.js", function( data, textStatus, jqxhr ) {
	$("#conn_search_btn").click( searchConnections );
	$("#add_conn_btn").click( addConnection );
});
$.getScript( "${pageContext.request.contextPath}/Views/MyConn/js/pendingconn.js", function( data, textStatus, jqxhr ) {
	getPendingConnections();
	$("#conn_accept_btn").click( acceptConnection );
	$("#conn_reject_btn").click( rejectConnection );
});
$.getScript( "${pageContext.request.contextPath}/Views/MyConn/js/connections.js", function( data, textStatus, jqxhr ) {
	connectionOnload();
});


</script>


</body>
</html>
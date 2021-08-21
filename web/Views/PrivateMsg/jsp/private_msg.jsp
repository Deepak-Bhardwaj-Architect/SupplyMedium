<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Messages</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/PrivateMsg/css/private_msg.css">
</head>
<body>
<%@include file="../../../session_check.jsp"%>

<script type="text/JavaScript">
$("#content_loader").hide();
hideAjaxLoader();
</script>

<div class="mainpage">
		<div class="pagetitlecontainer">
			<div class="pagetitle"> Message </div>
		</div>
		<div class="contentcontainer">
		<div class="content">
			<!--  <div class="globalsearch">
				<input type="text" placeholder="Search for first name, last name, email or business" class="searchtext">
			</div>
			<div class="bluestrip">
			</div>-->
			<div class="contentbg">
                            <div class="contentleft">
                                    <div class="searchcontainer">
                                            <input type="text" placeholder="Search" class="namesearch" >
                                            <img src="${pageContext.request.contextPath}/Views/PrivateMsg/images/search_button1.png" class="searchimg">

                                    </div>
                                    <div class="container_list" id="my_conn_list">

                                    </div>
                            </div>
                                            <div class="contentright" style="width:685px;">
                                    <div class="address_container">
                                            <div class="to_add">To:
                                            </div>
                                            <input type="text" placeholder="Name or Email" id="search_member_text" class="to_add_text" onkeyup="getSearchConnections( this.value );">
                                    </div>

                                    <div class="chatcontainer" id="chatcontainer">

                                    </div>
                                    <div id="search_result" class="search_result" style="display:none;">
                                    </div>
                                    <div class="chat_container">
                                            <input type="button" class="chatbutton" value="Post" id="post_message_btn">
                                            <textarea placeholder="Write a reply" class="chatbox" id="message"></textarea>

                                    </div>
                            </div>
			</div>
		</div>
		</div>	
	</div>

<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
<%@include file="../../Utils/jsp/ajax_loader.jsp"%>
<%@include file="../../Utils/jsp/footer.jsp"%>
<%@include file="../../Utils/jsp/Popup_Warning.jsp"%>


<script type="text/JavaScript"
	src="${pageContext.request.contextPath}/Views/PrivateMsg/js/private_msg.js"></script>


</body>
</html>
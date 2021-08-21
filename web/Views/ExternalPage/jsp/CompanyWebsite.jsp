<%@page import="core.login.SessionData"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Company Website</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Popup.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/custome_controls.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/ExternalPage/css/company_website.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/ExternalPage/css/T_form.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/ExternalPage/css/web_page_structure.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/ExternalPage/css/fill_content.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/ExternalPage/css/T_preview.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/ExternalPage/css/content_steps.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/ResetCSS.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
	
</head>
<body>
	 <%@include file="../../../session_check.jsp"%>

	<div class="pagetitlecontainer">
		<div class="pagetitle">Company Website</div>
	</div>
	
	<div class="page" style="padding-top: 0px;">		
		<div class="contentcontainer pageContent" style="width: 1010px;min-height:650px;font-family: Verdana, Arial, sans-serif;background-color:white;border:1px solid #c7c7c7;">
			<div  id="webselection">
				<div class="websel_container">
					<div style="padding: 15px 0px 0px 35px">
						<label for="text_company_url">Company URL Name</label>
						<input checked="checked" name="websel" class="textbox" value="" id="text_company_url" type="text">
						
					</div>
					<div style="padding: 15px 0px 0px 35px">
						<input checked="checked" name="websel" id="radio_ExternalWeb" value="ExternalLink" type="radio">
						<label for="radio_ExternalWeb">Link to an externalWebsite</label>
					</div>
					<div style="padding: 12px 0px 0px 35px">
						<input name="websel" id="radio_Template" value="ExternalPage" type="radio">
						<label for="radio_Template" >Create your own from a template</label>
					</div>
				</div>
				<div class="external_link" style="display: none;">
					<div class="external_link_container">
						<label style="float: left;line-height: 44px;padding-right: 5px;" for="txt_external_link"> ExternalWebsite URL </label>
						<div style="float: left;background: #cccccc;padding: 9px 9px 9px 17px;">
							<input id="txt_external_link" type="text" />
							<input id="btn_external_link" class="general-button gen-btn-link" type="button" value="Link" />
						</div>
					</div>
					<div class="external_loader">
						<img id="img_external_loader" alt="images" height="57px" width="57px" src="${pageContext.request.contextPath}/Views/ExternalPage/images/Link_Loader.gif">
					</div>
					<div class="external_message">
						<div style="margin: 42px 0px 0px 29px;">
							<div id="external_message_img" class="external_success">
							</div>
							<div id="external_message_text" style="margin-left: 15px;">
							</div>
							<div>
								<input id="btn_external_retry" class="general-button gen-btn-Orange" type="button" value="Retry" />
							</div>
						</div>
					</div>
				</div>
				<div class="template_link">
					<div class="template_container">
						There are 3 steps to creating your own website
						<ul>
							<li> Choose your webpage structure</li>
							<li> Fill in your contents</li>
							<li> Then choose a preferred template</li>						
						</ul>
					</div>
					<div>
						<input id="btn_template_proceed" class="general-button gen-btn-Orange" type="button" value="Proceed" />
					</div>
				</div>
			</div>
			<div style="display: none;" id="template_conatiner">
				<div class="template_menus">
					<ul>
						<li id="menu_webstructure" class="temp_menu_selected" style="width: 336px">Web page Structure</li>
						<li id="menu_fill_content" class="temp_menu_normal">Fill Content</li>
						<li id="menu_template" class="temp_menu_normal">Template</li>
					</ul>
				</div>
				<div class="template_menu_content">
					<div id="web_page_structure">
						<%@include file="webpage_structure.jsp" %>
					</div>
					<div style="display: none;" id="fill_content">
						<%@include file="fill_content.jsp" %>
					</div>
					<div style="display: none;" id="template">
						<%@include file="T_preview.jsp" %>
					</div>
				</div>
			</div>
		</div>
	</div>
	

<%@include file="../..//Utils/jsp/footer.jsp" %>

<%@include file="../..//Utils/jsp/Cus_Toast.jsp" %>

<script>

$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/company_website.js" );
$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/webpage_structure.js" );

$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/fill_content.js" );
$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/fill_home.js" );

$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/fill_products.js" );
$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/fill_services.js" );

$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/fill_solutions.js" );
$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/fill_aboutus.js" );

$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/fill_contactus.js" );
$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/T_preview.js" );

$.getScript( "${pageContext.request.contextPath}/Views/ExternalPage/js/jquery.form.js" );

</script>
		
</body>
</html>

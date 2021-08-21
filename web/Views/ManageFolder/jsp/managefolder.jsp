<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Supply Medium</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/ManageFolder/css/managefolder.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/jquery.mCustomScrollbar.css" />
</head>

<body>
<script type="text/JavaScript">
//$("#content_loader").hide();
</script>

 	<%@include file="../../../session_check.jsp"%>
	<div class="pagetitlecontainer">
		<div class="pagetitle">Manage Folders</div>
                <div class="pagetitle" style="color:black;font-size:12px;height:48px;line-height:14px;">
                    Manage Folder section is used to provide the privileges to group users / department groups by Admin
                    <br/> to provide the permissions to access the files as Read Only / Read and Write
                </div>
        </div>

	<div class="page">
		<div class="contentcontainer"
			style="min-height: 600px; background-color: white;">

			<div id="manage_fol_content" style="display:none">
			
			<div class="groupstyle">

				<div class="sectiontitle">
					<div class="sectiontext">Group Users</div>
				</div>

				<div class="mf_group" id="mf_group">
					<div class="mf_main" id="mf_main">
					
					</div>


					<div class="mf_content" id="mf_content">
					
					</div>

					<div id="mf_users">
						<div class="mf_main" id="mf_main_users"></div>
					</div>
				</div>
				
			</div>

			<div class="deptstyle">
			 <div class="deptlist">
				<div class="sectiontitle">
					<div class="sectiontext">Department groups</div>
				</div>

				<div id="mf_dept" class="mf_dept_content">
					<div class="mf_main">

					</div>

					<div class="mf_content">

					</div>
				</div>
				</div>
				
				<div class="folderaccess">
				
				<br/>
					<div class="checkContainer" style="margin-left:10px;">
								<input type="checkbox" name="read" id="read" />
								<div></div>
					</div>
					
					<label class="mf_content_text" style="line-height:17px;" for="read">&nbsp;Read Only
					</label>
							
							<br/><br/>
					<div class="checkContainer" style="margin-left:10px;">
						<input type="checkbox" name="readwrite" id="readwrite" />
						<div></div>
					</div>
					<label class="mf_content_text" style="line-height:17px;" for="readwrite">&nbsp;Read and Write
					</label>
				
				</div>
			</div>
			
			<div class="mf_button">
						<input type="button" class="gen-btn-Orange" onclick="updatePrivileges();" value="Update"/>
			</div>
			
		</div>
		</div>
	</div>

	<%@include file="../../Utils/jsp/footer.jsp"%>
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	
	
<script>

$.getScript( "${pageContext.request.contextPath}/Views/ManageFolder/js/MF_group_loader.js", function( data, textStatus, jqxhr ) {});
$.getScript( "${pageContext.request.contextPath}/Views/ManageFolder/js/MF_user_loader.js", function( data, textStatus, jqxhr ) {});
$.getScript( "${pageContext.request.contextPath}/Views/ManageFolder/js/MF_dept_loader.js", function( data, textStatus, jqxhr ) {});

$.getScript( "${pageContext.request.contextPath}/Views/ManageFolder/js/MF_privileges.js", function( data, textStatus, jqxhr ) {});
$.getScript( "${pageContext.request.contextPath}/Views/ManageFolder/js/managefolder.js", function( data, textStatus, jqxhr ) {
$("#content_loader").hide();
	
	listAllGroups(); 

	$(".mf_group").mCustomScrollbar({
		theme:"dark-thick",
		scrollInertia:150,
		horizontalScroll:true,
		});
		
		$("#manage_fol_content").show();
});
</script>
	

</body>
</html>
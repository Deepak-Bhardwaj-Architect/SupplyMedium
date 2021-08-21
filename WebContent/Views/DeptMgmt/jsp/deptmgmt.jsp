<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/DeptMgmt/css/deptmgmt.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/DeptMgmt/css/deptpopup.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />

	
<title>Supply Medium</title>


</head>
<body onload="">



	 <%@include file="../../../session_check.jsp"%>
	<div class="pagetitlecontainer">
	<div class="pagetitle">Departments</div>
	</div>
	<div class="page">
	<div class="contentcontainer">
		<div class="content" id="dept_content" style="display:none">
		
			<form  id="deptmgmtfrm" name="deptmgmtfrm" style="float:left;">
				<div class="deptlist">
					<div class="deptmgmtbtns">
						<input type="button" class="okbtn new_dept_btn" id="adddeptbtn" value="Add"  style="margin-right:5px;"/>
						<input type="button" class="okbtn edit_dept_btn" id="renamedeptbtn"
							value="Rename" style="margin-right:5px;"/> <input type="button" class="delbtn del_dept_btn"
							id="deldeptbtn" value="Delete" />
					</div>
					<div class="deptlistsearch" style="margin-right: 30px;">
						<input type="text" id="searchdept" class="searchdept"
							onkeyup="myfilter.set(this.value)" />
					</div>
					<div class="assignhead">Assign Users to Department</div>
					<BR />
					<br />
					
					<!-- Departments list -->
					<div class="deptlistbox">
						<div class="deptlisthead ctrlheading">List of Departments</div>

						<select
							class="deptlistsel listbox" name="deptlistsel" id="deptlistsel"
							size="5" onchange="getDeptData(this.value);">
							
						</select>

				

				
					<script type="text/javascript">
					
					
					 	var myfilter = new filterlist(document.deptmgmtfrm.deptlistsel);
					</script>

					</div>

					<div class="gap"></div>
					
					
					<!-- Assign Users to department -->
					<div class="deptgroupbox">
						<div class="groupboxhead ctrlheading">Existing Users</div>

						<select multiple="multiple" class="existusersel listbox"
							name="existusersel" id="existusersel" size="5">

						</select>
					</div>
					<div class="addremove">
						<div class="addbtn" id="adduserbtn"></div>
						<div class="removebtn" id="removeuserbtn"></div>
					</div>

					<div class="deptgroupbox">
						<div class="groupboxhead ctrlheading">Department Users</div>

						<select multiple="multiple" class="deptusersel listbox"
							name="deptusersel" id="deptusersel" size="5">

						</select>
					</div>


				</div>
				<div class="deptassign">
					<!-- Department Management and user assign errors -->
					<div id="deptmgmterr" class="depterror">.</div>
					<div id="userassignerr" class="depterror"></div>
					
					<!-- Assign Group and folder to department headings-->
					<div class="headings">
						<div class="assignhead" style="margin-right: 195px;">Assign
							Groups to Department</div>
						<div class="assignhead" style="width:270px;">Assign Folders to Department</div>
						<div class="managefolder">
							<input type="button" class="okbtn new_fol_btn" id="addfolderbtn"
								value="Add Folder" style="margin-right:5px;"/> <input type="button" class="delbtn del_fol_btn"
								id="deletefolderbtn" value="Delete Folder" />
						</div>
					</div>

					<!-- Assign Group to department -->
					<div class="deptgroupbox">
						<div class="groupboxhead ctrlheading">Existing Groups</div>

						<select class="existgroupsel listbox"
							name="existgroupsel" id="existgroupsel" size="5">

						</select>
					</div>
					<div class="addremove">
						<div class="addbtn" id="addgroupbtn"></div>
						<div class="removebtn" id="removegroupbtn"></div>
					</div>

					<div class="deptgroupbox">
						<div class="groupboxhead ctrlheading">Prerogative Groups</div>

						<select  class="pregroupsel listbox"
							name="pregroupsel" id="pregroupsel" size="5">

						</select>
					</div>

					<div class="gap"></div>
					
					<!-- Assign folder to department -->

					<div class="deptgroupbox">
						<div class="groupboxhead ctrlheading">Existing Folders</div>

						<select  class="existfoldersel listbox"
							name="existfoldersel" id="existfoldersel" size="5">

						</select>
					</div>

					<div class="addremove">
						<div class="addbtn" id="assignfolderbtn"></div>
						<div class="removebtn" id="removefolderbtn"></div>
					</div>

					<div class="deptgroupbox">
						<div class="groupboxhead ctrlheading">Departments Folders</div>

						<select class="deptfoldersel listbox" name="deptfoldersel"
							id="deptfoldersel" size="5">

						</select>
					</div>
				</div>
				<!-- Group assign and folder assign error -->
				<div id="groupassignerr" class="depterror"></div>
				<div id="folderassignerr" class="depterror"></div>
				<!-- Privileges settings -->
				<div class="deptpriv">
					<fieldset class="privfieldset">
						<legend>Privileges</legend>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="addfolder"><div></div></div>
							<label for="addfolder" class="prilbl">Add Folder on Department Page</label> <BR />
						</div>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="delfolder"><div></div></div>
							<label for="delfolder" class="prilbl">Delete Folder on Department Page</label><BR />
						</div>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="addfile"><div></div></div> 
							<label for="addfile" class="prilbl">Add Files on the Department page</label><BR />
						</div>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="delfile"><div></div></div>
							<label for="delfile" class="prilbl">Delete Files on the Department Page</label><BR />
						</div>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="postAnno"><div></div></div>
							<label for="postAnno" class="prilbl">Post Announcement</label><BR />
						</div>
						<!--  <div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="managefold"><div></div></div>
							<label for="managefold" class="prilbl">Manage Folder</label><BR />
						</div>-->
						<div class="deptupt">
							<input type="button" id="updatepribtn" value="Update" class="gen-btn-Orange" />
						</div>
						<div id="deptprierr" class="depterror"></div>
					</fieldset>
				</div>
				
			<%@include file="../../Utils/jsp/Popup_Warning.jsp"%>
			<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
		    <%@include file="../../Utils/jsp/ajax_loader.jsp"%>
			<%@include file="Popup_NewDept.jsp"%>
			<%@include file="Popup_EditDept.jsp"%>
			<%@include file="Popup_NewFolder.jsp"%>
			
		</form>
		</div>
	</div>
	</div>
	
	<%@include file="../../Utils/jsp/footer.jsp"%>
	
	<script type="text/JavaScript">
//$("#content_loader").hide();
</script>

<script>


$.getScript( "${pageContext.request.contextPath}/Views/DeptMgmt/js/deptvalidator.js");
$.getScript( "${pageContext.request.contextPath}/Views/DeptMgmt/js/userassign.js");

$.getScript( "${pageContext.request.contextPath}/Views/DeptMgmt/js/foldermgmt.js");
$.getScript( "${pageContext.request.contextPath}/Views/DeptMgmt/js/deptpopup.js");

$.getScript( "${pageContext.request.contextPath}/Views/DeptMgmt/js/groupassign.js");
$.getScript( "${pageContext.request.contextPath}/Views/DeptMgmt/js/folderassign.js");
$.getScript( "${pageContext.request.contextPath}/Views/DeptMgmt/js/deptprivileges.js");
$.getScript( "${pageContext.request.contextPath}/Views/DeptMgmt/js/deptmgmt.js", function( data, textStatus, jqxhr ) {
	$("#dept_content").show();
	
	getAllDepartments();
	
	$("#content_loader").hide();
	 
	});

</script>



<!--  <script defer="defer" type="text/JavaScript" src="${pageContext.request.contextPath}/Views/DeptMgmt/js/deptmgmt.js"></script>
<script defer="defer" type="text/JavaScript" src="${pageContext.request.contextPath}/Views/Utils/js/common.js"></script>
<script defer="defer" type="text/JavaScript" src="${pageContext.request.contextPath}/Views/DeptMgmt/js/deptvalidator.js"></script>

<script defer="defer" type="text/JavaScript" src="${pageContext.request.contextPath}/Views/DeptMgmt/js/userassign.js"></script>
<script defer="defer" type="text/JavaScript" src="${pageContext.request.contextPath}/Views/DeptMgmt/js/foldermgmt.js"></script>
<script defer="defer" type="text/JavaScript" src="${pageContext.request.contextPath}/Views/DeptMgmt/js/deptpopup.js"></script>
	
<script defer="defer" type="text/JavaScript" src="${pageContext.request.contextPath}/Views/DeptMgmt/js/groupassign.js"></script>
<script defer="defer" type="text/JavaScript" src="${pageContext.request.contextPath}/Views/DeptMgmt/js/folderassign.js"></script>
<script defer="defer" type="text/JavaScript" src="${pageContext.request.contextPath}/Views/DeptMgmt/js/deptprivileges.js"></script>-->



</body>


</html>
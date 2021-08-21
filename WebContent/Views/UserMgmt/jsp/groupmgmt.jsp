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
	href="${pageContext.request.contextPath}/Views/UserMgmt/css/groupmgmt.css" />
<link rel="stylesheet" 
	href="${pageContext.request.contextPath}/Views/Utils/css/jquery-ui-1.10.0.custom.css" />
	
<link rel="stylesheet" href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />

	
<title>Supply Medium</title>


 
</head>
<body>
<script type="text/JavaScript">
$("#content_loader").hide();
</script>

	 <%@include file="../../../session_check.jsp"%>
	<div class="pagetitlecontainer">
	<div class="pagetitle">Groups</div>
	</div>
	<div class="page">
	<div class="contentcontainer" style="min-height:700px;">
		<div class="content" id="group_content" style="display:none;">
			
			<form name="groupmgmtfrm" id="groupmgmtfrm" style="float:left;">
			<div class="grouplist">
				<div class="groupmgmtbtns">
					<input type="button" class="okbtn new_group_btn" id="addgroupbtn" value="Add" style="margin-right:5px;"/>
					<input type="button" class="okbtn edit_group_btn" id="renamegroupbtn" value="Rename" style="margin-right:5px;" />
					<input type="button" class="delbtn del_group_btn" id="delgroupbtn" value="Delete"/> 
				</div>
				<BR/><BR/>
				<div class="grouplistbox">
					<div class="grouplisthead ctrlheading">
						List of User Groups
					</div>
					
					<div class="searchbar">
						<input type="text" class="searchbartext" style="background-image:url('Views/UserMgmt/images/search-large.png');" id="searchgroups" onkeyup="groupfilter.set(this.value)" />
					</div>
					
					
					<select multiple="multiple"  class="grouplistsel listbox"  name="grouplistsel" id="grouplistsel" size="5" onchange="getGroupData(this.value);">
					</select>
					
					
					<script type="text/javascript">
					 	var groupfilter = new filterlist(document.groupmgmtfrm.grouplistsel);
					</script>
					
				</div>
				
				<div class="horizontalbar1">
				</div>
				<div class="horizontalbar2" style="margin-right:32px;">
				</div>
					
				<div class="groupassign">
				<div class="existgroupbox">
					<div class="existgrouphead ctrlheading">
						Existing Users
					</div>

					<div class="searchbar" style="width:225px;">
						<input type="text" class="searchbartext" style="background-image:url('Views/UserMgmt/images/search-small.png');width:187px;" id="searchallusers" onkeyup="existingUserfilter.set(this.value)"/>
					</div>
					
					<select multiple="multiple" class="existusersel listbox" name="existusersel" id="existusersel" size="5">
					</select>
					
					<script type="text/javascript">
					 	var existingUserfilter = new filterlist(document.groupmgmtfrm.existusersel);
					</script>
					
				</div>
				<div class="addremove">
					 <div class="addbtn" id="adduserbtn" ></div>
					<div class="removebtn" id="removeuserbtn" ></div>
				</div>
				
				<div class="pregroupbox">
					<div class="pregrouphead ctrlheading">
						Prerogative Users
					</div>
					
					<div class="searchbar" style="width:225px;">
						<input type="text" class="searchbartext" style="background-image:url('Views/UserMgmt/images/search-small.png');width:187px;"  id="searchexistingusers" onkeyup="groupUserfilter.set(this.value)"/>
					</div>
				
					<select multiple="multiple"  class="pregroupsel listbox"  name="pregroupsel" id="pregroupsel" size="5">
					</select>
					
					<script type="text/javascript">
					 	var groupUserfilter = new filterlist(document.groupmgmtfrm.pregroupsel);
					</script>
				</div>
				
				
			</div>
			
			</div>
			
			<div id="groupmgmterr" class="grouperror">.</div>
			<div id="userassignerr" class="grouperror"></div>
			
			<div class="grouppriv">
				<fieldset class="privfieldset">
					<legend>Privileges</legend>
					<div class="privleft">
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="addusers" /><div></div></div>
							<label for="addusers" class="group_pri_lbl">Add New Users</label>
							
						</div>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="delusers"><div></div></div>
							<label for="delusers"  class="group_pri_lbl">Delete Users</label>
						</div>
						<!-- <div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="uploaddoc"><div></div></div>
							<label for="uploaddoc" class="group_pri_lbl">Upload Documents</label>
						</div>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="deldoc"><div></div></div>
							<label for="deldoc" class="group_pri_lbl">Delete Documents</label>
						</div> -->
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="addbuyers"><div></div></div>
							<label for="addbuyers" class="group_pri_lbl">Add Buyers</label>
						</div>
						<!--  <div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="delbuyers"><div></div></div>
							<label for="delbuyers" class="group_pri_lbl">Delete Buyers</label>
						</div>-->
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="accessusermgmt"><div></div></div>
							<label for="accessusermgmt" class="group_pri_lbl">Access User Management</label>
						</div>
						
					</div>
					<div class="horizontalbar1" style="height:110px; margin-left:20px;">
					</div>
					<div class="horizontalbar2" style="height:110px;">
					</div>
					<div class="privright">
					
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="postanncemnt"><div></div></div>
							<label for="postanncemnt" class="group_pri_lbl">Post Announcements</label>
						</div>
						
						<!--  <div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox" id="delanncemnt"><div></div></div>
							<label for="delanncemnt" class="group_pri_lbl">Delete Announcements</label>
							
						</div>-->
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="rate"><div></div></div>
							<label for="rate" class="group_pri_lbl">Rate Buyers/Suppliers</label>
						</div>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="creategroup"><div></div></div>
							<label for="creategroup" class="group_pri_lbl">Create User Group</label>
						</div>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="delgroup"><div></div></div>
							<label for="delgroup" class="group_pri_lbl">Delete User Group</label>
						</div>
						<!--  <div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="applythemes"><div></div></div>
							<label for="applythemes" class="group_pri_lbl">Apply Themes and Orders</label>
						</div>
						<div class="listitem">
							<div class="checkContainer"><input type="checkbox" class="checkbox"  id="managefolder"><div></div></div>
							<label for="managefolder" class="group_pri_lbl">Manage Folder</label>
						</div>-->
						
					</div>
					<div class="groupupt">
					<input type="button"  id="updatepribtn" class="gen-btn-Orange" value="Update" />
					</div>
					<div id="groupprierr" class="grouperror"></div>
				</fieldset>
			</div>
			<%@include file="../../Utils/jsp/Popup_Warning.jsp"%>
			<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
			<%@include file="../../UserMgmt/jsp/popup_group.jsp"%>
			<%@include file="../../UserMgmt/jsp/popup_editgroup.jsp"%>
			</form>
		</div>
	</div>
	</div>
	
	 <%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	 
	 <%@include file="../../Utils/jsp/footer.jsp"%>
	 
	 
<script>

$.getScript( "${pageContext.request.contextPath}/Views/UserMgmt/js/grouppopup.js", function( data, textStatus, jqxhr ) {});
$.getScript( "${pageContext.request.contextPath}/Views/UserMgmt/js/groupprivileges.js", function( data, textStatus, jqxhr ) {});
$.getScript( "${pageContext.request.contextPath}/Views/UserMgmt/js/groupuserrel.js", function( data, textStatus, jqxhr ) {});
$.getScript( "${pageContext.request.contextPath}/Views/UserMgmt/js/groupmgmt.js", function( data, textStatus, jqxhr ) {
	$("#content_loader").hide();
	
	getAllGroups();
	
	if($("#create_group_flag").val()==0)
	{
		$("#addgroupbtn").hide();
	}
	if($("#delete_group_flag").val() == 0 )
	{
		$("#delgroupbtn").hide();
	}
	
	$("#group_content").show();
});


</script>

</body>




</html>
$().ready(
		function() {
			
			// Use to add user to department
			$('#adduserbtn').click(
					function() {
						assignUsers();
					});
			
			// Use to remove user from department
			$('#removeuserbtn').click(
					function() {
						removeUsers();
					});
			
			// Use to add group to department
			$('#addgroupbtn').click(
					function() {
						assignGroups();
					});
			
			// Use to remove group from department
			$('#removegroupbtn').click(
					function() {
						
						removeGroups();
					});
			
			// Use to assign folder to department
			$('#assignfolderbtn').click(
					function() { 
						
						assignFoders();
					});
			
			// use to remove folder from department
			$('#removefolderbtn').click(
					function() { 
						
						removeFolders();
						
					});
			
			// Add new department popup creation
			$('#adddeptbtn').click(
					function() {
						
						showAddDeptPopUp();
						
					});
			
			// Click save button in new department creation
			$('#save_dept').click(
					function() {
						addDept();
					});
			
		
			// Rename department popup creation
			$('#renamedeptbtn').click(
					function() {
						showEditDeptPopup();
					});
			
			// Click save button in edit the  department name
			$('#edit_dept').click(
					function() {
						editDept();
					});
			
			// delete department
			$('#deldeptbtn').click(
					function() {
						deleteDept();
					});
			
			
			// Add new folder popup creation
			$('#addfolderbtn').click(
					function() {
						showAddFolderPopup();
					});
			
			// Click save button in new folder creation
			$('#save_folder').click(
					function() {
						addFolder();
					});
			
			// Click update button to save the updated privileges
			$('#updatepribtn').click(
					function() {
						setPrivileges();
					});
			
			// Click delete button to delete the folder
			$('#deletefolderbtn').click(
					function() {
						deleteFolder();
					});
			
			
			
			
		});

function showAddDeptPopUp()
{
	$("#new_dept_input").val("");
	$("#add_group_popup").show();
	$("#new_dept_input").focus();
	$('#new_dept_input_err').text("");
}

function showEditDeptPopup()
{
	
	if ($("#deptlistsel")[0].selectedIndex < 0) 
	{
       alert("select any department");
    }
	else
	{
		var deptName = $("#deptlistsel option:selected").text();
		
		
		
		$( "#edit_group_popup" ).show();
		
		$("#edit_dept_input").val(deptName);
		
		//$("#edit_dept_input").focus();
		
		//$("#edit_dept_input").focus().val($("#edit_dept_input").val());
		
		$("#edit_dept_input").focus();
		tmpStr = $("#edit_dept_input").val();
		$("#edit_dept_input").val('');
		$("#edit_dept_input").val(tmpStr);
		
		$('#edit_dept_input_err').text("");
	}
	
}

function showAddFolderPopup()
{
	$("#new_folder_input").val("");
	 $( "#add_folder_popup" ).show();
	 $("#new_folder_input").focus();
	 $('#new_folder_input_err').text("");
}

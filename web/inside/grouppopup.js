$().ready(
		function() {
			
			// Use to add user to group
			$('#adduserbtn').click(
					function() {
						addUserToGroup();
					});
			
			// Use to remove user from group
			$('#removeuserbtn').click(
					function() {
						deleteUserFromGroup();
					});
			
			// Add new group popup creation
			$('#addgroupbtn').click(
					function() {
						
						showAddGroupPopUp();
						
					});
			
			// Click save button in new group creation
			$('#save_group').click(
					function() {
						//alert( "AddGroup" );
						addGroup();
					});
			
		
			// Rename group popup creation
			$('#renamegroupbtn').click(
					function() {
						showEditGroupPopup();
					});
			
			// Click save button in edit the  group name
			$('#edit_group').click(
					function() {
						updateGroup();
					});
			
			// delete department
			$('#delgroupbtn').click(
					function() {
						deleteGroup();            
					});
			
			// Click update button to save the updated privileges
			$('#updatepribtn').click(
					function() {
						setPrivileges();
					});
		});

function showAddGroupPopUp()
{
	$("#new_group_input").val("");
	$("#add_group_popup").show();
	$("#new_group_input").focus();
	$('#new_group_input_err').text("");
}

function showEditGroupPopup()
{
	if ($("#grouplistsel")[0].selectedIndex < 0) 
	{
       alert("select any group");
    }
	else
	{
		var groupName = $("#grouplistsel option:selected").text();
		
		$("#edit_group_input").val( groupName );		
		$( "#edit_group_popup" ).show();
		
		//$("#edit_group_input").focus();
	
		$("#edit_group_input").focus();
		tmpStr = $("#edit_group_input").val();
		$("#edit_group_input").val('');
		$("#edit_group_input").val(tmpStr);
		
		
		$('#edit_group_input_err').text("");
	}
	
}
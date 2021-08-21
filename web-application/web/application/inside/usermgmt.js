var oTable;
var lastSelectedRow=0;
var lastSelectedRowStatus="";

var deleteRow = 0;

$(document).ready(function(){
	
});

function userOnload()
{
	$("#content_loader").hide();
	
	
	$('select.selectbox').customSelect();
	
	fetchCountry();
	
	getAllUsers();
	
	$("#usermgmt_content").show();
			
	initDataTable();
	

	  $('#user_details').click(
				function() 
				{
					showUserDetailsTab();
					clearUserTblErr();
				});
	  $('#new_users').click(
				function() 
				{
					
					showNewUserTab();
					clearUserTblErr();
				}); 
	  
	  $("#exp_never").click(
			  function() {
			  if ($('#exp_never').is(':checked'))
				{ 
					$("#exp_end").attr("checked",false);
					$('#policies_spinner').val(0);
					
			    }
			  
			  });
	  
	  $("#exp_end").click(
			  function() {
			  if ($('#exp_end').is(':checked'))
				{ 
					$("#exp_never").attr("checked",false);
					
					
			    }
			  });
	  
	  $("#username").text("Welcome "+$("#firstName").val());
	  

	  var userImgURL = $("#user_image_url").val();

	if (userImgURL != "" && userImgURL != 'null') {

		$("#header_userimage").attr("src",
				userImgURL + "?timestamp=" + new Date().getTime())
				.load(
						function() {
							pic_real_width = this.width;

							pic_real_height = this.height;

							width = 20;
							height = 20;

							if (width > pic_real_width) {
								width = pic_real_width;
							}
							if (height > pic_real_height) {
								height = pic_real_height;
							}

							// pick the smaller ratio of width or
							// height, and resize to that
							var ratio = width / pic_real_width;
							if (height / pic_real_height < ratio)
								ratio = height / pic_real_height;

							// resize the image to the new dimensions
							// (rounded to 2 places)
							img.width = Math.round(pic_real_width
									* ratio * 100) / 100;
							img.height = Math.round(pic_real_height
									* ratio * 100) / 100;
							// createThumbnail($('#header_userimage'),20,20
							// );

						});
		/*
		 * $("#header_userimage").attr('src',userImgURL
		 * ).load(function(){
		 * 
		 * createThumbnail($('#userimage'),20,20 ); });;
		 */
	} else {
		$('#header_userimage').attr('src',
				'/SupplyMedium/Views/Utils/images/no_photo.png');
	}
		 
	  
	  // User dont have access permission to add user content
	  
	  if($("#add_user_flag").val() == 1 && $("#delete_user_flag").val() == 1 )
	  {
		  
		  $("#new_users").show();
		  $("#new_users").removeClass('highlight');
		  $("#new_users").addClass('normal');
		  
		  $("#user_details").show();
		  $("#user_details").removeClass('normal');
		  $("#user_details").addClass('highlight');
		  
		  $("#userdetails_content").show();
		  
		  $("#newuser_content").hide();
		  
	  }
	  else if($("#add_user_flag").val() == 1 && $("#delete_user_flag").val() == 0)
	  {
		  $("#new_users").show();
		  $("#new_users").removeClass('normal');
		  $("#new_users").addClass('highlight');
		  
		  $("#newuser_content").show();
		  
		  $("#user_details").hide();
	
		  $("#userdetails_content").hide();
		  
	  }
	  
	  else if($("#add_user_flag").val() == 0 && $("#delete_user_flag").val() == 1)
	  {
		  $("#new_user").hide();
		  $("#newuser_content").hide();
		  
		  $("#user_details").show();
		  $("#user_details").removeClass('normal');
		  $("#user_details").addClass('highlight');
	
		  $("#userdetails_content").show();
		  
	  }
	  
	  loadValidator();
}
			
		


function showUserDetailsTab()
{
	 $( "#userdetails_content" ).show();
	 $( "#newuser_content" ).hide();
	 
	 $('#user_details').removeClass('normal');
		$('#user_details').addClass('highlight');
		$('#new_users').removeClass('highlight');
		$('#new_users').addClass('normal'); 
}

function showNewUserTab()
{
	$( "#newuser_content" ).show();
	$( "#userdetails_content" ).hide();
	
	$('#user_details').removeClass('highlight');
	$('#user_details').addClass('normal');
	$('#new_users').removeClass('normal');
	$('#new_users').addClass('highlight');
}

function highlight_options(field)
{
	field.options[field.selectedIndex].className = 'test'; 
}

function getAllUsers()
{
	
	showAjaxLoader();
	
	var regnKey = $('#regnkey').val();
	
	$.ajax({
		   type: "POST",
		   url: getBaseURL()+"/UserFetchServlet",
		   data:{ 
		        'CompanyRegnKey':regnKey
		    } ,
		   cache:false,
		   success: function( data ) 
		   {
			   hideAjaxLoader();
			   
			   if(data == null)
				   return;
			   
			  if(data.result == "success")
				  {
				  	//alert(parseData);
				  	var userArr = new Array( data.users.length );
				  	
				  	userArr = data.users;
				  	
				  
				  	for( var i=0; i<data.users.length; i++ )
					  {
					  		var user = userArr[i];
					  		
					  		var firstName 		= user.firstname;
					  		var city			= user.city;
					  		
					  		if( city == "null" )
				  			{
				  				city = "";
				  			}
					  		
					  		var email			= user.email;
					  		var status			= user.status;
					  		var state			= user.state;
					  		var changePassword 	= user.changepassword;
					  		var disableAccount 	= user.disableaccount;
					  		var expireTime 		= user.expiretime;
					  		
					  		oTable.fnAddData( [firstName, city, email, status, state,"",changePassword,disableAccount,expireTime] );
				  		
					  }
				  }
			  
			  else
			 {
				 $('#usertblerr').text(data.message);
			  }
		   },
		    error : function(xhr, textStatus, errorThrown) 
		    {
		    	hideAjaxLoader();
				
				$('#usertblerr').text("Unexcepted error. Please try again.");
			}
		 });
}

function initDataTable()
{
	
	 oTable=$('#UserList').dataTable( {
		 
		 "aoColumnDefs":[
							{"bVisible":false,"aTargets":[6,7,8] },
							],
							sDom: 'lfr<"fixed_height"t>ip',
							
							"sPaginationType": "full_numbers",
							"bAutoWidth": false,
		 "aoColumnDefs": [
			       	      { "sWidth": "200px", "aTargets": [ 0 ] },
			       	      { "sWidth": "200px", "aTargets": [ 1 ] },
			       	      { "sWidth": "200px", "aTargets": [ 2 ] },
			       	      { "sWidth": "200px", "aTargets": [ 3 ] },
			       	      { "sWidth": "129px", "aTargets": [ 4 ] },
			       	      { "sWidth": "70px", "aTargets": [ 5 ] }
			       	    ],
			
			
			       	 "bLengthChange":false,
			       	"oLanguage": { "sSearch": "Search","sEmptyTable":"No Users found"},
			       	"bSort": true,
			       	"aaSorting": [[0,'asc']],
			
			"bPaginate": true,
			
			"bFilter": true,
			
			"bInfo": true,
			
			"iDisplayLength": 15,
			
			"fnRowCallback":function(nRow,aData,iDisplayIndex,iDisplayIndexFull){
				
				
				$("td",nRow).addClass('rowBorder');
				
				
				$(nRow).click(function()
				{
					
					
					
					var aTrs = oTable.fnGetNodes();
					
					var aPos = oTable.fnGetPosition( this );
					
					// if row already selected then just return
					if( $(aTrs[aPos]).hasClass('row_selected') )
					{
						return;
					}
					
					
					/*if( lastSelectedRowStatus !="" )
					{
						alert("")
						
						oTable.fnUpdate(lastSelectedRowStatus,lastSelectedRow,3,false,false);
						
						oTable.fnUpdate("",lastSelectedRow,5,false,false);
					}
					
					lastSelectedRow= aPos;
					
					lastSelectedRowStatus =aData[3];*/
						
					//addCtrlBtns(aPos,aData[2]);
					
					//alert("status="+aData[3]);
					
					//addStatusDropDown(aData[3],aPos,aData[2]);
					
					for ( var i=0 ; i<aTrs.length ; i++ )
					{				
						if ( $(aTrs[i]).hasClass('row_selected') )
						{
							$(aTrs[i]).removeClass('row_selected');
						}
						
					}
					
					$(nRow).addClass('row_selected');
					
					var aPos = oTable.fnGetPosition( this );
					
					if( lastSelectedRowStatus !="" )
					{
						
						oTable.fnUpdate(lastSelectedRowStatus,lastSelectedRow,3,false,false);
						
						oTable.fnUpdate("",lastSelectedRow,5,false,false);
					}
					
					var aDataNew = oTable.fnGetData( aPos );
					//alert(aDataNew);
					lastSelectedRow= aPos;
					
					lastSelectedRowStatus =aDataNew[3];
						
					addCtrlBtns(aPos,aDataNew[2]);
					
					addStatusDropDown(aDataNew[3],aPos,aDataNew[2]);
					
					
				});		
				
				return nRow;
			}
		} );
}


function addCtrlBtns( rowNo,aDataNew )
{
	// Add edit and delete button
	
	var buttons="<img src='Views/UserMgmt/images/user_edit_btn.png'style='cursor:pointer;z-index:100;margin-right:10px;' title='Update User Settings' onclick=\"javascript:openEditView("+rowNo+",'"+aDataNew+"')\"/>";
	
	if( $("#delete_user_flag").val() == 1 )
	{
		buttons+="<img src='Views/UserMgmt/images/user_delete_btn.png' style='cursor:pointer;z-index-101;' title='Delete User' onclick='deleteUser(";
		buttons+=rowNo+");'/>";
	}
	
		
	/*var editBtn="<img src='../images/user_edit_btn.png' class='user_edit_btn' style='margin-right:10px;cursor:pointer' onclick='openEditView();'/>";
	var delBtn= "<img src='../images/user_delete_btn.png'   class='user_del_btn' style='cursor:pointer' onclick='deleteUser(";
	delBtn =delBtn+ userId+"');>";
	btns=editBtn+delBtn;
	*/
	
	oTable.fnUpdate(buttons,rowNo,5,false,false);
}

function addStatusDropDown( status,rowNo,userId )
{
	// Add select box for status
	
	var selectBoxId = "sel"+rowNo;
	
	var selectBox = "<select name='"+selectBoxId+"'style='z-index:200;width:120px;margin-right:5px;margin-top:5px;' class='' id='"+selectBoxId+"'>";
	
	if( status == "Pending" )
	{
		selectBox = selectBox + "<option>Pending</option>";
		
		selectBox = selectBox + "<option>Active</option>";
		
		selectBox = selectBox + "<option>Rejected</option>";
		
	}
	else if( status == "Active" )
	{
		selectBox = selectBox + "<option>Active</option>";
		
		selectBox = selectBox + "<option>Blocked</option>";
	}
	else if( status == "Blocked" )
	{
		selectBox = selectBox + "<option>Blocked</option>";
		
		selectBox = selectBox + "<option>Active</option>";
	}
	else if( status == "Rejected" )
	{
		selectBox = selectBox + "<option>Rejected</option>";
	}
	
	selectBox = selectBox + "<select>";
	
	var statusStr='"'+status+'"';
	
	var saveBtn = "<input type='button'  style='cursor:pointer' class='status_save_btn' title='Save Status' onclick='saveStatus("+rowNo+","+statusStr+")'/>";
	
	selectboxbtn=selectBox+saveBtn;
	
	oTable.fnUpdate(selectboxbtn,rowNo,3,false,false);
	
}

function openEditView( rowNo,id)
{        
        //alert(data);
	var aData = oTable.fnGetData( rowNo );
	var changePassword = aData[6];
	var disableAccount = aData[7];
	var expireTime     = aData[8];
	
	
	var selectBoxId = "sel"+rowNo;
	
	var status = $('#'+selectBoxId+" option:selected").text();
	
	$('#emailId').val(aData[2]) ;
	
	$('#rowno').val(rowNo);
	
	//alert($('#emailId').val() );
	
	if( changePassword == 1 )
	{
		$('#change_password').attr('checked',true);
	}
	else
	{
		$('#change_password').attr('checked',false);
	}
	
	
	if( disableAccount == 1 && status=="Blocked" )
	{
		$('#acc_disable').attr('checked',true);
	}
	else
	{
		$('#acc_disable').attr('checked',false);
	}
	
	//alert("expiretime="+expireTime);
	
	if( expireTime == 0 )
	{
		$("#exp_never").attr("checked",true);
		
		$("#exp_end").attr("checked",false);
		
	}
	else
	{
		$("#exp_end").attr("checked",true);
		
		$("#exp_never").attr("checked",false);
		
		$('#policies_spinner').val(expireTime);	
	}
	
	$("#policies_popup").show();
        
        $.ajax({
		type : "POST",
		url : "user_settings.jsp",
		data : {
                        'id' : id
		},		
		success : function(data)
		{
                    //alert(data);
                   document.getElementById('user_settings').innerHTML=data;                    
		},
		error : function(xhr, textStatus, errorThrown) 
		{
//			alert('error'+errorThrown);
		}
	});
	
}



function deleteUser( rowNo )
{
	
	deleteRow = rowNo;
	
	var aData = oTable.fnGetData( rowNo );
	
	var name = aData[0];
	
	message = "This operation will delete the user '"+ name+"' permanently. Do you want to continue?";
	
	showWarning(message);
}

function deleteConfirm()
{
	showAjaxLoader();
	
	clearUserTblErr();
	
	var aData = oTable.fnGetData( deleteRow );
	
	var email = aData[2];
	
	var firstName = aData[0];
	
		$.ajax({
		type : "POST",
		url : getBaseURL() + "/UserSignupServlet",
		data : {
			'RequestType' : 'DeleteUser',
			'email' : email,
			'firstname' : firstName
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			//alert("result="+data.result);
			
			if (data.result == "success") 
			{
				 ShowToast( data.message,2000 );
				 
				 oTable.fnDeleteRow( deleteRow );
				 
				 oTable.fnDraw();
				 
				 lastSelectedRowStatus ="";
			}

			else 
			{
				 $('#usertblerr').text(data.message);
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
			$('#usertblerr').text("Unexcepted error. Please try again.");
		}
	});	
}

function savePolicies( )
{
	showAjaxLoader();
	
	clearUserTblErr();
	
	var changePassword = 0;
	var disableAccount = 0;
	var expireTime = 0;
	
	var email = $('#emailId').val();
	
	var rowNo = $('#rowno').val();
	
	var aData = oTable.fnGetData( rowNo );
	
	var firstName = aData[0];
	
	var selectBoxId = "sel"+rowNo;
	
	var status = $('#'+selectBoxId+" option:selected").text();
	
	var aTrs = oTable.fnGetNodes();
	
	if( $('#change_password').is(':checked'))
	{
		changePassword = 1;
	}
	
	if( $('#acc_disable').is(':checked'))
	{
		disableAccount = 1;
	}
	
	if( $("#exp_never").is(':checked'))
	{
		expireTime = 0;
	}
	else
	{	
		expireTime = $('#policies_spinner').val();
	}
	
	var regnKey = $('#regnkey').val();
	
	//alert( "ChangePassword = "+changePassword+"; DisableAccount = "+disableAccount+"; ExpireTime = "+expireTime+"Companykey = "+regnKey );
		
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/UserSignupServlet",
		data : 
		{
			'RequestType' : 'UpdatePolicies',
			'ExpireEnd' : expireTime,
			'ChangePassword':changePassword,
			'AccDisable':disableAccount,
			'Companyname':regnKey,
			'email':email,
			'status':status,
			'firstname': firstName
		},
		cache : false,
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success") 
			{
				 ShowToast( data.message,2000 );
				 
				 $("#policies_popup").hide();
				 
				// alert("status="+status);
				 
				 
				 
				 if( disableAccount == 1 && status== "Active")
				 {
					 status = "Blocked";
				 }
				 if( disableAccount == 0 && status== "Blocked")
				 {
					 status = "Active";
				 }
				 
				 lastSelectedRowStatus = "";
				 
				 //oTable.fnDeleteRow( rowNo );
				 
				 ////lastSelectedRowStatus = newStatus;
				 
				 //oTable.fnAddData( [aData[0], aData[1], aData[2],status, aData[4],"",changePassword,disableAccount, expireTime]);
				 
				  oTable.fnClearTable();
				  
				  getAllUsers();
				 
				 
				 //oTable.fnUpdate( [aData[0], aData[1], aData[2], newStatus, aData[4],"",aData[6],aData[7],aData[8]],rowNo );
				 
				// $(aTrs[rowNo]).removeClass('row_selected');
				 
				 
				 
				// oTable.fnUpdate( [aData[0], aData[1], aData[2],status, aData[4],"",changePassword,disableAccount, expireTime],rowNo );
				 
				// $(aTrs[rowNo]).removeClass('row_selected');
				 
				// oTable.fnDraw();
			}

			else 
			{
				 //oTable.fnUpdate( [aData[0], aData[1], aData[2], oldStatus, aData[4],"",aData[6],aData[7],aData[8]],rowNo );
				
				 $('#usertblerr').text(data.message);
				 
				 $("#policies_popup").hide();
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
			$('#usertblerr').text("Unexcepted error. Please try again.");
			
			$("#policies_popup").hide();
		}
	});	

}

function addUser()
{
	if($("#usermgmtfrm").valid() && $('#emailexist').val() == "0" && $('#phoneexist').val() == "0" )
	{
            return true;
	}
        else
        {
            return false;
        }
}

function saveStatus( rowNo,oldStatus )
{
	
	var aTrs = oTable.fnGetNodes();
	
	var aData = oTable.fnGetData( rowNo );
	
	var firstName = aData[0];
	
	var email = aData[2];
	
	var selectBoxId = "sel"+rowNo;
	
	var newStatus = $('#'+selectBoxId+" option:selected").text();
	
	if( oldStatus == newStatus )
	{
		
	/*	oTable.fnDeleteRow( rowNo );
		 
		lastSelectedRowStatus = newStatus;
		 
		 oTable.fnAddData( [aData[0], aData[1], aData[2], newStatus, aData[4],"",aData[6],aData[7],aData[8]]);
		 
		 $(aTrs[rowNo]).removeClass('row_selected');
		 
		 return;*/
	}
		
	//alert("selectbox="+newStatus);
	
	//return;
	
	
	
	clearUserTblErr();
	 
	$.ajax({
		type : "POST",
		url : getBaseURL() + "/UserSignupServlet",
		data : 
		{
			'RequestType' : 'UserStatusUpdate',
			'email' : email,
			'status':newStatus,
			'firstname' : firstName
		},
		cache : false,
		
		success : function(data) 
		{
			hideAjaxLoader();
			
			if (data.result == "success") 
			{
				 ShowToast( data.message,2000 );
				 
				 oTable.fnDeleteRow( rowNo );
				 
				 lastSelectedRowStatus = newStatus;
				 
				 var disableAccount=aData[7];
				 
				 if( newStatus == "Active" )
				 {
					 disableAccount = 0;
				 }
				 else if( newStatus == "Blocked" )
				 {
					 disableAccount = 1;
				 }
				 
				 oTable.fnAddData( [aData[0], aData[1], aData[2], newStatus, aData[4],"",aData[6],disableAccount,aData[8]]);
				 
				 //oTable.fnUpdate( [aData[0], aData[1], aData[2], newStatus, aData[4],"",aData[6],aData[7],aData[8]],rowNo );
				 
				 $(aTrs[rowNo]).removeClass('row_selected');
			}

			else 
			{
				 oTable.fnDeleteRow( rowNo );
				 
				lastSelectedRowStatus = "";
				
				 oTable.fnAddData( [aData[0], aData[1], aData[2], oldStatus, aData[4],"",aData[6],aData[7],aData[8]]);
				 
				 $(aTrs[rowNo]).removeClass('row_selected');
				 
				 //$('#usertblerr').text(data.message);
			}
		},
		error : function(xhr, textStatus, errorThrown) 
		{
			hideAjaxLoader();
			
			$('#usertblerr').text("Unexcepted error. Please try again.");
		}
	});	
}

function resetNewUserFrm()
{
	 $('#firstname').val("");
	 $('#lastname').val("");
	 $('#email').val("");
	 $('#phone').val("");
	 $('#cell').val("");
	 $('#zipcode').val("");
	 $('#countryregion_0').val("");
	 $("#state_0").val("");
	 $("#state_text").val("");
	 $("#newusererr").text("");
	 $("#city").val("");
	 $('#phonenocheck').removeClass('validtick');
	 $('#emailcheck').removeClass('validtick');
	 $( "select" ).trigger('update');
	 
}

function clearUserTblErr()
{
	$('#usertblerr').text("");
}



function changeStateDropDown(countryName)
{
	
	if(countryName=="United States")
	{
		$("#select_0_container").show();
		$("#state_text").hide();
	}
	else
	{	
		
		$("#state_text").show();
		$("#select_0_container").hide();
	}

}

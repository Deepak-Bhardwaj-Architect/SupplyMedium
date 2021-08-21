<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/commonlayout.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/elements.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/UserAcctMgmt/css/useracctmgmt.css" />


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Utils/css/Custome_Buttons.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/Views/Registration/css/usersignup.css" />




<title>Supply Medium</title>
</head>
<body>

<script type="text/JavaScript">
 $("#content_loader").hide();
</script>

	 <%@include file="../../../session_check.jsp"%>
	<div class="pagetitlecontainer">
		<div class="pagetitle">User Account Management</div>
	</div>

        <div id="sv_dtl_cnfrmtn_msg_dv" class="update_user_detail2" onclick="$('#sv_dtl_cnfrmtn_msg_dv').fadeOut();">User detail has been updated</div>
	<div class="page">
		<div class="contentcontainer">
			<div class="content" style="min-height:720px;padding-top:0px;">
				<div class="striphead" id="uploadpicheadid">
					<label class="stripheadtext" id="uploadpicheadid">Upload
						Picture</label>
					<div class="right_arrow" id="profile_arrow"></div>
				</div>
				<form name="useracct_profilefrm" id="useracct_profilefrm" style="display:none" action="${pageContext.request.contextPath}/UserAcctMgmtServlet" method="post" enctype="multipart/form-data" >
				<div class="profilecontainer">
					<div class="stripcontentleft" id="uploadpiccontent">

						<div class="innercontent" style="margin-left:250px;">

							<input type="file" class="file" style="font-size: 12px;"
								title="The logo dimensions should be at most 500x500 pixels in size should not exceed 1 MB.  Supported formats are jpg, png, bmp, gif and tiff."
								name="profilepictxt" id="profilepicid"  /> <label class="error" style="height:30px;margin-left:-50px"
								id="profilepic_err"></label>
								
								<img id="profile_thumbnail" style="display:none;" />
						</div>
					</div>

					<div class="stripcontentright">


						<div class="label stripcontenttext"
							style="width: auto; margin-left: 32px; margin-top: 5px;line-height:10px;">
                                                    Your Profile Picture (User Profile Image)<br/><br/>
                                                        <img class="profilepic" id="profilepicture" src="${pageContext.request.contextPath}/Views/UserAcctMgmt/images/noimagebig.png">
                                                </div>
						<!--  <div class="profilepic" id="profilepicture"></div>-->
						
					</div>

					<!-- <div class="hori_seperator"></div>

					<div class="actionstrip">


						<input type="button" class="gen-btn-Gray"
							style="height: 24px; width: 93px; font-size: 11px; margin-left: 200px;"
							id="piccancelbtnid" value="Cancel" /> <input type="button"
							class="gen-btn-Orange"
							style="height: 24px; width: 93px; font-size: 11px;"
							id="picsavebtnid" value="Save" />
					</div> -->
				</div>
				</form>
				
				<div class="striphead" id="passwordheadid">
					<label class="stripheadtext" id="passwordheadid">Reset
						Password</label>
					<div class="right_arrow" id="password_arrow"></div>
				</div>

				<form name="useracct_resetpass" id="useracct_resetpass" style="display:none">
				<div class="profilecontainer">
					<div class="stripcontent" id="uploadpiccontent">

						<div class="innercontent">
							<div class="label stripcontenttext">Current <span class="mandatory">*</span></div>
							<input type="password" id="currentpasstxtid"
								name="currentpasstxt" class="textbox customtext" />
								<label class="error" for="currentpasstxtid" style="text-align:left;margin-left:107px;"
								></label>
						</div>
						<div class="innercontent">
							<div class="label stripcontenttext">New<span class="mandatory">*</span></div>
							<input type="password" id="newpasstxtid" name="newpasstxt"
								class="textbox customtext" />
								<label class="error" for="newpasstxtid" style="text-align:left;margin-left:107px;"
								></label>
						</div>

						<div class="innercontent">
							<div class="label stripcontenttext">Re-type New
							<span class="mandatory">*</span></div>
							<input type="password" id="retypepasstxtid" name="retypepasstxt"
								class="textbox customtext" />
								<label class="error" for="retypepasstxtid" style="text-align:left;margin-left:107px;"
								></label>
						</div>

					</div>

					<div class="hori_seperator" style="margin-top: 24px;"></div>

					<div class="actionstrip">


						<input type="button" class="gen-btn-Gray"
							style="height: 30px; width: 80px; font-size: 11px; margin-left: 200px;"
							id="passcancelbtnid" value="Cancel" /> 
						
						<input type="button"
							class="gen-btn-Orange"
							style="margin-left:15px;height: 30px; width: 80px; font-size: 11px;"
							id="passsavebtnid" value="Save" />
					</div>
					<div class="innercontent">
								 <label class="error" style="height:25px;margin:0px;text-align:center;width:450px;"
								id="pass_err"></label>
						</div>
					
				</div>
				</form>
				

				<div class="striphead" id="notifyheadid">
					<label class="stripheadtext" id="notifyheadid">Notification
						Settings</label>
					<div class="right_arrow" id="notify_arrow"></div>
				</div>

			<form name="useracct_notifyfrm" id="useracct_notifyfrm" style="display:none">
				<div class="profilecontainer">
				
					<div class="stripcontent">
						
						<div class="innercontent">
								<div class="label stripcontenttext">Email<span class="mandatory">*</span></div>
								<input type="text" id="altemailtxtid" name="altemailtxt"
									class="textbox customtext" />
									<label for="altemailtxtid" generated="true" class="error"
								style="width:150px; margin-left:107px;text-align:left;"></label>
							</div>
	
						<div class="innercontent">
							<div class="checkContainer" style="margin-left:43px;">
								<input type="checkbox" id="altemailchkid" name="altemailtxt"
									style="float: left; margin-top: 0px; margin-left:-18px; cursor: pointer;" />
								<div></div>
							</div>
							<label class="label stripcontenttext" for="altemailchkid"
								style="text-align: left; width: 300px;">Use
								registered email address to send notifications
							</label> 
						</div>
							
						<div class="innercontent">
							<div class="label stripcontenttext">Mobile</div>
							<input type="text" id="altmobiletxtid" name="altmobiletxt"
								class="textbox customtext" />
							<label for="altmobiletxtid" generated="true" class="error"
							style="width:300px; margin-left:107px;text-align:left;"></label>
						</div>
					
					</div>
					
					<div class="notify_content_two">
						<label class="label stripcontenttext"
							style="text-align: left; width: 300px;margin-left:10px">Notifiaction Schedule:</label>
					</div>

						<div class="notify_content_one" style="border:none;">
							<div class="stripcontent">
								<div class="innercontent" style="width:300px;">
								
									<div class="checkContainer" style="margin-left: 43px;">
										<input type="checkbox" id="whchkid"
											name="whchktxt"
											style="float: left; margin-top: 0px; margin-left:-18px; cursor: pointer;" />
										<div></div>
									</div>

									<label class="label stripcontenttext" for="whchkid"
										style="width: 130px; text-align:left;">During
										working hours
									</label>  
									
									<select name="whselectname" id="whselectid"
										class="selectbox cs" style="width:80px;">
										<option value="Email">Email</option>
										<option value="SMS">SMS</option>
										<option value="Both">Both</option>
									</select> 

								</div>
								
								<div class="innercontent" style="width:300px;">
									<div class="checkContainer" style="margin-left: 43px;">
										<input type="checkbox" id="outsidewhchkid"
											name="outsidewhchktxt"
											style="float: left; margin-top: 0px; margin-left:-18px; cursor: pointer;" />
										<div></div>
									</div>
									<label class="label stripcontenttext" for="outsidewhchkid"
										style="width: 130px; text-align:left;">Outside
										working hours
									</label>
									<select	id='outsidewhselectid' name='outsidewhselectname'
										class="selectbox cs" style="width:80px;">
										<option value="Email">Email</option>
										<option value="SMS">SMS</option>
										<option value="Both">Both</option>
									</select>

								</div>

								<div class="innercontent" style="width: 300px;">
									<div class="checkContainer" style="margin-left: 43px;">
										<input type="checkbox" id="stopnotifychkid"
											name="stopnitifychktxt"
											style="float: left; margin-top: 0px; margin-left:-18px; cursor: pointer;" />
										<div></div>
									</div>
									<label class="label stripcontenttext" for="stopnotifychkid"
										style="width: 200px; text-align:left;">Stop all
										notifications between
									</label>

								</div>
								
								<div class="calender">
								
								<div class="innercontent calender" style="width: 350px;">
								
									<input type="text" class="textbox customtext" style="margin-left:45px; width:108px;" 
									id="stopnotify_fromdatepickerid" />
									<label class="label stripcontenttext"
										style="margin-left:20px;width: 20px; text-align:left;">AND</label>
										
									<input type="text" class="textbox customtext"  style="width:108px;" 
									id="stopnotify_todatepickerid" />	

								</div>
								
								</div>
								<!-- <div class="innercontent" style="margin: 0px;">
									<div class="hori_seperator" style="margin-top: 24px; margin-left:52px;"></div>
								</div>
								<div class="innercontent" style="margin-top: 0px;">

									<input type="button" class="gen-btn-Gray"
										style="margin-left: 160px; height: 24px; width: 93px; font-size: 11px;"
										id="notify_cancelbtnid" value="Cancel" /> 
									
									<input type="button"
										class="gen-btn-Orange"
										style="height: 24px; width: 93px; font-size: 11px;"
										id="notify_savebtnid" value="Save" />

								</div> -->
								
								<div class="hori_seperator" style="margin-top: 24px;"></div>

								<div class="actionstrip" style="text-align:left;">
			
			
									<input type="button" class="gen-btn-Gray"
										style="height: 30px; width: 80px; font-size: 11px; margin-left: 177px;"
										id="notify_cancelbtnid" value="Cancel" /> 
									
									<input type="button"
										class="gen-btn-Orange"
										style="margin-left:15px;height: 30px; width: 80px; font-size: 11px;"
										id="notify_savebtnid" value="Save" />
								</div>
								 <label class="error" style="height:20px;margin-left:300px"
								id="notify_err"></label>

							</div>
						</div>
					
				</div>
				
				</form>
				
				<div class="striphead" id="workinghoursheadid">
					<label class="stripheadtext" id="workinghoursheadid">Working
						Hours Settings</label>
					<div class="right_arrow" id="wh_arrow"></div>
				</div>

				<form name="useracct_whfrm" id="useracct_whfrm" style="display:none">
					<div class="notify_content_one">

						<div class="innercontent"
							style="width: 700px; margin-bottom: 10px;">

							<div class="checkContainer" style="margin-left: 43px;">
								<input type="checkbox" id="sundaychkid" name="sundaychktxt"
									style="margin-top: 0px; cursor: pointer;" />
								<div></div>
							</div>

							<label class="label stripcontenttext" for="sundaychkid"
								style="margin-left: 10px; width: 80px; text-align: left;">Sunday</label>

							<select class="selectbox cs" style="width: 80px;"
								id="sundayfromtimeid" name="sundayfromtimetxt">
							</select> 
							<label
								class="label stripcontenttext"
								style="width: 30px; margin-left: 20px; text-align: left; float: none;">To
							</label>

							<select class="selectbox cs" style="width: 80px;" 
								id="sundaytotimeid"	name="sundaytotimetxt">
							</select>
						</div>

						<div class="innercontent"
							style="width: 700px; margin-top: 0px; margin-bottom: 10px;">

							<div class="checkContainer" style="margin-left: 43px;">
								<input type="checkbox" id="mondaychkid" name="mondaychktxt"
									style="margin-top: 0px; cursor: pointer;" />
								<div></div>
							</div>

							<label class="label stripcontenttext" for="mondaychkid"
								style="margin-left: 10px; width: 80px; text-align: left;">Monday</label>

							<select class="selectbox cs" style="width: 80px;"
								id="mondayfromtimeid" name="mondayfromtimetxt">
							</select> 
							<label
								class="label stripcontenttext"
								style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
							</label>

							<select class="selectbox cs" style="width: 70px;" id="mondaytotimeid"
								name="mondaytotimetxt">
							</select>
						</div>

						<div class="innercontent"
							style="width: 700px; margin-top: 0px; margin-bottom: 10px;">

							<div class="checkContainer" style="margin-left: 43px;">
								<input type="checkbox" id="tuesdaychkid" name="tuesdaychktxt"
									style="margin-top: 0px; cursor: pointer;" />
								<div></div>
							</div>

							<label class="label stripcontenttext" for="tuesdaychkid"
								style="margin-left: 10px; width: 80px; text-align: left;">Tuesday
							</label>

							<select class="selectbox cs" style="width: 80px;"
								id="tuesdayfromtimeid" name="tuesdayfromtimetxt">
							</select> 
							<label
								class="label stripcontenttext"
								style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
							</label>

							<select class="selectbox cs" style="width: 80px;" id="tuesdaytotimeid"
								name="tuesdaytotimetxt">
							</select>
						</div>

						<div class="innercontent"
							style="width: 700px; margin-top: 0px; margin-bottom: 10px;">

							<div class="checkContainer" style="margin-left: 43px;">
								<input type="checkbox" id="wednesdaychkid"
									name="wednesdaychktxt"
									style="margin-top: 0px; cursor: pointer;" />
								<div></div>
							</div>

							<label class="label stripcontenttext" for="wednesdaychkid"
								style="margin-left: 10px; width: 80px; text-align: left;">Wednesday</label>

							<select class="selectbox cs" style="width: 80px;"
								id="wednesdayfromtimeid" name="wednesdayfromtimetxt">
							</select> 
							<label
								class="label stripcontenttext"
								style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
							</label>

							<select class="selectbox cs" style="width: 80px;" id="wednesdaytotimeid"
								name="wednesdaytotimetxt">
							</select>
						</div>

						<div class="innercontent"
							style="width: 700px; margin-top: 0px; margin-bottom: 10px;">

							<div class="checkContainer" style="margin-left: 43px;">
								<input type="checkbox" id="thursdaychkid" name="thursdaychktxt"
									style="margin-top: 0px; cursor: pointer;" />
								<div></div>
							</div>

							<label class="label stripcontenttext" for="thursdaychkid"
								style="margin-left: 10px; width: 80px; text-align: left;">Thursday</label>

							<select class="selectbox cs" style="width: 80px;"
								id="thursdayfromtimeid" name="thursdayfromtimetxt">
							</select> 
							<label
								class="label stripcontenttext"
								style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
							</label>

							<select class="selectbox cs" style="width: 80px;" id="thursdaytotimeid"
								name="thursdaytotimetxt">
							</select>
						</div>

						<div class="innercontent"
							style="width: 700px; margin-top: 0px; margin-bottom: 10px;">

							<div class="checkContainer" style="margin-left: 43px;">
								<input type="checkbox" id="fridaychkid" name="fridaychktxt"
									style="margin-top: 0px; cursor: pointer;" />
								<div></div>
							</div>

							<label class="label stripcontenttext" for="fridaychkid"
								style="margin-left: 10px; width: 80px; text-align: left; ">Friday</label>

							<select class="selectbox cs" style="width: 80px;"
								id="fridayfromtimeid" name="fridayfromtimetxt">
							</select> 
							<label
								class="label stripcontenttext"
								style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
							</label>

							<select class="selectbox cs" style="width: 80px;" id="fridaytotimeid"
								name="fridaytotimetxt">
							</select>
						</div>

						<div class="innercontent"
							style="width: 700px; margin-top: 0px; margin-bottom: 10px;">

							<div class="checkContainer" style="margin-left: 43px;">
								<input type="checkbox" id="saturdaychkid" name="saturdaychktxt"
									style="margin-top: 0px; cursor: pointer;" />
								<div></div>
							</div>

							<label class="label stripcontenttext" for="saturdaychkid"
								style="margin-left: 10px; width: 80px; text-align: left;">Saturday</label>

							<select class="selectbox cs" style="width: 80px;"
								id="saturdayfromtimeid" name="saturdayfromtimetxt">
							</select> 
							<label
								class="label stripcontenttext"
								style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
							</label>

							<select class="selectbox cs" style="width: 80px;" id="saturdaytotimeid"
								name="saturdaytotimetxt">
							</select>
						</div>
						<div class="innercontent" style="margin:0px;">
							<div class="hori_seperator" style="margin-top:14px;"></div>
						</div>
						
						<div class="innercontent" style="margin-top:0px;">

							<input type="button" class="gen-btn-Gray"
								style="margin-left:160px; height: 30px; width: 80px; font-size: 11px;"
								id="wh_cancelbtnid" value="Cancel" /> <input type="button"
								class="gen-btn-Orange"
								style="margin-left:15px;height: 30px; width: 80px; font-size: 11px;"
								id="wh_savebtnid" value="Save" />

						</div>
						 <label class="error" style="height:20px;margin-left:300px"
								id="wh_err"></label>

					</div>
				</form>
                                                <div class="striphead" id="updt_dtl" onclick="updt_dtl();">
					<label class="stripheadtext">User Details</label>
					<div class="right_arrow" id="updt_dtl_arw"></div>
				</div>                                               
                                    <form class="contentcontainer content" name="updt_dtl_bx" id="updt_dtl_bx" style="display:none;width:96%;height: auto;">

			</form>   
                        </div>


		</div>
	</div>

	<%@include file="../../Utils/jsp/footer.jsp"%>
	<%@include file="../../Utils/jsp/Cus_Toast.jsp"%>
	<%@include file="../../Utils/jsp/ajax_loader.jsp"%>
	
<script>


$.getScript( "${pageContext.request.contextPath}/Views/UserAcctMgmt/js/useracctmgmt_fieldvalidator.js", function( data, textStatus, jqxhr ) {
	
	loadValidator( ); 
	});

$.getScript( "${pageContext.request.contextPath}/Views/UserAcctMgmt/js/useracctmgmt.js", function( data, textStatus, jqxhr ) {
	
	userAcctMgmtOnload();
	});

</script>
	
	
	
<!--  <script type="text/javascript"
	src="${pageContext.request.contextPath}/Views/UserAcctMgmt/js/useracctmgmt_fieldvalidator.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/Views/UserAcctMgmt/js/useracctmgmt.js"></script>-->
	
	
</body>
</html>
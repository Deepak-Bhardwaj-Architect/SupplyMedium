<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="supply.medium.home.bean.UserSettingBean"%>
<%@page import="supply.medium.home.database.UserSettingMaster"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.bean.CompleteUserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <link rel="stylesheet" href="inside/jquery-ui-1.9.2.custom_spinner.css">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/jquery.mCustomScrollbar.css">
        <link rel="stylesheet" href="inside/user_home.css">
        <link rel="stylesheet" href="inside/dilbag.css">
        <link rel="stylesheet" href="inside/Cus_Toast.css">
        <!-- Chat JS style -->
        <link rel="stylesheet" type="text/css" href="inside/jquery.chatjs.css">
        <script type="text/JavaScript" src="inside/SMNamespace.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery-ui-1.9.2.custom_spinner.js">
        </script>
        <script type="text/JavaScript" src="inside/filterlist.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.customSelect.js">
        </script>
        <script type="text/javascript" src="inside/jquery.mCustomScrollbar.js">
        </script>
        <script src="inside/jquery.mousewheel.min.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.dataTables.js">
        </script>
        <script type="text/JavaScript" src="inside/common.js">
        </script>
        <script type="text/JavaScript" src="inside/jquery.validate.js">
        </script>
        <script type="text/JavaScript" src="inside/additional-methods.js">
        </script>
        <script type="text/JavaScript" src="inside/dropdownfiller.js">
        </script>
        <script type="text/JavaScript" src="inside/SMNamespace(1).js">
        </script>
        <script type="text/JavaScript" src="inside/footer.js">
        </script>
        <script type="text/JavaScript" src="inside/ajax_loader.js">
        </script>
        <!-- ChatJS and dependencies -->
        <script src="inside/jquery.chatjs.longpollingadapter.js" type="text/javascript">
        </script>
        <script src="inside/jquery.autosize.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.activity-indicator-1.0.0.min.js" type="text/javascript">
        </script>
        <script src="inside/jquery.chatjs.js" type="text/javascript">
        </script>
        <script type="text/JavaScript" src="inside/user_home.js">
        </script>
        <script type="text/JavaScript" src="inside/dilbag.js">
        </script>
        <title>Supply Medium</title>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
    </head>
    <body onLoad="lockUnlock('webkrit_content_loader'); Usr_anlys('Admin'); customizeMenu(); pypl_rslt('null');" onkeydown="displayunicode(event);">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/elements.css">
        <link rel="stylesheet" href="inside/useracctmgmt.css">
        <link rel="stylesheet" href="inside/useracctmgmt_fieldvalidator.js">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/usersignup.css">
        <div class="pagetitlecontainer">
            <div class="pagetitle">User Account Management</div>
        </div>
        <div id="sv_dtl_cnfrmtn_msg_dv" class="update_user_detail2" onClick="$( & #39; #sv_dtl_cnfrmtn_msg_dv & #39; ).fadeOut();">User detail has been updated</div>
        <div class="page">
            <div class="contentcontainer">
                <div class="content" style="min-height:720px;padding-top:0px;">
                    <div class="striphead" id="uploadpicheadid" onclick="openNCloseUsettingBox('useracct_profilefrm');">
                        <label class="stripheadtext" id="uploadpicheadid">Upload
                            Picture</label>
                        <div class="down_arrow" id="profile_arrow">
                        </div>
                    </div>
                    <form name="useracct_profilefrm" id="useracct_profilefrm" style="display: block;" action="UploadUserProfileImage" method="post" enctype="multipart/form-data">
                        <div class="profilecontainer">
                            <div class="stripcontentleft" id="uploadpiccontent">
                                <div class="innercontent" style="margin-left:250px;"><!--showImagePreview(this, 'profilepicture'); showImagePreview(this, 'header_userimage');-->
                                    <input type="file" class="file" style="font-size: 12px;" name="profilepictxt" id="profilepicid" onchange="updateUserProfileImage(this);">
                                    <label class="error" style="height:30px;margin-left:-50px" id="profilepic_err">
                                    </label>
                                    <img id="profile_thumbnail" style="display:none;">
                                </div>
                            </div>
                            <div class="stripcontentright">
                                <div class="label stripcontenttext" style="width: auto; margin-left: 32px; margin-top: 5px;line-height:10px;">
                                    Your Profile Picture (User Profile Image)<br>
                                    <br>
                                    <% String userImage = SmProperties.pathUrl + "users/" + session.getAttribute("userKey").toString() + ".png";
                                        File userPicImage = new File(SmProperties.folderPath + "users/" + session.getAttribute("companyKey").toString() + ".png");
                                        if (!userPicImage.exists()) {
                                            userImage = "inside/no_image_big.png";
                                        }
                                    %>
                                    <img class="profilepic" id="profilepicture" src="<%=userPic%>">
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="striphead" id="passwordheadid" onclick="openNCloseUsettingBox('useracct_resetpass');">
                        <label class="stripheadtext" id="passwordheadid">Reset
                            Password</label>
                        <div class="right_arrow" id="password_arrow">
                        </div>
                    </div>
                    <form name="useracct_resetpass" id="useracct_resetpass" style="display: none;" novalidate="novalidate">
                        <div class="profilecontainer">
                            <div class="stripcontent" id="uploadpiccontent">
                                <%
                                    ArrayList userdetail = UserMaster.showUserDetailToAdminForSetting(session.getAttribute("userKey").toString());
                                    String country = null;
                                    String firstName = null;
                                    String lastName = null;
                                    String address = null;
                                    String city = null;
                                    String state = null;
                                    String zipcode = null;
                                    String email2 = null;
                                    String department = null;
                                    String userRole = null;
                                    String phone = null;
                                    String mobile = null;
                                    String userType2 = null;
                                    String fax = null;
                                    String password = null;
                                    CompleteUserBean cub = null;
                                    for (int i = 0; i < userdetail.size(); i++) {
                                        cub = (CompleteUserBean) userdetail.get(i);
                                        country = cub.getCountry();
                                        firstName = cub.getFirstName();
                                        lastName = cub.getLastName();
                                        address = cub.getAddress();
                                        city = cub.getCity();
                                        state = cub.getState();
                                        zipcode = cub.getZipcode();
                                        email2 = cub.getEmail();
                                        department = cub.getDepartment();
                                        userRole = cub.getManagerSupervisor();
                                        phone = cub.getPhone();
                                        mobile = cub.getCell();
                                        userType2 = cub.getUserType();
                                        fax = cub.getFax();
                                        password = cub.getPassword();
                                    }
                                %>
                                <div class="innercontent">
                                    <div class="label stripcontenttext">Current <span class="mandatory">*</span>
                                    </div>
                                    <input type="password" id="currentpasstxtid" name="currentpasstxt" class="textbox customtext" onblur="$('#currentTypePasswordError').html('');">
                                    <label class="error" id="currentTypePasswordError" for="currentpasstxtid" style="text-align:left;margin-left:107px;">
                                    </label>
                                </div>
                                <div class="innercontent">
                                    <div class="label stripcontenttext">New<span class="mandatory">*</span>
                                    </div>
                                    <input type="password" id="newpasstxtid" name="newpasstxt" class="textbox customtext" onblur="$('#typePasswordError').html('');">
                                    <label class="error" id="typePasswordError" for="newpasstxtid" style="text-align:left;margin-left:107px;">
                                    </label>
                                </div>
                                <div class="innercontent">
                                    <div class="label stripcontenttext">Re-type New
                                        <span class="mandatory">*</span>
                                    </div>
                                    <input type="password" id="retypepasstxtid" name="retypepasstxt" class="textbox customtext" onblur="$('#retypepasstxtid').html('');">
                                    <label class="error" id="reTypePasswordError" for="retypepasstxtid" style="text-align:left;margin-left:107px;">
                                    </label>
                                </div>
                            </div>
                            <div class="hori_seperator" style="margin-top: 24px;">
                            </div>
                            <div class="actionstrip">
                                <input type="button" class="gen-btn-Gray" style="height: 30px; width: 80px; font-size: 11px; margin-left: 200px;" id="passcancelbtnid" value="Cancel" onclick="$('#useracct_resetpass').slideUp();">
                                <input type="button" class="gen-btn-Orange" style="margin-left:15px;height: 30px; width: 80px; font-size: 11px;" id="passsavebtnid" value="Save" onclick="changeUserPassword('<%=password%>');">
                            </div>
                            <div class="innercontent">
                                <label class="error" style="height:25px;margin:0px;text-align:center;width:450px;" id="pass_err">
                                </label>
                            </div>
                        </div>
                    </form>
                    <%
                        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                        DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
                        String userStatus = null;
                        String newEmail=null;
                        String useRegisteredEmail=null;
                        String newMobileNo=null;
                        String workinghoursNotify = null;
                        String nonworkinghoursNotify = null;
                        String workinghoursMode = null;
                        String nonworkinghoursMode = null;
                        String stopNotify = null;
                        String stoptimeFrom = null;
                        String stoptimeTo = null;
                        String workingDays = null;
                        String day1 = null;
                        String day2 = null;
                        String day3 = null;
                        String day4 = null;
                        String day5 = null;
                        String day6 = null;
                        String day7 = null;
                        String sunFrom = null;
                        String sunTo = null;
                        String monFrom = null;
                        String monTo = null;
                        String tueFrom = null;
                        String tueTo = null;
                        String wedFrom = null;
                        String wedTo = null;
                        String thuFrom = null;
                        String thuTo = null;
                        String friFrom = null;
                        String friTo = null;
                        String satFrom = null;
                        String satTo = null;
                        ArrayList usersetting = UserSettingMaster.showAllUserSetting(session.getAttribute("userKey").toString());
                        UserSettingBean usb = null;
                        for (int i = 0;

                        i< usersetting.size ();
                        i

                        
                            ++) {
                            usb = (UserSettingBean) usersetting.get(i);
                            userStatus = usb.getUserStatus();
                            newEmail=usb.getNewEmail();
                            useRegisteredEmail=usb.getUseRegisteredEmail();
                            newMobileNo=usb.getNewMobileNo();
                            workinghoursNotify = usb.getWorkinghoursNotify();
                            nonworkinghoursNotify = usb.getNonworkinghoursNotify();
                            workinghoursMode = usb.getWorkinghoursMode();
                            nonworkinghoursMode = usb.getNonworkinghoursMode();
                            stopNotify = usb.getStopNotify();
                            stoptimeFrom = usb.getStoptimeFrom();
                            stoptimeTo = usb.getStoptimeTo();
                            workingDays = usb.getWorkingDays();
                            
                            day1 = workingDays.charAt(0) + "";
                            day2 = workingDays.charAt(1) + "";
                            day3 = workingDays.charAt(2) + "";
                            day4 = workingDays.charAt(3) + "";
                            day5 = workingDays.charAt(4) + "";
                            day6 = workingDays.charAt(5) + "";
                            day7 = workingDays.charAt(6) + "";
                            
                            sunFrom = usb.getSunFrom();
                            sunTo = usb.getSunTo();
                            monFrom = usb.getMonFrom();
                            monTo = usb.getMonTo();
                            tueFrom = usb.getTueFrom();
                            tueTo = usb.getTueTo();
                            wedFrom = usb.getWedFrom();
                            wedTo = usb.getWedTo();
                            thuFrom = usb.getThuFrom();
                            thuTo = usb.getThuTo();
                            friFrom = usb.getFriFrom();
                            friTo = usb.getFriTo();
                            satFrom = usb.getSatFrom();
                            satTo = usb.getSatTo();

                            if (sunFrom.startsWith("0")) {
                                sunFrom = sunFrom.substring(1);
                            }
                            if (sunTo.startsWith("0")) {
                                sunTo = sunTo.substring(1);
                            }
                            if (monFrom.startsWith("0")) {
                                monFrom = monFrom.substring(1);
                            }
                            if (monTo.startsWith("0")) {
                                monTo = monTo.substring(1);
                            }
                            if (tueFrom.startsWith("0")) {
                                tueFrom = tueFrom.substring(1);
                            }
                            if (tueTo.startsWith("0")) {
                                tueTo = tueTo.substring(1);
                            }
                            if (wedFrom.startsWith("0")) {
                                wedFrom = wedFrom.substring(1);
                            }
                            if (wedTo.startsWith("0")) {
                                wedTo = wedTo.substring(1);
                            }
                            if (thuFrom.startsWith("0")) {
                                thuFrom = thuFrom.substring(1);
                            }
                            if (thuTo.startsWith("0")) {
                                thuTo = thuTo.substring(1);
                            }
                            if (friFrom.startsWith("0")) {
                                friFrom = friFrom.substring(1);
                            }
                            if (friTo.startsWith("0")) {
                                friTo = friTo.substring(1);
                            }
                            if (satFrom.startsWith("0")) {
                                satFrom = satFrom.substring(1);
                            }
                            if (satTo.startsWith("0")) {
                                satTo = satTo.substring(1);
                            }
                        }
                    %>        
                    <div class="striphead" id="notifyheadid" onclick="openNCloseUsettingBox('useracct_notifyfrm');">
                        <label class="stripheadtext" id="notifyheadid">Notification
                            Settings</label>
                        <div class="right_arrow" id="notify_arrow">
                        </div>
                    </div>
                    <form name="useracct_notifyfrm" id="useracct_notifyfrm" style="display: none;" novalidate="novalidate">
                        <div class="profilecontainer">
                            <div class="stripcontent">
                                <div class="innercontent">
                                    <div class="label stripcontenttext">Email<span class="mandatory">*</span>
                                    </div>
                                    <input type="text" autocomplete="off" id="newEmail" name="altemailtxt" value="<%=newEmail %>" class="textbox customtext">
                                    <label id="newEmailError" class="error" style="width:150px; margin-left:107px;text-align:left;display:none">
                                    Enter valid email id 
                                    </label>
                                </div>
                                <div class="innercontent">
                                    <div class="checkContainer" style="margin-left:43px;">
                                        <input type="checkbox" id="useRegisteredEmail" name="altemailtxt" <% if(useRegisteredEmail.equals("yes")){ out.print("checked"); } %> style="float: left; margin-top: 0px; margin-left:-18px; cursor: pointer;">
                                        <div>
                                        </div>
                                    </div>
                                    <label class="label stripcontenttext"  for="altemailchkid" style="text-align: left; width: 300px;">Use
                                        registered email address to send notifications
                                    </label>
                                </div>
                                <div class="innercontent">
                                    <div class="label stripcontenttext">Mobile</div>
                                    <input type="text" autocomplete="off" id="newMobileNo" name="altmobiletxt" value="<%=newMobileNo %>" class="textbox customtext">
                                    <label id="newMobileNoError" class="error" style="width:150px !important; margin-left:107px !important;text-align:left !important;display:none">
                                    Enter valid mobile no. 
                                    </label>
                                </div>
                            </div>
                            <div class="notify_content_two">
                                <label class="label stripcontenttext" style="text-align: left; width: 300px;margin-left:10px"><b>Notifiaction Schedule:</b></label>
                            </div>
                            <div class="notify_content_one" style="border:none;">
                                <div class="stripcontent">
                                    <div class="innercontent" style="width:300px;">
                                        <div class="checkContainer" style="margin-left: 43px;">
                                            <input type="checkbox" <% if(workinghoursNotify.equals ( 
                                                    "yes")){ out.print("checked");
                                                } %> id="workinghoursNotify" name="whchktxt" style="float: left; margin-top: 0px; margin-left:-18px; cursor: pointer;">
                                            <div>
                                            </div>
                                        </div>
                                        <label class="label stripcontenttext" for="whchkid" style="width: 130px; text-align:left;">During
                                            working hours
                                        </label>
                                        <select name="whselectname" id="workinghoursMode" class="selectbox cs hasCustomSelect" style="width: 103px; -webkit-appearance: menulist-button; position: absolute; opacity: 0; height: 28px; font-size: 12px;">
                                            <option value="email" <% if(workinghoursMode.equals ("email")){ out.print("selected"); } %> >Email</option>
                                            <option value="sms" <% if(workinghoursMode.equals ("sms")){ out.print("selected"); } %> >SMS</option>
                                            <option value="both" <% if(workinghoursMode.equals ("both")){ out.print("selected"); } %>>Both</option>
                                        </select>
                                        <span class="customSelect selectbox cs" style="width: 80px; display: inline-block;">
                                            <span class="customSelectInner" style="width: 80px; display: inline-block;">Email</span>
                                        </span>
                                    </div>
                                    <div class="innercontent" style="width:300px;">
                                        <div class="checkContainer" style="margin-left: 43px;">
                                            <input type="checkbox" <% if(nonworkinghoursNotify.equals ( 
                                                    "yes")){ out.print("checked");
                                                } %> id="nonworkinghoursNotify" name="outsidewhchktxt" style="float: left; margin-top: 0px; margin-left:-18px; cursor: pointer;">
                                            <div>
                                            </div>
                                        </div>
                                        <label class="label stripcontenttext" for="outsidewhchkid" style="width: 130px; text-align:left;">Outside
                                            working hours
                                        </label>
                                        <select id="nonworkinghoursMode" name="outsidewhselectname" class="selectbox cs hasCustomSelect" style="width: 103px; -webkit-appearance: menulist-button; position: absolute; opacity: 0; height: 28px; font-size: 12px;">
                                            <option value="email" <% if(nonworkinghoursMode.equals ("email")){ out.print("selected"); } %>>Email</option>
                                            <option value="sms" <% if(nonworkinghoursMode.equals ("sms")){ out.print("selected"); } %>>SMS</option>
                                            <option value="both" <% if(nonworkinghoursMode.equals ("both")){ out.print("selected"); } %>>Both</option>
                                        </select>
                                        <span class="customSelect selectbox cs" style="width: 80px; display: inline-block;">
                                            <span class="customSelectInner" style="width: 80px; display: inline-block;">Email</span>
                                        </span>
                                    </div>
                                    <div class="innercontent" style="width: 300px;">
                                        <div class="checkContainer" style="margin-left: 43px;">
                                            <input type="checkbox" <% if(stopNotify.equals ( 
                                                    "yes")){ out.print("checked");
                                                } %>  id="stopNotify" name="stopnotifychkid" style="float: left; margin-top: 0px; margin-left:-18px; cursor: pointer;">
                                            <div>
                                            </div>
                                        </div>
                                        <label class="label stripcontenttext" for="stopnotifychkid" style="width: 200px; text-align:left;">Stop all
                                            notifications between
                                        </label>
                                    </div>
                                    <div class="calender">
                                        <div class="innercontent calender" style="width: 350px;">
                                            <input type="text" autocomplete="off"  id="stoptimeFrom" value="<%=df2.format(df.parse(stoptimeFrom)) %>" class="textbox customtext hasDatepicker" style="margin-left:45px; width:108px;" disabled="">
                                            <label class="label stripcontenttext" style="margin-left:20px;width: 20px; text-align:left;">AND</label>
                                            <input type="text" autocomplete="off" id="stoptimeTo" value="<%=df2.format(df.parse(stoptimeTo)) %>" class="textbox customtext calender hasDatepicker" style="width:108px;" id="stopnotify_todatepickerid" disabled="">
                                        </div>
                                    </div>
                                    <div class="hori_seperator" style="margin-top: 24px;">
                                    </div>
                                    <div class="actionstrip" style="text-align:left;">
                                        <input type="button" class="gen-btn-Gray" style="height: 30px; width: 80px; font-size: 11px; margin-left: 177px;" id="notify_cancelbtnid" value="Cancel">
                                        <input type="button" class="gen-btn-Orange" style="margin-left:15px;height: 30px; width: 80px; font-size: 11px;" id="notify_savebtnid" value="Save" onclick="updateNotificationSetting()">
                                    </div>
                                    <label class="error" style="height:20px;margin-left:300px" id="notify_err">
                                    </label>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="striphead" id="workinghoursheadid" onclick="openNCloseUsettingBox('useracct_whfrm');">
                        <label class="stripheadtext" id="workinghoursheadid">Working
                            Hours Settings</label>
                        <div class="right_arrow" id="wh_arrow">
                        </div>
                    </div>
                    <form name="useracct_whfrm" id="useracct_whfrm" style="display: none;" action="UpdateWorkingHours" method="post">
                        <div class="notify_content_one">
                            <div class="innercontent" style="width: 700px; margin-bottom: 10px;">
                                <div class="checkContainer" style="margin-left: 43px;">
                                    <input type="checkbox" id="sundaychkid" name="sundaychktxt" style="margin-top: 0px; cursor: pointer;" <% if(day1.equals ( 
                                            "1")){ out.print("checked");
                                        }%>>
                                    <div>
                                    </div>
                                </div>
                                <label class="label stripcontenttext" for="sundaychkid" style="margin-left: 10px; width: 80px; text-align: left;">Sunday</label>
                                <select id="sundayfromtimeid" name="sundayfromtimetxt" class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (sunFrom.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%> ><%=i%>:00:00</option>
                                    <% }%>
                                </select>
                                <label class="label stripcontenttext" style="width: 30px; margin-left: 20px; text-align: left; float: none;">To
                                </label>
                                <select id="sundaytotimeid" name="sundaytotimetxt" class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (sunTo.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>
                            </div>
                            <div class="innercontent" style="width: 700px; margin-top: 0px; margin-bottom: 10px;">
                                <div class="checkContainer" style="margin-left: 43px;">
                                    <input type="checkbox" id="mondaychkid" name="mondaychktxt" style="margin-top: 0px; cursor: pointer;" <% if(day2.equals ( 
                                            "2")){ out.print("checked");
                                        }%>>
                                    <div>
                                    </div>
                                </div>
                                <label class="label stripcontenttext" for="mondaychkid" style="margin-left: 10px; width: 80px; text-align: left;">Monday</label>
                                <select id="mondayfromtimeid" name="mondayfromtimetxt" class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (monFrom.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>
                                <label class="label stripcontenttext" style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
                                </label>
                                <select id="mondaytotimeid" name="mondaytotimetxt" class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (monTo.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>
                            </div>
                            <div class="innercontent" style="width: 700px; margin-top: 0px; margin-bottom: 10px;">
                                <div class="checkContainer" style="margin-left: 43px;">
                                    <input type="checkbox" id="tuesdaychkid" name="tuesdaychktxt" style="margin-top: 0px; cursor: pointer;" <% if(day3.equals ( 
                                            "3")){ out.print("checked");
                                        }%>>
                                    <div>
                                    </div>
                                </div>
                                <label class="label stripcontenttext" for="tuesdaychkid" style="margin-left: 10px; width: 80px; text-align: left;">Tuesday
                                </label>
                                <select class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="tuesdayfromtimeid" name="tuesdayfromtimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (tueFrom.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                                <label class="label stripcontenttext" style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
                                </label>
                                <select  class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="tuesdaytotimeid" name="tuesdaytotimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (tueTo.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                            </div>
                            <div class="innercontent" style="width: 700px; margin-top: 0px; margin-bottom: 10px;">
                                <div class="checkContainer" style="margin-left: 43px;">
                                    <input type="checkbox" id="wednesdaychkid" name="wednesdaychktxt" style="margin-top: 0px; cursor: pointer;" <% if(day4.equals ( 
                                            "4")){ out.print("checked");
                                        }%>>
                                    <div>
                                    </div>
                                </div>
                                <label class="label stripcontenttext" for="wednesdaychkid" style="margin-left: 10px; width: 80px; text-align: left;">Wednesday</label>
                                <select  class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="wednesdayfromtimeid" name="wednesdayfromtimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (wedFrom.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                                <label class="label stripcontenttext" style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
                                </label>
                                <select class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="wednesdaytotimeid" name="wednesdaytotimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (wedTo.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                            </div>
                            <div class="innercontent" style="width: 700px; margin-top: 0px; margin-bottom: 10px;">
                                <div class="checkContainer" style="margin-left: 43px;">
                                    <input type="checkbox" id="thursdaychkid" name="thursdaychktxt" style="margin-top: 0px; cursor: pointer;" <% if(day5.equals ( 
                                            "5")){ out.print("checked");
                                        }%>>
                                    <div>
                                    </div>
                                </div>
                                <label class="label stripcontenttext" for="thursdaychkid" style="margin-left: 10px; width: 80px; text-align: left;">Thursday</label>
                                <select  class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="thursdayfromtimeid" name="thursdayfromtimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (thuFrom.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                                <label class="label stripcontenttext" style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
                                </label>
                                <select  class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="thursdaytotimeid" name="thursdaytotimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (thuTo.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                            </div>
                            <div class="innercontent" style="width: 700px; margin-top: 0px; margin-bottom: 10px;">
                                <div class="checkContainer" style="margin-left: 43px;">
                                    <input type="checkbox" id="fridaychkid" name="fridaychktxt" style="margin-top: 0px; cursor: pointer;" <% if(day6.equals ( 
                                            "6")){ out.print("checked");
                                        }%>>
                                    <div>
                                    </div>
                                </div>
                                <label class="label stripcontenttext" for="fridaychkid" style="margin-left: 10px; width: 80px; text-align: left; ">Friday</label>
                                <select  class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="fridayfromtimeid" name="fridayfromtimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (friFrom.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                                <label class="label stripcontenttext" style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
                                </label>
                                <select class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="fridaytotimeid" name="fridaytotimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (friTo.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                            </div>
                            <div class="innercontent" style="width: 700px; margin-top: 0px; margin-bottom: 10px;">
                                <div class="checkContainer" style="margin-left: 43px;">
                                    <input type="checkbox" id="saturdaychkid" name="saturdaychktxt" style="margin-top: 0px; cursor: pointer;" <% if(day6.equals ( 
                                            "6")){ out.print("checked");
                                        }%>>
                                    <div>
                                    </div>
                                </div>
                                <label class="label stripcontenttext" for="saturdaychkid" style="margin-left: 10px; width: 80px; text-align: left;">Saturday</label>
                                <select  class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="saturdayfromtimeid" name="saturdayfromtimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (satFrom.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                                <label class="label stripcontenttext" style="width: 30px; margin-left: 20px; text-align: left; float:none;">To
                                </label>
                                <select  class="hasCustomSelect" style="margin-bottom: 5px;-webkit-appearance: menulist-button;width: 80px;height: 28px;font-size: 12px;background-color: rgb(243, 243, 243);border: 1px solid rgb(165, 183, 187);" id="saturdaytotimeid" name="saturdaytotimetxt">
                                    <%
                                        for (int i = 0;
                                        i <= 23; i

                                        
                                            ++) {
                                    %>
                                    <option value="<%=i%>:00:00" <% if (satTo.equals(i + ":00:00")) {
                                            out.print("selected");
                                        }%>><%=i%>:00:00</option>
                                    <% }%>
                                </select>

                            </div>
                            <div class="innercontent" style="margin:0px;">
                                <div class="hori_seperator" style="margin-top:14px;">
                                </div>
                            </div>
                            <div class="innercontent" style="margin-top:0px;">
                                <input type="button" class="gen-btn-Gray" style="margin-left:160px; height: 30px; width: 80px; font-size: 11px;" id="wh_cancelbtnid" value="Cancel">
                                <input type="button" class="gen-btn-Orange" style="margin-left:15px;height: 30px; width: 80px; font-size: 11px;" id="wh_savebtnid" onclick="saveWorkinghoursSettings()" value="Save">
                            </div>
                            <label class="error" style="height:20px;margin-left:300px" id="wh_err">
                            </label>
                        </div>
                    </form>
                    <div class="striphead" id="updt_dtl" onclick="openNCloseUsettingBox('updt_dtl_bx'); updt_dtl();"> 
                        <label class="stripheadtext">User Details</label>
                        <div class="right_arrow" id="updt_dtl_arw">
                        </div>
                    </div>
                                <form class="contentcontainer content" action="UpdateUserSettingByUser" method="post" name="updt_dtl_bx" id="updt_dtl_bx" style="width: 96%; height: auto; display: none;">
                        <div style="width:100%;"> 
                            <div class="leftcontent" style="width:45%;float: left;height: auto;">
                                <div class="row">
                                    <div class="label">
                                        Country
                                    </div>
                                    <input type="text" autocomplete="off" name="country" class="textbox" value="<%=CountryMaster.showCountryFromKey(country)%>" readonly>
                                </div>
                                <div class="row">
                                    <div class="label">
                                        Company
                                    </div>
                                    <input type="text" autocomplete="off" name="companyName" class="textbox" value="<%=session.getAttribute("companyName").toString()%>" readonly>
                                </div>
                                <div class="row">
                                    <div class="label">
                                        First Name<span class="mandatory">*</span>
                                    </div>
                                    <input type="text" autocomplete="off" name="firstName" id="frstnm" class="required textbox" value="<%=firstName%>" onBlur="vldt_usr_dtl();" style="border-color: #a5b7bb;">
                                    <span id="frstnm_vldtn" style="color: red; float: left; width: 185px; margin-left: 200px; display: none;">Enter first name</span>
                                </div>
                                <div class="row">
                                    <div class="label">
                                        Last Name<span class="mandatory">*</span>
                                    </div>
                                    <input type="text" autocomplete="off" name="lastName" id="lstnm" class="required textbox" value="<%=lastName%>" onBlur="vldt_usr_dtl();" style="border-color: #a5b7bb;">
                                    <span id="lstnm_vldtn" style="color: red; float: left; width: 185px; margin-left: 200px; display: none;">Enter last name</span>
                                </div>
                                <div class="row">
                                    <div class="label">Address</div>
                                    <input type="text" autocomplete="off" name="adrs" id="adrs" class="textbox" value="<%=address%>">
                                </div>
                                <div class="row">
                                    <div class="label">City</div>
                                    <input type="text" autocomplete="off" name="city" id="cty" class="textbox" value="<%=city%>">
                                </div>
                                <div class="row">
                                    <div class="label">State/Province</div>
                                    <input type="text" autocomplete="off" name="st" id="st" class="textbox" value="<%=StateMaster.showStateFromKey(state)%>" readonly>
                                </div>
                                <div class="row">
                                    <div class="label">Zip Code/Postal Code</div>
                                    <input type="text" autocomplete="off" name="zipcode" id="zpcd" class="textbox" value="<%=zipcode%>">
                                </div>
                            </div>
                            <!--div class="formseparator" style="margin-top: 20px; height: 480px;">
                            </div-->
                            <div class="rightcontent" style="width:45%;float: right;height: auto;">
                                <!--<div class="row" style="height: 100px;">-->
                                <!--<div class="label">
                                                User Type<span class="mandatory">*</span>
                                </div>
                                <fieldset class="fieldset" style="width: 160px;">
                                <legend> User Type </legend>-->
                                <input type="hidden" value="Intranet User" name="usertype" id="internetuser">
                                <!--                                                        <input type="radio" style="vertical-align: middle; font-size: 12px;margin:0px;" disabled name="usertype" id="internetuser"
                                                                                                        checked class="radiobtn required" value="IntranetUser"
                                                                                                        style="vertical-align:middle;font-size: 12px;" />
                                <label for="internetuser"
                                                                                                        style="vertical-align: middle; font-size: 12px; color: #282828">
                                                                                                        Company User (Intranet) &nbsp;</label>
                                <BR /> -->
                                <!--							<input type="radio" id="transuser"
                                                                                                       name="usertype"  readonly disabled class="radiobtn" style="vertical-align: middle; font-size: 12px;margin:0px;"
                                                                                                        value="TransactionUser"
                                                                                                        style="vertical-align:middle;font-size: 12px;" />
                                <label for="transuser"
                                                                                                        style="vertical-align: middle; font-size: 12px; color: #282828">
                                                                                                        Regular UserIndividual User (Regular) &nbsp;</label>
                                <label for="usertype" checked generated="true"
                                                                                                        class="error" style="margin-left: 0px !important; width: 150px;">
                                </label>-->
                                <!--</fieldset>-->
                                <!--</div>-->
                                <div class="row">
                                    <div class="label">
                                        Email
                                    </div>
                                    <input type="text" autocomplete="off" name="email" id="email" readonly class="required textbox email" value="<%=email2%>">
                                </div>
                                <div class="row">
                                    <div class="label">Department</div>
                                    <input type="text" autocomplete="off" name="department" class="textbox" value="<%=department%>" readonly>
                                </div>
                                <div class="row">
                                    <div class="label">Manager/Supervisor</div>
                                    <input type="text" autocomplete="off" name="userRole" class="textbox" value="<%=userRole%>" readonly>
                                </div>
                                <div class="row">
                                    <div class="label">
                                        Phone
                                    </div>
                                    <input type="text" autocomplete="off" name="phone" id="phn" value="<%=phone%>" class="required textbox" onBlur="vldt_usr_dtl();" style="border-color: #a5b7bb;" readonly >
                                    <span id="phn_vldtn" style="color: red; float: left; width: 185px; margin-left: 200px; display: none;">Enter valid phone no.</span>
                                </div>
                                <div class="row">
                                    <div class="label">Cell</div>
                                    <input type="text" autocomplete="off" name="mobile" id="mbl" class="textbox" value="<%=mobile%>" onBlur="vldt_usr_dtl();" style="border-color: #a5b7bb;">
                                    <span id="mbl_vldtn" style="color: red; float: left; width: 185px; margin-left: 200px; display: none;">Enter valid cell no.</span>
                                </div>
                                <div class="row">
                                    <div class="label">Fax</div>
                                    <input type="text" autocomplete="off" name="fax" id="fx" class="textbox" value="<%=fax%>">
                                </div>
                            </div>
                        </div>
                        <div style="margin-left: 380px; margin-top: 45px; float: left;">
                            <input type="button" value="Reset" onClick="document.getElementById( & #39; updt_dtl_bx & #39; ).reset();" class="gen-btn-Gray">
                            <input type="submit" style="margin-left: 60px;" value="Update" class="gen-btn-Orange" onClick="sv_usr_dtl( & #39; info@webkrit.com & #39; )">
                        </div>
                        <input type="hidden" name="userKey" value="<%=session.getAttribute("userKey")%>">
                        <input type="hidden" name="userType" value="<%=userType2%>">
                        <input type="hidden" name="redirectTo" value="userAccountSetting">
                    </form>
                </div>
            </div>
        </div>
        <%@include file="_footer.jsp"%>
        <div>
        </div>
    </div>
    <%@include file="_invite.jsp" %>
    <script type="text/JavaScript" src="inside/restrict_menu.js">
    </script>
    <script>


                                        $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt_fieldvalidator.js");
                                        $.getScript("/SupplyMedium/Views/Registration/js/regvalidator.js");
                                        $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt.js", function(data, textStatus, jqxhr) {
                                        userOnload();
                                        });

    </script>
</body>
</html>
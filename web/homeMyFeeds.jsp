<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.FeedBean"%>
<%@page import="supply.medium.home.database.FeedMaster"%>
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
        <LINK rel="stylesheet" href="inside/userfeed.css">
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
        <script type="text/JavaScript" src="inside/folder.js">
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
        <script type="text/JavaScript" src="inside/myfeed.js">
        </script>
        <title>Supply Medium</title>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
    </head>
    <body onLoad="lockUnlock('webkrit_content_loader');splitFeedKeys();<% if(request.getParameter("folder")!=null){ %> get_user_folders('<%=request.getParameter("folder") %>'); <% }else { %>get_user_folders('none');<%}%>">
    <link rel="stylesheet" href="inside/userheader.css">
    <link rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp" %>
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <link rel="stylesheet" href="inside/commonlayout.css">
        <link rel="stylesheet" href="inside/Custome_Buttons.css">
        <link rel="stylesheet" href="inside/userfeed.css">
        <div id="ad_usr_fldr" class="rfq_post_confirmation" style="height: 140px; margin-left: 35%; width: 340px; display: none;">
            <center>
                <p>Enter folder name<br>
                    <br>
                    <input style="margin-left: 0px;width: 300px;height:30px;" type="text" autocomplete="off" class="feedTitle" name="fldr_nm" id="fldr_nm" placeholder="Folder name:">
                    <br>
                    <br>
                    <br>
                    <input type="button" class="gen-btn-blue" value="Save" onClick="add_user_folder();$(&#39;#ad_usr_fldr&#39;).fadeOut();" style="margin-left:10px;">
                    <input type="button" class="gen-btn-blue" value="Close" onClick="$(&#39;#ad_usr_fldr&#39;).fadeOut();" style="margin-left:10px;">
                </p>
            </center>
        </div>
        <div id="ad_usr_fldr_fl" class="rfq_post_confirmation" style="height: 140px; margin-left: 35%; width: 340px; display: none;">
            <center>
                <p>Select folder file<br>
                    <br>
                </p>
                <form name="frm_fl_nm" action="FolderManagementMyFeed" method="post" encType="multipart/form-data" >
                    <input type="hidden" name="folderName" id="folderName" >
                    <input style="margin-left: 0px;width: 300px;height:30px;background:#f1f1f1 ;border:0px;  " type="file" class="feedTitle" name="fileName" id="fileName">
                    <br>
                    <br>
                    <br>
                    <input type="submit" class="gen-btn-blue" value="Save" onClick="return add_user_folder_file();$(&#39;#ad_usr_fldr_fl&#39;).fadeOut();" style="margin-left:10px;">
                    <input type="button" class="gen-btn-blue" value="Close" onClick="$(&#39;#ad_usr_fldr_fl&#39;).fadeOut();" style="margin-left:10px;">
                </form>
                <p>
                </p>
            </center>
        </div>
        <div class="pagetitlecontainer">
            <div class="pagetitle">My Feeds Page</div>
        </div>
        <div class="page">
            <div class="contentcontainer">
                <div class="feed_content">
                    <div id="user_page_content">
                        <div style="width:230px;float: left;background: #FFFFFF">
                            <div style="width:230px;height:250px; float: left;border: 1px solid #d3d3d3;">
                                <div class="recommend_head" style="width:225px;text-align: center;">My Briefcase (Folders)</div>
                                <p style="float: right;">
                                    <input type="button" id="add_folder_btn" class="add_btn" title="Add Folder" onClick="$(&#39;#ad_usr_fldr&#39;).fadeIn();$(&#39;#fldr_nm&#39;).val(&#39;&#39;);">
                                    <input type="button" id="del_folder_btn" class="del_btn" title="Delete Folder" onClick="delete_user_folder();">
                                </p>
                                <div style="overflow: auto;width:230px;height:190px;">
                                    <ul class="folder_ul" id="usr_fld_lst">                                      
                                    </ul>
                                </div>
                            </div>
                            <div style="width:230px;height:350px;float: left;border: 1px solid #d3d3d3">
                                <div class="recommend_head" style="width:225px;text-align: center;">My Briefcase (Files)</div>
                                <div style="float: right;">
                                    <input type="button" id="upload_file" class="import_btn" title="Import File" onClick="$(&#39;#ad_usr_fldr_fl&#39;).fadeIn();">
                                    <a onClick="if(slctd_fl2.trim()!==''){this.href=$(&#39;#slected_user_folder_url&#39;).val()+&#39;/&#39;+slctd_fl2;}else { ShowToast('Select file to download','2000'); }" target="_blank">
                                        <input type="button" id="download_file" class="export_btn" title="Download File">
                                    </a>
                                    <input type="button" id="del_folder_btn" class="del_btn" title="Delete File" onClick="delete_user_file();">
                                </div>
                                <div style="overflow: auto;width:230px;height:290px;">
                                    <ul class="folder_ul" id="usr_fld_fls_lst">                                        
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div style="float: right;width:760px;">
                            <!-- New feed creation -->
                            <div id="newfeed" class="newfeed" style="width:750px;">
                                <form onsubmit="return feedValidater($('#feedTitle').val(),$('#feedDesc').val());" id="user_feed_frm" style="width:750px;" name="user_feed_frm" action="CreateFeed" method="post" enctype="multipart/form-data">
                                    <input type="hidden" name="redirectTo" value="myFeed"/>
                                    <input type="hidden" name="feedType" value="user feed"/>
                                    <input type="hidden" name="departmentKey" value="0" />
                                    <input type="hidden" name="isFeedCompanyWide" value="no" />
                                    <label class="feed_err_lbl" id="feed_err_lbl">
                                    </label>
                                    <label class="file_name_lbl" id="file_name_lbl" style="color: #f37d01;font-family:Verdana; font-size: 18px;">Feeds</label>
                                    <div class="feed_btns">
                                        <!--<div style="margin:auto 50px;float:right;">-->
                                        <input type="submit" class="gen-btn-Orange post_feed_btn" id="post_feed_btn" value="Post">
                                        <!--</div>-->
                                        <div class="new_feed_image_container">
                                            <input type="file" class="new_feed_image" name="new_feed_image" id="new_feed_image" onchange="getFileName();">
                                        </div>
                                    </div>
                                    <input style="margin-left: 0px;" type="text" autocomplete="off" class="feedTitle" name="feedTitle" id="feedTitle" placeholder="Feed Title:">
                                    <textarea style="margin-left: 0px;" class="feedDesc" name="feedDescription" id="feedDesc" placeholder="Feed Description:"></textarea>
                                </form>
                            </div>
                            <div class="no_feeds" id="no_feeds" style="display: none;"> No announcements available yet
                            </div>
                            <!-- List down the old feeds -->
                            <div id="feeds" class="feeds" style="margin-left:0px;">
                                    <%                                        
                                        String feedKey = null;
                                        String feedTitle = null;
                                        String feedDescription = null;
                                        String filePath = null;
                                        String postedOn = null;
                                        String fechedFeedKeys="";
                                        ArrayList feedList = FeedMaster.showUserFeed(session.getAttribute("userKey").toString());
                                        FeedBean sbcb = null;
                                        String feedusername=null;
                                        for (int i = 0; i < feedList.size(); i++) {
                                            sbcb = (FeedBean) feedList.get(i);
                                            feedKey=sbcb.getFeedKey();
                                            feedTitle=sbcb.getFeedTitle();
                                            feedDescription=sbcb.getFeedDescription();
                                            postedOn=sbcb.getPostedOn();
                                            fechedFeedKeys+=feedKey+",";
                                            UserBean ub=UserMaster.showUserDetailsByUserKey(sbcb.getUserKey());
                                            feedusername=ub.getFirstName()+" "+ub.getLastName();
                                            filePath=SmProperties.pathUrl+"/"+sbcb.getFilePath();
                                    %>
                                    <div id="feed_1" class="feed">                                        
                                        <div class="publisher_details">
                                            <div>
                                                <img src="cropData/users/<%=sbcb.getUserKey()%>.png" class="publisher_photo">
                                            </div>
                                            <div class="publisher_name"><%=feedusername%></div>
                                            <div class="date"><%=postedOn %></div>
                                            <%
                                            if(session.getAttribute("userKey").toString().equals(sbcb.getUserKey()))
                                            {
                                            %>
                                            <input type="button" class="feed_del" id="feed_del_1" onClick="showWarning('Do you want to delete this post?');document.getElementById('confirmFeedRemove').href='RemoveFeed?redirectTo=myFeed&feedKey=<%=feedKey %>';">
                                            <%
                                            }
                                            %>
                                        </div>
                                        <div class="feed_head"><%=feedTitle %></div>
                                        <div class="feed_summary">
                                            <% if(!sbcb.getFilePath().equals("null") && sbcb.getFilePath()!=null && !sbcb.getFilePath().equals(null)){ %><img src="<%=filePath %>" id="thumb_1" onClick="showFeedImage(this.src)" class="feed_image" width="200" height="150"><% } %>
                                            <p class="feed_text"><%=feedDescription %></p>
                                            <p>
                                            </p>
                                        </div>
                                        <div id="mn_<%=feedKey %>" style="height:0px;margin-top:-50px;">                                            
                                        </div>
                                    </div>
                                    <%
                                        }
                                    %> 
                                    <input type="hidden" id="fechedFeedKeys" value="<%=fechedFeedKeys %>">
                                </div>
                        </div>
                        <div id="feed_loader" style="display: none;">
                            <img src="inside/ajax_loader.gif" style="margin-left:480px;">
                        </div>
                        <div id="no_more_feed" style="">
                            No more feeds available
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <title>Toast Window </title>
        <link rel="stylesheet" href="inside/Cus_Toast.css">
        <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
        <div id="Toast_Window" style="display: none;">
            <p class="Toast_Data">Folder created</p>
        </div>
        <title>Insert title here</title>
        <link rel="stylesheet" href="inside/Custome_Popup.css">
        <link rel="stylesheet" href="inside/original_image_popup.css">
        <div id="image_container" style="display: none;">
            <center>
                <div id="image_popup">
                    <div id="image_head">
                        <input id="Popup_Cancel" style="float:right;font-size:20px;background-color:transparent;border:none;color:#ccc;cursor:pointer;padding-right:5px;" type="button" value="X">
                    </div>
                    <div id="image_body">
                    </div>
                </div>
            </center>
        </div>
        <%@include file="_footer.jsp" %>
        <div>
        </div>
        <LINK rel="stylesheet" href="inside/Custome_Popup.css">
        <LINK rel="stylesheet" href="inside/popup_warning.css">
        <SCRIPT type="text/javascript">
            $(function() {

                $(".Cus_Popup_Close").click(function() {
                    $("#warning_container").hide();
                });
                $("#Popup_Cancel").click(function() {
                    $("#warning_container").hide();
                });

                $('#okbtn').click(
                        function()
                        {
                            deleteConfirm();

                            $("#warning_container").hide();
                        }
                );

                $('#Popup_Cancel').click(
                        function()
                        {
                            alert('ok');
                            //cancelInfo();

                            $("#warning_container").hide();
                        }
                );
            }
            );

            function showWarning(message)
            {
                $('#war_message').text(message);

                $("#warning_container").show();

                $("#Popup_Cancel").focus();
            }

        </SCRIPT>
        <DIV style="display: none;" id="warning_container">
            <DIV id="warning_popup">
                <DIV id="war_head">
                    <LABEL id="war_head_title">Warning</LABEL>
                </DIV>
                <DIV id="war_body">
                    <LABEL id="war_message">
                    </LABEL>
                    <DIV id="war_btns">
                        <a id="confirmFeedRemove"><INPUT style="margin-right: 30px; float: left;" id="okbtn" class="pop-button pop-button-White" value="Yes" type="button"></a>
                        <INPUT style="float: left;" id="Popup_Cancel" class="pop-button pop-button-White" value="No" type="button" onclick="$('#warning_container').hide();">
                    </DIV>
                </DIV>
            </DIV>
        </DIV>
        <LINK rel="stylesheet" href="inside/Custome_Popup.css">
        <LINK rel="stylesheet" href="inside/ResetCSS.css">
        <SCRIPT type="text/javascript">
            $(function() {

                $(".Gen_Cus_Popup_Close").click(function() {
                    $("#add_watchlist_popup").hide();
                });
                $("#cancel_watchlist").click(function() {
                    $("#add_watchlist_popup").hide();
                });

                $('#new_watchlist_input').keyup(function()
                {
                    $('#new_watchlist_input_err').text("");
                });
            }
            );
        </SCRIPT>
        <!--  <script type="text/JavaScript"
                src="/SupplyMedium/Views/Feed/js/userfeed.js">
        </script>-->
    </div>
    <%@include file="_invite.jsp" %>
    <script type="text/JavaScript" src="inside/restrict_menu.js">
    </script>
    <script>


    $.getScript( "/SupplyMedium/Views/UserMgmt/js/usermgmt_fieldvalidator.js");
    $.getScript( "/SupplyMedium/Views/Registration/js/regvalidator.js");
    $.getScript( "/SupplyMedium/Views/UserMgmt/js/usermgmt.js", function( data, textStatus, jqxhr ) {
            userOnload();
	 
            });

    </script>
</body>
</html>
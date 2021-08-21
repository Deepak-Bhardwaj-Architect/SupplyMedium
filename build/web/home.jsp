<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.home.bean.FeedBean"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.database.FeedMaster"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <LINK rel="stylesheet" 
              href="inside/jquery-ui-1.9.2.custom_spinner.css" />
        <LINK rel="stylesheet" 
              href="inside/commonlayout.css" />
        <LINK rel="stylesheet" href="inside/elements.css">
        <LINK rel="stylesheet" href="inside/Custome_Buttons.css">
        <LINK rel="stylesheet" 
              href="inside/jquery.mCustomScrollbar.css">
        <LINK rel="stylesheet" href="inside/user_home.css">
        <LINK rel="stylesheet" href="inside/dilbag.css">
        <!-- Chat JS style -->
        <LINK 
            rel="stylesheet" type="text/css" href="inside/jquery.chatjs.css">
        <SCRIPT type="text/JavaScript" src="inside/SMNamespace.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-1.9.0.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.10.0.custom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery-ui-1.9.2.custom_spinner.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/filterlist.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.customSelect.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.mCustomScrollbar.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.tooltipster.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.dataTables.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/common.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/jquery.validate.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/additional-methods.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dropdownfiller.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/SMNamespace(1).js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/footer.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/ajax_loader.js">
        </SCRIPT>
        <!-- ChatJS and dependencies -->
        <SCRIPT type="text/javascript" src="inside/jquery.chatjs.longpollingadapter.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.autosize.min.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.activity-indicator-1.0.0.min.js">
        </SCRIPT>
        <SCRIPT type="text/javascript" src="inside/jquery.chatjs.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/user_home.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/dilbag.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/newsroom.js">
        </SCRIPT>
        <SCRIPT type="text/JavaScript" src="inside/watchlist.js">
        </SCRIPT>
        <TITLE>Supply Medium</TITLE>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onload="lockUnlock('webkrit_content_loader');splitFeedKeys();showWatchlist();chatList();" onkeydown="displayunicode(event);">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <DIV style="min-height: 700px; background-color: rgb(241, 241, 241);" id="mainpage" 
         class="mainpage">
        <LINK rel="stylesheet" href="inside/newsroom.css">
        <LINK rel="stylesheet" href="inside/newsroom_feed.css">
        <LINK rel="stylesheet" 
              href="inside/view_members.css">
        <!--<link rel="stylesheet" href="/SupplyMedium/dilbag.css">-->
        <!---->
        <div class="mainpage">
            <div class="pagetitlecontainer">
                <div class="pagetitle"> Newsroom </div>
            </div>
            <div class="contentcontainer">
                <div class="content">
                    <!--  	<div class="globalsearch">
                    <input type="text" autocomplete="off" placeholder="Search for first name, last name, email or business" class="searchtext">
                    </div>-->
                    <div class="contentbg">
                        <div class="content_left">
                            <div class="content_label content_label_selected" id="newsroom"><a href="home.jsp">Newsroom</a> 
                            </div>
                            <div class="content_label" id="create_watchlist" onclick="javascript:$('#add_watchlist_popup').show();">Create Watchlist
                            </div>
                            <div class="content_label_only" style="margin-top:20px;">Watchlist
                            </div>
                            <div id="watchlists">
                                <div class="content_label_sub" id="watchlist_1" onmouseover="javascript:document.getElementById('watchlist_del_1').style.display='block';" onmouseout="javascript:document.getElementById('watchlist_del_1').style.display='none';" style="margin-left:20px; width:160px;">
                                    <div class="watchlist_name_lbl" id="watchlist_1_name">mca</div>
                                    <input type="button" style="display: none;" class="delete_watchlist_btn" id="watchlist_del_1">
                                    <div></div>                                        
                                </div>
                            </div>
                        </div>
                        <div class="content_right">
                            <div class="cont_container">
                                <div class="content_title" id="NR_content_title">Newsroom
                                </div>
                                <div class="text_container" id="watchlist_head" style="display:none;">
                                    <input type="text" autocomplete="off" class="text_box" placeholder="Add Members to Watchlist" id="search_mem_textbox" onKeyUp="searchMemberToAdd(this.value);">
                                    <div class="view_mem" id="view_mem" onclick="javascript:$('#member_container').show();">View Members(count)</div>
                                    <div id="search_result" class="search_result" style="display: none;">
                                    </div>
                                </div>
                                <!--  <div class="new_feed_container">
                                <div class="button_container" style="float:right;width:70px;height:20px;">
                                <img src="/SupplyMedium/Views/NewsRoom/images/add_image.png" class="add_image">
                                <div class="post_but">Post</div>
                                </div>
                                <input type="text" autocomplete="off" class="feed_text_box" placeholder="Feed Title">
                                <textarea class="text_area">
                                </textarea>
                                </div>-->
                                <div id="newfeed" class="newfeed">
                                    <form id="user_feed_frm" onsubmit="return feedValidater($('#feedTitle').val(),$('#feedDesc').val());" style="float: left" name="user_feed_frm" action="CreateFeed" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="redirectTo" value="newsroom"/>
                                        <input type="hidden" name="feedType" value="user feed"/>
                                        <input type="hidden" name="departmentKey" value="0" />
                                        <input type="hidden" name="isFeedCompanyWide" value="no" />
                                        <label class="feed_err_lbl" id="feed_err_lbl">
                                        </label>
                                        <label class="file_name_lbl" id="file_name_lbl">
                                        </label>
                                        <div class="feed_btns">
                                            <input type="submit" class="gen-btn-Orange post_feed_btn" id="post_feed_btn" value="Post">
                                            <div class="new_feed_image_container">
                                                <input type="file" class="new_feed_image" name="new_feed_image" id="new_feed_image">
                                            </div>
                                        </div>
                                        <input type="text" autocomplete="off" class="feedTitle" name="feedTitle" id="feedTitle" placeholder="Feed Title:">
                                        <textarea class="feedDesc" name="feedDescription" id="feedDesc" placeholder="Feed Description:"></textarea>
                                    </form>
                                </div>
                                <div id="feeds" class="feeds">
                                    <%                                        
                                        String feedKey = null;
                                        String feedTitle = null;
                                        String feedDescription = null;
                                        String filePath = null;
                                        String postedOn = null;
                                        String fechedFeedKeys="";
                                        ArrayList feedList = FeedMaster.showNewsRoom(session.getAttribute("companyKey").toString());
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
                                            <input type="button" class="feed_del" id="feed_del_1" onClick="showWarning('Do you want to delete this post?');document.getElementById('confirmFeedRemove').href='RemoveFeed?redirectTo=newsroom&feedKey=<%=feedKey %>';">
                                            <%
                                            }
                                            %>
                                        </div>
                                        <div class="feed_head"><%=feedTitle %></div>
                                        <div class="feed_summary">
                                            <% if(!sbcb.getFilePath().equals("null") && sbcb.getFilePath()!=null && !sbcb.getFilePath().equals(null)){ %><img src="<%=filePath %>" id="thumb_1" onClick="showFeedImage(this.src)" class="feed_image" width="200" height="150" style="display: none;"><% } %>
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
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="_VendorsOfSameBusinessCategory.jsp"%>
        </div>
        <LINK rel="stylesheet" href="inside/Cus_Toast.css">
        <SCRIPT type="text/javascript">
            $(function() {
                $("#Open_Toast").click(function()
                {
                    ShowToast("Inforamtion Saved Successfully...", 1000);
                });
            });

            function ShowToast(Message, ShowTime)
            {
                if (typeof (ShowTime) === 'undefined')
                    ShowTime = 2000;

                var Toast_Obj = $("#Toast_Window");
                var outerWidth = $(Toast_Obj).outerWidth() / 2;
                //Toast_Obj.show(500);
                Toast_Obj.fadeIn(500);
                //Toast_Obj.slideToggle(500)
                $(".Toast_Data").html(Message);
                Toast_Obj.delay(ShowTime);
                //Toast_Obj.hide(500);
                Toast_Obj.fadeOut(2000);
                //Toast_Obj.slideToggle(500);
            }

        </SCRIPT>
        <!--  <input type="button" id="Open_Toast" value="AAAAAAAAAAAAAAAAAAAA"/>-->
        <DIV style="display: none;" id="Toast_Window">
            <P class="Toast_Data">
            </P>
        </DIV>
        <%@include file="_footer.jsp" %>
        <DIV>
        </DIV>
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
                            cancelInfo();

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
                        <INPUT style="float: left;" id="Popup_Cancel" class="pop-button pop-button-White" value="No" type="button">
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
        <DIV style="display: none;" id="add_watchlist_popup" class="Custome_Popup_Window">
            <TABLE>
                <TBODY>
                    <TR>
                        <TD style="vertical-align: middle;">
                            <DIV class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout">
                                <DIV class="Popup_Tilte_NewGroup Gen_Popup_Title ">					 	New Watchlist 
                                    Creation					 	
                                    <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                                    </DIV>
                                </DIV>
                                <DIV class="popupcontent">
                                    <DIV class="row">
                                        <DIV style="width: 150px;" class="label">Watchlist Name</DIV>
                                        <INPUT style="border: 1px solid rgb(165, 183, 187); width: 300px; height: 20px;" 
                                               id="new_watchlist_input" class="textbox required" name="new_watchlist_input" 
                                               type="text" autocomplete="off">
                                        <LABEL style="margin-left: 150px;" id="new_watchlist_input_err" 
                                               class="error">
                                        </LABEL>
                                    </DIV>
                                    <DIV style="margin-top: 60px; margin-left: 120px;" class="row">
                                        <INPUT style="margin-right: 20px;" id="cancel_watchlist" class="gen-btn-Gray" value="Cancel" onclick="$('#new_watchlist_input').val('');" type="button">
                                        <INPUT id="save_watchlist" class="gen-btn-Orange" value="Create" type="button" onclick="createWatchlist();">
                                    </DIV>
                                </DIV>
                            </DIV>
                        </TD>
                    </TR>
                </TBODY>
            </TABLE>
        </DIV>
        <SCRIPT>
            $(".Gen_Cus_Popup_Close").click(function()
            {
                $("#member_container").hide();
            });
        </SCRIPT>
        <DIV style="display: none;" id="member_container" class="container Cus_Popup_Outline">
            <DIV style="border-radius: 0px;" class="Popup_Tilte_NewGroup Gen_Popup_Title">
                <DIV style="padding: 0px 0px 0px 15px; float: left;">Members</DIV>
                <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close" onclick="javascript:$('#member_container').hide();"></DIV>
            </DIV>
            <DIV class="member_container">
                <DIV class="add_member_container">
                    <DIV id="members_count" class="add_member">
                    </DIV>
                    <DIV class="textbox_container">
                        <INPUT class="newsroom_textbox" type="text" autocomplete="off" id="userPartialMemberName"
                               placeholder="Find a Member" onkeyup="javascript:searchAddedMemberInWatchlist()">
                        <IMG style="float: left; cursor: pointer;" src="inside/search_lens.png">
                    </DIV>
                </DIV>
                <DIV class="button_container">
                    <DIV id="delete_members_btn" class="del_button" onclick="deleteMemberByWatchListMemberKey()">Delete</DIV>
                </DIV>
            </DIV>
            <DIV id="memberlist_container" class="memberlist_container">
            </DIV>
        </DIV>
        <LINK rel="stylesheet" href="inside/Custome_Popup.css">
        <LINK rel="stylesheet" href="inside/original_image_popup.css">
        <SCRIPT type="text/javascript">
            $(function()
            {

                $("#image_head").click(function()
                {
                    $("#image_container").hide();
                });
            }

            );

            function showFullSizeImage(url)
            {
                $("#image_container").show();
                $("#image_body").empty();
                var imageTag = "<img src='" + url + "' class='original_img' id='orgi_image'/>";
                $("#image_body").append(imageTag);

                createThumbnail($("#orgi_image"), 900, 600);
            }
        </SCRIPT>
        <DIV style="display: none;" id="image_container">
            <CENTER>
                <DIV id="image_popup">
                    <DIV id="image_head">
                        <INPUT style="border: currentColor; color: rgb(204, 204, 204); padding-right: 5px; font-size: 20px; float: right; cursor: pointer; background-color: transparent;" id="Popup_Cancel" value="X" type="button">
                    </DIV>
                    <DIV id="image_body">
                    </DIV>
                </DIV>
            </CENTER>
        </DIV>
    </DIV>
    <%@include file="_invite.jsp" %>
    <SCRIPT type="text/JavaScript" src="inside/restrict_menu.js">
    </SCRIPT>
    
</BODY>
</HTML>

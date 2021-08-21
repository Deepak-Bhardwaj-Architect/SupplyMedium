<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
    <HEAD>
        <META content="text/html; charset=ISO-8859-1" http-equiv="Content-Type">
        <LINK rel="stylesheet" href="inside/jquery-ui-1.10.0.custom.css">
        <LINK rel="stylesheet" 
              href="inside/jquery-ui-1.9.2.custom_spinner.css">
        <LINK rel="stylesheet" 
              href="inside/commonlayout.css">
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
        <TITLE>Supply Medium</TITLE>
        <!--<script>
        Usr_anlys('Admin');    
        </script>-->
        <META name="GENERATOR" content="MSHTML 9.00.8112.16561">
    </HEAD>
    <BODY onload="lockUnlock('webkrit_content_loader');" onkeydown="displayunicode(event);">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
        <link rel="stylesheet" href="inside/ad.css">
        <div class="mainpage" style="height:735px;">
            <div class="pagetitlecontainer">
                <div class="pagetitle">Create Your Ads</div>
            </div>

            <div class="contentcontainer">

                <div class="content" style="height:583px;">

                    <form id="form_ad" name="form_ad" action="CreateCompanyAdvertisement" method="post" enctype="multipart/form-data">


                        <div class="content_left">
                            <% 
                            if (request.getParameter("result") != null) {
                            %>
                            <div style="padding:10px; text-align: center;">
                                <% if(request.getParameter("result").trim().equals("success")) 
                                { %>    
                                <b style="color: green;">Advertisement created</b>
                                <% }
                                else if(request.getParameter("result").trim().equals("failed")) 
                                { %>
                                <b style="color: red;">Advertisement not created</b>
                                <% } %>
                            </div>
                            <%
                             }
                            %>                            
                            <div class="label_container">
                                <label class="title_label">Hover text for your creative</label> 
                                <input type="text" autocomplete="off" class="text_box" id="alternateText" name="hoverText"> 
                                <label class="hint_label">This is what users will see in some browsers when their mouse hovers over the ad.</label>
                            </div>

                            <div class="label_container">
                                <label class="title_label">Link</label>
                                <input type="text" autocomplete="off" class="text_box" id="link" name="linkPage"> 
                                <label class="hint_label">Enter the url of landing page</label>
                            </div>



                            <div class="label_container">
                                <label class="title_label" style="width: 160px;">Upload your creative</label> 
                                <input id="ad_file" name="ad_file" class="file_upload_control" type="file" onchange="showImagePreview(this, 'preview_image')" onclick="this.title = '';" onmouseover="this.title = 'The image dimensions should be 200x150 pixels';" title="The image dimensions should be 200x150 pixels">
                                <div class="ad_error" id="ad_error"></div>
                            </div>
                        </div>

                        <div class="content_right">
                            <div class="pic_label">Preview</div>
                            <img class="imge" id="preview_image">
                        </div>


                            <div id="btn_save"><input onclick="showLoading();" type="submit" class="orange_button" value="Save"></div>
                    </form>
                    <form id="hidden_form" name="hidden_form" action="/SupplyMedium/ImageValidationServlet" method="post" enctype="multipart/form-data">
                    </form>
                </div>
            </div>
        </div>
        <%@include file="_footer.jsp" %>
        <div>
        </div>
        <link rel="stylesheet" href="images/Custome_Popup.css">
        <link rel="stylesheet" href="images/popup_warning.css">
        <div id="warning_container" style="display: none;">
            <div id="warning_popup">
                <div id="war_head">
                    <label id="war_head_title">Warning</label>
                </div>
                <div id="war_body">
                    <label id="war_message">

                    </label>
                    <div id="war_btns">
                        <input type="button" id="okbtn" style="float:left;margin-right:30px;" class="pop-button pop-button-White" value="Yes">
                        <input id="Popup_Cancel" style="float:left;" type="button" class="pop-button pop-button-White" value="No">
                    </div>
                </div>
            </div>
        </div>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Toast Window </title>
        <link rel="stylesheet" href="images/Cus_Toast.css">
        
    </div>
    <%@include file="_invite.jsp" %>
    <SCRIPT type="text/JavaScript" src="inside/restrict_menu.js">
    </SCRIPT>
    <SCRIPT>


        $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt_fieldvalidator.js");
        $.getScript("/SupplyMedium/Views/Registration/js/regvalidator.js");
        $.getScript("/SupplyMedium/Views/UserMgmt/js/usermgmt.js", function(data, textStatus, jqxhr) {
            userOnload();

        });

    </SCRIPT>
</BODY>
</HTML>

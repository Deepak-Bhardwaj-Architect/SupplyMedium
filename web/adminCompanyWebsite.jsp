<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.home.bean.CompanyWebsiteBean"%>
<%@page import="supply.medium.home.database.CompanyWebsiteMaster"%>
<%@page import="java.util.ArrayList"%>
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
    <BODY onload="lockUnlock('webkrit_content_loader');">
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
        <div class="mainpage" id="mainpage" style="min-height:700px;background-color:#f1f1f1">
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Company Website</title>
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/Custome_Popup.css">
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/Custome_Buttons.css">
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/custome_controls.css">
            <link rel="stylesheet" href="inside/company_website.css">
            <link rel="stylesheet" href="inside/T_form.css">
            <link rel="stylesheet" href="inside/web_page_structure.css">
            <link rel="stylesheet" href="inside/fill_content.css">
            <link rel="stylesheet" href="inside/T_preview.css">
            <link rel="stylesheet" href="inside/content_steps.css">
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/ResetCSS.css">
            <link rel="stylesheet" href="/SupplyMedium/Views/Utils/css/commonlayout.css">
            <div class="pagetitlecontainer">
                <div class="pagetitle">Company Website</div>
            </div>
            <div class="page" style="padding-top: 0px;">
                <div class="contentcontainer pageContent" style="width: 1010px;min-height:650px;font-family: Verdana, Arial, sans-serif;background-color:white;border:1px solid #c7c7c7;">
                    <div id="webselection">
                        <%
                                        ArrayList websiteUrl = CompanyWebsiteMaster.showWebsiteUrl(session.getAttribute("companyKey").toString());
                                        String urlName="";
                                        String websiteUrlLink="";     
                                        CompanyWebsiteBean cwb = null;
                                        for (int i = 0; i < websiteUrl.size(); i++) {
                                            cwb = (CompanyWebsiteBean) websiteUrl.get(i);
                                             urlName=cwb.getUrlName();
                                             websiteUrlLink = cwb.getWebsiteUrl();
                                        }
                                        %>
                        <form method="post" action="InsertUpdateCompanyWebsiteUrl" >
                        <div class="websel_container">
                            <div style="padding: 15px 0px 0px 35px">
                                <label for="text_company_url">Company Name</label>
                                <input checked="checked" name="urlName" class="textbox" value="<%=urlName %>" id="text_company_url" type="text" autocomplete="off">
                            </div>
                            <div style="padding: 15px 0px 0px 35px">
                                <!--<input checked="checked" name="websel" id="radio_ExternalWeb" value="ExternalLink" type="radio">-->
                                <label for="radio_ExternalWeb"><b>Link to an external website</b>(copy and paste your website url at External website url)</label>
                            </div>
<!--                            <div style="padding: 12px 0px 0px 35px">
                                <input name="websel" id="radio_Template" value="ExternalPage" type="radio">
                                <label for="radio_Template">Create your own from a template</label>
                            </div>-->
                        </div>
                        <div class="external_link" style="display: block;">
                            <div class="external_link_container">
                                <label style="float: left;line-height: 44px;padding-right: 5px;" for="txt_external_link"> External Website URL </label>
                                <div style="float: left;background: #cccccc;padding: 9px 9px 9px 17px;">                                    
                                        
                                        <input id="txt_external_link" type="text" autocomplete="off" name="websiteUrl" value="<%=websiteUrlLink %>">
                                    <input id="btn_external_link" class="general-button gen-btn-link" type="submit" value="Link">
                                    </div>
                            </div>
                            <div class="external_loader">
                                <img id="img_external_loader" alt="images" height="57px" width="57px" src="inside/images/Link_Loader.gif">
                            </div>
                            <div class="external_message" style="display: none;">
                                <div style="margin: 42px 0px 0px 29px;">
                                    <div id="external_message_img" class="external_success">
                                    </div>
                                    <div id="external_message_text" style="margin-left: 15px;">
                                    </div>
                                    <div>
                                        <input id="btn_external_retry" class="general-button gen-btn-Orange" type="button" value="Retry">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="template_link" style="display: none;">
                            <div class="template_container">
                                There are 3 steps to creating your own website
                                <ul>
                                    <li> Choose your webpage structure</li>
                                    <li> Fill in your contents</li>
                                    <li> Then choose a preferred template</li>
                                </ul>
                            </div>
                            <div>
                                <input id="btn_template_proceed" class="general-button gen-btn-Orange" type="button" value="Proceed">
                            </div>
                        </div>
                                    </form> 
                    </div>
                    <div style="display: none;" id="template_conatiner">
                        <div class="template_menus">
                            <ul>
                                <li id="menu_webstructure" class="temp_menu_selected" style="width: 336px">Web page Structure</li>
                                <li id="menu_fill_content" class="temp_menu_normal">Fill Content</li>
                                <li id="menu_template" class="temp_menu_normal">Template</li>
                            </ul>
                        </div>
                        <div class="template_menu_content">
                            <div id="web_page_structure">
                                <div class="checkbox_atleast">
                                    Choose at least any of the following items on the list
                                </div>
                                <ul>
                                    <li>
                                        <div class="checkContainer">
                                            <input checked="checked" value="Home" disabled="disabled" type="checkbox" id="menu_home">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="menu_home">Home</label>
                                    </li>
                                    <li>
                                        <div class="checkContainer">
                                            <input type="checkbox" value="Products" id="menu_products">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="menu_products">Products</label>
                                    </li>
                                    <li>
                                        <div class="checkContainer">
                                            <input type="checkbox" value="Solutions" id="menu_solutions">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="menu_solutions">Solutions</label>
                                    </li>
                                    <li>
                                        <div class="checkContainer">
                                            <input type="checkbox" value="Service" id="menu_service">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="menu_service">Service/Support</label>
                                    </li>
                                    <li>
                                        <div class="checkContainer">
                                            <input type="checkbox" value="About_US" disabled="disabled" checked="checked" id="menu_about">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="menu_about">About Us</label>
                                    </li>
                                    <li>
                                        <div class="checkContainer">
                                            <input type="checkbox" disabled="disabled" value="ContactUS" checked="checked" id="menu_contact">
                                            <div>
                                            </div>
                                        </div>
                                        <label for="menu_contact">Contact Us</label>
                                    </li>
                                </ul>
                                <div class="btn_webstructure_container">
                                    <input id="btn_webstructure_cancel" class="general-button gen-btn-Gray" type="button" value="Cancel">
                                    <input style="margin-left: 71px;" id="btn_webstructure_next" class="general-button gen-btn-Orange" type="button" value="Next">
                                </div>
                            </div>
                            <div style="display: none;" id="fill_content">
                                <div class="fill_selection" style="margin: 0px">
                                    <div class="divrow" style="width:500px;margin-left:454px;height:35px;display:none;">
                                        <label>Select a Page</label>
                                    </div>
                                    <div style="float:left;width:600px;margin-left:272px;display:none;">
                                        <div class="next_prev_container" id="btn_fillcontent_previews_small">
                                            <div class="prev_image">
                                            </div>
                                            <div class="next_prev_lbl">Previous</div>
                                        </div>
                                        <div class="select-container" style="float: left;margin:0px 20px 0px 20px;">
                                            <select id="menu_selection_selectbox">
                                                <option value="fill_home">Home</option>
                                                <option value="fill_products">Products</option>
                                                <option value="fill_solutions">Solutions</option>
                                                <option value="fill_services">Service/Support</option>
                                                <option value="fill_aboutus">About Us</option>
                                                <option value="fill_contactus">Contact Us</option>
                                            </select>
                                        </div>
                                        <div>
                                            <div class="next_prev_container" id="btn_fill_save">
                                                <div class="next_prev_lbl" style="text-align:right;">Next</div>
                                                <div class="next_image">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div id="steps">
                                    </div>
                                </div>
                                <div class="fill_sel_menu" id="fill_home" style="margin-left: 20px;">
                                    <div>
                                        <div class="divrow" style="margin:20px 0px 20px 20px;width:425px;">
                                            <div style="float:left;width:109px;line-height:67px;">Video URL</div>
                                            <input id="home_fill_video_url" class="textbox video_url_textbox" type="text" autocomplete="off" style="float: left;margin-top:20px;width:276px;height:22px;margin: 20px 0px 0px 13px;padding: 0px 10px 0px 10px;">
                                            <div class="video_info" style="padding-left:122px;">
                                                <label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
                                                <div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br>
                                                     </div>8L8n2DjJzNg)">
                                                </div>
                                            </div>
                                        </div>
                                        <div id="home_fill_video_url_Error" class="ErrorImg">
                                        </div>
                                    </div>
                                    <fieldset class="external_fieldset" style="width:700px;">
                                        <legend class="external_legend">Title</legend>
                                        <input type="text" autocomplete="off" class="external_textbox" id="home_fill_video_title">
                                        <div class="content_lbl">Content</div>
                                        <div class="content_text" id="home_fill_content" style="display:none;width:590px;">
                                        </div>
                                        <div class="content_add" id="home_fill_image_add_content_1">
                                            <input type="button" class="add_content_btn" id="home_title_1_add_btn">
                                            <div class="add_content_text">Click to add content</div>
                                        </div>
                                        <input type="button" class="external_edit_btn" id="home_title_1_edit_btn" style="display:none;">
                                    </fieldset>
                                    <div class="external_image_content" style="width:710px;margin-bottom:70px;">
                                        <form id="home_file_upload_form_1" name="home_file_upload_form_1" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                            <input id="home_file_upload_control_1" name="home_file_upload_control_1" class="file_upload_control" type="file" value="Choose file">
                                            <input type="button" id="file_upload_btn_1" class="gen-btn-blue ext_browse_btn" value="Browse">
                                        </form>
                                        <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                            <img class="img_prev" id="home_img_prev_1" src="inside/images/default_img.png">
                                        </div>
                                        <input type="button" class="remove_image_btn" id="home_img_prev_1_del" style="display:none;">
                                    </div>
                                    <!--  
                                    
                                    <div>
                                    <div class="divrow">
                                    <label>Title* </label>
                                    </div>
                                    <input id="home_fill_video_title" class="textbox" type="text" autocomplete="off" />
                                    </div>
                                    <div>
                                    <div class="divrow">
                                    <label>Content*</label>
                                    </div>
                                    <textarea id="home_fill_content" rows="10" cols="90">
                                    </textarea>
                                    </div>
                                    <div class="basic_row" style="margin-top: 40px">
                                    <div class="divrow">
                                    <label>Upload Image</label>
                                    </div>
                                    <div class="file_upload_textbox" style="display:none;">
                                    <input id="home_fill_image_text_1" class="img_txt textbox" type="text" autocomplete="off" style="245px;" />
                                    </div>
                                    <div class="file_upload_container">
                                    <form id="home_file_upload_form_1" name="home_file_upload_form_1" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet">
                                    <input id="home_file_upload_control_1"  name="home_file_upload_control_1" class="file_upload_control"  type="file" value="File Chossee" />
                                    <input type="button" id="file_upload_btn_1" class="general-button gen-btn-upload" value="Browse" />
                                    </form>
                                    </div>
                                    </div>
                                    <div class="basic_row" >
                                    <div class="divrow" style="display:none;">
                                    <label>Content</label>
                                    </div>
                                    <textarea id="home_fill_image_content_1" style="width: 240px;height:65px;display:none">
                                    </textarea>
                                    <div class="PreviewImageContainer" style="position: absolute;margin: -50px 0px 0px 230px;">
                                    <img class="img_prev" id="home_img_prev_1" src="inside/images/default_img.png" />
                                    </div>
                                    </div>-->
                                </div>
                                <div class="fill_sel_menu" id="fill_products" style="display: none;">
                                    <div class="fill_left_content">
                                        <fieldset class="external_fieldset" style="padding-top:4px;">
                                            <legend class="external_legend">Video URL 1</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="product_fill_video_url_1">
                                            <div class="video_info">
                                                <label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
                                                <div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br>
                                                     </div>8L8n2DjJzNg)">
                                                </div>
                                            </div>
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="product_video_fill_content_1" style="display:none">
                                            </div>
                                            <div class="content_add" id="product_video_fill_add_content_1">
                                                <input type="button" class="add_content_btn" id="product_video_1_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="product_video_1_edit_btn" style="display:none;">
                                        </fieldset>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 1</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="product_fill_image_text_1">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="product_fill_image_content_1" style="display:none">
                                            </div>
                                            <div class="content_add" id="product_fill_image_add_content_1">
                                                <input type="button" class="add_content_btn" id="product_title_1_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="product_title_1_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="product_file_upload_form_1" name="product_file_upload_form_1" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="product_file_upload_control_1" name="product_file_upload_control_1" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_1" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="img_prev_1" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="img_prev_1_del" style="display:none;">
                                        </div>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 2</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="product_fill_image_text_2">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="product_fill_image_content_2" style="display:none">
                                            </div>
                                            <div class="content_add" id="product_fill_image_add_content_2">
                                                <input type="button" class="add_content_btn" id="product_title_2_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="product_title_2_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="product_file_upload_form_2" name="product_file_upload_form_2" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="product_file_upload_control_2" name="product_file_upload_control_2" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_2" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="img_prev_2" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="img_prev_2_del" style="display:none;">
                                        </div>
                                    </div>
                                    <div class="fill_right_separator" style="height:728px;">
                                    </div>
                                    <div class="fill_right_content">
                                        <fieldset class="external_fieldset" style="padding-top:4px;">
                                            <legend class="external_legend">Video URL 2</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="product_fill_video_url_2">
                                            <div class="video_info">
                                                <label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
                                                <div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br>
                                                     </div>8L8n2DjJzNg)">
                                                </div>
                                            </div>
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="product_video_fill_content_2" style="display:none">
                                            </div>
                                            <div class="content_add" id="product_video_fill_add_content_2">
                                                <input type="button" class="add_content_btn" id="product_video_2_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="product_video_2_edit_btn" style="display:none;">
                                        </fieldset>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 3</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="product_fill_image_text_3">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="product_fill_image_content_3" style="display:none">
                                            </div>
                                            <div class="content_add" id="product_fill_image_add_content_3">
                                                <input type="button" class="add_content_btn" id="product_title_3_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="product_title_3_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="product_file_upload_form_3" name="product_file_upload_form_3" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="product_file_upload_control_3" name="product_file_upload_control_3" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_3" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="img_prev_3" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="img_prev_3_del" style="display:none;">
                                        </div>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 4</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="product_fill_image_text_4">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="product_fill_image_content_4" style="display:none">
                                            </div>
                                            <div class="content_add" id="product_fill_image_add_content_4">
                                                <input type="button" class="add_content_btn" id="product_title_4_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="product_title_4_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="product_file_upload_form_4" name="product_file_upload_form_4" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="product_file_upload_control_4" name="product_file_upload_control_4" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_4" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="img_prev_4" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="img_prev_4_del" style="display:none;">
                                        </div>
                                    </div>
                                </div>
                                <div class="fill_sel_menu" id="fill_services" style="display: none;">
                                    <div class="fill_left_content">
                                        <fieldset class="external_fieldset" style="padding-top:4px;">
                                            <legend class="external_legend">Video URL 1</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="services_fill_video_url_1">
                                            <div class="video_info">
                                                <label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
                                                <div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br>
                                                     </div>8L8n2DjJzNg)">
                                                </div>
                                            </div>
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="services_video_fill_content_1" style="display:none">
                                            </div>
                                            <div class="content_add" id="services_video_fill_add_content_1">
                                                <input type="button" class="add_content_btn" id="services_video_1_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="services_video_1_edit_btn" style="display:none;">
                                        </fieldset>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 1</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="services_fill_image_text_1">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="services_fill_image_content_1" style="display:none">
                                            </div>
                                            <div class="content_add" id="services_fill_image_add_content_1">
                                                <input type="button" class="add_content_btn" id="services_title_1_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="services_title_1_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="services_file_upload_form_1" name="services_file_upload_form_1" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="services_file_upload_control_1" name="services_file_upload_control_1" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_1" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="services_img_prev_1" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="services_img_prev_1_del" style="display:none;">
                                        </div>
                                        <fieldset class="external_fieldset" style="padding-top:4px;">
                                            <legend class="external_legend">Title 2</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="services_fill_image_text_2">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="services_fill_image_content_2" style="display:none">
                                            </div>
                                            <div class="content_add" id="services_fill_image_add_content_2">
                                                <input type="button" class="add_content_btn" id="services_title_2_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="services_title_2_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="services_file_upload_form_2" name="services_file_upload_form_2" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="services_file_upload_control_2" name="services_file_upload_control_2" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_2" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="services_img_prev_2" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="services_img_prev_2_del" style="display:none;">
                                        </div>
                                    </div>
                                    <div class="fill_right_separator" style="height:728px;">
                                    </div>
                                    <div class="fill_right_content">
                                        <fieldset class="external_fieldset" style="padding-top:4px;">
                                            <legend class="external_legend">Video URL 2</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="services_fill_video_url_2">
                                            <div class="video_info">
                                                <label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
                                                <div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br>
                                                     </div>8L8n2DjJzNg)">
                                                </div>
                                            </div>
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="services_video_fill_content_2" style="display:none">
                                            </div>
                                            <div class="content_add" id="services_video_fill_add_content_2">
                                                <input type="button" class="add_content_btn" id="services_video_2_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="services_video_2_edit_btn" style="display:none;">
                                        </fieldset>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 3</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="services_fill_image_text_3">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="services_fill_image_content_3" style="display:none">
                                            </div>
                                            <div class="content_add" id="services_fill_image_add_content_3">
                                                <input type="button" class="add_content_btn" id="services_title_3_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="services_title_3_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="services_file_upload_form_3" name="services_file_upload_form_3" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="services_file_upload_control_3" name="services_file_upload_control_3" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_3" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="services_img_prev_3" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="services_img_prev_3_del" style="display:none;">
                                        </div>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 4</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="services_fill_image_text_4">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="services_fill_image_content_4" style="display:none">
                                            </div>
                                            <div class="content_add" id="services_fill_image_add_content_4">
                                                <input type="button" class="add_content_btn" id="services_title_4_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="services_title_4_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="services_file_upload_form_4" name="services_file_upload_form_4" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="services_file_upload_control_4" name="services_file_upload_control_4" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_4" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="services_img_prev_4" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="services_img_prev_4_del" style="display:none;">
                                        </div>
                                    </div>
                                </div>
                                <div class="fill_sel_menu" id="fill_solutions" style="display: none;">
                                    <div class="fill_left_content">
                                        <fieldset class="external_fieldset" style="padding-top:4px;">
                                            <legend class="external_legend">Video URL 1</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="solutions_fill_video_url_1">
                                            <div class="video_info">
                                                <label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
                                                <div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br>
                                                     </div>8L8n2DjJzNg)">
                                                </div>
                                            </div>
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="solutions_video_fill_content_1" style="display:none">
                                            </div>
                                            <div class="content_add" id="solutions_video_fill_add_content_1">
                                                <input type="button" class="add_content_btn" id="solutions_video_1_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="solutions_video_1_edit_btn" style="display:none;">
                                        </fieldset>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 1</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="solutions_fill_image_text_1">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="solutions_fill_image_content_1" style="display:none">
                                            </div>
                                            <div class="content_add" id="solutions_fill_image_add_content_1">
                                                <input type="button" class="add_content_btn" id="solutions_title_1_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="solutions_title_1_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="solutions_file_upload_form_1" name="solutions_file_upload_form_1" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="solutions_file_upload_control_1" name="solutions_file_upload_control_1" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_1" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="solutions_img_prev_1" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="solutions_img_prev_1_del" style="display:none;">
                                        </div>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 2</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="solutions_fill_image_text_2">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="solutions_fill_image_content_2" style="display:none">
                                            </div>
                                            <div class="content_add" id="solutions_fill_image_add_content_2">
                                                <input type="button" class="add_content_btn" id="solutions_title_2_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="solutions_title_2_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="solutions_file_upload_form_2" name="solutions_file_upload_form_2" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="solutions_file_upload_control_2" name="solutions_file_upload_control_2" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_2" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="solutions_img_prev_2" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="solutions_img_prev_2_del" style="display:none;">
                                        </div>
                                    </div>
                                    <div class="fill_right_separator" style="height:728px;">
                                    </div>
                                    <div class="fill_right_content">
                                        <fieldset class="external_fieldset" style="padding-top:4px;">
                                            <legend class="external_legend">Video URL 2</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="solutions_fill_video_url_2">
                                            <div class="video_info">
                                                <label class="video_info_lbl"> Copy and paste embedded URL from YouTube</label>
                                                <div class="video_info_icon tooltip" title="Enter the Embedded YouTube URL.(Ex:https://www.youtube.com/embed/<br>
                                                     </div>8L8n2DjJzNg)">
                                                </div>
                                            </div>
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="solutions_video_fill_content_2" style="display:none">
                                            </div>
                                            <div class="content_add" id="solutions_video_fill_add_content_2">
                                                <input type="button" class="add_content_btn" id="solutions_video_2_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="solutions_video_2_edit_btn" style="display:none;">
                                        </fieldset>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 3</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="solutions_fill_image_text_3">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="solutions_fill_image_content_3" style="display:none">
                                            </div>
                                            <div class="content_add" id="solutions_fill_image_add_content_3">
                                                <input type="button" class="add_content_btn" id="solutions_title_3_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="solutions_title_3_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="solutions_file_upload_form_3" name="solutions_file_upload_form_3" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="solutions_file_upload_control_3" name="solutions_file_upload_control_3" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_3" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="solutions_img_prev_3" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="solutions_img_prev_3_del" style="display:none;">
                                        </div>
                                        <fieldset class="external_fieldset">
                                            <legend class="external_legend">Title 4</legend>
                                            <input type="text" autocomplete="off" class="external_textbox" id="solutions_fill_image_text_4">
                                            <div class="content_lbl">Content</div>
                                            <div class="content_text" id="solutions_fill_image_content_4" style="display:none">
                                            </div>
                                            <div class="content_add" id="solutions_fill_image_add_content_4">
                                                <input type="button" class="add_content_btn" id="solutions_title_4_add_btn">
                                                <div class="add_content_text">Click to add content</div>
                                            </div>
                                            <input type="button" class="external_edit_btn" id="solutions_title_4_edit_btn" style="display:none;">
                                        </fieldset>
                                        <div class="external_image_content">
                                            <form id="solutions_file_upload_form_4" name="solutions_file_upload_form_4" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                                <input id="solutions_file_upload_control_4" name="solutions_file_upload_control_4" class="file_upload_control" type="file" value="Choose file">
                                                <input type="button" id="file_upload_btn_4" class="gen-btn-blue ext_browse_btn" value="Browse">
                                            </form>
                                            <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                                <img class="img_prev" id="solutions_img_prev_4" src="inside/images/default_img.png">
                                            </div>
                                            <input type="button" class="remove_image_btn" id="solutions_img_prev_4_del" style="display:none;">
                                        </div>
                                    </div>
                                </div>
                                <div class="fill_sel_menu" id="fill_aboutus" style="display: none;margin-left:20px;">
                                    <fieldset class="external_fieldset" style="width:700px;">
                                        <legend class="external_legend">Title</legend>
                                        <input type="text" autocomplete="off" class="external_textbox" id="aboutus_fill_image_text_1">
                                        <div class="content_lbl">Content</div>
                                        <div class="content_text" id="aboutus_fill_image_content_1" style="display:none;width:590px;">
                                        </div>
                                        <div class="content_add" id="aboutus_fill_image_add_content_1">
                                            <input type="button" class="add_content_btn" id="aboutus_title_1_add_btn">
                                            <div class="add_content_text">Click to add content</div>
                                        </div>
                                        <input type="button" class="external_edit_btn" id="aboutus_title_1_edit_btn" style="display:none;">
                                    </fieldset>
                                    <div class="external_image_content" style="width:710px;margin-bottom:70px;">
                                        <form id="aboutus_file_upload_form_1" name="aboutus_file_upload_form_1" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet" style="margin-left:100px;padding-top:30px;">
                                            <input id="aboutus_file_upload_control_1" name="aboutus_file_upload_control_1" class="file_upload_control" type="file" value="Choose file">
                                            <input type="button" id="aboutus_file_upload_btn_1" class="gen-btn-blue ext_browse_btn" value="Browse">
                                        </form>
                                        <div class="PreviewImageContainer" style="position: absolute;margin: -55px 0px 0px 315px">
                                            <img class="img_prev" id="aboutus_img_prev_1" src="inside/images/default_img.png">
                                        </div>
                                        <input type="button" class="remove_image_btn" id="aboutus_img_prev_1_del" style="display:none;">
                                    </div>
                                    <!--  
                                    <div>
                                    <div class="divrow">
                                    <label>Title*</label>
                                    </div>
                                    <input id="aboutus_fill_image_text_1" class="textbox" type="text" autocomplete="off" style="float: left;"  />
                                    <div class="file_upload_container">
                                    <form id="aboutus_file_upload_form_1" name="aboutus_file_upload_form_1" method="post" enctype="multipart/form-data" action="/SupplyMedium/uploadsevlet">
                                    <input id="aboutus_file_upload_control_1"  name="aboutus_file_upload_control_1" class="file_upload_control"  type="file" value="File Chossee" />
                                    <input type="button" id="aboutus_file_upload_btn_1" class="general-button gen-btn-upload" value="Browse" />
                                    </form>
                                    </div>
                                    <div class="PreviewImageContainer" style="margin: 5px 0px 0px 450px;">
                                    <img class="img_prev" id="aboutus_img_prev_1" src="inside/images/default_img.png" />
                                    </div>
                                    </div>
                                    <div>
                                    <div class="divrow">
                                    <label>Content*</label>
                                    </div>
                                    <textarea id="aboutus_fill_image_content_1" rows="10" cols="90" style="width: 820px;height: 175px">
                                    </textarea>
                                    </div>-->
                                </div>
                                <div class="fill_sel_menu" id="fill_contactus" style="display: none;">
                                    <div class="contactus_container">
                                        <div class="divrow">
                                            <div class="contactus_lbl">Address</div>
                                            <input id="contact_address_1" class="textbox" type="text" autocomplete="off">
                                        </div>
                                        <div class="divrow">
                                            <div class="contactus_lbl">City</div>
                                            <input id="contact_city_1" class="textbox" type="text" autocomplete="off">
                                        </div>
                                        <div class="divrow">
                                            <div class="contactus_lbl">State</div>
                                            <input id="contact_state_1" class="textbox" type="text" autocomplete="off">
                                        </div>
                                        <div class="divrow">
                                            <div class="contactus_lbl">Zip Code</div>
                                            <input id="contact_zipcode_1" class="textbox" type="text" autocomplete="off">
                                        </div>
                                        <div class="divrow">
                                            <div class="contactus_lbl">Country</div>
                                            <input id="contact_country_1" class="textbox" type="text" autocomplete="off">
                                        </div>
                                        <div class="divrow">
                                            <div class="contactus_lbl">Phone Number</div>
                                            <input id="contact_inquiry_1" class="textbox" type="text" autocomplete="off">
                                        </div>
                                    </div>
                                </div>
                                <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                                <title>Insert title here</title>
                                <div style="display: none; z-index: 1000;" class="Custome_Popup_Window" id="add_content_popup">
                                    <div class="Cus_Popup_Outline add_content_outline">
                                        <div class="Popup_Tilte_NewGroup Gen_Popup_Title" style="border-radius: 0px;">
                                            <div style="padding: 0px 0px 0px 15px; float: left">Content</div>
                                            <div class="Popup_Close_NewGroup Gen_Cus_Popup_Close" id="add_content_close">
                                            </div>
                                        </div>
                                        <textarea class="content_txtarea" id="content_txtarea">
                                        </textarea>
                                        <input type="hidden" id="content_div_id">
                                        <input type="hidden" id="add_content_div_id">
                                        <input type="hidden" id="edit_content_div_id">
                                        <input type="button" class="gen-btn-blue" value="Save" style="margin-left: 290px;" id="content_save_btn">
                                    </div>
                                </div>
                                <div id="fill_content_err" class="fill_content_err" style="display:none;">
                                </div>
                                <div style="margin: 10px 0px 30px 364px;float:left;">
                                    <input id="btn_fillcontent_previous" class="general-button gen-btn-Gray" type="button" value="Previous">
                                    <input style="margin-left: 71px;" id="btn_fillcontent_next" class="general-button gen-btn-Orange" type="button" value="Next">
                                </div>
                            </div>
                            <div style="display: none;" id="template">
                                <div class="tempreview_container">
                                    <div class="tempreview_title">
                                        <label>Choose a Template</label>
                                        <input id="btn_tempreview_preview" class="general-button gen-btn-Green" type="button" value="Preview">
                                    </div>
                                    <div class="tempreview_list">
                                        <ul>
                                            <li maintype="T1" subtype="common">
                                                <img src="inside/images/template1.png">
                                            </li>
                                            <!--                        <li mainType="T1" SubType="red">
                                            <img src="inside/images/template1.png" />
                                            </li>
                                            <li mainType="T1" SubType="blue">
                                            <img src="inside/images/template2.png" />
                                            </li>
                                            <li mainType="T1" SubType="green">
                                            <img src="inside/images/template3.png" />
                                            </li>
                                            <li mainType="T1" SubType="orange">
                                            <img src="inside/images/template4.png" />
                                            </li>
                                            <li mainType="T2" SubType="green">
                                            <img src="inside/images/template5.png" />
                                            </li>
                                            <li mainType="T2" SubType="orange">
                                            <img src="inside/images/template6.png" />
                                            </li>
                                            <li mainType="T2" SubType="red">
                                            <img src="inside/images/template7.png" />
                                            </li>-->
                                        </ul>
                                    </div>
                                </div>
                                <div style="margin: 30px 0px 30px 364px;">
                                    <input id="btn_tempreview_previous" class="general-button gen-btn-Gray" type="button" value="Previous">
                                    <input style="margin-left: 71px;" id="btn_tempreview_finish" class="general-button gen-btn-Orange" type="button" value="Finish">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
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
                        <INPUT style="margin-right: 30px; float: left;" id="okbtn" class="pop-button pop-button-White" value="Yes" type="button">
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
                                        <INPUT style="margin-right: 20px;" id="cancel_watchlist" class="gen-btn-Gray" value="Cancel" type="button">
                                        <INPUT id="save_watchlist" class="gen-btn-Orange" value="Create" type="button">
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
                <DIV class="Popup_Close_NewGroup Gen_Cus_Popup_Close">
                </DIV>
            </DIV>
            <DIV class="member_container">
                <DIV class="add_member_container">
                    <DIV id="members_count" class="add_member">
                    </DIV>
                    <DIV class="textbox_container">
                        <INPUT class="newsroom_textbox" type="text" autocomplete="off" 
                               placeholder="Find a Member">
                        <IMG style="float: left; cursor: pointer;" src="inside/search_lens.png">
                    </DIV>
                </DIV>
                <DIV class="button_container">
                    <DIV id="delete_members_btn" class="del_button">Delete</DIV>
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
        <SCRIPT>

            $.getScript("/SupplyMedium/Views/NewsRoom/js/newsroom_feed.js");
            $.getScript("/SupplyMedium/Views/NewsRoom/js/watchlist_feed.js");

            $.getScript("/SupplyMedium/Views/NewsRoom/js/watchlist_members.js", function(data, textStatus, jqxhr)
            {
                $("#delete_members_btn").click(removeMembers);

            });

            $.getScript("/SupplyMedium/Views/NewsRoom/js/newsroom.js", function(data, textStatus, jqxhr)
            {
                newsroomOnload();

            });
            $.getScript("/SupplyMedium/Views/NewsRoom/js/watchlist.js", function(data, textStatus, jqxhr)
            {
                fetchWatchLists();
                $("#save_watchlist").click(saveWatchList);

            });

        </SCRIPT>
    </DIV>
    <%@include file="_invite.jsp" %>
    <SCRIPT type="text/JavaScript" src="inside/restrict_menu1.js">
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

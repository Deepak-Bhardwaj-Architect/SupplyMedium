<%-- 
    Document   : userRegistration
    Created on : Sep 18, 2014, 1:38:15 PM
    Author     : LenovoB560
--%>
<%@page import="supply.medium.home.bean.RatingBean"%>
<%@page import="supply.medium.home.database.RatingMaster"%>
<%@page import="supply.medium.home.bean.CompanyBean"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
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
        <LINK rel="stylesheet" href="inside/Custome_Popup.css">
        <LINK rel="stylesheet" 
              href="inside/jquery.mCustomScrollbar.css">
        <LINK rel="stylesheet" href="inside/user_home.css">
        <LINK rel="stylesheet" href="inside/userratings.css">
        <LINK rel="stylesheet" href="inside/jRating.jquery.css">
        <LINK rel="stylesheet" href="inside/revieweditpopup.css">
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
    <BODY onload="lockUnlock('webkrit_content_loader');">
    <LINK rel="stylesheet" 
          href="inside/userheader.css">
    <LINK rel="stylesheet" href="inside/notifi_dropdown.css">
    <%@include file="_header.jsp"%>
    <div class="mainpage">

        <div class="pagetitlecontainer">  <!-- Page title container -->
            <div class="pagetitle">Ratings</div>
        </div>

        <div class="page">
            <div class="rattingscontainer contentcontainer">
                <!-- <input type="text" class="ratingstextbox textbox" placeholder="Search for first name, last name, email and business" /> -->
                <div class="ratingscompanylist">
                    <div class="ratingscompanylistInner mCustomScrollbar _mCS_5">
                        <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_5" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
                            <%
                                    ArrayList companyList = CompanyMaster.showConnnectedCompany(companyKey); 
                            CompanyBean companyBean = null;
                            for (int i = 0; i < companyList.size(); i++) {
                                companyBean = (CompanyBean) companyList.get(i);
                                RatingBean ratingBean=RatingMaster.showRating(companyBean.getCompanyKey());
                                float rating=Float.parseFloat((ratingBean.getRating()));
                                    %>
                                    <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;" onclick="showRatingDetail('<%=companyBean.getCompanyName() %>','<%=companyBean.getCompanyKey() %>','<%=Math.round(rating) %>');">
                                <div id="ratingscompanylistcontainer<%=companyBean.getCompanyKey() %>">
                                    <div class="ratingcompanylistrow selectedItem">
                                        <div class="ratinglistcompanylog">
                                            <div class="ratingcompanyimageholder" style="width: 46px;height: 46px;margin: 0px;">
                                                <img src="<%=SmProperties.pathUrl%>/companies/<%=companyBean.getCompanyKey()%>.png">
                                            </div>
                                        </div>
                                        <div class="ratingcomapnydetails">
                                            <div class="ratting_row">
                                                <label class="fontsize14px"><%=companyBean.getCompanyName() %></label>
                                            </div>
                                            <div class="ratting_row">
                                                <div class="rating_star_small_base rating_star_<%=Math.round(rating*2) %>">
                                                </div>
                                            </div>
                                            <div class="ratting_row">
                                                <label class="fontsize12px">Based on <%=ratingBean.getRatingTitle() %> ratings</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="clear: both;"></div>
                            <% } %>
                            <div class="mCSB_scrollTools" style="position: absolute; display: none;">
                                <div class="mCSB_draggerContainer">
                                    <div class="mCSB_dragger" style="position: absolute; top: 0px;" oncontextmenu="return false;">
                                        <div class="mCSB_dragger_bar" style="position:relative;">
                                        </div>
                                    </div>
                                    <div class="mCSB_draggerRail">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ratingsreview" id="ratingDetail"  style="">                    
                </div>
            </div>
        </div>  <!-- Page end -->

        <%@include file="_footer.jsp" %>
        <DIV>
        </DIV>
    </div><!-- main Page end -->
    <%@include file="_invite.jsp" %>
    <SCRIPT type="text/JavaScript" src="inside/restrict_menu.js">
    </SCRIPT>
    <script type="text/javascript">

        $(function()
        {

            $("#UserRatingsEditPopup_Close").click(function()
            {
                $("#UserRatingsEditPopup").hide();
                $("#rattingvaluebigmainpopup").text('');
            });

        });
    </script>
    <div style="display: none;" id="UserRatingsEditPopup" class="Custome_Popup_Window" style="border: 1px solid #000;-webkit-border: 1px solid #000;">
        <table>
            <tr>
                <td style="vertical-align: middle;">
                    <div class="Popup_Outline_NewGroup Cus_Popup_Outline popuplayout" style="border-right: 0px;-webkit-border-radius: 0px;" >
                        <div class="Popup_Tilte_NewGroup Gen_Popup_Title " style="border-right: 0px;-webkit-border-radius: 0px;">
                            Ratings
                            <div id="UserRatingsEditPopup_Close" class="Popup_Close_NewGroup Gen_Cus_Popup_Close" onclick="$('#UserRatingsEditPopup').fadeOut();">
                            </div>
                        </div>
                        <div class="popupcontent">
                            <form method="post" action="rating">
                            <div class="div_row" >
                                <label class="popup_label" for="popup_review_title" > Title </label>
                                <input id="popup_review_title" name="ratingTitle" type="text" maxlength="99" style="width: 401px" class="catalog_textbox_popup textbox">
                            </div>
                            <div class="div_row" >
                                <label class="popup_label" for="popup_item_description" > Review </label>
                                <textarea  id="popup_rating_review" name="ratingComment" maxlength="250" class="catalog_textbox_popup textbox" style="resize:none;"></textarea>
                            </div>                             
                            <div style="margin: 125px 60px 0px 110px">
                                <input type="hidden" name="companyKeyTo" id="companyKeyTo" value="0">
                                <input type="hidden" name="rating" id="rating" value="0">
                               <div id="rattingvaluebigmainpopup" style="float: left; height: 30px; width: 180px; overflow: hidden; z-index: 1; position: relative; cursor: default;" data-average="0">
                                    <img src="inside/great_star_empty.png" height="30" id="strting1" onmouseout="moveOutStarBg()" onmouseover="moveStarBg('1')" onclick="setStarBg('1');this.onmouseout='';"/>
                                    <img src="inside/great_star_empty.png" height="30" id="strting2" onmouseout="moveOutStarBg()" onmouseover="moveStarBg('2')" onclick="setStarBg('2');this.onmouseout='';"/>
                                    <img src="inside/great_star_empty.png" height="30" id="strting3" onmouseout="moveOutStarBg()" onmouseover="moveStarBg('3')" onclick="setStarBg('3');this.onmouseout='';"/>
                                    <img src="inside/great_star_empty.png" height="30" id="strting4" onmouseout="moveOutStarBg()" onmouseover="moveStarBg('4')" onclick="setStarBg('4');this.onmouseout='';"/>
                                    <img src="inside/great_star_empty.png" height="30" id="strting5" onmouseout="moveOutStarBg()" onmouseover="moveStarBg('5')" onclick="setStarBg('5');this.onmouseout='';"/>
                                    <input type="hidden" id="starRatingWK" name="starRatingWK" value="0"/>
                                </div>
<!--                                <script type="text/javascript" src="inside/jRating.jquery.js">
                                </script>
                                <script type="text/javascript" src="inside/userratings.js">
                                </script>-->
<input onclick="showLoading()" style="width: 50px;float: right;height: 30px;" type="submit" value="Post" class="general-button ratngs-btn-review" />
                            </div>
                            </form>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div>

</BODY>
</HTML>

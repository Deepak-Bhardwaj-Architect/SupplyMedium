<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.bean.RatingBean"%>
<%@page import="supply.medium.home.database.RatingMaster"%>
<%@page import="java.util.ArrayList"%>
<%    
    String companyName = request.getParameter("companyName");
    String companyKey = request.getParameter("companyKey");
    String rating = request.getParameter("rating");
%>
<div class="ratingcompanyinfo">
    <div class="rattingcompanydetails">
        <div class="ratting_row">
            <label class="ratingcompanyname"><%=companyName%></label>
        </div>
        <div class="ratting_row">
            <div class="rattingvaluebigmain" data-average="8" style="height: 30px; width: 150px; overflow: hidden; z-index: 1; position: relative;">
                <div class="jRatingColor" style="width: 120px;">
                </div>
                <div class="jRatingAverage" style="width: 0px; top: -30px;">
                </div>
                <div class="jStar" style="width: 150px; height: 30px; top: -60px; background: url(inside/big_star_empty.png) repeat-x;">
                </div>
            </div>
        </div>
        <div class="ratting_row">
            <label class="rating_number">Based on <span class="rating_number_value_main"><%=rating%></span> ratings</label>
        </div>
        <div class="ratting_row">
            <input id="btnratingswritereview" onclick="$('#UserRatingsEditPopup').fadeIn();" class="general-button ratngs-btn-review" type="button" value="Write a review">
        </div>
    </div>
    <div class="ratingcompanyimage">
        <div id="rattingcompanyimagefilemain" class="ratingcompanyimageholder">
            <img src="<%=SmProperties.pathUrl%>/companies/<%=companyKey %>.png">
        </div>
    </div>
</div>
<div class="ratingreviewlistcontainer">
    <div class="reviewlistheading">
        Review
    </div>
    <div id="ratingreviewlist" class="mCustomScrollbar _mCS_6">
        <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_6" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
            <%
                ArrayList ratingList = RatingMaster.showRatingList(companyKey);
                RatingBean ratingBean = null;
                UserBean userBean=null;
                float ratingCount=0;
                for (int i = 0; i < ratingList.size(); i++) {
                    ratingBean = (RatingBean) ratingList.get(i);
                    ratingCount=Float.parseFloat(ratingBean.getRating());
            %>
            <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
                <div id="ratingreviewlistplaceholder">
                    <div class="ratingreviewrow">
                        <span class="reviewrowtitle"><%=ratingBean.getRatingTitle() %></span>
                        <div class="rating_star_small_base rating_star_<%=Math.round(ratingCount)*2 %>">
                        </div>
                        <div style="float: none;width: 100%;height: 5px">
                        </div>
                        <% userBean=UserMaster.showUserDetails(ratingBean.getUserKeyFrom());   %>
                        <span class="ratingreviewinfo">by <%=userBean.getFirstName() %> <%=userBean.getLastName() %> -  <%=ratingBean.getRatingOn() %></span>
                        <div class="reviewcomments"><%=ratingBean.getRatingComment() %></div>
                    </div>
                </div>
            </div>
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
            <% } %>
        </div>
    </div>
</div>                        

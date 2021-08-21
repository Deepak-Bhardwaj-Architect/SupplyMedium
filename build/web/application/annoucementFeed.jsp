<%@page import="java.io.File"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.bean.UserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="supply.medium.home.bean.FeedBean"%>
<%@page import="supply.medium.home.database.FeedMaster"%>
<%@page import="java.util.ArrayList"%>
<%  String feedKey = null;
    String feedTitle = null;
    String feedDescription = null;
    String filePath = null;
    String postedOn = null;
    String fechedFeedKeys = "";
    ArrayList feedList = FeedMaster.showDepartmentFeed(request.getParameter("departmentKey"));
    FeedBean sbcb = null;
    String feedusername = null;
    for (int i = 0; i < feedList.size(); i++) {
        sbcb = (FeedBean) feedList.get(i);
        feedKey = sbcb.getFeedKey();
        feedTitle = sbcb.getFeedTitle();
        feedDescription = sbcb.getFeedDescription();
        postedOn = sbcb.getPostedOn();
        fechedFeedKeys += feedKey + ",";
        UserBean ub = UserMaster.showUserDetailsByUserKey(sbcb.getUserKey());
        feedusername = ub.getFirstName() + " " + ub.getLastName();
        filePath = SmProperties.pathUrl + "/" + sbcb.getFilePath();
%>
<div id="feed_<%=feedKey%>" class="feed" style="width: 640px; padding: 10px;">
    <div class="publisher_details">
        <div>
            <img src=<%=SmProperties.urlPath%>"users/<%=sbcb.getUserKey()%>.png" class="publisher_photo">
        </div>
        <div class="publisher_name" style="width:280px;"><%=feedusername%></div>
        <div class="date"><%=postedOn%></div>
        <%
            if (session.getAttribute("userKey").toString().equals(sbcb.getUserKey())) {
        %>
        <input type="button" class="feed_del" id="feed_del_<%=feedKey%>" onClick="deleteConfirmation(1);
                document.getElementById('confirmFeedRemove').href = 'RemoveFeed?redirectTo=internalFeed&feedKey=<%=feedKey%>';">
        <%
            }
        %>
    </div>
    <div class="feed_head"><%=feedTitle%></div>
    <div class="feed_summary">
        <p class="feed_text"><%=feedDescription%></p>
        <p>
        </p>
    </div>
    <div id="mn_<%=feedKey%>" style="height:0px;margin-top:-50px;">
        <div class="dilbag-lca" style="width:45%;">
            <div id="lk_<%=feedKey%>" class="dilbag-like" onClick="javascript:save_like_comment(' <%=feedKey%>', ' like', document.getElementById(' lks_cnt_<%=feedKey%>').value);">0 Like</div>
            <input type="hidden" value="0 Like" id="lks_cnt_<%=feedKey%>">
            <div id="cmnt2_<%=feedKey%>" class="dilbag-comment" onClick="javascript:hide_show(' cmnt_<%=feedKey%>');
                    select_comment(' <%=feedKey%>');">0 Comment</div>
            <input type="hidden" value="0 Comment" id="cmnts_cnt_<%=feedKey%>">
            <div style="width:100px;float:left;">
                <select class="dilbag-action" id="optn_<%=feedKey%>" style="display:none;">
                    <option class="dilbag-action-list">-Action-</option>
                    <option class="dilbag-action-list">Hide</option>
                    <option class="dilbag-action-list">Mute</option>
                    <option class="dilbag-action-list">Spam</option>
                </select>
            </div>
        </div>
        <div style="background:#F8F8F8;float:left;width:610px;display:none;padding:15px;" id="cmnt_<%=feedKey%>">
            <textarea id="cmntddd_<%=feedKey%>" placeholder="Write comment" style="width:560px;height:50px;background:#FFFFFF;">
            </textarea>
            <div style="background:#FF9933;color:white;width:30px;border:1px solid #F8F8F8;float:right;text-align:center;cursor:pointer;vertical-align: bottom;" onClick="javascript:save_like_comment('<%=feedKey%>', document.getElementById(' cmntddd_<%=feedKey%>').value, document.getElementById(' cmnts_cnt_<%=feedKey%>').value);">Post</div>
            <ul id="all_comment_<%=feedKey%>" style="background:#F8F8F8;float:left;width:560px;padding-top:15px;">
            </ul>
        </div>
    </div>
</div>
<% }%>
<input type="hidden" id="fechedFeedKeys" value="<%=fechedFeedKeys%>">

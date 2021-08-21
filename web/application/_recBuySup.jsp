<%@page import="java.io.File"%>
<%@page import="supply.medium.utility.SmProperties"%>
<%@page import="supply.medium.home.database.CompanyMaster"%>
<%@page import="supply.medium.home.bean.CompanyBean"%>
<%@page import="java.util.ArrayList"%>
<div class="recommend_content mCustomScrollbar _mCS_2" id="recommend_content">
    <div class="mCustomScrollBox mCS-dark-thick" id="mCSB_2" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
        <%
            ArrayList recObj=CompanyMaster.showAllCompaniesByBusinessCategoryKey(companyKey, 3);
            CompanyBean recCB=null;
            for(int recBS=0;recBS<recObj.size();recBS++)
            {
                recCB=(CompanyBean)recObj.get(recBS);
        %>
        <div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;border-bottom: 1px solid #e5e5e5">
            <!-- Recommend supplier or buyer content body -->
            <div class="rec_comp odd_row" id="00000" style="padding-top:15px;">
                <div class="rec_comp_left">
                    <div class="rec_comp_img">
                        <img src="<%=SmProperties.pathUrl+"company-"+recCB.getCompanyKey()+File.separator+"company-"+recCB.getCompanyKey()+".png"%>" style="width:60px;height:60px;border:none;">
                    </div>
                </div>
                <div class="rec_comp_right">
                    <div class="rec_comp_name"><%=recCB.getCompanyName()%></div>
                    <!--<input type="button" class="comp_reco_del" value="X" id="reco_del_00000" style="">-->
                    <div class="rec_comp_addr"><%=recCB.getCompanyType()%></div>
                    <!--<input type="button" class="gen-btn-Lblue rec_comp_btn" value="Add Vendor" onclick="addVendor('00000', 'webkrit', 'Buyers/Suppliers')">-->
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
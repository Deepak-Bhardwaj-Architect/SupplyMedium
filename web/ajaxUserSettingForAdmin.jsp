<%@page import="supply.medium.home.database.CountryMaster"%>
<%@page import="supply.medium.home.database.StateMaster"%>
<%@page import="supply.medium.home.bean.CompleteUserBean"%>
<%@page import="supply.medium.home.database.UserMaster"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList users = UserMaster.showUserDetailToAdminForSetting(request.getParameter("userKey"));
    CompleteUserBean cub = null;
    for (int i = 0; i < users.size(); i++) {
        cub = (CompleteUserBean) users.get(i);
%>
<form method="post" action="UpdateUserSettingByUser" onsubmit="return validateUserSettingUpdatedByAdmin();">
    <div style="width:50%;height:100%;float: left;">
        <div class="contentdetail" id="content_detail" style="padding:10px;width:100%;height: auto;border:0px;">
            <legend style="color:#FF9966">User detail</legend><br>
            <div class="row">
                <div class="label" style="font-size:13px;">
                    First Name<span class="mandatory">*</span>
                </div>
                <input type="text" autocomplete="off" name="firstName" id="firstNameId" class="textbox" value="<%=cub.getFirstName()%>" onblur="vldt_usr_stngs()">
                <span id="frstnm_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter first name</span>
            </div>
            <div class="row">
                <div class="label" style="font-size:13px;">
                    Last Name<span class="mandatory">*</span>
                </div>
                <input type="text" autocomplete="off" name="lastName" id="lastNameId" class="textbox" value="<%=cub.getLastName()%>" onblur="vldt_usr_stngs()">
                <span id="lstnm_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter last name</span>
            </div>                                        
            <div class="row">
                <div class="label" style="font-size:13px;">
                    Phone<span class="mandatory">*</span>
                </div>
                <input type="text" autocomplete="off" name="phone" id="phoneId" class="required textbox" onblur="vldt_usr_stngs()" value="<%=cub.getPhone()%>">
                <span id="phn_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter valid phone no.</span>
            </div>
            <div class="row">
                <div class="label" style="font-size:13px;">
                    Mobile
                </div>
                <input type="text" autocomplete="off" name="mobile" id="mobileId" class="textbox" value="<%=cub.getCell()%>" onblur="vldt_usr_stngs()">
                <span id="mbl_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter valid cell no.</span>
            </div>
            <div class="row">
                <div class="label" style="font-size:13px;">Fax</div>
                <input type="text" autocomplete="off" name="fax" id="fax" class="textbox" value="<%=cub.getFax()%>">
            </div>    
            <div class="row">
                <div class="label" style="font-size:13px;">
                    User Type<span class="mandatory">*</span>
                </div>
                <select class="textbox" style="height: 28px;width:190px;" name="userType" id="usr_tp">                                        
                    <option <% if (cub.getUserType().equals("Intranet User")) {
                            out.print("selected");
                        } %>>Intranet User</option>
                    <option <% if (cub.getUserType().equals("Regular")) {
                            out.print("selected");
                        }%>>Regular</option>
                </select>                                    
            </div>   
            <div class="row">
                <div class="label" style="font-size:13px;">Department</div>
                <input type="text" autocomplete="off" name="department" id="department" class="textbox" value="<%=cub.getDepartment()%>">
            </div>
            <div class="row">
                <div class="label" style="font-size:13px;">Manager/Supervisor</div>
                <input type="text" autocomplete="off" name="userRole" id="userRole" class="textbox" value="<%=cub.getManagerSupervisor()%>">
            </div>        
            <div id="newusererr" class="usermgmterr"></div>
        </div>
    </div>
    <div style="width:50%;height:100%;float:right; margin-top:40px;">
        <div class="row">
            <div class="label" style="font-size:13px;">
                City
            </div>
            <input type="text" autocomplete="off" name="city" id="city" class="textbox" value="<%=cub.getCity()%>">
        </div>

        <div class="row">
            <div class="label" style="font-size:13px;">
                Country<span class="mandatory">*</span>
            </div>
            <input type="text" autocomplete="off" readonly="" class="textbox" value="<%=CountryMaster.showCountryFromKey(cub.getCountry())%>">
            <!--select 
                class="selectbox">
                <option value="">--Select Country--</option>
            
        </select--> <label for="countryregion_0" generated="true" class="error" id="countryregion_0err"></label>
        </div>


        <div class="row">
            <div class="label" style="font-size:13px;">State/Province</div>
            <div id="select_0_container"><!--select id='state_0' name='state' class="selectbox">
                    <option>--Select State--</option>
                </select> 
                <label for="state_0" generated="true" class="error"
                       id="state_0err"></label--></div>

            <input type="text" autocomplete="off" readonly="" class="textbox" value="<%=StateMaster.showStateFromKey(cub.getState())%>">
        </div>


        <div class="row">
            <div class="label" style="font-size:13px;">
                Zip Code/Postal Code
            </div>
            <input type="text" autocomplete="off" name="zipcode" id="zipcode" class="textbox" value="<%=cub.getZipcode()%>">
        </div>   
        <div style="margin-left: 32%;margin-top:10px;">
            <input type="button" value="Cancel" class="gen-btn-Gray" onclick="$('#policies_popup').fadeOut();">
            <input type="submit" value="Update" style="margin-left: 30px;" class="gen-btn-Orange">
        </div>
<!--        <div class="popupcontent" style="/*border-bottom-color: #A4A4A4;*/ padding:10px;margin-top:0px;">
        
            <div class="policies_sub_head">Account Restriction</div>
            <div class="policies_row">
                <div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="change_password"><div></div></div> <label for="change_password" class="policies_checkbox_lbl">User must change password at
                    next login</label>
            </div>
            <div class="policies_row">
                <div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="acc_disable"><div></div></div> <label for="acc_disable" class="policies_checkbox_lbl">Account is disabled</label>
            </div>
            <div class="policies_sub_head">Account Expiration</div>
            <div class="policies_row">
                <div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="exp_end"><div></div></div> <label for="exp_end" class="policies_checkbox_lbl">End
                    of</label>&nbsp; <input id="policies_spinner" style="width:20px;" name="value"><label>&nbsp;days</label>
            </div>
            <div class="policies_row">
                <div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="exp_never"><div></div></div> <label for="exp_never" class="policies_checkbox_lbl">Never</label>
            </div>
            <input onclick="savePolicies();" style="margin-top:20px;margin-left:200px;" type="submit" value="Save" class="gen-btn-Orange">
            <div style="float: left;width: 100%;padding:10px;margin-top:10px;">
                    <div class="grouppriv"></div>
            </div>
        </div>-->
    <input type="hidden" name="userKey" value="<%=request.getParameter("userKey")%>" >
    <input type="hidden" name="redirectTo" value="userList">
</form>        
<% }%>
<%-- 
    Document   : user_settings
    Created on : Apr 18, 2014, 4:22:01 PM
    Author     : intel-i5
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="db.utils.DBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Statement statement = null;
    Connection con = null;
    ResultSet rs = null;
    try {
        con = DBConnect.instance().getConnection();
        statement = con.createStatement();
        rs = statement.executeQuery("SELECT user_profile.first_name,user_profile.last_name,user_profile.phone,user_profile.cell,mailing_address.city_name,mailing_address.state_name,mailing_address.zipcode,mailing_address.country_name,user_profile.department,user_profile.user_role,user_profile.fax FROM user_profile INNER JOIN mailing_address ON user_profile.email=mailing_address.email where user_profile.email='" + request.getParameter("id") + "'");
        if (rs.next()) {
%>
<div style="width:48%;height:auto;float: left;">
    <div class="contentdetail" id="content_detail" style="padding:10px;width:100%;height: auto;border-right:2px solid;border-right-color: #A4A4A4;">
        <legend style="color:#FF9966">User detail</legend><br>
        <div class="row">
            <div class="label" style="font-size:13px;">
                First Name<span class="mandatory">*</span>
            </div>
            <input type="text" id="frstnm" class="textbox" value="<%=rs.getString(1)%>" onblur="vldt_usr_stngs()"/>
            <span id="frstnm_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter first name</span>
        </div>
        <div class="row">
            <div class="label" style="font-size:13px;">
                Last Name<span class="mandatory">*</span>
            </div>
            <input type="text" id="lstnm" class="textbox" value="<%=rs.getString(2)%>"  onblur="vldt_usr_stngs()"/>
            <span id="lstnm_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter last name</span>
        </div>                                        
        <div class="row">
            <div class="label" style="font-size:13px;">
                Phone<span class="mandatory">*</span>
            </div>
            <input type="text" id="phn" class="required textbox"  onblur="vldt_usr_stngs()" value="<%=rs.getString(3)%>"/>
            <span id="phn_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter valid phone no.</span>
        </div>
        <div class="row">
            <div class="label" style="font-size:13px;">
                Mobile
            </div>
            <input type="text" id="mbl" class="textbox" value="<%=rs.getString(4)%>"  onblur="vldt_usr_stngs()"/>
            <span id="mbl_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter valid cell no.</span>
        </div>

        <div class="row">
            <div class="label" style="font-size:13px;">
                City
            </div>
            <input type="text" id="cty" class="textbox" value="<%=rs.getString(5)%>"/>
        </div>

        <div class="row">
            <div class="label" style="font-size:13px;">
                Country<span class="mandatory">*</span>
            </div>
            <input type="text" readonly class="textbox" value="<%=rs.getString(8)%>"/>
            <!--select 
                class="selectbox">
                <option value="">--Select Country--</option>
            <%
                /*  Statement statement2 = null;
                 Connection con2 = null;
                 ResultSet rs2 = null;
                 try {
                 con2 = DBConnect.instance().getConnection();
                 statement2 = con2.createStatement();
                 rs2 = statement2.executeQuery("SELECT country_name,country_code FROM sm_config_country_code order by country_code asc");
                 while (rs2.next()) {
                 out.print("<option>" + rs2.getString(1) + "</option>");
                 }

                 } catch (Exception e) {
                 //return e+"";
                 System.out.print("error" + e);
                 } finally {
                 try {
                 con2.close();
                 } catch (Exception e) {
                 out.print("error" + e);
                     }
                     }
                     */%>
        </select--> <label for="countryregion_0" generated="true" class="error"
                               id="countryregion_0err"></label>
        </div>


        <div class="row">
            <div class="label"style="font-size:13px;">State/Province</div>
            <div id="select_0_container"><!--select id='state_0' name='state' class="selectbox">
                    <option>--Select State--</option>
                </select> 
                <label for="state_0" generated="true" class="error"
                       id="state_0err"></label--></div>

            <input type="text" readonly class="textbox"  value="<%=rs.getString(6)%>">
        </div>


        <div class="row">
            <div class="label" style="font-size:13px;">
                Zip Code/Postal Code
            </div>
            <input type="text" id="zpcd" class="textbox" value="<%=rs.getString(7)%>"/>
        </div>
        <div class="row">
            <div class="label" style="font-size:13px;">
                User Type<span class="mandatory">*</span>
            </div>
            <select class="textbox"  style="height: 28px;width:190px;" name="usr_tp" id="usr_tp" >                                        
                <option>Intranet User</option>
                <option>Regular</option>
            </select>                                    
        </div>   
        <div class="row">
            <div class="label" style="font-size:13px;">Department</div>
            <input type="text" name="dprtmnt" id="dprtmnt" class="textbox" value="<%=rs.getString(9)%>"/>
        </div>
        <div class="row">
            <div class="label" style="font-size:13px;">Manager/Supervisor</div>
            <input type="text" name="usr_rl" id="usr_rl" class="textbox" value="<%=rs.getString(10)%>"/>
        </div>
        <div class="row">
            <div class="label" style="font-size:13px;">Fax</div>
            <input type="text" name="fx" id="fx" class="textbox" value="<%=rs.getString(11)%>"/>
        </div>
        <div style="margin-left: 20%">
            <input
                type="button" value="Reset" class="gen-btn-Gray" onclick="reset('usermgmtfrm');
                        resetNewUserFrm();"/>
            <input type="button" value="Update" style="margin-left: 30px;"  class="gen-btn-Orange" onclick="sv_usr_stngs('<%=request.getParameter("id")%>');" />
        </div>
        <div id="newusererr" class="usermgmterr"></div>
    </div>
</div>
<div style="width:50%;height:92%;float:left;">
    <div class="popupcontent" style="border-bottom: 2px solid;border-bottom-color: #A4A4A4; padding:10px;margin-top:0px;">

        <div class="policies_sub_head">Account Restriction</div>
        <div class="policies_row" >
            <div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="change_password" /><div></div></div> <label
                for="change_password" class="policies_checkbox_lbl">User must change password at
                next login</label>
        </div>
        <div class="policies_row" >
            <div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="acc_disable" /><div></div></div> <label
                for="acc_disable" class="policies_checkbox_lbl">Account is disabled</label>
        </div>
        <div class="policies_sub_head">Account Expiration</div>
        <div class="policies_row" >
            <div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="exp_end" /><div></div></div> <label for="exp_end" class="policies_checkbox_lbl">End
                of</label>&nbsp; <input id="policies_spinner" style="width:20px;"name="value"><label>&nbsp;days</label>
        </div>
        <div class="policies_row">
            <div class="checkContainer"><input type="checkbox" class="policies_checkbox_lbl" id="exp_never" /><div></div></div> <label for="exp_never" class="policies_checkbox_lbl">Never</label>
        </div>
        <input onclick="savePolicies();" style="margin-top:20px;margin-left:200px;" type="submit" value="Save" class="gen-btn-Orange" />
        <div style="float: left;width: 100%;padding:10px;margin-top:10px;">
            <div class="grouppriv">
                <fieldset class="privfieldset">
                    <legend style="color:#FF9966">Privileges</legend><br>
                    <div class="privleft" style="width: 45%;float:left; ">
                        <div class="listitem" style="float: left">
                            <div class="checkContainer"><input type="checkbox" class="checkbox" id="addusers" /><div></div></div>
                            <label for="addusers" class="group_pri_lbl">Add New Users</label>

                        </div>
                        <div class="listitem" style="float: left">
                            <div class="checkContainer"><input type="checkbox" class="checkbox"  id="delusers"><div></div></div>
                            <label for="delusers"  class="group_pri_lbl">Delete Users</label>
                        </div>                        
                        <div class="listitem" style="float: left">
                            <div class="checkContainer"><input type="checkbox" class="checkbox"  id="addbuyers"><div></div></div>
                            <label for="addbuyers" class="group_pri_lbl">Add Buyers</label>
                        </div>                       
                        <div class="listitem" style="float: left">
                            <div class="checkContainer"><input type="checkbox" class="checkbox"  id="accessusermgmt"><div></div></div>
                            <label for="accessusermgmt" class="group_pri_lbl">Access User Management</label>
                        </div>

                    </div>									
                    <div class="privright" style="width:40%; float:right;">

                        <div class="listitem" style="float: left">
                            <div class="checkContainer"><input type="checkbox" class="checkbox"  id="postanncemnt"><div></div></div>
                            <label for="postanncemnt" class="group_pri_lbl">Post Announcements</label>
                        </div>
                        <div class="listitem" style="float: left">
                            <div class="checkContainer"><input type="checkbox" class="checkbox"  id="rate"><div></div></div>
                            <label for="rate" class="group_pri_lbl">Rate Buyers/Suppliers</label>
                        </div>
                        <div class="listitem" style="float: left">
                            <div class="checkContainer"><input type="checkbox" class="checkbox"  id="creategroup"><div></div></div>
                            <label for="creategroup" class="group_pri_lbl">Create User Group</label>
                        </div>
                        <div class="listitem" style="float: left">
                            <div class="checkContainer"><input type="checkbox" class="checkbox"  id="delgroup"><div></div></div>
                            <label for="delgroup" class="group_pri_lbl">Delete User Group</label>
                        </div>                     
                    </div>
                    <div class="groupupt" style="margin-top:80px;margin-left:30%;">
                        <br><input type="button"  id="updatepribtn" class="gen-btn-Orange" value="Update" />
                    </div>
                    <div id="groupprierr" class="grouperror"></div>
                </fieldset>
            </div>
        </div>
    </div>
</div>      
<%
        }

    } catch (SQLException e) {
        System.out.print("error" + e);
    } finally {
        try {
            con.close();
        } catch (SQLException ex) {
            System.out.print("error" + ex);
        }
    }
%>

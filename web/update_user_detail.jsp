<%-- 
    Document   : update_user_detail
    Created on : Apr 21, 2014, 4:32:33 PM
    Author     : intel-i5
--%>

<%@page import="db.utils.DBConnect"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import="core.login.SessionData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    SessionData sessionObj = (SessionData)session.getAttribute("UserSessionData");
    Statement statement = null;
    Connection con = null;
    ResultSet rs = null;
    try {
        con = DBConnect.instance().getConnection();
        statement = con.createStatement();
        rs = statement.executeQuery("SELECT user_profile.first_name,user_profile.last_name,user_profile.phone,user_profile.cell,user_profile.department,user_profile.user_role,user_profile.fax,mailing_address.city_name,mailing_address.state_name,mailing_address.zipcode,mailing_address.country_name,mailing_address.address FROM user_profile INNER JOIN mailing_address ON user_profile.email=mailing_address.email where user_profile.email='" + sessionObj.username_ + "'");
        if (rs.next()) {
%>
<div style="width:100%;">
    <div class="leftcontent" style="width:45%;float: left;height: auto;">
        <div class="row">
            <div class="label">
                Country<span class="mandatory">*</span>
            </div>
            <input type="text" name="fax" class="textbox" value="<%=rs.getString(11) %>" readonly/>
        </div>
        <div class="row">
            <div class="label">
                Company<span class="mandatory">*</span>
            </div>
            <input type="text" name="fax" class="textbox" value="<%=sessionObj.companyName_ %>" readonly/>
        </div>       
            <div class="row">
            <div class="label">
                First Name<span class="mandatory">*</span>
            </div>
            <input type="text" name="frstnm" id="frstnm"
                   class="required textbox" value="<%=rs.getString(1) %>" onblur="vldt_usr_dtl();"/>
            <span id="frstnm_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter first name</span>
        </div>
        <div class="row">
            <div class="label">
                Last Name<span class="mandatory">*</span>
            </div>
            <input type="text" name="lstnm" id="lstnm"
                   class="required textbox"  value="<%=rs.getString(2) %>"  onblur="vldt_usr_dtl();"/>
            <span id="lstnm_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter last name</span>
        </div>

        <div class="row">
            <div class="label">Address</div>
            <input type="text" name="adrs" id="adrs" class="textbox"  value="<%=rs.getString(12) %>"/>
        </div>
        <div class="row">
            <div class="label">City</div>
            <input type="text" name="cty" id="cty" class="textbox"  value="<%=rs.getString(8) %>"/>
        </div>
        <div class="row">
            <div class="label">State/Province</div>
            <input type="text" name="st" id="st" class="textbox" value="<%=rs.getString(9) %>"/>
        </div>
        <div class="row">
            <div class="label">Zip Code/Postal Code</div>
            <input type="text" name="zpcd" id="zpcd" class="textbox"  value="<%=rs.getString(10) %>"/>
        </div>			

    </div>
    <!--div class="formseparator" style="margin-top: 20px; height: 480px;"></div-->
    <div class="rightcontent" style="width:45%;float: right;height: auto;">
        <!--<div class="row" style="height: 100px;">-->
        <!--<div class="label">
                User Type<span class="mandatory">*</span>
        </div>
        <fieldset class="fieldset" style="width: 160px;">
                <legend> User Type </legend>-->
        <input type="hidden" value="Intranet User" name="usertype" id="internetuser" />
        <!--                                                        <input type="radio" style="vertical-align: middle; font-size: 12px;margin:0px;" disabled name="usertype" id="internetuser"
                                                                        checked class="radiobtn required" value="IntranetUser"
                                                                        style="vertical-align:middle;font-size: 12px;" /> 
                                                                <label for="internetuser"
                                                                        style="vertical-align: middle; font-size: 12px; color: #282828">
                                                                        Company User (Intranet) &nbsp;</label><BR /> -->
        <!--							<input type="radio" id="transuser"
                                                                       name="usertype"  readonly disabled class="radiobtn" style="vertical-align: middle; font-size: 12px;margin:0px;"
                                                                        value="TransactionUser"
                                                                        style="vertical-align:middle;font-size: 12px;" /> 
                                                                <label for="transuser"
                                                                        style="vertical-align: middle; font-size: 12px; color: #282828">
                                                                        Regular UserIndividual User (Regular) &nbsp;</label> 
                                                                <label for="usertype" checked generated="true"
                                                                        class="error" style="margin-left: 0px !important; width: 150px;"></label>-->

        <!--</fieldset>-->

        <!--</div>-->
        <div class="row">
            <div class="label">
                Email<span class="mandatory">*</span>
            </div>
            <input type="text" name="email" id="email" readonly
                   class="required textbox email" value="<%=sessionObj.username_ %>"/>            
        </div>        
        <div class="row">
            <div class="label">Department</div>
            <input type="text" name="department" class="textbox" value="<%=rs.getString(5) %>" readonly/>
        </div>
        <div class="row">
            <div class="label">Manager/Supervisor</div>
            <input type="text" name="managersupervisor" class="textbox" value="<%=rs.getString(6) %>" readonly/>
        </div>
        <div class="row">
            <div class="label">
                Phone<span class="mandatory">*</span>
            </div>
            <input type="text" name="phn" id="phn" value="<%=rs.getString(3) %>" class="required textbox"  onblur="vldt_usr_dtl();"/>
         <span id="phn_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter valid phone no.</span>
        </div>
        <div class="row">
            <div class="label">Cell</div>
            <input type="text" name="mbl" id="mbl" class="textbox" value="<%=rs.getString(4) %>"  onblur="vldt_usr_dtl();"/>
            <span id="mbl_vldtn" style="color: red;float:left;width:185px;margin-left:200px;display:none;">Enter valid cell no.</span>
        </div>
        <div class="row">
            <div class="label">Fax</div>
            <input type="text" name="fx" id="fx" class="textbox" value="<%=rs.getString(7) %>"/>
        </div>					
    </div>
</div>
<div style="margin-left: 380px; margin-top: 45px; float: left;">

    <input
        type="button"  value="Reset"
        onclick="document.getElementById('updt_dtl_bx').reset();" class="gen-btn-Gray" />
    <input type="button" style="margin-left: 60px;" value="Update" class="gen-btn-Orange"  onclick="sv_usr_dtl('<%=sessionObj.username_ %>')"/>
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

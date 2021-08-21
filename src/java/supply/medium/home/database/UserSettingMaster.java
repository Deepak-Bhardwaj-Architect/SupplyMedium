/*
 * 
 * 
 * 
 */
package supply.medium.home.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import supply.medium.home.bean.UserSettingBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class UserSettingMaster {

    public static int insert(String userKey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into user_setting_master"
                    + "(user_key,user_status,new_email,use_registered_email,"
                    + "new_mobile,workinghours_notify,nonworkinghours_notify,workinghours_mode,"
                    + "nonworkinghours_mode,stop_notify,stoptime_from,stoptime_to,"
                    + "working_days,sun_from,sun_to,mon_from,"
                    + "mon_to,tue_from,tue_to,wed_from,"
                    + "wed_to,thu_from,thu_to,fri_from,"
                    + "fri_to,sat_from,sat_to) "
                    + "values('" + userKey + "','Not Connected','','no',"
                    + "'','yes','yes', 'Email',"
                    + " 'Email','no', sysdate(), sysdate(),"
                    + "'1234567','09:00:00', '17:00:00', '09:00:00',"
                    + "'17:00:00', '09:00:00', '17:00:00','09:00:00',"
                    + "'17:00:00', '09:00:00', '17:00:00', '09:00:00',"
                    + "'17:00:00', '09:00:00', '17:00:00') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in UserSettingMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in UserSettingMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showAllUserSetting(String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        UserSettingBean usb = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select user_status,new_email,use_registered_email,"
                    + "new_mobile,workinghours_notify,"
                    + "nonworkinghours_notify,workinghours_mode,"
                    + "nonworkinghours_mode,stop_notify,stoptime_from,stoptime_to,"
                    + "working_days,sun_from,sun_to,mon_from,mon_to,tue_from,"
                    + "tue_to,wed_from,wed_to,thu_from,thu_to,fri_from,fri_to,"
                    + "sat_from,sat_to from user_setting_master "
                    + "where user_key='" + userKey + "'";
            rs = st.executeQuery(query);
            al = new ArrayList();
            if (rs.next()) {
                usb = new UserSettingBean();
                usb.setUserStatus(rs.getString(1));
                usb.setNewEmail(rs.getString(2));
                usb.setUseRegisteredEmail(rs.getString(3));
                usb.setNewMobileNo(rs.getString(4));
                usb.setWorkinghoursNotify(rs.getString(5));
                usb.setNonworkinghoursNotify(rs.getString(6));
                usb.setWorkinghoursMode(rs.getString(7));
                usb.setNonworkinghoursMode(rs.getString(8));
                usb.setStopNotify(rs.getString(9));
                usb.setStoptimeFrom(rs.getString(10));
                usb.setStoptimeTo(rs.getString(11));
                usb.setWorkingDays(rs.getString(12));
                usb.setSunFrom(rs.getString(13));
                usb.setSunTo(rs.getString(14));
                usb.setMonFrom(rs.getString(15));
                usb.setMonTo(rs.getString(16));
                usb.setTueFrom(rs.getString(17));
                usb.setTueTo(rs.getString(18));
                usb.setWedFrom(rs.getString(19));
                usb.setWedTo(rs.getString(20));
                usb.setThuFrom(rs.getString(21));
                usb.setThuTo(rs.getString(22));
                usb.setFriFrom(rs.getString(23));
                usb.setFriTo(rs.getString(24));
                usb.setSatFrom(rs.getString(25));
                usb.setSatTo(rs.getString(26));
                al.add(usb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllUserSetting in UserSettingMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllUserSetting in UserSettingMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static int updateTiming(String userKey, String workingDays, String sunFrom, String sunTo,
            String monFrom, String monTo, String tueFrom, String tueTo, String wedFrom, String wedTo,
            String thuFrom, String thuTo, String friFrom, String friTo, String satFrom, String satTo) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update user_setting_master set "
                    + "working_days='" + workingDays + "',sun_from='" + sunFrom + "',sun_to='" + sunTo + "',"
                    + "mon_from='" + monFrom + "',mon_to='" + monTo + "',"
                    + "tue_from='" + tueFrom + "',tue_to='" + tueTo + "',"
                    + "wed_from='" + wedFrom + "',wed_to='" + wedTo + "',"
                    + "thu_from='" + thuFrom + "',thu_to='" + thuTo + "',"
                    + "fri_from='" + friFrom + "',fri_to='" + friTo + "',"
                    + "sat_from='" + satFrom + "',sat_to='" + satTo + "' "
                    + "where user_key='" + userKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateTiming in UserSettingMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updateTiming in UserSettingMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int updateStatus(String userKey, String status) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update user_setting_master set "
                    + "user_status='" + status + "' "
                    + "where user_key='" + userKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateStatus in UserSettingMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updateStatus in UserSettingMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int updateNotificationSetting(String userKey, String newEmail, String useRegisteredEmail,
            String newMobileNo, String workinghoursNotify, String nonworkinghoursNotify,
            String workinghoursMode, String nonworkinghoursMode, String stopNotify,
            String stoptimeFrom, String stoptimeTo) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update user_setting_master set "
                    + "new_email='" + newEmail + "',"
                    + "use_registered_email='" + useRegisteredEmail + "',"
                    + "new_mobile='" + newMobileNo + "',"
                    + "workinghours_notify='" + workinghoursNotify + "',"
                    + "nonworkinghours_notify='" + nonworkinghoursNotify + "',"
                    + "workinghours_mode='" + workinghoursMode + "',"
                    + "nonworkinghours_mode='" + nonworkinghoursMode + "',"
                    + "stop_notify='" + stopNotify + "',"
                    + "stoptime_from='" + stoptimeFrom + "',"
                    + "stoptime_to='" + stoptimeTo + "' "
                    + "where user_key='" + userKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateNotificationSetting in UserSettingMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updateNotificationSetting in UserSettingMaster : " + ex.getMessage());
            }
        }
        return result;
    }

}

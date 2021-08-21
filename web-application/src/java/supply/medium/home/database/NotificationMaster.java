/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import supply.medium.home.bean.NotificationBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Lenovo
 */
public class NotificationMaster {

    public static int insert(String userKeyFrom, String userKeyTo, String companyKeyFrom,
            String companyKeyTo, String notificationType, String notificationTypeId, String notificationMessage) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "notification_master(user_key_from,user_key_to,company_key_from,company_key_to,"
                    + "notification_type,notification_type_id,notification_message,status,notification_on) "
                    + "values('" + userKeyFrom + "','" + userKeyTo + "','" + companyKeyFrom + "',"
                    + "'" + companyKeyTo + "','" + notificationType + "','" + notificationTypeId + "','" + notificationMessage + "',"
                    + "'Unread',sysdate()) ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in NotificationMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in NotificationMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showNotiifcationTypeCount(String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        NotificationTypeCountBean nb = null;
        ArrayList al = null;
        String query = "";
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            query = "select notification_type,count(*),replace(replace(notification_type,'@#@Buyer',''),'@#@Supplier','') as nt from notification_master where status='Unread' and user_key_to='" + userKey + "' and notification_type not in('Chat') group by nt;";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                nb = new NotificationTypeCountBean();
                nb.setType(rs.getString(1));
                nb.setCount(rs.getString(2));
                al.add(nb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showNotiifcationTypeCount in NotificationMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByStatus in showNotiifcationTypeCount : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showByStatus(String status, String userKey, String notificationType) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        NotificationBean nb = null;
        ArrayList al = null;
        String query = "";
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            if (status.equals("all")) {
                query = "select * "
                        + "from notification_master "
                        + "where user_key_to='" + userKey + "' "
                        + "order by notification_key desc";
            } else {
                query = "select * "
                        + "from notification_master "
                        + "where user_key_to='" + userKey + "' "
                        + "and status='" + status + "' "
                        + "and notification_type='" + notificationType + "' "
                        + "and notification_type not in('Chat')"
                        + "order by notification_key desc";
            }

            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                nb = new NotificationBean();
                nb.setNotificationKey(rs.getString(1));
                nb.setUserKeyFrom(rs.getString(2));
                nb.setUserKeyTo(rs.getString(3));
                nb.setCompanyKeyFrom(rs.getString(4));
                nb.setCompanyKeyTo(rs.getString(5));
                nb.setNotificationType(rs.getString(6));
                nb.setNotificationTypeId(rs.getString(7));
                nb.setNotificationMessage(rs.getString(8));
                nb.setStatus(rs.getString(9));
                nb.setNotificationOn(rs.getString(10).replace(".", "@#@").split("@#@")[0]);
                al.add(nb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByStatus in NotificationMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByStatus in NotificationMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showByStatusChat(String status, String userKey, String notificationType) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        NotificationBean nb = null;
        ArrayList al = null;
        String query = "";
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            if (status.equals("all")) {
                query = "select * "
                        + "from notification_master "
                        + "where user_key_to='" + userKey + "' "
                        + "order by notification_key desc";
            } else {
                query = "select * "
                        + "from notification_master "
                        + "where user_key_to='" + userKey + "' "
                        + "and status='" + status + "' "
                        + "and notification_type='" + notificationType + "' "
                        + "and notification_type in('Chat')"
                        + "order by notification_key desc";
            }
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                nb = new NotificationBean();
                nb.setNotificationKey(rs.getString(1));
                nb.setUserKeyFrom(rs.getString(2));
                nb.setUserKeyTo(rs.getString(3));
                nb.setCompanyKeyFrom(rs.getString(4));
                nb.setCompanyKeyTo(rs.getString(5));
                nb.setNotificationType(rs.getString(6));
                nb.setNotificationTypeId(rs.getString(7));
                nb.setNotificationMessage(rs.getString(8));
                nb.setStatus(rs.getString(9));
                nb.setNotificationOn(rs.getString(10).replace(".", "@#@").split("@#@")[0]);
                al.add(nb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByStatusChat in NotificationMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByStatusChat in NotificationMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showByUserKey(String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        NotificationBean nb = null;
        ArrayList al = null;
        String query = "";
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            query = "select * "
                    + "from notification_master "
                    + "where user_key_to='" + userKey + "' "
                    + "order by notification_key desc";
//                ErrorMaster.insert(query);
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                nb = new NotificationBean();
                nb.setNotificationKey(rs.getString(1));
                nb.setUserKeyFrom(rs.getString(2));
                nb.setUserKeyTo(rs.getString(3));
                nb.setCompanyKeyFrom(rs.getString(4));
                nb.setCompanyKeyTo(rs.getString(5));
                nb.setNotificationType(rs.getString(6));
                nb.setNotificationTypeId(rs.getString(7));
                nb.setNotificationMessage(rs.getString(8));
                nb.setStatus(rs.getString(9));
                nb.setNotificationOn(rs.getString(10).replace(".", "@#@").split("@#@")[0]);
                al.add(nb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByUserKey in NotificationMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByUserKey in NotificationMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static int delete(String userKey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from notification_master "
                    + "where user_key_to='" + userKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in NotificationMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in NotificationMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public int update(String notificationKey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "";
            query = "update notification_master set status='Read' "
                    + "where notification_key='" + notificationKey + "'";
//            query = "delete from notification_master "
//                    + "where notification_key='" + notificationKey + "'";
//            System.out.println(query);
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at update in NotificationMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing update in NotificationMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static String globalNotificationCount(String userKey) {
        String result = "0";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select count(*) from notification_master "
                    + "where user_key_to='" + userKey + "' "
                    + "and status='Unread' "
                    + "and notification_type not in('Chat')";
            rs = st.executeQuery(query);
            if (rs.next()) {
                result = rs.getString(1);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in NotificationMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in NotificationMaster : " + ex.getMessage());
            }
        }
        return result;
    }
}

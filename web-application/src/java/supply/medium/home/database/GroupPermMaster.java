/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.database;

import java.sql.*;
import supply.medium.home.bean.GroupPermBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class GroupPermMaster {
    
    public static int insert(String groupKey,String a,String b,String c,String d,
            String e,String f,String g,String h,String i,String j,
            String k,String l,String m,String n) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "group_perm_master "
                    + "values('" + groupKey + "','" + a + "','" + b + "','" + c + "','" + d 
                    + "','" + e + "','" + f + "','" + g + "','" + h + "','" + i + "','" + j 
                    + "','" + k + "','" + l + "','" + m + "','" + n + "') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in GroupPermMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in GroupPermMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static int delete(String groupKey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from "
                    + "group_perm_master "
                    + "where group_perm_key='" + groupKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in GroupPermMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in GroupPermMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static GroupPermBean showPermByGroupKey(String groupKey)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GroupPermBean gpb = null;
        try {

            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * from group_perm_master "
                    + "where group_perm_key='"+groupKey+"'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                gpb = new GroupPermBean();
                gpb.setGroup_perm_key(rs.getString(1));
                gpb.setAdd_user(rs.getString(2));
                gpb.setDelete_user(rs.getString(3));
                gpb.setAdd_vendor(rs.getString(4));
                gpb.setDelete_vendor(rs.getString(5));
                gpb.setAccess_user_mngmt(rs.getString(6));
                gpb.setPost_announcements(rs.getString(7));
                gpb.setRate_vendor(rs.getString(8));
                gpb.setAdd_group(rs.getString(9));
                gpb.setDelete_group(rs.getString(10));
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showPermByGroupKey in GroupPermMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showPermByGroupKey in GroupPermMaster : " + ex.getMessage());
            }
        }
        return gpb;
    }
    
}

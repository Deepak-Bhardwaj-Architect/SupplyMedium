/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.database;

import java.sql.*;
import supply.medium.home.bean.DepartmentPermBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class DepartmentPermMaster {
    
    public static int insert(String deptKey,String a,String b,String c,String d,
            String e,String f) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "department_perm_master "
                    + "values('" + deptKey + "','" + a + "','" + b + "','" + c + "','" + d 
                    + "','" + e + "','" + f + "') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in DepartmentPermMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in DepartmentPermMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static int delete(String deptKey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from "
                    + "department_perm_master "
                    + "where department_perm_key='" + deptKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in DepartmentPermMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in DepartmentPermMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static DepartmentPermBean showPermByDeptKey(String deptKey)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        DepartmentPermBean dpb = null;
        try {

            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * from department_perm_master "
                    + "where department_perm_key='"+deptKey+"'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                dpb = new DepartmentPermBean();
                dpb.setDepartment_perm_key(rs.getString(1));
                dpb.setAdd_folder(rs.getString(2));
                dpb.setDelete_folder(rs.getString(3));
                dpb.setAdd_file(rs.getString(4));
                dpb.setDelete_file(rs.getString(5));
                dpb.setPost_announcements(rs.getString(6));
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showPermByDeptKey in DepartmentPermMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showPermByDeptKey in DepartmentPermMaster : " + ex.getMessage());
            }
        }
        return dpb;
    }
    
}

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
import supply.medium.home.bean.DepartmentBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author !!ULTIMATE
 */
public class DepartmentMaster {

    public static ArrayList showCompanyDepartment(String companyKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        DepartmentBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select department_key,department_name "
                    + "from department_master where company_key='" + companyKey + "'";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new DepartmentBean();
                ub.setDepartmentKey(rs.getString(1));
                ub.setDepartmentName(rs.getString(2));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showCompanyDepartment in DepartmentMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showCompanyDepartment in DepartmentMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static int insert(String deptKey,String companyKey, String departmentName) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "department_master(department_key,company_key,department_name) "
                    + "values('" + deptKey + "','" + companyKey + "','" + departmentName + "') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in DepartmentMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in DepartmentMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int update(String departmentKey, String departmentName) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update department_master "
                    + "set department_name='" + departmentName + "' "
                    + "where department_key='" + departmentKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at update in DepartmentMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing update in DepartmentMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int delete(String departmentKey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from department_master "
                    + "where department_key='" + departmentKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in DepartmentMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in DepartmentMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showDepartmentList(String userKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        DepartmentBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * from department_master "
                    + "where department_key in(select department_key from department_group_master "
                    + "where group_key in(select group_key from group_user_master where user_key='"+userKey+"'))";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new DepartmentBean();
                ub.setDepartmentKey(rs.getString(1));
                ub.setDepartmentName(rs.getString(3));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showDepartmentList in DepartmentMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showDepartmentList in DepartmentMaster : " + ex.getMessage());
            }
        }
        return al;
    }
}

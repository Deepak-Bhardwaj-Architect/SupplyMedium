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
import supply.medium.home.bean.GroupBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author !!ULTIMATE
 */
public class GroupMaster {

    public static ArrayList showCompanyGroup(String companyKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GroupBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select group_key,group_name "
                    + "from group_master where company_key='" + companyKey + "'"
                    + " order by group_name";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new GroupBean();
                ub.setGroupKey(rs.getString(1));
                ub.setGroupName(rs.getString(2));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showCompanyGroup in GroupMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showCompanyGroup in GroupMaster : " + ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList searchCompanyGroup(String companyKey, String groupName) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GroupBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select group_key,group_name "
                    + "from group_master "
                    + "where company_key='" + companyKey + "' "
                    + "and group_name like '"+groupName+"%'";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new GroupBean();
                ub.setGroupKey(rs.getString(1));
                ub.setGroupName(rs.getString(2));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at searchCompanyGroup in GroupMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing searchCompanyGroup in GroupMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static int insert(String groupKey,String companyKey, String groupName) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "group_master(group_key,company_key,group_name) "
                    + "values('" + groupKey + "','" + companyKey + "','" + groupName + "') ";
            result = st.executeUpdate(query);
            if(result>0)
            {
                query="no";
                result=GroupPermMaster.insert(groupKey, query, query, query, query, query, query, query, query, query, query, query, query, query, query);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in GroupMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in GroupMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int update(String groupKey, String groupName) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update group_master "
                    + "set group_name='" + groupName + "' "
                    + "where group_key='" + groupKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at update in GroupMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing update in GroupMaster : " + ex.getMessage());
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
            String query = "delete from group_master "
                    + "where group_key='" + groupKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in GroupMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in GroupMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showNonExistingGroupInDepartment(String companyKey, String departmentKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GroupBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select group_key,group_name from group_master "
                    + "where group_key not in "
                    + "(SELECT group_key "
                    + "FROM department_group_master "
                    + "where company_key='"+companyKey+"' "
                    + "and department_key='"+departmentKey+"') "
                    + " and company_key='"+companyKey+"'"
                    + " order by group_name";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new GroupBean();
                ub.setGroupKey(rs.getString(1));
                ub.setGroupName(rs.getString(2));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showNonExistingGroupInDepartment in GroupMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showNonExistingGroupInDepartment in GroupMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showExistingGroupInDepartment(String companyKey, String departmentKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GroupBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select group_key,group_name from group_master "
                    + "where group_key in "
                    + "(SELECT group_key "
                    + "FROM department_group_master "
                    + "where company_key='"+companyKey+"' "
                    + "and department_key='"+departmentKey+"') "
                    + " and company_key='"+companyKey+"'"
                    + " order by group_name";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new GroupBean();
                ub.setGroupKey(rs.getString(1));
                ub.setGroupName(rs.getString(2));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showExistingGroupInDepartment in GroupMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showExistingGroupInDepartment in GroupMaster : " + ex.getMessage());
            }
        }
        return al;
    }
}

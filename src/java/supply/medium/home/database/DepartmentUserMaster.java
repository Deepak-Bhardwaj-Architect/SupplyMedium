/*
 * 
 * 
 * 
 */
package supply.medium.home.database;

import java.sql.*;
import java.util.ArrayList;
import supply.medium.home.bean.DepartmentUserBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author LenovoB560
 */
public class DepartmentUserMaster {
public static int insert(String companyKey,String departmentkey,String userKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "department_user_master(company_key,department_key,user_key) "
                    + "values('"+companyKey+"','"+departmentkey+"','"+userKey+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in departmentUserMaster : "+ex.getMessage());
        }
        finally
        {            
            try
            {
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing insert in departmentUserMaster : "+ex.getMessage());
            }
        }
        return result;
    }
public static int delete(String companyKey,String departmentkey,String userKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="delete from department_user_master "
                    + "where company_key='"+companyKey+"' and department_key='"+departmentkey+"' and user_key='"+userKey+"'";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at delete in departmentUserMaster : "+ex.getMessage());
        }
        finally
        {            
            try
            {
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing delete in departmentUserMaster : "+ex.getMessage());
            }
        }
        return result;
    }
public static ArrayList showDepartmentsOfUser(String userKey)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        DepartmentUserBean gpb = null;
        ArrayList al=new ArrayList();
        try {

            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * from department_user_master "
                    + "where user_key='"+userKey+"'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                gpb = new DepartmentUserBean();
                gpb.setDepartmentUserKey(rs.getString(1));
                gpb.setCompanyKey(rs.getString(2));
                gpb.setDepartmentKey(rs.getString(3));
                gpb.setUserKey(rs.getString(4));
                al.add(gpb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showDepartmentsOfUser in DepartmentUserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showDepartmentsOfUser in DepartmentUserMaster : " + ex.getMessage());
            }
        }
        return al;
    }
}

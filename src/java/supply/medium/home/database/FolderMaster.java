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
import supply.medium.home.bean.FolderBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class FolderMaster {
 public static int insert(String companyKey,String folderName)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        long time=System.currentTimeMillis();
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "folder_master(company_key,folder_key,folder_name) "
                    + "values('"+companyKey+"','"+time+"','"+folderName+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in FolderMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in FolderMaster : "+ex.getMessage());
            }
        }
        return result;
    }

public static int delete(String folderKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="delete from folder_master "
                    + "where folder_key='"+folderKey+"'";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at delete in FolderMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing delete in FolderMaster : "+ex.getMessage());
            }
        }
        return result;
    } 
public static ArrayList showNonExistingFolderInDepartment(String companyKey, String departmentKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        FolderBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select folder_key,folder_name from folder_master "
                    + "where folder_key not in "
                    + "(SELECT folder_key "
                    + "FROM department_folder_master "
                    + "where company_key='"+companyKey+"' "
                    + "and department_key='"+departmentKey+"') "
                    + " and company_key='"+companyKey+"'";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new FolderBean();
                ub.setFolderKey(rs.getString(1));
                ub.setFolderName(rs.getString(2));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showNonExistingFolderInDepartment in FolderMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showNonExistingFolderInDepartment in FolderMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showExistingFolderInDepartment(String companyKey, String departmentKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        FolderBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select folder_key,folder_name from folder_master "
                    + "where folder_key in "
                    + "(SELECT folder_key "
                    + "FROM department_folder_master "
                    + "where company_key='"+companyKey+"' "
                    + "and department_key='"+departmentKey+"') "
                    + " and company_key='"+companyKey+"'";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new FolderBean();
                ub.setFolderKey(rs.getString(1));
                ub.setFolderName(rs.getString(2));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showExistingFolderInDepartment in FolderMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showExistingFolderInDepartment in FolderMaster : " + ex.getMessage());
            }
        }
        return al;
    }
    public static ArrayList showFolderOfDepartment(String companyKey, String departmentKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        FolderBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select folder_key,folder_name from folder_master "
                    + "where folder_key in "
                    + "(SELECT folder_key "
                    + "FROM department_folder_master "
                    + "where company_key='"+companyKey+"' "
                    + "and department_key='"+departmentKey+"') "
                    + " and company_key='"+companyKey+"'";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new FolderBean();
                ub.setFolderKey(rs.getString(1));
                ub.setFolderName(rs.getString(2));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showFolderOfDepartment in FolderMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showFolderOfDepartment in FolderMaster : " + ex.getMessage());
            }
        }
        return al;
    }
    
    public static String insertCorporateDepartmentFolder(String companyKey,String folderName)
    {
        String result="";
        Connection con=null;
        Statement st=null;
        long time=System.currentTimeMillis();
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "folder_master(company_key,folder_key,folder_name) "
                    + "values('"+companyKey+"','"+time+"','"+folderName+"') ";
            st.executeUpdate(query);
            result=time+"";
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insertCorporateDepartmentFolder in FolderMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insertCorporateDepartmentFolder in FolderMaster : "+ex.getMessage());
            }
        }
        return result;
    }

}

/*
 * 
 * 
 * 
 */

package supply.medium.home.database;

import java.sql.Connection;
import java.sql.Statement;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class DepartmentFolderMaster {    
    
 public static int insert(String companyKey,String departmentkey,String folderKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "department_folder_master(company_key,department_key,folder_key) "
                    + "values('"+companyKey+"','"+departmentkey+"','"+folderKey+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in DepartmentFolderMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in DepartmentFolderMaster : "+ex.getMessage());
            }
        }
        return result;
    }
public static int delete(String companyKey,String departmentkey,String folderKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="delete from department_folder_master "
                    + "where company_key='"+companyKey+"' and department_key='"+departmentkey+"' and folder_key='"+folderKey+"'";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at delete in DepartmentFolderMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing delete in DepartmentFolderMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
}

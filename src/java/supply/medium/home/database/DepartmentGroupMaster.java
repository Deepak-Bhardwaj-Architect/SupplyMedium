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
public class DepartmentGroupMaster {
public static int insert(String companyKey,String departmentkey,String groupKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "department_group_master(company_key,department_key,group_key) "
                    + "values('"+companyKey+"','"+departmentkey+"','"+groupKey+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in DepartmentGroupMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in DepartmentGroupMaster : "+ex.getMessage());
            }
        }
        return result;
    }
public static int delete(String companyKey,String departmentkey,String groupKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="delete from department_group_master "
                    + "where company_key='"+companyKey+"' and department_key='"+departmentkey+"' and group_key='"+groupKey+"'";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at delete in DepartmentGroupMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing delete in DepartmentGroupMaster : "+ex.getMessage());
            }
        }
        return result;
    }    
}

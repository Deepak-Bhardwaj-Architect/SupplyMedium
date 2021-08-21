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
}

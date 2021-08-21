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
public class GroupUserMaster {
public static int insert(String companyKey,String groupkey,String userKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "group_user_master(company_key,group_key,user_key) "
                    + "values('"+companyKey+"','"+groupkey+"','"+userKey+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in groupUserMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in groupUserMaster : "+ex.getMessage());
            }
        }
        return result;
    }
public static int delete(String companyKey,String groupkey,String userKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="delete from group_user_master "
                    + "where company_key='"+companyKey+"' and group_key='"+groupkey+"' and user_key='"+userKey+"'";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at delete in groupUserMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing delete in groupUserMaster : "+ex.getMessage());
            }
        }
        return result;
    }
}

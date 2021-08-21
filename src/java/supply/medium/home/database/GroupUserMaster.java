/*
 * 
 * 
 * 
 */
package supply.medium.home.database;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import supply.medium.home.bean.GroupUserBean;
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
    public static ArrayList showGroupsOfUser(String userKey)
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GroupUserBean gpb = null;
        ArrayList al=new ArrayList();
        try {

            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * from group_user_master "
                    + "where user_key='"+userKey+"'";
            rs = st.executeQuery(query);
            if (rs.next()) {
                gpb = new GroupUserBean();
                gpb.setGroupUserKey(rs.getString(1));
                gpb.setCompanyKey(rs.getString(2));
                gpb.setGroupKey(rs.getString(3));
                gpb.setUserKey(rs.getString(4));
                al.add(gpb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showGroupsOfUser in GroupUserMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showGroupsOfUser in GroupUserMaster : " + ex.getMessage());
            }
        }
        return al;
    }
}

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
import supply.medium.home.bean.UserBean;
import supply.medium.home.bean.WatchlistBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class WatchlistMaster {
  
    public static int insert(String userKey,String watchListName) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into watchlist_master "
                    + "(user_key,watch_list_name)"
                    + "values('" + userKey + "','" + watchListName + "') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in WatchlistMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in WatchlistMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static ArrayList showWatchlist(String userKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        WatchlistBean wb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from watchlist_master "
                    + "where user_key='"+userKey+"'";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                wb=new WatchlistBean();
                wb.setWatchlistKey(rs.getString(1));
                wb.setWatchListName(rs.getString(3));
                al.add(wb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showWatchlist in WatchlistMaster : "+ex.getMessage());
        }
        finally
        {            
            try
            {
                rs.close();
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing showWatchlist in WatchlistMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList showMembersToAddInWatchListByCompanyKey(String companyKey,String userPartialName,String userKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        UserBean wb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from user_master um,company_master cm "
                    + "where "
//                    + "um.company_key='"+companyKey+"' and "
                    + "um.company_key=cm.company_key "
                    + "and ("
                        + "first_name like '%"+userPartialName+"%' "
                        + "or last_name like '%"+userPartialName+"%'"
                    + ") "
                    + "and um.user_key not in('"+userKey+"')";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                wb=new UserBean();
                wb.setUserKey(rs.getString("user_key"));
                wb.setFirstName(rs.getString("first_name"));
                wb.setLastName(rs.getString("last_name"));
                wb.setCompanyKey(rs.getString("cm.company_key"));
                wb.setEmail(rs.getString("email"));
                wb.setDepartment(rs.getString("company_name"));
                al.add(wb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showMembersToAddInWatchListByCompanyKey in WatchlistMaster : "+ex.getMessage());
        }
        finally
        {            
            try
            {
                rs.close();
                st.close();
                con.close();
            }
            catch(Exception ex)
            {
                ErrorMaster.insert("Exception at closing showMembersToAddInWatchListByCompanyKey in WatchlistMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static int delete(String watchlistKey) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from watchlist_master "
                    + "where watchlist_key='"+watchlistKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in WatchlistMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in WatchlistMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
}

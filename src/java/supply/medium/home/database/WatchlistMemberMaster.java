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
import supply.medium.home.bean.WatchlistMemberBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class WatchlistMemberMaster {
  
    public static int insert(String watchlistKey,String userKey) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into watchlist_member_master "
                    + "(watchlist_key,user_key)"
                    + "values('" + watchlistKey + "','" + userKey + "') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in WatchlistMemberMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in WatchlistMemberMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static ArrayList showWatchlistMember(String watchlistKey)
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
                    + "from user_master um,company_master cm,watchlist_member_master wmm "
                    + "where um.company_key=cm.company_key "
                    + "and um.user_key=wmm.user_key "
                    + "and watchlist_key='"+watchlistKey+"'";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                wb=new UserBean();
                wb.setUserKey(rs.getString("um.user_key"));
                wb.setFirstName(rs.getString("first_name"));
                wb.setLastName(rs.getString("last_name"));
                wb.setCompanyKey(rs.getString("company_name"));
                wb.setEmail(rs.getString("email"));
                wb.setFax(rs.getString("watchlist_member_key"));
                al.add(wb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showWatchlistMember in WatchlistMemberMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showWatchlistMember in WatchlistMemberMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    
    
    public static ArrayList showMembersAddedInWatchlist(String watchlistKey,String userPartialName)
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
                    + "from user_master um,company_master cm,watchlist_member_master wmm "
                    + "where um.company_key=cm.company_key "
                    + "and um.user_key=wmm.user_key "
                    + "and watchlist_key='"+watchlistKey+"' "
                    + "and ("
                        + "first_name like '%"+userPartialName+"%' "
                        + "or last_name like '%"+userPartialName+"%'"
                    + ")";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                wb=new UserBean();
                wb.setUserKey(rs.getString("um.user_key"));
                wb.setFirstName(rs.getString("first_name"));
                wb.setLastName(rs.getString("last_name"));
                wb.setCompanyKey(rs.getString("company_name"));
                wb.setEmail(rs.getString("email"));
                wb.setFax(rs.getString("watchlist_member_key"));
                al.add(wb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showMembersAddedInWatchlist in WatchlistMemberMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showMembersAddedInWatchlist in WatchlistMemberMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static int delete(String watchlistMemberKey) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from watchlist_member_master "
                    + "where watchlist_member_key in("+watchlistMemberKey+")";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in WatchlistMemberMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in WatchlistMemberMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
}

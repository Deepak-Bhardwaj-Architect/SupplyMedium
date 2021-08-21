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
import supply.medium.home.bean.CountryBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class FeedLikeMaster {
    public static int insert(String feedKey,String userKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "feed_like_master(feed_key,user_key,liked_on) "
                    + "values('"+feedKey+"','"+userKey+"',sysdate()) ";
            result=showFeedLikedByUser(feedKey, userKey);
            if(result==0)
                result=st.executeUpdate(query);
            else
                result=0;
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in FeedLikeMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in FeedLikeMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    public static int showFeedLikesCount(String feedKey)
    {
        int result=0;
        Connection con=null;
        ResultSet rs=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select count(*) as rowcount from "
                    + "feed_like_master where feed_key='"+feedKey+"' ";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                result=rs.getInt("rowcount");
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showFeedLikesCount in FeedLikeMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showFeedLikesCount in FeedLikeMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static int showFeedLikedByUser(String feedKey,String userKey)
    {
        int result=0;
        Connection con=null;
        ResultSet rs=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from feed_like_master "
                    + "where feed_key='"+feedKey+"' "
                    + "and user_key='"+userKey+"'";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                result++;
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showFeedLikedByUser in FeedLikeMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showFeedLikedByUser in FeedLikeMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
}

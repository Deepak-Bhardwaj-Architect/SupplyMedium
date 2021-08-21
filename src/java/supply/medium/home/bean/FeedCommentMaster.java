/*
 * 
 * 
 * 
 */

package supply.medium.home.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class FeedCommentMaster {
 
    public static ArrayList showAll(String feedKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        FeedCommentBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from feed_comment_master where feed_key='"+feedKey+"'";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new FeedCommentBean();
                ub.setFeedCommentKey(rs.getString(1));
                ub.setFeedKey(rs.getString(2));
                ub.setUserKey(rs.getString(3));
                ub.setComment(rs.getString(4));
                ub.setCommentedOn(rs.getString(5));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAll in FeedCommentMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAll in FeedCommentMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static int insert(String feedKey,String userKey,String comment)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "feed_comment_master(feed_key,user_key,comment,commented_on) "
                    + "values('"+feedKey+"','"+userKey+"','"+comment+"',sysdate()) ";
            result=st.executeUpdate(query);
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
    public static int showFeedCommentCount(String feedKey)
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
                    + "feed_comment_master where feed_key='"+feedKey+"' ";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                result=rs.getInt("rowcount");
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showFeedCommentCount in FeedCommentMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showFeedCommentCount in FeedCommentMaster : "+ex.getMessage());
            }
        }
        return result;
    }   
}

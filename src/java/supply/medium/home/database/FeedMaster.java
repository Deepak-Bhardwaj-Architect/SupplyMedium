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
import supply.medium.home.bean.FeedBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class FeedMaster {

    public static int insert(String feedType,String companyKey, String userKey,String departmentKey, 
            String isFeedCompanyWide, String feedTitle,String feedDescription,String filePath) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into feed_master "
                    + "(feed_type,company_key,user_key,department_key,is_feed_company_wide,"
                    + "feed_title,feed_description,file_path,posted_on)"
                    + "values('" + feedType + "','" + companyKey + "','" + userKey + "',"
                    + "'" + departmentKey + "','" + isFeedCompanyWide + "',"
                    + "'" + feedTitle + "','"+feedDescription+"','"+filePath+"',sysdate()) ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in FeedMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in FeedMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static ArrayList showNewsRoom(String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        FeedBean fb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from feed_master "
                    + "where user_key in("
                        + "select user_key "
                        + "from user_master "
                            + "where company_key in("
                            + "select company_key "
                            + "from company_business_category_master "
                                + "where business_category_key in("
                                + "select business_category_key "
                                + "from company_business_category_master "
                                + "where company_key='"+companyKey+"')"
                            + ")"
                        + ") "
                    + "and feed_type='user feed' "
                    + "order by feed_key desc";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                fb=new FeedBean();
                fb.setFeedKey(rs.getString(1));
                fb.setFeedType(rs.getString(2));
                fb.setCompanyKey(rs.getString(3));
                fb.setUserKey(rs.getString(4));
                fb.setDepartmentKey(rs.getString(5));
                fb.setIsFeedCompanyWide(rs.getString(6));
                fb.setFeedTitle(rs.getString(7));
                fb.setFeedDescription(rs.getString(8));
                fb.setFilePath(rs.getString(9));
                fb.setPostedOn(rs.getString(10));
                al.add(fb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showNewsRoom in FeedMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showNewsRoom in FeedMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList showUserFeed(String userKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        FeedBean fb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from feed_master "
                    + "where user_key='"+userKey+"' "
                    + "and feed_type='user feed' "
                    + "order by feed_key desc";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                fb=new FeedBean();
                fb.setFeedKey(rs.getString(1));
                fb.setFeedType(rs.getString(2));
                fb.setCompanyKey(rs.getString(3));
                fb.setUserKey(rs.getString(4));
                fb.setDepartmentKey(rs.getString(5));
                fb.setIsFeedCompanyWide(rs.getString(6));
                fb.setFeedTitle(rs.getString(7));
                fb.setFeedDescription(rs.getString(8));
                fb.setFilePath(rs.getString(9));
                fb.setPostedOn(rs.getString(10));
                al.add(fb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showUserFeed in FeedMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showUserFeed in FeedMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList showInternalFeed(String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        FeedBean fb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from feed_master "
                    + "where company_key='"+companyKey+"' "
                    + "and is_feed_company_wide='yes' "
                    + "and feed_type='department feed' "
                    + "order by feed_key desc";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                fb=new FeedBean();
                fb.setFeedKey(rs.getString(1));
                fb.setFeedType(rs.getString(2));
                fb.setCompanyKey(rs.getString(3));
                fb.setUserKey(rs.getString(4));
                fb.setDepartmentKey(rs.getString(5));
                fb.setIsFeedCompanyWide(rs.getString(6));
                fb.setFeedTitle(rs.getString(7));
                fb.setFeedDescription(rs.getString(8));
                fb.setFilePath(rs.getString(9));
                fb.setPostedOn(rs.getString(10));
                al.add(fb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showInternalFeed in FeedMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showInternalFeed in FeedMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static int delete(String feedKey) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from feed_master "
                    + "where feed_key='"+feedKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in FeedMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in FeedMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static ArrayList showDepartmentFeed(String departmentKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        FeedBean fb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from feed_master "
                    + "where department_key='"+departmentKey+"' "
//                    + "and is_feed_company_wide='yes' "
                    + "and feed_type='department feed' "
                    + "order by feed_key desc";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                fb=new FeedBean();
                fb.setFeedKey(rs.getString(1));
                fb.setFeedType(rs.getString(2));
                fb.setCompanyKey(rs.getString(3));
                fb.setUserKey(rs.getString(4));
                fb.setDepartmentKey(rs.getString(5));
                fb.setIsFeedCompanyWide(rs.getString(6));
                fb.setFeedTitle(rs.getString(7));
                fb.setFeedDescription(rs.getString(8));
                fb.setFilePath(rs.getString(9));
                fb.setPostedOn(rs.getString(10));
                al.add(fb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showDepartmentFeed in FeedMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showDepartmentFeed in FeedMaster : "+ex.getMessage());
            }
        }
        return al;
    }

}

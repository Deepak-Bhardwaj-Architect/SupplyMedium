/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import supply.medium.home.bean.RatingBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author LenovoB560
 */
public class RatingMaster {
    public static int insert(String ratingKey,String userKeyFrom,String userKeyTo,String companyKeyFrom,
            String companyKeyTo,String ratingTitle,String ratingComment,String rating)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "rating_master(rating_key,user_key_from,user_key_to,company_key_from,company_key_to,"
                    + "rating_title,rating_comment,rating,rating_on) "
                    + "values('"+ratingKey+"','"+userKeyFrom+"','"+userKeyTo+"',"
                    + "'"+companyKeyFrom+"','"+companyKeyTo+"','"+ratingTitle+"',"
                    + "'"+ratingComment+"','"+rating+"',sysdate()) ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in RatingMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in RatingMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    public static RatingBean showRating(String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        RatingBean ratingBean=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            query="SELECT avg(rating),count(rating) "
                    + "FROM rating_master where "
                    + "company_key_to='"+companyKey+"'";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                ratingBean=new RatingBean();
                ratingBean.setRating(rs.getString(1));
                ratingBean.setRatingTitle(rs.getString(2));
            }
           if(ratingBean.getRating()==null){ ratingBean.setRating("0"); }
           if(ratingBean.getRatingTitle()==null){ ratingBean.setRatingTitle("0"); }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showRating in RatingMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showRating in RatingMaster : "+ex.getMessage());
            }
        }
        return ratingBean;
    }
    public static ArrayList showRatingList(String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        RatingBean ratingBean=null;
        ArrayList ratingList=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            query="select rating_key,user_key_from,user_key_to,company_key_from,"
                    + "rating_title,rating_comment,rating,rating_on "
                    + "from rating_master "
                    + "where company_key_to='"+companyKey+"' " ;
            rs=st.executeQuery(query);
            ratingList=new ArrayList();
            while(rs.next())
            {
                ratingBean=new RatingBean();
                ratingBean.setRating(rs.getString(1));
                ratingBean.setUserKeyFrom(rs.getString(2));
                ratingBean.setUserKeyTo(rs.getString(3));
                ratingBean.setCompanyKeyFrom(rs.getString(4));
                ratingBean.setRatingTitle(rs.getString(5));
                ratingBean.setRatingComment(rs.getString(6));
                ratingBean.setRating(rs.getString(7));
                ratingBean.setRatingOn(rs.getString(8));
                ratingList.add(ratingBean);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showRatingList in RatingMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showRatingList in RatingMaster : "+ex.getMessage());
            }
        }
        return ratingList;
    }
}

/*
 * 
 * 
 * 
 */

package supply.medium.home.database;

import java.sql.*;
import java.util.ArrayList;
import supply.medium.home.bean.CompanyAdvertisementBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class CompanyAdvertisementMaster {
    
    public static int insert(String companyKey,String userKey,
            String hoverText,String linkPage,String imagePath)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "company_advertisement_master"
                    + "(company_key,user_key,hover_text,link_page,image_path) "
                    + "values('"+companyKey+"','"+userKey+"','"+hoverText+"',"
                    + "'"+linkPage+"','"+imagePath+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in CompanyAdvertisementMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in CompanyAdvertisementMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static ArrayList showAll(String companyKey,int val)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CompanyAdvertisementBean cb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from company_advertisement_master "
                    + "where company_key in "
                    + "(select company_key from company_business_category_master where business_category_key in"
                    + "("
                    + "select business_category_key from company_business_category_master where company_key='"+companyKey+"'"
                    + ")) "
                    + "and company_key not in('"+companyKey+"') ORDER BY RAND() LIMIT "+val;
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                cb=new CompanyAdvertisementBean();
                cb.setAdvertisementKey(rs.getString(1)); 
                cb.setCompanyKey(rs.getString(2)); 
                cb.setUserKey(rs.getString(3)); 
                cb.setHoverText(rs.getString(4)); 
                cb.setLinkPage(rs.getString(5)); 
                cb.setImagePath(rs.getString(6)); 
                al.add(cb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAll in BusinessCategoryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAll in BusinessCategoryMaster : "+ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showAll()
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CompanyAdvertisementBean cb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from company_advertisement_master order by advertisement_key desc";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                cb=new CompanyAdvertisementBean();
                cb.setAdvertisementKey(rs.getString(1));
                cb.setCompanyKey(rs.getString(2));
                cb.setUserKey(rs.getString(3));
                cb.setHoverText(rs.getString(4));
                cb.setLinkPage(rs.getString(5));
                cb.setImagePath(rs.getString(6));
                al.add(cb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAll in BusinessCategoryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAll in BusinessCategoryMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
}

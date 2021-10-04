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
import supply.medium.home.bean.CompanyBusinessCategoryBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class CompanyBusinessCategoryMaster {
    
    public static ArrayList showAllByCompanyKey(String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CompanyBusinessCategoryBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from company_business_category_master "
                    + "where company_key='"+companyKey+"'";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new CompanyBusinessCategoryBean();
                ub.setCbcKey(rs.getString(1));
                ub.setCountryKey(rs.getString(2));
                ub.setBusinessCategoryKey(rs.getString(3));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAllByCompanyKey in CompanyBusinessCategoryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAllByCompanyKey in CompanyBusinessCategoryMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static String showAllValuesByCompanyKey(String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        String result="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select business_category_name "
                    + "from sm_business_category_master "
                    + "where business_category_key in "
                    + "(select business_category_key "
                    + "from company_business_category_master "
                    + "where company_key='"+companyKey+"')";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                result=rs.getString(1)+",";
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAllValuesByCompanyKey in CompanyBusinessCategoryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAllValuesByCompanyKey in CompanyBusinessCategoryMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static int insert(String companyKey,String businessCategoryKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "company_business_category_master"
                    + "(company_key,business_category_key) "
                    + "values('"+companyKey+"','"+businessCategoryKey+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in CompanyBusinessCategoryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in CompanyBusinessCategoryMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static int delete(String companyKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="delete from "
                    + "company_business_category_master "
                    + "where company_key='"+companyKey+"'";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at delete in CompanyBusinessCategoryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing delete in CompanyBusinessCategoryMaster : "+ex.getMessage());
            }
        }
        return result;
    }
}

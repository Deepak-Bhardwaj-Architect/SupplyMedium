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
import supply.medium.home.bean.CompanyWebsiteBean;
import supply.medium.home.bean.DepartmentBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class CompanyWebsiteMaster {
    public static ArrayList showWebsiteUrl(String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CompanyWebsiteBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select company_website_key,url_name,website_url "
                    + "from company_website_master where company_key='"+companyKey+"'";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new CompanyWebsiteBean();
                ub.setCompanyWebsiteKey(rs.getString(1));
                ub.setUrlName(rs.getString(2));
                ub.setWebsiteUrl(rs.getString(3));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showWebsiteUrl in CompanyWebsiteMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showWebsiteUrl in CompanyWebsiteMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
public static int insert(String companyKey,String urlName,String websiteUrl)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "company_website_master(company_key,url_name,website_url) "
                    + "values('"+companyKey+"','"+urlName+"','"+websiteUrl+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in CompanyWebsiteMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in CompanyWebsiteMaster : "+ex.getMessage());
            }
        }
        return result;
    }
public static int update(String companyKey,String urlName,String websiteUrl)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="update company_website_master "
                    + "set url_name='"+urlName+"',"
                    + "website_Url='"+websiteUrl+"' "
                    + "where company_key='"+companyKey+"'";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at update in CompanyWebsiteMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing update in CompanyWebsiteMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
}

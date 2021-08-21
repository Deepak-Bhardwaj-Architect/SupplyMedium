/*
 * 
 * 
 * 
 */

package supply.medium.home.database;

import java.sql.*;
import java.util.ArrayList;
import supply.medium.home.bean.CompanyBean;
import supply.medium.home.bean.CompanyDetailsForVrBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class CompanyMaster {
    
    public static int insert(String type,String logo,String name,
            String id,String segment,String unit)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "company_master(company_type,company_logo_path,company_name,"
                    + "company_id,segment_division,business_unit,"
                    + "pricing_key,amount_genrated,amount_platform,amount_paid,created_on) "
                    + "values('"+type+"','"+logo+"','"+name+"',"
                    + "'"+id+"','"+segment+"','"+unit+"','0','0','0','0',sysdate()) ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in CompanyMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static int update(String type,String key)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="update company_master set company_type='"+type+"' where company_key='"+key+"' ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at update in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing update in CompanyMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static int showLastCompanyKeyByCompanyId(String companyId)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select max(company_key) "
                    + "from company_master "
                    + "where company_id='"+companyId+"';";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                result=rs.getInt(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showLastCompanyKeyByCompanyId in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showLastCompanyKeyByCompanyId in CompanyMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static int showNoOfCompaniesForVrSearch(String query)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            rs=st.executeQuery(query);
            while(rs.next())
            {
                result=rs.getInt(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showNoOfCompaniesForVrSearch in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showNoOfCompaniesForVrSearch in CompanyMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static int checkExistCompanyId(String companyId)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select company_id "
                    + "from company_master "
                    + "where company_id='"+companyId+"';";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                result=1;
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at checkExistCompanyId in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at checkExistCompanyId insert in CompanyMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static CompanyDetailsForVrBean showCompanyDetailsForVrProcess(String companyKey)
    {
        CompanyDetailsForVrBean obj=null;
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="SELECT c.company_key, company_name,branch, country, address, city, state,zipcode, " +
                "first_name,last_name, department, email, phone, cell, fax, company_type " +
                "FROM company_master c, user_master u, company_mailing_address_master m " +
                "where c.company_key=u.company_key and user_type='Admin' "
                    + "and m.company_key=c.company_key and m.mailing_key in( "
                    + "SELECT min(mailing_key) FROM company_mailing_address_master group by company_key) "
                    + "and c.company_key='"+companyKey+"';";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                obj= new CompanyDetailsForVrBean();
                obj.setCompanyKey(rs.getString(1));
                obj.setCompanyName(rs.getString(2));
                obj.setBranch(rs.getString(3));
                obj.setCountry(rs.getString(4));
                obj.setAddress(rs.getString(5));
                obj.setCity(rs.getString(6));
                obj.setState(rs.getString(7));
                obj.setZipCode(rs.getString(8));
                obj.setContactName(rs.getString(9)+" "+rs.getString(10));               
                obj.setDepartment(rs.getString(11));
                obj.setEmail(rs.getString(12));
                obj.setPhone(rs.getString(13));
                obj.setCell(rs.getString(14));
                obj.setFax(rs.getString(15));
                obj.setTypeOfBusiness(rs.getString(16));              
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showCompanyDetailsForVrProcess in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at showCompanyDetailsForVrProcess insert in CompanyMaster : "+ex.getMessage());
            }
        }
        return obj;
    }
    
    public static ArrayList showAllCompaniesByCountryKey(String countryKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CompanyBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select company_name,company_key "
                    + "from company_master where "
                    + "company_key in("
                        + "SELECT distinct(company_key) "
                        + "FROM company_mailing_address_master "
                        + "where country='"+countryKey+"' "
//                        + "and "
                    + ")";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new CompanyBean();
                ub.setCompanyName(rs.getString(1));
                ub.setCompanyKey(rs.getString(2));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAllCompaniesByCountryKey in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAllCompaniesByCountryKey in CompanyMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList showAllCompaniesByBusinessCategoryKey(String companyKey,int val)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CompanyBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from company_master where "
                    + "company_key in("
                        + "select company_key "
                        + "from company_business_category_master "
                        + "where business_category_key in("
                    + "select business_category_key "
                    + "from company_business_category_master "
                    + "where company_key ='"+companyKey+"') "
//                        + "and "
                    + ") and company_key not in('"+companyKey+"') order by RAND() limit "+val;
//            System.out.println(query);
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new CompanyBean();
                ub.setCompanyKey(rs.getString(1));
                ub.setCompanyType(rs.getString(2));
                ub.setCompanyName(rs.getString(4));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAllCompaniesByBusinessCategoryKey in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAllCompaniesByBusinessCategoryKey in CompanyMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static String getCompanyNameFromKey(String key)
    {
        String result="";
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select company_name "
                    + "from company_master "
                    + "where company_key='"+key+"';";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                result=rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at getCompanyNameFromKey in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing getCompanyNameFromKey in CompanyMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static String getCompanyTypeFromKey(String key)
    {
        String result="";
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select company_type "
                    + "from company_master "
                    + "where company_key='"+key+"';";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                result=rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at getCompanyNameFromKey in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing getCompanyNameFromKey in CompanyMaster : "+ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showVendorSearch(String companyName,String companyType,String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CompanyBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select company_name,company_key "
                    + "from company_master where company_name like "
                    + "'"+companyName+"%' and company_type like "
                    + "'%"+companyType+"%' "
                    + "and company_key not in('"+companyKey+"') "
                    + "and company_key in"
                    + "("
                    + "select company_key_from "
                    + "from vendor_registration_master "
                    + "where company_key_to='"+companyKey+"' "
                    + "and request_status in('Accepted')"
//                    + ")"
//                    + "or company_key not in"
//                    + "("
//                    + "select company_key_from "
//                    + "from vendor_registration_master "
//                    + "where company_key_to='"+companyKey+"' "
//                    + "and request_status in('Accepted')"
                    + ")";
                    
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new CompanyBean();
                ub.setCompanyName(rs.getString(1));
                ub.setCompanyKey(rs.getString(2));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showVendorSearch in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showVendorSearch in CompanyMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList showNonVendorSearch(String companyName,String companyType,String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CompanyBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select company_name,company_key "
                    + "from company_master where company_name like "
                    + "'"+companyName+"%' and company_type like "
                    + "'%"+companyType+"%' "
                    + "and company_key not in('"+companyKey+"') "
                    + "and company_key not in"
                    + "("
                    + "select company_key_from "
                    + "from vendor_registration_master "
                    + "where company_key_to='"+companyKey+"' "
                    + "and request_status in('Accepted')"
                    + ")"
                    + "and company_key not in"
                    + "("
                    + "select company_key_to "
                    + "from vendor_registration_master "
                    + "where company_key_from='"+companyKey+"' "
                    + "and request_status in('Accepted')"
                    + ")";
                    
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new CompanyBean();
                ub.setCompanyName(rs.getString(1));
                ub.setCompanyKey(rs.getString(2));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showNonVendorSearch in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showNonVendorSearch in CompanyMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    public static ArrayList showConnnectedCompany(String companyKeyFrom)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CompanyBean companyBean=null;
        ArrayList connectionList=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            query="select company_name,company_key from company_master c "
                    + "where company_key "
                    + "in(SELECT company_key_to "
                    + "FROM vendor_registration_master "
                    + "where company_key_from='"+companyKeyFrom+"' "
//                    + "or company_key_to='"+companyKeyFrom+"' "
                    + "and request_status='Accepted')";
            rs=st.executeQuery(query);
            connectionList=new ArrayList();
            while(rs.next())
            {
                companyBean=new CompanyBean();
                companyBean.setCompanyName(rs.getString(1));
                companyBean.setCompanyKey(rs.getString(2));
                connectionList.add(companyBean);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showConnnectedCompany in CompanyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showConnnectedCompany in CompanyMaster : "+ex.getMessage());
            }
        }
        return connectionList;
    }
}

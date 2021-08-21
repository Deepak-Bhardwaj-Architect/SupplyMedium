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
import supply.medium.home.bean.BusinessCategoryBean;
import supply.medium.utility.MyConnection;
import supply.medium.utility.ErrorMaster;

/**
 *
 * @author Intel
 */
public class BusinessCategoryMaster {
    
    public static ArrayList showAll()
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        BusinessCategoryBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from sm_business_category_master";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new BusinessCategoryBean();
                ub.setBusinessCategoryKey(rs.getString(1)); 
                ub.setBusinessCategoryName(rs.getString(2));
                al.add(ub);
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
    
    public static ArrayList showAllByBusinessCategorySelected(String businessCategory)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        BusinessCategoryBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from sm_business_category_master "
                    + "where business_category_key in("+businessCategory+")";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new BusinessCategoryBean();
                ub.setBusinessCategoryKey(rs.getString(1)); 
                ub.setBusinessCategoryName(rs.getString(2));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAllByBusinessCategorySelected in BusinessCategoryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAllByBusinessCategorySelected in BusinessCategoryMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static String showBusinessCategoryKeyByName(String businessCategoryName)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        String key=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select business_category_key "
                    + "from sm_business_category_master "
                    + "where business_category_name='"+businessCategoryName+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                key=rs.getString(1); 
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showBusinessCategoryKeyByName in BusinessCategoryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showBusinessCategoryKeyByName in BusinessCategoryMaster : "+ex.getMessage());
            }
        }
        return key;
    }
    
    public static BusinessCategoryBean showBusinessCategoryNameByKey(String businessCategoryKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        String name=null;
        BusinessCategoryBean ub=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from sm_business_category_master "
                    + "where business_category_key='"+businessCategoryKey+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                ub=new BusinessCategoryBean();
                ub.setBusinessCategoryKey(rs.getString(1)); 
                ub.setBusinessCategoryName(rs.getString(2));
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showBusinessCategoryNameByKey in BusinessCategoryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showBusinessCategoryNameByKey in BusinessCategoryMaster : "+ex.getMessage());
            }
        }
        return ub;
    }
}

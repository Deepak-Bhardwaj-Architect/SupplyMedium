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
import supply.medium.home.bean.AccountPolicyBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class CompanyMailingAddressMaster {
    public static int insert(String companyKey,String branch,
            String country,String address,String city,String state,
            String zipcode,String addressType,String addedByUserKey)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "company_mailing_address_master(company_key,branch,country,"
                    + "address,city,state,zipcode,"
                    + "address_type,added_by_user_key,created_on) "
                    + "values('"+companyKey+"','"+branch+"','"+country+"',"
                    + "'"+address+"','"+city+"','"+state+"','"+zipcode+"',"
                    + "'"+addressType+"','"+addedByUserKey+"',sysdate())";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in CompanyMailingAddressMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in CompanyMailingAddressMaster : "+ex.getMessage());
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
                    + "company_mailing_address_master where "
                    + "company_key='"+companyKey+"'";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at delete in CompanyMailingAddressMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing delete in CompanyMailingAddressMaster : "+ex.getMessage());
            }
        }
        return result;
    }
}

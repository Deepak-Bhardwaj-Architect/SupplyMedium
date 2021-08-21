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
import supply.medium.utility.MyConnection;
import supply.medium.utility.ErrorMaster;

/**
 *
 * @author Intel
 */
public class CountryMaster {
    
    public static ArrayList showAll()
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CountryBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from sm_country_master";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new CountryBean();
                ub.setCountryKey(rs.getString(1));
                ub.setCountryName(rs.getString(2));
                ub.setCountryCode(rs.getString(3));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAll in CountryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAll in CountryMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    public static String showCountryFromKey(String country_key)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        String countryName=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select country_name "
                    + "from sm_country_master "
                    + "where country_key='"+country_key+"'";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                countryName=rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showCountryFromKey in CountryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showCountryFromKey in CountryMaster : "+ex.getMessage());
            }
        }
        if(countryName==null)
            countryName=country_key;
        return countryName;
    }
}

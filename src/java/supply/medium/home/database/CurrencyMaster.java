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
import supply.medium.home.bean.CurrencyBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class CurrencyMaster {
 public static ArrayList showAll()
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        CurrencyBean cb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from sm_currency_master";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                cb=new CurrencyBean();
                cb.setCurrencyKey(rs.getString(1));
                cb.setCurrencyCode(rs.getString(2));
                cb.setCurrencyDescription(rs.getString(3));
                al.add(cb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAll in CurrencyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAll in CurrencyMaster : "+ex.getMessage());
            }
        }
        return al;
    }   
 
    public static String showKeyByCode(String code)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        String result="0";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select currency_key "
                    + "from sm_currency_master "
                    + "where currency_code='"+code+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                result=rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showKeyByCode in CurrencyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showKeyByCode in CurrencyMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static String showCodeByKey(String key)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        String result="0";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select currency_code "
                    + "from sm_currency_master "
                    + "where currency_key='"+key+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                result=rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showCodeByKey in CurrencyMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showCodeByKey in CurrencyMaster : "+ex.getMessage());
            }
        }
        return result;
    }
}

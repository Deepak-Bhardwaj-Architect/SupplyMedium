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
import supply.medium.home.bean.QuantityTypeBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class QuantityTypeMaster {
 
    public static ArrayList showAll()
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        QuantityTypeBean qtb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from sm_quantity_type_master";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                qtb=new QuantityTypeBean();
                qtb.setQuantityKey(rs.getString(1));
                qtb.setQuantityCode(rs.getString(2));
                qtb.setQuantityDescription(rs.getString(3));
                al.add(qtb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAll in QuantityTypeMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAll in QuantityTypeMaster : "+ex.getMessage());
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
            String query="select quantity_key "
                    + "from sm_quantity_type_master "
                    + "where quantity_code='"+code+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                result=rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showKeyByCode in QuantityTypeMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showKeyByCode in QuantityTypeMaster : "+ex.getMessage());
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
            String query="select quantity_code "
                    + "from sm_quantity_type_master "
                    + "where quantity_key='"+key+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                result=rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showCodeByKey in QuantityTypeMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showCodeByKey in QuantityTypeMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
}

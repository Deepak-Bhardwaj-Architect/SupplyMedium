/*
 * 
 * 
 * 
 */

package supply.medium.home.database;

import java.sql.Connection;
import java.sql.Statement;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author !!ULTIMATE
 */
public class UserMailingAddressMaster {
    public static int insert(String userKey,String country,
            String address,String city,String state,String zipcode)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "user_mailing_address_master(user_key,country,"
                    + "address,city,state,zipcode,created_on) "
                    + "values('"+userKey+"','"+country+"',"
                    + "'"+address+"','"+city+"','"+state+"','"+zipcode+"',sysdate())";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in UserMailingAddressMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in UserMailingAddressMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static int updateUserAddressByAdmin(String userKey,String city,String address, String zipcode) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update user_mailing_address_master "
                    + "set city='"+city+"',address='"+address+"',zipcode='"+zipcode+"' "
                    + "where user_key='" + userKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateUserAddressByAdmin in UserMailingAddressMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updateUserAddressByAdmin in UserMailingAddressMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
}

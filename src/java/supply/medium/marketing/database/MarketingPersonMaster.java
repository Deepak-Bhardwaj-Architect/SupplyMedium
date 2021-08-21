/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.marketing.database;

import java.sql.*;
import java.util.ArrayList;
import supply.medium.marketing.bean.MarketingPersonBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel8GB
 */
public class MarketingPersonMaster {
    
    public static int insert(String marketing_person_name,String address_details,
            String account_details,String contact_number,String e_mail,
            String pass_word)
    {
        int result=0;Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "marketing_person_master(date_of_joining,marketing_person_name,address_details"
                    + "account_details,contact_number,e_mail,pass_word,user_status) "
                    + "values(sysdate(),'"+marketing_person_name+"','"+address_details+"',"
                    + "'"+account_details+"','"+contact_number+"','"+e_mail+"','"+pass_word+"','Active') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in MarketingAssociateMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in MarketingAssociateMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static ArrayList showAll()
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        MarketingPersonBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from marketing_person_master";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new MarketingPersonBean();
                ub.setMarketing_person_key(rs.getString(1));
                ub.setDate_of_joining(rs.getString(2));
                ub.setMarketing_person_name(rs.getString(3));
                ub.setAddress_details(rs.getString(4));
                ub.setAccount_details(rs.getString(5));
                ub.setContact_number(rs.getString(6));
                ub.setE_mail(rs.getString(7));
                ub.setPass_word(rs.getString(8));
                ub.setUser_status(rs.getString(9));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAllAssociatesOfMarketingPerson in MarketingAssociateMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at showAllAssociatesOfMarketingPerson showAll in MarketingAssociateMaster : "+ex.getMessage());
            }
        }
        return al;
    }
}

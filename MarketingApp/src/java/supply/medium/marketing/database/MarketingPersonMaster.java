/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.marketing.database;

import java.sql.*;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import supply.medium.marketing.bean.MarketingPersonBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel8GB
 */
public class MarketingPersonMaster {
    
    public static int insert(String marketing_person_key,String marketing_person_name,String address_details,
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
                    + "marketing_person_master(marketing_person_key,date_of_joining,marketing_person_name,address_details,"
                    + "account_details,contact_number,e_mail,pass_word,user_status) "
                    + "values('"+marketing_person_key+"',sysdate(),'"+marketing_person_name+"','"+address_details+"',"
                    + "'"+account_details+"','"+contact_number+"','"+e_mail+"','"+pass_word+"','Active') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in MarketingPersonMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in MarketingPersonMaster : "+ex.getMessage());
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
            ErrorMaster.insert("Exception at showAllAssociatesOfMarketingPerson in MarketingPersonMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at showAllAssociatesOfMarketingPerson showAll in MarketingPersonMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static MarketingPersonBean showByKey(String marketing_person_key)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        MarketingPersonBean ub=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from marketing_person_master "
                    + "where marketing_person_key='"+marketing_person_key+"'";
            rs=st.executeQuery(query);
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
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByKey in MarketingPersonMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at showByKey showAll in MarketingPersonMaster : "+ex.getMessage());
            }
        }
        return ub;
    }
    
    public static int checkLoginCredential(String e_mail,String pass_word,HttpSession session)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from marketing_person_master "
                    + "where e_mail='"+e_mail+"' "
                    + "and pass_word='"+pass_word+"' "
                    + "and user_status='Active';";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                session.setAttribute("e_mail", e_mail);
                session.setAttribute("person_name",rs.getString(3));
                session.setAttribute("marketing_person_key", rs.getString(1));
                result=1;
            }
                
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at checkLoginCredential in MarketingPersonMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing checkLoginCredential in MarketingPersonMaster : "+ex.getMessage());
            }
        }
        return result;
    }
}

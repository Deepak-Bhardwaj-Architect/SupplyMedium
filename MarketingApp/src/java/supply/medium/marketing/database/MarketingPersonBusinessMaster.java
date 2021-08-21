/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.marketing.database;

import java.sql.*;
import java.util.ArrayList;
import supply.medium.marketing.bean.MarketingPersonBusinessBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel8GB
 */
public class MarketingPersonBusinessMaster {
    
    public static int insert(String marketing_person_key,String company_key)
    {
        int result=0;Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "marketing_person_business_master(date_of_registration,marketing_person_key,company_key) "
                    + "values(sysdate(),'"+marketing_person_key+"','"+company_key+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in MarketingPersonBusinessMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in MarketingPersonBusinessMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static ArrayList showAllBusinessOfMarketingPerson(String marketing_person_key)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        MarketingPersonBusinessBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from marketing_person_business_master "
                    + "where marketing_person_key='"+marketing_person_key+"';";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new MarketingPersonBusinessBean();
                ub.setMarketing_person_business_key(rs.getString(1));
                ub.setDate_of_registration(rs.getString(2));
                ub.setMarketing_person_key(rs.getString(3));
                ub.setCompany_key(rs.getString(4));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAllAssociatesOfMarketingPerson in MarketingPersonBusinessMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at showAllAssociatesOfMarketingPerson showAll in MarketingPersonBusinessMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
}

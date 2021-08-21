/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.marketing.database;

import java.sql.*;
import java.util.ArrayList;
import supply.medium.marketing.bean.MarketingAssociateBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel8GB
 */
public class MarketingAssociateMaster {
    
    public static int insert(String marketing_associate_key,String marketing_person_key)
    {
        int result=0;Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "marketing_associate_master(marketing_associate_key,marketing_person_key) "
                    + "values('"+marketing_associate_key+"','"+marketing_person_key+"') ";
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
    
    public static ArrayList showAllAssociatesOfMarketingPerson(String marketing_person_key)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        MarketingAssociateBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from marketing_associate_master "
                    + "where marketing_person_key='"+marketing_person_key+"';";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new MarketingAssociateBean();
                ub.setMarketing_associate_key(rs.getString(1));
                ub.setMarketing_person_key(rs.getString(2));
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

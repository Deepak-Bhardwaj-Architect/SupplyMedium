/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import supply.medium.home.bean.DashboardTimeReportBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Vic
 */
public class DashboardTimeReportMaster {
    
    public static ArrayList showReport(String date1, String date2, String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        DashboardTimeReportBean cb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select inv_created_on,sum(inv_total_billing_amount) "
                    + "from transaction_master "
                    + "where company_key_to='"+companyKey+"' "
                    + "and inv_created_on between '"+date1+"' and '"+date2+"' "
                    + "group by inv_created_on";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                cb=new DashboardTimeReportBean();
                cb.setTime(rs.getString(1));
                cb.setAmount(rs.getString(2));
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
    
}

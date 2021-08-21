/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.database;

import java.sql.*;
import java.util.ArrayList;
import supply.medium.home.bean.DashboardCompanyReportBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Vic
 */
public class DashboardCompanyReportMaster {
    
    public static ArrayList showReport(String date1, String date2, String companyKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        DashboardCompanyReportBean cb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select company_key_from,sum(inv_total_billing_amount) "
                    + "from transaction_master "
                    + "where company_key_to='"+companyKey+"' "
                    + "and inv_created_on between '"+date1+"' and '"+date2+"' "
                    + "group by company_key_from";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                cb=new DashboardCompanyReportBean();
                cb.setCompany(rs.getString(1));
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

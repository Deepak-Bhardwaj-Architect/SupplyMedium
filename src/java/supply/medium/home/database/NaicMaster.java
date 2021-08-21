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
import supply.medium.home.bean.NaicBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class NaicMaster {
    public static ArrayList showAll()
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        NaicBean nb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from sm_naic_master";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                nb=new NaicBean();
                nb.setNaicsKey(rs.getString(1));
                nb.setNaicsCode(rs.getString(2));
                nb.setNaicsDescription(rs.getString(3));
                al.add(nb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAll in NaicMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAll in NaicMaster : "+ex.getMessage());
            }
        }
        return al;
    }
}

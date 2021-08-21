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
import supply.medium.home.bean.StateBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class StateMaster {
    public static ArrayList showAll()
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        StateBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from sm_state_master";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new StateBean();
                ub.setStateKey(rs.getString(1));
                ub.setCountryKey(rs.getString(2));
                ub.setStateName(rs.getString(3));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAll in StateMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAll in StateMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList showAllStatesByCountryKey(String countryKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        StateBean ub=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from sm_state_master "
                    + "where country_key='"+countryKey+"'";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                ub=new StateBean();
                ub.setStateKey(rs.getString(1));
                ub.setCountryKey(rs.getString(2));
                ub.setStateName(rs.getString(3));
                al.add(ub);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAllStatesByCountryKey in StateMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAllStatesByCountryKey in StateMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    public static String showStateFromKey(String statekey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        String stateName=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select state_name "
                    + "from sm_state_master "
                    + "where state_key='"+statekey+"'";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                stateName=rs.getString(1);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showStateFromKey in StateMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showStateFromKey in StateMaster : "+ex.getMessage());
            }
        }
        if(stateName==null)
            stateName=statekey;
        return stateName;
    }
}

/*
 * 
 * 
 * 
 */

package supply.medium.home.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class InviteMaster {
    public static int insert(String userkey,String email)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "invite_master(user_key,email,invited_on) "
                    + "values('"+userkey+"','"+email+"',sysdate()) ";
            result=showInviteByUser(userkey, email);
            if(result==0)
                result=st.executeUpdate(query);
            else
                result=0;
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in InviteMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in InviteMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static int showInviteByUser(String userKey,String email)
    {
        int result=0;
        Connection con=null;
        ResultSet rs=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from invite_master "
                    + "where email='"+email+"' "
                    + "and user_key='"+userKey+"'";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                result++;
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showInviteByUser in InviteMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showInviteByUser in InviteMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
}

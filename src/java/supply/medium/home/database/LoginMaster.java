/*
 * 
 * 
 * 
 */

package supply.medium.home.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author !!ULTIMATE
 */
public class LoginMaster {
    
    public static int checkLoginCredential(String email,String password,HttpSession session)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select user_key,company_key,first_name,last_name,email,user_type "
                    + "from user_master "
                    + "where email='"+email+"' "
                    + "and pass_word='"+password+"' "
                    + "and account_status='Activated';";
            rs=st.executeQuery(query);
            if(rs.next())
            {
                session.setAttribute("userKey", rs.getString(1));
                session.setAttribute("companyKey", rs.getString(2));
                session.setAttribute("userName", rs.getString(3));
                session.setAttribute("email", rs.getString(5));
                String companyName=CompanyMaster.getCompanyNameFromKey(session.getAttribute("companyKey").toString());
                session.setAttribute("companyName", companyName);
                session.setAttribute("userType", rs.getString(6));
                result=1;
            }
                
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at checkLoginCredential in LoginMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing checkLoginCredential in LoginMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
}

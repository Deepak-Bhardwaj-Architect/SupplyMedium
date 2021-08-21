/*
 * 
 * 
 * 
 */

package supply.medium.home.database;

import java.sql.Connection;
import java.sql.Statement;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class CompanyAdvertisementMaster {
    
    public static int insert(String companyKey,String userKey,
            String hoverText,String linkPage,String imagePath)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "company_advertisement_master"
                    + "(company_key,user_key,hover_text,link_page,image_path) "
                    + "values('"+companyKey+"','"+userKey+"','"+hoverText+"',"
                    + "'"+linkPage+"','"+imagePath+"') ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in CompanyAdvertisementMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in CompanyAdvertisementMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
}

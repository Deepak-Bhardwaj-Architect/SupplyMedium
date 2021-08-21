/*
 * 
 * 
 * 
 */
package supply.medium.utility;

import java.sql.*;

/**
 *
 * @author LenovoB560
 */
public class ErrorMaster {

    public static void insert(String error) {
//        clearConsole();
        /*
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "error_master(error) "
                    + "values('"+error+"') ";
            st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in ErrorMaster : "+ex.getMessage());
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
         */
        System.out.println(error);
    }

//    public static void clearConsole() {
//        try {
//            final String os = System.getProperty("os.name");
//
//            if (os.contains("Windows")) {
//                Runtime.getRuntime().exec("cls");
//            } else {
//                Runtime.getRuntime().exec("clear");
//            }
//        } catch (Exception ex) {
//            insert("Exception at clearConsole in ErrorMaster : "+ex.getMessage());
//        }
//    }

}

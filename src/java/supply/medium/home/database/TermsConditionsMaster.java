/*
 * 
 * 
 * 
 */

package supply.medium.home.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import supply.medium.home.bean.TermsConditionsBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class TermsConditionsMaster {
    
    public static int insert(String companyKey,String transactionType,
            String textMessage) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "terms_conditions_master(company_key,transaction_type,text_message,created_on) "
                    + "values('" + companyKey + "','" + transactionType + "','" + textMessage + "',sysdate()) ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in TermsConditionsMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in TermsConditionsMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static TermsConditionsBean show(String companyKey,String transactionType) 
    {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        TermsConditionsBean tcb = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT tc_key,text_message,created_on from terms_conditions_master "
                    + "where company_key='" + companyKey + "' and transaction_type='" + transactionType + "'";
            //ErrorMaster.insert(query);
            rs = st.executeQuery(query);
            if (rs.next()) {
                tcb = new TermsConditionsBean();
                tcb.setTcKey(rs.getString(1));
                tcb.setTextMessage(rs.getString(2));
                tcb.setCreatedOn(rs.getString(3));
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at show in TermsConditionsMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing show in TermsConditionsMaster : " + ex.getMessage());
            }
        }
        return tcb;
    }
    
    public static int update(String companyKey,String transactionType,String textMessage) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update terms_conditions_master set text_message='" + textMessage + "',created_on=sysdate() "
                    + "where company_key='" + companyKey + "' and transaction_type='" + transactionType + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at update in TermsConditionsMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing update in TermsConditionsMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
}

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
import supply.medium.home.bean.TransactionRfqBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class TransactionRfqMaster {
    
    public static String insertRfq(String transRfqKey,String rfqNo,String companyKeyFrom,
String companyKeyTo,String userKeyFrom,String userKeyTo,String transStatus,
String transAction,String quoteRef,String isOutside,String isOutsideAddress,String recurring,
String isQuoteCreated) 
    {
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into trans_rfq_master "
                    + "(trans_rfq_key,rfq_no,company_key_from,company_key_to,"
                    + "user_key_from,user_key_to,trans_status,trans_action,"
                    + "quote_ref,is_outside,is_outside_address,recurring,"
                    + "is_quote_created,created_on,action_on)"
                    + "values('"+transRfqKey+"','"+rfqNo+"','"+companyKeyFrom+"',"
            + "'"+companyKeyTo+"','"+userKeyFrom+"','"+userKeyTo+"','"+transStatus+"',"
            + "'"+transAction+"','"+quoteRef+"','"+isOutside+"','"+isOutsideAddress+"',"
                    + "'"+recurring+"','"+isQuoteCreated+"',sysdate(),sysdate()) ";
            st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insertRfq in TransRfqMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insertRfq in TransRfqMaster : " + ex.getMessage());
            }
        }
        return transRfqKey;
    } 
    
    public static ArrayList showByTypeCompanyKey(String transactionType,String compnayKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionRfqBean trb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="SELECT trans_rfq_key,rfq_no,company_key_from, "
                    + "company_key_to,trans_status,trans_action,quote_ref, "
                    + "is_outside,is_outside_address,recurring,is_quote_created, "
                    + "t.created_on,action_on,company_type,company_name, "
                    + "branch,country,address, "
                    + "city,state,phone,email,zipcode "
                    + "FROM trans_rfq_master t, "
                    + "company_master c, "
                    + "company_mailing_address_master m, "
                    + "user_master u "
                    + "where t.company_key_from=c.company_key "
                    + "and t.company_key_from=u.company_key "
                    + "and user_type='Admin' "
                    + "and t.company_key_from=m.company_key "
                    + "and m.mailing_key in( "
                        + "select min(mailing_key) "
                        + "from company_mailing_address_master "
                        + "group by company_key "
                    + ") "
                    + "and (company_key_from='"+compnayKey+"' "
                    + "or company_key_to='"+compnayKey+"') "
                    + "order by trans_rfq_key desc";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                trb=new TransactionRfqBean();
                trb.setTransactionKey(rs.getString(1));
                trb.setTransactionNo(rs.getString(2));
                trb.setBuyerCompanyKey(rs.getString(3));
                trb.setSupplierCompanyKey(rs.getString(4));
                trb.setTransactionStatus(rs.getString(5));
                trb.setTransactionAction(rs.getString(6));
                trb.setQuoteRef(rs.getString(7));
                trb.setIsOutersideSupplier(rs.getString(8));
                trb.setOutsiderAddress(rs.getString(9));
                trb.setRecurring(rs.getString(10));
                trb.setIsQuoteCreated(rs.getString(11));
                trb.setCreatedOn(rs.getString(12));
                trb.setActionOn(rs.getString(13));
                trb.setBuyerCompanyType(rs.getString(14));
                trb.setBuyerCompanyName(rs.getString(15));
                trb.setBuyerBranch(rs.getString(16));
                trb.setBuyerCountry(rs.getString(17));
                trb.setBuyerAddress(rs.getString(18));
                trb.setBuyerCity(rs.getString(19));
                trb.setBuyerState(StateMaster.showStateFromKey(rs.getString(20)));
                trb.setBuyerPhone(rs.getString(21));
                trb.setBuyerEmail(rs.getString(22));
                trb.setBuyerZipcode(rs.getString(23));
                al.add(trb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByTypeCompanyKey in TransactionRfqMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByTypeCompanyKey in TransactionRfqMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static TransactionRfqBean showByKey(String transactionKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionRfqBean trb=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="SELECT trans_rfq_key,rfq_no,company_key_from, "
                    + "company_key_to,trans_status,trans_action,quote_ref, "
                    + "is_outside,is_outside_address,recurring,is_quote_created, "
                    + "t.created_on,action_on,company_type,company_name, "
                    + "branch,country,address, "
                    + "city,state,phone,email,zipcode "
                    + "FROM trans_rfq_master t, "
                    + "company_master c, "
                    + "company_mailing_address_master m, "
                    + "user_master u "
                    + "where t.company_key_from=c.company_key "
                    + "and t.company_key_from=u.company_key "
                    + "and user_type='Admin' "
                    + "and t.company_key_from=m.company_key "
                    + "and m.mailing_key in( "
                        + "select min(mailing_key) "
                        + "from company_mailing_address_master "
                        + "group by company_key "
                    + ")  "
                    + "and trans_rfq_key='"+transactionKey+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                trb=new TransactionRfqBean();
                trb.setTransactionKey(rs.getString(1));
                trb.setTransactionNo(rs.getString(2));
                trb.setBuyerCompanyKey(rs.getString(3));
                trb.setSupplierCompanyKey(rs.getString(4));
                trb.setTransactionStatus(rs.getString(5));
                trb.setTransactionAction(rs.getString(6));
                trb.setQuoteRef(rs.getString(7));
                trb.setIsOutersideSupplier(rs.getString(8));
                trb.setOutsiderAddress(rs.getString(9));
                trb.setRecurring(rs.getString(10));
                trb.setIsQuoteCreated(rs.getString(11));
                trb.setCreatedOn(rs.getString(12));
                trb.setActionOn(rs.getString(13));
                trb.setBuyerCompanyType(rs.getString(14));
                trb.setBuyerCompanyName(rs.getString(15));
                trb.setBuyerBranch(rs.getString(16));
                trb.setBuyerCountry(rs.getString(17));
                trb.setBuyerAddress(rs.getString(18));
                trb.setBuyerCity(rs.getString(19));
                trb.setBuyerState(rs.getString(20));
                trb.setBuyerPhone(rs.getString(21));
                trb.setBuyerEmail(rs.getString(22));
                trb.setBuyerZipcode(rs.getString(23));
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByKey in TransactionRfqMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByKey in TransactionRfqMaster : "+ex.getMessage());
            }
        }
        return trb;
    }
    
    public static TransactionRfqBean showByTransactionKeyFromToCompany(String transactionKey,String companyKey,String who)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionRfqBean trb=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            if(who.equals("to"))
            {
            query="SELECT trans_rfq_key,rfq_no,company_key_from, "
                    + "company_key_to,trans_status,trans_action,quote_ref, "
                    + "is_outside,is_outside_address,recurring,is_quote_created, "
                    + "t.created_on,action_on,company_type,company_name, "
                    + "branch,country,address, "
                    + "city,state,phone,email,zipcode "
                    + "FROM trans_rfq_master t, "
                    + "company_master c, "
                    + "company_mailing_address_master m, "
                    + "user_master u "
                    + "where t.company_key_to=c.company_key "
                    + "and t.company_key_to=u.company_key "
                    + "and user_type='Admin' "
                    + "and t.company_key_to=m.company_key "
                    + "and m.mailing_key in( "
                        + "select min(mailing_key) "
                        + "from company_mailing_address_master "
                        + "group by company_key "
                    + ")  "
                    + "and trans_rfq_key='"+transactionKey+"' "
                    + "and company_key_to='"+companyKey+"'";
            }
            else if(who.equals("from"))
            {
                query="SELECT trans_rfq_key,rfq_no,company_key_from, "
                    + "company_key_to,trans_status,trans_action,quote_ref, "
                    + "is_outside,is_outside_address,recurring,is_quote_created, "
                    + "t.created_on,action_on,company_type,company_name, "
                    + "branch,country,address, "
                    + "city,state,phone,email,zipcode "
                    + "FROM trans_rfq_master t, "
                    + "company_master c, "
                    + "company_mailing_address_master m, "
                    + "user_master u "
                    + "where t.company_key_from=c.company_key "
                    + "and t.company_key_from=u.company_key "
                    + "and user_type='Admin' "
                    + "and t.company_key_from=m.company_key "
                    + "and m.mailing_key in( "
                        + "select min(mailing_key) "
                        + "from company_mailing_address_master "
                        + "group by company_key "
                    + ")  "
                    + "and trans_rfq_key='"+transactionKey+"' "
                    + "and company_key_from='"+companyKey+"'";
            }
            rs=st.executeQuery(query);
            while(rs.next())
            {
                trb=new TransactionRfqBean();
                trb.setTransactionKey(rs.getString(1));
                trb.setTransactionNo(rs.getString(2));
                trb.setBuyerCompanyKey(rs.getString(3));
                trb.setSupplierCompanyKey(rs.getString(4));
                trb.setTransactionStatus(rs.getString(5));
                trb.setTransactionAction(rs.getString(6));
                trb.setQuoteRef(rs.getString(7));
                trb.setIsOutersideSupplier(rs.getString(8));
                trb.setOutsiderAddress(rs.getString(9));
                trb.setRecurring(rs.getString(10));
                trb.setIsQuoteCreated(rs.getString(11));
                trb.setCreatedOn(rs.getString(12));
                trb.setActionOn(rs.getString(13));
                trb.setBuyerCompanyType(rs.getString(14));
                trb.setBuyerCompanyName(rs.getString(15));
                trb.setBuyerBranch(rs.getString(16));
                trb.setBuyerCountry(rs.getString(17));
                trb.setBuyerAddress(rs.getString(18));
                trb.setBuyerCity(rs.getString(19));
                trb.setBuyerState(rs.getString(20));
                trb.setBuyerPhone(rs.getString(21));
                trb.setBuyerEmail(rs.getString(22));
                trb.setBuyerZipcode(rs.getString(23));
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByTransactionKeyFromToCompany in TransactionRfqMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByTransactionKeyFromToCompany in TransactionRfqMaster : "+ex.getMessage());
            }
        }
        return trb;
    }
    
    public static int updateTransStatus(String transKey,String status) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update trans_rfq_master "
                    + "set trans_status='"+status+"',"
                    + "action_on=sysdate() "
                    + "where trans_rfq_key='"+transKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateTransStatus in TransRfqMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at updateTransStatus in TransRfqMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static int updateQteGenerated(String transKey,String status)
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update trans_rfq_master "
                    + "set is_quote_created='"+status+"'"
                    + "where trans_rfq_key='"+transKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateQteGenerated in TransRfqMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at updateQteGenerated in TransRfqMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
}

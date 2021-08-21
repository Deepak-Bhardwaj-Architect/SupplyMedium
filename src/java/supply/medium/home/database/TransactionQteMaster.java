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
import supply.medium.home.bean.TransactionQteBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Lenovo
 */
public class TransactionQteMaster {
    
    public static String insertQte(String trans_key,String company_key_from,String company_key_to,
String user_key_from,String user_key_to,String transaction_type,String q_trans_rqf_key,
String q_quote_no,String q_trans_status,String q_trans_action,String q_quote_ref,String q_is_outside,
String q_is_outside_address,String q_recurring,String q_total_amount,String q_tax_percentage,
String q_total_billing_amount,String q_is_po_created) 
    {
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into transaction_master "
                    + "(trans_key,company_key_from,company_key_to,user_key_from,"
                    + "user_key_to,transaction_type,q_trans_rqf_key,q_quote_no,"
                    + "q_trans_status,q_trans_action,q_quote_ref,q_is_outside,"
                    + "q_is_outside_address,q_recurring,q_total_amount,q_tax_percentage,"
                    + "q_total_billing_amount,q_is_po_created,q_created_on,q_action_on)"
                    + "values('"+trans_key+"','"+company_key_from+"','"+company_key_to+"',"
                    + "'"+user_key_from+"','"+user_key_to+"','"+transaction_type+"','"+q_trans_rqf_key+"',"
                    + "'"+q_quote_no+"','"+q_trans_status+"','"+q_trans_action+"','"+q_quote_ref+"',"
                    + "'"+q_is_outside+"','"+q_is_outside_address+"','"+q_recurring+"','"+q_total_amount+"',"
                    + "'"+q_tax_percentage+"','"+q_total_billing_amount+"','"+q_is_po_created+"',"
                    + "sysdate(),sysdate()) ";
            st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insertQte in TransactionQuoteMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insertQte in TransactionQuoteMaster : " + ex.getMessage());
            }
        }
        return trans_key;
    } 
    
    public static ArrayList showByTypeCompanyKey(String transactionType,String compnayKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionQteBean trb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="SELECT trans_key,q_trans_rqf_key,company_key_from, "
                            + "company_key_to,q_trans_status,q_trans_action,q_quote_ref, "
                            + "q_is_outside,q_is_outside_address,q_recurring,q_is_po_created,"
                            + " t.q_created_on,q_action_on,company_type,company_name,"
                            + "branch,country,address, "
                            + "city,state,phone,email,zipcode,"
                            + "q_total_amount,q_tax_percentage,q_total_billing_amount "
                            + "FROM transaction_master t, "
                            + "company_master c,"
                            + "company_mailing_address_master m, "
                            + "user_master u "
                            + "where t.company_key_from=c.company_key "
                            + "and t.company_key_from=u.company_key "
                            + "and user_type='Admin' "
                            + "and transaction_type='"+transactionType+"' "
                            + "and t.company_key_from=m.company_key "
                            + "and m.mailing_key in( "
                                + "select min(mailing_key) "
                                + "from company_mailing_address_master "
                                + "group by company_key "
                            + ") "
                            + "and (company_key_from='"+compnayKey+"' "
                            + "or company_key_to='"+compnayKey+"') "
                            + "order by trans_key desc";
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                trb=new TransactionQteBean();
                trb.setTrans_key(rs.getString(1));
                trb.setQ_trans_rqf_key("QTE"+trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setQ_trans_status(rs.getString(5));
                trb.setQ_trans_action(rs.getString(6));
                trb.setQ_quote_ref(rs.getString(7));
                trb.setQ_is_outside(rs.getString(8));
                trb.setQ_is_outside_address(rs.getString(9));
                trb.setQ_recurring(rs.getString(10));
                trb.setQ_is_po_created(rs.getString(11));
                trb.setQ_created_on(rs.getString(12));
                trb.setQ_action_on(rs.getString(13));
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
                trb.setQ_total_amount(rs.getString(24));
                trb.setQ_tax_percentage(rs.getString(25));
                trb.setQ_total_billing_amount(rs.getString(26));
                al.add(trb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByTypeCompanyKey in TransactionQuoteMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByTypeCompanyKey in TransactionQuoteMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static TransactionQteBean showByKey(String transactionKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionQteBean trb=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="SELECT trans_key,q_trans_rqf_key,company_key_from, "
                            + "company_key_to,q_trans_status,q_trans_action,q_quote_ref, "
                            + "q_is_outside,q_is_outside_address,q_recurring,q_is_po_created,"
                            + " t.q_created_on,q_action_on,company_type,company_name,"
                            + "branch,country,address, "
                            + "city,state,phone,email,zipcode,"
                            + "q_total_amount,q_tax_percentage,q_total_billing_amount "
                            + "FROM transaction_master t, "
                            + "company_master c,"
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
                            + "and trans_key='"+transactionKey+"'";
            rs=st.executeQuery(query);
            while(rs.next())
            {
                trb=new TransactionQteBean();
                trb.setTrans_key(rs.getString(1));
                trb.setQ_trans_rqf_key("QTE"+trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setQ_trans_status(rs.getString(5));
                trb.setQ_trans_action(rs.getString(6));
                trb.setQ_quote_ref(rs.getString(7));
                trb.setQ_is_outside(rs.getString(8));
                trb.setQ_is_outside_address(rs.getString(9));
                trb.setQ_recurring(rs.getString(10));
                trb.setQ_is_po_created(rs.getString(11));
                trb.setQ_created_on(rs.getString(12));
                trb.setQ_action_on(rs.getString(13));
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
                trb.setQ_total_amount(rs.getString(24));
                trb.setQ_tax_percentage(rs.getString(25));
                trb.setQ_total_billing_amount(rs.getString(26));
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByKey in TransactionQuoteMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByKey in TransactionQuoteMaster : "+ex.getMessage());
            }
        }
        return trb;
    }
    
    public static TransactionQteBean showByTransactionKeyFromToCompany(String transactionKey,String companyKey,String who)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionQteBean trb=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            if(who.equals("to"))
            {
            query="SELECT trans_key,q_trans_rqf_key,company_key_from, "
                    + "company_key_to,q_trans_status,q_trans_action,q_quote_ref, "
                    + "q_is_outside,q_is_outside_address,q_recurring,q_is_po_created,"
                    + " t.q_created_on,q_action_on,company_type,company_name,"
                    + "branch,country,address, "
                    + "city,state,phone,email,zipcode "
                    + "FROM transaction_master t, "
                    + "company_master c,"
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
                    + ") "
                    + "and trans_key='"+transactionKey+"' "
                    + "and company_key_to='"+companyKey+"'";
            }
            else if(who.equals("from"))
            {
                query="SELECT trans_key,q_trans_rqf_key,company_key_from, "
                    + "company_key_to,q_trans_status,q_trans_action,q_quote_ref, "
                    + "q_is_outside,q_is_outside_address,q_recurring,q_is_po_created,"
                    + " t.q_created_on,q_action_on,company_type,company_name,"
                    + "branch,country,address, "
                    + "city,state,phone,email,zipcode "
                    + "FROM transaction_master t, "
                    + "company_master c,"
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
                    + "and trans_key='"+transactionKey+"' "
                    + "and company_key_from='"+companyKey+"'";
            }
            System.out.println(query);
            rs=st.executeQuery(query);
            while(rs.next())
            {
                trb=new TransactionQteBean();
                trb.setTrans_key(rs.getString(1));
                trb.setQ_trans_rqf_key("QTE"+trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setQ_trans_status(rs.getString(5));
                trb.setQ_trans_action(rs.getString(6));
                trb.setQ_quote_ref(rs.getString(7));
                trb.setQ_is_outside(rs.getString(8));
                trb.setQ_is_outside_address(rs.getString(9));
                trb.setQ_recurring(rs.getString(10));
                trb.setQ_is_po_created(rs.getString(11));
                trb.setQ_created_on(rs.getString(12));
                trb.setQ_action_on(rs.getString(13));
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
            ErrorMaster.insert("Exception at showByTransactionKeyFromToCompany in TransactionQuoteMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByTransactionKeyFromToCompany in TransactionQuoteMaster : "+ex.getMessage());
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
            String query = "update transaction_master "
                    + "set q_trans_status='"+status+"',"
                    + "q_action_on=sysdate() "
                    + "where trans_key='"+transKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateTransStatus in TransactionQuoteMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at updateTransStatus in TransactionQuoteMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
}

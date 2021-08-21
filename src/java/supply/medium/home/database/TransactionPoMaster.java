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
import supply.medium.home.bean.TransactionPoBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Lenovo
 */
public class TransactionPoMaster {
    
    public static String insertPo(String trans_key,String company_key_from,String company_key_to,
String user_key_from,String user_key_to,String transaction_type,String po_trans_rqf_key,
String po_quote_no,String po_trans_status,String po_trans_action,String po_quote_ref,String po_is_outside,
String po_is_outside_address,String po_recurring,String po_total_amount,String po_tax_percentage,
String po_total_billing_amount,String po_is_inv_created) 
    {
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into transaction_master "
                    + "(trans_key,company_key_from,company_key_to,user_key_from,"
                    + "user_key_to,transaction_type,po_trans_rqf_key,po_no,"
                    + "po_trans_status,po_trans_action,po_quote_ref,po_is_outside,"
                    + "po_is_outside_address,po_recurring,po_total_amount,po_tax_percentage,"
                    + "po_total_billing_amount,po_is_inv_created,po_created_on,po_action_on)"
                    + "values('"+trans_key+"','"+company_key_from+"','"+company_key_to+"',"
                    + "'"+user_key_from+"','"+user_key_to+"','"+transaction_type+"','"+po_trans_rqf_key+"',"
                    + "'"+po_quote_no+"','"+po_trans_status+"','"+po_trans_action+"','"+po_quote_ref+"',"
                    + "'"+po_is_outside+"','"+po_is_outside_address+"','"+po_recurring+"','"+po_total_amount+"',"
                    + "'"+po_tax_percentage+"','"+po_total_billing_amount+"','"+po_is_inv_created+"',"
                    + "sysdate(),sysdate()) ";
            st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insertPo in TransactionPoMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insertPo in TransactionPoMaster : " + ex.getMessage());
            }
        }
        return trans_key;
    } 
    
    public static ArrayList showByTypeCompanyKey(String transactionType,String compnayKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionPoBean trb=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="SELECT trans_key,po_trans_rqf_key,company_key_from, "
                            + "company_key_to,po_trans_status,po_trans_action,po_quote_ref, "
                            + "po_is_outside,po_is_outside_address,po_recurring,po_is_inv_created,"
                            + " t.po_created_on,po_action_on,company_type,company_name,"
                            + "branch,country,address, "
                            + "city,state,phone,email,zipcode,"
                            + "po_total_amount,po_tax_percentage,po_total_billing_amount "
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
                trb=new TransactionPoBean();
                trb.setTrans_key(rs.getString(1));
                trb.setPo_trans_rqf_key("PO"+trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setPo_trans_status(rs.getString(5));
                trb.setPo_trans_action(rs.getString(6));
                trb.setPo_quote_ref(rs.getString(7));
                trb.setPo_is_outside(rs.getString(8));
                trb.setPo_is_outside_address(rs.getString(9));
                trb.setPo_recurring(rs.getString(10));
                trb.setPo_is_inv_created(rs.getString(11));
                trb.setPo_created_on(rs.getString(12));
                trb.setPo_action_on(rs.getString(13));
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
                trb.setPo_total_amount(rs.getString(24));
                trb.setPo_tax_percentage(rs.getString(25));
                trb.setPo_total_billing_amount(rs.getString(26));
                al.add(trb);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByTypeCompanyKey in TransactionPoMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByTypeCompanyKey in TransactionPoMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
    public static TransactionPoBean showByKey(String transactionKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionPoBean trb=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="SELECT trans_key,po_trans_rqf_key,company_key_from, "
                            + "company_key_to,po_trans_status,po_trans_action,po_quote_ref, "
                            + "po_is_outside,po_is_outside_address,po_recurring,po_is_inv_created,"
                            + " t.po_created_on,po_action_on,company_type,company_name,"
                            + "branch,country,address, "
                            + "city,state,phone,email,zipcode,"
                            + "po_total_amount,po_tax_percentage,po_total_billing_amount "
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
                trb=new TransactionPoBean();
                trb.setTrans_key(rs.getString(1));
                trb.setPo_trans_rqf_key("PO"+trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setPo_trans_status(rs.getString(5));
                trb.setPo_trans_action(rs.getString(6));
                trb.setPo_quote_ref(rs.getString(7));
                trb.setPo_is_outside(rs.getString(8));
                trb.setPo_is_outside_address(rs.getString(9));
                trb.setPo_recurring(rs.getString(10));
                trb.setPo_is_inv_created(rs.getString(11));
                trb.setPo_created_on(rs.getString(12));
                trb.setPo_action_on(rs.getString(13));
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
                trb.setPo_total_amount(rs.getString(24));
                trb.setPo_tax_percentage(rs.getString(25));
                trb.setPo_total_billing_amount(rs.getString(26));
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showByKey in TransactionPoMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByKey in TransactionPoMaster : "+ex.getMessage());
            }
        }
        return trb;
    }
    
    public static TransactionPoBean showByTransactionKeyFromToCompany(String transactionKey,String companyKey,String who)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionPoBean trb=null;
        String query="";
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            if(who.equals("to"))
            {
            query="SELECT trans_key,po_trans_rqf_key,company_key_from, "
                    + "company_key_to,po_trans_status,po_trans_action,po_quote_ref, "
                    + "po_is_outside,po_is_outside_address,po_recurring,po_is_inv_created,"
                    + " t.po_created_on,po_action_on,company_type,company_name,"
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
                query="SELECT trans_key,po_trans_rqf_key,company_key_from, "
                    + "company_key_to,po_trans_status,po_trans_action,po_quote_ref, "
                    + "po_is_outside,po_is_outside_address,po_recurring,po_is_inv_created,"
                    + " t.po_created_on,po_action_on,company_type,company_name,"
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
            rs=st.executeQuery(query);
            while(rs.next())
            {
                trb=new TransactionPoBean();
                trb.setTrans_key(rs.getString(1));
                trb.setPo_trans_rqf_key("PO"+trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setPo_trans_status(rs.getString(5));
                trb.setPo_trans_action(rs.getString(6));
                trb.setPo_quote_ref(rs.getString(7));
                trb.setPo_is_outside(rs.getString(8));
                trb.setPo_is_outside_address(rs.getString(9));
                trb.setPo_recurring(rs.getString(10));
                trb.setPo_is_inv_created(rs.getString(11));
                trb.setPo_created_on(rs.getString(12));
                trb.setPo_action_on(rs.getString(13));
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
            ErrorMaster.insert("Exception at showByTransactionKeyFromToCompany in TransactionPoMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showByTransactionKeyFromToCompany in TransactionPoMaster : "+ex.getMessage());
            }
        }
        return trb;
    }

    public static int updateInvGenerated(String transKey,String status)
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update transaction_master "
                    + "set po_is_inv_created='"+status+"'"
                    + "where trans_key='"+transKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateInvGenerated in TransactionPoMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at updateInvGenerated in TransactionPoMaster : " + ex.getMessage());
            }
        }
        return result;
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
                    + "set po_trans_status='"+status+"',"
                    + "q_action_on=sysdate() "
                    + "where trans_key='"+transKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateTransStatus in TransactionPoMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at updateTransStatus in TransactionPoMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
}

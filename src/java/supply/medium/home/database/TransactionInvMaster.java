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
import supply.medium.home.bean.CompanyDetailAddressBean;
import supply.medium.home.bean.TransactionInvBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Lenovo
 */
public class TransactionInvMaster {

    public static String insertInv(String trans_key, String company_key_from, String company_key_to,
            String user_key_from, String user_key_to, String transaction_type, String inv_trans_rqf_key,
            String inv_no, String inv_trans_status, String inv_trans_action, String inv_quote_ref, String inv_is_outside,
            String inv_is_outside_address, String inv_recurring, String inv_total_amount, String inv_tax_percentage,
            String inv_total_billing_amount, String inv_is_po_created, String freight_handling, String discount,
            String invoice_billing_period, String invoice_due_date, String invoice_no, String freight_carrier,
            String bill_of_landing, String freight_weight, String freight_weight_unit, String shipped_date,
            String is_nonpo_invoice, String po_num, String is_diff_address, String diff_address) {
        Connection con = null;
        Statement st = null;
        freight_weight_unit = QuantityTypeMaster.showCodeByKey(freight_weight_unit);
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into transaction_master "
                    + "(trans_key,company_key_from,company_key_to,user_key_from,"
                    + "user_key_to,transaction_type,inv_trans_rqf_key,inv_quote_no,"
                    + "inv_trans_status,inv_trans_action,inv_quote_ref,inv_is_outside,"
                    + "inv_is_outside_address,inv_recurring,inv_total_amount,inv_tax_percentage,"
                    + "inv_total_billing_amount,inv_is_po_created,inv_created_on,inv_action_on,"
                    + "freight_handling,discount,invoice_billing_period,invoice_due_date,"
                    + "invoice_no,freight_carrier,bill_of_landing,freight_weight,freight_weight_unit,"
                    + "shipped_date,is_nonpo_invoice,po_num,is_diff_address,diff_address,is_inv_paid,inv_paid_on_dated)"
                    + "values('" + trans_key + "','" + company_key_from + "','" + company_key_to + "',"
                    + "'" + user_key_from + "','" + user_key_to + "','" + transaction_type + "','" + inv_trans_rqf_key + "',"
                    + "'" + inv_no + "','" + inv_trans_status + "','" + inv_trans_action + "','" + inv_quote_ref + "',"
                    + "'" + inv_is_outside + "','" + inv_is_outside_address + "','" + inv_recurring + "','" + inv_total_amount + "',"
                    + "'" + inv_tax_percentage + "','" + inv_total_billing_amount + "','" + inv_is_po_created + "',"
                    + "sysdate(),sysdate(),'" + freight_handling + "','" + discount + "',"
                    + "'" + invoice_billing_period + "','" + invoice_due_date + "',"
                    + "'" + invoice_no + "','" + freight_carrier + "',"
                    + "'" + bill_of_landing + "','" + freight_weight + "',"
                    + "'" + freight_weight_unit + "','" + shipped_date + "',"
                    + "'" + is_nonpo_invoice + "','" + po_num + "',"
                    + "'" + is_diff_address + "','" + diff_address + "','no',sysdate())";
            st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insertInv in TransactionInvMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insertInv in TransactionInvMaster : " + ex.getMessage());
            }
        }
        return trans_key;
    }

    public static ArrayList showByTypeCompanyKey(String transactionType, String compnayKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        TransactionInvBean trb = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT trans_key,inv_trans_rqf_key,company_key_from, "
                    + "company_key_to,inv_trans_status,inv_trans_action,inv_quote_ref, "
                    + "inv_is_outside,inv_is_outside_address,inv_recurring,inv_is_po_created,"
                    + " t.inv_created_on,inv_action_on,company_type,company_name,"
                    + "branch,country,address, "
                    + "city,state,phone,email,zipcode,"
                    + "inv_total_amount,inv_tax_percentage,inv_total_billing_amount,is_inv_paid,inv_paid_on_dated "
                    + "FROM transaction_master t, "
                    + "company_master c,"
                    + "company_mailing_address_master m, "
                    + "user_master u "
                    + "where t.company_key_from=c.company_key "
                    + "and t.company_key_from=u.company_key "
                    + "and user_type='Admin' "
                    + "and transaction_type='" + transactionType + "' "
                    + "and t.company_key_from=m.company_key "
                    + "and m.mailing_key in( "
                    + "select min(mailing_key) "
                    + "from company_mailing_address_master "
                    + "group by company_key "
                    + ") "
                    + "and (company_key_from='" + compnayKey + "' "
                    + "or company_key_to='" + compnayKey + "') "
                    + "order by trans_key desc";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                trb = new TransactionInvBean();
                trb.setTrans_key(rs.getString(1));
                trb.setInv_trans_rqf_key("INV" + trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setInv_trans_status(rs.getString(5));
                trb.setInv_trans_action(rs.getString(6));
                trb.setInv_quote_ref(rs.getString(7));
                trb.setInv_is_outside(rs.getString(8));
                trb.setInv_is_outside_address(rs.getString(9));
                trb.setInv_recurring(rs.getString(10));
                trb.setInv_is_po_created(rs.getString(11));
                trb.setInv_created_on(rs.getString(12));
                trb.setInv_action_on(rs.getString(13));
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
                trb.setInv_total_amount(rs.getString(24));
                trb.setInv_tax_percentage(rs.getString(25));
                trb.setInv_total_billing_amount(rs.getString(26));
                trb.setIs_inv_paid(rs.getString(27));
                trb.setInv_paid_on_dated(rs.getString(28));
                al.add(trb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByTypeCompanyKey in TransactionInvMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByTypeCompanyKey in TransactionInvMaster : " + ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList showByTypeCompanyKeyOther(String transactionType, String compnayKey,String other,String status) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        TransactionInvBean trb = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT trans_key,inv_trans_rqf_key,company_key_from, "
                    + "company_key_to,inv_trans_status,inv_trans_action,inv_quote_ref, "
                    + "inv_is_outside,inv_is_outside_address,inv_recurring,inv_is_po_created,"
                    + " t.inv_created_on,inv_action_on,company_type,company_name,"
                    + "branch,country,address, "
                    + "city,state,phone,email,zipcode,"
                    + "inv_total_amount,inv_tax_percentage,inv_total_billing_amount,is_inv_paid,inv_paid_on_dated "
                    + "FROM transaction_master t, "
                    + "company_master c,"
                    + "company_mailing_address_master m, "
                    + "user_master u "
                    + "where t.company_key_from=c.company_key "
                    + "and t.company_key_from=u.company_key "
                    + "and user_type='Admin' "
                    + "and transaction_type='" + transactionType + "' "
                    + "and t.company_key_from=m.company_key "
                    + "and m.mailing_key in( "
                    + "select min(mailing_key) "
                    + "from company_mailing_address_master "
                    + "group by company_key "
                    + ") "
                    + "and ((company_key_from='" + compnayKey + "' "
                    + "and company_key_to='" + other + "') "
                    + "or (company_key_to='" + compnayKey + "' "
                    + "and company_key_from='" + other + "')) "
                    + "and is_inv_paid like '%"+status+"%' "
                    + "order by trans_key desc";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                trb = new TransactionInvBean();
                trb.setTrans_key(rs.getString(1));
                trb.setInv_trans_rqf_key("INV" + trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setInv_trans_status(rs.getString(5));
                trb.setInv_trans_action(rs.getString(6));
                trb.setInv_quote_ref(rs.getString(7));
                trb.setInv_is_outside(rs.getString(8));
                trb.setInv_is_outside_address(rs.getString(9));
                trb.setInv_recurring(rs.getString(10));
                trb.setInv_is_po_created(rs.getString(11));
                trb.setInv_created_on(rs.getString(12));
                trb.setInv_action_on(rs.getString(13));
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
                trb.setInv_total_amount(rs.getString(24));
                trb.setInv_tax_percentage(rs.getString(25));
                trb.setInv_total_billing_amount(rs.getString(26));
                trb.setIs_inv_paid(rs.getString(27));
                trb.setInv_paid_on_dated(rs.getString(28));
                al.add(trb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByTypeCompanyKeyOther in TransactionInvMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByTypeCompanyKeyOther in TransactionInvMaster : " + ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList showInvoicedCompanies(String companyKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompanyDetailAddressBean ub = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select company_master.company_name,"
                    + "company_master.company_id,"
                    + "company_mailing_address_master.branch,"
                    + "company_mailing_address_master.country,"
                    + "company_mailing_address_master.address,"
                    + "company_mailing_address_master.city,"
                    + "company_mailing_address_master.state,"
                    + "company_mailing_address_master.zipcode,"
                    + "company_master.company_type,"
                    + "company_master.company_key "
                    + "from company_master join company_mailing_address_master on "
                    + "company_master.company_key=company_mailing_address_master.company_key "
                    + "where company_master.company_key in("
                    + "select company_key_to "
                    + "from transaction_master "
                    + "where company_key_from='"+companyKey+"' "
                    + "and transaction_type='Invoice') "
                    + "or company_master.company_key in("
                    + "select company_key_from "
                    + "from transaction_master "
                    + "where company_key_to='"+companyKey+"' "
                    + "and transaction_type='Invoice')";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub=new CompanyDetailAddressBean();
                ub.setCompanyName(rs.getString(1));
                ub.setCompanyId(rs.getString(2));
                ub.setBranch(rs.getString(3));
                ub.setCountry(rs.getString(4));
                ub.setAddress(rs.getString(5));
                ub.setCity(rs.getString(6));
                ub.setState(rs.getString(7));
                ub.setZipcode(rs.getString(8));
                ub.setCompanyType(rs.getString(9));
                ub.setCompanyKey(rs.getString(10));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByTypeCompanyKey in TransactionInvMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByTypeCompanyKey in TransactionInvMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static int showTotalTransactionVolume(String companyKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompanyDetailAddressBean ub = null;
        int result=0;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select sum(inv_total_billing_amount) "
                    + "from transaction_master "
                    + "where (company_key_from='"+companyKey+"' "
                    + "or company_key_to='"+companyKey+"') "
                    + "and transaction_type='Invoice'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                result=rs.getInt(1);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showTotalTransactionVolume in TransactionInvMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showTotalTransactionVolume in TransactionInvMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static TransactionInvBean showByKey(String transactionKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        TransactionInvBean trb = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "SELECT trans_key,inv_trans_rqf_key,company_key_from, "
                    + "company_key_to,inv_trans_status,inv_trans_action,inv_quote_ref, "
                    + "inv_is_outside,inv_is_outside_address,inv_recurring,inv_is_po_created,"
                    + " t.inv_created_on,inv_action_on,company_type,company_name,"
                    + "branch,country,address, "
                    + "city,state,phone,email,zipcode,"
                    + "inv_total_amount,inv_tax_percentage,inv_total_billing_amount,"
                    + "freight_handling,bill_of_landing,freight_weight,"
                    + "freight_weight_unit,shipped_date date,is_inv_paid,inv_paid_on_dated "
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
                    + "and trans_key='" + transactionKey + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                trb = new TransactionInvBean();
                trb.setTrans_key(rs.getString(1));
                trb.setInv_trans_rqf_key("INV" + trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setInv_trans_status(rs.getString(5));
                trb.setInv_trans_action(rs.getString(6));
                trb.setInv_quote_ref(rs.getString(7));
                trb.setInv_is_outside(rs.getString(8));
                trb.setInv_is_outside_address(rs.getString(9));
                trb.setInv_recurring(rs.getString(10));
                trb.setInv_is_po_created(rs.getString(11));
                trb.setInv_created_on(rs.getString(12));
                trb.setInv_action_on(rs.getString(13));
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
                trb.setInv_total_amount(rs.getString(24));
                trb.setInv_tax_percentage(rs.getString(25));
                trb.setInv_total_billing_amount(rs.getString(26));
                trb.setFreight_handling(rs.getString(27));
                trb.setBill_of_landing(rs.getString(28));
                trb.setFreight_weight(rs.getString(29));
                trb.setFreight_weight_unit(rs.getString(30));
                trb.setShipped_date(rs.getString(31));
                trb.setIs_inv_paid(rs.getString(32));
                trb.setInv_paid_on_dated(rs.getString(33));
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByKey in TransactionInvMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByKey in TransactionInvMaster : " + ex.getMessage());
            }
        }
        return trb;
    }

    public static TransactionInvBean showByTransactionKeyFromToCompany(String transactionKey, String companyKey, String who) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        TransactionInvBean trb = null;
        String query = "";
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            if (who.equals("to")) {
                query = "SELECT trans_key,inv_trans_rqf_key,company_key_from, "
                        + "company_key_to,inv_trans_status,inv_trans_action,inv_quote_ref, "
                        + "inv_is_outside,inv_is_outside_address,inv_recurring,inv_is_po_created,"
                        + " t.inv_created_on,inv_action_on,company_type,company_name,"
                        + "branch,country,address, "
                        + "city,state,phone,email,zipcode,is_inv_paid,inv_paid_on_dated "
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
                        + "and trans_key='" + transactionKey + "' "
                        + "and company_key_to='" + companyKey + "'";
            } else if (who.equals("from")) {
                query = "SELECT trans_key,inv_trans_rqf_key,company_key_from, "
                        + "company_key_to,inv_trans_status,inv_trans_action,inv_quote_ref, "
                        + "inv_is_outside,inv_is_outside_address,inv_recurring,inv_is_po_created,"
                        + " t.inv_created_on,inv_action_on,company_type,company_name,"
                        + "branch,country,address, "
                        + "city,state,phone,email,zipcode,is_inv_paid,inv_paid_on_dated "
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
                        + "and trans_key='" + transactionKey + "' "
                        + "and company_key_from='" + companyKey + "'";
            }
            rs = st.executeQuery(query);
            while (rs.next()) {
                trb = new TransactionInvBean();
                trb.setTrans_key(rs.getString(1));
                trb.setInv_trans_rqf_key("INV" + trb.getTrans_key());
                trb.setCompany_key_from(rs.getString(3));
                trb.setCompany_key_to(rs.getString(4));
                trb.setInv_trans_status(rs.getString(5));
                trb.setInv_trans_action(rs.getString(6));
                trb.setInv_quote_ref(rs.getString(7));
                trb.setInv_is_outside(rs.getString(8));
                trb.setInv_is_outside_address(rs.getString(9));
                trb.setInv_recurring(rs.getString(10));
                trb.setInv_is_po_created(rs.getString(11));
                trb.setInv_created_on(rs.getString(12));
                trb.setInv_action_on(rs.getString(13));
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
                trb.setIs_inv_paid(rs.getString(24));
                trb.setInv_paid_on_dated(rs.getString(25));
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByTransactionKeyFromToCompany in TransactionInvMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByTransactionKeyFromToCompany in TransactionInvMaster : " + ex.getMessage());
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
                    + "set inv_trans_status='"+status+"',"
                    + "inv_action_on=sysdate() "
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
public static int updatePaymentStatus(String transKey) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update transaction_master "
                    + "set is_inv_paid='yes',"
                    + "inv_paid_on_dated=sysdate() "
                    + "where trans_key='"+transKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updatePaymentStatus in TransactionPoMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at updatePaymentStatus in TransactionPoMaster : " + ex.getMessage());
            }
        }
        return result;
    }
}

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
import supply.medium.home.bean.CompanyBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Lenovo
 */
public class VrSearchMaster {

    public static int showVrCount(String companyId) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select max(company_key) "
                    + "from company_master "
                    + "where company_id='" + companyId + "';";
            rs = st.executeQuery(query);
            while (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showLastCompanyKeyByCompanyId in CompanyMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showLastCompanyKeyByCompanyId in CompanyMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showCompaniesOutOfNetwork(String companyName, String businessKey, String companyKey, String vrType) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompanyBean cb = null;
        ArrayList al = null;
        String query = "";
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            if (!businessKey.equals("0")) {
                query = "select  company_name,company_key from company_master where company_key not in('"+companyKey+"')  "
                        + "and company_type like '%" + vrType + "%' and company_key in(select company_key from company_business_category_master where business_category_key = '" + businessKey + "') "
                        + "and ("
                        + "company_key in(select company_key_to from vendor_registration_master where company_key_from='" + companyKey + "' and request_status not in('Accepted')) "
                        + "or "
                        + "company_key not in(select company_key_to from vendor_registration_master) "
                        + ")"
                        + "and (company_name like '%" + companyName + "%' or company_key in(select company_key from product_master where item_name like '%" + companyName + "%' or item_description like '%" + companyName + "%')) ";
            } else if (businessKey.equals("0")) {
                query = "select  company_name,company_key from company_master where company_key not in('"+companyKey+"')  "
                        + "and company_type like '%" + vrType + "%' "
                        + "and ("
                        + "company_key in(select company_key_to from vendor_registration_master where company_key_from='" + companyKey + "' and request_status not in('Accepted')) "
                        + "or "
                        + "company_key not in(select company_key_to from vendor_registration_master) "
                        + ")"
                        + "and (company_name like '%" + companyName + "%' or company_key in(select company_key from product_master where item_name like '%" + companyName + "%' or item_description like '%" + companyName + "%')) ";
            }
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                cb = new CompanyBean();
                cb.setCompanyName(rs.getString(1));
                cb.setCompanyKey(rs.getString(2));
                al.add(cb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllCompaniesByCountryKey in CompanyMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllCompaniesByCountryKey in CompanyMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showCompaniesInNetwork(String companyName, String businessKey, String companyKey, String vrType) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompanyBean cb = null;
        ArrayList al = null;
        String query = "";
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            if (!businessKey.equals("0")) {
                query = "select company_name,company_key "
                        + "from company_master "
                        + "where (company_name like '%" + companyName + "%' or company_key in(select company_key from product_master where item_name like '%" + companyName + "%' or item_description like '%" + companyName + "%')) and company_key not in('"+companyKey+"')  "
                        + "and company_type like '%" + vrType + "%' "
                        + "and company_key in "
                        + "(select company_key "
                        + "from company_business_category_master "
                        + "where business_category_key = '" + businessKey + "') "
                        + "and company_key in "
                        + "(select company_key_to "
                        + "from vendor_registration_master "
                        + "where company_key_from='" + companyKey + "' "
                        + "and request_status in('Accepted')) ";
            } else if (businessKey.equals("0")) {
                query = "select company_name,company_key "
                        + "from company_master "
                        + "where (company_name like '%" + companyName + "%' or company_key in(select company_key from product_master where item_name like '%" + companyName + "%' or item_description like '%" + companyName + "%') ) and company_key not in('"+companyKey+"')  "
                        + "and company_type like '%" + vrType + "%' "
                        + "and company_key in "
                        + "(select company_key_to "
                        + "from vendor_registration_master "
                        + "where company_key_from='" + companyKey + "' "
                        + "and request_status in('Accepted')) ";
            }
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                cb = new CompanyBean();
                cb.setCompanyName(rs.getString(1));
                cb.setCompanyKey(rs.getString(2));
                al.add(cb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllCompaniesByCountryKey in CompanyMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllCompaniesByCountryKey in CompanyMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showCompaniesOutOfNetworkInAdvanceSearch(String companyKey,String vrType,String business_category, String country, String part_number, String product_name) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompanyBean cb = null;
        ArrayList al = null;
        String query = "";
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            query = "select company_name,company_key from company_master where company_type like '%" + vrType + "%' and company_key not in('"+companyKey+"') ";

           if (!business_category.equals("0")) {
                query += " and company_key in(select company_key from company_business_category_master where business_category_key='"+business_category+"') ";


            }
            if (!country.equals("0")) {
                query += " and company_key in(select company_key from company_mailing_address_master where country='"+country+"') ";


            }
            if (!part_number.equals("")) {
                query += " and company_key in(select company_key from product_master where part_no like '%"+part_number+"%') ";



            }
            if (!product_name.equals("")) {
                query += " and company_key in(select company_key from product_master where item_name like '%"+product_name+"%') ";

            
            }
            query+="  and (company_key in(select company_key_to from vendor_registration_master where "
                    + "company_key_from='"+companyKey+"' and request_status not in ('Accepted')) or "
                    + "company_key not in(select company_key_to from vendor_registration_master))";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                cb = new CompanyBean();
                cb.setCompanyName(rs.getString(1));
                cb.setCompanyKey(rs.getString(2));
                al.add(cb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllCompaniesByCountryKey in CompanyMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllCompaniesByCountryKey in CompanyMaster : " + ex.getMessage());
            }
        }
        return al;
    }

    public static ArrayList showCompaniesInNetworkInAdvanceSearch(String companyKey,String vrType,String business_category, String country, String part_number, String product_name) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        CompanyBean cb = null;
        ArrayList al = null;
        String query = "";
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            query = "select company_name,company_key from company_master "
                    + "where company_type like '%" + vrType + "%' and company_key not in('"+companyKey+"')  ";

           if (!business_category.equals("0")) {
                query += " and company_key in(select company_key "
                        + "from company_business_category_master "
                        + "where business_category_key='"+business_category+"') ";


            }
            if (!country.equals("0")) {
                query += " and company_key in(select company_key "
                        + "from company_mailing_address_master "
                        + "where country='"+country+"') ";


            }
            if (!part_number.equals("")) {
                query += " and company_key in(select company_key "
                        + "from product_master where part_no like '%"+part_number+"%') ";



            }
            if (!product_name.equals("")) {
                query += " and company_key in(select company_key "
                        + "from product_master where item_name like '%"+product_name+"%') ";

            
            }
            query+=" and company_key in (select company_key_to "
                    + "from vendor_registration_master "
                    + "where company_key_from='"+companyKey+"' "
                    + "and request_status in ('Accepted')) ";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                cb = new CompanyBean();
                cb.setCompanyName(rs.getString(1));
                cb.setCompanyKey(rs.getString(2));
                al.add(cb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllCompaniesByCountryKey in CompanyMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllCompaniesByCountryKey in CompanyMaster : " + ex.getMessage());
            }
        }
        return al;
    }
}

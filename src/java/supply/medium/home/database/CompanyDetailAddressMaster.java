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
import supply.medium.home.bean.CompanyDetailAddressBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class CompanyDetailAddressMaster {

    public static ArrayList showCompanyDetailAddress(String companyKey) {
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
                    + "company_master.company_type "
                    + "from company_master join company_mailing_address_master on "
                    + "company_master.company_key=company_mailing_address_master.company_key "
                    + "where company_master.company_key='"+companyKey+"'";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                ub = new CompanyDetailAddressBean();
                ub.setCompanyName(rs.getString(1));
                ub.setCompanyId(rs.getString(2));
                ub.setBranch(rs.getString(3));
                ub.setCountry(rs.getString(4));
                ub.setAddress(rs.getString(5));
                ub.setCity(rs.getString(6));
                ub.setState(rs.getString(7));
                ub.setZipcode(rs.getString(8));
                ub.setCompanyType(rs.getString(9));
                al.add(ub);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showCompanyDetailAddress in CompanyDetailAddressMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showCompanyDetailAddress in CompanyDetailAddressMaster : " + ex.getMessage());
            }
        }
        return al;
    }
}

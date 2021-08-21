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
import supply.medium.home.bean.VendorRegistrationBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class VendorRegistrationMaster {

    public static String insert(String companyKeyFrom,
            String companyKeyTo,
            String userKeyFrom,
            String userKeyTo,
            String requestSenderType,
            String companyType,
            String businessContactName,
            String businessTaxId,
            String naicsCode,
            String w9taxFormStatus,
            String w9taxFormPath,
            String businessSize,
            String businessClassification,
            String additionalDetails,
            String status) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        long vrKey=System.currentTimeMillis();
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into vendor_registration_master"
                    + "(vr_key,company_key_from,company_key_to,user_key_from,"
                    + "user_key_to,request_sender_type,company_type,"
                    + "business_contact_name,business_tax_id,naics_code,"
                    + "w9tax_form_status,w9tax_form_path,business_size,"
                    + "business_classification,additional_details,request_status,"
                    + "sent_on,accepted_on) "
                    + "values('"+vrKey+"','" + companyKeyFrom + "','" + companyKeyTo + "',"
                    + "'" + userKeyFrom + "','" + userKeyTo + "','" + requestSenderType + "',"
                    + "'" + companyType + "','" + businessContactName + "','" + businessTaxId + "',"
                    + "'" + naicsCode + "','" + w9taxFormStatus + "','" + w9taxFormPath + "',"
                    + "'" + businessSize + "','" + businessClassification + "','" + additionalDetails + "',"
                    + "'"+status+"', sysdate(), sysdate()) ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in VendorRegistrationMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in VendorRegistrationMaster : " + ex.getMessage());
            }
        }
        return vrKey+"";
    }
    
    public static ArrayList showAllByTypeAndStatus(String myCompanyKey,String type,String requestType,String status)
    {
        ArrayList al=new ArrayList();
        Connection con = null;
        Statement st = null;
        ResultSet rs=null;
        VendorRegistrationBean vrb=null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query ="select * "
                    + "from vendor_registration_master ";
            if(type.equals("received"))
            {
                    query+= "where request_sender_type like '%" + requestType + "%' "
                    + "and company_key_to='"+myCompanyKey+"' "
                    + "and request_status like '%" + status + "%';";
            }
            else if(type.equals("sent"))
            {
                    query+= "where request_sender_type like '%" + requestType + "%' "
                    + "and company_key_from='"+myCompanyKey+"' "
                    + "and request_status like '%" + status + "%';";
            }
            rs=st.executeQuery(query);
            while(rs.next())
            {
                vrb=new VendorRegistrationBean();
                vrb.setVrKey(rs.getString(1));
                vrb.setCompanyKeyFrom(rs.getString(2));
                vrb.setCompanyKeyTo(rs.getString(3));
                vrb.setUserKeyFrom(rs.getString(4));
                vrb.setUserKeyTo(rs.getString(5));
                vrb.setRequestSenderType(rs.getString(6));
                vrb.setCompanyType(rs.getString(7));
                vrb.setBusinessContactName(rs.getString(8));
                vrb.setBusinessTaxId(rs.getString(9));
                vrb.setNaicsCode(rs.getString(10));
                vrb.setW9taxFormStatus(rs.getString(11));
                vrb.setW9taxFormPath(rs.getString(12));
                vrb.setBusinessSize(rs.getString(13));
                vrb.setBusinessClassification(rs.getString(14));
                vrb.setAdditionalDetails(rs.getString(15));
                vrb.setRequestStatus(rs.getString(16));
                vrb.setSentOn(rs.getString(17));
                vrb.setAcceptedOn(rs.getString(18));
                al.add(vrb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllByTypeAndStatus in VendorRegistrationMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllByTypeAndStatus in VendorRegistrationMaster : " + ex.getMessage());
            }
        }
        return al;
    }
    
    public static VendorRegistrationBean showByVrKey(String vrKey)
    {
        
        Connection con = null;
        Statement st = null;
        ResultSet rs=null;
        VendorRegistrationBean vrb=null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query ="select * "
                    + "from vendor_registration_master "
                    + "where vr_key='"+vrKey+"' ";
           
            rs=st.executeQuery(query);
            while(rs.next())
            {
                vrb=new VendorRegistrationBean();
                vrb.setVrKey(rs.getString(1));
                vrb.setCompanyKeyFrom(rs.getString(2));
                vrb.setCompanyKeyTo(rs.getString(3));
                vrb.setUserKeyFrom(rs.getString(4));
                vrb.setUserKeyTo(rs.getString(5));
                vrb.setRequestSenderType(rs.getString(6));
                vrb.setCompanyType(rs.getString(7));
                vrb.setBusinessContactName(rs.getString(8));
                vrb.setBusinessTaxId(rs.getString(9));
                vrb.setNaicsCode(rs.getString(10));
                vrb.setW9taxFormStatus(rs.getString(11));
                vrb.setW9taxFormPath(rs.getString(12));
                vrb.setBusinessSize(rs.getString(13));
                vrb.setBusinessClassification(rs.getString(14));
                vrb.setAdditionalDetails(rs.getString(15));
                vrb.setRequestStatus(rs.getString(16));
                vrb.setSentOn(rs.getString(17));
                vrb.setAcceptedOn(rs.getString(18));
                
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByVrKey in VendorRegistrationMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByVrKey in VendorRegistrationMaster : " + ex.getMessage());
            }
        }
        return vrb;
    }
    
    public static int vrAction(String vrKey, String vrAction) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update vendor_registration_master "
                    + "set request_status='"+vrAction+"' "
                    + "where vr_key='"+vrKey+"'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at vrAction in VendorRegistrationMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing vrAction in VendorRegistrationMaster : " + ex.getMessage());
            }
        }
        return result;
    }

}

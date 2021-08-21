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
import supply.medium.home.bean.InquiryBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Lenovo
 */
public class InquiryMaster {
    
    public static int insert(String inquire_type,String vr_key,String rfq_key,
            String transaction_key,String inquire_from,String inquire_to,String inquire_details)
    {
        int result=0;
        Connection con=null;
        Statement st=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="insert into "
                    + "inquire_master(inquire_type,vr_key,rfq_key,"
                    + "transaction_key,inquire_from,inquire_to,"
                    + "inquire_details,sent_on) "
                    + "values('"+inquire_type+"','"+vr_key+"','"+rfq_key+"',"
                    + "'"+transaction_key+"','"+inquire_from+"','"+inquire_to+"','"+inquire_details+"',sysdate()) ";
            result=st.executeUpdate(query);
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at insert in InquiryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing insert in InquiryMaster : "+ex.getMessage());
            }
        }
        return result;
    }
    
    public static ArrayList showAllInquiryByTypeAndKey(String inquire_type,String key)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        ArrayList al=new ArrayList();
        InquiryBean ib=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select * "
                    + "from inquire_master "
                    + "where inquire_type='"+inquire_type+"' ";
            if(inquire_type.equals("VR"))
                    query+= "and vr_key='"+key+"';";
            else if(inquire_type.equals("RFQ"))
                    query+= "and rfq_key='"+key+"';";
            else
                    query+= "and transaction_key='"+key+"';";
            
            rs=st.executeQuery(query);
            while(rs.next())
            {
                ib=new InquiryBean();
                ib.setInquiry_key(rs.getString(1));
                ib.setInquire_type(rs.getString(2));
                ib.setVr_key(rs.getString(3));
                ib.setRfq_key(rs.getString(4));
                ib.setTransaction_key(rs.getString(5));
                ib.setInquire_from(rs.getString(6));
                ib.setInquire_to(rs.getString(7));
                ib.setInquire_details(rs.getString(8));
                ib.setSent_on(rs.getString(9));
                al.add(ib);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showAllInquiryByTypeAndKey in InquiryMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showAllInquiryByTypeAndKey in InquiryMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
}

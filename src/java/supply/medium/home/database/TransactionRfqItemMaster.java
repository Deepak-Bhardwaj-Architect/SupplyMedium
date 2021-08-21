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
import supply.medium.home.bean.TransactionRfqItemBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class TransactionRfqItemMaster {
    
    public static int insertRfqItem(String transRqfKey,String rfqNo,String itemKey,String partNo,String barcode,
            String quantity,String quantityUnitKey,String shipDate) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        quantityUnitKey=QuantityTypeMaster.showKeyByCode(quantityUnitKey);
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into trans_rfq_item_master "
                    + "(trans_rqf_key,rfq_no,item_key,part_no,"
                    + "barcode,quantity,quantity_unit_key,ship_date)"
                    + "values('"+transRqfKey+"','"+rfqNo+"',"
            + "'"+itemKey+"','"+partNo+"',"
            + "'"+barcode+"','"+quantity+"','"+quantityUnitKey+"','"+shipDate+"') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insertRfqItem in TransRfqItemMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insertRfqItem in TransRfqItemMaster : " + ex.getMessage());
            }
        }
        return result;
    } 
    
    public static ArrayList showRfqItemFromRfqKey(String rfqKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionRfqItemBean trib=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select trans_rqf_item_key,rfq_no,item_key,"
                    + "part_no,barcode,quantity,quantity_unit_key,"
                    + "ship_date "
                    + "from trans_rfq_item_master where trans_rqf_key='"+rfqKey+"'";
                    
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                trib=new TransactionRfqItemBean();
                trib.setTransRqfItemKey(rs.getString(1));
                trib.setRfqNo(rs.getString(2));
                trib.setItemKey(rs.getString(3));
                trib.setPartNo(rs.getString(4));
                trib.setBarcode(rs.getString(5));
                trib.setQuantity(rs.getString(6));
                trib.setQuantityUnitKey(rs.getString(7));
                trib.setShipDate(rs.getString(8));
                al.add(trib);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showRfqItemFromRfqKey in TransRfqItemMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showRfqItemFromRfqKey in TransRfqItemMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
}

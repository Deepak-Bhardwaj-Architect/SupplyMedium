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
import supply.medium.home.bean.TransactionPoItemBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Lenovo
 */
public class TransactionPoItemMaster {
    
    public static int insertPoItem(String transPoKey,String poNo,String itemKey,String partNo,String barcode,
            String quantity,String quantityUnitKey,String shipDate,String price,String currencyKey,String multiplier) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
//            quantityUnitKey=QuantityTypeMaster.showKeyByCode(quantityUnitKey);
//            currencyKey=CurrencyMaster.showKeyByCode(currencyKey);
            String query = "insert into transaction_item_master "
                    + "(trans_key,po_po_no,po_item_key,po_part_no,"
                    + "po_barcode,po_quantity,po_quantity_type_key,po_ship_date,"
                    + "po_price,po_currency_key,po_multiplier)"
                    + "values('"+transPoKey+"','"+poNo+"','"+itemKey+"','"+partNo+"',"
                    + "'"+barcode+"','"+quantity+"','"+quantityUnitKey+"','"+shipDate+"',"
                    + "'"+price+"','"+currencyKey+"','"+multiplier+"') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insertPoItem in TransactionPoItemMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insertPoItem in TransactionPoItemMaster : " + ex.getMessage());
            }
        }
        return result;
    } 
    
    public static ArrayList showPoItemFromPoKey(String poKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionPoItemBean trib=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select trans_item_key,po_po_no,po_item_key,"
                    + "po_part_no,po_barcode,po_quantity,po_quantity_type_key,"
                    + "po_ship_date,po_price,po_currency_key,po_multiplier "
                    + "from transaction_item_master where trans_key='"+poKey+"'";
                    
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                trib=new TransactionPoItemBean();
                trib.setTransPoItemKey(rs.getString(1));
                trib.setPoPoNo(rs.getString(2));
                trib.setItemKey(rs.getString(3));
                trib.setPartNo(rs.getString(4));
                trib.setBarcode(rs.getString(5));
                trib.setQuantity(rs.getString(6));
                trib.setQuantityUnitKey(rs.getString(7));
                trib.setShipDate(rs.getString(8));
                trib.setPrice(rs.getString(9));
                trib.setCurrencyKey(rs.getString(10));
                trib.setMultiplier(rs.getString(11));
                al.add(trib);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showPoItemFromPoKey in TransactionPoItemMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showPoItemFromPoKey in TransactionPoItemMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
}

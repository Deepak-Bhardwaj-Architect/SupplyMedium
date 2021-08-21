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
import supply.medium.home.bean.TransactionInvItemBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Lenovo
 */
public class TransactionInvItemMaster {
    
    public static int insertInvItem(String transInvKey,String poNo,String itemKey,String partNo,String barcode,
            String quantityOrdered,String quantityOrderedUnitKey,
            String quantityShipped,String quantityShippedUnitKey,String shipDate,String price,String currencyKey,String multiplier) 
    {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            quantityOrderedUnitKey=QuantityTypeMaster.showKeyByCode(quantityOrderedUnitKey);
            quantityShippedUnitKey=QuantityTypeMaster.showKeyByCode(quantityShippedUnitKey);
            currencyKey=CurrencyMaster.showKeyByCode(currencyKey);
            String query = "insert into transaction_item_master "
                    + "(trans_key,inv_inv_no,inv_item_key,inv_part_no,"
                    + "inv_barcode,inv_quantity_oredered,inv_quantity_oredered_type_key,"
                    + "inv_quantity_shipped,inv_quantity_shipped_type_key,inv_ship_date,"
                    + "inv_price,inv_currency_key,inv_multiplier)"
                    + "values('"+transInvKey+"','"+poNo+"',"
                    + "'"+itemKey+"','"+partNo+"',"
                    + "'"+barcode+"','"+quantityOrdered+"','"+quantityOrderedUnitKey+"',"
                    + "'"+quantityShipped+"','"+quantityShippedUnitKey+"','"+shipDate+"',"
                    + "'"+price+"','"+currencyKey+"','"+multiplier+"') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insertInvItem in TransactionInvItemMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insertInvItem in TransactionInvItemMaster : " + ex.getMessage());
            }
        }
        return result;
    } 
    
    public static ArrayList showInvItemFromInvKey(String poKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionInvItemBean trib=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select trans_item_key,inv_inv_no,inv_item_key,"
                    + "inv_part_no,inv_barcode,inv_quantity_oredered,inv_quantity_oredered_type_key,"
                    + "inv_quantity_shipped,inv_quantity_shipped_type_key,"
                    + "inv_ship_date,inv_price,inv_currency_key,inv_multiplier "
                    + "from transaction_item_master where trans_key='"+poKey+"'";
                    
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                trib=new TransactionInvItemBean();
                trib.setTransInvItemKey(rs.getString(1));
                trib.setInvInvNo(rs.getString(2));
                trib.setItemKey(rs.getString(3));
                trib.setPartNo(rs.getString(4));
                trib.setBarcode(rs.getString(5));
                trib.setQuantityOrdered(rs.getString(6));
                trib.setQuantityOrderedUnitKey(rs.getString(7));
                trib.setQuantityShipped(rs.getString(8));
                trib.setQuantityShippedUnitKey(rs.getString(9));
                trib.setShipDate(rs.getString(10));
                trib.setPrice(rs.getString(11));
                trib.setCurrencyKey(rs.getString(12));
                trib.setMultiplier(rs.getString(13));
                al.add(trib);
            }
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at showInvItemFromInvKey in TransactionInvItemMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showInvItemFromInvKey in TransactionInvItemMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
}

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
import supply.medium.home.bean.TransactionQteItemBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Lenovo
 */
public class TransactionQteItemMaster {
    
    public static int insertQteItem(String transQteKey,String qteNo,String itemKey,String partNo,String barcode,
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
                    + "(trans_key,q_quote_no,q_item_key,q_part_no,"
                    + "q_barcode,q_quantity,q_quantity_type_key,q_ship_date,"
                    + "q_price,q_currency_key,q_multiplier)"
                    + "values('"+transQteKey+"','"+qteNo+"',"
                    + "'"+itemKey+"','"+partNo+"',"
                    + "'"+barcode+"','"+quantity+"','"+quantityUnitKey+"','"+shipDate+"',"
                    + "'"+price+"','"+currencyKey+"','"+multiplier+"') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insertQteItem in TransactionQuoteItemMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insertQteItem in TransactionQuoteItemMaster : " + ex.getMessage());
            }
        }
        return result;
    } 
    
    public static ArrayList showQteItemFromQteKey(String qteKey)
    {
        Connection con=null;
        Statement st=null;
        ResultSet rs=null;
        TransactionQteItemBean trib=null;
        ArrayList al=null;
        try
        {
            con=MyConnection.connect();
            st=con.createStatement();
            String query="select trans_item_key,q_quote_no,q_item_key,"
                    + "q_part_no,q_barcode,q_quantity,q_quantity_type_key,"
                    + "q_ship_date,q_price,q_currency_key,q_multiplier "
                    + "from transaction_item_master where trans_key='"+qteKey+"'";
                    
            rs=st.executeQuery(query);
            al=new ArrayList();
            while(rs.next())
            {
                trib=new TransactionQteItemBean();
                trib.setTransQteItemKey(rs.getString(1));
                trib.setqQuoteNo(rs.getString(2));
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
            ErrorMaster.insert("Exception at showQteItemFromQteKey in TransactionQuoteItemMaster : "+ex.getMessage());
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
                ErrorMaster.insert("Exception at closing showQteItemFromQteKey in TransactionQuoteItemMaster : "+ex.getMessage());
            }
        }
        return al;
    }
    
}

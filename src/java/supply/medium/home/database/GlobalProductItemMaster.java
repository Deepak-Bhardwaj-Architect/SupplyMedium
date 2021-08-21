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
import supply.medium.home.bean.GlobalProductItemBean;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author !!ULTIMATE
 */
public class GlobalProductItemMaster {

    public static int insert(String companyKey, String itemName,String itemDesc,String partNo,
            String SKUno,String barcode,String quantity, String quantityKey,
            String price,String currencyKey,String tax,String picsCount) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            quantityKey=QuantityTypeMaster.showKeyByCode(quantityKey);
            currencyKey=CurrencyMaster.showKeyByCode(currencyKey);
            String query = "insert into "
                    + "product_master(company_key,item_name,item_description,part_no,"
                    + "sku,barcode,quantity,quantity_key,"
                    + "price,currency_key,tax,product_display,pics_count) "
                    + "values('" + companyKey + "','" + itemName + "','" + itemDesc + "','" + partNo + "',"
                    + "'" + SKUno + "','" + barcode + "','" + quantity + "','" + quantityKey + "',"
                    + "'" + price + "','" + currencyKey + "','" + tax + "','NO','"+picsCount+"') ";
            ErrorMaster.insert(query);
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in GlobalProductItemMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in GlobalProductItemMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static ArrayList showAllByCompanyKey(String companyKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GlobalProductItemBean pb = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * "
                    + "from product_master "
                    + "where company_key='" + companyKey + "'";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                pb = new GlobalProductItemBean();
                pb.setItemKey(rs.getString(1));
                pb.setCompanyKey(rs.getString(2));
                pb.setItemName(rs.getString(3));
                pb.setItemDescription(rs.getString(4));
                pb.setPartNo(rs.getString(5));
                pb.setSKUno(rs.getString(6));
                pb.setBarcode(rs.getString(7));
                pb.setQuantity(rs.getString(8));
                pb.setQuantityKey(rs.getString(9));
                pb.setPrice(rs.getString(10));
                pb.setCurrencyKey(rs.getString(11));
                pb.setTax(rs.getString(12));
                pb.setProductDisplay(rs.getString(13));
                pb.setPicsCount(rs.getString(14));
                al.add(pb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showAllByCompanyKey in GlobalProductItemMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showAllByCompanyKey in GlobalProductItemMaster : " + ex.getMessage());
            }
        }
        return al;
    }
    
    public static GlobalProductItemBean showByItemKey(String itemKey) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GlobalProductItemBean pb = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * "
                    + "from product_master "
                    + "where item_key='" + itemKey + "'";
            rs = st.executeQuery(query);
            while (rs.next()) {
                pb = new GlobalProductItemBean();
                pb.setItemKey(rs.getString(1));
                pb.setCompanyKey(rs.getString(2));
                pb.setItemName(rs.getString(3));
                pb.setItemDescription(rs.getString(4));
                pb.setPartNo(rs.getString(5));
                pb.setSKUno(rs.getString(6));
                pb.setBarcode(rs.getString(7));
                pb.setQuantity(rs.getString(8));
                pb.setQuantityKey(rs.getString(9));
                pb.setPrice(rs.getString(10));
                pb.setCurrencyKey(rs.getString(11));
                pb.setTax(rs.getString(12));
                pb.setProductDisplay(rs.getString(13));
                pb.setPicsCount(rs.getString(14));
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showByItemKey in GlobalProductItemMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showByItemKey in GlobalProductItemMaster : " + ex.getMessage());
            }
        }
        return pb;
    }
    
    public static ArrayList showSearchRecordsForTransaction(String companyKey,String startValue) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GlobalProductItemBean pb = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * "
                    + "from product_master "
                    + "where "
//                    + "company_key='" + companyKey + "'"
//                    + "and "
                    + "(item_name like '"+startValue+"%' "
                    + "or item_description like '"+startValue+"%' "
                    + "or part_no like '"+startValue+"%' "
                    + "or sku like '"+startValue+"%' "
                    + "or barcode like '"+startValue+"%')";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                pb = new GlobalProductItemBean();
                pb.setItemKey(rs.getString(1));
                pb.setCompanyKey(rs.getString(2));
                pb.setItemName(rs.getString(3));
                pb.setItemDescription(rs.getString(4));
                pb.setPartNo(rs.getString(5));
                pb.setSKUno(rs.getString(6));
                pb.setBarcode(rs.getString(7));
                pb.setQuantity(rs.getString(8));
                pb.setQuantityKey(rs.getString(9));
                pb.setPrice(rs.getString(10));
                pb.setCurrencyKey(rs.getString(11));
                pb.setTax(rs.getString(12));
                pb.setProductDisplay(rs.getString(13));
                pb.setPicsCount(rs.getString(14));
                al.add(pb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at showSearchRecordsForTransaction in GlobalProductItemMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing showSearchRecordsForTransaction in GlobalProductItemMaster : " + ex.getMessage());
            }
        }
        return al;
    }
    
    public static ArrayList searchProductItemByValue(String companyKey, String value) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        GlobalProductItemBean pb = null;
        ArrayList al = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "select * "
                    + "from product_master "
                    + "where company_key='" + companyKey + "' "
                    + "and item_name like '%"+value+"%' "
                    + "or item_desc like '%"+value+"%' "
                    + "or part_no like '%"+value+"%' "
                    + "or sku like '%"+value+"%' "
                    + "or barcode like '%"+value+"%' ";
            rs = st.executeQuery(query);
            al = new ArrayList();
            while (rs.next()) {
                pb = new GlobalProductItemBean();
                pb.setItemKey(rs.getString(1));
                pb.setCompanyKey(rs.getString(2));
                pb.setItemName(rs.getString(3));
                pb.setItemDescription(rs.getString(4));
                pb.setPartNo(rs.getString(5));
                pb.setSKUno(rs.getString(6));
                pb.setBarcode(rs.getString(7));
                pb.setQuantity(rs.getString(8));
                pb.setQuantityKey(rs.getString(9));
                pb.setPrice(rs.getString(10));
                pb.setCurrencyKey(rs.getString(11));
                pb.setTax(rs.getString(12));
                pb.setProductDisplay(rs.getString(13));
                pb.setPicsCount(rs.getString(14));
                al.add(pb);
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at searchProductItemByValue in GlobalProductItemMaster : " + ex.getMessage());
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing searchProductItemByValue in GlobalProductItemMaster : " + ex.getMessage());
            }
        }
        return al;
    }
    
    public static int updateProductDetails(String itemKey, String itemName,String itemDesc,String partNo,
            String SKUno,String barcode,String quantity, String quantityKey,
            String price,String currencyKey,String tax) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update product_master "
                    + "set item_name='" + itemName + "', "
                    + "item_description='"+itemDesc+"', "
                    + "part_no='"+partNo+"', "
                    + "sku='"+SKUno+"', "
                    + "quantity='"+quantity+"', "
                    + "quantity_key='"+quantityKey+"', "
                    + "price='"+price+"', "
                    + "currency_key='"+currencyKey+"', "
                    + "tax='"+tax+"' "
                    + "where item_key='" + itemKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateProductDetails in GlobalProductItemMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updateProductDetails in GlobalProductItemMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static int updateProductDisplay(String itemKey, String productDisplay) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update product_master "
                    + "set product_display='"+productDisplay+"' "
                    + "where item_key='" + itemKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updateProductDisplay in GlobalProductItemMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updateProductDisplay in GlobalProductItemMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
    public static int updatePicsCount(String itemKey, String picsCount) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "update product_master "
                    + "set pics_count='"+picsCount+"' "
                    + "where item_key='" + itemKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at updatePicsCount in GlobalProductItemMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing updatePicsCount in GlobalProductItemMaster : " + ex.getMessage());
            }
        }
        return result;
    }

    public static int delete(String itemKey) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "delete from group_master "
                    + "where item_key='" + itemKey + "'";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in GlobalProductItemMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing delete in GlobalProductItemMaster : " + ex.getMessage());
            }
        }
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.database;

import java.sql.Connection;
import java.sql.Statement;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.MyConnection;

/**
 *
 * @author Intel
 */
public class GroupPermMaster {
    
    public static int insert(String groupKey,String a,String b,String c,String d,
            String e,String f,String g,String h,String i,String j,
            String k,String l,String m,String n) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "group_perm_master "
                    + "values('" + groupKey + "','" + a + "','" + b + "','" + c + "','" + d 
                    + "','" + e + "','" + f + "','" + g + "','" + h + "','" + i + "','" + j 
                    + "','" + k + "','" + l + "','" + m + "','" + n + "') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in GroupPermMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in GroupPermMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
}

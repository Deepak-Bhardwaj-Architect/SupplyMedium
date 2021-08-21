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
public class DepartmentPermMaster {
    
    public static int insert(String deptKey,String a,String b,String c,String d,
            String e,String f) {
        int result = 0;
        Connection con = null;
        Statement st = null;
        try {
            con = MyConnection.connect();
            st = con.createStatement();
            String query = "insert into "
                    + "department_perm_master "
                    + "values('" + deptKey + "','" + a + "','" + b + "','" + c + "','" + d 
                    + "','" + e + "','" + f + "') ";
            result = st.executeUpdate(query);
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at insert in DepartmentPermMaster : " + ex.getMessage());
        } finally {
            try {
                st.close();
                con.close();
            } catch (Exception ex) {
                ErrorMaster.insert("Exception at closing insert in DepartmentPermMaster : " + ex.getMessage());
            }
        }
        return result;
    }
    
}

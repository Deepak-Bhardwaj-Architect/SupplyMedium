package supply.medium.home.superadmin;

import supply.medium.utility.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lokesh
 */
public class SuperAdminQuery {

    /**
     * *******************function used to select all queries data*************************
     */
    Statement statement = null;
    Connection con = null;
    ResultSet rs = null;

    public ResultSet select(String str) throws SQLException {
        statement = null;
        con = null;
        rs = null;
        con = MyConnection.connect();
        statement = con.createStatement();
        System.out.println(str);
        rs = statement.executeQuery(str);
        return rs;
    }

    /**
     * *******************************function used to insert or update or
     * delete queries******************************
     */
    public int insert_update_delete(String str) throws SQLException {
        int i = 0;
        statement = null;
        con = null;
        rs = null;
        con = MyConnection.connect();
        statement = con.createStatement();
        i = statement.executeUpdate(str);
        return i;
    }

    public void close() {
        try {
            con.close();
            statement = null;
            con = null;
        } catch (SQLException ex) {
            System.out.print("Problem close connection: " + ex);
        }
    }
}

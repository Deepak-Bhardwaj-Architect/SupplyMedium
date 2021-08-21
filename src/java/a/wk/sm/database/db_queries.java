package a.wk.sm.database;

import db.utils.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lokesh
 */
public class db_queries {
    /*********************function used to select all queries data**************************/
    public ResultSet select(String str) {
        Statement statement = null;
        Connection con = null;
        ResultSet rs = null;

        try {
            con = DBConnect.instance().getConnection();
            statement = con.createStatement();
            rs = statement.executeQuery(str);
        } catch (SQLException e) {
            System.out.print("Problem to get data: " + e);
        } finally {
            try {
                con.close();
                statement = null;
                con = null;
            } catch (SQLException ex) {
               // Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return rs;
    }
    /*********************************function used to insert or update or delete queries*******************************/
    public int insert_update_delete(String str) {
        int i = 0;
        Statement statement = null;
        Connection con = null;

        try {
            con = DBConnect.instance().getConnection();
            statement = con.createStatement();
            statement.executeUpdate(str);
            i = 1;
        } catch (SQLException e) {
            System.out.print("Problem to insert or update or delete data: " + e);
        } finally {
            try {
                con.close();
                statement = null;
                con = null;
            } catch (SQLException ex) {
                //Logger.getLogger(like_comment_count.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return i;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.utility;

import java.sql.*;

/**
 *
 * @author Intel8GB
 */
public class MyConnection {
    
    public static Connection connect()
    {
        Connection con=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/supplymedium", "root", "");
//           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/supplymedium", "root", "5AkWPXgCWr");
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at connect() of MyConnection : "+ex.getMessage());
        }
        return con;
    }
    
}
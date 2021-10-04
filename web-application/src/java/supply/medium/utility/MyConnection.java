package supply.medium.utility;

/*
 * 
 * 
 * 
 */



import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author LenovoB560
 */
public class MyConnection {
    
    public static Connection connect()
    {
        Connection con=null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
//            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/supplymedium", "root", "");
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/supplymedium", "root", "5AkWPXgCWr");
        }
        catch(Exception ex)
        {
            ErrorMaster.insert("Exception at connect() of MyConnection : "+ex.getMessage());
        }
        return con;
    }
    
}

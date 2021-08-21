
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lokesh
 */
public class random_number {
    public static String get_success()
    {
        Calendar lCDateTime = Calendar.getInstance();
        return lCDateTime.getTimeInMillis()+"1"; 
    }
    
    public static String get_fail()
    {
        Calendar lCDateTime = Calendar.getInstance();
        return lCDateTime.getTimeInMillis()+"0"; 
    }
   
    
}

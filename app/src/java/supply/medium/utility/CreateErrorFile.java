/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.utility;

import java.io.*;
import java.util.Date;

/**
 *
 * @author Intel8GB
 */
public class CreateErrorFile {

    public static void create(String msg)
    {
        try
        {
            Date obj=new Date();
            
            String strPath = "error"+obj.getTime()+".txt";
            File strFile = new File(strPath);
            boolean fileCreated = strFile.createNewFile();
            //File appending
            Writer objWriter = new BufferedWriter(new FileWriter(strFile));
            objWriter.write(msg);
            objWriter.flush();
            objWriter.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

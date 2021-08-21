
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lokesh
 */
public class delete_folder {
    
    public void delete(File file) {
        try
        {
        // Check if file is directory/folder
        if (file.isDirectory()) {
            // Get all files in the folder
            File[] files = file.listFiles();

            for (int i = 0; i < files.length; i++) {

                // Delete each file in the folder
                delete(files[i]);

            }

            // Delete the folder
            file.delete();

        } else {

            // Delete the file if it is not a folder
            file.delete();

        }
    }
        catch(Exception e)
        {
            System.out.println("error.........................................."+e);
        }
    }
    
}

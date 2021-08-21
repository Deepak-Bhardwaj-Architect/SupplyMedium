package supply.medium.utility;

import java.io.File;


public class DeleteFolder {

    public void delete(File file) {
        try {
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
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at delete in DeleteFolder : " + ex.getMessage());
        }
    }

}
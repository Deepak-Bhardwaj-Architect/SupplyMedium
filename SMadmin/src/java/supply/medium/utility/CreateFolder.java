/*
 * 
 * 
 * 
 */
package supply.medium.utility;

import java.io.File;

/**
 *
 * @author Intel
 */
public class CreateFolder {

    int result = 0;

    public int createFolder(String filePath) {
        try {
            File uploadDir = new File(filePath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            result = 1;
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at createFolder in CreateFolder : "+ ex.getMessage());
            result = 0;
        }
        return result;
    }
}

/*
 * 
 * 
 * 
 */
package supply.medium.utility;

/**
 *
 * @author Lenovo
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {
    
    public static void copyFiles(String source, String target, String fileName[])
    {
            File uploadDir = new File(source);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            File uploadDir2 = new File(target);
            if (!uploadDir2.exists()) {
                uploadDir2.mkdirs();
            }
        String filename=null;
        String sourceFileName=null;
        String destinationFileName=null;
        File inputLocation = new File(source);
        if (inputLocation.isDirectory()) {
            File[] attachmentFiles = inputLocation.listFiles();
            for (File aFile : attachmentFiles) {
                if (!aFile.isDirectory()) {
                    filename = aFile.getName();
                    for(int i=0;i<fileName.length;i++)
                    {
                        if(filename.equals(fileName[i]))
                        {
                            sourceFileName = aFile.getAbsolutePath();
                            destinationFileName = target + filename;
                            copyCompleteFile(sourceFileName, destinationFileName);
                        }
                    }
                }
                //aFile.delete();
            }
        }
    }

    /**
     *
     * @param sourceFileName
     * @param destionFileName
     *
     * Copies a <span id="IL_AD1" class="IL_AD">single file</span> from source
     * location to a destination location.
     */
    public static void copyCompleteFile(String sourceFileName, String destionFileName) {
        try {
//            ErrorMaster.insert("source : "+sourceFileName);
//            ErrorMaster.insert("target : "+destionFileName);
            File sourceFile = new File(sourceFileName);
            File destinationFile = new File(destionFileName);
            InputStream in = new FileInputStream(sourceFile);
            OutputStream out = new FileOutputStream(destinationFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at copyCompleteFile in FileCopy : "+ex.getMessage());
        }
    }
}

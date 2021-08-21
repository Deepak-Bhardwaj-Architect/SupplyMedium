/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supply.medium.home.edi;

import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 *
 * @author Vic
 */
public class Zipper {

    List<String> fileList;
    String OUTPUT_ZIP_FILE = "D:\\javal.zip";
    String SOURCE_FOLDER = "D:\\javal";

    Zipper(String ozf, String sf) {
        fileList = new ArrayList<String>();
        SOURCE_FOLDER = sf;
        OUTPUT_ZIP_FILE = ozf;
    }

    public static void main(String[] args) {
        String OUTPUT_ZIP_FILE = "D:\\javal.zip";
        String SOURCE_FOLDER = "D:\\javal";
        Zipper appZip = new Zipper(OUTPUT_ZIP_FILE, SOURCE_FOLDER);
        appZip.generateFileList();
        appZip.zipIt();
    }

    /**
     * Zip it
     *
     * @param zipFile output ZIP file location
     */
    public void zipIt() {

        String zipFile = OUTPUT_ZIP_FILE;
        byte[] buffer = new byte[1024];

        try {

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String file : this.fileList) {
                if (file.replace(".", "@#@#@").split("@#@#@")[1].equals("xml")) {
                    ZipEntry ze = new ZipEntry(file);
                    zos.putNextEntry(ze);

                    FileInputStream in
                            = new FileInputStream(SOURCE_FOLDER + File.separator + file);

                    int len;
                    while ((len = in.read(buffer)) > 0) {
                        zos.write(buffer, 0, len);
                    }

                    in.close();
                }
            }

            zos.closeEntry();
            //remember close it
            zos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Traverse a directory and get all files, and add the file into fileList
     *
     * @param node file or directory
     */
    public void generateFileList() {

        File node = new File(SOURCE_FOLDER);
        //add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                generateFileList(new File(node, filename));
            }
        }

    }

    public void generateFileList(File node) {

//        File node=new File(SOURCE_FOLDER);
        //add file only
        if (node.isFile()) {
            fileList.add(generateZipEntry(node.getAbsoluteFile().toString()));
        }

        if (node.isDirectory()) {
            String[] subNote = node.list();
            for (String filename : subNote) {
                generateFileList(new File(node, filename));
            }
        }

    }

    /**
     * Format the file path for zip
     *
     * @param file file path
     * @return Formatted file path
     */
    private String generateZipEntry(String file) {
        return file.substring(SOURCE_FOLDER.length() + 1, file.length());
    }
}

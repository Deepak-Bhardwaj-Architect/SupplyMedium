/*
 * 
 * 
 * 
 */
package supply.medium.utility;

import java.io.*;
import org.apache.commons.fileupload.FileItem;
import supply.medium.home.database.GlobalProductItemMaster;

/**
 *
 * @author Lenovo
 */
public class ImportProductFromExcel {

    public static int uploadCsvData(String companyKey, FileItem fileitem) {

        int i = 0;

        // processes only fields that are not form fields
        if (!fileitem.isFormField()) {

            BufferedReader br = null;
            String cvsSplitBy = "\\,";
            String line = "";

            try {
                br = new BufferedReader(new InputStreamReader(fileitem.getInputStream()));
                while ((line = br.readLine()) != null) {
                    String[] innerList = line.split(cvsSplitBy);

                    if (i == 0) {
                        // Validating the Column Header
                        if (!innerList[0].trim().equalsIgnoreCase("Item Name") || !innerList[1].trim().equalsIgnoreCase("Item Description")
                                || !innerList[2].trim().equalsIgnoreCase("Part Number") || !innerList[3].trim().equalsIgnoreCase("SKU")
                                || !innerList[4].trim().equalsIgnoreCase("Barcode") || !innerList[5].trim().equalsIgnoreCase("Quantity")
                                || !innerList[6].trim().equalsIgnoreCase("Quantity Unit") || !innerList[7].trim().equalsIgnoreCase("Price")
                                || !innerList[8].trim().equalsIgnoreCase("Currency") || !innerList[9].trim().equalsIgnoreCase("Tax")) {
                            return -1; // CSV Not in Correct format
                        }
                    } else {
                        GlobalProductItemMaster.insert(companyKey, innerList[0], innerList[1], innerList[2], innerList[3], innerList[4], innerList[5], innerList[6], innerList[7], innerList[8], innerList[9], ", ");
                    }
                    i++;

                }
                i--;

            } catch (Exception ex) {
                ErrorMaster.insert("Exception at uploadCsvData in ImportProductFromExcel : " + ex.getMessage());
            } finally {
                try {
                    br.close();
                } catch (Exception ex) {
                    ErrorMaster.insert("Exception at closing of uploadCsvData in ImportProductFromExcel : " + ex.getMessage());
                }
            }
        }
        return i;
    }

}

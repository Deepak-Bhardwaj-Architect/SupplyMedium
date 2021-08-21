package supply.medium.home.servlet;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import supply.medium.home.database.GlobalProductItemMaster;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.FileCopy;
import supply.medium.utility.SmProperties;
import supply.medium.utility.MemoryTest;

public class addProductToCatalog2 extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    //private int maxFileSize = 50 * 1024;
    //private int maxMemSize = 4 * 1024;
    private File file;

    public void init() {
        SmProperties.folderPath=SmProperties.folderPath.replace(File.separator+"app"+File.separator, File.separator+"zData"+File.separator);
        filePath = SmProperties.folderPath;
    }

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {
        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        if (!isMultipart) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p>No file uploaded</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        //factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        //factory.setRepository(new File("c:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        //upload.setSizeMax(maxFileSize);
        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);
            // Process the uploaded file items
            Iterator i = fileItems.iterator();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");    
            String sFile[]=new String[1];
            HttpSession session = request.getSession(true);
            SmProperties.folderPath = request.getRealPath("") + File.separator + "cropData" + File.separator;
            SmProperties.folderPath=SmProperties.folderPath.replace(File.separator+"app"+File.separator, File.separator+"zData"+File.separator);
            String uploadPath = SmProperties.folderPath + "products" + File.separator;
            String target=SmProperties.folderPath + "company-" + session.getAttribute("companyKey") +File.separator+ "products"+File.separator;
            File toCheckFilePath = new File(uploadPath);
            if (!toCheckFilePath.exists()) {
                toCheckFilePath.mkdirs();
            }
            String companyKey = session.getAttribute("companyKey").toString();
            String itemName = null;
            String itemDesc = null;
            String partNo = null;
            String SKUno = null;
            String barcode = null;
            String quantity = null;
            String quantityKey = null;
            String price = null;
            String currencyKey = null;
            String tax = null;
            String picsCount = "";
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    if (fi.getFieldName().equals("itemName")) {
                        itemName = fi.getString();
                    } else if (fi.getFieldName().equals("itemDesc")) {
                        itemDesc = fi.getString();
                    } else if (fi.getFieldName().equals("partNo")) {
                        partNo = fi.getString();
                    } else if (fi.getFieldName().equals("SKUno")) {
                        SKUno = fi.getString();
                    } else if (fi.getFieldName().equals("barcode")) {
                        barcode = fi.getString();
                    } else if (fi.getFieldName().equals("quantity")) {
                        quantity = fi.getString();
                    } else if (fi.getFieldName().equals("quantityKey")) {
                        quantityKey = fi.getString();
                    } else if (fi.getFieldName().equals("price")) {
                        price = fi.getString();
                    } else if (fi.getFieldName().equals("currencyKey")) {
                        currencyKey = fi.getString();
                    } else if (fi.getFieldName().equals("tax")) {
                        tax = fi.getString();
                    }
                } else if (!fi.isFormField()) {
                    if (fi.getName() != null && (!fi.getName().equals(""))) {
                        String milliSecond = System.currentTimeMillis() + "";
                        filePath = uploadPath + milliSecond + ".png";
                        file = new File(filePath);
                        try {
                            fi.write(file);
                            sFile[0]=milliSecond + ".png"; 
                            FileCopy.copyFiles(uploadPath, target, sFile); 
                            picsCount += milliSecond + ".png@#@";
                        } catch (Exception ex) {
                            Logger.getLogger(addProductToCatalog2.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
            GlobalProductItemMaster.insert(companyKey, itemName, itemDesc, partNo, SKUno, barcode, quantity, quantityKey, price, currencyKey, tax, picsCount);
            MemoryTest.test("footer start");
            System.gc();
            MemoryTest.test("footer end");
            response.sendRedirect("transactionsProductCatalog.jsp");
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at CreateFeed : " + ex.getMessage());
        }
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }
}

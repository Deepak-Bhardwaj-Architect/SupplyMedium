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
import supply.medium.home.database.BusinessCategoryMaster;
import supply.medium.home.database.CompanyBusinessCategoryMaster;
import supply.medium.home.database.CompanyMailingAddressMaster;
import supply.medium.home.database.CompanyMaster;
import supply.medium.utility.CreateFolder;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

public class UpdateCompanyProfile extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    //private int maxFileSize = 50 * 1024;
    //private int maxMemSize = 4 * 1024;
    private File file;

    public void init() {
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
            HttpSession session = request.getSession(true);
            String companyKey = session.getAttribute("companyKey").toString();
            String userKey = session.getAttribute("userKey").toString();
            String category = null;
            String branch = null;
            String country = null;
            String address = null;
            String city = null;
            String zipcode = null;
            String state = null;
            String branch2 = null;
            String country2 = null;
            String address2 = null;
            String city2 = null;
            String state2 = null;
            String zipcode2 = null;
            String branch3 = null;
            String country3 = null;
            String address3 = null;
            String city3 = null;
            String state3 = null;
            String zipcode3 = null;
            String signupas = null;
            String addressCount = null;
            FileItem forFile = null;

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    if (fi.getFieldName().equals("businesscategory2")) {
                        category = fi.getString();
                    } else if (fi.getFieldName().equals("branch_0_value")) {
                        branch = fi.getString();
                    } else if (fi.getFieldName().equals("countryregion_0_value")) {
                        country = fi.getString();
                    } else if (fi.getFieldName().equals("address_0_value")) {
                        address = fi.getString();
                    } else if (fi.getFieldName().equals("city_0_value")) {
                        city = fi.getString();
                    } else if (fi.getFieldName().equals("zipcode_0_value")) {
                        zipcode = fi.getString();
                    } else if (fi.getFieldName().equals("state_0")) {
                        state = fi.getString();
                    } else if (fi.getFieldName().equals("branch_1")) {
                        branch2 = fi.getString();
                    } else if (fi.getFieldName().equals("countryregion_1")) {
                        country2 = fi.getString();
                    } else if (fi.getFieldName().equals("address_1")) {
                        address2 = fi.getString();
                    } else if (fi.getFieldName().equals("city_1")) {
                        city2 = fi.getString();
                    } else if (fi.getFieldName().equals("state_1")) {
                        state2 = fi.getString();
                    } else if (fi.getFieldName().equals("zipcode_1")) {
                        zipcode2 = fi.getString();
                    } else if (fi.getFieldName().equals("branch_2")) {
                        branch3 = fi.getString();
                    } else if (fi.getFieldName().equals("countryregion_2")) {
                        country3 = fi.getString();
                    } else if (fi.getFieldName().equals("address_2")) {
                        address3 = fi.getString();
                    } else if (fi.getFieldName().equals("city_2")) {
                        city3 = fi.getString();
                    } else if (fi.getFieldName().equals("state_2")) {
                        state3 = fi.getString();
                    } else if (fi.getFieldName().equals("zipcode_2")) {
                        zipcode3 = fi.getString();
                    } else if (fi.getFieldName().equals("addresscount")) {
                        addressCount = fi.getString();
                    }
                    else if (fi.getFieldName().equals("signupas")) {
                        signupas = fi.getString();
                    }

                } else if (!fi.isFormField()) {
                    forFile = fi;
                }
            }

            if (forFile != null) {
                if (forFile.getName() != null && forFile.getName() != "") {

                    SmProperties.folderPath = request.getServletContext().getRealPath("") + File.separator + "cropData" + File.separator;
                    CreateFolder companies = new CreateFolder();
                    companies.createFolder(SmProperties.folderPath + "company-" + companyKey);
                    filePath=SmProperties.folderPath+ "company-" + companyKey ;
                    file = new File(filePath + File.separator + "company-" + companyKey + ".png");

                    try {
                        forFile.write(file);
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateCompanyProfile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            System.out.println("signupas :"+signupas +"nd companyKey :"+companyKey);
            CompanyMaster.update(signupas, companyKey);
            session.setAttribute("companyType", signupas);
            CompanyMailingAddressMaster.delete(companyKey);

            int result = CompanyBusinessCategoryMaster.delete(companyKey);

            String bcid[] = category.split(",");
            for (int ii = 0; ii < bcid.length; ii++) {
                CompanyBusinessCategoryMaster.insert("" + companyKey, BusinessCategoryMaster.showBusinessCategoryKeyByName(bcid[ii]));
            }

            int result3 = CompanyMailingAddressMaster.insert(companyKey + "", branch,
                    country, address, city, state,
                    zipcode, "Permanent", "0");

            out.print("addressCount" + addressCount);
            if (addressCount.equals("2")) {
                int result4 = CompanyMailingAddressMaster.insert(companyKey + "", branch2,
                        country2, address2, city2, state2,
                        zipcode2, "Permanent", "0");
            } else if (addressCount.equals("3")) {
                int result4 = CompanyMailingAddressMaster.insert(companyKey + "", branch2,
                        country2, address2, city2, state2,
                        zipcode2, "Permanent", "0");
                int result5 = CompanyMailingAddressMaster.insert(companyKey + "", branch3,
                        country3, address3, city3, state3,
                        zipcode3, "Permanent", "0");
            }
            TestMemory.test("footer start");
            System.gc();
            TestMemory.test("footer end");
            response.sendRedirect("adminCompanyProfile.jsp");
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at UpdateCompanyProfile : " + ex.getMessage());
        }
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }
}

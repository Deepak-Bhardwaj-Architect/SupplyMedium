package supply.medium.home.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import supply.medium.home.database.CompanyAdvertisementMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

public class CreateCompanyAdvertisement extends HttpServlet {

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
            String hoverText = null;
            String linkPage = null;
            String imagePath = null;
            FileItem forFile = null;
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    if (fi.getFieldName().equals("hoverText")) {
                        hoverText = fi.getString();
                    } else if (fi.getFieldName().equals("linkPage")) {
                        linkPage = fi.getString();
                    }
                } 
                else if (!fi.isFormField()) {
                    forFile = fi;
                }
            }
            
                /**
                 * *******************create directory************************
                 */
                SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
                filePath = SmProperties.folderPath + "company-" + companyKey + File.separator + "ad";
                imagePath = hoverText.replace(" ", "-") + "-" + System.currentTimeMillis() + ".png";
                File uploadDir = new File(filePath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                /**
                 * *******************end create
                 * directory***********************
                 */
                /**
                 * ***************Write file****************
                 */
                //out.print(forFile);
                if (forFile.getName() != null && forFile.getName() != "") {
                    String fileExt = "";
                    fileExt = forFile.getName();
                    fileExt = fileExt.substring(fileExt.lastIndexOf("."));
                    file = new File(filePath + File.separator + imagePath);
                    forFile.write(file);
                }
                /**
                 * *******************end write file***********************
                 */
                out.println("</body>");
                out.println("</html>");
                // response.sendRedirect("successResponse.jsp");
                if(!linkPage.startsWith("http://") && !linkPage.startsWith("https://"))
                    linkPage="http://"+linkPage;

            int result = CompanyAdvertisementMaster.insert(companyKey, userKey,
                    hoverText, linkPage, imagePath.replace(File.separator, "@#@#@"));
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
            if (result == 1) {
                response.sendRedirect("adminAdvertisement.jsp?result=success");
            } else {
                response.sendRedirect("adminAdvertisement.jsp?result=failed");
            }
        } catch (Exception ex) {
            out.print(ex);
        }
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }
}

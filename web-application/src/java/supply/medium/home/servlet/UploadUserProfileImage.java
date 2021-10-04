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
import supply.medium.home.database.CompanyAdvertisementMaster;
import supply.medium.home.database.FeedMaster;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.FileCopy;
import supply.medium.utility.SmProperties;
import supply.medium.utility.MemoryTest;

public class UploadUserProfileImage extends HttpServlet {

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
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        HttpSession session = request.getSession(true);
                       
                        SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
                                
                        filePath = SmProperties.folderPath + "users";
                        file = new File(filePath);
                        if(!file.exists())
                            {
                                file.mkdir();
                            }
                        
                        filePath = SmProperties.folderPath + "users/"+File.separator + session.getAttribute("userKey").toString() + ".png";
                        file = new File(filePath);
                        try {
                            fi.write(file);
                        } catch (Exception ex) {
                            Logger.getLogger(UploadUserProfileImage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        String source=SmProperties.folderPath + "users"+File.separator;
                        String target=SmProperties.folderPath + "company-" + session.getAttribute("companyKey") + File.separator;
                        String fileNames[]={session.getAttribute("userKey").toString() + ".png"};
                        FileCopy.copyFiles(source, target, fileNames);
                }
            }
            MemoryTest.test("footer start");
                System.gc();
                MemoryTest.test("footer end");
            response.sendRedirect("userAccountSettings.jsp");
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at UploadUserProfileImage : " + ex.getMessage());
        }
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }
}

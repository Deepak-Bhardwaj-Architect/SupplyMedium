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
import supply.medium.home.database.FeedMaster;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

public class CreateFeed extends HttpServlet {

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
            String feedType = null;
            String redirectTo = null;
            String companyKey = session.getAttribute("companyKey").toString();
            String userKey = session.getAttribute("userKey").toString();
            String departmentKey = null;
            String isFeedCompanyWide = null;
            String feedTitle = null;
            String feedDescription = null;
            String fileName = null;
            FileItem forFile = null;
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    if (fi.getFieldName().equals("feedType")) {
                        feedType = fi.getString();
                    } else if (fi.getFieldName().equals("departmentKey")) {
                        departmentKey = fi.getString();
                    } else if (fi.getFieldName().equals("isFeedCompanyWide")) {
                        isFeedCompanyWide = fi.getString();
                    } else if (fi.getFieldName().equals("feedTitle")) {
                        feedTitle = fi.getString();
                    } else if (fi.getFieldName().equals("feedDescription")) {
                        feedDescription = fi.getString();
                    } else if (fi.getFieldName().equals("redirectTo")) {
                        redirectTo = fi.getString();
                    }
                } else if (!fi.isFormField()) {
                    forFile = fi;
                }
            }
            SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
            if (redirectTo.equals("newsroom")) {
                if (forFile != null) {
                    if (forFile.getName() != null && forFile.getName() != "") {
                        String milliSecond = System.currentTimeMillis() + "";
                        fileName = "company-" + session.getAttribute("companyKey") +  "/user feed/" +
                                 "feed-" + milliSecond + ".png";

                        filePath = SmProperties.folderPath + "company-" + session.getAttribute("companyKey") + File.separator +"user feed"+ File.separator +
                                 "feed-" + milliSecond + ".png";

                        file = new File(filePath);

                        forFile.write(file);
                    }
                }
                int result = FeedMaster.insert(feedType, companyKey, userKey, departmentKey,
                        isFeedCompanyWide, feedTitle, feedDescription, fileName);
                response.sendRedirect("home.jsp");
            } else if (redirectTo.equals("myFeed")) {
                if (forFile != null) {
                    if (forFile.getName() != null && forFile.getName() != "") {
                        String milliSecond = System.currentTimeMillis() + "";
                        fileName = "company-" + session.getAttribute("companyKey") +  "/user feed/"+  
                                 "feed-" + milliSecond + ".png";

                        filePath = SmProperties.folderPath + "company-" + session.getAttribute("companyKey") +File.separator+ "user feed"+File.separator
                                + "feed-" + milliSecond + ".png";

                        file = new File(filePath);
                        forFile.write(file);
                    }
                }
                int result = FeedMaster.insert(feedType, companyKey, userKey, departmentKey,
                        isFeedCompanyWide, feedTitle, feedDescription, fileName);
                response.sendRedirect("homeMyFeeds.jsp");
            } else if (redirectTo.equals("internalFeed")) {
                if (forFile != null) {
                    if (forFile.getName() != null && forFile.getName() != "") {
                        String milliSecond = System.currentTimeMillis() + "";
                        fileName = "company-" + session.getAttribute("companyKey") +"/department feed/"
                                + "feed-" + milliSecond + ".png";

                        filePath = SmProperties.folderPath + "company-" + session.getAttribute("companyKey") +File.separator+ "user feed"+File.separator
                                + "feed-" + milliSecond + ".png";

                        file = new File(filePath);
                        forFile.write(file);
                    }
                }
                int result = FeedMaster.insert(feedType, companyKey, userKey, departmentKey,
                        isFeedCompanyWide, feedTitle, feedDescription, fileName);
                response.sendRedirect("corporate.jsp");
            } else if (redirectTo.equals("announcement")) {
                if (forFile != null) {
                    if (forFile.getName() != null && forFile.getName() != "") {
                        String milliSecond = System.currentTimeMillis() + "";
                        fileName = "company-" + session.getAttribute("companyKey") +"/department feed/"
                                + "feed-" + milliSecond + ".png";

                        filePath = SmProperties.folderPath + "company-" + session.getAttribute("companyKey") +File.separator+ "user feed"+File.separator
                                + "feed-" + milliSecond + ".png";

                        file = new File(filePath);
                        forFile.write(file);
                    }
                }
                int result = FeedMaster.insert(feedType, companyKey, userKey, departmentKey,
                        isFeedCompanyWide, feedTitle, feedDescription, fileName);
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
                response.sendRedirect("corporateDepartmentPage.jsp?open=announcement&key=" + departmentKey);
            }
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

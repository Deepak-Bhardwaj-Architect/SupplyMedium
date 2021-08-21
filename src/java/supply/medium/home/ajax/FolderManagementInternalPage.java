package supply.medium.home.ajax;

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
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

public class FolderManagementInternalPage extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {
        HttpSession session = request.getSession(true);
        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
      //Object obj=request.getAttribute("file_name2" );
        //out.println(obj);
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
        //upload.setSizeMax( maxFileSize );

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);
//	
//      // Process the uploaded file items
            Iterator i = fileItems.iterator();
//      out.println("i.hasNext ()---------"+i.hasNext ());
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet upload</title>");
            out.println("</head>");
            out.println("<body>");
            String folderName = null;
            FileItem forFile = null;
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    folderName = fi.getString();
                } else if (!fi.isFormField()) {
                    forFile = fi;
                }

            }
            SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
            String pth = SmProperties.folderPath + "company-" + session.getAttribute("companyKey") +File.separator+ "internal feed" +File.separator+ folderName + File.separator;
            File fl = new File(pth);
            if (!fl.exists()) {
                fl.mkdirs();
            }
            filePath = pth + forFile.getName();
            file = new File(filePath);
            forFile.write(file);
            out.print("filePath :" + filePath);
            out.println("</body>");
            out.println("</html>");
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
            response.sendRedirect("corporate.jsp?folder="+folderName);
        } catch (Exception ex) {
            ErrorMaster.insert("Error on FolderManagementInternalPage servlet :" + ex.getMessage());
        }
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }
}

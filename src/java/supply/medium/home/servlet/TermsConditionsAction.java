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
import org.apache.commons.io.IOUtils;
import supply.medium.home.bean.TermsConditionsBean;
import supply.medium.home.database.FeedMaster;
import supply.medium.home.database.TermsConditionsMaster;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.SmProperties;
import supply.medium.utility.TestMemory;

public class TermsConditionsAction extends HttpServlet {

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
            String transactionType = null;
            String textMessage = null;
            FileItem file=null;
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    if (fi.getFieldName().equals("transactionType")) {
                        transactionType = fi.getString();
                    } else if (fi.getFieldName().equals("textMessage")) {
                        textMessage = fi.getString();
                    } 
                } else if (!fi.isFormField()) {
                    file=fi;                    
                }
            }            
            if(file!=null)
            {
                //out.print("file.getName() "+file.getName());
                if(file.getName()!=null && !file.getName().trim().equals(""))
                {
                InputStream in=file.getInputStream();
                textMessage=IOUtils.toString(in,"UTF-8");
                }
            }
            TermsConditionsBean tcb= TermsConditionsMaster.show(companyKey, transactionType);
            
            if(tcb==null)
            {
            TermsConditionsMaster.insert(companyKey, transactionType, textMessage);
            }
            else
            {
             TermsConditionsMaster.update(companyKey, transactionType, textMessage);
            }
           
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
            if(transactionType.equals("RFQ"))
            {
               response.sendRedirect("transactionsTaC.jsp"); 
            }    
            else if(transactionType.equals("QTE"))
            {
               response.sendRedirect("transactionsTaCq.jsp"); 
            }    
            else if(transactionType.equals("PO"))
            {
               response.sendRedirect("transactionsTaCp.jsp"); 
            }    
            else if(transactionType.equals("INV"))
            {
               response.sendRedirect("transactionsTaCi.jsp"); 
            }
             
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at TermsConditionasAction : " + ex.getMessage());
        }
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }
}

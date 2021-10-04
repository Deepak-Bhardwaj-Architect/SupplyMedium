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
import supply.medium.home.bean.CompanyDetailsForVrBean;
import supply.medium.home.database.CompanyMaster;
import supply.medium.home.database.NotificationMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.home.database.VendorRegistrationMaster;
import supply.medium.home.mailing.ActivationMailing;
import supply.medium.home.mailing.HTMLMailComposer;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.FileCopy;
import supply.medium.utility.SmProperties;
import supply.medium.utility.MemoryTest;

public class VendorRegistration extends HttpServlet {

    private boolean isMultipart;
    private String filePath;
    //private int maxFileSize = 50 * 1024;
    //private int maxMemSize = 4 * 1024;
    private File file;
    private File file2;

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
            String companyKeyFrom = session.getAttribute("companyKey").toString();
            String companyKeyTo = null;
            String userKeyFrom = session.getAttribute("userKey").toString();
            String userKeyTo = null;
            String requestSenderType = null;
            String companyType = null;
            String businessContactName = session.getAttribute("userName").toString();
            String businessTaxId = null;
            String naicsCode = null;
            String w9taxFormStatus = null;
            String w9taxFormPath = null;
            String businessSize = null;
            String businessClassification = "";
            String additionalDetails = null;
            String bc[] = {"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"};

            FileItem forFile = null;

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (fi.isFormField()) {
                    if (fi.getFieldName().equals("comtype")) {
                        companyType = fi.getString();
                    } else if (fi.getFieldName().equals("taxid")) {
                        businessTaxId = fi.getString();
                    } else if (fi.getFieldName().equals("naicscode")) {
                        naicsCode = fi.getString();
                    } else if (fi.getFieldName().equals("w9form_flag")) {
                        w9taxFormStatus = fi.getString();
                    } else if (fi.getFieldName().equals("buss_size")) {
                        businessSize = fi.getString();
                    } else if (fi.getFieldName().equals("Disadvantaged")) {
                        if (fi.getString() != null) {
                            bc[0] = "1";
                        }
                        out.print(businessClassification);
                    } else if (fi.getFieldName().equals("HubZone")) {
                        if (fi.getString() != null) {
                            bc[1] = "1";
                        }
                    } else if (fi.getFieldName().equals("WomenOwned")) {
                        if (fi.getString() != null) {
                            bc[2] = "1";
                        }
                    } else if (fi.getFieldName().equals("HandicappedOwned")) {
                        if (fi.getString() != null) {
                            bc[3] = "1";
                        }
                    } else if (fi.getFieldName().equals("VETERANOWNED")) {
                        if (fi.getString() != null) {
                            bc[4] = "1";
                        }
                    } else if (fi.getFieldName().equals("SDVETERANOWNED")) {
                        if (fi.getString() != null) {
                            bc[5] = "1";
                        }
                    } else if (fi.getFieldName().equals("HBCORMI")) {
                        if (fi.getString() != null) {
                            bc[6] = "1";
                        }
                    } else if (fi.getFieldName().equals("MBE")) {
                        if (fi.getString() != null) {
                            bc[7] = "1";
                        }
                    } else if (fi.getFieldName().equals("NonProfit")) {
                        if (fi.getString() != null) {
                            bc[8] = "1";
                        }
                    } else if (fi.getFieldName().equals("Foreign")) {
                        if (fi.getString() != null) {
                            bc[9] = "1";
                        }
                    } else if (fi.getFieldName().equals("PublicSector")) {
                        if (fi.getString() != null) {
                            bc[10] = "1";
                        }
                    } else if (fi.getFieldName().equals("ANCORITNSB")) {
                        if (fi.getString() != null) {
                            bc[11] = "1";
                        }
                    } else if (fi.getFieldName().equals("ANCORITNCD")) {
                        if (fi.getString() != null) {
                            bc[12] = "1";
                        }
                    } else if (fi.getFieldName().equals("additional_det")) {
                        additionalDetails = fi.getString();
                    } else if (fi.getFieldName().equals("selectedVendorKey")) {
                        companyKeyTo = fi.getString();
                        userKeyTo=UserMaster.showAdminKeyFromCompanyKey(companyKeyTo)+"";
                    } else if (fi.getFieldName().equals("sendertype")) {
                        requestSenderType = fi.getString();
                    }
                } else if (!fi.isFormField()) {
                    forFile = fi;
                }
            }
            for(int ii=0;ii<bc.length;ii++)
                businessClassification+=bc[ii];

            naicsCode="0";
            
            SmProperties.folderPath = request.getRealPath("")+ File.separator + "cropData" + File.separator;
            
            filePath=SmProperties.folderPath;
            
            String fname=""+System.currentTimeMillis();
            
            if (forFile != null) {
                if (forFile.getName() != null && forFile.getName() != "") {

                    String fn=forFile.getName();
                    fn=fn.replace(".", "@#@");
                    fname+="."+fn.split("@#@")[1];
                    w9taxFormPath=fname;
                    
                    String source=filePath + "company-" + companyKeyFrom + File.separator+"w9forms"+File.separator + fname;
                    String target=filePath + "company-" + companyKeyTo + File.separator+"w9forms"+File.separator + fname;
                    
                    file = new File(source);

                    try {
                        forFile.write(file);
                    FileCopy.copyCompleteFile(source, target);
                    } catch (Exception ex) {
                        Logger.getLogger(UpdateCompanyProfile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if(w9taxFormStatus!=null)
            {
                w9taxFormStatus="yes";
            }
            else
            {
                 w9taxFormStatus="no";   
            }
            if(w9taxFormPath==null)
            {
                w9taxFormPath="";
            }
            String vrKey=VendorRegistrationMaster.insert(companyKeyFrom, companyKeyTo, userKeyFrom, userKeyTo, requestSenderType, companyType, businessContactName, businessTaxId, naicsCode, w9taxFormStatus, w9taxFormPath, businessSize, businessClassification, additionalDetails,"Form Sent");
            
            userKeyTo=UserMaster.showAdminKeyFromCompanyKey(companyKeyTo)+"";
            
            NotificationMaster.insert(userKeyFrom, userKeyTo, companyKeyFrom, companyKeyTo, "Vendor Registration@#@"+requestSenderType, vrKey, "VR Form Sent");
            
            HTMLMailComposer composer = new HTMLMailComposer();
            
            CompanyDetailsForVrBean cb=CompanyMaster.showCompanyDetailsForVrProcess(companyKeyTo);
            CompanyDetailsForVrBean cb2=CompanyMaster.showCompanyDetailsForVrProcess(companyKeyFrom);

            String message = composer.composeVendorRegistrationMail(cb.getCompanyName(), cb2.getCompanyName()+",  who is currently registered with Supply Medium, has requested to be your registered vendor. You can add them as your registered vendor by clicking the link below.");

            composer = null;

            ActivationMailing am = new ActivationMailing();
            
            String emailTo[]={cb.getEmail()};

            am.composeAndSendMail(emailTo, "Supply Medium - Vendor Registration Request", message.toString(), "SM Vendor Registration");
            //out.print("requestSenderType "+requestSenderType);
            MemoryTest.test("footer start");
                System.gc();
                MemoryTest.test("footer end");
            if(requestSenderType.equals("Buyer"))
            {
             response.sendRedirect("suppliersVR.jsp");   
            }
            else
            {
            response.sendRedirect("buyersVR.jsp");
            }
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at VendorRegistration : " + ex.getMessage());
        }
    }

    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {

        throw new ServletException("GET method used with "
                + getClass().getName() + ": POST method required.");
    }
}

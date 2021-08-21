/*
 * 
 * 
 * 
 */
package supply.medium.home.servlet;

import java.io.*;
import java.sql.*;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.ImportProductFromExcel;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Lenovo
 */
public class UploadProductFromCsv extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UploadProductFromCsv</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UploadProductFromCsv at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(true);


            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

//      // Process the uploaded file items
            Iterator i = fileItems.iterator();
            FileItem forFile = null;
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    forFile = fi;
                }
            }
            int result = ImportProductFromExcel.uploadCsvData(session.getAttribute("companyKey").toString(), forFile);
                response.sendRedirect("transactionsProductCatalog.jsp?"+result);

        } catch (Exception ex) {
            ErrorMaster.insert("Exception at doPost() in UploadProductFromCsv : " + ex.getMessage());
        } finally {
            out.close();
        }
    }

    ;

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

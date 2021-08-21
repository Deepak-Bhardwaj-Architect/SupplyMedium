/*
 * 
 * 
 * 
 */
package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.database.CompanyMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class VerifiactionAndValidatioin extends HttpServlet {

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
            String verify = request.getParameter("verify");
            if (verify.equals("companyId")) {
                int result = 1;
                String companyId = request.getParameter("companyId");
                result = CompanyMaster.checkExistCompanyId(companyId);
                out.print(result);
            }
            if (verify.equals("phoneNo")) {
                int result = 1;
                String phoneNo = request.getParameter("phoneNo");
                result = UserMaster.checkExistUserPhoneNo(phoneNo);
                out.print(result);
            }
            if (verify.equals("email")) {
                int result = 1;
                String email = request.getParameter("email");
                result = UserMaster.checkExistUserEmail(email);
                out.print(result);
            }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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

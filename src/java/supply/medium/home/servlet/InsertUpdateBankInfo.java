/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package supply.medium.home.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.database.BankInfoMaster;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author Intel
 */
public class InsertUpdateBankInfo extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        String companyKey = session.getAttribute("companyKey").toString();
        String acName = request.getParameter("acName");
        String acNo = request.getParameter("acNo");
        String swiffCode = request.getParameter("swiffCode");
        String ifscCode = request.getParameter("ifscCode");
        String branchAddress = request.getParameter("branchAddress");
        String paypalAcName = request.getParameter("paypalAcName");
        String paypalEmail = request.getParameter("paypalEmail");

        PrintWriter out = response.getWriter();
        try {
            int result=BankInfoMaster.showBankInfo(companyKey);
            if (result == 0) {
                result = BankInfoMaster.insert(companyKey, acName, acNo, swiffCode, ifscCode, branchAddress, paypalAcName, paypalEmail);
            } else {
                result = BankInfoMaster.update(companyKey, acName, acNo, swiffCode, ifscCode, branchAddress, paypalAcName, paypalEmail);
            }
            MemoryTest.test("footer start");
            System.gc();
            MemoryTest.test("footer end");
            if(result>0)
            response.sendRedirect("adminBank.jsp?msg=success");
            else
            response.sendRedirect("adminBank.jsp?msg=fail");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

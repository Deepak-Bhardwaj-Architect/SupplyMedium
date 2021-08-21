/*
 * 
 * 
 * 
 */
package supply.medium.home.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.database.UserMailingAddressMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class UpdateUserSettingByUser extends HttpServlet {

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
        PrintWriter out = response.getWriter(); try { 
            /* TODO output your page here. You may use following sample code. */
            String userKey = request.getParameter("userKey");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String phone = request.getParameter("phone");
            String mobile = request.getParameter("mobile");
            String userType = request.getParameter("userType");
            String department = request.getParameter("department");
            String userRole = request.getParameter("userRole");
            String city = request.getParameter("city");            
            String adrs = request.getParameter("adrs");
            String zipcode = request.getParameter("zipcode");
            String fax = request.getParameter("fax");
            String redirectTo=request.getParameter("redirectTo");
            int result = 0;
            result = UserMaster.updateUserSettingByAdmin(userKey, firstName, lastName, phone,
                    mobile, fax, userType, department, userRole);
            result = UserMailingAddressMaster.updateUserAddressByAdmin(userKey, city,adrs, zipcode);
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
            if(redirectTo.equals("userAccountSetting"))
            {
                response.sendRedirect("userAccountSettings.jsp");
            }
            else
            {
            response.sendRedirect("adminListUsers.jsp");
            }
        } finally {
            out.close();
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

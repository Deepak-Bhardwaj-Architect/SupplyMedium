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
import supply.medium.home.bean.UserBean;
import supply.medium.home.database.UserMaster;
import supply.medium.home.mailing.ActivationMailing;
import supply.medium.home.mailing.HTMLMailComposer;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class UpdateUserSettingByAdmin extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try {
            String eventType = request.getParameter("eventType");
            if (eventType.equals("updateUserStatus")) {
                String userKey = request.getParameter("userKey");
                String status = request.getParameter("status");
                UserMaster.updateUserStatusByAdmin(userKey, status);
                
                    UserBean ub = UserMaster.showUserDetails(userKey);
                    String[] to = {ub.getEmail()};

                    String sub = "SupplyMedium login activation link";

                    HTMLMailComposer composer = new HTMLMailComposer();

                    String message = composer.sendUserStatusChangeMail(ub.getEmail(), ub.getFirstName(),status);

                    composer = null;

                    ActivationMailing am = new ActivationMailing();

                    am.composeAndSendMail(to, sub, message.toString(), "SM Registration");
                    
            } else if (eventType.equals("deleteUser")) {
                String userKey = request.getParameter("userKey");
                UserMaster.deleteUser(userKey);
            }
            TestMemory.test("footer start");
            System.gc();
            TestMemory.test("footer end");
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

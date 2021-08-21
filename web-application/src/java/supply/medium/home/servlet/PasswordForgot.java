/*
 * 
 * 
 * 
 */
package supply.medium.home.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.database.UserMaster;
import supply.medium.home.mailing.ActivationMailing;
import supply.medium.home.mailing.HTMLMailComposer;
import supply.medium.utility.MemoryTest;

/**
 *
 * @author LenovoB560
 */
public class PasswordForgot extends HttpServlet {

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
            
            String emailId[]={request.getParameter("email")};
            HTMLMailComposer mail=new HTMLMailComposer();
            String firstName=UserMaster.showUserDetailsByUserKey(emailId[0]).getFirstName();
            String message=mail.composeResetPasswordMail(UUID.randomUUID( ).toString( ), emailId[0], firstName);
            ActivationMailing am = new ActivationMailing( );
            am.composeAndSendMail( emailId, "Password Reset Information for Supply Medium Account", message.toString( ),"SM Password Reset" );
            MemoryTest.test("footer start");
                System.gc();
                MemoryTest.test("footer end");
            response.sendRedirect("emailPasswordForgot.jsp");
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

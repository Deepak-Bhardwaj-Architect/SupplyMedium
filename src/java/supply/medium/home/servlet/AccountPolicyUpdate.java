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
import javax.servlet.http.HttpSession;
import supply.medium.home.database.AccountPolicyMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class AccountPolicyUpdate extends HttpServlet {

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
            String accountPolicyKey = request.getParameter("accountPolicyKey");
            String enforcePasswordHistory = request.getParameter("enforcePasswordHistory");
            String maxPasswordAge = request.getParameter("maxPasswordAge");
            String minPasswordAge = request.getParameter("minPasswordAge");
            String minPasswordLength = request.getParameter("minPasswordLength");
            String sendEmailBeforePasswordExpire = request.getParameter("sendEmailBeforePasswordExpire");
            String sendDailyReminderAfterDate = request.getParameter("sendDailyReminderAfterDate");
            String passwordComplexity = request.getParameter("passwordComplexity");
            String upperLowerCaseLetter = request.getParameter("upperLowerCaseLetter");
            String numericalCharacters = request.getParameter("numericalCharacters");
            String nonAlphanummericCharacters = request.getParameter("nonAlphanummericCharacters");
            String accountLockAfterAttempts = request.getParameter("accountLockAfterAttempts");
            String lockoutDuration = request.getParameter("lockoutDuration");
            String resetLockoutCounterAfter = request.getParameter("resetLockoutCounterAfter");
            String accountUnlockedByAdmin = request.getParameter("accountUnlockedByAdmin");
            String sessionEndTime = request.getParameter("sessionEndTime");
            HttpSession session=request.getSession(true);
            session.setMaxInactiveInterval(60*(Integer.parseInt(sessionEndTime)));

            if (sendDailyReminderAfterDate == null) {
                sendDailyReminderAfterDate="no";
            }
            if (upperLowerCaseLetter == null) {
                upperLowerCaseLetter="no";
            }
            if (numericalCharacters == null) {
                numericalCharacters="no";
            }
            if (nonAlphanummericCharacters == null) {
                nonAlphanummericCharacters="no";
            }
            if (accountUnlockedByAdmin == null) {
                accountUnlockedByAdmin="no";
            }
            int result=AccountPolicyMaster.update(accountPolicyKey, enforcePasswordHistory, maxPasswordAge, minPasswordAge, minPasswordLength, sendEmailBeforePasswordExpire, sendDailyReminderAfterDate, passwordComplexity, upperLowerCaseLetter, numericalCharacters, nonAlphanummericCharacters, accountLockAfterAttempts, lockoutDuration, resetLockoutCounterAfter, accountUnlockedByAdmin, sessionEndTime);
            TestMemory.test("footer start");
                System.gc();
                TestMemory.test("footer end");
            if(result>=0)
                response.sendRedirect("adminAccountPolicies.jsp");
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

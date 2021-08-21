/*
 * 
 * 
 * 
 */
package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import supply.medium.home.database.UserMaster;
import supply.medium.home.database.UserSettingMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author Intel
 */
public class UserAccountSetting extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter(); try { 
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(true);
            String eventType = request.getParameter("eventType");
            int result = 0;
            if (eventType.equals("updateUserPassword")) {
                String password = request.getParameter("password");
                result = UserMaster.updateUserPassword(session.getAttribute("userKey").toString(), password);
            } else if (eventType.equals("updateNotificationSetting")) {
                String newEmail = request.getParameter("newEmail");
                String useRegisteredEmail = request.getParameter("useRegisteredEmail");
                String newMobileNo = request.getParameter("newMobileNo");
                String workinghoursNotify = request.getParameter("workinghoursNotify");
                String nonworkinghoursNotify = request.getParameter("nonworkinghoursNotify");
                String workinghoursMode = request.getParameter("workinghoursMode");
                String nonworkinghoursMode = request.getParameter("nonworkinghoursMode");
                String stopNotify = request.getParameter("stopNotify");
                String stoptimeFrom = request.getParameter("stoptimeFrom");
                String stoptimeTo = request.getParameter("stoptimeTo");
                String userKey = session.getAttribute("userKey").toString();

                if (useRegisteredEmail != null) {
                    useRegisteredEmail = "yes";
                } else {
                    useRegisteredEmail = "no";
                }
                if (workinghoursNotify != null) {
                    workinghoursNotify = "yes";
                } else {
                    workinghoursNotify = "no";
                }
                if (nonworkinghoursNotify != null) {
                    nonworkinghoursNotify = "yes";
                } else {
                    nonworkinghoursNotify = "no";
                }
                if (stopNotify != null) {
                    stopNotify = "yes";
                } else {
                    stopNotify = "no";
                }

                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
                stoptimeFrom = df.format(df2.parse(stoptimeFrom)) + "";
                stoptimeTo = df.format(df2.parse(stoptimeTo)) + "";

                result = UserSettingMaster.updateNotificationSetting(userKey, newEmail, useRegisteredEmail, newMobileNo, workinghoursNotify, nonworkinghoursNotify, workinghoursMode, nonworkinghoursMode, stopNotify, stoptimeFrom, stoptimeTo);
                out.print(result);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UserAccountSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UserAccountSetting.class.getName()).log(Level.SEVERE, null, ex);
        }
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

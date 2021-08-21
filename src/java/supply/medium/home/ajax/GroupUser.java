/*
 * 
 * 
 * 
 */
package supply.medium.home.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import supply.medium.home.bean.UserBean;
import supply.medium.home.database.GroupMaster;
import supply.medium.home.database.GroupUserMaster;
import supply.medium.home.database.UserMaster;
import supply.medium.utility.TestMemory;

/**
 *
 * @author LenovoB560
 */
public class GroupUser extends HttpServlet {

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
            int result = 0;
            if (request.getParameter("eventType").endsWith("addUserToGroup")) {
                result = GroupUserMaster.insert(request.getParameter("companyKey"), request.getParameter("groupKey"), request.getParameter("userKey"));
            } else if (request.getParameter("eventType").endsWith("deleteUserFromGroup")) {
                result = GroupUserMaster.delete(request.getParameter("companyKey"), request.getParameter("groupKey"), request.getParameter("userKey"));
            } else if (request.getParameter("eventType").endsWith("showNonExistingUserInGroup")) {
                ArrayList userList = UserMaster.showNonExistingUserInGroup(request.getParameter("companyKey"), request.getParameter("groupKey"));
                UserBean ub = null;
                for (int i = 0; i < userList.size(); i++) {
                    ub = (UserBean) userList.get(i);
                    out.print("<option value=" + ub.getUserKey() + ">" + ub.getFirstName() + " " + ub.getLastName() + "</option>");
                }
            } else if (request.getParameter("eventType").endsWith("showExistingUserInGroup")) {
                ArrayList userList = UserMaster.showExistingUserInGroup(request.getParameter("companyKey"), request.getParameter("groupKey"));
                UserBean ub = null;
                for (int i = 0; i < userList.size(); i++) {
                    ub = (UserBean) userList.get(i);
                    out.print("<option value=" + ub.getUserKey() + ">" + ub.getFirstName() + " " + ub.getLastName() + "</option>");
                }
            } else if (request.getParameter("eventType").endsWith("showExistingUserOfGroup")) {
                ArrayList userList = UserMaster.showExistingUserInGroup(request.getParameter("companyKey"), request.getParameter("groupKey"));
                UserBean ub = null;
                for (int i = 0; i < userList.size(); i++) {
                    ub = (UserBean) userList.get(i);
                    String userDiv = "<div class='mf_sub' id='group_user_id" + ub.getUserKey() + "'>";

                    userDiv += "<div class='t_shape'></div>";

                    userDiv += "<div class='user'></div>";

                    userDiv += "<div class='mf_content_text' id=''>" + ub.getFirstName() + " " + ub.getLastName() + "</div></div>";
                    out.print(userDiv);
                }
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
